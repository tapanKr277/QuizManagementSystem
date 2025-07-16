FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/apigateway.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apigateway.jar"]