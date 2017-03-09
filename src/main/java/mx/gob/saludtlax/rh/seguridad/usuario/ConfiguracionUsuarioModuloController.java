package mx.gob.saludtlax.rh.seguridad.usuario;

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

import org.primefaces.event.RowEditEvent;

import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccion;
import mx.gob.saludtlax.rh.modulos.ConfiguracionModuloAccionDTO;
import mx.gob.saludtlax.rh.modulos.ModuloDTO;
import mx.gob.saludtlax.rh.modulos.Modulos;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

@ManagedBean
@ViewScoped
public class ConfiguracionUsuarioModuloController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 727584121566233404L;

	@Inject
	Usuario usuarioEjb;
	
	@Inject
	Modulos moduloEjb;
	
	@Inject
	ConfiguracionModuloAccion  configuracionModuloAccion;
	
	@Inject
	ConfiguracionUsuarioModulo configuracionUsuarioModulo;
	
	private List<ConfiguracionUsuarioModuloDTO> listaConfigruacion = new ArrayList<>();
	private List<ConfiguracionModuloAccionDTO> listaModulos = new ArrayList<>();
	private List<UsuarioDTO> listUsuarios = new ArrayList<>();
	
	private ConfiguracionUsuarioModuloDTO configuracionUsuarioModuloSelecciondo;
	private ConfiguracionUsuarioModuloDTO configuracionUsuarioModuloNew= new ConfiguracionUsuarioModuloDTO();
	
	
	@PostConstruct
	public void inicio(){
		List<ConfiguracionUsuarioModuloDTO> configuracionModuloUsuarioTemp = new ArrayList<>();
		configuracionModuloUsuarioTemp = configuracionUsuarioModulo.obtenerLista();
		listaConfigruacion.clear();
		listaConfigruacion.addAll(configuracionModuloUsuarioTemp);
		List<UsuarioDTO> list= usuarioEjb.consultarTodosUsuarios(); 
		listUsuarios.clear();
		listUsuarios.addAll(list);
		List<ConfiguracionModuloAccionDTO> lista= configuracionModuloAccion.obtenerListaConfiguracionModuloAccionDTO();
		listaModulos.clear();
		listaModulos.addAll(lista);
	}
	
	public void validatorConfiguracionModuloAccion(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();
		switch (nombreComponete) {

		case "modulo":
			Integer modulo = (Integer) value;
			
			
			
			if (!ValidacionUtil.esNumeroPositivo(modulo)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione un modulo.");
				context.addMessage(component.getClientId(), facesMessage1);
				throw new ValidatorException(facesMessage1);
			}
			break;
		
		
			case "usuario":
				Integer accion = (Integer) value;
				
				if (!ValidacionUtil.esNumeroPositivo(accion)) {
					FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Seleccione una acción.");
					context.addMessage(component.getClientId(), facesMessage1);
					throw new ValidatorException(facesMessage1);
				}
				break;
		}
	}
	
	
	public void agregarConfiguracionModuloAccion(){
		configuracionUsuarioModulo.crear(configuracionUsuarioModuloNew);
		inicio();
		configuracionUsuarioModuloNew = new ConfiguracionUsuarioModuloDTO();
	}
	
	
	public void onRowEdit(RowEditEvent event) {
		try {

			ConfiguracionUsuarioModuloDTO configuracionModuloUsuario = ((ConfiguracionUsuarioModuloDTO) event.getObject());
			configuracionUsuarioModulo.editar(configuracionModuloUsuario);
			
			FacesMessage msg = new FacesMessage("",
					"Actualizado Correctamente.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("", "No se pudo guardar los cambios.");
		}

		
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición Cancelada:",
				"Actualizado Correctamente.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
	public void eliminarConfiguracion(){
		configuracionUsuarioModulo.eliminar(configuracionUsuarioModuloSelecciondo.getId_configuracion_usuario_modulo());
		inicio();
	}


	public List<ConfiguracionUsuarioModuloDTO> getListaConfigruacion() {
		return listaConfigruacion;
	}


	public void setListaConfigruacion(List<ConfiguracionUsuarioModuloDTO> listaConfigruacion) {
		this.listaConfigruacion = listaConfigruacion;
	}

	public List<ConfiguracionModuloAccionDTO> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<ConfiguracionModuloAccionDTO> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public List<UsuarioDTO> getListUsuarios() {
		return listUsuarios;
	}


	public void setListUsuarios(List<UsuarioDTO> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}


	public ConfiguracionUsuarioModuloDTO getConfiguracionUsuarioModuloSelecciondo() {
		return configuracionUsuarioModuloSelecciondo;
	}


	public void setConfiguracionUsuarioModuloSelecciondo(
			ConfiguracionUsuarioModuloDTO configuracionUsuarioModuloSelecciondo) {
		this.configuracionUsuarioModuloSelecciondo = configuracionUsuarioModuloSelecciondo;
	}


	public ConfiguracionUsuarioModuloDTO getConfiguracionUsuarioModuloNew() {
		return configuracionUsuarioModuloNew;
	}


	public void setConfiguracionUsuarioModuloNew(ConfiguracionUsuarioModuloDTO configuracionUsuarioModuloNew) {
		this.configuracionUsuarioModuloNew = configuracionUsuarioModuloNew;
	}
	
	
	
}
