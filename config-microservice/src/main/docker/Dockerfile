FROM java:8
VOLUME /tmp
ADD config-microservice-1.0-SNAPSHOT.jar config-microservice.jar
RUN bash -c 'touch /config-microservice.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-microservice.jar"]