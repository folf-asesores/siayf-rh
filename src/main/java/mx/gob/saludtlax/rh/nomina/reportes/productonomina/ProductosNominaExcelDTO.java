/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.productonomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

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

    public ProductosNominaExcelDTO() {
        rfc = "";
        nombreEmpleado = "";
        fechaIngreso = Calendar.getInstance().getTime();
        centroResponsabilidad = "";
        conceptoCentroResponsabilidad = "";
        funcion = "";
        programa = "";
        honorariosAsimilables = BigDecimal.ZERO;
        suplencias = BigDecimal.ZERO;
        diasEconomicos = BigDecimal.ZERO;
        percepcionComplementaria = BigDecimal.ZERO;
        bono = BigDecimal.ZERO;
        aguinaldo = BigDecimal.ZERO;
        subsidio = BigDecimal.ZERO;
        primaVacacional = BigDecimal.ZERO;
        bonificacionFalta = BigDecimal.ZERO;
        retroactivo = BigDecimal.ZERO;
        otros = BigDecimal.ZERO;
        faltasRetardos = BigDecimal.ZERO;
        isr = BigDecimal.ZERO;
        responsabilidades = BigDecimal.ZERO;
        prestamo = BigDecimal.ZERO;
        juicioMercantil = BigDecimal.ZERO;
        cuotaSindical = BigDecimal.ZERO;
        pensionAlimenticia = BigDecimal.ZERO;
    }
    
    // Getters and Setters

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
        this.honorariosAsimilables = honorariosAsimilables == null ? BigDecimal.ZERO : honorariosAsimilables;
    }

    public BigDecimal getSuplencias() {
        return suplencias;
    }

    public void setSuplencias(BigDecimal suplencias) {
        this.suplencias = suplencias == null ? BigDecimal.ZERO : suplencias;
    }

    public BigDecimal getDiasEconomicos() {
        return diasEconomicos;
    }

    public void setDiasEconomicos(BigDecimal diasEconomicos) {
        this.diasEconomicos = diasEconomicos == null ? BigDecimal.ZERO : diasEconomicos;
    }

    public BigDecimal getPercepcionComplementaria() {
        return percepcionComplementaria;
    }

    public void setPercepcionComplementaria(BigDecimal percepcionComplementaria) {
        this.percepcionComplementaria = percepcionComplementaria == null ? BigDecimal.ZERO : percepcionComplementaria;
    }

    public BigDecimal getBono() {
        return bono;
    }

    public void setBono(BigDecimal bono) {
        this.bono = bono == null ? BigDecimal.ZERO :  bono;
    }

    public BigDecimal getAguinaldo() {
        return aguinaldo;
    }

    public void setAguinaldo(BigDecimal aguinaldo) {
        this.aguinaldo = aguinaldo == null ? BigDecimal.ZERO : aguinaldo;
    }

    public BigDecimal getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(BigDecimal subsidio) {
        this.subsidio = subsidio == null ? BigDecimal.ZERO : subsidio;
    }

    public BigDecimal getPrimaVacacional() {
        return primaVacacional;
    }

    public void setPrimaVacacional(BigDecimal primaVacacional) {
        this.primaVacacional = primaVacacional == null ? BigDecimal.ZERO : primaVacacional;
    }

    public BigDecimal getBonificacionFalta() {
        return bonificacionFalta;
    }

    public void setBonificacionFalta(BigDecimal bonificacionFalta) {
        this.bonificacionFalta = bonificacionFalta == null ? BigDecimal.ZERO : bonificacionFalta;
    }

    public BigDecimal getRetroactivo() {
        return retroactivo;
    }

    public void setRetroactivo(BigDecimal retroactivo) {
        this.retroactivo = retroactivo == null ? BigDecimal.ZERO : retroactivo;
    }

    public BigDecimal getOtros() {
        return otros;
    }

    public void setOtros(BigDecimal otros) {
        this.otros = otros == null ? BigDecimal.ZERO : otros;
    }

    public BigDecimal getFaltasRetardos() {
        return faltasRetardos;
    }

    public void setFaltasRetardos(BigDecimal faltasRetardos) {
        this.faltasRetardos = faltasRetardos == null ? BigDecimal.ZERO : faltasRetardos;
    }

    public BigDecimal getIsr() {
        return isr;
    }

    public void setIsr(BigDecimal isr) {
        this.isr = isr == null ? BigDecimal.ZERO : isr;
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
        this.prestamo = prestamo == null ? BigDecimal.ZERO : prestamo;
    }

    public BigDecimal getJuicioMercantil() {
        return juicioMercantil;
    }

    public void setJuicioMercantil(BigDecimal juicioMercantil) {
        this.juicioMercantil = juicioMercantil == null ? BigDecimal.ZERO : juicioMercantil;
    }

    public BigDecimal getCuotaSindical() {
        return cuotaSindical;
    }

    public void setCuotaSindical(BigDecimal cuotaSindical) {
        this.cuotaSindical = cuotaSindical == null ? BigDecimal.ZERO : cuotaSindical;
    }

    public BigDecimal getPensionAlimenticia() {
        return pensionAlimenticia;
    }

    public void setPensionAlimenticia(BigDecimal pensionAlimenticia) {
        this.pensionAlimenticia = pensionAlimenticia == null ? BigDecimal.ZERO : pensionAlimenticia;
    }

    public BigDecimal getTotal() {
        BigDecimal percepciones = BigDecimal.ZERO;
        percepciones = percepciones.add(honorariosAsimilables == null ? BigDecimal.ZERO : honorariosAsimilables);
        percepciones = percepciones.add(suplencias == null ? BigDecimal.ZERO : suplencias);
        percepciones = percepciones.add(diasEconomicos == null ? BigDecimal.ZERO : diasEconomicos);
        percepciones = percepciones.add(percepcionComplementaria == null ? BigDecimal.ZERO : percepcionComplementaria);
        percepciones = percepciones.add(bono == null ? BigDecimal.ZERO : bono);
        percepciones = percepciones.add(aguinaldo == null ? BigDecimal.ZERO : aguinaldo);
        percepciones = percepciones.add(subsidio == null ? BigDecimal.ZERO : subsidio);
        percepciones = percepciones.add(primaVacacional == null ? BigDecimal.ZERO : primaVacacional);
        percepciones = percepciones.add(bonificacionFalta == null ? BigDecimal.ZERO : bonificacionFalta);
        percepciones = percepciones.add(retroactivo == null ? BigDecimal.ZERO : retroactivo);
        percepciones = percepciones.add(otros == null ? BigDecimal.ZERO : otros);

        BigDecimal deducciones = BigDecimal.ZERO;
        deducciones  = deducciones.add(faltasRetardos == null ? BigDecimal.ZERO : faltasRetardos);
        deducciones  = deducciones.add(isr == null ? BigDecimal.ZERO : isr);
        deducciones  = deducciones.add(responsabilidades == null ? BigDecimal.ZERO : responsabilidades);
        deducciones  = deducciones.add(prestamo == null ? BigDecimal.ZERO : prestamo);
        deducciones  = deducciones.add(juicioMercantil == null ? BigDecimal.ZERO : juicioMercantil);
        deducciones  = deducciones.add(cuotaSindical == null ? BigDecimal.ZERO : cuotaSindical);
        deducciones  = deducciones.add(pensionAlimenticia == null ? BigDecimal.ZERO : pensionAlimenticia);

        return percepciones.subtract(deducciones);
    }

    @Override
    public String toString() {
        return "ProductosNominaExcelDTO["
                + "rfc=" + rfc
                + ", nombreEmpleado=" + nombreEmpleado
                + ", fechaIngreso=" + fechaIngreso
                + ", centroResponsabilidad=" + centroResponsabilidad
                + ", conceptoCentroResponsabilidad=" + conceptoCentroResponsabilidad
                + ", funcion=" + funcion
                + ", programa=" + programa
                + ", honorariosAsimilables=" + honorariosAsimilables
                + ", suplencias=" + suplencias
                + ", diasEconomicos=" + diasEconomicos
                + ", percepcionComplementaria=" + percepcionComplementaria
                + ", bono=" + bono
                + ", aguinaldo=" + aguinaldo
                + ", subsidio=" + subsidio
                + ", primaVacacional=" + primaVacacional
                + ", bonificacionFalta=" + bonificacionFalta
                + ", retroactivo=" + retroactivo
                + ", otros=" + otros
                + ", faltasRetardos=" + faltasRetardos
                + ", isr=" + isr
                + ", responsabilidades=" + responsabilidades
                + ", prestamo=" + prestamo
                + ", juicioMercantil=" + juicioMercantil
                + ", cuotaSindical=" + cuotaSindical
                + ", pensionAlimenticia=" + pensionAlimenticia
                + ", total=" + getTotal()
                + ']';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(rfc);
        hash = 43 * hash + Objects.hashCode(nombreEmpleado);
        hash = 43 * hash + Objects.hashCode(fechaIngreso);
        hash = 43 * hash + Objects.hashCode(centroResponsabilidad);
        hash = 43 * hash + Objects.hashCode(conceptoCentroResponsabilidad);
        hash = 43 * hash + Objects.hashCode(funcion);
        hash = 43 * hash + Objects.hashCode(programa);
        hash = 43 * hash + Objects.hashCode(honorariosAsimilables);
        hash = 43 * hash + Objects.hashCode(suplencias);
        hash = 43 * hash + Objects.hashCode(diasEconomicos);
        hash = 43 * hash + Objects.hashCode(percepcionComplementaria);
        hash = 43 * hash + Objects.hashCode(bono);
        hash = 43 * hash + Objects.hashCode(aguinaldo);
        hash = 43 * hash + Objects.hashCode(subsidio);
        hash = 43 * hash + Objects.hashCode(primaVacacional);
        hash = 43 * hash + Objects.hashCode(bonificacionFalta);
        hash = 43 * hash + Objects.hashCode(retroactivo);
        hash = 43 * hash + Objects.hashCode(otros);
        hash = 43 * hash + Objects.hashCode(faltasRetardos);
        hash = 43 * hash + Objects.hashCode(isr);
        hash = 43 * hash + Objects.hashCode(responsabilidades);
        hash = 43 * hash + Objects.hashCode(prestamo);
        hash = 43 * hash + Objects.hashCode(juicioMercantil);
        hash = 43 * hash + Objects.hashCode(cuotaSindical);
        hash = 43 * hash + Objects.hashCode(pensionAlimenticia);
        hash = 43 * hash + Objects.hashCode(getTotal());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductosNominaExcelDTO other = (ProductosNominaExcelDTO) obj;
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(this.nombreEmpleado, other.nombreEmpleado)) {
            return false;
        }
        if (!Objects.equals(this.centroResponsabilidad, other.centroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(this.conceptoCentroResponsabilidad, other.conceptoCentroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(this.funcion, other.funcion)) {
            return false;
        }
        if (!Objects.equals(this.programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(this.fechaIngreso, other.fechaIngreso)) {
            return false;
        }
        if (!Objects.equals(this.honorariosAsimilables, other.honorariosAsimilables)) {
            return false;
        }
        if (!Objects.equals(this.suplencias, other.suplencias)) {
            return false;
        }
        if (!Objects.equals(this.diasEconomicos, other.diasEconomicos)) {
            return false;
        }
        if (!Objects.equals(this.percepcionComplementaria, other.percepcionComplementaria)) {
            return false;
        }
        if (!Objects.equals(this.bono, other.bono)) {
            return false;
        }
        if (!Objects.equals(this.aguinaldo, other.aguinaldo)) {
            return false;
        }
        if (!Objects.equals(this.subsidio, other.subsidio)) {
            return false;
        }
        if (!Objects.equals(this.primaVacacional, other.primaVacacional)) {
            return false;
        }
        if (!Objects.equals(this.bonificacionFalta, other.bonificacionFalta)) {
            return false;
        }
        if (!Objects.equals(this.retroactivo, other.retroactivo)) {
            return false;
        }
        if (!Objects.equals(this.otros, other.otros)) {
            return false;
        }
        if (!Objects.equals(this.faltasRetardos, other.faltasRetardos)) {
            return false;
        }
        if (!Objects.equals(this.isr, other.isr)) {
            return false;
        }
        if (!Objects.equals(this.responsabilidades, other.responsabilidades)) {
            return false;
        }
        if (!Objects.equals(this.prestamo, other.prestamo)) {
            return false;
        }
        if (!Objects.equals(this.juicioMercantil, other.juicioMercantil)) {
            return false;
        }
        if (!Objects.equals(this.cuotaSindical, other.cuotaSindical)) {
            return false;
        }
        if (!Objects.equals(this.pensionAlimenticia, other.pensionAlimenticia)) {
            return false;
        }
        return Objects.equals(this.getTotal(), other.getTotal());
    }

}
