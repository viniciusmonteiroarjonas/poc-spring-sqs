package br.com.poc.arjonas.controller;

import br.com.poc.arjonas.dto.request.PropostaRequestDTO;
import br.com.poc.arjonas.service.SQSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SQSController {

    @Autowired
    SQSService sqsService;

    @PostMapping("/envia/documento")
    public String sendDocument(@RequestBody PropostaRequestDTO request) {
        sqsService.sendDocument(request.getDocumento());
        return "Enviado documento para fila";
    }
}
