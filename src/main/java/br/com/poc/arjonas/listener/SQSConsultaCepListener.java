package br.com.poc.arjonas.listener;

import br.com.poc.arjonas.dto.request.CepRequestDTO;
import br.com.poc.arjonas.service.ConsultaCepRestTemplateService;
import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSConsultaCepListener {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ConsultaCepRestTemplateService consultaCepService;

    /**
     * Implementação consulta CEP.
     * Ao receber uma mensagem na fila SQS, fazemos uma chamada no webservice viaCep utilizamos o RestTemplate.
     * @param message
     * @param messageId
     */
    @SqsListener("${sqs.queue.consulta-cep}")
    public void getMessagesQueueCep(Message message, @Header("MessageId") String messageId) throws JsonProcessingException {
        log.info("Received message= {} with messageId= {}", message.toString(), messageId);
        CepRequestDTO request = objectMapper.readValue(message.getBody(), CepRequestDTO.class);
        consultaCepService.consultaCep(request);
    }
}
