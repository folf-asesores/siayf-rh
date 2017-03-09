package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.util.Date;

public class BitacoraImportarCFDIDTO {
	private Integer idBitacoraImportarCfdi;
	private Integer idUsuario;
	private Date fecha;
	private Date hora;
	private String pathFile;
	private String mensaje;
	private String rfcReceptor;

	public Integer getIdBitacoraImportarCfdi() {
		return idBitacoraImportarCfdi;
	}

	public void setIdBitacoraImportarCfdi(Integer idBitacoraImportarCfdi) {
		this.idBitacoraImportarCfdi = idBitacoraImportarCfdi;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getRfcReceptor() {
		return rfcReceptor;
	}

	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}
}