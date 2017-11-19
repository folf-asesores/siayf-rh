
package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "timbrarProductoNominaController")
@ViewScoped
public class TimbradoProductoNominaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5110142312031288519L;

    @Inject
    TimbradoProductoNominaService timbradoProductoNominaService;
    @Inject
    ServiciosWebEJB servicioWebEJB;

    private List<ProductoNominaDTO> listadoProductosNominaDTO;

    @PostConstruct
    public void init() {

        try {
            ServiciosRSEntity servicioRSEntity = servicioWebEJB
                    .getServicioActivo(ServicioWebEnum.FACTURACION_ELECTRONICA);
            if (!servicioRSEntity.isProduccion()) {
                HttpServletRequest req = (HttpServletRequest) FacesContext
                        .getCurrentInstance().getExternalContext().getRequest();
                String url = req.getContextPath().toString();
                FacesMessage facesMessage = new FacesMessage(
                        FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
                        "El servcio configurado como activo para este modulo es de pruebas consulte la <a href='"
                                + url
                                + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
                FacesContext.getCurrentInstance().addMessage(null,
                        facesMessage);

            }

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, e1.getMessage(),
                    e1.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        listadoProductosNominaDTO = timbradoProductoNominaService
                .listadoNominasPendientesPorTimbrar();

    }

    public void timbrarNomina(int idNominaSeleccionada) {

        timbradoProductoNominaService
                .timbrarProductoNominina(idNominaSeleccionada);
    }

    public List<ProductoNominaDTO> getListadoProductosNominaDTO() {
        return listadoProductosNominaDTO;
    }

    public void setListadoProductosNominaDTO(
            List<ProductoNominaDTO> listadoProductosNominaDTO) {
        this.listadoProductosNominaDTO = listadoProductosNominaDTO;
    }

}
