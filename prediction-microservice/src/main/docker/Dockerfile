# Dockerfile for prediction microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD prediction-microservice-1.0-SNAPSHOT.jar prediction-microservice.jar
RUN bash -c 'touch /prediction-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/prediction-microservice.jar"]