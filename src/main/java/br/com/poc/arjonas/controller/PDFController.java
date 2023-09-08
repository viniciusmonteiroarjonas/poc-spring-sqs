package br.com.poc.arjonas.controller;

import br.com.poc.arjonas.dto.request.PropostaRequestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/v1")
public class PDFController {

    @PostMapping("/upload/documento")
    public ResponseEntity<String> uploadDocumentoPdf(@RequestParam("documento") MultipartFile file) {
        try {
            // Verifique se o arquivo é um PDF
            if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
                return ResponseEntity.badRequest().body("O arquivo não é um PDF.");
            }

            // Leia o arquivo PDF como um array de bytes
            byte[] pdfBytes = file.getBytes();

            // Converta os bytes em uma representação base64
            String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

            return ResponseEntity.ok(base64Pdf);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }

    @PostMapping("/download/documento")
    public ResponseEntity<byte[]> downloadPdf(@RequestBody PropostaRequestDTO request) {
        try {
            // Decodifique a representação base64 em bytes
            byte[] pdfBytes = Base64.getDecoder().decode(request.getDocumento());

            // Configure os cabeçalhos de resposta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "documento.pdf");

            // Retorne os bytes do PDF como resposta
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Lidar com erros de decodificação base64
            return ResponseEntity.badRequest().body("A representação base64 é inválida.".getBytes());
        } catch (Exception e) {
            // Lidar com outros erros
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.".getBytes());
        }
    }

}
