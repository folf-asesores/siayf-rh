/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.nombramientos.impresion;

import java.io.IOException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.empleados.nombramientos.Nombramiento;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:29:49 13/09/2016
 */
@ManagedBean(name = "impresionNombramiento")
@ViewScoped
public class ImpresionNombramientoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8820506125977536557L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private Nombramiento nombramiento;
	@Inject
	private PuestosAutorizadosEmpleados puestosEmpleados;

	private ImpresionNombramientoView view;

	@PostConstruct
	public void init() {
		this.view = new ImpresionNombramientoView();

		this.view.setItemsTipoNombramiento(SelectItemsUtil.listaCatalogos(catalogo.obtenerTipoNombramiento()));

		obtenerListaInfoNombramiento();
	}

	public void obtenerListaInfoNombramiento() {
		try {

			List<InfoNombramientoDTO> listaNombramiento = nombramiento.obtenerListaInfoNombramiento();

			if (!listaNombramiento.isEmpty()) {
				this.view.setListaNombramiento(listaNombramiento);
			} else {
				this.view.setListaNombramiento(new ArrayList<InfoNombramientoDTO>());
			}

		} catch (ValidacionException validacionException) {
			throw new ValidacionException(validacionException.getMessage(), null);
		} catch (ReglaNegocioException reglaNegocioException) {
			throw new ReglaNegocioException(reglaNegocioException.getMessage(), reglaNegocioException.getCodigoError());
		}
	}

	public void obtenerListaInfoNombramientoPoTipo() {
		try {

			List<InfoNombramientoDTO> listaNombramiento = nombramiento
					.obtenerListaInfoNombramientoPorTipo(this.view.getTipoNombramiento());

			if (!listaNombramiento.isEmpty()) {
				this.view.setListaNombramiento(listaNombramiento);
			} else {
				this.view.setListaNombramiento(new ArrayList<InfoNombramientoDTO>());
			}

		} catch (ValidacionException validacionException) {
			throw new ValidacionException(validacionException.getMessage(), null);
		} catch (ReglaNegocioException reglaNegocioException) {
			throw new ReglaNegocioException(reglaNegocioException.getMessage(), reglaNegocioException.getCodigoError());
		}
	}

	public void mostrarDetalleNombramiento(Integer idNombramiento, String tipoNombramiento) {
		try {

			String nombreTipoNombramiento = "NOMBRAMIENTO " + tipoNombramiento;

			this.view.setNombreTipoNombramiento(nombreTipoNombramiento);

			Integer idInventario = nombramiento.obtenerInventarioVacantePorIdNombramiento(idNombramiento);

			this.view.setPuestoEmpleadoDTO(puestosEmpleados.obtenerInformacionPuesto(idInventario));

			this.view.setInfoLugarAdscripcionNombramientoDTO(
					nombramiento.obtenerInfoLugarAdscripcion(this.view.getPuestoEmpleadoDTO().getIdAdscripcion(),
							this.view.getPuestoEmpleadoDTO().getIdSubadscripcion(),
							this.view.getPuestoEmpleadoDTO().getIdServicio()));

			this.view.setIdNombramiento(idNombramiento);

			administarcioVista(this.view.getNombreTipoNombramiento());

			this.view.getItemsTiposAdscripcion()
					.put(EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE + ": "
							+ this.view.getPuestoEmpleadoDTO().getUnidadResponsable(),
							EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE);
			this.view.getItemsTiposAdscripcion()
					.put(EnumTipoAdscripcionNombramiento.ADSCRIPCION + ": "
							+ this.view.getInfoLugarAdscripcionNombramientoDTO().getAdscripcion(),
							EnumTipoAdscripcionNombramiento.ADSCRIPCION);
			this.view.getItemsTiposAdscripcion()
					.put(EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION + ": "
							+ this.view.getInfoLugarAdscripcionNombramientoDTO().getAreaAdscripcion(),
							EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION);
			this.view.getItemsTiposAdscripcion()
					.put(EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION + ": "
							+ this.view.getInfoLugarAdscripcionNombramientoDTO().getLugarAdscripcion(),
							EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION);

		} catch (ValidacionException validacionException) {
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());

		} catch (ReglaNegocioException reglaNegocioException) {
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());

		}
	}

	public static String ucFirst(String str) {
		if (str.isEmpty()) {
			return str;
		} else {
			return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		}
	}

	public void descargarNombramiento() {
		try {

			// String nombreReporte =
			// nombreReporte(this.view.getNombreTipoNombramiento());

			this.view.getClasificacionReporteDTO().setClasificacionReporte(this.view.getNombreTipoNombramiento());
			this.view.getClasificacionReporteDTO().setHorarioTrabajo("El que le asigne su Unidad");
			this.view.getClasificacionReporteDTO().setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

			Integer idClasificacion = nombramiento
					.actualizarEstructuraNombramiento(this.view.getClasificacionReporteDTO());

			actualizarNombramientoImpreso(this.view.getTipoAdscripcion());

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"nombramiento-generico", "TIPO_REPORTE", "docx", "ID_NOMBRAMIENTO",
					String.valueOf(this.view.getIdNombramiento()), "ID_CLASIFICACION",
					String.valueOf(idClasificacion) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			this.view.setBytes(admintradorReportes.obtenerReporte(referencia));

			if (this.view.getBytes() != null) {
				JSFUtils.descargarArchivo(this.view.getBytes(),
						CadenaUtil.converterSpace(this.view.getNombreTipoNombramiento()),
						TipoArchivo.getMIMEType("docx"));

			}

			JSFUtils.infoMessage("Descargar Nombramiento: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {
			System.err.println(validacionException.getMessage());
			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

	public void descargarNombramientoFormalizado() {
		try {

			// String nombreReporte =
			// nombreReporte(this.view.getNombreTipoNombramiento());

			this.view.getClasificacionReporteDTO().setClasificacionReporte(this.view.getNombreTipoNombramiento());
			this.view.getClasificacionReporteDTO().setHorarioTrabajo("El que le asigne su Unidad");
			this.view.getClasificacionReporteDTO().setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

			Integer idClasificacion = nombramiento
					.actualizarEstructuraNombramiento(this.view.getClasificacionReporteDTO());

			actualizarNombramientoImpreso(this.view.getTipoAdscripcion());

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"nombramiento-formalizado-fase", "TIPO_REPORTE", "docx", "ID_NOMBRAMIENTO",
					String.valueOf(this.view.getIdNombramiento()), "ID_CLASIFICACION",
					String.valueOf(idClasificacion) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			this.view.setBytes(admintradorReportes.obtenerReporte(referencia));

			if (this.view.getBytes() != null) {
				JSFUtils.descargarArchivo(this.view.getBytes(),
						CadenaUtil.converterSpace(this.view.getNombreTipoNombramiento()),
						TipoArchivo.getMIMEType("docx"));

			}

			JSFUtils.infoMessage("Descargar Nombramiento: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {
			System.err.println(validacionException.getMessage());
			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

	public void descargarNombramientoInterinato() {
		try {

			// String nombreReporte =
			// nombreReporte(this.view.getNombreTipoNombramiento());

			this.view.getClasificacionReporteDTO().setClasificacionReporte(this.view.getNombreTipoNombramiento());
			this.view.getClasificacionReporteDTO().setHorarioTrabajo("El que le asigne su Unidad");
			this.view.getClasificacionReporteDTO().setNombreSecretario("DR. ALEJANDRO GUARNEROS CHUMACERO");

			Integer idClasificacion = nombramiento
					.actualizarEstructuraNombramiento(this.view.getClasificacionReporteDTO());

			actualizarNombramientoImpreso(this.view.getTipoAdscripcion());

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"nombramiento-interino", "TIPO_REPORTE", "docx", "ID_NOMBRAMIENTO",
					String.valueOf(this.view.getIdNombramiento()), "ID_CLASIFICACION",
					String.valueOf(idClasificacion) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			this.view.setBytes(admintradorReportes.obtenerReporte(referencia));

			if (this.view.getBytes() != null) {
				JSFUtils.descargarArchivo(this.view.getBytes(),
						CadenaUtil.converterSpace(this.view.getNombreTipoNombramiento()),
						TipoArchivo.getMIMEType("docx"));

			}

			JSFUtils.infoMessage("Descargar Nombramiento: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {
			System.err.println(validacionException.getMessage());
			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		}
	}

	public void administarcioVista(String nombreClasificaiconNombramiento) {

		this.view.setMostrarConfirmacionImpresion(false);
		this.view.setMostrarFormalizado(false);
		this.view.setMostrarInterinato(false);
		this.view.setMostrarPrincipal(false);
		this.view.setMostrarOpcionDescarga(true);

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROVISIONAL)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoProvisional);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosProvisional);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROMOCION)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoPromocion);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosPromocion);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROFESIONALIZACION)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoProfesionalizacion);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosProfesionalizacion);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.ESCALAFON)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoEscalafon);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosEscalafon);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.DEFINITIVO)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoDefinitivo);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosDefinitivo);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.CONFIANZA)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoConfianza);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosConfianza);

			this.view.setMostrarConfirmacionImpresion(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.DEFINITIVO_FORMALIZADO_FASE_II)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoFormalizadoDos);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosFormalizadoDos);

			this.view.setMostrarFormalizado(true);
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.INTERINATO)) {

			this.view.getClasificacionReporteDTO().setTextoUno(EnumPlantillaNombramiento.posicionUnoInterinato);
			this.view.getClasificacionReporteDTO().setTextoDos(EnumPlantillaNombramiento.posicionDosInterinato);

			this.view.setMostrarInterinato(true);
		}

	}

	public String nombreReporte(String nombreClasificaiconNombramiento) {
		String nombreReporte = "";
		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROVISIONAL)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROMOCION)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.PROFESIONALIZACION)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.ESCALAFON)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.DEFINITIVO)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.CONFIANZA)) {
			nombreReporte = "nombramiento-generico";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.DEFINITIVO_FORMALIZADO_FASE_II)) {
			nombreReporte = "nombramiento-formalizado-fase";
		}

		if (nombreClasificaiconNombramiento.equals(EnumClasificacionNombramiento.INTERINATO)) {
			nombreReporte = "nombramiento-interinato";
		}

		return nombreReporte;
	}

	public void actualizarNombramientoImpreso(String tipoAdscripcion) {
		if (tipoAdscripcion.equals(EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE)) {
			nombramiento.actualizarNombramientoImpreso(this.view.getIdNombramiento(),
					this.view.getPuestoEmpleadoDTO().getUnidadResponsable(), this.view.isImprimirNombramiento());
		}

		if (tipoAdscripcion.equals(EnumTipoAdscripcionNombramiento.ADSCRIPCION)) {
			nombramiento.actualizarNombramientoImpreso(this.view.getIdNombramiento(),
					this.view.getInfoLugarAdscripcionNombramientoDTO().getAdscripcion(),
					this.view.isImprimirNombramiento());
		}

		if (tipoAdscripcion.equals(EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION)) {
			nombramiento.actualizarNombramientoImpreso(this.view.getIdNombramiento(),
					this.view.getInfoLugarAdscripcionNombramientoDTO().getAreaAdscripcion(),
					this.view.isImprimirNombramiento());
		}

		if (tipoAdscripcion.equals(EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION)) {
			nombramiento.actualizarNombramientoImpreso(this.view.getIdNombramiento(),
					this.view.getInfoLugarAdscripcionNombramientoDTO().getLugarAdscripcion(),
					this.view.isImprimirNombramiento());
		}
	}

	public void validatorTipoAdscripcion(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();

		switch (nombreComponete) {

		case "adscripcion":
			String adscripcion = (String) value;

			if (ValidacionUtil.esCadenaVacia(adscripcion)) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Seleccione una opción valida.");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);

			} else if (!ValidacionUtil.esCadenaVacia(adscripcion)) {
				if (adscripcion.equals(EnumTipoAdscripcionNombramiento.UNIDAD_RESPONSABLE)) {
					if (ValidacionUtil.esCadenaVacia(this.view.getPuestoEmpleadoDTO().getUnidadResponsable())) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"El empleado no tiene una unidad responsable registrado.");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}

				if (adscripcion.equals(EnumTipoAdscripcionNombramiento.ADSCRIPCION)) {
					if (ValidacionUtil
							.esCadenaVacia(this.view.getInfoLugarAdscripcionNombramientoDTO().getAdscripcion())) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"El empleado no tiene una adscripción registrado.");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}

				if (adscripcion.equals(EnumTipoAdscripcionNombramiento.AREA_ADSCRIPCION)) {
					if (ValidacionUtil
							.esCadenaVacia(this.view.getInfoLugarAdscripcionNombramientoDTO().getAreaAdscripcion())) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"El empleado no tiene un area adscripción registrado.");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}

				if (adscripcion.equals(EnumTipoAdscripcionNombramiento.LUGAR_ADSCRIPCION)) {
					if (ValidacionUtil
							.esCadenaVacia(this.view.getInfoLugarAdscripcionNombramientoDTO().getLugarAdscripcion())) {
						FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"El empleado no tiene un lugar adscripción registrado.");
						context.addMessage(component.getClientId(), facesMessage);
						throw new ValidatorException(facesMessage);
					}
				}
			}

			break;
		default:
			JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
			break;
		}

	}

	public void validatorTipoNombramiento(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		String nombreComponete = component.getId();

		switch (nombreComponete) {

		case "tipoNombramiento":
			Integer tipoNombramiento = (Integer) value;

			this.obtenerListaInfoNombramiento();

			if (!ValidacionUtil.esNumeroPositivo(tipoNombramiento)) {
				FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
						"Por favor seleccione un tipo nombramiento.");
				context.addMessage(component.getClientId(), facesMessage1);

				throw new ValidatorException(facesMessage1);
			}
			break;
		default:
			JSFUtils.errorMessage("Error: ", "validaciòn incorrecta...");
			break;
		}

	}

	public void regresarModulo() {
		try {
			JSFUtils.redireccionar("/siayf-rh/contenido/empleado/impresionNombramiento.xhtml?faces-redirect=true");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/************** Getters and Setters ****************/

	/**
	 * @return the view
	 */
	public ImpresionNombramientoView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(ImpresionNombramientoView view) {
		this.view = view;
	}

}
