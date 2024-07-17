package edu.alura.chalenger_foro.DTO;

import edu.alura.chalenger_foro.models.usuario.Usuario;

public record DatosDTOUsuario(String nombre,
        String email) {

    public DatosDTOUsuario(Usuario autor) {
        this(autor.getNombre(), autor.getEmail());
    }

}
