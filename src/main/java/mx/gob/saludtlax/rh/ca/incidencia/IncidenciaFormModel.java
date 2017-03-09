package mx.gob.saludtlax.rh.ca.incidencia;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Formulario de captura de informacion de una incidencia. *
 * 
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class IncidenciaFormModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988412750303806284L;

	private InputStream imagenMarca;

	private Integer idIncidencia;

	private String descripcion;

	private String marcaReporte;

	private Integer esImagen;

	private String colorTexto;

	private Integer tipoRegistro;

	public String getColorTexto() {
		return colorTexto;
	}

	public void setColorTexto(String colorTexto) {
		this.colorTexto = colorTexto;
	}

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarcaReporte() {
		return marcaReporte;
	}

	public void setMarcaReporte(String marcaReporte) {
		this.marcaReporte = marcaReporte;
	}

	public Integer getEsImagen() {
		return esImagen;
	}

	public void setEsImagen(Integer esImagen) {
		this.esImagen = esImagen;
	}

	public InputStream getImagenMarca() {
		return imagenMarca;
	}

	public void setImagenMarca(InputStream imagenMarca) {
		this.imagenMarca = imagenMarca;
	}

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	@Override
	public String toString() {

		return super.toString() + this.idIncidencia + "|" + this.descripcion + "|" + this.marcaReporte + "|"
				+ this.esImagen + "|" + this.imagenMarca;
	}

}
