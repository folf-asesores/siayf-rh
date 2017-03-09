package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.DatosPersonalesEntity;
import mx.gob.saludtlax.rh.persistencia.DatosPersonalesRepository;

public class ConsultaDatosService implements Serializable{
	
    private static final long serialVersionUID = 1135974749754039111L;
	
    @Inject
	private DatosPersonalesRepository datosPersonalesRepository;

		

	protected DatosPersonalesDTO obtenerDatosPersonal(String rfc) {
		DatosPersonalesDTO datoPersonal = datosPersonalesRepository.obtenerDatoPersonal(rfc);
		return datoPersonal;
	}
	
	protected List<DatosPersonalesDTO> obtenerListaDatosPersonal() {
		List<DatosPersonalesDTO>  datoPersonal = datosPersonalesRepository.obtenerListaDatoPersonal();
		return datoPersonal;
	}
	
	protected List<DatosPersonalesDTO> obtenerListaDatosPersonalesPorCriterio(String rfc) {
		List<DatosPersonalesDTO> datoPersonal = datosPersonalesRepository.obtenerlistaDatosPersonalesPorCriterio(rfc);
		return datoPersonal;
	}
	
	
	
//	<<<CLAE (Creacion-Lectura-Actualizacion-Eliminacion)>>>
	
	public DatosPersonalesDTO nuevosDatosPersonales() {
		DatosPersonalesDTO  dto = new DatosPersonalesDTO();
		dto.setRfc(null);
		dto.setIdEmpleadoDatosPersonales(null);
		dto.setApellidoPaterno(null);
		dto.setApellidoMaterno(null);
		dto.setNombre(null);
		dto.setFechaNacimiento(null);
		dto.setSexo(null);
		dto.setIdLocalidad(null);
		dto.setIdColonia(null);
		dto.setCalle(null);
		dto.setNumeroExterior(null);
		dto.setNumeroInterior(null);
		dto.setCodigoPostal(null);
		dto.setTelefono(null);
		dto.setIdEstadoEmpleado(null);
		return dto;
	}

	public DatosPersonalesDTO crearDatosPersonales(DatosPersonalesDTO dto) {
		DatosPersonalesEntity entity = new DatosPersonalesEntity();
		entity.setRfc(dto.getRfc());
		entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
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
		return datosPersonalesRepository.obtenerDatosPersonalesPorId(entity.getIdDatoPersonal());
	}

	public DatosPersonalesDTO actualizarDatosPersonales(DatosPersonalesDTO dto) {
		DatosPersonalesEntity entity = datosPersonalesRepository.obtenerPorId(dto.getIdDatoPersonal());
		entity.setRfc(dto.getRfc());
		entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
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
		datosPersonalesRepository.actualizar(entity);
		return datosPersonalesRepository.obtenerDatosPersonalesPorId(entity.getIdDatoPersonal());
	}
	
	protected void eliminarDatosPersonales(DatosPersonalesDTO dto) {
		
	}
	
	public DatosPersonalesDTO obtenerDatosPersonalesPorId(Integer idDatoPersonal) {
				 
		return datosPersonalesRepository.obtenerDatosPersonalesPorId(idDatoPersonal);
	}
	
	public int verificaDatosPersonalesPorId(Integer idDatoPersonal) {
		 
		return datosPersonalesRepository.verificaDatosPersonalesPorId(idDatoPersonal);
	}
	
		
		
}