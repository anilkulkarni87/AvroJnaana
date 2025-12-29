## Project setup (local Kafka + Schema Registry)

Install the Confluent CLI; it ships with Kafka, ZooKeeper, and Schema Registry in one bundle.  
[Installation instructions](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html){:target="_blank"}

### Start services
```bash
confluent local services start
```
This starts ZooKeeper (2181), Kafka (9092), and Schema Registry (8081).

If you prefer a Gradle entry point (requires Confluent CLI on PATH):
```bash
./gradlew startKafkaDemo
```

Or Docker (no local Confluent install needed):
```bash
docker-compose up demo-producer demo-consumer
```
Logs:
```bash
docker-compose logs -f demo-producer
docker-compose logs -f demo-consumer
```
Defaults inside the containers:
- `BOOTSTRAP_SERVERS=kafka:9092`
- `SCHEMA_REGISTRY_URL=http://schema-registry:8081`
- `TOPIC=first_topic`

### Create a topic
```bash
confluent kafka topic create first_topic --partitions 1 --replication-factor 1
```
Or via Gradle:
```bash
./gradlew createTopicDemo
```

### Run the producer demo
- Ensure schemas are generated: `./gradlew schemas:generateSchema`
- Run `ProducerDemo` (from IDE or `./gradlew run` if you wire an application plugin) to send 10 `QueryRecord` messages to `first_topic`.

### Run the consumer demo
- Run `ConsumerDemo` to read from `first_topic`. You should see decrypted values in the logs similar to:
```
Key: null, Value: 123-45-6789  Jane Austen   Bob Ross
Partition: 0, Offset: 0
```

### Inspect with the CLI
If you consume with the Kafka CLI without the custom conversions, you will see the encrypted payload instead of the decrypted one. That contrast is intentional to show the value of custom logical types.

```bash
confluent kafka topic consume first_topic --from-beginning
```

### Stop services
```bash
confluent local services stop
```
Or via Gradle:
```bash
./gradlew stopKafkaDemo
```

## Things to Observe

- The producer uses the Java classes generated from the `.avdl` files.
- The consumer decrypts the data it reads from the topic thanks to the custom logical type/conversion pair.
- The CLI consumer (without the conversion) shows encrypted data, illustrating how Avro logical types can shield sensitive values in transit.
Sample CLI output (encrypted) vs consumer log (decrypted):
```
# CLI
{"queryId":"584-86-6254","queryAuthor":{"string":"ReversedDaisey Gaylord"},"secretName":{"string":"X3Glf/VIN92Ks74eEsl8..."}}

# ConsumerDemo log
Key: null, Value: 584-86-6254  Daisey Gaylord   yoshiko.kshlerin
```
