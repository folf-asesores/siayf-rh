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
 * @since 14/03/2016-19:25:12
 */
@Entity
@Table(name = "direcciones")
public class DireccionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2547526737730012291L;

	@Id
	@Column(name = "id_direccion_empleado")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDireccionEmpleado;

	@Column(name = "calle")
	private String calle;

	@Column(name = "numero_interior")
	private String numeroInterior;

	@Column(name = "numero_exterior")
	private String numeroExterior;

	@Column(name = "codigo_postal")
	private int codigoPostal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	private EstadoEntity estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio")
	private MunicipiosEntity municipio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_asentamiento")
	private AsentamientoEntity asentamiento;

	@Column(name = "id_empleado")
	private Integer idEmpleado;

	@Column(name = "id_aspirante")
	private Integer idAspirante;

	@Override
	public String toString() {
		return "DireccionEntity [idDireccionEmpleado=" + idDireccionEmpleado
				+ ", calle=" + calle + ", numeroInterior=" + numeroInterior
				+ ", numeroExterior=" + numeroExterior + ", codigoPostal="
				+ codigoPostal + ", estado=" + estado + ", municipio="
				+ municipio + ", asentamiento=" + asentamiento
				+ ", idEmpleado=" + idEmpleado + ", idAspirante=" + idAspirante
				+ "]";
	}

	public String lccDomicilio() {
		String lccDomicilio = "DomicilioDTO[calle=" + calle
				+ ", numeroExterior=" + numeroExterior + ", numeroInterior="
				+ numeroInterior + ", codigoPostal=" + codigoPostal
				+ ", idMunicipio=" + municipio.getIdMunicipio()
				+ ", idAsentamiento=" + asentamiento.getIdAsentamiento() + "]";
		return lccDomicilio;
	}

	public String direccionCompleta() {
		String direccionCompleta = "CALLE " + this.calle + " NO. EXT. "
				+ this.numeroExterior;
		if (this.numeroInterior != null) {
			direccionCompleta = direccionCompleta + " NO. INT. "
					+ this.numeroInterior;
		}
		direccionCompleta = direccionCompleta + " "
				+ this.asentamiento.getNombre().toUpperCase();
		direccionCompleta = direccionCompleta + " C.P. " + this.codigoPostal;
		direccionCompleta = direccionCompleta + ", "
				+ this.municipio.getNombre().toUpperCase();

		return direccionCompleta;
	}

	public Integer getIdAspirante() {
		return idAspirante;
	}

	public void setIdAspirante(Integer idAspirante) {
		this.idAspirante = idAspirante;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public String getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
	}

	public MunicipiosEntity getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipiosEntity municipio) {
		this.municipio = municipio;
	}

	public AsentamientoEntity getAsentamiento() {
		return asentamiento;
	}

	public void setAsentamiento(AsentamientoEntity asentamiento) {
		this.asentamiento = asentamiento;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdDireccionEmpleado() {
		return idDireccionEmpleado;
	}

}
