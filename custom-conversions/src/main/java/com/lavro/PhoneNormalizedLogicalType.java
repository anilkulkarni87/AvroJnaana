package com.lavro;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class PhoneNormalizedLogicalType extends LogicalType {
    static final PhoneNormalizedLogicalType INSTANCE = new PhoneNormalizedLogicalType();
    public static final String LOGICAL_TYPE_NAME = "phone_normalized";

    public PhoneNormalizedLogicalType() {
        super(LOGICAL_TYPE_NAME);
    }

    @Override
    public void validate(Schema schema) {
        super.validate(schema);
        if (schema.getType() != Schema.Type.STRING) {
            throw new IllegalArgumentException("Logical type 'phone_normalized' must be backed by String");
        }
    }
}
