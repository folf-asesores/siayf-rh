/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumFiltroConsultaVacante;
import mx.gob.saludtlax.rh.puestosautorizados.FiltroVacanteDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 13/09/2016 01:42:00
 */
@ManagedBean(name = "configuracionArea")
@ViewScoped
public class ConfiguracionAreaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 75529045788980696L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private PuestosAutorizadosEmpleados puestosEmpleados;

	private UbicacionEmpleadoView view;

	@PostConstruct
	public void inicio() {
		view = new UbicacionEmpleadoView();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

		view.setListaAdscripciones(SelectItemsUtil.listaCatalogos(catalogo.consultarAdscripciones()));
		view.setListaSubadscripcion(SelectItemsUtil.listaCatalogos(catalogo.consultarSubadscripciones()));
		view.setListaServicio(SelectItemsUtil.listaCatalogos(catalogo.consultarServicios()));
		view.setListaFuncion(SelectItemsUtil.listaCatalogos(catalogo.consultarFunciones()));
		view.setListaClues(SelectItemsUtil.listaCatalogos(catalogo.consultarClues()));
		view.setListaCentrosResponsabilidad(
				SelectItemsUtil.listaCatalogos(catalogo.consultarCentrosResponsabilidades()));

		view.setListaUbicaciones(SelectItemsUtil.listaFiltrosConsultaUbicaciones());
		view.setListaDependencias(SelectItemsUtil.listaCatalogos(catalogo.listaDependencias()));
		view.setMostrarBusqueda(true);
		view.setCriterio(" ");
		view.getUbicacion().setIdUsuario(usuario.getIdUsuario());
	}

	public void seleccionarFiltro() {
		try {
			view.getPuestosEmpleados().clear();
			view.setMostrarUnidades(false);
			view.setMostrarCriterio(false);
			if (view.getIdFiltro() == EnumFiltroConsultaVacante.UNIDAD_RESPONSABLE) {
				view.setMostrarUnidades(true);
			} else if (view.getIdFiltro() == EnumFiltroConsultaVacante.NOMBRE_RFC) {
				view.setMostrarCriterio(true);
			} else {

				FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
				filtroVacanteDTO.setTipoBusqueda(view.getIdFiltro());
				view.setPuestosEmpleados(puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
				if (view.getPuestosEmpleados().isEmpty()) {
					JSFUtils.warningMessage("", "Sin resultados.");
				}
			}

		} catch (ReglaNegocioException exception) {
			throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
		}
	}

	public void buscarEmpleados() {
		try {
			view.getPuestosEmpleados().clear();
			FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
			filtroVacanteDTO.setTipoBusqueda(view.getIdFiltro());
			filtroVacanteDTO.setIdentificador(view.getIdContexto());
			view.setPuestosEmpleados(puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
			if (view.getPuestosEmpleados().isEmpty()) {
				JSFUtils.warningMessage("", "Sin resultados.");
			}

		} catch (ReglaNegocioException exception) {
			throw new ReglaNegocioException(exception.getMessage(), exception.getCodigoError());
		}
	}

	public void obtenerUnidadesResponsables() {
		this.view.getListaUnidadesResponsables().clear();
		if (view.getIdDependencia() != 0) {
			view.setListaUnidadesResponsables(SelectItemsUtil
					.listaCatalogos(catalogo.listaUnidadesResponsablesPorDependencia(view.getIdDependencia())));
		}

	}

	public void buscarEmpleadoCriterio() {
		try {
			if (view.getCriterio().length() > 3) {
				view.getPuestosEmpleados().clear();
				FiltroVacanteDTO filtroVacanteDTO = new FiltroVacanteDTO();
				filtroVacanteDTO.setTipoBusqueda(view.getIdFiltro());
				filtroVacanteDTO.setCriterio(view.getCriterio());
				view.setPuestosEmpleados(puestosEmpleados.consultaVacantesPorCriterio(filtroVacanteDTO));
				if (view.getPuestosEmpleados().isEmpty()) {
					JSFUtils.warningMessage("", "Sin resultados.");
				}
			}
		} catch (ReglaNegocioException | ValidacionException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void obtenerDetalle(Integer idInventario) {
		try {
			view.getUbicacion().setIdInventarioVacante(idInventario);
			view.setMostrarBusqueda(false);
			view.setMostrarDetalle(true);
			view.setPuesto(puestosEmpleados.obtenerInformacionPuesto(idInventario));

			view.getUbicacion().setIdAdscripcion(view.getPuesto().getIdAdscripcion());
			view.getUbicacion().setIdSubadscripcion(view.getPuesto().getIdSubadscripcion());
			view.getUbicacion().setIdServicio(view.getPuesto().getIdServicio());
			view.getUbicacion().setIdFuncion(view.getPuesto().getIdFuncion());
			view.getUbicacion().setIdClue(view.getPuesto().getIdClue());
			view.getUbicacion().setIdCentroResponsabilidad(view.getPuesto().getIdCentroResponsabilidad());

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void ubicarEmpleado() {
		try {
			puestosEmpleados.ubicarEmpleado(view.getUbicacion());
			inicio();
			JSFUtils.infoMessage("", "¡La ubicación ha sido registrada con éxito!");

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public UbicacionEmpleadoView getView() {
		return view;
	}

	public void setView(UbicacionEmpleadoView view) {
		this.view = view;
	}

}
