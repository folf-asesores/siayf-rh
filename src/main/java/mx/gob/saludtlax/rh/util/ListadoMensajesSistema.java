
package mx.gob.saludtlax.rh.util;

import java.io.Serializable;

/**
 * Recopilacion de los diferentes mensajes que puede desplegar el sistema.
 *
 * @author Juan Carlos Ivan Ganzo Domínguez
 *
 */
public class ListadoMensajesSistema implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7359039995832060898L;

    /**
     * No se puede verificar el estado del servicio
     */

    public static final MensajeSistema E000 = new MensajeSistema(
            "No se puede verificar el estado del servicio", "E0000");

    /**
     * Ocurrio un problema al conectar con el servicio. Codigo de Estatus:
     */
    public static final MensajeSistema E001 = new MensajeSistema(
            "Ocurrio un problema al conectar con el servicio. Codigo de Estatus: ",
            "E0001");

    /**
     * No se pudo llenar el listado de incidencias.
     */
    public static final MensajeSistema E002 = new MensajeSistema(
            "No se pudo llenar la lista de incidencias.", "E002");

    // MENSAJE VALIDACION INCIDENCIA
    /**
     * Debe capturar la descripción de la incidencia
     */
    public static final MensajeSistema CAIV001 = new MensajeSistema("CAIV001",
            "Debe capturar la descripción de la incidencia");
    /**
     * Debe seleccionar la imagen antes de guardar
     */
    public static final MensajeSistema CAIV002 = new MensajeSistema("CAIV002",
            "Debe selecciona la imagen antes de guardar");
    /**
     * Debe capturar la marca para el reporte
     */
    public static final MensajeSistema CAIV003 = new MensajeSistema("CAIV003",
            "Debe capturar la marca para el reporte");
    /**
     * El tamaño de la imagen debe ser menor a 256kb;
     */
    public static final MensajeSistema CAIV004 = new MensajeSistema("CAIV004",
            "El tamaño de la imagen debe ser menor a 256kb");
    /**
     * Solo se permiten imagenes con extension .png;
     */
    public static final MensajeSistema CAIV005 = new MensajeSistema("CAIV005",
            "Solo se permiten imagenes con extension png");
    /**
     * Se ha guardado con éxito
     */
    public static final MensajeSistema CA001 = new MensajeSistema("CA001",
            "Se han guardado los datos con exito");

    /**
     * Debe Selecionar la incidencia a capturar
     */
    public static final MensajeSistema CARA001 = new MensajeSistema("CARA001",
            "Debe Selecionar la incidencia a capturar");

    /**
     * El minuto final debe ser mayor al minuto final
     */
    public static final MensajeSistema CARA002 = new MensajeSistema("CARA002",
            "El minuto final debe ser mayor al minuto final");

    /**
     * Debe capturar la descripcion de la jornada.
     */
    public static final MensajeSistema CARA003 = new MensajeSistema("CARA003",
            "Debe capturar la descripcion de la jornada.");

    /**
     * Se guardo la jornada, pero algunas reglas no fueron guardadas verifique la informacion.
     */
    public static final MensajeSistema CARA004 = new MensajeSistema("CARA004",
            "Se guardo la jornada, pero algunas reglas no fueron guardadas verifique la informacion.");

}
