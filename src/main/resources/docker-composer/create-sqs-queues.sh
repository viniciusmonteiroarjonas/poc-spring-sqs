aws configure set aws_access_key_id access_key --profile=localstack
aws configure set aws_secret_access_key secret_key --profile=localstack
aws configure set region sa-east-1 --profile=localstack

## CREATE QUEUES ##
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-consulta-cep-service-queue-loc --profile=localstack
aws --endpoint-url http://localhost:4566 sqs create-queue --queue-name sqs-consulta-cep-service-queue-loc-DLQ --attributes '{"RedrivePolicy": "{\"deadLetterTargetArn\":\"arn:aws:sqs:sa-east-1:000000000000:sqs-consulta-cep-service-queue-loc\",\"maxReceiveCount\":\"5\"}"}' --profile=localstack


## LIST QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs list-queues --profile=localstack

## SHOW QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-consulta-cep-service-queue-loc --attribute-names All --profile=localstack

## SHOW QUEUE DLQ ##
aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-consulta-cep-service-queue-loc-dql --attribute-names All --profile=localstack


## ENVIANDO MENSAGEM - TESTE SUCESSO##
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/sqs-consulta-cep-service-queue-loc --message-body "{\"cep\":\"05271000\"}" --profile=localstack

## ENVIANDO MENSAGEM - TESTE ERRO - ENVIA PARA VILA DQL##
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/sqs-consulta-cep-service-queue-loc --message-body "{\"cep\":\"062801900\"}" --profile=localstack
