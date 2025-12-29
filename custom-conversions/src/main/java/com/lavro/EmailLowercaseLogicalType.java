package com.lavro;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EmailLowercaseLogicalType extends LogicalType {
    static final EmailLowercaseLogicalType INSTANCE = new EmailLowercaseLogicalType();
    public static final String LOGICAL_TYPE_NAME = "email_lower";

    public EmailLowercaseLogicalType() {
        super(LOGICAL_TYPE_NAME);
    }

    @Override
    public void validate(Schema schema) {
        super.validate(schema);
        if (schema.getType() != Schema.Type.STRING) {
            throw new IllegalArgumentException("Logical type 'email_lower' must be backed by String");
        }
    }
}
