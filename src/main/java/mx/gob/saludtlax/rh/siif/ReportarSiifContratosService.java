
package mx.gob.saludtlax.rh.siif;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoRepository;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraEntity;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraRepository;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;
import mx.gob.saludtlax.rh.siif.reportarcontratos.ReglaNegocioException;
import mx.gob.saludtlax.rh.siif.reportarcontratos.UploadExcelFileAnexo;
import mx.gob.saludtlax.rh.util.Configuracion;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

@Asynchronous
@LocalBean
public class ReportarSiifContratosService {

    /*** < < < < < Inserciones > > > > > ***/

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    @Inject
    private SiifBitacoraRepository reporteSiifDAO;
    @Inject
    private SIIFEncabezadoRepository encabezadoRepository;

    private UploadExcelFileAnexo uploadedFile;

    private Integer i;

    public Integer getI() {
        return this.i;
    }

    public void setI(Integer i) {
        this.i = i;
    }//variable de conteo

    /*** < < < < < Metodos > > > > > ***/

    public List<SiifBitacoraDTO> obtenerReporteSiifPorPeriodo(String periodo, Integer anio) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("SELECT id_siif_bitacoras AS idSiifBitacora, " + " id_nombramiento AS idNombramiento, "
                        + " nombramiento_descripcion AS nombramiento, " + " total_nomina AS totalNomina, " + " status, " + " anio, "
                        + " fecha_importado AS fechaImportado, " + " fecha_envio AS fechaEnvio, " + " periodo AS periodo, "
                        + " total_percepciones AS totalPercepciones, " + " total_deducciones AS totalDeducciones, " + " total_neto AS totalNeto, "
                        + " id_tipo_nomina AS idTipoNomina, " + " id_cuenta_bancaria AS idCuentaBancaria, " + " cuenta_bancaria AS cuentaBancaria, "
                        + " tipo_nomina AS tipoNomina," + " periodo_reporte AS periodoAfectacion," + " anio_reporte AS anioAfectacion" + " FROM "
                        + "		vw_siif_bitacoras " + " WHERE " + "	periodo_reporte = :periodo AND " + " anio_reporte = :anio ")
                .setParameter("periodo", periodo).setParameter("anio", anio);
        query.setResultTransformer(Transformers.aliasToBean(SiifBitacoraDTO.class));
        @SuppressWarnings("unchecked")
        List<SiifBitacoraDTO> reporteSiifList = query.list();
        return reporteSiifList;
    }

    public List<CuentaBancariaDTO> obtenerCuentaBancariaList() {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT id_cuenta_bancaria AS idCuentaBancaria, " + "numero_cuenta AS numeroCuenta, " + "descripcion, "
                + "clave_cuenta AS claveCuenta " + "FROM cuentas_bancarias");
        query.setResultTransformer(Transformers.aliasToBean(CuentaBancariaDTO.class));
        @SuppressWarnings("unchecked")
        List<CuentaBancariaDTO> result = query.list();
        return result;
    }

    public List<TipoNominaDTO> obtenerTipoNominaList() {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "SELECT id_tipo_nomina AS idTipoNomina, " + "id_clasificacion_nomina AS idClasificacionNomina, " + "descripcion " + "FROM tipos_nominas");
        query.setResultTransformer(Transformers.aliasToBean(TipoNominaDTO.class));
        @SuppressWarnings("unchecked")
        List<TipoNominaDTO> result = query.list();
        return result;
    }

    public SiifBitacoraDTO obtenerSiiifBitacoraById(Integer idSiifBitacora) {
        System.out.println("id_siif_bitacoras:: " + idSiifBitacora);
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery("SELECT id_siif_bitacoras AS idSiifBitacora, " + "id_nombramiento AS idNombramiento, "
                        + "nombramiento_descripcion AS nombramiento, " + "total_nomina AS totalNomina, " + "status, " + "anio, "
                        + "fecha_importado AS fechaImportado, " + "fecha_envio AS fechaEnvio, " + "periodo AS periodo, "
                        + "total_percepciones AS totalPercepciones, " + "total_deducciones AS totalDeducciones, " + "total_neto AS totalNeto, "
                        + "id_tipo_nomina AS idTipoNomina, " + "id_cuenta_bancaria AS idCuentaBancaria, " + "cuenta_bancaria AS cuentaBancaria, "
                        + "tipo_nomina AS tipoNomina " + "FROM vw_siif_bitacoras " + "WHERE id_siif_bitacoras = :id_siif_bitacoras")
                .setParameter("id_siif_bitacoras", idSiifBitacora);
        query.setResultTransformer(Transformers.aliasToBean(SiifBitacoraDTO.class));
        SiifBitacoraDTO result = (SiifBitacoraDTO) query.list().get(0);

        query = session.createSQLQuery("CALL usp_encabezados_inconsistencia_prospera(:idSiifBitacora)").setParameter("idSiifBitacora", idSiifBitacora);
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> siifEncabezadoList = query.list();

        System.out.println("siifEncabezadoList:: " + siifEncabezadoList);
        System.out.println("siifEncabezadoList:: " + siifEncabezadoList.size());

        result.setSiifEncabezadoList(siifEncabezadoList);

        return result;
    }

    /***
     * < < < < < Llenado de Bitacoras e Insercion de Datos > > > > >
     ***/

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SiifBitacoraDTO cambiarClaveConceptosTraContratos(SiifBitacoraDTO bitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_cambiar_clave_concepto_prospera(:idSiifBitacora)").setParameter("idSiifBitacora",
                bitacora.getIdSiifBitacora());
        query.executeUpdate();
        return bitacora;
    }

    public SiifBitacoraDTO verificarDatosContratos(SiifBitacoraDTO bitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_calcular_totales_encabezados_prospera(:id_siif_bitacoras) ").setParameter("id_siif_bitacoras",
                bitacora.getIdSiifBitacora());
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> result = query.list();

        return bitacora;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SiifBitacoraDTO calcularTotalesEncabezadosContratos(SiifBitacoraDTO bitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_calcular_totales_encabezados_prospera(:id_siif_bitacoras) ").setParameter("id_siif_bitacoras",
                bitacora.getIdSiifBitacora());
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> result = query.list();
        for (SIIFEncabezadoDTO dto : result) {
            BigDecimal totalDeducciones = bitacora.getTotalDeducciones() == null ? BigDecimal.ZERO : bitacora.getTotalDeducciones();
            BigDecimal totalPercepciones = bitacora.getTotalPercepciones() == null ? BigDecimal.ZERO : bitacora.getTotalPercepciones();
            BigDecimal neto = bitacora.getTotalNeto() == null ? BigDecimal.ZERO : bitacora.getTotalNeto();
            bitacora.setTotalDeducciones(totalDeducciones.add(dto.getDeducciones()));
            bitacora.setTotalPercepciones(totalPercepciones.add(dto.getPercepciones()));
            bitacora.setTotalNeto(neto.add(dto.getNeto()));
        }

        bitacora.setStatus("Importado");
        actualizarSiifBitacora(bitacora);
        return bitacora;
    }

    public void actualizarSiifBitacora(SiifBitacoraDTO reporte) {

        System.out.println("actualizarSiifBitacora:: reporte.getIdSiifBitacora():: " + reporte.getIdSiifBitacora());
        System.out.println(":::status::" + reporte.getStatus());
        SiifBitacoraEntity entity = this.reporteSiifDAO.obtenerPorId(reporte.getIdSiifBitacora());
        entity.setAnio(reporte.getAnio());
        entity.setFechaEnvio(reporte.getFechaEnvio());
        entity.setFechaImportado(reporte.getFechaImportado());
        entity.setIdNombramiento(reporte.getIdNombramiento());
        entity.setNombramiento(reporte.getNombramiento());
        entity.setPeriodo(reporte.getPeriodo());
        entity.setStatus(reporte.getStatus());
        entity.setTotalNomina(reporte.getTotalNomina());
        entity.setTotalDeducciones(reporte.getTotalDeducciones());
        entity.setTotalNomina(reporte.getTotalNomina());
        entity.setTotalNeto(reporte.getTotalNeto());
        entity.setTotalPercepciones(reporte.getTotalPercepciones());
        this.reporteSiifDAO.actualizar(entity);
    }

    public SiifBitacoraDTO crearSiifBitacoraContratos(PaqueteEntradaContratoDTO paqueteEntrada) {
        SiifBitacoraEntity entity = new SiifBitacoraEntity();
        entity.setFechaImportado(FechaUtil.fechaActual());
        entity.setIdCuentaBancaria(paqueteEntrada.getIdCuentaBancaria());
        entity.setIdTipoNomina(paqueteEntrada.getIdTipoNomina());
        entity.setAnioAfectacion(paqueteEntrada.getAnioAfectacion());
        entity.setPeriodoAfectacion(paqueteEntrada.getPeriodoAfectacion());
        this.reporteSiifDAO.crear(entity);
        return obtenerSiiifBitacoraById(entity.getIdReporteSiif());
    }

    public SiifBitacoraDTO importarDatosToSIIF(PaqueteEntradaContratoDTO paqueteEntrada, SiifBitacoraDTO bitacora) {
        this.i = 0;
        this.uploadedFile = new UploadExcelFileAnexo();
        this.uploadedFile.validate(paqueteEntrada.getDat());
        bitacora = settingDataDAT(this.uploadedFile.getAnexoDTOs(), bitacora);
        bitacora.setTotalNomina(this.i);
        this.uploadedFile.validate(paqueteEntrada.getTra());
        bitacora = settingDataTRA(this.uploadedFile.getAnexoDTOs(), bitacora);

        bitacora.setAnio(Integer.valueOf(paqueteEntrada.getPeriodoAfectacion()));
        bitacora.setPeriodo(paqueteEntrada.getPeriodoAfectacion());
        return bitacora;
    }

    //	<<<<<Ingresar TRA>>>>>

    private SiifBitacoraDTO settingDataTRA(List<EstructuraDTO> data, SiifBitacoraDTO bitacora) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<EstructuraContratosTrailersDTO> listaEstructura = new ArrayList<>();
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
                estructuraDTO.setIdSiifBitacoras(bitacora.getIdSiifBitacora());
                estructuraDTO.setConcep(estructuraDTO.getConcep() + estructuraDTO.getPtaAnt());

                listaEstructura.add(estructuraDTO);

            }

            registrarListaEstructura(listaEstructura);
            JSFUtils.infoMessage("Registro Estructura", "Proceso realizado correctamente");
        } catch (EstructuraException e) {
            e.printStackTrace();
            JSFUtils.errorMessage("Error", e.getMessage());
        }
        return bitacora;
    }

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

                registroEstructura(estructuraEntity);
            }
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public String registroEstructura(EstructuraContratoTrailersEntity estructura) {
        this.entityManager.persist(estructura);
        return estructura.getNumEmp();
    }

    //	<<<<<Ingresar DAT>>>>>

    private SiifBitacoraDTO settingDataDAT(List<EstructuraDTO> data, SiifBitacoraDTO bitacora) {

        Iterator<EstructuraDTO> arrayIterator = data.iterator();
        List<EstructuraContratosDatDTO> listaEstructura = new ArrayList<>();

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
                estructuraDTO.setIdSubfuenteFinanciamiento(genericoDTO.getDato(77, Integer.class));

                estructuraDTO.setIdSiifBitacoras(bitacora.getIdSiifBitacora());

                listaEstructura.add(estructuraDTO);
                this.i++;
            }

            registrarListaEstructuraDat(listaEstructura);
            JSFUtils.infoMessage("Registro Nomina Trailers", "Proceso realizado correctamente");
        } catch (EstructuraException e) {
            e.printStackTrace();
            JSFUtils.errorMessage("Error", e.getMessage());
        }
        return bitacora;
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
                estructuraEntity.setIdSubfuenteFinanciamiento(trailers.getIdSubfuenteFinanciamiento());

                estructuraEntity.setIdSiifBitacoras(trailers.getIdSiifBitacoras());

                registroEstructuraExcel(estructuraEntity);
            }
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public String registroEstructuraExcel(EstructuraContratoEntity estructura) {
        this.entityManager.persist(estructura);
        return estructura.getNumEmp();
    }

    //		< < < < < Clasificar > > > > >

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SiifBitacoraDTO clasificarEncabezadosContratos(SiifBitacoraDTO bitacora) {
        System.out.println("ID::" + bitacora.getIdSiifBitacora());
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_clasificar_prospera_estructura(:idSiifBitacora)").setParameter("idSiifBitacora",
                bitacora.getIdSiifBitacora());
        query.executeUpdate();
        return bitacora;
    }

    public void actualizarSiifEncabezado(SIIFEncabezadoDTO siifEncabezado) {
        SIIFEncabezadoEntity encabezadoEntity = null;
        //		System.out.println("siifEncabezado:: " + siifEncabezado);
        //		System.out.println("siifEncabezado.getIdSIIFEncabezado():: " + siifEncabezado.getIdSIIFEncabezado());
        if (siifEncabezado.getIdSIIFEncabezado() != null) {
            encabezadoEntity = this.entityManager.find(SIIFEncabezadoEntity.class, siifEncabezado.getIdSIIFEncabezado());
        }
        //		System.out.println("1 encabezadoEntity:: " + encabezadoEntity);
        if (encabezadoEntity == null) {
            encabezadoEntity = new SIIFEncabezadoEntity();
        }

        int idBitacora = siifEncabezado.getIdSIIFBitacora();

        if (idBitacora < 1) {
            throw new ValidationException("El ID no puede ser negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
        }

        SiifBitacoraEntity bitacora = this.entityManager.find(SiifBitacoraEntity.class, idBitacora);
        TipoNominaEntity tipoNomina = this.entityManager.find(TipoNominaEntity.class, siifEncabezado.getIdTipoNomina());
        CuentasBancariasEntity cuentaBancaria = this.entityManager.find(CuentasBancariasEntity.class, siifEncabezado.getIdCuentaBancaria());

        //		System.out.println("2 encabezadoEntity:: " + encabezadoEntity);

        encabezadoEntity.setIdNomina(siifEncabezado.getIdNomina());
        encabezadoEntity.setIdPoder(siifEncabezado.getIdPoder() == null ? 'E' : siifEncabezado.getIdPoder());
        encabezadoEntity.setIdNombramiento(siifEncabezado.getIdNombramiento());
        encabezadoEntity.setTipoNomina(tipoNomina);
        encabezadoEntity.setFechaFinQuincena(siifEncabezado.getFechaFinQuincena());
        encabezadoEntity.setCuentaBancaria(cuentaBancaria);
        encabezadoEntity.setIdTipoEmisionNomina(siifEncabezado.getIdTipoEmisionNomina());
        encabezadoEntity.setPercepciones(siifEncabezado.getPercepciones() == null ? BigDecimal.ZERO : siifEncabezado.getPercepciones());
        encabezadoEntity.setDeducciones(siifEncabezado.getDeducciones() == null ? BigDecimal.ZERO : siifEncabezado.getDeducciones());
        encabezadoEntity.setNeto(siifEncabezado.getNeto() == null ? BigDecimal.ZERO : siifEncabezado.getNeto());
        encabezadoEntity.setIdEstadoNomina(siifEncabezado.getIdEstadoNomina() == null ? 'A' : siifEncabezado.getIdEstadoNomina());
        encabezadoEntity.setBitacora(bitacora);

        this.encabezadoRepository.actualizar(encabezadoEntity);

        //Limpiar encabezados//
        limpiarSiifEncabezados(idBitacora);
    }

    public void limpiarSiifEncabezados(Integer idSiifBitacora) {
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL sp_limpiar_siif_encabezados(:idSiifBitacora)").setParameter("idSiifBitacora", idSiifBitacora);
        query.executeUpdate();

    }

    public SiifBitacoraDTO actualizarEncabezadosContratos(SiifBitacoraDTO bitacora) {
        System.out.println("ID::" + bitacora.getIdSiifBitacora());
        Session session = this.entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(" SELECT " + " e.id_siif_encabezado AS idSIIFEncabezado, " + " e.id_nomina AS idNomina, "
                + " e.id_poder AS idPoder, " + " e.id_tipo_nomina AS idTipoNomina, " + " e.fecha_fin_quincena AS fechaFinQuincena, "
                + " e.id_tipo_emision_nomina AS idTipoEmisionNomina, " + " e.id_cuenta_bancaria AS idCuentaBancaria, " + " e.percepciones AS percepciones, "
                + " e.deducciones AS deducciones, " + " e.neto AS neto, " + " e.id_estado_nomina AS idEstadoNomina, "
                + " e.id_siif_bitacora AS idSIIFBitacora, " + " e.id_nombramiento AS idNombramiento, " + " e.sub_programa AS subPrograma "
                + " FROM siif_encabezados AS e " + " WHERE e.id_siif_bitacora = :idSiifBitacora ").setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
        query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
        @SuppressWarnings("unchecked")
        List<SIIFEncabezadoDTO> result = query.list();
        System.out.println("sp_clasificar_prospera_estructura result:: " + result);
        for (SIIFEncabezadoDTO dto : result) {
            dto.setIdCuentaBancaria(bitacora.getIdCuentaBancaria());
            dto.setIdTipoNomina(bitacora.getIdTipoNomina());
            dto.setIdSIIFBitacora(bitacora.getIdSiifBitacora());
            actualizarSiifEncabezado(dto);
        }
        return bitacora;
    }

}