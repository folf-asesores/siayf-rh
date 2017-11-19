/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.salariominimo;

import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface SalarioMinimo {

    public void crearSalarioMinimo(SalarioMinimoDTO dto);

    public void actualizarSalarioMinimo(SalarioMinimoDTO dto);

    public void eliminarSalarioMinimo(Integer idSalarioMinimo);

    public List<SalarioMinimoDTO> obtenerListaSalarioMinimo();

}
