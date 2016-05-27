# Dockerfile for config-file-microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD config-file-microservice-1.0-SNAPSHOT.jar config-file-microservice.jar
RUN bash -c 'touch /config-file-microservice.jar'
EXPOSE 8001
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/config-file-microservice.jar"]