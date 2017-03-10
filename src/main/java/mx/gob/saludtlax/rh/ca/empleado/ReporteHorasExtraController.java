package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.persistencia.ServicioEntity;
import mx.gob.saludtlax.rh.persistencia.ServicioRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.ConfiguracionUsuarioModulo;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

@ManagedBean(name = "reporteHorasExtraController")
@ViewScoped
public class ReporteHorasExtraController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8501210074825960112L;

	@Inject
	Empleado empleadoService;
	@Inject
	private Catalogo catalogo;
	@Inject
	private TipoContratacionRepository tipoContratacionRepository;
	@Inject
	private ServicioRepository serviciosRepository;
	@Inject
	private ConfiguracionUsuarioModulo configuracionUsuarioModulo;

	private Integer idEmpleado = -1;
	public Integer idAdscripcion = -1;
	public Integer idTipoContratacion = -1;
	public Integer idDepartamentos = -1;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer tipoReporte = 0;
	InfoEmpleadoDTO empleadoDTO = new InfoEmpleadoDTO();
	List<CatalogoDTO> catalogoTipoContratacionDTO;
	List<CatalogoDTO> catalogoDepartamentosDTO;
	private List<SelectItem> catalogoAdscripciones;
	private boolean tieneRestriccionAdscripcion;
	private Integer idAdscripcionUsuario;
	private String mensaje;

	@PostConstruct
	public void init() {

		List<TipoContratacionEntity> listadoTiposContrataciones = tipoContratacionRepository.consultarTodos();
		catalogoTipoContratacionDTO = new ArrayList<>();
		for (TipoContratacionEntity tipoContratacionEntity : listadoTiposContrataciones) {
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			catalogoDTO.setId(tipoContratacionEntity.getId());
			catalogoDTO.setNombre(tipoContratacionEntity.getTipoContratacion());
			catalogoTipoContratacionDTO.add(catalogoDTO);
		}

		List<ServicioEntity> listadoServiciosEntity = serviciosRepository.consultarTodos();
		catalogoDepartamentosDTO = new ArrayList<CatalogoDTO>();
		for (ServicioEntity serviciosEntity : listadoServiciosEntity) {
			CatalogoDTO catalogoServicio = new CatalogoDTO();
			catalogoServicio.setId(serviciosEntity.getIdServicio());
			catalogoServicio.setNombre(serviciosEntity.getServicio());
			catalogoDepartamentosDTO.add(catalogoServicio);
		}

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

		setIdAdscripcionUsuario(usuario.getIdAdscripcion());
		setTieneRestriccionAdscripcion(
				configuracionUsuarioModulo.tienePermiso("Jurisdiccion_asiganda", usuario.getIdUsuario()));

		if (isTieneRestriccionAdscripcion()) {
			if (getIdAdscripcionUsuario() == null) {
				setMensaje(
						"El usuario no tiene asignada adscripción por lo cual no podrá visualizar a sus empleados. ");
			} else {

				catalogoAdscripciones = new ArrayList<>();
				CatalogoDTO adscripcion = catalogo.obtenerAdscripcionPorId(getIdAdscripcionUsuario());
				SelectItem a = new SelectItem(adscripcion.getId(), adscripcion.getNombre());
				catalogoAdscripciones.add(a);
				setMensaje("El usuario solo podrá visualizar empleados que pertenezcan a su adscripción");
			}
		} else {
			setCatalogoAdscripciones(SelectItemsUtil.listaCatalogos(catalogo.consultarAdscripciones()));
			setMensaje("El usuario podrá visualizar a todos los empleados.");
		}
	}

	public List<InfoEmpleadoDTO> buscarEmpleadoAutoComplete(String query) {

		List<InfoEmpleadoDTO> listadoEmpleadoDTO = null;

		if (query == "") {
			query = ".";
		}
		if (query.length() > 4) {
			FiltroDTO filtroDTO = new FiltroDTO();
			filtroDTO.setCriterio(query);
			filtroDTO.setId(getIdAdscripcionUsuario());
			if (isTieneRestriccionAdscripcion()) {
				filtroDTO.setTipoFiltro(EnumTipoFiltro.CRITERIO_COMBO_ADSCRIPCION_ASIGNADA);
			} else {
				filtroDTO.setTipoFiltro(EnumTipoFiltro.CRITERIO_COMBO_TODAS_ADSCRIPCIONES);
			}
			listadoEmpleadoDTO = empleadoService.consultarEmpleadosConPuestosActivos(filtroDTO);
		}
		return listadoEmpleadoDTO;

	}

	public String imprimirReporte() {
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");

		Integer idEmpleadoR = -1;

		if (empleadoDTO != null) {
			if (empleadoDTO.getIdEmpleado() != null) {
				idEmpleadoR = empleadoDTO.getIdEmpleado();
			}
		}

		switch (tipoReporte) {
		case 1:
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
					"reporte-lista-asistencia-empleado?idEmpleado=" + idEmpleadoR + "&fechaInicio="
							+ dt1.format(fechaInicio) + "&fechaFin=" + dt1.format(fechaFin) + "&ida=" + idAdscripcion
							+ "&idt=" + idTipoContratacion + "&idd=" + idDepartamentos);
			break;
		case 2:
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
					"reporte-tarjeta-empleado?idEmpleado=" + idEmpleadoR + "&fechaInicio=" + dt1.format(fechaInicio)
							+ "&fechaFin=" + dt1.format(fechaFin) + "&ida=" + idAdscripcion + "&idt="
							+ idTipoContratacion + "&idd=" + idDepartamentos);
			break;
		case 3:
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
					"reporte-horas-extra-empleado?idEmpleado=" + idEmpleadoR + "&fechaInicio=" + dt1.format(fechaInicio)
							+ "&fechaFin=" + dt1.format(fechaFin) + "&ida=" + idAdscripcion + "&idt="
							+ idTipoContratacion + "&idd=" + idDepartamentos);
			break;
		case 4:
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
					"reporte-lista-incidencias-acumulado?idEmpleado=" + idEmpleadoR + "&fechaInicio="
							+ dt1.format(fechaInicio) + "&fechaFin=" + dt1.format(fechaFin) + "&ida=" + idAdscripcion
							+ "&idt=" + idTipoContratacion + "&idd=" + idDepartamentos);
			break;
		case 5:
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
					"reporte-lista-incidencias-empleados?idEmpleado=" + idEmpleadoR + "&fechaInicio="
							+ dt1.format(fechaInicio) + "&fechaFin=" + dt1.format(fechaFin) + "&ida=" + idAdscripcion
							+ "&idt=" + idTipoContratacion + "&idd=" + idDepartamentos);
			break;

		default:
			break;
		}

		return "imprimirreporte.xhtml?faces-redirect=true";

	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isTieneRestriccionAdscripcion() {
		return tieneRestriccionAdscripcion;
	}

	public void setTieneRestriccionAdscripcion(boolean tieneRestriccionAdscripcion) {
		this.tieneRestriccionAdscripcion = tieneRestriccionAdscripcion;
	}

	public Integer getIdAdscripcionUsuario() {
		return idAdscripcionUsuario;
	}

	public void setIdAdscripcionUsuario(Integer idAdscripcionUsuario) {
		this.idAdscripcionUsuario = idAdscripcionUsuario;
	}

	public List<SelectItem> getCatalogoAdscripciones() {
		return catalogoAdscripciones;
	}

	public void setCatalogoAdscripciones(List<SelectItem> catalogoAdscripciones) {
		this.catalogoAdscripciones = catalogoAdscripciones;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(Integer tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public InfoEmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}

	public List<SelectItem> getTipoContratacion() {

		return SelectItemsUtil.listaCatalogos(catalogoTipoContratacionDTO);
	}

	public List<SelectItem> getDepartamentos() {
		return SelectItemsUtil.listaCatalogos(catalogoDepartamentosDTO);
	}

	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}

	public Integer getIdTipoContratacion() {
		return idTipoContratacion;
	}

	public void setIdTipoContratacion(Integer idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}

	public Integer getIdDepartamentos() {
		return idDepartamentos;
	}

	public void setIdDepartamentos(Integer idDepartamentos) {
		this.idDepartamentos = idDepartamentos;
	}

}
