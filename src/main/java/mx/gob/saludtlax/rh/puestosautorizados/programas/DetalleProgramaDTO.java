/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados.programas;

import java.math.BigDecimal;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:18:49
 */
public class DetalleProgramaDTO {
	private Integer idPrograma;
	private Integer idDetalle;
	private String clave;
	private String descripcion;
	private BigDecimal costoUnitario;
	private Integer numeroPersonas;
	private BigDecimal totalGlobal;
	private Integer mesesContratacion;
	private Integer idUsuario;
	private String idTipoDetalle;
	private Boolean esHonorario;

	public Boolean getEsHonorario() {
		return esHonorario;
	}

	public void setEsHonorario(Boolean esHonorario) {
		this.esHonorario = esHonorario;
	}

	public String getIdTipoDetalle() {
		return idTipoDetalle;
	}

	public void setIdTipoDetalle(String idTipoDetalle) {
		this.idTipoDetalle = idTipoDetalle;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Integer getNumeroPersonas() {
		return numeroPersonas;
	}

	public void setNumeroPersonas(Integer numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}

	public BigDecimal getTotalGlobal() {
		return totalGlobal;
	}

	public void setTotalGlobal(BigDecimal totalGlobal) {
		this.totalGlobal = totalGlobal;
	}

	public Integer getMesesContratacion() {
		return mesesContratacion;
	}

	public void setMesesContratacion(Integer mesesContratacion) {
		this.mesesContratacion = mesesContratacion;
	}

}
