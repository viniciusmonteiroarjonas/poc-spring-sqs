package br.com.poc.arjonas.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationConfig {

    @Value("${sqs.endpoint}")
    private String getSqsEndpoint;

    @Value("${sqs.queue.envia-documento}")
    private String getSqsQueueEnviaDocumento;

    @Value("${cloud.aws.credentials.access-key}")
    private String getCloudAwsCredentialsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String getCloudAwsCredentialsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String getCloudAwsRegion;
}
