/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.FiltroDTO;
import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.empleado.ExpedienteDTO;
import mx.gob.saludtlax.rh.experiencialaboral.ExperienciaLaboralDTO;
import mx.gob.saludtlax.rh.historialacademico.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.PuestoEmpleadoDTO;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 11:15:35 10/08/2016
 */
public class ConsultaEmpleadoView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8661814882802077170L;

    private FiltroDTO filtro = new FiltroDTO();

    private List<SelectItem> listaFiltros;

    // Listas principales
    private List<InfoEmpleadoDTO> empleados = new ArrayList<>();
    private List<HistorialAcademicoDTO> listaHistorialesAcademicos = new ArrayList<>();
    private List<ExperienciaLaboralDTO> listaExperienciasLaborales = new ArrayList<>();
    private List<InformacionAdjuntoDTO> listaDocumentosExpedientes = new ArrayList<>();
    private List<BitacoraEmpleadoDTO> bitacorasMovimientos = new ArrayList<>();
    // Caracteristica individual
    private DatosGeneralesDTO datoGeneral = new DatosGeneralesDTO();
    private DomicilioDTO domicilio = new DomicilioDTO();
    private HistorialAcademicoDTO historialAcademico = new HistorialAcademicoDTO();
    private ExperienciaLaboralDTO experienciaLaboral = new ExperienciaLaboralDTO();
    private ExpedienteDTO expediente = new ExpedienteDTO();
    private PuestoEmpleadoDTO puesto = new PuestoEmpleadoDTO();
    // Renderizados consulta
    private boolean mostrarResultadoConsulta = false;
    private boolean mostrarTipoBusquedaHeader = false;
    // Renderizados principales
    private boolean mostrarDatosGenerales = false;
    private boolean mostrarDomicilio = false;
    private boolean mostrarHistorialAcademico = false;
    private boolean mostrarProfesion = false;
    private boolean mostrarEspecialidad = false;
    private boolean mostrarExperienciaLaboral = false;
    private boolean mostrarDependientesEconomicos = false;
    private boolean mostrarPuesto = false;
    private boolean mostrarBitacora;
    // Renderizados secundarios
    private Boolean mostrarMenuDetalles = false;

    private String tipoBusquedaHeader = "";
    // Items utilizados
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

    // Otros
    private Integer idEmpleado;
    private String imagenExpediente;
    private boolean mostrarActualizacionExpediente;
    private String numeroExpediente;
    private Integer idExpediente;
    private boolean mostrarAperturaExpediente;

    // Dependentes Economicos
    private List<InfoDependienteEconomicoDTO> dependientesEconomicos = new ArrayList<>();

    // Profesion
    private List<ProfesionDTO> listaProfesion = new ArrayList<>();

    // Especialidad
    private List<EspecialidadDTO> listaEspecialidad = new ArrayList<>();

    // expedientes
    private List<InformacionAdjuntoDTO> documentosExpedientes = new ArrayList<>();
    private boolean mostrarExpediente;
    private Integer idImagenExpediente;
    private boolean mostrarImagenExpediente;

    //
    private HistorialAcademicoDTO historialAcademicoSeleccionado = new HistorialAcademicoDTO();
    private boolean mostrarAdjuntoDocumentoHistorial;
    private List<InformacionAdjuntoDTO> documentosAdjuntosGradoAcademico;

    public List<SelectItem> getListaEstadosCiviles() {
        return listaEstadosCiviles;
    }

    public List<BitacoraEmpleadoDTO> getBitacorasMovimientos() {
        return bitacorasMovimientos;
    }

    public void setBitacorasMovimientos(List<BitacoraEmpleadoDTO> bitacorasMovimientos) {
        this.bitacorasMovimientos = bitacorasMovimientos;
    }

    public boolean isMostrarBitacora() {
        return mostrarBitacora;
    }

    public void setMostrarBitacora(boolean mostrarBitacora) {
        this.mostrarBitacora = mostrarBitacora;
    }

    public boolean isMostrarPuesto() {
        return mostrarPuesto;
    }

    public void setMostrarPuesto(boolean mostrarPuesto) {
        this.mostrarPuesto = mostrarPuesto;
    }

    public PuestoEmpleadoDTO getPuesto() {
        return puesto;
    }

    public void setPuesto(PuestoEmpleadoDTO puesto) {
        this.puesto = puesto;
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
     * @return the listaTiposDocumentosExpediente
     */
    public List<SelectItem> getListaTiposDocumentosExpediente() {
        return listaTiposDocumentosExpediente;
    }

    /**
     * @param listaTiposDocumentosExpediente
     *            the listaTiposDocumentosExpediente to set
     */
    public void setListaTiposDocumentosExpediente(List<SelectItem> listaTiposDocumentosExpediente) {
        this.listaTiposDocumentosExpediente = listaTiposDocumentosExpediente;
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
     * @return the listaDocumentosDependientes
     */
    public List<SelectItem> getListaDocumentosDependientes() {
        return listaDocumentosDependientes;
    }

    /**
     * @param listaDocumentosDependientes
     *            the listaDocumentosDependientes to set
     */
    public void setListaDocumentosDependientes(List<SelectItem> listaDocumentosDependientes) {
        this.listaDocumentosDependientes = listaDocumentosDependientes;
    }

    /**
     * @return the filtro
     */
    public FiltroDTO getFiltro() {
        return filtro;
    }

    /**
     * @param filtro
     *            the filtro to set
     */
    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the listaFiltros
     */
    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    /**
     * @param listaFiltros
     *            the listaFiltros to set
     */
    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
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
     * @return the tipoBusquedaHeader
     */
    public String getTipoBusquedaHeader() {
        return tipoBusquedaHeader;
    }

    /**
     * @param tipoBusquedaHeader
     *            the tipoBusquedaHeader to set
     */
    public void setTipoBusquedaHeader(String tipoBusquedaHeader) {
        this.tipoBusquedaHeader = tipoBusquedaHeader;
    }

    /**
     * @return the mostrarTipoBusquedaHeader
     */
    public boolean isMostrarTipoBusquedaHeader() {
        return mostrarTipoBusquedaHeader;
    }

    /**
     * @param mostrarTipoBusquedaHeader
     *            the mostrarTipoBusquedaHeader to set
     */
    public void setMostrarTipoBusquedaHeader(boolean mostrarTipoBusquedaHeader) {
        this.mostrarTipoBusquedaHeader = mostrarTipoBusquedaHeader;
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
     * @return the mostrarProfesion
     */
    public boolean isMostrarProfesion() {
        return mostrarProfesion;
    }

    /**
     * @param mostrarProfesion
     *            the mostrarProfesion to set
     */
    public void setMostrarProfesion(boolean mostrarProfesion) {
        this.mostrarProfesion = mostrarProfesion;
    }

    /**
     * @return the mostrarEspecialidad
     */
    public boolean isMostrarEspecialidad() {
        return mostrarEspecialidad;
    }

    /**
     * @param mostrarEspecialidad
     *            the mostrarEspecialidad to set
     */
    public void setMostrarEspecialidad(boolean mostrarEspecialidad) {
        this.mostrarEspecialidad = mostrarEspecialidad;
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
     * @return the mostrarDependientesEconomicos
     */
    public boolean isMostrarDependientesEconomicos() {
        return mostrarDependientesEconomicos;
    }

    /**
     * @param mostrarDependientesEconomicos
     *            the mostrarDependientesEconomicos to set
     */
    public void setMostrarDependientesEconomicos(boolean mostrarDependientesEconomicos) {
        this.mostrarDependientesEconomicos = mostrarDependientesEconomicos;
    }

    /**
     * @return the mostrarMenuDetalles
     */
    public Boolean getMostrarMenuDetalles() {
        return mostrarMenuDetalles;
    }

    /**
     * @param mostrarMenuDetalles
     *            the mostrarMenuDetalles to set
     */
    public void setMostrarMenuDetalles(Boolean mostrarMenuDetalles) {
        this.mostrarMenuDetalles = mostrarMenuDetalles;
    }

    /**
     * @return the listaHistorialesAcademicos
     */
    public List<HistorialAcademicoDTO> getListaHistorialesAcademicos() {
        return listaHistorialesAcademicos;
    }

    /**
     * @param listaHistorialesAcademicos
     *            the listaHistorialesAcademicos to set
     */
    public void setListaHistorialesAcademicos(List<HistorialAcademicoDTO> listaHistorialesAcademicos) {
        this.listaHistorialesAcademicos = listaHistorialesAcademicos;
    }

    /**
     * @return the listaExperienciasLaborales
     */
    public List<ExperienciaLaboralDTO> getListaExperienciasLaborales() {
        return listaExperienciasLaborales;
    }

    /**
     * @param listaExperienciasLaborales
     *            the listaExperienciasLaborales to set
     */
    public void setListaExperienciasLaborales(List<ExperienciaLaboralDTO> listaExperienciasLaborales) {
        this.listaExperienciasLaborales = listaExperienciasLaborales;
    }

    /**
     * @return the listaDocumentosExpedientes
     */
    public List<InformacionAdjuntoDTO> getListaDocumentosExpedientes() {
        return listaDocumentosExpedientes;
    }

    /**
     * @param listaDocumentosExpedientes
     *            the listaDocumentosExpedientes to set
     */
    public void setListaDocumentosExpedientes(List<InformacionAdjuntoDTO> listaDocumentosExpedientes) {
        this.listaDocumentosExpedientes = listaDocumentosExpedientes;
    }

    /**
     * @return the datoGeneral
     */
    public DatosGeneralesDTO getDatoGeneral() {
        return datoGeneral;
    }

    /**
     * @param datoGeneral
     *            the datoGeneral to set
     */
    public void setDatoGeneral(DatosGeneralesDTO datoGeneral) {
        this.datoGeneral = datoGeneral;
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
     * @return the historialAcademico
     */
    public HistorialAcademicoDTO getHistorialAcademico() {
        return historialAcademico;
    }

    /**
     * @param historialAcademico
     *            the historialAcademico to set
     */
    public void setHistorialAcademico(HistorialAcademicoDTO historialAcademico) {
        this.historialAcademico = historialAcademico;
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
     * @return the expediente
     */
    public ExpedienteDTO getExpediente() {
        return expediente;
    }

    /**
     * @param expediente
     *            the expediente to set
     */
    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }

    /**
     * @return the idEmpleado
     */
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado
     *            the idEmpleado to set
     */
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public List<InfoDependienteEconomicoDTO> getDependientesEconomicos() {
        return dependientesEconomicos;
    }

    public void setDependientesEconomicos(List<InfoDependienteEconomicoDTO> dependientesEconomicos) {
        this.dependientesEconomicos = dependientesEconomicos;
    }

    /**
     * @return the listaProfesion
     */
    public List<ProfesionDTO> getListaProfesion() {
        return listaProfesion;
    }

    /**
     * @param listaProfesion
     *            the listaProfesion to set
     */
    public void setListaProfesion(List<ProfesionDTO> listaProfesion) {
        this.listaProfesion = listaProfesion;
    }

    /**
     * @return the listaEspecialidad
     */
    public List<EspecialidadDTO> getListaEspecialidad() {
        return listaEspecialidad;
    }

    /**
     * @param listaEspecialidad
     *            the listaEspecialidad to set
     */
    public void setListaEspecialidad(List<EspecialidadDTO> listaEspecialidad) {
        this.listaEspecialidad = listaEspecialidad;
    }

    /**
     * @return the documentosExpedientes
     */
    public List<InformacionAdjuntoDTO> getDocumentosExpedientes() {
        return documentosExpedientes;
    }

    /**
     * @param documentosExpedientes
     *            the documentosExpedientes to set
     */
    public void setDocumentosExpedientes(List<InformacionAdjuntoDTO> documentosExpedientes) {
        this.documentosExpedientes = documentosExpedientes;
    }

    /**
     * @return the mostrarExpediente
     */
    public boolean isMostrarExpediente() {
        return mostrarExpediente;
    }

    /**
     * @param mostrarExpediente
     *            the mostrarExpediente to set
     */
    public void setMostrarExpediente(boolean mostrarExpediente) {
        this.mostrarExpediente = mostrarExpediente;
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

}
