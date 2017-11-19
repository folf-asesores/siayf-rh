/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 30/10/2016 12:43:38
 */
public class AltaSuplenciaView {
    private List<SuplenteDTO> suplentesAutorizados = new ArrayList<>();
    private List<DetalleSuplenciaDTO> detallesSuplencias = new ArrayList<>();
    private List<AltaSuplenciaDTO> altasSuplencias = new ArrayList<>();
    private List<InformacionAdjuntoDTO> documentosAdjuntos = new ArrayList<>();
    private List<SelectItem> listadoEmpleadoDTO = new ArrayList<>();
    private DetalleSuplenciaDTO detalleSeleccionado = new DetalleSuplenciaDTO();
    private SuplenteDTO suplenteSeleccionado = new SuplenteDTO();
    private AltaSuplenciaDTO altaSuplencia = new AltaSuplenciaDTO();
    private EdicionSuplenciaDTO edicion = new EdicionSuplenciaDTO();
    private FiltroSuplenciaDTO filtro = new FiltroSuplenciaDTO();
    private List<SelectItem> tiposSuplencias;
    private List<SelectItem> listaDocumentos;
    private List<SelectItem> listaTabuladores;
    private List<SelectItem> listaJornadas;

    private String criterioEmpleado;
    private boolean mostrarBusqueda;
    private boolean mostrarDetalleSuplente;
    private boolean mostrarAltaSuplenciaRango;
    private boolean mostrarAltaSuplenciaDias;
    private boolean bloquearAdjunto;
    private boolean mostrarEmpleado;
    private boolean mostrarEdicion;
    private boolean mostrarDocumentacion;
    private int numeroQuincenaActual;
    private int ejercicioActivo;
    private String fechaMaxima;
    private String fechaMinima;
    private Integer idQuincena;
    private Integer idDocumentoAdjuntable;
    private Integer idSuplente;
    private Integer idUsuario;
    private Date fechaSuplencia;
    private BigDecimal totalSuplencias;
    private int totalDias;

    public BigDecimal getTotalSuplencias() {
        return totalSuplencias;
    }

    public void setTotalSuplencias(BigDecimal totalSuplencias) {
        this.totalSuplencias = totalSuplencias;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public int getEjercicioActivo() {
        return ejercicioActivo;
    }

    public void setEjercicioActivo(int ejercicioActivo) {
        this.ejercicioActivo = ejercicioActivo;
    }

    public Integer getIdSuplente() {
        return idSuplente;
    }

    public void setIdSuplente(Integer idSuplente) {
        this.idSuplente = idSuplente;
    }

    public List<InformacionAdjuntoDTO> getDocumentosAdjuntos() {
        return documentosAdjuntos;
    }

    public void setDocumentosAdjuntos(
            List<InformacionAdjuntoDTO> documentosAdjuntos) {
        this.documentosAdjuntos = documentosAdjuntos;
    }

    public boolean isMostrarDocumentacion() {
        return mostrarDocumentacion;
    }

    public void setMostrarDocumentacion(boolean mostrarDocumentacion) {
        this.mostrarDocumentacion = mostrarDocumentacion;
    }

    public Date getFechaSuplencia() {
        return fechaSuplencia;
    }

    public void setFechaSuplencia(Date fechaSuplencia) {
        this.fechaSuplencia = fechaSuplencia;
    }

    public List<AltaSuplenciaDTO> getAltasSuplencias() {
        return altasSuplencias;
    }

    public void setAltasSuplencias(List<AltaSuplenciaDTO> altasSuplencias) {
        this.altasSuplencias = altasSuplencias;
    }

    public boolean isMostrarAltaSuplenciaRango() {
        return mostrarAltaSuplenciaRango;
    }

    public void setMostrarAltaSuplenciaRango(
            boolean mostrarAltaSuplenciaRango) {
        this.mostrarAltaSuplenciaRango = mostrarAltaSuplenciaRango;
    }

    public boolean isMostrarAltaSuplenciaDias() {
        return mostrarAltaSuplenciaDias;
    }

    public void setMostrarAltaSuplenciaDias(boolean mostrarAltaSuplenciaDias) {
        this.mostrarAltaSuplenciaDias = mostrarAltaSuplenciaDias;
    }

    public DetalleSuplenciaDTO getDetalleSeleccionado() {
        return detalleSeleccionado;
    }

    public void setDetalleSeleccionado(
            DetalleSuplenciaDTO detalleSeleccionado) {
        this.detalleSeleccionado = detalleSeleccionado;
    }

    public boolean isMostrarEdicion() {
        return mostrarEdicion;
    }

    public void setMostrarEdicion(boolean mostrarEdicion) {
        this.mostrarEdicion = mostrarEdicion;
    }

    public EdicionSuplenciaDTO getEdicion() {
        return edicion;
    }

    public void setEdicion(EdicionSuplenciaDTO edicion) {
        this.edicion = edicion;
    }

    public boolean isMostrarEmpleado() {
        return mostrarEmpleado;
    }

    public void setMostrarEmpleado(boolean mostrarEmpleado) {
        this.mostrarEmpleado = mostrarEmpleado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<SelectItem> getListaJornadas() {
        return listaJornadas;
    }

    public void setListaJornadas(List<SelectItem> listaJornadas) {
        this.listaJornadas = listaJornadas;
    }

    public FiltroSuplenciaDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroSuplenciaDTO filtro) {
        this.filtro = filtro;
    }

    public String getCriterioEmpleado() {
        return criterioEmpleado;
    }

    public void setCriterioEmpleado(String criterioEmpleado) {
        this.criterioEmpleado = criterioEmpleado;
    }

    public List<SelectItem> getListadoEmpleadoDTO() {
        return listadoEmpleadoDTO;
    }

    public void setListadoEmpleadoDTO(List<SelectItem> listadoEmpleadoDTO) {
        this.listadoEmpleadoDTO = listadoEmpleadoDTO;
    }

    public List<SelectItem> getListaTabuladores() {
        return listaTabuladores;
    }

    public void setListaTabuladores(List<SelectItem> listaTabuladores) {
        this.listaTabuladores = listaTabuladores;
    }

    public boolean isBloquearAdjunto() {
        return bloquearAdjunto;
    }

    public void setBloquearAdjunto(boolean bloquearAdjunto) {
        this.bloquearAdjunto = bloquearAdjunto;
    }

    public Integer getIdDocumentoAdjuntable() {
        return idDocumentoAdjuntable;
    }

    public void setIdDocumentoAdjuntable(Integer idDocumentoAdjuntable) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
    }

