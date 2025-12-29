package com.lavro;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EmailLowercaseConversion extends Conversion<CharSequence> {
    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return EmailLowercaseLogicalType.LOGICAL_TYPE_NAME;
    }

    @Override
    public CharSequence toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        if (value == null) {
            return null;
        }
        return value.toString().toLowerCase();
    }

    @Override
    public CharSequence fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        if (value == null) {
            return null;
        }
        return value.toString().toLowerCase();
    }
}
