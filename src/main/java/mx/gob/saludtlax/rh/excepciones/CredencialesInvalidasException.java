
package mx.gob.saludtlax.rh.excepciones;

import java.io.Serializable;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CredencialesInvalidasException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8412926846298278264L;

    public CredencialesInvalidasException() {

    }

    public CredencialesInvalidasException(String arg0) {
        super(arg0);
    }

    public CredencialesInvalidasException(Throwable arg0) {
        super(arg0);
    }

    public CredencialesInvalidasException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CredencialesInvalidasException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}