package br.com.api_hubspot.controller;

import br.com.api_hubspot.config.AutenticacaoConfig;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
public class AutenticacaoController {

    private final AutenticacaoConfig config;

    public AutenticacaoController(AutenticacaoConfig config) {
        this.config = config;
    }

    @GetMapping("/autorizacao")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "retornoLimite")
    public void authorize(HttpServletResponse response, HttpSession session,
                          @RequestParam(value = "state", required = false) String state) throws IOException {

        if (state == null || state.isEmpty()) {
            state = UUID.randomUUID().toString(); // gera um state único
        }

        // Armazena na sessão para validar depois
        session.setAttribute("oauth_state", state);
        String url = config.getAuthorizationUrl()
                + "?client_id=" + config.getClientId()
                + "&redirect_uri=" + config.getRedirectUri()
                + "&scope=" + config.getScopes()
                + "&response_type=code"
                + "&state=" + URLEncoder.encode(state, StandardCharsets.UTF_8);

        response.sendRedirect(url);
    }

    public ResponseEntity<String> retornoLimite(String requestBody, String bearerToken, RequestNotPermitted ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Limite de requisições excedido. Tente novamente mais tarde.");
    }
}
