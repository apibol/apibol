## Docker and Run instructions

### Build docker image using maven-spotify-plugin

mvn clean install

### Run Docker Container:

docker run --name zuul-proxy -d -p 8761:8761 claudioed/discovery-microservice

### Access Routes

Visit: http://localhost:8761 to verify your cluster