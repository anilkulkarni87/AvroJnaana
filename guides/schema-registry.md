---
layout: docs
title: Schema Registry
description: Managing schema evolution and compatibility checks
---

<div class="mb-xl">
  <h2>Overview</h2>
  <p>
    Confluent Schema Registry provides a serving layer for your metadata. It provides a RESTful interface for storing and retrieving your Avro schemas.
    This project includes Gradle tasks to register schemas and validate compatibility.
  </p>
</div>

<h2 id="register">Registering Schemas</h2>
<p>The repo is preconfigured to register `customerrecord` against a local registry.</p>

```bash
./gradlew schemas:generateSchema schemaRegistryRegister
```

<p>This registers the schema at `http://localhost:8081` with the subject `customerrecord-value`.</p>

<h2 id="compatibility">Compatibility Checks</h2>
<p>Before deploying changes, you can verify they are compatible with previous versions.</p>

<div class="card mb-lg">
  <h3>⚡️ Fast Check</h3>
  <pre>./gradlew schemaRegistryCompatibility</pre>
  <p class="mb-0 text-sm">Fails the build if your local `.avsc` files are incompatible with the registry.</p>
</div>

<h3 id="diff">Detailed Diff</h3>
<p>For a detailed breakdown of <em>why</em> a schema is incompatible, use the `schemaDiff` task:</p>

```bash
./gradlew schemaDiff -Pold=original.avsc -Pnew=updated.avsc
```

<h2 id="rules">Evolution Rules</h2>

<div class="grid grid-2 mb-2xl">
  <div class="card" style="border-left: 4px solid var(--text-success);">
    <h4>✅ Compatible</h4>
    <p>Adding an optional field with a default value.</p>
    <code>union {null, string} note = null;</code>
  </div>
  
  <div class="card" style="border-left: 4px solid var(--text-error);">
    <h4>❌ Incompatible</h4>
    <p>Adding a required field (no default value).</p>
    <code>string note;</code>
  </div>
</div>
