package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.Serializable;

public class ReglaIncidenciaViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8957396098232831147L;

	private Integer idReglaIncidencia;
	private Integer idTipoContratacion;
	private Integer idIncidencia;
	private Integer maximo;
	private Integer idTipoAcmulado;
	private String tipoCalculo;
	private String tipoAcumuladoDescripcion;
	private String tipoContratacionDescripcion;
	private String tipoFecha;
	private String incidenciaDescripcion;
	private Integer mesesAntiguedadInicio;
	private Integer mesesAntiguedadFin;

	public String getIncidenciaDescripcion() {
		return incidenciaDescripcion;
	}

	public void setIncidenciaDescripcion(String incidenciaDescripcion) {
		this.incidenciaDescripcion = incidenciaDescripcion;
	}

	public Integer getIdReglaIncidencia() {
		return idReglaIncidencia;
	}

	public void setIdReglaIncidencia(Integer idReglaIncidencia) {
		this.idReglaIncidencia = idReglaIncidencia;
	}

	public Integer getIdTipoContratacion() {
		return idTipoContratacion;
	}

	public void setIdTipoContratacion(Integer idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getIdTipoAcmulado() {
		return idTipoAcmulado;
	}

	public void setIdTipoAcmulado(Integer idTipoAcmulado) {
		this.idTipoAcmulado = idTipoAcmulado;
	}

	public String getTipoAcumuladoDescripcion() {
		return tipoAcumuladoDescripcion;
	}

	public void setTipoAcumuladoDescripcion(String tipoAcumuladoDescripcion) {
		this.tipoAcumuladoDescripcion = tipoAcumuladoDescripcion;
	}

	public String getTipoContratacionDescripcion() {
		return tipoContratacionDescripcion;
	}

	public void setTipoContratacionDescripcion(String tipoContratacionDescripcion) {
		this.tipoContratacionDescripcion = tipoContratacionDescripcion;
	}

	public String getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public String getTipoFecha() {
		return tipoFecha;
	}

	public void setTipoFecha(String tipoFecha) {
		this.tipoFecha = tipoFecha;
	}

	public Integer getMesesAntiguedadInicio() {
		return mesesAntiguedadInicio;
	}

	public void setMesesAntiguedadInicio(Integer mesesAntiguedadInicio) {
		this.mesesAntiguedadInicio = mesesAntiguedadInicio;
	}

	public Integer getMesesAntiguedadFin() {
		return mesesAntiguedadFin;
	}

	public void setMesesAntiguedadFin(Integer mesesAntiguedadFin) {
		this.mesesAntiguedadFin = mesesAntiguedadFin;
	}

}
