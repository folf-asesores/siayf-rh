package mx.gob.saludtlax.rh.seguridad.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.perfiles.PerfilDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.Crypto;
import mx.gob.saludtlax.rh.util.EmailValidator;
import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "usuario")
@ViewScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = -6197623242412472773L;

    @Inject
    private Usuario usuarioEJB;
    


    private UsuarioView usuarioView = new UsuarioView();
    private List<PerfilDTO> perfiles;
    private Integer seleccionarPerfil;
    private boolean activo = true;
    private boolean inactivo = true;
    private boolean usuarios;
    private String password;
    private String criterio;
    private String passwordChange;
    
    
    private List<SelectItem> listaAdscripciones = new ArrayList<>();
    private List<SelectItem> listaAreasAdscripciones = new ArrayList<>();
    private List<SelectItem> listaLugarAdscripciones = new ArrayList<>();
    
    private UsuarioDTO usuarioEnSession;

    @PostConstruct
    public void init() {
    	
    	listaAdscripciones = new ArrayList<>();
    	listaAreasAdscripciones = new ArrayList<>();
    	listaLugarAdscripciones = new ArrayList<>();
    	
    	List<CatalogoDTO> adscripciones = usuarioEJB.obtenerAdscripciones();
    	for(CatalogoDTO dto : adscripciones){
    		listaAdscripciones.add(new SelectItem(dto.getId(),dto.getNombre()));
    	}
    	
    	List<CatalogoDTO> areaadscripciones = usuarioEJB.obtenerAdscripciones();
    	for(CatalogoDTO dto : areaadscripciones){
    		listaAreasAdscripciones.add(new SelectItem(dto.getId(),dto.getNombre()));
    	}
    	
    	List<CatalogoDTO> lugaradscripciones = usuarioEJB.obtenerAdscripciones();
    	for(CatalogoDTO dto : lugaradscripciones){
    		listaLugarAdscripciones.add(new SelectItem(dto.getId(),dto.getNombre()));
    	}
    	
    	
    	
    	HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		 usuarioEnSession = (UsuarioDTO) httpSession
				.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
    }

    /**
     * Registra la información de un nuevo usuario en el sistema
     */
    public void registrarUsuario() {
        UsuarioDTO usuario = null;
        try {
            usuario = usuarioEJB.obtenerUsuarioPorNombreUsuario(usuarioView.getUsuarioDTO().getUserName());
            if (usuario != null) {
                JSFUtils.warningMessage("Usuario", "Se encuentra registrado seleccione otro nombre de usuario");
            } else
            if (EmailValidator.validate(usuarioView.getUsuarioDTO().getCorreo())) {
                UsuarioDTO newUserDto = new UsuarioDTO();
                newUserDto = usuarioView.getUsuarioDTO();
            	
                newUserDto.setPassword(Crypto.hmac(password));
                newUserDto.setIdPerfil(seleccionarPerfil);
                usuarioEJB.crear(newUserDto);
                JSFUtils.infoMessage("Usuario", "Registrado correctamente");
            } else {
                JSFUtils.warningMessage("Correo invalido", "Verifique su correo electrónico");
            }
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error", "Ocurrio un error durante el registro del nuevo usuario");
        }
    }

    /**
     * Actualiza la información de un usuario
     */
    public void actualizarUsuario() {
         try {
        	
                if(usuarioView.isCambiarContrasenia()){
                 String newPass=usuarioView.getUsuarioDTO().getPassword();
                 
                
                 usuarioView.getEditarUsuarioDTO().setPassword(Crypto.hmac(newPass));
                
                }
                usuarioEJB.actualizar(usuarioView.getEditarUsuarioDTO());
                JSFUtils.infoMessage("Usuario", "Actualizado correctamente");
            
        } catch (ReglaNegocioException ex) {
            JSFUtils.errorMessage("Error", "Ocurrio un error durante el registro del nuevo usuario");
        }
    }
    public String irCambioContrasenya(){
    	
    	return "/contenido/seguridad/cambiarPassword.xhtml";
    }
    //Metodo para actualizar la contraseña del ususario
    public void actualizarContrasenya() {
        try {
        		if(password.equals(passwordChange)){
        			UsuarioDTO usuario = usuarioEnSession;
            		//usuario.setPassword(password);
            		 usuario.setPassword(Crypto.hmac(password));
                    usuarioEJB.actualizar(usuario);
                    JSFUtils.infoMessage("", "Contraseña cambiada correctamente");
        		}else {
        			JSFUtils.errorMessage("", "Las contraseñas deben coincidir");
        		}
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error", "Ocurrio un error durante el registro del nuevo usuario");
        }
    }
