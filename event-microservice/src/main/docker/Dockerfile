# Dockerfile for event microservice
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
FROM java:8
VOLUME /tmp
ADD event-microservice-1.0-SNAPSHOT.jar event-microservice.jar
RUN bash -c 'touch /event-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/event-microservice.jar"]