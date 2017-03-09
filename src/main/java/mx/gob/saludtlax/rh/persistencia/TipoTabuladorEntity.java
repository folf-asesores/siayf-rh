package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author eduardo mex
 *
 */
@Entity
@Table(name  = "tipos_tabuladores")
public class TipoTabuladorEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2718139230901785394L;
	
	@Id
	@Column(name = "id_tipo_tabulador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoTabulador;
	
	@Column(name = "descripcion")
	private String descripcion;

	/**
	 * @return the idTipoTabulador
	 */
	public Integer getIdTipoTabulador() {
		return idTipoTabulador;
	}

	/**
	 * @param idTipoTabulador the idTipoTabulador to set
	 */
	public void setIdTipoTabulador(Integer idTipoTabulador) {
		this.idTipoTabulador = idTipoTabulador;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
