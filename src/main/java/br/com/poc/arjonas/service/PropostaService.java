package br.com.poc.arjonas.service;

import br.com.poc.arjonas.dto.request.PropostaRequestDTO;
import br.com.poc.arjonas.dto.response.PropostaResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PropostaService {

    private final WebClient webClient;

    public PropostaService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:3000/api/v1/consulta/proposta/1")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<PropostaResponseDTO> consultaStatusProposta(PropostaRequestDTO request) {

        String clientId = "232323";
        String accessToken = "454545";

        return webClient.get()
                .header("client_id", clientId)
                .header("acess_token", accessToken)
                .retrieve()
                .bodyToMono(PropostaResponseDTO.class);
    }


}
