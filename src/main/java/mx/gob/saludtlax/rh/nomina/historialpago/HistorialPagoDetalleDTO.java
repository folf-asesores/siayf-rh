/*
 *
 */

package mx.gob.saludtlax.rh.nomina.historialpago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class HistorialPagoDetalleDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2133672734636138987L;

    private String nombreProducto;
    private Date inicioPeriodo;
    private Date finPeriodo;
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
        return "HistorialPagoDetalleDTO [rfc=" + rfc + ", nombreEmpleado="
                + nombreEmpleado + ", fechaIngreso=" + fechaIngreso
                + ", centroResponsabilidad=" + centroResponsabilidad
                + ", conceptoCentroResponsabilidad="
                + conceptoCentroResponsabilidad + ", funcion=" + funcion
                + ", programa=" + programa + ", honorariosAsimilables="
                + honorariosAsimilables + ", suplencias=" + suplencias
                + ", diasEconomicos=" + diasEconomicos
                + ", percepcionComplementaria=" + percepcionComplementaria
                + ", bono=" + bono + ", aguinaldo=" + aguinaldo + ", subsidio="
                + subsidio + ", primaVacacional=" + primaVacacional
                + ", bonificacionFalta=" + bonificacionFalta + ", retroactivo="
                + retroactivo + ", otros=" + otros + ", faltasRetardos="
                + faltasRetardos + ", isr=" + isr + ", responsabilidades="
                + responsabilidades + ", prestamo=" + prestamo
                + ", juicioMercantil=" + juicioMercantil + ", cuotaSindical="
                + cuotaSindical + ", pensionAlimenticia=" + pensionAlimenticia
                + ", total=" + total + "]";
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

    public void setConceptoCentroResponsabilidad(
            String conceptoCentroResponsabilidad) {
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

    public void setPercepcionComplementaria(
            BigDecimal percepcionComplementaria) {
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

        total = BigDecimal.ZERO;

        total = total.add(honorariosAsimilables == null ? BigDecimal.ZERO
                : honorariosAsimilables);
        total = total.add(suplencias == null ? BigDecimal.ZERO : suplencias);
        total = total
                .add(diasEconomicos == null ? BigDecimal.ZERO : diasEconomicos);
        total = total.add(percepcionComplementaria == null ? BigDecimal.ZERO
                : percepcionComplementaria);
        total = total.add(bono == null ? BigDecimal.ZERO : bono);
        total = total.add(aguinaldo == null ? BigDecimal.ZERO : aguinaldo);
        total = total.add(subsidio == null ? BigDecimal.ZERO : subsidio);
        total = total.add(
                primaVacacional == null ? BigDecimal.ZERO : primaVacacional);
        total = total.add(bonificacionFalta == null ? BigDecimal.ZERO
                : bonificacionFalta);
        total = total.add(retroactivo == null ? BigDecimal.ZERO : retroactivo);
        total = total.add(otros == null ? BigDecimal.ZERO : otros);
        total = total
                .add(faltasRetardos == null ? BigDecimal.ZERO : faltasRetardos);
        total = total.add(isr == null ? BigDecimal.ZERO : isr);
        total = total.add(responsabilidades == null ? BigDecimal.ZERO
                : responsabilidades);
        total = total.add(prestamo == null ? BigDecimal.ZERO : prestamo);
        total = total.add(
                juicioMercantil == null ? BigDecimal.ZERO : juicioMercantil);
        total = total
                .add(cuotaSindical == null ? BigDecimal.ZERO : cuotaSindical);
        total = total.add(pensionAlimenticia == null ? BigDecimal.ZERO
                : pensionAlimenticia);

        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the finPeriodo
     */
    public Date getFinPeriodo() {
        return finPeriodo;
    }

    /**
     * @param finPeriodo
     *            the finPeriodo to set
     */
    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    /**
     * @return the inicioPeriodo
     */
    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    /**
     * @param inicioPeriodo
     *            the inicioPeriodo to set
     */
    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto
     *            the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
