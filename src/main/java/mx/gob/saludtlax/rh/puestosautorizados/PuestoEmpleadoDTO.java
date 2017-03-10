/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 * @since 13/09/2016 12:57:23
 * 
 */
public class PuestoEmpleadoDTO {
	private Integer idPuesto;
	private String nombre;
	private Integer numeroEmpleado;
	private String proyecto;
	private String dependencia;
	private String unidadResponsable;
	private String fuente;
	private String subfuente;
	private String tipoContratacion;
	private String tipoNombramiento;
	private String codigoPuesto;
	private String puesto;
	private String numeroVacante;
	private Integer idAdscripcion;
	private Integer idSubadscripcion;
	private Integer idServicio;
	private Integer idClue;
	private Integer idCentroResponsabilidad;
	private Integer idPrograma;
	private String adscripcion;
	private String subadscripcion;
	private String servicio;
	private String funcion;
	private Integer idFuncion;
	private BigDecimal sueldoAutorizado;
	private Boolean seguroPopular;

	public Boolean getSeguroPopular() {
		return seguroPopular;
	}

	public void setSeguroPopular(Boolean seguroPopular) {
		this.seguroPopular = seguroPopular;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Integer getIdCentroResponsabilidad() {
		return idCentroResponsabilidad;
	}

	public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
		this.idCentroResponsabilidad = idCentroResponsabilidad;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getSubfuente() {
		return subfuente;
	}

	public void setSubfuente(String subfuente) {
		this.subfuente = subfuente;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public String getSubadscripcion() {
		return subadscripcion;
	}

	public void setSubadscripcion(String subadscripcion) {
		this.subadscripcion = subadscripcion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public Integer getIdClue() {
		return idClue;
	}

	public void setIdClue(Integer idClue) {
		this.idClue = idClue;
	}

	public Integer getIdSubadscripcion() {
		return idSubadscripcion;
	}

	public void setIdSubadscripcion(Integer idSubadscripcion) {
		this.idSubadscripcion = idSubadscripcion;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public BigDecimal getSueldoAutorizado() {
		return sueldoAutorizado;
	}

	public void setSueldoAutorizado(BigDecimal sueldoAutorizado) {
		this.sueldoAutorizado = sueldoAutorizado;
	}

	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(Integer numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getUnidadResponsable() {
		return unidadResponsable;
	}

	public void setUnidadResponsable(String unidadResponsable) {
		this.unidadResponsable = unidadResponsable;
	}

	public String getTipoContratacion() {
		return tipoContratacion;
	}

	public void setTipoContratacion(String tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public String getTipoNombramiento() {
		return tipoNombramiento;
	}

	public void setTipoNombramiento(String tipoNombramiento) {
		this.tipoNombramiento = tipoNombramiento;
	}

	public String getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(String codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNumeroVacante() {
		return numeroVacante;
	}

	public void setNumeroVacante(String numeroVacante) {
		this.numeroVacante = numeroVacante;
	}

}
