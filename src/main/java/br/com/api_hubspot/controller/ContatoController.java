package br.com.api_hubspot.controller;

import br.com.api_hubspot.service.ContatoService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public ResponseEntity<String> criarContato(@RequestBody String requestBody, @RequestHeader("Authorization") String bearerToken) {
        return contatoService.criarContato(requestBody, bearerToken);
    }

    @GetMapping("/{id}")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public ResponseEntity<String> buscarContato(@PathVariable("id") String contactId, @RequestHeader("Authorization") String bearerToken) {
        return contatoService.buscarContatoPorId(contactId, bearerToken);
    }

    public ResponseEntity<String> retornoLimite(String requestBody, String bearerToken, RequestNotPermitted ex) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Limite de requisições excedido. Tente novamente mais tarde.");
    }
}