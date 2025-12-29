
# Apache Avro - Schemas and Custom LogicalTypes

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![Java CI with Gradle](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml/badge.svg)](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml)
![GitHub last commit (branch)](https://img.shields.io/github/last-commit/anilkulkarni87/AvroJnaana/main)
![GitHub Repo stars](https://img.shields.io/github/stars/anilkulkarni87/AvroJnaana?style=social)



### AvroJnaana: 
    This name combines "Avro" and "Jnaana" (meaning "knowledge" or "wisdom" in Sanskrit) 
    to suggest a project focused on understanding and mastering the use of logical types in Apache Avro.

This is a companion code repo for the Apache Avro series of articles. The articles cover:

- Custom Logical Types
- Apache Avro Schemas
  - QueryRecord
  - CustomerObjectModel
- Building Avrodoc via Gradle
- Writing to Avro using Java Faker

Here is the link to the Avrodoc of the schemas that the repo currently has:

### [Avrodoc (hosted)](https://anilkulkarni87.github.io/AvroJnaana/docs/avrodoc/avrodoc.html#/){:target="_blank"}

What you can do here
- Run demos locally (`./gradlew runQueryDemo`, `./gradlew runCustomerDemo`)
- Try the hosted playground (logical types on/off)
- Spin up a Docker sandbox for Kafka + Schema Registry and see encrypted vs decrypted output
- Check schema compatibility with a CLI task
- Browse hosted Avrodoc and test reports
- Scaffold new logical types with a CLI helper

## Prerequisites

- JDK 17+
- Gradle wrapper from this repo (`./gradlew`)
- Node.js 18+ (for `@mikaello/avrodoc-plus` via the Gradle Node plugin)
- Confluent CLI (for optional Kafka/Schema Registry examples)

## 10-minute setup

Local (Gradle)
1. Clone the repo and use the wrapper: `./gradlew -v` (verifies JDK is picked up)
2. Generate schemas and Avrodoc: `./gradlew schemas:generateSchema schemas:generateAvrodoc copyAvroSchemas`
3. Build everything and run unit tests: `./gradlew clean build`
4. Open the generated docs (or use the hosted versions):
   - Avrodoc: `docs/avrodoc/avrodoc.html` or https://anilkulkarni87.github.io/AvroJnaana/docs/avrodoc/avrodoc.html#/
   - Unit test report: `docs/reports/index.html` or https://anilkulkarni87.github.io/AvroJnaana/docs/reports/index.html
5. Run a small writer/reader demo via the Gradle tasks below.

Docker sandbox (requires Docker)
- Start Kafka + Schema Registry + demos:
  ```bash
  docker-compose up demo-producer demo-consumer
  ```
- View logs:
  ```bash
  docker-compose logs -f demo-producer
  docker-compose logs -f demo-consumer
  ```
- Env defaults inside containers:
  - `BOOTSTRAP_SERVERS=kafka:9092`
  - `SCHEMA_REGISTRY_URL=http://schema-registry:8081`
  - `TOPIC=first_topic`

Hosted (no local setup)
- Avrodoc: https://anilkulkarni87.github.io/AvroJnaana/docs/avrodoc/avrodoc.html#/
- Test reports: https://anilkulkarni87.github.io/AvroJnaana/docs/reports/index.html
- Playground: https://anilkulkarni87.github.io/AvroJnaana/docs/avro-playground/index.html

Expected outputs

- `schemas/src/main/resources/schemas/**.avsc` populated from the IDL files
- `docs/avrodoc/avrodoc.html` regenerated
- `query.avro` written by `QueryRecordOutput` and showing decrypted values when read back

## Quick note on encryption

The `encrypted` logical type is **for learning only**. The Java demo uses AES-GCM with per-message IVs; inject your own key/IV via env/secret store in real use. The browser playground uses Base64 only to visualize change.

## Run the demos (no IDE)

- Query demo (writes and reads `query.avro` using `encrypted` and `reversed` logical types):
  ```bash
  ./gradlew runQueryDemo
  ```
  Optionally set custom secrets:
  ```bash
  ENCRYPTED_LOGICAL_KEY="my-16-byte-key!" ENCRYPTED_LOGICAL_IV="0123456789abcdef" ./gradlew runQueryDemo
  ```
- Customer demo (writes `CustomerObjectModel.avro` with the retail model):
  ```bash
  ./gradlew runCustomerDemo
  ```
Both tasks depend on schema generation, so you do not need to run that separately.

Sample output for `runQueryDemo`:
```
INFO com.lavro.QueryRecordOutput - Successfully wrote query.avro
INFO com.lavro.QueryRecordOutput - Query ID        : 584-86-6254
INFO com.lavro.QueryRecordOutput - Query Author    : Daisey Gaylord
INFO com.lavro.QueryRecordOutput - Secret Name     : yoshiko.kshlerin
INFO com.lavro.QueryRecordOutput - Engine Name     : X3Glf/VIN92Ks74eEsl8
```

## Interactive avrodoc playground

- Open `docs/avro-playground/index.html` locally or via Pages (https://anilkulkarni87.github.io/AvroJnaana/docs/avro-playground/index.html) to toggle logical types and see how sample records change (encrypted/reversed/email_lower/phone_normalized). Note: the playground uses Base64 to illustrate the “encrypted” change; the Java conversion uses AES-GCM for the real demo.

## Schema evolution playground

Quickly compare two schemas and see compatibility + reasons:
```bash
./gradlew schemaDiff -Pold=schemas/src/main/resources/schemas/com/lina/query/QueryRecord.avsc -Pnew=/path/to/updated.avsc
```
Example output:
```
Compatibility: INCOMPATIBLE
Reasons:
- Required field note added without a default
```

## Custom logical type catalog

- `encrypted` – AES-GCM encryption/decryption (demo key/IV, override via env)
- `reversed` – Adds/removes a prefix for illustration
- `email_lower` – Lowercases emails (see `ContactCatalog.avdl`)
- `phone_normalized` – Strips formatting, keeps leading + (see `ContactCatalog.avdl`)

## From demo to production

- Secrets: remove baked-in keys; source from a secret store/KMS, rotate, and inject via config. Keep conversions injectable so key/IV providers can be swapped.
- Distribution: package the logical types/conversions as a versioned library and add to producer/consumer classpaths; pin versions to avoid drift.
- Schema lifecycle: enforce compatibility in CI (FULL/FORWARD), register via Schema Registry, and treat `.avdl/.avsc` as reviewed code. Publish avrodoc on every merge.
- Security: enable TLS/SASL for Kafka and Schema Registry; use service accounts/API keys and ACLs. Externalize endpoints/topics per environment.
- Testing: add integration tests with Testcontainers (Kafka + SR) plus round-trip tests for logical types; fail builds on incompatible schema changes.
- Observability and failure handling: use structured logging/metrics; decide on decryption failures (DLQ vs drop vs retry) and alert on them.

## When custom logical types add value

- PII/PCI masking or encryption at schema level so only trusted consumers can read sensitive fields.
- Data normalization (e.g., lowercasing emails, trimming identifiers) before persistence to keep datasets consistent.
- Derived representations (hashing for joins without exposing raw values) to protect sensitive identifiers.
- Domain validation and transformations (e.g., canonical phone numbers, encoded IDs) kept close to the schema for reuse across teams.

## Troubleshooting

- Confluent CLI not found: ensure `confluent` is on your PATH (or install via Confluent docs).
- Schema Registry port in use: stop other local registries or change the `schemaRegistry.url` in `build.gradle`.
- Node/avrodoc errors: use Node 18+ and rerun `./gradlew schemas:generateAvroDoc`.
- Gradle cache issues: try `./gradlew --stop && ./gradlew clean build`.
- Need a quick check-all: run `./gradlew lint` (checkstyle + tests).

## Problem statement

When you define avro schemas or models or contracts for a particular dataset or domain, we have a limited set of data types available.

What if there is a field which needs to be encrypted?
What if there is a field which needs to be transformed in a different way?

[Logical Types](https://avro.apache.org/docs/1.11.1/idl-language/#logical-types) to the rescue.

By defining custom logical types, we can handle such usecases. This code repo has examples of that.

Some of the questions it will help answer right away:
- How to define a Custom Logical type and package it?
- How do we maintain our schemas?
- Better way of sharing schemas with other team members?
- How can we write complex schemas easily?
- How can schemas be made reusable?

## Schemas Used

- QueryRecord schema

  Used for testing.
- CustomerObjectModel

  My take on a generic Customer Model of a retail store.

## Build project

- Clone the repo
- Build project

  `gradle clean build`
- Generate Schema files

  `gradle schemas:generateSchema`

- Build Avrodoc

  `gradle schemas:generateAvroDoc`

  The avro schemas are represented in a html file making it easy to search and understand the schema.

- Run `QueryRecordOutput.java` and verify the logs.

## Testing with Kafka
Read more at [Test with Kafka](./kafka.md). Quick helpers:
- Start services: `./gradlew startKafkaDemo`
- Create topic: `./gradlew createTopicDemo`
- Stop services: `./gradlew stopKafkaDemo`
- Docker sandbox (requires Docker): `docker-compose up demo-producer demo-consumer` to launch Kafka+Schema Registry and run the producer/consumer against them.
- Scaffold a new logical type:
  ```bash
  ./gradlew newLogicalType -Pname=mask
  ```
  (Creates LogicalType, factory, conversion, and test templates. Register it in `schemas/build.gradle` and implement your logic.)
- Docker sandbox: `docker-compose up demo-producer demo-consumer` (starts Kafka+Schema Registry and runs producer/consumer against them)

## Working with Schema Registry
Read more at [Schema Registry](./schema_registry.md)

## Unit Testing Results
Click here to look at the [Unit test results](https://anilkulkarni87.github.io/AvroJnaana/docs/reports/index.html)

## Directory Tree
Generated by the command 
```tree -r -I 'node_modules|bin|package*|java_*'```
```
├── src
│   ├── test
│   │   ├── resources
│   │   └── java
│   └── main
│       ├── resources
│       └── java
│           └── com
│               └── lavro
│                   ├── QueryRecordOutput.java
│                   ├── ProducerDemo.java
│                   ├── CustomerObjectModelOutput.java
│                   └── ConsumerDemo.java
├── settings.gradle
├── schemas
│   ├── src
│   │   ├── test
│   │   │   ├── resources
│   │   │   └── java
│   │   └── main
│   │       ├── resources
│   │       ├── java
│   │       │   └── com
│   │       │       └── lavro
│   │       └── avro
│   │           ├── query.avdl
│   │           ├── SimpleOrder.avdl
│   │           ├── SimpleCustomer.avdl
│   │           ├── CustomerObjects.avdl
│   │           └── CustomerObjectModel.avdl
│   └── build.gradle
├── schema-registry
│   └── results
│       └── registered.csv
├── query.avro
├── kafka.md
├── gradlew.bat
├── gradlew
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.properties
│       └── gradle-wrapper.jar
├── docs
│   ├── ecommerce.png
│   ├── ecommerce.drawio
│   └── avrodoc
│       └── avrodoc.html
├── custom-conversions
│   ├── src
│   │   ├── test
│   │   │   ├── resources
│   │   │   └── java
│   │   └── main
│   │       ├── resources
│   │       └── java
│   │           └── com
│   │               └── lavro
│   │                   ├── ReversedLogicalTypeFactory.java
│   │                   ├── ReversedLogicalType.java
│   │                   ├── ReversedConversion.java
│   │                   ├── EncryptedLogicalTypeFactory.java
│   │                   ├── EncryptedLogicalType.java
│   │                   └── EncryptedConversion.java
│   └── build.gradle
├── config
│   ├── xsl
│   │   └── checkstyle-custom.xsl
│   └── checkstyle
│       ├── suppressions.xml
│       └── checkstyle.xml
├── build.gradle
└── README.md



```


## Roadmap

- [x] Add Github workflow for building schemas and avrodoc
- [x] Add unit tests for Conversions
- [x] Publish To Kafka topic
- [x] Add spotless or checkstyle plugins
- [ ] Fix for fields which are union logicaltype and null
- [x] Schema evolution/ Schema registry plugin
- [x] Keep the README.md updated
- [x] Add documentation for Schema compatibility check
- [ ] Explore adding testcontainers for Kafka
- [x] Add Logging instead of Print statements
- [x] Add interactive playground and Docker sandbox


![Complete flow](./docs/ecommerce.png)

## Tech Stack

**Language:** Java

**Build Tool:** Gradle


## Authors

- [@anilkulkarni](https://github.com/anilkulkarni87)


## Acknowledgements

 - [Awesome Readme Templates](https://readme.so/)
 - [Avrodoc Plus](https://github.com/mikaello/avrodoc-plus)
 - [Gradle Avro Plugin](https://github.com/davidmc24/gradle-avro-plugin)
 - [Gradle Schema registry plugin](https://github.com/ImFlog/schema-registry-plugin)


## 🔗 Links
[![Blog](https://img.shields.io/badge/WordPress-21759B.svg?style=for-the-badge&logo=WordPress&logoColor=white)](https://anilkulkarni.com/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anilakulkarni/)
