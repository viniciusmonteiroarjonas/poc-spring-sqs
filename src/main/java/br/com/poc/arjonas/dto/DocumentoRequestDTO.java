package br.com.poc.arjonas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DocumentoRequestDTO {

    @JsonProperty("numero_proposta")
    private String numeroProposta;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("documento")
    private String documento;



}
