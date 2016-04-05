FROM java:8
VOLUME /tmp
ADD user-microservice-1.0-SNAPSHOT.jar user-microservice.jar
RUN bash -c 'touch /user-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/user-microservice.jar"]