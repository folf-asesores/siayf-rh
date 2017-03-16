/**
 * 
 */
package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 14/03/2017
 */
public class EstructuraNominaView {
	private FiltroDTO filtro = new FiltroDTO();
	private EstructuraNominaDTO estructuraNomina = new EstructuraNominaDTO();
	private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
	private List<InfoEmpleadoDTO> empleados = new ArrayList<InfoEmpleadoDTO>();
	private Integer idUsuarioLogeado;
	private boolean mostrarInformacionPuesto;

	public EstructuraNominaDTO getEstructuraNomina() {
		return estructuraNomina;
	}

	public void setEstructuraNomina(EstructuraNominaDTO estructuraNomina) {
		this.estructuraNomina = estructuraNomina;
	}

	public FiltroDTO getFiltro() {
		return filtro;
	}

	public boolean isMostrarInformacionPuesto() {
		return mostrarInformacionPuesto;
	}

	public void setMostrarInformacionPuesto(boolean mostrarInformacionPuesto) {
		this.mostrarInformacionPuesto = mostrarInformacionPuesto;
	}

	public void setFiltro(FiltroDTO filtro) {
		this.filtro = filtro;
	}

	public PuestoEmpleadoDTO getPuesto() {
		return puesto;
	}

	public void setPuesto(PuestoEmpleadoDTO puesto) {
		this.puesto = puesto;
	}

	public List<InfoEmpleadoDTO> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
		this.empleados = empleados;
	}

	public Integer getIdUsuarioLogeado() {
		return idUsuarioLogeado;
	}

	public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
		this.idUsuarioLogeado = idUsuarioLogeado;
	}

	public List<SelectItem> getSubfunciones() {
		return SelectItemsUtil.listaSubfunciones();
	}

	public List<SelectItem> getTabuladoresPuestos() {
		return SelectItemsUtil.listaTabuladorPuesto();
	}

	public List<SelectItem> getPagadurias() {
		return SelectItemsUtil.listaPagadurias();
	}

	public List<SelectItem> getIndicadoresMando() {
		return SelectItemsUtil.listaIndicadoresdeMando();
	}

	public List<SelectItem> getTiposUnidad() {
		return SelectItemsUtil.listaTiposUnidades();
	}

	public List<SelectItem> getTiposPagos() {
		return SelectItemsUtil.listaTiposPago();
	}

	public List<SelectItem> getFinaciamientos() {
		return SelectItemsUtil.listaFinanciamientosFederal();
	}

	public List<SelectItem> getJornadas() {
		return SelectItemsUtil.listaJornadas();
	}
}
