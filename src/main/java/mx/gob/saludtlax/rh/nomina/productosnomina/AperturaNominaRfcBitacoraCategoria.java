/*
 * AperturaNominaRfcBitacoraCategoria.java
 * Creado el 03/Jan/2017 5:17:43 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum AperturaNominaRfcBitacoraCategoria {
    INFORMACION("Información"),
    ADVERTENCIA("Advertencia"),
    ERROR("Error");

    private AperturaNominaRfcBitacoraCategoria(String valor) {
        this.valor = valor;
    }

    /**
     * Get the value of valor
     *
     * @return the value of valor
     */
    public String getValor() {
        return valor;
    }

    private final String valor;
}
