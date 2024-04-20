# Start with a base image containing Java runtime (version 17 in this case)
FROM openjdk:17-slim as build

# Set the working directory in the Docker image
WORKDIR /app

# Copy the build.gradle and settings.gradle into the Docker image
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code into the Docker image
COPY src src

# Build the application
RUN ./gradlew build -x test

# Run the application
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

