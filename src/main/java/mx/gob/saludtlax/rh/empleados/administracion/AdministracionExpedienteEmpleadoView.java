/*
 * AdministracionExpedienteEmpleadoView.java
 * Creado el Oct 18, 2016 12:23:22 PM
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.ExpedienteDTO;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.historialacademico.NuevoHistorialDTO;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class AdministracionExpedienteEmpleadoView {

    private String criterio;
    private String numeroExpediente;
    private Integer idDocumentoAdjuntable;
    private Integer idImagenExpediente;
    private Integer idDocumentoAdjuntableHistorial;
    private Integer idExpediente;
    private List<String> documentacionActualHistorial;
    private Integer idDocumentoAduntableDependiente;
    private Integer idUsuarioLogeado;

    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();
    private List<HistorialAcademicoDTO> historialesAcademicos = new ArrayList<>();
    private List<ExperienciaLaboralDTO> experienciasLaborales = new ArrayList<>();
    private List<InformacionAdjuntoDTO> documentosExpedientes = new ArrayList<>();
    private List<SelectItem> listaEstadosCiviles;
    private List<SelectItem> listaTiposSexos;
    private List<SelectItem> listaTiposSangre;
    private List<SelectItem> listaTiposParentescos;
    private List<SelectItem> listaEstados = new ArrayList<>();
    private List<SelectItem> listaMuncipios = new ArrayList<>();
    private List<SelectItem> listaPoblaciones = new ArrayList<>();
    private List<SelectItem> listaColonias = new ArrayList<>();
    private List<SelectItem> listaEscolaridades = new ArrayList<>();
    private List<SelectItem> listaComprobantesEstudios = new ArrayList<>();
    private List<SelectItem> listaTiposDocumentosExpediente = new ArrayList<>();
    private List<SelectItem> listaDocumentosHistorialAcademico = new ArrayList<>();
    private List<SelectItem> listaDocumentosDependientes = new ArrayList<>();

    private DomicilioDTO domicilio = new DomicilioDTO();
    private HistorialAcademicoDTO historialAcademicoSeleccionado = new HistorialAcademicoDTO();
    private ExperienciaLaboralDTO experienciaLaboral = new ExperienciaLaboralDTO();
    private ExpedienteDTO expediente = new ExpedienteDTO();
    private NuevoHistorialDTO historial = new NuevoHistorialDTO();
    private DependienteEconomicoDTO dependiente = new DependienteEconomicoDTO();
    private InfoDependienteEconomicoDTO dependienteSeleccionado = new InfoDependienteEconomicoDTO();
    private EmpleadoDetalladoDTO empleadoSeleccionado = new EmpleadoDetalladoDTO();
    private List<InfoDependienteEconomicoDTO> dependientesEconomicos = new ArrayList<>();

    private boolean mostrarResultadoConsulta;
    private boolean mostrarPanelCorrecciones;
    private boolean mostrarDomicilio;
    private boolean mostrarHistorialAcademico;
    private boolean mostrarExperienciaLaboral;
    private boolean mostrarExpediente;
    private boolean mostrarAperturaExpediente;
    private boolean mostrarActualizacionExpediente;
    private boolean mostrarImagenExpediente;
    private boolean mostrarRegistroHistorial;
    private boolean mostrarAdjuntarDocumentoHistorial;
    private boolean visualizarDatosCurso;
    private boolean mostrarDependientesEconomicos;
    private boolean mostrarOtroParentesco;
    private boolean mostrarAdjuntarDocumentoDependiente;
    private boolean mostrarRegistroExperienciaLaboral;
    

  

	public EmpleadoDetalladoDTO getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(EmpleadoDetalladoDTO empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	public AdministracionExpedienteEmpleadoView() {
        mostrarPanelCorrecciones = false;
    }

    public Integer getIdUsuarioLogeado() {
        return idUsuarioLogeado;
    }

    public void setIdUsuarioLogeado(Integer idUsuarioLogeado) {
        this.idUsuarioLogeado = idUsuarioLogeado;
    }

    public List<SelectItem> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public boolean getMostrarRegistroExperienciaLaboral() {
        return mostrarRegistroExperienciaLaboral;
    }

    public void setMostrarRegistroExperienciaLaboral(
            boolean mostrarRegistroExperienciaLaboral) {
        this.mostrarRegistroExperienciaLaboral = mostrarRegistroExperienciaLaboral;
    }

    public boolean getMostrarAdjuntarDocumentoDependiente() {
        return mostrarAdjuntarDocumentoDependiente;
    }

    public void setMostrarAdjuntarDocumentoDependiente(
            boolean mostrarAdjuntarDocumentoDependiente) {
        this.mostrarAdjuntarDocumentoDependiente = mostrarAdjuntarDocumentoDependiente;
    }

    public Integer getIdDocumentoAduntableDependiente() {
        return idDocumentoAduntableDependiente;
    }

    public void setIdDocumentoAduntableDependiente(
            Integer idDocumentoAduntableDependiente) {
        this.idDocumentoAduntableDependiente = idDocumentoAduntableDependiente;
    }

    public InfoDependienteEconomicoDTO getDependienteSeleccionado() {
        return dependienteSeleccionado;
    }

    public void setDependienteSeleccionado(
            InfoDependienteEconomicoDTO dependienteSeleccionado) {
        this.dependienteSeleccionado = dependienteSeleccionado;
    }

    public List<SelectItem> getListaDocumentosDependientes() {
        return listaDocumentosDependientes;
    }

    public void setListaDocumentosDependientes(
            List<SelectItem> listaDocumentosDependientes) {
        this.listaDocumentosDependientes = listaDocumentosDependientes;
    }

    public List<InfoDependienteEconomicoDTO> getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(
            List<InfoDependienteEconomicoDTO> dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    public boolean getMostrarOtroParentesco() {
        return mostrarOtroParentesco;
    }

    public void setMostrarOtroParentesco(boolean mostrarOtroParentesco) {
        this.mostrarOtroParentesco = mostrarOtroParentesco;
    }

    public boolean getMostrarDependientesEconomicos() {
        return mostrarDependientesEconomicos;
    }

    public void setMostrarDependientesEconomicos(
            boolean mostrarDependientesEconomicos) {
        this.mostrarDependientesEconomicos = mostrarDependientesEconomicos;
    }

    public DependienteEconomicoDTO getDependiente() {
        return dependiente;
    }

    public void setDependiente(DependienteEconomicoDTO dependiente) {
        this.dependiente = dependiente;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Integer getIdDocumentoAdjuntableHistorial() {
        return idDocumentoAdjuntableHistorial;
    }

    public void setIdDocumentoAdjuntableHistorial(
            Integer idDocumentoAdjuntableHistorial) {
        this.idDocumentoAdjuntableHistorial = idDocumentoAdjuntableHistorial;
    }

    public List<SelectItem> getListaDocumentosHistorialAcademico() {
        return listaDocumentosHistorialAcademico;
    }

    public void setListaDocumentosHistorialAcademico(
            List<SelectItem> listaDocumentosHistorialAcademico) {
        this.listaDocumentosHistorialAcademico = listaDocumentosHistorialAcademico;
    }

    public List<String> getDocumentacionActualHistorial() {
        return documentacionActualHistorial;
    }

    public void setDocumentacionActualHistorial(
            List<String> documentacionActualHistorial) {
        this.documentacionActualHistorial = documentacionActualHistorial;
    }

    public boolean getVisualizarDatosCurso() {
        return visualizarDatosCurso;
    }

    public void setVisualizarDatosCurso(boolean visualizarDatosCurso) {
        this.visualizarDatosCurso = visualizarDatosCurso;
    }

    public NuevoHistorialDTO getHistorial() {
        return historial;
    }

    public void setHistorial(NuevoHistorialDTO historial) {
        this.historial = historial;
    }

    public boolean getMostrarRegistroHistorial() {
        return mostrarRegistroHistorial;
    }

    public void setMostrarRegistroHistorial(boolean mostrarRegistroHistorial) {
        this.mostrarRegistroHistorial = mostrarRegistroHistorial;
    }

    public boolean getMostrarAdjuntarDocumentoHistorial() {
        return mostrarAdjuntarDocumentoHistorial;
    }

    public void setMostrarAdjuntarDocumentoHistorial(
            boolean mostrarAdjuntarDocumentoHistorial) {
        this.mostrarAdjuntarDocumentoHistorial = mostrarAdjuntarDocumentoHistorial;
    }

    public Integer getIdImagenExpediente() {
        return idImagenExpediente;
    }

    public void setIdImagenExpediente(Integer idImagenExpediente) {
        this.idImagenExpediente = idImagenExpediente;
    }

    public boolean getMostrarImagenExpediente() {
        return mostrarImagenExpediente;
    }

    public void setMostrarImagenExpediente(boolean mostrarImagenExpediente) {
        this.mostrarImagenExpediente = mostrarImagenExpediente;
    }

    public List<InformacionAdjuntoDTO> getDocumentosExpedientes() {
        return documentosExpedientes;
    }

    public void setDocumentosExpedientes(
            List<InformacionAdjuntoDTO> documentosExpedientes) {
        this.documentosExpedientes = documentosExpedientes;
    }

    public Integer getIdDocumentoAdjuntable() {
        return idDocumentoAdjuntable;
    }

    public void setIdDocumentoAdjuntable(Integer idDocumentoAdjuntable) {
        this.idDocumentoAdjuntable = idDocumentoAdjuntable;
    }

    public List<SelectItem> getListaTiposDocumentosExpediente() {
        return listaTiposDocumentosExpediente;
    }

    public void setListaTiposDocumentosExpediente(
            List<SelectItem> listaTiposDocumentosExpediente) {
        this.listaTiposDocumentosExpediente = listaTiposDocumentosExpediente;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public ExpedienteDTO getExpediente() {
        return expediente;
    }

    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }

    public boolean getMostrarAperturaExpediente() {
        return mostrarAperturaExpediente;
    }

    public void setMostrarAperturaExpediente(boolean mostrarAperturaExpediente) {
        this.mostrarAperturaExpediente = mostrarAperturaExpediente;
    }

    public boolean getMostrarActualizacionExpediente() {
        return mostrarActualizacionExpediente;
    }

    public void setMostrarActualizacionExpediente(
            boolean mostrarActualizacionExpediente) {
        this.mostrarActualizacionExpediente = mostrarActualizacionExpediente;
    }

    private Integer idEmpleadoSeleccionado;
    private String imagenExpediente;

    public String getImagenExpediente() {
        return imagenExpediente;
    }

    public void setImagenExpediente(String imagenExpediente) {
        this.imagenExpediente = imagenExpediente;
    }

    public Integer getIdEmpleadoSeleccionado() {
        return idEmpleadoSeleccionado;
    }

    public void setIdEmpleadoSeleccionado(Integer idEmpleadoSeleccionado) {
        this.idEmpleadoSeleccionado = idEmpleadoSeleccionado;
    }

    public List<SelectItem> getListaEstadosCiviles() {
        return listaEstadosCiviles;
    }

    public void setListaEstadosCiviles(List<SelectItem> listaEstadosCiviles) {
        this.listaEstadosCiviles = listaEstadosCiviles;
    }

    public List<SelectItem> getListaTiposSexos() {
        return listaTiposSexos;
    }

    public void setListaTiposSexos(List<SelectItem> listaTiposSexos) {
        this.listaTiposSexos = listaTiposSexos;
    }

    public List<SelectItem> getListaTiposSangre() {
        return listaTiposSangre;
    }

    public void setListaTiposSangre(List<SelectItem> listaTiposSangre) {
        this.listaTiposSangre = listaTiposSangre;
    }

    public List<SelectItem> getListaTiposParentescos() {
        return listaTiposParentescos;
    }

    public void setListaTiposParentescos(List<SelectItem> listaTiposParentescos) {
        this.listaTiposParentescos = listaTiposParentescos;
    }

    public List<SelectItem> getListaMuncipios() {
        return listaMuncipios;
    }

    public void setListaMuncipios(List<SelectItem> listaMuncipios) {
        this.listaMuncipios = listaMuncipios;
    }

    public List<SelectItem> getListaPoblaciones() {
        return listaPoblaciones;
    }

    public void setListaPoblaciones(List<SelectItem> listaPoblaciones) {
        this.listaPoblaciones = listaPoblaciones;
    }

    public List<SelectItem> getListaColonias() {
        return listaColonias;
    }

    public void setListaColonias(List<SelectItem> listaColonias) {
        this.listaColonias = listaColonias;
    }

    public List<SelectItem> getListaEscolaridades() {
        return listaEscolaridades;
    }

    public void setListaEscolaridades(List<SelectItem> listaEscolaridades) {
        this.listaEscolaridades = listaEscolaridades;
    }

    public List<SelectItem> getListaComprobantesEstudios() {
        return listaComprobantesEstudios;
    }

    public void setListaComprobantesEstudios(
            List<SelectItem> listaComprobantesEstudios) {
        this.listaComprobantesEstudios = listaComprobantesEstudios;
    }

    public List<HistorialAcademicoDTO> getHistorialesAcademicos() {
        return historialesAcademicos;
    }

    public void setHistorialesAcademicos(
            List<HistorialAcademicoDTO> historialesAcademicos) {
        this.historialesAcademicos = historialesAcademicos;
    }

    public List<ExperienciaLaboralDTO> getExperienciasLaborales() {
        return experienciasLaborales;
    }

    public void setExperienciasLaborales(
            List<ExperienciaLaboralDTO> experienciasLaborales) {
        this.experienciasLaborales = experienciasLaborales;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }

    public HistorialAcademicoDTO getHistorialAcademicoSeleccionado() {
        return historialAcademicoSeleccionado;
    }

    public void setHistorialAcademicoSeleccionado(
            HistorialAcademicoDTO historialAcademicoSeleccionado) {
        this.historialAcademicoSeleccionado = historialAcademicoSeleccionado;
    }

    public ExperienciaLaboralDTO getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ExperienciaLaboralDTO experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public boolean getMostrarDomicilio() {
        return mostrarDomicilio;
    }

    public void setMostrarDomicilio(boolean mostrarDomicilio) {
        this.mostrarDomicilio = mostrarDomicilio;
    }

    public boolean getMostrarHistorialAcademico() {
        return mostrarHistorialAcademico;
    }

    public void setMostrarHistorialAcademico(boolean mostrarHistorialAcademico) {
        this.mostrarHistorialAcademico = mostrarHistorialAcademico;
    }

    public boolean getMostrarExperienciaLaboral() {
        return mostrarExperienciaLaboral;
    }

    public void setMostrarExperienciaLaboral(boolean mostrarExperienciaLaboral) {
        this.mostrarExperienciaLaboral = mostrarExperienciaLaboral;
    }

    public boolean getMostrarExpediente() {
        return mostrarExpediente;
    }

    public void setMostrarExpediente(boolean mostrarExpediente) {
        this.mostrarExpediente = mostrarExpediente;
    }

    public boolean getMostrarPanelCorrecciones() {
        return mostrarPanelCorrecciones;
    }

    public void setMostrarPanelCorrecciones(boolean mostrarPanelCorrecciones) {
        this.mostrarPanelCorrecciones = mostrarPanelCorrecciones;
    }

    public boolean getMostrarResultadoConsulta() {
        return mostrarResultadoConsulta;
    }

    public void setMostrarResultadoConsulta(boolean mostrarResultadoConsulta) {
        this.mostrarResultadoConsulta = mostrarResultadoConsulta;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<InfoEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<InfoEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }
}
