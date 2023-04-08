package com.lavro;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;


public class EncryptedConversion extends Conversion<CharSequence> {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256;
    private static final byte[] DEFAULT_KEY = "MySecretKey12345".getBytes(StandardCharsets.UTF_8);
    private static final byte[] DEFAULT_IV = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
    private static SecretKey secretKey;
    private static IvParameterSpec iv;

    public EncryptedConversion()  {
        this(DEFAULT_KEY, DEFAULT_IV);
    }

    public EncryptedConversion(byte[] key, byte[] iv){
        this.secretKey = new SecretKeySpec(key, "AES");
        this.iv = new IvParameterSpec((Arrays.copyOf(iv, 16)));
    }

    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return EncryptedLogicalType.ENCRYPTED_LOGICAL_TYPE_NAME;
    }

    public static String encrypt(String plaintext) throws Exception {
        if(plaintext == null){
            return null;
        }
        byte[] new_data = plaintext.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getIV());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        int padding_len = cipher.getBlockSize() - (new_data.length % cipher.getBlockSize());
        byte[] padding = new byte[padding_len];
        for (int i = 0; i < padding_len; i++) {
            padding[i] = (byte) padding_len;
        }
        byte[] padded_data = new byte[new_data.length + padding_len];
        System.arraycopy(new_data, 0, padded_data, 0, new_data.length);
        System.arraycopy(padding, 0, padded_data, new_data.length, padding_len);
        byte[] encrypted = cipher.doFinal(padded_data);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getIV());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(encrypted);
        int padding_len = decrypted[decrypted.length - 1];
        byte[] unpadded_data = new byte[decrypted.length - padding_len];
        System.arraycopy(decrypted, 0, unpadded_data, 0, unpadded_data.length);
        return new String(unpadded_data, StandardCharsets.UTF_8);
    }

    public String toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        try {
//            String plaintext = Base64.getEncoder().encodeToString(value.array());
            String encrypted = encrypt(String.valueOf(value));
            System.out.println("When writing to Avro ToByteBuffer is :"+encrypted.toString());
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting value", e);
        }
    }

    @Override
    public CharSequence fromCharSequence(CharSequence bytes, Schema schema, LogicalType type) {
        try {
//            String encryptedText = Base64.getEncoder().encodeToString(bytes.array());
            String decryptedText = decrypt((String.valueOf(bytes)));
            System.out.println("When reading from the avro file FromByteBuffer is :"+decryptedText);
            return decryptedText;
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting value", e);
        }
    }
}
