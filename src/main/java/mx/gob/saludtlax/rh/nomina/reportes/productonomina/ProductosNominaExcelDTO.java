/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.productonomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Eduardo Mex
 *
 */
public class ProductosNominaExcelDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410052793284349728L;

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
		return "ProductosNominaExcelDTO [rfc=" + rfc + ", nombreEmpleado=" + nombreEmpleado + ", fechaIngreso="
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

	/************ Getters and Setters **************/

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

		BigDecimal percepciones = BigDecimal.ZERO;
		BigDecimal deducciones = BigDecimal.ZERO;

		percepciones = percepciones.add(this.honorariosAsimilables == null ? 	BigDecimal.ZERO : this.honorariosAsimilables);
		percepciones = percepciones.add(this.suplencias == null ? 				BigDecimal.ZERO : this.suplencias);
		percepciones = percepciones.add(this.diasEconomicos == null ? 			BigDecimal.ZERO : this.diasEconomicos);
		percepciones = percepciones.add(this.percepcionComplementaria == null ? BigDecimal.ZERO : this.percepcionComplementaria);
		percepciones = percepciones.add(this.bono == null ? 					BigDecimal.ZERO : this.bono);
		percepciones = percepciones.add(this.aguinaldo == null ? 				BigDecimal.ZERO : this.aguinaldo);
		percepciones = percepciones.add(this.subsidio == null ? 				BigDecimal.ZERO : this.subsidio);
		percepciones = percepciones.add(this.primaVacacional == null ? 			BigDecimal.ZERO : this.primaVacacional);
		percepciones = percepciones.add(this.bonificacionFalta == null ?		BigDecimal.ZERO : this.bonificacionFalta);
		percepciones = percepciones.add(this.retroactivo == null ? 				BigDecimal.ZERO : this.retroactivo);
		percepciones = percepciones.add(this.otros == null ? 					BigDecimal.ZERO : this.otros);
		deducciones  = deducciones.add(this.faltasRetardos == null ? 			BigDecimal.ZERO : this.faltasRetardos);
		deducciones  = deducciones.add(this.isr == null ? 						BigDecimal.ZERO : this.isr);
		deducciones  = deducciones.add(this.responsabilidades == null ? 		BigDecimal.ZERO : this.responsabilidades);
		deducciones  = deducciones.add(this.prestamo == null ? 					BigDecimal.ZERO : this.prestamo);
		deducciones  = deducciones.add(this.juicioMercantil == null ? 			BigDecimal.ZERO : this.juicioMercantil);
		deducciones  = deducciones.add(this.cuotaSindical == null ? 			BigDecimal.ZERO : this.cuotaSindical);
		deducciones  = deducciones.add(this.pensionAlimenticia == null ? 		BigDecimal.ZERO : this.pensionAlimenticia);
		return total = percepciones.subtract(deducciones);
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
