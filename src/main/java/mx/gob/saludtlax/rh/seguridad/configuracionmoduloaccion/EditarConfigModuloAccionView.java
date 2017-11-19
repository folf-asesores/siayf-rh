/*
 *
 */

package mx.gob.saludtlax.rh.seguridad.configuracionmoduloaccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class EditarConfigModuloAccionView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -271938671961676780L;

    private DualListModel<AccionDTO> pikListAcciones = new DualListModel<>();

    private List<ModuloDTO> listaModulos = new ArrayList<>();

    private ConfiguracionModuloAccionDTO configModuloAccionEditar = new ConfiguracionModuloAccionDTO();

    private Integer idConfigModuloAccion;

    private Integer idModuloConparator;

    private boolean panelPrincipal;

    /**
     * @return the idConfigModuloAccion
     */
    public Integer getIdConfigModuloAccion() {
        return idConfigModuloAccion;
    }

    public List<ModuloDTO> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<ModuloDTO> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public ConfiguracionModuloAccionDTO getConfigModuloAccionEditar() {
        return configModuloAccionEditar;
    }

    public void setConfigModuloAccionEditar(
            ConfiguracionModuloAccionDTO configModuloAccionEditar) {
        this.configModuloAccionEditar = configModuloAccionEditar;
    }

    /**
     * @param idConfigModuloAccion
     *            the idConfigModuloAccion to set
     */
    public void setIdConfigModuloAccion(Integer idConfigModuloAccion) {
        this.idConfigModuloAccion = idConfigModuloAccion;
    }

    /**
     * @return the pikListAcciones
     */
    public DualListModel<AccionDTO> getPikListAcciones() {
        return pikListAcciones;
    }

    /**
     * @param pikListAcciones
     *            the pikListAcciones to set
     */
    public void setPikListAcciones(DualListModel<AccionDTO> pikListAcciones) {
        this.pikListAcciones = pikListAcciones;
    }

    /**
     * @return the panelPrincipal
     */
    public boolean isPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * @param panelPrincipal
     *            the panelPrincipal to set
     */
    public void setPanelPrincipal(boolean panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * @return the idModuloConparator
     */
    public Integer getIdModuloConparator() {
        return idModuloConparator;
    }

    /**
     * @param idModuloConparator
     *            the idModuloConparator to set
     */
    public void setIdModuloConparator(Integer idModuloConparator) {
        this.idModuloConparator = idModuloConparator;
    }

}
