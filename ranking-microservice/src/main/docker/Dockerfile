# Dockerfile for ranking microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD ranking-microservice-1.0-SNAPSHOT.jar ranking-microservice.jar
RUN bash -c 'touch /ranking-microservice.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ranking-microservice.jar"]