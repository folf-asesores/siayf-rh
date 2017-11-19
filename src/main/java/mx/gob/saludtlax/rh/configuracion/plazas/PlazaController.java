
package mx.gob.saludtlax.rh.configuracion.plazas;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.saludtlax.rh.excepciones.BusinessException;

@ManagedBean(name = "plaza")
@ApplicationScoped
public class PlazaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9057498703887149332L;

    @Inject
    private PlazaEJB plazaEJB;

    private PlazaView plazaView;
    private String criterio;

    @PostConstruct
    public void init() {
        plazaView = new PlazaView();
    }

    public void registraPlaza() {
        PlazaDTO plaza = plazaView.getPlazaDTO();
        try {
            String claveEncontrada = plazaEJB.buscarClave(plaza.getClave());
            if (!claveEncontrada.isEmpty()) {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Clave encontrada", "Ingrese una clave diferente");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            } else {
                plazaEJB.registrarPlaza(plaza);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Plaza", "Registrada correctamente");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } catch (BusinessException ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un problema al registrar la plaza");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void actualizarPlaza() {
        try {
            PlazaDTO plaza = plazaView.getEditarPlazaDTO();
            plazaEJB.actualizarPlaza(plaza);
            obtenerPlaza();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Plaza", "Cambios guardados");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (BusinessException ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un problema al guardar los cambios de la plaza");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void obtenerPlaza() {
        try {
            List<PlazaDTO> plazas = plazaEJB.obtenerListaPlazas(criterio);
            if (!plazas.isEmpty()) {
                plazaView.setListaPlazas(plazas);
            } else {
                plazaView.setListaPlazas(null);
            }
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * public void obtenerPlazaPorId() { try { Integer idPlaza =
     * plazaView.getSeleccionarPlaza().getIdPlaza();
     * plazaEJB.obtenerPorId(idPlaza); plazaView.cancelar(); } catch
     * (BusinessException ex) { ex.printStackTrace(); } }
     */

    public String eliminarPlaza() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            Integer idPlaza = plazaView.getSeleccionarPlaza().getIdPlaza();
            plazaEJB.eliminarPlaza(idPlaza);
            obtenerPlaza();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Plaza", "Eliminada Correctamente");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (BusinessException ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrio un problema al eliminar la plaza seleccionada");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        response.sendRedirect(request.getContextPath() + "/contenido/plazas/plaza.xhtml");
        plazaView.cancelar();
        return null;
    }

    public String cancelar() {
        plazaView.cancelar();
        return "/contenido/plazas/plaza.xhtml?faces-redirect=true";
    }

    public String habilitarFormulario() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        plazaView.habilitarFormulario();
        response.sendRedirect(request.getContextPath() + "/contenido/plazas/plazaRegistroEdicion.xhtml");
        return null;
    }

    public String habilitarFormularioEdicion() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        plazaView.setEditarPlazaDTO(plazaView.getSeleccionarPlaza());
        plazaView.habilitarFormularioEdicion();
        response.sendRedirect(request.getContextPath() + "/contenido/plazas/plazaRegistroEdicion.xhtml");
        return null;
    }

    public void habilitarBotonEdicion() {
        plazaView.setHabilitarBotonEdicion(true);
        plazaView.setHabilitarCancelarEdicion(true);
        plazaView.setHabilitarBotonNuevaPlaza(false);
    }

    public PlazaEJB getPlazaEJB() {
        return plazaEJB;
    }

    public void setPlazaEJB(PlazaEJB plazaEJB) {
        this.plazaEJB = plazaEJB;
    }

    public PlazaView getPlazaView() {
        return plazaView;
    }

    public void setPlazaView(PlazaView plazaView) {
        this.plazaView = plazaView;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

}