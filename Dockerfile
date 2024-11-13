# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from your local machine into the container
COPY target/ShipRS-0.0.1-SNAPSHOT.jar /app/ShipRS-0.0.1-SNAPSHOT.jar

# Make the jar file executable
RUN chmod +x /app/ShipRS-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/ShipRS-0.0.1-SNAPSHOT.jar"]

# Expose port 8080 to allow external access
EXPOSE 8080
