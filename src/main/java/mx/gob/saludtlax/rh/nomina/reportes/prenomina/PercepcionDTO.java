/*
 * PercepcionDTO.java
 * Creado el 27/Jun/2017 3:34:25 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class PercepcionDTO {

    private String clave;
    private String nombre;
    private BigDecimal monto;

    public PercepcionDTO(String clave, String nombre, BigDecimal monto) {
        this.clave = clave;
        this.nombre = nombre;
        this.monto = monto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.clave);
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.monto);
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
        final PercepcionDTO other = (PercepcionDTO) obj;
        if (!Objects.equals(this.clave, other.clave)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.monto, other.monto);
    }

    @Override
    public String toString() {
        return "PercepcionDTO{" + "clave=" + clave + ", nombre=" + nombre + ", monto=" + monto + '}';
    }
}
