# Dockerfile for user microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD auth-microservice-1.0-SNAPSHOT.jar auth-microservice.jar
RUN bash -c 'touch /auth-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/auth-microservice.jar"]