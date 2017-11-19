
package mx.gob.saludtlax.rh.contrato;

import java.util.List;

/**
 *
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 14:35:04 09/09/2016
 */
public interface Contrato {

    public ContratoDTO obtenerContratoEmpleadoPorIdContrato(Integer idContrato);

    public List<ContratoDTO> listaContrato();

    public List<ContratoDTO> obtenerListaContratoPorTipo(Integer tipoContrato);

    public void actualizarContratoPorImpresion(Integer idContrato,
            String numeroContrato);

}
