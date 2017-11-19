/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.persistencia.BitacoraModificacionEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.BitacoraModificacionEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.DireccionEntity;
import mx.gob.saludtlax.rh.persistencia.DireccionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoModificacionEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoModificacionEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-21:34:57
 */
public class BitacoraModificacionService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3495510991667579397L;

    @Inject
    private BitacoraModificacionEmpleadoRepository bitacoraMovimientoRepository;
    @Inject
    private BitacoraModificacionEmpleadoRepository bitacoraModificacionEmpleadoRepository;
    @Inject
    private DireccionRepository direccionRepository;
    @Inject
    private TipoModificacionEmpleadoRepository tipoModificacionEmpleadoRepository;
    @Inject
    private UsuarioRepository usuarioRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject
    private ExperienciaLaboralRepository experienciaLaboralRepository;

    protected void registrarBitacoraModificacionDatoGeneral(DatosGeneralesDTO datosGeneralesDTO) {

        EmpleadoEntity empleado = validarEmpleado(datosGeneralesDTO.getIdEmpleado());

        BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();

        if (empleado.getRfc().compareToIgnoreCase(datosGeneralesDTO.getRfc().trim()) != 0) {
            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.RFC);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.getRfc());
            dto.setLccAnterior(empleado.getRfc());

            registrarBitacora(dto);
        }

        else if (empleado.getCurp().compareToIgnoreCase(datosGeneralesDTO.getCurp().trim()) != 0) {
            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.CURP);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.getCurp());
            dto.setLccAnterior(empleado.getCurp());

            registrarBitacora(dto);
        }

        else if (empleado.getNombre().compareTo(datosGeneralesDTO.getNombre().trim()) != 0) {
            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.NOMBRE);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.getNombre());
            dto.setLccAnterior(empleado.getNombre());

            registrarBitacora(dto);
        }

        else if (empleado.getApellidoPaterno().compareTo(datosGeneralesDTO.getApellidoPaterno().trim()) != 0) {
            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.NOMBRE);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.getApellidoPaterno());
            dto.setLccAnterior(empleado.getApellidoPaterno());

            registrarBitacora(dto);
        }

        else if (empleado.getApellidoMaterno().compareTo(datosGeneralesDTO.getApellidoMaterno().trim()) != 0) {
            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.NOMBRE);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.getApellidoMaterno());
            dto.setLccAnterior(empleado.getApellidoMaterno());

            registrarBitacora(dto);
        }

        else {

            dto = new BitacoraEmpleadoDTO();
            dto.setComentarios(datosGeneralesDTO.getComentarioMovimiento());
            dto.setEmpleado(datosGeneralesDTO.getIdEmpleado());
            dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.DATOS_GENERALES);
            dto.setIdUsuario(datosGeneralesDTO.getIdUsuarioEnSesion());
            dto.setLccActual(datosGeneralesDTO.lccDatoGeneral());
            dto.setLccAnterior(empleado.lccDatoGeneral());

            registrarBitacora(dto);
        }

    }

    private EmpleadoEntity validarEmpleado(Integer idEmpleado) {
        String contexto = "validarEmpleado: ";
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
        if (empleado == null) {
            throw new ReglaNegocioException(contexto + "El empleado con identificador " + idEmpleado + " no esta registrado en el sistema",
                    ReglaNegocioCodigoError.EMPLEADO_NO_ENCONTRADO);
        }

        if (empleado.getIdEstatus().equals("INACTIVO")) {
            throw new ReglaNegocioException(contexto + " El empleado no esta activo.", ReglaNegocioCodigoError.EMPLEADO_INACTIVO);
        }

        return empleado;
    }

    protected void registrarBitacoraModificacionDomicilio(DomicilioDTO domicilioDTO, Integer idEmpleado) {
        EmpleadoEntity empleado = validarEmpleado(idEmpleado);

        DireccionEntity direccionEntity = direccionRepository.consultarDireccionEmpleadoPorId(empleado.getIdEmpleado());

        BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();

        dto.setComentarios(domicilioDTO.getComentarioMovimiento());
        dto.setEmpleado(empleado.getIdEmpleado());
        dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.DOMICILIO);
        dto.setIdUsuario(domicilioDTO.getIdUsuarioEnSesion());
        dto.setLccActual(domicilioDTO.lccDireccion());
        dto.setLccAnterior(direccionEntity != null ? direccionEntity.lccDomicilio() : " ");

        registrarBitacora(dto);
    }

    public void registrarBitacoraModificacionHistorial(HistorialAcademicoDTO historialAcademicoDTO) {
        EmpleadoEntity empleado = validarEmpleado(historialAcademicoDTO.getIdEmpleado());

        HistorialAcademicoEntity historialAcademicoEntity = historialAcademicoRepository.obtenerPorId(historialAcademicoDTO.getIdHistorialAcademico());

        BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();

        dto.setComentarios(historialAcademicoDTO.getComentarioMovimiento());
        dto.setEmpleado(empleado.getIdEmpleado());
        dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.HISTORIAL_ACADEMICO);
        dto.setIdUsuario(historialAcademicoDTO.getIdUsuarioEnSesion());
        dto.setLccActual(historialAcademicoDTO.lccHistorial());
        dto.setLccAnterior(historialAcademicoEntity.lccHistorial());

        registrarBitacora(dto);

    }

    public void registrarBitacoraModificacionExperiencia(ExperienciaLaboralDTO experienciaLaboral, Integer idEmpleado) {
        EmpleadoEntity empleado = validarEmpleado(idEmpleado);

        ExperienciaLaboralEntity experienciaLaboralEntity = experienciaLaboralRepository.obtenerPorId(experienciaLaboral.getIdExperienciaLaboral());

        BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();

        dto.setComentarios(experienciaLaboral.getComentarioMovimiento());
        dto.setEmpleado(empleado.getIdEmpleado());
        dto.setTipoMovimientoEmpleado(EnumTipoModificacionEmpleado.EXPERIENCIA_LABORAL);
        dto.setIdUsuario(experienciaLaboral.getIdUsuarioEnSesion());
        dto.setLccActual(experienciaLaboral.lccExperiencia());
        dto.setLccAnterior(experienciaLaboralEntity.lccExperiencia());

        registrarBitacora(dto);

    }

    /**
     * Registra la bitacora de movimientos realizados a los datos personales del
     * empleado
     *
     * @param bitacoraEmpleadoDTO
     */
    public Integer registrarBitacora(BitacoraEmpleadoDTO bitacoraEmpleadoDTO) {
        TipoModificacionEmpleadoEntity tipoMovimiento = tipoModificacionEmpleadoRepository.obtenerPorId(bitacoraEmpleadoDTO.getTipoMovimientoEmpleado());
        UsuarioEntity usuarioEntity = usuarioRepository.obtenerPorId(bitacoraEmpleadoDTO.getIdUsuario());
        BitacoraModificacionEmpleadoEntity bitacora = new BitacoraModificacionEmpleadoEntity();

        if (bitacoraEmpleadoDTO.getComentarios() == null) {
            bitacora.setComentarios(" ");
        } else {
            bitacora.setComentarios(bitacoraEmpleadoDTO.getComentarios().toUpperCase());
        }

        bitacora.setFecha(FechaUtil.fechaActualSinHora());
        bitacora.setHora(FechaUtil.horaActual());
        bitacora.setLccActual(bitacoraEmpleadoDTO.getLccActual());
        bitacora.setLccAnterior(bitacoraEmpleadoDTO.getLccAnterior());
        bitacora.setTipoMovimiento(tipoMovimiento);
        bitacora.setUsuario(usuarioEntity);
        bitacora.setIdEmpleado(bitacoraEmpleadoDTO.getEmpleado());

        bitacoraMovimientoRepository.crear(bitacora);
        return bitacora.getIdBitacoraMovimiento();
    }

    public void registrarBitacoraProductoNominaEmpleado(NominaEmpleadoEntity nominaEmpleado, String lccActual, Integer idUsuario,
            Integer tipoMovimientoNomina) {

        String criterioTipoMovimiento = "ACTUALIZACIÓN PRODUCTO NOMINA EMPLEADO";

        BitacoraEmpleadoDTO bitacoraEmpleadoDTO = new BitacoraEmpleadoDTO();

        TipoModificacionEmpleadoEntity tipoMovimiento = tipoModificacionEmpleadoRepository.obtenerTipoModificacionPorCriterio(criterioTipoMovimiento);

        if (tipoMovimiento == null) {
            tipoMovimiento = new TipoModificacionEmpleadoEntity();

            tipoMovimiento.setMovimiento(criterioTipoMovimiento);

            tipoModificacionEmpleadoRepository.crear(tipoMovimiento);
        }

        bitacoraEmpleadoDTO.setTipoMovimientoEmpleado(tipoMovimiento.getIdTipoMovimiento());
        bitacoraEmpleadoDTO.setIdUsuario(idUsuario);
        bitacoraEmpleadoDTO.setLccActual(lccActual);
        bitacoraEmpleadoDTO.setLccAnterior(obtenerTipoMovimientoNomina(nominaEmpleado, tipoMovimientoNomina));
        bitacoraEmpleadoDTO.setEmpleado(nominaEmpleado.getIdEmpleado().getIdEmpleado());
        bitacoraEmpleadoDTO.setComentarios(criterioTipoMovimiento);

        registrarBitacora(bitacoraEmpleadoDTO);
    }

    public static String obtenerTipoMovimientoNomina(NominaEmpleadoEntity nominaEmpleado, int tipoMovimientoNomina) {

        String tipoMovimiento = "";

        switch (tipoMovimientoNomina) {

            case 1:// ESTATUS FALTA PULIR ESTA PARTE, SI SE REQUIERE AGREGAR OTRO
                   // TIPO DE MOVIMIENTO PARA ADMINISTRAR ESTA PARTE PUEDE AGREGAN
                   // OTRO CASE.
                tipoMovimiento = nominaEmpleado.getIdEstatusNominaEmpleado().getEstatusNominaEmpleado();
                break;

            default:
                tipoMovimiento = "Invalido";
                break;

        }
        return tipoMovimiento;
    }

    public List<BitacoraEmpleadoDTO> consultarBitacorasMovimientos(Integer idEmpleado) {
        List<BitacoraEmpleadoDTO> bitacoras = new ArrayList<>();
        List<BitacoraModificacionEmpleadoEntity> consulta = bitacoraModificacionEmpleadoRepository.consultarBitacorasModificacionesIdEmpleado(idEmpleado);
        if (!consulta.isEmpty()) {
            for (BitacoraModificacionEmpleadoEntity b : consulta) {
                BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();
                dto.setComentarios(b.getComentarios());
                dto.setUsuario(b.getUsuario().nombreCompleto());
                dto.setLccActual(b.getLccActual());
                dto.setLccAnterior(b.getLccAnterior());
                dto.setTipoModificacion(b.getTipoMovimiento().getMovimiento());
                dto.setFecha(b.getFecha());
                bitacoras.add(dto);

            }
        }
        return bitacoras;
    }

    /**
     * Obtiene la bitácora de la modificación realizada al empleado.
     *
     * @param idBitacora
     *            identificador de la bitácora.
     */
    public BitacoraEmpleadoDTO obtenerBitacoraPorId(Integer idBitacora) {
        if (!ValidacionUtil.esNumeroPositivo(idBitacora)) {
            throw new ValidacionException("El identificador de la bitácora es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        BitacoraModificacionEmpleadoEntity b = bitacoraModificacionEmpleadoRepository.obtenerPorId(idBitacora);
        if (b == null) {
            throw new SistemaException("No se encontró bitacora con identificador " + idBitacora, SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(b.getIdEmpleado());
        BitacoraEmpleadoDTO dto = new BitacoraEmpleadoDTO();
        dto.setComentarios(b.getComentarios());
        dto.setUsuario(b.getUsuario().nombreCompleto());
        dto.setLccActual(b.getLccActual());
        dto.setLccAnterior(b.getLccAnterior());
        dto.setTipoModificacion(b.getTipoMovimiento().getMovimiento());
        dto.setFecha(b.getFecha());
        dto.setNombreEmpleado(empleado.getNombreCompleto());
        return dto;
    }
}
