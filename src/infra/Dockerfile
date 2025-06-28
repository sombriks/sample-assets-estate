FROM eclipse-temurin:24-jdk-alpine AS stage1
WORKDIR /work
ADD pom.xml mvnw ./
ADD src/ ./src/
ADD .mvn ./.mvn/
RUN ./mvnw install -Dmaven.test.skip

FROM eclipse-temurin:24-jre-alpine AS stage2
WORKDIR /app
COPY --from=stage1 /work/target/assets-estate-0.0.1-SNAPSHOT.jar .
ENTRYPOINT exec java -jar assets-estate-0.0.1-SNAPSHOT.jar

# docker build \
#  -t sombriks/assets-estate:testing
#  -f src/infra/Dockerfile .
