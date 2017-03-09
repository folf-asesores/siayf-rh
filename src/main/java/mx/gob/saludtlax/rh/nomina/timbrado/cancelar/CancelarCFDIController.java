package mx.gob.saludtlax.rh.nomina.timbrado.cancelar;

import java.io.File;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.excepciones.RESTClientException;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "cancelarCFDI")
@ViewScoped
public class CancelarCFDIController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5820970566551926608L;

	@Inject
	ServiciosWebEJB serviocWebEJB;
	@Inject
	CancelarCFDIService cancelarCFDIService;

	private boolean mostrarInformacion;

	private ComprobanteEntity comprobante;

	private String uuid;

	public void init() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String url = req.getContextPath().toString();
		try {
			ServiciosRSEntity servicioRSEntity = serviocWebEJB.getServicioActivo(ServicioWebEnum.CANCELACION_CFDI);
			if (!servicioRSEntity.isProduccion()) {

				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
						"El servcio configurado como activo para este modulo es de pruebas consulte la <a href='" + url
								+ "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);

			}

		} catch (ServicioWebException e1) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Servicio Web",
					e1.getMessage() + ". Consulte la <a href='" + url
							+ "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);

		}

	}

	public void buscarCFDI() {

		comprobante = cancelarCFDIService.buscarCFDICancelarPorUIID(uuid);

		if (comprobante == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Buscar CFDI",
					"No se encontro informacion del folio ingresado");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			mostrarInformacion = false;
		} else if (comprobante.isCancelado()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Buscar CFDI",
					"El comprobante ya se encuentra cancelado");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			mostrarInformacion = false;

		} else {
			mostrarInformacion = true;
		}

	}

	public void cancelar() {

		try {
			cancelarCFDIService.cancelar(uuid);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelar",
					"Se cancelo comprobante CFDI  con folio fiscal:" + uuid);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			uuid = "";
			mostrarInformacion = false;
		} catch (RESTClientException e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cancelar", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}

	public void cancelarMasivo() {

		String PATH_CANCELADO = "C:\\ArchivosXSLT\\xmlTimbrado";
		String files;
		File folder = new File(PATH_CANCELADO);
		File[] listOfFiles = folder.listFiles();

		System.out.println("total a cancelar" + listOfFiles.length);

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files = listOfFiles[i].getName();
				String[] partesUUID = files.split("-");

				String uuidCancelar = partesUUID[1] + "-" + partesUUID[2] + "-" + partesUUID[3] + "-" + partesUUID[4]
						+ "-" + partesUUID[5];

				System.out.println(uuidCancelar.subSequence(0, 36));
				String uuidParaCancelar= (String) uuidCancelar.subSequence(0, 36);
				try {
					cancelarCFDIService.cancelar(uuidParaCancelar);
				} catch (Exception e) {
					System.out.println("No se cancelo" + uuidCancelar.subSequence(0, 36));
				}
			}
		}
	}
	
	public void cancelarMasivosPorId(){
		
	}

	public boolean isMostrarInformacion() {
		return mostrarInformacion;
	}

	public void setMostrarInformacion(boolean mostrarInformacion) {
		this.mostrarInformacion = mostrarInformacion;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ComprobanteEntity getComprobante() {
		return comprobante;
	}

	public void setComprobante(ComprobanteEntity comprobante) {
		this.comprobante = comprobante;
	}

}
