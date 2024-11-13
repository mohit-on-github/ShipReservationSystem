# Stage 1: Build the application with Maven
FROM maven:3.9.4-eclipse-temurin-19 AS build
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application with a minimal JDK runtime
FROM openjdk:19-jdk-slim
WORKDIR /app

# Copy only the generated JAR file from the build stage
COPY --from=build /app/target/ShipRS-0.0.1-SNAPSHOT.jar app.jar

# Set the PORT environment variable to match Render’s requirement
ENV PORT=9095
EXPOSE 9095

# Run the application
CMD ["java", "-jar", "app.jar"]
