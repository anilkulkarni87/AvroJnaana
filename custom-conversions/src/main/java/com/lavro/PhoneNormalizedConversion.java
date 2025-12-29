package com.lavro;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class PhoneNormalizedConversion extends Conversion<CharSequence> {
    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return PhoneNormalizedLogicalType.LOGICAL_TYPE_NAME;
    }

    @Override
    public CharSequence toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        if (value == null) {
            return null;
        }
        return normalize(value.toString());
    }

    @Override
    public CharSequence fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        if (value == null) {
            return null;
        }
        return normalize(value.toString());
    }

    private String normalize(String value) {
        // strip non-digits; keep leading + if present
        String trimmed = value.trim();
        if (trimmed.startsWith("+")) {
            return "+" + trimmed.replaceAll("[^0-9]", "");
        }
        return trimmed.replaceAll("[^0-9]", "");
    }
}
