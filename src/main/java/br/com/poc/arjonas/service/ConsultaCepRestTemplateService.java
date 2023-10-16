package br.com.poc.arjonas.service;


import br.com.poc.arjonas.config.ApplicationConfig;
import br.com.poc.arjonas.dto.request.CepRequestDTO;
import br.com.poc.arjonas.dto.response.CepResponseDTO;
import br.com.poc.arjonas.exception.ViaCepServiceException;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class ConsultaCepRestTemplateService {

    private final RestTemplate restTemplate;
    private final ApplicationConfig applicationConfig;

    public CepResponseDTO consultaCep(CepRequestDTO request) {
        try {
            Gson json = new Gson();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<CepResponseDTO> response = restTemplate.exchange(applicationConfig.getWebserviceUrlViaCep() + request.getCep() + "/json", HttpMethod.GET, requestEntity, CepResponseDTO.class);
            log.info("Dados de endereço do cliente, retornando do webservice via CEP: {}", json.toJson(response.getBody()));
            return response.getBody();
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new ViaCepServiceException("Erro ao chamar o serviço ViaCEP: " + ex.getMessage());
        } catch (Exception ex) {
            throw new ViaCepServiceException("Erro ao chamar o serviço ViaCEP: " + ex.getMessage());
        }
    }
}
