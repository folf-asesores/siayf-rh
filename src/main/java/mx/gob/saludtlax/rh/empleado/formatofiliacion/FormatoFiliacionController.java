/*
 *
 */

package mx.gob.saludtlax.rh.empleado.formatofiliacion;

import java.io.IOException;
import java.io.Serializable;
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

import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoDependienteEconomicoDTO;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.reportes.ReporteParamDTO;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.SelectItemsUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@ManagedBean(name = "formatoFiliacion")
@ViewScoped
public class FormatoFiliacionController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3215671781558762301L;

    @Inject
    private Empleado empleado;
    @Inject
    private FormatoFiliacion formatoFiliacion;

    private FormatoFiliacionView view;
    private String urlReporte;

    @PostConstruct
    public void init() {
        view = new FormatoFiliacionView();
        view.setListaBoca(SelectItemsUtil.listaBoca());
        view.setListaCabello(SelectItemsUtil.listaCabello());
        view.setListaCeja(SelectItemsUtil.listaCejas());
        view.setListaColorPiel(SelectItemsUtil.listaColorPiel());
        view.setListaFrente(SelectItemsUtil.listaFrente());
        view.setListaNariz(SelectItemsUtil.listaNariz());
        view.setListaOjos(SelectItemsUtil.listaOjos());
    }

    public void consultarEmpleados() {
        try {
            view.setListaEmpleados(
                    empleado.consultaEmpleadosFederales(view.getCriterio()));

            if (view.getListaEmpleados().isEmpty()) {
                JSFUtils.warningMessage("",
                        "No se encontrarón registros con el criterio "
                                + view.getCriterio());
            }

        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("", reglaNegocioException.getMessage());
        } catch (ValidatorException validatorException) {
            JSFUtils.errorMessage("", validatorException.getMessage());
        }
    }

    public void verFormatoFiliacion() throws IOException {
        try {

            view.getFormatoFiliacionDTO().setIdEmpleado(view.getIdEmpleado());

            formatoFiliacion
                    .crearFormatoFiliacion(view.getFormatoFiliacionDTO());

            urlReporte = "FormatoFiliacionServlet?" + "idEmpleado="
                    + view.getIdEmpleado();
            view.setFormulario(false);
            view.setMostrarReporteNuevaVentana(true);
            view.setMostrarExitoReporte(true);
            JSFUtils.infoMessage("Formato Filiación: ",
                    "El proceso de realizo correctamente");

        } catch (NullPointerException | IllegalArgumentException exception) {

            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void verFormatoFiliacionDos() throws IOException {

        try {

            view.getFormatoFiliacionDTO().setIdEmpleado(view.getIdEmpleado());

            view.setIdFormato(formatoFiliacion
                    .crearFormatoFiliacion(view.getFormatoFiliacionDTO()));

            byte[] bytes = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "formato-filiacion", "TIPO_REPORTE", "pdf", "ID_EMPLEADO",
                    String.valueOf(view.getIdEmpleado()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            bytes = admintradorReportes.obtenerReporte(referencia);

            if (bytes != null) {

                // JSFUtils.descargarArchivo(bytes,
                // "Formato_Filiacion",
                // TipoArchivo.getMIMEType("pdf"));

                ReporteParamDTO reporteParamDTO = new ReporteParamDTO();

                reporteParamDTO.setParametros(parametros);
                reporteParamDTO.setNombreReporte("Formato_Filiacion.pdf");
                reporteParamDTO.setTituloReporte("Reporte Formato Filiación");
                reporteParamDTO
                        .setSubtituloReporte("Reporte Formato Filiación");
                reporteParamDTO.setBytes(bytes);

                HttpSession session = (HttpSession) FacesContext
                        .getCurrentInstance().getExternalContext()
                        .getSession(true);
                session.setAttribute("reporteParam", reporteParamDTO);

                urlReporte = "contenido/reportesLaborales/administradorReporte.xhtml?faces-redirect=true";
                view.setFormulario(false);
                view.setMostrarReporteNuevaVentana(true);
                view.setMostrarExitoReporte(true);

            }

        } catch (NullPointerException | IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ValidacionException validacionException) {
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        }
    }

    public void mostrarFormulario(Integer idEmpleado, String nombreEmpleado) {

        view.setPrincipal(false);
        view.setFormulario(true);
        view.setIdEmpleado(idEmpleado);
        view.setNombreEmpleado(nombreEmpleado);
        JSFUtils.infoMessage("",
                "En caso de ser necesaria la información de los padres y conyuge, es requerido que previamente sean registrados en el módulo de dependientes economicos.");

        List<InfoDependienteEconomicoDTO> dependienteEconomicoDTOs = empleado
                .consultarDependientesEconomicoPadres(idEmpleado);

        if (!dependienteEconomicoDTOs.isEmpty()) {

            for (InfoDependienteEconomicoDTO infoDependienteEconomicoDTO : dependienteEconomicoDTOs) {

                if (infoDependienteEconomicoDTO.getParentesco()
                        .equals("PADRES")) {

                    if (infoDependienteEconomicoDTO.getParentesco()
                            .equals("CONYUGE")) {
                        view.getFormatoFiliacionDTO()
                                .setNombreConyuge(infoDependienteEconomicoDTO
                                        .getNombreCompleto());
                    }

                    if (infoDependienteEconomicoDTO.getSexo()
                            .equals("MASCULINO")) {
                        view.getFormatoFiliacionDTO()
                                .setNombrePadre(infoDependienteEconomicoDTO
                                        .getNombreCompleto());
                    }

                    if (infoDependienteEconomicoDTO.getSexo()
                            .equals("FEMENINO")) {
                        view.getFormatoFiliacionDTO()
                                .setNombreMadre(infoDependienteEconomicoDTO
                                        .getNombreCompleto());
                    }

                }
            }

        }

    }

    public void ajaxColorPiel() {
        view.getFormatoFiliacionDTO().setColorBlanco(false);
        view.getFormatoFiliacionDTO().setColorNegro(false);
        view.getFormatoFiliacionDTO().setColorMorenoClaro(false);
        view.getFormatoFiliacionDTO().setColorMorenoOscuro(false);
        view.getFormatoFiliacionDTO().setColorAmarillo(false);

        if (view.getIdColorPiel().equals(EnumColorPiel.COLOR_BLANCO)) {
            view.getFormatoFiliacionDTO().setColorBlanco(true);
        }
        if (view.getIdColorPiel().equals(EnumColorPiel.COLOR_NEGRO)) {
            view.getFormatoFiliacionDTO().setColorNegro(true);
        }
        if (view.getIdColorPiel().equals(EnumColorPiel.COLOR_MORENO_CLARO)) {
            view.getFormatoFiliacionDTO().setColorMorenoClaro(true);
        }
        if (view.getIdColorPiel().equals(EnumColorPiel.COLOR_MORENO_OSCURO)) {
            view.getFormatoFiliacionDTO().setColorMorenoOscuro(true);
        }
        if (view.getIdColorPiel().equals(EnumColorPiel.COLOR_AMARILLO)) {
            view.getFormatoFiliacionDTO().setColorAmarillo(true);
        }
    }

    public void ajaxCabello() {
        view.getFormatoFiliacionDTO().setCabelloCastClaro(false);
        view.getFormatoFiliacionDTO().setCabelloCastOscuro(false);
        view.getFormatoFiliacionDTO().setCabelloNegro(false);
        view.getFormatoFiliacionDTO().setCabelloRubio(false);
        view.getFormatoFiliacionDTO().setCabelloRojo(false);
        view.getFormatoFiliacionDTO().setCabelloAlbino(false);
        view.getFormatoFiliacionDTO().setCabelloEntrecano(false);
        view.getFormatoFiliacionDTO().setCabelloCano(false);

        if (view.getIdCabello().equals(EnumCabello.CABELLO_CASTANIO_CLARO)) {
            view.getFormatoFiliacionDTO().setCabelloCastClaro(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_CASTANIO_OSCURO)) {
            view.getFormatoFiliacionDTO().setCabelloCastOscuro(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_NEGRO)) {
            view.getFormatoFiliacionDTO().setCabelloNegro(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_RUBIO)) {
            view.getFormatoFiliacionDTO().setCabelloRubio(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_ROJO)) {
            view.getFormatoFiliacionDTO().setCabelloRojo(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_ALBINO)) {
            view.getFormatoFiliacionDTO().setCabelloAlbino(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_ENTRECANO)) {
            view.getFormatoFiliacionDTO().setCabelloEntrecano(true);
        }
        if (view.getIdCabello().equals(EnumCabello.CABELLO_CANO)) {
            view.getFormatoFiliacionDTO().setCabelloCano(true);
        }
    }

    public void ajaxFrente() {
        view.getFormatoFiliacionDTO().setFrentePequenia(false);
        view.getFormatoFiliacionDTO().setFrenteMediana(false);
        view.getFormatoFiliacionDTO().setFrenteGrande(false);

        if (view.getIdFrente().equals(EnumFrente.FRENTE_PEQUENIA)) {
            view.getFormatoFiliacionDTO().setFrentePequenia(true);
        }
        if (view.getIdFrente().equals(EnumFrente.FRENTE_MEDIANA)) {
            view.getFormatoFiliacionDTO().setFrenteMediana(true);
        }
        if (view.getIdFrente().equals(EnumFrente.FRENTE_GRANDE)) {
            view.getFormatoFiliacionDTO().setFrenteGrande(true);
        }
    }

    public void ajaxCejas() {
        view.getFormatoFiliacionDTO().setCejasAbundantes(false);
        view.getFormatoFiliacionDTO().setCejasEscasas(false);
        view.getFormatoFiliacionDTO().setCejasRegulares(false);

        if (view.getIdCeja().equals(EnumCeja.CEJAS_ABUNDANTES)) {
            view.getFormatoFiliacionDTO().setCejasAbundantes(true);
        }
        if (view.getIdCeja().equals(EnumCeja.CEJAS_ESCASAS)) {
            view.getFormatoFiliacionDTO().setCejasEscasas(true);
        }
        if (view.getIdCeja().equals(EnumCeja.CEJAS_REGULARES)) {
            view.getFormatoFiliacionDTO().setCejasRegulares(true);
        }
    }

    public void ajaxOjos() {
        view.getFormatoFiliacionDTO().setOjosAzules(false);
        view.getFormatoFiliacionDTO().setOjosVerdes(false);
        view.getFormatoFiliacionDTO().setOjosCastClaro(false);
        view.getFormatoFiliacionDTO().setOjosCastOscuro(false);
        view.getFormatoFiliacionDTO().setOjosPardos(false);
        view.getFormatoFiliacionDTO().setOjosVerdosos(false);
        view.getFormatoFiliacionDTO().setOjosNegros(false);

        if (view.getIdOjos().equals(EnumOjos.OJOS_AZULES)) {
            view.getFormatoFiliacionDTO().setOjosAzules(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_VERDES)) {
            view.getFormatoFiliacionDTO().setOjosVerdes(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_CASTANIO_CLARO)) {
            view.getFormatoFiliacionDTO().setOjosCastClaro(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_CASTANIO_OSCURO)) {
            view.getFormatoFiliacionDTO().setOjosCastOscuro(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_PARDOS)) {
            view.getFormatoFiliacionDTO().setOjosPardos(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_VERDOSOS)) {
            view.getFormatoFiliacionDTO().setOjosVerdosos(true);
        }
        if (view.getIdOjos().equals(EnumOjos.OJOS_NEGROS)) {
            view.getFormatoFiliacionDTO().setOjosNegros(true);
        }
    }

    public void ajaxNariz() {
        view.getFormatoFiliacionDTO().setNarizConvexa(false);
        view.getFormatoFiliacionDTO().setNarizConcava(false);
        view.getFormatoFiliacionDTO().setNarizRectilinea(false);

        if (view.getIdNariz().equals(EnumNariz.NARIZ_CONVEXA)) {
            view.getFormatoFiliacionDTO().setNarizConvexa(true);
        }
        if (view.getIdNariz().equals(EnumNariz.NARIZ_CONCAVA)) {
            view.getFormatoFiliacionDTO().setNarizConcava(true);
        }
        if (view.getIdNariz().equals(EnumNariz.NARIZ_RECTILINEA)) {
            view.getFormatoFiliacionDTO().setNarizRectilinea(true);
        }
    }

    public void ajaxBoca() {
        view.getFormatoFiliacionDTO().setBocaPequenia(false);
        view.getFormatoFiliacionDTO().setBocaRegular(false);
        view.getFormatoFiliacionDTO().setBocaGrande(false);

        if (view.getIdBoca().equals(EnumBoca.BOCA_PEQUENIA)) {
            view.getFormatoFiliacionDTO().setBocaPequenia(true);
        }
        if (view.getIdBoca().equals(EnumBoca.BOCA_REGULAR)) {
            view.getFormatoFiliacionDTO().setBocaRegular(true);
        }
        if (view.getIdBoca().equals(EnumBoca.BOCA_GRANDE)) {
            view.getFormatoFiliacionDTO().setBocaGrande(true);
        }
    }

    public void validatorConsulta(FacesContext context, UIComponent component,
            Object value) {
        String nombreComponete = component.getId();

        switch (nombreComponete) {
            case "criterio":
                String criterio = (String) value;

                if (ValidacionUtil.esCadenaVacia(criterio)) {
                    FacesMessage facesMessage = new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "",
                            "Por favor ingrese un criterio de búsqueda.");
                    context.addMessage(component.getClientId(), facesMessage);
                    throw new ValidatorException(facesMessage);
                } else {
                    if (criterio.trim().length() < 5) {
                        FacesMessage facesMessage = new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "",
                                "Por favor ingrese un criterio de búsqueda mayor a 4 letras.");
                        context.addMessage(component.getClientId(),
                                facesMessage);
                        throw new ValidatorException(facesMessage);
                    }
                }

                break;
            default:
                JSFUtils.errorMessage("Error: ", "Validar criterio");
                break;
        }
    }

    public void regresar() {
        try {
            JSFUtils.redireccionar(
                    "/siayf-rh/contenido/empleado/formatoFiliacion.xhtml?faces-redirect=true");
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public FormatoFiliacionView getView() {
        return view;
    }

    public void setView(FormatoFiliacionView view) {
        this.view = view;
    }

    /**
     * @return the urlReporte
     */
    public String getUrlReporte() {
        return urlReporte;
    }

    /**
     * @param urlReporte
     *            the urlReporte to set
     */
    public void setUrlReporte(String urlReporte) {
        this.urlReporte = urlReporte;
    }

}
