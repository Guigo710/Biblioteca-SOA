package com.example.service_emprestimos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long livroId;

    private Long usuarioId;

    private boolean ativo;

    public Emprestimo() {
    }

    public Emprestimo(Long livroId, Long usuarioId) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.ativo = true;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}