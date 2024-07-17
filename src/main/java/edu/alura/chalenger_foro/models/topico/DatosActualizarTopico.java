package edu.alura.chalenger_foro.models.topico;

import edu.alura.chalenger_foro.models.curso.DatosActualizarCurso;

public record DatosActualizarTopico(
    String titulo,
    String mensaje,
    DatosActualizarCurso curso
) {

}
