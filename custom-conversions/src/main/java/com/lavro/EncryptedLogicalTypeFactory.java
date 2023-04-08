package com.lavro;

import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

public class EncryptedLogicalTypeFactory implements LogicalTypes.LogicalTypeFactory{
    @Override
    public LogicalType fromSchema(Schema schema) {
        return EncryptedLogicalType.INSTANCE;
    }
}
