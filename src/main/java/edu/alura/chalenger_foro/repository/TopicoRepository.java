package edu.alura.chalenger_foro.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.alura.chalenger_foro.models.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico,Long>{
    Page<Topico> findByStatusTrue(Pageable page);
    Optional<Topico> findByIdAndStatusTrue(long id);
}
