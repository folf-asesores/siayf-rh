/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.profesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 10/03/2016 11:09:17
 */
public class ProfesionView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2244593490789555305L;

    private List<ProfesionDTO> listaProfesion = new ArrayList<>();
    private ProfesionDTO crearProfesion = new ProfesionDTO();
    private ProfesionDTO seleccionarProfesion = new ProfesionDTO();

    private Boolean habilitarOpciones = Boolean.TRUE;
    private Boolean dialogCrear = Boolean.FALSE;
    private Boolean dialogActualizar = Boolean.FALSE;
    private Boolean dialogEliminar = Boolean.FALSE;

    /**
     * @return the listaProfesion
     */
    public List<ProfesionDTO> getListaProfesion() {
        return listaProfesion;
    }

    /**
     * @param listaProfesion
     *            the listaProfesion to set
     */
    public void setListaProfesion(List<ProfesionDTO> listaProfesion) {
        this.listaProfesion = listaProfesion;
    }

    /**
     * @return the crearProfesion
     */
    public ProfesionDTO getCrearProfesion() {
        return crearProfesion;
    }

    /**
     * @param crearProfesion
     *            the crearProfesion to set
     */
    public void setCrearProfesion(ProfesionDTO crearProfesion) {
        this.crearProfesion = crearProfesion;
    }

    /**
     * @return the seleccionarProfesion
     */
    public ProfesionDTO getSeleccionarProfesion() {
        return seleccionarProfesion;
    }

    /**
     * @param seleccionarProfesion
     *            the seleccionarProfesion to set
     */
    public void setSeleccionarProfesion(ProfesionDTO seleccionarProfesion) {
        this.seleccionarProfesion = seleccionarProfesion;
    }

    /**
     * @return the habilitarOpciones
     */
    public Boolean getHabilitarOpciones() {
        return habilitarOpciones;
    }

    /**
     * @param habilitarOpciones
     *            the habilitarOpciones to set
     */
    public void setHabilitarOpciones(Boolean habilitarOpciones) {
        this.habilitarOpciones = habilitarOpciones;
    }

    /**
     * @return the dialogCrear
     */
    public Boolean getDialogCrear() {
        return dialogCrear;
    }

    /**
     * @param dialogCrear
     *            the dialogCrear to set
     */
    public void setDialogCrear(Boolean dialogCrear) {
        this.dialogCrear = dialogCrear;
    }

    /**
     * @return the dialogActualizar
     */
    public Boolean getDialogActualizar() {
        return dialogActualizar;
    }

    /**
     * @param dialogActualizar
     *            the dialogActualizar to set
     */
    public void setDialogActualizar(Boolean dialogActualizar) {
        this.dialogActualizar = dialogActualizar;
    }

    /**
     * @return the dialogEliminar
     */
    public Boolean getDialogEliminar() {
        return dialogEliminar;
    }

    /**
     * @param dialogEliminar
     *            the dialogEliminar to set
     */
    public void setDialogEliminar(Boolean dialogEliminar) {
        this.dialogEliminar = dialogEliminar;
    }

}
