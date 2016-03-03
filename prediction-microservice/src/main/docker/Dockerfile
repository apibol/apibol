FROM java:8
VOLUME /tmp
ADD prediction-microservice-1.0-SNAPSHOT.jar prediction-microservice.jar
RUN bash -c 'touch /hystrix-dashboard.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/prediction-microservice.jar"]