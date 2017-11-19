/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.SalarioMinimoEntity;
import mx.gob.saludtlax.rh.persistencia.SalarioMinimoRepository;

/**
 * @author Eduardo Mex
 *
 */
public class SalarioMinimoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1076236767321363451L;

    @Inject
    private SalarioMinimoRepository salarioMinimoRepository;

    public void crearSalarioMinimo(SalarioMinimoDTO dto) {

        SalarioMinimoEntity entity = new SalarioMinimoEntity();

        entity.setFecha(dto.getFecha());
        entity.setSalarioMinimo(dto.getSalarioMinimo());

        salarioMinimoRepository.crear(entity);

    }

    public void actualizarSalarioMinimo(SalarioMinimoDTO dto) {

        SalarioMinimoEntity entity = salarioMinimoRepository.obtenerPorId(dto.getIdSalarioMinimo());

        entity.setFecha(dto.getFecha());
        entity.setSalarioMinimo(dto.getSalarioMinimo());

        salarioMinimoRepository.actualizar(entity);

    }

    public void eliminarSalarioMinimo(Integer idSalarioMinimo) {

        salarioMinimoRepository.eliminarPorId(idSalarioMinimo);

    }

    public List<SalarioMinimoDTO> obtenerListaSalarioMinimo() {

        List<SalarioMinimoDTO> dtos = new ArrayList<>();

        List<SalarioMinimoEntity> entities = salarioMinimoRepository.obtenerListaSalarioMinimo();

        if (!entities.isEmpty() || entities != null) {
            for (SalarioMinimoEntity salarioMinimoEntity : entities) {
                SalarioMinimoDTO dto = new SalarioMinimoDTO();

                dto.setIdSalarioMinimo(salarioMinimoEntity.getIdSalarioMinimo());
                dto.setFecha(salarioMinimoEntity.getFecha());
                dto.setSalarioMinimo(salarioMinimoEntity.getSalarioMinimo());

                dtos.add(dto);
            }
        }

        return dtos;
    }

}
