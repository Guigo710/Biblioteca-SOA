package com.example.services_livros.service;

import com.example.services_livros.model.Livro;
import com.example.services_livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
//cria a conexão com o meu Repository ** final ** (garante que essa conexão nao mude depois de criar)
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrar(Livro livro) {
        livro.setDisponivel(true);

        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }

//aqui ele faz a busca pelo id o .orElseThrow seria um else do "if" findbyid onde ele retorna um erro e aparece a frase que coloquei (Livro não encontrado)
    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Livro não encontrado"));
    }
}