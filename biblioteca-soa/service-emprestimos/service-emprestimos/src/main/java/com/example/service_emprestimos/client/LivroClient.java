package com.example.service_emprestimos.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LivroClient {

    private final RestClient restClient;

    // Alterado: não pede mais o Builder no construtor
    public LivroClient() {
        this.restClient = RestClient.create("http://localhost:8081");
    }

    public boolean livroExiste(Long livroId) {
        try {
            restClient
                    .get()
                    .uri("/livros/{id}", livroId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}