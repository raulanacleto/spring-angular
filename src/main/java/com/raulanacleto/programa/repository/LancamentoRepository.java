package com.raulanacleto.programa.repository;

import com.raulanacleto.programa.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
