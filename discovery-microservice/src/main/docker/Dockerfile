# Dockerfile for discovery microservice
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
FROM java:8
VOLUME /tmp
ADD discovery-microservice-1.0-SNAPSHOT.jar discovery-microservice.jar
RUN bash -c 'touch /discovery-microservice.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/discovery-microservice.jar"]