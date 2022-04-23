FROM openjdk:16
LABEL maintener="beyzanuryuksel"

EXPOSE 8087

ADD target/calendar-service-0.0.1-SNAPSHOT.jar calendar-service.jar

ENTRYPOINT [ "java", "-jar" , "calendar-service.jar"]