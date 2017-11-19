package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;

import javax.ejb.ApplicationException;

/**
 * 
 * @deprecated Remplazada por {@link mx.gob.saludtlax.rh.excepciones.ReglaNegocioException}
 */
@ApplicationException(rollback=true)
public class ReglaNegocioException extends RuntimeException implements Serializable{

	
	
	public ReglaNegocioException(String message){
		super(message);
	}
	
	public ReglaNegocioException(){
		
	}

}
