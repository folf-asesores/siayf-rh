/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Eduardo Mex
 *
 */
public class MovimientoEmpleadoReporteView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7069996680507273965L;
	
	private List<SelectItem> listaTipoReporte = new ArrayList<SelectItem>();
	private Date fechaInicialComisionadoLicencia = null;
	private Date fechaFinalComisionadoLicencia = null;
	private SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
	private byte[] bytes = null;
	private String tipoReporte = "";
	private boolean mostrarPanelDescargaReporte = false;
	private Integer idTipoContratacion;
	private boolean mostrarSelectTipoContratacion = false;

	/************Getters and Setters*****************/
	
	/**
	 * @return the fechaInicialComisionadoLicencia
	 */
	public Date getFechaInicialComisionadoLicencia() {
		return fechaInicialComisionadoLicencia;
	}

	/**
	 * @param fechaInicialComisionadoLicencia
	 *            the fechaInicialComisionadoLicencia to set
	 */
	public void setFechaInicialComisionadoLicencia(Date fechaInicialComisionadoLicencia) {
		this.fechaInicialComisionadoLicencia = fechaInicialComisionadoLicencia;
	}

	/**
	 * @return the fechaFinalComisionadoLicencia
	 */
	public Date getFechaFinalComisionadoLicencia() {
		return fechaFinalComisionadoLicencia;
	}

	/**
	 * @param fechaFinalComisionadoLicencia
	 *            the fechaFinalComisionadoLicencia to set
	 */
	public void setFechaFinalComisionadoLicencia(Date fechaFinalComisionadoLicencia) {
		this.fechaFinalComisionadoLicencia = fechaFinalComisionadoLicencia;
	}

	/**
	 * @return the formatoDelTexto
	 */
	public SimpleDateFormat getFormatoDelTexto() {
		return formatoDelTexto;
	}

	/**
	 * @param formatoDelTexto
	 *            the formatoDelTexto to set
	 */
	public void setFormatoDelTexto(SimpleDateFormat formatoDelTexto) {
		this.formatoDelTexto = formatoDelTexto;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the listaTipoReporte
	 */
	public List<SelectItem> getListaTipoReporte() {
		return listaTipoReporte;
	}

	/**
	 * @param listaTipoReporte
	 *            the listaTipoReporte to set
	 */
	public void setListaTipoReporte(List<SelectItem> listaTipoReporte) {
		this.listaTipoReporte = listaTipoReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the mostrarPanelDescargaReporte
	 */
	public boolean isMostrarPanelDescargaReporte() {
		return mostrarPanelDescargaReporte;
	}

	/**
	 * @param mostrarPanelDescargaReporte the mostrarPanelDescargaReporte to set
	 */
	public void setMostrarPanelDescargaReporte(boolean mostrarPanelDescargaReporte) {
		this.mostrarPanelDescargaReporte = mostrarPanelDescargaReporte;
	}

	/**
	 * @return the idTipoContratacion
	 */
	public Integer getIdTipoContratacion() {
		return idTipoContratacion;
	}

	/**
	 * @param idTipoContratacion the idTipoContratacion to set
	 */
	public void setIdTipoContratacion(Integer idTipoContratacion) {
		this.idTipoContratacion = idTipoContratacion;
	}

	/**
	 * @return the mostrarSelectTipoContratacion
	 */
	public boolean isMostrarSelectTipoContratacion() {
		return mostrarSelectTipoContratacion;
	}

	/**
	 * @param mostrarSelectTipoContratacion the mostrarSelectTipoContratacion to set
	 */
	public void setMostrarSelectTipoContratacion(boolean mostrarSelectTipoContratacion) {
		this.mostrarSelectTipoContratacion = mostrarSelectTipoContratacion;
	}

}
