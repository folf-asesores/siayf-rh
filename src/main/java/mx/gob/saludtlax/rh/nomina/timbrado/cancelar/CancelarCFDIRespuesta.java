package mx.gob.saludtlax.rh.nomina.timbrado.cancelar;

import java.io.Serializable;

public class CancelarCFDIRespuesta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1367472235466350864L;
	private String codigo;
	private String mensaje;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
