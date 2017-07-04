/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.administracion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.util.JSFUtils;

/**
 * @author Leila Schiaffini Ehuan
 * @since 2016-10-20
 * 
 */
@ManagedBean(name = "cruce")
@ViewScoped
public class CruceInformacionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3207857583630130296L;

	@Inject
	private CruceInformacion cruceInformacion;

	@PostConstruct
	public void inicio() {

	}

	public void actualizarPerfilEmpleado() {
		cruceInformacion.actualizarPerfilEmpleado();
	}

	public void cruzarInformacion() {

		cruceInformacion.cruceGlobal();
	}

	public void crearPuestos() {
		cruceInformacion.crearPuestos();
	}

	public void cruzarEventual() {
		cruceInformacion.cruzarEventual();
	}

	public void cruzarVoluntarios() {
		cruceInformacion.cruzarVoluntarios();
	}

	public void importarSuplencias() {
		cruceInformacion.importarSuplencias();
	}

	public void crearEmpleados() {
		cruceInformacion.crearEmpleados();
	}

	public void cruzarLaboralSuplencias() {
		cruceInformacion.cruzarInformacionLaboralSuplencias();
	}

	public void cruzarSeguroPopular() {
		cruceInformacion.marcarSeguroPopular();
	}

	public void actualizarSeguroPopular() {
		cruceInformacion.actualizarSeguroPopular();
	}

	public void actualizarPuestos() {
		cruceInformacion.actualizarPuestos();
	}

	public void complementarSeguroPopular() {
		cruceInformacion.complementarSeguroPopular();
	}

	public void actualizarEstatusSuplentes() {
		String mensaje = cruceInformacion.actualizarEstatusSuplentes();
		JSFUtils.infoMessage("", mensaje);
	}

	public void cruzarSuplencias24() {
		cruceInformacion.cruzarSuplencias24();
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void crearEmpleadoEspecifico() {
		cruceInformacion.crearPuestoEmpleado();
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void agregarSuplentesFaltantes() {
		cruceInformacion.agregarSuplentesFaltantes();
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void inactivarContratosFederales() {
		cruceInformacion.inactivarContratosFederales();
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void actualizarEstatal() {
		cruceInformacion.cruzarContratoEstatal();
		;
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void altasBajas() {
		cruceInformacion.altasBajas();
		JSFUtils.infoMessage("", "Proceso finalizado");
	}

	public void procesarPlantillaAutorizada() {
		cruceInformacion.puestosPlantillaAutorizada();
		JSFUtils.infoMessage("", "Plantilla autorizada finalizada");
	}
	public void actualizarConfiguracionPresupuestal(){
		cruceInformacion.actualizarConfiguracionPresupuestal();
		JSFUtils.infoMessage("", "Plantilla autorizada finalizada");
	}
}
