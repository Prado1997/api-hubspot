package br.com.api_hubspot.controller;

import br.com.api_hubspot.model.WebhookDTO;
import br.com.api_hubspot.service.WebhookService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private WebhookService service;

    @PostMapping("/contatos")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public void receiveContactWebhook(@RequestBody WebhookDTO dto) {
        service.processContactCreation(dto);
    }

    public ResponseEntity<String> retornoLimite(String requestBody, String bearerToken, RequestNotPermitted ex) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Limite de requisições excedido. Tente novamente mais tarde.");
    }
}
