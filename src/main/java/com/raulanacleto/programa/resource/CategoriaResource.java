package com.raulanacleto.programa.resource;

import com.raulanacleto.programa.model.Categoria;
import com.raulanacleto.programa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED) // pra retornar code 201 created.
    @PostMapping // post insere novos registros a
    public ResponseEntity<Categoria> salva(@Valid @RequestBody Categoria categoria, HttpServletResponse responseHeader) { //requestBody faz o que vier do postman virar objeto aqui
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        responseHeader.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categoriaSalva);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
        Optional categoria = this.categoriaRepository.findById(codigo);
        return categoria.isPresent() ?
                ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
    }


}
