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
		this.servicioEnum = servicio;
	}

	@PostConstruct
	public void consultarInformacion() {
		ServiciosRSEntity servicioRS;
		try {
			servicioRS = servicioWebEJB.getServicioActivo(servicioEnum);
			this.url_serivicio = servicioRS.getUrl();

			if (servicioRS.getPuerto() != null && servicioRS.getPuerto().length() > 0) {
				this.url_serivicio += ":" + servicioRS.getPuerto();
			}

			if (servicioRS.getContexto() != null && servicioRS.getContexto().length() > 0) {
				this.url_serivicio += "/" + servicioRS.getContexto();
			}

			this.usuario = servicioRS.getUsuario();
			this.clave = servicioRS.getClave();
			sinServicio = false;

		} catch (ServicioWebException e) {
			sinServicio = true;
		}

	}

}
