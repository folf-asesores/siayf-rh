/*
 *
 */

package mx.gob.saludtlax.rh.empleado.issste;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class IsssteEJB implements Issste, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9143771386457625196L;

    @Inject
    private IsssteService isssteService;
    @Inject
    private IsssteConsultaService isssteConsultaService;

    @Override
    public Integer altaIsssteMovimiento(
            MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

        return isssteService.altaIsssteMovimiento(movimientoIsssteEmpleadoDTO);
    }

    @Override
    public void modificacionIsssteMovimiento(
            MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

        isssteService.modificacionIsssteMovimiento(movimientoIsssteEmpleadoDTO);
    }

    @Override
    public void bajaIsssteMovimiento(
            MovimientoIsssteEmpleadoDTO movimientoIsssteEmpleadoDTO) {

        isssteService.bajaIsssteMovimiento(movimientoIsssteEmpleadoDTO);
    }

    @Override
    public boolean existeEmpleado(Integer idEmpleado) {

        return isssteConsultaService.existeEmpleado(idEmpleado);
    }

    @Override
    public List<InfoMovimientoIsssteDTO> obtenerListaMovimientoPorCriterio(
            String criterio) {

        return isssteConsultaService
                .obtenerListaMovimientoPorCriterio(criterio);
    }

}
