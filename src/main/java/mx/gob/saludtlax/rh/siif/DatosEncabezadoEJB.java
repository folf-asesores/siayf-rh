package mx.gob.saludtlax.rh.siif;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;


@Stateless
public class DatosEncabezadoEJB implements Serializable{
	
	private static final long serialVersionUID = 648837261243804947L;
	
	@Inject
	private DatosEncabezadoServices datosEncabezadoService;
	
	
	public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezado(Integer idEncabezado) {
		return datosEncabezadoService.obtenerListaDatosEncabezado(idEncabezado);
	}
	
	
	public ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoSelect(ConsultaDatosEncabezadoDTO DatosEncabezadoSelect) {
		return null;
	}


	public ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoPorId(Integer idEmpeladoDatosPersonales) {
		ConsultaDatosEncabezadoDTO dto = datosEncabezadoService.obtenerDatosEncabezadoPorId(idEmpeladoDatosPersonales);
		return dto;
	}

	/** PROSPERA Y AFASPE **/
	public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoProspera(Integer idSiifEncabezado) {
		return datosEncabezadoService.obtenerListaDatosEncabezadoProspera(idSiifEncabezado);
	}
	
	public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoContrato(Integer idSiifEncabezado) {
		return datosEncabezadoService.obtenerListaDatosEncabezadoContrato(idSiifEncabezado);
	}



	public Integer obtenerCuentaBancaria(Integer idSiifEncabezado) {
		return datosEncabezadoService.obtenerCuentaBancaria(idSiifEncabezado);
	}
	
	
	
	

}
