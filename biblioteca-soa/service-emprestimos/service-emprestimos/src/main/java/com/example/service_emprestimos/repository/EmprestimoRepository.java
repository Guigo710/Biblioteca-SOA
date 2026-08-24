package com.example.service_emprestimos.repository;

import com.example.service_emprestimos.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}

