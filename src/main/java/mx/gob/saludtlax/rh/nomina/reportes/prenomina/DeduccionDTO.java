/*
 * DeduccionDTO.java
 * Creado el 27/Jun/2017 3:35:09 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public final class DeduccionDTO {

    private final String clave;
    private final String nombre;
    private final BigDecimal monto;

    public DeduccionDTO(String clave, String nombre, BigDecimal monto) {
        this.clave = clave;
        this.nombre = nombre;
        this.monto = monto;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.clave);
        hash = 61 * hash + Objects.hashCode(this.nombre);
        hash = 61 * hash + Objects.hashCode(this.monto);
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
        final DeduccionDTO other = (DeduccionDTO) obj;
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
        return "DeduccionDTO{" + "clave=" + clave + ", nombre=" + nombre + ", monto=" + monto + '}';
    }

}
