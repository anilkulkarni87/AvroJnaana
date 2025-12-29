package com.lavro;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EncryptedConversion extends Conversion<CharSequence> {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int GCM_IV_LENGTH = 12;
    private static final String KEY_ENV = "ENCRYPTED_LOGICAL_KEY";
    private static final String IV_ENV = "ENCRYPTED_LOGICAL_IV";
    private static final byte[] DEFAULT_KEY = "MySecretKey123456".getBytes(StandardCharsets.UTF_8);
    private static final byte[] DEFAULT_IV = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);

    private final SecretKey secretKey;
    private final byte[] baseIv;
    private final SecureRandom random = new SecureRandom();

    public EncryptedConversion() {
        this(loadKey(), loadIv());
    }

    public EncryptedConversion(byte[] key, byte[] ivBytes) {
        this.secretKey = new SecretKeySpec(normalizeToBlockSize(key), "AES");
        this.baseIv = normalizeIv(ivBytes);
    }

    private static byte[] loadKey() {
        String fromEnv = System.getenv(KEY_ENV);
        return fromEnv == null || fromEnv.isEmpty() ? DEFAULT_KEY : fromEnv.getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] loadIv() {
        String fromEnv = System.getenv(IV_ENV);
        return fromEnv == null || fromEnv.isEmpty() ? DEFAULT_IV : fromEnv.getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] normalizeToBlockSize(byte[] bytes) {
        // AES expects 16/24/32 byte keys; demo trims or pads to 16 bytes for simplicity
        return Arrays.copyOf(bytes, 16);
    }

    private static byte[] normalizeIv(byte[] bytes) {
        // GCM prefers 12 byte IVs; trim/pad to 12 for the demo
        return Arrays.copyOf(bytes, GCM_IV_LENGTH);
    }

    private byte[] nextIv() {
        byte[] iv = Arrays.copyOf(baseIv, GCM_IV_LENGTH);
        byte[] rand = new byte[GCM_IV_LENGTH];
        random.nextBytes(rand);
        for (int i = 0; i < GCM_IV_LENGTH; i++) {
            iv[i] = (byte) (iv[i] ^ rand[i]);
        }
        return iv;
    }

    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return EncryptedLogicalType.ENCRYPTED_LOGICAL_TYPE_NAME;
    }

    public String encrypt(String plaintext) throws Exception {
        if (plaintext == null) {
            return null;
        }
        byte[] ivBytes = nextIv();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        byte[] combined = new byte[ivBytes.length + encrypted.length];
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
        System.arraycopy(encrypted, 0, combined, ivBytes.length, encrypted.length);
        return Base64.getEncoder().encodeToString(combined);
    }

    public String decrypt(String encryptedData) throws Exception {
        if (encryptedData == null) {
            return null;
        }
        byte[] combined = Base64.getDecoder().decode(encryptedData);
        if (combined.length < GCM_IV_LENGTH) {
            throw new IllegalArgumentException("Invalid encrypted payload");
        }
        byte[] ivBytes = Arrays.copyOfRange(combined, 0, GCM_IV_LENGTH);
        byte[] cipherBytes = Arrays.copyOfRange(combined, GCM_IV_LENGTH, combined.length);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] decrypted = cipher.doFinal(cipherBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public String toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        try {
            if (value == null) {
                return null;
            }
            return encrypt(String.valueOf(value));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting value", e);
        }
    }

    @Override
    public CharSequence fromCharSequence(CharSequence bytes, Schema schema, LogicalType type) {
        try {
            if (bytes == null) {
                return null;
            }
            return decrypt(String.valueOf(bytes));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting value", e);
        }
    }
}
