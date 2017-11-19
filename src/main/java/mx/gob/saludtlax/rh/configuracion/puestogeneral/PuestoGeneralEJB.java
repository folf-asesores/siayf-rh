/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.puestogeneral;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 21/07/2016 13:40:39
 */
@Stateless
public class PuestoGeneralEJB implements PuestoGeneral, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6480325997869609984L;

    @Inject
    private PuestoGeneralService puestoGeneralService;

    @Override
    public List<PuestoGeneralDTO> consultarListaPuestoGeneral() {
        return puestoGeneralService.consultarListaPuestoGeneral();
    }

    @Override
    public void crearPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO) {
        puestoGeneralService.crearPuestoGeneral(puestoGeneralDTO);
    }

    @Override
    public void actualizarPuestoGeneral(PuestoGeneralDTO puestoGeneralDTO) {
        puestoGeneralService.actualizarPuestoGeneral(puestoGeneralDTO);
    }

    @Override
    public void eliminarPuestoGeneral(Integer idPuestoGeneral) {
        puestoGeneralService.eliminarPuestoGeneral(idPuestoGeneral);
    }

    @Override
    public Boolean existeCodigo(String codigo) {
        return puestoGeneralService.existeCodigo(codigo);
    }

    @Override
    public Boolean existeCodigoIdPuesto(Integer idPuestoGeneral,
            String codigo) {
        return puestoGeneralService.existeCodigoIdPuesto(idPuestoGeneral,
                codigo);
    }

    @Override
    public PuestoGeneralDTO puestoPorClave(String clave) {

        return puestoGeneralService.puestoPorClave(clave);
    }

    @Override
    public PuestoGeneralDTO puestoPorId(Integer id) {

        return puestoGeneralService.puestoPorId(id);
    }

}
