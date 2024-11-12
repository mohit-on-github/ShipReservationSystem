# Use an official OpenJDK runtime as a parent image
FROM openjdk:19-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the project
RUN ./mvnw clean install

# Expose the port the app runs on
EXPOSE 9095

# Run the application
CMD ["java", "-jar", "target/your-app-name.jar"]