package edu.alura.chalenger_foro.models.usuario;


public enum Perfil {
    ADMINISTRADOR("administrador"),
    MODERADOR("moderador"),
    ESTUDIANTE("estudiante"),
    INSTRUCTOR("instructor");

    private String nombre;
    Perfil(String nombre) {
        this.nombre = nombre;
    }
    public Perfil fromString(String nombre){
        for (Perfil p : Perfil.values()){
            if (p.nombre.equalsIgnoreCase(nombre)){
                return p;
            }
        }
        return null;
    }
}
