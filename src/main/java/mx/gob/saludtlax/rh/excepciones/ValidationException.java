/*
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
 * Esta clase define las excepciones que estan relacionadas con las excepciones
 * de validación en el back-end.
 *
 * @author Leila Schiaffini Ehuan
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 *
 * @since 18/03/2016-13:32:40
 * @deprecated Remplazada por {@link ValidacionException}
 */
@Deprecated
@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException {

    
    private static final long serialVersionUID = 4927904283939010719L;

    private CodigoError codigoError;
    private final Map<String, Object> propiedades = new TreeMap<>();

    /**
     *
     * @param message
     * @deprecated Remplazado por {@link ValidationException#ValidationException(java.lang.String, mx.gob.saludtlax.rh.excepciones.CodigoError) }
     */
    @Deprecated
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Inicializa la excepción.
     *
     * @param message
     *            un mensaje breve para describir el error.
     * @param codigoError
     *            el código de error que identifica la excepción.
     */
    public ValidationException(String message, ValidacionCodigoError codigoError) {
        super(message);
        this.codigoError = codigoError;
    }

    /**
     * Inicializa la excepción.
     *
     * @param message
     *            un mensaje breve para describir el error.b
     * @param cause
     *            la razón que ocaciono la excepción.
     * @param codigoError
     *            el código de error que identifica la excepción.
     */
    public ValidationException(String message, Throwable cause, ValidacionCodigoError codigoError) {
        super(message, cause);
        this.codigoError = codigoError;
    }

    /**
     * Inicializa la excepción.
     *
     * @param cause
     *            la razón que ocaciono la excepción.
     * @param codigoError
     *            el código de error que identifica la excepción.
     */
    public ValidationException(Throwable cause, ValidacionCodigoError codigoError) {
        super(cause);
        this.codigoError = codigoError;
    }

    /**
     * Este método devuelve el código de error con un enumerable.
     *
     * @return el código de error que identifica la excepción.
     */
    public CodigoError getCodigoError() {
        return codigoError;
    }

    /**
     * Este método permite establecer el código de error.
     *
     * @param codigoError
     *            el código de error que identifica la excepción.
     */
    public void setCodigoError(CodigoError codigoError) {
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
     * @param <T>
     *            el tipo que será retornado.
     * @param clave
     *            el nombre clave de la propiedad.
     * @return
     */
    public <T> T get(String clave) {
        return (T) propiedades.get(clave);
    }

    /**
     * este método permite agregar propiedades adicionales a la excepción.
     *
     * @param clave
     *            la clave con la que se identificara la propiedad.
     * @param valor
     *            el valor que tendrá dicha propiedad.
     * @return un instancia de la excepción.
     */
    public ValidationException set(String clave, Object valor) {
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