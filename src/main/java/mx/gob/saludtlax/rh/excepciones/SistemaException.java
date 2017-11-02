/*
 *  SistemaException.java
 *  Creado el Jun 17, 2016 3:06:28 PM
 * 
 */
package mx.gob.saludtlax.rh.excepciones;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.ApplicationException;

/**
 * Esta clase define la excepciones del sistema, excepciones que afectan al 
 * sistema como por ejemplo la falta de recurso la lectura o escritura de 
 * streams entre otras cosas.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@ApplicationException(rollback = true)
public class SistemaException extends RuntimeException {
    
    private static final long serialVersionUID = 6074299317281139282L;
 
    private SistemaCodigoError codigoError;
    private final Map<String, Object> propiedades = new TreeMap<>();
    
    /**
     * Inicializa la excepción.
     * 
     * @param message un mensaje breve para describir el error.
     * @param codigoError el código de error que identifica la excepción.
     */
    public SistemaException(String message, SistemaCodigoError codigoError) {
        super(message);
        this.codigoError = codigoError;
    }
    
    /**
     * Inicializa la excepción.
     * 
     * @param cause la razón que ocaciono la excepción.
     * @param codigoError el código de error que identifica la excepción.
     */
    public SistemaException(Throwable cause, SistemaCodigoError codigoError) {
        super(cause);
        this.codigoError = codigoError;
    }

    /**
     * Inicializa la excepción.
     * 
     * @param message un mensaje breve para describir el error.b
     * @param cause la razón que ocaciono la excepción.
     * @param codigoError el código de error que identifica la excepción.
     */
    public SistemaException(String message, Throwable cause, SistemaCodigoError codigoError) {
        super(message, cause);
        this.codigoError = codigoError;
    }
    
    /**
     * Este método devuelve el código de error con un enumerable.
     * 
     * @return el código de error que identifica la excepción.
     */
    public SistemaCodigoError getCodigoError() {
        return codigoError;
    }

    /**
     * Este método permite establecer el código de error.
     * 
     * @param codigoError el código de error que identifica la excepción.
     */
    public void setCodigoError(SistemaCodigoError codigoError) {
        this.codigoError = codigoError;
    }
    
    /**
     * Este método permite obtener propiedades adicionales sobre la excepción.
     * 
     * @return las propiedades de la excepciones.
     */
    public Map<String, Object> getPropiedades() {
        return Collections.unmodifiableMap(propiedades);
    }
    
    /**
     * Este método permite obtener una propiedad adicional sobre la excepción.
     * 
     * @param <T> el tipo que será retornado.
     * @param clave el nombre clave de la propiedad.
     * @return
     */
    public <T> T get(String clave) {
        return (T) propiedades.get(clave);
    }
    
    /**
     * este método permite agregar propiedades adicionales a la excepción.
     * 
     * @param clave la clave con la que se identificara la propiedad.
     * @param valor el valor que tendrá dicha propiedad.
     * @return un instancia de la excepción.
     */
    public SistemaException set(String clave, Object valor) {
        propiedades.put(clave, valor);
        return this;
    }

    @Override
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            printStackTrace(new PrintWriter(s));
        }
    }
    
    @Override
    public void printStackTrace(PrintWriter s) {
        synchronized (s) {
            s.println(this);
            s.println("\t-------------------------------");
            
            if (codigoError != null) {
                s.println("\t" + codigoError + ":" + codigoError.getNumero() + ":" + codigoError.getClass().getName());
            }
            
            for (String key : propiedades.keySet()) {
                s.println("\t" + key + "=[" + propiedades.get(key) + "]");
            }
            
            s.println("\t-------------------------------");
            StackTraceElement[] traceElements = getStackTrace();

            for (StackTraceElement traceElement : traceElements) {
                s.println("\tat " + traceElement);
            }

            Throwable ourCause = getCause();

            if (ourCause != null) {
                ourCause.printStackTrace(s);
            }
            s.flush();
        }
    }
}
