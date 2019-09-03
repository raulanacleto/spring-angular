package com.raulanacleto.programa.resource;

import com.raulanacleto.programa.ExceptionHandler.ProgramaExceptionHandler;
import com.raulanacleto.programa.model.Lancamento;
import com.raulanacleto.programa.repository.LancamentoRepository;
import com.raulanacleto.programa.repository.filter.LancamentoFilter;
import com.raulanacleto.programa.service.LancamentoService;
import com.raulanacleto.programa.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.filtrar(lancamentoFilter);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable Long codigo) {
        Optional lancamentoSalvo = this.lancamentoRepository.findById(codigo);
        return lancamentoSalvo.isPresent() ?
                ResponseEntity.ok(lancamentoSalvo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse responseHeader) {
        Lancamento lancamentoSalvo = lancamentoService.save(lancamento);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(lancamentoSalvo.getCodigo()).toUri();
        responseHeader.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(lancamentoSalvo);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<ProgramaExceptionHandler.Erro> erros = Arrays.asList(new ProgramaExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

}
