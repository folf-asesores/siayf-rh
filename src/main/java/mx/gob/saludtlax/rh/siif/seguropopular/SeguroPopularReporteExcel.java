/*
 * SeguroPopularReporteExcel.java
 * Creado el 09/Dec/2016 6:32:39 PM
 *
 */
package mx.gob.saludtlax.rh.siif.seguropopular;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;

/**
 * Esta clase se encarga de trabajar el archivo de Excel (plantilla) para
 * generar el reporte de seguro popular.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SeguroPopularReporteExcel implements Serializable {

    private static final long serialVersionUID = 5229284594367895774L;
    private static final Logger LOGGER = Logger.getLogger(SeguroPopularReporteExcel.class.getName());
    /** La fila desde la cual se iniciara el vaciado de la información. */
    private static final int FILA_INICIO_DETALLE = 1;
    // Las columnas de la plantilla.
    private static final int NUMERO_CONSECUTIVO                       = 0;
    private static final int MES                                      = 1;
    private static final int ESTADO                                   = 2;
    private static final int TIPO_CENTRO_SALUD_HOSPITAL               = 3;
    private static final int CLUES                                    = 4;
    private static final int NOMBRE_LA_UNIDAD                         = 5;
    private static final int AREA_ADSCRIPCION                         = 6;
    private static final int PUESTO                                   = 7;
    private static final int CLAVE_PUESTO                             = 8;
    private static final int SERVICIO                                 = 9;
    private static final int RAMA                                     = 10;
    private static final int NOMBRE                                   = 11;
    private static final int RFC                                      = 12;
    private static final int TURNO                                    = 13;
    private static final int FECHA_INGRESO                            = 14;
    private static final int C07_SUELDO_BASE                          = 15;
    private static final int C30_COMPENSACION_RIESGO                  = 16;
    private static final int C32_PRIMA_DOMINICAL                      = 17;
    private static final int C38_DESPENSA                             = 18;
    private static final int C42_ASIGNACION_BRUTA                     = 19;
    private static final int C44_PREVISION_SOCIAL_MULTIPLE            = 20;
    private static final int C45_AYUDA_ANTEOJOS                       = 21;
    private static final int C46_AYUDA_SERVICIO                       = 22;
    private static final int C55_AYUDA_GASTOS_ACTUALIZACION           = 23;
    private static final int CQQ_PRIMA_QUINQUENAL                     = 24;
    private static final int PERCEPCION_TOTAL                         = 25;
    private static final int C01_IMPUESTO_SOBRE_RENTA                 = 26;
    private static final int C02_SR_PENSION_JUBILACION                = 27;
    private static final int C02_SI_SERVICIO_MEDICO                   = 28;
    private static final int C02_SS_SERVICIO_SOCIAL_CULTURAL          = 29;
    private static final int C03_PRESTAMO_PERSONAL                    = 30;
    private static final int C04_SS_SERVICIO_MEDICO_MATERNIDAD        = 31;
    private static final int C04_SP_SEGURO_INVALIDEZ_VIDA             = 32;
    private static final int C09_SEGURO_DANYO_PRESTAMO_AVALADO_ISSSTE = 33;
    private static final int C17_RETARDO_FALTA                        = 34;
    private static final int C21_FONDO_AHORRO_CAPITALIZABLE           = 35;
    private static final int C29_RESPONSABILIDAD                      = 36;
    private static final int C34_SEGURO_RIESGO_PROFESIONAL            = 37;
    private static final int C46_INBURSA                              = 38;
    private static final int C46_SEGURO_ARGOS                         = 39;
    private static final int C46_ETESA                                = 40;
    private static final int C46_LA_TENDA                             = 41;
    private static final int C46_PS_PUBLISEG                          = 42;
    private static final int C46_SEGUROS_GNP                          = 43;
    private static final int C46_AUDITORIA_OPERACION_CREDIEMPLEADO    = 44;
    private static final int C46_CREDITO_EXPRES                       = 45;
    private static final int C51_SEGURO_VIDA_INSTITUCIONAL            = 46;
    private static final int C56_PRESTAMO_HIPOTECARIO                 = 47;
    private static final int C57_SEGURO_VIDA_ADICIONAL_METLIFE        = 48;
    private static final int C58_CUOTA_SINDICAL                       = 49;
    private static final int C62_PENSION_ALIMENTICIA                  = 50;
    private static final int C64_FONDO_VIVIENDA                       = 51;
    private static final int C70_FONDO_AHORRO_DEFUNCION               = 52;
    private static final int C77_SEGURO_RETIRO_METLIFE                = 53;
    private static final int C97_DESCUENTO_PROMOBIEN                  = 54;
    private static final int CAS_AHORRO_SOLIDARIO                     = 55;
    private static final int DEDUCCION_TOTAL                          = 56;
    private static final int PERCEPCION_NETA                          = 57;
    /** El nombre de la hoja donde se encuentra el detalle. */
    private static final String NOMBRE_HOJA = "SeguroPopular";
    /** Objeto que representa el stream del archivo de Excel. */
    private InputStream is;
    /** Objeto que representa la plantilla de Excel en memoria. */
    private Workbook libro;
    /** Objeto que representa la hoja de Excel sobre la cual se trabajará. */
    private Sheet hoja;

    /**
     * Este método carga la plantilla según el anexo, llama al método
     * {@link #llenarDetalles(List) llenarDetalles} para llenar el archivo de
     * Excel con la información que recibe por parametro y finalmente devuelve
     * la plantilla llena como arreglo de bytes.
     *
     * @param detalles la información para llenar el archivo de Excel.
     * @return un arreglo de bytes que representan al archivo de Excel.
     */
    protected byte[] generar(List<SeguroPopularReporteDTO> detalles) {
        try {
            cargarPlantilla();
            llenarDetalles(detalles);
            llenarTotales(detalles);
            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Error de lectura/escritura", SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }
    }

    /**
     * Carga de la plantilla y prepara el libro y la hoja para que se puedan
     * usar.
     *
     * @throws IOException en caso de que no se encuentre el archivo o este
     * dañado lanzara esta excepción.
     */
    private void cargarPlantilla() throws IOException {
        is = SeguroPopularReporteExcel.class.getClassLoader().getResourceAsStream("plantillas/siif/plantilla--seguro-popular.xlsx");
        libro = new XSSFWorkbook(is);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    /**
     * Se encarga de llenar la lista de los detalles.
     *
     * @param detalles los datos que representa los detalles del reporte.
     */
    private void llenarDetalles(List<SeguroPopularReporteDTO> detalles) {
        int iteradorFila = FILA_INICIO_DETALLE;

        for (SeguroPopularReporteDTO detalle : detalles) {
            Row fila = hoja.createRow(iteradorFila);

            for(int iteradorColumna = NUMERO_CONSECUTIVO; iteradorColumna <= PERCEPCION_NETA; iteradorColumna++) {
                Cell celda = fila.createCell(iteradorColumna);
                switch(iteradorColumna) {
                    case NUMERO_CONSECUTIVO:
                        celda.setCellValue(detalle.getNumeroConsecutivo());
                        break;
                    case MES:
                        celda.setCellValue(detalle.getMes());
                        break;
                    case ESTADO:
                        celda.setCellValue(detalle.getEstado());
                        break;
                    case TIPO_CENTRO_SALUD_HOSPITAL:
                        celda.setCellValue(detalle.getTipoCentroSaludHospital());
                        break;
                    case CLUES:
                        celda.setCellValue(detalle.getClues());
                        break;
                    case NOMBRE_LA_UNIDAD:
                        celda.setCellValue(detalle.getNombreUnidad());
                        break;
                    case AREA_ADSCRIPCION:
                        celda.setCellValue(detalle.getAreaAdscripcion());
                        break;
                    case PUESTO:
                        celda.setCellValue(detalle.getPuesto());
                        break;
                    case CLAVE_PUESTO:
                        celda.setCellValue(detalle.getClavePuesto());
                        break;
                    case SERVICIO:
                        celda.setCellValue(detalle.getServicio());
                        break;
                    case RAMA:
                        celda.setCellValue(detalle.getRama());
                        break;
                    case NOMBRE:
                        celda.setCellValue(detalle.getNombre());
                        break;
                    case RFC:
                        celda.setCellValue(detalle.getRfc());
                        break;
                    case TURNO:
                        celda.setCellValue(detalle.getTurno());
                        break;
                    case FECHA_INGRESO:
                        celda.setCellValue(detalle.getFechaIngreso());
                        break;
                    case C07_SUELDO_BASE:
                        if (detalle.getC07SueldoBase()!=null) {
                            celda.setCellValue(detalle.getC07SueldoBase().doubleValue());
                        } else{
                            celda.setCellValue(0.0);
                        }
                        break;
                    case C30_COMPENSACION_RIESGO:
                        celda.setCellValue(detalle.getC30CompensacionRiesgo().doubleValue());
                        break;
                    case C32_PRIMA_DOMINICAL:
                        celda.setCellValue(detalle.getC32PrimaDominical().doubleValue());
                        break;
                    case C38_DESPENSA:
                        celda.setCellValue(detalle.getC38Despensa().doubleValue());
                        break;
                    case C42_ASIGNACION_BRUTA:
                        celda.setCellValue(detalle.getC42AsignacionBruta().doubleValue());
                        break;
                    case C44_PREVISION_SOCIAL_MULTIPLE:
                        celda.setCellValue(detalle.getC44PrevisionSocialMultiple().doubleValue());
                        break;
                    case C45_AYUDA_ANTEOJOS:
                        celda.setCellValue(detalle.getC45AyudaAnteojo().doubleValue());
                        break;
                    case C46_AYUDA_SERVICIO:
                        celda.setCellValue(detalle.getC46AyudaServicio().doubleValue());
                        break;
                    case C55_AYUDA_GASTOS_ACTUALIZACION:
                        celda.setCellValue(detalle.getC55AyudaParaGastosActualizacion().doubleValue());
                        break;
                    case CQQ_PRIMA_QUINQUENAL:
                        celda.setCellValue(detalle.getCqqPrimaQuinquenal().doubleValue());
                        break;
                    case PERCEPCION_TOTAL:
                        if (detalle.getPercepcionTotal()!=null) {
                            celda.setCellValue(detalle.getPercepcionTotal().doubleValue());
                        } else{
                            celda.setCellValue(0.0);
                        }
                        break;
                    case C01_IMPUESTO_SOBRE_RENTA:
                        celda.setCellValue(detalle.getC01ImpuestoSobreRenta().doubleValue());
                        break;
                    case C02_SR_PENSION_JUBILACION:
                        celda.setCellValue(detalle.getC02SrPensionJubilacion() .doubleValue());
                        break;
                    case C02_SI_SERVICIO_MEDICO:
                        celda.setCellValue(detalle.getC02SiServicioMedico().doubleValue());
                        break;
                    case C02_SS_SERVICIO_SOCIAL_CULTURAL:
                        celda.setCellValue(detalle.getC02SsServicioSocialCultural().doubleValue());
                        break;
                    case C03_PRESTAMO_PERSONAL:
                        celda.setCellValue(detalle.getC03PrestamoPersonal().doubleValue());
                        break;
                    case C04_SS_SERVICIO_MEDICO_MATERNIDAD:
                        celda.setCellValue(detalle.getC04SsServicioMedicoMaternidad().doubleValue());
                        break;
                    case C04_SP_SEGURO_INVALIDEZ_VIDA:
                        celda.setCellValue(detalle.getC04SpSeguroInvalidezVida().doubleValue());
                        break;
                    case C09_SEGURO_DANYO_PRESTAMO_AVALADO_ISSSTE:
                        celda.setCellValue(detalle.getC09SeguroDanyosPrestamoAvaladoIssste().doubleValue());
                        break;
                    case C17_RETARDO_FALTA:
                        celda.setCellValue(detalle.getC17RetardoFalta().doubleValue());
                        break;
                    case C21_FONDO_AHORRO_CAPITALIZABLE:
                        celda.setCellValue(detalle.getC21FondoAhorroCapitalizable().doubleValue());
                        break;
                    case C29_RESPONSABILIDAD:
                        celda.setCellValue(detalle.getC29Responsabilidad().doubleValue());
                        break;
                    case C34_SEGURO_RIESGO_PROFESIONAL:
                        celda.setCellValue(detalle.getC34SeguroRiesgoProfesional().doubleValue());
                        break;
                    case C46_INBURSA:
                        celda.setCellValue(detalle.getC46Inbursa().doubleValue());
                        break;
                    case C46_SEGURO_ARGOS:
                        celda.setCellValue(detalle.getC46SeguroArgos().doubleValue());
                        break;
                    case C46_ETESA:
                        celda.setCellValue(detalle.getC46Etesa().doubleValue());
                        break;
                    case C46_LA_TENDA:
                        celda.setCellValue(detalle.getC46LaTenda().doubleValue());
                        break;
                    case C46_PS_PUBLISEG:
                        celda.setCellValue(detalle.getC46PsPubliseg().doubleValue());
                        break;
                    case C46_SEGUROS_GNP:
                        celda.setCellValue(detalle.getC46SeguroGnp().doubleValue());
                        break;
                    case C46_AUDITORIA_OPERACION_CREDIEMPLEADO:
                        celda.setCellValue(detalle.getC46AuditoriaOperacionCrediempleado().doubleValue());
                        break;
                    case C46_CREDITO_EXPRES:
                        celda.setCellValue(detalle.getC46CreditoExpres().doubleValue());
                        break;
                    case C51_SEGURO_VIDA_INSTITUCIONAL:
                        celda.setCellValue(detalle.getC51SeguroVidaInstitucional().doubleValue());
                        break;
                    case C56_PRESTAMO_HIPOTECARIO:
                        celda.setCellValue(detalle.getC56PrestamoHipotecario().doubleValue());
                        break;
                    case C57_SEGURO_VIDA_ADICIONAL_METLIFE:
                        celda.setCellValue(detalle.getC57SeguroDeVidaAdicionalMetlife().doubleValue());
                        break;
                    case C58_CUOTA_SINDICAL:
                        celda.setCellValue(detalle.getC58CuotaSindical().doubleValue());
                        break;
                    case C62_PENSION_ALIMENTICIA:
                        celda.setCellValue(detalle.getC62PensionAlimenticia().doubleValue());
                        break;
                    case C64_FONDO_VIVIENDA:
                        celda.setCellValue(detalle.getC64FondoVivienda().doubleValue());
                        break;
                    case C70_FONDO_AHORRO_DEFUNCION:
                        celda.setCellValue(detalle.getC70FondoAhorroDefuncion().doubleValue());
                        break;
                    case C77_SEGURO_RETIRO_METLIFE:
                        celda.setCellValue(detalle.getC77SeguroRetiroMetlife().doubleValue());
                        break;
                    case C97_DESCUENTO_PROMOBIEN:
                        celda.setCellValue(detalle.getC97DescuentoPromobien().doubleValue());
                        break;
                    case CAS_AHORRO_SOLIDARIO:
                        celda.setCellValue(detalle.getCasAhorroSolidario().doubleValue());
                        break;
                    case DEDUCCION_TOTAL:
                        if (detalle.getDeduccionTotal()!=null) {
                            celda.setCellValue(detalle.getDeduccionTotal().doubleValue());
                        } else{
                            celda.setCellValue(0.0);
                        }
                        break;
                    case PERCEPCION_NETA:
                        if (detalle.getPercepcionNeta()!=null) {
                            celda.setCellValue(detalle.getPercepcionNeta().doubleValue());
                        } else{
                            celda.setCellValue(0.0);
                        }
                        break;
                    default:
                        // No hará nada.
                        break;
                }
            }

            iteradorFila++;
            hoja.shiftRows(iteradorFila, iteradorFila + 1, 1);
        }
    }

    private void llenarTotales(List<SeguroPopularReporteDTO> detalles) {
        BigDecimal c07SueldoBaseTotal = BigDecimal.ZERO;
        BigDecimal c30CompensacionRiesgoTotal = BigDecimal.ZERO;
        BigDecimal c32PrimaDominicalTotal = BigDecimal.ZERO;
        BigDecimal c38DespensaTotal = BigDecimal.ZERO;
        BigDecimal c42AsignacionBrutaTotal = BigDecimal.ZERO;
        BigDecimal c44PrevisionSocialMultipleTotal = BigDecimal.ZERO;
        BigDecimal c45AyudaAnteojoTotal = BigDecimal.ZERO;
        BigDecimal c46AyudaServicioTotal = BigDecimal.ZERO;
        BigDecimal c55AyudaParaGastosActualizacionTotal = BigDecimal.ZERO;
        BigDecimal cqqPrimaQuinquenalTotal = BigDecimal.ZERO;
        BigDecimal percepcionGranTotal = BigDecimal.ZERO;
        BigDecimal c01ImpuestoSobreRentaTotal = BigDecimal.ZERO;
        BigDecimal c02SrPensionJubilacionTotal = BigDecimal.ZERO;
        BigDecimal c02SiServicioMedicoTotal = BigDecimal.ZERO;
        BigDecimal c02SsServicioSocialCulturalTotal = BigDecimal.ZERO;
        BigDecimal c03PrestamoPersonalTotal = BigDecimal.ZERO;
        BigDecimal c04SsServicioMedicoMaternidadTotal = BigDecimal.ZERO;
        BigDecimal c04SpSeguroInvalidezVidaTotal = BigDecimal.ZERO;
        BigDecimal c09SeguroDanyoPrestamoAvaladoIsssteTotal = BigDecimal.ZERO;
        BigDecimal c17RetardoFaltaTotal = BigDecimal.ZERO;
        BigDecimal c21FondoAhorroCapitalizableTotal = BigDecimal.ZERO;
        BigDecimal c29ResponsabilidadTotal = BigDecimal.ZERO;
        BigDecimal c34SeguroRiesgoProfesionalTotal = BigDecimal.ZERO;
        BigDecimal c46InbursaTotal = BigDecimal.ZERO;
        BigDecimal c46SeguroArgosTotal = BigDecimal.ZERO;
        BigDecimal c46EtesaTotal = BigDecimal.ZERO;
        BigDecimal c46LaTendaTotal = BigDecimal.ZERO;
        BigDecimal c46PsPublisegTotal = BigDecimal.ZERO;
        BigDecimal c46SeguroGnpTotal = BigDecimal.ZERO;
        BigDecimal c46AuditoriaOperacionCrediempleadoTotal = BigDecimal.ZERO;
        BigDecimal c46CreditoExpresTotal = BigDecimal.ZERO;
        BigDecimal c51SeguroVidaInstitucionalTotal = BigDecimal.ZERO;
        BigDecimal c56PrestamoHipotecarioTotal = BigDecimal.ZERO;
        BigDecimal c57SeguroDeVidaAdicionalMetlifeTotal = BigDecimal.ZERO;
        BigDecimal c58CuotaSindicalTotal = BigDecimal.ZERO;
        BigDecimal c62PensionAlimenticiaTotal = BigDecimal.ZERO;
        BigDecimal c64FondoViviendaTotal = BigDecimal.ZERO;
        BigDecimal c70FondoAhorroDefuncionTotal = BigDecimal.ZERO;
        BigDecimal c77SeguroRetiroMetlifeTotal = BigDecimal.ZERO;
        BigDecimal c97DescuentoPromobienTotal = BigDecimal.ZERO;
        BigDecimal casAhorroSolidarioTotal = BigDecimal.ZERO;
        BigDecimal deduccionGranTotal = BigDecimal.ZERO;
        BigDecimal percepcionNetaTotal = BigDecimal.ZERO;
        BigDecimal cero = BigDecimal.ZERO;

        for (SeguroPopularReporteDTO detalle : detalles) {
            for(int iteradorColumna = C07_SUELDO_BASE; iteradorColumna <= PERCEPCION_NETA; iteradorColumna++) {
                switch(iteradorColumna) {
                case C07_SUELDO_BASE:
                        if (detalle.getC07SueldoBase() != null) {
                            c07SueldoBaseTotal = c07SueldoBaseTotal.add(detalle.getC07SueldoBase());
                        } else {
                            c07SueldoBaseTotal = c07SueldoBaseTotal.add(cero);
                        }
                    break;
                    case C30_COMPENSACION_RIESGO:
                        c30CompensacionRiesgoTotal = c30CompensacionRiesgoTotal.add(detalle.getC30CompensacionRiesgo());
                        break;
                    case C32_PRIMA_DOMINICAL:
                        c32PrimaDominicalTotal = c32PrimaDominicalTotal.add(detalle.getC32PrimaDominical());
                        break;
                    case C38_DESPENSA:
                        c38DespensaTotal = c38DespensaTotal.add(detalle.getC38Despensa());
                        break;
                    case C42_ASIGNACION_BRUTA:
                        c42AsignacionBrutaTotal = c42AsignacionBrutaTotal.add(detalle.getC42AsignacionBruta());
                        break;
                    case C44_PREVISION_SOCIAL_MULTIPLE:
                        c44PrevisionSocialMultipleTotal = c44PrevisionSocialMultipleTotal.add(detalle.getC44PrevisionSocialMultiple());
                        break;
                    case C45_AYUDA_ANTEOJOS:
                        c45AyudaAnteojoTotal = c45AyudaAnteojoTotal.add(detalle.getC45AyudaAnteojo());
                        break;
                    case C46_AYUDA_SERVICIO:
                        c46AyudaServicioTotal = c46AyudaServicioTotal.add(detalle.getC46AyudaServicio());
                        break;
                    case C55_AYUDA_GASTOS_ACTUALIZACION:
                        c55AyudaParaGastosActualizacionTotal = c55AyudaParaGastosActualizacionTotal .add(detalle.getC55AyudaParaGastosActualizacion());
                        break;
                    case CQQ_PRIMA_QUINQUENAL:
                        cqqPrimaQuinquenalTotal = cqqPrimaQuinquenalTotal.add(detalle.getCqqPrimaQuinquenal());
                        break;
                    case PERCEPCION_TOTAL:
                        percepcionGranTotal = percepcionGranTotal.add(detalle.getPercepcionTotal());
                        break;
                    case C01_IMPUESTO_SOBRE_RENTA:
                        c01ImpuestoSobreRentaTotal = c01ImpuestoSobreRentaTotal.add(detalle.getC01ImpuestoSobreRenta());
                        break;
                    case C02_SR_PENSION_JUBILACION:
                        c02SrPensionJubilacionTotal = c02SrPensionJubilacionTotal.add(detalle.getC02SrPensionJubilacion());
                        break;
                    case C02_SI_SERVICIO_MEDICO:
                        c02SiServicioMedicoTotal = c02SiServicioMedicoTotal.add(detalle.getC02SiServicioMedico());
                        break;
                    case C02_SS_SERVICIO_SOCIAL_CULTURAL:
                        c02SsServicioSocialCulturalTotal = c02SsServicioSocialCulturalTotal.add(detalle.getC02SsServicioSocialCultural());
                        break;
                    case C03_PRESTAMO_PERSONAL:
                        c03PrestamoPersonalTotal = c03PrestamoPersonalTotal.add(detalle.getC03PrestamoPersonal());
                        break;
                    case C04_SS_SERVICIO_MEDICO_MATERNIDAD:
                        c04SsServicioMedicoMaternidadTotal = c04SsServicioMedicoMaternidadTotal.add(detalle.getC04SsServicioMedicoMaternidad());
                        break;
                    case C04_SP_SEGURO_INVALIDEZ_VIDA:
                        c04SpSeguroInvalidezVidaTotal = c04SpSeguroInvalidezVidaTotal.add(detalle.getC04SpSeguroInvalidezVida());
                        break;
                    case C09_SEGURO_DANYO_PRESTAMO_AVALADO_ISSSTE:
                        c09SeguroDanyoPrestamoAvaladoIsssteTotal = c09SeguroDanyoPrestamoAvaladoIsssteTotal.add(detalle.getC09SeguroDanyosPrestamoAvaladoIssste());
                        break;
                    case C17_RETARDO_FALTA:
                        c17RetardoFaltaTotal = c17RetardoFaltaTotal.add(detalle.getC17RetardoFalta());
                        break;
                    case C21_FONDO_AHORRO_CAPITALIZABLE:
                        c21FondoAhorroCapitalizableTotal = c21FondoAhorroCapitalizableTotal.add(detalle.getC21FondoAhorroCapitalizable());
                        break;
                    case C29_RESPONSABILIDAD:
                        c29ResponsabilidadTotal = c29ResponsabilidadTotal.add(detalle.getC29Responsabilidad());
                        break;
                    case C34_SEGURO_RIESGO_PROFESIONAL:
                        c34SeguroRiesgoProfesionalTotal = c34SeguroRiesgoProfesionalTotal.add(detalle.getC34SeguroRiesgoProfesional());
                        break;
                    case C46_INBURSA:
                        c46InbursaTotal = c46InbursaTotal.add(detalle.getC46Inbursa());
                        break;
                    case C46_SEGURO_ARGOS:
                        c46SeguroArgosTotal = c46SeguroArgosTotal.add(detalle.getC46SeguroArgos());
                        break;
                    case C46_ETESA:
                        c46EtesaTotal = c46EtesaTotal.add(detalle.getC46Etesa());
                        break;
                    case C46_LA_TENDA:
                        c46LaTendaTotal= c46LaTendaTotal.add(detalle.getC46LaTenda());
                        break;
                    case C46_PS_PUBLISEG:
                        c46PsPublisegTotal = c46PsPublisegTotal.add(detalle.getC46PsPubliseg());
                        break;
                    case C46_SEGUROS_GNP:
                        c46SeguroGnpTotal = c46SeguroGnpTotal.add(detalle.getC46SeguroGnp());
                        break;
                    case C46_AUDITORIA_OPERACION_CREDIEMPLEADO:
                        c46AuditoriaOperacionCrediempleadoTotal = c46AuditoriaOperacionCrediempleadoTotal.add(detalle.getC46AuditoriaOperacionCrediempleado());
                        break;
                    case C46_CREDITO_EXPRES:
                        c46CreditoExpresTotal = c46CreditoExpresTotal.add(detalle.getC46CreditoExpres());
                        break;
                    case C51_SEGURO_VIDA_INSTITUCIONAL:
                        c51SeguroVidaInstitucionalTotal = c51SeguroVidaInstitucionalTotal.add(detalle.getC51SeguroVidaInstitucional());
                        break;
                    case C56_PRESTAMO_HIPOTECARIO:
                        c56PrestamoHipotecarioTotal = c56PrestamoHipotecarioTotal.add(detalle.getC56PrestamoHipotecario());
                        break;
                    case C57_SEGURO_VIDA_ADICIONAL_METLIFE:
                        c57SeguroDeVidaAdicionalMetlifeTotal = c57SeguroDeVidaAdicionalMetlifeTotal.add(detalle.getC57SeguroDeVidaAdicionalMetlife());
                        break;
                    case C58_CUOTA_SINDICAL:
                        c58CuotaSindicalTotal = c58CuotaSindicalTotal.add(detalle.getC58CuotaSindical());
                        break;
                    case C62_PENSION_ALIMENTICIA:
                        c62PensionAlimenticiaTotal = c62PensionAlimenticiaTotal.add(detalle.getC62PensionAlimenticia());
                        break;
                    case C64_FONDO_VIVIENDA:
                        c64FondoViviendaTotal = c64FondoViviendaTotal.add(detalle.getC64FondoVivienda());
                        break;
                    case C70_FONDO_AHORRO_DEFUNCION:
                        c70FondoAhorroDefuncionTotal = c70FondoAhorroDefuncionTotal.add(detalle.getC70FondoAhorroDefuncion());
                        break;
                    case C77_SEGURO_RETIRO_METLIFE:
                        c77SeguroRetiroMetlifeTotal = c77SeguroRetiroMetlifeTotal.add(detalle.getC77SeguroRetiroMetlife());
                        break;
                    case C97_DESCUENTO_PROMOBIEN:
                        c97DescuentoPromobienTotal = c97DescuentoPromobienTotal.add(detalle.getC97DescuentoPromobien());
                        break;
                    case CAS_AHORRO_SOLIDARIO:
                        casAhorroSolidarioTotal = casAhorroSolidarioTotal.add(detalle.getCasAhorroSolidario());
                        break;
                    case DEDUCCION_TOTAL:
                        deduccionGranTotal = deduccionGranTotal.add(detalle.getDeduccionTotal());
                        break;
                    case PERCEPCION_NETA:
                        percepcionNetaTotal = percepcionNetaTotal.add(detalle.getPercepcionNeta());
                        break;
                    default:
                        // No hará nada.
                        break;
                }
            }
        }

        int ultimaFila = hoja.getLastRowNum() + 1;
        Row fila = hoja.createRow(ultimaFila);

        for(int iteradorColumna = C07_SUELDO_BASE; iteradorColumna <= PERCEPCION_NETA; iteradorColumna++) {
            Cell celda = fila.createCell(iteradorColumna);
            switch(iteradorColumna) {
                    case C07_SUELDO_BASE:
                        celda.setCellValue(c07SueldoBaseTotal.doubleValue());
                        break;
                    case C30_COMPENSACION_RIESGO:
                        celda.setCellValue(c30CompensacionRiesgoTotal.doubleValue());
                        break;
                    case C32_PRIMA_DOMINICAL:
                        celda.setCellValue(c32PrimaDominicalTotal.doubleValue());
                        break;
                    case C38_DESPENSA:
                        celda.setCellValue(c38DespensaTotal.doubleValue());
                        break;
                    case C42_ASIGNACION_BRUTA:
                        celda.setCellValue(c42AsignacionBrutaTotal.doubleValue());
                        break;
                    case C44_PREVISION_SOCIAL_MULTIPLE:
                        celda.setCellValue(c44PrevisionSocialMultipleTotal.doubleValue());
                        break;
                    case C45_AYUDA_ANTEOJOS:
                        celda.setCellValue(c45AyudaAnteojoTotal.doubleValue());
                        break;
                    case C46_AYUDA_SERVICIO:
                        celda.setCellValue(c46AyudaServicioTotal.doubleValue());
                        break;
                    case C55_AYUDA_GASTOS_ACTUALIZACION:
                        celda.setCellValue(c55AyudaParaGastosActualizacionTotal.doubleValue());
                        break;
                    case CQQ_PRIMA_QUINQUENAL:
                        celda.setCellValue(cqqPrimaQuinquenalTotal.doubleValue());
                        break;
                    case PERCEPCION_TOTAL:
                        celda.setCellValue(percepcionGranTotal.doubleValue());
                        break;
                    case C01_IMPUESTO_SOBRE_RENTA:
                        celda.setCellValue(c01ImpuestoSobreRentaTotal.doubleValue());
                        break;
                    case C02_SR_PENSION_JUBILACION:
                        celda.setCellValue(c02SrPensionJubilacionTotal.doubleValue());
                        break;
                    case C02_SI_SERVICIO_MEDICO:
                        celda.setCellValue(c02SiServicioMedicoTotal.doubleValue());
                        break;
                    case C02_SS_SERVICIO_SOCIAL_CULTURAL:
                        celda.setCellValue(c02SsServicioSocialCulturalTotal.doubleValue());
                        break;
                    case C03_PRESTAMO_PERSONAL:
                        celda.setCellValue(c03PrestamoPersonalTotal.doubleValue());
                        break;
                    case C04_SS_SERVICIO_MEDICO_MATERNIDAD:
                        celda.setCellValue(c04SsServicioMedicoMaternidadTotal.doubleValue());
                        break;
                    case C04_SP_SEGURO_INVALIDEZ_VIDA:
                        celda.setCellValue(c04SpSeguroInvalidezVidaTotal.doubleValue());
                        break;
                    case C09_SEGURO_DANYO_PRESTAMO_AVALADO_ISSSTE:
                        celda.setCellValue(c09SeguroDanyoPrestamoAvaladoIsssteTotal.doubleValue());
                        break;
                    case C17_RETARDO_FALTA:
                        celda.setCellValue(c17RetardoFaltaTotal.doubleValue());
                        break;
                    case C21_FONDO_AHORRO_CAPITALIZABLE:
                        celda.setCellValue(c21FondoAhorroCapitalizableTotal.doubleValue());
                        break;
                    case C29_RESPONSABILIDAD:
                        celda.setCellValue(c29ResponsabilidadTotal.doubleValue());
                        break;
                    case C34_SEGURO_RIESGO_PROFESIONAL:
                        celda.setCellValue(c34SeguroRiesgoProfesionalTotal.doubleValue());
                        break;
                    case C46_INBURSA:
                        celda.setCellValue(c46InbursaTotal.doubleValue());
                        break;
                    case C46_SEGURO_ARGOS:
                        celda.setCellValue(c46SeguroArgosTotal.doubleValue());
                        break;
                    case C46_ETESA:
                        celda.setCellValue(c46EtesaTotal.doubleValue());
                        break;
                    case C46_LA_TENDA:
                        celda.setCellValue(c46LaTendaTotal.doubleValue());
                        break;
                    case C46_PS_PUBLISEG:
                        celda.setCellValue(c46PsPublisegTotal.doubleValue());
                        break;
                    case C46_SEGUROS_GNP:
                        celda.setCellValue(c46SeguroGnpTotal.doubleValue());
                        break;
                    case C46_AUDITORIA_OPERACION_CREDIEMPLEADO:
                        celda.setCellValue(c46AuditoriaOperacionCrediempleadoTotal.doubleValue());
                        break;
                    case C46_CREDITO_EXPRES:
                        celda.setCellValue(c46CreditoExpresTotal.doubleValue());
                        break;
                    case C51_SEGURO_VIDA_INSTITUCIONAL:
                        celda.setCellValue(c51SeguroVidaInstitucionalTotal.doubleValue());
                        break;
                    case C56_PRESTAMO_HIPOTECARIO:
                        celda.setCellValue(c56PrestamoHipotecarioTotal.doubleValue());
                        break;
                    case C57_SEGURO_VIDA_ADICIONAL_METLIFE:
                        celda.setCellValue(c57SeguroDeVidaAdicionalMetlifeTotal.doubleValue());
                        break;
                    case C58_CUOTA_SINDICAL:
                        celda.setCellValue(c58CuotaSindicalTotal.doubleValue());
                        break;
                    case C62_PENSION_ALIMENTICIA:
                        celda.setCellValue(c62PensionAlimenticiaTotal.doubleValue());
                        break;
                    case C64_FONDO_VIVIENDA:
                        celda.setCellValue(c64FondoViviendaTotal.doubleValue());
                        break;
                    case C70_FONDO_AHORRO_DEFUNCION:
                        celda.setCellValue(c70FondoAhorroDefuncionTotal.doubleValue());
                        break;
                    case C77_SEGURO_RETIRO_METLIFE:
                        celda.setCellValue(c77SeguroRetiroMetlifeTotal.doubleValue());
                        break;
                    case C97_DESCUENTO_PROMOBIEN:
                        celda.setCellValue(c97DescuentoPromobienTotal.doubleValue());
                        break;
                    case CAS_AHORRO_SOLIDARIO:
                        celda.setCellValue(casAhorroSolidarioTotal.doubleValue());
                        break;
                    case DEDUCCION_TOTAL:
                        celda.setCellValue(deduccionGranTotal.doubleValue());
                        break;
                    case PERCEPCION_NETA:
                        celda.setCellValue(percepcionNetaTotal.doubleValue());
                        break;
                    default:
                        // No hará nada.
                        break;
            }
        }
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException en caso de no poder tener acceso al archivo se
     * lanzara esta excepción.
     */
    private byte[] obtenerBytes() throws IOException {
        byte[] bytes;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            libro.write(byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
            libro.close();
            is.close();
        }

        return bytes;
    }

}
