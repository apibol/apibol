## Docker and Run instructions

### Build docker image using maven-spotify-plugin

mvn clean install

### Run Docker Container:

docker run --name zuul-proxy -d -p 8888:8888 claudioed/api-gateway-microservice

### Access Routes

Visit: http://localhost:8888/routes to verify enabled routes