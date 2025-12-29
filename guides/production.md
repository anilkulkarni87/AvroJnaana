---
layout: default
title: Production Guide
description: Best practices for moving from demo-grade code to production deployments
---

<div class="mb-xl">
  <h1>From Demo to Production</h1>
  <p class="lead">
    The code in this repository is designed for educational purposes. To take these concepts to production, follow these best practices.
  </p>
</div>

<div class="grid grid-2 mb-2xl">
  <div class="card">
    <h3>🔑 Secrets Management</h3>
    <p><strong>Demo:</strong> Uses hardcoded keys or environment variables passed directly to the build.</p>
    <p><strong>Production:</strong></p>
    <ul>
      <li>Use a Key Management Service (KMS) like AWS KMS, Vault, or Azure Key Vault.</li>
      <li>Rotate encryption keys regularly (key versioning).</li>
      <li>Inject keys into the `Conversion` class via a dependency injection framework (Spring, Guice).</li>
    </ul>
  </div>

  <div class="card">
    <h3>📦 Distribution</h3>
    <p><strong>Demo:</strong> Sources are in the same repo.</p>
    <p><strong>Production:</strong></p>
    <ul>
      <li>Package your custom logical types and conversions as a separate Maven/Gradle artifact.</li>
      <li>Publish to a private artifact repository (Artifactory, Nexus).</li>
      <li>Consumers and Producers add this library as a dependency.</li>
    </ul>
  </div>

  <div class="card">
    <h3>🛡️ Schema Governance</h3>
    <p><strong>Demo:</strong> Evolution is manual.</p>
    <p><strong>Production:</strong></p>
    <ul>
      <li>Enforce BACKWARD or FULL compatibility in the Schema Registry.</li>
      <li>Treat `.avdl` files as code: require code reviews for changes.</li>
      <li>Automate `schemaRegistrycompatibility` checks in CI/CD pipelines.</li>
    </ul>
  </div>
  
  <div class="card">
    <h3>🚨 Failure Handling</h3>
    <p><strong>Demo:</strong> Logs errors to stdout.</p>
    <p><strong>Production:</strong></p>
    <ul>
      <li>Decide on a strategy for decryption failures: Dead Letter Queue (DLQ) vs. poison pill.</li>
      <li>Emit metrics for encryption/decryption latency.</li>
      <li>Alert on sudden spikes in conversion errors.</li>
    </ul>
  </div>
</div>

<div class="card bg-secondary">
  <h3>Next Steps</h3>
  <p class="mb-md">Ready to implement? Scaffold your own custom logical type to get started.</p>
  <code>./gradlew newLogicalType -Pname=MySecureType</code>
</div>
