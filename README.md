# [sample-assets-estate][repo]

[![Java CI with Maven](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml/badge.svg)](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml)

Small experiment on several things

## Requirements

- [java 24][java]
- [maven 3.9][maven]
- [spring boot 3.5][spring-boot]
- [sqlite 3][sqlite]
- [htmx.org 2][htmx]
- [pug4j 2][pug] 

## Initial setup

- [spring starter link][initializr]

## How to build

```bash
./mvnw install 
```

## How to run

```bash
./mvnw spring-boot:run
```

## Noteworthy

- 

[repo]: https://github.com/sombriks/sample-assets-estate
[java]: https://dev.java
[maven]: https://maven.apache.org
[spring-boot]: https://spring.io/projects/spring-boot
[sqlite]: https://sqlite.org
[htmx]: https://htmx.org
[pug]: https://github.com/neuland/pug4j
[initializr]: https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.5.0&packaging=jar&jvmVersion=24&groupId=sample&artifactId=assets-estate&name=assets-estate&description=Demo%20project%20for%20Spring%20Boot&packageName=sample.assets.estate&dependencies=devtools,liquibase,data-jpa,web
