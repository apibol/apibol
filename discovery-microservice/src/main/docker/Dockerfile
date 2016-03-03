FROM java:8
VOLUME /tmp
ADD discovery-microservice-1.0-SNAPSHOT.jar discovery-microservice.jar
RUN bash -c 'touch /discovery-microservice.jar'
EXPOSE 8761
ENTRYPOINT ["java","-jar","/discovery-microservice.jar"]