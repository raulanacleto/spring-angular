package com.raulanacleto.programa.repository;

import com.raulanacleto.programa.model.Lancamento;
import com.raulanacleto.programa.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
