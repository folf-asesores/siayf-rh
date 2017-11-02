/*
 * SistemaCodigoError.java
 * Creado el Jun 24, 2016 2:22:12 PM
 * 
 */
package mx.gob.saludtlax.rh.excepciones;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum SistemaCodigoError implements CodigoError {

    
    /** Cuando un recurso no está presente. */
    RECURSO_NO_ENCONTRADO(1000),
    /** Cuando un recurso está bloquedo porque esta siendo usado. (Bloque de concurencia) */
    RECURSO_NO_DISPONIBLE(1001),
    /** Cuando una consulta no regresa resultados. */
    BUSQUEDA_SIN_RESULTADOS(1002),
    /** Cuando no se puede eliminar una entidad u objeto porque no exite.  */
    IMPOSIBLE_ELIMINAR_OBJETO_INEXISTENTE(1003),
    /** Cuando se intenta iniciar sesión sin ingresar el nombre de usuario. */
    NOMBRE_DE_USUARIO_REQUERIDO(1004),
    /** Cuando se intenta iniciar sesión y nombre de usuario no exite. */
    NOMBRE_DE_USUARIO_NO_EXISTE(1005),
    /** Cuando se intenda iniciar sesión sin ingresar contraseña de usuario. */
    CONTRASENYA_REQUERIDA(1006),
    /** Cuando se persistir una entidad y no es posible por alguna excepción inesperada. */
    IMPOSIBLE_PERSISTIR_OBJETO(1007),
    /** Cuando se actualizar una entidad y no es posible por alguna excepción inesperada. */
    IMPOSIBLE_ACTUALIZAR_OBJETO(1008), 
    /** Cuando se trata de eliminar una entidad la cual por reglas del sistema no se deben eliminar. */
    ELIMINACION_NO_PERMITIDA (1009), 
    /** Cuando se trata de leer o escribir. */
    ERROR_LECTURA_ESCRITURA(1010),
    //Cuando se encuentra mas de un resultado de los que se esperan.
    ERROR_MULTIPLES_RESULTADOS(1011);
    
    SistemaCodigoError (int numero){
        this.numero = numero;
    }    
    
    @Override
    public int getNumero() {
        return numero;
    }
    
    private final int numero;
    
}
