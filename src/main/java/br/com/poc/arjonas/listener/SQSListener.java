package br.com.poc.arjonas.listener;

import br.com.poc.arjonas.dto.DocumentoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSListener {

    @Autowired
    ObjectMapper objectMapper;

    @SqsListener("${sqs.queue.envia-documento}")
    public void queueListener(String message) {
        try {
            log.info("message received {} ", message);
            DocumentoRequestDTO request = objectMapper.readValue(message, DocumentoRequestDTO.class);

            // Chama API para consultar status da proposta ...


            // SE STATUS FOR APROVADO ...
            // CHAMA API PARA FAZER O DOSSIE ...
            // CHAMA UNICO PARA FAZER O O UPLOAD DO DOCUMENTO...


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
