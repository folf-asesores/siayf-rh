/**
 * 
 */
package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 24/10/2016 17:04:06
 */
public class AprobacionCandidatoView {

	private List<InfoPostulacionDTO> postulacionesDisponibles = new ArrayList<>();
	private List<InfoCandidatoDTO> candidatosPostulados = new ArrayList<>();
	private boolean mostrarCandidatosPostulados;
	private boolean mostrarPostulacionesDisponibles;
	private boolean mostrarDetalleSeleccionado;
	private String consulta;

	private InfoPostulacionDTO postulacionSeleccionada = new InfoPostulacionDTO();
	private InfoCandidatoDTO candidatoSeleccionado = new InfoCandidatoDTO();

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public InfoCandidatoDTO getCandidatoSeleccionado() {
		return candidatoSeleccionado;
	}

	public void setCandidatoSeleccionado(InfoCandidatoDTO candidatoSeleccionado) {
		this.candidatoSeleccionado = candidatoSeleccionado;
	}

	public boolean isMostrarDetalleSeleccionado() {
		return mostrarDetalleSeleccionado;
	}

	public void setMostrarDetalleSeleccionado(boolean mostrarDetalleSeleccionado) {
		this.mostrarDetalleSeleccionado = mostrarDetalleSeleccionado;
	}

	public boolean isMostrarPostulacionesDisponibles() {
		return mostrarPostulacionesDisponibles;
	}

	public void setMostrarPostulacionesDisponibles(boolean mostrarPostulacionesDisponibles) {
		this.mostrarPostulacionesDisponibles = mostrarPostulacionesDisponibles;
	}

	public InfoPostulacionDTO getPostulacionSeleccionada() {
		return postulacionSeleccionada;
	}

	public void setPostulacionSeleccionada(InfoPostulacionDTO postulacionSeleccionada) {
		this.postulacionSeleccionada = postulacionSeleccionada;
	}

	public List<InfoPostulacionDTO> getPostulacionesDisponibles() {
		return postulacionesDisponibles;
	}

	public void setPostulacionesDisponibles(List<InfoPostulacionDTO> postulacionesDisponibles) {
		this.postulacionesDisponibles = postulacionesDisponibles;
	}

	public List<InfoCandidatoDTO> getCandidatosPostulados() {
		return candidatosPostulados;
	}

	public void setCandidatosPostulados(List<InfoCandidatoDTO> candidatosPostulados) {
		this.candidatosPostulados = candidatosPostulados;
	}

	public boolean isMostrarCandidatosPostulados() {
		return mostrarCandidatosPostulados;
	}

	public void setMostrarCandidatosPostulados(boolean mostrarCandidatosPostulados) {
		this.mostrarCandidatosPostulados = mostrarCandidatosPostulados;
	}

}
