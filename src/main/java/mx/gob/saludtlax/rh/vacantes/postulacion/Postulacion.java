/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.util.List;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/10/2016 12:38:51
 */
public interface Postulacion {

    /**
     * Consulta la lista de puestos disponibles.
     *
     */

    public List<PuestoDisponibleDTO> consultarPuestosDisponibles();

    /**
     * Postula un candidato en la vacante disponible.
     *
     * @param dto
     */
    public Integer postularCandidato(PostulacionDTO dto);

    /**
     * Consulta los candidatos postulados,
     */
    public List<InfoCandidatoDTO> consultarCandidatosPostulacion(Integer idPostulacion);

    /**
     * Consulta el detalle del candidato postulado a una vacante.
     */
    public InfoCandidatoDTO obtenerInformacionCandidatoAprobado(Integer idVacante);

    /**
     * Consulta las postulaciones disponibles para aprobar un candidato.
     */
    public List<InfoPostulacionDTO> consultarPostulacionesDisponibles();

    /**
     * Obtiene la postulación activa del puesto.
     *
     * @param idPuesto
     */
    public Integer obtenerIdPostulacionActiva(Integer idPuesto);

    /**
     * Aprueba al candidato seleccionado para ocupar la vacante
     */
    public void aprobarCandidatoPostulacion(Integer idPostulacion, Integer idCandidato);

}
