/*
 *
 */

package mx.gob.saludtlax.rh.contrato;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author eduardo Mex
 *
 */
@Stateless
public class ContratoEJB implements Contrato {

    @Inject
    private ContratoService contratoService;

    @Override
    public ContratoDTO obtenerContratoEmpleadoPorIdContrato(Integer idContrato) {

        return contratoService.obtenerContratoEmpleadoPorIdContrato(idContrato);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.contrato.Contrato#listaContrato()
     */
    @Override
    public List<ContratoDTO> listaContrato() {

        return contratoService.listaContrato();
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.contrato.Contrato#actualizarContratoPorImpresion(java.lang.Integer, java.lang.String)
     */
    @Override
    public void actualizarContratoPorImpresion(Integer idContrato, String numeroContrato) {
        contratoService.actualizarContratoPorImpresion(idContrato, numeroContrato);

    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.contrato.Contrato#obtenerListaContratoPorTipo(java.lang.Integer)
     */
    @Override
    public List<ContratoDTO> obtenerListaContratoPorTipo(Integer tipoContrato) {

        return contratoService.obtenerListaContratoPorTipo(tipoContrato);
    }

}
