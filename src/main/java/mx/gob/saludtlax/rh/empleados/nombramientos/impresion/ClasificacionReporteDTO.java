/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class ClasificacionReporteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9067602037878921126L;
	private String clasificacionReporte;
	private String textoUno;
	private String textoDos;
	private String sustituye;
	private String nombreSecretario;
	private String horarioTrabajo;
	private Date fechaPosicionUno;
	

	public String getClasificacionReporte() {
		return clasificacionReporte;
	}

	public void setClasificacionReporte(String clasificacionReporte) {
		this.clasificacionReporte = clasificacionReporte;
	}

	public String getTextoUno() {
		return textoUno;
	}

	public void setTextoUno(String textoUno) {
		this.textoUno = textoUno;
	}

	public String getTextoDos() {
		return textoDos;
	}

	public void setTextoDos(String textoDos) {
		this.textoDos = textoDos;
	}

	public String getSustituye() {
		return sustituye;
	}

	public void setSustituye(String sustituye) {
		this.sustituye = sustituye;
	}

	public String getNombreSecretario() {
		return nombreSecretario;
	}

	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}

	public String getHorarioTrabajo() {
		return horarioTrabajo;
	}

	public void setHorarioTrabajo(String horarioTrabajo) {
		this.horarioTrabajo = horarioTrabajo;
	}

	public Date getFechaPosicionUno() {
		return fechaPosicionUno;
	}

	public void setFechaPosicionUno(Date fechaPosicionUno) {
		this.fechaPosicionUno = fechaPosicionUno;
	}

}
