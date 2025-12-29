---
layout: docs
title: Custom Logical Types
description: Catalog of custom logical types included in this project
---

<h2 id="catalog">Type Catalog</h2>

<div class="grid grid-2 mb-2xl">
  <div class="card">
    <h3>🔒 Encrypted</h3>
    <p>Encrypts string fields using AES-GCM. The schema stores the cyphertext, but the application sees plain text.</p>
    <ul>
      <li><strong>Logical Type:</strong> <code>encrypted</code></li>
      <li><strong>Underlying Type:</strong> <code>bytes</code> or <code>string</code></li>
      <li><strong>Use case:</strong> PII, Secrets, PCI data</li>
    </ul>
  </div>

  <div class="card">
    <h3>🔄 Reversed</h3>
    <p>A simple demo type that reverses the string value. Useful for understanding the conversion API.</p>
    <ul>
      <li><strong>Logical Type:</strong> <code>reversed</code></li>
      <li><strong>Underlying Type:</strong> <code>string</code></li>
    </ul>
  </div>

  <div class="card">
    <h3>📧 Email (Normalized)</h3>
    <p>Automatically lowercases email addresses on write to ensure consistency.</p>
    <ul>
      <li><strong>Logical Type:</strong> <code>email_lower</code></li>
      <li><strong>Underlying Type:</strong> <code>string</code></li>
    </ul>
  </div>

  <div class="card">
    <h3>📱 Phone (Normalized)</h3>
    <p>Strips formatting characters (dashes, spaces) and standardizes the format.</p>
    <ul>
      <li><strong>Logical Type:</strong> <code>phone_normalized</code></li>
      <li><strong>Underlying Type:</strong> <code>string</code></li>
    </ul>
  </div>
</div>

<h2 id="scaffold">Create Your Own</h2>
<p>
  The project includes a Gradle task to scaffold the boilerplate for a new logical type. 
  This creates the LogicalType class, the Conversion class, and the Factory.
</p>

```bash
./gradlew newLogicalType -Pname=mask
```

<div class="card bg-secondary mt-lg">
  <h4>What gets created?</h4>
  <ul class="mb-0">
    <li><code>MaskLogicalType.java</code> defines the type name</li>
    <li><code>MaskConversion.java</code> handles the read/write logic</li>
    <li><code>MaskLogicalTypeFactory.java</code> registers it with Avro</li>
  </ul>
</div>
