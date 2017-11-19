/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/09/2016 12:51:56
 *
 */
public class EnumEstatusPuesto {
    // Nuevo programa, ampliación presupuesto, proceso de homologacion,
    // formalizacion o regularizacion.
    public static final int APERTURA_DESIGNACION = 1;
    // Disponibles por Bajas
    public static final int LIBERADA = 2;

    // No disponibles por tener empleado activo.
    public static final int EMPLEADO_ACTIVO = 4;
    // No disponibles por tener empleado en permiso
    public static final int EMPLEADO_EN_PERMISO = 5;
    // No disponibles por cancelación de puesto.
    public static final int CANCELADA = 6;
}
