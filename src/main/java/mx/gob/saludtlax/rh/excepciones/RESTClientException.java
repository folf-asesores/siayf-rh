package mx.gob.saludtlax.rh.excepciones;

import java.io.Serializable;

public class RESTClientException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5879738141352086997L;

	public RESTClientException(String mensaje) {
		super(mensaje);
	}

}
