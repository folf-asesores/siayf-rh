/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.TabChangeEvent;

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.configuracion.tabulador.Tabulador;
import mx.gob.saludtlax.rh.empleados.datolaboral.ConfiguracionPresupuestal;
import mx.gob.saludtlax.rh.empleados.datolaboral.DatoLaboralDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.SolicitudNuevoPuestoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.programas.Programa;
import mx.gob.saludtlax.rh.puestosautorizados.programas.ProgramaDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 08/08/2016 16:58:45
 *
 */
@ManagedBean(name = "solicitudApertura")
@ViewScoped
public class SolicitudAperturaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3079133439014391055L;

    private SolicitudAperturaView view = new SolicitudAperturaView();

    @Inject
    private Catalogo catalogo;
    @Inject
    private ConfiguracionPresupuestal configuracionPresupuestal;

    @Inject
    private Tabulador tabulador;

    @Inject
    private Programa programa;

    @Inject
    private PuestosAutorizadosEmpleados puestosAutorizadosEmpleados;

    @PostConstruct
    public void inicio() {
        view = new SolicitudAperturaView();

        view.setListaContratacionEventual(
                SelectItemsUtil.listaTiposContratacionEventual());
        view.setListaContratacionLaboral(
                SelectItemsUtil.listaTiposContratacionLaborales());
        view.setListaPuestos(
                SelectItemsUtil.listaCatalogos(catalogo.listaPuestos()));
        view.setListaDependencias(
                SelectItemsUtil.listaCatalogos(catalogo.listaDependencias()));
        view.setListaFuentesFinanciamiento(SelectItemsUtil
                .listaCatalogos(catalogo.listaFuentesFinanciamientos()));
        view.setListaTiposRecursos(
                SelectItemsUtil.listaCatalogos(catalogo.listaTiposRecursos()));
        view.setListaProyectos(
                SelectItemsUtil.listaCatalogos(catalogo.consultarProyectos()));
        view.setListaProgramas(
                SelectItemsUtil.listaCatalogos(catalogo.consultarProgramas()));

        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        UsuarioDTO usuario = (UsuarioDTO) httpSession
                .getAttribute(ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);
        view.setIdUsuarioLogeado(usuario.getIdUsuario());
        DatoLaboralDTO datosLaborales = new DatoLaboralDTO();
        view.getSolicitud().setDatosLaborales(datosLaborales);

    }

    public void obtenerSalario() {
        view.setMostrarSalarioEstatal(false);
        view.setMostrarSalarioFederal(false);

        try {
            Integer tipoContratacion = view.getSolicitud().getDatosLaborales()
                    .getTipoContratacion();
            if (ValidacionUtil.esNumeroPositivo(tipoContratacion)) {
                view.setSalario(tabulador.obtenerSueldoPorPuestoTipoTabulador(
                        view.getSolicitud().getDatosLaborales().getIdPuesto(),
                        tipoContratacion));

                if (tipoContratacion == EnumTipoContratacion.BASE
                        || tipoContratacion == EnumTipoContratacion.CONFIANZA
                        || tipoContratacion == EnumTipoContratacion.INTERINATO) {
                    view.setMostrarSalarioFederal(true);
                } else if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL) {
                    view.setMostrarSalarioEstatal(true);

                }
            }

        } catch (ReglaNegocioException exception) {
            JSFUtils.errorMessage("", exception.getMessage());
        }

    }

    public void seleccionarNombramiento() {
        view.setMostrarNombramientos(false);
        view.setMostrarContratoFederal(false);
        view.setMostrarSalarioEstatal(false);
        view.setMostrarSalarioFederal(false);
        view.setMostrarInterinatos(false);
        view.setMostrarSalarioInterino(false);

        int tipoContratacion = view.getSolicitud().getDatosLaborales()
                .getTipoContratacion();

        if (tipoContratacion != 0) {

            if (tipoContratacion == EnumTipoContratacion.FORMALIZADOS
                    || tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
                List<SelectItem> listaTiposNombramiento = SelectItemsUtil
                        .listaCatalogos(
                                catalogo.consultarNombramientosPorContratacion(
                                        view.getSolicitud().getDatosLaborales()
                                                .getTipoContratacion()));

                view.setListaTiposNombramiento(listaTiposNombramiento);
                view.setMostrarNombramientos(true);
            } else if (view.getSolicitud().getDatosLaborales()
                    .getTipoContratacion() == EnumTipoContratacion.CONTRATO_FEDERAL) {
                view.setMostrarContratoFederal(true);
            }

        }

    }

    public void seleccionarPuestoDisponibleInterinato() {
        if (ValidacionUtil.esNumeroPositivo(
                view.getSolicitud().getIdPuestoDisponible())) {

            DatoLaboralDTO datosLaboralesPuestoDisponible = configuracionPresupuestal
                    .obtenerDatosLaboralesPuesto(
                            view.getSolicitud().getIdPuestoDisponible());

            view.getSolicitud().getDatosLaborales().setIdProyecto(
                    datosLaboralesPuestoDisponible.getIdProyecto());
            view.getSolicitud().getDatosLaborales().setIdDependencia(
                    datosLaboralesPuestoDisponible.getIdDependencia());
            obtenerUnidadesResponsables();
            view.getSolicitud().getDatosLaborales().setIdUnidadResponsable(
                    datosLaboralesPuestoDisponible.getIdUnidadResponsable());
            view.getSolicitud().getDatosLaborales().setIdFuenteFinanciamiento(
                    datosLaboralesPuestoDisponible.getIdFuenteFinanciamiento());
            obtenerSubfuentesFinanciamiento();
            view.getSolicitud().getDatosLaborales()
                    .setIdSubfuenteFinanciamiento(datosLaboralesPuestoDisponible
                            .getIdSubfuenteFinanciamiento());
            view.getSolicitud().getDatosLaborales().setIdTipoRecurso(
                    datosLaboralesPuestoDisponible.getIdTipoRecurso());
            view.getSolicitud().getDatosLaborales()
                    .setIdPuesto(datosLaboralesPuestoDisponible.getIdPuesto());
            view.getSolicitud().getDatosLaborales()
                    .setSueldo(datosLaboralesPuestoDisponible.getSueldo());
            view.setMostrarSalarioInterino(true);
        }

    }

    public void obtenerDetallesPrograma() {
        view.getListaDetallesPrograma().clear();
        if (view.getSolicitud().getDatosLaborales().getIdPrograma() != 0) {
            view.setListaDetallesPrograma(SelectItemsUtil.listaCatalogos(
                    catalogo.consultarListaDetallesPrograma(view.getSolicitud()
                            .getDatosLaborales().getIdPrograma())));

            ProgramaDTO p = programa.obtenerProgramaPorId(
                    view.getSolicitud().getDatosLaborales().getIdPrograma());

            view.getSolicitud().getDatosLaborales()
                    .setIdProyecto(p.getIdProyecto());
            view.getSolicitud().getDatosLaborales()
                    .setIdDependencia(p.getIdDependencia());
            obtenerUnidadesResponsables();
            view.getSolicitud().getDatosLaborales()
                    .setIdUnidadResponsable(p.getIdUnidadResponsable());
            view.getSolicitud().getDatosLaborales()
                    .setIdFuenteFinanciamiento(p.getIdFuenteFinanciamiento());
            obtenerSubfuentesFinanciamiento();
            view.getSolicitud().getDatosLaborales()
                    .setIdSubfuenteFinanciamiento(
                            p.getIdSubfuentefinanciamiento());
            view.getSolicitud().getDatosLaborales()
                    .setIdTipoRecurso(p.getIdTipoRecurso());

        }
    }

    public void obtenerUnidadesResponsables() {
        view.getListaUnidadesResponsables().clear();
        if (view.getSolicitud().getDatosLaborales().getIdDependencia() != 0) {
            view.setListaUnidadesResponsables(SelectItemsUtil.listaCatalogos(
                    catalogo.listaUnidadesResponsablesPorDependencia(
                            view.getSolicitud().getDatosLaborales()
                                    .getIdDependencia())));
        }

    }

    public void obtenerSubfuentesFinanciamiento() {
        view.getListaSubfuentesFinanciamiento().clear();
        if (view.getSolicitud().getDatosLaborales()
                .getIdFuenteFinanciamiento() != 0) {
            view.setListaSubfuentesFinanciamiento(
                    SelectItemsUtil.listaCatalogos(catalogo
                            .listaSubfuentesFinanciamientosPorFinanciamiento(
                                    view.getSolicitud().getDatosLaborales()
                                            .getIdFuenteFinanciamiento())));
        }
    }

    public void guardarVacante() {

        try {
            view.getSolicitud().getDatosLaborales()
                    .setIdTabulador(view.getSalario().getIdTabulador());
            view.getSolicitud().setIdUsuario(view.getIdUsuarioLogeado());
            puestosAutorizadosEmpleados
                    .solicitarAperturaVacante(view.getSolicitud());

            inicio();

            JSFUtils.infoMessage("",
                    "¡La solicitud ha sido registrada y enviada con éxito!");

        } catch (ReglaNegocioException | ValidacionException exception) {
            System.out.println("entro a error");
            JSFUtils.errorMessage("", exception.getMessage());

        }
    }

    public SolicitudAperturaView getView() {
        return view;
    }

    public void setView(SolicitudAperturaView view) {
        this.view = view;
    }

    public void validatorVacantes(FacesContext context, UIComponent component,
            Object value) {

        String nombreComponete = component.getId();
        switch (nombreComponete) {
            case "tipoContratacion":
                Integer tipoContratacion = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(tipoContratacion)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un tipo de contratación");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "progrDis":
                Integer progrDis = (Integer) value;
                if (view.getSolicitud().getDatosLaborales()
                        .getTipoContratacion() == 2) {
                    if (!ValidacionUtil.esNumeroPositivo(progrDis)) {
                        FacesMessage facesMessage1 = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor seleccione un Programa");
                        context.addMessage(component.getClientId(),
                                facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    }
                } else {
                    break;
                }
                break;

            case "nombramiento":
                Integer nombramiento = (Integer) value;
                if (view.getSolicitud().getDatosLaborales()
                        .getTipoContratacion() > 6) {
                    if (!ValidacionUtil.esNumeroPositivo(nombramiento)) {
                        FacesMessage facesMessage1 = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor seleccione un Nombramiento");
                        context.addMessage(component.getClientId(),
                                facesMessage1);
                        throw new ValidatorException(facesMessage1);
                    } else {
                        break;
                    }
                }
                break;
            case "puesto":
                Integer puesto = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(puesto)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un Puesto");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "proyecto":
                Integer proyecto = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(proyecto)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un Proyecto");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "dependencia":
                Integer dependencia = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(dependencia)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Dependencia");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "unidadResponsable":
                Integer unidadResponsable = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(unidadResponsable)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Unidad Responsable");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "fuente":
                Integer fuente = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(fuente)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Fuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "subfuente":
                Integer subfuente = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(subfuente)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una subfuente de Financiamiento");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "tipoRecurso":
                Integer tipoRecurso = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(tipoRecurso)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione un Tipo Recurso");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            case "cuenta":
                Integer cuenta = (Integer) value;
                if (!ValidacionUtil.esNumeroPositivo(cuenta)) {
                    FacesMessage facesMessage1 = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor seleccione una Cuenta");
                    context.addMessage(component.getClientId(), facesMessage1);
                    throw new ValidatorException(facesMessage1);
                }
                break;
            default:
                break;
        }
    }

    public void onTabChange(TabChangeEvent event) {

        SolicitudNuevoPuestoDTO nuevo = new SolicitudNuevoPuestoDTO();
        DatoLaboralDTO datosLaborales = new DatoLaboralDTO();
        view.setSolicitud(nuevo);
        view.getSolicitud().setDatosLaborales(datosLaborales);

    }

}
