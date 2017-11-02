/*
 * NominaMandoMedioService.java
 * Creado el 29/Nov/2016 2:10:42 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.persistencia.AdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaMandoMedioEntity;
import mx.gob.saludtlax.rh.persistencia.NominaMandoMedioRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;

/**
 * Esta clase sirve de ayudante del EJB {@link NominaMandoMedioEJB} para 
 * realizar las operaciones de negocios de las nominas de mandos medios.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NominaMandoMedioService {
    
    @Inject private AdscripcionRepository adscripcionRepository;
    @Inject private EmpleadoRepository empleadoRepository;
    @Inject private NominaMandoMedioRepository mandoMedioRepository;
    @Inject private PuestoGeneralRepository puestoGeneralRepository;

    protected void crear(NominaMandoMedioDTO dto) {
        NominaMandoMedioEntity entidad = convertirDtoAEntidad(dto);
        mandoMedioRepository.crear(entidad);
    }

    protected NominaMandoMedioDTO obtenerPorId(Integer id) {
        NominaMandoMedioDTO dto = convertirEntidadADto(mandoMedioRepository.obtenerPorId(id));
        return dto;
    }

    protected List<NominaMandoMedioDTO> consultarTodos() {
        List<NominaMandoMedioEntity> entidades = mandoMedioRepository.consultarTodos();
        
        return convertirEntidadesADtos(entidades);
    }

    protected void actualizar(NominaMandoMedioDTO dto) {
        NominaMandoMedioEntity entidad = mandoMedioRepository.obtenerPorId(dto.getIdNominaMandoMedio());
        entidad = convertirDtoAEntidad(dto, entidad);
        mandoMedioRepository.actualizar(entidad);
    }

    protected void eliminar(Integer id) {
        mandoMedioRepository.eliminarPorId(id);
    }

    private NominaMandoMedioEntity convertirDtoAEntidad(NominaMandoMedioDTO dto) {
        return convertirDtoAEntidad(dto, null);
    }
    
    private NominaMandoMedioEntity convertirDtoAEntidad(NominaMandoMedioDTO dto, NominaMandoMedioEntity entidad) {
        if(entidad == null) {
            entidad = new NominaMandoMedioEntity();
        }

        AdscripcionEntity adscripcion = adscripcionRepository.obtenerPorId(dto.getIdAdscripcion());
        EmpleadoEntity empleado = empleadoRepository.obtenerPorId(dto.getIdEmpleado());
        PuestoGeneralEntity puestoGeneral = puestoGeneralRepository.obtenerPorId(dto.getIdPuestoGeneral());
        
        entidad.setAdscripcion(adscripcion);
        entidad.setEmpleado(empleado);
        entidad.setPuestoGeneral(puestoGeneral);
        entidad.setPuesto(dto.getPuesto());
        entidad.setDescripcionCodigo(dto.getDescripcionCodigo());
        entidad.setTipoContratacion(dto.getTipoContratacion());
        entidad.setComplemento(dto.getComplemento());
        entidad.setIsr(dto.getIsr());
        entidad.setNeto(dto.getNeto());

        return entidad;
    }

    private NominaMandoMedioDTO convertirEntidadADto(NominaMandoMedioEntity entidad) {
        if(entidad == null) {
            return null;
        }

        NominaMandoMedioDTO dto = new NominaMandoMedioDTO();
        dto.setIdNominaMandoMedio(entidad.getIdNominaMandoMedio());
        dto.setIdAdscripcion(entidad.getAdscripcion().getIdAdscripcion());
        dto.setAdscripcion(entidad.getAdscripcion().getAdscripcion());
        dto.setIdEmpleado(entidad.getEmpleado().getIdEmpleado());
        dto.setNombreEmpleado(entidad.getEmpleado().getNombreCompleto());
        dto.setRfc(entidad.getEmpleado().getRfc());
        dto.setPuesto(entidad.getPuesto());
        dto.setIdPuestoGeneral(entidad.getPuestoGeneral().getIdPuestoGeneral());
        dto.setCodigoFuncional(entidad.getPuestoGeneral().getCodigo());
        dto.setDescripcionCodigo(entidad.getDescripcionCodigo());
        dto.setTipoContratacion(entidad.getTipoContratacion());
        dto.setNeto(entidad.getNeto());
        dto.setIsr(entidad.getIsr());
        dto.setComplemento(entidad.getComplemento());

        return dto;
    }

    private List<NominaMandoMedioDTO> convertirEntidadesADtos(List<NominaMandoMedioEntity> entidades) {
        List<NominaMandoMedioDTO> dtos = new ArrayList<>();

        for (NominaMandoMedioEntity entidad : entidades) {
            dtos.add(convertirEntidadADto(entidad));
        }

        return dtos;
    }

}
