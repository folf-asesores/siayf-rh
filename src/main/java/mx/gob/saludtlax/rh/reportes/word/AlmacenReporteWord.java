/*
 *
 */

package mx.gob.saludtlax.rh.reportes.word;

import java.util.HashMap;
import java.util.Map;

import mx.gob.saludtlax.rh.reportes.AlmacenReportes;

/**
 * @author eduardo Mex
 *
 */
public class AlmacenReporteWord implements AlmacenReportes<WordReporte> {

    private static final Map<String, WordReporte> REPORTES;

    static {

        WordReporte contrato = new WordReporte("CONTRATO_INDIVIDUAL.docx", "plantillas/contrato/");
        WordReporte nombramiento = new WordReporte("NOMBRAMIENTO_DEFINITIVO.docx", "plantillas/nombramiento/");
        WordReporte nombramientoGenerico = new WordReporte("NOMBRAMIENTO_GENERICO.docx", "plantillas/nombramiento/");
        WordReporte nombramientoFormalizadoII = new WordReporte("NOMBRAMIENTO_FORMALIZADO_FASE.docx", "plantillas/nombramiento/");
        WordReporte nombramientoInterinato = new WordReporte("NOMBRAMIENTO_INTERINO.docx", "plantillas/nombramiento/");
        WordReporte cambioAdscripcion = new WordReporte("CAMBIO_ADSCRIPCION.docx", "plantillas/cambio/");
        WordReporte comisionOficial = new WordReporte("COMISION_OFICIAL.docx", "plantillas/comision/");
        WordReporte eleccionPopular = new WordReporte("RESERVACION_PLAZA_ELECCION_POPULAR.docx", "plantillas/reservacion/");
        WordReporte reincorporacionBase = new WordReporte("REINCORPORACION_BASE.docx", "plantillas/reincorporacion/");
        WordReporte reservacionOtraDependencia = new WordReporte("RESERVACION_PLAZA_CONFIANZA_OTRA_DEPENDENCIA.docx", "plantillas/reservacion/");
        WordReporte reservacionPlazaConfianza = new WordReporte("RESERVACION_PLAZA_CONFIANZA.docx", "plantillas/reservacion/");
        WordReporte residenciaMedica = new WordReporte("RESIDENCIA_MEDICA.docx", "plantillas/residencia/");
        WordReporte terminoComisionReincorporacion = new WordReporte("TERMINO_COMISION_Y_REINCORPORACION_ADSCRIPCION.docx", "plantillas/termino/");
        WordReporte terminoConfianza = new WordReporte("TERMINO_CONFIANZA.docx", "plantillas/termino/");
        WordReporte terminoConfianzaReincorporacion = new WordReporte("TERMINO_CONFIANZA_Y_REINCORPORACION_BASE.docx", "plantillas/termino/");
        WordReporte terminoInterinato = new WordReporte("TERMINO_INTERINATO.docx", "plantillas/termino/");

        REPORTES = new HashMap<>();

        REPORTES.put("contrato-individual", contrato);
        REPORTES.put("nombramiento-definitivo", nombramiento);
        REPORTES.put("nombramiento-generico", nombramientoGenerico);
        REPORTES.put("nombramiento-formalizado-fase", nombramientoFormalizadoII);
        REPORTES.put("nombramiento-interino", nombramientoInterinato);
        REPORTES.put("cambio-adscripcion", cambioAdscripcion);
        REPORTES.put("comision-oficial", comisionOficial);
        REPORTES.put("reservacion-plaza-eleccion-popular", eleccionPopular);
        REPORTES.put("reincorporacion-base", reincorporacionBase);
        REPORTES.put("reservacion-plaza-confianza-otra-dependencia", reservacionOtraDependencia);
        REPORTES.put("reservacion-plaza-confianza", reservacionPlazaConfianza);
        REPORTES.put("residencia-medica", residenciaMedica);
        REPORTES.put("termino-comision-reincorporacion-adscripcion", terminoComisionReincorporacion);
        REPORTES.put("termino-confianza", terminoConfianza);
        REPORTES.put("termino-confianza-reincorporacion-base", terminoConfianzaReincorporacion);
        REPORTES.put("termino-interinato", terminoInterinato);

    }

    @Override
    public WordReporte obtenerReporte(String nombreReporte) {
        return REPORTES.get(nombreReporte);
    }

    @Override
    public boolean extisteReporte(String nombreReporte) {
        return REPORTES.containsKey(nombreReporte);
    }

}
