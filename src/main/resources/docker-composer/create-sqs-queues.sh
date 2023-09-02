aws configure set aws_access_key_id access_key --profile=localstack
aws configure set aws_secret_access_key secret_key --profile=localstack
aws configure set region sa-east-1 --profile=localstack

## CREATE QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name sqs-envia-documento-service-queue-loc --profile=localstack

## CREATE QUEUE ##
aws --endpoint-url=http://localhost:4566 sqs list-queues --profile=localstack

## SHOW QUEUE ##
aws sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/sqs-envia-documento-service-queue-loc --profile=localstack
