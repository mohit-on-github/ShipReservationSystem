# Stage 1: Build the application
FROM maven:3.8.5-eclipse-temurin-19 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with a smaller footprint
FROM openjdk:19-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set the PORT environment variable to match Render’s requirement
ENV PORT=8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
