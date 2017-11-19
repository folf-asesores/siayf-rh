/*
 *
 */

package mx.gob.saludtlax.rh.nomina.movimientos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.movimientofijo.MovimientoNominaDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class MovimientosView implements Serializable {

    private static final long serialVersionUID = 6609660319792534234L;
    public static final String ELEGIR_MOVIMIENTO = "Seleccione el movimiento que desea gestionar";
    public static final String SELECCIONAR_EMPLEADO = "Busque y seleccione el empleado para gestionar sus movimientos";

    private List<TipoMovimientoDTO> movimientoLista;
    private List<InfoEmpleadoDTO> empleadoLista;
    private String claveMovimiento;
    private String criterioEmpleado;
    private String titulo;
    private String moduloSubtitulo;
    private InfoEmpleadoDTO empleadoSeleccionado;
    private Boolean panelElegirMovimiento;
    private Boolean panelElegirEmpleado;
    private Boolean mostrarTablaEmpleados;
    private Boolean panelBusqueda;
    private Boolean panelMovimientosFijos;
    private EmpleadoDetalladoDTO empleadoDatos;
    private MovimientoNominaDTO movimientoSeleccionado = new MovimientoNominaDTO();
    private List<MovimientoNominaDTO> listaMovimientos;
    private Boolean permitirAltaMovimiento;
    private List<SelectItem> quincenas = new ArrayList<>();
    private Integer quincenaSeleccionada;
    private List<SelectItem> listaConceptos;
    private TipoMovimientoNominaDTO tipoMovimientoSeleccionado = new TipoMovimientoNominaDTO();

    private Integer formaRegistroMovSeleccionado = 0;

    public void showPanelElegirMovimiento() {
        panelElegirMovimiento = Boolean.TRUE;
        panelElegirEmpleado = Boolean.FALSE;
    }

    public void showPanelElegirEmpleado() {
        panelElegirEmpleado = Boolean.TRUE;
        panelElegirMovimiento = Boolean.FALSE;
        panelBusqueda = Boolean.FALSE;
    }

    public void mostrarPanelImportacion() {
        panelBusqueda = false;
        mostrarTablaEmpleados = false;
    }

    public void ocultarPanelImportacion() {
        panelBusqueda = true;
    }

    public void validator(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponete = component.getId();
        switch (nombreComponete) {

            case "numDias":
                Integer dias = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(dias)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el numero de dias, el campo no puede quedar vacio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }

                break;

            case "folio":
                String folio = (String) value;

                if (ValidacionUtil.esCadenaVacia(folio)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el folio del documento, el campo no puede quedar vacio.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }

                break;
            case "anioFinal":
                Integer anioFinal = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(anioFinal) || anioFinal.compareTo(2015) < 0) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese el año final, debe ser mayor a" + (FechaUtil.ejercicioActual() - 1) + ".");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "importeQuincenal":
                BigDecimal importeQuincenal = (BigDecimal) value;
                if (!ValidacionUtil.esMayorCero(importeQuincenal)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "El importe debe ser mayor a 0.");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "concepto":
                Integer concepto = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(concepto)) {
                    FacesMessage facesMessage1 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Eliga un concepto para el movimiento.");
                    System.out.println("concepto::" + concepto);
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
        }
    }

    public List<InfoEmpleadoDTO> getEmpleadoLista() {
        return empleadoLista;
    }

    public void setEmpleadoLista(List<InfoEmpleadoDTO> empleadoLista) {
        this.empleadoLista = empleadoLista;
    }

    public List<TipoMovimientoDTO> getMovimientoLista() {
        return movimientoLista;
    }

    public void setMovimientoLista(List<TipoMovimientoDTO> movimientoLista) {
        this.movimientoLista = movimientoLista;
    }

    public String getClaveMovimiento() {
        return claveMovimiento;
    }

    public void setClaveMovimiento(String claveMovimiento) {
        this.claveMovimiento = claveMovimiento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getModuloSubtitulo() {
        return moduloSubtitulo;
    }

    public void setModuloSubtitulo(String moduloSubtitulo) {
        this.moduloSubtitulo = moduloSubtitulo;
    }

    public Boolean getPanelElegirMovimiento() {
        return panelElegirMovimiento;
    }

    public void setPanelElegirMovimiento(Boolean panelElegirMovimiento) {
        this.panelElegirMovimiento = panelElegirMovimiento;
    }

    public Boolean getPanelElegirEmpleado() {
        return panelElegirEmpleado;
    }

    public void setPanelElegirEmpleado(Boolean panelElegirEmpleado) {
        this.panelElegirEmpleado = panelElegirEmpleado;
    }

    public String getCriterioEmpleado() {
        return criterioEmpleado;
    }

    public void setCriterioEmpleado(String criterioEmpleado) {
        this.criterioEmpleado = criterioEmpleado;
    }

    public Boolean getMostrarTablaEmpleados() {
        return mostrarTablaEmpleados;
    }

    public void setMostrarTablaEmpleados(Boolean mostrarTablaEmpleados) {
        this.mostrarTablaEmpleados = mostrarTablaEmpleados;
    }

    public Boolean getPanelBusqueda() {
        return panelBusqueda;
    }

    public void setPanelBusqueda(Boolean panelBusqueda) {
        this.panelBusqueda = panelBusqueda;
    }

    public Boolean getPanelMovimientosFijos() {
        return panelMovimientosFijos;
    }

    public void setPanelMovimientosFijos(Boolean panelMovimientosFijos) {
        this.panelMovimientosFijos = panelMovimientosFijos;
    }

    public InfoEmpleadoDTO getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(InfoEmpleadoDTO empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public EmpleadoDetalladoDTO getEmpleadoDatos() {
        return empleadoDatos;
    }

    public void setEmpleadoDatos(EmpleadoDetalladoDTO empleadoDatos) {
        this.empleadoDatos = empleadoDatos;
    }

    public MovimientoNominaDTO getMovimientoSeleccionado() {
        return movimientoSeleccionado;
    }

    public void setMovimientoSeleccionado(MovimientoNominaDTO movimientoSeleccionado) {
        this.movimientoSeleccionado = movimientoSeleccionado;
    }

    public List<MovimientoNominaDTO> getListaMovimientos() {
        return listaMovimientos;
    }

    public void setListaMovimientos(List<MovimientoNominaDTO> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    public Boolean getPermitirAltaMovimiento() {
        return permitirAltaMovimiento;
    }

    public void setPermitirAltaMovimiento(Boolean permitirAltaMovimiento) {
        this.permitirAltaMovimiento = permitirAltaMovimiento;
    }

    public List<SelectItem> getQuincenas() {
        return quincenas;
    }

    public void setQuincenas(List<SelectItem> quincenas) {
        this.quincenas = quincenas;
    }

    public Integer getQuincenaSeleccionada() {
        return quincenaSeleccionada;
    }

    public void setQuincenaSeleccionada(Integer quincenaSeleccionada) {
        this.quincenaSeleccionada = quincenaSeleccionada;
    }

    public List<SelectItem> getListaConceptos() {
        return listaConceptos;
    }

    public void setListaConceptos(List<SelectItem> listaConceptos) {
        this.listaConceptos = listaConceptos;
    }

    public TipoMovimientoNominaDTO getTipoMovimientoSeleccionado() {
        return tipoMovimientoSeleccionado;
    }

    public void setTipoMovimientoSeleccionado(TipoMovimientoNominaDTO tipoMovimientoSeleccionado) {
        this.tipoMovimientoSeleccionado = tipoMovimientoSeleccionado;
    }

    public Integer getFormaRegistroMovSeleccionado() {
        return formaRegistroMovSeleccionado;
    }

    public void setFormaRegistroMovSeleccionado(Integer formaRegistroMovSeleccionado) {
        this.formaRegistroMovSeleccionado = formaRegistroMovSeleccionado;
    }
}