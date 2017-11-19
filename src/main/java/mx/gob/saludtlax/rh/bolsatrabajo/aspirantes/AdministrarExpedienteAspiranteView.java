/*
 * AdministrarExpedienteAspiranteView.java
 * Creado el Oct 21, 2016 12:32:14 PM
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.aspirante.ExpedienteAspiranteDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class AdministrarExpedienteAspiranteView {

    private String criterio;
    private String numeroExpediente;
    private String imagenExpediente;

    private Integer idAspiranteSeleccionado;
    private Integer idDocumentoAdjuntable;
    private Integer idDocumentoAdjuntableHistorial;
    private Integer idExpediente;
    private Integer idImagenExpediente;

    private Boolean mostrarActualizacionExpediente;
    private Boolean mostrarAdjuntarDocumentoHistorial;
    private Boolean mostrarAperturaExpediente;
    private Boolean mostrarHistorialAcademico;
    private Boolean mostrarImagenExpediente;
    private Boolean mostrarPanelCorrecciones;
    private Boolean mostrarResultadoConsulta;
    private Boolean visualizarDatosCurso;

    private ExpedienteAspiranteDTO expediente;
    private HistorialAcademicoDTO historialAcademicoSeleccionado;
    private HistorialAcademicoDTO historial;

    private List<HistorialAcademicoDTO> historialesAcademicos;
    private List<InfoAspiranteDTO> aspirantes;
    private List<InformacionAdjuntoDTO> documentosExpedientes;
    private List<SelectItem> listaComprobantesEstudios;
    private List<SelectItem> listaDocumentosHistorialAcademico;
    private List<SelectItem> listaEscolaridades;
    private List<SelectItem> listaTiposDocumentosExpediente;
    private List<String> documentacionActualHistorial;

    /**
     * Crea una nueva instancia de AdministrarExpedienteAspiranteView
     */
    public AdministrarExpedienteAspiranteView() {
        criterio = "";
        numeroExpediente = "";
        imagenExpediente = "";
        idAspiranteSeleccionado = 0;
        idDocumentoAdjuntable = 0;
        idDocumentoAdjuntableHistorial = 0;
        idExpediente = 0;
        idImagenExpediente = 0;
        mostrarActualizacionExpediente = false;
        mostrarAdjuntarDocumentoHistorial = false;
        mostrarAperturaExpediente = false;
        mostrarHistorialAcademico = false;
        mostrarImagenExpediente = false;
        mostrarPanelCorrecciones = false;
        mostrarResultadoConsulta = false;
        visualizarDatosCurso = false;
        expediente = new ExpedienteAspiranteDTO();
        historial = new HistorialAcademicoDTO();
        historialAcademicoSeleccionado = new HistorialAcademicoDTO();
        aspirantes = new ArrayList<>();
        documentacionActualHistorial = new ArrayList<>();
        documentosExpedientes = new ArrayList<>();
        historialesAcademicos = new ArrayList<>();
        listaComprobantesEstudios = new ArrayList<>();
        listaDocumentosHistorialAcademico = new ArrayList<>();
        listaEscolaridades = new ArrayList<>();
        listaTiposDocumentosExpediente = new ArrayList<>();
    }

    /**
     * Obtiene el valor de criterio
     *
     * @return el valor de criterio
     */
    public String getCriterio() {
        return criterio;
    }

    /**
     * Pone el valor de criterio
     *
     * @param criterio
     *            el nuevo valor de criterio
     */
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    /**
     * Obtiene el valor de aspirantes
     *
     * @return el valor de aspirantes
     */
    public List<InfoAspiranteDTO> getAspirantes() {
        return aspirantes;
    }

    /**
     * Pone el valor de aspirantes
     *
     * @param aspirantes
     *            el nuevo valor de aspirantes
     */
    public void setAspirantes(List<InfoAspiranteDTO> aspirantes) {
        this.aspirantes = aspirantes;
    }

    /**
     * Obtiene el valor de mostrarResultadoConsulta
     *
     * @return el valor de mostrarResultadoConsulta
     */
    public Boolean getMostrarResultadoConsulta() {
        return mostrarResultadoConsulta;
    }

    /**
     * Pone el valor de mostrarResultadoConsulta
     *
     * @param mostrarResultadoConsulta
     *            el nuevo valor de mostrarResultadoConsulta
     */
    public void setMostrarResultadoConsulta(Boolean mostrarResultadoConsulta) {
        this.mostrarResultadoConsulta = mostrarResultadoConsulta;
    }

    /**
     * Obtiene el valor de imagenExpediente
     *
     * @return el valor de imagenExpediente
     */
    public String getImagenExpediente() {
        return imagenExpediente;
    }

    /**
     * Pone el valor de imagenExpediente
     *
     * @param imagenExpediente
     *            el nuevo valor de imagenExpediente
     */
    public void setImagenExpediente(String imagenExpediente) {
        this.imagenExpediente = imagenExpediente;
    }

    /**
     * Obtiene el valor de mostrarAperturaExpediente
     *
     * @return el valor de mostrarAperturaExpediente
     */
    public Boolean getMostrarAperturaExpediente() {
        return mostrarAperturaExpediente;
    }

    /**
     * Pone el valor de mostrarAperturaExpediente
     *
     * @param mostrarAperturaExpediente
     *            el nuevo valor de mostrarAperturaExpediente
     */
    public void setMostrarAperturaExpediente(Boolean mostrarAperturaExpediente) {
        this.mostrarAperturaExpediente = mostrarAperturaExpediente;
    }

    /**
     * Obtiene el valor de mostrarActualizacionExpediente
     *
     * @return el valor de mostrarActualizacionExpediente
     */
    public Boolean getMostrarActualizacionExpediente() {
        return mostrarActualizacionExpediente;
    }

    /**
     * Pone el valor de mostrarActualizacionExpediente
     *
     * @param mostrarActualizacionExpediente
     *            el nuevo valor de
     *            mostrarActualizacionExpediente
     */
    public void setMostrarActualizacionExpediente(Boolean mostrarActualizacionExpediente) {
        this.mostrarActualizacionExpediente = mostrarActualizacionExpediente;
    }

    /**
     * Obtiene el valor de numeroExpediente
     *
     * @return el valor de numeroExpediente
     */
    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    /**
     * Pone el valor de numeroExpediente
     *
     * @param numeroExpediente
     *            el nuevo valor de numeroExpediente
     */
    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    /**
     * Obtiene el valor de expediente
     *
     * @return el valor de expediente
     */
    public ExpedienteAspiranteDTO getExpediente() {
        return expediente;
    }

    /**
     * Pone el valor de expediente
     *
     * @param expediente
     *            el nuevo valor de expediente
     */
    public void setExpediente(ExpedienteAspiranteDTO expediente) {
        this.expediente = expediente;
    }

    /**
     * Obtiene el valor de idAspiranteSeleccionado
     *
     * @return el valor de idAspiranteSeleccionado
     */
    public Integer getIdAspiranteSeleccionado() {
        return idAspiranteSeleccionado;
    }

    /**
     * Pone el valor de idAspiranteSeleccionado
     *
     * @param idAspiranteSeleccionado
     *            el nuevo valor de idAspiranteSeleccionado
     */
    public void setIdAspiranteSeleccionado(Integer idAspiranteSeleccionado) {
        this.idAspiranteSeleccionado = idAspiranteSeleccionado;
    }

    /**
     * Obtiene el valor de idExpediente
     *
     * @return el valor de idExpediente
     */
    public Integer getIdExpediente() {
        return idExpediente;
    }

    /**
     * Pone el valor de idExpediente
     *
     * @param idExpediente
     *            el nuevo valor de idExpediente
     */
    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    /**
     * Obtiene el valor de idDocumentoAdjuntable
     *
     * @return el valor de idDocumentoAdjuntable
     */
    public Integer getIdDocumentoAdjuntable() {
        return idDocumentoAdjuntable;
    }

    /**
     * Pone el valor de idDocumentoAdjuntable
     *
     * @param idDocumentoAdjuntable
     *            el nuevo valor de idDocumentoAdjuntable
     */
    public void setIdDocumentoAdjuntable(Integer idDocumentoAdjuntable) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
    }

    /**
     * Obtiene el valor de listaTiposDocumentosExpediente
     *
     * @return el valor de listaTiposDocumentosExpediente
     */
    public List<SelectItem> getListaTiposDocumentosExpediente() {
        return listaTiposDocumentosExpediente;
    }

    /**
     * Pone el valor de listaTiposDocumentosExpediente
     *
     * @param listaTiposDocumentosExpediente
     *            el nuevo valor de
     *            listaTiposDocumentosExpediente
     */
    public void setListaTiposDocumentosExpediente(List<SelectItem> listaTiposDocumentosExpediente) {
        this.listaTiposDocumentosExpediente = listaTiposDocumentosExpediente;
    }

    /**
     * Obtiene el valor de documentosExpedientes
     *
     * @return el valor de documentosExpedientes
     */
    public List<InformacionAdjuntoDTO> getDocumentosExpedientes() {
        return documentosExpedientes;
    }

    /**
     * Pone el valor de documentosExpedientes
     *
     * @param documentosExpedientes
     *            el nuevo valor de documentosExpedientes
     */
    public void setDocumentosExpedientes(List<InformacionAdjuntoDTO> documentosExpedientes) {
        this.documentosExpedientes = documentosExpedientes;
    }

    /**
     * Obtiene el valor de idImagenExpediente
     *
     * @return el valor de idImagenExpediente
     */
    public Integer getIdImagenExpediente() {
        return idImagenExpediente;
    }

    /**
     * Pone el valor de idImagenExpediente
     *
     * @param idImagenExpediente
     *            el nuevo valor de idImagenExpediente
     */
    public void setIdImagenExpediente(Integer idImagenExpediente) {
        this.idImagenExpediente = idImagenExpediente;
    }

    /**
     * Obtiene el valor de mostrarImagenExpediente
     *
     * @return el valor de mostrarImagenExpediente
     */
    public Boolean getMostrarImagenExpediente() {
        return mostrarImagenExpediente;
    }

    /**
     * Pone el valor de mostrarImagenExpediente
     *
     * @param mostrarImagenExpediente
     *            el nuevo valor de mostrarImagenExpediente
     */
    public void setMostrarImagenExpediente(Boolean mostrarImagenExpediente) {
        this.mostrarImagenExpediente = mostrarImagenExpediente;
    }

    /**
     * Obtiene el valor de mostrarPanelCorrecciones
     *
     * @return el valor de mostrarPanelCorrecciones
     */
    public Boolean getMostrarPanelCorrecciones() {
        return mostrarPanelCorrecciones;
    }

    /**
     * Pone el valor de mostrarPanelCorrecciones
     *
     * @param mostrarPanelCorrecciones
     *            el nuevo valor de mostrarPanelCorrecciones
     */
    public void setMostrarPanelCorrecciones(Boolean mostrarPanelCorrecciones) {
        this.mostrarPanelCorrecciones = mostrarPanelCorrecciones;
    }

    /**
     * Obtiene el valor de mostrarAdjuntarDocumentoHistorial
     *
     * @return el valor de mostrarAdjuntarDocumentoHistorial
     */
    public Boolean getMostrarAdjuntarDocumentoHistorial() {
        return mostrarAdjuntarDocumentoHistorial;
    }

    /**
     * Pone el valor de mostrarAdjuntarDocumentoHistorial
     *
     * @param mostrarAdjuntarDocumentoHistorial
     *            el nuevo valor de
     *            mostrarAdjuntarDocumentoHistorial
     */
    public void setMostrarAdjuntarDocumentoHistorial(Boolean mostrarAdjuntarDocumentoHistorial) {
        this.mostrarAdjuntarDocumentoHistorial = mostrarAdjuntarDocumentoHistorial;
    }

    /**
     * Obtiene el valor de historialesAcademicos
     *
     * @return el valor de historialesAcademicos
     */
    public List<HistorialAcademicoDTO> getHistorialesAcademicos() {
        return historialesAcademicos;
    }

    /**
     * Pone el valor de historialesAcademicos
     *
     * @param historialesAcademicos
     *            el nuevo valor de historialesAcademicos
     */
    public void setHistorialesAcademicos(List<HistorialAcademicoDTO> historialesAcademicos) {
        this.historialesAcademicos = historialesAcademicos;
    }

    /**
     * Obtiene el valor de mostrarHistorialAcademico
     *
     * @return el valor de mostrarHistorialAcademico
     */
    public Boolean getMostrarHistorialAcademico() {
        return mostrarHistorialAcademico;
    }

    /**
     * Pone el valor de mostrarHistorialAcademico
     *
     * @param mostrarHistorialAcademico
     *            el nuevo valor de mostrarHistorialAcademico
     */
    public void setMostrarHistorialAcademico(Boolean mostrarHistorialAcademico) {
        this.mostrarHistorialAcademico = mostrarHistorialAcademico;
    }

    /**
     * Obtiene el valor de historialAcademicoSeleccionado
     *
     * @return el valor de historialAcademicoSeleccionado
     */
    public HistorialAcademicoDTO getHistorialAcademicoSeleccionado() {
        return historialAcademicoSeleccionado;
    }

    /**
     * Pone el valor de historialAcademicoSeleccionado
     *
     * @param historialAcademicoSeleccionado
     *            el nuevo valor de
     *            historialAcademicoSeleccionado
     */
    public void setHistorialAcademicoSeleccionado(HistorialAcademicoDTO historialAcademicoSeleccionado) {
        this.historialAcademicoSeleccionado = historialAcademicoSeleccionado;
    }

    /**
     * Obtiene el valor de historial
     *
     * @return el valor de historial
     */
    public HistorialAcademicoDTO getHistorial() {
        return historial;
    }

    /**
     * Pone el valor de historial
     *
     * @param historial
     *            el nuevo valor de historial
     */
    public void setHistorial(HistorialAcademicoDTO historial) {
        this.historial = historial;
    }

    /**
     * Obtiene el valor de listaEscolaridades
     *
     * @return el valor de listaEscolaridades
     */
    public List<SelectItem> getListaEscolaridades() {
        return listaEscolaridades;
    }

    /**
     * Pone el valor de listaEscolaridades
     *
     * @param listaEscolaridades
     *            el nuevo valor de listaEscolaridades
     */
    public void setListaEscolaridades(List<SelectItem> listaEscolaridades) {
        this.listaEscolaridades = listaEscolaridades;
    }

    /**
     * Obtiene el valor de visualizarDatosCurso
     *
     * @return el valor de visualizarDatosCurso
     */
    public Boolean getVisualizarDatosCurso() {
        return visualizarDatosCurso;
    }

    /**
     * Pone el valor de visualizarDatosCurso
     *
     * @param visualizarDatosCurso
     *            el nuevo valor de visualizarDatosCurso
     */
    public void setVisualizarDatosCurso(Boolean visualizarDatosCurso) {
        this.visualizarDatosCurso = visualizarDatosCurso;
    }

    /**
     * Obtiene el valor de listaComprobantesEstudios
     *
     * @return el valor de listaComprobantesEstudios
     */
    public List<SelectItem> getListaComprobantesEstudios() {
        return listaComprobantesEstudios;
    }

    /**
     * Pone el valor de listaComprobantesEstudios
     *
     * @param listaComprobantesEstudios
     *            el nuevo valor de listaComprobantesEstudios
     */
    public void setListaComprobantesEstudios(List<SelectItem> listaComprobantesEstudios) {
        this.listaComprobantesEstudios = listaComprobantesEstudios;
    }

    /**
     * Obtiene el valor de documentacionActualHistorial
     *
     * @return el valor de documentacionActualHistorial
     */
    public List<String> getDocumentacionActualHistorial() {
        return documentacionActualHistorial;
    }

    /**
     * Pone el valor de documentacionActualHistorial
     *
     * @param documentacionActualHistorial
     *            el nuevo valor de
     *            documentacionActualHistorial
     */
    public void setDocumentacionActualHistorial(List<String> documentacionActualHistorial) {
        this.documentacionActualHistorial = documentacionActualHistorial;
    }

    /**
     * Obtiene el valor de idDocumentoAdjuntableHistorial
     *
     * @return el valor de idDocumentoAdjuntableHistorial
     */
    public Integer getIdDocumentoAdjuntableHistorial() {
        return idDocumentoAdjuntableHistorial;
    }

    /**
     * Pone el valor de idDocumentoAdjuntableHistorial
     *
     * @param idDocumentoAdjuntableHistorial
     *            el nuevo valor de
     *            idDocumentoAdjuntableHistorial
     */
    public void setIdDocumentoAdjuntableHistorial(Integer idDocumentoAdjuntableHistorial) {
        this.idDocumentoAdjuntableHistorial = idDocumentoAdjuntableHistorial;
    }

    /**
     * Obtiene el valor de listaDocumentosHistorialAcademico
     *
     * @return el valor de listaDocumentosHistorialAcademico
     */
    public List<SelectItem> getListaDocumentosHistorialAcademico() {
        return listaDocumentosHistorialAcademico;
    }

    /**
     * Pone el valor de listaDocumentosHistorialAcademico
     *
     * @param listaDocumentosHistorialAcademico
     *            el nuevo valor de
     *            listaDocumentosHistorialAcademico
     */
    public void setListaDocumentosHistorialAcademico(List<SelectItem> listaDocumentosHistorialAcademico) {
        this.listaDocumentosHistorialAcademico = listaDocumentosHistorialAcademico;
    }

}
