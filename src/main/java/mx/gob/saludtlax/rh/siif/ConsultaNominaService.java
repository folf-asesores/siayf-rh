package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatRepository;
import mx.gob.saludtlax.rh.acumulados.AcumuladosDTO;
import mx.gob.saludtlax.rh.acumulados.ConceptoNominaAcumuladoDTO;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosPlantillaExcelDTO;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersEntity;


@Stateless
public class ConsultaNominaService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	
	@Inject
	private EstructuraNominaDatRepository DAO;
	@Inject
	private EstructuraNominaTrailersRepository trailersDAO;

	
	public List<EstructuraNominaDatDTO> listaEstructuraNomina() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_estructuras_nominas AS idEstructurasNominas, "
				+ "num_emp AS numEmp, "
				+ "rfc AS rfc, "
				+ "curp AS curp, "
				+ "nombre AS nombre, "
				+ "sar AS sar, "
				+ "banco_a AS bancoA, "
				+ "banco_n AS bancoN, "
				+ "num_cta AS numCta, "
				+ "clabe AS clabe, "
				+ "calle AS calle, "
				+ "colonia AS colonia, "
				+ "deleg AS deleg, "
				+ "ur AS ur, "
				+ "gf AS gf, "
				+ "fn AS fn, "
				+ "sf AS sf, "
				+ "pg AS pg, "
				+ "ai AS ai, "
				+ "pp AS pp, "
				+ "partida AS partida, "
				+ "puesto AS puesto, "
				+ "num_pto AS numPto, "
				+ "edo AS edo, "
				+ "mpio As mpio, "
				+ "cr AS cr, "
				+ "ci AS ci, "
				+ "paga_d AS pagaD, "
				+ "financiamiento AS financiamiento, "
				+ "tab_pto AS tabPto, "
				+ "nivel AS nivel, "
				+ "rango AS rango, "
				+ "ind_mando AS indMando, "
				+ "hora AS hora, "
				+ "porcent AS porcent, "
				+ "tipo_trab AS tipoTrab, "
				+ "nivel_pto AS nivelPto, "
				+ "ind_emp AS indEmp, "
				+ "fIgf AS fIgf, "
				+ "fIssa AS fIssa, "
				+ "f_reing AS fReing, "
				+ "tipo_mov AS tipoMov, "
				+ "f_pago As fPago, "
				+ "p_pago_i AS pPagoI, "
				+ "p_pago_f AS pPagoF, "
				+ "p_qna_i AS pQnaI, "
				+ "p_qna_f As pQnaF, "
				+ "qna_real AS qnaReal, "
				+ "anio_real AS anioReal, "
				+ "tipo_pago AS tipoPago, "
				+ "instru_a AS instruA, "
				+ "instru_n AS instruN, "
				+ "per AS per, "
				+ "ded AS ded, "
				+ "neto AS neto, "
				+ "no_trail AS noTrail, "
				+ "dias_lab AS diasLab, "
				+ "nom_prod AS nomProd, "
				+ "num_ctrol AS numCtrol, "
				+ "num_cheq AS numCheq, "
				+ "dig_ver AS digVer, "
				+ "jornada as jornada, "
				+ "dias_p AS diasP, "
				+ "ciclo_f AS cicloF, "
				+ "num_aport AS numAport, "
				+ "acum_f AS acumF, "
				+ "faltas AS faltas, "
				+ "clues  AS clues, "
				+ "por_pen_01 AS porPen01, "
				+ "por_pen_02 AS porPen02, "
				+ "por_pen_03 AS porPen03, "
				+ "por_pen_04 AS porPen04, "
				+ "por_pen_05 AS porPen05, "
				+ "issste AS issste, "
				+ "tipo_uni AS tipoUni, "
				+ "cresp_des AS crespDes, "
				+ "tipo_emision_nomina As tipoEmisionNomina "				
				+ "FROM estructuras_nominas");
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaDatDTO> result = (List<EstructuraNominaDatDTO>) query.list();
		return result;
	}
	

	public List<EstructuraNominaDatDTO> listaConsultaNominaPorCriterios(String rfc) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas AS idEstructurasNominas, "
				+ "num_emp AS numEmp, "
				+ "rfc AS rfc, "
				+ "curp AS curp, "
				+ "nombre AS nombre, "
				+ "sar AS sar, "
				+ "banco_a AS bancoA, "
				+ "banco_n AS bancoN, "
				+ "num_cta AS numCta, "
				+ "clabe AS clabe, "
				+ "calle AS calle, "
				+ "colonia AS colonia, "
				+ "deleg AS deleg, "
				+ "ur AS ur, "
				+ "gf AS gf, "
				+ "fn AS fn, "
				+ "sf AS sf, "
				+ "pg AS pg, "
				+ "ai AS ai, "
				+ "pp AS pp, "
				+ "partida AS partida, "
				+ "puesto AS puesto, "
				+ "num_pto AS numPto, "
				+ "edo AS edo, "
				+ "mpio As mpio, "
				+ "cr AS cr, "
				+ "ci AS ci, "
				+ "paga_d AS pagaD, "
				+ "financiamiento AS financiamiento, "
				+ "tab_pto AS tabPto, "
				+ "nivel AS nivel, "
				+ "rango AS rango, "
				+ "ind_mando AS indMando, "
				+ "hora AS hora, "
				+ "porcent AS porcent, "
				+ "tipo_trab AS tipoTrab, "
				+ "nivel_pto AS nivelPto, "
				+ "ind_emp AS indEmp, "
				+ "fIgf AS fIgf, "
				+ "fIssa AS fIssa, "
				+ "f_reing AS fReing, "
				+ "tipo_mov AS tipoMov, "
				+ "f_pago As fPago, "
				+ "p_pago_i AS pPagoI, "
				+ "p_pago_f AS pPagoF, "
				+ "p_qna_i AS pQnaI, "
				+ "p_qna_f As pQnaF, "
				+ "qna_real AS qnaReal, "
				+ "anio_real AS anioReal, "
				+ "tipo_pago AS tipoPago, "
				+ "instru_a AS instruA, "
				+ "instru_n AS instruN, "
				+ "per AS per, "
				+ "ded AS ded, "
				+ "neto AS neto, "
				+ "no_trail AS noTrail, "
				+ "dias_lab AS diasLab, "
				+ "nom_prod AS nomProd, "
				+ "num_ctrol AS numCtrol, "
				+ "num_cheq AS numCheq, "
				+ "dig_ver AS digVer, "
				+ "jornada as jornada, "
				+ "dias_p AS diasP, "
				+ "ciclo_f AS cicloF, "
				+ "num_aport AS numAport, "
				+ "acum_f AS acumF, "
				+ "faltas AS faltas, "
				+ "clues  AS clues, "
				+ "por_pen_01 AS porPen01, "
				+ "por_pen_02 AS porPen02, "
				+ "por_pen_03 AS porPen03, "
				+ "por_pen_04 AS porPen04, "
				+ "por_pen_05 AS porPen05, "
				+ "issste AS issste, "
				+ "tipo_uni AS tipoUni, "
				+ "cresp_des AS crespDes, "
				+ "id_nombramiento AS idNombramiento "		
				+ "FROM estructuras_nominas WHERE  rfc LIKE :rfc")
				.setParameter("rfc", "%" + rfc + "%");
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaDatDTO> result = (List<EstructuraNominaDatDTO>) query.list();
		return result;
	}
	
	public List<EstructuraContratosPlantillaExcelDTO> listaConsultaNominaContratoPorCriterios(String rfc) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas AS idEstructurasNominas, "
				+ "num_emp AS numEmp, "
				+ "rfc AS rfc, "
				+ "curp AS curp, "
				+ "nombre AS nombre, "
				+ "sar AS sar, "
				+ "banco_a AS bancoA, "
				+ "banco_n AS bancoN, "
				+ "num_cta AS numCta, "
				+ "clabe AS clabe, "
				+ "calle AS calle, "
				+ "colonia AS colonia, "
				+ "deleg AS deleg, "
				+ "ur AS ur, "
				+ "gf AS gf, "
				+ "fn AS fn, "
				+ "sf AS sf, "
				+ "pg AS pg, "
				+ "ai AS ai, "
				+ "pp AS pp, "
				+ "partida AS partida, "
				+ "puesto AS puesto, "
				+ "num_pto AS numPto, "
				+ "edo AS edo, "
				+ "mpio As mpio, "
				+ "cr AS cr, "
				+ "ci AS ci, "
				+ "paga_d AS pagaD, "
				+ "financiamiento AS financiamiento, "
				+ "tab_pto AS tabPto, "
				+ "nivel AS nivel, "
				+ "rango AS rango, "
				+ "ind_mando AS indMando, "
				+ "hora AS hora, "
				+ "porcent AS porcent, "
				+ "tipo_trab AS tipoTrab, "
				+ "nivel_pto AS nivelPto, "
				+ "ind_emp AS indEmp, "
				+ "fIgf AS fIgf, "
				+ "fIssa AS fIssa, "
				+ "f_reing AS fReing, "
				+ "tipo_mov AS tipoMov, "
				+ "f_pago As fPago, "
				+ "p_pago_i AS pPagoI, "
				+ "p_pago_f AS pPagoF, "
				+ "p_qna_i AS pQnaI, "
				+ "p_qna_f As pQnaF, "
				+ "qna_real AS qnaReal, "
				+ "anio_real AS anioReal, "
				+ "tipo_pago AS tipoPago, "
				+ "instru_a AS instruA, "
				+ "instru_n AS instruN, "
				+ "per AS per, "
				+ "ded AS ded, "
				+ "neto AS neto, "
				+ "no_trail AS noTrail, "
				+ "dias_lab AS diasLab, "
				+ "nom_prod AS nomProd, "
				+ "num_ctrol AS numCtrol, "
				+ "num_cheq AS numCheq, "
				+ "dig_ver AS digVer, "
				+ "jornada as jornada, "
				+ "dias_p AS diasP, "
				+ "ciclo_f AS cicloF, "
				+ "num_aport AS numAport, "
				+ "acum_f AS acumF, "
				+ "faltas AS faltas, "
				+ "clues  AS clues, "
				+ "por_pen_01 AS porPen01, "
				+ "por_pen_02 AS porPen02, "
				+ "por_pen_03 AS porPen03, "
				+ "por_pen_04 AS porPen04, "
				+ "por_pen_05 AS porPen05, "
				+ "issste AS issste, "
				+ "tipo_uni AS tipoUni, "
				+ "cresp_des AS crespDes, "
				+ "id_nombramiento AS idNombramiento "		
				+ "FROM estructuras_nominas WHERE  rfc LIKE :rfc")
				.setParameter("rfc", "%" + rfc + "%");
		query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosPlantillaExcelDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraContratosPlantillaExcelDTO> result = (List<EstructuraContratosPlantillaExcelDTO>) query.list();
		return result;
	}
	
	
	public List<AcumuladosDTO> listaConsultaNominaPorNombramiento(Integer tipoNombramiento, Integer quincenaIni, Integer quincenaFin, Integer anio ) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"CALL usp_acumulado_nomina(:tipoNombramiento, :quincenaIni, :quincenaFin, :anioReal)")
				.setParameter("tipoNombramiento", tipoNombramiento)
				.setParameter("quincenaIni", quincenaIni)
				.setParameter("quincenaFin", quincenaFin)
				.setParameter("anioReal", anio);
		query.setResultTransformer(Transformers.aliasToBean(AcumuladosDTO.class));
		@SuppressWarnings("unchecked")
		List<AcumuladosDTO> result = (List<AcumuladosDTO>) query.list();
		return result;
	}
	
	public List<ConceptoNominaAcumuladoDTO> listaConceptosNominasPorIdEstructuraNomina(Integer idEstructuraNomina){
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT "
				+"conceptos_nominas.id_concepto_nomina as idCoceptoNomina, "
				+"conceptos_nominas.clave as clave, "
				+"conceptos_nominas.tipo as tipo, "
				+"conceptos_nominas.descripcion as descripcion, "
				+"estructuras_nominas_trailers.importe as importe "
				+"FROM "
				+"conceptos_nominas "
				+"INNER JOIN estructuras_nominas_trailers ON estructuras_nominas_trailers.id_concepto = conceptos_nominas.id_concepto_nomina "
				+"INNER JOIN estructuras_nominas ON estructuras_nominas_trailers.id_estructuras_nominas = estructuras_nominas.id_estructuras_nominas "
				+"WHERE "
				+"estructuras_nominas.id_estructuras_nominas=:idEstructuraNomina")
				.setParameter("idEstructuraNomina",idEstructuraNomina);
				query.setResultTransformer(Transformers.aliasToBean(ConceptoNominaAcumuladoDTO.class));
				@SuppressWarnings("unchecked")
				List<ConceptoNominaAcumuladoDTO> result = (List<ConceptoNominaAcumuladoDTO>) query.list();
				return result;
	}
	
	
	public EstructuraNominaDatDTO obtenerEstructuraNominaDatPorId(Integer idEstructurasNominas) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas AS idEstructurasNominas, "
				+ "num_emp AS numEmp, "
				+ "rfc AS rfc, "
				+ "curp AS curp, "
				+ "nombre AS nombre, "
				+ "sar AS sar, "
				+ "banco_a AS bancoA, "
				+ "banco_n AS bancoN, "
				+ "num_cta AS numCta, "
				+ "clabe AS clabe, "
				+ "calle AS calle, "
				+ "colonia AS colonia, "
				+ "deleg AS deleg, "
				+ "ur AS ur, "
				+ "gf AS gf, "
				+ "fn AS fn, "
				+ "sf AS sf, "
				+ "pg AS pg, "
				+ "ai AS ai, "
				+ "pp AS pp, "
				+ "partida AS partida, "
				+ "puesto AS puesto, "
				+ "num_pto AS numPto, "
				+ "edo AS edo, "
				+ "mpio As mpio, "
				+ "cr AS cr, "
				+ "ci AS ci, "
				+ "paga_d AS pagaD, "
				+ "financiamiento AS financiamiento, "
				+ "tab_pto AS tabPto, "
				+ "nivel AS nivel, "
				+ "rango AS rango, "
				+ "ind_mando AS indMando, "
				+ "hora AS hora, "
				+ "porcent AS porcent, "
				+ "tipo_trab AS tipoTrab, "
				+ "nivel_pto AS nivelPto, "
				+ "ind_emp AS indEmp, "
				+ "fIgf AS fIgf, "
				+ "fIssa AS fIssa, "
				+ "f_reing AS fReing, "
				+ "tipo_mov AS tipoMov, "
				+ "f_pago As fPago, "
				+ "p_pago_i AS pPagoI, "
				+ "p_pago_f AS pPagoF, "
				+ "p_qna_i AS pQnaI, "
				+ "p_qna_f As pQnaF, "
				+ "qna_real AS qnaReal, "
				+ "anio_real AS anioReal, "
				+ "tipo_pago AS tipoPago, "
				+ "instru_a AS instruA, "
				+ "instru_n AS instruN, "
				+ "per AS per, "
				+ "ded AS ded, "
				+ "neto AS neto, "
				+ "no_trail AS noTrail, "
				+ "dias_lab AS diasLab, "
				+ "nom_prod AS nomProd, "
				+ "num_ctrol AS numCtrol, "
				+ "num_cheq AS numCheq, "
				+ "dig_ver AS digVer, "
				+ "jornada as jornada, "
				+ "dias_p AS diasP, "
				+ "ciclo_f AS cicloF, "
				+ "num_aport AS numAport, "
				+ "acum_f AS acumF, "
				+ "faltas AS faltas, "
				+ "clues  AS clues, "
				+ "por_pen_01 AS porPen01, "
				+ "por_pen_02 AS porPen02, "
				+ "por_pen_03 AS porPen03, "
				+ "por_pen_04 AS porPen04, "
				+ "por_pen_05 AS porPen05, "
				+ "issste AS issste, "
				+ "tipo_uni AS tipoUni, "
				+ "cresp_des AS crespDes, "
				+ "tipo_emision_nomina As tipoEmisionNomina "				
				+ "FROM estructuras_nominas WHERE id_estructuras_nominas = :idEstructurasNominas")
				.setParameter("idEstructurasNominas", idEstructurasNominas);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));
		EstructuraNominaDatDTO result = (EstructuraNominaDatDTO) query.list().get(0);
		return result;
	}
	


	public List<EstructuraNominaTrailersDTO> listaEstructuraNominaTrailers() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				  "SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
				+ "rfc As rfc, "
				+ "num_emp AS numEmp, "
				+ "num_cheq AS numCheq, "
				+ "t_concep AS tConcep, "
				+ "concep AS concep, "
				+ "importe As importe, "
				+ "anio AS anio, "
				+ "qna AS qna, "
				+ "pta_ant AS ptaAnt, "
				+ "tot_pagos AS totPagos, "
				+ "pago_efec AS pagoEfec, "
				+ "nom_prod As nomProd, "
				+ "num_ctrol AS numCtrol, "
				+ "id_estructuras_nominas AS idEstructurasNominas, "
				+ "id_siif_bitacoras AS idSiifBitacora, "
				+ "sub_cheque AS subCheque, "
				+ "id_concepto AS idConcepto, "
				+ "concepto_siif AS conceptoSiif "				
				+ "FROM estructuras_nominas_trailers");
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}
		
	public List<EstructuraNominaTrailersDTO> listaConsultaNominaTrailersPorCriterios(String rfc) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc As rfc, "
						+ "num_emp AS numEmp, "
						+ "num_cheq AS numCheq, "
						+ "t_concep AS tConcep, "
						+ "concep AS concep, "
						+ "importe As importe, "
						+ "anio AS anio, "
						+ "qna AS qna, "
						+ "pta_ant AS ptaAnt, "
						+ "tot_pagos AS totPagos, "
						+ "pago_efec AS pagoEfec, "
						+ "nom_prod As nomProd, "
						+ "num_ctrol AS numCtrol, "
						+ "id_estructuras_nominas AS idEstructurasNominas, "
						+ "id_siif_bitacoras AS idSiifBitacora, "
						+ "sub_cheque AS subCheque, "
						+ "id_concepto AS idConcepto, "
						+ "concepto_siif AS conceptoSiif "				
						+ "FROM estructuras_nominas_trailers WHERE  rfc LIKE :rfc")
				.setParameter("rfc", "%" + rfc + "%");
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}
	
	public List<EstructuraNominaTrailersDTO> listaConsultaNominaTrailersPorIdDatos(Integer idEstructurasNominas) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc As rfc, "
						+ "num_emp AS numEmp, "
						+ "num_cheq AS numCheq, "
						+ "t_concep AS tConcep, "
						+ "concep AS concep, "
						+ "importe As importe, "
						+ "anio AS anio, "
						+ "qna AS qna, "
						+ "pta_ant AS ptaAnt, "
						+ "tot_pagos AS totPagos, "
						+ "pago_efec AS pagoEfec, "
						+ "nom_prod As nomProd, "
						+ "num_ctrol AS numCtrol, "
						+ "id_estructuras_nominas AS idEstructurasNominas, "
						+ "id_siif_bitacoras AS idSiifBitacora, "
						+ "sub_cheque AS subCheque, "
						+ "id_concepto AS idConcepto, "
						+ "concepto_siif AS conceptoSiif "				
						+ "FROM estructuras_nominas_trailers WHERE  id_estructuras_nominas = :idEstructurasNominas")
				.setParameter("idEstructurasNominas",idEstructurasNominas);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}
	
	public EstructuraNominaTrailersDTO obtenerEstructuraNominaTrailersDatPorId(Integer idEstructurasNominasTrailers) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc As rfc, "
						+ "num_emp AS numEmp, "
						+ "num_cheq AS numCheq, "
						+ "t_concep AS tConcep, "
						+ "concep AS concep, "
						+ "importe As importe, "
						+ "anio AS anio, "
						+ "qna AS qna, "
						+ "pta_ant AS ptaAnt, "
						+ "tot_pagos AS totPagos, "
						+ "pago_efec AS pagoEfec, "
						+ "nom_prod As nomProd, "
						+ "num_ctrol AS numCtrol, "
						+ "id_estructuras_nominas AS idEstructurasNominas, "
						+ "id_siif_bitacoras AS idSiifBitacora, "
						+ "sub_cheque AS subCheque, "
						+ "id_concepto AS idConcepto, "
						+ "concepto_siif AS conceptoSiif "				
						+ "FROM estructuras_nominas_trailers WHERE id_estructuras_nominas_trailers = :idEstructurasNominasTrailers")
				.setParameter("idEstructurasNominasTrailers", idEstructurasNominasTrailers);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		EstructuraNominaTrailersDTO result = (EstructuraNominaTrailersDTO) query.list().get(0);
		return result;
	}
	
