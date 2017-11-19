/*
 *
 */

package mx.gob.saludtlax.rh.historialacademico;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioEntity;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 09/06/2016 12:55:58
 *
 */
public class HistorialAcademicoService {

    @Inject
    private BitacoraModificacionService bitacoraModificacionService;
    @Inject
    private ComprobanteEstudioRepository comprobanteEstudioRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private EscolaridadRepository escolaridadRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;

    protected List<HistorialAcademicoDTO> listaHistorialAcademicoPorIdEmpleado(Integer idEmpleado) {
        List<HistorialAcademicoDTO> listaDTOs = new ArrayList<>();

        List<HistorialAcademicoEntity> listaEntities = historialAcademicoRepository.consultarHistorialAcademicoEmpleado(idEmpleado);
        if (!listaEntities.isEmpty()) {
            for (HistorialAcademicoEntity entity : listaEntities) {
                HistorialAcademicoDTO dto = new HistorialAcademicoDTO();

                dto.setIdHistorialAcademico(entity.getIdHistorialAcademico());
                dto.setEscolaridad(entity.getEscolaridad().getIdEscolaridad());
                dto.setDocumentoComprobatorio(entity.getComprobanteEstudio().getIdComprobanteEstudio());
                dto.setEstatusComprobatorio(entity.getComprobanteEstudio().getEstatus());
                dto.setNombreInstitucion(entity.getNombreInstitucion());
                dto.setFechaInicial(entity.getFechaInicial());
                dto.setFechaFinal(entity.getFechaFinal());
                dto.setDuracion(entity.getDuracion());
                dto.setNombreCurso(entity.getNombreCurso());
                dto.setCursando(entity.getCursando());
                dto.setNombreDocumentoComprobatorio(entity.getComprobanteEstudio().getEstatus());
                dto.setNombreEscolaridad(entity.getEscolaridad().getGrupoAcademico());
                dto.setTieneDocumentacion(entity.getTieneDocumentacion());
                dto.setEsMaximoEstudio(entity.getMaximoEstudio());

                dto.setFechaExpedicionCedula(entity.getFechaExpedicionCedula());
                dto.setNumeroCedula(entity.getNumeroCedula());

                listaDTOs.add(dto);
            }
        } else {
            listaDTOs = new ArrayList<>();
        }
        return listaDTOs;
    }

    protected void crearHistorial(NuevoHistorialDTO historialAcademico, boolean esEmpleado) {
        String contexto = "Registro de historial académico: ";

        if (historialAcademico == null) {
            throw new BusinessException(contexto + "Es necesario los datos importantes del historial academico");
        }

        HistorialAcademicoEntity registroHistorialAcademico = new HistorialAcademicoEntity();
        if (esEmpleado) {
            EmpleadoEntity empleado = validarEmpleado(historialAcademico.getIdEmpleado());
            registroHistorialAcademico.setIdEmpleado(empleado.getIdEmpleado());
        }

        EscolaridadEntity escolaridad = escolaridadRepository.escolaridadPorId(historialAcademico.getIdEscolaridad());
        ComprobanteEstudioEntity documentoComprobatorio = comprobanteEstudioRepository.obtenerPorId(historialAcademico.getIdComprobanteEstudio());

        registroHistorialAcademico.setEscolaridad(escolaridad);
        registroHistorialAcademico.setComprobanteEstudio(documentoComprobatorio);
        registroHistorialAcademico.setNombreInstitucion(historialAcademico.getNombreInstitucion());
        registroHistorialAcademico.setFechaInicial(historialAcademico.getFechaInicial());
        registroHistorialAcademico.setFechaFinal(historialAcademico.getFechaFinal());

        registroHistorialAcademico.setDuracion(historialAcademico.getDuracion());
        registroHistorialAcademico.setNombreCurso(historialAcademico.getNombreCurso());
        registroHistorialAcademico.setCursando(historialAcademico.isCursando());
        registroHistorialAcademico.setTieneDocumentacion(Boolean.FALSE);
        registroHistorialAcademico.setMaximoEstudio(historialAcademico.isMaximoEstudios());
        registroHistorialAcademico.setFechaExpedicionCedula(historialAcademico.getFechaExpedicionCedula());
        registroHistorialAcademico.setNumeroCedula(historialAcademico.getNumeroCedula());

        historialAcademicoRepository.crear(registroHistorialAcademico);

    }

    protected void actualizarHistorialAcademico(HistorialAcademicoDTO historialAcademico) {

        String contexto = "Actualización de historial académico: ";

        if (historialAcademico == null) {
            throw new ValidacionException(contexto + "Es necesario los datos importantes del historial academico", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        // Valida datos los para la bitacora
        if (ValidacionUtil.esCadenaVacia(historialAcademico.getComentarioMovimiento())
                | ValidacionUtil.esNumeroPositivo(historialAcademico.getIdUsuarioEnSesion())) {

            bitacoraModificacionService.registrarBitacoraModificacionHistorial(historialAcademico);

        }

        HistorialAcademicoEntity actualizarHistorialAcademico = historialAcademicoRepository.obtenerPorId(historialAcademico.getIdHistorialAcademico());

        if (actualizarHistorialAcademico == null) {
            throw new ReglaNegocioException(contexto + "El historial academico no se encuentra registrado", ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        EscolaridadEntity escolaridad = escolaridadRepository.escolaridadPorId(historialAcademico.getEscolaridad());
        ComprobanteEstudioEntity documentoComprobatorio = comprobanteEstudioRepository.obtenerPorId(historialAcademico.getDocumentoComprobatorio());

        actualizarHistorialAcademico.setEscolaridad(escolaridad);
        actualizarHistorialAcademico.setComprobanteEstudio(documentoComprobatorio);
        actualizarHistorialAcademico.setNombreInstitucion(historialAcademico.getNombreInstitucion());
        actualizarHistorialAcademico.setFechaInicial(historialAcademico.getFechaInicial());
        actualizarHistorialAcademico.setFechaFinal(historialAcademico.getFechaFinal());
        actualizarHistorialAcademico.setDuracion(historialAcademico.getDuracion());
        actualizarHistorialAcademico.setNombreCurso(historialAcademico.getNombreCurso());
        actualizarHistorialAcademico.setCursando(historialAcademico.getCursando());
        actualizarHistorialAcademico.setMaximoEstudio(historialAcademico.getEsMaximoEstudio());
        actualizarHistorialAcademico.setFechaExpedicionCedula(historialAcademico.getFechaExpedicionCedula());
        actualizarHistorialAcademico.setNumeroCedula(historialAcademico.getNumeroCedula());

        historialAcademicoRepository.actualizar(actualizarHistorialAcademico);

    }

    protected void actualizarAdjuntoHistorial(Integer idHistorialAcademico) {
        HistorialAcademicoEntity historial = historialAcademicoRepository.obtenerPorId(idHistorialAcademico);
        historial.setTieneDocumentacion(Boolean.TRUE);
        historialAcademicoRepository.crear(historial);
    }

    private EmpleadoEntity validarEmpleado(Integer idEmpleado) {
        String contexto = "Actualizacion datos: ";
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(idEmpleado);
        if (empleado == null) {
            throw new BusinessException(contexto + "El empleado con identificador " + idEmpleado + " no esta registrado en el sistema");
        }

        if (empleado.getIdEstatus().equals("INACTIVO")) {
            throw new BusinessException(contexto + " El empleado no esta activo.");
        }

        return empleado;
    }
}
