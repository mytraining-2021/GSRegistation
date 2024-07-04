FROM openjdk:17
EXPOSE 8080
WORKDIR /app
ADD target/grocerystore-image.jar /app/grocerystore-image.jar
ENTRYPOINT ["java", "-jar", "grocerystore-image.jar"]
