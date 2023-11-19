package com.lavro;

import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class ReversedLogicalType extends LogicalType {
    static final ReversedLogicalType INSTANCE = new ReversedLogicalType();
    //The key to use as a reference to the type
    public static final String REVERSED_LOGICAL_TYPE_NAME = "reversed";
    // public static final String LOGICAL_TYPE_PROP = "reversed";
    public ReversedLogicalType() {
        super(REVERSED_LOGICAL_TYPE_NAME);
    }


    @Override
    public void validate(Schema schema) {
        super.validate(schema);
        if(schema.getType() != Schema.Type.STRING){
            throw new IllegalArgumentException("Logical type 'reversed' must be backed by String");
        }
    }

//    public static class TypeFactory implements LogicalTypes.LogicalTypeFactory {
//        private ReversedLogicalType type = new ReversedLogicalType();
//        @Override
//        public LogicalType fromSchema(Schema schema) {
//            return type;
//        }
//    }
}
