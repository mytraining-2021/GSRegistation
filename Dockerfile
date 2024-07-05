FROM openjdk:17
USER rooot
EXPOSE 8080
ADD target/GSRegistation-*.jar GSRegistation.jar
ENTRYPOINT ["java","-jar","GSRegistation.jar"]