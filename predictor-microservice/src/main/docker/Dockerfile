FROM java:8
VOLUME /tmp
ADD predictor-microservice-1.0-SNAPSHOT.jar predictor-microservice.jar
RUN bash -c 'touch /predictor-microservice.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/predictor-microservice.jar"]