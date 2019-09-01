package com.raulanacleto.programa.repository;

import com.raulanacleto.programa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
