package edu.alura.chalenger_foro.DTO;

import edu.alura.chalenger_foro.models.curso.Categoria;
import edu.alura.chalenger_foro.models.curso.Curso;

public record DatosDTOCurso(String nombre,
        Categoria categoria) {

    public DatosDTOCurso(Curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }

}
