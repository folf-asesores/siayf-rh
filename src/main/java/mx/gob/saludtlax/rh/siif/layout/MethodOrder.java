/*
 * MethodOrder.java
 * Creado el 26/06/2016 07:28:10 AM
 * 
 */
package mx.gob.saludtlax.rh.siif.layout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Esta ayuda a obtener los metodos de los DTOs con un orden especifico.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodOrder {
    int value();
}
