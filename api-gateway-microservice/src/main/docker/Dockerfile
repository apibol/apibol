# Dockerfile for api gateway microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD api-gateway-microservice-1.0-SNAPSHOT.jar api-gateway.jar
RUN bash -c 'touch /api-gateway.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/api-gateway.jar"]