# Build Stage
FROM maven:3.6.3-openjdk-8-slim AS build

WORKDIR /opt/app

COPY ../pom.xml .
COPY pom.xml currency-service/pom.xml
COPY src currency-service/src

RUN mvn dependency:go-offline -B
RUN mvn -f currency-service/pom.xml clean package

# Run Stage
# FROM openjdk:8u275-jre
# ADD ./target/api-gateway.jar /app/
# CMD ["java", "-Xmx200m", "-jar", "/app/api-gateway.jar"]
# EXPOSE 8001