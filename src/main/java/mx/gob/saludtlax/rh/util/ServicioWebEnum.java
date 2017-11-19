
package mx.gob.saludtlax.rh.util;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez Enumerador con los diferentes
 *         servicios que puede cosumir el sistema
 *
 */
public enum ServicioWebEnum {
    CONTROL_ASISTENCIA_RS(1), FACTURACION_ELECTRONICA(2), CANCELACION_CFDI(3),
    RELOJ_CHECADOR(4);

    private final int id;

    ServicioWebEnum(int _id) {
        id = _id;
    }

    public int getId() {
        return id;
    }

}
