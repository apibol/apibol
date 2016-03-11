FROM java:8
VOLUME /tmp
ADD ranking-microservice-1.0-SNAPSHOT.jar ranking-microservice.jar
RUN bash -c 'touch /ranking-microservice.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ranking-microservice.jar"]