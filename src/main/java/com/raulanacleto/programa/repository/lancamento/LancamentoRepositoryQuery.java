package com.raulanacleto.programa.repository.lancamento;

import com.raulanacleto.programa.model.Lancamento;
import com.raulanacleto.programa.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar (LancamentoFilter lancamentoFilter);

}
