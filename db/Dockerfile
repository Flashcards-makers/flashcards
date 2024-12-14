FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY src src

RUN chmod +x ./gradlew

ENTRYPOINT ./gradlew clean build update -Dprofile=docker | tee migration-status.txt && tail -f /dev/null

