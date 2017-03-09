/*
 * 
 */
package mx.gob.saludtlax.rh.reportes.word;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mx.gob.saludtlax.rh.contrato.Contrato;
import mx.gob.saludtlax.rh.contrato.ContratoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento;
import mx.gob.saludtlax.rh.empleados.nombramientos.NombramientoInterinatoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.NombramientoDetalleDTO;
import mx.gob.saludtlax.rh.reporteslaborales.cambio.CambioAdscripcionDTO;
import mx.gob.saludtlax.rh.reporteslaborales.comision.ComisionOficialDTO;
import mx.gob.saludtlax.rh.reporteslaborales.reincorporacion.ReincorporacionBaseDTO;
import mx.gob.saludtlax.rh.reportes.Generador;
import mx.gob.saludtlax.rh.reporteslaborales.comision.ComisionEJB;
import mx.gob.saludtlax.rh.reporteslaborales.reservacion.ReservacionDTO;
import mx.gob.saludtlax.rh.reporteslaborales.termino.TerminoDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import mx.gob.saludtlax.rh.util.NumeroUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jboss.logging.Logger;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

/**
 * @author eduardo Mex
 *
 */
public class WordGenerador implements Generador {

    private static final Logger LOGGER = Logger.getLogger(WordGenerador.class.getName());
    private static final String CONTRATO_BEAN = "java:module/ContratoEJB";
    private static final String NOMBRAMIENTO_BEAN = "java:module/NombramientoEJB";
    private static final String COMISION_BEAN = "java:module/ComisionEJB";
    private static final String HOME = System.getProperty("user.home");
    private static final String RUTA = "plantillas/nombramiento/";
    private static String PLANTILLA = "";
    private XWPFDocument plantilla;
    private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';
    private static final Map<String, String> CAMPOS = new HashMap<String, String>();
    private static final DateFormat FORMATO_FECHA = DateFormat.getDateInstance(DateFormat.LONG);
    
    private static DateFormat formatFecha = DateFormat.getDateInstance(DateFormat.LONG);
    private static Integer idNombramientoGenerico = 0;
    private static Integer idClasificacion = 0;
    private static NombramientoDetalleDTO nombramientoDTO;
    private static NombramientoInterinatoDTO nombramientoInterinatoDTO;
    private static ComisionOficialDTO comisionOficialDTO;
    private static CambioAdscripcionDTO cambioAdscripcionDTO;
    private static ReincorporacionBaseDTO reincorporacionBaseDTO;
    private static ReservacionDTO reservacionDTO;
    private static TerminoDTO terminoDTO;

