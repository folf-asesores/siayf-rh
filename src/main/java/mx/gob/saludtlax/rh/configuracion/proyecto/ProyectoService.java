/*
 * ProyectoService.java
 * Creado el 23/07/2016 09:40:16 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.proyecto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempEntity;
import mx.gob.saludtlax.rh.persistencia.ProyectoTempRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ProyectoService {

    @Inject
    private ProyectoTempRepository proyectoRepository;

    protected ProyectoDTO obtenerPorId(int idProyecto) {
        ProyectoTempEntity entidad = proyectoRepository.obtenerPorId(idProyecto);

        return convertirEntidadADTO(entidad);
    }

    protected int crear(ProyectoDTO dto) {
        if (dto == null) {
            throw new ValidacionException("Proyecto no debe ser nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(dto.getNombre())) {
            throw new ValidacionException("Proyecto no debe ser nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esMenorQueUno(dto.getEjercicioFiscal())) {
            throw new ValidacionException("El ejercicio fiscal no puede ser" + " nulo, cero o negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        if (proyectoRepository.existeProyectoEnEjercicioFiscal(dto.getNombre(), dto.getEjercicioFiscal())) {
            throw new ReglaNegocioException("Ya existe un proyecto con el mismo" + " nombre en el ejercicio fiscal (" + dto.getEjercicioFiscal() + ").",
                    ReglaNegocioCodigoError.PROYECTO_DUPLICADO);
        }

        ProyectoTempEntity entidad = convertirDTOAEntidad(dto);
        entidad.setIdProyecto(null);
        ProyectoTempEntity proyecto = proyectoRepository.crear(entidad);

        return proyecto.getIdProyecto();
    }

    protected void actualizar(ProyectoDTO dto) {
        ProyectoTempEntity entidad = convertirDTOAEntidad(dto);
        proyectoRepository.actualizar(entidad);
    }

    protected List<ProyectoDTO> consultarProyectosPorEjercicioFiscal(int ejercicioFiscal) {
        List<ProyectoTempEntity> entidades = proyectoRepository.consultarProyectosPorEjercicioFiscal(ejercicioFiscal);

        return convertirEntidadesADTOs(entidades);
    }

    protected void eliminar(int idProyecto) {
        proyectoRepository.eliminarPorId(idProyecto);
    }

    private static ProyectoDTO convertirEntidadADTO(ProyectoTempEntity entidad) {
        ProyectoDTO dto = new ProyectoDTO();

        dto.setIdProyecto(entidad.getIdProyecto());
        dto.setIdDependencia(entidad.getIdDependencia());
        dto.setIdUnidadResponsable(entidad.getIdUnidadResponsable());

        return dto;
    }

    private ProyectoTempEntity convertirDTOAEntidad(ProyectoDTO dto) {
        ProyectoTempEntity entidad = new ProyectoTempEntity();

        entidad.setIdProyecto(dto.getIdProyecto());
        entidad.setIdDependencia(dto.getIdDependencia());
        entidad.setIdUnidadResponsable(dto.getIdUnidadResponsable());

        return entidad;
    }

    private List<ProyectoDTO> convertirEntidadesADTOs(List<ProyectoTempEntity> entidades) {
        List<ProyectoDTO> dtos = new ArrayList<>();

        for (ProyectoTempEntity entidad : entidades) {
            ProyectoDTO dto = convertirEntidadADTO(entidad);
            dtos.add(dto);
        }

        return dtos;
    }
}
