package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipos_movimientos_nomina")
public class TiposMovimientosNominaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_movimiento_nomina")
	private Integer idMovimientoNomina;
	
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="forma_registro")
	private Integer formaRegistro;

	@Column(name="es_movimiento")
	private Boolean esMovimiento;
	
	@Column(name="id_padre")
	private Integer idPadre;
	
	public Integer getIdMovimientoNomina() {
		return idMovimientoNomina;
	}

	public void setIdMovimientoNomina(Integer idMovimientoNomina) {
		this.idMovimientoNomina = idMovimientoNomina;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getFormaRegistro() {
		return formaRegistro;
	}

	public void setFormaRegistro(Integer formaRegistro) {
		this.formaRegistro = formaRegistro;
	}

	public Boolean getEsMovimiento() {
		return esMovimiento;
	}

	public void setEsMovimiento(Boolean esMovimiento) {
		this.esMovimiento = esMovimiento;
	}

	public Integer getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	
	
}
