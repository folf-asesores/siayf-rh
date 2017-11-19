
package mx.gob.saludtlax.rh.nomina.importar.cfdi;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mx.gob.saludtlax.rh.excepciones.ImportarPaqueteNominaException;
import mx.gob.saludtlax.rh.sat.xml.nomina.paquete.PaqueteNomina;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */

@Stateless
public class ProcesarPaqueteNominaService implements Serializable {

    private static final long serialVersionUID = 1241850105746944795L;

    @Inject
    ComprobanteNominaService comprobanteNominaFactory;

    private Integer avanceImportarPaquete;

    private Boolean procesando;

    private Integer totalRegistros;

    private PaqueteNominaView paqueteNominaView;

    private InputStream paqueteNominaIn;

    private String pathXstl;

    public List<Future<ComprobanteNominaView>> procesar(InputStream paqueteNominaIn) throws InterruptedException {

        try {

            procesando = true;
            //paqueteNominaView = importar(paqueteNominaIn);
            procesando = false;
            return importar(paqueteNominaIn);

        } catch (ImportarPaqueteNominaException e) {

            e.printStackTrace();
            return null;
        }

    }

    public List<Future<ComprobanteNominaView>> importar(InputStream paqueteNomina) throws ImportarPaqueteNominaException {

        List<Future<ComprobanteNominaView>> listadoComprobanteNominaFuture = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PaqueteNomina.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PaqueteNomina paqueteNominaDTO = (PaqueteNomina) jaxbUnmarshaller.unmarshal(paqueteNomina);

            PaqueteNominaView paqueteNominaView = new PaqueteNominaView();
            paqueteNominaView.setId(paqueteNominaDTO.getId());
            paqueteNominaView.setTotalRegistros(paqueteNominaDTO.getTotalRegistros());

            List<ComprobanteNominaView> listadoComprobanteNominaView = new ArrayList<>();

            avanceImportarPaquete = 0;

            for (PaqueteNomina.ControlComprobante comprobanteNomina : paqueteNominaDTO.getControlComprobanteNomina()) {
                procesando = true;
                listadoComprobanteNominaFuture.add(comprobanteNominaFactory.tranformComprobanteToNomina(comprobanteNomina.getXmlComp(),
                        comprobanteNomina.getId(), comprobanteNomina.getNum()));

            }
            Integer totalHilo = listadoComprobanteNominaFuture.size();

            /*
             * while (totalHilo > 0) {
             *
             * for (Future<ComprobanteNominaView> comprobanteNominaViewFuture : listadoComprobanteNominaFuture) {
             *
             * if (comprobanteNominaViewFuture.isDone()) {
             * try {
             * listadoComprobanteNominaView.add(comprobanteNominaViewFuture.get());
             * } catch (InterruptedException | ExecutionException e) {
             * e.printStackTrace();
             * }
             * listadoComprobanteNominaFuture.remove(comprobanteNominaViewFuture);
             * totalHilo--;
             * break;
             * }
             *
             * }
             * }
             */

            procesando = false;

            paqueteNominaView.setComprobanteNominaView(listadoComprobanteNominaView);

            //return paqueteNominaView;
            return listadoComprobanteNominaFuture;

        } catch (JAXBException e) {

            /*
             * log.error(e.getMessage()); log.error(e.getCause());
             * log.error(e.getLocalizedMessage());
             * log.error(e.fillInStackTrace());
             */

            throw new ImportarPaqueteNominaException(e.getMessage());
        }
    }

    public Integer getAvanceImportarPaquete() {
        return avanceImportarPaquete;
    }

    public void setAvanceImportarPaquete(Integer avanceImportarPaquete) {
        this.avanceImportarPaquete = avanceImportarPaquete;
    }

    public Boolean getProcesando() {
        return procesando;
    }

    public void setProcesando(Boolean procesando) {
        this.procesando = procesando;
    }

    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public PaqueteNominaView getPaqueteNominaView() {
        return paqueteNominaView;
    }

    public void setPaqueteNominaView(PaqueteNominaView paqueteNominaView) {
        this.paqueteNominaView = paqueteNominaView;
    }

    public InputStream getPaqueteNominaIn() {
        return paqueteNominaIn;
    }

    public void setPaqueteNominaIn(InputStream paqueteNominaIn) {
        this.paqueteNominaIn = paqueteNominaIn;
    }

    public String getPathXstl() {
        return pathXstl;
    }

    public void setPathXstl(String pathXstl) {
        this.pathXstl = pathXstl;
    }

}
