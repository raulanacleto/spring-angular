package com.raulanacleto.programa.service;

import com.raulanacleto.programa.model.Pessoa;
import com.raulanacleto.programa.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

        return this.pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo){
        Pessoa pessoaSalva = pessoaRepository.findById(codigo)
                .orElseThrow(()-> new EmptyResultDataAccessException(1));
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo){
        Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (Objects.isNull(pessoaSalva)){
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }
}
