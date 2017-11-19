
package mx.gob.saludtlax.rh.ca;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

public class ClienteRest {

    protected String url_serivicio;
    protected String usuario;
    protected String clave;
    protected boolean sinServicio;
    protected ServicioWebEnum servicioEnum;

    @Inject
    ServiciosWebEJB servicioWebEJB;

    public ClienteRest(ServicioWebEnum servicio) {
        servicioEnum = servicio;
    }

    @PostConstruct
    public void consultarInformacion() {
        ServiciosRSEntity servicioRS;
        try {
            servicioRS = servicioWebEJB.getServicioActivo(servicioEnum);
            url_serivicio = servicioRS.getUrl();

            if (servicioRS.getPuerto() != null
                    && servicioRS.getPuerto().length() > 0) {
                url_serivicio += ":" + servicioRS.getPuerto();
            }

            if (servicioRS.getContexto() != null
                    && servicioRS.getContexto().length() > 0) {
                url_serivicio += "/" + servicioRS.getContexto();
            }

            usuario = servicioRS.getUsuario();
            clave = servicioRS.getClave();
            sinServicio = false;

        } catch (ServicioWebException e) {
            sinServicio = true;
        }

    }

}
