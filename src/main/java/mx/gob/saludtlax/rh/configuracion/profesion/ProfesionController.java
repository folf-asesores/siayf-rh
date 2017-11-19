/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.profesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 10/03/2016 11:08:20
 */
@ManagedBean(name = "profesion")
@ViewScoped
public class ProfesionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8020623041064529836L;

    @EJB
    private Profesion profesionEJB;

    private ProfesionView profesion = new ProfesionView();

    @PostConstruct
    public void init() {
        listaProfesion();
    }

    public void listaProfesion() {
        List<ProfesionDTO> listaProfesion = profesionEJB.listaProfesion();
        if (!listaProfesion.isEmpty()) {
            profesion.setListaProfesion(listaProfesion);
        } else {
            profesion.setListaProfesion(new ArrayList<ProfesionDTO>());
        }
    }

    public void crearProfesion() {
        try {
            profesionEJB.crearProfesion(profesion.getCrearProfesion());
            listaProfesion();
            cerrarDialogo();
            JSFUtils.infoMessage("Puesto", "Registrada Correctamente");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void actualizarProfesion() {
        try {
            profesionEJB.actualizarProfesion(profesion.getSeleccionarProfesion());
            listaProfesion();
            cerrarDialogo();
            JSFUtils.infoMessage("Puesto", "Actualizada Correctamente");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void eliminarProfesion() {
        try {
            profesionEJB.eliminarProfesion(profesion.getSeleccionarProfesion().getIdProfesion());
            listaProfesion();
            cerrarDialogo();
            JSFUtils.infoMessage("Puesto", "Elimanada Correctamente");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void habilitarOpciones() {
        if (profesion.getSeleccionarProfesion() != null) {
            profesion.setHabilitarOpciones(false);
        } else if (profesion.getSeleccionarProfesion() == null) {
            profesion.setHabilitarOpciones(true);
        }
    }

    public void abrirDialogoRegistro() {
        profesion.setCrearProfesion(new ProfesionDTO());
        profesion.setDialogCrear(true);
        profesion.setDialogActualizar(false);
        profesion.setDialogEliminar(false);
    }

    public void abrirDialogoActualizacion() {
        profesion.setDialogCrear(false);
        profesion.setDialogActualizar(true);
        profesion.setDialogEliminar(false);
    }

    public void abrirDialogoEliminacion() {
        profesion.setDialogCrear(false);
        profesion.setDialogActualizar(false);
        profesion.setDialogEliminar(true);
    }

    public void cerrarDialogo() {
        profesion.setSeleccionarProfesion(new ProfesionDTO());
        profesion.setDialogCrear(false);
        profesion.setDialogActualizar(false);
        profesion.setDialogEliminar(false);
        profesion.setHabilitarOpciones(true);

    }

    public String irPaginaInicio() {
        return "/contenido/inicio.xhtml?faces-redirect=true";
    }

    /**
     * @return the profesion
     */
    public ProfesionView getProfesion() {
        return profesion;
    }

    /**
     * @param profesion
     *            the profesion to set
     */
    public void setProfesion(ProfesionView profesion) {
        this.profesion = profesion;
    }

}
