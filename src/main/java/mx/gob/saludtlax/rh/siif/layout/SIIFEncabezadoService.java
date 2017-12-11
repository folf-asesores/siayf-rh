/*
 * SIIFEncabezadoService.java
 * Creado el Jul 5, 2016 6:53:03 PM
 * 
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.persistencia.EstructuraContratoEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoRepository;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoEntity;
import mx.gob.saludtlax.rh.siif.EstructuraNominaDatDTO;
import mx.gob.saludtlax.rh.siif.EstructuraNominaTrailersDTO;
import mx.gob.saludtlax.rh.siif.EstructuraSeguroPopularDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.ReglaNegocioException;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFEncabezadoService {
    private static final Logger LOGGER = Logger.getLogger(SIIFLayoutEJB.class.getName());

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    @Inject
    private EstructuraContratoRepository estructuraDatDAO;

    protected List<SIIFEncabezadoDTO> consultarEncabezado(String periodo, int anyo) {
        //       List<SIIFEncabezadoEntity> encabezadoEntidades = encabezadoRepository.consultarEncabezado(periodo, anyo);
        //       List<SIIFEncabezadoDTO> encabezadoDTOs = convertirListaEncabezadosEntidadesADTOs(encabezadoEntidades);
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_obtener_encabezados(:anyo, :periodo)").setParameter("anyo", anyo).setParameter("periodo", periodo);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));

        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<SIIFEncabezadoDTO> generarEncabezado(String periodo, int anyo) {
        //      List<SIIFEncabezadoEntity> encabezadoEntidades = encabezadoRepository.consultarEncabezado(periodo, anyo);
        //      List<SIIFEncabezadoDTO> encabezadoDTOs = convertirListaEncabezadosEntidadesADTOs(encabezadoEntidades);
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_encabezados(:anyo, :periodo)").setParameter("anyo", anyo).setParameter("periodo", periodo);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));

        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<SIIFEncabezadoDTO> generarEncabezadoRH(int idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_encabezados_rh(:idProductoNomina)").setParameter("idProductoNomina", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));

        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected void crearEncabezadoRH(int idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_crear_encabezados_rh(:idProductoNomina)").setParameter("idProductoNomina", idProductoNomina);
        query.executeUpdate();

    }

    protected List<SIIFEncabezadoDTO> consultarEncabezadoRH(int idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_encabezados_rh_cont(:idProductoNomina)").setParameter("idProductoNomina", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));

        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<SIIFEncabezadoDTO> consultarEncabezadoFinal(String periodo, int anyo) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_encabezado_siif_final(:anyo, :periodo)").setParameter("anyo", anyo).setParameter("periodo",
                periodo);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));

        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaTrailersDTO> consultarTra(String periodo, int anyo) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra_rh");

        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));

        @SuppressWarnings("unchecked")
        List<EstructuraNominaTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaTrailersDTO> consultarTraN(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
        List<EstructuraNominaTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaTrailersDTO> consultarTraRH(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra_rh(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
        List<EstructuraNominaTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarTra(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaDatDTO> consultarDat(String periodo, int anyo) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat_rh");

        query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));

        @SuppressWarnings("unchecked")
        List<EstructuraNominaDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraCont(String producto) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra_contrato(:producto)").setParameter("producto", producto);

        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));

        @SuppressWarnings("unchecked")
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatCont(String producto) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat_contrato(:producto)").setParameter("producto", producto);

        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));

        @SuppressWarnings("unchecked")
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<String> listProductos(String quincena) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT DISTINCT ec.nom_prod FROM estructuras_contratos AS ec WHERE ec.qna_real=" + quincena);
        //Query query = session.createSQLQuery("SELECT DISTINCT ec.nom_prod FROM estructuras_contratos AS ec");

        List<String> list = query.list();

        return list;
    }

    protected List<EstructuraContratosDatDTO> consultarDat(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaDatDTO> consultarDatN(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));
        List<EstructuraNominaDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraNominaDatDTO> consultarDatRH(Integer idBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat_rh(:id_siif_bitacora)");
        query.setParameter("id_siif_bitacora", idBitacora);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaDatDTO.class));
        List<EstructuraNominaDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatProdNom(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_dat_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraProdNom(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_generar_tra_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatProdNomRH(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_dat_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraProdNomRH(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_tra_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatProdNomRHCont(Integer id_producto_nomina, Integer id_programa) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_dat_rh_dev_cont(:id_producto_nomina, :id_programa)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setParameter("id_programa", id_programa);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraProdNomRHCont(Integer id_producto_nomina, Integer id_programa) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_tra_rh_dev_cont(:id_producto_nomina, :id_programa)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setParameter("id_programa", id_programa);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatProdNomRHContrato(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_dat_rh_dev_contrato(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraProdNomRHContrato(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_tra_rh_dev_contrato(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosDatDTO> consultarDatProdNomRHContSegPop(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_dat_rh_dev_cont_seg_pop(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    protected List<EstructuraContratosTrailersDTO> consultarTraProdNomRHContSegPop(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_consultar_tra_rh_dev_cont_seg_pop(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    @SuppressWarnings("unchecked")
    protected List<EstructuraContratosDatDTO> crearDatProdNom(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_crear_dat_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        List<EstructuraContratosDatDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    @SuppressWarnings("unchecked")
    protected List<EstructuraContratosTrailersDTO> crearTraProdNom(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_crear_tra_rh_dev(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosTrailersDTO.class));
        @SuppressWarnings("unchecked")
        List<EstructuraContratosTrailersDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;
    }

    @SuppressWarnings("unchecked")
    protected List<SIIFEncabezadoDTO> crearLayoutNominaContrato(Integer id_producto_nomina) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_crear_encabezados_rh_cont(:id_producto_nomina)");
        query.setParameter("id_producto_nomina", id_producto_nomina);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        return siifEncabezadoList;

    }

    @SuppressWarnings("unchecked")
    protected List<EstructuraSeguroPopularDTO> consultarSeguroPopular(String periodo, int anyo) {
        List<Object[]> objetos;
        List<EstructuraSeguroPopularDTO> seguroPopularList = new ArrayList<>();
        objetos = this.entityManager.createNativeQuery("CALL usp_generar_seguro_popular(:periodo)").setParameter("periodo", periodo).getResultList();

        if (objetos.size() > 0) {
            for (Object[] obj : objetos) {

                EstructuraSeguroPopularDTO dto = new EstructuraSeguroPopularDTO();
                //BigInteger numero =((BigInteger)obj[0]);
                Double numero = ((Double) obj[0]);
                dto.setNumero(numero.intValue());
                dto.setMes((String) obj[1]);
                dto.setEstado((String) obj[2]);
                dto.setCentro((String) obj[3]);
                dto.setClues((String) obj[4]);
                dto.setUnidad((String) obj[5]);
                dto.setAdscripcion((String) obj[6]);
                dto.setPuesto((String) obj[7]);
                dto.setClave((String) obj[8]);
                dto.setServicio((String) obj[9]);
                dto.setRama((String) obj[10]);
                dto.setNombre((String) obj[11]);
                dto.setRfc((String) obj[12]);
                dto.setTurno((String) obj[13]);
                dto.setFingreso((String) obj[14]);
                dto.setSueldoBase((BigDecimal) obj[15]);
                dto.setPercepcion((BigDecimal) obj[16]);
                dto.setDeduccion((BigDecimal) obj[17]);
                dto.setNeto((BigDecimal) obj[18]);
                seguroPopularList.add(dto);
            }
        }
        return seguroPopularList;
    }

    @SuppressWarnings("unchecked")
    protected List<EstructuraSeguroPopularDTO> consultarSeguroPopularRHCont(int idProductoNomina) {
        List<Object[]> objetos;
        List<EstructuraSeguroPopularDTO> seguroPopularList = new ArrayList<>();
        objetos = this.entityManager.createNativeQuery("CALL usp_generar_seguro_popular_rh(:idProductoNomina)")
                .setParameter("idProductoNomina", idProductoNomina).getResultList();

        if (objetos.size() > 0) {
            for (Object[] obj : objetos) {

                EstructuraSeguroPopularDTO dto = new EstructuraSeguroPopularDTO();
                //BigInteger numero =((BigInteger)obj[0]);
                Integer numero = ((Integer) obj[0]);
                dto.setNumero(numero.intValue());
                dto.setMes((String) obj[1]);
                dto.setEstado((String) obj[2]);
                dto.setCentro((String) obj[3]);
                dto.setClues((String) obj[4]);
                dto.setUnidad((String) obj[5]);
                dto.setAdscripcion((String) obj[6]);
                dto.setPuesto((String) obj[7]);
                dto.setClave((String) obj[8]);
                dto.setServicio((String) obj[9]);
                dto.setRama((String) obj[10]);
                dto.setNombre((String) obj[11]);
                dto.setRfc((String) obj[12]);
                dto.setTurno((String) obj[13]);
                dto.setFingreso((String) obj[14]);
                dto.setSueldoBase((BigDecimal) obj[15]);
                dto.setPercepcion((BigDecimal) obj[16]);
                dto.setDeduccion((BigDecimal) obj[17]);
                dto.setNeto((BigDecimal) obj[18]);
                seguroPopularList.add(dto);
            }
        }
        return seguroPopularList;
    }

    @SuppressWarnings("unchecked")
    protected List<EstructuraSeguroPopularDTO> consultarSeguroPopularRH(int idProductoNomina) {
        List<Object[]> objetos;
        List<EstructuraSeguroPopularDTO> seguroPopularList = new ArrayList<>();
        objetos = this.entityManager.createNativeQuery("CALL usp_generar_seguro_popular_rh(:idProductoNomina)")
                .setParameter("idProductoNomina", idProductoNomina).getResultList();

        if (objetos.size() > 0) {
            for (Object[] obj : objetos) {

                EstructuraSeguroPopularDTO dto = new EstructuraSeguroPopularDTO();
                //BigInteger numero =((BigInteger)obj[0]);
                Integer numero = ((Integer) obj[0]);
                dto.setNumero(numero.intValue());
                dto.setMes((String) obj[1]);
                dto.setEstado((String) obj[2]);
                dto.setCentro((String) obj[3]);
                dto.setClues((String) obj[4]);
                dto.setUnidad((String) obj[5]);
                dto.setAdscripcion((String) obj[6]);
                dto.setPuesto((String) obj[7]);
                dto.setClave((String) obj[8]);
                dto.setServicio((String) obj[9]);
                dto.setRama((String) obj[10]);
                dto.setNombre((String) obj[11]);
                dto.setRfc((String) obj[12]);
                dto.setTurno((String) obj[13]);
                dto.setFingreso((String) obj[14]);
                dto.setSueldoBase((BigDecimal) obj[15]);
                dto.setPercepcion((BigDecimal) obj[16]);
                dto.setDeduccion((BigDecimal) obj[17]);
                dto.setNeto((BigDecimal) obj[18]);
                seguroPopularList.add(dto);
            }
        }
        return seguroPopularList;
    }

    private static List<SIIFEncabezadoDTO> convertirListaEncabezadosEntidadesADTOs(List<SIIFEncabezadoEntity> encabezadoEntidades) {
        List<SIIFEncabezadoDTO> encabezadoDTOs = new ArrayList<>();

        for (SIIFEncabezadoEntity encabezadoEntidad : encabezadoEntidades) {
            SIIFEncabezadoDTO encabezadoDTO = convertirEncabezadoEntidadADTO(encabezadoEntidad);

            encabezadoDTOs.add(encabezadoDTO);
        }

        return encabezadoDTOs;
    }

    private static SIIFEncabezadoDTO convertirEncabezadoEntidadADTO(SIIFEncabezadoEntity entidad) {

        SIIFEncabezadoDTO dto = new SIIFEncabezadoDTO();

        dto.setIdSIIFEncabezado(entidad.getIdSIIFEncabezado());
        dto.setIdNomina(entidad.getIdNomina());
        dto.setIdPoder(entidad.getIdPoder());
        dto.setIdTipoNomina(entidad.getTipoNomina().getIdTipoNomina());
        dto.setFechaFinQuincena(entidad.getFechaFinQuincena());
        dto.setIdCuentaBancaria(entidad.getCuentaBancaria().getClaveCuenta());
        dto.setIdTipoEmisionNomina(entidad.getIdTipoEmisionNomina());
        dto.setPercepciones(entidad.getPercepciones());
        dto.setDeducciones(entidad.getDeducciones());
        dto.setNeto(entidad.getNeto());
        dto.setIdEstadoNomina(entidad.getIdEstadoNomina());
        dto.setIdSIIFBitacora(entidad.getBitacora().getIdReporteSiif());

        return dto;
    }

    public void generarNumDatCont(List<EstructuraContratosDatDTO> listaDetalles) {

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstructuraDat(List<EstructuraContratosDatDTO> listaEstructura) {
        try {
            Session session = this.entityManager.unwrap(Session.class);

            if (listaEstructura.isEmpty()) {
                throw new ReglaNegocioException("No existen registros de datos");
            } else {
                int a = 1;
                //LOGGER.debugv("Lista tamaño: ", listaEstructura.size());
                for (EstructuraContratosDatDTO dat : listaEstructura) {
                    Query queryA = session.createSQLQuery("UPDATE estructuras_contratos_dat AS d	" + "SET d.num_emp = (:num_emp), " +
                    //"d.nombre = (:nombre), " +
                            "d.num_ctrol = (:num) " + "WHERE d.id_estructuras_contratos = (:id_estructuras_contratos) " + "AND d.id_programa = 23")
                            .setParameter("id_estructuras_contratos", dat.getIdEstructurasContratos()).setParameter("num_emp", formatoNumEmp(a))
                            //.setParameter("nombre",formatoNombre(dat.getNombre()))
                            .setParameter("num", a + 1);
                    queryA.executeUpdate();
                    if (dat.getIdPrograma() != null && dat.getIdPrograma() == 23) {
                        a = a + 1;
                    }
                }
                int f = 1;
                for (EstructuraContratosDatDTO dat : listaEstructura) {
                    Query queryF = session.createSQLQuery("UPDATE estructuras_contratos_dat AS d	" + "SET d.num_emp = (:num_emp), " +
                    //"d.nombre = (:nombre), " +
                            "d.num_ctrol = (:num) " + "WHERE d.id_estructuras_contratos = (:id_estructuras_contratos)" + "AND d.id_programa = 27")
                            .setParameter("id_estructuras_contratos", dat.getIdEstructurasContratos()).setParameter("num_emp", formatoNumEmp(f))
                            //.setParameter("nombre",formatoNombre(dat.getNombre()))
                            .setParameter("num", f + 1);
                    queryF.executeUpdate();
                    if (dat.getIdPrograma() != null && dat.getIdPrograma() == 27) {
                        f = f + 1;
                    }
                }
                int c = 1;
                for (EstructuraContratosDatDTO dat : listaEstructura) {
                    Query queryC = session.createSQLQuery("UPDATE estructuras_contratos_dat AS d	" + "SET d.num_emp = (:num_emp), " +
                    //"d.nombre = (:nombre), " +
                            "d.num_ctrol = (:num) " + "WHERE d.id_estructuras_contratos = (:id_estructuras_contratos)"
                            + "AND ( d.id_programa != 23 AND d.id_programa != 27 and d.id_subfuente_financiamiento != 207)")
                            .setParameter("id_estructuras_contratos", dat.getIdEstructurasContratos()).setParameter("num_emp", formatoNumEmp(c))
                            //.setParameter("nombre",formatoNombre(dat.getNombre()))
                            .setParameter("num", c + 1);
                    queryC.executeUpdate();
                    if (dat.getIdPrograma() != null && dat.getIdPrograma() != 23 && dat.getIdPrograma() != 27) {
                        if (dat.getIdSubfuenteFinanciamiento() != null && dat.getIdSubfuenteFinanciamiento() != 207) {
                            c = c + 1;
                        }
                    }
                }
                int s = 1;
                for (EstructuraContratosDatDTO dat : listaEstructura) {
                    Query querySP = session.createSQLQuery("UPDATE estructuras_contratos_dat AS d	" + "SET d.num_emp = (:num_emp), " +
                    //"d.nombre = (:nombre), " +
                            "d.num_ctrol = (:num) " + "WHERE d.id_estructuras_contratos = (:id_estructuras_contratos)"
                            + "AND d.id_subfuente_financiamiento = 207 ").setParameter("id_estructuras_contratos", dat.getIdEstructurasContratos())
                            .setParameter("num_emp", formatoNumEmp(s))
                            //.setParameter("nombre",formatoNombre(dat.getNombre()))
                            .setParameter("num", s + 1);
                    querySP.executeUpdate();
                    if (dat.getIdSubfuenteFinanciamiento() != null && dat.getIdSubfuenteFinanciamiento() == 207) {
                        s = s + 1;
                    }
                }
            }
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarEstructuraTra(int id_producto_nomina) {

        Session session = this.entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery("UPDATE estructuras_contratos_tra AS t " + "INNER JOIN estructuras_contratos_dat AS d " + "ON d.rfc = t.rfc "
                        + "SET t.num_emp = d.num_emp, " + "t.num_ctrol = d.num_ctrol " + "WHERE t.id_producto_nomina = (:id_producto_nomina)")
                .setParameter("id_producto_nomina", id_producto_nomina);
        query.executeUpdate();
        //Actualiza No_Trail
        Query querytra = session
                .createSQLQuery("UPDATE estructuras_contratos_dat AS d " + "SET d.no_trail = (SELECT COUNT(t.rfc) FROM estructuras_contratos_tra AS t "
                        + "WHERE t.rfc=d.rfc AND t.id_producto_nomina = (:id_producto_nomina) ) " + "WHERE d.id_producto_nomina = (:id_producto_nomina) ")
                .setParameter("id_producto_nomina", id_producto_nomina);
        query.executeUpdate();

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarNoTrailer(int id_producto_nomina) {

        Session session = this.entityManager.unwrap(Session.class);

        Query query = session
                .createSQLQuery("UPDATE estructuras_contratos_dat AS d " + "SET d.no_trail = (SELECT COUNT(t.rfc) FROM estructuras_contratos_tra AS t "
                        + "WHERE t.rfc=d.rfc AND t.id_producto_nomina = (:id_producto_nomina) ) " + "WHERE d.id_producto_nomina = (:id_producto_nomina) ")
                .setParameter("id_producto_nomina", id_producto_nomina);
        query.executeUpdate();

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SIIFEncabezadoDTO actualizaTra(EstructuraContratosTrailersDTO dto) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("UPDATE estructuras_contratos_tra AS t	" + "INNER JOIN estructuras_conytratos_dat as d " + "SET t.num_emp = (:nombre), "
                        + "t.num_ctrol = (:num) " + "WHERE t.rfc = (:rfc)")
                .setParameter("rfc", dto.getRfc()).setParameter("num_emp", dto.getNumEmp()).setParameter("num", dto.getNumControl());
        query.executeUpdate();
        return null;
    }

    public void actualizarListaEstructuraDat(List<EstructuraContratosDatDTO> listaEstructura) {
        try {

            if (listaEstructura.isEmpty()) {
                throw new ReglaNegocioException("No existen registros de datos");
            } else {
                int num = 0;
                LOGGER.debugv("Lista tamaño: ", listaEstructura.size());
                for (EstructuraContratosDatDTO dat : listaEstructura) {

                    EstructuraContratoEntity estructuraEntity = this.estructuraDatDAO.obtenerPorId(dat.getIdEstructurasContratos());
                    estructuraEntity.setNumEmp(formatoNumEmp(num));
                    estructuraEntity.setNombre(formatoNombre(dat.getNombre()));
                    estructuraEntity.setNumCtrol(num + 1);
                    this.estructuraDatDAO.actualizar(estructuraEntity);

                    num = num + 1;
                }
            }
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public String registroEstructuraExcel(EstructuraContratoEntity estructura) {
        //estructuraDatDAO.persist(estructura);
        return estructura.getNumEmp();
    }

    public String formatoNombre(String nombre) {
        String formato = "";
        StringTokenizer frase = new StringTokenizer(nombre);
        Stack<String> pila = new Stack<>();
        while (frase.hasMoreTokens()) {
            pila.push(frase.nextToken());
        }
        int cont = 1;
        while (!pila.empty()) {
            if (cont == 1) {
                formato = formato + pila.pop() + "/";
            } else if (cont == 2) {
                formato = pila.pop() + "," + formato;
            } else {
                formato = formato + pila.pop() + " ";
            }
            cont++;
        }
        return formato.substring(0, formato.length() - 1);
    }

    public int verificaProductoNomina(Integer idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT dat.id_estructuras_contratos as idEstructurasContratos FROM estructuras_contratos_dat"
                + " AS dat WHERE dat.id_producto_nomina=:idProductoNomina").setParameter("idProductoNomina", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(EstructuraContratosDatDTO.class));
        //EstructuraContratosDatDTO result;
        if (query.list().size() > 0) {
            //result = (EstructuraContratosDatDTO) query.list().get(0);           
            return 1;
        }

        return 0;
    }

    public int verificaLayoutNomina(Integer idProductoNomina) {
        Session session = this.entityManager.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session
                .createSQLQuery(
                        "SELECT l.id_siif_encabezado as idSIIFEncabezado FROM layout_encabezados " + "AS l WHERE l.id_producto_nomina=:idProductoNomina")
                .setParameter("idProductoNomina", idProductoNomina);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        SIIFEncabezadoDTO result;
        if (query.list().size() > 0) {
            result = (SIIFEncabezadoDTO) query.list().get(0);
            System.out.println("Lista de encabezados, tamaño: " + result);
            return 1;
        }
        System.out.println("Lista de encabezados vacia: ");
        return 0;
    }

    public String formatoNumEmp(int num) {
        String numCtrol = String.valueOf(num);
        String edo = "29", cero = "";
        for (int i = 0; i < 8 - numCtrol.length(); i++) {
            cero += "0";
        }
        return edo + cero + numCtrol;
    }

}
