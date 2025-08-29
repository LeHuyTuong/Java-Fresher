FROM openjdk:21

ARG FILE_JAR=target/sample-code-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service.jar

ENTRYPOINT ["java" , "-jar", "api-service.jar"]

EXPOSE 80