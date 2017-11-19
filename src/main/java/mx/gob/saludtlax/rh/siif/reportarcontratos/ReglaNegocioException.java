
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;

import javax.ejb.ApplicationException;

/**
 *
 * @deprecated Remplazada por {@link mx.gob.saludtlax.rh.excepciones.ReglaNegocioException}
 */
@Deprecated
@ApplicationException(rollback = true)
public class ReglaNegocioException extends RuntimeException
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7056273336981219259L;

    public ReglaNegocioException(String message) {
        super(message);
    }

    public ReglaNegocioException() {

    }

}
