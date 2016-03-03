FROM java:8
VOLUME /tmp
ADD championship-microservice-1.0-SNAPSHOT.jar championship-microservice.jar
RUN bash -c 'touch /championship-microservice.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/championship-microservice.jar"]