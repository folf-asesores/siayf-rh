package mx.gob.saludtlax.rh.siif.reportarfederales;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.StringTokenizer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersEntity;
import mx.gob.saludtlax.rh.util.JSFUtils;

import org.primefaces.model.UploadedFile;

@Stateless
public class ReportarSiifFederalesEJB {

	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	public void procesarNominaTheosToSIIF(UploadedFile dat, UploadedFile tra) {
		try {
			String valoresDat = new String(dat.getContents(),"UTF-8");
			StringTokenizer stDat = new StringTokenizer(valoresDat, "\n");
			while(stDat.hasMoreTokens()) {
			    String dato = stDat.nextToken();
			    procesarArchivoDat(dato);
			}

			String valoresTra  = new String(tra.getContents(),"UTF-8");
			StringTokenizer stTra = new StringTokenizer(valoresTra, "\n");
			while(stTra.hasMoreTokens()) {
			    String dato = stTra.nextToken();
			    procesarArchivoTra(dato);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSFUtils.infoMessage("En este momento ha terminado de subir el archivo y se empieza a procesar","En este momento ha terminado de subir el archivo y se empieza a procesar");
	}

//	<<<<<<Se Introducen los Datos de Estructura Nomina>>>>>>
	
	
	public void procesarArchivoDat(String dato) {
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
		int i=0;
		while(string.hasMoreTokens()) {
			String s= string.nextToken();
			array[i]=s;
			i++;
		}
		EstructuraNominaDatEntity estructuraNominaEntity = toEstructuraNominaEntity(array);
		entityManager.persist(estructuraNominaEntity);
	}

	private EstructuraNominaDatEntity toEstructuraNominaEntity(String[] array) {
		EstructuraNominaDatEntity entity = new EstructuraNominaDatEntity();
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
//		entity.setPorPen01(((array[69]).equals(" ")) ? null : new BigDecimal(array[69]));
//		entity.setPorPen02(((array[70]).equals(" ")) ? null : new BigDecimal(array[70]));
//		entity.setPorPen03(((array[71]).equals(" ")) ? null : new BigDecimal(array[71]));
//		entity.setPorPen04(((array[72]).equals(" ")) ? null : new BigDecimal(array[72]));
//		entity.setPorPen05(((array[73]).equals(" ")) ? null : new BigDecimal(array[73]));
		entity.setIssste(((array[74]).equals(" ")) ? null : Integer.parseInt(array[74]));
		entity.setTipoUni(((array[75]).equals(" ")) ? null : Integer.parseInt(array[75]));
		entity.setCrespDes(((array[76]).equals(" ")) ? null : String.valueOf(array[76]));
		return entity;
	}
	
	
//	<<<<<<Se Introducen los Datos de Estructura Nomina Trailer>>>>>>
	
	
	private void procesarArchivoTra(String dato) {
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
		int i=0;
		while(string.hasMoreTokens()) {
			String s= string.nextToken();
			array[i]=s;
			i++;
		}
		EstructuraNominaTrailersEntity estructuraNominaTraEntity = toEstructuraNominaTrailers(array);
		entityManager.persist(estructuraNominaTraEntity);
	}
	
	
	private EstructuraNominaTrailersEntity toEstructuraNominaTrailers(String[] array) {
		EstructuraNominaTrailersEntity entity = new EstructuraNominaTrailersEntity();
		entity.setRfc(((array[0]).equals(" ")) ? null : String.valueOf(array[0]));
		entity.setNumEmp(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		entity.setNumCheq(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
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
}