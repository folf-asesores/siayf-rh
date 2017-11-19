
package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;

import mx.gob.saludtlax.rh.excepciones.ImportarPaqueteNominaException;

@ManagedBean(name = "importarCFDIController")
@ViewScoped
public class ImportarCFDIController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8832526481251622355L;

    private static final Logger LOGGER = Logger
            .getLogger(ImportarPaqueteCFDIController.class.getName());

    private boolean mostrarTabla = false;

    @Inject
    ComprobanteNominaService comprobanteNominaService;

    List<Future<ComprobanteNominaView>> listadoComprobanteNominaFuture = new ArrayList<>();
    List<ComprobanteNominaView> listadoComprobanteNomina = new ArrayList<>();

    public void handleFileUpload(FileUploadEvent event) {
        try {
            {

                String comprobante = IOUtils
                        .toString(event.getFile().getInputstream(), "UTF-8");
                listadoComprobanteNominaFuture.add(comprobanteNominaService
                        .tranformComprobanteToNominaTimbrado(comprobante));
                event.getFile().getInputstream().close();
                mostrarTabla = true;

            }

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ImportarPaqueteNominaException e) {

            e.printStackTrace();
        }

    }

    public List<ComprobanteNominaView> getListadoComprobanteNomina() {

        /*
         * for (Future<ComprobanteNominaView> comprobanteNominaViewFuture : listadoComprobanteNominaFuture) {
         *
         * if (comprobanteNominaViewFuture.isDone()) {
         *
         * try {
         * listadoComprobanteNomina.add(comprobanteNominaViewFuture.get());
         *
         * } catch (InterruptedException | ExecutionException e) {
         * e.printStackTrace();
         * }
         *
         * }
         * }
         */

        return listadoComprobanteNomina;
    }

    public void setListadoComprobanteNomina(
            List<ComprobanteNominaView> listadoComprobanteNomina) {
        this.listadoComprobanteNomina = listadoComprobanteNomina;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

}
