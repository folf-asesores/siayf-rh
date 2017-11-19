
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.acciones.Accion;
import mx.gob.saludtlax.rh.acciones.AccionDTO;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean
@SessionScoped
public class VistaUsuarioController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7274638001768881330L;

    @Inject
    Usuario usuarioEjb;

    @Inject
    Modulos moduloEjb;

    @Inject
    Accion accionEJB;

    @Inject
    VistaUsuario vistaUsuarioEjb;

    @Inject
    ConfiguracionModuloAccion configuracionModuloAccion;

    @Inject
    ConfiguracionUsuarioModulo configuracionUsuarioModulo;

    @Inject
    VistaUsuario vistaUsuario;

    private VistaUsuarioView view;
    private List<VistaUsuarioDTO> listaConfiguracion = new ArrayList<>();
    private List<ConfiguracionUsuarioModuloDTO> listaConfiguracionUsuario;
    private List<ConfiguracionModuloAccionDTO> listaModulos = new ArrayList<>();
    private List<UsuarioDTO> listaUsuarios = new ArrayList<>();
    private UsuarioDTO usuarioSeleccionado;
    private List<AccionDTO> listaAcciones = new ArrayList<>();
    private VistaUsuarioDTO vistaModuloDTO;
    private VistaUsuarioDTO vistaUsuarioNew;
    private List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccionRestantes;
    private List<ConfiguracionModuloAccionDTO> listaConfiguracionUsuarioSelect;

    @PostConstruct
    public void inicio() {
        view = new VistaUsuarioView();

        List<UsuarioDTO> list = usuarioEjb.consultarTodosUsuarios();
        listaUsuarios.clear();
        listaUsuarios.addAll(list);

        view.setPrincipal(true);
        view.setNueva(true);
    }

    public String mostrarAccion() {
        view.setAcciones(true);
        view.setPrincipal(false);
        view.setTabla(false);
        listaConfiguracion.clear();
        listaConfiguracionUsuario = configuracionUsuarioModulo
                .obtenerListaPorUsuario(usuarioSeleccionado.getIdUsuario());
        listaConfiguracionModuloAccionRestantes = configuracionModuloAccion
                .obtenerListaConfiguracionModuloAccionDTOPorUsuario(
                        usuarioSeleccionado.getIdUsuario());
        return null;
    }

    public void agregarConfiguracionModuloAccion() {
        vistaUsuario.crear(vistaUsuarioNew);
        inicio();
        vistaUsuarioNew = new VistaUsuarioDTO();
    }

    public void agregarConfiguracionUsuario() {

        for (ConfiguracionModuloAccionDTO dto : listaConfiguracionUsuarioSelect) {
            ConfiguracionUsuarioModuloDTO configuracionUsuarioDto = new ConfiguracionUsuarioModuloDTO();
            configuracionUsuarioDto.setConfiguracionModulo(dto);
            configuracionUsuarioDto.setFechaCreacion(new Date());
            configuracionUsuarioDto.setUsuario(usuarioSeleccionado);
            listaConfiguracionUsuario.add(configuracionUsuarioDto);
            listaConfiguracionModuloAccionRestantes.remove(dto);
        }

    }

    public void guardar() {

        List<ConfiguracionUsuarioModuloDTO> listaConfiguracionUsuarioExistente = configuracionUsuarioModulo
                .obtenerListaPorUsuario(usuarioSeleccionado.getIdUsuario());

        for (ConfiguracionUsuarioModuloDTO configuracion : listaConfiguracionUsuarioExistente) {
            configuracionUsuarioModulo
                    .eliminar(configuracion.getIdConfiguracionUsuarioModulo());
        }

        try {
            for (ConfiguracionUsuarioModuloDTO configuracion : listaConfiguracionUsuario) {
                configuracionUsuarioModulo.crear(configuracion);
            }
            listaConfiguracionUsuario = configuracionUsuarioModulo
                    .obtenerListaPorUsuario(usuarioSeleccionado.getIdUsuario());

            listaConfiguracionModuloAccionRestantes = configuracionModuloAccion
                    .obtenerListaConfiguracionModuloAccionDTOPorUsuario(
                            usuarioSeleccionado.getIdUsuario());
            JSFUtils.infoMessage("",
                    "Se crearon las configuraciones correctamente");
        } catch (PersistenceException e) {
            JSFUtils.infoMessage("",
                    "Se presento un problema al guardar la configuracion");
        }
    }

    public void eliminar(ConfiguracionUsuarioModuloDTO eliminarConfiguracion) {

        if (eliminarConfiguracion.getIdConfiguracionUsuarioModulo() != null) {
            configuracionUsuarioModulo.eliminar(
                    eliminarConfiguracion.getIdConfiguracionUsuarioModulo());
            listaConfiguracionUsuario = configuracionUsuarioModulo
                    .obtenerListaPorUsuario(usuarioSeleccionado.getIdUsuario());

            listaConfiguracionModuloAccionRestantes = configuracionModuloAccion
                    .obtenerListaConfiguracionModuloAccionDTOPorUsuario(
                            usuarioSeleccionado.getIdUsuario());

            listaConfiguracionUsuarioSelect.clear();
        } else {
            JSFUtils.errorMessage("",
                    "Verifique ya guardo previamente la configuracion que desea eliminar, de lo contrario no es necesario eliminarla.");
        }
    }

    public void regresar() {
        view.setAcciones(false);
        view.setPrincipal(true);
        view.setTabla(false);
    }

    public void nuevaAccion() {
        view.setPrincipal(false);
        view.setNueva(true);
        view.setTabla(true);
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public VistaUsuarioView getView() {
        return view;
    }

    public void setView(VistaUsuarioView view) {
        this.view = view;
    }

    public List<AccionDTO> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<AccionDTO> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public VistaUsuarioDTO getVistaModuloDTO() {
        return vistaModuloDTO;
    }

    public void setVistaModuloDTO(VistaUsuarioDTO vistaModuloDTO) {
        this.vistaModuloDTO = vistaModuloDTO;
    }

    public List<VistaUsuarioDTO> getListaConfiguracion() {
        return listaConfiguracion;
    }

    public void setListaConfiguracion(
            List<VistaUsuarioDTO> listaConfiguracion) {
        this.listaConfiguracion = listaConfiguracion;
    }

    public List<ConfiguracionModuloAccionDTO> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(
            List<ConfiguracionModuloAccionDTO> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public VistaUsuarioDTO getVistaUsuarioNew() {
        return vistaUsuarioNew;
    }

    public void setVistaUsuarioNew(VistaUsuarioDTO vistaUsuarioNew) {
        this.vistaUsuarioNew = vistaUsuarioNew;
    }

    public UsuarioDTO getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(UsuarioDTO usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<ConfiguracionUsuarioModuloDTO> getListaConfiguracionUsuario() {
        return listaConfiguracionUsuario;
    }

    public void setListaConfiguracionUsuario(
            List<ConfiguracionUsuarioModuloDTO> listaConfiguracionUsuario) {
        this.listaConfiguracionUsuario = listaConfiguracionUsuario;
    }

    public List<ConfiguracionModuloAccionDTO> getListaConfiguracionModuloAccionRestantes() {
        return listaConfiguracionModuloAccionRestantes;
    }

    public void setListaConfiguracionModuloAccionRestantes(
            List<ConfiguracionModuloAccionDTO> listaConfiguracionModuloAccionRestantes) {
        this.listaConfiguracionModuloAccionRestantes = listaConfiguracionModuloAccionRestantes;
    }

    public List<ConfiguracionModuloAccionDTO> getListaConfiguracionUsuarioSelect() {
        return listaConfiguracionUsuarioSelect;
    }

    public void setListaConfiguracionUsuarioSelect(
            List<ConfiguracionModuloAccionDTO> listaConfiguracionUsuarioSelect) {
        this.listaConfiguracionUsuarioSelect = listaConfiguracionUsuarioSelect;
    }

}
