
# Apache Avro - Schemas and Custom LogicalTypes

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![Java CI with Gradle](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml/badge.svg)](https://github.com/anilkulkarni87/AvroJnaana/actions/workflows/gradle.yml)
![GitHub last commit (branch)](https://img.shields.io/github/last-commit/anilkulkarni87/AvroJnaana/main)
![GitHub Repo stars](https://img.shields.io/github/stars/anilkulkarni87/AvroJnaana?style=social)



### AvroJnaana: 
    This name combines "Avro" and "Jnaana" (meaning "knowledge" or "wisdom" in Sanskrit) 
    to suggest a project focused on understanding and mastering the use of logical types in Apache Avro.

This is a companion code repo for the Apache Avro series of articles. The articles will cover the below topics:

- Custom Logical Types
- Apache Avro Schemas
    - QueryRecord
    - CustomerObjectModel
- Building Avrodoc via gradle
- Writing to Avro using Java Faker

Here is the link to the Avrodoc of the schemas that the repo currently has:

### [Avrodoc](avrodoc.html){:target="_blank"}



## Problem statement

When you define avro schemas or models or contracts for a particular dataset or domain, we have a limited set of data types available.

What if there is a field which needs to be encrypted?
What if there is a field which needs to be transformed in a different way?

[Logical Types](https://avro.apache.org/docs/1.11.1/idl-language/#logical-types) to the rescue.

By defining custom logical types, we can handle such usecases. This code repo has examples of that.

Some of the questions it will help answer right away:
- How to define a Custom Logical type and package it?
- How do we maintain our schemas?
- Better way of sharing schemas with other team members?
## Schemas Used

- QueryRecord schema

  Used for testing.
- CustomerObjectModel

  My take on a generic Customer Model of a retail store.
## Usage/Examples

- Clone the repo
- Build project

  `gradle clean build`
- Generate Schema files

  `gradle schemas:generateSchema`

- Build Avrodoc

  `gradle schemas:generateAvroDoc`

  The avro schemas are represented in a html file making it easy to search and understand the schema.

- Run `QueryRecordOutput.java` and verify the logs.



## Directory Tree

```
├── build.gradle
├── custom-conversions
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lavro
│       │   │           ├── EncryptedConversion.java
│       │   │           ├── EncryptedLogicalType.java
│       │   │           ├── EncryptedLogicalTypeFactory.java
│       │   │           ├── ReversedConversion.java
│       │   │           ├── ReversedLogicalType.java
│       │   │           └── ReversedLogicalTypeFactory.java
│       │   └── resources
│       └── test
│           ├── java
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── schemas
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── avro
│       │   │   ├── CustomerObjectModel.avdl
│       │   │   ├── CustomerObjects.avdl
│       │   │   └── query.avdl
│       │   ├── java
│       │   │   └── com
│       │   │       └── lavro
│       │   └── resources
│       └── test
│           ├── java
│           └── resources
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── lavro
    │   │           ├── CustomerObjectModelOutput.java
    │   │           └── QueryRecordOutput.java
    │   └── resources
    └── test
        ├── java
        └── resources


```


## Roadmap

- Add Github workflow
- Add unit tests for Conversions
- Publish To Kafka topic
- Add spotless or checkstyle plugins
- Fix for fields which are union logicaltype and null
- Schema evolution
- Keep the README.md updated

## Tech Stack

**Language:** Java

**Build Tool:** Gradle


## Authors

- [@anilkulkarni](https://github.com/anilkulkarni87)


## Acknowledgements

 - [Awesome Readme Templates](https://readme.so/)
 - [Avrodoc Plus](https://github.com/mikaello/avrodoc-plus)
 - [Gradle Avro Plugin](https://github.com/davidmc24/gradle-avro-plugin)


## 🔗 Links
[![Blog](https://img.shields.io/badge/WordPress-21759B.svg?style=for-the-badge&logo=WordPress&logoColor=white)](https://anilkulkarni.com/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anilakulkarni/)



