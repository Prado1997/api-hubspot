package br.com.api_hubspot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WebhookDTO {
    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("contactId")
    private String contactId;

    @JsonProperty("email")
    private String email;
}
