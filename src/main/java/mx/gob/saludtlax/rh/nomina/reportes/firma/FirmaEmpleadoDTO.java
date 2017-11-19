/*
 * FirmaDTO.java
 * Creado el 08/sep/2017 12:29:01 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class FirmaEmpleadoDTO implements Serializable {

    private static final long serialVersionUID = -6648191785606871680L;

    private final String filiacion;
    private final String nombre;
    private final String numeroCheque;
    private final BigDecimal importe;

    public FirmaEmpleadoDTO(String filiacion, String nombre, String numeroCheque, BigDecimal importe) {
        this.filiacion = filiacion;
        this.nombre = nombre;
        this.numeroCheque = numeroCheque;
        this.importe = importe;
    }

    public String getFiliacion() {
        return filiacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(filiacion);
        hash = 97 * hash + Objects.hashCode(nombre);
        hash = 97 * hash + Objects.hashCode(numeroCheque);
        hash = 97 * hash + Objects.hashCode(importe);
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
        final FirmaEmpleadoDTO other = (FirmaEmpleadoDTO) obj;
        if (!Objects.equals(filiacion, other.filiacion)) {
            return false;
        }
        if (!Objects.equals(nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(numeroCheque, other.numeroCheque)) {
            return false;
        }
        return Objects.equals(importe, other.importe);
    }

    @Override
    public String toString() {
        return "FirmaEmpleadoDTO{" + "filiacion : " + filiacion + ", nombre : " + nombre + ", numeroCheque : " + numeroCheque + ", importe : " + importe + '}';
    }

    public static final class Builder {

        private String filiacion;
        private String nombre;
        private String numeroCheque;
        private BigDecimal importe;

        public Builder() {
            filiacion = null;
            nombre = null;
            numeroCheque = null;
            importe = null;
        }

        public Builder setFiliacion(String filiacion) {
            this.filiacion = filiacion;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setNumeroCheque(String numeroCheque) {
            this.numeroCheque = numeroCheque;
            return this;
        }

        public Builder setImporte(BigDecimal importe) {
            this.importe = importe;
            return this;
        }

        public FirmaEmpleadoDTO construirFirmaEmpleadoDTO() {
            return new FirmaEmpleadoDTO(filiacion, nombre, numeroCheque, importe);
        }
    }
}
