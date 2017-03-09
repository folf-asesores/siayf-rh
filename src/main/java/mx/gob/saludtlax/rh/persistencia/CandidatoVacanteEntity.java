/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.sql.Time;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author eduardo
 *
 */
@Entity
@Table(name = "candidatos_vacantes")
public class CandidatoVacanteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6141542344469882931L;

	@Id
	@Column(name = "id_candidato_vacante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCandidatoVacante;

	@Column(name = "tipo_candidato")
	private Integer tipoCandidato;

	@Column(name = "id_contexto")
	private Integer idContexto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_postulado_vacante")
	private PostulacionVacanteEntity postuladoVacante;

	@Column(name = "seleccionado")
	private Boolean seleccionado;

	/************** Getters and Setters ***************/

	/**
	 * @return the idCandidatoVacante
	 */
	public Integer getIdCandidatoVacante() {
		return idCandidatoVacante;
	}

	/**
	 * @param idCandidatoVacante
	 *            the idCandidatoVacante to set
	 */
	public void setIdCandidatoVacante(Integer idCandidatoVacante) {
		this.idCandidatoVacante = idCandidatoVacante;
	}

	/**
	 * @return the tipoCandidato
	 */
	public Integer getTipoCandidato() {
		return tipoCandidato;
	}

	/**
	 * @param tipoCandidato
	 *            the tipoCandidato to set
	 */
	public void setTipoCandidato(Integer tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	/**
	 * @return the idContexto
	 */
	public Integer getIdContexto() {
		return idContexto;
	}

	/**
	 * @param idContexto the idContexto to set
	 */
	public void setIdContexto(Integer idContexto) {
		this.idContexto = idContexto;
	}

	/**
	 * @return the postuladoVacante
	 */
	public PostulacionVacanteEntity getPostuladoVacante() {
		return postuladoVacante;
	}

	/**
	 * @param postuladoVacante the postuladoVacante to set
	 */
	public void setPostuladoVacante(PostulacionVacanteEntity postuladoVacante) {
		this.postuladoVacante = postuladoVacante;
	}

	/**
	 * @return the seleccionado
	 */
	public Boolean getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @param seleccionado the seleccionado to set
	 */
	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

}
