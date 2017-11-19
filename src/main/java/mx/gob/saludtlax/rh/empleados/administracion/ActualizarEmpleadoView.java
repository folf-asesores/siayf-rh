/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.historialacademico.NuevoHistorialDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:18:29 21/09/2016
 */
public class ActualizarEmpleadoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -110072638551813974L;

	private List<InfoEmpleadoDTO> empleados = new ArrayList<InfoEmpleadoDTO>();

	private String criterio;
	private Integer idEmpleadoSeleccionado;

	// Principales
	private boolean mostrarResultadoConsulta = true;

	// Secundarios
	private boolean mostrarPanelCorrecciones = false;

	// Datos Generales
	private DatosGeneralesDTO datosGenerales = new DatosGeneralesDTO();
	private boolean visualizarBotonDependientes = false;
	private boolean mostrarActualizacionExpediente;
	private String numeroExpediente;
	private Integer idExpediente;
	private String imagenExpediente;
	private boolean mostrarAperturaExpediente;
	private boolean mostrarDatosGenerales;
	private List<SelectItem> listaEstadosCiviles;
	private List<SelectItem> listaTiposSexos;
	private List<SelectItem> listaTiposSangre;
	private List<SelectItem> listaTiposParentescos;
	private List<SelectItem> listaEscolaridades = new ArrayList<SelectItem>();
	private List<SelectItem> listaComprobantesEstudios = new ArrayList<SelectItem>();

	// Domicilio
	private List<SelectItem> listaEstados = new ArrayList<SelectItem>();
	private List<SelectItem> listaMuncipios = new ArrayList<SelectItem>();
	private List<SelectItem> listaPoblaciones = new ArrayList<SelectItem>();
	private List<SelectItem> listaColonias = new ArrayList<SelectItem>();
	private boolean mostrarDomicilio = false;
	private DomicilioDTO domicilio = new DomicilioDTO();

	// Historial
	private List<HistorialAcademicoDTO> historialesAcademicos = new ArrayList<HistorialAcademicoDTO>();
	private HistorialAcademicoDTO historialAcademicoSeleccionado = new HistorialAcademicoDTO();
	private NuevoHistorialDTO historial = new NuevoHistorialDTO();
	private List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico;
	private boolean mostrarAdjuntoDocumentoHistorial = false;
	private boolean mostrarHistorialAcademico = false;
	private boolean mostrarRegistroHistorial = false;
	private boolean mostrarActualizarHistorial = false;
	private boolean visualizarDatosCurso = false;
	private Integer idDocumentoAdjuntableHistorial;
	private List<SelectItem> listaDocumentosHistorialAcademico = new ArrayList<SelectItem>();
	private Integer idImagenExpediente;
	private boolean mostrarImagenExpediente = false;

	// Experiencias laborales
	private boolean mostrarExperienciaLaboral = false;
	private List<ExperienciaLaboralDTO> experienciasLaborales = new ArrayList<ExperienciaLaboralDTO>();
	private ExperienciaLaboralDTO experienciaLaboral = new ExperienciaLaboralDTO();
	private ExperienciaLaboralDTO experienciaLaboralSeleccionado = new ExperienciaLaboralDTO();
	private boolean mostrarRegistroExperiencia = false;
	private boolean mostrarActualizacionExperiencia = false;

	// Dialogs
	private boolean mostrarDialogoConfirmacionDatosGenerales = false;
	private Boolean mostrarDialogoConfirmacionDomicilio = Boolean.FALSE;
	private boolean mostrarDialogoConfirmacionHistorial = false;
	private boolean mostrarDialogoConfirmacionExperiencia = false;

	
	/**
	 * @return the listaEstadosCiviles
	 */
	public List<SelectItem> getListaEstadosCiviles() {
		return listaEstadosCiviles;
	}

	/**
	 * @param listaEstadosCiviles
	 *            the listaEstadosCiviles to set
	 */
	public void setListaEstadosCiviles(List<SelectItem> listaEstadosCiviles) {
		this.listaEstadosCiviles = listaEstadosCiviles;
	}

	/**
	 * @return the listaTiposSexos
	 */
	public List<SelectItem> getListaTiposSexos() {
		return listaTiposSexos;
	}

	/**
	 * @param listaTiposSexos
	 *            the listaTiposSexos to set
	 */
	public void setListaTiposSexos(List<SelectItem> listaTiposSexos) {
		this.listaTiposSexos = listaTiposSexos;
	}

	/**
	 * @return the listaTiposSangre
	 */
	public List<SelectItem> getListaTiposSangre() {
		return listaTiposSangre;
	}

	public boolean isMostrarDialogoConfirmacionDatosGenerales() {
		return mostrarDialogoConfirmacionDatosGenerales;
	}

	public void setMostrarDialogoConfirmacionDatosGenerales(boolean mostrarDialogoConfirmacionDatosGenerales) {
		this.mostrarDialogoConfirmacionDatosGenerales = mostrarDialogoConfirmacionDatosGenerales;
	}

	/**
	 * @param listaTiposSangre
	 *            the listaTiposSangre to set
	 */
	public void setListaTiposSangre(List<SelectItem> listaTiposSangre) {
		this.listaTiposSangre = listaTiposSangre;
	}

	/**
	 * @return the listaTiposParentescos
	 */
	public List<SelectItem> getListaTiposParentescos() {
		return listaTiposParentescos;
	}

	/**
	 * @param listaTiposParentescos
	 *            the listaTiposParentescos to set
	 */
	public void setListaTiposParentescos(List<SelectItem> listaTiposParentescos) {
		this.listaTiposParentescos = listaTiposParentescos;
	}

	/**
	 * @return the empleados
	 */
	public List<InfoEmpleadoDTO> getEmpleados() {
		return empleados;
	}

	/**
	 * @param empleados
	 *            the empleados to set
	 */
	public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
		this.empleados = empleados;
	}

	/**
	 * @return the criterio
	 */
	public String getCriterio() {
		return criterio;
	}

	/**
	 * @param criterio
	 *            the criterio to set
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	/**
	 * @return the mostrarResultadoConsulta
	 */
	public boolean isMostrarResultadoConsulta() {
		return mostrarResultadoConsulta;
	}

	/**
	 * @param mostrarResultadoConsulta
	 *            the mostrarResultadoConsulta to set
	 */
	public void setMostrarResultadoConsulta(boolean mostrarResultadoConsulta) {
		this.mostrarResultadoConsulta = mostrarResultadoConsulta;
	}

	/**
	 * @return the datosGenerales
	 */
	public DatosGeneralesDTO getDatosGenerales() {
		return datosGenerales;
	}

	/**
	 * @param datosGenerales
	 *            the datosGenerales to set
	 */
	public void setDatosGenerales(DatosGeneralesDTO datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	/**
	 * @return the visualizarBotonDependientes
	 */
	public boolean isVisualizarBotonDependientes() {
		return visualizarBotonDependientes;
	}

	/**
	 * @param visualizarBotonDependientes
	 *            the visualizarBotonDependientes to set
	 */
	public void setVisualizarBotonDependientes(boolean visualizarBotonDependientes) {
		this.visualizarBotonDependientes = visualizarBotonDependientes;
	}

	/**
	 * @return the mostrarActualizacionExpediente
	 */
	public boolean isMostrarActualizacionExpediente() {
		return mostrarActualizacionExpediente;
	}

	/**
	 * @param mostrarActualizacionExpediente
	 *            the mostrarActualizacionExpediente to set
	 */
	public void setMostrarActualizacionExpediente(boolean mostrarActualizacionExpediente) {
		this.mostrarActualizacionExpediente = mostrarActualizacionExpediente;
	}

	/**
	 * @return the numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @param numeroExpediente
	 *            the numeroExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * @return the idExpediente
	 */
	public Integer getIdExpediente() {
		return idExpediente;
	}

	/**
	 * @param idExpediente
	 *            the idExpediente to set
	 */
	public void setIdExpediente(Integer idExpediente) {
		this.idExpediente = idExpediente;
	}

	/**
	 * @return the imagenExpediente
	 */
	public String getImagenExpediente() {
		return imagenExpediente;
	}

	/**
	 * @param imagenExpediente
	 *            the imagenExpediente to set
	 */
	public void setImagenExpediente(String imagenExpediente) {
		this.imagenExpediente = imagenExpediente;
	}

	/**
	 * @return the mostrarAperturaExpediente
	 */
	public boolean isMostrarAperturaExpediente() {
		return mostrarAperturaExpediente;
	}

	/**
	 * @param mostrarAperturaExpediente
	 *            the mostrarAperturaExpediente to set
	 */
	public void setMostrarAperturaExpediente(boolean mostrarAperturaExpediente) {
		this.mostrarAperturaExpediente = mostrarAperturaExpediente;
	}

	/**
	 * @return the mostrarPanelCorrecciones
	 */
	public boolean isMostrarPanelCorrecciones() {
		return mostrarPanelCorrecciones;
	}

	/**
	 * @param mostrarPanelCorrecciones
	 *            the mostrarPanelCorrecciones to set
	 */
	public void setMostrarPanelCorrecciones(boolean mostrarPanelCorrecciones) {
		this.mostrarPanelCorrecciones = mostrarPanelCorrecciones;
	}

	/**
	 * @return the mostrarDatosGenerales
	 */
	public boolean isMostrarDatosGenerales() {
		return mostrarDatosGenerales;
	}

	/**
	 * @param mostrarDatosGenerales
	 *            the mostrarDatosGenerales to set
	 */
	public void setMostrarDatosGenerales(boolean mostrarDatosGenerales) {
		this.mostrarDatosGenerales = mostrarDatosGenerales;
	}

	/**
	 * @return the idEmpleadoSeleccionado
	 */
	public Integer getIdEmpleadoSeleccionado() {
		return idEmpleadoSeleccionado;
	}

	/**
	 * @param idEmpleadoSeleccionado
	 *            the idEmpleadoSeleccionado to set
	 */
	public void setIdEmpleadoSeleccionado(Integer idEmpleadoSeleccionado) {
		this.idEmpleadoSeleccionado = idEmpleadoSeleccionado;
	}

	/**
	 * @return the listaEscolaridades
	 */
	public List<SelectItem> getListaEscolaridades() {
		return listaEscolaridades;
	}

	/**
	 * @param listaEscolaridades
	 *            the listaEscolaridades to set
	 */
	public void setListaEscolaridades(List<SelectItem> listaEscolaridades) {
		this.listaEscolaridades = listaEscolaridades;
	}

	/**
	 * @return the listaComprobantesEstudios
	 */
	public List<SelectItem> getListaComprobantesEstudios() {
		return listaComprobantesEstudios;
	}

	/**
	 * @param listaComprobantesEstudios
	 *            the listaComprobantesEstudios to set
	 */
	public void setListaComprobantesEstudios(List<SelectItem> listaComprobantesEstudios) {
		this.listaComprobantesEstudios = listaComprobantesEstudios;
	}

	/**
	 * @return the listaColonias
	 */
	public List<SelectItem> getListaColonias() {
		return listaColonias;
	}

	/**
	 * @param listaColonias
	 *            the listaColonias to set
	 */
	public void setListaColonias(List<SelectItem> listaColonias) {
		this.listaColonias = listaColonias;
	}

	/**
	 * @return the listaPoblaciones
	 */
	public List<SelectItem> getListaPoblaciones() {
		return listaPoblaciones;
	}

	/**
	 * @param listaPoblaciones
	 *            the listaPoblaciones to set
	 */
	public void setListaPoblaciones(List<SelectItem> listaPoblaciones) {
		this.listaPoblaciones = listaPoblaciones;
	}

	/**
	 * @return the listaMuncipios
	 */
	public List<SelectItem> getListaMuncipios() {
		return listaMuncipios;
	}

	/**
	 * @param listaMuncipios
	 *            the listaMuncipios to set
	 */
	public void setListaMuncipios(List<SelectItem> listaMuncipios) {
		this.listaMuncipios = listaMuncipios;
	}

	/**
	 * @return the listaEstados
	 */
	public List<SelectItem> getListaEstados() {
		return listaEstados;
	}

	/**
	 * @param listaEstados
	 *            the listaEstados to set
	 */
	public void setListaEstados(List<SelectItem> listaEstados) {
		this.listaEstados = listaEstados;
	}

	/**
	 * @return the mostrarDomicilio
	 */
	public boolean isMostrarDomicilio() {
		return mostrarDomicilio;
	}

	/**
	 * @param mostrarDomicilio
	 *            the mostrarDomicilio to set
	 */
	public void setMostrarDomicilio(boolean mostrarDomicilio) {
		this.mostrarDomicilio = mostrarDomicilio;
	}

	/**
	 * @return the domicilio
	 */
	public DomicilioDTO getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the mostrarHistorialAcademico
	 */
	public boolean isMostrarHistorialAcademico() {
		return mostrarHistorialAcademico;
	}

	/**
	 * @param mostrarHistorialAcademico
	 *            the mostrarHistorialAcademico to set
	 */
	public void setMostrarHistorialAcademico(boolean mostrarHistorialAcademico) {
		this.mostrarHistorialAcademico = mostrarHistorialAcademico;
	}

	/**
	 * @return the mostrarRegistroHistorial
	 */
	public boolean isMostrarRegistroHistorial() {
		return mostrarRegistroHistorial;
	}

	/**
	 * @param mostrarRegistroHistorial
	 *            the mostrarRegistroHistorial to set
	 */
	public void setMostrarRegistroHistorial(boolean mostrarRegistroHistorial) {
		this.mostrarRegistroHistorial = mostrarRegistroHistorial;
	}

	/**
	 * @return the historialesAcademicos
	 */
	public List<HistorialAcademicoDTO> getHistorialesAcademicos() {
		return historialesAcademicos;
	}

	/**
	 * @param historialesAcademicos
	 *            the historialesAcademicos to set
	 */
	public void setHistorialesAcademicos(List<HistorialAcademicoDTO> historialesAcademicos) {
		this.historialesAcademicos = historialesAcademicos;
	}

	/**
	 * @return the historialAcademicoSeleccionado
	 */
	public HistorialAcademicoDTO getHistorialAcademicoSeleccionado() {
		return historialAcademicoSeleccionado;
	}

	/**
	 * @param historialAcademicoSeleccionado
	 *            the historialAcademicoSeleccionado to set
	 */
	public void setHistorialAcademicoSeleccionado(HistorialAcademicoDTO historialAcademicoSeleccionado) {
		this.historialAcademicoSeleccionado = historialAcademicoSeleccionado;
	}

	/**
	 * @return the documentosAdjuntosGradoAcademico
	 */
	public List<InformacionAdjuntoDTO> getDocumentosAdjuntosGradoAcademico() {
		return documentosAdjuntosGradoAcademico;
	}

	/**
	 * @param documentosAdjuntosGradoAcademico
	 *            the documentosAdjuntosGradoAcademico to set
	 */
	public void setDocumentosAdjuntosGradoAcademico(List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico) {
		this.documentosAdjuntosGradoAcademico = documentosAdjuntosGradoAcademico;
	}

	/**
	 * @return the mostrarAdjuntoDocumentoHistorial
	 */
	public boolean isMostrarAdjuntoDocumentoHistorial() {
		return mostrarAdjuntoDocumentoHistorial;
	}

	/**
	 * @param mostrarAdjuntoDocumentoHistorial
	 *            the mostrarAdjuntoDocumentoHistorial to set
	 */
	public void setMostrarAdjuntoDocumentoHistorial(boolean mostrarAdjuntoDocumentoHistorial) {
		this.mostrarAdjuntoDocumentoHistorial = mostrarAdjuntoDocumentoHistorial;
	}

	/**
	 * @return the historial
	 */
	public NuevoHistorialDTO getHistorial() {
		return historial;
	}

	/**
	 * @param historial
	 *            the historial to set
	 */
	public void setHistorial(NuevoHistorialDTO historial) {
		this.historial = historial;
	}

	/**
	 * @return the visualizarDatosCurso
	 */
	public boolean isVisualizarDatosCurso() {
		return visualizarDatosCurso;
	}

	/**
	 * @param visualizarDatosCurso
	 *            the visualizarDatosCurso to set
	 */
	public void setVisualizarDatosCurso(boolean visualizarDatosCurso) {
		this.visualizarDatosCurso = visualizarDatosCurso;
	}

	/**
	 * @return the idDocumentoAdjuntableHistorial
	 */
	public Integer getIdDocumentoAdjuntableHistorial() {
		return idDocumentoAdjuntableHistorial;
	}

	/**
	 * @param idDocumentoAdjuntableHistorial
	 *            the idDocumentoAdjuntableHistorial to set
	 */
	public void setIdDocumentoAdjuntableHistorial(Integer idDocumentoAdjuntableHistorial) {
		this.idDocumentoAdjuntableHistorial = idDocumentoAdjuntableHistorial;
	}

	/**
	 * @return the listaDocumentosHistorialAcademico
	 */
	public List<SelectItem> getListaDocumentosHistorialAcademico() {
		return listaDocumentosHistorialAcademico;
	}

	/**
	 * @param listaDocumentosHistorialAcademico
	 *            the listaDocumentosHistorialAcademico to set
	 */
	public void setListaDocumentosHistorialAcademico(List<SelectItem> listaDocumentosHistorialAcademico) {
		this.listaDocumentosHistorialAcademico = listaDocumentosHistorialAcademico;
	}

	/**
	 * @return the idImagenExpediente
	 */
	public Integer getIdImagenExpediente() {
		return idImagenExpediente;
	}

	/**
	 * @param idImagenExpediente
	 *            the idImagenExpediente to set
	 */
	public void setIdImagenExpediente(Integer idImagenExpediente) {
		this.idImagenExpediente = idImagenExpediente;
	}

	/**
	 * @return the mostrarImagenExpediente
	 */
	public boolean isMostrarImagenExpediente() {
		return mostrarImagenExpediente;
	}

	/**
	 * @param mostrarImagenExpediente
	 *            the mostrarImagenExpediente to set
	 */
	public void setMostrarImagenExpediente(boolean mostrarImagenExpediente) {
		this.mostrarImagenExpediente = mostrarImagenExpediente;
	}

	/**
	 * @return the mostrarActualizarHistorial
	 */
	public boolean isMostrarActualizarHistorial() {
		return mostrarActualizarHistorial;
	}

	/**
	 * @param mostrarActualizarHistorial
	 *            the mostrarActualizarHistorial to set
	 */
	public void setMostrarActualizarHistorial(boolean mostrarActualizarHistorial) {
		this.mostrarActualizarHistorial = mostrarActualizarHistorial;
	}

	/**
	 * @return the mostrarExperienciaLaboral
	 */
	public boolean isMostrarExperienciaLaboral() {
		return mostrarExperienciaLaboral;
	}

	/**
	 * @param mostrarExperienciaLaboral
	 *            the mostrarExperienciaLaboral to set
	 */
	public void setMostrarExperienciaLaboral(boolean mostrarExperienciaLaboral) {
		this.mostrarExperienciaLaboral = mostrarExperienciaLaboral;
	}

	/**
	 * @return the experienciasLaborales
	 */
	public List<ExperienciaLaboralDTO> getExperienciasLaborales() {
		return experienciasLaborales;
	}

	/**
	 * @param experienciasLaborales
	 *            the experienciasLaborales to set
	 */
	public void setExperienciasLaborales(List<ExperienciaLaboralDTO> experienciasLaborales) {
		this.experienciasLaborales = experienciasLaborales;
	}

	/**
	 * @return the experienciaLaboral
	 */
	public ExperienciaLaboralDTO getExperienciaLaboral() {
		return experienciaLaboral;
	}

	/**
	 * @param experienciaLaboral
	 *            the experienciaLaboral to set
	 */
	public void setExperienciaLaboral(ExperienciaLaboralDTO experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	/**
	 * @return the mostrarRegistroExperiencia
	 */
	public boolean isMostrarRegistroExperiencia() {
		return mostrarRegistroExperiencia;
	}

	/**
	 * @param mostrarRegistroExperiencia
	 *            the mostrarRegistroExperiencia to set
	 */
	public void setMostrarRegistroExperiencia(boolean mostrarRegistroExperiencia) {
		this.mostrarRegistroExperiencia = mostrarRegistroExperiencia;
	}

	/**
	 * @return the mostrarActualizacionExperiencia
	 */
	public boolean isMostrarActualizacionExperiencia() {
		return mostrarActualizacionExperiencia;
	}

	/**
	 * @param mostrarActualizacionExperiencia
	 *            the mostrarActualizacionExperiencia to set
	 */
	public void setMostrarActualizacionExperiencia(boolean mostrarActualizacionExperiencia) {
		this.mostrarActualizacionExperiencia = mostrarActualizacionExperiencia;
	}

	/**
	 * @return the experienciaLaboralSeleccionado
	 */
	public ExperienciaLaboralDTO getExperienciaLaboralSeleccionado() {
		return experienciaLaboralSeleccionado;
	}

	/**
	 * @param experienciaLaboralSeleccionado
	 *            the experienciaLaboralSeleccionado to set
	 */
	public void setExperienciaLaboralSeleccionado(ExperienciaLaboralDTO experienciaLaboralSeleccionado) {
		this.experienciaLaboralSeleccionado = experienciaLaboralSeleccionado;
	}



	/**
	 * @return the mostrarDialogoConfirmacionHistorial
	 */
	public boolean isMostrarDialogoConfirmacionHistorial() {
		return mostrarDialogoConfirmacionHistorial;
	}

	/**
	 * @param mostrarDialogoConfirmacionHistorial the mostrarDialogoConfirmacionHistorial to set
	 */
	public void setMostrarDialogoConfirmacionHistorial(boolean mostrarDialogoConfirmacionHistorial) {
		this.mostrarDialogoConfirmacionHistorial = mostrarDialogoConfirmacionHistorial;
	}

	/**
	 * @return the mostrarDialogoConfirmacionExperiencia
	 */
	public boolean isMostrarDialogoConfirmacionExperiencia() {
		return mostrarDialogoConfirmacionExperiencia;
	}

	/**
	 * @param mostrarDialogoConfirmacionExperiencia the mostrarDialogoConfirmacionExperiencia to set
	 */
	public void setMostrarDialogoConfirmacionExperiencia(boolean mostrarDialogoConfirmacionExperiencia) {
		this.mostrarDialogoConfirmacionExperiencia = mostrarDialogoConfirmacionExperiencia;
	}

	/**
	 * @return the mostrarDialogoConfirmacionDomicilio
	 */
	public Boolean getMostrarDialogoConfirmacionDomicilio() {
		return mostrarDialogoConfirmacionDomicilio;
	}

	/**
	 * @param mostrarDialogoConfirmacionDomicilio the mostrarDialogoConfirmacionDomicilio to set
	 */
	public void setMostrarDialogoConfirmacionDomicilio(Boolean mostrarDialogoConfirmacionDomicilio) {
		this.mostrarDialogoConfirmacionDomicilio = mostrarDialogoConfirmacionDomicilio;
	}

}
