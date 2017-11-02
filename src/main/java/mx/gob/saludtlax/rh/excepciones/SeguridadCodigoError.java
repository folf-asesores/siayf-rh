/*
 * 
 * SeguridadCodigoError.java
 * Creado el Oct 10, 2016 5:31:54 PM
 * 
 */
package mx.gob.saludtlax.rh.excepciones;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum SeguridadCodigoError implements CodigoError {
    /** Cuando se intenta iniciar sesión y el nombre de usuario no existe. */
    NOMBRE_DE_USUARIO_NO_EXISTE(4000),
    /** Cuando se ha intentado ingresar una contraseña invalida. */
    CONTRASENYA_INVALIDA(4001),
    /** Cuando se intenta iniciar sesión con una cuenta de usuario inactiva. */
    USUARIO_INACTIVO(4002),
    /** Cuando se intenta iniciar sesión sin ingresar el nombre de usuario. */
    NOMBRE_DE_USUARIO_REQUERIDO(4003),
    /** Cuando se intenda iniciar sesión sin ingresar la contraseña del usuario. */
    CONTRASENYA_REQUERIDA(4004);

    SeguridadCodigoError (int numero){
        this.numero = numero;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    private final int numero;
}
