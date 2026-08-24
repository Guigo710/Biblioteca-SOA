package com.example.services_livros.repository;

import com.example.services_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

// criação da comunicação do Spring Data jpa com o BD 
public interface LivroRepository extends JpaRepository<Livro, Long> {
}