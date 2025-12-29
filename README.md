# Apache Avro - Schemas and Custom LogicalTypes

> **Visit the full documentation site: [anilkulkarni87.github.io/AvroJnaana](https://anilkulkarni87.github.io/AvroJnaana/)**

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![Java CI with Gradle](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml/badge.svg)](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml)

**AvroJnaana** ("Avro Knowledge") is a comprehensive project for mastering Apache Avro custom logical types, schema evolution, and Kafka integration.

## 📚 Documentation

The documentation has been moved to a generated site for better navigation:

- [**Getting Started**](https://anilkulkarni87.github.io/AvroJnaana/getting-started): Installation and first run
- [**Guides**](https://anilkulkarni87.github.io/AvroJnaana/guides/):
  - [Kafka Integration](https://anilkulkarni87.github.io/AvroJnaana/guides/kafka)
  - [Schema Registry](https://anilkulkarni87.github.io/AvroJnaana/guides/schema-registry)
  - [Custom Logical Types](https://anilkulkarni87.github.io/AvroJnaana/guides/logical-types)
- [**Interactive Playground**](https://anilkulkarni87.github.io/AvroJnaana/docs/avro-playground/): Try transformations in your browser
- [**Schema Docs**](https://anilkulkarni87.github.io/AvroJnaana/docs/avrodoc/avrodoc.html): Generated Avrodoc

## ⚡️ Quick Start

Clone and run the demo:

```bash
git clone https://github.com/anilkulkarni87/AvroJnaana
cd AvroJnaana
./gradlew runQueryDemo
```

## 🛠 Features

- **Custom Logical Types**: Encryption (AES-GCM), PII masking, data normalization.
- **Schema Evolution**: Tools to diff schemas and register with Confluent Schema Registry.
- **Kafka Demos**: Full Producer/Consumer examples with Docker Compose support.
- **Testing**: Unit tests for conversions and integration tests for logical types.

## License

MIT
