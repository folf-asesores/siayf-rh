package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
/**
 * @author José Pablo
 * 
 */
import java.util.List;

import org.primefaces.model.DualListModel;

import mx.gob.saludtlax.rh.configuracion.banco.BancoDTO;
import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoOPDDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;

public class EjecutarProductoNominaView implements Serializable {
	private static final long serialVersionUID = -1646585530060404962L;

	private List<FuenteFinanciamientoOPDDTO> fuenteFinanciamientoList;
	private List<SubfuenteFinanciamientoDTO> subfuenteFinanciamientoList;
	private List<NominaEmpleadoDTO> nominaEmpleadoList;
	private List<EstatusProductoNominaDTO> estatusProductoNominaLista;
	private List<BancoDTO> bancoList;
	private List<CuentaBancariaDTO> cuentaBancariaList;
	private List<NominaErroneaDTO> nominasErroneas;
	private List<PensionesNominaDTO> pensionesNominaList;
	private ProductoNominaDTO productoNomina;
	private NominaEmpleadoDTO nominaEmpleadoSelect;
	private ConceptosNominaEmpleadosDTO conceptosNominaSelect;
	private Boolean mostrarNominaErronea;
	private Boolean editar;
	private Boolean panelPrincipal;
    private Boolean panelDetalle;
    private Boolean panelDetalleConceptos;
    private Boolean panelDetalleGestionFaltas;
    private Boolean panelPensiones;
    private Boolean panelPagosForm;
    private Boolean panelCheques;
	private DualListModel<FaltaContadaDTO> faltasGestionar;
	private List<PagoNominaDTO> pagoNominaList;
	private PagoNominaDTO pagoNominaSelect;
	private Boolean operacion;
    private Integer ultimoNumeroCheque;
	private boolean habilitarOpcionRetener = true;
	private boolean habilitarOpcionAutorizado = true;
	private Boolean usuarioAutoriza;

	public void showPanelPrincipal() {
		panelPrincipal = Boolean.TRUE;
		panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
        panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.FALSE;
        panelPagosForm = Boolean.FALSE;
        panelCheques = Boolean.FALSE;
	}

	public void showPanelDetalle() {
		panelPrincipal = Boolean.FALSE;
		panelDetalle = Boolean.TRUE;
        panelPensiones = Boolean.FALSE;
        panelDetalleConceptos = Boolean.TRUE;
        panelDetalleGestionFaltas = Boolean.FALSE;
        panelPagosForm = Boolean.FALSE;
        panelCheques = Boolean.FALSE;
	}

	public void showGestionFaltas() {
		panelPrincipal = Boolean.FALSE;
		panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
		panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.TRUE;
        panelPagosForm = Boolean.FALSE;
        panelCheques = Boolean.FALSE;
	}

	public void showPanelPension() {
		panelPrincipal = Boolean.FALSE;
		panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.TRUE;
		panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.FALSE;
        panelPagosForm = Boolean.FALSE;
        panelCheques = Boolean.FALSE;
	}


	public void showPanelPagosForm() {
        panelPagosForm = Boolean.TRUE;
		panelPrincipal = Boolean.FALSE;
		panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
		panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.FALSE;
        panelCheques = Boolean.FALSE;
	}

	public void showPanelCheques() {
        panelCheques = Boolean.TRUE;
		panelPrincipal = Boolean.FALSE;
		panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
		panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.FALSE;
        panelPagosForm = Boolean.FALSE;
	}

