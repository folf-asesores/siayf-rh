/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.riesgopuesto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.persistencia.RiesgoPuestoEntity;
import mx.gob.saludtlax.rh.persistencia.RiesgoPuestoRepository;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 07/06/2016 19:04:22
 */
public class RiesgoPuestoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5817829341056017550L;

    @Inject
    private RiesgoPuestoRepository riesgoPuestoRepository;

    protected void crearRiesgoPuesto(RiesgoPuestoDTO dto) {

        String contexto = "Registro Riesgo Puesto: ";

        if (dto.getClave() == null) {
            throw new BusinessException(
                    contexto + "la clave es necesario, ingrese la clave");
        }

        Boolean validarClave = riesgoPuestoRepository
                .validarClave(dto.getClave());

        if (validarClave) {
            throw new BusinessException(contexto
                    + "la clave ya se encuetra registrada, ingrese otra clave");
        }

        RiesgoPuestoEntity riesgoPuestoEntity = new RiesgoPuestoEntity();

        riesgoPuestoEntity.setClave(dto.getClave());
        riesgoPuestoEntity
                .setDescripcionRiesgoPuesto(dto.getDescripcionRiesgoPuesto());

        riesgoPuestoRepository.crear(riesgoPuestoEntity);

    }

    protected void actualizarRiesgoPuesto(RiesgoPuestoDTO dto) {

        RiesgoPuestoEntity riesgoPuestoEntity = riesgoPuestoRepository
                .obtenerPorId(dto.getIdRiesgoPuesto());

        riesgoPuestoEntity.setClave(dto.getClave());
        riesgoPuestoEntity
                .setDescripcionRiesgoPuesto(dto.getDescripcionRiesgoPuesto());

        riesgoPuestoRepository.actualizar(riesgoPuestoEntity);

    }

    protected void eliminarRiesgoPuesto(Integer idRiesgoPuesto) {
        RiesgoPuestoEntity riesgoPuestoEntity = riesgoPuestoRepository
                .obtenerPorId(idRiesgoPuesto);

        riesgoPuestoRepository.eliminar(riesgoPuestoEntity);
    }

    protected List<RiesgoPuestoDTO> obtenerListaRiesgoPuesto() {
        List<RiesgoPuestoDTO> listaRiesgoPuestoDTOs = new ArrayList<>();

        List<RiesgoPuestoEntity> obtenerRiesgoPuestoEntities = riesgoPuestoRepository
                .obtenerListaRiesgoPuesto();

        if (!obtenerRiesgoPuestoEntities.isEmpty()) {
            for (RiesgoPuestoEntity riesgoPuestoEntity : obtenerRiesgoPuestoEntities) {
                RiesgoPuestoDTO dto = new RiesgoPuestoDTO();

                dto.setIdRiesgoPuesto(riesgoPuestoEntity.getIdRiesgoPuesto());
                dto.setClave(riesgoPuestoEntity.getClave());
                dto.setDescripcionRiesgoPuesto(
                        riesgoPuestoEntity.getDescripcionRiesgoPuesto());

                listaRiesgoPuestoDTOs.add(dto);
            }

        } else {
            listaRiesgoPuestoDTOs = new ArrayList<>();
        }

        return listaRiesgoPuestoDTOs;
    }

}
