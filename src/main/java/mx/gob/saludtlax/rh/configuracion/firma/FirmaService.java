/*
 * FirmaService.java
 * Creado el 15/Nov/2016 5:12:07 PM
 *
 */

package mx.gob.saludtlax.rh.configuracion.firma;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.AdscripcionEntity;
import mx.gob.saludtlax.rh.persistencia.AdscripcionRepository;
import mx.gob.saludtlax.rh.persistencia.FirmaEntity;
import mx.gob.saludtlax.rh.persistencia.FirmaRepository;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaService {

    @Inject
    private FirmaRepository firmaRepository;
    @Inject
    private AdscripcionRepository adscripcionRepository;

    protected Integer crear(FirmaDTO dto) {
        FirmaEntity entidad = convertirDtoAEntidad(dto);
        firmaRepository.crear(entidad);

        return entidad.getIdFirma();
    }

    protected FirmaDTO obtenerPorId(Integer idFirma) {
        return convertirEntidadADto(firmaRepository.obtenerPorId(idFirma));
    }

    protected List<FirmaDTO> consultarTodas() {
        List<FirmaEntity> entidades = firmaRepository.consultarTodos();
        List<FirmaDTO> dtos = new ArrayList<>();

        for (FirmaEntity enidad : entidades) {
            FirmaDTO dto = convertirEntidadADto(enidad);

            dtos.add(dto);
        }

        return dtos;
    }

    protected void actualizar(FirmaDTO dto) {
        FirmaEntity entidad = convertirDtoAEntidad(dto, firmaRepository.obtenerPorId(dto.getIdFirma()));
        firmaRepository.actualizar(entidad);
    }

    protected void eliminar(Integer idFirma) {
        firmaRepository.eliminarPorId(idFirma);
    }

    private FirmaEntity convertirDtoAEntidad(FirmaDTO dto) {
        return convertirDtoAEntidad(dto, null);
    }

    private FirmaEntity convertirDtoAEntidad(FirmaDTO dto, FirmaEntity entidad) {
        if (entidad == null) {
            entidad = new FirmaEntity();
        }

        AdscripcionEntity adscripcion = adscripcionRepository.obtenerPorId(dto.getIdAdscripcion());

        entidad.setAdscripcion(adscripcion);
        entidad.setNombre(dto.getNombre());
        entidad.setCargo(dto.getCargo());
        entidad.setNivel(dto.getNivel());

        return entidad;
    }

    private FirmaDTO convertirEntidadADto(FirmaEntity entidad) {
        FirmaDTO dto = new FirmaDTO();
        dto.setIdFirma(entidad.getIdFirma());
        dto.setIdAdscripcion(entidad.getAdscripcion().getIdAdscripcion());
        dto.setAdscripcion(entidad.getAdscripcion().getAdscripcion());
        dto.setNombre(entidad.getNombre());
        dto.setCargo(entidad.getCargo());
        dto.setNivel(entidad.getNivel());

        return dto;
    }

}
