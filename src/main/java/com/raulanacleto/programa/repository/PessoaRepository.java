package com.raulanacleto.programa.repository;

import com.raulanacleto.programa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
