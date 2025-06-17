package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE :fecha between a.anioNacimiento AND a.anioFallecimiento")
    List<Autor> findForYear(int fecha);

    Optional<Autor> findByNombreContainingIgnoreCase(String nombreAutor);
}
