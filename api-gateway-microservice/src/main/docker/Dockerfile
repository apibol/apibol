FROM java:8
VOLUME /tmp
ADD api-gateway-microservice-1.0-SNAPSHOT.jar api-gateway.jar
RUN bash -c 'touch /api-gateway.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-gateway.jar"]