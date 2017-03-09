/*
 *  CodigoError.java
 *  Creado el Jun 17, 2016 11:37:23 AM
 * 
 */
package mx.gob.saludtlax.rh.excepciones;

/**
 * Esta intefaz describe los métodos necesarioa para el código de error.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public interface CodigoError {

    /**
     * Este método permite conocer el número de error.
     * 
     * @return el número de error.
     */
    int getNumero();
}
