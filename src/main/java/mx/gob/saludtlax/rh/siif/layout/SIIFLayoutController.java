/*
 * SIIFLayoutController.java
 * Creado el 28/06/2016 12:32:13 AM
 *
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.JSFUtils;
import mx.gob.saludtlax.rh.util.TipoArchivo;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@ManagedBean(name = "generarLayoutController")
@SessionScoped
public class SIIFLayoutController implements Serializable {

    private static final long serialVersionUID = -2525785591722344961L;
    private static final Logger LOGGER = Logger
            .getLogger(SIIFLayoutController.class.getName());

    @Inject
    private SIIFLayout generarLayout;

    public void descargarZip() {

        byte[] bytes = generarLayout.getLayoutComoZip("20", 2015);

        try {
            JSFUtils.descargarArchivo(bytes, "layout", TipoArchivo.ZIP);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }
    }
}