
package mx.gob.saludtlax.rh.excepciones;

import java.io.Serializable;

public class RESTClientReglaAsistenciaJornadaException extends Exception
        implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1050722462121517935L;

    public RESTClientReglaAsistenciaJornadaException(String mensaje) {

        super(mensaje);

    }

}
