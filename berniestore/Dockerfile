FROM maven:3.9.10-eclipse-temurin-24-noble AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jdk-noble
WORKDIR /app
COPY --from=build /app/target/berniestore-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/berniestore-0.0.1-SNAPSHOT.jar"]
