/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class SalarioMinimoEJB implements SalarioMinimo, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6984907591538696145L;

    @Inject
    private SalarioMinimoService salarioMinimoService;

    @Override
    public void crearSalarioMinimo(SalarioMinimoDTO dto) {
        salarioMinimoService.crearSalarioMinimo(dto);
    }

    @Override
    public void actualizarSalarioMinimo(SalarioMinimoDTO dto) {
        salarioMinimoService.actualizarSalarioMinimo(dto);
    }

    @Override
    public void eliminarSalarioMinimo(Integer idSalarioMinimo) {
        salarioMinimoService.eliminarSalarioMinimo(idSalarioMinimo);
    }

    @Override
    public List<SalarioMinimoDTO> obtenerListaSalarioMinimo() {
        return salarioMinimoService.obtenerListaSalarioMinimo();
    }

}
