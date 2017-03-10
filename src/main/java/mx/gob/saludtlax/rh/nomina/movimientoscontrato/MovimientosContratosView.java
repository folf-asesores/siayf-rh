package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.util.Date;
import java.util.List;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos.ConceptoNominaContratosDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaDTO;

/**
 * 
 * @author José Pablo
 *
 */
public class MovimientosContratosView {
    private List<InfoEmpleadoDTO> empleados;
    private InfoEmpleadoDTO empleadoSelect;
    private EmpleadoDetalladoDTO empleadoDatos;
    private List<ConceptoNominaDTO> conceptos;
    private String criterio;
    private MovimientoContratosDTO movimientoContratos;
    private List<MovimientoContratosDTO> movimientoContratosLista;
    private List<ConceptoNominaContratosDTO> listaConceptos;
    private List<ProductoNominaDTO> productoNominaLista;
    private boolean mostrarBusqueda;
    private boolean mostrarResultados;
    private boolean mostrarMovimientos;
    private boolean mostrarFormulario;
    private boolean desabilitarFijos;
    private boolean habilitarFaltas;
    private Boolean consultaMovimientos;
    private Boolean mostrarTablaMovimientos;
    private Boolean mostrarInfoEmpleado;
    private Boolean nuevo;
    private Date fechaFalta;

	public void panelBusqueda() {
        mostrarBusqueda = true;
        mostrarMovimientos = false;
        mostrarFormulario = false;
        mostrarTablaMovimientos = false;
        mostrarInfoEmpleado = false;
    }
    public void panelMovimientos() {
        mostrarBusqueda = false;
        mostrarMovimientos = true;
        mostrarFormulario = false;
        mostrarTablaMovimientos = true;
        mostrarInfoEmpleado = true;
    }

    public void panelFormulario() {
        mostrarBusqueda = false;
        mostrarMovimientos = false;
        mostrarFormulario = true;
        mostrarTablaMovimientos = false;
        mostrarInfoEmpleado = true;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }
    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
    public InfoEmpleadoDTO getEmpleadoSelect() {
        return empleadoSelect;
    }
    public void setEmpleadoSelect(InfoEmpleadoDTO empleadoSelect) {
        this.empleadoSelect = empleadoSelect;
    }
    public EmpleadoDetalladoDTO getEmpleadoDatos() {
        return empleadoDatos;
    }
    public void setEmpleadoDatos(EmpleadoDetalladoDTO empleadoDatos) {
        this.empleadoDatos = empleadoDatos;
    }
    public List<ConceptoNominaDTO> getConceptos() {
        return conceptos;
    }
    public void setConceptos(List<ConceptoNominaDTO> conceptos) {
        this.conceptos = conceptos;
    }
    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }
    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }
    public boolean isMostrarResultados() {
        return mostrarResultados;
    }
    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }
    public boolean isMostrarFormulario() {
        return mostrarFormulario;
    }
    public void setMostrarFormulario(boolean mostrarFormulario) {
        this.mostrarFormulario = mostrarFormulario;
    }
    public Boolean getConsultaMovimientos() {
        return consultaMovimientos;
    }
    public void setConsultaMovimientos(Boolean consultaMovimientos) {
        this.consultaMovimientos = consultaMovimientos;
    }
    public MovimientoContratosDTO getMovimientoContratos() {
        return movimientoContratos;
    }
    public void setMovimientoContratos(MovimientoContratosDTO movimientoContratos) {
        this.movimientoContratos = movimientoContratos;
    }
    public List<MovimientoContratosDTO> getMovimientoContratosLista() {
        return movimientoContratosLista;
    }
    public void setMovimientoContratosLista(List<MovimientoContratosDTO> movimientoContratosLista) {
        this.movimientoContratosLista = movimientoContratosLista;
    }
	public boolean isMostrarMovimientos() {
		return mostrarMovimientos;
	}
	public void setMostrarMovimientos(boolean mostrarMovimientos) {
		this.mostrarMovimientos = mostrarMovimientos;
	}
	public List<ConceptoNominaContratosDTO> getListaConceptos() {
		return listaConceptos;
	}
	public void setListaConceptos(List<ConceptoNominaContratosDTO> listaConceptos) {
		this.listaConceptos = listaConceptos;
	}
	public boolean isDesabilitarFijos() {
		return desabilitarFijos;
	}
	public void setDesabilitarFijos(boolean desabilitarFijos) {
		this.desabilitarFijos = desabilitarFijos;
	}
	public Boolean getMostrarTablaMovimientos() {
		return mostrarTablaMovimientos;
	}
	public void setMostrarTablaMovimientos(Boolean mostrarTablaMovimientos) {
		this.mostrarTablaMovimientos = mostrarTablaMovimientos;
	}
	public Boolean getMostrarInfoEmpleado() {
		return mostrarInfoEmpleado;
	}
	public void setMostrarInfoEmpleado(Boolean mostrarInfoEmpleado) {
		this.mostrarInfoEmpleado = mostrarInfoEmpleado;
	}
    public List<ProductoNominaDTO> getProductoNominaLista() {
        return productoNominaLista;
    }
    public void setProductoNominaLista(List<ProductoNominaDTO> productoNominaLista) {
        this.productoNominaLista = productoNominaLista;
    }
    public boolean isHabilitarFaltas() {
        return habilitarFaltas;
    }
    public void setHabilitarFaltas(boolean habilitarFaltas) {
        this.habilitarFaltas = habilitarFaltas;
    }
	public Date getFechaFalta() {
		return fechaFalta;
	}
	public void setFechaFalta(Date fechaFalta) {
		this.fechaFalta = fechaFalta;
	}
	public Boolean getNuevo() {
		return nuevo;
	}
	public void setNuevo(Boolean nuevo) {
		this.nuevo = nuevo;
	}
 }