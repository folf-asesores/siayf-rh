/*
 * AdministradorReportes.java
 * Creado el 23/oct/2016 8:06:11 PM
 * 
 */
 
package mx.gob.saludtlax.rh.reportes;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import mx.gob.saludtlax.rh.util.TipoArchivo;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


/**
 * Este managed bean ayuda en la visualización de los reportes.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 * @author Eduardo Mex
 */
@Named(value = "administradorReporte")
@ApplicationScoped
public class AdministradorReporteController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4798543890543233287L;
    private final AdministradorReportes administradorReportes;

    public AdministradorReporteController() {
        this.administradorReportes = new AdministradorReportes();
    }

    /**
     * @return the stream
     */
    public StreamedContent getStream() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String referencia = context.getExternalContext().getRequestParameterMap().get("referencia");

            if(!ValidacionUtil.esCadenaVacia(referencia) && "pdf".equals(administradorReportes.obtenerTipoReporte(referencia))) {
                byte[] bytesReporte = administradorReportes.obtenerReporte(referencia);
                ByteArrayInputStream bais = new ByteArrayInputStream(bytesReporte);

                return new DefaultStreamedContent(bais, TipoArchivo.PDF.getMIMEType(), administradorReportes.obtenerNombreReporte(referencia));
            } else {
                return new DefaultStreamedContent();
            }
        }
    }
}
