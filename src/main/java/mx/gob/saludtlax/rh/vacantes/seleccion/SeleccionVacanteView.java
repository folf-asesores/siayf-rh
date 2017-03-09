/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:33:31 12/08/2016
 */
public class SeleccionVacanteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5397522833962860619L;

	private List<InfoPuestoDTO> listaSeleccionVacante = new ArrayList<InfoPuestoDTO>();
	private List<InfoVacantePostularDTO> listaVacantePostular = new ArrayList<InfoVacantePostularDTO>();
	private List<InfoVacantePostularDTO> listaSeleccionadaVacantePostular = new ArrayList<InfoVacantePostularDTO>();
	private List<InfoPuestoDTO> filtroSeleccionVacante;
	private List<InfoVacantePostularDTO> filtroVacantePostular;

	private List<SelectItem> listaTipoCandidato;
	private List<SelectItem> listaTipoPerfil;
	private List<SelectItem> listaTipoProfesion;
	private List<SelectItem> listaTipoEspecialidad;

	private InfoPuestoDTO seleccionVacante = new InfoPuestoDTO();
	private InfoVacantePostularDTO vacantePostular = new InfoVacantePostularDTO();
	private PostuladoVacanteDTO postuladoVacante = new PostuladoVacanteDTO();

	private boolean mostrarTabla = true;
	private boolean mostrarSeleccionCandidato = false;
	private boolean mostrarConfirmacionPostular = false;
	private boolean mostrarFiltroPerfil = false;
	private boolean mostrarFiltroProfesion = false;
	private boolean mostrarFiltroEspecialidad = false;
	private boolean mostrarColumnaHeaderPerfil = false;

	private Integer tipoCandidato;
	private Integer tipoPerfil;
	private Integer tipoProfesion;
	private Integer tipoEspecialidad;

	private String headerPerfil = "Profesión/Especialidad";

	/**************** Getters and Setters ********************/
	/**
	 * @return the listaSeleccionVacante
	 */
	public List<InfoPuestoDTO> getListaSeleccionVacante() {
		return listaSeleccionVacante;
	}

	/**
	 * @param listaSeleccionVacante
	 *            the listaSeleccionVacante to set
	 */
	public void setListaSeleccionVacante(List<InfoPuestoDTO> listaSeleccionVacante) {
		this.listaSeleccionVacante = listaSeleccionVacante;
	}

	/**
	 * @return the filtroSeleccionVacante
	 */
	public List<InfoPuestoDTO> getFiltroSeleccionVacante() {
		return filtroSeleccionVacante;
	}

	/**
	 * @param filtroSeleccionVacante
	 *            the filtroSeleccionVacante to set
	 */
	public void setFiltroSeleccionVacante(List<InfoPuestoDTO> filtroSeleccionVacante) {
		this.filtroSeleccionVacante = filtroSeleccionVacante;
	}

	public InfoPuestoDTO getSeleccionVacante() {
		return seleccionVacante;
	}

	public void setSeleccionVacante(InfoPuestoDTO seleccionVacante) {
		this.seleccionVacante = seleccionVacante;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}

	public boolean isMostrarSeleccionCandidato() {
		return mostrarSeleccionCandidato;
	}

	public void setMostrarSeleccionCandidato(boolean mostrarSeleccionCandidato) {
		this.mostrarSeleccionCandidato = mostrarSeleccionCandidato;
	}

	/**
	 * @return the listaTipoCandidato
	 */
	public List<SelectItem> getListaTipoCandidato() {
		return listaTipoCandidato;
	}

	/**
	 * @param listaTipoCandidato
	 *            the listaTipoCandidato to set
	 */
	public void setListaTipoCandidato(List<SelectItem> listaTipoCandidato) {
		this.listaTipoCandidato = listaTipoCandidato;
	}

	/**
	 * @return the listaVacantePostular
	 */
	public List<InfoVacantePostularDTO> getListaVacantePostular() {
		return listaVacantePostular;
	}

	/**
	 * @param listaVacantePostular
	 *            the listaVacantePostular to set
	 */
	public void setListaVacantePostular(List<InfoVacantePostularDTO> listaVacantePostular) {
		this.listaVacantePostular = listaVacantePostular;
	}

	/**
	 * @return the tipoCandidato
	 */
	public Integer getTipoCandidato() {
		return tipoCandidato;
	}

	/**
	 * @param tipoCandidato
	 *            the tipoCandidato to set
	 */
	public void setTipoCandidato(Integer tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}

	/**
	 * @return the mostrarConfirmacionPostular
	 */
	public boolean isMostrarConfirmacionPostular() {
		return mostrarConfirmacionPostular;
	}

	/**
	 * @param mostrarConfirmacionPostular
	 *            the mostrarConfirmacionPostular to set
	 */
	public void setMostrarConfirmacionPostular(boolean mostrarConfirmacionPostular) {
		this.mostrarConfirmacionPostular = mostrarConfirmacionPostular;
	}

	/**
	 * @return the vacantePostular
	 */
	public InfoVacantePostularDTO getVacantePostular() {
		return vacantePostular;
	}

	/**
	 * @param vacantePostular
	 *            the vacantePostular to set
	 */
	public void setVacantePostular(InfoVacantePostularDTO vacantePostular) {
		this.vacantePostular = vacantePostular;
	}

	/**
	 * @return the listaTipoPerfil
	 */
	public List<SelectItem> getListaTipoPerfil() {
		return listaTipoPerfil;
	}

	/**
	 * @param listaTipoPerfil
	 *            the listaTipoPerfil to set
	 */
	public void setListaTipoPerfil(List<SelectItem> listaTipoPerfil) {
		this.listaTipoPerfil = listaTipoPerfil;
	}

	/**
	 * @return the tipoPerfil
	 */
	public Integer getTipoPerfil() {
		return tipoPerfil;
	}

	/**
	 * @param tipoPerfil
	 *            the tipoPerfil to set
	 */
	public void setTipoPerfil(Integer tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}

	/**
	 * @return the mostrarFiltroPerfil
	 */
	public boolean isMostrarFiltroPerfil() {
		return mostrarFiltroPerfil;
	}

	/**
	 * @param mostrarFiltroPerfil
	 *            the mostrarFiltroPerfil to set
	 */
	public void setMostrarFiltroPerfil(boolean mostrarFiltroPerfil) {
		this.mostrarFiltroPerfil = mostrarFiltroPerfil;
	}

	/**
	 * @return the mostrarFiltroProfesion
	 */
	public boolean isMostrarFiltroProfesion() {
		return mostrarFiltroProfesion;
	}

	/**
	 * @param mostrarFiltroProfesion
	 *            the mostrarFiltroProfesion to set
	 */
	public void setMostrarFiltroProfesion(boolean mostrarFiltroProfesion) {
		this.mostrarFiltroProfesion = mostrarFiltroProfesion;
	}

	/**
	 * @return the mostrarFiltroEspecialidad
	 */
	public boolean isMostrarFiltroEspecialidad() {
		return mostrarFiltroEspecialidad;
	}

	/**
	 * @param mostrarFiltroEspecialidad
	 *            the mostrarFiltroEspecialidad to set
	 */
	public void setMostrarFiltroEspecialidad(boolean mostrarFiltroEspecialidad) {
		this.mostrarFiltroEspecialidad = mostrarFiltroEspecialidad;
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
	 * @return the tipoProfesion
	 */
	public Integer getTipoProfesion() {
		return tipoProfesion;
	}

	/**
	 * @param tipoProfesion
	 *            the tipoProfesion to set
	 */
	public void setTipoProfesion(Integer tipoProfesion) {
		this.tipoProfesion = tipoProfesion;
	}

	/**
	 * @return the tipoEspecialidad
	 */
	public Integer getTipoEspecialidad() {
		return tipoEspecialidad;
	}

	/**
	 * @param tipoEspecialidad
	 *            the tipoEspecialidad to set
	 */
	public void setTipoEspecialidad(Integer tipoEspecialidad) {
		this.tipoEspecialidad = tipoEspecialidad;
	}

	/**
	 * @return the filtroVacantePostular
	 */
	public List<InfoVacantePostularDTO> getFiltroVacantePostular() {
		return filtroVacantePostular;
	}

	/**
	 * @param filtroVacantePostular
	 *            the filtroVacantePostular to set
	 */
	public void setFiltroVacantePostular(List<InfoVacantePostularDTO> filtroVacantePostular) {
		this.filtroVacantePostular = filtroVacantePostular;
	}

	/**
	 * @return the headerPerfil
	 */
	public String getHeaderPerfil() {
		return headerPerfil;
	}

	/**
	 * @param headerPerfil
	 *            the headerPerfil to set
	 */
	public void setHeaderPerfil(String headerPerfil) {
		this.headerPerfil = headerPerfil;
	}

	/**
	 * @return the mostrarColumnaHeaderPerfil
	 */
	public boolean isMostrarColumnaHeaderPerfil() {
		return mostrarColumnaHeaderPerfil;
	}

	/**
	 * @param mostrarColumnaHeaderPerfil
	 *            the mostrarColumnaHeaderPerfil to set
	 */
	public void setMostrarColumnaHeaderPerfil(boolean mostrarColumnaHeaderPerfil) {
		this.mostrarColumnaHeaderPerfil = mostrarColumnaHeaderPerfil;
	}

	/**
	 * @return the listaSeleccionadaVacantePostular
	 */
	public List<InfoVacantePostularDTO> getListaSeleccionadaVacantePostular() {
		return listaSeleccionadaVacantePostular;
	}

	/**
	 * @param listaSeleccionadaVacantePostular
	 *            the listaSeleccionadaVacantePostular to set
	 */
	public void setListaSeleccionadaVacantePostular(List<InfoVacantePostularDTO> listaSeleccionadaVacantePostular) {
		this.listaSeleccionadaVacantePostular = listaSeleccionadaVacantePostular;
	}

	/**
	 * @return the postuladoVacante
	 */
	public PostuladoVacanteDTO getPostuladoVacante() {
		return postuladoVacante;
	}

	/**
	 * @param postuladoVacante
	 *            the postuladoVacante to set
	 */
	public void setPostuladoVacante(PostuladoVacanteDTO postuladoVacante) {
		this.postuladoVacante = postuladoVacante;
	}

}
