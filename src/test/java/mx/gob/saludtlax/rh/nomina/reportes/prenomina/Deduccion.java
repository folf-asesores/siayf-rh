/*
 * Deduccion.java
 * Creado el 27/Jun/2017 3:35:09 PM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class Deduccion {

    private int clave;
    private String nombre;
    private BigDecimal monto;

    public Deduccion() {
    }

    public Deduccion(int clave, String nombre, BigDecimal monto) {
        this.clave = clave;
        this.nombre = nombre;
        this.monto = monto;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
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

}
