/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.expediente.EntidadContexto;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 07/11/2016 09:42:16
 */
@ManagedBean(name = "revisionSuplencia")
@ViewScoped
public class RevisionSuplenciaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923314677613390055L;
	@Inject
	private AdjuntoEmpleado adjuntoEmpleado;
	@Inject
	private Catalogo catalogos;
	@Inject
	private Suplencia suplencia;

	private RevisionSuplenciaView view;

	@PostConstruct
	public void inicio() {
		view = new RevisionSuplenciaView();
		view.setMostrarBusqueda(true);

		view.setListaEstatus(SelectItemsUtil.listaEstatusSuplencias());
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		UsuarioDTO usuarioLogeado = (UsuarioDTO) httpSession
				.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
		view.setIdUsuario(usuarioLogeado.getIdUsuario());
		view.setListaCentrosResponsabilidades(
				SelectItemsUtil.listaCatalogos(catalogos.consultarCentrosResponsabilidades()));
		QuincenaActivaDTO quincenaActivaDTO = suplencia.obtenerQuincenaActiva();
		view.getConsulta().setQuincena(quincenaActivaDTO.getNumeroQuincenaActiva());
		view.getConsulta().setEjercicio(quincenaActivaDTO.getEjercicioFiscal());
		view.setListaTiposBusqueda(SelectItemsUtil.listaBusquedasRevisionSuplencias());

	}

	public void seleccionarTipoBusqueda() {

		view.setMostrarCamposBusquedaCentro(false);
		view.setMostrarCamposBusquedaCriterio(false);
		view.getConsulta().setIdCentroResponsabilidad(0);
		view.getConsulta().setCriterio("");

		if (view.getConsulta().getTipoConsulta() == EnumTipoConsultaSuplencia.QUINCENA_POR_CRITERIO) {
			view.setMostrarCamposBusquedaCriterio(true);

		} else if (view.getConsulta().getTipoConsulta() == EnumTipoConsultaSuplencia.QUINCENAS_CENTROS_ESTATUS) {
			view.setMostrarCamposBusquedaCentro(true);
		}

	}

	public void consultarQuincenasSuplente() {

		try {
			view.getConsulta().setEstatus(EnumEstatusSuplencia.REVISION);
			view.setQuincenas(suplencia.consultarQuincenasSuplente(view.getConsulta()));
			if (view.getQuincenas().isEmpty()) {
				JSFUtils.warningMessage("", "El suplente no tiene quincenas en revisión con los criterios ingresados.");
			}
		} catch (ValidacionException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}

	}

	public RevisionSuplenciaView getView() {
		return view;
	}

	public void setView(RevisionSuplenciaView view) {

	}

	public void mostrarDesglose(QuincenaSuplenteDTO quincena) {

		view.setQuincenaSeleccionada(quincena);
		view.getDetallesQuincena().clear();
		view.setMostrarPendientes(false);
		ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
		dto.setConDetalleMovimieto(true);
		dto.setIdQuincena(quincena.getIdQuincena());
		view.setDetallesQuincena(suplencia.consultarDetallesSuplenteQuincena(dto));
		if (!view.getDetallesQuincena().isEmpty()) {
			List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
					.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto.SUPLENCIA,
							quincena.getIdQuincena());
			view.setDocumentosAdjuntos(documentosAdjuntosGradoAcademico);
		} else {
			JSFUtils.warningMessage("", "La quincena seleccionada no tiene detalles asignados.");
		}
		view.setDetallesPendientes(
				suplencia.consultarSuplenciasPendientes(quincena.getIdSuplente(), quincena.getIdQuincena()));
		if (!view.getDetallesPendientes().isEmpty()) {
			view.setMostrarPendientes(true);
		}

		view.setMostrarDesglose(true);
		view.setMostrarBusqueda(false);
		calcularTotal();

	}

	public void calcularTotal() {
		BigDecimal totalAprobado = BigDecimal.ZERO;
		BigDecimal totalPendiente = BigDecimal.ZERO;
		int totalDias = 0;
		view.setTotalAprobado(totalAprobado);
		view.setTotalPendiente(totalPendiente);
		if (!view.getDetallesQuincena().isEmpty()) {
			for (DetalleSuplenciaDTO dto : view.getDetallesQuincena()) {
				if (dto.getEstatus().equals(EnumEstatusSuplencia.APROBADO)) {
					totalAprobado = totalAprobado.add(dto.getTotal());
				} else {
					totalPendiente = totalPendiente.add(dto.getTotal());
				}
				totalDias = totalDias + dto.getDias();
			}
			view.setTotalAprobado(totalAprobado);
			view.setTotalPendiente(totalPendiente);
			view.setTotalDias(totalDias);
		}
	}

	public void ocultarDesglose() {
		view.setMostrarDesglose(false);
		view.setMostrarBusqueda(true);
		view.getDetallesQuincena().clear();
	}

	public void mostrarDetalle(DetalleSuplenciaDTO detalle) {
		String estatusQuincena = view.getQuincenaSeleccionada().getEstatus();

		if (estatusQuincena.equals(EnumEstatusSuplencia.APROBADO)) {
			JSFUtils.errorMessage("", "La quincena se ha enviado a cierre no puede ser modificada.");

		} else {
			view.setMostrarMovimiento(false);
			view.setDetalleSeleccionado(detalle);
			view.setMostrarDetalle(true);
			view.setIdDetalleQuincena(detalle.getIdDetalleSuplencia());
			if (detalle.getIdTipoSuplencia() == EnumTipoSuplencia.INCAPACIDADES
					|| detalle.getIdTipoSuplencia() == EnumTipoSuplencia.LICENCIA_CON_SUELDO
					|| detalle.getIdTipoSuplencia() == EnumTipoSuplencia.LICENCIA_SIN_SUELDO
					|| detalle.getIdTipoSuplencia() == EnumTipoSuplencia.COMISION) {
				view.setMostrarMovimiento(true);
			}
		}

	}

	public void ocultarDetalle() {
		view.setMostrarDetalle(false);
	}

	public void actualizarDetalleSuplencia() {
		try {
			suplencia.actualizarEstatusDetalleQuincena(view.getIdDetalleQuincena(), view.getEstatus());
			view.setMostrarDetalle(false);
			ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
			dto.setConDetalleMovimieto(true);
			dto.setIdQuincena(view.getQuincenaSeleccionada().getIdQuincena());
			view.setDetallesQuincena(suplencia.consultarDetallesSuplenteQuincena(dto));
			calcularTotal();

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessageEspecifico("error", "", exception.getMessage());
		}
	}

	public void mostrarDescuento(DetalleSuplenciaDTO detalle) {
		view.setDetalleSeleccionado(detalle);
		view.setMostrarDescuento(true);
		DescuentoSuplenciaDTO descuento = new DescuentoSuplenciaDTO();
		view.setDescuento(descuento);
		view.getDescuento().setIdDetalleSuplencia(detalle.getIdDetalleSuplencia());
		view.getDescuento().setIdUsuarioLogeado(view.getIdUsuario());
	}

	public void ocultarDescuento() {
		view.setMostrarDescuento(false);
	}

	public void descuentoSuplencia() {
		try {
			suplencia.descuentoSuplencia(view.getDescuento());
			DescuentoSuplenciaDTO descuento = new DescuentoSuplenciaDTO();
			view.setDescuento(descuento);
			view.setMostrarDescuento(false);
			ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
			dto.setConDetalleMovimieto(true);
			dto.setIdQuincena(view.getQuincenaSeleccionada().getIdQuincena());
			view.setDetallesQuincena(suplencia.consultarDetallesSuplenteQuincena(dto));
			calcularTotal();
		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessageEspecifico("errorEdicion", "", exception.getMessage());
		}
	}

	public void descargarAdjunto(InformacionAdjuntoDTO adjunto) {
		try {

			byte[] bytes = adjuntoEmpleado.obtenerAdjuntoPorIdAdjunto(adjunto.getIdAdjunto());

			JSFUtils.descargarArchivo(bytes, adjunto.getNombreAdjunto(), adjunto.getExtension().getMIMEType());
			JSFUtils.infoMessage("Descarga iniciada", "La descarga del archivo ha iniciado.");

		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("Error: ", validatorException.getMessage());
		} catch (IOException ioException) {
			JSFUtils.errorMessage("Error: ", ioException.getMessage());
		}
	}

	public void eliminarAdjunto(Integer idAdjunto) {
		try {

			adjuntoEmpleado.elimnar(idAdjunto);

			List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
					.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto.SUPLENCIA,
							view.getDetallesQuincena().get(0).getIdQuincena());
			view.setDocumentosAdjuntos(documentosAdjuntosGradoAcademico);

			JSFUtils.infoMessageEspecifico("info", "", "El documento se ha eliminado correctamente.");

		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidatorException validatorException) {
			JSFUtils.errorMessage("Error: ", validatorException.getMessage());
		}
	}

	public String imprimirReporte() {
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("url",
				"reporte-lista-asistencia-empleado?idEmpleado=" + view.getDetalleSeleccionado().getIdEmpleadoSuplente()
						+ "&fechaInicio=" + dt1.format(view.getDetalleSeleccionado().getFechaInicio()) + "&fechaFin="
						+ dt1.format(view.getDetalleSeleccionado().getFechaFin()) + "&ida=" + -1 + "&idt=" + -1
						+ "&idd=" + -1);

		return "imprimirreporte.xhtml?faces-redirect=true";

	}

	public void actualizarEstatusQuincenaSuplencia() {
		try {
			suplencia.actualizarEstatusQuincena(view.getQuincenaSeleccionada().getIdQuincena(),
					EnumEstatusSuplencia.APROBADO);
			JSFUtils.infoMessage("", "La quincena se ha mandado a cierre");
			view.setMostrarBusqueda(true);
			view.setMostrarDesglose(false);

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	/**
	 * Aprueba de manera global los detalles de una suplencia.
	 */
	public void aprobarDetalles() {
		if (!view.getDetallesQuincena().isEmpty()) {
			for (DetalleSuplenciaDTO d : view.getDetallesQuincena()) {
				suplencia.actualizarEstatusDetalleQuincena(d.getIdDetalleSuplencia(), EnumEstatusSuplencia.APROBADO);
				view.setMostrarDetalle(false);
			}

			ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
			dto.setConDetalleMovimieto(true);
			dto.setIdQuincena(view.getQuincenaSeleccionada().getIdQuincena());
			view.setDetallesQuincena(suplencia.consultarDetallesSuplenteQuincena(dto));
			calcularTotal();
			JSFUtils.infoMessage("", "¡Se han aprobado todos los detalles con éxito!");

		}

	}

	public void regresarACaptura() {
		try {
			suplencia.actualizarEstatusQuincena(view.getQuincenaSeleccionada().getIdQuincena(),
					EnumEstatusSuplencia.CAPTURA);
			JSFUtils.infoMessage("", "La quincena se ha regresado a captura con éxito.");
			view.setMostrarBusqueda(true);
			view.setMostrarDesglose(false);

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void agregarSuplenciaPendiente(Integer idDetalleSuplencia, Integer idSuplente) {
		try {
			suplencia.agregarSuplenciaPendiente(view.getQuincenaSeleccionada().getIdQuincena(), idDetalleSuplencia);
			view.setDetallesPendientes(suplencia.consultarSuplenciasPendientes(idSuplente,
					view.getQuincenaSeleccionada().getIdQuincena()));
			ConsultaSuplenciaDTO dto = new ConsultaSuplenciaDTO();
			dto.setConDetalleMovimieto(true);
			dto.setIdQuincena(view.getQuincenaSeleccionada().getIdQuincena());
			view.setDetallesQuincena(suplencia.consultarDetallesSuplenteQuincena(dto));
			if (!view.getDetallesQuincena().isEmpty()) {
				calcularTotal();
			} else {
				JSFUtils.warningMessage("", "La quincena seleccionada no tiene detalles asignados.");
			}
			JSFUtils.infoMessage("", "El detalle ha sido agregado con éxito");

		} catch (ReglaNegocioException exception) {
			JSFUtils.errorMessage("", exception.getMessage());
		}
	}

	public void mostrarAdjuntos() {

		if (view.getQuincenaSeleccionada().getIdQuincena() != null) {
			List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico = adjuntoEmpleado
					.consultarInformacionAdjuntosPorEntidadContextoIdEntidadContexto(EntidadContexto.SUPLENCIA,
							view.getQuincenaSeleccionada().getIdQuincena());
			view.setDocumentosAdjuntos(documentosAdjuntosGradoAcademico);
			if (!view.getDocumentosAdjuntos().isEmpty()) {
				view.setMostrarDocumentacion(true);
			} else {
				JSFUtils.warningMessage("", "Quincena sin documentación.");
			}

		} else {
			JSFUtils.errorMessage("", "La quincena no tiene asignado ningun detalle");
		}

	}

	public void ocultarAdjuntos() {
		view.setMostrarDocumentacion(false);
		view.getDocumentosAdjuntos().clear();
	}

}
