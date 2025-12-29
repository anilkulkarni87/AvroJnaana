---
layout: default
title: Getting Started
description: Set up AvroJnaana locally or with Docker in 10 minutes
---

<div class="container" style="padding: var(--space-3xl) var(--space-lg);">
  <div class="text-center mb-2xl">
    <h1>Getting Started</h1>
    <p class="lead" style="max-width: 600px; margin: 0 auto;">
      Follow this guide to get the project running on your machine.
    </p>
  </div>

  <div class="grid grid-2 mb-2xl">
    <div class="card">
      <h3 class="mb-md">Prerequisites</h3>
      <ul style="padding-left: 20px;">
        <li>JDK 17+</li>
        <li>Docker (optional, for sandbox)</li>
        <li>Node.js 18+ (for Avrodoc generation)</li>
      </ul>
    </div>
    <div class="card">
      <h3 class="mb-md">What you'll build</h3>
      <ul style="padding-left: 20px;">
        <li>Generate Avro schemas from IDL</li>
        <li>Run a Producer/Consumer demo</li>
        <li>Visualize schemas with Avrodoc</li>
      </ul>
    </div>
  </div>

  <div class="mb-2xl">
    <h2>1. Installation</h2>
    <p>Clone the repository and verify the environment using the included Gradle wrapper.</p>
    <pre>
git clone https://github.com/anilkulkarni87/AvroJnaana
cd AvroJnaana
./gradlew -v  # Verify JDK 17+</pre>
  </div>

  <div class="mb-2xl">
    <h2>2. Generate Schemas & Docs</h2>
    <p>Compile the Avro IDL files (`.avdl`) into schemas (`.avsc`) and generate HTML documentation.</p>
    <pre>
./gradlew schemas:generateSchema schemas:generateAvrodoc copyAvroSchemas</pre>
    <p>You can now open <a href="{{ site.baseurl }}/docs/avrodoc/avrodoc.html">docs/avrodoc/avrodoc.html</a> to browse the schemas.</p>
  </div>

  <div class="mb-2xl">
    <h2>3. Run the Demo</h2>
    <p>Execute the query demo which writes and reads data using the custom `encrypted` and `reversed` logical types.</p>
    <pre>./gradlew runQueryDemo</pre>
    <p>Expected output:</p>
    <pre>
INFO com.lavro.QueryRecordOutput - Successfully wrote query.avro
INFO com.lavro.QueryRecordOutput - Query ID        : 584-86-6254
INFO com.lavro.QueryRecordOutput - Query Author    : Daisey Gaylord (Reversed)
INFO com.lavro.QueryRecordOutput - Secret Name     : yoshiko.kshlerin (Decrypted)</pre>
  </div>

  <div class="card bg-secondary">
    <h3>Next Steps</h3>
    <div class="grid grid-3 mt-md">
      <a href="{{ site.baseurl }}/guides/kafka" class="btn btn-secondary">Integration with Kafka →</a>
      <a href="{{ site.baseurl }}/guides/logical-types" class="btn btn-secondary">Custom Logical Types →</a>
      <a href="{{ site.baseurl }}/guides/schema-registry" class="btn btn-secondary">Schema Registry →</a>
    </div>
  </div>
</div>
