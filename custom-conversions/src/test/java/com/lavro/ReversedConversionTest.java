package com.lavro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;
import org.junit.jupiter.api.Test;

class ReversedConversionTest {

  @Test
  void roundTripsWithPrefix() {
    Schema schema = mock(Schema.class);
    LogicalType logicalType = mock(LogicalType.class);
    ReversedConversion conversion = new ReversedConversion();

    CharSequence stored = conversion.toCharSequence("value", schema, logicalType);
    assertNotNull(stored);
    CharSequence readBack = conversion.fromCharSequence(stored, schema, logicalType);

    assertEquals("value", readBack.toString());
  }
}
