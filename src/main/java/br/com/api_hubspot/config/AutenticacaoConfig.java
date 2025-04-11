package br.com.api_hubspot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "hubspot")
public class AutenticacaoConfig {
    @Value("${hubspot.token-url}")
    private String tokenUrl;

    private String clientId;
    private String redirectUri;
    private String authorizationUrl;
    private String scopes;
    private String clientSecret;
}
