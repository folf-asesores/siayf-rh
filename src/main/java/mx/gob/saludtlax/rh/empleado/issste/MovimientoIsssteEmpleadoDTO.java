/**
 * 
 */
package mx.gob.saludtlax.rh.empleado.issste;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author Eduardo Mex
 *
 */
public class MovimientoIsssteEmpleadoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3066288982770375755L;
	
	private Integer idMovimientoIsssteEmpleado;

	private Integer idEmpleado;

	private Integer idTipoMovimientoIssste;

	private BigDecimal sueldoIssste;

	private BigDecimal sueldoSar;

	private BigDecimal totalRemuneracion;

	private String nivelSalario;

	private String nombramiento;

	private Integer idCausaBaja;

	private Integer idUsuario;
	
	private Integer idTipoNombramiento;
	
	private Integer idNivelSalarial;

	public Integer getIdMovimientoIsssteEmpleado() {
		return idMovimientoIsssteEmpleado;
	}

	public void setIdMovimientoIsssteEmpleado(Integer idMovimientoIsssteEmpleado) {
		this.idMovimientoIsssteEmpleado = idMovimientoIsssteEmpleado;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Integer getIdTipoMovimientoIssste() {
		return idTipoMovimientoIssste;
	}

	public void setIdTipoMovimientoIssste(Integer idTipoMovimientoIssste) {
		this.idTipoMovimientoIssste = idTipoMovimientoIssste;
	}

	public BigDecimal getSueldoIssste() {
		return sueldoIssste;
	}

	public void setSueldoIssste(BigDecimal sueldoIssste) {
		this.sueldoIssste = sueldoIssste;
	}

	public BigDecimal getSueldoSar() {
		return sueldoSar;
	}

	public void setSueldoSar(BigDecimal sueldoSar) {
		this.sueldoSar = sueldoSar;
	}

	public BigDecimal getTotalRemuneracion() {
		return totalRemuneracion;
	}

	public void setTotalRemuneracion(BigDecimal totalRemuneracion) {
		this.totalRemuneracion = totalRemuneracion;
	}

	public String getNivelSalario() {
		return nivelSalario;
	}

	public void setNivelSalario(String nivelSalario) {
		this.nivelSalario = nivelSalario;
	}

	public String getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}

	public Integer getIdCausaBaja() {
		return idCausaBaja;
	}

	public void setIdCausaBaja(Integer idCausaBaja) {
		this.idCausaBaja = idCausaBaja;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTipoNombramiento() {
		return idTipoNombramiento;
	}

	public void setIdTipoNombramiento(Integer idTipoNombramiento) {
		this.idTipoNombramiento = idTipoNombramiento;
	}

	public Integer getIdNivelSalarial() {
		return idNivelSalarial;
	}

	public void setIdNivelSalarial(Integer idNivelSalarial) {
		this.idNivelSalarial = idNivelSalarial;
	}
	
	

}
