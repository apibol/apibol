# Dockerfile for config microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD config-microservice-1.0-SNAPSHOT.jar config-microservice.jar
RUN bash -c 'touch /config-microservice.jar'
EXPOSE 8001
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/config-microservice.jar"]