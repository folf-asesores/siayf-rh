
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessException extends RuntimeException
        implements Serializable {

    private static final long serialVersionUID = -3882066491372656965L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {

    }

}
