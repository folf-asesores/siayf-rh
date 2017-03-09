/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.vacantes.consulta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.ExperienciaLaboralAspiranteDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.HistorialAcademicoDTO;
import mx.gob.saludtlax.rh.vacantes.postulacion.InfoCandidatoDTO;


/**
 * @author L.I. Eduardo B. C. Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 16/08/2016 10:42:51
 */
public class ConsultaCandidatoPostuladoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 840240196010190179L;

	private List<InfoPostuladoVacanteDTO> listaPostuladoVacante = new ArrayList<InfoPostuladoVacanteDTO>();

	private List<InfoPostuladoVacanteDTO> filtroPostuladoVacante;

	private InfoPostuladoVacanteDTO infoPostuladoVacanteDTO = new InfoPostuladoVacanteDTO();
	private InfoCandidatoDTO infoCandidatoVacanteDTO = new InfoCandidatoDTO();

	private boolean mostrarTablaCandidatos = true;

	private boolean mostrarDetalleCandidatoSeleccionado = false;

	// Detalles Candidato

	private boolean mostrarDetalleVacante;
	private boolean mostrarDetalleGeneralCandidato;
	private boolean mostrarDetalleHistorialAcademico;
	private boolean mostrarDetalleExperienciaLaboral;

	// Datos generales
	private DatoGeneralCandidatoDTO datoGeneral = new DatoGeneralCandidatoDTO();
	private List<SelectItem> listaEstadosCiviles;
	private List<SelectItem> listaTiposSexos;
	private List<SelectItem> listaTiposSangre;
	
	//Historial Academico
	private List<HistorialAcademicoDTO> listaHistorialAcademico = new ArrayList<HistorialAcademicoDTO>();
	
	// Experiencia Laboral
	private List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral = new ArrayList<>();

	/*********** Getters and Setters ****************/

	/**
	 * @return the mostrarTablaCandidatos
	 */
	public boolean isMostrarTablaCandidatos() {
		return mostrarTablaCandidatos;
	}

	/**
	 * @param mostrarTablaCandidatos
	 *            the mostrarTablaCandidatos to set
	 */
	public void setMostrarTablaCandidatos(boolean mostrarTablaCandidatos) {
		this.mostrarTablaCandidatos = mostrarTablaCandidatos;
	}

	/**
	 * @return the mostrarDetalleCandidatoSeleccionado
	 */
	public boolean isMostrarDetalleCandidatoSeleccionado() {
		return mostrarDetalleCandidatoSeleccionado;
	}

	/**
	 * @param mostrarDetalleCandidatoSeleccionado
	 *            the mostrarDetalleCandidatoSeleccionado to set
	 */
	public void setMostrarDetalleCandidatoSeleccionado(boolean mostrarDetalleCandidatoSeleccionado) {
		this.mostrarDetalleCandidatoSeleccionado = mostrarDetalleCandidatoSeleccionado;
	}

	/**
	 * @return the listaPostuladoVacante
	 */
	public List<InfoPostuladoVacanteDTO> getListaPostuladoVacante() {
		return listaPostuladoVacante;
	}

	/**
	 * @param listaPostuladoVacante
	 *            the listaPostuladoVacante to set
	 */
	public void setListaPostuladoVacante(List<InfoPostuladoVacanteDTO> listaPostuladoVacante) {
		this.listaPostuladoVacante = listaPostuladoVacante;
	}

	/**
	 * @return the filtroPostuladoVacante
	 */
	public List<InfoPostuladoVacanteDTO> getFiltroPostuladoVacante() {
		return filtroPostuladoVacante;
	}

	/**
	 * @param filtroPostuladoVacante
	 *            the filtroPostuladoVacante to set
	 */
	public void setFiltroPostuladoVacante(List<InfoPostuladoVacanteDTO> filtroPostuladoVacante) {
		this.filtroPostuladoVacante = filtroPostuladoVacante;
	}

	public InfoPostuladoVacanteDTO getInfoPostuladoVacanteDTO() {
		return infoPostuladoVacanteDTO;
	}

	public void setInfoPostuladoVacanteDTO(InfoPostuladoVacanteDTO infoPostuladoVacanteDTO) {
		this.infoPostuladoVacanteDTO = infoPostuladoVacanteDTO;
	}

	public InfoCandidatoDTO getInfoCandidatoVacanteDTO() {
		return infoCandidatoVacanteDTO;
	}

	public void setInfoCandidatoVacanteDTO(InfoCandidatoDTO infoCandidatoVacanteDTO) {
		this.infoCandidatoVacanteDTO = infoCandidatoVacanteDTO;
	}

	/**
	 * @return the mostrarDetalleVacante
	 */
	public boolean isMostrarDetalleVacante() {
		return mostrarDetalleVacante;
	}

	/**
	 * @param mostrarDetalleVacante
	 *            the mostrarDetalleVacante to set
	 */
	public void setMostrarDetalleVacante(boolean mostrarDetalleVacante) {
		this.mostrarDetalleVacante = mostrarDetalleVacante;
	}

	/**
	 * @return the mostrarDetalleGeneralCandidato
	 */
	public boolean isMostrarDetalleGeneralCandidato() {
		return mostrarDetalleGeneralCandidato;
	}

	/**
	 * @param mostrarDetalleGeneralCandidato
	 *            the mostrarDetalleGeneralCandidato to set
	 */
	public void setMostrarDetalleGeneralCandidato(boolean mostrarDetalleGeneralCandidato) {
		this.mostrarDetalleGeneralCandidato = mostrarDetalleGeneralCandidato;
	}

	/**
	 * @return the mostrarDetalleHistorialAcademico
	 */
	public boolean isMostrarDetalleHistorialAcademico() {
		return mostrarDetalleHistorialAcademico;
	}

	/**
	 * @param mostrarDetalleHistorialAcademico
	 *            the mostrarDetalleHistorialAcademico to set
	 */
	public void setMostrarDetalleHistorialAcademico(boolean mostrarDetalleHistorialAcademico) {
		this.mostrarDetalleHistorialAcademico = mostrarDetalleHistorialAcademico;
	}

	/**
	 * @return the mostrarDetalleExperienciaLaboral
	 */
	public boolean isMostrarDetalleExperienciaLaboral() {
		return mostrarDetalleExperienciaLaboral;
	}

	/**
	 * @param mostrarDetalleExperienciaLaboral
	 *            the mostrarDetalleExperienciaLaboral to set
	 */
	public void setMostrarDetalleExperienciaLaboral(boolean mostrarDetalleExperienciaLaboral) {
		this.mostrarDetalleExperienciaLaboral = mostrarDetalleExperienciaLaboral;
	}

	/**
	 * @return the datoGeneral
	 */
	public DatoGeneralCandidatoDTO getDatoGeneral() {
		return datoGeneral;
	}

	/**
	 * @param datoGeneral
	 *            the datoGeneral to set
	 */
	public void setDatoGeneral(DatoGeneralCandidatoDTO datoGeneral) {
		this.datoGeneral = datoGeneral;
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
	 * @return the listaHistorialAcademico
	 */
	public List<HistorialAcademicoDTO> getListaHistorialAcademico() {
		return listaHistorialAcademico;
	}

	/**
	 * @param listaHistorialAcademico the listaHistorialAcademico to set
	 */
	public void setListaHistorialAcademico(List<HistorialAcademicoDTO> listaHistorialAcademico) {
		this.listaHistorialAcademico = listaHistorialAcademico;
	}

	/**
	 * @return the listaExperienciaLaboral
	 */
	public List<ExperienciaLaboralAspiranteDTO> getListaExperienciaLaboral() {
		return listaExperienciaLaboral;
	}

	/**
	 * @param listaExperienciaLaboral the listaExperienciaLaboral to set
	 */
	public void setListaExperienciaLaboral(List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral) {
		this.listaExperienciaLaboral = listaExperienciaLaboral;
	}

}
