package br.com.api_hubspot.service;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContatoService {

    @Value("${hubspot.contato-url}")
    private String urlContato;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> criarContato(String requestBody, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> resposta = restTemplate.postForEntity(urlContato, entity, String.class);
            return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar contato: " + e.getMessage());
        }
    }

    public ResponseEntity<String> buscarContatoPorId(String contactId, String bearerToken) {
        String url = urlContato + "/" + contactId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> resposta = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar contato: " + e.getMessage());
        }
    }
}