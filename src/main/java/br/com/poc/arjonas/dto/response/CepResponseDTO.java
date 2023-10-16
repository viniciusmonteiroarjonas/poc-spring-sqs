package br.com.poc.arjonas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CepResponseDTO {
    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("localidade")
    private String localidade;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("ibge")
    private String ibge;

    @JsonProperty("gia")
    private String gia;

    @JsonProperty("ddd")
    private String ddd;

    @JsonProperty("siafi")
    private String siafi;
}
