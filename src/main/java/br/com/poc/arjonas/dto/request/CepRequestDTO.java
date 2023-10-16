package br.com.poc.arjonas.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CepRequestDTO {
    @JsonProperty("cep")
    private String cep;
}
