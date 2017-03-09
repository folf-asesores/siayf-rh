package mx.gob.saludtlax.rh.reporteslaborales.comision;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

/**
 * @author Daniela
 *
 */

public class ComisionOficialView implements Serializable {

	private static final long serialVersionUID = 6898060539285305143L;
	
	private List<InfoEmpleadoDTO> listaEmpleados = new ArrayList<InfoEmpleadoDTO>();
	private List<ComisionDetalleDTO> comisionDetalle = new ArrayList<ComisionDetalleDTO>();
	private List<ComisionOficialDTO> comisionOficial = new ArrayList<ComisionOficialDTO>();
	private ComisionOficialDTO comisionOficialDTO = new ComisionOficialDTO ();
	private Integer idMovimiento;
	private String criterio;
	private byte[] bytes = null;
	private String comision;

	private boolean mostrarComision = false;
	private boolean mostrarPrincipal = true;
	private boolean mostrarOpcionDescarga = false;


	public Integer getIdTipoMovimiento() {
		return idMovimiento;
	}

	public void setIdTipoMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public boolean isMostrarComision() {
		return mostrarComision;
	}

	public void setMostrarComision(boolean mostrarComision) {
		this.mostrarComision = mostrarComision;
	}

	public boolean isMostrarPrincipal() {
		return mostrarPrincipal;
	}

	public void setMostrarPrincipal(boolean mostrarPrincipal) {
		this.mostrarPrincipal = mostrarPrincipal;
	}

	public List<InfoEmpleadoDTO> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<InfoEmpleadoDTO> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public ComisionOficialDTO getComisionOficialDTO() {
		return comisionOficialDTO;
	}

	public void setComisionOficialDTO(ComisionOficialDTO comisionOficialDTO) {
		this.comisionOficialDTO = comisionOficialDTO;
	}

	public boolean isMostrarOpcionDescarga() {
		return mostrarOpcionDescarga;
	}

	public void setMostrarOpcionDescarga(boolean mostrarOpcionDescarga) {
		this.mostrarOpcionDescarga = mostrarOpcionDescarga;
	}

	public List<ComisionDetalleDTO> getComisionDetalle() {
		return comisionDetalle;
	}

	public void setComisionDetalle(List<ComisionDetalleDTO> comisionDetalle) {
		this.comisionDetalle = comisionDetalle;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public List<ComisionOficialDTO> getComisionOficial() {
		return comisionOficial;
	}

	public void setComisionOficial(List<ComisionOficialDTO> comisionOficial) {
		this.comisionOficial = comisionOficial;
	}
}
