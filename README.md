# [sample-assets-estate][repo]

[![Java CI with Maven](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml/badge.svg)](https://github.com/sombriks/sample-assets-estate/actions/workflows/maven.yml)
[![Java CI with Maven](https://gitlab.com/sombriks/sample-assets-estate/badges/main/pipeline.svg)][gitlab]
[![coverage report](https://gitlab.com/sombriks/sample-assets-estate/badges/main/coverage.svg?job=build-job)][gitlab]

Small experiment on server side components served by htmx

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
  proper access control management.
- Components and controls rely on _conditional rendering_ to serve their purpose
  properly. The same template has the default mixin declaration and further
  states of the component/control.
- Tests are mostly integration tests, due to the simplicity to provision a
  complete spring context and database with known state. The need for mocks are
  near to zero. Thanks to [liquibase][liquibase] and its contexts, spacial
  scripts can be used to set the database to a known state.
- Server-side validations are handled by [jsr303][jsr303] bean validation on the
  [DTO][dto] layer.

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
[liquibase]: https://docs.liquibase.com/home.html
[jsr303]: https://docs.spring.io/spring-boot/reference/io/validation.html
[dto]: https://stackoverflow.com/questions/1051182/what-is-a-data-transfer-object-dto
[gitlab]: https://gitlab.com/sombriks/sample-assets-estate
