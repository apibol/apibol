Fluxos para obtenção do token:

1º No browser acesse:

	http://localhost:7777/oauth/authorize?response_type=code&client_id=user-service&redirect_uri=http://example.com&scope=trust&state=97536

2º Digite suas credenciais e aprove a confirmação

3º Depois obtenha o token
	curl user-service:4f7ec648a48b9d3fa239b497f7b6b4d8019697bd@localhost:7777/oauth/token \
 -d grant_type=authorization_code \
 -d client_id=user-service \
 -d redirect_uri=http://example.com \
 -d code=BFg8Vd -s | jq .


Referência:

http://callistaenterprise.se/blogg/teknik/2015/04/27/building-microservices-part-3-secure-APIs-with-OAuth/




