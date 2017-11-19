/*
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.productonomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ProductosNominaProgramasExcelDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4410052793284349728L;

    private String rfc;
    private String nombreEmpleado;
    private Date fechaIngreso;
    private String centroResponsabilidad;
    private String programa;
    private String funcion;
    private BigDecimal sueldo;
    private BigDecimal isr;
    private BigDecimal pensionAlimenticia;

    public ProductosNominaProgramasExcelDTO() {
        rfc = "";
        nombreEmpleado = "";
        fechaIngreso = Calendar.getInstance().getTime();
        centroResponsabilidad = "";
        funcion = "";
        programa = "";
        sueldo = BigDecimal.ZERO;
        isr = BigDecimal.ZERO;
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

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void seSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo == null ? BigDecimal.ZERO : sueldo;
    }

    public BigDecimal getIsr() {
        return isr;
    }

    public void setIsr(BigDecimal isr) {
        this.isr = isr == null ? BigDecimal.ZERO : isr;
    }

    public BigDecimal getPensionAlimenticia() {
        return pensionAlimenticia;
    }

    public void setPensionAlimenticia(BigDecimal pensionAlimenticia) {
        this.pensionAlimenticia = pensionAlimenticia == null ? BigDecimal.ZERO
                : pensionAlimenticia;
    }

    public BigDecimal getTotal() {
        BigDecimal percepciones = BigDecimal.ZERO;
        percepciones = percepciones
                .add(sueldo == null ? BigDecimal.ZERO : sueldo);

        BigDecimal deducciones = BigDecimal.ZERO;
        deducciones = deducciones.add(isr == null ? BigDecimal.ZERO : isr);
        deducciones = deducciones.add(pensionAlimenticia == null
                ? BigDecimal.ZERO : pensionAlimenticia);

        return percepciones.subtract(deducciones);
    }

    @Override
    public String toString() {
        return "ProductosNominaExcelDTO[" + "rfc=" + rfc + ", nombreEmpleado="
                + nombreEmpleado + ", fechaIngreso=" + fechaIngreso
                + ", centroResponsabilidad=" + centroResponsabilidad
                + ", funcion=" + funcion + ", programa=" + programa
                + ", sueldo=" + sueldo + ", isr=" + isr
                + ", pensionAlimenticia=" + pensionAlimenticia + ", total="
                + getTotal() + ']';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(rfc);
        hash = 43 * hash + Objects.hashCode(nombreEmpleado);
        hash = 43 * hash + Objects.hashCode(fechaIngreso);
        hash = 43 * hash + Objects.hashCode(centroResponsabilidad);
        hash = 43 * hash + Objects.hashCode(programa);
        hash = 43 * hash + Objects.hashCode(funcion);
        hash = 43 * hash + Objects.hashCode(sueldo);
        hash = 43 * hash + Objects.hashCode(isr);
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
        final ProductosNominaProgramasExcelDTO other = (ProductosNominaProgramasExcelDTO) obj;
        if (!Objects.equals(rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(nombreEmpleado, other.nombreEmpleado)) {
            return false;
        }
        if (!Objects.equals(centroResponsabilidad,
                other.centroResponsabilidad)) {
            return false;
        }

        if (!Objects.equals(funcion, other.funcion)) {
            return false;
        }
        if (!Objects.equals(programa, other.programa)) {
            return false;
        }
        if (!Objects.equals(fechaIngreso, other.fechaIngreso)) {
            return false;
        }
        if (!Objects.equals(sueldo, other.sueldo)) {
            return false;
        }

        if (!Objects.equals(isr, other.isr)) {
            return false;
        }

        if (!Objects.equals(pensionAlimenticia, other.pensionAlimenticia)) {
            return false;
        }
        return Objects.equals(getTotal(), other.getTotal());
    }

}
