package br.com.poc.arjonas.schedule;

import br.com.poc.arjonas.config.ApplicationConfig;
import br.com.poc.arjonas.dto.request.CepRequestDTO;
import br.com.poc.arjonas.dto.response.CepResponseDTO;
import br.com.poc.arjonas.exception.ViaCepServiceException;
import br.com.poc.arjonas.service.ConsultaCepWebFluxService;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProcessDLQMessagesSchedule {

    @Autowired
    AmazonSQS amazonSQS;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ConsultaCepWebFluxService consultaCepService;

    private final ApplicationConfig applicationConfig;


    /**
     * Implementação consulta CEP.
     * Ao receber uma mensagem na fila SQS, fazemos uma chamada no webservice viaCep utilizamos o WebFlux.
     */
    @Scheduled(fixedRate = 6000)
    public void processDLQMessages() {
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
                .withQueueUrl(applicationConfig.getSqsQueueConsultaCepDLQ())
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(20);

        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(receiveRequest);
        List<Message> messages = receiveMessageResult.getMessages();

        if (messages.isEmpty()) {
            log.info("A fila está vazia.");
        } else {
            for (Message message : messages) {
                log.info("Received message= {} with messageId= {}", message.toString(), message.getMessageId());
                try {
                    CepRequestDTO request = objectMapper.readValue(message.getBody(), CepRequestDTO.class);
                    Mono<CepResponseDTO> responseCep = consultaCepService.consultaCep(request);
                    responseCep.subscribe(
                            response -> {
                                log.info("Success return: {} ", response);
                            },
                            error -> {
                                throw new ViaCepServiceException("Erro ao chamar o serviço ViaCEP: " + error.getMessage());
                            }
                    );
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                }

            }
        }

    }
}
