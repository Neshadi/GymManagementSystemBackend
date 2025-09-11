# Use Java 17 JDK as base image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Make Maven wrapper executable (just in case)
RUN chmod +x mvnw

# Build the Spring Boot JAR using Maven
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Render will map it to its dynamic PORT)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "target/GymManagementSystem-0.0.1-SNAPSHOT.jar"]
