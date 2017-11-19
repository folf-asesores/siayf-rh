/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;

/**
 * @author Eduardo Mex

 * @version 06/04/2016 21:10:11
 */
public class ExperienciaLaboralService {
    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private ExperienciaLaboralRepository experienciaLaboralRepository;

    /**
     * Registrar Experiencia Laboral del Aspirante
     *
     * @param registroExperienciaLaboral
     */
    protected void crearExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral) {
        String contexto = "Registro Experiencia Laboral: ";

        AspiranteEntity aspirante = aspiranteRepository.obtenerPorId(experienciaLaboral.getIdAspirante());

        if (aspirante == null) {
            throw new BusinessException(contexto + "El identificador del aspirante no existe, registre los datos generales");
        }

        ExperienciaLaboralEntity registroExperienciaLaboral = new ExperienciaLaboralEntity();

        registroExperienciaLaboral.setNombreEmpresa(experienciaLaboral.getNombreEmpresa());
        registroExperienciaLaboral.setTelefono(experienciaLaboral.getTelefono());
        registroExperienciaLaboral.setCorreoContacto(experienciaLaboral.getCorreoContacto());
        registroExperienciaLaboral.setPuesto(experienciaLaboral.getPuesto());
        registroExperienciaLaboral.setFechaInicial(experienciaLaboral.getFechaInicial());
        registroExperienciaLaboral.setFechaFinal(experienciaLaboral.getFechaFinal());
        registroExperienciaLaboral.setDireccionEmpresa(experienciaLaboral.getDireccionEmpresa());
        registroExperienciaLaboral.setMotivoSeparacion(experienciaLaboral.getMotivoSeparacion());
        registroExperienciaLaboral.setSueldoMensual(experienciaLaboral.getSueldoMensual());

        registroExperienciaLaboral.setAspirante(aspirante);
        registroExperienciaLaboral.setNombreJefe(experienciaLaboral.getNombreJefe());
        registroExperienciaLaboral.setPuestoJefe(experienciaLaboral.getPuestoJefe());
        registroExperienciaLaboral.setSolicitarInformacion(experienciaLaboral.getSolicitarInformacion());
        registroExperienciaLaboral.setRazonNoSolicitar(experienciaLaboral.getRazonNoSolicitar());
        registroExperienciaLaboral.setComentarios(experienciaLaboral.getComentarios());

        // registrando experiencia personal
        experienciaLaboralRepository.crear(registroExperienciaLaboral);

    }

    /**
     * Actualiza la experiencia laboral del aspirante
     *
     * @param experienciaLaboral
     */
    protected void actualizarExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral) {

        String contexto = "Actualización Experiencia Laboral: ";

        AspiranteEntity aspirante = aspiranteRepository.obtenerPorId(experienciaLaboral.getIdAspirante());

        if (aspirante == null) {
            throw new BusinessException(contexto + "El identificador del aspirante no existe, registre los datos generales");
        }
        // Obteniendo la experiencia laboral por identificador unico
        ExperienciaLaboralEntity registroExperienciaLaboral = experienciaLaboralRepository.obtenerPorId(experienciaLaboral.getIdExperienciaLaboralAspirante());

        registroExperienciaLaboral.setNombreEmpresa(experienciaLaboral.getNombreEmpresa());
        registroExperienciaLaboral.setTelefono(experienciaLaboral.getTelefono());
        registroExperienciaLaboral.setCorreoContacto(experienciaLaboral.getCorreoContacto());
        registroExperienciaLaboral.setPuesto(experienciaLaboral.getPuesto());
        registroExperienciaLaboral.setFechaInicial(experienciaLaboral.getFechaInicial());
        registroExperienciaLaboral.setFechaFinal(experienciaLaboral.getFechaFinal());

        registroExperienciaLaboral.setDireccionEmpresa("");

        registroExperienciaLaboral.setDireccionEmpresa(experienciaLaboral.getDireccionEmpresa());

        registroExperienciaLaboral.setMotivoSeparacion(experienciaLaboral.getMotivoSeparacion());
        registroExperienciaLaboral.setSueldoMensual(experienciaLaboral.getSueldoMensual());

        registroExperienciaLaboral.setAspirante(aspirante);
        registroExperienciaLaboral.setNombreJefe(experienciaLaboral.getNombreJefe());
        registroExperienciaLaboral.setPuestoJefe(experienciaLaboral.getPuestoJefe());
        registroExperienciaLaboral.setSolicitarInformacion(experienciaLaboral.getSolicitarInformacion());
        registroExperienciaLaboral.setRazonNoSolicitar(experienciaLaboral.getRazonNoSolicitar());
        registroExperienciaLaboral.setComentarios(experienciaLaboral.getComentarios());

        // actualizando experiencia personal
        experienciaLaboralRepository.actualizar(registroExperienciaLaboral);

    }

    /**
     * Eliminando experiencia laboral del aspirante
     *
     * @param idExperienciaLaboral
     */
    protected void eliminarExperienciaLaboral(Integer idExperienciaLaboral) {

        // Obteniendo la experiencia laboral por identificador unico
        ExperienciaLaboralEntity registroExperienciaLaboral = experienciaLaboralRepository.obtenerPorId(idExperienciaLaboral);
        // Eliminando Experiencia Laboral
        experienciaLaboralRepository.eliminar(registroExperienciaLaboral);
    }

    /***
     * Lista de experiencia laboral por identificador unico del aspirante
     *
     * @param idAspirante
     * @return
     * @throws ParseException
     */
    protected List<ExperienciaLaboralAspiranteDTO> obtenerListaExperienciaLaboralPorIdAspirante(Integer idAspirante) {
        List<ExperienciaLaboralAspiranteDTO> experienciasLaboralesDTO = new ArrayList<>();

        List<ExperienciaLaboralEntity> experienciasLaboralesEntities = experienciaLaboralRepository.consultarExperienciasLaboralesAspirante(idAspirante);

        if (!experienciasLaboralesEntities.isEmpty()) {
            for (ExperienciaLaboralEntity entity : experienciasLaboralesEntities) {
                ExperienciaLaboralAspiranteDTO dto = new ExperienciaLaboralAspiranteDTO();

                dto.setIdExperienciaLaboralAspirante(entity.getIdExperienciaLaboral());

                dto.setIdAspirante(entity.getAspirante().getIdAspirante());

                dto.setNombreEmpresa(entity.getNombreEmpresa());
                dto.setPuesto(entity.getPuesto());

                //				String pattern = "yyyy-MM-dd";
                //				SimpleDateFormat format = new SimpleDateFormat(pattern);
                //
                //				Date date = format.parse("0000-00-00");
                //
                //				if (entity.getFechaInicial() != date) {
                dto.setFechaInicial(entity.getFechaInicial());
                //				}
                //
                //				if (entity.getFechaFinal() != date) {
                dto.setFechaFinal(entity.getFechaFinal());
                //				}

                dto.setDireccionEmpresa(entity.getNombreEmpresa());
                dto.setCorreoContacto(entity.getCorreoContacto());
                dto.setMotivoSeparacion(entity.getMotivoSeparacion());
                dto.setSueldoMensual(entity.getSueldoMensual());
                dto.setComentarios(entity.getComentarios());
                dto.setTelefono(entity.getTelefono());
                dto.setNombreJefe(entity.getNombreJefe());
                dto.setPuestoJefe(entity.getPuestoJefe());
                dto.setSolicitarInformacion(entity.getSolicitarInformacion());
                dto.setRazonNoSolicitar(entity.getRazonNoSolicitar());
                dto.setDireccionEmpresa(entity.getDireccionEmpresa());

                experienciasLaboralesDTO.add(dto);

            }
        } else {
            experienciasLaboralesDTO = new ArrayList<>();
        }

        return experienciasLaboralesDTO;
    }

}
