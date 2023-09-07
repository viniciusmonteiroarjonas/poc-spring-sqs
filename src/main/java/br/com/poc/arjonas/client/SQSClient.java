package br.com.poc.arjonas.client;

import br.com.poc.arjonas.config.ApplicationConfig;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SQSClient {

    @Autowired
    private AmazonSQS sqs;

    private final ApplicationConfig applicationConfig;

    public void sendMessage(String message, String url) {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(applicationConfig.getGetSqsQueueEnviaDocumento())
                .addAttributesEntry(QueueAttributeName.VisibilityTimeout.toString(), "30") // Mensagem ficara invisel apoś ser consumida após 30 segundos
                .addAttributesEntry(QueueAttributeName.MessageRetentionPeriod.toString(), "86400"); // Mensagem será mantida na fila por 1 dia

        String queueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
        System.out.println("Fila criada com URL: " + queueUrl);
    }
}
