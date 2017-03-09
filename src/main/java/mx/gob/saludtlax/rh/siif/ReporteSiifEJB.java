package mx.gob.saludtlax.rh.siif;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.TransactionTimeout;
import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;

@Stateless
public class ReporteSiifEJB {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private ReporteSiifService reporteSiifService;

	public List<SiifBitacoraDTO> obtenerReporteSiifPorPeriodo(String periodo, Integer anio) {
		List<SiifBitacoraDTO> reporteSiifList = reporteSiifService.obtenerReporteSiifPorPeriodo(periodo, anio);
		return reporteSiifList;
	}

	@TransactionTimeout(value = 10, unit = TimeUnit.HOURS)
	public SiifBitacoraDTO procesarNominaTheosToSIIF(PaqueteEntradaFederalDTO paqueteEntrada) throws UnsupportedEncodingException {
		UploadedFile dat = paqueteEntrada.getDat();
		UploadedFile tra = paqueteEntrada.getTra();

		SiifBitacoraDTO bitacora = reporteSiifService.crearSiifBitacora(paqueteEntrada);
		bitacora = reporteSiifService.importarNominaTheosToSIIF(dat, tra, bitacora);
		bitacora = reporteSiifService.cambiarClaveConceptosTra(bitacora);
		bitacora = reporteSiifService.clasificarEncabezados(bitacora);
		bitacora = reporteSiifService.verificarDatos(bitacora);
		bitacora = reporteSiifService.asignarEncabezadosTrailers(bitacora);
		return bitacora;
	}
	
	public SiifBitacoraDTO calcularEncabezados(SiifBitacoraDTO bitacora) {
		bitacora = reporteSiifService.calcularEncabezados(bitacora);
		return bitacora;
	}

	public SiifBitacoraDTO procesarNominaContTheosToSIIF(PaqueteEntradaFederalDTO paqueteEntrada) {
		UploadedFile cont = paqueteEntrada.getCont();

		SiifBitacoraDTO bitacora = reporteSiifService.crearSiifBitacora(paqueteEntrada);		
		
		bitacora = reporteSiifService.importarNominaContSIIF(cont, bitacora, paqueteEntrada);
//		bitacora = reporteSiifService.cambiarClaveConceptosTra(bitacora);
		bitacora = reporteSiifService.clasificarEncabezadosContratos(bitacora);
//		bitacora = reporteSiifService.verificarDatos(bitacora);
		bitacora = reporteSiifService.calcularTotalesEncabezadosContrato(bitacora);
		return bitacora;
	}
	
		

	public List<CuentaBancariaDTO> obtenerCuentaBancariaList() {
		return reporteSiifService.obtenerCuentaBancariaList();
	}

	public List<TipoNominaDTO> obtenerTipoNominaList() {
		return reporteSiifService.obtenerTipoNominaList();
	}

	public SiifBitacoraDTO obtenerSiifBitacora(SiifBitacoraDTO bitacoraDTO) {
		return reporteSiifService.obtenerSiiifBitacoraById(bitacoraDTO.getIdSiifBitacora());
	}

	public void deudores(Integer idSiifBitacora, Integer TipoNomina, String periodo) {
		reporteSiifService.deudores(idSiifBitacora, TipoNomina, periodo);
	}
	
	
	public void bitacora(Integer idSiifBitacora, Integer TipoNomina, String periodo) {
		reporteSiifService.eliminaBitacoraPorId(idSiifBitacora);
		//reporteSiifService.eliminaEncabezadoPorId(idSiifBitacora);
	
	}

	public List<EstructuraNominaTrailersDTO> listaDeudores(Integer idSiifBitacora) {
		return reporteSiifService.listaDeudores(idSiifBitacora);
	}
	
	public List<EstructuraNominaTrailersDTO> listaDispersion(Integer idSiifBitacora) {
		return reporteSiifService.listaDispersion(idSiifBitacora);
	}

//	public List<SubfuenteFinanciamiento> obtenerSubfuenteFinanciamientoList() {
//		return reporteSiifService.obtenerSubfuenteFinanciamientoList();
//	}
	
	public List<SIIFEncabezadoDTO> generarEncabezadoSiif(Integer anio, String periodo) {		
		return reporteSiifService.generarEncabezadoSiif(anio, periodo);
	}
	
	public  SIIFEncabezadoDTO obtenerEncabezado(SIIFEncabezadoDTO encabezadoDTO){
		return reporteSiifService.obtenerEncabezadoPorId(encabezadoDTO.getIdSIIFEncabezado());
	}
	
	public SIIFEncabezadoDTO actualizarEncabezado(SIIFEncabezadoDTO encabezadoDTO){
		return reporteSiifService.actualizarEncabezado(encabezadoDTO);	
		
	}
	
	public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO,String periodo){
		reporteSiifService.estatusEncabezado(encabezadoDTO,periodo);
		//SiifBitacoraDTO dtoBitacora=reporteSiifService.obtenerSiiifBitacoraById(encabezadoDTO.getIdSIIFBitacora());
		//Integer qna=Integer.parseInt(dtoBitacora.getPeriodoAfectacion());
		Integer qna=Integer.parseInt(periodo);
		return reporteSiifService.actualizarCheques(encabezadoDTO,20);	
		
	}
	
	public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO, Integer idBitacora){
		SiifBitacoraDTO dtoBitacora=reporteSiifService.obtenerSiiifBitacoraById(idBitacora);
		Integer qna=Integer.parseInt(dtoBitacora.getPeriodoAfectacion());
		return reporteSiifService.actualizarCheques(encabezadoDTO,qna);	
		
	}
	
	
}