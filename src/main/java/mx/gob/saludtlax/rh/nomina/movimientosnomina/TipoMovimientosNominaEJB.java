
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaRepository;

@Stateless
public class TipoMovimientosNominaEJB {

    @Inject
    TiposMovimientosNominaRepository tiposMovimientosNominaRepository;

    public List<TipoMovimientoNominaDTO> obtenerListaMovimientos() {
        List<TiposMovimientosNominaEntity> list = new ArrayList<>();
        list = tiposMovimientosNominaRepository.consultarTodos();

        List<TipoMovimientoNominaDTO> listDtos = new ArrayList<>();
        for (TiposMovimientosNominaEntity en : list) {
            TipoMovimientoNominaDTO dto = new TipoMovimientoNominaDTO();
            dto.setClave(en.getClave());
            dto.setDescripcion(en.getDescripcion());
            dto.setIdTimpoMovimiento(en.getIdMovimientoNomina());
            dto.setFormaRegistro(en.getFormaRegistro());
            dto.setEsMovimiento(en.getEsMovimiento());
            dto.setIdPadre(en.getIdPadre());
            listDtos.add(dto);
        }

        return listDtos;

    }

    public TipoMovimientoNominaDTO obtenerTipoMovimientoPorId(Integer id) {

        TiposMovimientosNominaEntity en = tiposMovimientosNominaRepository.obtenerPorId(id);
        TipoMovimientoNominaDTO dto = new TipoMovimientoNominaDTO();
        dto.setClave(en.getClave());
        dto.setDescripcion(en.getDescripcion());
        dto.setIdTimpoMovimiento(en.getIdMovimientoNomina());
        dto.setFormaRegistro(en.getFormaRegistro());
        dto.setEsMovimiento(en.getEsMovimiento());
        dto.setIdPadre(en.getIdPadre());
        return dto;
    }

    public TipoMovimientoNominaDTO obtenerTipoMovimientoPorClave(String clave) {

        TipoMovimientoNominaDTO dto = new TipoMovimientoNominaDTO();
        dto = tiposMovimientosNominaRepository.obtenerMovimientoPorClave(clave);
        return dto;
    }

}
