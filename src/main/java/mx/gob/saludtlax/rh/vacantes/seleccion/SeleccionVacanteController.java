/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.vacantes.seleccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.BolsaTrabajo;
import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.configuracion.especialidad.Especialidad;
import mx.gob.saludtlax.rh.configuracion.profesion.Profesion;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.puestosautorizados.PuestosAutorizadosEmpleados;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 13:31:53 12/08/2016
 */
@ManagedBean(name = "seleccionVacante")
@ViewScoped
public class SeleccionVacanteController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7596616355238493284L;

	@Inject
	private Catalogo catalogo;
	@Inject
	private BolsaTrabajo bolsaTrabajo;
	@Inject
	private Empleado empleado;
	@Inject
	private Especialidad especialidad;
	@Inject
	private PuestosAutorizadosEmpleados vacantes;
	@Inject
	private Profesion profesion;

	private SeleccionVacanteView view;

	@PostConstruct
	public void init() {
		this.view = new SeleccionVacanteView();

		obtenerListaInfoVacante();

	}

	public void obtenerListaInfoVacante() {
/*
		List<InfoPuestoDTO> listaVacantes = vacantes
				.consultarVacantesDisponibles();

		if (!listaVacantes.isEmpty()) {
			this.view.setListaSeleccionVacante(listaVacantes);
		}*/

	}

	public void obtenerListaTipoCandidato() {
		this.view.setListaTipoCandidato(SelectItemsUtil.listaTipoCandidato());
	}

	public void obtenerListaTipoPerfil(String tipoCandidato) {
		this.view.setListaTipoPerfil(SelectItemsUtil
				.listaTipoPerfil(tipoCandidato));
	}

	public void seleccionarCandidato(
			InfoPuestoDTO infoSeleccionVacanteDTO) {
		this.view.setSeleccionVacante(infoSeleccionVacanteDTO);

		this.view.setMostrarTabla(false);
		this.view.setMostrarSeleccionCandidato(true);

		obtenerListaTipoCandidato();
	}

	public void obtenerListaVacantePostular() {

		if (this.view.getTipoPerfil() == EnumFiltroPerfil.PROFESION) {
			List<InfoVacantePostularDTO> listaVacantePostular = profesion
					.obtenerListaProfesionPorTipoCandidato(
							this.view.getTipoProfesion(),
							this.view.getTipoCandidato());
			// bolsaTrabajo.obtenerListaAspiranteCandidato();

			if (!listaVacantePostular.isEmpty()) {
				this.view.setListaVacantePostular(listaVacantePostular);
			} else {
				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
				JSFUtils.errorMessage(
						"Selección Candidato: ",
						"No se encontrarón candidatos con la profesión seleccionada, intentelo con otro...");
			}

		}

		if (this.view.getTipoPerfil() == EnumFiltroPerfil.ESPECIALIDAD) {
			List<InfoVacantePostularDTO> listaVacantePostular = especialidad
					.obtenerListaEspecialidadPorTipoCandidato(
							this.view.getTipoEspecialidad(),
							this.view.getTipoCandidato());
			// empleado.obtenerListaEmpleadoCandidato();

			if (!listaVacantePostular.isEmpty()) {
				this.view.setListaVacantePostular(listaVacantePostular);
			} else {
				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
				JSFUtils.errorMessage(
						"Selección Candidato: ",
						"No se encontrarón candidatos con la especialidad seleccionada, intentelo con otro...");
			}
		}

		if (this.view.getTipoPerfil() != EnumFiltroPerfil.PROFESION
				&& this.view.getTipoPerfil() != EnumFiltroPerfil.ESPECIALIDAD) {
			this.view
					.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
		}

	}

	public void mostrarConfirmacionPostular() {

		if (!this.view.getListaSeleccionadaVacantePostular().isEmpty()) {
			this.view.setMostrarConfirmacionPostular(true);

		} else if (this.view.getListaSeleccionadaVacantePostular().isEmpty()) {

			this.view.setMostrarConfirmacionPostular(false);
			JSFUtils.errorMessage("Selección Candidato: ",
					"Seleccione uno o mas candidatos para postular...");
		}

	}

	public void cerrarConfirmacionPostular() {
		this.view
				.setListaSeleccionadaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
		this.view.setMostrarConfirmacionPostular(false);
	}

	public void mostrarFiltroPerfil() {
		this.view.setMostrarFiltroPerfil(true);
		this.view.setTipoPerfil(0);
		this.view.setMostrarFiltroEspecialidad(false);
		this.view.setTipoEspecialidad(0);
		this.view.setMostrarFiltroProfesion(false);
		this.view.setTipoProfesion(0);
		this.view
				.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
		this.view.setHeaderPerfil("Profesión/Especialidad");
		this.view.setMostrarColumnaHeaderPerfil(false);

		if (this.view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
			this.obtenerListaTipoPerfil("ASPIRANTES");

		}

		if (this.view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
			this.obtenerListaTipoPerfil("EMPLEADOS");
		}

	}

	public void mostrarFiltroProfesionEspecialidad() {

		this.view.setMostrarFiltroProfesion(false);
		this.view.setTipoProfesion(0);
		this.view.setMostrarFiltroEspecialidad(false);
		this.view.setTipoEspecialidad(0);
		this.view
				.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
		this.view.setHeaderPerfil("Profesión/Especialidad");
		this.view.setMostrarColumnaHeaderPerfil(false);

		if (this.view.getTipoPerfil() == EnumFiltroPerfil.PROFESION) {
			this.view.setMostrarFiltroProfesion(true);
			this.view.setHeaderPerfil("Profesión");
			this.view.setMostrarColumnaHeaderPerfil(true);
			this.view.setListaTipoProfesion(SelectItemsUtil
					.listaCatalogos(catalogo.obtenerListaProfesion()));

		}

		if (this.view.getTipoPerfil() == EnumFiltroPerfil.ESPECIALIDAD) {
			this.view.setHeaderPerfil("Especialidad");
			this.view.setMostrarColumnaHeaderPerfil(true);
			this.view.setMostrarFiltroEspecialidad(true);
			this.view.setListaTipoEspecialidad(SelectItemsUtil
					.listaCatalogos(catalogo.obtenerListaEspecialidad()));
		}

		if (this.view.getTipoPerfil() == EnumFiltroPerfil.TODOS) {

			if (this.view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
				List<InfoVacantePostularDTO> listaVacantePostular = bolsaTrabajo
						.obtenerListaAspiranteCandidato();

				if (!listaVacantePostular.isEmpty()) {
					this.view.setListaVacantePostular(listaVacantePostular);
				} else {
					this.view
							.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
					JSFUtils.errorMessage("Selección Candidato: ",
							"No se encontrarón candidatos, intentelo con otro...");
				}
			}

			if (this.view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
				List<InfoVacantePostularDTO> listaVacantePostular = empleado
						.obtenerListaEmpleadoCandidato();

				if (!listaVacantePostular.isEmpty()) {
					this.view.setListaVacantePostular(listaVacantePostular);
				} else {
					this.view
							.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
					JSFUtils.errorMessage("Selección Candidato: ",
							"No se encontrarón candidatos, intentelo con otro...");
				}
			}

			if (this.view.getTipoCandidato() != EnumTipoCandidato.ASPIRANTE
					&& this.view.getTipoCandidato() != EnumTipoCandidato.EMPLEADO) {
				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
			}

		}
	}

	public void postularCandidatoVacante() {
		if (!this.view.getListaSeleccionadaVacantePostular().isEmpty()) {
			try {

				List<CandidatoVacanteDTO> listaCandidatoVacante = new ArrayList<CandidatoVacanteDTO>();

				HttpServletRequest request = (HttpServletRequest) FacesContext
						.getCurrentInstance().getExternalContext().getRequest();
				HttpSession httpSession = request.getSession(false);
				UsuarioDTO usuario = (UsuarioDTO) httpSession
						.getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

				this.view.getPostuladoVacante().setUsuario(
						usuario.getUserName());
				this.view.getPostuladoVacante().setIdInventarioVacante(
						this.view.getSeleccionVacante()
								.getIdInventarioVacante());

				for (InfoVacantePostularDTO infoVacantePostularDTO : this.view
						.getListaSeleccionadaVacantePostular()) {

					CandidatoVacanteDTO candidatoVacanteDTO = new CandidatoVacanteDTO();

					candidatoVacanteDTO.setTipoCandidato(this.view
							.getTipoCandidato());

					if (this.view.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
						candidatoVacanteDTO
								.setIdContexto(infoVacantePostularDTO
										.getIdEmpleadoAspirante());

					}

					if (this.view.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {
						candidatoVacanteDTO
								.setIdContexto(infoVacantePostularDTO
										.getIdEmpleadoAspirante());

					}

					listaCandidatoVacante.add(candidatoVacanteDTO);
				}

				this.view.getPostuladoVacante().setListaCandidatoVacante(
						listaCandidatoVacante);

			/*	vacantes.postularCandidatoVacante(
						this.view.getPostuladoVacante(),
						this.view.getTipoCandidato());*/

				JSFUtils.infoMessage("Selección Candidato: ",
						"Se postulo correctamente...");

				cerrarConfirmacionPostular();

			} catch (ReglaNegocioException exception) {

				if (ReglaNegocioCodigoError.EMPLEADO_REPETIDO.equals(exception
						.getCodigoError())) {
					JSFUtils.errorMessage("",exception.getMessage());
				}

			}
		} else {
			JSFUtils.errorMessage("Selección Candidato: ",
					"Seleccione uno o mas candidatos para postular...");
		}

	}

	public String regresarPaginaSeleccionCandidato() {
		return "/contenido/vacantes/postularCandidato.xhtml?faces-redirect=true";
	}

	public void validarCampo(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String nombreComponente = component.getId();
		String contexto = "Campo requerido.";

		switch (nombreComponente) {

		case "tipoCandidato":

			Integer tipoCandidato = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoCandidato)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo candidato");
				context.addMessage(component.getClientId(), facesMessage);

				this.view.setMostrarFiltroPerfil(false);
				this.view.setTipoPerfil(0);
				this.view.setMostrarFiltroEspecialidad(false);
				this.view.setTipoEspecialidad(0);
				this.view.setMostrarFiltroProfesion(false);
				this.view.setTipoProfesion(0);
				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
				this.view.setHeaderPerfil("Profesión/Especialidad");
				this.view.setMostrarColumnaHeaderPerfil(false);

				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoPerfil":

			Integer tipoPerfil = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoPerfil)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo perfil");
				context.addMessage(component.getClientId(), facesMessage);

				this.view.setMostrarFiltroProfesion(false);
				this.view.setTipoProfesion(0);
				this.view.setMostrarFiltroEspecialidad(false);
				this.view.setTipoEspecialidad(0);
				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());
				this.view.setHeaderPerfil("Profesión/Especialidad");
				this.view.setMostrarColumnaHeaderPerfil(false);

				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoProfesion":

			Integer tipoProfesion = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoProfesion)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo profesión");
				context.addMessage(component.getClientId(), facesMessage);

				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());

				throw new ValidatorException(facesMessage);
			}

			break;

		case "tipoEspecialidad":

			Integer tipoEspecialidad = (Integer) value;

			if (!ValidacionUtil.esNumeroPositivo(tipoEspecialidad)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, contexto,
						"Seleccione el tipo especialidad");
				context.addMessage(component.getClientId(), facesMessage);

				this.view
						.setListaVacantePostular(new ArrayList<InfoVacantePostularDTO>());

				throw new ValidatorException(facesMessage);
			}

			break;

		default:
			JSFUtils.errorMessage("Selección Candidato: ",
					"Error de validación...");
			break;
		}

	}

	public String redireccionarConsultaCandidato() {
		String resultado = "";

		if (this.view.getTipoCandidato() == null) {
			JSFUtils.errorMessage("Error: ", "Seleccione el tipo de candidato");
			return "";
		}

		if (this.view.getTipoCandidato() == 1) {
			return "/contenido/aspirante/consultaAspirante.xhtml?faces-redirect=true";
		}

		if (this.view.getTipoCandidato() == 2) {
			return "/contenido/empleado/consultaEmpleado.xhtml?faces-redirect=true";
		}

		return resultado;
	}

	/*************** Getters and Setters *****************/

	/**
	 * @return the view
	 */
	public SeleccionVacanteView getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(SeleccionVacanteView view) {
		this.view = view;
	}

}
