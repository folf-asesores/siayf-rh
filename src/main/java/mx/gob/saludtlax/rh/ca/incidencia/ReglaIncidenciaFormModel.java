package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.Serializable;

public class ReglaIncidenciaFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -582126618151897581L;

	private Integer idTipoContratacion;
	private Integer idIncidencia;
	private Integer maximo;
	private Integer tipoCalculo;
	private Integer tipoAcumulado;
	private Integer mesesAntiguedadInicio;
	private Integer mesesAntiguedadFin;
	private Integer tipoFecha;

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

	public Integer getTipoCalculo() {
		return tipoCalculo;
	}

	public void setTipoCalculo(Integer tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public Integer getTipoAcumulado() {
		return tipoAcumulado;
	}

	public void setTipoAcumulado(Integer tipoAcumulado) {
		this.tipoAcumulado = tipoAcumulado;
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

	public Integer getTipoFecha() {
		return tipoFecha;
	}

	public void setTipoFecha(Integer tipoFecha) {
		this.tipoFecha = tipoFecha;
	}

}
