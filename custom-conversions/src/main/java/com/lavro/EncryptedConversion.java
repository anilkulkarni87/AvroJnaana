package com.lavro;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EncryptedConversion extends Conversion<CharSequence> {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ENV = "ENCRYPTED_LOGICAL_KEY";
    private static final String IV_ENV = "ENCRYPTED_LOGICAL_IV";
    private static final byte[] DEFAULT_KEY = "MySecretKey123456".getBytes(StandardCharsets.UTF_8);
    private static final byte[] DEFAULT_IV = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);

    private final SecretKey secretKey;
    private final IvParameterSpec iv;

    public EncryptedConversion() {
        this(loadKey(), loadIv());
    }

    public EncryptedConversion(byte[] key, byte[] ivBytes) {
        this.secretKey = new SecretKeySpec(normalizeToBlockSize(key), "AES");
        this.iv = new IvParameterSpec(normalizeToBlockSize(ivBytes));
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
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encryptedData) throws Exception {
        if (encryptedData == null) {
            return null;
        }
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
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
