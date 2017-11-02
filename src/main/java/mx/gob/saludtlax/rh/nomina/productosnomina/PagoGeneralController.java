/*
 * PagoGeneralController.java
 * Creado el 25/Dic/2016 2:40:20 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import org.jboss.logging.Logger;

/**
 * Este ManagedBean controla la vista
 * /contenido/nomina/productos/pagoQuincena.xhtml.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Named(value = "pagoGeneralController")
@ViewScoped
public class PagoGeneralController implements Serializable {
    
    private static final long serialVersionUID = -5983498898625094712L;
    private static final Logger LOGGER = Logger.getLogger(PagoGeneralController.class.getName());
    
    private PagoGeneralView view;

    /**
     * Creates a new instance of PagoQuincenaController
     */
    public PagoGeneralController() {
        view = new PagoGeneralView();
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final Integer idProductoNomina = parameters.get("idProductoNomina") != null ? Integer.parseInt(parameters.get("idProductoNomina")) : 0;
//        HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        final Integer idProductoNomina = session.getAttribute("idProductoNomina") != null ? (Integer) session.getAttribute("idProductoNomina") : 0;
//        session.removeAttribute("idProductoNomina");
        view.setIdProductoNomina(idProductoNomina);
        LOGGER.debugv("idProductoNomina :: {0}", view.getIdProductoNomina());
    }
    
    
    public void agregarRfc() {
        String[] rfcs = view.getRfcTexto().split("\n");
        //List<String> listaRfc = Arrays.asList(rfcs);
        
        for(int indice = 0; indice < rfcs.length; indice++) {
            String rfc = rfcs[indice].trim();
            LOGGER.debugv("{0} : {1}", rfc, indice);
        }
    }

//    public void dividirNomina() {
//        DividirNominaFiltro filtro = new DividirNominaFiltro();
//        filtro.setIdProductoNomina(view.getIdProductoNomina());
//        filtro.setNombreProductoNomina(view.getNombreProductoNominaNuevo());
//        List<String> rfc = new ArrayList<>();

//        for (DividirNominaDTO dividirNominaDTO : view.getProductoNominaSeleccionado()) {
//            rfc.add(dividirNominaDTO.getRfc());
//        }

//        filtro.setRfc(rfc);
//        // Comentado temporalmente para probar que la excepción 
//        //List<DividirNominaDTO> nuevoProductoNomina = dividirNominaEjb.dividirProductoNomina(filtro, AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario());
//        List<DividirNominaDTO> nuevoProductoNomina = dividirNominaEjb.dividirProductoNomina(null, AutenticacionUtil.recuperarUsuarioSesion().getIdUsuario());
//        view.setProductoNomina(nuevoProductoNomina);
//    }
    
//    public void cargarArchivoRfc(FileUploadEvent evento) {
//        UploadedFile archivo = evento.getFile();
//        LOGGER.debugv("Nombre del archivo: {0}", archivo.getFileName());
//        try {
//            List<String> listaRfc = obtenerListaRfc(archivo.getInputstream());
//            Map<String, Integer> mapa = new HashMap<>();

//            for (int indice = 0; indice < view.getProductoNomina().size(); indice++) {
//                DividirNominaDTO dividirNominaDTO = view.getProductoNomina().get(indice);
//                mapa.put(dividirNominaDTO.getRfc(), indice);
//            }
            
//            if (view.getProductoNominaSeleccionado().isEmpty()) {
//                view.setProductoNominaSeleccionado(new ArrayList<DividirNominaDTO>());
//            }

//            for (String rfc : listaRfc) {
//                if (mapa.containsKey(rfc)) {
//                    LOGGER.debug(rfc);
//                    Integer indice = mapa.get(rfc);
//                    DividirNominaDTO dividirNominaDTO = view.getProductoNomina().get(indice);
//                    view.getProductoNominaSeleccionado().add(dividirNominaDTO);
//                }
//            }
//        } catch (IOException ex) {
//            LOGGER.error(ex);
//        }
//    }

//    private List<String> obtenerListaRfc(InputStream is) {
//        List<String> listaRfc = new ArrayList<>();
//        int linea = 1;

//        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//            String rfc;
//            while((rfc = br.readLine()) != null) {
//                if(ValidacionUtil.validarRfc(rfc)) {
//                    listaRfc.add(rfc);
//                } else {
//                    LOGGER.warnv("El formato del RFC \"{0}\" no es valido, en la línea {1}.", rfc, linea);
//                }
//                linea++;
//            }
//            Collections.sort(listaRfc);
//        }  catch (IOException ex) {
//            LOGGER.error(ex);
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException ex) {
//                    LOGGER.error(ex);
//                }
//            }
//        }
//        return listaRfc;
//    }
    
    /**
     * Get the value of view
     *
     * @return the value of view
     */
    public PagoGeneralView getView() {
        return view;
    }

    /**
     * Set the value of view
     *
     * @param view new value of view
     */
    public void setView(PagoGeneralView view) {
        this.view = view;
    }
    
}
