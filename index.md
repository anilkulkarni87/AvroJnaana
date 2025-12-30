---
layout: default
title: AvroJnaana
description: Master Apache Avro integration with Kafka, Schema Registry, and Custome Logical Types
---

<style>
  .hero-section {
    position: relative;
    padding: var(--space-3xl) 0;
    text-align: center;
    background: radial-gradient(circle at 50% 0%, rgba(123, 223, 242, 0.1) 0%, transparent 50%);
  }

  .hero-title {
    font-size: 3.5rem;
    line-height: 1.1;
    margin-bottom: var(--space-lg);
    background: var(--accent-gradient);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }

  .hero-subtitle {
    font-size: 1.25rem;
    color: var(--text-secondary);
    max-width: 700px;
    margin: 0 auto var(--space-2xl);
  }

  .hero-badges {
    display: flex;
    gap: var(--space-md);
    justify-content: center;
    flex-wrap: wrap;
    margin-bottom: var(--space-2xl);
  }

  .hero-actions {
    display: flex;
    gap: var(--space-md);
    justify-content: center;
    margin-bottom: var(--space-3xl);
  }

  .feature-icon {
    font-size: 2rem;
    margin-bottom: var(--space-md);
  }
</style>

<div class="hero-section">
  <div class="container">
    <h1 class="hero-title">Unlock the Power of Avro</h1>
    <p class="hero-subtitle">
      A comprehensive toolkit for mastering Apache Avro custom logical types, schema evolution, and Kafka integration.
    </p>

    <div class="hero-actions">
      <a href="{{ site.baseurl }}/docs/avro-playground/" class="btn btn-primary">Try the Playground</a>
      <a href="{{ site.baseurl }}/getting-started" class="btn btn-secondary">Get Started</a>
    </div>

    <div class="hero-badges">
      <span class="badge">Apache Avro 1.11+</span>
      <span class="badge">Kafka 3.x</span>
      <span class="badge">Schema Registry</span>
      <span class="badge">Gradle</span>
      <a href="https://codespaces.new/anilkulkarni87/AvroJnaana" class="badge" style="text-decoration: none; border-color: var(--accent);">⚡ Codespaces Ready</a>
      <span class="badge-warning">Experimental</span>
    </div>
  </div>
</div>

<div class="container mb-2xl">
  <div class="grid grid-3">
    <div class="card">
      <div class="feature-icon">🔒</div>
      <h3>Custom Logical Types</h3>
      <p>Seamlessly handle encryption, PII masking, and data normalization directly within your Avro schemas.</p>
      <a href="{{ site.baseurl }}/guides/logical-types">Explore types →</a>
    </div>
    
    <div class="card">
      <div class="feature-icon">⚖️</div>
      <h3>Schema Evolution</h3>
      <p>Validate compatibility changes before you deploy. Integration with Confluent Schema Registry included.</p>
      <a href="{{ site.baseurl }}/guides/schema-registry">Learn more →</a>
    </div>
    
    <div class="card">
      <div class="feature-icon">🚀</div>
      <h3>Kafka Integration</h3>
      <p>Production-ready examples of producers and consumers with automatic type conversion.</p>
      <a href="{{ site.baseurl }}/guides/kafka">View examples →</a>
    </div>
  </div>
</div>

<div class="container mb-2xl">
  <div class="grid grid-2">
    <div>
      <h2>Interactive Playground</h2>
      <p>
        Experiment with custom logical types in your browser. Toggle encryption and data masking to see how your data transforms in real-time.
      </p>
      <ul style="padding-left: 20px; color: var(--text-secondary);">
        <li>AES-GCM Encryption simulation</li>
        <li>PII Masking</li>
        <li>Custom transformations</li>
      </ul>
      <a href="{{ site.baseurl }}/docs/avro-playground/" class="btn btn-secondary mt-lg">Launch Playground</a>
    </div>
    <div class="card" style="display: flex; align-items: center; justify-content: center; min-height: 200px; background: var(--bg-secondary);">
      <!-- Interactive visual placeholder using existing styles -->
      <div style="text-align: center;">
        <span style="font-size: 3rem; margin-bottom: 1rem; display: block;">🛠️</span>
        <div style="font-family: var(--font-mono); color: var(--text-accent);">{"secret": "*********"}</div>
      </div>
    </div>
  </div>
</div>

<div class="container mb-2xl">
  <h2 class="text-center mb-xl">Quick Start</h2>
  
  <div class="grid grid-2">
    <div class="card">
      <h3>Run Locally</h3>
      <p>Clone and run the demo with Gradle wrapper:</p>
      <pre>
git clone https://github.com/anilkulkarni87/AvroJnaana
cd AvroJnaana
./gradlew runQueryDemo</pre>
    </div>
    
    <div class="card">
      <h3>Run with Docker</h3>
      <p>Spin up Kafka & Schema Registry stack:</p>
      <pre>docker-compose up demo-producer demo-consumer</pre>
    </div>
  </div>
</div>
