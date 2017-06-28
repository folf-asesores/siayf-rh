package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

import mx.gob.saludtlax.rh.persistencia.ComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;
import mx.gob.saludtlax.rh.persistencia.DeduccionComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.HoraExtraComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.IncapacidadComprobanteRepository;
import mx.gob.saludtlax.rh.persistencia.PercepcionComprobanteRepository;

@Singleton
public class TimbrarNominaEJB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8961351308435273086L;

	@Inject
	private ComprobanteRepository comprobanteEstatalDAO;

	@Inject
	private TimbradoService timbradoService;

	private boolean timbrando = false;

	private Integer numeroNominasEnviadas = 0;

	public Long totalNominasPorTimbrar() {
		Long totalNominasPorTimbrar;
		totalNominasPorTimbrar = comprobanteEstatalDAO.totalNominasPorTimbrar();
		return totalNominasPorTimbrar;
	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	public void procesarNominaEstatal() {

		if (timbrando == false) {
			timbrando = true;
			List<ComprobanteEntity> comprobanteEstatalList = comprobanteEstatalDAO.obtenerLista();

			for (ComprobanteEntity comprobanteEstatal : comprobanteEstatalList) {

				//if (ValidarCamposComprobanteEntity.esValido(comprobanteEstatal)) {
					timbradoService.generarCFDI2(comprobanteEstatal);
					numeroNominasEnviadas++;
			/*	} else {
					System.out.println("El registro  con id :" + comprobanteEstatal.getIdComprobante() + " "
							+ comprobanteEstatal.getrFC()
							+ " no cuenta con los datos minimos del empleado para timbrar");
				}*/

			}
			timbrando = false;
		}

	}

	public boolean isTimbrando() {
		return timbrando;
	}

	public void setTimbrando(boolean timbrando) {
		this.timbrando = timbrando;
	}

	public Integer getNumeroNominasEnviadas() {
		return numeroNominasEnviadas;
	}

	public void setNumeroNominasEnviadas(Integer numeroNominasEnviadas) {
		this.numeroNominasEnviadas = numeroNominasEnviadas;
	}

}