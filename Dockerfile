# Use OpenJDK 17 as the base image
FROM openjdk:17

# Expose port 8080 for your application
EXPOSE 8080

# Set the working directory inside the container
WORKDIR /app

# Add the packaged jar file into our container, in the working directory
ADD target/grocerystore-image.jar /app/grocerystore-image.jar

# Set the command to run your jar file
ENTRYPOINT ["java", "-jar", "grocerystore-image.jar"]
