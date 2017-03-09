package mx.gob.saludtlax.rh.reporteslaborales.residencia;

import java.io.Serializable;

public class ResidenciaMedicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String asunto;
	private String presenteNombre;
	private String presenteClaveUno;
	private String presenteClaveDos;
	private String fecha;
	private String encargado;
	private String clavePresupuestal;
	private String denominacion;
	private String adscripcion;
	private String vigencia;
	private String solicitud;
	private String posicionUno;
	private String posicionDos;
	private String jefe;
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getPresenteNombre() {
		return presenteNombre;
	}
	public void setPresenteNombre(String presenteNombre) {
		this.presenteNombre = presenteNombre;
	}
	public String getPresenteClaveUno() {
		return presenteClaveUno;
	}
	public void setPresenteClaveUno(String presenteClaveUno) {
		this.presenteClaveUno = presenteClaveUno;
	}
	public String getPresenteClaveDos() {
		return presenteClaveDos;
	}
	public void setPresenteClaveDos(String presenteClaveDos) {
		this.presenteClaveDos = presenteClaveDos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	public String getClavePresupuestal() {
		return clavePresupuestal;
	}
	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}
	public String getPosicionUno() {
		return posicionUno;
	}
	public void setPosicionUno(String posicionUno) {
		this.posicionUno = posicionUno;
	}
	public String getPosicionDos() {
		return posicionDos;
	}
	public void setPosicionDos(String posicionDos) {
		this.posicionDos = posicionDos;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	
	

}
