/**
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

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
 * @since 05/04/2016-02:37:33
 */
@Entity
@Table(name = "documentaciones")
public class DocumentacionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -266939360341978627L;

	@Id
	@Column(name = "id_documentacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDocumentacion;

	@Column(name = "tipo_documento")
	private String tipoDocumento;

	@Column(name = "documento")
	private String documento;

	@Column(name = "tipo_licencia")
	private String tipoLicencia;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aspirante")
	private AspiranteEntity idAspirante;

	/*************** Getters and Setter ***************/

	public Integer getIdDocumentacion() {
		return idDocumentacion;
	}

	public void setIdAspirante(AspiranteEntity idAspirante) {
		this.idAspirante = idAspirante;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setIdDocumentacion(Integer idDocumentacion) {
		this.idDocumentacion = idDocumentacion;
	}

}
