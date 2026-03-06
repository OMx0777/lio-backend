# Use OpenJDK 17 (standard for Spring Boot)
FROM openjdk:17-jdk-slim

# Copy the gradle wrapper and build files
COPY . .

# Build the application
RUN ./gradlew build -x test

# Find the generated jar and name it app.jar
RUN cp build/libs/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]