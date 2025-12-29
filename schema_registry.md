## Confluent Schema Registry

Read [kafka.md](./kafka.md) to set up Confluent locally.

### Registering schemas (Gradle plugin)
The repo ships with the Schema Registry Gradle plugin preconfigured to register `customerrecord`:
```bash
./gradlew schemas:generateSchema schemaRegistryRegister
```
The plugin will register `schemas/build/generated-main-avro-avsc/com/dataanada/customer/CustomerRecord.avsc` against the local registry at `http://localhost:8081`.

### Setting compatibility
The same plugin config sets compatibility to `FULL_TRANSITIVE`. To push compatibility changes:
```bash
./gradlew schemaRegistryConfig
```

### Checking compatibility locally
```bash
./gradlew schemaRegistryCompatibility
```
If you evolve a schema in `schemas/src/main/avro` and regenerate (`schemas:generateSchema`), this task will fail fast when the change is incompatible.

Example evolution:
- Compatible: add an optional field with a default  
  `union {null, string} note = null`
- Incompatible: add a required field with no default  
  `string note`

### Adding more subjects
1. Regenerate schemas: `./gradlew schemas:generateSchema`
2. Add another `subject("name", "path-to-avsc", "AVRO")` entry under `schemaRegistry.register {}` in `build.gradle`.
3. Run `./gradlew schemaRegistryRegister` again.

### What to look for
- Successful registration prints the subject and schema ID to `schema-registry/results/registered.csv`.
- Incompatible changes fail the Gradle task before anything is registered, giving you feedback during development.
