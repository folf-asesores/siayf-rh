
package mx.gob.saludtlax.rh.catalogos.areasadscripcion;

import java.util.List;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.AreaAdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AreasAdscripcionRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class AreaAdscripcionService {

    @Inject
    private AreasAdscripcionRepository areasAdscripcionRepository;

    private static final Logger LOGGER = Logger
            .getLogger(AreaAdscripcionService.class.getName());

    //Comentado porque genera un error de NullPointerException (porque el
    //arreglo de chars no tiene nada).
    //private char[] clave;
    //String claves = String.valueOf(clave);

    protected void crearAreaAdscripcion(AreaAdscripcionDTO areaAdscripcionDTO) {
        String claves = "";

        //consulta que no exista un area con la clave
        boolean existe = areasAdscripcionRepository.existeClave(claves);

        // Si existe lanzar una excepcion (consulta con freddy la implementacion de las excepciones)
        if (existe) {
            throw new ReglaNegocioException("Ya existe una clave registrada.",
                    ReglaNegocioCodigoError.CLAVE_AREA_DUPLICADA);
        }
    }

    protected AreaAdscripcionDTO obtenerPorId(int idAreaAdscripcion) {
        if (ValidacionUtil.esMenorQueUno(idAreaAdscripcion)) {
            throw new ValidationException(
                    "El ID del área de adscripción a "
                            + "buscar no puede ser nulo o menor que uno.",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        AreaAdscripcionEntity entidad = areasAdscripcionRepository
                .obtenerPorId(idAreaAdscripcion);

        if (entidad != null) {
            return convertirEntidadADTO(entidad);
        } else {
            return null;
        }
    }

    // En base al filtro especificado crear la consulta correspondiente
    public List<InfoAreaAdscripcionDTO> consultarAreasAdscripcion(
            TipoFiltro filtro, Integer idFiltro) {
        return null;
    }

    private static AreaAdscripcionDTO convertirEntidadADTO(
            AreaAdscripcionEntity entidad) {
        AreaAdscripcionDTO dto = new AreaAdscripcionDTO();
        String centrosPago = entidad.getCentros_pago() == null ? ""
                : entidad.getCentros_pago().getCentro_pago();
        String clues = entidad.getClues() == null ? ""
                : entidad.getClues().getClues();

        dto.setIdAreaAdscripcion(entidad.getIdAreaAdscripcion());
        dto.setClave(entidad.getClave());
        dto.setCentros_pago(centrosPago);
        dto.setAreaAdscripcion(entidad.getAreaAdscripcion());
        dto.setClues(clues);

        return dto;
    }

    public List<AreaAdscripcionEntity> verAreas() {
        return areasAdscripcionRepository.listaAreas();
    }

    protected List<String> consultarAreaAdscripcionPorCriterio(
            String consulta) {
        return areasAdscripcionRepository
                .consultarAreaAdscripcionPorCriterio(consulta);
    }

    Integer consultarIdAreaAdscripcionPorDescripcion(String descripcion) {
        return areasAdscripcionRepository
                .consultarIdAreaAdscripcionPorDescripcion(descripcion);
    }
}
