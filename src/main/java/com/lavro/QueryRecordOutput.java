package com.lavro;

import com.lina.query.QueryRecord;
import java.io.File;
import java.io.IOException;
import net.datafaker.Faker;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;


public class QueryRecordOutput {
  public static void main(String[] args) {
    Faker faker = new Faker();
    Schema schema = QueryRecord.getClassSchema();
//    System.out.println(schema.getField("testField").schema());

    final DatumWriter<QueryRecord> datumWriter = new SpecificDatumWriter<>();

    try (DataFileWriter<QueryRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
      dataFileWriter.create(schema, new File("query.avro"));
      for (int i = 0; i < 1; i++) {
        QueryRecord queryRecord = QueryRecord.newBuilder()
            .setQueryId(faker.idNumber().ssnValid())
            .setQueryAuthor(faker.book().author())
            .setSecretName(faker.number().digits(4))
            .setQueryEngine(faker.name().username())
//            .setTestField(faker.animal().name())
            .build();
        dataFileWriter.append(queryRecord);
      }
      // Close the file
      dataFileWriter.close();
      System.out.println("successfully wrote query.avro");
      System.out.println("*****************************");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // read it from a file
    final File file = new File("query.avro");
    final DatumReader<QueryRecord> datumReader = new SpecificDatumReader<>(QueryRecord.class);
    final DataFileReader<QueryRecord> dataFileReader;
    try {
      System.out.println("Reading our specific record");
      System.out.println("*****************************");
      dataFileReader = new DataFileReader<>(file, datumReader);
      while (dataFileReader.hasNext()) {
        QueryRecord query = dataFileReader.next();
        System.out.println(query.toString());
        System.out.println("Query ID        : " + query.getQueryId());
        System.out.println("Query Author    : " + query.getQueryAuthor());
        System.out.println("Secret Name     : " + query.getSecretName());
        System.out.println("Engine Name     : " + query.getQueryEngine());
//        System.out.println("Test Field     : " + query.getTestField());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