//	<<<CLAE para Estructura Nomina Datos(Creacion-Lectura-Actualizacion-Eliminacion)>>>
	
	public EstructuraNominaDatDTO nuevosDatos() {
			EstructuraNominaDatDTO  dto = new EstructuraNominaDatDTO();
			dto.setRfc(null);
			dto.setCurp(null);
			dto.setNombre(null);
			dto.setBancoN(null);
			dto.setNumCta(null);
			dto.setFuncion(null);
			dto.setUr(null);
			dto.setFn(null);
			dto.setSf(null);
			dto.setPg(null);
			dto.setPartida(null);
			dto.setPuesto(null);
			dto.setCr(null);
			dto.setFinanciamiento(null);
			dto.setfIgf(null);
			dto.setfIssa(null);
			dto.setfReing(null);
			dto.setTipoPago(null);
			dto.setClues(null);
			dto.setIdNombramiento(null);
			dto.setTipoEmisionNomina(null);
			return dto;
	}

	public EstructuraNominaDatDTO crearDatos(EstructuraNominaDatDTO dto) {
		EstructuraNominaDatEntity entity = new EstructuraNominaDatEntity();
		entity.setRfc(dto.getRfc());
		entity.setCurp(dto.getCurp());
		entity.setNombre(dto.getNombre());
		entity.setBancoN(dto.getBancoN());
		entity.setNumCta(dto.getNumCta());
		entity.setFuncion(dto.getFuncion());
		entity.setUr(dto.getUr());
		entity.setFn(dto.getFn());
		entity.setSf(dto.getSf());
		entity.setPg(dto.getPg());
		entity.setPartida(dto.getPartida());
		entity.setPuesto(dto.getPuesto());
		entity.setCr(dto.getCr());
		entity.setFinanciamiento(dto.getFinanciamiento());
		entity.setfIgf(dto.getfIgf());
		entity.setfIssa(dto.getfIssa());
		entity.setfReing(dto.getfReing());
		entity.setTipoPago(dto.getTipoPago());
		entity.setClues(dto.getClues());
		entity.setIdNombramiento(dto.getIdNombramiento());
		entity.setTipoEmisionNomina(dto.getTipoEmisionNomina());
		DAO.crear(entity);
		return obtenerEstructuraNominaDatPorId(entity.getIdEstructurasNominas());
	}

	public EstructuraNominaDatDTO actualizarDatos(EstructuraNominaDatDTO dto) {
		EstructuraNominaDatEntity entity = DAO.obtenerPorId(dto.getIdEstructurasNominas());
		entity.setRfc(dto.getRfc());
		entity.setCurp(dto.getCurp());
		entity.setNombre(dto.getNombre());
		entity.setBancoN(dto.getBancoN());
		entity.setNumCta(dto.getNumCta());
		entity.setFuncion(dto.getFuncion());
		entity.setUr(dto.getUr());
		entity.setFn(dto.getFn());
		entity.setSf(dto.getSf());
		entity.setPg(dto.getPg());
		entity.setPartida(dto.getPartida());
		entity.setPuesto(dto.getPuesto());
		entity.setCr(dto.getCr());
		entity.setFinanciamiento(dto.getFinanciamiento());
		entity.setfIgf(dto.getfIgf());
		entity.setfIssa(dto.getfIssa());
		entity.setfReing(dto.getfReing());
		entity.setTipoPago(dto.getTipoPago());
		entity.setClues(dto.getClues());
		entity.setIdNombramiento(dto.getIdNombramiento());
		entity.setTipoEmisionNomina(dto.getTipoEmisionNomina());
		DAO.actualizar(entity);
		return obtenerEstructuraNominaDatPorId(entity.getIdEstructurasNominas());
	}
	
	
