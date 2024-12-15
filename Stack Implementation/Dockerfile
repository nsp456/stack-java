FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/stack-0.0.1-SNAPSHOT.jar"]