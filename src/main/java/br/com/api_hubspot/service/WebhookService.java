package br.com.api_hubspot.service;

import br.com.api_hubspot.model.WebhookDTO;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {

    public void processContactCreation(WebhookDTO dto) {

        if (!"contact.creation".equals(dto.getEventType())) {
            return;
        }

        System.out.println("Novo contato criado:");
        System.out.println("ID: " + dto.getContactId());
        System.out.println("Email: " + dto.getEmail());

        // Implementar abaixo a lógica de acordo com a necessidade
    }
}
