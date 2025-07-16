FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/quiz.jar .

EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "quiz.jar" ]