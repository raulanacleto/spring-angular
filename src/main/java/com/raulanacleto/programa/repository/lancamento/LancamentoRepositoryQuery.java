package com.raulanacleto.programa.repository.lancamento;

import com.raulanacleto.programa.model.Lancamento;
import com.raulanacleto.programa.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar (LancamentoFilter lancamentoFilter, Pageable pageable);

}
