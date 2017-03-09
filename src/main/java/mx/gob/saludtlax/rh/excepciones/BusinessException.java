package mx.gob.saludtlax.rh.excepciones;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.ApplicationException;

/**
 * Esta clase define las excepciones que están relacionadas con las reglas de 
 * negocio.
 * 
 * @author luisAlfonso
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @deprecated Remplazada por {@link ReglaNegocioException}
 */
@ApplicationException(rollback = true)
public class BusinessException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 8853273999950326870L;

    private CodigoError codigoError;
    private final Map<String, Object> propiedades = new TreeMap<>();
        
    /**
     * 
     * @param message
     * @deprecated Remplazado por {@link BusinessException#BusinessException(java.lang.String, mx.gob.saludtlax.rh.excepciones.CodigoError) }
     */
    @Deprecated
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, CodigoError codigoError) {
        super(message);
        this.codigoError = codigoError;
    }

    public BusinessException(Throwable cause, CodigoError codigoError) {
        super(cause);
        this.codigoError = codigoError;
    }

    public BusinessException(String message, Throwable cause, CodigoError codigoError) {
        super(message, cause);
        this.codigoError = codigoError;
    }
    
    /**
     *
     * @return
     */
    public CodigoError getCodigoError() {
        return codigoError;
    }

    /**
     *
     * @param codigoError
     */
    public void setCodigoError(CodigoError codigoError) {
        this.codigoError = codigoError;
    }
    
    /**
     *
     * @return
     */
    public Map<String, Object> getPropiedades() {
        return Collections.unmodifiableMap(propiedades);
    }
    
    /**
     *
     * @param <T>
     * @param clave
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String clave) {
        return (T) propiedades.get(clave);
    }
    
    /**
     *
     * @param clave
     * @param valor
     * @return
     */
    public BusinessException set(String clave, Object valor) {
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
                s.println("\t" + codigoError + ":" + codigoError.getClass().getName() + "(" + codigoError.getNumero() + ")");
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
