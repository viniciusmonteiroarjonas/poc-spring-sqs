package br.com.poc.arjonas.client;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SQSClient {

    @Autowired
    private AmazonSQS sqsClient;

    public void sendMessage(String message, String url) {

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(url)
                .withMessageBody(message);

        sqsClient.sendMessage(sendMessageRequest);

    }
}
