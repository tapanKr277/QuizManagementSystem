# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-jammy

# Set a working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/eureka-server.jar eureka-server.jar

# Expose the default port for Eureka (usually 8761)
EXPOSE 8761

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
