# Use an official OpenJDK runtime as a parent image
FROM openjdk:19-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file and install dependencies
COPY pom.xml .
RUN mkdir -p target

# Copy the project source code to the container
COPY . .

# Package the application (using Maven wrapper if included, otherwise use mvn)
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot application
CMD ["java", "-jar", "target/ShipRS-0.0.1-SNAPSHOT.jar"]

# Expose the application port
EXPOSE 9095
