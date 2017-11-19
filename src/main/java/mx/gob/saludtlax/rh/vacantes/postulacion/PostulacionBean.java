/*
 *
 */

package mx.gob.saludtlax.rh.vacantes.postulacion;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 24/10/2016 12:39:03
 */
@Stateless
public class PostulacionBean implements Postulacion {
    @Inject
    private PostulacionVacanteService postulacionVacanteService;

    @Override
    public Integer postularCandidato(PostulacionDTO dto) {
        return postulacionVacanteService.postularCandidato(dto);

    }

    @Override
    public List<InfoCandidatoDTO> consultarCandidatosPostulacion(
            Integer idPostulacion) {
        return postulacionVacanteService
                .consultarCandidatosPostulacion(idPostulacion);
    }

    @Override
    public InfoCandidatoDTO obtenerInformacionCandidatoAprobado(
            Integer idVacante) {
        return postulacionVacanteService.obtenerInformacionCandidato(idVacante);
    }

    @Override
    public List<InfoPostulacionDTO> consultarPostulacionesDisponibles() {

        return postulacionVacanteService.consultarPostulacionesDisponibles();
    }

    @Override
    public Integer obtenerIdPostulacionActiva(Integer idPuesto) {
        return postulacionVacanteService.obtenerIdPostulacionActiva(idPuesto);
    }

    @Override
    public void aprobarCandidatoPostulacion(Integer idPostulacion,
            Integer idCandidato) {
        postulacionVacanteService.aprobarCandidatoPostulacion(idPostulacion,
                idCandidato);

    }

    @Override
    public List<PuestoDisponibleDTO> consultarPuestosDisponibles() {
        return postulacionVacanteService.consultarPuestosDisponibles();
    }

}
