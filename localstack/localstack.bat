@echo off
set server=server.bieniek
set port=4566
set endpoint=--endpoint http://%server%:%port%
set endpoint_url=--endpoint-url http://%server%:%port%
set tabela_dynamo=cadastro_medicos.json

echo ### Criando Tabela no DynamoDB do LocalStack...
aws %endpoint_url% dynamodb create-table --cli-input-json file://%tabela_dynamo%