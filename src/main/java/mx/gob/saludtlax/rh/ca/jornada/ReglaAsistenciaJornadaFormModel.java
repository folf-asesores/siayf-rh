package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;
/**
 * 
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class ReglaAsistenciaJornadaFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2184007806412746590L;

	private Integer idReglaAsistenciaJornada;
	
	private Integer idJornada;

	private Integer idIncidencia;

	private Integer minutoInicio;

	private Integer minutoFinal;

	private Integer requiereTramite;

	public Integer getIdReglaAsistenciaJornada() {
		return idReglaAsistenciaJornada;
	}

	public void setIdReglaAsistenciaJornada(Integer idReglaAsistenciaJornada) {
		this.idReglaAsistenciaJornada = idReglaAsistenciaJornada;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public Integer getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(Integer minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Integer getMinutoFinal() {
		return minutoFinal;
	}

	public void setMinutoFinal(Integer minutoFinal) {
		this.minutoFinal = minutoFinal;
	}

	public Integer getRequiereTramite() {
		return requiereTramite;
	}

	public void setRequiereTramite(Integer requiereTramite) {
		this.requiereTramite = requiereTramite;
	}
	
	
	

}
