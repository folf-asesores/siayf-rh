package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;

@Stateless
public class SiifLaboralesSubfuentesEJB {
	@Inject
	private SiifLaboralesSubfuentesService service;
	
//	Listas para Estructura Nomina Datos
	
	public List<SiifLaboralesSubfuentesDTO> obtenerSiifLaboralesSubfuentes() {
		List<SiifLaboralesSubfuentesDTO> SiifLaboralesSubfuentesLista = service.listaSiifLaboralesSubfuentes();
		return SiifLaboralesSubfuentesLista;
	}

	public SiifLaboralesSubfuentesDTO obtenerSiifLaboralesSubfuentesListaPorId(SiifLaboralesSubfuentesDTO DTO) {
		SiifLaboralesSubfuentesDTO dto = service.obtenerSiifLaboralesSubfuentesPorId(DTO.getIdSiifDatosLaborales());
		return dto;
	}
	
	
//	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Datos
	
	public SiifLaboralesSubfuentesDTO nuevoDatos() {
		return service.nuevasSubfuentes();
	}

	public void eliminarDatos(SiifLaboralesSubfuentesDTO dto) {
		service.eliminarSiifLAborlaesSubfuente(dto);
	}

	public SiifLaboralesSubfuentesDTO obtenerDatos(SiifLaboralesSubfuentesDTO dtoSelect) {
		SiifLaboralesSubfuentesDTO dto = service.obtenerSiifLaboralesSubfuentesPorId(dtoSelect.getIdSiifLaboralesSubfuentes());
		return dto;
	}

	public void crearDatos(SiifLaboralesSubfuentesDTO dto) {
		service.crearSubfuentes(dto);		
	}

	public void actualizarDatos(SiifLaboralesSubfuentesDTO estructuraNomina, Integer idDL) {
		service.actualizarDatos(estructuraNomina, idDL);
	}
	
	
//	Otras Listas
	
	public List<FuenteFinanciamientoDTO> obtenerFuentesF(){
		return service.listaFuenteFinanciamiento();
	}
	public List<SubfuenteFinanciamientoDTO> obtenerSubfuentesF(){
		return service.listaSubfuenteFinanciamiento();
	}
	public List<SubfuenteFinanciamientoDTO> obtenerSubfuentesFPorId(Integer dto){
		return service.listaSubfuenteFinanciamientoPorIdFF(dto);
	}
}