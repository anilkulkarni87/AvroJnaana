package com.lavro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class EncryptedConversionTest {

  @Test
  void roundTripWithDefaultKey() {
    EncryptedConversion conversion = new EncryptedConversion();
    String plaintext = "TestValue";

    String cipher = conversion.toCharSequence(plaintext, null, null);
    assertNotNull(cipher);
    assertNotEquals(plaintext, cipher, "Ciphertext should not equal plaintext");

    CharSequence decrypted = conversion.fromCharSequence(cipher, null, null);
    assertEquals(plaintext, decrypted.toString());
  }

  @Test
  void roundTripWithCustomKeyAndIv() {
    byte[] key = "abcdefghijklmnop".getBytes();
    byte[] iv = "1234567890abcdef".getBytes();
    EncryptedConversion conversion = new EncryptedConversion(key, iv);
    String plaintext = "CustomKeyValue";

    String cipher = conversion.toCharSequence(plaintext, null, null);
    CharSequence decrypted = conversion.fromCharSequence(cipher, null, null);

    assertEquals(plaintext, decrypted.toString());
  }

  @Test
  void nullValuesPassThrough() {
    EncryptedConversion conversion = new EncryptedConversion();
    assertNull(conversion.toCharSequence(null, null, null));
    assertNull(conversion.fromCharSequence(null, null, null));
  }
}