	public List<FuenteFinanciamientoOPDDTO> getFuenteFinanciamientoList() {
		return fuenteFinanciamientoList;
	}
	public void setFuenteFinanciamientoList(List<FuenteFinanciamientoOPDDTO> fuenteFinanciamientoList) {
		this.fuenteFinanciamientoList = fuenteFinanciamientoList;
	}
	public List<SubfuenteFinanciamientoDTO> getSubfuenteFinanciamientoList() {
		return subfuenteFinanciamientoList;
	}
	public void setSubfuenteFinanciamientoList(List<SubfuenteFinanciamientoDTO> subfuenteFinanciamientoList) {
		this.subfuenteFinanciamientoList = subfuenteFinanciamientoList;
	}
	public List<NominaEmpleadoDTO> getNominaEmpleadoList() {
		return nominaEmpleadoList;
	}
	public void setNominaEmpleadoList(List<NominaEmpleadoDTO> nominaEmpleadoList) {
		this.nominaEmpleadoList = nominaEmpleadoList;
	}
	public List<EstatusProductoNominaDTO> getEstatusProductoNominaLista() {
		return estatusProductoNominaLista;
	}
	public void setEstatusProductoNominaLista(List<EstatusProductoNominaDTO> estatusProductoNominaLista) {
		this.estatusProductoNominaLista = estatusProductoNominaLista;
	}
	public List<BancoDTO> getBancoList() {
		return bancoList;
	}
	public void setBancoList(List<BancoDTO> bancoList) {
		this.bancoList = bancoList;
	}
	public List<CuentaBancariaDTO> getCuentaBancariaList() {
		return cuentaBancariaList;
	}
	public void setCuentaBancariaList(List<CuentaBancariaDTO> cuentaBancariaList) {
		this.cuentaBancariaList = cuentaBancariaList;
	}
	public List<NominaErroneaDTO> getNominasErroneas() {
		return nominasErroneas;
	}
	public void setNominasErroneas(List<NominaErroneaDTO> nominasErroneas) {
		this.nominasErroneas = nominasErroneas;
	}
	public List<PensionesNominaDTO> getPensionesNominaList() {
		return pensionesNominaList;
	}
	public void setPensionesNominaList(List<PensionesNominaDTO> pensionesNominaList) {
		this.pensionesNominaList = pensionesNominaList;
	}
	public Boolean getMostrarNominaErronea() {
		return mostrarNominaErronea;
	}
	public void setMostrarNominaErronea(Boolean mostrarNominaErronea) {
		this.mostrarNominaErronea = mostrarNominaErronea;
	}
	public ProductoNominaDTO getProductoNomina() {
		return productoNomina;
	}
	public void setProductoNomina(ProductoNominaDTO productoNomina) {
		this.productoNomina = productoNomina;
	}
	public NominaEmpleadoDTO getNominaEmpleadoSelect() {
		return nominaEmpleadoSelect;
	}
	public void setNominaEmpleadoSelect(NominaEmpleadoDTO nominaEmpleadoSelect) {
		this.nominaEmpleadoSelect = nominaEmpleadoSelect;
	}
	public ConceptosNominaEmpleadosDTO getConceptosNominaSelect() {
		return conceptosNominaSelect;
	}
	public void setConceptosNominaSelect(ConceptosNominaEmpleadosDTO conceptosNominaSelect) {
		this.conceptosNominaSelect = conceptosNominaSelect;
	}
	public Boolean getPanelDetalle() {
		return panelDetalle;
	}
	public void setPanelDetalle(Boolean panelDetalle) {
		this.panelDetalle = panelDetalle;
	}
	public Boolean getPanelDetalleConceptos() {
		return panelDetalleConceptos;
	}
	public void setPanelDetalleConceptos(Boolean panelDetalleConceptos) {
		this.panelDetalleConceptos = panelDetalleConceptos;
	}
	public Boolean getPanelDetalleGestionFaltas() {
		return panelDetalleGestionFaltas;
	}
	public void setPanelDetalleGestionFaltas(Boolean panelDetalleGestionFaltas) {
		this.panelDetalleGestionFaltas = panelDetalleGestionFaltas;
	}
	public Boolean getPanelPensiones() {
		return panelPensiones;
	}
	public void setPanelPensiones(Boolean panelPensiones) {
		this.panelPensiones = panelPensiones;
	}
	public DualListModel<FaltaContadaDTO> getFaltasGestionar() {
		return faltasGestionar;
	}
	public void setFaltasGestionar(DualListModel<FaltaContadaDTO> faltasGestionar) {
		this.faltasGestionar = faltasGestionar;
	}
	public Boolean getEditar() {
		return editar;
	}
	public void setEditar(Boolean editar) {
		this.editar = editar;
	}
	public Boolean getPanelPrincipal() {
		return panelPrincipal;
	}
	public void setPanelPrincipal(Boolean panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}
	public boolean isHabilitarOpcionRetener() {
		return habilitarOpcionRetener;
	}
	public void setHabilitarOpcionRetener(boolean habilitarOpcionRetener) {
		this.habilitarOpcionRetener = habilitarOpcionRetener;
	}
	public boolean isHabilitarOpcionAutorizado() {
		return habilitarOpcionAutorizado;
	}
	public void setHabilitarOpcionAutorizado(boolean habilitarOpcionAutorizado) {
		this.habilitarOpcionAutorizado = habilitarOpcionAutorizado;
	}
	public List<PagoNominaDTO> getPagoNominaList() {
		return pagoNominaList;
	}
	public void setPagoNominaList(List<PagoNominaDTO> pagoNominaList) {
		this.pagoNominaList = pagoNominaList;
	}
	public PagoNominaDTO getPagoNominaSelect() {
		return pagoNominaSelect;
	}
	public void setPagoNominaSelect(PagoNominaDTO pagoNominaSelect) {
		this.pagoNominaSelect = pagoNominaSelect;
	}
	public Boolean getOperacion() {
		return operacion;
	}
	public void setOperacion(Boolean operacion) {
		this.operacion = operacion;
	}
	public Boolean getPanelPagosForm() {
		return panelPagosForm;
	}
	public void setPanelPagosForm(Boolean panelPagosForm) {
		this.panelPagosForm = panelPagosForm;
	}
	public Boolean getPanelCheques() {
		return panelCheques;
	}
	public void setPanelCheques(Boolean panelCheques) {
		this.panelCheques = panelCheques;
	}
	public Integer getUltimoNumeroCheque() {
		return ultimoNumeroCheque;
	}
	public void setUltimoNumeroCheque(Integer ultimoNumeroCheque) {
		this.ultimoNumeroCheque = ultimoNumeroCheque;
	}
	public Boolean getUsuarioAutoriza() {
		return usuarioAutoriza;
	}
	public void setUsuarioAutoriza(Boolean usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}
}