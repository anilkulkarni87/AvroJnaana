---
title: AvroJnaana
---

<style>
  :root {
    --bg: #0b1021;
    --panel: #11172b;
    --text: #e8edf7;
    --muted: #9aa6bf;
    --accent: #7bdff2;
    --accent-2: #f5a524;
    --card-radius: 14px;
  }
  body {
    background: var(--bg);
    color: var(--text);
    font-family: "Inter", "Segoe UI", system-ui, -apple-system, sans-serif;
    margin: 0;
    padding: 0;
  }
  .page {
    max-width: 1080px;
    margin: 0 auto;
    padding: 48px 24px 96px;
  }
  .hero {
    padding: 32px;
    background: linear-gradient(135deg, #121a30, #0e2038);
    border: 1px solid #1f2a46;
    border-radius: var(--card-radius);
    box-shadow: 0 20px 60px rgba(0,0,0,0.35);
  }
  h1 {
    margin-top: 0;
    font-size: 34px;
    letter-spacing: -0.02em;
  }
  p.lead {
    color: var(--muted);
    font-size: 17px;
    line-height: 1.6;
    margin-bottom: 24px;
  }
  .badges {
    display: flex;
    flex-wrap: wrap;
    gap: 12px;
    margin-bottom: 24px;
  }
  .badge {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 10px 14px;
    border-radius: 12px;
    background: rgba(255,255,255,0.04);
    border: 1px solid #1f2a46;
    color: var(--text);
    text-decoration: none;
    font-weight: 600;
  }
  .badge.accent { background: rgba(123, 223, 242, 0.12); border-color: rgba(123,223,242,0.4); }
  .grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
    gap: 18px;
    margin-top: 22px;
  }
  .card {
    background: var(--panel);
    border: 1px solid #1f2a46;
    border-radius: var(--card-radius);
    padding: 18px;
    box-shadow: 0 10px 30px rgba(0,0,0,0.25);
  }
  .card h3 { margin-top: 0; margin-bottom: 10px; }
  .card p { color: var(--muted); margin: 0 0 10px; line-height: 1.5; }
  .link {
    color: var(--accent);
    text-decoration: none;
    font-weight: 600;
  }
  .link:hover { text-decoration: underline; }
  ul.clean { padding-left: 18px; color: var(--muted); line-height: 1.6; }
  @media (max-width: 600px) {
    .hero { padding: 24px; }
    h1 { font-size: 28px; }
  }
</style>

<div class="page">
  <div class="hero">
    <h1>AvroJnaana</h1>
    <p class="lead">Learn and experiment with Apache Avro custom logical types, schema evolution, and Kafka/Schema Registry integration. Jump into the hosted playground, generated docs, or runnable demos.</p>
    <div class="badges">
      <a class="badge accent" href="https://anilkulkarni87.github.io/AvroJnaana/docs/avro-playground/index.html">Open Playground</a>
      <a class="badge" href="https://anilkulkarni87.github.io/AvroJnaana/docs/avrodoc/avrodoc.html#/">Avrodoc</a>
      <a class="badge" href="https://anilkulkarni87.github.io/AvroJnaana/docs/reports/index.html">Test Reports</a>
      <a class="badge" href="https://github.com/anilkulkarni87/AvroJnaana">GitHub Repo</a>
      <a class="badge" href="README.md">README</a>
    </div>
  </div>

  <div class="grid">
    <div class="card">
      <h3>Run the demos</h3>
      <p>Local (Gradle):</p>
      <ul class="clean">
        <li><code>./gradlew runQueryDemo</code></li>
        <li><code>./gradlew runCustomerDemo</code></li>
      </ul>
      <p>Docker sandbox:</p>
      <ul class="clean">
        <li><code>docker-compose up demo-producer demo-consumer</code></li>
        <li><code>docker-compose logs -f demo-consumer</code></li>
      </ul>
    </div>

    <div class="card">
      <h3>Schema tools</h3>
      <ul class="clean">
        <li>Generate schemas: <code>./gradlew schemas:generateSchema</code></li>
        <li>Avrodoc: <code>./gradlew schemas:generateAvroDoc</code></li>
        <li>Schema diff: <code>./gradlew schemaDiff -Pold=... -Pnew=...</code></li>
        <li>Schema Registry: <code>./gradlew schemaRegistryRegister</code></li>
      </ul>
    </div>

    <div class="card">
      <h3>Custom logical types</h3>
      <p>Included:</p>
      <ul class="clean">
        <li><code>encrypted</code> (AES-GCM)</li>
        <li><code>reversed</code> (demo)</li>
        <li><code>email_lower</code>, <code>phone_normalized</code></li>
      </ul>
      <p>Scaffold your own:</p>
      <ul class="clean">
        <li><code>./gradlew newLogicalType -Pname=mask</code></li>
      </ul>
    </div>

    <div class="card">
      <h3>Play &amp; learn</h3>
      <p>Hosted playground lets you toggle logical types, view raw vs transformed vs decrypted, and download sample JSON.</p>
      <p>Avrodoc provides searchable schema docs; test reports show conversion coverage.</p>
    </div>
  </div>
</div>
