package mx.gob.saludtlax.rh.configuracion.isr;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoService;


@Stateless
public class TablaRetencionEJB {

	@Inject
	private TablaRetencionService tablaRetencionService;
	@Inject
	private TipoPeriodoService periodoService;

	public List<TablaRetencionListaDTO> obtenerTablaRetencionLista() {
		List<TablaRetencionListaDTO> tablaRetencionLista = tablaRetencionService.listaTablaRetencion();
		return tablaRetencionLista;
	}
	
	public List<TablaSubsidioListaDTO> obtenerTablaSubsidioLista() {
		List<TablaSubsidioListaDTO> tablaSubsidioLista = tablaRetencionService.listaTablaSubsidio();
		return tablaSubsidioLista;
	}

	public List<TablaRetencionListaDTO> obtenerTablaRetencionListaPorAnio(Integer ejercicioFiscal, String periodicidad) {
		List<TablaRetencionListaDTO> tablaRetencionListaPorAnio = tablaRetencionService.listaTablaRetencionPorejercicioFiscal(ejercicioFiscal, periodicidad);
		return tablaRetencionListaPorAnio;
	}
	
	public List<TipoPeriodoDTO> obtenerPeriodicidadLista() {
		return periodoService.listaTipoPeriodo();
	}

	public List<TablaRetencionListaDTO> obtenerAnioLista() {
		List<TablaRetencionListaDTO> anioLista = tablaRetencionService.listaAnio();
		return anioLista;
	}

	public List<TablaSubsidioListaDTO> obtenerTablaSubsidioListaPorAnio(Integer ejercicioFiscal, String periodicidad) {
		List<TablaSubsidioListaDTO> tablaSubsidioListaPorAnio = tablaRetencionService.listaTablaSubsidioPorejercicioFiscal(ejercicioFiscal, periodicidad);
		return tablaSubsidioListaPorAnio;
	}

//Opciones para Tabla Retencion	
	public TablaRetencionDTO nuevaRetencion() {
		return tablaRetencionService.nuevaTablaRetencion();
	}
	
	public TablaRetencionDTO crearTablaRetencion(TablaRetencionDTO tablaRetencionDTO) {
		return tablaRetencionService.crearTablaRetencion(tablaRetencionDTO);
	}
	
	public TablaRetencionDTO actualizarTablaRetencion(TablaRetencionDTO tablaRetencion) {
		return tablaRetencionService.actualizarTablaRetencion(tablaRetencion);
		}
	
	public TablaRetencionDTO obtenerTablaRetencion(TablaRetencionListaDTO periodoSelect) {
		return tablaRetencionService.obtenertablaRetencionPorId(periodoSelect.getIdTarifaRetencion());
	}

	public void eliminarTablaRetencion(TablaRetencionListaDTO tablaRetencion) {
		tablaRetencionService.eliminarTablaRetencion(tablaRetencion);
	}
	
//Opciones para Tabla Subsidio	
	
	public TablaSubsidioDTO nuevoSubsidio() {
		return tablaRetencionService.nuevoSubsidio();
	}

	public TablaSubsidioDTO crearTablaSubsidio(TablaSubsidioDTO tablaSubsidioDTO) {
		return tablaRetencionService.crearTablaSubsidio(tablaSubsidioDTO);	
	}

	public TablaSubsidioDTO actualizarTablaSubsidio(TablaSubsidioDTO tablaSubsidioDTO) {
		return tablaRetencionService.actualizarTablaSubsidio(tablaSubsidioDTO);
	}

	public TablaSubsidioDTO obtenerTablaSubsidio(TablaSubsidioListaDTO tablaSubsidioSelect) {
		return tablaRetencionService.obtenertablaSubsidioPorId(tablaSubsidioSelect.getIdTablaSubsidio());
	}

	public void eliminarTablaSubsidio(TablaSubsidioListaDTO tablaSubsidioSelect) {
		tablaRetencionService.eliminarTablaSubsidio(tablaSubsidioSelect);
	}
	
	
}