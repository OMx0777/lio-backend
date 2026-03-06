# Use Eclipse Temurin JDK 17
FROM eclipse-temurin:17-jdk-jammy

# Copy the gradle wrapper and build files
COPY . .

# Build the application
RUN ./gradlew build -x test

# Delete the extra 'plain' jar so Linux doesn't get confused
RUN rm -f build/libs/*-plain.jar

# Find the generated jar and name it app.jar
RUN cp build/libs/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]