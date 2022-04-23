FROM openjdk:11
LABEL maintener="beyzanuryuksel"

EXPOSE 8086

ADD target/account-service-0.0.1-SNAPSHOT.jar account-service.jar

ENTRYPOINT [ "java", "-jar" , "account-service.jar"]