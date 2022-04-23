FROM openjdk:16
LABEL maintener="beyzanuryuksel"

EXPOSE 9191

ADD target/gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

ENTRYPOINT [ "java", "-jar" , "api-gateway.jar"]