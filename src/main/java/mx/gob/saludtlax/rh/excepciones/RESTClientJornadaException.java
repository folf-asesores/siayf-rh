package mx.gob.saludtlax.rh.excepciones;

import java.io.Serializable;

public class RESTClientJornadaException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1332082050166122449L;

	public RESTClientJornadaException(String mensaje) {
		super(mensaje);
	}

}
