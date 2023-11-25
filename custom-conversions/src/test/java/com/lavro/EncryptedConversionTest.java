package com.lavro;

import static com.lavro.EncryptedConversion.encrypt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.junit.jupiter.api.Test;

class EncryptedConversionTest {

  @Test
  public void testEncryptAndDecrypt() {
    // Mocking a value and schema for testing
    CharSequence valueToEncrypt = "TestValue";
    Schema schema = mock(Schema.class);
    LogicalType logicalType = mock(LogicalType.class);

    // Creating an instance of EncryptedConversion
    EncryptedConversion encryptedConversion = new EncryptedConversion();

    // Encrypt the value
    String encryptedValue = encryptedConversion.toCharSequence(valueToEncrypt, schema, logicalType);
    assertNotNull(encryptedValue);

    // Decrypt the value
    CharSequence decryptedValue = encryptedConversion.fromCharSequence(encryptedValue, schema, logicalType);
    assertNotNull(decryptedValue);

    // Assert that the decrypted value matches the original value
    assertEquals(valueToEncrypt.toString(), decryptedValue.toString());
  }

}