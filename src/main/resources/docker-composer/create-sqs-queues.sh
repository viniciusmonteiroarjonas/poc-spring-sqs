aws configure set aws_access_key_id access_key --profile=localstack
aws configure set aws_secret_access_key secret_key --profile=localstack
aws configure set region sa-east-1 --profile=localstack

## CREATE QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-envia-documento-service-queue-loc --attributes VisibilityTimeout=30,MessageRetentionPeriod=604800,DelaySeconds=5 --profile=localstack

## CREATE QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs list-queues --profile=localstack

## SHOW QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-envia-documento-service-queue-loc --attribute-names All --profile=localstack

## ENVIANDO MENSAGEM ##
aws --endpoint-url=http://localhost:4566 sqs send-message --queue-url http://localhost:4566/000000000000/sqs-envia-documento-service-queue-loc --message-body "{\"numero_proposta\":\"1\",\"cpf_cnpj\":\"12345678901\"}" --profile=localstack
