# Dockerfile for user microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD user-microservice-1.0-SNAPSHOT.jar user-microservice.jar
RUN bash -c 'touch /user-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/user-microservice.jar"]