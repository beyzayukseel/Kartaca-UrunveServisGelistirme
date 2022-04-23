FROM openjdk:16
LABEL maintener="beyzanuryuksel"

EXPOSE 8762

ADD target/service-registry-0.0.1-SNAPSHOT.jar service-registry.jar

ENTRYPOINT [ "java", "-jar" , "service-registry.jar"]
