/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 12/05/2016 17:28:24
 */
public class ActualizacionAspiranteView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9032360619696478851L;

    // Datos Del Aspirante
    private AspiranteDTO actualizacionAspirante = new AspiranteDTO();
    private HistorialAcademicoDTO HistorialAcademico = new HistorialAcademicoDTO();
    private ExperienciaLaboralAspiranteDTO actualizacionExperienciaLaboral = new ExperienciaLaboralAspiranteDTO();
    private HabilidadesPersonalesAspiranteDTO habilidadesPersonales = new HabilidadesPersonalesAspiranteDTO();

    private List<HistorialAcademicoDTO> listaHistorialAcademicoAspirante = new ArrayList<>();
    private List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboralAspirante = new ArrayList<>();

    private List<InfoAspiranteDTO> listaInfoAspirante = new ArrayList<>();
    private boolean mostrarResultados = false;
    private String tipoBusquedaHeader = "";
    private boolean mostrarTipoBusquedaHeader = false;
    private FiltroDTO filtro = new FiltroDTO();
    private List<SelectItem> listaFiltros;

    private Integer idAspirante;
    private Integer idHistorialCademico;
    private Integer idExperienciaLaboral;

    private String accionHistorialAcademico = "Registrar";
    private String accionExperienciaLaboral = "Registrar";
    private String accionEncuestaPersonal = "Registrar";

    private List<String> personasDependientes;
    // Paneles Primarios
    private Boolean mostrarPanelConsultaAspirante = Boolean.TRUE;
    private Boolean mostrarPanelDatosAspirante = Boolean.FALSE;
    // Paneles secundarios
    private boolean mostrarDatosPersonales;
    private boolean mostrarHistorialAcademico;
    private boolean mostrarExperienciaLaboral;
    private boolean mostrarEscuestaPersonal;
    private boolean mostrarAdjuntarDocumento;
    // Experiencia laboral
    private boolean mostrarRazonNosolicitar = true;
    private boolean mostrarFormularioExperiencia;
    // Encuesta personal
    private boolean mostrarTxtOtroMedio = true;
    private boolean mostrarTxtNombresParientes = true;
    private boolean mostrarTxtNombreAfianza = true;
    private boolean mostrarTxtNombreSindicato = true;
    private boolean mostrarTxtNombreSegurVida = true;
    private boolean mostrarTxtRazonNoViajar = true;
    private boolean mostrarTxtRazonNoCambioResidencia = true;
    private boolean mostrarTxtOtrosIngresos = true;
    private boolean mostrarTxtConyugeTrabaja = true;

    private boolean mostrarTxtCasaPropia = true;
    private boolean mostrarTxtPagaRenta = true;
    private boolean mostrarTxtAutomovilPropio = true;
    private boolean mostrarTxtTieneDeudas = true;
    private Boolean mostrarDialogEliminar = Boolean.TRUE;
    // Personas Dependientes
    private boolean habilitarTxtHijos = true;
    private boolean habilitarTxtConyuge = true;
    private boolean tieneConyuge = false;
    private boolean habilitarTxtPadres = true;
    private boolean habilitarTxtOtros = true;
    // SelectItems
    private List<SelectItem> listaTiposSangre;
    private List<SelectItem> listaNacionalidades;
    private List<SelectItem> listaEstadosCiviles;
    private List<SelectItem> listaTiposSexo;
    private List<SelectItem> listaPaises;
    private List<SelectItem> listaEstados;
    private List<SelectItem> listaMunicipios;
    private List<SelectItem> listaAsentamientos = new ArrayList<>();
    private List<SelectItem> listaPuestos;
    private List<SelectItem> listaDepartamentos;
    private List<SelectItem> listaViveCon;
    private List<SelectItem> listaDependientes;
    private List<SelectItem> listaTiposLicencia;

    private List<SelectItem> listaEscolaridades;
    private List<SelectItem> listaEstatusEscolaridades;

    // historial academico
    private boolean mostrarPanelHistorialAcedemico = false;
    private List<SelectItem> listaComprobantesEstudio;

    // Profesion
    private List<ProfesionDTO> listaProfesion = new ArrayList<>();
    private ProfesionDTO profesionDTO = new ProfesionDTO();
    private boolean mostrarProfesion;
    private List<SelectItem> listaTipoProfesion;
    private Integer idProfesion;
    private Integer idProfesionAspirante;
    private String accionProfesion = "Registrar";
    private boolean mostrarFormularioPofesion;

    // Especialidad
    private List<EspecialidadDTO> listaEspecialidad = new ArrayList<>();
    private EspecialidadDTO especialidadDTO = new EspecialidadDTO();
    private boolean mostrarEspecialidad;
    private List<SelectItem> listaTipoEspecialidad;
    private Integer idEspecialidad;
    private Integer idEspecialidadAspirante;
    private String accionEspecialidad = "Registrar";
    private boolean mostrarFormularioEspecialidad;

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
     * @return the listaTipoProfesion
     */
    public List<SelectItem> getListaTipoProfesion() {
        return listaTipoProfesion;
    }

    /**
     * @param listaTipoProfesion
     *            the listaTipoProfesion to set
     */
    public void setListaTipoProfesion(List<SelectItem> listaTipoProfesion) {
        this.listaTipoProfesion = listaTipoProfesion;
    }

    /**
     * @return the idProfesion
     */
    public Integer getIdProfesion() {
        return idProfesion;
    }

    /**
     * @param idProfesion
     *            the idProfesion to set
     */
    public void setIdProfesion(Integer idProfesion) {
        this.idProfesion = idProfesion;
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
     * @return the especialidadDTO
     */
    public EspecialidadDTO getEspecialidadDTO() {
        return especialidadDTO;
    }

    /**
     * @param especialidadDTO
     *            the especialidadDTO to set
     */
    public void setEspecialidadDTO(EspecialidadDTO especialidadDTO) {
        this.especialidadDTO = especialidadDTO;
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
     * @return the listaTipoEspecialidad
     */
    public List<SelectItem> getListaTipoEspecialidad() {
        return listaTipoEspecialidad;
    }

    /**
     * @param listaTipoEspecialidad
     *            the listaTipoEspecialidad to set
     */
    public void setListaTipoEspecialidad(List<SelectItem> listaTipoEspecialidad) {
        this.listaTipoEspecialidad = listaTipoEspecialidad;
    }

    /**
     * @return the idEspecialidad
     */
    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    /**
     * @param idEspecialidad
     *            the idEspecialidad to set
     */
    public void setIdEspecialidad(Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    /**
     * @return the idEspecialidadAspirante
     */
    public Integer getIdEspecialidadAspirante() {
        return idEspecialidadAspirante;
    }

    /**
     * @param idEspecialidadAspirante
     *            the idEspecialidadAspirante to set
     */
    public void setIdEspecialidadAspirante(Integer idEspecialidadAspirante) {
        this.idEspecialidadAspirante = idEspecialidadAspirante;
    }

    /**
     * @return the accionEspecialidad
     */
    public String getAccionEspecialidad() {
        return accionEspecialidad;
    }

    /**
     * @param accionEspecialidad
     *            the accionEspecialidad to set
     */
    public void setAccionEspecialidad(String accionEspecialidad) {
        this.accionEspecialidad = accionEspecialidad;
    }

    /**
     * @return the mostrarFormularioEspecialidad
     */
    public boolean isMostrarFormularioEspecialidad() {
        return mostrarFormularioEspecialidad;
    }

    /**
     * @param mostrarFormularioEspecialidad
     *            the mostrarFormularioEspecialidad to set
     */
    public void setMostrarFormularioEspecialidad(boolean mostrarFormularioEspecialidad) {
        this.mostrarFormularioEspecialidad = mostrarFormularioEspecialidad;
    }

    public List<SelectItem> getListaTiposSangre() {
        return listaTiposSangre;
    }

    public void setListaTiposSangre(List<SelectItem> listaTiposSangre) {
        this.listaTiposSangre = listaTiposSangre;
    }

    public List<SelectItem> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<SelectItem> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }

    public List<SelectItem> getListaEstadosCiviles() {
        return listaEstadosCiviles;
    }

    public void setListaEstadosCiviles(List<SelectItem> listaEstadosCiviles) {
        this.listaEstadosCiviles = listaEstadosCiviles;
    }

    public List<SelectItem> getListaTiposSexo() {
        return listaTiposSexo;
    }

    public void setListaTiposSexo(List<SelectItem> listaTiposSexo) {
        this.listaTiposSexo = listaTiposSexo;
    }

    public List<SelectItem> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<SelectItem> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public List<SelectItem> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<SelectItem> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<SelectItem> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<SelectItem> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List<SelectItem> getListaPuestos() {
        return listaPuestos;
    }

    public void setListaPuestos(List<SelectItem> listaPuestos) {
        this.listaPuestos = listaPuestos;
    }

    public List<SelectItem> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<SelectItem> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<SelectItem> getListaViveCon() {
        return listaViveCon;
    }

    public void setListaViveCon(List<SelectItem> listaViveCon) {
        this.listaViveCon = listaViveCon;
    }

    public List<SelectItem> getListaDependientes() {
        return listaDependientes;
    }

    public void setListaDependientes(List<SelectItem> listaDependientes) {
        this.listaDependientes = listaDependientes;
    }

    public List<SelectItem> getListaTiposLicencia() {
        return listaTiposLicencia;
    }

    public void setListaTiposLicencia(List<SelectItem> listaTiposLicencia) {
        this.listaTiposLicencia = listaTiposLicencia;
    }

    public List<SelectItem> getListaEscolaridades() {
        return listaEscolaridades;
    }

    public void setListaEscolaridades(List<SelectItem> listaEscolaridades) {
        this.listaEscolaridades = listaEscolaridades;
    }

    public List<SelectItem> getListaEstatusEscolaridades() {
        return listaEstatusEscolaridades;
    }

    public void setListaEstatusEscolaridades(List<SelectItem> listaEstatusEscolaridades) {
        this.listaEstatusEscolaridades = listaEstatusEscolaridades;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Boolean getMostrarPanelDatosAspirante() {
        return mostrarPanelDatosAspirante;
    }

    public void setMostrarPanelDatosAspirante(Boolean mostrarPanelDatosAspirante) {
        this.mostrarPanelDatosAspirante = mostrarPanelDatosAspirante;
    }

    public Boolean getMostrarPanelConsultaAspirante() {
        return mostrarPanelConsultaAspirante;
    }

    public void setMostrarPanelConsultaAspirante(Boolean mostrarPanelConsultaAspirante) {
        this.mostrarPanelConsultaAspirante = mostrarPanelConsultaAspirante;
    }

    public boolean isMostrarDatosPersonales() {
        return mostrarDatosPersonales;
    }

    public void setMostrarDatosPersonales(boolean mostrarDatosPersonales) {
        this.mostrarDatosPersonales = mostrarDatosPersonales;
    }

    public boolean isMostrarHistorialAcademico() {
        return mostrarHistorialAcademico;
    }

    public void setMostrarHistorialAcademico(boolean mostrarHistorialAcademico) {
        this.mostrarHistorialAcademico = mostrarHistorialAcademico;
    }

    public boolean isMostrarExperienciaLaboral() {
        return mostrarExperienciaLaboral;
    }

    public void setMostrarExperienciaLaboral(boolean mostrarExperienciaLaboral) {
        this.mostrarExperienciaLaboral = mostrarExperienciaLaboral;
    }

    public boolean isMostrarEscuestaPersonal() {
        return mostrarEscuestaPersonal;
    }

    public void setMostrarEscuestaPersonal(boolean mostrarEscuestaPersonal) {
        this.mostrarEscuestaPersonal = mostrarEscuestaPersonal;
    }

    public boolean isMostrarAdjuntarDocumento() {
        return mostrarAdjuntarDocumento;
    }

    public void setMostrarAdjuntarDocumento(boolean mostrarAdjuntarDocumento) {
        this.mostrarAdjuntarDocumento = mostrarAdjuntarDocumento;
    }

    /**
     * @return the actualizacionAspirante
     */
    public AspiranteDTO getActualizacionAspirante() {
        return actualizacionAspirante;
    }

    /**
     * @param actualizacionAspirante
     *            the actualizacionAspirante to set
     */
    public void setActualizacionAspirante(AspiranteDTO actualizacionAspirante) {
        this.actualizacionAspirante = actualizacionAspirante;
    }

    /**
     * @return the listaHistorialAcademicoAspirante
     */
    public List<HistorialAcademicoDTO> getListaHistorialAcademicoAspirante() {
        return listaHistorialAcademicoAspirante;
    }

    /**
     * @param listaHistorialAcademicoAspirante
     *            the listaHistorialAcademicoAspirante to set
     */
    public void setListaHistorialAcademicoAspirante(List<HistorialAcademicoDTO> listaHistorialAcademicoAspirante) {
        this.listaHistorialAcademicoAspirante = listaHistorialAcademicoAspirante;
    }

    /**
     * @return the listaExperienciaLaboralAspirante
     */
    public List<ExperienciaLaboralAspiranteDTO> getListaExperienciaLaboralAspirante() {
        return listaExperienciaLaboralAspirante;
    }

    /**
     * @param listaExperienciaLaboralAspirante
     *            the listaExperienciaLaboralAspirante to set
     */
    public void setListaExperienciaLaboralAspirante(List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboralAspirante) {
        this.listaExperienciaLaboralAspirante = listaExperienciaLaboralAspirante;
    }

    /**
     * @return the accionHistorialAcademico
     */
    public String getAccionHistorialAcademico() {
        return accionHistorialAcademico;
    }

    /**
     * @param accionHistorialAcademico
     *            the accionHistorialAcademico to set
     */
    public void setAccionHistorialAcademico(String accionHistorialAcademico) {
        this.accionHistorialAcademico = accionHistorialAcademico;
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
     * @return the actualizacionExperienciaLaboral
     */
    public ExperienciaLaboralAspiranteDTO getActualizacionExperienciaLaboral() {
        return actualizacionExperienciaLaboral;
    }

    /**
     * @param actualizacionExperienciaLaboral
     *            the actualizacionExperienciaLaboral to set
     */
    public void setActualizacionExperienciaLaboral(ExperienciaLaboralAspiranteDTO actualizacionExperienciaLaboral) {
        this.actualizacionExperienciaLaboral = actualizacionExperienciaLaboral;
    }

    /**
     * @return the accionExperienciaLaboral
     */
    public String getAccionExperienciaLaboral() {
        return accionExperienciaLaboral;
    }

    /**
     * @param accionExperienciaLaboral
     *            the accionExperienciaLaboral to set
     */
    public void setAccionExperienciaLaboral(String accionExperienciaLaboral) {
        this.accionExperienciaLaboral = accionExperienciaLaboral;
    }

    /**
     * @return the mostrarRazonNosolicitar
     */
    public boolean isMostrarRazonNosolicitar() {
        return mostrarRazonNosolicitar;
    }

    /**
     * @param mostrarRazonNosolicitar
     *            the mostrarRazonNosolicitar to set
     */
    public void setMostrarRazonNosolicitar(boolean mostrarRazonNosolicitar) {
        this.mostrarRazonNosolicitar = mostrarRazonNosolicitar;
    }

    public boolean isMostrarTxtOtroMedio() {
        return mostrarTxtOtroMedio;
    }

    public void setMostrarTxtOtroMedio(boolean mostrarTxtOtroMedio) {
        this.mostrarTxtOtroMedio = mostrarTxtOtroMedio;
    }

    public boolean isMostrarTxtNombresParientes() {
        return mostrarTxtNombresParientes;
    }

    public void setMostrarTxtNombresParientes(boolean mostrarTxtNombresParientes) {
        this.mostrarTxtNombresParientes = mostrarTxtNombresParientes;
    }

    public boolean isMostrarTxtNombreAfianza() {
        return mostrarTxtNombreAfianza;
    }

    public void setMostrarTxtNombreAfianza(boolean mostrarTxtNombreAfianza) {
        this.mostrarTxtNombreAfianza = mostrarTxtNombreAfianza;
    }

    public boolean isMostrarTxtNombreSindicato() {
        return mostrarTxtNombreSindicato;
    }

    public void setMostrarTxtNombreSindicato(boolean mostrarTxtNombreSindicato) {
        this.mostrarTxtNombreSindicato = mostrarTxtNombreSindicato;
    }

    public boolean isMostrarTxtNombreSegurVida() {
        return mostrarTxtNombreSegurVida;
    }

    public void setMostrarTxtNombreSegurVida(boolean mostrarTxtNombreSegurVida) {
        this.mostrarTxtNombreSegurVida = mostrarTxtNombreSegurVida;
    }

    public boolean isMostrarTxtRazonNoViajar() {
        return mostrarTxtRazonNoViajar;
    }

    public void setMostrarTxtRazonNoViajar(boolean mostrarTxtRazonNoViajar) {
        this.mostrarTxtRazonNoViajar = mostrarTxtRazonNoViajar;
    }

    public boolean isMostrarTxtRazonNoCambioResidencia() {
        return mostrarTxtRazonNoCambioResidencia;
    }

    public void setMostrarTxtRazonNoCambioResidencia(boolean mostrarTxtRazonNoCambioResidencia) {
        this.mostrarTxtRazonNoCambioResidencia = mostrarTxtRazonNoCambioResidencia;
    }

    public boolean isMostrarTxtOtrosIngresos() {
        return mostrarTxtOtrosIngresos;
    }

    public void setMostrarTxtOtrosIngresos(boolean mostrarTxtOtrosIngresos) {
        this.mostrarTxtOtrosIngresos = mostrarTxtOtrosIngresos;
    }

    public boolean isMostrarTxtConyugeTrabaja() {
        return mostrarTxtConyugeTrabaja;
    }

    public void setMostrarTxtConyugeTrabaja(boolean mostrarTxtConyugeTrabaja) {
        this.mostrarTxtConyugeTrabaja = mostrarTxtConyugeTrabaja;
    }

    public boolean isMostrarTxtCasaPropia() {
        return mostrarTxtCasaPropia;
    }

    public void setMostrarTxtCasaPropia(boolean mostrarTxtCasaPropia) {
        this.mostrarTxtCasaPropia = mostrarTxtCasaPropia;
    }

    public boolean isMostrarTxtPagaRenta() {
        return mostrarTxtPagaRenta;
    }

    public void setMostrarTxtPagaRenta(boolean mostrarTxtPagaRenta) {
        this.mostrarTxtPagaRenta = mostrarTxtPagaRenta;
    }

    public boolean isMostrarTxtAutomovilPropio() {
        return mostrarTxtAutomovilPropio;
    }

    public void setMostrarTxtAutomovilPropio(boolean mostrarTxtAutomovilPropio) {
        this.mostrarTxtAutomovilPropio = mostrarTxtAutomovilPropio;
    }

    public boolean isMostrarTxtTieneDeudas() {
        return mostrarTxtTieneDeudas;
    }

    public void setMostrarTxtTieneDeudas(boolean mostrarTxtTieneDeudas) {
        this.mostrarTxtTieneDeudas = mostrarTxtTieneDeudas;
    }

    public Boolean getMostrarDialogEliminar() {
        return mostrarDialogEliminar;
    }

    public void setMostrarDialogEliminar(Boolean mostrarDialogEliminar) {
        this.mostrarDialogEliminar = mostrarDialogEliminar;
    }

    public boolean isHabilitarTxtHijos() {
        return habilitarTxtHijos;
    }

    public void setHabilitarTxtHijos(boolean habilitarTxtHijos) {
        this.habilitarTxtHijos = habilitarTxtHijos;
    }

    public boolean isHabilitarTxtConyuge() {
        return habilitarTxtConyuge;
    }

    public void setHabilitarTxtConyuge(boolean habilitarTxtConyuge) {
        this.habilitarTxtConyuge = habilitarTxtConyuge;
    }

    public boolean isTieneConyuge() {
        return tieneConyuge;
    }

    public void setTieneConyuge(boolean tieneConyuge) {
        this.tieneConyuge = tieneConyuge;
    }

    public boolean isHabilitarTxtPadres() {
        return habilitarTxtPadres;
    }

    public void setHabilitarTxtPadres(boolean habilitarTxtPadres) {
        this.habilitarTxtPadres = habilitarTxtPadres;
    }

    public boolean isHabilitarTxtOtros() {
        return habilitarTxtOtros;
    }

    public void setHabilitarTxtOtros(boolean habilitarTxtOtros) {
        this.habilitarTxtOtros = habilitarTxtOtros;
    }

    public String getAccionEncuestaPersonal() {
        return accionEncuestaPersonal;
    }

    public void setAccionEncuestaPersonal(String accionEncuestaPersonal) {
        this.accionEncuestaPersonal = accionEncuestaPersonal;
    }

    public Integer getIdHistorialCademico() {
        return idHistorialCademico;
    }

    public void setIdHistorialCademico(Integer idHistorialCademico) {
        this.idHistorialCademico = idHistorialCademico;
    }

    public Integer getIdExperienciaLaboral() {
        return idExperienciaLaboral;
    }

    public void setIdExperienciaLaboral(Integer idExperienciaLaboral) {
        this.idExperienciaLaboral = idExperienciaLaboral;
    }

    public List<InfoAspiranteDTO> getListaInfoAspirante() {
        return listaInfoAspirante;
    }

    public void setListaInfoAspirante(List<InfoAspiranteDTO> listaInfoAspirante) {
        this.listaInfoAspirante = listaInfoAspirante;
    }

    public boolean isMostrarResultados() {
        return mostrarResultados;
    }

    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }

    public String getTipoBusquedaHeader() {
        return tipoBusquedaHeader;
    }

    public void setTipoBusquedaHeader(String tipoBusquedaHeader) {
        this.tipoBusquedaHeader = tipoBusquedaHeader;
    }

    public boolean isMostrarTipoBusquedaHeader() {
        return mostrarTipoBusquedaHeader;
    }

    public void setMostrarTipoBusquedaHeader(boolean mostrarTipoBusquedaHeader) {
        this.mostrarTipoBusquedaHeader = mostrarTipoBusquedaHeader;
    }

    public FiltroDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroDTO filtro) {
        this.filtro = filtro;
    }

    public List<SelectItem> getListaFiltros() {
        return listaFiltros;
    }

    public void setListaFiltros(List<SelectItem> listaFiltros) {
        this.listaFiltros = listaFiltros;
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
     * @return the historialAcademico
     */
    public HistorialAcademicoDTO getHistorialAcademico() {
        return HistorialAcademico;
    }

    /**
     * @param historialAcademico
     *            the historialAcademico to set
     */
    public void setHistorialAcademico(HistorialAcademicoDTO historialAcademico) {
        HistorialAcademico = historialAcademico;
    }

    /**
     * @return the mostrarPanelHistorialAcedemico
     */
    public boolean isMostrarPanelHistorialAcedemico() {
        return mostrarPanelHistorialAcedemico;
    }

    /**
     * @param mostrarPanelHistorialAcedemico
     *            the mostrarPanelHistorialAcedemico to set
     */
    public void setMostrarPanelHistorialAcedemico(boolean mostrarPanelHistorialAcedemico) {
        this.mostrarPanelHistorialAcedemico = mostrarPanelHistorialAcedemico;
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
    public void setListaComprobantesEstudio(List<SelectItem> listaComprobantesEstudio) {
        this.listaComprobantesEstudio = listaComprobantesEstudio;
    }

    /**
     * @return the accionProfesion
     */
    public String getAccionProfesion() {
        return accionProfesion;
    }

    /**
     * @param accionProfesion
     *            the accionProfesion to set
     */
    public void setAccionProfesion(String accionProfesion) {
        this.accionProfesion = accionProfesion;
    }

    /**
     * @return the mostrarFormularioPofesion
     */
    public boolean isMostrarFormularioPofesion() {
        return mostrarFormularioPofesion;
    }

    /**
     * @param mostrarFormularioPofesion
     *            the mostrarFormularioPofesion to set
     */
    public void setMostrarFormularioPofesion(boolean mostrarFormularioPofesion) {
        this.mostrarFormularioPofesion = mostrarFormularioPofesion;
    }

    /**
     * @return the profesionDTO
     */
    public ProfesionDTO getProfesionDTO() {
        return profesionDTO;
    }

    /**
     * @param profesionDTO
     *            the profesionDTO to set
     */
    public void setProfesionDTO(ProfesionDTO profesionDTO) {
        this.profesionDTO = profesionDTO;
    }

    /**
     * @return the idProfesionAspirante
     */
    public Integer getIdProfesionAspirante() {
        return idProfesionAspirante;
    }

    /**
     * @param idProfesionAspirante
     *            the idProfesionAspirante to set
     */
    public void setIdProfesionAspirante(Integer idProfesionAspirante) {
        this.idProfesionAspirante = idProfesionAspirante;
    }

    /**
     * @return the mostrarFormularioExperiencia
     */
    public boolean isMostrarFormularioExperiencia() {
        return mostrarFormularioExperiencia;
    }

    /**
     * @param mostrarFormularioExperiencia
     *            the mostrarFormularioExperiencia to set
     */
    public void setMostrarFormularioExperiencia(boolean mostrarFormularioExperiencia) {
        this.mostrarFormularioExperiencia = mostrarFormularioExperiencia;
    }

    /**
     * @return the habilidadesPersonales
     */
    public HabilidadesPersonalesAspiranteDTO getHabilidadesPersonales() {
        return habilidadesPersonales;
    }

    /**
     * @param habilidadesPersonales
     *            the habilidadesPersonales to set
     */
    public void setHabilidadesPersonales(HabilidadesPersonalesAspiranteDTO habilidadesPersonales) {
        this.habilidadesPersonales = habilidadesPersonales;
    }

}
