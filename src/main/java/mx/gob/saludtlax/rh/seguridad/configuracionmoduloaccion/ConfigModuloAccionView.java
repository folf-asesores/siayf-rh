/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ConfigModuloAccionView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3085159994580509525L;

    private List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccion = new ArrayList<>();

    private List<ConfiguracionModuloAccionDTO> filtroConfiguracionModuloAccion;

    /**
     * @return the listaConfiguracionModuloAccion
     */
    public List<ConfiguracionModuloAccionDTO> getListaConfiguracionModuloAccion() {
        return listaConfiguracionModuloAccion;
    }

    /**
     * @param listaConfiguracionModuloAccion
     *            the listaConfiguracionModuloAccion to set
     */
    public void setListaConfiguracionModuloAccion(
            List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccion) {
        this.listaConfiguracionModuloAccion = listaConfiguracionModuloAccion;
    }

    /**
     * @return the filtroConfiguracionModuloAccion
     */
    public List<ConfiguracionModuloAccionDTO> getFiltroConfiguracionModuloAccion() {
        return filtroConfiguracionModuloAccion;
    }

    /**
     * @param filtroConfiguracionModuloAccion
     *            the filtroConfiguracionModuloAccion to set
     */
    public void setFiltroConfiguracionModuloAccion(
            List<ConfiguracionModuloAccionDTO> filtroConfiguracionModuloAccion) {
        this.filtroConfiguracionModuloAccion = filtroConfiguracionModuloAccion;
    }

}
