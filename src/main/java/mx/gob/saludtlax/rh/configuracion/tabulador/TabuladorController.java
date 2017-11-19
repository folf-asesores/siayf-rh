/*
 *
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
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
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

        view = new TabuladorView();
        view.setMostrarEstatal(false);
        view.setMostrarFederal(false);
        List<TabuladorDTO> listaTabulador = tabulador.obtenerListaTabulador();
        view.setListaTabulador(listaTabulador);
        view.setListaPuestoGeneral(
                SelectItemsUtil.listaCatalogos(catalogo.listaPuestos()));
        view.setListaTipoTabulador(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerlistaTipoTabulador()));
        view.setListaEjercicioFiscal(SelectItemsUtil
                .listaCatalogos(catalogo.obtenerListaEjercicioFiscal()));
        view.setListaSubClasificacionTabulador(SelectItemsUtil.listaCatalogos(
                catalogo.obtenerListaSubClasificacionTabulador()));

    }

    public void obtenerListaTabulador() {
        view.setMostrarEstatal(false);
        view.setMostrarFederal(false);
        if (ValidacionUtil.esNumeroPositivo(view.getIdTipoTabulador())) {
            view.getListaTabulador().clear();
            List<TabuladorDTO> listaTabulador = tabulador
                    .listaTabulador(view.getIdTipoTabulador());
            view.setListaTabulador(listaTabulador);
            if (view.getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
                view.setMostrarEstatal(true);
            }

            if (view.getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
                view.setMostrarFederal(true);
            }

            if (view.getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
                view.setMostrarUnicoPersonalSuplente(true);
            }

        } else {
            List<TabuladorDTO> listaTabulador = tabulador
                    .obtenerListaTabulador();
            view.setListaTabulador(listaTabulador);
        }

    }

    public void obtenerInfoPuestoGeneral() {
        InfoTabuladorPuestoDTO dto = tabulador
                .obtenerInfoPuesto(view.getTabuladorDTO().getIdPuestoGeneral());

        view.getTabuladorDTO().setNombreRamaPuesto(dto.getNombreRamaPuesto());
        view.getTabuladorDTO()
                .setCodigoPuestoGeneral(dto.getCodigoPuestoGeneral());
        view.getTabuladorDTO().setNivelTipoPuesto(dto.getNivelTipoPuesto());
    }

    public void accionRegistraActualizar() {
        try {

            if (view.getTabuladorDTO()
                    .getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
                view.getTabuladorDTO().setSueldoBrutoMensual(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setAsignacionBrutaMensual(new BigDecimal(0));
                view.getTabuladorDTO().setAgaBrutaMensual(new BigDecimal(0));
                view.getTabuladorDTO().setTotalBrutoMensual(new BigDecimal(0));
            }

            if (view.getTabuladorDTO()
                    .getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMinimo(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMedio(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMaximo(new BigDecimal(0));
                view.getTabuladorDTO().setIdSubClasificacion(0);
            }

            if (view.getTabuladorDTO()
                    .getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
                // federal
                view.getTabuladorDTO().setSueldoBrutoMensual(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setAsignacionBrutaMensual(new BigDecimal(0));
                view.getTabuladorDTO().setAgaBrutaMensual(new BigDecimal(0));
                view.getTabuladorDTO().setTotalBrutoMensual(new BigDecimal(0));
                // estatal
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMinimo(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMedio(new BigDecimal(0));
                view.getTabuladorDTO()
                        .setSueldoBaseMensualMaximo(new BigDecimal(0));
                view.getTabuladorDTO().setIdSubClasificacion(0);
            }

            if (view.getAccionTabulador().equals("Registrar Tabulador")) {
                tabulador.crearTabulador(view.getTabuladorDTO());

                cerrarDialogoPrincipal();
                obtenerListaTabulador();

                JSFUtils.infoMessage("Registro Tabulador: ",
                        "Se realizo correctamente...");

            } else if (view.getAccionTabulador()
                    .equals("Actualizar Tabulador Seleccionado")) {
                tabulador.actualizarTabulador(view.getTabuladorDTO());

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
            tabulador.eliminarTabulador(view.getIdTabulador());

            cerrarDialogoAccionEliminar();
            obtenerListaTabulador();

            JSFUtils.infoMessage("Eliminación Tabulador: ",
                    "Se realizo correctamente...");
        } catch (BusinessException ex) {
            JSFUtils.errorMessage("Error: ", ex.getMessage());
        }
    }

    public void mostrarDialogoNuevoRegistro() {
        view.setDialogoCrearActualizar(true);
        view.setTabuladorDTO(new TabuladorDTO());

        view.setAccionTabulador("Registrar Tabulador");
        view.setMostrarInputEstatal(false);
        view.setMostrarInputFederal(false);
        view.setMostrarInputMandosMedios(false);
        view.setMostrarInputUnicoPersonalSuplente(false);
    }

    public void mostrarDialogoActualizar(TabuladorDTO tabuladorDTO) {
        view.setTabuladorDTO(tabuladorDTO);
        obtenerInfoPuestoGeneral();
        view.setDialogoCrearActualizar(true);
        view.setAccionTabulador("Actualizar Tabulador Seleccionado");

        view.setMostrarInputEstatal(false);
        view.setMostrarInputFederal(false);
        view.setMostrarInputMandosMedios(false);
        view.setMostrarInputUnicoPersonalSuplente(false);

        if (view.getTabuladorDTO()
                .getIdTipoTabulador() == EnumTipoTabulador.ESTATAL) {
            view.setMostrarInputEstatal(true);
        }

        if (view.getTabuladorDTO()
                .getIdTipoTabulador() == EnumTipoTabulador.FEDERAL) {
            view.setMostrarInputFederal(true);
        }

        if (view.getTabuladorDTO()
                .getIdTipoTabulador() == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
            view.setMostrarInputUnicoPersonalSuplente(true);
        }
    }

    public void cerrarDialogoPrincipal() {
        view.setDialogoCrearActualizar(false);
        view.setTabuladorDTO(new TabuladorDTO());

        view.setAccionTabulador("Registrar Tabulador");
    }

    public void mostrarDialogoEliminar(Integer idTabulador) {
        view.setIdTabulador(idTabulador);
        view.setDialogoEliminar(true);
    }

    public void cerrarDialogoAccionEliminar() {
        view.setTabuladorDTO(new TabuladorDTO());
        view.setAccionTabulador("Registrar Tabulador");
        view.setDialogoEliminar(false);
    }

    public void validarCampo(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        String nombreComponente = component.getId();
        String contexto = "Campo requerido.";

        switch (nombreComponente) {

            case "tipoTabulador":

                view.setMostrarInputEstatal(false);
                view.setMostrarInputFederal(false);
                view.setMostrarInputMandosMedios(false);
                view.setMostrarInputUnicoPersonalSuplente(false);

                Integer tipoTabulador = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(tipoTabulador)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, contexto,
                            "Seleccione el tipo tabulador");
                    context.addMessage(component.getClientId(), facesMessage);
                    view.getTabuladorDTO().setIdTipoTabulador(0);
                    throw new ValidatorException(facesMessage);
                }

                if (tipoTabulador == EnumTipoTabulador.ESTATAL) {
                    view.setMostrarInputEstatal(true);
                }

                if (tipoTabulador == EnumTipoTabulador.FEDERAL) {
                    view.setMostrarInputFederal(true);
                }

                if (tipoTabulador == EnumTipoTabulador.UNICO_PERSONAL_SUPLENTE) {
                    view.setMostrarInputUnicoPersonalSuplente(true);
                }

                break;

            case "puestoGeneral":

                Integer puestoGeneral = (Integer) value;

                if (!ValidacionUtil.esNumeroPositivo(puestoGeneral)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, contexto,
                            "Seleccione el puesto general");
                    context.addMessage(component.getClientId(), facesMessage);

                    view.getTabuladorDTO().setNombreRamaPuesto("");
                    view.getTabuladorDTO().setCodigoPuestoGeneral("");

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

        total = total.add(view.getTabuladorDTO().getSueldoBrutoMensual() == null
                ? new BigDecimal(0)
                : view.getTabuladorDTO().getSueldoBrutoMensual());
        total = total
                .add(view.getTabuladorDTO().getAsignacionBrutaMensual() == null
                        ? new BigDecimal(0)
                        : view.getTabuladorDTO().getAsignacionBrutaMensual());
        total = total.add(view.getTabuladorDTO().getAgaBrutaMensual() == null
                ? new BigDecimal(0)
                : view.getTabuladorDTO().getAgaBrutaMensual());

        view.getTabuladorDTO().setTotalBrutoMensual(total);

    }

    public TabuladorView getView() {
        return view;
    }

    public void setView(TabuladorView view) {
        this.view = view;
    }

}
