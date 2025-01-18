FROM maven:3.8.7-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and dependencies first for caching
COPY subfolder/pom.xml .

# Copy the entire project folder
COPY subfolder/src ./src

# Build the project using Maven
RUN mvn clean install -DskipTests

# Use a lightweight JDK for the final image
FROM openjdk:11-jre-slim

# Set the working directory for the application
WORKDIR /app

# Copy the built JAR files from the build stage
COPY --from=build /app/target/*.jar /app/selenium-tests.jar

# Command to run the Selenium tests
CMD ["java", "-jar", "selenium-tests.jar"]
