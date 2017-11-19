/*
 * TipoQuinquenios.java
 * Creado el Mar 10, 2016, 7:14:07 PM
 *
 *
 */

package mx.gob.saludtlax.rh.quinquenios;

/**
 * Este enumerable contiene los tipos de quinquenios
 *
 * @author Edgar
 */
public enum TipoQuinquenios {

    A100("A100"), A200("A200"), A300("A300"), A400("A400"), A500("A500");

    TipoQuinquenios(String clave) {
        this.clave = clave;

    }

    public static String getClaveConcepto(Integer antiguedad) {
        String clave = "";

        switch (antiguedad) {
            case 5:
                clave = A100.clave;
                break;
            case 10:
                clave = A200.clave;
                break;
            case 15:
                clave = A300.clave;
                break;
            case 20:
                clave = A400.clave;
                break;
            case 25:
                clave = A500.clave;
                break;
            default:
                throw new IllegalArgumentException("error al generar la clave de quinquenio");
        }

        return clave;
    }

    private final String clave;

}
