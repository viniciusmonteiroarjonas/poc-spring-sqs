package br.com.poc.arjonas.service;

import br.com.poc.arjonas.client.SQSClient;

import br.com.poc.arjonas.config.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SQSService {

    @Autowired
    SQSClient sqsClient;

    private final ApplicationConfig applicationConfig;

    public void sendDocument(String documento) {
        sqsClient.sendMessage(documento, applicationConfig.getGetSqsQueueEnviaDocumento());
    }
}
