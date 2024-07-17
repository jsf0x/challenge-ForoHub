package edu.alura.chalenger_foro.DTO;

import java.time.LocalDateTime;
import java.util.List;

import edu.alura.chalenger_foro.models.topico.Topico;

public record DatosDTOTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        DatosDTOUsuario autor,
        DatosDTOCurso curso,
        List<DatosDTORespuesta> respuestas) {
    public DatosDTOTopico(Topico t) {
        this(t.getTitulo(), t.getMensaje(), t.getFecha(), new DatosDTOUsuario(t.getAutor()),
                new DatosDTOCurso(t.getCurso()),
                t.getRespuestas().stream().map(r -> new DatosDTORespuesta(r)).toList());
    }
}
