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
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalListaDTO;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.PeriodoCalendarioDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoOPDDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;

public class ProductosNominaView implements Serializable {
	private static final long serialVersionUID = -1646585530060404962L;

	private List<EjercicioFiscalListaDTO> ejercicioFiscalList;
	private List<PeriodoCalendarioDTO> periodoCalendarioList;
	private List<FuenteFinanciamientoOPDDTO> fuenteFinanciamientoList;
	private List<SubfuenteFinanciamientoDTO> subfuenteFinanciamientoList;
	private List<TipoNominaDTO> tipoNominaList;
	private List<NominaEmpleadoDTO> nominaEmpleadoList;
	private List<EstatusProductoNominaDTO> estatusProductoNominaLista;
	private List<TipoContratacionDTO> tipoContratacionList;
	private List<BancoDTO> bancoList;
	private List<CuentaBancariaDTO> cuentaBancariaList;
	private List<NominaErroneaDTO> nominasErroneas;
	private List<PensionesNominaDTO> pensionesNominaList;
	private Boolean mostrarNominaErronea;
	private Boolean mostrarEspera;

	private ProductoNominaFiltroDTO filtro;
	private ProductoNominaListaDTO productoNominaSelect;
	private ProductoNominaDTO productoNomina;
	private Boolean cambiarFuenteFinanciamiento;
	private Boolean panelForm;
	private Boolean panelFiltro;
	private Boolean panelPrincipalDetalle;
    private Boolean panelDetalle;
    private Boolean panelDetalleConceptos;
    private Boolean panelDetalleGestionFaltas;
    private Boolean panelCalculoNomina;
    private Boolean panelPensiones;
    private Boolean panelActualizarNomina;
	private Boolean habilitarEstatus;
	private Boolean renderedValido;
	private Boolean mostrarTablaNominaEmpleado;
	private List<ProductoNominaListaDTO> filtroProductoNominaList;
	private NominaEmpleadoDTO nominaEmpleadoSelect;
	private ConceptosNominaEmpleadosDTO conceptosNominaSelect;
	private ProcesoDTO proceso = new ProcesoDTO();
	private Boolean operacion;
	private List<TipoPeriodoDTO> tipoPeriodoLista;
    private DualListModel<FaltaContadaDTO> faltasGestionar;
    private List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList;
    private List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoSelectList;

    
    private Boolean mostrarCalendarios = false;
    
	public ProductosNominaView(){
		productoNomina = new ProductoNominaDTO();
	}

	public void panelCalculoNomina() {
	    panelCalculoNomina = Boolean.TRUE;
        panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
        mostrarNominaErronea = Boolean.FALSE;
        mostrarTablaNominaEmpleado = Boolean.FALSE;
	    panelActualizarNomina = Boolean.FALSE;
    }

	public void panelActualizarNomina() {
	    panelCalculoNomina = Boolean.FALSE;
	    panelActualizarNomina = Boolean.TRUE;
        panelDetalle = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
        mostrarNominaErronea = Boolean.FALSE;
        mostrarTablaNominaEmpleado = Boolean.FALSE;
    }

	public void showPanelPrincipal() {
		panelFiltro = Boolean.TRUE;
		panelForm = Boolean.FALSE;
	}

	public void showPrincipalDetalle() {
		panelPrincipalDetalle = Boolean.TRUE;
        mostrarTablaNominaEmpleado = Boolean.TRUE;
		panelDetalle = Boolean.FALSE;
	    panelActualizarNomina = Boolean.FALSE;
	    panelCalculoNomina = Boolean.FALSE;
	}

	public void showPanelForm() {
		panelForm = Boolean.TRUE;
		panelFiltro = Boolean.FALSE;
		cambiarFuenteFinanciamiento = Boolean.FALSE;
		renderedValido = Boolean.FALSE;
	}

	public void showPanelPrincipalDetalle() {
		panelPrincipalDetalle = Boolean.TRUE;
        mostrarTablaNominaEmpleado = Boolean.TRUE;
		panelDetalle = Boolean.FALSE;
        panelCalculoNomina = Boolean.FALSE;
        panelPensiones = Boolean.FALSE;
        panelActualizarNomina = Boolean.FALSE;
	}

	public void showPanelDetalle() {
		panelDetalle = (Boolean.TRUE);
		panelPrincipalDetalle = Boolean.FALSE;
		panelDetalleConceptos = Boolean.TRUE;
        panelDetalleGestionFaltas = Boolean.FALSE;
	}