//	/**
//	 * Obtiene la información del usuario buscado de acuerdo al
//	 * criterio de busqueda, las opciones para el criterio son su username o su
//	 * correo electrónico
//	 */
	public void buscarUsuario() {
		UsuarioDTO usuario = usuarioEJB.obtenerUsuarioPorNombreUsuario(criterio);
		if (usuario == null) {
			usuarioView.setHabilitarPanelUsuario(false); 
		} else {
			usuarioView.setUsuarioDTO(usuario);
			usuarioView.setHabilitarPanelUsuario(true);
		}
	}
    /**
     * Quita de la vista principal la información obtenida del usuario de
     * acuerdo al criterio de busqueda
     */
	public void cancelarBusqueda() {
		criterio = "";
		buscarUsuario();
		usuarioView.cancelar();
	}
    /**
     * Edita la información del usuario obtenido de acuerdo al criterio de
     * busqueda
     */
	public void edicionBusqueda() {
		usuarioView.setEditarUsuarioDTO(usuarioView.getUsuarioDTO());
		usuarioView.habilitarFormularioEdicion();
	}
    /**
     * Elimina el usuario obtenido de acuerdo al criterio de busqueda
     */
    public void eliminarBusqueda() {
        try {
            usuarioEJB.eliminar(usuarioView.getUsuarioDTO().getIdUsuario());
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error", "Al tratar de eliminar el usuario seleccionado");
        }
    }

    /**
     * Coloca a un usuario en un estatus inhabilitado
     */
	public void inhabilitarUsuarioBusqueda() {
		Integer idUsuario = usuarioView.getUsuarioDTO().getIdUsuario();
		usuarioEJB.usuarioInactivo(idUsuario);
	}
    /**
     * Muestra la información de los usuarios dependiendo del estatus que tengan
     * activos o inactivos
     */
    public void usuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        if (activo && inactivo) {
            lista = usuarioEJB.consultarTodosUsuarios();
            usuarioView.setUsuarios(lista);
            usuarioView.setHabilitarTabla(true);
        } else if (activo) {
            lista = usuarioEJB.consultarUsuariosActivos();
            usuarioView.setUsuarios(lista);
            usuarioView.setHabilitarTabla(true);
        } else if (inactivo) {
            lista = usuarioEJB.consultarUsuariosInactivos();
            usuarioView.setUsuarios(lista);
            usuarioView.setHabilitarTabla(true);
        } else {
            usuarioView.setUsuarios(lista);
            usuarioView.setHabilitarTabla(false);
        }
    }

    /**
     * Habilita el formulario de edición
     */
    public void habilitarEdicion() {
        usuarioView.setEditarUsuarioDTO(usuarioView.getSeleccionarUsuario());
        usuarioView.habilitarFormularioEdicion();
    }

    /**
     * Elimina un usuario, utilizado en el formulario de edición
     */
	public void eliminar() {
		try {
			Integer idUsuario = usuarioView.getSeleccionarUsuario().getIdUsuario();
			usuarioEJB.eliminar(idUsuario);
			JSFUtils.infoMessage("Usuario", "Eliminado Correctamente");
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error", "Al tratar de eliminar el usuario seleccionado");
		}
	}
    /*
     * Coloca a un usuario en un estatus inhabilitado
     */
	public void inhabilitarUsuario() {
		Integer idUsuario = usuarioView.getUsuarioDTO().getIdUsuario();
		usuarioEJB.usuarioInactivo(idUsuario);
		JSFUtils.infoMessage("El usuario", "ha sido inhabilitado");
	}

    public UsuarioView getUsuarioView() {
        return usuarioView;
    }

    public void setUsuarioView(UsuarioView usuarioView) {
        this.usuarioView = usuarioView;
    }

    public List<PerfilDTO> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<PerfilDTO> perfiles) {
        this.perfiles = perfiles;
    }

    public Integer getSeleccionarPerfil() {
        return seleccionarPerfil;
    }

    public void setSeleccionarPerfil(Integer seleccionarPerfil) {
        this.seleccionarPerfil = seleccionarPerfil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isInactivo() {
        return inactivo;
    }

    public void setInactivo(boolean inactivo) {
        this.inactivo = inactivo;
    }

    public boolean isUsuarios() {
        return usuarios;
    }

    public void setUsuarios(boolean usuarios) {
        this.usuarios = usuarios;
    }

	public UsuarioDTO getUsuarioEnSession() {
		return usuarioEnSession;
	}

	public void setUsuarioEnSession(UsuarioDTO usuarioEnSession) {
		this.usuarioEnSession = usuarioEnSession;
	}


	public List<SelectItem> getListaAdscripciones() {
		return listaAdscripciones;
	}

	public void setListaAdscripciones(List<SelectItem> listaAdscripciones) {
		this.listaAdscripciones = listaAdscripciones;
	}

	public List<SelectItem> getListaAreasAdscripciones() {
		return listaAreasAdscripciones;
	}

	public void setListaAreasAdscripciones(List<SelectItem> listaAreasAdscripciones) {
		this.listaAreasAdscripciones = listaAreasAdscripciones;
	}

	public List<SelectItem> getListaLugarAdscripciones() {
		return listaLugarAdscripciones;
	}

	public void setListaLugarAdscripciones(List<SelectItem> listaLugarAdscripciones) {
		this.listaLugarAdscripciones = listaLugarAdscripciones;
	}


	public String getPasswordChange() {
		return passwordChange;
	}

	public void setPasswordChange(String passwordChange) {
		this.passwordChange = passwordChange;
	}


}
