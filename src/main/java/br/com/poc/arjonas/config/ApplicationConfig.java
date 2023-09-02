package br.com.poc.arjonas.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApplicationConfig {

    @Value("${sqs.endpoint}")
    private String getSqsEndpoint;

    @Value("${sqs.url.envia-documento}")
    private String getSqsUrlEnviaDocumento;

    @Value("${cloud.aws.credentials.access-key}")
    private String getCloudAwsCredentialsAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String getCloudAwsCredentialsSecretKey;

    @Value("${cloud.aws.region}")
    private String getCloudAwsRegion;
}