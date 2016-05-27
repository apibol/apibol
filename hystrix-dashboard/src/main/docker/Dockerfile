# Dockerfile for hystrix dashboard microservice
FROM java:8
MAINTAINER Claudio de Oliveira<claudioed.oliveira@gmail.com>
VOLUME /tmp
ADD hystrix-dashboard-1.0-SNAPSHOT.jar hystrix-dashboard.jar
RUN bash -c 'touch /hystrix-dashboard.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hystrix-dashboard.jar"]