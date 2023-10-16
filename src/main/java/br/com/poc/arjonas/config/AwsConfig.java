package br.com.poc.arjonas.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final ApplicationConfig applicationConfig;

    @Bean
    @Primary
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                applicationConfig.getSqsEndpoint(),
                                applicationConfig.getCloudAwsRegion()
                        )
                )
                .build();
    }

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                applicationConfig.getSqsEndpoint(),
                                applicationConfig.getCloudAwsRegion()
                        )
                )
                .build();
    }

}
