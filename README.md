# [sample-assets-estate][repo]

[![Java CI with Maven](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml/badge.svg)](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml)

Small experiment on several things

## Requirements

- [java 24][java]
- [maven 3.9][maven]
- [spring boot 3.5][spring-boot]
- [sqlite 3][sqlite]
- [htmx.org 2][htmx]
- [bulma.css][bulma]
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

- One key aspect studied here is the transactional behavior of asset management.
  Instead of keep history records, the asset record itself is a transactional
  entry properly located in time.
- Frontend experiment involves full [HATEOAS][hateoas] compliance combined with
  access control management.
- Components and fragments are quite similar, except that fragments are meant to
  server-side composition only
- 

[repo]: https://github.com/sombriks/sample-assets-estate
[java]: https://dev.java
[maven]: https://maven.apache.org
[spring-boot]: https://spring.io/projects/spring-boot
[sqlite]: https://sqlite.org
[htmx]: https://htmx.org
[bulma]: https://bulma.io
[pug]: https://github.com/neuland/pug4j
[initializr]: https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.5.0&packaging=jar&jvmVersion=24&groupId=sample&artifactId=assets-estate&name=assets-estate&description=Demo%20project%20for%20Spring%20Boot&packageName=sample.assets.estate&dependencies=devtools,liquibase,data-jpa,web
[hateoas]: https://htmx.org/essays/hateoas
