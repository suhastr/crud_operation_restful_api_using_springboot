# Use the official OpenJDK image for a slim version of Java 17 for the build stage
FROM openjdk:17-slim as build

# Set the working directory in the Docker image for the build stage
WORKDIR /app

# Copy the build tool configuration files and the source code into the Docker image
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Grant execution rights on the gradlew build script
RUN chmod +x ./gradlew

# Build the application without running tests
RUN ./gradlew build -x test

# Use the official OpenJDK image for a slim version of Java 17 for the run stage
FROM openjdk:17-slim

# Set the working directory in the Docker image for the run stage
WORKDIR /app

# Copy only the built JAR from the build stage into the working directory
#COPY --from=build /app/build/libs/*.jar /app/

# Copy files from build stage to a temporary location
COPY --from=build /app/build/libs/*.jar /app/build/tmp/

# Run a shell command to list files in the directory (for debugging purposes)
RUN ls /app/build/tmp/

# Rename a specific file to app.jar
RUN mv /app/build/tmp/demo-0.0.1-SNAPSHOT.jar /app/app.jar



# Set the default command to run the application when the container starts
ENTRYPOINT ["java","-jar","/app/app.jar"]



