Token Flow:

Implicit:

    1º In browser go to:

        http://localhost:7777/oauth/authorize?response_type=code&client_id=user-service&redirect_uri=http://example.com&scope=trust&state=97536

    2º Type your credentials and approve the app

    3º Get the token
        curl user-service:4f7ec648a48b9d3fa239b497f7b6b4d8019697bd@localhost:7777/oauth/token \
     -d grant_type=authorization_code \
     -d client_id=user-service \
     -d redirect_uri=http://example.com \
     -d code=BFg8Vd -s | jq .


Client Credentials:

    curl -s user-service:4f7ec648a48b9d3fa239b497f7b6b4d8019697bd@localhost:7777/oauth/token   -d grant_type=client_credentials  -d scope=trust | jq .


References:

http://callistaenterprise.se/blogg/teknik/2015/04/27/building-microservices-part-3-secure-APIs-with-OAuth/




