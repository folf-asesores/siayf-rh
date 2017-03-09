/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.suplencia.relacionpersonal;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 * @author Eduardo Mex
 *
 */
@ManagedBean(name = "relacionPersonalSuplente")
@ViewScoped
public class RelacionPersonalSuplenteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3857283165462315819L;

	@Inject
	private RelacionPersonalSuplente relacionPersonalSuplente;

	private Integer numeroQuincena;
	private Integer ejercicioFiscal;
	private Integer idCentroResponsabilidad;

	private List<SelectItem> listaEjercicioFiscal;
	private List<SelectItem> listaCentroResponsabilidad;

	@PostConstruct
	public void init() {

		this.listaEjercicioFiscal = relacionPersonalSuplente.listaEjercicioFiscal();
		this.listaCentroResponsabilidad = relacionPersonalSuplente.listaCentroResponsabilidad();

	}

	public void descargarRelacionPersonalSuplente() {
		try {

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession(false);
			UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

			String[] parametros = { "ID_USUARIO", String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
					"relacion_personal_suplente", "TIPO_REPORTE", "xlsx", "NUMERO_QUINCENA",
					String.valueOf(this.numeroQuincena), "EJERCICIO_FISCAL", String.valueOf(this.ejercicioFiscal),
					"ID_CENTRO_RESPONSABILIDAD", String.valueOf(this.idCentroResponsabilidad) };

			AdministradorReportes admintradorReportes = new AdministradorReportes();
			String referencia = admintradorReportes.obtenerReferencia(parametros);

			byte[] bytes = null;

			bytes = admintradorReportes.obtenerReporte(referencia);

			if (bytes != null) {
				JSFUtils.descargarArchivo(bytes, CadenaUtil.converterSpace("Relacion_Personal_Suplente"),
						TipoArchivo.getMIMEType("xlsx"));

			}

			JSFUtils.infoMessage("Descarga Relacion Personal Suplente: ", "Se descargo correctamente...");

		} catch (NullPointerException | IllegalArgumentException | IOException exception) {
			exception.printStackTrace();
			JSFUtils.errorMessage("Error: ", exception.getMessage());
		} catch (ReglaNegocioException reglaNegocioException) {
			reglaNegocioException.printStackTrace();
			JSFUtils.errorMessage("Error: ", reglaNegocioException.getMessage());
		} catch (ValidacionException validacionException) {
			validacionException.printStackTrace();
			JSFUtils.errorMessage("Error: ", validacionException.getMessage());
		} catch (SistemaException sistemaException) {
			sistemaException.printStackTrace();
			JSFUtils.errorMessage("Error: ", sistemaException.getMessage());
		}
	}

	/********** Getter and Setter ***********/

	public List<SelectItem> getListaEjercicioFiscal() {
		return listaEjercicioFiscal;
	}

	public void setListaEjercicioFiscal(List<SelectItem> listaEjercicioFiscal) {
		this.listaEjercicioFiscal = listaEjercicioFiscal;
	}

	public List<SelectItem> getListaCentroResponsabilidad() {
		return listaCentroResponsabilidad;
	}

	public void setListaCentroResponsabilidad(List<SelectItem> listaCentroResponsabilidad) {
		this.listaCentroResponsabilidad = listaCentroResponsabilidad;
	}

	public Integer getNumeroQuincena() {
		return numeroQuincena;
	}

	public void setNumeroQuincena(Integer numeroQuincena) {
		this.numeroQuincena = numeroQuincena;
	}

	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public Integer getIdCentroResponsabilidad() {
		return idCentroResponsabilidad;
	}

	public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
		this.idCentroResponsabilidad = idCentroResponsabilidad;
	}

}
