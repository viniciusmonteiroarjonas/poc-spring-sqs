package br.com.poc.arjonas.service;

import br.com.poc.arjonas.client.SQSClient;
import br.com.poc.arjonas.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SQSService {

    @Autowired
    SQSClient sqsClient;

    public void sendDocument(String documento) {
        System.out.println("Documento: " + documento);
        sqsClient.sendMessage(documento, "/000000000000/sqs-envia-documento-service-queue-loc");
    }
}
