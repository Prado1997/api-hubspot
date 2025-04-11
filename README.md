# API HubSpot

API desenvolvida para realizar integração com a plataforma HubSpot, com funcionalidade de autenticação (OAuth 2.0), criação e consulta de Contatos no CRM.

## Tecnologias Utilizadas

* Linguagem de Programação: Java.
* Framework: Spring Boot.
* Outras tecnologias relevantes: Lombok, Resilience4j, Springdoc OpenAPI.

## Como Executar o Projeto

* Clone o seguinte repositório: https://github.com/Prado1997/api-hubspot.git
* Abra a pasta do projeto em uma IDE (preferencialmente IntelliJ)
* Configure as variáveis de ambiente conforme as credenciais do seu Aplicativo
* Execute a classe principal do projeto: ApiHubspotApplication.java
* Por se tratar de um projeto local, após iniciar acesse http://localhost:8080/autorizacao
* Acessar o link acima e fazer login em sua conta HubSpot e escolher o aplicativo criado em sua conta
* Você será redirecionado para a url de callback, com o code gerado
* Faça uma requisição do tipo POST para http://localhost:8080/login, passando code no body
* Será retornado o access_token e com esse token você fará as outras requisições

### Pré-Requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

* Java 17+
* Maven 3.8+
* Conta de desenvolvedor no HubSpot → Criar Conta

### Configuração de Variáveis de Ambiente

Certifique-se de ter criado as seguintes variáveis de ambiente:

* \${HUBSPOT_CLIENT_ID}
* \${HUBSPOT_REDIRECT_URI}
* \${HUBSPOT_AUTHORIZATION_URL}
* \${HUBSPOT_SCOPES}
* \${HUBSPOT_CLIENT_SECRET}

## Documentação da API

A documentação completa da API pode ser acessada através do seguinte link:

* http://localhost:8080/swagger-ui/index.html

Esta documentação fornece detalhes sobre todos os endpoints, métodos HTTP, parâmetros, corpos de requisição e respostas da API.