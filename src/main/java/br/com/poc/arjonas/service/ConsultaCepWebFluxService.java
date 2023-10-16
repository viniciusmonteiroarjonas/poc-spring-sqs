package br.com.poc.arjonas.service;

import br.com.poc.arjonas.config.ApplicationConfig;
import br.com.poc.arjonas.dto.request.CepRequestDTO;
import br.com.poc.arjonas.dto.response.CepResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ConsultaCepWebFluxService {
    private final WebClient webClient;
    private final ApplicationConfig applicationConfig;

    public ConsultaCepWebFluxService(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
        this.webClient = WebClient.builder()
                .baseUrl(applicationConfig.getWebserviceUrlViaCep())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<CepResponseDTO> consultaCep(CepRequestDTO request) {
        return webClient.get()
                .uri(request.getCep() + "/json/")
                .retrieve()
                .bodyToMono(CepResponseDTO.class);
    }


}
