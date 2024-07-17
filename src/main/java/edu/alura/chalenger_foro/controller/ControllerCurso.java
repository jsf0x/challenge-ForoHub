package edu.alura.chalenger_foro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import edu.alura.chalenger_foro.DTO.DatosDTOCurso;
import edu.alura.chalenger_foro.infra.mis_execpciones.NoExiste;
import edu.alura.chalenger_foro.models.curso.DatosActualizarCurso;
import edu.alura.chalenger_foro.models.curso.DatosCurso;
import edu.alura.chalenger_foro.service.ServiceCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/curso")
@SecurityRequirement(name = "bearer-key")
public class ControllerCurso {
    @Autowired
    private ServiceCurso service;
    @GetMapping()
    public ResponseEntity<Page<DatosDTOCurso>> showAllCursos(
            @PageableDefault(size = 3, sort = "titulo") Pageable page) {
        var Cursos = service.getAllCursos(page);
        return ResponseEntity.ok(Cursos);
    }

    @PostMapping()
    public ResponseEntity<DatosDTOCurso> registrarCurso(@RequestBody @Valid DatosCurso datoCurso,
            UriComponentsBuilder uriComponentsBuilder) {
        var Curso = service.registrarCurso(datoCurso);
        URI url = uriComponentsBuilder.path("/curso/{id}").buildAndExpand(Curso.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDTOCurso(Curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDTOCurso> showCurso(@PathVariable Long id) throws NoExiste {
        var Curso = service.getCursoById(id);
        return ResponseEntity.ok(Curso);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDTOCurso> updateCurso(@PathVariable Long id,
            @RequestBody DatosActualizarCurso datosCurso) throws NoExiste {
        var Curso = service.updateCurso(id, datosCurso);
        return ResponseEntity.ok(Curso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) throws NoExiste {
        service.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
