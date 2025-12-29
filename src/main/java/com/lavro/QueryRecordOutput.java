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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QueryRecordOutput {
  private static final Logger log = LoggerFactory.getLogger(QueryRecordOutput.class);

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
      dataFileWriter.close();
      log.info("Successfully wrote query.avro");
    } catch (IOException e) {
      e.printStackTrace();
    }

    // read it from a file
    final File file = new File("query.avro");
    final DatumReader<QueryRecord> datumReader = new SpecificDatumReader<>(QueryRecord.class);
    final DataFileReader<QueryRecord> dataFileReader;
    try {
      log.info("Reading the specific record");
      dataFileReader = new DataFileReader<>(file, datumReader);
      while (dataFileReader.hasNext()) {
        QueryRecord query = dataFileReader.next();
        log.info("Query ID        : {}", query.getQueryId());
        log.info("Query Author    : {}", query.getQueryAuthor());
        log.info("Secret Name     : {}", query.getSecretName());
        log.info("Engine Name     : {}", query.getQueryEngine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
