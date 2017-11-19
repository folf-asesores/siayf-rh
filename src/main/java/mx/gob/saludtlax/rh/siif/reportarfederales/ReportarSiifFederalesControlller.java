
package mx.gob.saludtlax.rh.siif.reportarfederales;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "reportarSiifFederales")
@ViewScoped
public class ReportarSiifFederalesControlller {
    private static final Logger LOGGER = Logger
            .getLogger(ReportarSiifFederalesControlller.class.getName());

    @Inject
    private ReportarSiifFederalesEJB ejb;
    private ReportarSiifFederalesView view;

    @PostConstruct
    public void initController() {
        view = new ReportarSiifFederalesView();
    }

    public String cancelar() {
        return "/contenido/siif/reportarSiif.xhtml?faces-redirect=true";
    }

    public String procesarPrenomina() {
        System.out.println("view.getDat():: " + view.getDat());
        System.out.println("view.getTra():: " + view.getTra());

        boolean eval = true;
        if (view.getDat() == null
                || StringUtils.isEmpty(view.getDat().getFileName())) {
            JSFUtils.errorMessage("Archivo Requerido",
                    "El Archivo DAT es requerido, El Archivo debe tener la extención *.dat");
            eval = false;
        }
        if (view.getTra() == null
                || StringUtils.isEmpty(view.getTra().getFileName())) {
            JSFUtils.errorMessage("Archivo Requerido",
                    "El Archivo TRA es requerido, El Archivo debe tener la extención *.tra");
            eval = false;
        }

        if (eval) {
            System.out
                    .println("view.getDat():: " + view.getDat().getFileName());
            System.out
                    .println("view.getTra():: " + view.getTra().getFileName());

            LOGGER.info(view.getDat().getFileName());
            LOGGER.info(view.getTra().getFileName());

            ejb.procesarNominaTheosToSIIF(view.getDat(), view.getTra());
            try {
                ejb.procesarNominaTheosToSIIF(view.getDat(), view.getTra());
                JSFUtils.infoMessage(
                        "En este momento ha terminado de subir el archivo y se empieza a procesar",
                        "En este momento ha terminado de subir el archivo y se empieza a procesar");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public ReportarSiifFederalesView getView() {
        return view;
    }

}
