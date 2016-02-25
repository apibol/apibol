FROM java:8
VOLUME /tmp
ADD discovery-microservice-0.1.0.jar discovery-microservice.jar
RUN bash -c 'touch /discovery-microservice.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]