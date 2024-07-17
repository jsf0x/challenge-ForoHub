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

import edu.alura.chalenger_foro.DTO.DatosDTORespuesta;
import edu.alura.chalenger_foro.infra.mis_execpciones.NoExiste;
import edu.alura.chalenger_foro.models.respuesta.DatosActualizarRespuesta;
import edu.alura.chalenger_foro.models.respuesta.DatosRespuesta;
import edu.alura.chalenger_foro.service.ServiceRespuesta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/respuesta")
@SecurityRequirement(name = "bearer-key")
public class ControllerRespuesta {
    @Autowired
    private ServiceRespuesta service;

    @GetMapping()
    public ResponseEntity<Page<DatosDTORespuesta>> showAllRespuestas(
            @PageableDefault(size = 3, sort = "fecha") Pageable page) {
        var Respuestas = service.getAllRespuestas(page);
        return ResponseEntity.ok(Respuestas);
    }

    @PostMapping()
    public ResponseEntity<DatosDTORespuesta> registrarRespuesta(@RequestBody @Valid DatosRespuesta datoRespuesta,
            UriComponentsBuilder uriComponentsBuilder) throws NoExiste {
        var respuesta = service.registrarRespuesta(datoRespuesta);
        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosDTORespuesta(respuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDTORespuesta> showRespuesta(@PathVariable Long id) throws NoExiste {
        var Respuesta = service.getRespuestaById(id);
        return ResponseEntity.ok(Respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDTORespuesta> updateRespuesta(@PathVariable Long id,
            @RequestBody DatosActualizarRespuesta datosRespuesta) throws NoExiste {
        var Respuesta = service.updateRespuesta(id, datosRespuesta);
        return ResponseEntity.ok(Respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarRespuesta(@PathVariable Long id) throws NoExiste {
        service.deletarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
