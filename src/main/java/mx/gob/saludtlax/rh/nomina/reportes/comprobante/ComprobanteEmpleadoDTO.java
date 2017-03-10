/*
 * ComprobanteEmpleadoDTO.java
 * Creado el 18/Nov/2016 3:12:03 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Este DTO representa la informacion del comprobante de pago.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoDTO implements Comparable<ComprobanteEmpleadoDTO>, Serializable {

    private static final long serialVersionUID = -7098331436352872300L;

    private String nombre;
    private String filiacion;
    private Date fechaPago;
    private String claveCentroResponsabilidad;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;
    private List<ConceptoComprobanteDTO> conceptos;

    /**
     * <p>Crea una nueva instancia de la esta clase e inicializa la clase con 
     * valores por defecto.<\p>
     */
    public ComprobanteEmpleadoDTO() {
        nombre = "";
        filiacion = "";
        fechaPago = Calendar.getInstance().getTime();
        claveCentroResponsabilidad = "";
        inicioPeriodo = Calendar.getInstance().getTime();
        finPeriodo = Calendar.getInstance().getTime();
        percepciones  = BigDecimal.ZERO;
        deducciones = BigDecimal.ZERO;
        neto = BigDecimal.ZERO;
        conceptos = new ArrayList<>();
    }

    public ComprobanteEmpleadoDTO(String nombre, String filiacion, Date fechaPago, String claveCentroResponsabilidad, Date inicioPeriodo, Date finPeriodo, BigDecimal percepciones, BigDecimal deducciones, BigDecimal neto, List<ConceptoComprobanteDTO> conceptos) {
        this.nombre = nombre;
        this.filiacion = filiacion;
        this.fechaPago = fechaPago;
        this.claveCentroResponsabilidad = claveCentroResponsabilidad;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.neto = neto;
        this.conceptos = conceptos;
    }
    
    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of filiacion
     *
     * @return the value of filiacion
     */
    public String getFiliacion() {
        return filiacion;
    }

    /**
     * Set the value of filiacion
     *
     * @param filiacion new value of filiacion
     */
    public void setFiliacion(String filiacion) {
        this.filiacion = filiacion;
    }

    /**
     * Get the value of fechaPago
     *
     * @return the value of fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Set the value of fechaPago
     *
     * @param fechaPago new value of fechaPago
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Get the value of claveCentroResponsabilidad
     *
     * @return the value of claveCentroResponsabilidad
     */
    public String getClaveCentroResponsabilidad() {
        return claveCentroResponsabilidad;
    }

    /**
     * Set the value of claveCentroResponsabilidad
     *
     * @param claveCentroResponsabilidad new value of claveCentroResponsabilidad
     */
    public void setClaveCentroResponsabilidad(String claveCentroResponsabilidad) {
        this.claveCentroResponsabilidad = claveCentroResponsabilidad;
    }

    /**
     * Get the value of inicioPeriodo
     *
     * @return the value of inicioPeriodo
     */
    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    /**
     * Set the value of inicioPeriodo
     *
     * @param inicioPeriodo new value of inicioPeriodo
     */
    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    /**
     * Get the value of finPeriodo
     *
     * @return the value of finPeriodo
     */
    public Date getFinPeriodo() {
        return finPeriodo;
    }

    /**
     * Set the value of finPeriodo
     *
     * @param finPeriodo new value of finPeriodo
     */
    public void setFinPeriodo(Date finPeriodo) {
        this.finPeriodo = finPeriodo;
    }

    /**
     * Get the value of percepciones
     *
     * @return the value of percepciones
     */
    public BigDecimal getPercepciones() {
        return percepciones;
    }

    /**
     * Set the value of percepciones
     *
     * @param percepciones new value of percepciones
     */
    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    /**
     * Get the value of deducciones
     *
     * @return the value of deducciones
     */
    public BigDecimal getDeducciones() {
        return deducciones;
    }

    /**
     * Set the value of deducciones
     *
     * @param deducciones new value of deducciones
     */
    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    /**
     * Get the value of neto
     *
     * @return the value of neto
     */
    public BigDecimal getNeto() {
        return neto;
    }

    /**
     * Set the value of neto
     *
     * @param neto new value of neto
     */
    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    /**
     * Get the value of conceptos
     *
     * @return the value of conceptos
     */
    public List<ConceptoComprobanteDTO> getConceptos() {
        return conceptos;
    }

    /**
     * Set the value of conceptos
     *
     * @param conceptos new value of conceptos
     */
    public void setConceptos(List<ConceptoComprobanteDTO> conceptos) {
        this.conceptos = conceptos;
    }

    @Override
    public int compareTo(ComprobanteEmpleadoDTO o) {
    	if(claveCentroResponsabilidad == null && o.claveCentroResponsabilidad == null) {
    		return 0;
    	}
    	if(claveCentroResponsabilidad == null) {
    		return -1;
    	}
    	if(o.claveCentroResponsabilidad == null) {
    		return 1;
    	}
        return claveCentroResponsabilidad.equals(o.claveCentroResponsabilidad) 
                ? filiacion.compareTo(o.filiacion) 
                : claveCentroResponsabilidad
                        .compareTo(o.claveCentroResponsabilidad);
    }

    @Override
    public String toString() {
        return "ComprobanteEmpleadoDTO{" 
                + "nombre=" + nombre 
                + ", filiacion=" + filiacion
                + ", fechaPago=" + fechaPago 
                + ", claveCentroResponsabilidad=" + claveCentroResponsabilidad
                + ", inicioPeriodo=" + inicioPeriodo
                + ", finPeriodo=" + finPeriodo
                + ", percepciones=" + percepciones
                + ", deducciones=" + deducciones
                + ", neto=" + neto
                + ", conceptos=" + conceptos
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(nombre);
        hash = 97 * hash + Objects.hashCode(filiacion);
        hash = 97 * hash + Objects.hashCode(fechaPago);
        hash = 97 * hash + Objects.hashCode(claveCentroResponsabilidad);
        hash = 97 * hash + Objects.hashCode(inicioPeriodo);
        hash = 97 * hash + Objects.hashCode(finPeriodo);
        hash = 97 * hash + Objects.hashCode(percepciones);
        hash = 97 * hash + Objects.hashCode(deducciones);
        hash = 97 * hash + Objects.hashCode(neto);
        hash = 97 * hash + Objects.hashCode(conceptos);
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
        final ComprobanteEmpleadoDTO other = (ComprobanteEmpleadoDTO) obj;
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(filiacion, other.filiacion)) {
            return false;
        }
        if (!Objects.equals(claveCentroResponsabilidad, other.claveCentroResponsabilidad)) {
            return false;
        }
        if (!Objects.equals(fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(inicioPeriodo, other.inicioPeriodo)) {
            return false;
        }
        if (!Objects.equals(finPeriodo, other.finPeriodo)) {
            return false;
        }
        if (!Objects.equals(percepciones, other.percepciones)) {
            return false;
        }
        if (!Objects.equals(deducciones, other.deducciones)) {
            return false;
        }
        if (!Objects.equals(neto, other.neto)) {
            return false;
        }
        return Objects.equals(conceptos, other.conceptos);
    }

}
