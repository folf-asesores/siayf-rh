/*
 * ConceptoComprobanteDTO.java
 * Creado el 18/nov/2016 3:42:37 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.comprobante;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Este DTO representa el el concepto de pago del comprobante de pago.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ConceptoComprobanteDTO implements Comparable<ConceptoComprobanteDTO>, Serializable {

    private static final long serialVersionUID = 1805453295202528057L;

    // Códigos de percepciones
    public static final short HONORARIOS_ASIMILADOS_A_SUELDOS = 1;
    public static final short SUPLENCIAS = 5;
    public static final short DIAS_ECONOMICOS = 8;
    public static final short PERCEPCION_COMPLEMENTARIA = 14;
    public static final short BONO = 17;
    public static final short AGUINALDO = 24;
    public static final short SUBSIDIO = 26;
    public static final short PRIMA_VACACIONAL = 27;
    public static final short BONIFICACION_DE_FALTAS = 29;
    public static final short RETROACTIVO = 30;
    public static final short OTROS = 32;

    // Códigos de deducciones
    public static final short FALTAS_Y_RETARDOS = 51;
    public static final short ISR = 52;
    public static final short RESPONSABILIDADES = 53;
    public static final short PRESTAMO = 55;
    public static final short PRESTAMO_COOFIA = 56;
    public static final short CUOTA_SINDICAL = 58;
    public static final short PENSION_ALIMENTICIA = 62;

    private short clave;
    private BigDecimal importe;

    /**
     * Crea una nueva instancia de esta clase e inicializa las propiedades con
     * valores con defecto.
     *
     */
    public ConceptoComprobanteDTO() {

    }

    /**
     * Crea una nueva instancia de esta clase e inicializa las propiedades con
     * los valores que recibe como parametros.
     *
     * @param clave la clave del concepto.
     * @param importe el importe aplicado al concepto.
     */
    public ConceptoComprobanteDTO(short clave, BigDecimal importe) {
        this.clave = clave;
        this.importe = importe;
    }

    /**
     * Get the value of clave
     *
     * @return the value of clave
     */
    public short getClave() {
        return clave;
    }

    /**
     * Set the value of clave
     *
     * @param clave new value of clave
     */
    public void setClave(short clave) {
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
    public int compareTo(ConceptoComprobanteDTO o) {
        if (clave == o.clave) {
            return 0;
        } else if (o.clave < OTROS) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.clave;
        hash = 89 * hash + Objects.hashCode(this.importe);
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
        final ConceptoComprobanteDTO other = (ConceptoComprobanteDTO) obj;
        if (this.clave != other.clave) {
            return false;
        }
        return Objects.equals(this.importe, other.importe);
    }

    @Override
    public String toString() {
        return "ConceptoComprobanteDTO{"
                + "clave : " + clave
                + ", importe : " + importe
                + '}';
    }

}
