/*
 * DispersionDTO.java
 * Creado el 07/Dec/2016 6:51:15 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.dispersion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class DispersionDTO implements Serializable, Comparable<DispersionDTO> {

    private static final long serialVersionUID = -3617851396552564851L;

    // Datos que se emplean en la generación de la hoja de cálculo (Excel).
    private String numeroCuenta;
    private BigDecimal monto;
    private Date fechaPago;
    private String nombreEmpleado;
    private String fuenteFinanciamiento;
    // Datos que se emplean en la generación del archivo de texto.
    private String numeroCheque;
    private String nombreProducto;

    /**
     * Por defecto iniliza todos los valores del DTO con valores vacios.
     */
    public DispersionDTO() {
        this("", BigDecimal.ZERO, "", "", "");
    }

    /**
     * Constructor que se puede emplear en la generación del archivo de texto.
     *
     * @param numeroCuenta
     *            el número de cuenta.
     * @param monto
     *            el monto del pago.
     * @param nombreEmpleado
     *            el nombre del empleado.
     * @param numeroCheque
     *            el número de cheque.
     * @param nombreProducto
     *            el nombre del producto de nómina.
     */
    public DispersionDTO(String numeroCuenta, BigDecimal monto, String nombreEmpleado, String numeroCheque, String nombreProducto) {
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
        fechaPago = Calendar.getInstance().getTime();
        this.nombreEmpleado = nombreEmpleado;
        fuenteFinanciamiento = "";
        this.numeroCheque = numeroCheque;
        this.nombreProducto = nombreProducto;
    }

    /**
     * Constructor que se puede emplear en la generación de la hoja de cálculo
     * (Excel).
     *
     * @param nombreEmpleado
     *            el nombre del empleado.
     * @param numeroCuenta
     *            el número de cuenta.
     * @param monto
     *            el monto del pago.
     * @param fuenteFinanciamiento
     *            la fuente del financiamiento del empleado.
     * @param fechaPago
     *            la fecha de pago.
     */
    public DispersionDTO(String nombreEmpleado, String numeroCuenta, BigDecimal monto, String fuenteFinanciamiento, Date fechaPago) {
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.nombreEmpleado = nombreEmpleado;
        this.fuenteFinanciamiento = fuenteFinanciamiento;
        numeroCheque = "";
        nombreProducto = "";
    }

    /**
     * Get the value of numeroCuenta
     *
     * @return the value of numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Set the value of numeroCuenta
     *
     * @param numeroCuenta
     *            new value of numeroCuenta
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Get the value of monto
     *
     * @return the value of monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Set the value of monto
     *
     * @param monto
     *            new value of monto
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
     * @param fechaPago
     *            new value of fechaPago
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Get the value of nombreEmpleado
     *
     * @return the value of nombreEmpleado
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Set the value of nombreEmpleado
     *
     * @param nombreEmpleado
     *            new value of nombreEmpleado
     */
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    /**
     * Get the value of fuenteFinanciamiento
     *
     * @return the value of fuenteFinanciamiento
     */
    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    /**
     * Set the value of fuenteFinanciamiento
     *
     * @param fuenteFinanciamiento
     *            new value of fuenteFinanciamiento
     */
    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
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
     * @param numeroCheque
     *            new value of numeroCheque
     */
    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    /**
     * Get the value of nombreProducto
     *
     * @return the value of nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Set the value of nombreProducto
     *
     * @param nombreProducto
     *            new value of nombreProducto
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public int compareTo(DispersionDTO o) {
        if (o == null) {
            return 1;
        }

        int compare;

        if (fuenteFinanciamiento == null && o.fuenteFinanciamiento == null) {
            compare = 0;
        } else if (fuenteFinanciamiento == null && o.fuenteFinanciamiento != null) {
            compare = -1;
        } else if (fuenteFinanciamiento != null && o.fuenteFinanciamiento == null) {
            compare = 1;
        } else {
            compare = fuenteFinanciamiento.compareTo(o.fuenteFinanciamiento);
        }

        if (compare == 0) {
            if (numeroCuenta == null && o.numeroCuenta == null) {
                return 0;
            } else if (numeroCuenta == null && o.numeroCuenta != null) {
                return -1;
            } else if (numeroCuenta != null && o.numeroCuenta == null) {
                return 1;
            } else {
                return numeroCuenta.compareTo(o.numeroCuenta);
            }
        }

        return compare;
    }

    @Override
    public String toString() {
        return "DispersionDTO{" + "numeroCuenta=" + numeroCuenta + ", monto=" + monto + ", fechaPago=" + fechaPago + ", nombreEmpleado=" + nombreEmpleado
                + ", fuenteFinanciamiento=" + fuenteFinanciamiento + ", numeroCheque=" + numeroCheque + ", nombreProducto=" + nombreProducto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(numeroCuenta);
        hash = 37 * hash + Objects.hashCode(monto);
        hash = 37 * hash + Objects.hashCode(fechaPago);
        hash = 37 * hash + Objects.hashCode(nombreEmpleado);
        hash = 37 * hash + Objects.hashCode(fuenteFinanciamiento);
        hash = 37 * hash + Objects.hashCode(numeroCheque);
        hash = 37 * hash + Objects.hashCode(nombreProducto);
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
        final DispersionDTO other = (DispersionDTO) obj;
        if (!Objects.equals(numeroCuenta, other.numeroCuenta)) {
            return false;
        }
        if (!Objects.equals(monto, other.monto)) {
            return false;
        }
        if (!Objects.equals(fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(nombreEmpleado, other.nombreEmpleado)) {
            return false;
        }
        if (!Objects.equals(fuenteFinanciamiento, other.fuenteFinanciamiento)) {
            return false;
        }
        if (!Objects.equals(numeroCheque, other.numeroCheque)) {
            return false;
        }
        return Objects.equals(nombreProducto, other.nombreProducto);
    }

}
