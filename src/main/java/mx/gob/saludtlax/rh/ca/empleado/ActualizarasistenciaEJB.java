package mx.gob.saludtlax.rh.ca.empleado;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;
import javax.enterprise.inject.ResolutionException;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.AsistenciasEmpleadosRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;

@Stateless
public class ActualizarasistenciaEJB {

	@Inject
	private AsistenciasEmpleadosRepository asistenciasEmpleadosRepository;

	public Integer actualizarAsistencias(Date fechaIni, Date fechaFin,Integer tipoContratacion,Integer adscripcion) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			String fInicio = sdf.format(fechaIni);
			String fFin = sdf.format(fechaFin);
			
			Integer res =asistenciasEmpleadosRepository.actualizarAsistencias(fInicio, fFin, tipoContratacion, adscripcion);
			System.out.println("Estas son las asistencias actualizadas");
			return res;
		} catch (ResolutionException e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
