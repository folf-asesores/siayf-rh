/*
 *
 * VistaPreviaService.java
 * Creado el Jun 11, 2016 8:55:08 AM
 *
 */

package mx.gob.saludtlax.rh.expediente;

import static java.lang.Integer.parseInt;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static javax.faces.event.PhaseId.RENDER_RESPONSE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.saludtlax.rh.expediente.aspirante.AdjuntoAspirante;
import mx.gob.saludtlax.rh.expediente.empleado.AdjuntoEmpleado;

/**
 * @see http://stackoverflow.com/questions/8207325/display-dynamic-image-from-database-with-pgraphicimage-and-streamedcontent
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@ManagedBean
@ApplicationScoped
public class VistaPreviaService implements Serializable {

    private static final long serialVersionUID = 8026485966156871175L;

    @Inject
    private AdjuntoEmpleado adjuntoEmpleadoEJB;
    @Inject
    private AdjuntoAspirante adjuntoAspiranteEJB;

    /**
     * Este método permite obtener una imagen previa del archivo.
     *
     * Requiere como parametro POST o GET un entero <code>idAdjunto</code>
     *
     * @return un stream con la imagen.
     */
    public StreamedContent getVistaPreviaAdjuntoEmpleado() {
        return getVistaPreviaAdjunto(true);
    }

    /**
     * Este método permite obtener una imagen previa del archivo.
     *
     * Requiere como parametro POST o GET un entero <code>idAdjunto</code>
     *
     * @return un stream con la imagen.
     */
    public StreamedContent getVistaPreviaAdjuntoAspirante() {
        return getVistaPreviaAdjunto(false);
    }

    /**
     * Este método permite obtener una imagen previa del archivo.
     *
     * Requiere como parametro POST o GET un entero <code>idAdjunto</code>
     *
     * @return un stream con la imagen.
     */
    private StreamedContent getVistaPreviaAdjunto(boolean esEmpleado) {

        FacesContext context = getCurrentInstance();

        if (context.getCurrentPhaseId() == RENDER_RESPONSE) {
            // Como se está renderizando el HTML.
            // Devolver un trozo StreamedContent por lo que va a generar
            // URL correcta.
            return new DefaultStreamedContent();
        } else {
            // Ya que, el navegador solicita la imagen.
            // Se devolverá un StreamedContent real con los bytes de imagen.
            String idAdjuntoComoString = context.getExternalContext()
                    .getRequestParameterMap().get("idAdjunto");
            Integer idAdjunto = parseInt(idAdjuntoComoString);

            InformacionAdjuntoDTO informacionAdjunto;
            byte[] archivo;

            if (esEmpleado) {
                informacionAdjunto = adjuntoEmpleadoEJB
                        .obtenerInformacionAdjuntoPorIdAdjunto(idAdjunto);
                archivo = adjuntoEmpleadoEJB
                        .obtenerVistaPreviaPorIdAdjunto(idAdjunto);
            } else {
                informacionAdjunto = adjuntoAspiranteEJB
                        .obtenerInformacionAdjuntoPorIdAdjunto(idAdjunto);
                archivo = adjuntoAspiranteEJB
                        .obtenerVistaPreviaPorIdAdjunto(idAdjunto);
            }

            if (archivo != null) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(
                        archivo);
                StreamedContent image = new DefaultStreamedContent(inputStream,
                        informacionAdjunto.getExtension().getMIMEType(),
                        informacionAdjunto.getNombreAdjunto());
                return image;
            } else {
                InputStream is;

                switch (informacionAdjunto.getExtension()) {
                    case JPG:
                    case JPEG:
                        is = getCurrentInstance().getExternalContext()
                                .getResourceAsStream(
                                        "/resources/imagenes/icon-jpeg.png");
                        break;
                    case PNG:
                        is = getCurrentInstance().getExternalContext()
                                .getResourceAsStream(
                                        "/resources/imagenes/icon-png");
                        break;
                    case TIF:
                    case TIFF:
                        is = getCurrentInstance().getExternalContext()
                                .getResourceAsStream(
                                        "/resources/imagenes/icon-tiff.png");
                        break;
                    default:
                        is = getCurrentInstance().getExternalContext()
                                .getResourceAsStream(
                                        "/resources/imagenes/icon-file.png");
                        break;
                }

                StreamedContent image = new DefaultStreamedContent(is,
                        informacionAdjunto.getExtension().getMIMEType(),
                        informacionAdjunto.getNombreAdjunto());
                return image;
            }
        }
    }
}
