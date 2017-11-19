/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.tabulador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 28/07/2016 13:11:43
 */
@ManagedBean(name = "tabulador")
@ViewScoped
public class TabuladorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207223960278037681L;

	@Inject
	private Tabulador tabulador;

	@Inject
	private Catalogo catalogo;

	private TabuladorView view;

	@PostConstruct
	public void init() {

		this.view = new TabuladorView();
		this.view.setMostrarEstatal(false);
		this.view.setMostrarFederal(false);
		List<TabuladorDTO> listaTabulador = tabulador.obtenerListaTabulador();
		this.view.setListaTabulador(listaTabulador);
		this.view.setListaPuestoGeneral(SelectItemsUtil.listaCatalogos(catalogo
				.listaPuestos()));
		this.view.setListaTipoTabulador(SelectItemsUtil.listaCatalogos(catalogo
				.obtenerlistaTipoTabulador()));
		this.view.setListaEjercicioFiscal(SelectItemsUtil
				.listaCatalogos(catalogo.obtenerListaEjercicioFiscal()));
		this.view.setListaSubClasificacionTabulador(SelectItemsUtil
				.listaCatalogos(catalogo
						.obtenerListaSubClasificacionTabulador()));

	}

	public void obtenerListaTabulador() {
		this.view.setMostrarEstatal(false);
		this.view.setMostrarFederal(false);
		if (ValidacionUtil.esNumeroPositivo(this.view.getIdTipoTabulador())) {
			this.view.getListaTabulador().clear();
			List<TabuladorDTO> listaTabulador = tabulador
					.listaTabulador(this.view.getIdTipoTabulador());
			this.view.setListaTabulador(listaTabulador);
			if (this.view.getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
				this.view.setMostrarEstatal(true);
			}

			if (this.view.getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
				this.view.setMostrarFederal(true);
			}

			if (this.view.getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
				this.view.setMostrarUnicoPersonalSuplente(true);
			}

		} else {
			List<TabuladorDTO> listaTabulador = tabulador
					.obtenerListaTabulador();
			this.view.setListaTabulador(listaTabulador);
		}

	}

	public void obtenerInfoPuestoGeneral() {
		InfoTabuladorPuestoDTO dto = tabulador.obtenerInfoPuesto(this.view
				.getTabuladorDTO().getIdPuestoGeneral());

		this.view.getTabuladorDTO().setNombreRamaPuesto(
				dto.getNombreRamaPuesto());
		this.view.getTabuladorDTO().setCodigoPuestoGeneral(
				dto.getCodigoPuestoGeneral());
		this.view.getTabuladorDTO()
				.setNivelTipoPuesto(dto.getNivelTipoPuesto());
	}

	public void accionRegistraActualizar() {
		try {

			if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
				this.view.getTabuladorDTO().setSueldoBrutoMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setAsignacionBrutaMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setAgaBrutaMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setTotalBrutoMensual(
						new BigDecimal(0));
			}

			if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
				this.view.getTabuladorDTO().setSueldoBaseMensualMinimo(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setSueldoBaseMensualMedio(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setSueldoBaseMensualMaximo(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setIdSubClasificacion(0);
			}

			if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
				// federal
				this.view.getTabuladorDTO().setSueldoBrutoMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setAsignacionBrutaMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setAgaBrutaMensual(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setTotalBrutoMensual(
						new BigDecimal(0));
				// estatal
				this.view.getTabuladorDTO().setSueldoBaseMensualMinimo(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setSueldoBaseMensualMedio(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setSueldoBaseMensualMaximo(
						new BigDecimal(0));
				this.view.getTabuladorDTO().setIdSubClasificacion(0);
			}

			if (this.view.getAccionTabulador().equals("Registrar Tabulador")) {
				this.tabulador.crearTabulador(this.view.getTabuladorDTO());

				cerrarDialogoPrincipal();
				obtenerListaTabulador();

				JSFUtils.infoMessage("Registro Tabulador: ",
						"Se realizo correctamente...");

			} else if (this.view.getAccionTabulador().equals(
					"Actualizar Tabulador Seleccionado")) {
				this.tabulador.actualizarTabulador(this.view.getTabuladorDTO());

				cerrarDialogoPrincipal();
				obtenerListaTabulador();

				JSFUtils.infoMessage("Actualización Tabulador: ",
						"Se realizo correctamente...");
			}

		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void eliminarTabulador() {
		try {
			tabulador.eliminarTabulador(this.view.getIdTabulador());

			cerrarDialogoAccionEliminar();
			obtenerListaTabulador();

			JSFUtils.infoMessage("Eliminación Tabulador: ",
					"Se realizo correctamente...");
		} catch (BusinessException ex) {
			JSFUtils.errorMessage("Error: ", ex.getMessage());
		}
	}

	public void mostrarDialogoNuevoRegistro() {
		this.view.setDialogoCrearActualizar(true);
		this.view.setTabuladorDTO(new TabuladorDTO());

		this.view.setAccionTabulador("Registrar Tabulador");
		this.view.setMostrarInputEstatal(false);
		this.view.setMostrarInputFederal(false);
		this.view.setMostrarInputMandosMedios(false);
		this.view.setMostrarInputUnicoPersonalSuplente(false);
	}

	public void mostrarDialogoActualizar(TabuladorDTO tabuladorDTO) {
		this.view.setTabuladorDTO(tabuladorDTO);
		this.obtenerInfoPuestoGeneral();
		this.view.setDialogoCrearActualizar(true);
		this.view.setAccionTabulador("Actualizar Tabulador Seleccionado");

		this.view.setMostrarInputEstatal(false);
		this.view.setMostrarInputFederal(false);
		this.view.setMostrarInputMandosMedios(false);
		this.view.setMostrarInputUnicoPersonalSuplente(false);

		if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
			this.view.setMostrarInputEstatal(true);
		}

		if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
			this.view.setMostrarInputFederal(true);
		}

		if (this.view.getTabuladorDTO().getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
			this.view.setMostrarInputUnicoPersonalSuplente(true);
		}
	}

	public void cerrarDialogoPrincipal() {
		this.view.setDialogoCrearActualizar(false);
		this.view.setTabuladorDTO(new TabuladorDTO());

		this.view.setAccionTabulador("Registrar Tabulador");
	}

	public void mostrarDialogoEliminar(Integer idTabulador) {
		this.view.setIdTabulador(idTabulador);
		this.view.setDialogoEliminar(true);
	}

	public void cerrarDialogoAccionEliminar() {
		this.view.setTabuladorDTO(new TabuladorDTO());
		this.view.setAccionTabulador("Registrar Tabulador");
		this.view.setDialogoEliminar(false);
	}

	public void validarCampo(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "tipoTabulador":

			this.view.setMostrarInputEstatal(false);
			this.view.setMostrarInputFederal(false);
			this.view.setMostrarInputMandosMedios(false);
			this.view.setMostrarInputUnicoPersonalSuplente(false);

			Integer tipoTabulador = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoTabulador)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo tabulador");
				context.addMessage(component.getClientId(), facesMessage);
				this.view.getTabuladorDTO().setIdTipoTabulador(0);
				throw new ValidatorException(facesMessage);
			}

			if (tipoTabulador == EnumTipoTabulador.ESTATAL) {
				this.view.setMostrarInputEstatal(true);
			}

			if (tipoTabulador == EnumTipoTabulador.FEDERAL) {
				this.view.setMostrarInputFederal(true);
			}

			if (tipoTabulador == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
				this.view.setMostrarInputUnicoPersonalSuplente(true);
			}

			break;

		case "puestoGeneral":

			Integer puestoGeneral = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(puestoGeneral)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el puesto general");
				context.addMessage(component.getClientId(), facesMessage);

				this.view.getTabuladorDTO().setNombreRamaPuesto("");
				this.view.getTabuladorDTO().setCodigoPuestoGeneral("");

				throw new ValidatorException(facesMessage);
			}

			break;

		case "ejercicioFiscal":

			Integer ejercicioFiscal = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(ejercicioFiscal)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el ejercicio fiscal");
				context.addMessage(component.getClientId(), facesMessage);
				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			break;
		}

	}

	public void sumaTotalFederal() {

		BigDecimal total = new BigDecimal(0);

		total = total
				.add(this.view.getTabuladorDTO().getSueldoBrutoMensual() == null ? new BigDecimal(
						0) : this.view.getTabuladorDTO()
						.getSueldoBrutoMensual());
		total = total.add(this.view.getTabuladorDTO()
				.getAsignacionBrutaMensual() == null ? new BigDecimal(0)
				: this.view.getTabuladorDTO().getAsignacionBrutaMensual());
		total = total
				.add(this.view.getTabuladorDTO().getAgaBrutaMensual() == null ? new BigDecimal(
						0) : this.view.getTabuladorDTO().getAgaBrutaMensual());

		this.view.getTabuladorDTO().setTotalBrutoMensual(total);

	}

	
	public TabuladorView getView() {
		return view;
	}

	public void setView(TabuladorView view) {
		this.view = view;
	}

}
