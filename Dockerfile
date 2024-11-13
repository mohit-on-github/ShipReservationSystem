# Stage 1: Build the application
FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:18-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ship-reservation-system.jar /app/ship-reservation-system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ship-reservation-system.jar"]
