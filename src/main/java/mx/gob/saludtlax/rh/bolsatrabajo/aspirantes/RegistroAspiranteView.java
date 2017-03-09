/**
 * 
 */
package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.configuracion.especialidad.EspecialidadDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;
import mx.gob.saludtlax.rh.expediente.InformacionAdjuntoDTO;
import mx.gob.saludtlax.rh.expediente.aspirante.ExpedienteAspiranteDTO;


/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/03/2016-12:50:51
 */
public class RegistroAspiranteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 167901675085938859L;
	private List<SelectItem> listaTiposSangre;
	private List<SelectItem> listaNacionalidades;
	private List<SelectItem> listaEstadosCiviles;
	private List<SelectItem> listaTiposSexo;
	private List<SelectItem> listaPaises;
	private List<SelectItem> listaEstados;
	private List<SelectItem> listaMunicipios;
	private List<SelectItem> listaAsentamientos = new ArrayList<SelectItem>();
	private List<SelectItem> listaColonias = new ArrayList<SelectItem>();
	private List<SelectItem> listaPuestos;
	private List<SelectItem> listaDepartamentos;
	private List<SelectItem> listaViveCon;
	private List<SelectItem> listaDependientes;
	private List<SelectItem> listaTiposLicencia;

	private List<SelectItem> listaEscolaridades;
	private List<SelectItem> listaComprobantesEstudio;

	private List<SelectItem> listaDocumentosHistorialAcademico = new ArrayList<SelectItem>();
	// Renderizados principales
	private boolean mostrarDatosPersonales;
	private boolean mostrarHistorialAcademico;
	private boolean mostrarHistorialAcademicoRegistro;
	private boolean mostrarExperienciaLaboral;
	private boolean mostrarEscuestaPersonal;
	private boolean mostrarAdjuntarDocumento;
	// Experiencia laboral
	private boolean mostrarRazonNosolicitar = true;
	private boolean mostrarDialogExperiencia = false;
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
	private boolean mostrarDialogoEliminarHistorial = false;
	// Personas Dependientes
	private boolean habilitarTxtHijos = true;
	private boolean habilitarTxtConyuge = true;
	private boolean tieneConyuge = false;
	private boolean habilitarTxtPadres = true;
	private boolean habilitarTxtOtros = true;
	// Datos Para adjuntos
	private boolean mostrarAdjuntarDocumentoHistorial;
	private boolean mostrarAdjuntarDocumentoExperiencia;
	private boolean mostrarAdjuntarDocumentacionEmpleado;
	private boolean visualizarDocumentacionAdjunta;
	private boolean visualizarDocumento;
	private boolean mostrarSinFoto = true;
	private boolean mostrarFoto = false;

	// Datos principales
	private AspiranteDTO altaAspirante = new AspiranteDTO();
	private HistorialAcademicoDTO altaHistorialAcademico = new HistorialAcademicoDTO();
	private ExperienciaLaboralAspiranteDTO altaExperienciaLaboral = new ExperienciaLaboralAspiranteDTO();
	private HabilidadesPersonalesAspiranteDTO altaEncuestaPersonal = new HabilidadesPersonalesAspiranteDTO();
	private HistorialAcademicoDTO historialAcademicoSeleccionado = new HistorialAcademicoDTO();
	private ExperienciaLaboralAspiranteDTO experienciaLaboralAspiranteSeleccionado = new ExperienciaLaboralAspiranteDTO();

	private List<HistorialAcademicoDTO> listaHistorialAcademicoAspirante = new ArrayList<>();
	private List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboralAspirante = new ArrayList<>();

	private String descripcionDocumentoAdjunto = "";

	private String[] personasDependientes;
	// Historial Academico
	private String idDocumentoAdjuntable;
	private List<String> documentacionActualHistorial;
	private boolean mostrarDialogHistorial = false;
	// Profesion
	private List<ProfesionDTO> listaProfesion = new ArrayList<ProfesionDTO>();
	private boolean mostrarProfesion;
	private List<SelectItem> listaTipoProfesion;
	private Integer idProfesion;

	// Especialidad
	private List<EspecialidadDTO> listaEspecialidad = new ArrayList<EspecialidadDTO>();
	private boolean mostrarEspecialidad;
	private List<SelectItem> listaTipoEspecialidad;
	private Integer idEspecialidad;
	// Expediente
	private String numeroExpediente;
	private boolean mostrarExpediente = false;
	private String imagenExpediente;
	private boolean mostrarAperturaExpediente;
	private ExpedienteAspiranteDTO expediente = new ExpedienteAspiranteDTO();
	private boolean mostrarActualizacionExpediente;
	private List<SelectItem> listaTiposDocumentosExpediente = new ArrayList<SelectItem>();
	private List<InformacionAdjuntoDTO> documentosExpedientes = new ArrayList<InformacionAdjuntoDTO>();
	private Integer idExpediente;
	private Integer idAspiranteSeleccionado;
	private Integer idDocumentoAdjunto;
	private Integer idImagenExpediente;
	private boolean mostrarImagenExpediente;

	/******************** Getters and Setters **************************/
	public List<SelectItem> getListaTiposLicencia() {
		return listaTiposLicencia;
	}

	public void setListaTiposLicencia(List<SelectItem> listaTiposLicencia) {
		this.listaTiposLicencia = listaTiposLicencia;
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

	public List<SelectItem> getListaDepartamentos() {
		return listaDepartamentos;
	}

	public void setListaDepartamentos(List<SelectItem> listaDepartamentos) {
		this.listaDepartamentos = listaDepartamentos;
	}

	public List<SelectItem> getListaPuestos() {
		return listaPuestos;
	}

	public void setListaPuestos(List<SelectItem> listaPuestos) {
		this.listaPuestos = listaPuestos;
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

	public List<SelectItem> getListaColonias() {
		return listaColonias;
	}

	public void setListaColonias(List<SelectItem> listaColonias) {
		this.listaColonias = listaColonias;
	}

	public AspiranteDTO getAltaAspirante() {
		return altaAspirante;
	}

	public void setAltaAspirante(AspiranteDTO altaAspirante) {
		this.altaAspirante = altaAspirante;
	}

	public List<SelectItem> getListaTiposSexo() {
		return listaTiposSexo;
	}

	public void setListaTiposSexo(List<SelectItem> listaTiposSexo) {
		this.listaTiposSexo = listaTiposSexo;
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
	 * @return the altaHistorialAcademico
	 */
	public HistorialAcademicoDTO getAltaHistorialAcademico() {
		return altaHistorialAcademico;
	}

	/**
	 * @param altaHistorialAcademico
	 *            the altaHistorialAcademico to set
	 */
	public void setAltaHistorialAcademico(HistorialAcademicoDTO altaHistorialAcademico) {
		this.altaHistorialAcademico = altaHistorialAcademico;
	}

	/**
	 * @return the mostrarEscuestaPersonal
	 */
	public boolean isMostrarEscuestaPersonal() {
		return mostrarEscuestaPersonal;
	}

	/**
	 * @param mostrarEscuestaPersonal
	 *            the mostrarEscuestaPersonal to set
	 */
	public void setMostrarEscuestaPersonal(boolean mostrarEscuestaPersonal) {
		this.mostrarEscuestaPersonal = mostrarEscuestaPersonal;
	}

	/**
	 * @return the altaExperienciaLaboral
	 */
	public ExperienciaLaboralAspiranteDTO getAltaExperienciaLaboral() {
		return altaExperienciaLaboral;
	}

	/**
	 * @param altaExperienciaLaboral
	 *            the altaExperienciaLaboral to set
	 */
	public void setAltaExperienciaLaboral(ExperienciaLaboralAspiranteDTO altaExperienciaLaboral) {
		this.altaExperienciaLaboral = altaExperienciaLaboral;
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

	/**
	 * @return the altaEncuestaPersonal
	 */
	public HabilidadesPersonalesAspiranteDTO getAltaEncuestaPersonal() {
		return altaEncuestaPersonal;
	}

	/**
	 * @param altaEncuestaPersonal
	 *            the altaEncuestaPersonal to set
	 */
	public void setAltaEncuestaPersonal(HabilidadesPersonalesAspiranteDTO altaEncuestaPersonal) {
		this.altaEncuestaPersonal = altaEncuestaPersonal;
	}

	/**
	 * @return the mostrarTxtOtroMedio
	 */
	public boolean isMostrarTxtOtroMedio() {
		return mostrarTxtOtroMedio;
	}

	/**
	 * @param mostrarTxtOtroMedio
	 *            the mostrarTxtOtroMedio to set
	 */
	public void setMostrarTxtOtroMedio(boolean mostrarTxtOtroMedio) {
		this.mostrarTxtOtroMedio = mostrarTxtOtroMedio;
	}

	/**
	 * @return the mostrarAdjuntarDocumento
	 */
	public boolean isMostrarAdjuntarDocumento() {
		return mostrarAdjuntarDocumento;
	}

	/**
	 * @param mostrarAdjuntarDocumento
	 *            the mostrarAdjuntarDocumento to set
	 */
	public void setMostrarAdjuntarDocumento(boolean mostrarAdjuntarDocumento) {
		this.mostrarAdjuntarDocumento = mostrarAdjuntarDocumento;
	}

	/**
	 * @return the mostrarTxtNombresParientes
	 */
	public boolean isMostrarTxtNombresParientes() {
		return mostrarTxtNombresParientes;
	}

	/**
	 * @param mostrarTxtNombresParientes
	 *            the mostrarTxtNombresParientes to set
	 */
	public void setMostrarTxtNombresParientes(boolean mostrarTxtNombresParientes) {
		this.mostrarTxtNombresParientes = mostrarTxtNombresParientes;
	}

	/**
	 * @return the mostrarTxtNombreAfianza
	 */
	public boolean isMostrarTxtNombreAfianza() {
		return mostrarTxtNombreAfianza;
	}

	/**
	 * @param mostrarTxtNombreAfianza
	 *            the mostrarTxtNombreAfianza to set
	 */
	public void setMostrarTxtNombreAfianza(boolean mostrarTxtNombreAfianza) {
		this.mostrarTxtNombreAfianza = mostrarTxtNombreAfianza;
	}

	/**
	 * @return the mostrarTxtNombreSindicato
	 */
	public boolean isMostrarTxtNombreSindicato() {
		return mostrarTxtNombreSindicato;
	}

	/**
	 * @param mostrarTxtNombreSindicato
	 *            the mostrarTxtNombreSindicato to set
	 */
	public void setMostrarTxtNombreSindicato(boolean mostrarTxtNombreSindicato) {
		this.mostrarTxtNombreSindicato = mostrarTxtNombreSindicato;
	}

	/**
	 * @return the mostrarTxtNombreSegurVida
	 */
	public boolean isMostrarTxtNombreSegurVida() {
		return mostrarTxtNombreSegurVida;
	}

	/**
	 * @param mostrarTxtNombreSegurVida
	 *            the mostrarTxtNombreSegurVida to set
	 */
	public void setMostrarTxtNombreSegurVida(boolean mostrarTxtNombreSegurVida) {
		this.mostrarTxtNombreSegurVida = mostrarTxtNombreSegurVida;
	}

	/**
	 * @return the mostrarTxtRazonNoViajar
	 */
	public boolean isMostrarTxtRazonNoViajar() {
		return mostrarTxtRazonNoViajar;
	}

	/**
	 * @param mostrarTxtRazonNoViajar
	 *            the mostrarTxtRazonNoViajar to set
	 */
	public void setMostrarTxtRazonNoViajar(boolean mostrarTxtRazonNoViajar) {
		this.mostrarTxtRazonNoViajar = mostrarTxtRazonNoViajar;
	}

	/**
	 * @return the motrarTxtRazonNoCambioResidencia
	 */
	public boolean isMostrarTxtRazonNoCambioResidencia() {
		return mostrarTxtRazonNoCambioResidencia;
	}

	/**
	 * @param motrarTxtRazonNoCambioResidencia
	 *            the motrarTxtRazonNoCambioResidencia to set
	 */
	public void setMostrarTxtRazonNoCambioResidencia(boolean mostrarTxtRazonNoCambioResidencia) {
		this.mostrarTxtRazonNoCambioResidencia = mostrarTxtRazonNoCambioResidencia;
	}

	/**
	 * @return the mostrarTxtOtrosIngresos
	 */
	public boolean isMostrarTxtOtrosIngresos() {
		return mostrarTxtOtrosIngresos;
	}

	/**
	 * @param mostrarTxtOtrosIngresos
	 *            the mostrarTxtOtrosIngresos to set
	 */
	public void setMostrarTxtOtrosIngresos(boolean mostrarTxtOtrosIngresos) {
		this.mostrarTxtOtrosIngresos = mostrarTxtOtrosIngresos;
	}

	/**
	 * @return the mostrarTxtConyugeTrabaja
	 */
	public boolean isMostrarTxtConyugeTrabaja() {
		return mostrarTxtConyugeTrabaja;
	}

	/**
	 * @param mostrarTxtConyugeTrabaja
	 *            the mostrarTxtConyugeTrabaja to set
	 */
	public void setMostrarTxtConyugeTrabaja(boolean mostrarTxtConyugeTrabaja) {
		this.mostrarTxtConyugeTrabaja = mostrarTxtConyugeTrabaja;
	}

	/**
	 * @return the mostrarTxtCasaPropia
	 */
	public boolean isMostrarTxtCasaPropia() {
		return mostrarTxtCasaPropia;
	}

	/**
	 * @param mostrarTxtCasaPropia
	 *            the mostrarTxtCasaPropia to set
	 */
	public void setMostrarTxtCasaPropia(boolean mostrarTxtCasaPropia) {
		this.mostrarTxtCasaPropia = mostrarTxtCasaPropia;
	}

	/**
	 * @return the mostrarTxtPagaRenta
	 */
	public boolean isMostrarTxtPagaRenta() {
		return mostrarTxtPagaRenta;
	}

	/**
	 * @param mostrarTxtPagaRenta
	 *            the mostrarTxtPagaRenta to set
	 */
	public void setMostrarTxtPagaRenta(boolean mostrarTxtPagaRenta) {
		this.mostrarTxtPagaRenta = mostrarTxtPagaRenta;
	}

	/**
	 * @return the mostrarTxtAutomovilPropio
	 */
	public boolean isMostrarTxtAutomovilPropio() {
		return mostrarTxtAutomovilPropio;
	}

	/**
	 * @param mostrarTxtAutomovilPropio
	 *            the mostrarTxtAutomovilPropio to set
	 */
	public void setMostrarTxtAutomovilPropio(boolean mostrarTxtAutomovilPropio) {
		this.mostrarTxtAutomovilPropio = mostrarTxtAutomovilPropio;
	}

	/**
	 * @return the mostrarTxtTieneDeudas
	 */
	public boolean isMostrarTxtTieneDeudas() {
		return mostrarTxtTieneDeudas;
	}

	/**
	 * @param mostrarTxtTieneDeudas
	 *            the mostrarTxtTieneDeudas to set
	 */
	public void setMostrarTxtTieneDeudas(boolean mostrarTxtTieneDeudas) {
		this.mostrarTxtTieneDeudas = mostrarTxtTieneDeudas;
	}

	/**
	 * @return the descripcionDocumentoAdjunto
	 */
	public String getDescripcionDocumentoAdjunto() {
		return descripcionDocumentoAdjunto;
	}

	/**
	 * @param descripcionDocumentoAdjunto
	 *            the descripcionDocumentoAdjunto to set
	 */
	public void setDescripcionDocumentoAdjunto(String descripcionDocumentoAdjunto) {
		this.descripcionDocumentoAdjunto = descripcionDocumentoAdjunto;
	}


	/**
	 * @return the mostrarDialogEliminar
	 */
	public Boolean getMostrarDialogEliminar() {
		return mostrarDialogEliminar;
	}

	/**
	 * @param mostrarDialogEliminar
	 *            the mostrarDialogEliminar to set
	 */
	public void setMostrarDialogEliminar(Boolean mostrarDialogEliminar) {
		this.mostrarDialogEliminar = mostrarDialogEliminar;
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
	 * @return the experienciaLaboralAspiranteSeleccionado
	 */
	public ExperienciaLaboralAspiranteDTO getExperienciaLaboralAspiranteSeleccionado() {
		return experienciaLaboralAspiranteSeleccionado;
	}

	/**
	 * @param experienciaLaboralAspiranteSeleccionado
	 *            the experienciaLaboralAspiranteSeleccionado to set
	 */
	public void setExperienciaLaboralAspiranteSeleccionado(
			ExperienciaLaboralAspiranteDTO experienciaLaboralAspiranteSeleccionado) {
		this.experienciaLaboralAspiranteSeleccionado = experienciaLaboralAspiranteSeleccionado;
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
	public void setListaExperienciaLaboralAspirante(
			List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboralAspirante) {
		this.listaExperienciaLaboralAspirante = listaExperienciaLaboralAspirante;
	}

	public String[] getPersonasDependientes() {
		return personasDependientes;
	}

	public void setPersonasDependientes(String[] personasDependientes) {
		this.personasDependientes = personasDependientes;
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

	public boolean isTieneConyuge() {
		return tieneConyuge;
	}

	public void setTieneConyuge(boolean tieneConyuge) {
		this.tieneConyuge = tieneConyuge;
	}

	/**
	 * @return the mostrarHistorialAcademicoRegistro
	 */
	public boolean isMostrarHistorialAcademicoRegistro() {
		return mostrarHistorialAcademicoRegistro;
	}

	/**
	 * @param mostrarHistorialAcademicoRegistro
	 *            the mostrarHistorialAcademicoRegistro to set
	 */
	public void setMostrarHistorialAcademicoRegistro(boolean mostrarHistorialAcademicoRegistro) {
		this.mostrarHistorialAcademicoRegistro = mostrarHistorialAcademicoRegistro;
	}

	/**
	 * @return the idDocumentoAdjuntable
	 */
	public String getIdDocumentoAdjuntable() {
		return idDocumentoAdjuntable;
	}

	/**
	 * @param idDocumentoAdjuntable
	 *            the idDocumentoAdjuntable to set
	 */
	public void setIdDocumentoAdjuntable(String idDocumentoAdjuntable) {
		this.idDocumentoAdjuntable = idDocumentoAdjuntable;
	}

	public boolean isMostrarAdjuntarDocumentoHistorial() {
		return mostrarAdjuntarDocumentoHistorial;
	}

	public void setMostrarAdjuntarDocumentoHistorial(boolean mostrarAdjuntarDocumentoHistorial) {
		this.mostrarAdjuntarDocumentoHistorial = mostrarAdjuntarDocumentoHistorial;
	}

	/**
	 * @return the mostrarAdjuntarDocumentoExperiencia
	 */
	public boolean isMostrarAdjuntarDocumentoExperiencia() {
		return mostrarAdjuntarDocumentoExperiencia;
	}

	/**
	 * @param mostrarAdjuntarDocumentoExperiencia
	 *            the mostrarAdjuntarDocumentoExperiencia to set
	 */
	public void setMostrarAdjuntarDocumentoExperiencia(boolean mostrarAdjuntarDocumentoExperiencia) {
		this.mostrarAdjuntarDocumentoExperiencia = mostrarAdjuntarDocumentoExperiencia;
	}

	/**
	 * @return the mostrarAdjuntarDocumentacionEmpleado
	 */
	public boolean isMostrarAdjuntarDocumentacionEmpleado() {
		return mostrarAdjuntarDocumentacionEmpleado;
	}

	/**
	 * @param mostrarAdjuntarDocumentacionEmpleado
	 *            the mostrarAdjuntarDocumentacionEmpleado to set
	 */
	public void setMostrarAdjuntarDocumentacionEmpleado(boolean mostrarAdjuntarDocumentacionEmpleado) {
		this.mostrarAdjuntarDocumentacionEmpleado = mostrarAdjuntarDocumentacionEmpleado;
	}

	/**
	 * @return the visualizarDocumentacionAdjunta
	 */
	public boolean isVisualizarDocumentacionAdjunta() {
		return visualizarDocumentacionAdjunta;
	}

	/**
	 * @param visualizarDocumentacionAdjunta
	 *            the visualizarDocumentacionAdjunta to set
	 */
	public void setVisualizarDocumentacionAdjunta(boolean visualizarDocumentacionAdjunta) {
		this.visualizarDocumentacionAdjunta = visualizarDocumentacionAdjunta;
	}

	/**
	 * @return the visualizarDocumento
	 */
	public boolean isVisualizarDocumento() {
		return visualizarDocumento;
	}

	/**
	 * @param visualizarDocumento
	 *            the visualizarDocumento to set
	 */
	public void setVisualizarDocumento(boolean visualizarDocumento) {
		this.visualizarDocumento = visualizarDocumento;
	}

	/**
	 * @return the mostrarSinFoto
	 */
	public boolean isMostrarSinFoto() {
		return mostrarSinFoto;
	}

	/**
	 * @param mostrarSinFoto
	 *            the mostrarSinFoto to set
	 */
	public void setMostrarSinFoto(boolean mostrarSinFoto) {
		this.mostrarSinFoto = mostrarSinFoto;
	}

	/**
	 * @return the mostrarFoto
	 */
	public boolean isMostrarFoto() {
		return mostrarFoto;
	}

	/**
	 * @param mostrarFoto
	 *            the mostrarFoto to set
	 */
	public void setMostrarFoto(boolean mostrarFoto) {
		this.mostrarFoto = mostrarFoto;
	}

	public boolean isMostrarDialogoEliminarHistorial() {
		return mostrarDialogoEliminarHistorial;
	}

	public void setMostrarDialogoEliminarHistorial(boolean mostrarDialogoEliminarHistorial) {
		this.mostrarDialogoEliminarHistorial = mostrarDialogoEliminarHistorial;
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
	 * @return the documentacionActualHistorial
	 */
	public List<String> getDocumentacionActualHistorial() {
		return documentacionActualHistorial;
	}

	/**
	 * @param documentacionActualHistorial
	 *            the documentacionActualHistorial to set
	 */
	public void setDocumentacionActualHistorial(List<String> documentacionActualHistorial) {
		this.documentacionActualHistorial = documentacionActualHistorial;
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
	 * @return the idAspiranteSeleccionado
	 */
	public Integer getIdAspiranteSeleccionado() {
		return idAspiranteSeleccionado;
	}

	/**
	 * @param idAspiranteSeleccionado
	 *            the idAspiranteSeleccionado to set
	 */
	public void setIdAspiranteSeleccionado(Integer idAspiranteSeleccionado) {
		this.idAspiranteSeleccionado = idAspiranteSeleccionado;
	}

	/**
	 * @return the idDocumentoAdjunto
	 */
	public Integer getIdDocumentoAdjunto() {
		return idDocumentoAdjunto;
	}

	/**
	 * @param idDocumentoAdjunto
	 *            the idDocumentoAdjunto to set
	 */
	public void setIdDocumentoAdjunto(Integer idDocumentoAdjunto) {
		this.idDocumentoAdjunto = idDocumentoAdjunto;
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
	 * @return the expediente
	 */
	public ExpedienteAspiranteDTO getExpediente() {
		return expediente;
	}

	/**
	 * @param expediente
	 *            the expediente to set
	 */
	public void setExpediente(ExpedienteAspiranteDTO expediente) {
		this.expediente = expediente;
	}

	/**
	 * @return the mostrarDialogExperiencia
	 */
	public boolean isMostrarDialogExperiencia() {
		return mostrarDialogExperiencia;
	}

	/**
	 * @param mostrarDialogExperiencia
	 *            the mostrarDialogExperiencia to set
	 */
	public void setMostrarDialogExperiencia(boolean mostrarDialogExperiencia) {
		this.mostrarDialogExperiencia = mostrarDialogExperiencia;
	}

	/**
	 * @return the mostrarDialogHistorial
	 */
	public boolean isMostrarDialogHistorial() {
		return mostrarDialogHistorial;
	}

	/**
	 * @param mostrarDialogHistorial
	 *            the mostrarDialogHistorial to set
	 */
	public void setMostrarDialogHistorial(boolean mostrarDialogHistorial) {
		this.mostrarDialogHistorial = mostrarDialogHistorial;
	}

	public List<ProfesionDTO> getListaProfesion() {
		return listaProfesion;
	}

	public void setListaProfesion(List<ProfesionDTO> listaProfesion) {
		this.listaProfesion = listaProfesion;
	}

	public List<EspecialidadDTO> getListaEspecialidad() {
		return listaEspecialidad;
	}

	public void setListaEspecialidad(List<EspecialidadDTO> listaEspecialidad) {
		this.listaEspecialidad = listaEspecialidad;
	}

	public boolean isMostrarProfesion() {
		return mostrarProfesion;
	}

	public void setMostrarProfesion(boolean mostrarProfesion) {
		this.mostrarProfesion = mostrarProfesion;
	}

	public boolean isMostrarEspecialidad() {
		return mostrarEspecialidad;
	}

	public void setMostrarEspecialidad(boolean mostrarEspecialidad) {
		this.mostrarEspecialidad = mostrarEspecialidad;
	}

	public List<SelectItem> getListaTipoProfesion() {
		return listaTipoProfesion;
	}

	public void setListaTipoProfesion(List<SelectItem> listaTipoProfesion) {
		this.listaTipoProfesion = listaTipoProfesion;
	}

	public List<SelectItem> getListaTipoEspecialidad() {
		return listaTipoEspecialidad;
	}

	public void setListaTipoEspecialidad(List<SelectItem> listaTipoEspecialidad) {
		this.listaTipoEspecialidad = listaTipoEspecialidad;
	}

	public Integer getIdProfesion() {
		return idProfesion;
	}

	public void setIdProfesion(Integer idProfesion) {
		this.idProfesion = idProfesion;
	}

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

}
