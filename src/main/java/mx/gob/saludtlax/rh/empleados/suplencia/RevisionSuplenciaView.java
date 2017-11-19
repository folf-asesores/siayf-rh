/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/11/2016 09:42:36
 */
public class RevisionSuplenciaView {
    private List<SelectItem> listaEstatus = new ArrayList<>();
    private List<SelectItem> listaCentrosResponsabilidades;
    private List<SelectItem> listaTiposBusqueda;
    private List<QuincenaSuplenteDTO> quincenas = new ArrayList<>();
    private List<DetalleSuplenciaDTO> detallesQuincena = new ArrayList<>();
    private List<DetalleSuplenciaDTO> desglosesSeleccionados = new ArrayList<>();
    private List<DetalleSuplenciaDTO> detallesPendientes = new ArrayList<>();
    private List<InformacionAdjuntoDTO> documentosAdjuntos = new ArrayList<>();
    private Integer idDetalleQuincena;
    private String estatus;
    private DetalleSuplenciaDTO detalleSeleccionado = new DetalleSuplenciaDTO();
    private DescuentoSuplenciaDTO descuento = new DescuentoSuplenciaDTO();
    private ConsultaSuplenciaDTO consulta = new ConsultaSuplenciaDTO();
    private QuincenaSuplenteDTO quincenaSeleccionada = new QuincenaSuplenteDTO();
    private boolean mostrarDesglose;
    private boolean mostrarBusqueda;
    private boolean mostrarDetalle;
    private boolean mostrarMovimiento;
    private boolean mostrarDescuento;
    private boolean mostrarPendientes;
    private boolean mostrarDocumentacion;
    private boolean mostrarCamposBusquedaCriterio;
    private boolean mostrarCamposBusquedaCentro;
    private BigDecimal totalAprobado;
    private BigDecimal totalPendiente;
    private int totalDias;
    private Integer idUsuario;

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public Integer getIdDetalleQuincena() {
        return idDetalleQuincena;
    }

    public void setIdDetalleQuincena(Integer idDetalleQuincena) {
        this.idDetalleQuincena = idDetalleQuincena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<SelectItem> getListaTiposBusqueda() {
        return listaTiposBusqueda;
    }

    public void setListaTiposBusqueda(List<SelectItem> listaTiposBusqueda) {
        this.listaTiposBusqueda = listaTiposBusqueda;
    }

    public List<SelectItem> getListaCentrosResponsabilidades() {
        return listaCentrosResponsabilidades;
    }

    public void setListaCentrosResponsabilidades(List<SelectItem> listaCentrosResponsabilidades) {
        this.listaCentrosResponsabilidades = listaCentrosResponsabilidades;
    }

    public boolean isMostrarCamposBusquedaCriterio() {
        return mostrarCamposBusquedaCriterio;
    }

    public void setMostrarCamposBusquedaCriterio(boolean mostrarCamposBusquedaCriterio) {
        this.mostrarCamposBusquedaCriterio = mostrarCamposBusquedaCriterio;
    }

    public boolean isMostrarCamposBusquedaCentro() {
        return mostrarCamposBusquedaCentro;
    }

    public void setMostrarCamposBusquedaCentro(boolean mostrarCamposBusquedaCentro) {
        this.mostrarCamposBusquedaCentro = mostrarCamposBusquedaCentro;
    }

    public boolean isMostrarDocumentacion() {
        return mostrarDocumentacion;
    }

    public void setMostrarDocumentacion(boolean mostrarDocumentacion) {
        this.mostrarDocumentacion = mostrarDocumentacion;
    }

    public QuincenaSuplenteDTO getQuincenaSeleccionada() {
        return quincenaSeleccionada;
    }

    public void setQuincenaSeleccionada(QuincenaSuplenteDTO quincenaSeleccionada) {
        this.quincenaSeleccionada = quincenaSeleccionada;
    }

    public ConsultaSuplenciaDTO getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaSuplenciaDTO consulta) {
        this.consulta = consulta;
    }

    public List<QuincenaSuplenteDTO> getQuincenas() {
        return quincenas;
    }

    public void setQuincenas(List<QuincenaSuplenteDTO> quincenas) {
        this.quincenas = quincenas;
    }

    public List<DetalleSuplenciaDTO> getDetallesPendientes() {
        return detallesPendientes;
    }

    public void setDetallesPendientes(List<DetalleSuplenciaDTO> detallesPendientes) {
        this.detallesPendientes = detallesPendientes;
    }

    public boolean isMostrarPendientes() {
        return mostrarPendientes;
    }

    public void setMostrarPendientes(boolean mostrarPendientes) {
        this.mostrarPendientes = mostrarPendientes;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public DescuentoSuplenciaDTO getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoSuplenciaDTO descuento) {
        this.descuento = descuento;
    }

    public boolean isMostrarDescuento() {
        return mostrarDescuento;
    }

    public void setMostrarDescuento(boolean mostrarDescuento) {
        this.mostrarDescuento = mostrarDescuento;
    }

    public BigDecimal getTotalAprobado() {
        return totalAprobado;
    }

    public void setTotalAprobado(BigDecimal totalAprobado) {
        this.totalAprobado = totalAprobado;
    }

    public BigDecimal getTotalPendiente() {
        return totalPendiente;
    }

    public void setTotalPendiente(BigDecimal totalPendiente) {
        this.totalPendiente = totalPendiente;
    }

    public List<InformacionAdjuntoDTO> getDocumentosAdjuntos() {
        return documentosAdjuntos;
    }

    public void setDocumentosAdjuntos(List<InformacionAdjuntoDTO> documentosAdjuntos) {
        this.documentosAdjuntos = documentosAdjuntos;
    }

    public List<SelectItem> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<SelectItem> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public boolean isMostrarMovimiento() {
        return mostrarMovimiento;
    }

    public void setMostrarMovimiento(boolean mostrarMovimiento) {
        this.mostrarMovimiento = mostrarMovimiento;
    }

    public DetalleSuplenciaDTO getDetalleSeleccionado() {
        return detalleSeleccionado;
    }

    public void setDetalleSeleccionado(DetalleSuplenciaDTO detalleSeleccionado) {
        this.detalleSeleccionado = detalleSeleccionado;
    }

    public boolean isMostrarDetalle() {
        return mostrarDetalle;
    }

    public void setMostrarDetalle(boolean mostrarDetalle) {
        this.mostrarDetalle = mostrarDetalle;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public List<DetalleSuplenciaDTO> getDesglosesSeleccionados() {
        return desglosesSeleccionados;
    }

    public void setDesglosesSeleccionados(List<DetalleSuplenciaDTO> desglosesSeleccionados) {
        this.desglosesSeleccionados = desglosesSeleccionados;
    }

    public boolean isMostrarDesglose() {
        return mostrarDesglose;
    }

    public void setMostrarDesglose(boolean mostrarDesglose) {
        this.mostrarDesglose = mostrarDesglose;
    }

    public List<DetalleSuplenciaDTO> getDetallesQuincena() {
        return detallesQuincena;
    }

    public void setDetallesQuincena(List<DetalleSuplenciaDTO> detallesQuincena) {
        this.detallesQuincena = detallesQuincena;
    }

}
