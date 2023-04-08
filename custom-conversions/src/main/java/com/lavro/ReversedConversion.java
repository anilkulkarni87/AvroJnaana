package com.lavro;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

public class ReversedConversion extends Conversion<CharSequence> {
    private static final ReversedConversion INSTANCE = new ReversedConversion();
    public static final ReversedConversion get(){ return INSTANCE; }
    public ReversedConversion(){ super(); }
    @Override
    public Class<CharSequence> getConvertedType() {
        return CharSequence.class;
    }

    @Override
    public String getLogicalTypeName() {
        return ReversedLogicalType.REVERSED_LOGICAL_TYPE_NAME;
    }

    @Override
    //Called when Reading from Avro
    public CharSequence fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        System.out.println("When reading from the avro file FromCharSequence is :"+value);
        System.out.println("*****************************");
        //return value.toString();
        return value.toString().replace("Reversed","");
    }

    @Override
    //Called when writing to Avro
    public CharSequence toCharSequence(CharSequence value, Schema schema, LogicalType type) {
        System.out.println("When writing to Avro ToCharSequence is :"+value);
        System.out.println("*****************************");
        return "Reversed"+value;
    }
}
