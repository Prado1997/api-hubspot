package br.com.api_hubspot.controller;

import br.com.api_hubspot.model.LoginDTO;
import br.com.api_hubspot.service.TokenService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String tokenResponse = tokenService.gerarToken(loginDTO.getCode());
        return ResponseEntity.ok(tokenResponse);
    }

    public ResponseEntity<String> retornoLimite(String requestBody, String bearerToken, RequestNotPermitted ex) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Limite de requisições excedido. Tente novamente mais tarde.");
    }
}
