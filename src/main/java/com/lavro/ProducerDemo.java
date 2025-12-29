package com.lavro;


import com.lina.query.QueryRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import java.util.Properties;
import net.datafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemo {
  private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);

  public static void main(String[] args) {
    Faker faker = new Faker();
    log.info("I am a Kafka Producer");

    String bootstrapServers = System.getenv().getOrDefault("BOOTSTRAP_SERVERS", "127.0.0.1:9092");
    String SCHEMA_REGISTRY_URL =
        System.getenv().getOrDefault("SCHEMA_REGISTRY_URL", "http://localhost:8081");
    String topic = System.getenv().getOrDefault("TOPIC", "first_topic");

    //create Producer properties
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        KafkaAvroSerializer.class.getName());
    properties.setProperty(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
        SCHEMA_REGISTRY_URL);

    //create the Producer
    KafkaProducer<String, QueryRecord> producer = new KafkaProducer<>(properties);

    for (int i = 0; i < 10; i++) {
      QueryRecord queryRecord = QueryRecord.newBuilder()
          .setQueryId(faker.idNumber().ssnValid())
          .setQueryAuthor(faker.book().author())
          .setSecretName(faker.artist().name())
          .setQueryEngine(faker.name().username())
          .build();
      // create a producer record
      ProducerRecord<String, QueryRecord> producerRecord =
          new ProducerRecord<>(topic, queryRecord);
      // send data - asynchronous
      producer.send(producerRecord);

    }


    // flush data - synchronous
    producer.flush();

    // flush and close producer
    producer.close();
  }
}
