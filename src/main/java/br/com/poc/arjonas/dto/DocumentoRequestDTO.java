package br.com.poc.arjonas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoRequestDTO {
    @JsonProperty("documento")
    private String documento;
}
