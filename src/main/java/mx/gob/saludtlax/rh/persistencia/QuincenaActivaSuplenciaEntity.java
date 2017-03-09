/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @Since 28/12/2016 13:33:16
 */

@Entity
@Table(name = "quincena_activa_suplencia")
public class QuincenaActivaSuplenciaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2950274977779918286L;

	@Id
	@Column(name = "id_quincena_suplencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "quincena_activa")
	private Integer quincenaActiva;

	@Column(name = "ejercicio_fiscal")
	private Integer ejercicioFiscal;

	@Column(name = "fecha_activacion")
	private Date fechaActivacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	@Column(name = "activo")
	private boolean activo;

	public Integer getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Integer getQuincenaActiva() {
		return quincenaActiva;
	}

	public void setQuincenaActiva(Integer quincenaActiva) {
		this.quincenaActiva = quincenaActiva;
	}

	public Date getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public Integer getId() {
		return id;
	}

}
