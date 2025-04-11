package br.com.api_hubspot.controller;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CallbackController {

    @GetMapping("/callback")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public ResponseEntity<Map<String, String>> retornoCode(@RequestParam(required = false) String code,
                                           @RequestParam(required = false) String state,
                                           HttpSession session) {
        // Recupera o state armazenado na sessão
        String savedState = (String) session.getAttribute("oauth_state");

        if (code == null || code.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao gerar code"));
        }

        if (state == null || !state.equals(savedState)) {
            return ResponseEntity.badRequest().body(Map.of("error", "State inválido ou ausente"));
        }

        // Remove o state da sessão após uso
        session.removeAttribute("oauth_state");

        return ResponseEntity.ok(Map.of("code", code));
    }

    public ResponseEntity<String> retornoLimite(String requestBody, String bearerToken, RequestNotPermitted ex) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Limite de requisições excedido. Tente novamente mais tarde.");
    }
}
