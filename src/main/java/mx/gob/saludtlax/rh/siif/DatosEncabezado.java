package mx.gob.saludtlax.rh.siif;

import java.util.List;


public interface DatosEncabezado {
	
	List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezado(Integer idEncabezado);
	ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoSelect(ConsultaDatosEncabezadoDTO DatosEncabezadoSelect);
	ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoPorId(Integer idEmpeladoDatosPersonales);
	
}
