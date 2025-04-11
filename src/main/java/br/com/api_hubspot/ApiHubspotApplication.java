package br.com.api_hubspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiHubspotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHubspotApplication.class, args);

		System.out.println("API HubSpot em Funcionamento!");
	}
}
