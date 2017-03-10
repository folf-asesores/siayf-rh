package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;

public class EstructurasContratosView {
	private boolean mostrarInformacionPuesto;
	private FiltroDTO filtro = new FiltroDTO();
	private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
	private EstructuraContratoDTO estructuraContrato = new EstructuraContratoDTO();
	private List<InfoEmpleadoDTO> empleados = new ArrayList<InfoEmpleadoDTO>();
	private List<SelectItem> listaFunciones = new ArrayList<SelectItem>();
	private List<SelectItem> listaJornadas = new ArrayList<SelectItem>();
	private List<SelectItem> listaFinanciamientos = new ArrayList<SelectItem>();
	private Integer idUsuarioLogeado;

	public List<SelectItem> getListaFunciones() {
		return listaFunciones;
	}

	public void setListaFunciones(List<SelectItem> listaFunciones) {
		this.listaFunciones = listaFunciones;
	}

	public List<SelectItem> getListaJornadas() {
		return listaJornadas;
	}

	public void setListaJornadas(List<SelectItem> listaJornadas) {
		this.listaJornadas = listaJornadas;
	}

	public List<SelectItem> getListaFinanciamientos() {
		return listaFinanciamientos;
	}

	public void setListaFinanciamientos(List<SelectItem> listaFinanciamientos) {
		this.listaFinanciamientos = listaFinanciamientos;
	}

	public EstructuraContratoDTO getEstructuraContrato() {
		return estructuraContrato;
	}

	public void setEstructuraContrato(EstructuraContratoDTO estructuraContrato) {
		this.estructuraContrato = estructuraContrato;
	}

	public PuestoEmpleadoDTO getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoEmpleadoDTO puesto) {
		this.puesto = puesto;
	}

	public boolean isMostrarInformacionPuesto() {
		return mostrarInformacionPuesto;
	}

	public void setMostrarInformacionPuesto(boolean mostrarInformacionPuesto) {
		this.mostrarInformacionPuesto = mostrarInformacionPuesto;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

	public FiltroDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroDTO filtro) {
		this.filtro = filtro;
	}

	public List<InfoEmpleadoDTO> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
		this.empleados = empleados;
	}

}
