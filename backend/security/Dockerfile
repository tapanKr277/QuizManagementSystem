FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/security.jar .

EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "security.jar" ]