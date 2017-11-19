/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.seguridad.usuario.InfoUsuarioDTO;
import mx.gob.saludtlax.rh.seguridad.usuario.Usuario;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * @since 04/11/2016 12:47:58
 */
@ManagedBean(name = "configuracionAprobacion")
@ViewScoped
public class ConfiguracionAprobacionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4378225335844109504L;

    @Inject
    private Catalogo catalogo;
    @Inject
    private ConfiguracionAprobaciones configuracionAprobaciones;
    @Inject
    private Usuario usuario;

    private ConfiguracionAprobacionView view;

    @PostConstruct
    public void inicio() {

        view = new ConfiguracionAprobacionView();

        view.setListaOperaciones(SelectItemsUtil
                .listaCatalogos(catalogo.consultarOperacionesSistema()));
        view.setUsuariosActivos(usuario.consultarInfoUsuariosActivos());
    }

    public void consultarUsuariosOperacion() {
        try {
            view.setUsuarios(configuracionAprobaciones
                    .consultarUsuariosAprobacion(view.getIdOperacion()));
        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void obtenerListaUsuarioActivo() {
        try {
            List<InfoUsuarioDTO> usuariosActivos = usuario
                    .consultarInfoUsuariosActivos();

            List<CatalogoDTO> lista = new ArrayList<>();

            if (!usuariosActivos.isEmpty()) {
                for (InfoUsuarioDTO usuario : usuariosActivos) {
                    CatalogoDTO dto = new CatalogoDTO();
                    dto.setId(usuario.getIdUsuario());
                    dto.setNombre(usuario.getNombre());
                    lista.add(dto);
                }
            }

            view.setListaUsuarioActivoItems(
                    SelectItemsUtil.listaCatalogos(lista));

        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void obtenerTiposMovimientoEmpleados() {
        try {
            view.setListaTipoMovimientoEmpleado(SelectItemsUtil.listaCatalogos(
                    catalogo.obtenerListaTipoMovimientoEmpleado()));
        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void mostrarRegistro() {
        try {
            if (ValidacionUtil.esNumeroPositivo(view.getIdOperacion())) {
                view.setUsuariosSeleccionados(new ArrayList<InfoUsuarioDTO>());
                view.setMostrarRegistro(true);
                view.setOperacionSeleccionada(configuracionAprobaciones
                        .obtenerDescripcionOperacion(view.getIdOperacion()));
            } else {
                throw new ValidacionException("Seleccione una operación",
                        ValidacionCodigoError.VALOR_REQUERIDO);
            }
        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void mostrarActualizacion(UsuarioConfiguracionDTO dto) {

        view.getActualizarConfiguracionAprobacion()
                .setIdConfiguracionAprobacion(
                        dto.getIdConfiguracionAprobacion());
        view.getActualizarConfiguracionAprobacion()
                .setIdAccionUsuario(dto.getIdAccionUsuario());
        view.getActualizarConfiguracionAprobacion()
                .setIdUsuario(dto.getIdUsuario());
        view.setAplicaMovimientos(configuracionAprobaciones
                .aplicaMovimientos(dto.getIdAccionUsuario()));
        if (view.getAplicaMovimientos()) {
            view.getActualizarConfiguracionAprobacion()
                    .setIdTipoMovimiento(dto.getIdTipoMovimiento());
            ;
        }
        if (dto.getEstatus().equals(EnumEstatusConfiguracion.ACTIVO)) {
            view.getActualizarConfiguracionAprobacion().setEstatus(2);
        } else {
            view.getActualizarConfiguracionAprobacion().setEstatus(1);
        }

        view.setPanelActualizar(true);
        view.setPanelPrincipal(false);

        obtenerListaUsuarioActivo();
        obtenerTiposMovimientoEmpleados();

    }

    public void mostrarDialogEliminar(Integer idConfiguracionAprobacion) {
        view.setDialogEliminar(true);
        view.setIdConfiguracionAprobacion(idConfiguracionAprobacion);
    }

    public void ocultarRegistro() {
        view.setMostrarRegistro(false);
    }

    public void cerrarDialogEliminar() {
        view.setDialogEliminar(false);
        view.setIdConfiguracionAprobacion(0);
    }

    public void registrarConfiguracionAprobacion() {
        try {
            RegistrarUsuarioDTO dto = new RegistrarUsuarioDTO();
            Integer idOperacion = view.getIdOperacion();
            List<Integer> usuarios = new ArrayList<>();

            for (InfoUsuarioDTO usuarioSeleccionado : view
                    .getUsuariosSeleccionados()) {
                usuarios.add(usuarioSeleccionado.getIdUsuario());
            }

            dto.setIdAccion(idOperacion);
            dto.setUsuarios(usuarios);

            configuracionAprobaciones.registrarUsuariosAprobacion(dto);

            ocultarRegistro();
            consultarUsuariosOperacion();

            JSFUtils.infoMessage("Registro Configuración Aprovación: ",
                    "Se realizo correctamente.");

        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void actualizarConfiguracionAprovacion() {
        try {
            configuracionAprobaciones.actualizarConfiguracionAprobacion(
                    view.getActualizarConfiguracionAprobacion());
            JSFUtils.infoMessage("Actualizar Configuración Aprobación: ",
                    "Se realizo correctamente.");
            view.setPanelPrincipal(true);
            view.setPanelActualizar(false);
            consultarUsuariosOperacion();
            view.setActualizarConfiguracionAprobacion(
                    new ActualizacionConfiguracionAprobacionDTO());
        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void eliminarConfiguracionAprobacion() {
        try {
            configuracionAprobaciones.eliminarConfiguracionAprobacion(
                    view.getIdConfiguracionAprobacion());
            JSFUtils.infoMessage("Eliminación Configuración Aprobación: ",
                    "Se realizo correctamente.");

            cerrarDialogEliminar();
            consultarUsuariosOperacion();
        } catch (ReglaNegocioException | ValidacionException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public String regresarModulo() {
        return "/contenido/configuracion/configuracionAprobaciones.xhtml?faces-redirect=true";
    }

    public ConfiguracionAprobacionView getView() {
        return view;
    }

    public void setView(ConfiguracionAprobacionView view) {
        this.view = view;
    }

}
