FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./build/libs/server-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "app.jar"]