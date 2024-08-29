# # Use an official Tomcat image with JDK 8 from the Docker Hub
# FROM tomcat:9.0-jdk8-openjdk-slim

# # Set the working directory inside the container
# WORKDIR /usr/local/tomcat

# # Copy the WAR file to the webapps directory of Tomcat
# COPY target/your-app-name.war /usr/local/tomcat/webapps/

# # Expose the port that Tomcat is running on (default is 8080)
# EXPOSE 8090

# # Start Tomcat server
# CMD ["catalina.sh", "run"]

# Use an official Java runtime as a parent image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/spring-boot-web-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on (default is 8080)
EXPOSE 8090

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]