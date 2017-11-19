/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioEntity;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEstudioRepository;
import mx.gob.saludtlax.rh.persistencia.EscolaridadEntity;
import mx.gob.saludtlax.rh.persistencia.EscolaridadRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 06/04/2016 21:09:11
 */
public class HistorialAcademicoService {

    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private ComprobanteEstudioRepository documentosComprobatoriosRepository;
    @Inject
    private EscolaridadRepository escolaridadRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;

    /**
     * Registrando el historial academico del aspirante
     *
     * @param historialAcademico
     */
    protected void crearHistorialAcademicoAspirante(
            HistorialAcademicoDTO historialAcademico) {
        String contexto = "Registro Historial académico: ";

        AspiranteEntity aspirante = aspiranteRepository
                .obtenerPorId(historialAcademico.getAspirante());

        if (aspirante == null) {
            throw new ReglaNegocioException(contexto
                    + "El identificador del aspirante no existe, registre los datos personales",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        HistorialAcademicoEntity registroHistorialAcademico = new HistorialAcademicoEntity();
        // Obteniendo Escolaridad por identificador unico
        EscolaridadEntity escolaridad = escolaridadRepository
                .obtenerPorId(historialAcademico.getEscolaridad());
        // Obteniendo Documento Comprobatoria por identificador unico
        ComprobanteEstudioEntity comprobanteEstudio = documentosComprobatoriosRepository
                .obtenerPorId(historialAcademico.getComprobanteEstudio());

        registroHistorialAcademico
                .setIdAspirante(historialAcademico.getAspirante());
        registroHistorialAcademico.setEscolaridad(escolaridad);
        registroHistorialAcademico.setComprobanteEstudio(comprobanteEstudio);
        registroHistorialAcademico.setNombreInstitucion(
                historialAcademico.getNombreInstitucion());
        registroHistorialAcademico
                .setFechaInicial(historialAcademico.getFechaInicial());
        registroHistorialAcademico
                .setFechaFinal(historialAcademico.getFechaFinal());

        registroHistorialAcademico
                .setDuracion(historialAcademico.getDuracion());
        registroHistorialAcademico
                .setNombreCurso(historialAcademico.getNombreCurso());
        registroHistorialAcademico
                .setCursando(historialAcademico.getCursando());
        // Es falso asta que se le adjunte una dopcumentacion
        registroHistorialAcademico.setTieneDocumentacion(
                historialAcademico.getTieneDocumentacion());
        registroHistorialAcademico
                .setMaximoEstudio(historialAcademico.getEsMaximoEstudio());
        // si tiene cedula profesional
        registroHistorialAcademico.setFechaExpedicionCedula(
                historialAcademico.getFechaExpedicionCedula());
        registroHistorialAcademico
                .setNumeroCedula(historialAcademico.getNumeroCedula());
        // registrando historial academico
        historialAcademicoRepository.crear(registroHistorialAcademico);

        // Actualizar perfil del aspirante
        List<String> profesiones = historialAcademicoRepository
                .consultarProfesionesAspirante(aspirante.getIdAspirante());
        if (!profesiones.isEmpty()) {
            StringBuilder perfilAspirante = new StringBuilder();
            for (String profesion : profesiones) {
                if (!ValidacionUtil.esCadenaVacia(profesion)) {
                    if (!perfilAspirante.toString().contains(profesion)) {
                        perfilAspirante.append(profesion).append("/");
                    }

                }
            }

            if (!perfilAspirante.toString().trim().isEmpty()) {
                int ultimaPosicion = perfilAspirante.toString().length() - 1;
                aspirante.setPerfilAcademico(perfilAspirante.toString()
                        .substring(0, ultimaPosicion));
                aspiranteRepository.actualizar(aspirante);
            }

        }

    }

    /**
     * Actualiza el historial academico del aspirante
     *
     * @param historialAcademico
     */
    protected void actualizarHistorialAcademicoAspirante(
            HistorialAcademicoDTO historialAcademico) {

        String contexto = "Actualización Historial Académico: ";

        AspiranteEntity aspirante = aspiranteRepository
                .obtenerPorId(historialAcademico.getAspirante());

        if (aspirante == null) {
            throw new ReglaNegocioException(contexto
                    + "El identificador del aspirante no existe, registre los datos generales",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }
        // Obteniendo Historial Academico por identificador unico
        HistorialAcademicoEntity registroHistorialAcademico = historialAcademicoRepository
                .obtenerPorId(historialAcademico.getIdHistorialAcademico());
        // Obtener Escolaridad por identificador unico
        EscolaridadEntity escolaridad = escolaridadRepository
                .obtenerPorId(historialAcademico.getEscolaridad());
        // Obtener Documento Comprobatorio por identificador
        ComprobanteEstudioEntity comprobanteEstudio = documentosComprobatoriosRepository
                .obtenerPorId(historialAcademico.getComprobanteEstudio());

        registroHistorialAcademico
                .setIdAspirante(historialAcademico.getAspirante());
        registroHistorialAcademico.setEscolaridad(escolaridad);
        registroHistorialAcademico.setComprobanteEstudio(comprobanteEstudio);
        registroHistorialAcademico.setNombreInstitucion(
                historialAcademico.getNombreInstitucion());
        registroHistorialAcademico
                .setFechaInicial(historialAcademico.getFechaInicial());
        registroHistorialAcademico
                .setFechaFinal(historialAcademico.getFechaFinal());

        registroHistorialAcademico
                .setDuracion(historialAcademico.getDuracion());
        registroHistorialAcademico
                .setNombreCurso(historialAcademico.getNombreCurso());
        registroHistorialAcademico
                .setCursando(historialAcademico.getCursando());
        // Es falso asta que se le adjunte una dopcumentacion
        registroHistorialAcademico.setTieneDocumentacion(
                historialAcademico.getTieneDocumentacion());
        registroHistorialAcademico
                .setMaximoEstudio(historialAcademico.getEsMaximoEstudio());
        registroHistorialAcademico.setFechaExpedicionCedula(
                historialAcademico.getFechaExpedicionCedula());
        registroHistorialAcademico
                .setNumeroCedula(historialAcademico.getNumeroCedula());
        // Atualizar historial academico
        historialAcademicoRepository.actualizar(registroHistorialAcademico);

    }

    /**
     * Eliminar historial academico
     *
     * @param idHistorialAcademico
     */
    protected void eliminarHistorialAcademico(Integer idHistorialAcademico) {

        // Obteniendo Historial Academico por identificador unico
        HistorialAcademicoEntity eliminarHistorialAcademico = historialAcademicoRepository
                .obtenerPorId(idHistorialAcademico);
        // Eliminando Historial Academico
        historialAcademicoRepository.eliminar(eliminarHistorialAcademico);
    }

    /**
     * lista de historial academico por identificador unico del aspirante
     *
     * @param idAspirante
     * @return
     */
    protected List<HistorialAcademicoDTO> obtenerListaHistorialAcademicoPorIdAspirante(
            Integer idAspirante) {
        List<HistorialAcademicoDTO> listaDTOs = new ArrayList<>();

        List<HistorialAcademicoEntity> listaEntities = historialAcademicoRepository
                .consultarHistorialAcademicoAspirante(idAspirante);
        if (!listaEntities.isEmpty()) {
            for (HistorialAcademicoEntity entity : listaEntities) {
                HistorialAcademicoDTO dto = new HistorialAcademicoDTO();

                dto.setIdHistorialAcademico(entity.getIdHistorialAcademico());
                dto.setAspirante(entity.getIdAspirante());
                dto.setEscolaridad(entity.getEscolaridad().getIdEscolaridad());
                dto.setComprobanteEstudio(entity.getComprobanteEstudio()
                        .getIdComprobanteEstudio());
                dto.setEstatusComprobanteEstudio(
                        entity.getComprobanteEstudio().getEstatus());
                dto.setNombreInstitucion(entity.getNombreInstitucion());
                dto.setFechaInicial(entity.getFechaInicial());
                dto.setFechaFinal(entity.getFechaFinal());
                dto.setDuracion(entity.getDuracion());
                dto.setNombreCurso(entity.getNombreCurso());
                dto.setCursando(entity.getCursando());
                dto.setNombreComprobanteEstudio(
                        entity.getComprobanteEstudio().getEstatus());
                dto.setNombreEscolaridad(
                        entity.getEscolaridad().getEscolaridad());
                dto.setEsMaximoEstudio(entity.getMaximoEstudio());
                dto.setTieneDocumentacion(entity.getTieneDocumentacion());

                dto.setFechaExpedicionCedula(entity.getFechaExpedicionCedula());
                dto.setNumeroCedula(entity.getNumeroCedula());

                listaDTOs.add(dto);
            }
        } else {
            listaDTOs = new ArrayList<>();
        }
        return listaDTOs;
    }

}
