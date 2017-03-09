/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.nombramientos;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class NombramientoInterinatoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7045969389723954587L;
	
	private String presenteNombre;
	private String presenteClaveUno;
	private String presenteClaveDos;
	private String textoPosicionUno;
	private Date fechaNombramiento;
	private String funcion;
	private String clavePresupuestal;
	private String propietarioPlaza;
	private String tipoRecurso;
	private String tipoNombramiento;
	private Date vigencia;
	private String posicionDos;
	private String nombreSecretario;
	private String posicionTres;
	
	public String getTextoPosicionUno() {
		return textoPosicionUno;
	}
	public void setTextoPosicionUno(String textoPosicionUno) {
		this.textoPosicionUno = textoPosicionUno;
	}
	public Date getFechaNombramiento() {
		return fechaNombramiento;
	}
	public void setFechaNombramiento(Date fechaNombramiento) {
		this.fechaNombramiento = fechaNombramiento;
	}
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getClavePresupuestal() {
		return clavePresupuestal;
	}
	public void setClavePresupuestal(String clavePresupuestal) {
		this.clavePresupuestal = clavePresupuestal;
	}
	public String getPropietarioPlaza() {
		return propietarioPlaza;
	}
	public void setPropietarioPlaza(String propietarioPlaza) {
		this.propietarioPlaza = propietarioPlaza;
	}
	public String getTipoRecurso() {
		return tipoRecurso;
	}
	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	public String getTipoNombramiento() {
		return tipoNombramiento;
	}
	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	public String getPosicionDos() {
		return posicionDos;
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
	public void setPosicionDos(String posicionDos) {
		this.posicionDos = posicionDos;
	}
	public String getNombreSecretario() {
		return nombreSecretario;
	}
	public void setNombreSecretario(String nombreSecretario) {
		this.nombreSecretario = nombreSecretario;
	}
	public String getPosicionTres() {
		return posicionTres;
	}
	public void setPosicionTres(String posicionTres) {
		this.posicionTres = posicionTres;
	}
	
	
	
	

}
