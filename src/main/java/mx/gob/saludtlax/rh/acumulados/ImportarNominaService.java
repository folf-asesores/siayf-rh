/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.acumulados;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.primefaces.model.UploadedFile;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ArchivosAcumuladosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaRepository;

import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatAcumEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersAcumEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;
import mx.gob.saludtlax.rh.siif.reportarcontratos.UploadExcelFileAnexo;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author Eduardo Mex
 * @email lic.eduardo_mex@hotmail.com
 * @version 1.0
 * @since 17:50:55 24/09/2016
 */
@Stateless
@LocalBean
public class ImportarNominaService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885382757164431919L;
	private UploadExcelFileAnexo uploadedFile;

	@Inject
	private TiposNombramientosRepository tipoNombramientoRepository;
	@Inject
	private ConceptoNominaRepository conceptoNominaRepository;

	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	public void importarNominaTheosToSIIF(UploadedFile dat, UploadedFile tra, String idContexto) {
		// int i = 0;
		try {
			// dat
			String valoresDat = new String(dat.getContents(), "UTF-8");
			StringTokenizer stDat = new StringTokenizer(valoresDat, "\n");
			// EstructuraNominaDatAcumEntity estructuraNominaEntity = null;

			while (stDat.hasMoreTokens()) {
				String dato = stDat.nextToken();
				String[] array = this.procesarArchivoDat(dato);
				// estructuraNominaEntity =
				// this.toEstructuraNominaEntity(array);
				// //
				// estructuraNominaEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
				// // i++;
				// estructuraNominaEntity.setIdContexto(idContexto);
				// //obteniendo id tipo nombramiento
				// estructuraNominaEntity.setIdNombramiento(
				// tipoNombramientoRepository.obtenerIdNombramientoPorClave(estructuraNominaEntity.getUr()));
				//
				// entityManager.persist(estructuraNominaEntity);
				crearEstructuraNomina(array, idContexto);
			}
			guardarlogArchivo(dat.getFileName(), idContexto);
			// if (estructuraNominaEntity != null) {
			// bitacora.setAnio(Integer.valueOf(estructuraNominaEntity.getAnioReal()));
			// bitacora.setPeriodo(estructuraNominaEntity.getQnaReal());
			// }
			// bitacora.setTotalNomina(i);

			// tra
			String valoresTra = new String(tra.getContents(), "UTF-8");
			StringTokenizer stTra = new StringTokenizer(valoresTra, "\n");
			
			while (stTra.hasMoreTokens()) {
				String dato = stTra.nextToken();
				String[] array = procesarArchivoTra(dato);
				// EstructuraNominaTrailersAcumEntity estructuraNominaTraEntity
				// = toEstructuraNominaTrailers(array);
				// //
				// estructuraNominaTraEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
				// estructuraNominaTraEntity
				// .setConceptoSiif(estructuraNominaTraEntity.getConcep() +
				// estructuraNominaTraEntity.getPtaAnt());
				// estructuraNominaTraEntity.setIdContexto(idContexto);
				// // obtiene el id concepto
				// estructuraNominaTraEntity.setIdConcepto(conceptoNominaRepository.obtenerIdConceptoPorTipoyConceptoSiif(
				// estructuraNominaTraEntity.gettConcep(),
				// estructuraNominaTraEntity.getConceptoSiif()));
				// //Obtiene el idEstructuraNomina
				//// estructuraNominaTraEntity.setIdEstructurasNominas(estructuraNominaRepository
				//// .obtenerIdEstructuraNominaPorRfcyIdContexto(estructuraNominaTraEntity.getRfc(),
				// idContexto));
				//
				// entityManager.persist(estructuraNominaTraEntity);
				crearEstructuraNominaTra(array, idContexto);
			}
			guardarlogArchivo(tra.getFileName(), idContexto);
			System.out.println("Se registraron los archivos correctamente....Dat: " + dat.getFileName() + " y tra: "
					+ tra.getFileName());
		} catch (UnsupportedEncodingException|PersistenceException e) {
			e.printStackTrace();
		}

	}

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void guardarlogArchivo(String nombreArchivo, String idContexto){
		ArchivosAcumuladosEntity archivo = new ArchivosAcumuladosEntity();
		archivo.setArchivo(nombreArchivo);
		archivo.setFechaCreacion(new Date());
		archivo.setIdContexto(idContexto);
		entityManager.persist(archivo);
		System.out.println("log archivo: "+ nombreArchivo +"--"+ idContexto);
	}
	
	
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearEstructuraNomina(String[] array, String idContexto) {
		EstructuraNominaDatAcumEntity estructuraNominaEntity = null;

		estructuraNominaEntity = this.toEstructuraNominaEntity(array);
		// estructuraNominaEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
		// i++;
		estructuraNominaEntity.setIdContexto(idContexto);
		// obteniendo id tipo nombramiento
		estructuraNominaEntity.setIdNombramiento(
				tipoNombramientoRepository.obtenerIdNombramientoPorClave(estructuraNominaEntity.getUr()));

		entityManager.persist(estructuraNominaEntity);
		System.out.println("Guardo estructura Nomina: " + estructuraNominaEntity.getIdEstructurasNominas()
				+ " con id contexto: " + idContexto);

	}

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearEstructuraNominaTra(String[] array, String idContexto) {

		EstructuraNominaTrailersAcumEntity estructuraNominaTraEntity = toEstructuraNominaTrailers(array);
		// estructuraNominaTraEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
	
		estructuraNominaTraEntity
				.setConceptoSiif(estructuraNominaTraEntity.getConcep() + estructuraNominaTraEntity.getPtaAnt());
		estructuraNominaTraEntity.setIdContexto(idContexto);
		// obtiene el id concepto
		estructuraNominaTraEntity.setIdConcepto(conceptoNominaRepository.obtenerIdConceptoPorTipoyConceptoSiif(
				estructuraNominaTraEntity.gettConcep(), estructuraNominaTraEntity.getConceptoSiif()));
		// Obtiene el idEstructuraNomina
		// estructuraNominaTraEntity.setIdEstructurasNominas(estructuraNominaRepository
		// .obtenerIdEstructuraNominaPorRfcyIdContexto(estructuraNominaTraEntity.getRfc(),
		// idContexto));

		entityManager.persist(estructuraNominaTraEntity);
		System.out.println("Guardo estructura nomina trailers: "
				+ estructuraNominaTraEntity.getIdEstructurasNominasTrailers() + " con id contexto: " + idContexto);

	}

	// <<<<<<Se Introducen los Datos de Estructura Nomina>>>>>>
	public String[] procesarArchivoDat(String dato) {
		String newArray = "";
		for (int i = 0; i < dato.length(); i++) {
			newArray += dato.charAt(i);
			if (String.valueOf(dato.charAt(i)).equals("|")) {
				try {
					if (String.valueOf(dato.charAt(i + 1)).equals("|")) {
						newArray += "| |";
					}
				} catch (StringIndexOutOfBoundsException e) {
				}
			}
		}

		StringTokenizer string = new StringTokenizer(newArray, "|");

		Integer datos = string.countTokens();
		String[] array = new String[datos];
		int i = 0;
		while (string.hasMoreTokens()) {
			String s = string.nextToken();
			array[i] = s;
			i++;
		}

		return array;
	}

	// <<<<<<Se Introducen los Datos de Estructura Nomina Trailer>>>>>>
	private String[] procesarArchivoTra(String dato) {
		String newArray = "";
		for (int i = 0; i < dato.length(); i++) {
			newArray += dato.charAt(i);
			if (String.valueOf(dato.charAt(i)).equals("|")) {
				if (String.valueOf(dato.charAt(i + 1)).equals("|")) {
					newArray += "| |";
				}
			}
		}

		StringTokenizer string = new StringTokenizer(newArray, "|");

		Integer datos = string.countTokens();
		String[] array = new String[datos];
		int i = 0;
		while (string.hasMoreTokens()) {
			String s = string.nextToken();
			array[i] = s;
			i++;
		}
		return array;
	}

	private EstructuraNominaTrailersAcumEntity toEstructuraNominaTrailers(String[] array) {
		EstructuraNominaTrailersAcumEntity entity = new EstructuraNominaTrailersAcumEntity();
		entity.setRfc(((array[0]).equals(" ")) ? null : String.valueOf(array[0]));
		entity.setNumEmp(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		entity.setNumCheq(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
		entity.setSubCheque(((array[2]).equals(" ")) ? 0 :((array[2]).equals("NA")) ? 0 : Integer.valueOf(array[2].substring(0, 2)));
		entity.setTConcep(((array[3]).equals(" ")) ? null : Integer.parseInt(array[3]));
		entity.setConcep(((array[4]).equals(" ")) ? null : String.valueOf(array[4]));
		entity.setImporte(((array[5]).equals(" ")) ? null : new BigDecimal(array[5]));
		entity.setAnio(((array[6]).equals(" ")) ? null : String.valueOf(array[6]));
		entity.setQna(((array[7]).equals(" ")) ? null : String.valueOf(array[7]));
		entity.setPtaAnt(((array[8]).equals(" ")) ? null : String.valueOf(array[8]));
		entity.setTotPAgos(((array[9]).equals(" ")) ? null : String.valueOf(array[9]));
		entity.setPagoEfec(((array[10]).equals(" ")) ? null : String.valueOf(array[10]));
		entity.setNomProd(((array[11]).equals(" ")) ? null : String.valueOf(array[11]));
		entity.setNumCtrol(((array[12]).equals(" ")) ? null : Integer.parseInt(array[12]));

		return entity;
	}

	private EstructuraNominaDatAcumEntity toEstructuraNominaEntity(String[] array) {
		EstructuraNominaDatAcumEntity entity = new EstructuraNominaDatAcumEntity();
		entity.setNumEmp(((array[0]).equals(" ")) ? null : String.valueOf(array[0]));
		entity.setRfc(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		entity.setCurp(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
		entity.setNombre(((array[3]).equals(" ")) ? null : String.valueOf(array[3]));
		entity.setSar(((array[4]).equals(" ")) ? null : String.valueOf(array[4]));
		entity.setBancoA(((array[5]).equals(" ")) ? null : String.valueOf(array[5]));
		entity.setBancoN(((array[6]).equals(" ")) ? null : String.valueOf(array[6]));
		entity.setNumCta(((array[7]).equals(" ")) ? null : String.valueOf(array[7]));
		entity.setClabe(((array[8]).equals(" ")) ? null : String.valueOf(array[8]));
		entity.setFuncion(((array[9]).equals(" ")) ? null : String.valueOf(array[9]));
		entity.setCp(((array[10]).equals(" ")) ? null : String.valueOf(array[10]));
		entity.setCalle(((array[11]).equals(" ")) ? null : String.valueOf(array[11]));
		entity.setColonia(((array[12]).equals(" ")) ? null : String.valueOf(array[12]));
		entity.setDeleg(((array[13]).equals(" ")) ? null : String.valueOf(array[13]));
		entity.setUr(((array[14]).equals(" ")) ? null : String.valueOf(array[14]));
		entity.setGf(((array[15]).equals(" ")) ? null : String.valueOf(array[15]));
		entity.setFn(((array[16]).equals(" ")) ? null : String.valueOf(array[16]));
		entity.setSf(((array[17]).equals(" ")) ? null : String.valueOf(array[17]));
		entity.setPg(((array[18]).equals(" ")) ? null : String.valueOf(array[18]));
		entity.setAi(((array[19]).equals(" ")) ? null : String.valueOf(array[19]));
		entity.setPp(((array[20]).equals(" ")) ? null : String.valueOf(array[20]));
		entity.setPartida(((array[21]).equals(" ")) ? null : String.valueOf(array[21]));
		entity.setPuesto(((array[22]).equals(" ")) ? null : String.valueOf(array[22]));
		entity.setNumPto(((array[23]).equals(" ")) ? null : String.valueOf(array[23]));
		entity.setEdo(((array[24]).equals(" ")) ? null : String.valueOf(array[24]));
		entity.setMpio(((array[25]).equals(" ")) ? null : String.valueOf(array[25]));
		entity.setCr(((array[26]).equals(" ")) ? null : String.valueOf(array[26]));
		entity.setCi(((array[27]).equals(" ")) ? null : String.valueOf(array[27]));
		entity.setPagaD(((array[28]).equals(" ")) ? null : String.valueOf(array[28]));
		entity.setFinanciamiento(((array[29]).equals(" ")) ? null : String.valueOf(array[29]));
		entity.setTabPto(((array[30]).equals(" ")) ? null : String.valueOf(array[30]));
		entity.setNivel(((array[31]).equals(" ")) ? null : String.valueOf(array[31]));
		entity.setRango(((array[32]).equals(" ")) ? null : String.valueOf(array[32]));
		entity.setIndMando(((array[33]).equals(" ")) ? null : String.valueOf(array[33]));
		entity.setHoras(((array[34]).equals(" ")) ? null : String.valueOf(array[34]));
		entity.setPorcent(((array[35]).equals(" ")) ? null : String.valueOf(array[35]));
		entity.setTipoTrab(((array[36]).equals(" ")) ? null : String.valueOf(array[36]));
		entity.setNivelPto(((array[37]).equals(" ")) ? null : String.valueOf(array[37]));
		entity.setIndEmp(((array[38]).equals(" ")) ? null : String.valueOf(array[38]));
		entity.setfIgf(((array[39]).equals(" ")) ? null : String.valueOf(array[39]));
		entity.setfIssa(((array[40]).equals(" ")) ? null : String.valueOf(array[40]));
		entity.setfReing(((array[41]).equals(" ")) ? null : String.valueOf(array[41]));
		entity.setTipoMov(((array[42]).equals(" ")) ? null : String.valueOf(array[42]));
		entity.setfPago(((array[43]).equals(" ")) ? null : String.valueOf(array[43]));
		entity.setpPagoI(((array[44]).equals(" ")) ? null : String.valueOf(array[44]));
		entity.setpPagoF(((array[45]).equals(" ")) ? null : String.valueOf(array[45]));
		entity.setpQnaI(((array[46]).equals(" ")) ? null : String.valueOf(array[46]));
		entity.setpQnaF(((array[47]).equals(" ")) ? null : String.valueOf(array[47]));
		entity.setQnaReal(((array[48]).equals(" ")) ? null : String.valueOf(array[48]));
		entity.setAnioReal(((array[49]).equals(" ")) ? null : String.valueOf(array[49]));
		entity.setTipoPago(((array[50]).equals(" ")) ? null : Integer.parseInt(array[50]));
		entity.setInstruA(((array[51]).equals(" ")) ? null : String.valueOf(array[51]));
		entity.setInstruN(((array[52]).equals(" ")) ? null : String.valueOf(array[52]));
		entity.setPer(((array[53]).equals(" ")) ? null : new BigDecimal(array[53]));
		entity.setDed(((array[54]).equals(" ")) ? null : new BigDecimal(array[54]));
		entity.setNeto(((array[55]).equals(" ")) ? null : new BigDecimal(array[55]));
		entity.setNoTrail(((array[56]).equals(" ")) ? null : Integer.parseInt(array[56]));
		entity.setDiasLab(((array[57]).equals(" ")) ? null : Integer.parseInt(array[57]));
		entity.setNomProd(((array[58]).equals(" ")) ? null : String.valueOf(array[58]));
		entity.setNumCtrol(((array[59]).equals(" ")) ? null : Integer.parseInt(array[59]));
		entity.setNumCheq(((array[60]).equals(" ")) ? null : String.valueOf(array[60]));
		entity.setDigVer(((array[61]).equals(" ")) ? null : String.valueOf(array[61]));
		entity.setJornada(((array[62]).equals(" ")) ? null : Integer.parseInt(array[62]));
		entity.setDiasP(((array[63]).equals(" ")) ? null : String.valueOf(array[63]));
		entity.setCicloF(((array[64]).equals(" ")) ? null : String.valueOf(array[64]));
		entity.setNumAport(((array[65]).equals(" ")) ? null : String.valueOf(array[65]));
		entity.setAcumF(((array[66]).equals(" ")) ? null : new BigDecimal(array[66]));
		entity.setFaltas(((array[67]).equals(" ")) ? null : Integer.parseInt(array[67]));
		entity.setClues(((array[68]).equals(" ")) ? null : String.valueOf(array[68]));
		entity.setPorPen01(((array[69]).equals(" ")) ? null : new BigDecimal(array[69]));
		entity.setPorPen02(((array[70]).equals(" ")) ? null : new BigDecimal(array[70]));
		entity.setPorPen03(((array[71]).equals(" ")) ? null : new BigDecimal(array[71]));
		entity.setPorPen04(((array[72]).equals(" ")) ? null : new BigDecimal(array[72]));
		entity.setPorPen05(((array[73]).equals(" ")) ? null : new BigDecimal(array[73]));
		entity.setIssste(((array[74]).equals(" ")) ? null : Integer.parseInt(array[74]));
		entity.setTipoUni(((array[75]).equals(" ")) ? null : Integer.parseInt(array[75]));
		entity.setCrespDes(((array[76]).equals(" ")) ? null : String.valueOf(array[76]));
		return entity;
	}

	public void importarNominaExcelDatTra(UploadedFile dat, UploadedFile tra, String idContexto) {
		try{
		procesarExcelDat(dat, idContexto);
		procesarExcelTra(tra, idContexto);
		}catch(EstructuraException|PersistenceException e){
			e.printStackTrace();
		}
	}

	public void procesarExcelDat(UploadedFile dat, String idContexto) throws EstructuraException {
		uploadedFile = new UploadExcelFileAnexo();
		uploadedFile.validate(dat);
		guardarlogArchivo(dat.getFileName(), idContexto);
		settingDataDAT(uploadedFile.getAnexoDTOs(), idContexto);
		
	}

	public void procesarExcelTra(UploadedFile tra, String idContexto)  throws EstructuraException{
		uploadedFile = new UploadExcelFileAnexo();
		uploadedFile.validate(tra);
		guardarlogArchivo(tra.getFileName(), idContexto);
		settingDataTRA(uploadedFile.getAnexoDTOs(), idContexto);
		
	}

	private void settingDataDAT(List<EstructuraDTO> data, String idContexto) {

		Iterator<EstructuraDTO> arrayIterator = data.iterator();
		List<EstructuraContratosDatDTO> listaEstructura = new ArrayList<EstructuraContratosDatDTO>();

		try {
			while (arrayIterator.hasNext()) {

				EstructuraDTO genericoDTO = arrayIterator.next();
				EstructuraContratosDatDTO estructuraDTO = new EstructuraContratosDatDTO();

				estructuraDTO.setNumEmp(genericoDTO.getDato(0, String.class));
				estructuraDTO.setRfc(genericoDTO.getDato(1, String.class));
				estructuraDTO.setCurp(genericoDTO.getDato(2, String.class));
				estructuraDTO.setNombre(genericoDTO.getDato(3, String.class));
				estructuraDTO.setSar(genericoDTO.getDato(4, String.class));
				estructuraDTO.setBancoA(genericoDTO.getDato(5, String.class));
				estructuraDTO.setBancoN(genericoDTO.getDato(6, String.class));
				estructuraDTO.setNumCta(genericoDTO.getDato(7, String.class));
				estructuraDTO.setClabe(genericoDTO.getDato(8, String.class));
				estructuraDTO.setFuncion(genericoDTO.getDato(9, String.class));
				estructuraDTO.setCp(genericoDTO.getDato(10, String.class));
				estructuraDTO.setCalle(genericoDTO.getDato(11, String.class));
				estructuraDTO.setPuesto(genericoDTO.getDato(12, String.class));
				estructuraDTO.setDesPuesto(genericoDTO.getDato(13, String.class));
				estructuraDTO.setUr(genericoDTO.getDato(14, String.class));
				estructuraDTO.setGf(genericoDTO.getDato(15, String.class));
				estructuraDTO.setFn(genericoDTO.getDato(16, String.class));
				estructuraDTO.setSf(genericoDTO.getDato(17, String.class));
				estructuraDTO.setPg(genericoDTO.getDato(18, String.class));
				estructuraDTO.setAi(genericoDTO.getDato(19, String.class));
				estructuraDTO.setPp(genericoDTO.getDato(20, String.class));
				estructuraDTO.setPartida(genericoDTO.getDato(21, String.class));
				estructuraDTO.setPuestoTab(genericoDTO.getDato(22, String.class));
				estructuraDTO.setNumPto(genericoDTO.getDato(23, String.class));
				estructuraDTO.setEdo(genericoDTO.getDato(24, String.class));
				estructuraDTO.setMpio(genericoDTO.getDato(25, String.class));
				estructuraDTO.setCr(genericoDTO.getDato(26, String.class));
				estructuraDTO.setCi(genericoDTO.getDato(27, String.class));
				estructuraDTO.setPagaD(genericoDTO.getDato(28, String.class));
				estructuraDTO.setFinanciamiento(genericoDTO.getDato(29, String.class));
				estructuraDTO.setTabPto(genericoDTO.getDato(30, String.class));
				estructuraDTO.setNivel(genericoDTO.getDato(31, String.class));
				estructuraDTO.setRango(genericoDTO.getDato(32, String.class));
				estructuraDTO.setIndMando(genericoDTO.getDato(33, String.class));
				estructuraDTO.setHoras(genericoDTO.getDato(34, String.class));
				estructuraDTO.setPorcent(genericoDTO.getDato(35, String.class));
				estructuraDTO.setTipoTrab(genericoDTO.getDato(36, String.class));
				estructuraDTO.setNivelPto(genericoDTO.getDato(37, String.class));
				estructuraDTO.setIndEmp(genericoDTO.getDato(38, String.class));
				estructuraDTO.setFecInicial(genericoDTO.getDato(39, String.class));
				estructuraDTO.setFecFinal(genericoDTO.getDato(40, String.class));
				estructuraDTO.setFecIngreso(genericoDTO.getDato(41, String.class));
				estructuraDTO.setTipoMov(genericoDTO.getDato(42, String.class));
				estructuraDTO.setfPago(genericoDTO.getDato(43, String.class));
				estructuraDTO.setpPagoI(genericoDTO.getDato(44, String.class));
				estructuraDTO.setpPagoF(genericoDTO.getDato(45, String.class));
				estructuraDTO.setpQnaI(genericoDTO.getDato(46, String.class));
				estructuraDTO.setpQnaF(genericoDTO.getDato(47, String.class));
				estructuraDTO.setQnaReal(genericoDTO.getDato(48, String.class));
				estructuraDTO.setAnioReal(genericoDTO.getDato(49, String.class));
				estructuraDTO.setTipoPago(genericoDTO.getDato(50, Integer.class));
				estructuraDTO.setInstruA(genericoDTO.getDato(51, String.class));
				estructuraDTO.setInstruN(genericoDTO.getDato(52, String.class));
				estructuraDTO.setPer(genericoDTO.getDato(53, BigDecimal.class));
				estructuraDTO.setDed(genericoDTO.getDato(54, BigDecimal.class));
				estructuraDTO.setNeto(genericoDTO.getDato(55, BigDecimal.class));
				estructuraDTO.setNoTrail(genericoDTO.getDato(56, Integer.class));
				estructuraDTO.setDiasLab(genericoDTO.getDato(57, Integer.class));
				estructuraDTO.setNomProd(genericoDTO.getDato(58, String.class));
				estructuraDTO.setNumCtrol(genericoDTO.getDato(59, Integer.class));
				estructuraDTO.setNumCheq(genericoDTO.getDato(60, String.class));
				estructuraDTO.setDigVer(genericoDTO.getDato(61, String.class));
				estructuraDTO.setJornada(genericoDTO.getDato(62, String.class));
				estructuraDTO.setDiasP(genericoDTO.getDato(63, String.class));
				estructuraDTO.setCicloF(genericoDTO.getDato(64, String.class));
				estructuraDTO.setNumAport(genericoDTO.getDato(65, String.class));
				estructuraDTO.setAcumF(genericoDTO.getDato(66, BigDecimal.class));
				estructuraDTO.setFaltas(genericoDTO.getDato(67, Integer.class));
				estructuraDTO.setClues(genericoDTO.getDato(68, String.class));
				estructuraDTO.setPorPen01(genericoDTO.getDato(69, Integer.class));
				estructuraDTO.setPorPen02(genericoDTO.getDato(70, Integer.class));
				estructuraDTO.setPorPen03(genericoDTO.getDato(71, Integer.class));
				estructuraDTO.setPorPen04(genericoDTO.getDato(72, Integer.class));
				estructuraDTO.setPorPen05(genericoDTO.getDato(73, Integer.class));
				estructuraDTO.setIssste(genericoDTO.getDato(74, Integer.class));
				estructuraDTO.setTipoUni(genericoDTO.getDato(75, Integer.class));
				estructuraDTO.setCrespDes(genericoDTO.getDato(76, String.class));

				// estructuraDTO.setIdSiifBitacoras(bitacora.getIdSiifBitacora());

				listaEstructura.add(estructuraDTO);
				// i++;
			}

			registrarListaEstructuraDat(listaEstructura, idContexto);
			// JSFUtils.infoMessage("Registro Nomina Trailers", "Proceso
			// realizado correctamente");
			System.out.println("Registro Nomina Dat: " + "Proceso realizado correctamente");
		} catch (EstructuraException|IndexOutOfBoundsException e) {
			// JSFUtils.errorMessage("Error", e.getMessage());
			e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void registrarListaEstructuraDat(List<EstructuraContratosDatDTO> listaEstructura, String idContexto) {
		try {
			String contexto = "registrarListaEstructuraDat: ";
			if (listaEstructura.isEmpty()) {
				throw new ValidacionException(contexto + "No existen registros de datos Excel Dat",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			for (EstructuraContratosDatDTO trailers : listaEstructura) {
				EstructuraNominaDatAcumEntity estructuraEntity = new EstructuraNominaDatAcumEntity();

				estructuraEntity.setRfc(trailers.getRfc());
				estructuraEntity.setNumEmp(trailers.getNumEmp());
				estructuraEntity.setRfc(trailers.getRfc());
				estructuraEntity.setCurp(trailers.getCurp());
				estructuraEntity.setNombre(trailers.getNombre());
				estructuraEntity.setSar(trailers.getSar());
				estructuraEntity.setBancoA(trailers.getBancoA());
				estructuraEntity.setBancoN(trailers.getBancoA());
				estructuraEntity.setNumCta(trailers.getNumCta());
				estructuraEntity.setClabe(trailers.getClabe());
				estructuraEntity.setFuncion(trailers.getFuncion());
				estructuraEntity.setCp(trailers.getCp());
				estructuraEntity.setCalle(trailers.getCalle());
				estructuraEntity.setPuesto(trailers.getPuesto());
				// estructuraEntity.setDesPuesto(trailers.getDesPuesto());
				estructuraEntity.setUr(trailers.getUr());
				estructuraEntity.setGf(trailers.getGf());
				estructuraEntity.setFn(trailers.getFn());
				estructuraEntity.setSf(trailers.getSf());
				estructuraEntity.setPg(trailers.getPg());
				estructuraEntity.setAi(trailers.getAi());
				estructuraEntity.setPp(trailers.getPp());
				estructuraEntity.setPartida(trailers.getPartida());
				// estructuraEntity.setPuestoTab(trailers.getPuesto());
				estructuraEntity.setNumPto(trailers.getNumPto());
				estructuraEntity.setEdo(trailers.getEdo());
				estructuraEntity.setMpio(trailers.getMpio());
				estructuraEntity.setCr(trailers.getCr());
				estructuraEntity.setCi(trailers.getCi());
				estructuraEntity.setPagaD(trailers.getPagaD());
				estructuraEntity.setFinanciamiento(trailers.getFinanciamiento());
				estructuraEntity.setTabPto(trailers.getTabPto());
				estructuraEntity.setNivel(trailers.getNivel());
				estructuraEntity.setRango(trailers.getRango());
				estructuraEntity.setIndMando(trailers.getIndMando());
				estructuraEntity.setHoras(trailers.getHoras());
				estructuraEntity.setPorcent(trailers.getPorcent());
				estructuraEntity.setTipoTrab(trailers.getTipoTrab());
				estructuraEntity.setNivelPto(trailers.getNivelPto());
				estructuraEntity.setIndEmp(trailers.getIndEmp());
				estructuraEntity.setfIgf(trailers.getFecInicial());
				estructuraEntity.setfIssa(trailers.getFecFinal());
				estructuraEntity.setfReing(trailers.getFecIngreso());
				estructuraEntity.setTipoMov(trailers.getTipoMov());
				estructuraEntity.setfPago(trailers.getfPago());
				estructuraEntity.setpPagoI(trailers.getpPagoI());
				estructuraEntity.setpPagoF(trailers.getpPagoF());
				estructuraEntity.setpQnaI(trailers.getpQnaI());
				estructuraEntity.setpQnaF(trailers.getpQnaF());
				estructuraEntity.setQnaReal(trailers.getQnaReal());
				estructuraEntity.setAnioReal(trailers.getAnioReal());
				estructuraEntity.setTipoPago(trailers.getTipoPago());
				estructuraEntity.setInstruA(trailers.getInstruA());
				estructuraEntity.setInstruN(trailers.getInstruN());
				estructuraEntity.setPer(trailers.getPer());
				estructuraEntity.setDed(trailers.getDed());
				estructuraEntity.setNeto(trailers.getNeto());
				estructuraEntity.setNoTrail(trailers.getNoTrail());
				estructuraEntity.setDiasLab(trailers.getDiasLab());
				estructuraEntity.setNomProd(trailers.getNomProd());
				estructuraEntity.setNumCtrol(trailers.getNumCtrol());
				estructuraEntity.setNumCheq(trailers.getNumCheq());
				estructuraEntity.setDigVer(trailers.getDigVer());
				estructuraEntity
						.setJornada(trailers.getJornada() == null ? null : Integer.parseInt(trailers.getJornada()));
				estructuraEntity.setDiasP(trailers.getDiasP());
				estructuraEntity.setCicloF(trailers.getCicloF());
				estructuraEntity.setNumAport(trailers.getNumAport());
				estructuraEntity.setAcumF(trailers.getAcumF());
				estructuraEntity.setFaltas(trailers.getFaltas());
				estructuraEntity.setClues(trailers.getClues());
				estructuraEntity
						.setPorPen01(trailers.getPorPen01() == null ? null : BigDecimal.valueOf(trailers.getPorPen01()));
				estructuraEntity
						.setPorPen02(trailers.getPorPen02() == null ? null : BigDecimal.valueOf(trailers.getPorPen02()));
				estructuraEntity
						.setPorPen03(trailers.getPorPen03() == null ? null : BigDecimal.valueOf(trailers.getPorPen03()));
				estructuraEntity
						.setPorPen04(trailers.getPorPen04() == null ? null : BigDecimal.valueOf(trailers.getPorPen04()));
				estructuraEntity
						.setPorPen05(trailers.getPorPen05() == null ? null : BigDecimal.valueOf(trailers.getPorPen05()));
				estructuraEntity.setIssste(trailers.getIssste());
				estructuraEntity.setTipoUni(trailers.getTipoUni());
				estructuraEntity.setCrespDes(trailers.getCrespDes());

				// estructuraEntity.setIdSiifBitacoras(trailers.getIdSiifBitacoras());

				estructuraEntity.setIdContexto(idContexto);
				// obteniendo id tipo nombramiento
				estructuraEntity
						.setIdNombramiento(tipoNombramientoRepository.obtenerIdNombramientoPorClave(trailers.getUr()));

				registroEstructuraExcel(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public String registroEstructuraExcel(EstructuraNominaDatAcumEntity estructura) {
		entityManager.persist(estructura);
		System.out.println("Guardo estructura Nomina: " + estructura.getIdEstructurasNominas() + " con id contexto: "
				+ estructura.getIdContexto());
		return estructura.getNumEmp();
	}

	private void settingDataTRA(List<EstructuraDTO> data, String idContexto) {

		Iterator<EstructuraDTO> arrayIterator = data.iterator();
		List<EstructuraContratosTrailersDTO> listaEstructura = new ArrayList<EstructuraContratosTrailersDTO>();
		try {
			while (arrayIterator.hasNext()) {

				EstructuraDTO genericoDTO = arrayIterator.next();
				EstructuraContratosTrailersDTO estructuraDTO = new EstructuraContratosTrailersDTO();
				estructuraDTO.setRfc(genericoDTO.getDato(0, String.class));
				estructuraDTO.setNumEmp(genericoDTO.getDato(1, String.class));
				estructuraDTO.setNumCheq(genericoDTO.getDato(2, String.class));
				estructuraDTO.settConcep(genericoDTO.getDato(3, Integer.class));
				estructuraDTO.setConcep(genericoDTO.getDato(4, String.class));
				estructuraDTO.setImporte(genericoDTO.getDato(5, BigDecimal.class));
				estructuraDTO.setAnio(genericoDTO.getDato(6, String.class));
				estructuraDTO.setQna(genericoDTO.getDato(7, String.class));
			
				estructuraDTO.setPtaAnt(genericoDTO.getDato(8, String.class));
				estructuraDTO.setTotPagos(genericoDTO.getDato(9, String.class));
				estructuraDTO.setPagoEfec(genericoDTO.getDato(10, String.class));
				estructuraDTO.setNomProd(genericoDTO.getDato(11, String.class));
				estructuraDTO.setNumControl(genericoDTO.getDato(12, Integer.class));
				// estructuraDTO.setIdSiifBitacoras(bitacora.getIdSiifBitacora());
				estructuraDTO.setConceptosSiif(estructuraDTO.getConcep() + estructuraDTO.getPtaAnt());
				listaEstructura.add(estructuraDTO);

			}

			registrarListaEstructura(listaEstructura, idContexto);
			// JSFUtils.infoMessage("Registro Estructura", "Proceso realizado
			// correctamente");
			System.out.println("Registro Estructura Tra: " + "Proceso realizado correctamente");
		} catch (EstructuraException|IndexOutOfBoundsException e) {
			e.printStackTrace();
			// JSFUtils.errorMessage("Error", e.getMessage());
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void registrarListaEstructura(List<EstructuraContratosTrailersDTO> listaEstructura, String idContexto) {
		try {

			String contexto = "registrarListaEstructura: ";
			if (listaEstructura.isEmpty()) {
				throw new ValidacionException(contexto + "No existen registros de datos Excel Tra",
						ValidacionCodigoError.VALOR_REQUERIDO);
			}

			for (EstructuraContratosTrailersDTO trailers : listaEstructura) {
				EstructuraNominaTrailersAcumEntity estructuraEntity = new EstructuraNominaTrailersAcumEntity();
				estructuraEntity.setNumEmp(trailers.getNumEmp());
				estructuraEntity.setRfc(trailers.getRfc());
				estructuraEntity.setNumCheq(trailers.getNumCheq());
				estructuraEntity.setTConcep(trailers.gettConcep());
				estructuraEntity.setConcep(trailers.getConcep());
				estructuraEntity.setImporte(trailers.getImporte());
				estructuraEntity.setAnio(trailers.getAnio());
				estructuraEntity.setQna(trailers.getQna());
				estructuraEntity.setPtaAnt(trailers.getPtaAnt());
				estructuraEntity.setTotPAgos(trailers.getTotPagos());
				estructuraEntity.setPagoEfec(trailers.getPagoEfec());
				estructuraEntity.setNomProd(trailers.getNomProd());
				estructuraEntity.setNumCtrol(trailers.getNumControl());
				// estructuraEntity.setIdSiifBitacoras(trailers.getIdSiifBitacoras());
				//System.out.println("cncepto:: "+ estructuraEntity.getConcep());				
				//estructuraEntity.setConcep(trailers.getConcep() + trailers.getPtaAnt());

				estructuraEntity.setConceptoSiif(trailers.getConcep() + trailers.getPtaAnt());
				estructuraEntity.setIdContexto(idContexto);
				// obtiene el id concepto
				estructuraEntity.setIdConcepto(conceptoNominaRepository.obtenerIdConceptoPorTipoyConceptoSiif(
						estructuraEntity.gettConcep(), estructuraEntity.getConceptoSiif()));

				registroEstructura(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public String registroEstructura(EstructuraNominaTrailersAcumEntity estructura) {
		entityManager.persist(estructura);
		System.out.println("Guardo estructura nomina trailers: " + estructura.getIdEstructurasNominasTrailers()
				+ " con id contexto: " + estructura.getIdContexto());
		return estructura.getNumEmp();
	}

}
