
package mx.gob.saludtlax.rh.presupuesto;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.reportes.AdministradorReportes;
import mx.gob.saludtlax.rh.seguridad.ConfiguracionConst;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.util.CadenaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

@ManagedBean(name = "distribucionPresupuesto")
@SessionScoped
public class DistribucionPresupuestoController {

    private DistribucionPresupuestoView view;

    @Inject
    private DistribucionPresupuestoEJB ejb;
    private static final Logger LOGGER = Logger
            .getLogger(DistribucionPresupuestoController.class.getName());

    @PostConstruct
    public void initConsultarPresupuesto() {
        view = new DistribucionPresupuestoView();
        view.setListaTipoNombramiento(ejb.getListaTipoNombramiento());
        view.setListaDependencia(ejb.getListaDependencia());
        view.setListaSubfuente(ejb.getListaSubfuente());
        view.setMostrarPrincipal(Boolean.TRUE);
        view.setMostrarDistribucion(Boolean.FALSE);
    }

    public String obtenerDistribucionesPresupuestales() {
        System.out.print("buscar");
        LOGGER.debugv("buscar\n");
        try {
            view.setListaDistribucion(ejb.distribucionPresupuesto(
                    view.getAnioPresupuesto(), view.getIdTipoNombramiento(),
                    view.getIdDependencia(), view.getIdSubfuente()));
            view.setMostrarPrincipal(true);
            LOGGER.debugv("Tamaño lista: {0}",
                    view.getListaDistribucion().size());
        } catch (ReglaNegocioException e) {
            view.setListaDistribucion(
                    new ArrayList<DistribucionPresupuestoDTO>());
            JSFUtils.infoMessage(e.getMessage(), "");
            view.setMostrarPrincipal(false);
            view.setMostrarOpcionDescarga(false);
            JSFUtils.infoMessage(e.getMessage(), "");
        }
        view.setMostrarDistribucion(false);
        return null;
    }

    public void descargarContrato() {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            HttpSession httpSession = request.getSession(false);
            UsuarioDTO usuario = (UsuarioDTO) httpSession.getAttribute(
                    ConfiguracionConst.SESSION_ATRIBUTE_LOGGED_USER);

            String[] parametros = { "ID_USUARIO",
                    String.valueOf(usuario.getIdUsuario()), "REPORTE_NOMBRE",
                    "reporte_distribucion_presupuestal", "TIPO_REPORTE", "xlsx",
                    "ANYO_PRESUPUESTO",
                    String.valueOf(view.getAnioPresupuesto()),
                    "ID_TIPO_NOMBRAMIENTO",
                    String.valueOf(view.getIdTipoNombramiento()), "DEPENDENCIA",
                    String.valueOf(view.getIdDependencia()),
                    "ID_SUBFUENTE_FINANCIAMIENTO",
                    String.valueOf(view.getIdSubfuente()) };

            AdministradorReportes admintradorReportes = new AdministradorReportes();
            String referencia = admintradorReportes
                    .obtenerReferencia(parametros);

            byte[] bytes = admintradorReportes.obtenerReporte(referencia);

            if (bytes != null) {
                JSFUtils.descargarArchivo(bytes,
                        CadenaUtil.converterSpace("Distribucion_Presupuestal"),
                        TipoArchivo.getMIMEType("xlsx"));
            }

            JSFUtils.infoMessage("Descargar Reporte: ",
                    "Se descargo correctamente...");

        } catch (NullPointerException | IllegalArgumentException
                | IOException exception) {

            exception.printStackTrace();
            JSFUtils.errorMessage("Error: ", exception.getMessage());
        } catch (ReglaNegocioException reglaNegocioException) {
            reglaNegocioException.printStackTrace();
            JSFUtils.errorMessage("Error: ",
                    reglaNegocioException.getMessage());
        } catch (ValidacionException validacionException) {

            validacionException.printStackTrace();
            JSFUtils.errorMessage("Error: ", validacionException.getMessage());
        }
    }

    public DistribucionPresupuestoView getView() {
        return view;
    }

    public void setView(DistribucionPresupuestoView view) {
        this.view = view;
    }
}
