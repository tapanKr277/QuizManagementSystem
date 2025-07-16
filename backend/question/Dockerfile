FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/question.jar .

EXPOSE 8083

ENTRYPOINT [ "java", "-jar", "question.jar" ]