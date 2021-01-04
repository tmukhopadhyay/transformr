# Build Stage
# FROM maven:3.6.3-openjdk-8-slim AS build

# WORKDIR /opt/app

# COPY ../pom.xml .
# COPY pom.xml api-gateway/pom.xml
# COPY src api-gateway/src

# RUN mvn dependency:go-offline -B
# RUN mvn -f api-gateway/pom.xml clean package

# Run Stage
FROM openjdk:8u275-jre
VOLUME /tmp
ADD target/*.jar app.jar

HEALTHCHECK --interval=5s --timeout=60s CMD curl -f http://localhost:8888/actuator/health || exit 1

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]