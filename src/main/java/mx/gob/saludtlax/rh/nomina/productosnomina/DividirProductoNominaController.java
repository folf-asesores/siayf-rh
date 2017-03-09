/*
 * DividirProductoNominaController.java
 * Creado el 25/Dec/2016 2:40:20 PM
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import mx.gob.saludtlax.rh.seguridad.autenticacion.AutenticacionUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * Este ManagedBean controla la vista
 * /contenido/nomina/productos/dividirProductoNomina.xhtml.
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named(value = "dividirProductoNominaController")
@ViewScoped
public class DividirProductoNominaController implements Serializable {

    private static final long serialVersionUID = -5983498898625094712L;
    private static final Logger LOGGER = Logger.getLogger(DividirProductoNominaController.class.getName());

    @Inject private DividirNomina dividirNominaEjb;

    private DividirProductoNominaView view;

    /**
     * Creates a new instance of DividirProductoNominaController
     */
    public DividirProductoNominaController() {
        view = new DividirProductoNominaView();
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final Integer idProductoNomina = parameters.get("idProductoNomina") != null ? Integer.parseInt(parameters.get("idProductoNomina")) : 0;
//        HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        Integer idProductoNomina = session.getAttribute("idProductoNomina") != null ? (Integer) session.getAttribute("idProductoNomina") : 0;
//        session.removeAttribute("idProductoNomina");
        view.setIdProductoNomina(idProductoNomina);
        LOGGER.debugv("idProductoNomina :: {0}", view.getIdProductoNomina());
        List<DividirNominaDTO> productoNomina = dividirNominaEjb.obtenerProductoNomina(view.getIdProductoNomina());
        view.setProductoNomina(productoNomina);
    }
    
    public void dividirNomina() {
        DividirNominaFiltro filtro = new DividirNominaFiltro();
        filtro.setIdProductoNomina(view.getIdProductoNomina());
        filtro.setNombreProductoNomina(view.getNombreProductoNominaNuevo());
        List<String> rfc = new ArrayList<>();

        for (DividirNominaDTO dividirNominaDTO : view.getProductoNominaSeleccionado()) {
            rfc.add(dividirNominaDTO.getRfc());
        }

        filtro.setRfc(rfc);
        List<DividirNominaDTO> nuevoProductoNomina = dividirNominaEjb.dividirProductoNomina(filtro, AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario());
        view.setProductoNomina(nuevoProductoNomina);
    }

    public void cargarArchivoRfc(FileUploadEvent evento) {
        UploadedFile archivo = evento.getFile();
        LOGGER.debugv("Nombre del archivo: {0}", archivo.getFileName());
        try {
            List<String> listaRfc = obtenerListaRfc(archivo.getInputstream());
            Map<String, Integer> mapa = new HashMap<>();

            for (int indice = 0; indice < view.getProductoNomina().size(); indice++) {
                DividirNominaDTO dividirNominaDTO = view.getProductoNomina().get(indice);
                mapa.put(dividirNominaDTO.getRfc(), indice);
            }
            
            if (view.getProductoNominaSeleccionado().isEmpty()) {
                view.setProductoNominaSeleccionado(new ArrayList<DividirNominaDTO>());
            }

            for (String rfc : listaRfc) {
                if (mapa.containsKey(rfc)) {
                    Integer indice = mapa.get(rfc);
                    DividirNominaDTO dividirNominaDTO = view.getProductoNomina().get(indice);
                    view.getProductoNominaSeleccionado().add(dividirNominaDTO);
                }
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }
    
    private List<String> obtenerListaRfc(InputStream is) {
        List<String> listaRfc = new ArrayList<>();
        int linea = 1;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String rfc;
            while((rfc = br.readLine()) != null) {
                if(ValidacionUtil.validarRfc(rfc)) {
                    listaRfc.add(rfc);
                } else {
                    LOGGER.warnv("El formato del RFC \"{0}\" no es valido, en la línea {1}.", rfc, linea);
                }
                linea++;
            }
            Collections.sort(listaRfc);
        }  catch (IOException ex) {
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
        return listaRfc;
    }

    public DividirProductoNominaView getView() {
        return view;
    }

    public void setView(DividirProductoNominaView view) {
        this.view = view;
    }

}
