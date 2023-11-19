package com.lavro;

import com.lina.query.QueryRecord;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {

  private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class);

  public static void main(String[] args) {
    String bootstrapServers = "127.0.0.1:9092";
    String groupId = "my-fourth-application";
    String topic = "first_topic";

    // create consumer configs
    Properties properties = new Properties();

    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        KafkaAvroDeserializer.class.getName());
    properties.setProperty("specific.avro.reader", "true");
    properties.setProperty(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
        "http://localhost:8081");
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    KafkaConsumer<String, QueryRecord> consumer = new KafkaConsumer<>(properties);

    // subscribe consumer to our topic(s)
    consumer.subscribe(Arrays.asList(topic));

    while (true) {
      ConsumerRecords<String, QueryRecord> records =
          consumer.poll(Duration.ofMillis(100));

      for (ConsumerRecord<String, QueryRecord> record : records) {
        log.info("Key: " + record.key() + ", Value: " + record.value().getQueryId() + "  "
            + record.value().getQueryAuthor() + "   " + record.value().getSecretName());
        log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
      }
    }

  }
}
