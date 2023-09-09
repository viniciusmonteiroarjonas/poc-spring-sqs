package br.com.poc.arjonas.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProcessDLQMessagesSchedule {


    //    @Scheduled(fixedDelay = 20 * 60 * 1000) // Executa a cada 20 minutos
    @Scheduled(fixedRate = 30_000) // Executa a cada 30 segundos
    public void processDLQMessages() {
        System.out.println("Tarefa agendada executada a cada 30 segundos.");
//        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
//                .withQueueUrl(dlqUrl())
//                .withMaxNumberOfMessages(10)
//                .withWaitTimeSeconds(20); // Tempo de espera para novas mensagens na fila DLQ
//
//        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(receiveRequest);
//
//        for (Message message : receiveMessageResult.getMessages()) {
//            // Processar mensagem da fila DLQ
//            // Se o processamento for bem-sucedido, a mensagem pode ser excluída
//            // Se ocorrer um erro durante o processamento, você pode deixar a mensagem na fila DLQ
//        }
    }
}
