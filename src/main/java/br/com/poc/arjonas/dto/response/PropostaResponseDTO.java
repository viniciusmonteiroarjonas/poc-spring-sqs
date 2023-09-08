package br.com.poc.arjonas.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PropostaResponseDTO {

    @JsonProperty("status")
    private String status;
}
