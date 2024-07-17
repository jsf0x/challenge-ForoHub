package edu.alura.chalenger_foro.infra.mis_execpciones;

public class DatosInvalidos extends Throwable{
    public DatosInvalidos(){
        super();
    }
    public DatosInvalidos(String mensaje){
        super(mensaje);
    }
    public DatosInvalidos(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
