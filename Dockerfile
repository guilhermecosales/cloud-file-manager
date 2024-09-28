# Build stage
FROM gradle:jdk21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy necessary files
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src

# Build the project
RUN gradle build -x test --no-daemon --refresh-dependencies

FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY --from=build /app/build/libs/*.jar ./application.jar

# Run the application
ENTRYPOINT ["java", "-jar", "application.jar"]