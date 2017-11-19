
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.EstructuraContratoEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoTrailersRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

@Stateless
public class ConsultaProsperaService {
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    @Inject
    private EstructuraContratoRepository DAO;
    @Inject
    private EstructuraContratoTrailersRepository trailersDAO;

    public void modificarTrailers(String rfc, Integer id) {
        for (EstructuraContratosTrailersDTO tra : listaConsultaProsperaTrailersPorCriterios(
                rfc)) {
            //			for(EstructuraNominaTrailersDTO tra: listaConsultaNominaTrailersPorCriterios(rfcOriginal)){
            System.out.println("lista encontrada..." + tra);
            EstructuraContratoTrailersEntity entity = trailersDAO
                    .obtenerPorId(tra.getIdEstructurasContratosTrailers());
            entity.setRfc(rfc);
        }
    }

    /**
     *
     * Aqui Comienza lo de Prospera
     *
     **/
    //TODO	<<<<<Prospera Datos>>>>>

    public List<EstructuraContratosDatDTO> listaEstructuraProspera() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos AS idEstructurasContratos, "
                        + "num_emp AS numEmp, " + "rfc AS rfc, "
                        + "curp AS curp, " + "nombre AS nombre, "
                        + "sar AS sar, " + "banco_a AS bancoA, "
                        + "banco_n AS bancoN, " + "num_cta AS numCta, "
                        + "clabe AS clabe, " + "funcion AS funcion, "
                        + "cp AS cp, " + "calle AS calle, "
                        + "puesto AS puesto, " + "des_puesto AS desPuesto, "
                        + "ur AS ur, " + "gf AS gf, " + "fn AS fn, "
                        + "sf AS sf, " + "pg AS pg, " + "ai AS ai, "
                        + "pp AS pp, " + "partida AS partida, "
                        + "num_pto AS numPto, " + "edo AS edo, "
                        + "mpio As mpio, " + "cr AS cr, " + "ci AS ci, "
                        + "paga_d AS pagaD, "
                        + "financiamiento AS financiamiento, "
                        + "tab_pto AS tabPto, " + "nivel AS nivel, "
                        + "rango AS rango, " + "ind_mando AS indMando, "
                        + "horas AS horas, " + "porcent AS porcent, "
                        + "tipo_trab AS tipoTrab, " + "nivel_pto AS nivelPto, "
                        + "ind_emp AS indEmp, " + "fec_inicial AS fecInicial, "
                        + "fec_final AS fecFinal, "
                        + "fec_ingreso AS fecIngreso, "
                        + "tipo_mov AS tipoMov, " + "f_pago As fPago, "
                        + "p_pago_i AS pPagoI, " + "p_pago_f AS pPagoF, "
                        + "p_qna_i AS pQnaI, " + "p_qna_f As pQnaF, "
                        + "qna_real AS qnaReal, " + "anio_real AS anioReal, "
                        + "tipo_pago AS tipoPago, " + "instru_a AS instruA, "
                        + "instru_n AS instruN, " + "per AS per, "
                        + "ded AS ded, " + "neto AS neto, "
                        + "no_trail AS noTrail, " + "dias_lab AS diasLab, "
                        + "nom_prod AS nomProd, " + "num_ctrol AS numCtrol, "
                        + "num_cheq AS numCheq, " + "dig_ver AS digVer, "
                        + "jornada as jornada, " + "dias_p AS diasP, "
                        + "ciclo_f AS cicloF, " + "num_aport AS numAport, "
                        + "acum_f AS acumF, " + "faltas AS faltas, "
                        + "clues  AS clues, " + "por_pen_01 AS porPen01, "
                        + "por_pen_02 AS porPen02, "
                        + "por_pen_03 AS porPen03, "
                        + "por_pen_04 AS porPen04, "
                        + "por_pen_05 AS porPen05, " + "issste AS issste, "
                        + "tipo_uni AS tipoUni, " + "cresp_des AS crespDes, "
                        + "tipo_emision_nomina As tipoEmisionNomina "
                        + "FROM estructuras_contratos");
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        @SuppressWarnings("unchecked")
        List<EstructuraContratosDatDTO> result = query.list();
        return result;
    }

    public EstructuraContratosDatDTO obtenerEstructuraProsperaDatPorId(
            Integer idEstructurasContratos) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos AS idEstructurasContratos, "
                        + "num_emp AS numEmp, " + "rfc AS rfc, "
                        + "curp AS curp, " + "nombre AS nombre, "
                        + "sar AS sar, " + "banco_a AS bancoA, "
                        + "banco_n AS bancoN, " + "num_cta AS numCta, "
                        + "clabe AS clabe, " + "funcion AS funcion, "
                        + "cp AS cp, " + "calle AS calle, "
                        + "puesto AS puesto, " + "des_puesto AS desPuesto, "
                        + "ur AS ur, " + "gf AS gf, " + "fn AS fn, "
                        + "sf AS sf, " + "pg AS pg, " + "ai AS ai, "
                        + "pp AS pp, " + "partida AS partida, "
                        + "num_pto AS numPto, " + "edo AS edo, "
                        + "mpio As mpio, " + "cr AS cr, " + "ci AS ci, "
                        + "paga_d AS pagaD, "
                        + "financiamiento AS financiamiento, "
                        + "tab_pto AS tabPto, " + "nivel AS nivel, "
                        + "rango AS rango, " + "ind_mando AS indMando, "
                        + "horas AS horas, " + "porcent AS porcent, "
                        + "tipo_trab AS tipoTrab, " + "nivel_pto AS nivelPto, "
                        + "ind_emp AS indEmp, " + "fec_inicial AS fecInicial, "
                        + "fec_final AS fecFinal, "
                        + "fec_ingreso AS fecIngreso, "
                        + "tipo_mov AS tipoMov, " + "f_pago As fPago, "
                        + "p_pago_i AS pPagoI, " + "p_pago_f AS pPagoF, "
                        + "p_qna_i AS pQnaI, " + "p_qna_f As pQnaF, "
                        + "qna_real AS qnaReal, " + "anio_real AS anioReal, "
                        + "tipo_pago AS tipoPago, " + "instru_a AS instruA, "
                        + "instru_n AS instruN, " + "per AS per, "
                        + "ded AS ded, " + "neto AS neto, "
                        + "no_trail AS noTrail, " + "dias_lab AS diasLab, "
                        + "nom_prod AS nomProd, " + "num_ctrol AS numCtrol, "
                        + "num_cheq AS numCheq, " + "dig_ver AS digVer, "
                        + "jornada as jornada, " + "dias_p AS diasP, "
                        + "ciclo_f AS cicloF, " + "num_aport AS numAport, "
                        + "acum_f AS acumF, " + "faltas AS faltas, "
                        + "clues  AS clues, " + "por_pen_01 AS porPen01, "
                        + "por_pen_02 AS porPen02, "
                        + "por_pen_03 AS porPen03, "
                        + "por_pen_04 AS porPen04, "
                        + "por_pen_05 AS porPen05, " + "issste AS issste, "
                        + "tipo_uni AS tipoUni, " + "cresp_des AS crespDes, "
                        + "tipo_emision_nomina As tipoEmisionNomina "
                        + "FROM estructuras_contratos WHERE id_estructuras_contratos = :idEstructurasContratos")
                .setParameter("idEstructurasContratos", idEstructurasContratos);
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        EstructuraContratosDatDTO result = (EstructuraContratosDatDTO) query
                .list().get(0);
        return result;
    }

    public List<EstructuraContratosDatDTO> listaEstructuraProsperaPorCriterios(
            String rfc) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos AS idEstructurasContratos, "
                        + "num_emp AS numEmp, " + "rfc AS rfc, "
                        + "curp AS curp, " + "nombre AS nombre, "
                        + "sar AS sar, " + "banco_a AS bancoA, "
                        + "banco_n AS bancoN, " + "num_cta AS numCta, "
                        + "clabe AS clabe, " + "funcion AS funcion, "
                        + "cp AS cp, " + "calle AS calle, "
                        + "puesto AS puesto, " + "des_puesto AS desPuesto, "
                        + "ur AS ur, " + "gf AS gf, " + "fn AS fn, "
                        + "sf AS sf, " + "pg AS pg, " + "ai AS ai, "
                        + "pp AS pp, " + "partida AS partida, "
                        + "num_pto AS numPto, " + "edo AS edo, "
                        + "mpio As mpio, " + "cr AS cr, " + "ci AS ci, "
                        + "paga_d AS pagaD, "
                        + "financiamiento AS financiamiento, "
                        + "tab_pto AS tabPto, " + "nivel AS nivel, "
                        + "rango AS rango, " + "ind_mando AS indMando, "
                        + "horas AS horas, " + "porcent AS porcent, "
                        + "tipo_trab AS tipoTrab, " + "nivel_pto AS nivelPto, "
                        + "ind_emp AS indEmp, " + "fec_inicial AS fecInicial, "
                        + "fec_final AS fecFinal, "
                        + "fec_ingreso AS fecIngreso, "
                        + "tipo_mov AS tipoMov, " + "f_pago As fPago, "
                        + "p_pago_i AS pPagoI, " + "p_pago_f AS pPagoF, "
                        + "p_qna_i AS pQnaI, " + "p_qna_f As pQnaF, "
                        + "qna_real AS qnaReal, " + "anio_real AS anioReal, "
                        + "tipo_pago AS tipoPago, " + "instru_a AS instruA, "
                        + "instru_n AS instruN, " + "per AS per, "
                        + "ded AS ded, " + "neto AS neto, "
                        + "no_trail AS noTrail, " + "dias_lab AS diasLab, "
                        + "nom_prod AS nomProd, " + "num_ctrol AS numCtrol, "
                        + "num_cheq AS numCheq, " + "dig_ver AS digVer, "
                        + "jornada as jornada, " + "dias_p AS diasP, "
                        + "ciclo_f AS cicloF, " + "num_aport AS numAport, "
                        + "acum_f AS acumF, " + "faltas AS faltas, "
                        + "clues  AS clues, " + "por_pen_01 AS porPen01, "
                        + "por_pen_02 AS porPen02, "
                        + "por_pen_03 AS porPen03, "
                        + "por_pen_04 AS porPen04, "
                        + "por_pen_05 AS porPen05, " + "issste AS issste, "
                        + "tipo_uni AS tipoUni, " + "cresp_des AS crespDes, "
                        + "tipo_emision_nomina As tipoEmisionNomina "
                        + "FROM estructuras_contratos WHERE  rfc LIKE :rfc")
                .setParameter("rfc", "%" + rfc + "%");
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        @SuppressWarnings("unchecked")
        List<EstructuraContratosDatDTO> result = query.list();
        return result;
    }

    //TODO 	< < < < < Trailers Prospera > > > > >

    public List<EstructuraContratosTrailersDTO> listaEstructuraProsperaTrailers() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos_trailers AS idEstructurasContratosTrailers, "
                        + "rfc As rfc, " + "num_emp AS numEmp, "
                        + "num_cheq AS numCheq, " + "t_concep AS tConcep, "
                        + "concep AS concep, " + "importe As importe, "
                        + "anio AS anio, " + "qna AS qna, "
                        + "pta_ant AS ptaAnt, " + "tot_pagos AS totPagos, "
                        + "pago_efec AS pagoEfec, " + "nom_prod As nomProd, "
                        + "num_ctrol AS numControl, "
                        + "id_estructuras_contratos AS idEstructurasContratos, "
                        + "id_siif_bitacoras AS idSiifBitacoras, "
                        + "id_siif_encabezados AS idSiifEncabezados, "
                        + "id_concepto AS idConcepto, "
                        + "concepto_siif AS conceptosSiif "
                        + "FROM estructuras_contratos_trailers");
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        @SuppressWarnings("unchecked")
        List<EstructuraContratosTrailersDTO> result = query.list();
        return result;
    }

    public List<EstructuraContratosTrailersDTO> listaConsultaProsperaTrailersPorCriterios(
            String rfc) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos_trailers AS idEstructurasContratosTrailers, "
                        + "rfc As rfc, " + "num_emp AS numEmp, "
                        + "num_cheq AS numCheq, " + "t_concep AS tConcep, "
                        + "concep AS concep, " + "importe As importe, "
                        + "anio AS anio, " + "qna AS qna, "
                        + "pta_ant AS ptaAnt, " + "tot_pagos AS totPagos, "
                        + "pago_efec AS pagoEfec, " + "nom_prod As nomProd, "
                        + "num_ctrol AS numControl, "
                        + "id_estructuras_contratos AS idEstructurasContratos, "
                        + "id_siif_bitacoras AS idSiifBitacoras, "
                        + "id_siif_encabezados AS idSiifEncabezados, "
                        + "id_concepto AS idConcepto, "
                        + "concepto_siif AS conceptosSiif "
                        + "FROM estructuras_contratos_trailers WHERE  rfc LIKE :rfc")
                .setParameter("rfc", "%" + rfc + "%");
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        @SuppressWarnings("unchecked")
        List<EstructuraContratosTrailersDTO> result = query.list();
        return result;
    }

    public EstructuraContratosTrailersDTO obtenerEstructuraProsperaTrailersDatPorId(
            Integer idEstructurasContratosTrailers) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_estructuras_contratos_trailers AS idEstructurasContratosTrailers, "
                        + "rfc As rfc, " + "num_emp AS numEmp, "
                        + "num_cheq AS numCheq, " + "t_concep AS tConcep, "
                        + "concep AS concep, " + "importe As importe, "
                        + "anio AS anio, " + "qna AS qna, "
                        + "pta_ant AS ptaAnt, " + "tot_pagos AS totPagos, "
                        + "pago_efec AS pagoEfec, " + "nom_prod As nomProd, "
                        + "num_ctrol AS numControl, "
                        + "id_estructuras_contratos AS idEstructurasContratos, "
                        + "id_siif_bitacoras AS idSiifBitacoras, "
                        + "id_siif_encabezados AS idSiifEncabezados, "
                        + "id_concepto AS idConcepto, "
                        + "concepto_siif AS conceptosSiif "
                        + "FROM estructuras_contratos_trailers WHERE id_estructuras_contratos_trailers = :idEstructurasContratosTrailers")
                .setParameter("idEstructurasContratosTrailers",
                        idEstructurasContratosTrailers);
        query.setResultTransformer(
                Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        EstructuraContratosTrailersDTO result = (EstructuraContratosTrailersDTO) query
                .list().get(0);
        return result;
    }

    public EstructuraContratosDatDTO nuevosDatos() {
        EstructuraContratosDatDTO dto = new EstructuraContratosDatDTO();
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
        dto.setTipoPago(null);
        dto.setClues(null);
        dto.setIdNombramiento(null);
        dto.setTipoEmisionNomina(null);
        return dto;
    }

    //TODO <<<CLAE para Estructura Prospera Datos(Creacion-Lectura-Actualizacion-Eliminacion)>>>

    public EstructuraContratosDatDTO crearDatosProspera(
            EstructuraContratosDatDTO dto) {
        EstructuraContratoEntity entity = new EstructuraContratoEntity();
        entity.setNumEmp(dto.getNumEmp());
        entity.setRfc(dto.getRfc());
        entity.setCurp(dto.getCurp());
        entity.setNombre(dto.getNombre());
        entity.setSar(dto.getSar());
        entity.setBancoA(dto.getBancoA());
        entity.setBancoN(dto.getBancoN());
        entity.setNumCta(dto.getNumCta());
        entity.setClabe(null);
        entity.setFuncion(dto.getFuncion());
        entity.setUr(dto.getUr());
        entity.setFn(dto.getFn());
        entity.setSf(dto.getSf());
        entity.setPg(dto.getPg());
        entity.setPartida(dto.getPartida());
        entity.setPuesto(dto.getPuesto());
        entity.setCr(dto.getCr());
        entity.setFinanciamiento(dto.getFinanciamiento());
        entity.setTipoPago(dto.getTipoPago());
        entity.setClues(dto.getClues());
        entity.setIdNombramiento(dto.getIdNombramiento());
        entity.setTipoEmisionNomina(dto.getTipoEmisionNomina());
        DAO.crear(entity);
        return obtenerEstructuraProsperaDatPorId(
                entity.getIdEstructurasContratos());

    }

    public EstructuraContratosDatDTO actualizarDatosProspera(
            EstructuraContratosDatDTO dto) {
        EstructuraContratoEntity entity = DAO
                .obtenerPorId(dto.getIdEstructurasContratos());
        entity.setNumEmp(dto.getNumEmp());
        entity.setRfc(dto.getRfc());
        entity.setCurp(dto.getCurp());
        entity.setNombre(dto.getNombre());
        entity.setSar(dto.getSar());
        entity.setBancoA(dto.getBancoA());
        entity.setBancoN(dto.getBancoN());
        entity.setNumCta(dto.getNumCta());
        entity.setClabe(null);
        entity.setFuncion(dto.getFuncion());
        entity.setUr(dto.getUr());
        entity.setFn(dto.getFn());
        entity.setSf(dto.getSf());
        entity.setPg(dto.getPg());
        entity.setPartida(dto.getPartida());
        entity.setPuesto(dto.getPuesto());
        entity.setCr(dto.getCr());
        entity.setFinanciamiento(dto.getFinanciamiento());
        entity.setTipoPago(dto.getTipoPago());
        entity.setClues(dto.getClues());
        entity.setIdNombramiento(dto.getIdNombramiento());
        entity.setTipoEmisionNomina(dto.getTipoEmisionNomina());
        DAO.actualizar(entity);
        return obtenerEstructuraProsperaDatPorId(
                entity.getIdEstructurasContratos());

    }

    public EstructuraContratosTrailersDTO nuevosTrailersProspera() {
        EstructuraContratosTrailersDTO dto = new EstructuraContratosTrailersDTO();
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
        dto.setNumControl(null);
        return dto;
    }

    public EstructuraContratosTrailersDTO crearTrailersProspera(
            EstructuraContratosTrailersDTO dto) {
        EstructuraContratoTrailersEntity entity = new EstructuraContratoTrailersEntity();
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
        entity.setNumCtrol(dto.getNumControl());
        trailersDAO.crear(entity);
        return obtenerEstructuraProsperaTrailersDatPorId(
                entity.getIdEstructurasContratosTrailers());
    }

    public EstructuraContratosTrailersDTO actualizarTrailersProspera(
            EstructuraContratosTrailersDTO dto) {
        EstructuraContratoTrailersEntity entity = trailersDAO
                .obtenerPorId(dto.getIdEstructurasContratosTrailers());
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
        entity.setNumCtrol(dto.getNumControl());
        trailersDAO.actualizar(entity);
        return obtenerEstructuraProsperaTrailersDatPorId(
                entity.getIdEstructurasContratosTrailers());
    }

}