/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.contrato.impresion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.contrato.ContratoDTO;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 14:25:22 09/09/2016
 */
public class ImpresionContratoView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -827698487737354096L;

	private List<ContratoDTO> listaContrato = new ArrayList<ContratoDTO>();

	private List<SelectItem> listaTipoContrato = new ArrayList<SelectItem>();

	private boolean mostrarConfirmacionImpresion;
	private boolean mostrarPrincipal = true;
	private boolean mostrarReimpresion;

	private String numeroContrato;

	private Integer idContratoSeleccionado;
	private Integer tipoContrato = 0;

	private byte[] bytes = null;

	public boolean isMostrarReimpresion() {
		return mostrarReimpresion;
	}

	public void setMostrarReimpresion(boolean mostrarReimpresion) {
		this.mostrarReimpresion = mostrarReimpresion;
	}

	/******** Getters and Setters ***********/
	/**
	 * @return the listaContrato
	 */
	public List<ContratoDTO> getListaContrato() {
		return listaContrato;
	}

	/**
	 * @param listaContrato
	 *            the listaContrato to set
	 */
	public void setListaContrato(List<ContratoDTO> listaContrato) {
		this.listaContrato = listaContrato;
	}

	/**
	 * @return the mostrarConfirmacionImpresion
	 */
	public boolean isMostrarConfirmacionImpresion() {
		return mostrarConfirmacionImpresion;
	}

	/**
	 * @param mostrarConfirmacionImpresion
	 *            the mostrarConfirmacionImpresion to set
	 */
	public void setMostrarConfirmacionImpresion(
			boolean mostrarConfirmacionImpresion) {
		this.mostrarConfirmacionImpresion = mostrarConfirmacionImpresion;
	}

	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato
	 *            the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the idContratoSeleccionado
	 */
	public Integer getIdContratoSeleccionado() {
		return idContratoSeleccionado;
	}

	/**
	 * @param idContratoSeleccionado
	 *            the idContratoSeleccionado to set
	 */
	public void setIdContratoSeleccionado(Integer idContratoSeleccionado) {
		this.idContratoSeleccionado = idContratoSeleccionado;
	}

	/**
	 * @return the tipoContrato
	 */
	public Integer getTipoContrato() {
		return tipoContrato;
	}

	/**
	 * @param tipoContrato
	 *            the tipoContrato to set
	 */
	public void setTipoContrato(Integer tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	/**
	 * @return the listaTipoContrato
	 */
	public List<SelectItem> getListaTipoContrato() {
		return listaTipoContrato;
	}

	/**
	 * @param listaTipoContrato
	 *            the listaTipoContrato to set
	 */
	public void setListaTipoContrato(List<SelectItem> listaTipoContrato) {
		this.listaTipoContrato = listaTipoContrato;
	}

	/**
	 * @return the mostrarPrincipal
	 */
	public boolean isMostrarPrincipal() {
		return mostrarPrincipal;
	}

	/**
	 * @param mostrarPrincipal
	 *            the mostrarPrincipal to set
	 */
	public void setMostrarPrincipal(boolean mostrarPrincipal) {
		this.mostrarPrincipal = mostrarPrincipal;
	}

}
