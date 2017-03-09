package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.ArrayList;
import java.util.List;

public class MovimientosSuplentesView {
	private List<SuplenteDTO> suplentes = new ArrayList<SuplenteDTO>();
	private SuplenteDTO suplenteSeleccionado;
	private MovimientoSuplenteDTO movimiento = new MovimientoSuplenteDTO();
	private FiltroSuplenciaDTO filtro;
	private boolean mostrarRegistroMovimiento;
	private boolean mostrarVacaciones;
	private boolean mostrarIncapacidad;

	public boolean isMostrarVacaciones() {
		return mostrarVacaciones;
	}

	public void setMostrarVacaciones(boolean mostrarVacaciones) {
		this.mostrarVacaciones = mostrarVacaciones;
	}

	public boolean isMostrarIncapacidad() {
		return mostrarIncapacidad;
	}

	public void setMostrarIncapacidad(boolean mostrarIncapacidad) {
		this.mostrarIncapacidad = mostrarIncapacidad;
	}

	public MovimientoSuplenteDTO getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(MovimientoSuplenteDTO movimiento) {
		this.movimiento = movimiento;
	}

	public boolean isMostrarRegistroMovimiento() {
		return mostrarRegistroMovimiento;
	}

	public void setMostrarRegistroMovimiento(boolean mostrarRegistroMovimiento) {
		this.mostrarRegistroMovimiento = mostrarRegistroMovimiento;
	}

	public SuplenteDTO getSuplenteSeleccionado() {
		return suplenteSeleccionado;
	}

	public void setSuplenteSeleccionado(SuplenteDTO suplenteSeleccionado) {
		this.suplenteSeleccionado = suplenteSeleccionado;
	}

	public FiltroSuplenciaDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroSuplenciaDTO filtro) {
		this.filtro = filtro;
	}

	public List<SuplenteDTO> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(List<SuplenteDTO> suplentes) {
		this.suplentes = suplentes;
	}

}
