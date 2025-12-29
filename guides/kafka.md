---
layout: docs
title: Kafka Integration
description: Running producers and consumers with transparent Avro logical type conversion
---

<div class="mb-xl">
  <h2>Overview</h2>
  <p>
    One of the most powerful features of custom logical types is the ability to handle data transformation transparently at the driver level. 
    This guide shows how to run the included Kafka demos where encryption and normalization happen automatically.
  </p>
</div>

<div class="mb-2xl">
  <h2>The "Magic" Explained</h2>
  <p>
    How does the application see Plain Text while Kafka stores Cyphertext? The conversion happens transparently in the Avro Logic Type layer during serialization/deserialization.
  </p>
  
  <div class="card bg-secondary mb-lg">
    <h3 class="text-center mb-md">Producer Flow (Encryption)</h3>
    <pre class="mermaid">
    sequenceDiagram
        participant App as Application
        participant Avro as Avro Serializer
        participant Logic as LogicalType (AES)
        participant Kafka
        
        Note over App, Kafka: Writing a Record
        
        App->>Avro: write(QueryRecord)
        Note right of App: "secret-value"
        
        Avro->>Logic: conversion.toCharSequence()
        Logic->>Logic: Encrypt(value, key)
        Logic-->>Avro: "encrypted-cyphertext"
        
        Avro->>Kafka: Produce Message
        Note right of Kafka: Stored as Cyphertext
    </pre>
  </div>

  <div class="card bg-secondary">
    <h3 class="text-center mb-md">Consumer Flow (Decryption)</h3>
    <pre class="mermaid">
    sequenceDiagram
        participant Kafka
        participant Avro as Avro Deserializer
        participant Logic as LogicalType (AES)
        participant App as Application
        
        Note over Kafka, App: Reading a Record
        
        Kafka->>Avro: Consume Message
        Note right of Kafka: "encrypted-cyphertext"
        
        Avro->>Logic: conversion.fromCharSequence()
        Logic->>Logic: Decrypt(value, key)
        Logic-->>Avro: "secret-value"
        
        Avro->>App: read(QueryRecord)
        Note right of App: Plain Text restored!
    </pre>
  </div>
</div>

<div class="card mb-2xl">
  <h3>Prerequisites</h3>
  <p class="mb-0">You need a running Kafka cluster. You can use the included Docker Compose file or a local Confluent Platform installation.</p>
</div>

<h2 id="docker">Option A: Docker Sandbox</h2>
<p>The easiest way to run the full stack (Kafka + Schema Registry + Zookeeper).</p>

```bash
# Start services and demos
docker-compose up demo-producer demo-consumer

# View logs
docker-compose logs -f demo-producer
docker-compose logs -f demo-consumer
```

<div class="grid grid-2 mt-lg mb-2xl">
  <div>
    <h4>Defaults</h4>
    <ul>
      <li>Bootstrap: <code>kafka:9092</code></li>
      <li>Schema Registry: <code>http://schema-registry:8081</code></li>
      <li>Topic: <code>first_topic</code></li>
    </ul>
  </div>
</div>

<h2 id="local">Option B: Local Gradle Run</h2>
<p>If you have Confluent CLI installed or a cluster running locally:</p>

```bash
# 1. Start services
confluent local services start

# 2. Create topic
./gradlew createTopicDemo

# 3. Run Producer
./gradlew runProducerDemo

# 4. Run Consumer
./gradlew runConsumerDemo
```

<h2 id="observe">What to Observe</h2>

<div class="grid grid-2 mb-xl">
  <div class="card">
    <h4>👀 CLI Consumer (Raw)</h4>
    <p>If you consume the topic without the custom logical types on the classpath, you see the <strong>encrypted</strong> data:</p>
    <pre>
{"secretName": {"string": "X3Glf/VIN92Ks..."}}</pre>
  </div>
  
  <div class="card" style="border-color: var(--text-accent);">
    <h4>🔓 Java Consumer (Decrypted)</h4>
    <p>The demo consumer has the conversion logic, so it automatically <strong>decrypts</strong> the data:</p>
    <pre>
Secret Name: yoshiko.kshlerin</pre>
  </div>
</div>
