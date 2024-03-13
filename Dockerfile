#
# Packaging
#
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY build/libs/Test-0.0.1-SNAPSHOT.jar /app/teamviewer-app.jar
COPY build/resources/main /app/resources

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "teamviewer-app.jar"]