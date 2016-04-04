FROM java:8
VOLUME /tmp
ADD config-microservice-1.0-SNAPSHOT.jar config-microservice.jar
RUN bash -c 'touch /config-microservice.jar'
EXPOSE 8001
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/config-microservice.jar"]