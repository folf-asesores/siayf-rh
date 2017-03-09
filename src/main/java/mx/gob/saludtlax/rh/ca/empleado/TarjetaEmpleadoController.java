package mx.gob.saludtlax.rh.ca.empleado;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServicioWebException;
import mx.gob.saludtlax.rh.configuracion.serviciosweb.ServiciosWebEJB;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.persistencia.ServiciosRSEntity;
import mx.gob.saludtlax.rh.util.ServicioWebEnum;

@ManagedBean(name = "tarjetaEmpleadoController")
@ViewScoped
public class TarjetaEmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3897416574307292664L;

	@Inject
	ServiciosWebEJB serviocWebEJB;

	@Inject
	Empleado empleadoService;

	@Inject
	EmpleadoClientRest empleadoClienteRest;

	InfoEmpleadoDTO empleadoDTO;

	ConsultaTarjetaEmpleadoFormModel consultaTarjetaEmpleadoFormModel = new ConsultaTarjetaEmpleadoFormModel();

	private ScheduleModel datosTarjeta;

	private Integer retardo;

	private Integer retardoMayor = 0;

	private Integer falta ;

	private boolean mostraDetalle = false;

	public void init() {

		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		 * 
		 * try { String dateInString = "28-07-2016 08:20:56"; String
		 * dateOtString = "28-07-2016 16:00:56"; Date date =
		 * sdf.parse(dateInString); Date dateSal = sdf.parse(dateOtString);
		 * 
		 * Calendar cSchedStartCal =
		 * Calendar.getInstance(TimeZone.getTimeZone("UTC-6"));
		 * 
		 * 
		 * 
		 * DefaultScheduleEvent evento =new
		 * DefaultScheduleEvent("Asistencia",date,dateSal);
		 * 
		 * 
		 * datosTarjeta.addEvent(evento);
		 * 
		 * 
		 * } catch (ParseException e) {
		 * 
		 * e.printStackTrace(); }
		 */

		try {
			ServiciosRSEntity servicioRSEntity = serviocWebEJB.getServicioActivo(ServicioWebEnum.CONTROL_ASISTENCIA_RS);
			if (!servicioRSEntity.isProduccion()) {
				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				String url = req.getContextPath().toString();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Servicio en Modo Prueba",
						"El servcio configurado como activo para este modulo es de pruebas consulte la <a href='" + url
								+ "/contenido/configuracion/serviciosweb/index.xhtml'>configuracion</a>");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);

			}

		} catch (ServicioWebException e1) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e1.getMessage(), e1.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}

		datosTarjeta = new LazyScheduleModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8870625127333932828L;

			@Override
			public void loadEvents(Date start, Date end) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				if(falta == null){
					falta=0;
					retardo=0;
				}
				for (int i = 1; i < 25; i++) {

					Random rnd = new Random();
					Integer minuto = (int) (rnd.nextDouble() * 50);
					String minutoStr = minuto.toString();

					if (minutoStr.length() < 2) {
						minutoStr = "0" + minutoStr;
					}

					String minutoSalStr = minuto.toString();

					if (minutoSalStr.length() < 2) {
						minutoSalStr = "0" + minutoStr;
					}

					String dateInString = i + "-07-2016 08:" + minutoStr + ":50";
					String dateOtString = i + "-07-2016 16:" + minutoStr + ":50";
					Date date;
					Date dateSal;
					
					
					try {
						date = sdf.parse(dateInString);
						dateSal = sdf.parse(dateOtString);

						String incidencia = "";
						String style = "";
						if (minuto > 15 && minuto <= 40) {
							incidencia = "Retardo Menor";
							retardo++;
							style = "retardo";
						} else if (minuto > 40) {
							style = "falta";
							falta++;
							incidencia = "Falta Injustificada";
						}

						
						if (date.getDay() != 0 && date.getDay() != 6) {
							addEvent(new DefaultScheduleEvent("Entrada " + incidencia, date, date, style));
							addEvent(new DefaultScheduleEvent("Salida", dateSal, dateSal, style));
						}
					} catch (ParseException e) {

					}

				}
				String dateInString = "25-07-2016 00:00:56";
				String dateOtString = "31-07-2016 00:00:56";
				Date date;
				try {
					date = sdf.parse(dateInString);
					Date date2 = sdf.parse(dateOtString);
					DefaultScheduleEvent eventoT = new DefaultScheduleEvent("Vacaciones", date, date2, "emp1");
					eventoT.setAllDay(true);
					eventoT.setStyleClass("emp1");
					addEvent(eventoT);
				} catch (ParseException e) {

				}

			}
		};
		
		

	}

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public void buscarInformacionEmpleado() {
		mostraDetalle = true;

	}

	public List<InfoEmpleadoDTO> buscarEmpleadoAutoComplete(String query) {

		List<InfoEmpleadoDTO> listadoEmpleadoDTO = null;

		if (query == "") {
			query = ".";
		}

		if (query.length() > 4) {
			listadoEmpleadoDTO = empleadoService.consultaPorCriterio(query);
		}

		return listadoEmpleadoDTO;

	}

	public InfoEmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(InfoEmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}

	public ConsultaTarjetaEmpleadoFormModel getConsultaTarjetaEmpleadoFormModel() {
		return consultaTarjetaEmpleadoFormModel;
	}

	public void setConsultaTarjetaEmpleadoFormModel(ConsultaTarjetaEmpleadoFormModel consultaTarjetaEmpleadoFormModel) {
		this.consultaTarjetaEmpleadoFormModel = consultaTarjetaEmpleadoFormModel;
	}

	public ScheduleModel getDatosTarjeta() {
		return datosTarjeta;
	}

	public void setDatosTarjeta(ScheduleModel datosTarjeta) {
		this.datosTarjeta = datosTarjeta;
	}

	public Integer getRetardo() {
		return retardo;
	}

	public void setRetardo(Integer retardo) {
		this.retardo = retardo;
	}

	public Integer getFalta() {
		
		System.out.println("Falta"+falta);
		return falta;
	}

	public void setFalta(Integer falta) {
		this.falta = falta;
	}

	public boolean isMostraDetalle() {
		return mostraDetalle;
	}

	public void setMostraDetalle(boolean mostraDetalle) {
		this.mostraDetalle = mostraDetalle;
	}

}
