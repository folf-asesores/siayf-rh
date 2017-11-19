
package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean(name = "autorizaciones")
@ViewScoped

public class AutorizacionesController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1371559233282028398L;
    /**
     *
     */

    private AutorizacionesView view;
    @Inject
    private ConfiguracionAprobaciones autorizacionUsuario;
    @Inject
    private Catalogo catalogo;

    @PostConstruct
    public void initAutorizaciones() {
        view = new AutorizacionesView();
        irPrincipal();
    }

    public AutorizacionesView getView() {
        return view;
    }

    public String irPrincipal() {
        //	view.setListaAutorizados(autorizacionUsuario.obtenerListaAutorizados());
        view.panelPrincipal();
        return null;
    }

    public String irCrear() {
        view.setListAutorizaciones(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaAutorizaciones()));
        //	view.setListaUsuarios(autorizacionUsuario.obtenerListaUsuarios());
        view.panelCrear();
        return null;
    }

    public String irActualizar() {
        view.setListAutorizaciones(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaAutorizaciones()));
        //		view.setListaAutorizados(autorizacionUsuario.obtenerListaAutorizadosDesactivados());
        view.panelActualizar();
        return null;
    }

    public String irEliminar() {
        //		view.setListaAutorizados(autorizacionUsuario.obtenerListaAutorizadosDesactivados());
        view.panelEliminar();
        return null;
    }

    public String irActivar(UsuarioConfiguracionDTO dto) {
        view.setAutorizados(dto);
        view.panelAccion();
        return null;
    }

    //	<<<<<Opciones para Autorizaciones>>>>>

    public String buscar() {
        //	view.setListaAutorizados(autorizacionUsuario.obtenerAutorizadosPorCriterio(view.getDes()));
        return null;
    }

    public String guardarAutorizacion() {
        try {
            List<Integer> lid = new ArrayList<>();
            for (UsuarioDTO usuario : view.getUsuariosLista()) {
                lid.add(usuario.getIdUsuario());
            }
            view.getAutorizacion().setUsuarios(lid);
            //		autorizacionUsuario.crearConfiguracionAutorizacion(view.getAutorizacion());
            irPrincipal();
            return null;
        } catch (BusinessException ex) {
            return null;
        }
    }

    public void actualizarAutorizacion() {
        try {
            List<UsuarioConfiguracionDTO> lid = new ArrayList<>();
            for (UsuarioConfiguracionDTO usuario : view
                    .getAutorizadosSelect()) {
                lid.add(usuario);
            }
            //	autorizacionUsuario.actualizarConfiguracionAutorizacion(lid, view.getDes());
            irPrincipal();
        } catch (BusinessException ex) {

        }
    }

    public void eliminarAutorizacion() {
        try {
            List<UsuarioConfiguracionDTO> lid = new ArrayList<>();
            for (UsuarioConfiguracionDTO usuario : view
                    .getAutorizadosSelect()) {
                lid.add(usuario);
            }
            //		autorizacionUsuario.eliminaConfiguracionAutorizacion(lid);
            irPrincipal();
        } catch (BusinessException ex) {

        }
    }

    //	>>>>>Opciones Singulares<<<<<

    public void mostrarDialogoEliminar() {
        view.setDialogoEliminar(true);
    }

    public void ocultarDialogoEliminar() {
        view.setDialogoEliminar(false);
    }

    public void eliminar() {
        //	autorizacionUsuario.eliminar(view.getAutorizados().getIdAccionUsuario());
        view.setDialogoEliminar(false);
        view.panelPrincipal();
        irPrincipal();
    }

    public void activar() {
        //	autorizacionUsuario.activar(view.getAutorizados());
        view.setDialogoActivo(false);
        view.panelPrincipal();
        irPrincipal();
    }
    //	>>>>>Validadores<<<<<

    public void validatorAutorizaciones(FacesContext context,
            UIComponent component, Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "accion":
                Integer accion = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(accion)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Seleccione una Accion");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }
                break;
            default:
                break;
        }
    }

}
