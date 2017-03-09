/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/05/2016 04/05/2016
 */

@ViewScoped
@ManagedBean(name = "movimientoPersonal")
public class MovimientosEmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3392346042740305963L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private Empleado empleado;
	@Inject
	private MovimientosEmpleados movimientosEmpleados;
	@Inject
	private Tabulador tabulador;

	private MovimientoEmpleadoView view;

	@PostConstruct
	public void inicio() {
		this.view = new MovimientoEmpleadoView();
		List<CatalogoDTO> movimientosPadre = catalogo
				.consultaMovimientosAutorizadosPorPadre(0);

		this.view.setListaMovimientosPadre(SelectItemsUtil
				.listaCatalogos(movimientosPadre));
		this.view.setMostrarPanelGroupBusqueda(true);
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession
				.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
		this.view.getMovimiento().setIdUsuario(usuario.getIdUsuario());

	}

	public void consultarEmpleado() {

		FiltroDTO filtroDTO = new FiltroDTO();
		filtroDTO.setCriterio(this.view.getCriterio());
		filtroDTO.setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP);

		List<InfoEmpleadoDTO> listaInfoEmpleado = empleado
				.consultarEmpleadosConPuestosActivos(filtroDTO);
		this.view.setEmpleados(listaInfoEmpleado);
		
		if (view.getEmpleados().isEmpty()) {
			JSFUtils.warningMessage(
					"",
					"No se encontraron empleados con el criterio "
							+ filtroDTO.getCriterio());
		}

	}

	public void seleccionarMovimiento() {
		try {
			this.view.setMostrarIncapacidad(false);
			view.setMostrarFechas(false);
			int movimiento = view.getMovimiento().getIdMovimiento();
			int movimientoPadre = movimientosEmpleados
					.obtenerPadreMovimiento(movimiento);

			movimientosEmpleados.validarMovimiento(view.getInfoEmpleado()
					.getIdVacante(), view.getMovimiento().getIdMovimiento());

			if (movimientoPadre == EnumTipoMovimiento.LICENCIA_POR_INCAPACIDAD_MEDICA
					|| movimientoPadre == EnumTipoMovimiento.LICENCIAS_C_C_S
					|| movimientoPadre == EnumTipoMovimiento.LICENCIAS_C_S_S
					|| movimientoPadre == EnumTipoMovimiento.LICENCIAS_D_C_S
					|| movimientoPadre == EnumTipoMovimiento.LICENCIAS_D_S_S) {
				if (view.getMovimiento().getIdMovimiento() == EnumTipoMovimiento.LICENCIA_POR_INCAPACIDAD_MEDICA) {
					view.setMostrarIncapacidad(true);
				} else {
					view.setMostrarFechas(true);
				}
			} else if (movimiento == EnumTipoMovimiento.PROMOCION_PUESTO_AUMENTO_PERCEPCIONES) {
				view.setMostrarModificacionPuesto(true);
				view.setListaPuestos(SelectItemsUtil.listaCatalogos(catalogo
						.listaPuestos()));
			}
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void obtenerSalario() {
		try {
			view.setSalario(tabulador.obtenerSueldoPorPuestoTipoTabulador(
					view.getIdPuesto(), EnumTipoContratacion.BASE));
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void seleccionarEmpleado(InfoEmpleadoDTO infoEmpleado) {
		this.view.setMostrarPanelGroupBusqueda(false);
		this.view.setMostrarPanelGroupMovimiento(true);
		this.view.setInfoEmpleado(infoEmpleado);
		this.view.getMovimiento().setIdVacante(infoEmpleado.getIdVacante());

	}

	public void consultarMovimientosHijos() {
		List<CatalogoDTO> movimientosHijos = catalogo
				.consultaMovimientosAutorizadosPorPadre(this.view
						.getIdMovimientoPadre());
		this.view.setListaMovimientosHijos(SelectItemsUtil
				.listaCatalogos(movimientosHijos));
	}

	public void crearMovimiento() {
		try {
			movimientosEmpleados.crearMovimientoEmpleado(this.view
					.getMovimiento());
			inicio();
			JSFUtils.infoMessage("",
					"¡El movimiento ha sido registrado con éxito!");
		} catch (ReglaNegocioException exception) {
			throw new ReglaNegocioException(exception.getMessage(),
					exception.getCodigoError());
		}
	}

	public MovimientoEmpleadoView getView() {
		return view;
	}

	public void setView(MovimientoEmpleadoView view) {
		this.view = view;
	}

}
