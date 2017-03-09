/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:12:07
 */
public class TabuladorView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2797962068413288706L;

	private List<TabuladorDTO> listaTabulador = new ArrayList<TabuladorDTO>();

	private List<SelectItem> listaPuestoGeneral;

	private List<SelectItem> listaTipoTabulador;

	private List<SelectItem> listaEjercicioFiscal;

	private List<SelectItem> listaSubClasificacionTabulador;

	private TabuladorDTO tabuladorDTO = new TabuladorDTO();

	private Boolean dialogoCrearActualizar = Boolean.FALSE;

	private Boolean dialogoEliminar = Boolean.FALSE;

	private Integer idTabulador = 0;

	private String accionTabulador = "Registrar Tabulador";
	private Integer idTipoTabulador;
	private boolean mostrarFederal;
	private boolean mostrarEstatal;
	private boolean mostrarUnicoPersonalSuplente = false;

	private boolean mostrarInputEstatal = false;
	private boolean mostrarInputFederal = false;
	private boolean mostrarInputUnicoPersonalSuplente = false;
	private boolean mostrarInputMandosMedios = false;

	/******************** Getters and Setters *******************/

	public TabuladorDTO getTabuladorDTO() {
		return tabuladorDTO;
	}

	public boolean isMostrarFederal() {
		return mostrarFederal;
	}

	public void setMostrarFederal(boolean mostrarFederal) {
		this.mostrarFederal = mostrarFederal;
	}

	public boolean isMostrarEstatal() {
		return mostrarEstatal;
	}

	public void setMostrarEstatal(boolean mostrarEstatal) {
		this.mostrarEstatal = mostrarEstatal;
	}

	public Integer getIdTipoTabulador() {
		return idTipoTabulador;
	}

	public void setIdTipoTabulador(Integer idTipoTabulador) {
		this.idTipoTabulador = idTipoTabulador;
	}

	public void setTabuladorDTO(TabuladorDTO tabuladorDTO) {
		this.tabuladorDTO = tabuladorDTO;
	}

	/**
	 * @return the listaTabulador
	 */
	public List<TabuladorDTO> getListaTabulador() {
		return listaTabulador;
	}

	/**
	 * @param listaTabulador
	 *            the listaTabulador to set
	 */
	public void setListaTabulador(List<TabuladorDTO> listaTabulador) {
		this.listaTabulador = listaTabulador;
	}

	/**
	 * @return the dialogoCrearActualizar
	 */
	public Boolean getDialogoCrearActualizar() {
		return dialogoCrearActualizar;
	}

	/**
	 * @param dialogoCrearActualizar
	 *            the dialogoCrearActualizar to set
	 */
	public void setDialogoCrearActualizar(Boolean dialogoCrearActualizar) {
		this.dialogoCrearActualizar = dialogoCrearActualizar;
	}

	/**
	 * @return the dialogoEliminar
	 */
	public Boolean getDialogoEliminar() {
		return dialogoEliminar;
	}

	/**
	 * @param dialogoEliminar
	 *            the dialogoEliminar to set
	 */
	public void setDialogoEliminar(Boolean dialogoEliminar) {
		this.dialogoEliminar = dialogoEliminar;
	}

	/**
	 * @return the idTabulador
	 */
	public Integer getIdTabulador() {
		return idTabulador;
	}

	/**
	 * @param idTabulador
	 *            the idTabulador to set
	 */
	public void setIdTabulador(Integer idTabulador) {
		this.idTabulador = idTabulador;
	}

	/**
	 * @return the accionTabulador
	 */
	public String getAccionTabulador() {
		return accionTabulador;
	}

	/**
	 * @param accionTabulador
	 *            the accionTabulador to set
	 */
	public void setAccionTabulador(String accionTabulador) {
		this.accionTabulador = accionTabulador;
	}

	/**
	 * @return the listaPuestoGeneral
	 */
	public List<SelectItem> getListaPuestoGeneral() {
		return listaPuestoGeneral;
	}

	/**
	 * @param listaPuestoGeneral
	 *            the listaPuestoGeneral to set
	 */
	public void setListaPuestoGeneral(List<SelectItem> listaPuestoGeneral) {
		this.listaPuestoGeneral = listaPuestoGeneral;
	}

	/**
	 * @return the listaTipoTabulador
	 */
	public List<SelectItem> getListaTipoTabulador() {
		return listaTipoTabulador;
	}

	/**
	 * @param listaTipoTabulador
	 *            the listaTipoTabulador to set
	 */
	public void setListaTipoTabulador(List<SelectItem> listaTipoTabulador) {
		this.listaTipoTabulador = listaTipoTabulador;
	}

	/**
	 * @return the listaEjercicioFiscal
	 */
	public List<SelectItem> getListaEjercicioFiscal() {
		return listaEjercicioFiscal;
	}

	/**
	 * @param listaEjercicioFiscal
	 *            the listaEjercicioFiscal to set
	 */
	public void setListaEjercicioFiscal(List<SelectItem> listaEjercicioFiscal) {
		this.listaEjercicioFiscal = listaEjercicioFiscal;
	}

	/**
	 * @return the mostrarInputEstatal
	 */
	public boolean isMostrarInputEstatal() {
		return mostrarInputEstatal;
	}

	/**
	 * @param mostrarInputEstatal
	 *            the mostrarInputEstatal to set
	 */
	public void setMostrarInputEstatal(boolean mostrarInputEstatal) {
		this.mostrarInputEstatal = mostrarInputEstatal;
	}

	/**
	 * @return the mostrarInputFederal
	 */
	public boolean isMostrarInputFederal() {
		return mostrarInputFederal;
	}

	/**
	 * @param mostrarInputFederal
	 *            the mostrarInputFederal to set
	 */
	public void setMostrarInputFederal(boolean mostrarInputFederal) {
		this.mostrarInputFederal = mostrarInputFederal;
	}

	/**
	 * @return the mostrarInputUnicoPersonalSuplente
	 */
	public boolean isMostrarInputUnicoPersonalSuplente() {
		return mostrarInputUnicoPersonalSuplente;
	}

	/**
	 * @param mostrarInputUnicoPersonalSuplente
	 *            the mostrarInputUnicoPersonalSuplente to set
	 */
	public void setMostrarInputUnicoPersonalSuplente(boolean mostrarInputUnicoPersonalSuplente) {
		this.mostrarInputUnicoPersonalSuplente = mostrarInputUnicoPersonalSuplente;
	}

	/**
	 * @return the mostrarInputMandosMedios
	 */
	public boolean isMostrarInputMandosMedios() {
		return mostrarInputMandosMedios;
	}

	/**
	 * @param mostrarInputMandosMedios
	 *            the mostrarInputMandosMedios to set
	 */
	public void setMostrarInputMandosMedios(boolean mostrarInputMandosMedios) {
		this.mostrarInputMandosMedios = mostrarInputMandosMedios;
	}

	/**
	 * @return the mostrarUnicoPersonalSuplente
	 */
	public boolean isMostrarUnicoPersonalSuplente() {
		return mostrarUnicoPersonalSuplente;
	}

	/**
	 * @param mostrarUnicoPersonalSuplente
	 *            the mostrarUnicoPersonalSuplente to set
	 */
	public void setMostrarUnicoPersonalSuplente(boolean mostrarUnicoPersonalSuplente) {
		this.mostrarUnicoPersonalSuplente = mostrarUnicoPersonalSuplente;
	}

	/**
	 * @return the listaSubClasificacionTabulador
	 */
	public List<SelectItem> getListaSubClasificacionTabulador() {
		return listaSubClasificacionTabulador;
	}

	/**
	 * @param listaSubClasificacionTabulador
	 *            the listaSubClasificacionTabulador to set
	 */
	public void setListaSubClasificacionTabulador(List<SelectItem> listaSubClasificacionTabulador) {
		this.listaSubClasificacionTabulador = listaSubClasificacionTabulador;
	}

}
