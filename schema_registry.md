## Confluent Schema registry

Read here to setup confluent [kafka](./kafka.md) locally. 

The schemas generated from the avdl files can be registered on the Schema registry. 
One can also define the compatibility levels for each subject. At the same time when new changes are made to the schema, 
It will automatically error out if the updated schema does not match the initially defined compatibility.

When schemas are updated , the compatibility checks can be made locally while building the project.

