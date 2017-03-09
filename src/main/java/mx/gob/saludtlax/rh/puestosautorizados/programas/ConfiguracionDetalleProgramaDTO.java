/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados.programas;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-22
 * 
 */
public class ConfiguracionDetalleProgramaDTO {
	private String nombrePrograma;
	private String proyecto;
	private String dependencia;
	private String unidadResponsable;
	private String fuenteFinanciamiento;
	private String subFuenteFinanciamiento;
	private String tipoRecurso;
	private String cuenta;
	private InfoDetallePrograma detallePrograma;

	public InfoDetallePrograma getDetallePrograma() {
		return detallePrograma;
	}

	public void setDetallePrograma(InfoDetallePrograma detallePrograma) {
		this.detallePrograma = detallePrograma;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
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

	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}

	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}

	public String getSubFuenteFinanciamiento() {
		return subFuenteFinanciamiento;
	}

	public void setSubFuenteFinanciamiento(String subFuenteFinanciamiento) {
		this.subFuenteFinanciamiento = subFuenteFinanciamiento;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
