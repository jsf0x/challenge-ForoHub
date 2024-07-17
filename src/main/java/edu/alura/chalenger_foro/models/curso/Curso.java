package edu.alura.chalenger_foro.models.curso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private boolean activo;

    public Curso(DatosCurso datosCurso){
        this.nombre = datosCurso.nombre();
        this.categoria = datosCurso.categoria();
        this.activo = true;
    }

    public void ActualizarCurso(DatosActualizarCurso curso){
        if(curso.nombre()!=null){
            this.nombre = curso.nombre();
        }
        if(curso.categoria()!=null){
            this.categoria  = curso.categoria();
        }

    }

    public void desativar(){
        this.activo = false;
    }

}
