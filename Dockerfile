# Stage 1 : Build JAR
FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests=true

# Stage 2 : Run JAR
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/target/category-service.jar

# Expose application port
EXPOSE 8082

CMD [ "java", "-jar", "/app/target/category-service.jar" ]