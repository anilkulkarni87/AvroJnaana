package com.lavro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.avro.Schema;
import org.apache.avro.SchemaCompatibility;
import org.junit.jupiter.api.Test;

class SchemaCompatibilityTest {

  @Test
  void addingOptionalFieldRemainsCompatible() {
    Schema writer = new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"Demo\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"}]}");
    Schema reader = new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"Demo\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},"
            + "{\"name\":\"note\",\"type\":[\"null\",\"string\"],\"default\":null}]}");

    SchemaCompatibility.SchemaCompatibilityType result =
        SchemaCompatibility.checkReaderWriterCompatibility(reader, writer).getType();

    assertEquals(SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE, result);
  }

  @Test
  void addingRequiredFieldBreaksCompatibility() {
    Schema writer = new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"Demo\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"}]}");
    Schema reader = new Schema.Parser().parse(
        "{\"type\":\"record\",\"name\":\"Demo\",\"fields\":[{\"name\":\"id\",\"type\":\"string\"},"
            + "{\"name\":\"note\",\"type\":\"string\"}]}");

    SchemaCompatibility.SchemaCompatibilityType result =
        SchemaCompatibility.checkReaderWriterCompatibility(reader, writer).getType();

    assertEquals(SchemaCompatibility.SchemaCompatibilityType.INCOMPATIBLE, result);
  }
}