    @Override
    public byte[] obtenerReporte(Map<String, String> parametros) {

        AlmacenReporteWord almacen = new AlmacenReporteWord();
        String nombreReporte = parametros.get("REPORTE_NOMBRE");

        byte[] bytes = null;

        try {

            WordReporte reporte = almacen.obtenerReporte(nombreReporte);

            if (almacen.extisteReporte(nombreReporte)) {
                switch (nombreReporte) {
                    case "contrato-individual":

                        Integer idContratoEmpleado
                                = Integer.parseInt(parametros.get("ID_CONTRATO"));

                        ContratoDTO dto
                                = getContrato().obtenerContratoEmpleadoPorIdContrato(idContratoEmpleado);

                        WordprocessingMLPackage wordMLPackage
                                = WordprocessingMLPackage.load(reporte.getInputStream());

                        //TODO: Campos a combinar 
                        List<Map<DataFieldName, String>> listaRegitrosCombinar = new ArrayList<>();
                        Map<DataFieldName, String> camposCombinar = new HashMap<>();

                        camposCombinar.put(new DataFieldName("nombreTrabajador"),
                                dto.getNombreCompletoEmpleado());
                        camposCombinar.put(new DataFieldName("fechaInicio"), FORMATO_FECHA.format(dto.getFechaInicio()));
                        camposCombinar.put(new DataFieldName("fechaFin"),
                                FORMATO_FECHA.format(dto.getFechaFin()));
                        camposCombinar.put(new DataFieldName("fechaInicio2"),
                                FORMATO_FECHA.format(dto.getFechaInicio()));
                        camposCombinar.put(new DataFieldName("fechaFin2"), FORMATO_FECHA.format(dto.getFechaFin()));
                        camposCombinar.put(new DataFieldName("puestoGeneral"),
                                dto.getNombrePuestoGeneral());
                        camposCombinar.put(new DataFieldName("domicilioServicio"), dto.getDomicilioServicio());
                        camposCombinar.put(new DataFieldName("sueldoMensualNumero"), "$"
                                + NumeroUtil.formatBigDecimal(dto.getSueldoMensual()));
                        camposCombinar.put(new DataFieldName("sueldoMensualLetra"),
                                NumeroALetra.convertir(dto.getSueldoMensual()));
                        camposCombinar.put(new DataFieldName("sueldoMensualNumero2"), "$"
                                + NumeroUtil.formatBigDecimal(dto.getSueldoMensual()));
                        camposCombinar.put(new DataFieldName("sueldoMensualLetra2"),
                                NumeroALetra.convertir(dto.getSueldoMensual()));
                        camposCombinar.put(new DataFieldName("fechaInicio3"),
                                FORMATO_FECHA.format(dto.getFechaInicio()));
                        camposCombinar.put(new DataFieldName("fechaInicio4"),
                                FORMATO_FECHA.format(dto.getFechaInicio()));
                        camposCombinar.put(new DataFieldName("nombreTrabajadorFirma"), dto.getNombreCompletoEmpleado());

                        listaRegitrosCombinar.add(camposCombinar);

                        ByteArrayOutputStream documentoCombinado = new ByteArrayOutputStream();

                        MailMerger.setMERGEFIELDInOutput(MailMerger.OutputField.KEEP_MERGEFIELD);
                        WordprocessingMLPackage output
                                = MailMerger.getConsolidatedResultCrude(wordMLPackage,
                                        listaRegitrosCombinar, true);

                        output.save(documentoCombinado);
                        // output.save(new java.io.File(HOME + "/CONTRATO_INDIVIDUAL_DE_TRABAJO_POR_TIEMPO_DETERMINADO.docx"));

                        // bytes = output.getContentType().getBytes();
                        bytes = documentoCombinado.toByteArray();

                        break;

                    case "nombramiento-definitivo":

                        Integer idNombramiento
                                = Integer.parseInt(parametros.get("ID_NOMBRAMIENTO"));

                        NombramientoDetalleDTO nombramientoDetalleDTO = getNombramiento()
                                .obtenerNombramientoReportePorId(idNombramiento, null);

                        WordprocessingMLPackage wordMLPackageNombramiento
                                = WordprocessingMLPackage.load(reporte.getInputStream());

                        //TODO: Campos a combinar
                        List<Map<DataFieldName, String>> listaRegitrosCombinarNombramiento = new ArrayList<>();
                        Map<DataFieldName, String> camposCombinarNombramiento = new HashMap<>();

                        DateFormat formatoFechaNombramiento
                                = DateFormat.getDateInstance(DateFormat.LONG);

                        camposCombinarNombramiento.put(new DataFieldName("nombreEmpleado"),
                                nombramientoDetalleDTO.getNombreEmpleado());
                        camposCombinarNombramiento.put(new DataFieldName("rfc"),
                                nombramientoDetalleDTO.getRfc());
                        camposCombinarNombramiento.put(new DataFieldName("curp"), nombramientoDetalleDTO.getCurp());
                        camposCombinarNombramiento.put(new DataFieldName("edad"),
                                nombramientoDetalleDTO.getEdad());
                        camposCombinarNombramiento.put(new DataFieldName("nacionalidad"), nombramientoDetalleDTO.getNacionalidad());
                        camposCombinarNombramiento.put(new DataFieldName("sexo"),
                                nombramientoDetalleDTO.getSexo());
                        camposCombinarNombramiento.put(new DataFieldName("estadoCivil"), nombramientoDetalleDTO.getEstadoCivil());
                        camposCombinarNombramiento.put(new DataFieldName("domicilioEmpleado"),
                                nombramientoDetalleDTO.getDomicilioEmpleado());
                        camposCombinarNombramiento.put(new DataFieldName("clavePresupuestal"),
                                nombramientoDetalleDTO.getClavePresupuestal());
                        camposCombinarNombramiento.put(new DataFieldName("funcion"),
                                nombramientoDetalleDTO.getFuncion());
                        camposCombinarNombramiento.put(new DataFieldName("tipoNombramiento"),
                                nombramientoDetalleDTO.getTipoNombramiento());
                        camposCombinarNombramiento.put(new DataFieldName("jornadaTrabajo"),
                                nombramientoDetalleDTO.getJornadaTrabajo());
                        camposCombinarNombramiento.put(new DataFieldName("sueldo"), "$"
                                + NumeroUtil.formatBigDecimal(nombramientoDetalleDTO.getSueldo()));
                        camposCombinarNombramiento.put(new DataFieldName("lugarAdscripcion"),
                                nombramientoDetalleDTO.getLugarAdscripcion());
                        camposCombinarNombramiento.put(new DataFieldName("vigenciaFechaIngresoEmpleado"),
                                formatoFechaNombramiento.format(nombramientoDetalleDTO.
                                        getVigenciaFechaIngresoEmpleado()));

                        listaRegitrosCombinarNombramiento.add(camposCombinarNombramiento);

                        ByteArrayOutputStream documentoCombinadoNombramiento = new ByteArrayOutputStream();

                        MailMerger.setMERGEFIELDInOutput(MailMerger.OutputField.KEEP_MERGEFIELD);
                        WordprocessingMLPackage outputNombramiento
                                = MailMerger.getConsolidatedResultCrude(wordMLPackageNombramiento,
                                        listaRegitrosCombinarNombramiento, true);

                        outputNombramiento.save(documentoCombinadoNombramiento);

                        bytes = documentoCombinadoNombramiento.toByteArray();

                        break;

                    case "nombramiento-generico":

                        PLANTILLA = "NOMBRAMIENTO_GENERICO.docx";

                        idNombramientoGenerico
                                = Integer.parseInt(parametros.get("ID_NOMBRAMIENTO"));
                        idClasificacion
                                = Integer.parseInt(parametros.get("ID_CLASIFICACION"));

                        nombramientoDTO
                                = getNombramiento().obtenerNombramientoReportePorId(idNombramientoGenerico,
                                        idClasificacion);

                        cargarPlantilla();

                        formatFecha = DateFormat.getDateInstance(DateFormat.LONG);

                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                nombramientoDTO.getTextoPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "nombreTipoNombramiento" + SIGNO_CIERRE,
                                nombramientoDTO.getNombreTipoNombramiento());
                        CAMPOS.put(SIGNO_APERTURA
                                + "nombreEmpleado" + SIGNO_CIERRE, nombramientoDTO.getNombreEmpleado());
                        CAMPOS.put(SIGNO_APERTURA + "rfc" + SIGNO_CIERRE,
                                nombramientoDTO.getRfc());
                        CAMPOS.put(SIGNO_APERTURA + "curp"
                                + SIGNO_CIERRE, nombramientoDTO.getCurp());
                        CAMPOS.put(SIGNO_APERTURA
                                + "edad" + SIGNO_CIERRE, nombramientoDTO.getEdad());
                        CAMPOS.put(SIGNO_APERTURA + "nacionalidad" + SIGNO_CIERRE,
                                nombramientoDTO.getNacionalidad());
                        CAMPOS.put(SIGNO_APERTURA + "sexo"
                                + SIGNO_CIERRE, nombramientoDTO.getSexo());
                        CAMPOS.put(SIGNO_APERTURA
                                + "estadoCivil" + SIGNO_CIERRE, nombramientoDTO.getEstadoCivil());
                        CAMPOS.put(SIGNO_APERTURA + "domicilioEmpleado" + SIGNO_CIERRE,
                                nombramientoDTO.getDomicilioEmpleado());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePresupuestal" + SIGNO_CIERRE,
                                nombramientoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA
                                + "funcion" + SIGNO_CIERRE, nombramientoDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA + "tipoNombramiento" + SIGNO_CIERRE,
                                nombramientoDTO.getTipoNombramiento());
                        CAMPOS.put(SIGNO_APERTURA
                                + "jornadaTrabajo" + SIGNO_CIERRE, nombramientoDTO.getJornadaTrabajo());
                        CAMPOS.put(SIGNO_APERTURA + "horarioTrabajo" + SIGNO_CIERRE,
                                nombramientoDTO.getHorarioTrabajo());
                        CAMPOS.put(SIGNO_APERTURA
                                + "sueldo" + SIGNO_CIERRE, "$"
                                + NumeroUtil.formatBigDecimal(nombramientoDTO.getSueldo()));
                        CAMPOS.put(SIGNO_APERTURA + "lugarAdscripcion" + SIGNO_CIERRE,
                                nombramientoDTO.getLugarAdscripcion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "vigenciaFechaIngresoEmpleado" + SIGNO_CIERRE,
                                formatFecha.format(nombramientoDTO.getVigenciaFechaIngresoEmpleado()));
                        CAMPOS.put(SIGNO_APERTURA + "sustituye" + SIGNO_CIERRE,
                                nombramientoDTO.getSustituye());
                        CAMPOS.put(SIGNO_APERTURA
                                + "posicionDos" + SIGNO_CIERRE, nombramientoDTO.getTextoPosicionDos());
                        CAMPOS.put(SIGNO_APERTURA + "NOMBRESECRETARIO" + SIGNO_CIERRE,
                                nombramientoDTO.getNombreSecretario());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "nombramiento-formalizado-fase":

                        PLANTILLA = "NOMBRAMIENTO_FORMALIZADO_FASE.docx";

                        idNombramientoGenerico
                                = Integer.parseInt(parametros.get("ID_NOMBRAMIENTO"));
                        idClasificacion
                                = Integer.parseInt(parametros.get("ID_CLASIFICACION"));

                        nombramientoDTO = getNombramiento()
                                .obtenerNombramientoReporteFormalizaFaseIIPorId(idNombramientoGenerico,
                                        idClasificacion);

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                nombramientoDTO.getTextoPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "nombreTipoNombramiento" + SIGNO_CIERRE,
                                nombramientoDTO.getNombreTipoNombramiento());
                        CAMPOS.put(SIGNO_APERTURA
                                + "nombreEmpleado" + SIGNO_CIERRE, nombramientoDTO.getNombreEmpleado());
                        CAMPOS.put(SIGNO_APERTURA + "rfc" + SIGNO_CIERRE,
                                nombramientoDTO.getRfc());
                        CAMPOS.put(SIGNO_APERTURA + "curp"
                                + SIGNO_CIERRE, nombramientoDTO.getCurp());
                        CAMPOS.put(SIGNO_APERTURA
                                + "edad" + SIGNO_CIERRE, nombramientoDTO.getEdad()); //
                        CAMPOS.put(SIGNO_APERTURA + "nacionalidad"
                                + SIGNO_CIERRE,
                                nombramientoDTO.getNacionalidad());
                        CAMPOS.put(SIGNO_APERTURA + "sexo"
                                + SIGNO_CIERRE, nombramientoDTO.getSexo());
                        CAMPOS.put(SIGNO_APERTURA
                                + "estadoCivil" + SIGNO_CIERRE, nombramientoDTO.getEstadoCivil());
                        CAMPOS.put(SIGNO_APERTURA + "domicilioEmpleado" + SIGNO_CIERRE,
                                nombramientoDTO.getDomicilioEmpleado());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePresupuestal" + SIGNO_CIERRE,
                                nombramientoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA
                                + "tipoNombramiento" + SIGNO_CIERRE,
                                nombramientoDTO.getTipoNombramiento());
                        CAMPOS.put(SIGNO_APERTURA
                                + "funcion" + SIGNO_CIERRE, nombramientoDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA + "rama" + SIGNO_CIERRE,
                                nombramientoDTO.getRama());
                        CAMPOS.put(SIGNO_APERTURA + "jornadaTrabajo"
                                + SIGNO_CIERRE, nombramientoDTO.getJornadaTrabajo()); //
                        CAMPOS.put(SIGNO_APERTURA + "horarioTrabajo"
                                + SIGNO_CIERRE,
                                nombramientoDTO.getHorarioTrabajo());
                        CAMPOS.put(SIGNO_APERTURA
                                + "sueldoMensual" + SIGNO_CIERRE, "$"
                                + NumeroUtil.formatBigDecimal(nombramientoDTO.getSueldo()));
                        CAMPOS.put(SIGNO_APERTURA + "lugarAdscripcion" + SIGNO_CIERRE,
                                nombramientoDTO.getLugarAdscripcion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "fechaPosicionUno" + SIGNO_CIERRE,
                                formatFecha.format(nombramientoDTO.getFechaPosicionUno())); //
                        CAMPOS.put(SIGNO_APERTURA + "sustituye" + SIGNO_CIERRE, //
                                nombramientoDTO.getSustituye());
                        CAMPOS.put(SIGNO_APERTURA
                                + "posicionDos" + SIGNO_CIERRE, nombramientoDTO.getTextoPosicionDos());
                        CAMPOS.put(SIGNO_APERTURA + "nombreSecretario" + SIGNO_CIERRE,
                                nombramientoDTO.getNombreSecretario());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "nombramiento-interino":

