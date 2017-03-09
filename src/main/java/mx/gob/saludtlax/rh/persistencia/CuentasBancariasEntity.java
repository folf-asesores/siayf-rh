/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentasBancariasEntity implements Serializable {

	private static final long serialVersionUID = -3491218769714297031L;

	@Id
	@Column(name = "id_cuenta_bancaria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuentaBancaria;
	

	@Column(name = "banco")
	private String banco;
	@Column(name = "numero_cuenta")
	private String numeroCuenta;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "fuente_financiamiento")
	private String fuenteFinanciamiento;
	@Column(name = "ejercicio_fiscal")
	private Integer ejercicioFiscal;
	@Column(name = "clave_cuenta")
	private Integer claveCuenta;
	
//	<<<<<Getters & Setters>>>>>
	
	public Integer getIdCuentaBancaria() {
		return idCuentaBancaria;
	}
	public void setIdCuentaBancaria(Integer idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFuenteFinanciamiento() {
		return fuenteFinanciamiento;
	}
	public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
		this.fuenteFinanciamiento = fuenteFinanciamiento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}
	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}
	public Integer getClaveCuenta() {
		return claveCuenta;
	}
	public void setClaveCuenta(Integer claveCuenta) {
		this.claveCuenta = claveCuenta;
	}
		
}