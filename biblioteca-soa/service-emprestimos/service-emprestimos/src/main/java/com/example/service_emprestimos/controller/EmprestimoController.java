package com.example.service_emprestimos.controller;

import com.example.service_emprestimos.model.Emprestimo;
import com.example.service_emprestimos.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo criar(@RequestBody Emprestimo emprestimo) {
        return service.criar(emprestimo);
    }

    @GetMapping
    public List<Emprestimo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}