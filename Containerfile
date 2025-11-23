# Multi-stage Containerfile for building and running the Spring Boot Kotlin app with Podman

# Builder stage: use official Gradle image with JDK 21 to compile the project
FROM gradle:9.2.1-jdk21 AS builder
WORKDIR /home/gradle/project

# Copy project files and keep ownership for the gradle user
COPY --chown=gradle:gradle . .

# Build the fat jar (skip tests to speed up)
RUN gradle bootJar -x test --no-daemon

# Runtime stage: minimal JRE image
FROM eclipse-temurin:21-jre

# Application jar copied from builder
COPY --from=builder /home/gradle/project/build/libs/*.jar /app/app.jar

WORKDIR /app

# Expose the port used by the application
EXPOSE 7777

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
