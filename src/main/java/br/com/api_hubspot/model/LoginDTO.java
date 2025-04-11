package br.com.api_hubspot.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank
    private String code;
}
