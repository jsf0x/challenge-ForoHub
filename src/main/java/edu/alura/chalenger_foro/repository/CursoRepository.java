package edu.alura.chalenger_foro.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.alura.chalenger_foro.models.curso.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long>{

    Page<Curso> findByActivoTrue(Pageable page);

    Optional<Curso> findByIdAndActivoTrue(Long id);

}
