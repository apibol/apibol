## Run RabbitMQ in Docker Container

    docker run -d --hostname my-rabbit --name some-rabbit -p 15671:15672 -p 5672:5672 rabbitmq:3.6.1-management