                        PLANTILLA = "NOMBRAMIENTO_INTERINO.docx";

                        idNombramientoGenerico
                                = Integer.parseInt(parametros.get("ID_NOMBRAMIENTO"));
                        idClasificacion
                                = Integer.parseInt(parametros.get("ID_CLASIFICACION"));

                        nombramientoInterinatoDTO = getNombramiento()
                                .obtenerNombramientoReporteInterinato(idNombramientoGenerico,
                                        idClasificacion);

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "presenteNombre" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveUno" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getTextoPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA + "fechaNombramiento" + SIGNO_CIERRE,
                                formatFecha.format(nombramientoInterinatoDTO.getFechaNombramiento()));
                        CAMPOS.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePresupuestal" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA + "propietarioPlaza" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPropietarioPlaza());
                        CAMPOS.put(SIGNO_APERTURA + "tipoRecurso" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getTipoRecurso());
                        CAMPOS.put(SIGNO_APERTURA
                                + "tipoNombramiento" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getTipoNombramiento());
                        CAMPOS.put(SIGNO_APERTURA + "vigencia" + SIGNO_CIERRE,
                                FORMATO_FECHA.format(nombramientoInterinatoDTO.getVigencia()));
                        CAMPOS.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPosicionDos());
                        CAMPOS.put(SIGNO_APERTURA
                                + "nombreSecretario" + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getNombreSecretario());
                        CAMPOS.put(SIGNO_APERTURA + "posicionTres"
                                + SIGNO_CIERRE,
                                nombramientoInterinatoDTO.getPosicionTres());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "cambio-adscripcion":

                        PLANTILLA = "CAMBIO_ADSCRIPCION.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveUno" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA
                                + "fecha" + SIGNO_CIERRE, cambioAdscripcionDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "fechaCambio" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getFechaCambio());
                        CAMPOS.put(SIGNO_APERTURA
                                + "cambioAdscripcion" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getCambioAdscripcion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "funcion" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "turno" + SIGNO_CIERRE, cambioAdscripcionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "encargadoLabores" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getTurno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "secretarioSalud" + SIGNO_CIERRE,
                                cambioAdscripcionDTO.getSecretarioSalud());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "reincorporacion-base":

                        PLANTILLA = "REINCORPORACION_BASE.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveUno" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA
                                + "fecha" + SIGNO_CIERRE, reincorporacionBaseDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "fechaNombramiento" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getFechaNombramiento());
                        CAMPOS.put(SIGNO_APERTURA
                                + "funcion" + SIGNO_CIERRE, reincorporacionBaseDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA
                                + "directoraUnidad" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getDirectoraUnidad());
                        CAMPOS.put(SIGNO_APERTURA
                                + "directoraAdministracion" + SIGNO_CIERRE,
                                reincorporacionBaseDTO.getDirectoraAdministracion());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "reservacion-plaza-eleccion-popular":

                        PLANTILLA = "RESERVACION_PLAZA_ELECCION_POPULAR.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                reservacionDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, reservacionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                                reservacionDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE, reservacionDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                reservacionDTO.getPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA + "fecha"
                                + SIGNO_CIERRE, reservacionDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "fechaIngreso" + SIGNO_CIERRE,
                                FechaUtil.formatoFecha(reservacionDTO.getFechaIngreso()));
                        CAMPOS.put(SIGNO_APERTURA
                                + "claveOriginal" + SIGNO_CIERRE, reservacionDTO.getClaveOriginal());
                        CAMPOS.put(SIGNO_APERTURA + "denominacionAlta" + SIGNO_CIERRE,
                                reservacionDTO.getDenominacionAlta());
                        CAMPOS.put(SIGNO_APERTURA
                                + "adscripcionComision" + SIGNO_CIERRE,
                                reservacionDTO.getAdscripcionComision());
                        CAMPOS.put(SIGNO_APERTURA
                                + "fechaDesignacion" + SIGNO_CIERRE, reservacionDTO.getFechaDesignacion());
                        CAMPOS.put(SIGNO_APERTURA + "claveDesignada" + SIGNO_CIERRE,
                                reservacionDTO.getClaveDesignada());
                        CAMPOS.put(SIGNO_APERTURA
                                + "denominacion" + SIGNO_CIERRE, reservacionDTO.getDenominacion());
                        CAMPOS.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE,
                                reservacionDTO.getPosicionDos());
                        CAMPOS.put(SIGNO_APERTURA
                                + "directoraAdministracion" + SIGNO_CIERRE,
                                reservacionDTO.getDirectoraAdministracion());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "reservacion-plaza-confianza-otra-dependencia":

                        PLANTILLA = "RESERVACION_PLAZA_CONFIANZA_OTRA_DEPENDENCIA.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                reservacionDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, reservacionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                                reservacionDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE, reservacionDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "comunicado" + SIGNO_CIERRE,
                                reservacionDTO.getComunicado());
                        CAMPOS.put(SIGNO_APERTURA + "fecha"
                                + SIGNO_CIERRE, reservacionDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "fechaIngreso" + SIGNO_CIERRE,
                                FechaUtil.formatoFecha(reservacionDTO.getFechaIngreso()));
                        CAMPOS.put(SIGNO_APERTURA
                                + "claveOriginal" + SIGNO_CIERRE, reservacionDTO.getClaveOriginal());
                        CAMPOS.put(SIGNO_APERTURA + "denominacionAlta" + SIGNO_CIERRE,
                                reservacionDTO.getDenominacionAlta());
                        CAMPOS.put(SIGNO_APERTURA
                                + "adscripcion" + SIGNO_CIERRE, reservacionDTO.getAdscripcion());
                        CAMPOS.put(SIGNO_APERTURA + "fechaDesignacion" + SIGNO_CIERRE,
                                reservacionDTO.getFechaDesignacion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "denominacion" + SIGNO_CIERRE, reservacionDTO.getDenominacion());
                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                reservacionDTO.getPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "directoraAdministracion" + SIGNO_CIERRE,
                                reservacionDTO.getDirectoraAdministracion());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "reservacion-plaza-confianza":

                        PLANTILLA = "RESERVACION_PLAZA_CONFIANZA.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                reservacionDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, reservacionDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                                reservacionDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE, reservacionDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "comunicado" + SIGNO_CIERRE,
                                reservacionDTO.getComunicado());
                        CAMPOS.put(SIGNO_APERTURA + "fecha"
                                + SIGNO_CIERRE, reservacionDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "fechaIngreso" + SIGNO_CIERRE,
                                FechaUtil.formatoFecha(reservacionDTO.getFechaIngreso()));
                        CAMPOS.put(SIGNO_APERTURA
                                + "claveOriginal" + SIGNO_CIERRE, reservacionDTO.getClaveOriginal());
                        CAMPOS.put(SIGNO_APERTURA + "denominacionAlta" + SIGNO_CIERRE,
                                reservacionDTO.getDenominacionAlta());
                        CAMPOS.put(SIGNO_APERTURA
                                + "adscripcionComison" + SIGNO_CIERRE, reservacionDTO.getAdscripcionComision());
                        CAMPOS.put(SIGNO_APERTURA + "fechaDesignacion" + SIGNO_CIERRE,
                                reservacionDTO.getFechaDesignacion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "claveDesignada" + SIGNO_CIERRE, reservacionDTO.getClaveDesignada());
                        CAMPOS.put(SIGNO_APERTURA + "denominacion" + SIGNO_CIERRE,
                                reservacionDTO.getDenominacion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "adscripcion" + SIGNO_CIERRE, reservacionDTO.getAdscripcion());
                        CAMPOS.put(SIGNO_APERTURA + "posicionUno" + SIGNO_CIERRE,
                                reservacionDTO.getPosicionUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "directoraAdministracion" + SIGNO_CIERRE,
                                reservacionDTO.getDirectoraAdministracion());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "termino-comision-reincorporacion-adscripcion":

                        PLANTILLA = "TERMINO_COMISION_Y_REINCORPORACION_ADSCRIPCION.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                terminoDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, terminoDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "puesto" + SIGNO_CIERRE,
                                terminoDTO.getPuesto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveUno" + SIGNO_CIERRE, terminoDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveDos" + SIGNO_CIERRE,
                                terminoDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA
                                + "numeroOficio" + SIGNO_CIERRE, terminoDTO.getNumeroOficio());
                        CAMPOS.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE,
                                terminoDTO.getFecha());
                        CAMPOS.put(SIGNO_APERTURA + "encargado"
                                + SIGNO_CIERRE, terminoDTO.getEncargado());
                        CAMPOS.put(SIGNO_APERTURA + "fechaComision" + SIGNO_CIERRE,
                                terminoDTO.getFechaComision());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePesupuestal" + SIGNO_CIERRE, terminoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA + "asignacion" + SIGNO_CIERRE,
                                terminoDTO.getAsignacion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "encargadoAdministracion" + SIGNO_CIERRE,
                                terminoDTO.getEncargadoAdministracion());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "termino-confianza":

                        PLANTILLA = "TERMINO_CONFIANZA.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                terminoDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, terminoDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                                terminoDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE, terminoDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "fechaTermino" + SIGNO_CIERRE,
                                terminoDTO.getFechaTermino());
                        CAMPOS.put(SIGNO_APERTURA + "funcion"
                                + SIGNO_CIERRE, terminoDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePesupuestal" + SIGNO_CIERRE, terminoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA + "secretaroioSalud" + SIGNO_CIERRE,
                                terminoDTO.getSecretarioSalud());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "termino-confianza-reincorporacion-base":

                        PLANTILLA = "TERMINO_CONFIANZA_Y_REINCORPORACION_BASE.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                terminoDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteNombre" + SIGNO_CIERRE, terminoDTO.getPresenteNombre());
                        CAMPOS.put(SIGNO_APERTURA + "presenteClaveUno" + SIGNO_CIERRE,
                                terminoDTO.getPresenteClaveUno());
                        CAMPOS.put(SIGNO_APERTURA
                                + "presenteClaveDos" + SIGNO_CIERRE, terminoDTO.getPresenteClaveDos());
                        CAMPOS.put(SIGNO_APERTURA + "fechaTermino" + SIGNO_CIERRE,
                                terminoDTO.getFechaTermino());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePesupuestal" + SIGNO_CIERRE, terminoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA + "fechaPlaza" + SIGNO_CIERRE,
                                terminoDTO.getFechaPlaza());
                        CAMPOS.put(SIGNO_APERTURA + "nuevaClave"
                                + SIGNO_CIERRE, terminoDTO.getNuevaClave());
                        CAMPOS.put(SIGNO_APERTURA + "jefe" + SIGNO_CIERRE,
                                terminoDTO.getJefe());
                        CAMPOS.put(SIGNO_APERTURA
                                + "secretarioSalud" + SIGNO_CIERRE, terminoDTO.getSecretarioSalud());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    case "termino-interinato":

                        PLANTILLA = "TERMINO_INTERINATO.docx";

                        cargarPlantilla();

                        CAMPOS.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,
                                terminoDTO.getAsunto());
                        CAMPOS.put(SIGNO_APERTURA
                                + "posicionDos" + SIGNO_CIERRE, terminoDTO.getPosicionDos());
                        CAMPOS.put(SIGNO_APERTURA + "posicionTres" + SIGNO_CIERRE,
                                terminoDTO.getPosicionTres());
                        CAMPOS.put(SIGNO_APERTURA
                                + "posicionCuatro" + SIGNO_CIERRE, terminoDTO.getPosicionCuatro());
                        CAMPOS.put(SIGNO_APERTURA + "reincorporacionTitular" + SIGNO_CIERRE,
                                terminoDTO.getReincorporacionTitular());
                        CAMPOS.put(SIGNO_APERTURA
                                + "fechaTermino" + SIGNO_CIERRE, terminoDTO.getFechaTermino());
                        CAMPOS.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE,
                                terminoDTO.getFuncion());
                        CAMPOS.put(SIGNO_APERTURA
                                + "clavePresupuestal" + SIGNO_CIERRE, terminoDTO.getClavePresupuestal());
                        CAMPOS.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE,
                                terminoDTO.getSecretarioSalud());

                        for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
                            remplazarCampos(parrafo, CAMPOS);
                        }

                        bytes = escribirArchivo().toByteArray();

                        break;

                    default:
                        LOGGER.error("Error al seleccionar el documento...");
                        break;
                }
            }
        } catch (NullPointerException nullPointerException) {
            LOGGER.errorv("Mensaje crítico... {0}", nullPointerException);
        } catch (Docx4JException docx4jException) {
            LOGGER.errorv("Ocurrio un error al generar el documento: {0}", 
                    docx4jException.getMessage());
        } catch (IOException ex) {
            LOGGER.errorv("Ocurrio un error al generar el documento: {0}", ex);
        }

        return bytes;
    }

    /**
     * Interface del contrato
     *
     * @return
     */
    private Contrato getContrato() {
        try {
            Context initContext = new InitialContext();
            Contrato contratoEmpleado = (Contrato) initContext.lookup(CONTRATO_BEAN);

            return contratoEmpleado;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", CONTRATO_BEAN, ex.getCause());
            return null;
        }
    }

    /**
     * Interface del nombramiento
     *
     * @return
     */
    private Nombramiento getNombramiento() {
        try {
            Context initContext = new InitialContext();
            Nombramiento nombramiento = (Nombramiento) initContext.lookup(NOMBRAMIENTO_BEAN);

            return nombramiento;
        } catch (NamingException ex) {
            LOGGER.errorv("Error al buscar el bean: {0}\n{1}", CONTRATO_BEAN, ex.getCause());
            return null;
        }
    }

 
    /**
     *
     * @throws IOException
     */
    private void cargarPlantilla() throws IOException {
        InputStream inputStream
                = getClass().getClassLoader().getResourceAsStream(RUTA
                        + PLANTILLA);

        plantilla = new XWPFDocument(inputStream);
    }

    /**
     * @return @throws IOException
     */
    private ByteArrayOutputStream escribirArchivo() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        plantilla.write(out);
        return out;
    }

}
