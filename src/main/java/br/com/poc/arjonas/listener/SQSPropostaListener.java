package br.com.poc.arjonas.listener;

import br.com.poc.arjonas.dto.request.PropostaRequestDTO;
import br.com.poc.arjonas.dto.response.PropostaResponseDTO;
import br.com.poc.arjonas.service.PropostaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSPropostaListener {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PropostaService propostaService;

    @SqsListener("${sqs.queue.envia-documento}")
    public void queueListener(String message) {
        log.info("message received {} ", message);
        try {
            log.info("Transform message in Object PropostaRequestDTO");
            PropostaRequestDTO request = objectMapper.readValue(message, PropostaRequestDTO.class);
            Mono<PropostaResponseDTO> responseProposta = propostaService.consultaStatusProposta(request);

            responseProposta.subscribe(
                    response -> {
                        log.info("Success return from proposal API: {} ", response);
                        if (response.getStatus().equals("APROVADO")) {
                            callyngSignatureContract(response);
                        } else {
                            log.info("Status Proposal is: {}", response.getStatus(), "Enviando dados para fila DLQ");
                        }
                    },
                    error -> {
                        log.error("Return with error from the proposal API: {} ", error);
                        // Send the message to DLQ
                        // sqsTemplate.send("DLQ-Queue-Name", message);
                    }
            );

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private void callyngSignatureContract(PropostaResponseDTO response) {
        log.info("Start filling dossier data");
        // CHAMA API PARA FAZER O DOSSIE ...
        // CHAMA UNICO PARA FAZER O O UPLOAD DO DOCUMENTO...
    }
}
