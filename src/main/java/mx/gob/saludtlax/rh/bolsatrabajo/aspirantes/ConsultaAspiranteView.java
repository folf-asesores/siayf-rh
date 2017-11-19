
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 01/08/2016 16:47:20
 *
 */
public class ConsultaAspiranteView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 57942766468629842L;

    private List<InfoAspiranteDTO> listaInfoAspirante = new ArrayList<>();

    private FiltroDTO filtro = new FiltroDTO();

    private List<SelectItem> listaFiltros;

    private String tipoBusquedaHeader = "";

    private Integer idAspirante;

    private boolean mostrarTipoBusquedaHeader = false;

    private boolean mostrarResultados = false;

    private Boolean mostrarDetallesAspirante = false;

    // Renderizados principales
    private boolean mostrarDatosPersonales;
    private boolean mostrarHistorialAcademico;
    private boolean mostrarProfesion;
    private boolean mostrarEspecialidad;
    private boolean mostrarExperienciaLaboral;
    private boolean mostrarHabilidadPersonal;

    // Datos principales
    private AspiranteDTO aspirante = new AspiranteDTO();

    private List<SelectItem> listaTiposSangre;
    private List<SelectItem> listaNacionalidades;
    private List<SelectItem> listaEstadosCiviles;
    private List<SelectItem> listaTiposSexo;
    private List<SelectItem> listaPaises;
    private List<SelectItem> listaEstados;
    private List<SelectItem> listaMunicipios;
    private List<SelectItem> listaAsentamientos = new ArrayList<>();
    private List<SelectItem> listaColonias = new ArrayList<>();
    private List<SelectItem> listaPuestos;
    private List<SelectItem> listaDepartamentos;
    private List<SelectItem> listaViveCon;
    private List<SelectItem> listaDependientes;
    private List<SelectItem> listaTiposLicencia;

    private boolean tieneConyuge = false;

    private List<String> personasDependientes;

    // Historial Academico
    private List<HistorialAcademicoDTO> listaHistorialAcademico = new ArrayList<>();

    private HistorialAcademicoDTO historialAcademicoDTO = new HistorialAcademicoDTO();

    private boolean mostrarHistorialAcedemicoSeleccionado = false;

    private List<SelectItem> listaEscolaridades;
    private List<SelectItem> listaComprobantesEstudio;

    // Profesion
    private List<ProfesionDTO> listaProfesion = new ArrayList<>();

    // Especialidad
    private List<EspecialidadDTO> listaEspecialidad = new ArrayList<>();

    // Experiencia Laboral
    private List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral = new ArrayList<>();

    private ExperienciaLaboralAspiranteDTO experienciaLaboral = new ExperienciaLaboralAspiranteDTO();

    private boolean mostrarExperienciaLaboralSeleccionado = false;

    //Habilidad Personal
    private HabilidadesPersonalesAspiranteDTO encuestaPersonal = new HabilidadesPersonalesAspiranteDTO();

    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
    }

    public boolean isMostrarResultados() {
        return mostrarResultados;
    }

    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }

    public FiltroDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the listaInfoAspirante
     */
    public List<InfoAspiranteDTO> getListaInfoAspirante() {
        return listaInfoAspirante;
    }

    /**
     * @param listaInfoAspirante
     *            the listaInfoAspirante to set
     */
    public void setListaInfoAspirante(
            List<InfoAspiranteDTO> listaInfoAspirante) {
        this.listaInfoAspirante = listaInfoAspirante;
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
    public void setMostrarTipoBusquedaHeader(
            boolean mostrarTipoBusquedaHeader) {
        this.mostrarTipoBusquedaHeader = mostrarTipoBusquedaHeader;
    }

    /**
     * @return the mostrarDetallesAspirante
     */
    public Boolean getMostrarDetallesAspirante() {
        return mostrarDetallesAspirante;
    }

    /**
     * @param mostrarDetallesAspirante
     *            the mostrarDetallesAspirante to set
     */
    public void setMostrarDetallesAspirante(Boolean mostrarDetallesAspirante) {
        this.mostrarDetallesAspirante = mostrarDetallesAspirante;
    }

    /**
     * @return the mostrarDatosPersonales
     */
    public boolean isMostrarDatosPersonales() {
        return mostrarDatosPersonales;
    }

    /**
     * @param mostrarDatosPersonales
     *            the mostrarDatosPersonales to set
     */
    public void setMostrarDatosPersonales(boolean mostrarDatosPersonales) {
        this.mostrarDatosPersonales = mostrarDatosPersonales;
    }

    /**
     * @return the aspirante
     */
    public AspiranteDTO getAspirante() {
        return aspirante;
    }

    /**
     * @param aspirante
     *            the aspirante to set
     */
    public void setAspirante(AspiranteDTO aspirante) {
        this.aspirante = aspirante;
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
     * @return the listaNacionalidades
     */
    public List<SelectItem> getListaNacionalidades() {
        return listaNacionalidades;
    }

    /**
     * @param listaNacionalidades
     *            the listaNacionalidades to set
     */
    public void setListaNacionalidades(List<SelectItem> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

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
     * @return the listaTiposSexo
     */
    public List<SelectItem> getListaTiposSexo() {
        return listaTiposSexo;
    }

    /**
     * @param listaTiposSexo
     *            the listaTiposSexo to set
     */
    public void setListaTiposSexo(List<SelectItem> listaTiposSexo) {
        this.listaTiposSexo = listaTiposSexo;
    }

    /**
     * @return the listaPaises
     */
    public List<SelectItem> getListaPaises() {
        return listaPaises;
    }

    /**
     * @param listaPaises
     *            the listaPaises to set
     */
    public void setListaPaises(List<SelectItem> listaPaises) {
        this.listaPaises = listaPaises;
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
     * @return the listaMunicipios
     */
    public List<SelectItem> getListaMunicipios() {
        return listaMunicipios;
    }

    /**
     * @param listaMunicipios
     *            the listaMunicipios to set
     */
    public void setListaMunicipios(List<SelectItem> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    /**
     * @return the listaAsentamientos
     */
    public List<SelectItem> getListaAsentamientos() {
        return listaAsentamientos;
    }

    /**
     * @param listaAsentamientos
     *            the listaAsentamientos to set
     */
    public void setListaAsentamientos(List<SelectItem> listaAsentamientos) {
        this.listaAsentamientos = listaAsentamientos;
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
     * @return the listaPuestos
     */
    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    /**
     * @param listaPuestos
     *            the listaPuestos to set
     */
    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    /**
     * @return the listaDepartamentos
     */
    public List<SelectItem> getListaDepartamentos() {
        return listaDepartamentos;
    }

    /**
     * @param listaDepartamentos
     *            the listaDepartamentos to set
     */
    public void setListaDepartamentos(List<SelectItem> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    /**
     * @return the listaViveCon
     */
    public List<SelectItem> getListaViveCon() {
        return listaViveCon;
    }

    /**
     * @param listaViveCon
     *            the listaViveCon to set
     */
    public void setListaViveCon(List<SelectItem> listaViveCon) {
        this.listaViveCon = listaViveCon;
    }

    /**
     * @return the listaDependientes
     */
    public List<SelectItem> getListaDependientes() {
        return listaDependientes;
    }

    /**
     * @param listaDependientes
     *            the listaDependientes to set
     */
    public void setListaDependientes(List<SelectItem> listaDependientes) {
        this.listaDependientes = listaDependientes;
    }

    /**
     * @return the listaTiposLicencia
     */
    public List<SelectItem> getListaTiposLicencia() {
        return listaTiposLicencia;
    }

    /**
     * @param listaTiposLicencia
     *            the listaTiposLicencia to set
     */
    public void setListaTiposLicencia(List<SelectItem> listaTiposLicencia) {
        this.listaTiposLicencia = listaTiposLicencia;
    }

    /**
     * @return the tieneConyuge
     */
    public boolean isTieneConyuge() {
        return tieneConyuge;
    }

    /**
     * @param tieneConyuge
     *            the tieneConyuge to set
     */
    public void setTieneConyuge(boolean tieneConyuge) {
        this.tieneConyuge = tieneConyuge;
    }

    /**
     * @return the personasDependientes
     */
    public List<String> getPersonasDependientes() {
        return personasDependientes;
    }

    /**
     * @param personasDependientes
     *            the personasDependientes to set
     */
    public void setPersonasDependientes(List<String> personasDependientes) {
        this.personasDependientes = personasDependientes;
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
    public void setMostrarHistorialAcademico(
            boolean mostrarHistorialAcademico) {
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
    public void setMostrarExperienciaLaboral(
            boolean mostrarExperienciaLaboral) {
        this.mostrarExperienciaLaboral = mostrarExperienciaLaboral;
    }

    /**
     * @return the mostrarHabilidadPersonal
     */
    public boolean isMostrarHabilidadPersonal() {
        return mostrarHabilidadPersonal;
    }

    /**
     * @param mostrarHabilidadPersonal
     *            the mostrarHabilidadPersonal to set
     */
    public void setMostrarHabilidadPersonal(boolean mostrarHabilidadPersonal) {
        this.mostrarHabilidadPersonal = mostrarHabilidadPersonal;
    }

    /**
     * @return the listaHistorialAcademico
     */
    public List<HistorialAcademicoDTO> getListaHistorialAcademico() {
        return listaHistorialAcademico;
    }

    /**
     * @param listaHistorialAcademico
     *            the listaHistorialAcademico to set
     */
    public void setListaHistorialAcademico(
            List<HistorialAcademicoDTO> listaHistorialAcademico) {
        this.listaHistorialAcademico = listaHistorialAcademico;
    }

    /**
     * @return the historialAcademicoDTO
     */
    public HistorialAcademicoDTO getHistorialAcademicoDTO() {
        return historialAcademicoDTO;
    }

    /**
     * @param historialAcademicoDTO
     *            the historialAcademicoDTO to set
     */
    public void setHistorialAcademicoDTO(
            HistorialAcademicoDTO historialAcademicoDTO) {
        this.historialAcademicoDTO = historialAcademicoDTO;
    }

    /**
     * @return the mostrarHistorialAcedemicoSeleccionado
     */
    public boolean isMostrarHistorialAcedemicoSeleccionado() {
        return mostrarHistorialAcedemicoSeleccionado;
    }

    /**
     * @param mostrarHistorialAcedemicoSeleccionado
     *            the mostrarHistorialAcedemicoSeleccionado to set
     */
    public void setMostrarHistorialAcedemicoSeleccionado(
            boolean mostrarHistorialAcedemicoSeleccionado) {
        this.mostrarHistorialAcedemicoSeleccionado = mostrarHistorialAcedemicoSeleccionado;
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
     * @return the idAspirante
     */
    public Integer getIdAspirante() {
        return idAspirante;
    }

    /**
     * @param idAspirante
     *            the idAspirante to set
     */
    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    /**
     * @return the listaComprobantesEstudio
     */
    public List<SelectItem> getListaComprobantesEstudio() {
        return listaComprobantesEstudio;
    }

    /**
     * @param listaComprobantesEstudio
     *            the listaComprobantesEstudio to set
     */
    public void setListaComprobantesEstudio(
            List<SelectItem> listaComprobantesEstudio) {
        this.listaComprobantesEstudio = listaComprobantesEstudio;
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
     * @return the listaExperienciaLaboral
     */
    public List<ExperienciaLaboralAspiranteDTO> getListaExperienciaLaboral() {
        return listaExperienciaLaboral;
    }

    /**
     * @param listaExperienciaLaboral
     *            the listaExperienciaLaboral to set
     */
    public void setListaExperienciaLaboral(
            List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral) {
        this.listaExperienciaLaboral = listaExperienciaLaboral;
    }

    /**
     * @return the experienciaLaboral
     */
    public ExperienciaLaboralAspiranteDTO getExperienciaLaboral() {
        return experienciaLaboral;
    }

    /**
     * @param experienciaLaboral
     *            the experienciaLaboral to set
     */
    public void setExperienciaLaboral(
            ExperienciaLaboralAspiranteDTO experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    /**
     * @return the mostrarExperienciaLaboralSeleccionado
     */
    public boolean isMostrarExperienciaLaboralSeleccionado() {
        return mostrarExperienciaLaboralSeleccionado;
    }

    /**
     * @param mostrarExperienciaLaboralSeleccionado
     *            the mostrarExperienciaLaboralSeleccionado to set
     */
    public void setMostrarExperienciaLaboralSeleccionado(
            boolean mostrarExperienciaLaboralSeleccionado) {
        this.mostrarExperienciaLaboralSeleccionado = mostrarExperienciaLaboralSeleccionado;
    }

    /**
     * @return the encuestaPersonal
     */
    public HabilidadesPersonalesAspiranteDTO getEncuestaPersonal() {
        return encuestaPersonal;
    }

    /**
     * @param encuestaPersonal
     *            the encuestaPersonal to set
     */
    public void setEncuestaPersonal(
            HabilidadesPersonalesAspiranteDTO encuestaPersonal) {
        this.encuestaPersonal = encuestaPersonal;
    }

}
