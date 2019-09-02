package com.raulanacleto.programa.service;

import com.raulanacleto.programa.model.Lancamento;
import com.raulanacleto.programa.model.Pessoa;
import com.raulanacleto.programa.repository.LancamentoRepository;
import com.raulanacleto.programa.repository.PessoaRepository;
import com.raulanacleto.programa.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento save(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (Objects.isNull(pessoa) || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}
