package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFDeudoresDiversosEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFDispersionChequesEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFSeguroPopularEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoExcelEntity;

@Stateless
public class EstructuraContratoTrailersService {
	
	@Inject
	private EstructuraContratosTrailersQuery estructuraTrailersQuery;
		
	public void registrarListaEstructura(List<EstructuraContratosTrailersDTO> listaEstructura) {
		try {
			
			if (listaEstructura.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (EstructuraContratosTrailersDTO trailers : listaEstructura) {
				EstructuraContratoTrailersEntity estructuraEntity = new EstructuraContratoTrailersEntity();
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
				estructuraEntity.setIdSiifBitacoras(trailers.getIdSiifBitacoras());
				estructuraEntity.setConcep(trailers.getConcep() + trailers.getPtaAnt());
				
				estructuraTrailersQuery.registroEstructura(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void registrarListaEstructuraDat(List<EstructuraContratosDatDTO> listaEstructura) {
		try {
			
			if (listaEstructura.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (EstructuraContratosDatDTO trailers : listaEstructura) {
				EstructuraContratoEntity estructuraEntity = new EstructuraContratoEntity();
				
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
				estructuraEntity.setDesPuesto(trailers.getDesPuesto());
				estructuraEntity.setUr(trailers.getUr());
				estructuraEntity.setGf(trailers.getGf());
				estructuraEntity.setFn(trailers.getFn());
				estructuraEntity.setSf(trailers.getSf());
				estructuraEntity.setPg(trailers.getPg());
				estructuraEntity.setAi(trailers.getAi());
				estructuraEntity.setPp(trailers.getPp());
				estructuraEntity.setPartida(trailers.getPartida());
				estructuraEntity.setPuestoTab(trailers.getPuesto());
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
				estructuraEntity.setFecInicial(trailers.getFecInicial());
				estructuraEntity.setFecFinal(trailers.getFecFinal());
				estructuraEntity.setFecIngreso(trailers.getFecIngreso());
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
				estructuraEntity.setJornada(trailers.getJornada().toString());
				estructuraEntity.setDiasP(trailers.getDiasP());
				estructuraEntity.setCicloF(trailers.getCicloF());
				estructuraEntity.setNumAport(trailers.getNumAport());
				estructuraEntity.setAcumF(trailers.getAcumF());
				estructuraEntity.setFaltas(trailers.getFaltas());
				estructuraEntity.setClues(trailers.getClues());
				estructuraEntity.setPorPen01(trailers.getPorPen01().intValue());
				estructuraEntity.setPorPen02(trailers.getPorPen02().intValue());
				estructuraEntity.setPorPen03(trailers.getPorPen03().intValue());
				estructuraEntity.setPorPen04(trailers.getPorPen04().intValue());
				estructuraEntity.setPorPen05(trailers.getPorPen05().intValue());
				estructuraEntity.setIssste(trailers.getIssste());
				estructuraEntity.setTipoUni(trailers.getTipoUni());
				estructuraEntity.setCrespDes(trailers.getCrespDes());
				
				
				estructuraTrailersQuery.registroEstructura(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	
	public void registrarListaEstructuraExcel(List<EstructuraContratosExcelDTO> listaEstructura) {
		try {
			
			if (listaEstructura.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (EstructuraContratosExcelDTO cont : listaEstructura) {
				EstructuraContratoExcelEntity estructuraEntity = new EstructuraContratoExcelEntity();
				
				estructuraEntity.setNum(cont.getNum());
				estructuraEntity.setPrograma(cont.getPrograma());
				estructuraEntity.setRfc(cont.getRfc());
				estructuraEntity.setRfcVal(cont.getRfcVal());
				estructuraEntity.setNombre(cont.getNombre());
				estructuraEntity.setfI(cont.getfI());
				estructuraEntity.setcResponsable(cont.getcResponsable());
				estructuraEntity.setFuncion(cont.getFuncion());
				estructuraEntity.setRama(cont.getRama());
				estructuraEntity.setfFinan(cont.getfFinan());
				estructuraEntity.setProg(cont.getProg());
				estructuraEntity.setTotalBruto(cont.getTotalBruto());
				estructuraEntity.setPercepciones(cont.getPercepciones());
				estructuraEntity.setDeducciones(cont.getDeducciones());
				estructuraEntity.setNeto(cont.getNeto());
				estructuraEntity.setSueldo(cont.getSueldo());
				estructuraEntity.setSup(cont.getSup());
				estructuraEntity.setComp(cont.getComp());
				estructuraEntity.setAg(cont.getAg());
				estructuraEntity.setSubsidio(cont.getSubsidio());
				estructuraEntity.setVac(cont.getVac());estructuraEntity.setrFaltas(cont.getrFaltas());
				estructuraEntity.setrFaltas(cont.getrFaltas());//faltaba esta
				estructuraEntity.setRetroa(cont.getRetroa());
				estructuraEntity.setOtros(cont.getOtros());
				estructuraEntity.setFaltas(cont.getFaltas());
				estructuraEntity.setIstp(cont.getIstp());
				estructuraEntity.setRespons(cont.getRespons());
				estructuraEntity.setPrestamo(cont.getPrestamo());
				estructuraEntity.setPa(cont.getPa());
				estructuraEntity.setTotal(cont.getTotal());
				estructuraEntity.setVerifica(cont.getVerifica());
				estructuraEntity.settCentro(cont.gettCentro());
				estructuraEntity.setcClues(cont.getcClues());
				estructuraEntity.setNomUnidad(cont.getNomUnidad());
				estructuraEntity.setaAdscripcion(cont.getaAdscripcion());
				estructuraEntity.setPuesto(cont.getPuesto());
				estructuraEntity.setcPuesto(cont.getcPuesto());
				estructuraEntity.setServicio(cont.getServicio());
				estructuraEntity.setRamas(cont.getRamas());
				estructuraEntity.setTurno(cont.getTurno());
				
				estructuraTrailersQuery.registroEstructuraExcel(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}


	public void registrarListaSeguroPopular(List<SiifSeguroPopularDTO> listaSeguro) {
		try {
			
			if (listaSeguro.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (SiifSeguroPopularDTO seguro : listaSeguro) {
				SIIFSeguroPopularEntity entity = new SIIFSeguroPopularEntity();
				
				entity.setRfc(seguro.getRfc());
				entity.setQuincena(seguro.getQuincena());
				
				estructuraTrailersQuery.registroSeguroPopular(entity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void registrarListaDeudoresDiversos(List<SiifDeudoresDiversosDTO> listaSeguro) {
		try {
			
			if (listaSeguro.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (SiifDeudoresDiversosDTO seguro : listaSeguro) {
				SIIFDeudoresDiversosEntity entity = new SIIFDeudoresDiversosEntity();
				
				entity.setRfc(seguro.getRfc());
				entity.setImporte(seguro.getImporte());
				entity.setQuincena(seguro.getQuincena());
				
				estructuraTrailersQuery.registroDeudoresDiversos(entity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void registrarListaDispersionCheques(List<SiifDispersionChequesDTO> listaCheques) {
		try {
			
			if (listaCheques.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}
			
			for (SiifDispersionChequesDTO lista : listaCheques) {
				SIIFDispersionChequesEntity entity = new SIIFDispersionChequesEntity();
				
				entity.setNum(lista.getNum());
				entity.setNumCheque(lista.getNumCheque());
				entity.setNombre(lista.getNombre());
				entity.setImporte(lista.getImporte());
				entity.setTipoNomina(lista.getTipoNomina());
				entity.setQuincena(lista.getQuincena());
				entity.setRfc(lista.getRfc());				
				
				estructuraTrailersQuery.registroDispersionCheques(entity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	

	
}