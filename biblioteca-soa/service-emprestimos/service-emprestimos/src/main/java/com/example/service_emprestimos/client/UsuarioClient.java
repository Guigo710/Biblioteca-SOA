package com.example.service_emprestimos.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UsuarioClient {

    private final RestClient restClient;

    // Alterado: inicializa o RestClient diretamente sem pedir o Builder
    public UsuarioClient() {
        this.restClient = RestClient.create("http://localhost:8082");
    }

    public boolean usuarioExiste(Long usuarioId) {
        try {
            restClient
                    .get()
                    .uri("/usuarios/{id}", usuarioId)
                    .retrieve()
                    .toBodilessEntity();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}