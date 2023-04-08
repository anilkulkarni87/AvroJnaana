package com.lavro;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class EncryptedLogicalType extends LogicalType {

    static final EncryptedLogicalType INSTANCE = new EncryptedLogicalType();
    //The key to use as a reference to the type
    public static final String ENCRYPTED_LOGICAL_TYPE_NAME = "encrypted";
    public EncryptedLogicalType() {
        super(ENCRYPTED_LOGICAL_TYPE_NAME);
    }

    @Override
    public void validate(Schema schema) {
        super.validate(schema);
        if(schema.getType() != Schema.Type.STRING){
            throw new IllegalArgumentException("Logical type 'encrypted' must be backed by String");
        }
    }

//    public static class TypeFactory implements LogicalTypes.LogicalTypeFactory {
//        private EncryptedLogicalType type = new EncryptedLogicalType();
//        @Override
//        public LogicalType fromSchema(Schema schema) {
//            return type;
//        }
//    }
}
