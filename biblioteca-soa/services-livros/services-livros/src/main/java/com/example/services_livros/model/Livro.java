package com.example.services_livros.model;

import jakarta.persistence.*; // o * é para puxar todas dependencias do jakarta

//criação das tabelas do banco de dados
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    
    private String autor;

    private boolean disponivel;

    public Livro(){

    }

    public Livro(String titulo, String autor){
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public Long getId(){
        return id; // CORRIGIDO: Retorna o 'id' (Long) e não o 'titulo'
    }

    public String getTitulo(){
        return titulo;
    }

//void avisa que não tem retorno de dados. e setTitulo (setter, cria "regra" para validação de dados)
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}