package com.lavro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PhoneNormalizedConversionTest {

  @Test
  void stripsNonDigitsAndKeepsPlus() {
    PhoneNormalizedConversion conversion = new PhoneNormalizedConversion();
    String input = "+1 (555) 123-4567";
    CharSequence stored = conversion.toCharSequence(input, null, null);
    CharSequence read = conversion.fromCharSequence(stored, null, null);
    assertEquals("+15551234567", stored.toString());
    assertEquals("+15551234567", read.toString());
  }

  @Test
  void nullPassThrough() {
    PhoneNormalizedConversion conversion = new PhoneNormalizedConversion();
    assertNull(conversion.toCharSequence(null, null, null));
    assertNull(conversion.fromCharSequence(null, null, null));
  }
}
