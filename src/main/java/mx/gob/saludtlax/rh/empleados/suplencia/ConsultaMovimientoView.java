package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.List;

import javax.faces.model.SelectItem;

public class ConsultaMovimientoView {
	private List<SelectItem> listaMovimientos;
	private List<MovimientoSuplenteDTO> movimientos;
	private FiltroMovimientoSuplenteDTO filtro = new FiltroMovimientoSuplenteDTO();
	private boolean mostrarBusqueda;
	private boolean mostrarDetalle;

	public List<SelectItem> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<SelectItem> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public List<MovimientoSuplenteDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientoSuplenteDTO> movimientos) {
		this.movimientos = movimientos;
	}

	public FiltroMovimientoSuplenteDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroMovimientoSuplenteDTO filtro) {
		this.filtro = filtro;
	}

	public boolean isMostrarBusqueda() {
		return mostrarBusqueda;
	}

	public void setMostrarBusqueda(boolean mostrarBusqueda) {
		this.mostrarBusqueda = mostrarBusqueda;
	}

	public boolean isMostrarDetalle() {
		return mostrarDetalle;
	}

	public void setMostrarDetalle(boolean mostrarDetalle) {
		this.mostrarDetalle = mostrarDetalle;
	}

}
