
package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.util.JSFUtils;

@ManagedBean(name = "importarXMLPaqueteNomina")
@ViewScoped
public class ImportarPaqueteCFDIController {
    private static final Logger LOGGER = Logger.getLogger(ImportarPaqueteCFDIController.class.getName());

    @Inject
    ProcesarPaqueteNominaService procesarPaqueteNominaService;

    private ImportarCFDIView view;

    private Integer progreso;

    private UploadedFile uploadFile;

    private boolean mostrarBarraProgreso = false;

    private boolean habilitarProcesar = false;

    private boolean mostrarTabla = false;

    private boolean mostrarSubirArchivo = true;

    private Integer totalProcesos;

    private Integer completados;

    private Integer monitor;

    private List<ComprobanteNominaView> listadoComprobanteNominaView;

    List<Future<ComprobanteNominaView>> listadoNominasProcesando;

    @PostConstruct
    public void conceptosInit() {
        view = new ImportarCFDIView();
        completados = 0;
        monitor = 0;
        listadoComprobanteNominaView = new ArrayList<>();
    }

    public void test() throws InterruptedException, ExecutionException {

    }

    public void cargarPaqueteCfdi() {
        LOGGER.info(view.getFile().getFileName());

    }

    public void procesarXML() throws IOException, InterruptedException {

        listadoNominasProcesando = procesarPaqueteNominaService.procesar(uploadFile.getInputstream());
        totalProcesos = listadoNominasProcesando.size();
        // listadoComprobanteNominaView = paquete.getComprobanteNominaView();
        mostrarTabla = true;
    }

    public String irPrincipal() {
        view.panelPrincipal();
        return null;
    }

    public Integer getProgreso() {

        if (listadoNominasProcesando == null) {
            return 0;
        }

        Integer porcentaje = (completados * 100) / totalProcesos;

        System.out.println(monitor);
        if (monitor == 2) {

            System.out.println(monitor);
            completados = 0;

            for (Future<ComprobanteNominaView> comprobanteNominaViewFuture : listadoNominasProcesando) {

                if (comprobanteNominaViewFuture.isDone()) {
                    completados++;
                    /*
                     * try { listadoComprobanteNominaView.add(
                     * comprobanteNominaViewFuture.get()); completados++; }
                     * catch (InterruptedException | ExecutionException e) {
                     * e.printStackTrace(); } listadoNominasProcesando.remove(
                     * comprobanteNominaViewFuture);
                     */

                }

            }
            monitor = 0;

            return porcentaje;

        } else {

            monitor++;
            return porcentaje;
        }

        // return 0;
    }

    public void mostrarTablaFinal() {

        for (Future<ComprobanteNominaView> comprobanteNominaViewFuture : listadoNominasProcesando) {

            if (comprobanteNominaViewFuture.isDone()) {

                try {
                    listadoComprobanteNominaView.add(comprobanteNominaViewFuture.get());
                    completados++;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }

        //listadoComprobanteNominaView = procesarPaqueteNominaService.getPaqueteNominaView().getComprobanteNominaView();
        mostrarBarraProgreso = false;
        mostrarSubirArchivo = true;
        mostrarTabla = true;

    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public void handleFileUpload(FileUploadEvent event) {
        uploadFile = event.getFile();

        try {
            mostrarBarraProgreso = true;
            mostrarSubirArchivo = false;
            mostrarTabla = false;
            procesarXML();
            JSFUtils.infoMessage("Se ha empezado a importar los CFDI", "Se ha empezado a importar los CFDI");
            RequestContext.getCurrentInstance().execute("PF('pbLeerXML').start()");

        } catch (IOException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

    public UploadedFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadedFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public void procesar() {

    }

    public ImportarCFDIView getView() {
        return view;
    }

    public boolean isMostrarBarraProgreso() {
        return mostrarBarraProgreso;
    }

    public void setMostrarBarraProgreso(boolean mostrarBarraProgreso) {
        this.mostrarBarraProgreso = mostrarBarraProgreso;
    }

    public boolean isHabilitarProcesar() {
        return habilitarProcesar;
    }

    public void setHabilitarProcesar(boolean habilitarProcesar) {
        this.habilitarProcesar = habilitarProcesar;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public boolean isMostrarSubirArchivo() {
        return mostrarSubirArchivo;
    }

    public void setMostrarSubirArchivo(boolean mostrarSubirArchivo) {
        this.mostrarSubirArchivo = mostrarSubirArchivo;
    }

    public List<ComprobanteNominaView> getListadoComprobanteNominaView() {
        return listadoComprobanteNominaView;
    }

    public void setListadoComprobanteNominaView(List<ComprobanteNominaView> listadoComprobanteNominaView) {
        this.listadoComprobanteNominaView = listadoComprobanteNominaView;
    }

}