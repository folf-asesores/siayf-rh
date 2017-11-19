/*
 *
 */

package mx.gob.saludtlax.rh.empleado.issste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoIsssteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoIsssteEmpleadoRepository;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class IsssteConsultaService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7636177516927670151L;

    @Inject
    private MovimientoIsssteEmpleadoRepository movimientoIsssteEmpleadoRepository;

    protected boolean existeEmpleado(Integer idEmpleado) {

        return movimientoIsssteEmpleadoRepository.existeEmpleado(idEmpleado);
    }

    protected List<InfoMovimientoIsssteDTO> obtenerListaMovimientoPorCriterio(
            String criterio) {

        List<InfoMovimientoIsssteDTO> dtos = new ArrayList<>();

        List<MovimientoIsssteEmpleadoEntity> lista = movimientoIsssteEmpleadoRepository
                .obtenerListaMovimientoPorCriterio(criterio);

        if (!lista.isEmpty()) {

            for (MovimientoIsssteEmpleadoEntity movimientoIsssteEmpleadoEntity : lista) {
                InfoMovimientoIsssteDTO dto = new InfoMovimientoIsssteDTO();

                dto.setIdMovimientoIsssteEmpleado(movimientoIsssteEmpleadoEntity
                        .getIdMovimientoIsssteEmpleado());
                dto.setRfc(
                        movimientoIsssteEmpleadoEntity.getEmpleado().getRfc());
                dto.setNombreEmpleado(movimientoIsssteEmpleadoEntity
                        .getEmpleado().getNombreCompleto());
                dto.setTipoMovimientoIssste(movimientoIsssteEmpleadoEntity
                        .getTipoMovimientoIssste().getTipoMovimientoIssste());
                dto.setIdTipoMovimientoIssste(
                        movimientoIsssteEmpleadoEntity.getTipoMovimientoIssste()
                                .getIdTipoMovimientoIsssteEmpleado());
                dto.setNumeroSeguroSocial(movimientoIsssteEmpleadoEntity
                        .getEmpleado().getNumeroSeguroSocial());
                dto.setSueldoIssste(
                        movimientoIsssteEmpleadoEntity.getSueldoIssste());
                dto.setSueldoSar(movimientoIsssteEmpleadoEntity.getSueldoSar());
                dto.setTotalRemuneracion(
                        movimientoIsssteEmpleadoEntity.getTotalRemuneracion());
                dto.setCurp(
                        movimientoIsssteEmpleadoEntity.getEmpleado().getCurp());
                dto.setDomicilio(movimientoIsssteEmpleadoEntity.getEmpleado()
                        .getDireccionCompleta());
                dto.setClaveCobro(movimientoIsssteEmpleadoEntity.getEmpleado()
                        .getClaveCobro());
                dto.setNombramiento(
                        movimientoIsssteEmpleadoEntity.getNombramiento());
                if (movimientoIsssteEmpleadoEntity.getCausaBaja() != null) {
                    dto.setIdCausaBaja(movimientoIsssteEmpleadoEntity
                            .getCausaBaja().getIdCausaBaja());
                }

                dtos.add(dto);

            }

        }
        return dtos;
    }

}
