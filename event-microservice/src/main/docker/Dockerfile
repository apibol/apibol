# Dockerfile for event microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD event-microservice-1.0-SNAPSHOT.jar event-microservice.jar
RUN bash -c 'touch /event-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/event-microservice.jar"]