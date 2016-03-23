FROM java:8
VOLUME /tmp
ADD user-microservice-1.0-SNAPSHOT.jar user-microservice.jar
RUN bash -c 'touch /user-microservice.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-microservice.jar"]