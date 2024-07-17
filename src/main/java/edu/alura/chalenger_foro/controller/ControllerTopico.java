package edu.alura.chalenger_foro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import edu.alura.chalenger_foro.DTO.DatosDTOTopico;
import edu.alura.chalenger_foro.infra.mis_execpciones.NoExiste;
import edu.alura.chalenger_foro.models.topico.DatosActualizarTopico;
import edu.alura.chalenger_foro.models.topico.DatosTopico;
import edu.alura.chalenger_foro.service.ServiceTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/topico")
@SecurityRequirement(name = "bearer-key")
public class ControllerTopico {
    @Autowired
    private ServiceTopico service;

    @GetMapping()
    public ResponseEntity<Page<DatosDTOTopico>> showAllTopicos(
            @PageableDefault(size = 3, sort = "titulo") Pageable page) {
        var topicos = service.getAllTopicos(page);
        return ResponseEntity.ok(topicos);
    }

    @PostMapping()
    public ResponseEntity<DatosDTOTopico> registrarTopico(@RequestBody @Valid DatosTopico datoTopico,
            UriComponentsBuilder uriComponentsBuilder) throws NoExiste {
        var topico = service.registrarTopico(datoTopico);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDTOTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDTOTopico> showTopico(@PathVariable Long id) throws NoExiste {
        var topico = service.getTopicoById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDTOTopico> updateTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosTopico) throws NoExiste{
        var topico = service.updateTopico(id,datosTopico);
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) throws NoExiste{
        service.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
