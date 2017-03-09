package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

@Stateless
public class CruceInformacionBean implements CruceInformacion {
	@Inject
	private CruceInformacionService cruceInformacionService;

	@Override
	public void cruceGlobal() {
		cruceInformacionService.globalEmpleados();

	}

	@Override
	public void actualizarPerfilEmpleado() {
		cruceInformacionService.actualizarPerfilEmpleado();

	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	@Override
	public void crearPuestos() {
		cruceInformacionService.crearPuestosEmpleados();

	}

	@Override
	public void cruzarEventual() {
		cruceInformacionService.cruzarEventual();

	}

	@Override
	public void cruzarVoluntarios() {
		cruceInformacionService.procesarVoluntarios();

	}

	@Override
	public void importarSuplencias() {
		cruceInformacionService.cruzarSuplencias();

	}

	@Override
	public void crearEmpleados() {
		cruceInformacionService.crearEmpleados();

	}

	@Override
	public void cruzarInformacionLaboralSuplencias() {
		cruceInformacionService.cruzarInformacionLaboralSuplentes();

	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	@Override
	public void marcarSeguroPopular() {
		cruceInformacionService.marcarSeguroPopular();

	}

	@Override
	public void actualizarSeguroPopular() {
		cruceInformacionService.actualizarSeguroPopular();

	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	@Override
	public void actualizarPuestos() {
		cruceInformacionService.actualizarPuestos();

	}

	@Override
	public void complementarSeguroPopular() {
		cruceInformacionService.complementarSeguroPopular();

	}

	@Override
	public String actualizarEstatusSuplentes() {
		return cruceInformacionService.actualizarEstatusSuplentes();
	}

	@Override
	public void cruzarSuplencias24() {
		cruceInformacionService.cruzarSuplencias24();

	}

	@Override
	public void crearPuestoEmpleado() {
		cruceInformacionService.crearPuestoEmpleado();

	}

	@Override
	public void agregarSuplentesFaltantes() {
		cruceInformacionService.agregarSuplentesFaltantes2();
	}

	@Override
	public void inactivarContratosFederales() {
		cruceInformacionService.inactivarContratosFederales();

	}

	@Override
	public void cruzarContratoEstatal() {
		cruceInformacionService.actualizarContratoEstatal();

	}

	@Override
	public void altasBajas() {
		cruceInformacionService.altasBajasEstatal();

	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	@Override
	public void puestosPlantillaAutorizada() {
		cruceInformacionService.puestosPlantillaAutorizada();

	}

}
