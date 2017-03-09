/**
 * 
 */
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.persistencia.DatosPersonalesEntity;
import mx.gob.saludtlax.rh.persistencia.DatosPersonalesRepository;
import mx.gob.saludtlax.rh.persistencia.SIIFDatosLaboralesEntity;
import mx.gob.saludtlax.rh.persistencia.SiifDatosLaboralesRepository;
import mx.gob.saludtlax.rh.siif.DatosPersonalesDTO;
import mx.gob.saludtlax.rh.siif.SiifDatosLaboralesDTO;

/**
 * @author Eduardo Mex
 *
 */
@Stateless
public class DatoPersonalLaboralEJB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4774051203841670223L;

	@Inject
	private DatosPersonalesRepository datosPersonalesRepository;
	@Inject
	private SiifDatosLaboralesRepository siifDatosLaboralesRepository;

	public void registrarListaDatoPersonal(List<DatosPersonalesDTO> data) {
		try {

			if (data.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}

			for (DatosPersonalesDTO dto : data) {

				DatosPersonalesEntity entity = new DatosPersonalesEntity();

				entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
				entity.setRfc(dto.getRfc());
				entity.setApellidoPaterno(dto.getApellidoPaterno());
				entity.setApellidoMaterno(dto.getApellidoMaterno());
				entity.setNombre(dto.getNombre());
				entity.setFechaNacimiento(dto.getFechaNacimiento());
				entity.setSexo(dto.getSexo());
				entity.setIdLocalidad(dto.getIdLocalidad());
				entity.setIdColonia(dto.getIdColonia());
				entity.setCalle(dto.getCalle());
				entity.setNumeroExterior(dto.getNumeroExterior());
				entity.setNumeroInterior(dto.getNumeroInterior());
				entity.setCodigoPostal(dto.getCodigoPostal());
				entity.setTelefono(dto.getTelefono());
				entity.setIdEstadoEmpleado(dto.getIdEstadoEmpleado());

				datosPersonalesRepository.crear(entity);

			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void registrarListaDatoLaboral(List<SiifDatosLaboralesDTO> data) {
		try {

			if (data.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}

			for (SiifDatosLaboralesDTO dto : data) {

				SIIFDatosLaboralesEntity entity = new SIIFDatosLaboralesEntity();

				entity.setIdEmpleadoDatosLaborales(dto.getIdEmpleadoDatosLaborales());
				entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
				entity.setRfc(dto.getRfc());
				entity.setIdRfc(dto.getIdRfc());
				entity.setIdPlaza(dto.getIdPlaza());
				entity.setIdProyecto(dto.getIdProyecto());
				entity.setIdDependencia(dto.getIdDependencia());
				entity.setIdUnidadResponsable(dto.getIdUnidadResponsable());
				entity.setNombramiento(dto.getNombramiento());
				entity.setIdPuesto(dto.getIdPuesto());
				entity.setIdSindicato(dto.getIdSindicato());
				entity.setIdHabilitado(dto.getIdHabilitado());
				entity.setFechaIngreso(dto.getFechaIngreso());
				entity.setNoQuinquenios(dto.getNoQuinquenios());
				entity.setSueldoMensual(dto.getSueldoMensual());
				entity.setPercepcionComplementaria(dto.getPercepcionComplementaria());
				entity.setDespensa(dto.getDespensa());
				entity.setIncentivoAhorro(dto.getIncentivoAhorro());
				entity.setCompensacion(dto.getCompensacion());
				entity.setQuinquenio(dto.getQuinquenio());
				entity.setNoCuenta(dto.getNoCuenta());
				entity.setPolicia(dto.getPolicia());
				entity.setIdFuenteFinanciamiento(dto.getIdFuenteFinanciamiento());
				entity.setIdSubfuenteFinanciamiento(dto.getIdSubfuenteFinanciamiento());
				entity.setIdTipoRecurso(dto.getIdTipoRecurso());
				entity.setIdEstadoEmpleado(dto.getIdEstadoEmpleado());
				entity.setIdNomina(dto.getIdNomina());

				siifDatosLaboralesRepository.crear(entity);

			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
