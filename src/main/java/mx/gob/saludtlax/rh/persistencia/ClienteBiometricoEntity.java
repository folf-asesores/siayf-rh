package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicios_rest_biometricos")
public class ClienteBiometricoEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 247004734270654114L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_servicio_rest_biometrico")
	private int idClienteBiometrico;
	
	@Column(name="nombre")
	private String unidad;
	
	@Column(name="direccion_ip")
	private String ip;
	
	@Column(name="puerto")
	private int puerto;

	public int getIdClienteBiometrico() {
		return idClienteBiometrico;
	}

	public void setIdClienteBiometrico(int idClienteBiometrico) {
		this.idClienteBiometrico = idClienteBiometrico;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	

}
