
package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

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

@ManagedBean(name = "timbrarNomina")
@ViewScoped
public class TimbrarNominaController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -540003861228520400L;

    @Inject
    TimbrarNominaEJB timbraNominaService;

    @Inject
    ServiciosWebEJB serviocWebEJB;

    private Long totalNominaPendientePorTimbrar;

    private boolean deshabilitar = true;

    public void init() {

        totalNominaPendientePorTimbrar = timbraNominaService.totalNominasPorTimbrar();

        if (totalNominaPendientePorTimbrar > 0 && !timbraNominaService.isTimbrando()) {
            deshabilitar = false;
        }
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getContextPath().toString();
        try {
            ServiciosRSEntity servicioRSEntity = serviocWebEJB.getServicioActivo(ServicioWebEnum.FACTURACION_ELECTRONICA);
            if (!servicioRSEntity.isProduccion()) {

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
                        "El servcio configurado como activo para este modulo es de pruebas consulte la <a href='" + url
                                + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            }

        } catch (ServicioWebException e1) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Servicio Web",
                    e1.getMessage() + ". Consulte la <a href='" + url + "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            deshabilitar = true;
        }

    }

    public String timbrar() {
        timbraNominaService.procesarNominaEstatal();
        return "timbrarNomina.xhtml?faces-redirect=true";
    }

    public Long getTotalNominaPendientePorTimbrar() {
        return totalNominaPendientePorTimbrar;
    }

    public void setTotalNominaPendientePorTimbrar(Long totalNominaPendientePorTimbrar) {
        this.totalNominaPendientePorTimbrar = totalNominaPendientePorTimbrar;
    }

    public boolean isDeshabilitar() {
        return deshabilitar;
    }

    public void setDeshabilitar(boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

}