package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.PaqueteEntradaContratoDTO;
import mx.gob.saludtlax.rh.siif.ReportarSiifContratosService;
import mx.gob.saludtlax.rh.siif.SiifBitacoraDTO;



@Stateless
public class ReportarSiifContratosEJB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3204978182588936115L;

	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;	
	
	@Inject
	private ReportarSiifContratosService reportarSiifContratosService;	

	public void procesarNominaTheosToSIIF(UploadedFile dat) throws IOException {	
		
				
	}	
	

	public List<CuentaBancariaDTO> obtenerCuentaBancariaList() {
		return reportarSiifContratosService.obtenerCuentaBancariaList();
	}
	public List<TipoNominaDTO> obtenerTipoNominaList() {
		return reportarSiifContratosService.obtenerTipoNominaList();
	}


	public SiifBitacoraDTO obtenerSiifBitacora(SiifBitacoraDTO siifBitacoraProcesada) {
		return reportarSiifContratosService.obtenerSiiifBitacoraById(siifBitacoraProcesada.getIdSiifBitacora());
		
	}


	public SiifBitacoraDTO procesarContratosTheosToSIIF(PaqueteEntradaContratoDTO paqueteEntrada) {
		System.out.println(1);
		SiifBitacoraDTO bitacora = reportarSiifContratosService.crearSiifBitacoraContratos(paqueteEntrada);//revisar y crear bitacora con insercion correcta de datos
		System.out.println(2);
		bitacora = reportarSiifContratosService.importarDatosToSIIF(paqueteEntrada, bitacora);//Cambiar el proceso de insercion de datos a como lo uso
//		System.out.println(3);
//		bitacora = reportarSiifContratosService.cambiarClaveConceptosTraContratos(bitacora);// revisar
//		System.out.println(4);
//		bitacora = reportarSiifContratosService.clasificarEncabezadosContratos(bitacora);// REVISAR por que no esta metiendo todos los datos
//		System.out.println(5);
//		bitacora = reportarSiifContratosService.actualizarEncabezadosContratos(bitacora);
//		System.out.println(6);
//		bitacora = reportarSiifContratosService.verificarDatosContratos(bitacora);//revisar
//		System.out.println(7);
//		bitacora = reportarSiifContratosService.calcularTotalesEncabezadosContratos(bitacora);//Revisar si esta haciendo la insercion de todos los datos
		return bitacora;
	}
	

}