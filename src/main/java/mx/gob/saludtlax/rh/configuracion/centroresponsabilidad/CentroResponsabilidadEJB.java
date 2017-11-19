
package mx.gob.saludtlax.rh.configuracion.centroresponsabilidad;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author José Pablo
 *
 */
@Stateless
public class CentroResponsabilidadEJB {
    @Inject
    private CentroResponsabilidadService centroResponsabilidadService;

    public List<CentroResponsabilidadDTO> obtenerCentroresponsabilidadLista() {
        List<CentroResponsabilidadDTO> centroResponsabilidadLista = centroResponsabilidadService.listaCentroResponsabilidad();
        return centroResponsabilidadLista;
    }

    public CentroResponsabilidadDTO obtenerCuentaBancaria(CentroResponsabilidadDTO centroResponsabilidad) {
        CentroResponsabilidadDTO dto = centroResponsabilidadService.obtenerCentroResponsabilidadPorId(centroResponsabilidad.getIdCentroResponsabilidad());
        return dto;
    }

    public CentroResponsabilidadDTO nuevoCentroresponsabilidad() {
        return centroResponsabilidadService.nuevoCentroresponsabilidad();
    }

    public CentroResponsabilidadDTO crearCentroResponsabilidad(CentroResponsabilidadDTO dto) {
        return centroResponsabilidadService.crearCentroResponsabilidad(dto);
    }

    public CentroResponsabilidadDTO actualizarCentroResponsabilidad(CentroResponsabilidadDTO dto) {
        return centroResponsabilidadService.actualizarCentroResponsabilidad(dto);
    }

    public void eliminarCentroresponsabilidad(Integer idCentroResponsabilidad) {
        centroResponsabilidadService.eliminarCentroResponsabilidad(idCentroResponsabilidad);
    }
}
