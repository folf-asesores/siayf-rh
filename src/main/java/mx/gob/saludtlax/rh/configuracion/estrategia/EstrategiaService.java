/*
 *
 * EstrategiaService.java
 * Creado el Jul 12, 2016 10:44:34 AM
 *
 */

package mx.gob.saludtlax.rh.configuracion.estrategia;

import static mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError.CODIGO_ESTRATEGIA_DUPLICADO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.EstrategiaEntity;
import mx.gob.saludtlax.rh.persistencia.EstrategiaRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class EstrategiaService {

    @Inject
    private EstrategiaRepository estrategiaRepository;

    protected int crear(EstrategiaDTO dto) {
        if (dto.getCodigoEstrategia() < 1) {
            throw new ValidationException(
                    "El código de la estrategia no puede ser negativo",
                    ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (estrategiaRepository
                .existeCodigoEstrategia(dto.getCodigoEstrategia())) {
            throw new ReglaNegocioException(
                    "Ya existe un código de estrategia registrado, con el mismo código.",
                    CODIGO_ESTRATEGIA_DUPLICADO);
        }

        EstrategiaEntity entidad = convertirDTOAEntidad(dto);
        entidad.setIdEstrategia(null);
        entidad = estrategiaRepository.crear(entidad);

        return entidad.getIdEstrategia();
    }

    protected void actualizar(EstrategiaDTO dto) {
        EstrategiaEntity entidad = convertirDTOAEntidad(dto);
        estrategiaRepository.actualizar(entidad);
    }

    protected EstrategiaDTO obtenerPorId(int idEstrategia) {
        EstrategiaEntity entidad = estrategiaRepository
                .obtenerPorId(idEstrategia);

        return convertirEntidadADTO(entidad);
    }

    protected List<EstrategiaDTO> consultarEstrategias() {
        List<EstrategiaEntity> listaEntidades = estrategiaRepository
                .consultarEstrategias();

        return convetirListaEntidadesAListaDTOs(listaEntidades);
    }

    protected List<String> consultarDescripcionEstrategiaPorCriterio(
            String criterio) {
        return estrategiaRepository
                .consultarDescripcionEstrategiaPorCriterio(criterio);
    }

    protected Integer consultarIdEstrategiaPorDescripcion(String descripcion) {
        return estrategiaRepository
                .consultarIdEstrategiaPorDescripcion(descripcion);
    }

    protected void eliminar(int idEstrategia) {
        estrategiaRepository.eliminarPorId(idEstrategia);
    }

    public boolean existeCodigoEstrategia(int codigoEstrategia) {
        return estrategiaRepository.existeCodigoEstrategia(codigoEstrategia);
    }

    private static EstrategiaEntity convertirDTOAEntidad(EstrategiaDTO dto) {
        EstrategiaEntity entidad = new EstrategiaEntity();

        entidad.setIdEstrategia(dto.getIdEstrategia());
        entidad.setCodigoEstrategia(dto.getCodigoEstrategia());
        entidad.setEstrategia(dto.getEstrategia());

        return entidad;
    }

    private EstrategiaDTO convertirEntidadADTO(EstrategiaEntity entidad) {
        EstrategiaDTO dto = new EstrategiaDTO();

        dto.setIdEstrategia(entidad.getIdEstrategia());
        dto.setCodigoEstrategia(entidad.getCodigoEstrategia());
        dto.setEstrategia(entidad.getEstrategia());

        return dto;
    }

    private List<EstrategiaDTO> convetirListaEntidadesAListaDTOs(
            List<EstrategiaEntity> listaEntidades) {
        List<EstrategiaDTO> listaDTOs = new ArrayList<>();

        for (EstrategiaEntity entidad : listaEntidades) {
            EstrategiaDTO dto = convertirEntidadADTO(entidad);

            listaDTOs.add(dto);
        }

        return listaDTOs;
    }

}
