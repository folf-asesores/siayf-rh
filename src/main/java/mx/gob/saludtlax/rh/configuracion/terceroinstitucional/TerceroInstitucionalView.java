/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.configuracion.terceroinstitucional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 24/05/2016 12:28:38
 */
public class TerceroInstitucionalView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4495735069141625115L;

    private TerceroInstitucionalDTO terceroInstitucional = new TerceroInstitucionalDTO();

    private List<TerceroInstitucionalDTO> listaTerceroInstitucional = new ArrayList<>();

    private String accionButton = "Registrar";

    /**
     * @return the terceroInstitucional
     */
    public TerceroInstitucionalDTO getTerceroInstitucional() {
        return terceroInstitucional;
    }

    /**
     * @param terceroInstitucional
     *            the terceroInstitucional to set
     */
    public void setTerceroInstitucional(TerceroInstitucionalDTO terceroInstitucional) {
        this.terceroInstitucional = terceroInstitucional;
    }

    /**
     * @return the listaTerceroInstitucional
     */
    public List<TerceroInstitucionalDTO> getListaTerceroInstitucional() {
        return listaTerceroInstitucional;
    }

    /**
     * @param listaTerceroInstitucional
     *            the listaTerceroInstitucional to set
     */
    public void setListaTerceroInstitucional(List<TerceroInstitucionalDTO> listaTerceroInstitucional) {
        this.listaTerceroInstitucional = listaTerceroInstitucional;
    }

    public String getAccionButton() {
        return accionButton;
    }

    public void setAccionButton(String accionButton) {
        this.accionButton = accionButton;
    }

}
