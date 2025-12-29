package com.lavro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EmailLowercaseConversionTest {

  @Test
  void lowercasesEmailsBothWays() {
    EmailLowercaseConversion conversion = new EmailLowercaseConversion();
    String input = "User.Name@Example.COM";
    CharSequence stored = conversion.toCharSequence(input, null, null);
    CharSequence read = conversion.fromCharSequence(stored, null, null);
    assertEquals("user.name@example.com", stored.toString());
    assertEquals("user.name@example.com", read.toString());
  }

  @Test
  void nullPassThrough() {
    EmailLowercaseConversion conversion = new EmailLowercaseConversion();
    assertNull(conversion.toCharSequence(null, null, null));
    assertNull(conversion.fromCharSequence(null, null, null));
  }
}
