FROM openjdk:17
EXPOSE 8080
ADD target/GSRegistation-*.jar grocerystore-image.jar
ENTRYPOINT ["java","-jar","grocerystore-image.jar"]