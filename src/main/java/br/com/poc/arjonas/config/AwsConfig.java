package br.com.poc.arjonas.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "http://localhost:4566",
                                "sa-east-1"
                        )
                )
                .build();
    }

}
