## Use Java 17 JDK as base image
#FROM eclipse-temurin:17-jdk
#
## Set working directory inside the container
#WORKDIR /app
#
## Copy all project files into the container
#COPY . .
#
## Make Maven wrapper executable (just in case)
#RUN chmod +x mvnw
#
## Build the Spring Boot JAR using Maven
##RUN ./mvnw clean package -DskipTests
#
## Copy the built jar from target folder
#COPY target/*.jar app.jar
#
## Expose port 8080 (Render will map it to its dynamic PORT)
#EXPOSE 8080
#
## Command to run the Spring Boot application
#CMD ["java", "-jar", "target/GymManagementSystem-0.0.1-SNAPSHOT.jar"]



# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first (to leverage Docker cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies (Docker cache optimization)
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Build the Spring Boot application (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (Render maps its dynamic port automatically)
EXPOSE 8080

# Set Spring Boot to listen on all interfaces (inside container)
ENV JAVA_OPTS="-Dserver.port=8080 -Dserver.address=0.0.0.0"

# Run the Spring Boot JAR
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/*.jar"]
