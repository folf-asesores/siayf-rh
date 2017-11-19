/*
 * AperturaNominaRfcController.java
 * Creado el 03/Jan/2017 9:02:22 AM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;

import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * Este ManagedBean administra la vista
 * contenido/nomina/productos/abrirProductoNominaRfc.xhtml.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "aperturaNominaRfcController")
@ViewScoped
public class AperturaNominaRfcController implements Serializable {

    private static final long serialVersionUID = 6898204281387972710L;
    private static final Logger LOGGER = Logger
            .getLogger(AperturaNominaRfcController.class.getName());

    @Inject
    private AperturaNominaRfc aperturaNominaRfcEjb;

    private List<String> listaRfc;
    private Integer idProductoNomina;
    private Integer idUsuario;
    private Integer idBitacora;
    private AperturaNominaRfcBitacoraDTO bitacora;

    /**
     * Creates a new instance of AperturaNominaRfcController
     */
    public AperturaNominaRfcController() {
        listaRfc = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String idProductoNominaStr = params.get("idProductoNomina");
        idProductoNomina = idProductoNominaStr != null
                ? Integer.parseInt(idProductoNominaStr) : 0;
        idUsuario = AutenticacionUtil.recuperarUsuarioSesion() != null
                ? AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario() : 0;
        idBitacora = aperturaNominaRfcEjb.obtenerIdBitacora(idUsuario);
    }

    public void abrirProductoNomina() {
        aperturaNominaRfcEjb.abrirProductoNomina(idProductoNomina, listaRfc,
                idBitacora);
        FacesMessage message = new FacesMessage("Producto de nómina aperturado",
                "El producto de nómina se ha aperturado correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        actualizarBitacora();
    }

    public void procesarArchivo(FileUploadEvent event) {
        try {
            aperturaNominaRfcEjb.registrarEnBitacoraEventoInformacion(
                    idBitacora, "Archivo cargado: {0}",
                    event.getFile().getFileName());
            obtenerListaRfc(event.getFile().getInputstream());
            FacesMessage message = new FacesMessage(
                    "Archivo cargado correctamente",
                    "Se han cargado " + listaRfc.size() + " RFC correctamente");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        actualizarBitacora();
    }

    private void obtenerListaRfc(InputStream is) {
        if (listaRfc == null) {
            listaRfc = new ArrayList<>();
        } else if (!listaRfc.isEmpty()) {
            listaRfc.clear();
        }

        int linea = 1;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(is))) {
            String rfc;
            while ((rfc = br.readLine()) != null) {
                if (ValidacionUtil.validarRfc(rfc)) {
                    listaRfc.add(rfc);
                } else {
                    aperturaNominaRfcEjb.registrarEnBitacoraEventoError(
                            idBitacora,
                            "El formato del RFC \"{0}\" no es valido, en la línea {1}.",
                            rfc, linea);
                }
                linea++;
            }
            Collections.sort(listaRfc);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    LOGGER.error(ex);
                }
            }
        }
    }

    public String obtenerIcono(AperturaNominaRfcBitacoraCategoria categoria) {
        switch (categoria) {
            case INFORMACION:
                return "ui-icon  ui-icon-info fm-font-color-blue";
            case ADVERTENCIA:
                return "ui-icon  ui-icon-notice fm-font-color-yellow";
            case ERROR:
                return "ui-icon  ui-icon-alert fm-font-color-red";
            default:
                return "";
        }
    }

    private void actualizarBitacora() {
        bitacora = aperturaNominaRfcEjb.obtenerBitacora(idBitacora);
    }

    public List<String> getListaRfc() {
        return listaRfc;
    }

    public void setListaRfc(List<String> listaRfc) {
        this.listaRfc = listaRfc;
    }

    /**
     * Get the value of bitacora
     *
     * @return the value of bitacora
     */
    public AperturaNominaRfcBitacoraDTO getBitacora() {
        return bitacora;
    }

    /**
     * Set the value of bitacora
     *
     * @param bitacora
     *            new value of bitacora
     */
    public void setBitacora(AperturaNominaRfcBitacoraDTO bitacora) {
        this.bitacora = bitacora;
    }

}
