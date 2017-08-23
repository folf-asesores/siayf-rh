/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumTipoFiltro;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/12/2016 02:18:27
 */
@ManagedBean(name = "modificacionLaboral")
@ViewScoped
public class ModificacionLaboralController implements Serializable {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -2751121828958516333L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private ConfiguracionPresupuestal datoLaboral;
	@Inject
	private Empleado empleado;
	@Inject
	private PuestosAutorizadosEmpleados puestosAutorizados;
	@Inject
	private Tabulador tabulador;

	private ModificacionLaboralView view;

	@PostConstruct
	public void inicio() {
		view = new ModificacionLaboralView();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
		view.setIdUsuarioLogeado(usuario.getIdUsuario());
		view.setListaDependencias(SelectItemsUtil.listaCatalogos(catalogo.listaDependencias()));
		view.setListaFuentesFinanciamiento(SelectItemsUtil.listaCatalogos(catalogo.listaFuentesFinanciamientos()));
		view.setListaTiposRecursos(SelectItemsUtil.listaCatalogos(catalogo.listaTiposRecursos()));
		view.setListaProyectos(SelectItemsUtil.listaCatalogos(catalogo.consultarProyectos()));
		view.setListaPuestos(SelectItemsUtil.listaCatalogos(catalogo.listaPuestos()));
		view.setListaProgramas(SelectItemsUtil.listaCatalogos(catalogo.consultarProgramas()));
	}

	public void seleccionarEmpleado(InfoEmpleadoDTO empleado) {
		try {
			view.setPuesto(puestosAutorizados.obtenerInformacionIdPuesto(empleado.getIdVacante()));
			view.setDatoLaboral(datoLaboral.obtenerDatosLaboralesPuesto(empleado.getIdVacante()));
			view.getDatoLaboral().setSeguroPopular(view.getPuesto().getSeguroPopular());
			view.setMostrarLaboral(true);

			int tipoContratacion = view.getDatoLaboral().getTipoContratacion();
			if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
				view.setMostrarSalarioEstatal(true);
			}
			if (tipoContratacion == EnumTipoContratacion.BASE || tipoContratacion == EnumTipoContratacion.CONFIANZA
					|| tipoContratacion == EnumTipoContratacion.INTERINATO
					|| tipoContratacion == EnumTipoContratacion.FORMALIZADOS
					|| tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
				view.setMostrarSalarioFederal(true);
			}

			try {
				view.setSalario(tabulador.obtenerSueldoPorPuestoTipoTabulador(view.getDatoLaboral().getIdPuesto(),
						tipoContratacion));
				view.getDatoLaboral().setIdTabulador(view.getSalario().getIdTabulador());

			} catch (ReglaNegocioException exception) {
				JSFUtils.warningMessageEspecifico("error", "", exception.getMessage());

			}

			obtenerUnidadesResponsables();
			obtenerSubfuentesFinanciamiento();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public void consultarEmpleado() {
		view.getFiltro().setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP);
		view.setEmpleados(empleado.consultarEmpleadosConPuestosActivos(view.getFiltro()));
		if (view.getEmpleados().isEmpty()) {
			JSFUtils.warningMessage("",
					"No se encontraron empleados con el criterio " + view.getFiltro().getCriterio());
		}
	}

	public void editarDatosLaborales() {
		try {
			datoLaboral.modificarDatoLaboral(view.getDatoLaboral(), view.getPuesto().getIdPuesto(),
					view.getIdUsuarioLogeado());
			view.setMostrarLaboral(false);
			JSFUtils.infoMessage("", "Los datos laborales han sido moficados con éxito.");

		} catch (ReglaNegocioException exception) {

			JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
		}
	}

	public void obtenerUnidadesResponsables() {
		view.getListaUnidadesResponsables().clear();
		if (view.getDatoLaboral().getIdDependencia() != null) {
			view.setListaUnidadesResponsables(SelectItemsUtil.listaCatalogos(
					catalogo.listaUnidadesResponsablesPorDependencia(view.getDatoLaboral().getIdDependencia())));
		}

	}

	public void obtenerSubfuentesFinanciamiento() {
		view.getListaSubfuentesFinanciamiento().clear();
		if (view.getDatoLaboral().getIdFuenteFinanciamiento() != null) {
			view.setListaSubfuentesFinanciamiento(
					SelectItemsUtil.listaCatalogos(catalogo.listaSubfuentesFinanciamientosPorFinanciamiento(
							view.getDatoLaboral().getIdFuenteFinanciamiento())));
		}
	}

	public void obtenerSalario() {
		view.setMostrarSalarioEstatal(false);
		view.setMostrarSalarioFederal(false);

		try {
			int tipoContratacion = view.getDatoLaboral().getTipoContratacion();
			if (ValidacionUtil.esNumeroPositivo(tipoContratacion)) {
				view.setSalario(tabulador.obtenerSueldoPorPuestoTipoTabulador(view.getDatoLaboral().getIdPuesto(),
						tipoContratacion));
				view.getDatoLaboral().setIdTabulador(view.getSalario().getIdTabulador());

				if (tipoContratacion == EnumTipoContratacion.BASE || tipoContratacion == EnumTipoContratacion.CONFIANZA
						|| tipoContratacion == EnumTipoContratacion.INTERINATO
						|| tipoContratacion == EnumTipoContratacion.FORMALIZADOS
						|| tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
					view.setMostrarSalarioFederal(true);
				} else if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
					view.setMostrarSalarioEstatal(true);
					view.getDatoLaboral().setSueldo(view.getSalario().getSueldoBaseMensualMinimo());
					view.getDatoLaboral().setSueldo01(BigDecimal.ZERO);
					view.getDatoLaboral().setSueldo14(BigDecimal.ZERO);
				}
			}

		} catch (ReglaNegocioException exception) {
			if (exception.getCodigoError() == ReglaNegocioCodigoError.TABULADOR_NO_CONFIGURADO) {
				JSFUtils.warningMessageEspecifico("advertencia", "", exception.getMessage());
			}

			JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
		}

	}

	public void ocultarEdicion() {
		view.setMostrarLaboral(false);
		DatoLaboralDTO datoLaboral = new DatoLaboralDTO();
		view.setDatoLaboral(datoLaboral);
	}

	public void mostrarEdicionPrograma(Integer idPuesto) {

		view.setPuesto(puestosAutorizados.obtenerInformacionIdPuesto(idPuesto));
		view.setIdPuesto(view.getPuesto().getIdPuesto());
		view.setIdPrograma(view.getPuesto().getIdPrograma());

		view.setMostrarPrograma(true);
	}

	public void ocultarEdicionPrograma() {
		view.setMostrarPrograma(false);
	}

	public void editarPrograma() {

		try {
			puestosAutorizados.modificacionPrograma(view.getIdPuesto(), view.getIdPrograma());
			view.setMostrarPrograma(false);
			JSFUtils.infoMessage("", "¡El programa ha sido modificado con éxito!");

		} catch (NoResultException exception) {
			JSFUtils.errorMessageEspecifico("errorPrograma", "", exception.getMessage());
		}

	}

	public ModificacionLaboralView getView() {
		return view;
	}

	public void setView(ModificacionLaboralView view) {
		this.view = view;
	}

}