    public List<SelectItem> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<SelectItem> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMinima(String fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public AltaSuplenciaDTO getAltaSuplencia() {
        return altaSuplencia;
    }

    public void setAltaSuplencia(AltaSuplenciaDTO altaSuplencia) {
        this.altaSuplencia = altaSuplencia;
    }

    public List<SelectItem> getTiposSuplencias() {
        return tiposSuplencias;
    }

    public void setTiposSuplencias(List<SelectItem> tiposSuplencias) {
        this.tiposSuplencias = tiposSuplencias;
    }

    public int getNumeroQuincenaActual() {
        return numeroQuincenaActual;
    }

    public void setNumeroQuincenaActual(int numeroQuincenaActual) {
        this.numeroQuincenaActual = numeroQuincenaActual;
    }

    public List<DetalleSuplenciaDTO> getDetallesSuplencias() {
        return detallesSuplencias;
    }

    public void setDetallesSuplencias(
            List<DetalleSuplenciaDTO> detallesSuplencias) {
        this.detallesSuplencias = detallesSuplencias;
    }

    public SuplenteDTO getSuplenteSeleccionado() {
        return suplenteSeleccionado;
    }

    public void setSuplenteSeleccionado(SuplenteDTO suplenteSeleccionado) {
        this.suplenteSeleccionado = suplenteSeleccionado;
    }

    public boolean isMostrarBusqueda() {
        return mostrarBusqueda;
    }

    public void setMostrarBusqueda(boolean mostrarBusqueda) {
        this.mostrarBusqueda = mostrarBusqueda;
    }

    public boolean isMostrarDetalleSuplente() {
        return mostrarDetalleSuplente;
    }

    public void setMostrarDetalleSuplente(boolean mostrarDetalleSuplente) {
        this.mostrarDetalleSuplente = mostrarDetalleSuplente;
    }

    public List<SuplenteDTO> getSuplentesAutorizados() {
        return suplentesAutorizados;
    }

    public void setSuplentesAutorizados(
            List<SuplenteDTO> suplentesAutorizados) {
        this.suplentesAutorizados = suplentesAutorizados;
    }

}
