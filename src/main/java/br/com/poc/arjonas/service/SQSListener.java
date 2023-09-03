package br.com.poc.arjonas.service;

import br.com.poc.arjonas.config.ApplicationConfig;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSListener {

        @SqsListener("${sqs.queue-envia-documento}")
        public void queueListener(String message) {
            try {
                log.info("message received {} ", message);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }
}