	public void showGestionFaltas() {
		panelDetalle = (Boolean.TRUE);
		panelPrincipalDetalle = Boolean.FALSE;
		panelDetalleConceptos = Boolean.FALSE;
        panelDetalleGestionFaltas = Boolean.TRUE;
	}

	public void showPanelPension() {
        panelPensiones = Boolean.TRUE;
	    panelCalculoNomina = Boolean.FALSE;
        panelDetalle = Boolean.FALSE;
        mostrarNominaErronea = Boolean.FALSE;
        mostrarTablaNominaEmpleado = Boolean.FALSE;
	}

	public void showCalendarFaltas(){
		if (productoNomina.getCalcularFaltas()) {
			mostrarCalendarios = true;
		} else {
			mostrarCalendarios = false;
		}
	}

	public ProcesoDTO getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}
	public boolean isMostrarEspera() {
		return mostrarEspera;
	}
	public void setMostrarEspera(boolean mostrarEspera) {
		this.mostrarEspera = mostrarEspera;
	}
	public List<NominaErroneaDTO> getNominasErroneas() {
		return nominasErroneas;
	}
	public void setNominasErroneas(List<NominaErroneaDTO> nominasErroneas) {
		this.nominasErroneas = nominasErroneas;
	}
	public Boolean getMostrarEspera() {
        return mostrarEspera;
    }
    public void setMostrarEspera(Boolean mostrarEspera) {
        this.mostrarEspera = mostrarEspera;
    }
    public Boolean getMostrarNominaErronea() {
        return mostrarNominaErronea;
    }
    public void setMostrarNominaErronea(Boolean mostrarNominaErronea) {
		this.mostrarNominaErronea = mostrarNominaErronea;
	}
	public List<EjercicioFiscalListaDTO> getEjercicioFiscalList() {
		return ejercicioFiscalList;
	}
	public void setEjercicioFiscalList(List<EjercicioFiscalListaDTO> ejercicioFiscalList) {
		this.ejercicioFiscalList = ejercicioFiscalList;
	}
	public List<PeriodoCalendarioDTO> getPeriodoCalendarioList() {
		return periodoCalendarioList;
	}
	public void setPeriodoCalendarioList(List<PeriodoCalendarioDTO> periodoCalendarioList) {
		this.periodoCalendarioList = periodoCalendarioList;
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
	public List<TipoNominaDTO> getTipoNominaList() {
		return tipoNominaList;
	}
	public void setTipoNominaList(List<TipoNominaDTO> tipoNominaList) {
		this.tipoNominaList = tipoNominaList;
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
	public ProductoNominaDTO getProductoNomina() {
		return productoNomina;
	}
	public void setProductoNomina(ProductoNominaDTO productoNomina) {
		this.productoNomina = productoNomina;
	}
	public ProductoNominaFiltroDTO getFiltro() {
		return filtro;
	}
	public void setFiltro(ProductoNominaFiltroDTO filtro) {
		this.filtro = filtro;
	}
	public Boolean getCambiarFuenteFinanciamiento() {
		return cambiarFuenteFinanciamiento;
	}
	public void setCambiarFuenteFinanciamiento(Boolean cambiarFuenteFinanciamiento) {
		this.cambiarFuenteFinanciamiento = cambiarFuenteFinanciamiento;
	}
	public Boolean getPanelForm() {
		return panelForm;
	}
	public void setPanelForm(Boolean panelForm) {
		this.panelForm = panelForm;
	}
	public Boolean getPanelFiltro() {
		return panelFiltro;
	}
	public void setPanelFiltro(Boolean panelFiltro) {
		this.panelFiltro = panelFiltro;
	}
	public Boolean getPanelPrincipalDetalle() {
		return panelPrincipalDetalle;
	}
	public void setPanelPrincipalDetalle(Boolean panelPrincipalDetalle) {
		this.panelPrincipalDetalle = panelPrincipalDetalle;
	}
	public Boolean getPanelDetalle() {
		return panelDetalle;
	}
	public void setPanelDetalle(Boolean panelDetalle) {
		this.panelDetalle = panelDetalle;
	}
	public Boolean getHabilitarEstatus() {
		return habilitarEstatus;
	}
	public void setHabilitarEstatus(Boolean habilitarEstatus) {
		this.habilitarEstatus = habilitarEstatus;
	}
	public Boolean getRenderedValido() {
		return renderedValido;
	}
	public void setRenderedValido(Boolean renderedValido) {
		this.renderedValido = renderedValido;
	}
	public List<ProductoNominaListaDTO> getFiltroProductoNominaList() {
		return filtroProductoNominaList;
	}
	public void setFiltroProductoNominaList(List<ProductoNominaListaDTO> filtroProductoNominaList) {
		this.filtroProductoNominaList = filtroProductoNominaList;
	}
	public ProductoNominaListaDTO getProductoNominaSelect() {
		return productoNominaSelect;
	}
	public void setProductoNominaSelect(ProductoNominaListaDTO productoNominaSelect) {
		this.productoNominaSelect = productoNominaSelect;
	}
	public NominaEmpleadoDTO getNominaEmpleadoSelect() {
		return nominaEmpleadoSelect;
	}
	public void setNominaEmpleadoSelect(NominaEmpleadoDTO nominaEmpleadoSelect) {
		this.nominaEmpleadoSelect = nominaEmpleadoSelect;
	}
	public Boolean getOperacion() {
		return operacion;
	}
	public void setOperacion(Boolean operacion) {
		this.operacion = operacion;
	}
	public List<TipoPeriodoDTO> getTipoPeriodoLista() {
		return tipoPeriodoLista;
	}
	public void setTipoPeriodoLista(List<TipoPeriodoDTO> tipoPeriodoLista) {
		this.tipoPeriodoLista = tipoPeriodoLista;
	}
	public List<TipoContratacionDTO> getTipoContratacionList() {
		return tipoContratacionList;
	}
	public void setTipoContratacionList(List<TipoContratacionDTO> tipoContratacionList) {
		this.tipoContratacionList = tipoContratacionList;
	}
	public ConceptosNominaEmpleadosDTO getConceptosNominaSelect() {
		return conceptosNominaSelect;
	}
	public List<PensionesNominaDTO> getPensionesNominaList() {
		return pensionesNominaList;
	}
	public void setPensionesNominaList(List<PensionesNominaDTO> pensionesNominaList) {
		this.pensionesNominaList = pensionesNominaList;
	}
	public void setConceptosNominaSelect(ConceptosNominaEmpleadosDTO conceptosNominaSelect) {
		this.conceptosNominaSelect = conceptosNominaSelect;
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
    public Boolean getPanelCalculoNomina() {
        return panelCalculoNomina;
    }
    public void setPanelCalculoNomina(Boolean panelCalculoNomina) {
        this.panelCalculoNomina = panelCalculoNomina;
    }
    public Boolean getPanelPensiones() {
		return panelPensiones;
	}
	public void setPanelPensiones(Boolean panelPensiones) {
		this.panelPensiones = panelPensiones;
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

    public Boolean getMostrarTablaNominaEmpleado() {
        return mostrarTablaNominaEmpleado;
    }
    public void setMostrarTablaNominaEmpleado(Boolean mostrarTablaNominaEmpleado) {
        this.mostrarTablaNominaEmpleado = mostrarTablaNominaEmpleado;
    }
	public DualListModel<FaltaContadaDTO> getFaltasGestionar() {
		return faltasGestionar;
	}
	public void setFaltasGestionar(DualListModel<FaltaContadaDTO> faltasGestionar) {
		this.faltasGestionar = faltasGestionar;
	}

	public Boolean getPanelActualizarNomina() {
		return panelActualizarNomina;
	}

	public void setPanelActualizarNomina(Boolean panelActualizarNomina) {
		this.panelActualizarNomina = panelActualizarNomina;
	}
    public List<ActualizarNominaEmpleadoDTO> getActualizarNominaEmpleadoList() {
        return actualizarNominaEmpleadoList;
    }
    public void setActualizarNominaEmpleadoList(List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList) {
        this.actualizarNominaEmpleadoList = actualizarNominaEmpleadoList;
    }

    public List<ActualizarNominaEmpleadoDTO> getActualizarNominaEmpleadoSelectList() {
        return actualizarNominaEmpleadoSelectList;
    }

    public void setActualizarNominaEmpleadoSelectList(
            List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoSelectList) {
        this.actualizarNominaEmpleadoSelectList = actualizarNominaEmpleadoSelectList;
    }

	

	public Boolean getMostrarCalendarios() {
		return mostrarCalendarios;
	}

	public void setMostrarCalendarios(Boolean mostrarCalendarios) {
		this.mostrarCalendarios = mostrarCalendarios;
	}
}