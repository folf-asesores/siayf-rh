package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Vista con la informacion de la jornada.
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class JornadaFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3961438818448926609L;

	private Integer idJornada;

	private String descripcion;

	private Integer trabajaDiasNoLaborales;
	
	
	
	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getTrabajaDiasNoLaborales() {
		return trabajaDiasNoLaborales;
	}

	public void setTrabajaDiasNoLaborales(Integer trabajaDiasNoLaborales) {
		this.trabajaDiasNoLaborales = trabajaDiasNoLaborales;
	}

}
