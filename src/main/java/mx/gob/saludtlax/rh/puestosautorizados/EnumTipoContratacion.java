/**
 *
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leila Schiaffini Ehuan
 * @since 10/08/2016 12:34:53
 *
 */
public class EnumTipoContratacion {

    public static final int CONTRATO_ESTATAL = 1;
    public static final int CONTRATO_FEDERAL = 2;
    public static final int BASE = 3;
    public static final int CONFIANZA = 4;
    public static final int HOMOLOGADOS = 5;
    public static final int PASANTES = 6;
    public static final int FORMALIZADOS = 7;
    public static final int REGULARIZADOS = 8;
    public static final int INTERINATO = 9;
    public static final int VOLUNTARIOS = 13;
    public static final int HONORARIOS = 12;
    public static final int SERVICIO_SOCIAL = 14;
    public static final int UNEMES = 16;
    public static final int PROSPERA = 17;
    public static final int SUPLENCIA = 11;

    public static String obtenerDescripcion(int tipoContratacion) {

        switch (tipoContratacion) {
            case CONTRATO_ESTATAL:
                return "CONTRATO ESTATAL";
            case CONTRATO_FEDERAL:
                return "CONTRATO FEDERAL";
            case BASE:
                return "BASE FEDERAL";
            case CONFIANZA:
                return "CONFIANZA";
            case HOMOLOGADOS:
                return "HOMOLOGADOS";
            case PASANTES:
                return "PASANTES";
            case FORMALIZADOS:
                return "FORMALIZADOS";
            case REGULARIZADOS:
                return "REGULARIZADOS";
            case INTERINATO:
                return "INTERINATO";
            case VOLUNTARIOS:
                return "VOLUNTARIO";
            case HONORARIOS:
                return "HONORARIOS";
            case SERVICIO_SOCIAL:
                return "SERVICIO SOCIAL";
            case UNEMES:
                return "UNEMES";
            case PROSPERA:
                return "PROSPERA";
            default:
                throw new IllegalArgumentException("El valor indicado no es un tipo de contración correcto:" + tipoContratacion);
        }
    }

    public static Map<Integer, String> obtenerTipoContrataciones() {
        Map<Integer, String> tipoContrataciones = new HashMap<>();

        tipoContrataciones.put(EnumTipoContratacion.CONTRATO_ESTATAL, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.CONTRATO_ESTATAL));
        tipoContrataciones.put(EnumTipoContratacion.CONTRATO_FEDERAL, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.CONTRATO_FEDERAL));
        tipoContrataciones.put(EnumTipoContratacion.BASE, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.BASE));
        tipoContrataciones.put(EnumTipoContratacion.CONFIANZA, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.CONFIANZA));
        tipoContrataciones.put(EnumTipoContratacion.HOMOLOGADOS, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.HOMOLOGADOS));
        tipoContrataciones.put(EnumTipoContratacion.PASANTES, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.PASANTES));
        tipoContrataciones.put(EnumTipoContratacion.FORMALIZADOS, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.FORMALIZADOS));
        tipoContrataciones.put(EnumTipoContratacion.REGULARIZADOS, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.REGULARIZADOS));
        tipoContrataciones.put(EnumTipoContratacion.INTERINATO, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.INTERINATO));
        tipoContrataciones.put(EnumTipoContratacion.VOLUNTARIOS, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.VOLUNTARIOS));
        tipoContrataciones.put(EnumTipoContratacion.HONORARIOS, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.HONORARIOS));
        tipoContrataciones.put(EnumTipoContratacion.SERVICIO_SOCIAL, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.SERVICIO_SOCIAL));
        tipoContrataciones.put(EnumTipoContratacion.UNEMES, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.UNEMES));
        tipoContrataciones.put(EnumTipoContratacion.PROSPERA, EnumTipoContratacion.obtenerDescripcion(EnumTipoContratacion.PROSPERA));

        return Collections.unmodifiableMap(tipoContrataciones);
    }
}
