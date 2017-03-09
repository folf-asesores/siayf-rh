package mx.gob.saludtlax.rh.areas;

import java.util.List;

public interface Areas {

	void crearArea(AreaDTO areaDTO);

	void editarArea(AreaDTO areaDTO);
	
	Boolean eliminarArea(Integer idArea);
	
	List<AreaDTO> obtenerAreas();
	
}