//	<<<CLAE para Estructura Nomina Trailers(Creacion-Lectura-Actualizacion-Eliminacion)>>>
	
	public EstructuraNominaTrailersDTO nuevosTrailers() {
			EstructuraNominaTrailersDTO  dto = new EstructuraNominaTrailersDTO();
			dto.setRfc(null);
			dto.setNumCheq(null);
			dto.settConcep(null);
			dto.setConcep(null);
			dto.setImporte(null);
			dto.setAnio(null);
			dto.setQna(null);
			dto.setPtaAnt(null);
			dto.setTotPagos(null);
			dto.setPagoEfec(null);
			dto.setNomProd(null);
			dto.setNomProd(null);
			dto.setNumCtrol(null);
			dto.setSubCheque(null);			
			return dto;
	}

	public EstructuraNominaTrailersDTO crearTrailers(EstructuraNominaTrailersDTO dto) {
		EstructuraNominaTrailersEntity entity = new EstructuraNominaTrailersEntity();
		entity.setRfc(dto.getRfc());
		entity.setNumCheq(dto.getNumCheq());
		entity.setTConcep(dto.gettConcep());
		entity.setConcep(dto.getConcep());
		entity.setImporte(dto.getImporte());
		entity.setAnio(dto.getAnio());
		entity.setQna(dto.getQna());
		entity.setPtaAnt(dto.getPtaAnt());
		entity.setTotPAgos(dto.getTotPagos());
		entity.setPagoEfec(dto.getPagoEfec());
		entity.setNomProd(dto.getNomProd());
		entity.setNumCtrol(dto.getNumCtrol());
		entity.setSubCheque(dto.getSubCheque());
		trailersDAO.crear(entity);
		return obtenerEstructuraNominaTrailersDatPorId(entity.getIdEstructurasNominasTrailers());
	}

	public EstructuraNominaTrailersDTO actualizarTrailers(EstructuraNominaTrailersDTO dto) {
		EstructuraNominaTrailersEntity entity = trailersDAO.obtenerPorId(dto.getIdEstructurasNominasTrailers());
		entity.setRfc(dto.getRfc());
		entity.setNumCheq(dto.getNumCheq());
		entity.setTConcep(dto.gettConcep());
		entity.setConcep(dto.getConcep());
		entity.setImporte(dto.getImporte());
		entity.setAnio(dto.getAnio());
		entity.setQna(dto.getQna());
		entity.setPtaAnt(dto.getPtaAnt());
		entity.setTotPAgos(dto.getTotPagos());
		entity.setPagoEfec(dto.getPagoEfec());
		entity.setNomProd(dto.getNomProd());
		entity.setNumCtrol(dto.getNumCtrol());
		entity.setSubCheque(dto.getSubCheque());
		trailersDAO.actualizar(entity);
		return obtenerEstructuraNominaTrailersDatPorId(entity.getIdEstructurasNominasTrailers());
	}
	
	public void modificarTrailers(String rfc, Integer id){
			for(EstructuraNominaTrailersDTO tra: listaConsultaNominaTrailersPorIdDatos(id)){
//			for(EstructuraNominaTrailersDTO tra: listaConsultaNominaTrailersPorCriterios(rfcOriginal)){
				System.out.println("lista encontrada..."+tra);
				EstructuraNominaTrailersEntity entity = trailersDAO.obtenerPorId(tra.getIdEstructurasNominasTrailers());
				entity.setRfc(rfc);
			}
	}
}