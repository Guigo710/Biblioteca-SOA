package com.example.service_emprestimos.service;

import com.example.service_emprestimos.client.LivroClient;
import com.example.service_emprestimos.client.UsuarioClient;
import com.example.service_emprestimos.model.Emprestimo;
import com.example.service_emprestimos.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository repository;
    private final LivroClient livroClient;
    private final UsuarioClient usuarioClient;

    public EmprestimoService(
            EmprestimoRepository repository,
            LivroClient livroClient,
            UsuarioClient usuarioClient) {

        this.repository = repository;
        this.livroClient = livroClient;
        this.usuarioClient = usuarioClient;
    }

    public Emprestimo criar(Emprestimo emprestimo) {

        if (!livroClient.livroExiste(emprestimo.getLivroId())) {
            throw new RuntimeException("Livro não encontrado");
        }

        if (!usuarioClient.usuarioExiste(emprestimo.getUsuarioId())) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return repository.save(emprestimo);
    }

    public List<Emprestimo> listar() {
        return repository.findAll();
    }

    public Emprestimo buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Empréstimo não encontrado"));
    }
}