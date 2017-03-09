/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class HistorialPagoDetalleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2133672734636138987L;
	
	private String rfc;
	private String nombreEmpleado;
	private Date fechaIngreso;
	private String centroResponsabilidad;
	private String conceptoCentroResponsabilidad;
	private String funcion;
	private String programa;
	private BigDecimal honorariosAsimilables;
	private BigDecimal suplencias;
	private BigDecimal diasEconomicos;
	private BigDecimal percepcionComplementaria;
	private BigDecimal bono;
	private BigDecimal aguinaldo;
	private BigDecimal subsidio;
	private BigDecimal primaVacacional;
	private BigDecimal bonificacionFalta;
	private BigDecimal retroactivo;
	private BigDecimal otros;
	private BigDecimal faltasRetardos;
	private BigDecimal isr;
	private BigDecimal responsabilidades;
	private BigDecimal prestamo;
	private BigDecimal juicioMercantil;
	private BigDecimal cuotaSindical;
	private BigDecimal pensionAlimenticia;

	private BigDecimal total;

	@Override
	public String toString() {
		return "HistorialPagoDetalleDTO [rfc=" + rfc + ", nombreEmpleado=" + nombreEmpleado + ", fechaIngreso="
				+ fechaIngreso + ", centroResponsabilidad=" + centroResponsabilidad + ", conceptoCentroResponsabilidad="
				+ conceptoCentroResponsabilidad + ", funcion=" + funcion + ", programa=" + programa
				+ ", honorariosAsimilables=" + honorariosAsimilables + ", suplencias=" + suplencias
				+ ", diasEconomicos=" + diasEconomicos + ", percepcionComplementaria=" + percepcionComplementaria
				+ ", bono=" + bono + ", aguinaldo=" + aguinaldo + ", subsidio=" + subsidio + ", primaVacacional="
				+ primaVacacional + ", bonificacionFalta=" + bonificacionFalta + ", retroactivo=" + retroactivo
				+ ", otros=" + otros + ", faltasRetardos=" + faltasRetardos + ", isr=" + isr + ", responsabilidades="
				+ responsabilidades + ", prestamo=" + prestamo + ", juicioMercantil=" + juicioMercantil
				+ ", cuotaSindical=" + cuotaSindical + ", pensionAlimenticia=" + pensionAlimenticia + ", total=" + total
				+ "]";
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCentroResponsabilidad() {
		return centroResponsabilidad;
	}

	public void setCentroResponsabilidad(String centroResponsabilidad) {
		this.centroResponsabilidad = centroResponsabilidad;
	}

	public String getConceptoCentroResponsabilidad() {
		return conceptoCentroResponsabilidad;
	}

	public void setConceptoCentroResponsabilidad(String conceptoCentroResponsabilidad) {
		this.conceptoCentroResponsabilidad = conceptoCentroResponsabilidad;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public BigDecimal getHonorariosAsimilables() {
		return honorariosAsimilables;
	}

	public void setHonorariosAsimilables(BigDecimal honorariosAsimilables) {
		this.honorariosAsimilables = honorariosAsimilables;
	}

	public BigDecimal getSuplencias() {
		return suplencias;
	}

	public void setSuplencias(BigDecimal suplencias) {
		this.suplencias = suplencias;
	}

	public BigDecimal getDiasEconomicos() {
		return diasEconomicos;
	}

	public void setDiasEconomicos(BigDecimal diasEconomicos) {
		this.diasEconomicos = diasEconomicos;
	}

	public BigDecimal getPercepcionComplementaria() {
		return percepcionComplementaria;
	}

	public void setPercepcionComplementaria(BigDecimal percepcionComplementaria) {
		this.percepcionComplementaria = percepcionComplementaria;
	}

	public BigDecimal getBono() {
		return bono;
	}

	public void setBono(BigDecimal bono) {
		this.bono = bono;
	}

	public BigDecimal getAguinaldo() {
		return aguinaldo;
	}

	public void setAguinaldo(BigDecimal aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	public BigDecimal getSubsidio() {
		return subsidio;
	}

	public void setSubsidio(BigDecimal subsidio) {
		this.subsidio = subsidio;
	}

	public BigDecimal getPrimaVacacional() {
		return primaVacacional;
	}

	public void setPrimaVacacional(BigDecimal primaVacacional) {
		this.primaVacacional = primaVacacional;
	}

	public BigDecimal getBonificacionFalta() {
		return bonificacionFalta;
	}

	public void setBonificacionFalta(BigDecimal bonificacionFalta) {
		this.bonificacionFalta = bonificacionFalta;
	}

	public BigDecimal getRetroactivo() {
		return retroactivo;
	}

	public void setRetroactivo(BigDecimal retroactivo) {
		this.retroactivo = retroactivo;
	}

	public BigDecimal getOtros() {
		return otros;
	}

	public void setOtros(BigDecimal otros) {
		this.otros = otros;
	}

	public BigDecimal getFaltasRetardos() {
		return faltasRetardos;
	}

	public void setFaltasRetardos(BigDecimal faltasRetardos) {
		this.faltasRetardos = faltasRetardos;
	}

	public BigDecimal getIsr() {
		return isr;
	}

	public void setIsr(BigDecimal isr) {
		this.isr = isr;
	}

	public BigDecimal getResponsabilidades() {
		return responsabilidades;
	}

	public void setResponsabilidades(BigDecimal responsabilidades) {
		this.responsabilidades = responsabilidades;
	}

	public BigDecimal getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(BigDecimal prestamo) {
		this.prestamo = prestamo;
	}

	public BigDecimal getJuicioMercantil() {
		return juicioMercantil;
	}

	public void setJuicioMercantil(BigDecimal juicioMercantil) {
		this.juicioMercantil = juicioMercantil;
	}

	public BigDecimal getCuotaSindical() {
		return cuotaSindical;
	}

	public void setCuotaSindical(BigDecimal cuotaSindical) {
		this.cuotaSindical = cuotaSindical;
	}

	public BigDecimal getPensionAlimenticia() {
		return pensionAlimenticia;
	}

	public void setPensionAlimenticia(BigDecimal pensionAlimenticia) {
		this.pensionAlimenticia = pensionAlimenticia;
	}

	public BigDecimal getTotal() {
		
		this.total = BigDecimal.ZERO;

		this.total = total.add(this.honorariosAsimilables == null ? BigDecimal.ZERO : this.honorariosAsimilables);
		this.total = total.add(this.suplencias == null ? BigDecimal.ZERO : this.suplencias);
		this.total = total.add(this.diasEconomicos == null ? BigDecimal.ZERO : this.diasEconomicos);
		this.total = total.add(this.percepcionComplementaria == null ? BigDecimal.ZERO : this.percepcionComplementaria);
		this.total = total.add(this.bono == null ? BigDecimal.ZERO : this.bono);
		this.total = total.add(this.aguinaldo == null ? BigDecimal.ZERO : this.aguinaldo);
		this.total = total.add(this.subsidio == null ? BigDecimal.ZERO : this.subsidio);
		this.total = total.add(this.primaVacacional == null ? BigDecimal.ZERO : this.primaVacacional);
		this.total = total.add(this.bonificacionFalta == null ? BigDecimal.ZERO : this.bonificacionFalta);
		this.total = total.add(this.retroactivo == null ? BigDecimal.ZERO : this.retroactivo);
		this.total = total.add(this.otros == null ? BigDecimal.ZERO : this.otros);
		this.total = total.add(this.faltasRetardos == null ? BigDecimal.ZERO : this.faltasRetardos);
		this.total = total.add(this.isr == null ? BigDecimal.ZERO : this.isr);
		this.total = total.add(this.responsabilidades == null ? BigDecimal.ZERO : this.responsabilidades);
		this.total = total.add(this.prestamo == null ? BigDecimal.ZERO : this.prestamo);
		this.total = total.add(this.juicioMercantil == null ? BigDecimal.ZERO : this.juicioMercantil);
		this.total = total.add(this.cuotaSindical == null ? BigDecimal.ZERO : this.cuotaSindical);
		this.total = total.add(this.pensionAlimenticia == null ? BigDecimal.ZERO : this.pensionAlimenticia);
		
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}
