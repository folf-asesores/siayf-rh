/*
 *
 */

package mx.gob.saludtlax.rh.reporteslaborales.detallesempleado;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.saludtlax.rh.empleados.detallesempleado.DetalleEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.reporteslaborales.proyeccion.ContratoExcel;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class DetalleEmpleadoExcel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -467008949119374655L;

    private final InputStream is = ContratoExcel.class
            .getResourceAsStream("/plantillas/empleado/Detalle_Empleado.xlsx");

    /**
     * El nombre de la hoja donde se encuentra el detalle
     */
    private static final String NOMBRE_HOJA = "Detalle_Empleado";

    /**
     * Instancia de la plantilla de Excel en memoria
     */
    private Workbook libro;
    /**
     * Instancia que representa la hoja de Excel en la que se esta trabajando
     */
    private Sheet hoja;
    /**
     * Fila en la que se iniciara a escribir los detalles.
     */
    private static final int FILA_INICIO_DETALLE = 1;

    private static final int NOMBRE_EMPLEADO = 0;
    private static final int RFC = 1;
    private static final int CURP = 2;
    private static final int FECHA_NACIMIENTO = 3;
    private static final int TELEFONO = 4;
    private static final int FECHA_ALTA = 5;
    private static final int DIRECCION_COMPLETA = 6;
    private static final int TIPO_SANGRE = 7;
    private static final int NACIONALIDAD = 8;
    private static final int ESTADO_CIVIL = 9;
    private static final int SEXO = 10;
    private static final int ESTATURA = 11;
    private static final int PESO = 12;
    private static final int ESTATUS_EMPLEADO = 13;
    private static final int CORREO_ELECTRONICO = 14;
    private static final int FECHA_INGRESO = 15;
    private static final int PERSONA_DEPENDIENTE = 16;
    private static final int ID_DATO_PERSONAL = 17;
    private static final int CONYUGE = 18;
    private static final int NUMERO_PADRES = 19;
    private static final int NUMERO_HIJOS = 20;
    private static final int OTRO_PARENTESCO = 21;
    private static final int NUMERO_CUENTA = 22;
    private static final int BANCO = 23;
    private static final int NUMERO_IDENTIFICADOR_BIOMETRICO = 24;
    private static final int TIPO_EMPLEADO = 25;
    private static final int CLAVE_COBRO = 26;
    private static final int METODO_PAGO = 27;
    private static final int NUMERO_VACANTE = 28;
    private static final int INVENTARIO_VACANTE_DISPONIBLE = 29;
    private static final int CODIGO_VACANTE = 30;
    private static final int FOLIO_VACANTE = 31;
    private static final int PROGRAMA = 32;
    private static final int ADSCRIPCION = 33;
    private static final int AREA_ADSCRIPCION = 34;
    private static final int LUGAR_ADSCRIPCION = 35;
    private static final int ACTIVIDAD = 36;
    private static final int FUNCION = 37;
    private static final int CLUES = 38;
    private static final int SEGURO_POPULAR = 39;
    private static final int TIPO_JORNADA = 40;
    private static final int PROYECTO_DESCRIPCION = 41;
    private static final int DEPENDENCIA_DESCRIPCION = 42;
    private static final int UNIDAD_RESPONSABLE_DESCRIPCION = 43;
    private static final int TIPO_CONTRATACION_CODIGO = 44;
    private static final int PUESTO_GENERAL_CODIGO = 45;
    private static final int FUENTE_FINANCIAMIENTO_DESCRIPCION = 46;
    private static final int SUBFUENTE_FINANCIAMIENTO_DESCRIPCION = 47;
    private static final int TIPO_RECURSO_DESCRIPCION = 48;
    private static final int ID_DATO_LABORAL = 49;
    private static final int CENTRO_RESPOSABILIDAD_DESCRIPCION = 50;
    private static final int CENTRO_RESPONSABILIDAD_CLAVE = 51;
    private static final int CUENTA_BANCARIA_CLAVE_CUENTA = 52;
    private static final int CUENTA_BANCARIA_BANCO = 53;
    private static final int CUENTA_BANCARIA_DESCRIPCION = 54;
    private static final int CONFIGURACION_PRESUPUESTAL_ESTADO = 55;
    private static final int JORNADA_DESCRIPCION = 56;
    private static final int PLAZA_CLAVE = 57;
    private static final int PLAZA_NOMBRE = 58;
    private static final int SUELDO = 59;
    private static final int SUELDO_01 = 60;
    private static final int SUELDO_14 = 61;
    private static final int RIESGO_PUESTO_DESCRIPCION = 62;
    private static final int TIPO_TABULADOR_DESCRIPCION = 63;
    private static final int TIPO_PERIODO = 64;

    /**
     * Este método carga de la plantilla y prepara el libro y la hoja que se
     * pueda usaer.
     *
     * @throws IOException
     *             en caso de que no se encuentre el archivo o este dañado
     *             lanzara esta excepción.
     */
    private void cargarPlantilla() throws IOException {
        libro = new XSSFWorkbook(is);
        hoja = libro.getSheet(NOMBRE_HOJA);
    }

    /**
     * Devuelve el contenido del archivo cargado como un arreglo de bytes.
     *
     * @return los bytes que representan el archivo.
     * @throws IOException
     *             en caso de no poder tener acceso al archivo se lanzara esta
     *             excepción.
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

    /**
     * Permite crear un reporte con los detalle con una lista de acumulados.
     *
     * @param detalles
     *            una lista de comisionado o licencia.
     * @return un arreglo de bytes que representa el archivo de excel.
     */
    public byte[] generar(List<DetalleEmpleadoDTO> detalles) {
        try {

            cargarPlantilla();
            llenarDetalles(detalles);

            return obtenerBytes();
        } catch (IOException e) {
            throw new SistemaException("Ocurrio un error al leer la platilla",
                    SistemaCodigoError.ERROR_LECTURA_ESCRITURA);
        }

    }

    private void llenarDetalles(List<DetalleEmpleadoDTO> estructura) {
        int i = FILA_INICIO_DETALLE;
        int numeroConsecutivo = 1;

        String pattern = "#,##0.00#";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        for (DetalleEmpleadoDTO detalle : estructura) {
            Row filaDetalle = hoja.createRow(i);

            Cell celdaCapitulo = filaDetalle.createCell(NOMBRE_EMPLEADO);
            celdaCapitulo.setCellValue(detalle.getNombreCompleto());

            Cell celdaPartidaEspecifica = filaDetalle.createCell(RFC);
            celdaPartidaEspecifica.setCellValue(detalle.getRfc());

            Cell celdaConcepto = filaDetalle.createCell(CURP);
            celdaConcepto.setCellValue(detalle.getCurp());

            Cell celdaEnero = filaDetalle.createCell(FECHA_NACIMIENTO);
            celdaEnero.setCellValue(detalle.getFechaNacimiento() == null ? ""
                    : format.format(detalle.getFechaNacimiento()));

            Cell celdaFebrero = filaDetalle.createCell(TELEFONO);
            celdaFebrero.setCellValue(detalle.getTelefono());

            Cell celdaMarzo = filaDetalle.createCell(FECHA_ALTA);
            celdaMarzo.setCellValue(detalle.getFechaAlta() == null ? ""
                    : format.format(detalle.getFechaAlta()));

            Cell celdaAbril = filaDetalle.createCell(DIRECCION_COMPLETA);
            celdaAbril.setCellValue(detalle.getDireccionCompleta());

            Cell celdaMayo = filaDetalle.createCell(TIPO_SANGRE);
            celdaMayo.setCellValue(detalle.getTipoSangre());

            Cell celdaJunio = filaDetalle.createCell(NACIONALIDAD);
            celdaJunio.setCellValue(detalle.getNacionalidad());

            Cell celdaJulio = filaDetalle.createCell(ESTADO_CIVIL);
            celdaJulio.setCellValue(detalle.getEstadoCivil());

            Cell celdaAgosto = filaDetalle.createCell(SEXO);
            celdaAgosto.setCellValue(detalle.getSexo());

            Cell celdaSeptiembre = filaDetalle.createCell(ESTATURA);
            celdaSeptiembre.setCellValue(detalle.getEstatura());

            Cell celdaOctubre = filaDetalle.createCell(PESO);
            celdaOctubre.setCellValue(detalle.getPeso());

            Cell celdaNoviembre = filaDetalle.createCell(ESTATUS_EMPLEADO);
            celdaNoviembre.setCellValue(detalle.getEstatus());

            Cell celdaDiciembre = filaDetalle.createCell(CORREO_ELECTRONICO);
            celdaDiciembre.setCellValue(detalle.getCorreoElectronico());

            Cell celdaTotal = filaDetalle.createCell(FECHA_INGRESO);
            celdaTotal.setCellValue(detalle.getFechaIngreso() == null ? ""
                    : format.format(detalle.getFechaIngreso()));

            Cell celdaPersonaDependiente = filaDetalle
                    .createCell(PERSONA_DEPENDIENTE);
            celdaPersonaDependiente
                    .setCellValue(detalle.getTienePersonaDependiente());

            Cell celdaIdDatoPersonal = filaDetalle.createCell(ID_DATO_PERSONAL);
            celdaIdDatoPersonal.setCellValue(detalle.getIdDatoPersonal());

            Cell celdaConyuge = filaDetalle.createCell(CONYUGE);
            celdaConyuge.setCellValue(detalle.getNumeroConyuge());

            Cell celdaNumeroPadres = filaDetalle.createCell(NUMERO_PADRES);
            celdaNumeroPadres.setCellValue(detalle.getNumeroPadre() == null
                    ? "0" : detalle.getNumeroPadre().toString());

            Cell celdaNumeroHijos = filaDetalle.createCell(NUMERO_HIJOS);
            celdaNumeroHijos.setCellValue(detalle.getNumeroHijo() == null ? "0"
                    : detalle.getNumeroHijo().toString());

            Cell celdaOtroParentesco = filaDetalle.createCell(OTRO_PARENTESCO);
            celdaOtroParentesco.setCellValue(
                    detalle.getNumeroOtroParentesco() == null ? "0"
                            : detalle.getNumeroOtroParentesco().toString());

            Cell celdaNumeroCuenta = filaDetalle.createCell(NUMERO_CUENTA);
            celdaNumeroCuenta.setCellValue(detalle.getNumeroCuenta());

            Cell celdaBanco = filaDetalle.createCell(BANCO);
            celdaBanco.setCellValue(detalle.getBanco());

            Cell celdaNumeroIdentificadorBiometrico = filaDetalle
                    .createCell(NUMERO_IDENTIFICADOR_BIOMETRICO);
            celdaNumeroIdentificadorBiometrico.setCellValue(
                    detalle.getNumeroIdentificadorBiometrico() == null ? ""
                            : detalle.getNumeroIdentificadorBiometrico()
                                    .toString());

            Cell celdaTipoEmpleado = filaDetalle.createCell(TIPO_EMPLEADO);
            celdaTipoEmpleado.setCellValue(detalle.getTipoEmpleado());

            Cell celdaClaveCobro = filaDetalle.createCell(CLAVE_COBRO);
            celdaClaveCobro.setCellValue(detalle.getClaveCobro());

            Cell celdaMetodoPago = filaDetalle.createCell(METODO_PAGO);
            celdaMetodoPago.setCellValue(detalle.getMetodoPago());

            Cell celdaNumeroVacante = filaDetalle.createCell(NUMERO_VACANTE);
            celdaNumeroVacante.setCellValue(detalle.getNumeroVacante() == null
                    ? "" : detalle.getNumeroVacante().toString());

            Cell celdaInventarioVacante = filaDetalle
                    .createCell(INVENTARIO_VACANTE_DISPONIBLE);
            celdaInventarioVacante
                    .setCellValue(detalle.getInventarioVacanteDisponible());

            Cell celdaCodigoVacante = filaDetalle.createCell(CODIGO_VACANTE);
            celdaCodigoVacante.setCellValue(detalle.getCodigoVacante());

            Cell celdaFolioVacante = filaDetalle.createCell(FOLIO_VACANTE);
            celdaFolioVacante.setCellValue(detalle.getFolioVacante());

            Cell celdaPrograma = filaDetalle.createCell(PROGRAMA);
            celdaPrograma.setCellValue(detalle.getPrograma());

            Cell celdaAdscripcion = filaDetalle.createCell(ADSCRIPCION);
            celdaAdscripcion.setCellValue(detalle.getAdscripcion());

            Cell celdaAreaAdscripcion = filaDetalle
                    .createCell(AREA_ADSCRIPCION);
            celdaAreaAdscripcion.setCellValue(detalle.getAreaAdscripcion());

            Cell celdaLugarAdscripcion = filaDetalle
                    .createCell(LUGAR_ADSCRIPCION);
            celdaLugarAdscripcion.setCellValue(detalle.getLugarAdscripcion());

            Cell celdaActividad = filaDetalle.createCell(ACTIVIDAD);
            celdaActividad.setCellValue(detalle.getActividad());

            Cell celdaFiuncion = filaDetalle.createCell(FUNCION);
            celdaFiuncion.setCellValue(detalle.getFuncion());

            Cell celdaClues = filaDetalle.createCell(CLUES);
            celdaClues.setCellValue(detalle.getClues());

            Cell celdaSeguroPopular = filaDetalle.createCell(SEGURO_POPULAR);
            celdaSeguroPopular.setCellValue(detalle.getSeguroPopular());

            Cell celdaTipoJornada = filaDetalle.createCell(TIPO_JORNADA);
            celdaTipoJornada.setCellValue(detalle.getTipoJornada());

            Cell celdaProyectoDescripcion = filaDetalle
                    .createCell(PROYECTO_DESCRIPCION);
            celdaProyectoDescripcion
                    .setCellValue(detalle.getProyectoDescripcion());

            Cell celdaDependenciaDescripcion = filaDetalle
                    .createCell(DEPENDENCIA_DESCRIPCION);
            celdaDependenciaDescripcion
                    .setCellValue(detalle.getDependenciaDescripccion());

            Cell celdaUnidadResponsableDescripcion = filaDetalle
                    .createCell(UNIDAD_RESPONSABLE_DESCRIPCION);
            celdaUnidadResponsableDescripcion
                    .setCellValue(detalle.getUnidadResponsableDescripcion());

            Cell celdaTipoContratacion = filaDetalle
                    .createCell(TIPO_CONTRATACION_CODIGO);
            celdaTipoContratacion
                    .setCellValue(detalle.getTipoContratacionCodigo());

            Cell celdaPuestoGeneralCodigo = filaDetalle
                    .createCell(PUESTO_GENERAL_CODIGO);
            celdaPuestoGeneralCodigo
                    .setCellValue(detalle.getPuestoGeneralCodigo());

            Cell celdaFuenteFinanciamiento = filaDetalle
                    .createCell(FUENTE_FINANCIAMIENTO_DESCRIPCION);
            celdaFuenteFinanciamiento
                    .setCellValue(detalle.getFuenteFinanciamientoDescripcion());

            Cell celdaSubfuenteFinanciamiento = filaDetalle
                    .createCell(SUBFUENTE_FINANCIAMIENTO_DESCRIPCION);
            celdaSubfuenteFinanciamiento.setCellValue(
                    detalle.getSubfuenteFinanciamientoDescripcion());

            Cell celdaTipoRecurso = filaDetalle
                    .createCell(TIPO_RECURSO_DESCRIPCION);
            celdaTipoRecurso.setCellValue(detalle.getTipoRecursoDescripcion());

            Cell celdaIdDatoLaboral = filaDetalle.createCell(ID_DATO_LABORAL);
            celdaIdDatoLaboral.setCellValue(detalle.getIdDatoLaboral() == null
                    ? "" : detalle.getIdDatoLaboral().toString());

            Cell celdaCentroResponsabilidadDescripcion = filaDetalle
                    .createCell(CENTRO_RESPOSABILIDAD_DESCRIPCION);
            celdaCentroResponsabilidadDescripcion.setCellValue(
                    detalle.getCentroResponsabilidadDescripcion());

            Cell celdaCentroResponsabilidadClave = filaDetalle
                    .createCell(CENTRO_RESPONSABILIDAD_CLAVE);
            celdaCentroResponsabilidadClave
                    .setCellValue(detalle.getCentroResponsabilidadClave());

            Cell celdaCuentaBancariaClave = filaDetalle
                    .createCell(CUENTA_BANCARIA_CLAVE_CUENTA);
            celdaCuentaBancariaClave
                    .setCellValue(detalle.getCuentaBancariaClaveCuenta() == null
                            ? "" : detalle.getCuentaBancariaClaveCuenta()
                                    .toString());

            Cell celdaCuentaBancariaBanco = filaDetalle
                    .createCell(CUENTA_BANCARIA_BANCO);
            celdaCuentaBancariaBanco
                    .setCellValue(detalle.getCuentaBancariaBanco());

            Cell celdaBancariaDescripcion = filaDetalle
                    .createCell(CUENTA_BANCARIA_DESCRIPCION);
            celdaBancariaDescripcion
                    .setCellValue(detalle.getCuentaBancariaDescripcion());

            Cell celdaConfiguracionPresupuestal = filaDetalle
                    .createCell(CONFIGURACION_PRESUPUESTAL_ESTADO);
            celdaConfiguracionPresupuestal
                    .setCellValue(detalle.getConfiguracionPresupuestalEstado());

            Cell celdaJornada = filaDetalle.createCell(JORNADA_DESCRIPCION);
            celdaJornada.setCellValue(detalle.getJornadaDescripcion());

            Cell celdaPlazaClave = filaDetalle.createCell(PLAZA_CLAVE);
            celdaPlazaClave.setCellValue(detalle.getPlazaClave());

            Cell celdaPlazaNombre = filaDetalle.createCell(PLAZA_NOMBRE);
            celdaPlazaNombre.setCellValue(detalle.getPlazaNombre());

            Cell celdaSueldo = filaDetalle.createCell(SUELDO);
            celdaSueldo.setCellValue(detalle.getSueldo() == null ? "0" : "$ "
                    + new DecimalFormat(pattern).format(detalle.getSueldo()));

            Cell celdaSueldo01 = filaDetalle.createCell(SUELDO_01);
            celdaSueldo01.setCellValue(detalle.getSueldo01() == null ? "0"
                    : "$ " + new DecimalFormat(pattern)
                            .format(detalle.getSueldo01()));

            Cell celdaSueldo14 = filaDetalle.createCell(SUELDO_14);
            celdaSueldo14.setCellValue(detalle.getSueldo14() == null ? "0"
                    : "$ " + new DecimalFormat(pattern)
                            .format(detalle.getSueldo14()));

            Cell celdaRiesgoPuesto = filaDetalle
                    .createCell(RIESGO_PUESTO_DESCRIPCION);
            celdaRiesgoPuesto
                    .setCellValue(detalle.getRiesgoPuestoDescripcion());

            Cell celdaTipoTabulador = filaDetalle
                    .createCell(TIPO_TABULADOR_DESCRIPCION);
            celdaTipoTabulador
                    .setCellValue(detalle.getTipoTabuladorDescripcion());

            Cell celdaTipoPeriodo = filaDetalle.createCell(TIPO_PERIODO);
            celdaTipoPeriodo.setCellValue(detalle.getTipoPeriodo());

            numeroConsecutivo++;
            i++;
            hoja.shiftRows(i, i + 1, 1);
        }
    }

}
