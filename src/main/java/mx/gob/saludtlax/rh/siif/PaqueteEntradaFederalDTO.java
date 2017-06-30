package mx.gob.saludtlax.rh.siif;

import java.util.Date;

import org.primefaces.model.UploadedFile;

public class PaqueteEntradaFederalDTO {
	private UploadedFile dat;
	private UploadedFile tra;
	private Integer idTipoNomina;
	private Integer idCuentaBancaria;
	private String periodoAfectacion;
	private Integer anioAfectacion;
	private Date fechaEnvio;
	private UploadedFile cont;
	private Integer tipoArchivo;

	public UploadedFile getDat() {
		return dat;
	}
	public void setDat(UploadedFile dat) {
		this.dat = dat;
	}
	public UploadedFile getTra() {
		return tra;
	}
	public void setTra(UploadedFile tra) {
		this.tra = tra;
	}
	public Integer getIdTipoNomina() {
		return idTipoNomina;
	}
	public void setIdTipoNomina(Integer idTipoNomina) {
		this.idTipoNomina = idTipoNomina;
	}
	public Integer getIdCuentaBancaria() {
		return idCuentaBancaria;
	}
	public void setIdCuentaBancaria(Integer idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}
	public String getPeriodoAfectacion() {
		return periodoAfectacion;
	}
	public void setPeriodoAfectacion(String periodoAfectacion) {
		this.periodoAfectacion = periodoAfectacion;
	}
	public Integer getAnioAfectacion() {
		return anioAfectacion;
	}
	public void setAnioAfectacion(Integer anioAfectacion) {
		this.anioAfectacion = anioAfectacion;
	}
	public UploadedFile getCont() {
		return cont;
	}
	public void setCont(UploadedFile cont) {
		this.cont = cont;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public Integer getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(Integer tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	
	
	
}
