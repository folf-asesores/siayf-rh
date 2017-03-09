/**
 * 
 */
package mx.gob.saludtlax.rh.reporteslaborales.formatoaltaissste;

import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Daniela
 *
 */
public class FormatoAltaIsssteView {
	
	private String urlReporte;
	private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<InfoEmpleadoDTO>();
	private InfoEmpleadoDTO empleadoSeleccionado= new InfoEmpleadoDTO(); 
	private Integer idEmpleado;
	private String criterio;
	
	private boolean principalDatos = true;
	private boolean datosPersona = false;
	private boolean ventanaNuevoReporte  = false;
	private boolean reporteExitoso = false;
		
	
	public String getUrlReporte() {
		return urlReporte;
	}

	public void setUrlReporte(String urlReporte) {
		this.urlReporte = urlReporte;
	}

	public List<InfoEmpleadoDTO> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public InfoEmpleadoDTO getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	public boolean isPrincipalDatos() {
		return principalDatos;
	}

	public void setPrincipalDatos(boolean principalDatos) {
		this.principalDatos = principalDatos;
	}

	public boolean isDatosPersona() {
		return datosPersona;
	}

	public void setDatosPersona(boolean datosPersona) {
		this.datosPersona = datosPersona;
	}

	public boolean isVentanaNuevoReporte() {
		return ventanaNuevoReporte;
	}

	public void setVentanaNuevoReporte(boolean ventanaNuevoReporte) {
		this.ventanaNuevoReporte = ventanaNuevoReporte;
	}

	public boolean isReporteExitoso() {
		return reporteExitoso;
	}

	public void setReporteExitoso(boolean reporteExitoso) {
		this.reporteExitoso = reporteExitoso;
	}
}
