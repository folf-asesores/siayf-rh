/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.io.IOException;
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

import mx.gob.saludtlax.rh.catalogos.Catalogo;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.excepciones.BusinessException;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 01/08/2016 16:45:21
 * @modify Eduardo Mex
 */

@ManagedBean(name = "consultaAspirante")
@ViewScoped
public class ConsultaAspiranteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6278321558656065977L;

    @Inject
    private BolsaTrabajo bolsaTrabajo;
    @Inject
    private Catalogo catalogo;

    private ConsultaAspiranteView view;

    private static final int DATOS_GENERALES = 1;
    private static final int HISTORIAL_ACADEMICO = 2;
    private static final int EXPERIENCIA_LABORAL = 3;
    private static final int HABILIDADES_PERSONALES = 4;
    private static final int EXPEDIENTE = 5;

    @PostConstruct
    public void inicio() {

        view = new ConsultaAspiranteView();

        view.setListaFiltros(SelectItemsUtil.listaFiltrosConsultaAspirantes());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession httpSession = request.getSession(false);
        Integer idAspirante = (Integer) httpSession.getAttribute("idAspirante");
        if (idAspirante != null) {
            verDetalleAspirante(idAspirante);
            httpSession.removeAttribute("idAspirante");
        }
    }

    public void cargarCatalogo() {
        List<CatalogoDTO> listaPaises = catalogo.listaPaises();
        List<CatalogoDTO> listaPuestos = catalogo.listaPuestos();
        List<CatalogoDTO> listaDepartamentos = catalogo.listaDepartamentos();
        List<CatalogoDTO> estado = catalogo.listaEstados();

        view.setListaPaises(SelectItemsUtil.listaCatalogos(listaPaises));
        view.setListaEstadosCiviles(SelectItemsUtil.listaEstadosCivil());
        view.setListaNacionalidades(SelectItemsUtil.listaNacionalidad());
        view.setListaTiposSangre(SelectItemsUtil.listaTiposSangre());
        view.setListaTiposSexo(SelectItemsUtil.listaTiposSexo());
        view.setListaPuestos(SelectItemsUtil.listaCatalogos(listaPuestos));
        view.setListaDepartamentos(SelectItemsUtil.listaCatalogos(listaDepartamentos));
        view.setListaViveCon(SelectItemsUtil.listaViveCon());
        view.setListaDependientes(SelectItemsUtil.listaDependientes());
        view.setListaTiposLicencia(SelectItemsUtil.listaTiposLicencia());
        view.setListaEstados(SelectItemsUtil.listaCatalogos(estado));

        view.setMostrarDatosPersonales(true);

    }

    public void obtenerInfoAspirante() {

        view.setMostrarDetallesAspirante(false);
        view.setAspirante(new AspiranteDTO());
        view.setHistorialAcademicoDTO(new HistorialAcademicoDTO());
        view.setIdAspirante(0);

        cerrarMenu();

        view.getFiltro().setTipoFiltro(EnumTipoFiltro.NOMBRE_RFC_CURP_PROFESION);
        List<InfoAspiranteDTO> listaInfoAspirante = bolsaTrabajo.consultarPorCriterio(view.getFiltro());

        if (!listaInfoAspirante.isEmpty()) {
            view.setListaInfoAspirante(listaInfoAspirante);
            view.setMostrarResultados(true);

            view.setMostrarTipoBusquedaHeader(false);
            view.setTipoBusquedaHeader("");

        } else {
            view.setListaInfoAspirante(new ArrayList<InfoAspiranteDTO>());
            view.setMostrarResultados(false);
            view.setFiltro(new FiltroDTO());
            JSFUtils.errorMessage("Consulta Aspirante", "No se encontrarón resultados, intentelo de nuevo");
        }
    }

    public void menu(int panel) {

        try {

            cerrarMenu();

            switch (panel) {

                case DATOS_GENERALES:

                    view.setMostrarDatosPersonales(true);

                    break;

                case HISTORIAL_ACADEMICO:

                    view.setMostrarHistorialAcademico(true);

                    view.setHistorialAcademicoDTO(new HistorialAcademicoDTO());

                    view.setListaEscolaridades(SelectItemsUtil.listaEscolaridad(catalogo.listaEscolaridad()));
                    view.setListaComprobantesEstudio(SelectItemsUtil.listaCatalogos(catalogo.listaComprobantesEstudios()));

                    List<HistorialAcademicoDTO> listaHistorial = bolsaTrabajo.obtenerListaHistorialAcademico(view.getIdAspirante());

                    if (!listaHistorial.isEmpty()) {
                        view.setListaHistorialAcademico(listaHistorial);
                    } else {
                        view.setListaHistorialAcademico(new ArrayList<HistorialAcademicoDTO>());
                        JSFUtils.warningMessage("Historial Academico: ",
                                "Se ha detectado que no ha registrado ningún historial academico, se recomienda mantener su información actualizada");
                    }

                    break;

                case EXPERIENCIA_LABORAL:

                    view.setMostrarExperienciaLaboral(true);

                    view.setExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());

                    List<ExperienciaLaboralAspiranteDTO> listaExperienciaLaboral = bolsaTrabajo.obtenerListaExperienciaLaboral(view.getIdAspirante());

                    if (!listaExperienciaLaboral.isEmpty()) {
                        view.setListaExperienciaLaboral(listaExperienciaLaboral);
                    } else {
                        view.setListaExperienciaLaboral(new ArrayList<ExperienciaLaboralAspiranteDTO>());
                        JSFUtils.warningMessage("Experiencia Laboral: ",
                                "Se ha detectado que no ha registrado ninguna experiencia laboral, se recomienda mantener su información actualizada");
                    }

                    break;
                case HABILIDADES_PERSONALES:

                    view.setMostrarHabilidadPersonal(true);

                    HabilidadesPersonalesAspiranteDTO habilidad = bolsaTrabajo.obtenerHabilidadesPersonalesPorIdAspirante(view.getIdAspirante());

                    if (habilidad.getIdEncuestaPersonalAspirante() != 0) {

                        view.setEncuestaPersonal(habilidad);

                    } else {
                        view.setEncuestaPersonal(new HabilidadesPersonalesAspiranteDTO());

                        JSFUtils.warningMessage("Habilidades Parsonales: ",
                                "Se ha detectado que no ha registrado ningún habilidad personal, se recomienda mantener su información actualizada");
                    }

                    break;

                case EXPEDIENTE:

                    break;
            }

        } catch (BusinessException exception) {
            throw new BusinessException(exception.getMessage());
        }

    }

    public void cerrarMenu() {
        view.setMostrarDatosPersonales(false);
        view.setMostrarHistorialAcademico(false);
        view.setMostrarProfesion(false);
        view.setMostrarEspecialidad(false);
        view.setMostrarExperienciaLaboral(false);
        view.setMostrarExperienciaLaboralSeleccionado(false);
        view.setMostrarHabilidadPersonal(false);
        view.setMostrarHistorialAcedemicoSeleccionado(false);

    }

    public void verDetalleAspirante(Integer idAspirante) {
        try {

            view.setIdAspirante(idAspirante);

            view.setMostrarDetallesAspirante(true);
            view.setMostrarResultados(false);
            view.setMostrarDatosPersonales(true);
            view.setMostrarHistorialAcademico(false);
            view.setMostrarProfesion(false);
            view.setMostrarEspecialidad(false);
            view.setMostrarExperienciaLaboral(false);
            view.setMostrarHabilidadPersonal(false);

            AspiranteDTO aspirante = bolsaTrabajo.obtenerAspirantePorIdentificador(idAspirante);

            List<String> personasDependientes = new ArrayList<>();

            // VALIDANDO LAS PERSONAS QUE DEPENDEN DEL ASPIRANTE
            if (aspirante.getNumeroConyuges() != 0) {
                personasDependientes.add("CONYUGE");
            }

            if (aspirante.getNumeroHijos() != 0) {
                personasDependientes.add("HIJOS");
            }

            if (aspirante.getNumeroPadres() != 0) {
                personasDependientes.add("PADRES");
            }

            if (aspirante.getNumeroOtros() != 0) {
                personasDependientes.add("OTROS");
            }

            view.setPersonasDependientes(personasDependientes);
            /*
             * List<CatalogoDTO> municipios = catalogo
             * .consultarMunicipiosPorEstado(aspirante.getDireccionDTO().
             * getIdMunicipio());
             * this.view.setListaMunicipios(SelectItemsUtil.listaCatalogos(
             * municipios));
             *
             * List<CatalogoDTO> asentamientos = catalogo
             * .consultarAsantamientosPorMunicipios(aspirante.getDireccionDTO().
             * getIdAsentamiento());
             * this.view.setListaAsentamientos(SelectItemsUtil.listaCatalogos(
             * asentamientos));
             */

            cargarCatalogo();

            view.setAspirante(aspirante);

        } catch (BusinessException exception) {
            throw new BusinessException(exception.getMessage());
        }
    }

    public void verDetalleHistorialAcademico(HistorialAcademicoDTO historialAcademicoDTO) {
        view.setHistorialAcademicoDTO(historialAcademicoDTO);

        view.setMostrarHistorialAcademico(false);
        view.setMostrarHistorialAcedemicoSeleccionado(true);
    }

    public void cerrarDetalleHistorialAcademico() {
        view.setHistorialAcademicoDTO(new HistorialAcademicoDTO());

        view.setMostrarHistorialAcademico(true);
        view.setMostrarHistorialAcedemicoSeleccionado(false);
    }

    public void verDetalleExperienciaLaboral(ExperienciaLaboralAspiranteDTO experienciaLaboralAspiranteDTO) {
        view.setExperienciaLaboral(experienciaLaboralAspiranteDTO);

        view.setMostrarExperienciaLaboral(false);
        view.setMostrarExperienciaLaboralSeleccionado(true);
    }

    public void cerrarExperienciaLaboral() {
        view.setExperienciaLaboral(new ExperienciaLaboralAspiranteDTO());

        view.setMostrarExperienciaLaboral(true);
        view.setMostrarExperienciaLaboralSeleccionado(false);
    }

    public void validarConsulta(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String nombreComponente = component.getId();
        String contexto = "Campo requerido.";

        switch (nombreComponente) {

            case "criterio":

                String criterio = String.valueOf(value);

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, contexto, "Ingrese el criterio");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                }

                break;

            default:
                break;
        }

    }

    public void regresarModulo() throws IOException {
        JSFUtils.redireccionar("/siayf-rh/contenido/bolsaTrabajo/consultaAspirante.xhtml?faces-redirect=true");
    }

    public ConsultaAspiranteView getView() {
        return view;
    }

    public void setView(ConsultaAspiranteView view) {
        this.view = view;
    }

}
