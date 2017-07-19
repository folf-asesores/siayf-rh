/*
 * DescuentosController.java
 * Creado el 08/Nov/2016 7:59:40 PM
 * 
 */
package mx.gob.saludtlax.rh.ca.empleado;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.ConfiguracionUsuarioModulo;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import org.jboss.logging.Logger;

/**
 * Este ManagedBean controla la vista
 * /contenido/controlasistencia/empleado/reportes.xhtml.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named(value = "descuentosController")
@ViewScoped
public class DescuentosController implements Serializable {

	private static final long serialVersionUID = -7963420549132356803L;
	private static final Logger LOGGER = Logger.getLogger(DescuentosController.class.getName());

	@Inject
	private Catalogo catalogo;
	@Inject
	private ConfiguracionUsuarioModulo configuracionUsuarioModulo;

	private final AdministradorReportes administradorReportes;

	private UsuarioDTO usuario;
	private Short tipoReporte;
	private Integer idAdscripcion;
	private Integer tipoContratacion;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer quincenaAplicacionMovimiento;
	private Integer anyoAplicacionMovimiento;
	private String referencia;
	private List<SelectItem> catalogoAdscripciones = new ArrayList<>();

	/**
	 * Creates a new instance of DescuentosController.
	 */
	public DescuentosController() {
		administradorReportes = new AdministradorReportes();
	}

	/**
	 * Realiza tareas adicionales después de la crecación de una instancia de la
	 * clase.
	 * 
	 * <ol>
	 * <li>Obtiene la información del usuario en sesión</li>
	 * </ol>
	 */
	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession httpSession = request.getSession(false);
		usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

		boolean tieneRestriccionAdscripcion = configuracionUsuarioModulo.tienePermiso("Jurisdiccion_asiganda",
				usuario.getIdUsuario());

		if (tieneRestriccionAdscripcion) {
			if (usuario.getIdAdscripcion() == null) {
				SelectItem a = new SelectItem(0, "El usuario no tiene adscripción asignada.");
				catalogoAdscripciones.add(a);
			} else {
				CatalogoDTO adscripcion = catalogo.obtenerAdscripcionPorId(usuario.getIdAdscripcion());
				SelectItem a = new SelectItem(adscripcion.getId(), adscripcion.getNombre());
				catalogoAdscripciones.add(a);
			}
		} else {
			setCatalogoAdscripciones(SelectItemsUtil.listaCatalogos(catalogo.consultarAdscripciones()));
		}
	}

	public void descargarReporte(ActionEvent actionEvent) {
		String nombreReporte = null;

		if (tipoReporte != null)
			switch (tipoReporte) {
			case 1:
				nombreReporte = "8001-descuentos";
				break;
			case 2:
				nombreReporte = "9201-prima_dominical";
				break;
			case 3:
				nombreReporte = "9204-trimestral";
				break;
			case 4:
				nombreReporte = "9205-anual";
				break;
			case 5:
				nombreReporte = "9207-asistencia_perfecta";
				break;
			case 6:
				nombreReporte = "reporte_unico_incidencias";
				break;
			}

		String[] parametros = new String[] { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
				nombreReporte, "TIPO_REPORTE", "pdf", "ID_ADSCRIPCION", String.valueOf(idAdscripcion),
				"TIPO_CONTRATACION", String.valueOf(tipoContratacion), "FECHA_INICIO",
				FechaUtil.formatearFecha("yyyyMMdd", fechaInicio), "FECHA_FIN",
				FechaUtil.formatearFecha("yyyyMMdd", fechaFin), "QUINCENA_APLICACION_MOVIMIENTO",
				String.valueOf(quincenaAplicacionMovimiento), "ANYO_APLICACION_MOVIMIENTO",
				String.valueOf(anyoAplicacionMovimiento) };

		if (nombreReporte != null && parametros != null) {
			try {
				System.out.println("Parametros::" + parametros);
				referencia = administradorReportes.obtenerReferencia(parametros);
				System.out.println("Referemcia: "+referencia);
				byte[] reporte = administradorReportes.obtenerReporte(referencia);
				System.out.println("Reporte**t: " + reporte+"--" +nombreReporte+"--"+TipoArchivo.PDF);
				JSFUtils.descargarArchivo(reporte, nombreReporte, TipoArchivo.PDF);
			} catch (IOException ex) {
				LOGGER.error(ex);
			}
		}
	}

	/**
	 * Permite la previzualizar el reporte.
	 * 
	 * @param actionEvent
	 *            el evanto de la vista para la genración del reporte.
	 */
	public void previsualizarReporte(ActionEvent actionEvent) {
		String nombreReporte = null;

		if (tipoReporte != null)
			switch (tipoReporte) {
			case 1:
				nombreReporte = "8001-descuentos";
				break;
			case 2:
				nombreReporte = "9201-prima_dominical";
				break;
			case 3:
				nombreReporte = "9204-trimestral";
				break;
			case 4:
				nombreReporte = "9205-anual";
				break;
			case 5:
				nombreReporte = "9207-asistencia_perfecta";
				break;
			case 6:
				nombreReporte = "reporte_unico_incidencias";
				break;
			}

		String[] parametros = new String[] { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
				nombreReporte, "TIPO_REPORTE", "pdf", "ID_ADSCRIPCION", String.valueOf(idAdscripcion),
				"TIPO_CONTRATACION", String.valueOf(tipoContratacion), "FECHA_INICIO",
				FechaUtil.formatearFecha("yyyyMMdd", fechaInicio), "FECHA_FIN",
				FechaUtil.formatearFecha("yyyyMMdd", fechaFin), "QUINCENA_APLICACION_MOVIMIENTO",
				String.valueOf(quincenaAplicacionMovimiento), "ANYO_APLICACION_MOVIMIENTO",
				String.valueOf(anyoAplicacionMovimiento) };

		if (nombreReporte != null && parametros != null) {
			referencia = administradorReportes.obtenerReferencia(parametros);
		}
	}

	/**
	 * Get the value of tipoReporte
	 *
	 * @return the value of tipoReporte
	 */
	public Short getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * Set the value of tipoReporte
	 *
	 * @param tipoReporte
	 *            new value of tipoReporte
	 */
	public void setTipoReporte(Short tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * Get the value of idAdscripcion
	 *
	 * @return the value of idAdscripcion
	 */
	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

	/**
	 * Set the value of idAdscripcion
	 *
	 * @param idAdscripcion
	 *            new value of idAdscripcion
	 */
	public void setIdAdscripcion(Integer idAdscripcion) {
		this.idAdscripcion = idAdscripcion;
	}

	/**
	 * Get the value of tipoContratacion
	 *
	 * @return the value of tipoContratacion
	 */
	public Integer getTipoContratacion() {
		return tipoContratacion;
	}

	/**
	 * Set the value of tipoContratacion
	 *
	 * @param tipoContratacion
	 *            new value of tipoContratacion
	 */
	public void setTipoContratacion(Integer tipoContratacion) {
		this.tipoContratacion = tipoContratacion;
	}

	public List<SelectItem> getTipoContrataciones() {
		List<SelectItem> items = new ArrayList<>();

		for (Map.Entry<Integer, String> tipoContrataciones : EnumTipoContratacion.obtenerTipoContrataciones()
				.entrySet()) {
			SelectItem item = new SelectItem(tipoContrataciones.getKey(), tipoContrataciones.getValue());
			items.add(item);
		}

		return items;
	}

	/**
	 * Get the value of fechaInicio
	 *
	 * @return the value of fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Set the value of fechaInicio
	 *
	 * @param fechaInicio
	 *            new value of fechaInicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Get the value of fechaFin
	 *
	 * @return the value of fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Set the value of fechaFin
	 *
	 * @param fechaFin
	 *            new value of fechaFin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Get the value of quincenaAplicacionMovimiento
	 *
	 * @return the value of quincenaAplicacionMovimiento
	 */
	public Integer getQuincenaAplicacionMovimiento() {
		return quincenaAplicacionMovimiento;
	}

	/**
	 * Set the value of quincenaAplicacionMovimiento
	 *
	 * @param quincenaAplicacionMovimiento
	 *            new value of quincenaAplicacionMovimiento
	 */
	public void setQuincenaAplicacionMovimiento(Integer quincenaAplicacionMovimiento) {
		this.quincenaAplicacionMovimiento = quincenaAplicacionMovimiento;
	}

	/**
	 * Get the value of anyoAplicacionMovimiento
	 *
	 * @return the value of anyoAplicacionMovimiento
	 */
	public Integer getAnyoAplicacionMovimiento() {
		return anyoAplicacionMovimiento;
	}

	/**
	 * Set the value of anyoAplicacionMovimiento
	 *
	 * @param anyoAplicacionMovimiento
	 *            new value of anyoAplicacionMovimiento
	 */
	public void setAnyoAplicacionMovimiento(Integer anyoAplicacionMovimiento) {
		this.anyoAplicacionMovimiento = anyoAplicacionMovimiento;
	}

	/**
	 * Get the value of referencia
	 *
	 * @return the value of referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Set the value of referencia
	 *
	 * @param referencia
	 *            new value of referencia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<SelectItem> getCatalogoAdscripciones() {
		return catalogoAdscripciones;
	}

	public void setCatalogoAdscripciones(List<SelectItem> catalogoAdscripciones) {
		this.catalogoAdscripciones = catalogoAdscripciones;
	}

}
