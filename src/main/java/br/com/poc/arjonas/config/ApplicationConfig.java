package br.com.poc.arjonas.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String cloudAwsCredentialsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String cloudAwsCredentialsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String cloudAwsRegion;

    @Value("${sqs.endpoint}")
    private String sqsEndpoint;

    @Value("${sqs.queue.consulta-cep}")
    private String sqsQueueConsultaCep;

    @Value("${sqs.queue.consulta-cep-dlq}")
    private String sqsQueueConsultaCepDLQ;

    @Value("${webservice.url.via-cep}")
    private String webserviceUrlViaCep;
}
