/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:29:17
 */
@ManagedBean(name = "banco")
@ViewScoped
public class BancoController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7484039062574113727L;

    @Inject
    private Banco banco;

    private BancoView view;

    @PostConstruct
    public void init() {
        view = new BancoView();

        obtenerListaBanco();
    }

    public void obtenerListaBanco() {
        try {

            List<BancoDTO> listaBanco = banco.obtenerListaBanco();

            if (!listaBanco.isEmpty()) {
                view.setListaBanco(listaBanco);
            } else {
                view.setListaBanco(new ArrayList<BancoDTO>());
            }

        } catch (ReglaNegocioException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void accionBanco() {
        try {
            if (view.getAccionBanco().equals("Registrar")) {
                banco.crearBanco(view.getBancoDTO());
                JSFUtils.infoMessage("Registro Banco: ", "Se realizo correctamente");
            } else if (view.getAccionBanco().equals("Actualizar")) {
                banco.actualizarBanco(view.getBancoDTO());
                JSFUtils.infoMessage("Actualización Banco: ", "Se realizo correctamente");
            }
            limpiarVista();
        } catch (ReglaNegocioException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void eliminarBanco() {
        try {
            banco.eliminarBanco(view.getIdBanco());
            JSFUtils.infoMessage("Eliminación Banco: ", "Se realizo correctamente");
            cerrarDialogoEliminar();
            limpiarVista();
        } catch (ReglaNegocioException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void limpiarVista() {
        view.setBancoDTO(new BancoDTO());
        view.setAccionBanco("Registrar");
        obtenerListaBanco();
    }

    public void seleccionarBancoActualizacion(BancoDTO bancoDTO) {
        view.setBancoDTO(bancoDTO);
        view.setAccionBanco("Actualizar");
    }

    public void seleccionaBancoEliminar(Integer idBanco) {
        view.setIdBanco(idBanco);
        view.setDialogEliminarBanco(true);
    }

    public void cerrarDialogoEliminar() {
        view.setAccionBanco("Registrar");
        view.setBancoDTO(new BancoDTO());
        view.setDialogEliminarBanco(false);
        obtenerListaBanco();
    }

    /**
     * @return the view
     */
    public BancoView getView() {
        return view;
    }

    /**
     * @param view
     *            the view to set
     */
    public void setView(BancoView view) {
        this.view = view;
    }

}
