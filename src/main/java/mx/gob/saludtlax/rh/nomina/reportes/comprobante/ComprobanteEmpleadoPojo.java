/*
 * ComprobanteEmpleadoPojo.java
 * Creado el 22/nov/2016 6:11:00 AM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import mx.gob.saludtlax.rh.persistencia.espejo.ComprobanteEmpleadoRepository;

/**
 * Esta clase representa la información tal cual se obtiene desde la 
 * base de datos.
 * 
 * @see ComprobanteEmpleadoRepository#obtenerDatos(java.lang.Integer)
 * @see ComprobanteEmpleadoService#convertir(java.util.List)
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ComprobanteEmpleadoPojo implements Serializable {

    private static final long serialVersionUID = -3974694988544745680L;

    private String nombre;
    private String filiacion;
    private Date fechaPago;
    private String claveCentroResposabilidad;
    private Date inicioPeriodo;
    private Date finPeriodo;
    private String numeroCheque;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;
    private String clave;
    private BigDecimal importe;

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
     * Get the value of claveCentroResposabilidad
     *
     * @return the value of claveCentroResposabilidad
     */
    public String getClaveCentroResposabilidad() {
        return claveCentroResposabilidad;
    }

    /**
     * Set the value of claveCentroResposabilidad
     *
     * @param claveCentroResposabilidad new value of claveCentroResposabilidad
     */
    public void setClaveCentroResposabilidad(String claveCentroResposabilidad) {
        this.claveCentroResposabilidad = claveCentroResposabilidad;
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
     * Get the value of numeroCheque
     *
     * @return the value of numeroCheque
     */
    public String getNumeroCheque() {
        return numeroCheque;
    }

    /**
     * Set the value of numeroCheque
     *
     * @param numeroCheque new value of numeroCheque
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
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
     * Get the value of clave
     *
     * @return the value of clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Get the value of importe
     *
     * @return the value of importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * Set the value of importe
     *
     * @param importe new value of importe
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "ComprobanteEmpleadoPOJO{"
                + "nombre : " + nombre
                + ", filiacion : " + filiacion
                + ", fechaPago : " + fechaPago
                + ", claveCentroResposabilidad : " + claveCentroResposabilidad
                + ", numeroCheque : " + numeroCheque
                + ", inicioPeriodo : " + inicioPeriodo
                + ", finPeriodo : " + finPeriodo
                + ", percepciones : " + percepciones
                + ", deducciones : " + deducciones
                + ", neto : " + neto
                + ", clave : " + clave
                + ", importe : " + importe
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(nombre);
        hash = 67 * hash + Objects.hashCode(filiacion);
        hash = 67 * hash + Objects.hashCode(fechaPago);
        hash = 67 * hash + Objects.hashCode(claveCentroResposabilidad);
        hash = 67 * hash + Objects.hashCode(numeroCheque);
        hash = 67 * hash + Objects.hashCode(inicioPeriodo);
        hash = 67 * hash + Objects.hashCode(finPeriodo);
        hash = 67 * hash + Objects.hashCode(percepciones);
        hash = 67 * hash + Objects.hashCode(deducciones);
        hash = 67 * hash + Objects.hashCode(neto);
        hash = 67 * hash + Objects.hashCode(clave);
        hash = 67 * hash + Objects.hashCode(importe);
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
        final ComprobanteEmpleadoPojo other = (ComprobanteEmpleadoPojo) obj;
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(filiacion, other.filiacion)) {
            return false;
        }
        if (!Objects.equals(claveCentroResposabilidad, other.claveCentroResposabilidad)) {
            return false;
        }
        if (!Objects.equals(clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(numeroCheque, other.numeroCheque)) {
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
        return Objects.equals(importe, other.importe);
    }

}
