FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./build/libs/auth-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "app.jar"]