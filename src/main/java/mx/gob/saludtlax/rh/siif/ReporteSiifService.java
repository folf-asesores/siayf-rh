package mx.gob.saludtlax.rh.siif;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidationException;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.DatosPersonalesEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoExcelEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraContratoPlantillaExcelEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaDatEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersRepository;
import mx.gob.saludtlax.rh.persistencia.EstructuraNominaTrailersEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoEntity;
import mx.gob.saludtlax.rh.persistencia.SIIFEncabezadoRepository;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraRepository;
import mx.gob.saludtlax.rh.persistencia.SiifBitacoraEntity;
import mx.gob.saludtlax.rh.persistencia.TipoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoDTO;
import mx.gob.saludtlax.rh.siif.layout.SIIFEncabezadoService;
import mx.gob.saludtlax.rh.siif.layout.SIIFLayoutEJB;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosExcelDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosPlantillaExcelDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraException;
import mx.gob.saludtlax.rh.siif.reportarcontratos.ReglaNegocioException;
import mx.gob.saludtlax.rh.siif.reportarcontratos.SiifDeudoresDiversosDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.UploadExcelFileAnexo;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.JSFUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.primefaces.model.UploadedFile;
import org.jboss.logging.Logger;


@Asynchronous
@LocalBean
public class ReporteSiifService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;
	@Inject
	private SiifBitacoraRepository reporteSiifDAO;
	@Inject
	private TiposNombramientosRepository nombramientoRepository;
	@Inject
	private SIIFEncabezadoRepository encabezadoRepository;
	@Inject
	private EstructuraNominaTrailersRepository nominaTrailersDAO;

	private UploadExcelFileAnexo uploadedFile;
	
	private static final Logger LOGGER = Logger.getLogger(ReporteSiifService.class.getName());

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<SiifBitacoraDTO> obtenerReporteSiifPorPeriodo(String periodo, Integer anio) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_siif_bitacoras AS idSiifBitacora, "
				+ " id_nombramiento AS idNombramiento, " + " nombramiento_descripcion AS nombramiento, "
				+ " total_nomina AS totalNomina, " + " status, " + " anio, " + " fecha_importado AS fechaImportado, "
				+ " fecha_envio AS fechaEnvio, " + " periodo AS periodo, "
				+ " total_percepciones AS totalPercepciones, " + " total_deducciones AS totalDeducciones, "
				+ " total_neto AS totalNeto, " + " id_tipo_nomina AS idTipoNomina, "
				+ " id_cuenta_bancaria AS idCuentaBancaria, " + " cuenta_bancaria AS cuentaBancaria, "
				+ " tipo_nomina AS tipoNomina," + " periodo_reporte AS periodoAfectacion,"
				+ " anio_reporte AS anioAfectacion" + " FROM " + "		vw_siif_bitacoras " + " WHERE "
				+ "	periodo_reporte = :periodo AND " + " anio_reporte = :anio ").setParameter("periodo", periodo)
				.setParameter("anio", anio);
		query.setResultTransformer(Transformers.aliasToBean(SiifBitacoraDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifBitacoraDTO> reporteSiifList = (List<SiifBitacoraDTO>) query.list();
		return reporteSiifList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SIIFEncabezadoDTO obtenerEncabezadoPorId(int idSiifEncabezado) {
		System.out.println(":: Busca Encabezado Por Id:" + idSiifEncabezado);
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT	" + "en.id_siif_encabezado as idSIIFEncabezado,"
				+ "en.id_nomina as idNomina," + " en.id_poder as idPoder," + " en.id_tipo_nomina as idTipoNomina,"
				+ " en.fecha_fin_quincena as fechaFinQuincena," + " en.id_tipo_emision_nomina as idTipoEmisionNomina,"
				+ " en.id_cuenta_bancaria as idCuentaBancaria," + " en.percepciones AS percepciones,"
				+ " en.deducciones AS deducciones," + " en.neto AS neto," + " en.id_estado_nomina as idEstadoNomina"
				+ " FROM siif_encabezados AS en" + " WHERE en.id_siif_encabezado = (:id_siif_encabezado)")
				.setParameter("id_siif_encabezado", (Integer) idSiifEncabezado);
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		SIIFEncabezadoDTO result = (SIIFEncabezadoDTO) query.list().get(0);
		System.out.println(":: Encuentra Encabezado Por Id:::");
		return result;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SIIFEncabezadoDTO actualizarEncabezado(SIIFEncabezadoDTO encabezadoDTO) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("UPDATE " + " siif_encabezados AS se " + " SET "
						+ "se.id_nomina = (:id_nomina) , "
						+ " se.id_tipo_nomina = (:id_tipo_nomina) ," 
						+ " se.id_tipo_emision_nomina = (:id_tipo_emision_nomina) ,"
						+ " se.id_cuenta_bancaria = (:id_cuenta_bancaria)"
						+ " WHERE " + " se.id_siif_encabezado = (:id_siif_encabezado)")
				.setParameter("id_nomina", encabezadoDTO.getIdNomina())
				.setParameter("id_tipo_nomina", encabezadoDTO.getIdTipoNomina())
				.setParameter("id_tipo_emision_nomina", encabezadoDTO.getIdTipoEmisionNomina())
				.setParameter("id_cuenta_bancaria", encabezadoDTO.getIdCuentaBancaria())
				.setParameter("id_siif_encabezado", encabezadoDTO.getIdSIIFEncabezado());
		query.executeUpdate();

		System.out.println(":: Actualiza Encabezado:" + encabezadoDTO.getIdSIIFEncabezado());
		return obtenerEncabezadoPorId(encabezadoDTO.getIdSIIFEncabezado());

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void estatusEncabezado(SIIFEncabezadoDTO encabezadoDTO, String periodo) {
		System.out.println(":: Estatus Encabezado:" + encabezadoDTO.getIdSIIFEncabezado());
		System.out.println(":: Estatus Encabezado-IdBitacora:" + encabezadoDTO.getIdSIIFBitacora());
		System.out.println(":: Estatus Encabezado-Periodo:" + periodo);

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SIIFEncabezadoDTO actualizarCheques(SIIFEncabezadoDTO encabezadoDTO, Integer qna) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_cheques_siif(:idSiifEncabezdo,:qna)")
				.setParameter("idSiifEncabezdo", encabezadoDTO.getIdSIIFEncabezado()).setParameter("qna", qna);
		query.executeUpdate();
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SIIFEncabezadoDTO actualizarCheques(Integer id, Integer qna) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_actualizar_cheques_siif(:idSiifEncabezdo,:qna)")
				.setParameter("idSiifEncabezdo",id).setParameter("qna", qna);
		query.executeUpdate();
		return null;
	}

	// public SIIFEncabezadoDTO actualizarEncabezado(int idSiifEncabezado){
	// SIIFEncabezadoEntity entity =
	// siifEncabezadiDAO.obtenerPorId(dto.getIdDatoPersonal());
	// entity.setRfc(dto.getRfc());
	// entity.setIdEmpleadoDatosPersonales(dto.getIdEmpleadoDatosPersonales());
	// entity.setApellidoPaterno(dto.getApellidoPaterno());
	// entity.setApellidoMaterno(dto.getApellidoMaterno());
	// entity.setNombre(dto.getNombre());
	// entity.setFechaNacimiento(dto.getFechaNacimiento());
	// entity.setSexo(dto.getSexo());
	// entity.setIdLocalidad(dto.getIdLocalidad());
	// entity.setIdColonia(dto.getIdColonia());
	// entity.setCalle(dto.getCalle());
	// entity.setNumeroExterior(dto.getNumeroExterior());
	// entity.setNumeroInterior(dto.getNumeroInterior());
	// entity.setCodigoPostal(dto.getCodigoPostal());
	// entity.setTelefono(dto.getTelefono());
	// entity.setIdEstadoEmpleado(dto.getIdEstadoEmpleado());
	// datosPersonalesDAO.actualizar(entity);
	// return
	// datosPersonalesRepository.obtenerDatosPersonalesPorId(entity.getIdDatoPersonal());
	//
	// }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<SiifBitacoraDTO> obtenerReporteSiifNuevo(String periodo, Integer anio) {
		List<TiposNombramientosEntity> nombramientoList = nombramientoRepository.nombramientos();
		List<SiifBitacoraDTO> reporteSiifList = new ArrayList<SiifBitacoraDTO>();
		for (TiposNombramientosEntity nombramiento : nombramientoList) {
			SiifBitacoraDTO reporteSiifDTO = new SiifBitacoraDTO();
			reporteSiifDTO.setIdSiifBitacora(nombramiento.getIdTipoNombramiento());
			reporteSiifDTO.setNombramiento(nombramiento.getDescripcion());
			reporteSiifDTO.setAnio(anio);
			// reporteSiifDTO.setFechaEnvio();
			// reporteSiifDTO.setFechaImportado();
			reporteSiifDTO.setIdNombramiento(nombramiento.getIdTipoNombramiento());
			reporteSiifDTO.setPeriodo(periodo);
			reporteSiifDTO.setStatus("Pendiente");
			reporteSiifDTO.setTotalNomina(0);
			reporteSiifList.add(reporteSiifDTO);
		}
		return reporteSiifList;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO crearSiifBitacora(PaqueteEntradaFederalDTO paqueteEntrada) {
		SiifBitacoraEntity entity = new SiifBitacoraEntity();
		System.out.println("Inicia creacion SiifBitacora:::: Fecha actual::: " + FechaUtil.fechaActual());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateInString = dateFormat.format(date);
		System.out.println("Fecha Actualizada::: " + dateFormat.format(date));

		try {
			Date datenow = dateFormat.parse(dateInString);

			// entity.setFechaImportado(FechaUtil.fechaActual());
			entity.setFechaImportado(datenow);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		entity.setIdCuentaBancaria(paqueteEntrada.getIdCuentaBancaria());
		entity.setIdTipoNomina(paqueteEntrada.getIdTipoNomina());
		entity.setAnioAfectacion(paqueteEntrada.getAnioAfectacion());
		entity.setPeriodoAfectacion(paqueteEntrada.getPeriodoAfectacion());
		entity.setFechaEnvio(paqueteEntrada.getFechaEnvio());
		reporteSiifDAO.crear(entity);
		System.out.println("Termina creacion SiifBitacora:::: " + entity.getIdReporteSiif());
		return obtenerSiiifBitacoraById(entity.getIdReporteSiif());
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarSiifBitacora(SiifBitacoraDTO reporte) {

		System.out.println("actualizarSiifBitacora:: reporte.getIdSiifBitacora():: " + reporte.getIdSiifBitacora());

		SiifBitacoraEntity entity = reporteSiifDAO.obtenerPorId(reporte.getIdSiifBitacora());
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
		reporteSiifDAO.actualizar(entity);
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CuentaBancariaDTO> obtenerCuentaBancariaList() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_cuenta_bancaria AS idCuentaBancaria, " + "numero_cuenta AS numeroCuenta, "
						+ "descripcion, " + "clave_cuenta AS claveCuenta " + "FROM cuentas_bancarias");
		query.setResultTransformer(Transformers.aliasToBean(CuentaBancariaDTO.class));
		@SuppressWarnings("unchecked")
		List<CuentaBancariaDTO> result = (List<CuentaBancariaDTO>) query.list();
		return result;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<TipoNominaDTO> obtenerTipoNominaList() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("SELECT id_tipo_nomina AS idTipoNomina, "
				+ "id_clasificacion_nomina AS idClasificacionNomina, " + "descripcion " + "FROM tipos_nominas");
		query.setResultTransformer(Transformers.aliasToBean(TipoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoNominaDTO> result = (List<TipoNominaDTO>) query.list();
		return result;
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String obtenerTipoEmisionNomina(EstructuraNominaDatEntity estructuraNominaEntity) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT num_cheq " + "FROM estructuras_nominas_trailers AS t "
						+ "WHERE t.rfc = :rfc AND " + "id_siif_bitacoras = :idSiifBitacora ")
				.setParameter("rfc", estructuraNominaEntity.getRfc())
				.setParameter("idSiifBitacora", estructuraNominaEntity.getIdSiifBitacora());
		// .setParameter("anio", estructuraNominaEntity.getAnioReal());
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>) query.list();
		// 00 Cheque
		// 90 Cheque
		// 80 Cheque
		// 29 Tarjeta o deposito

		return ((!result.isEmpty()) && result.get(0).startsWith("29")) ? "T" : "C";
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EstructuraNominaDatEntity obtenerEstructuraNominaDatEntity(String rfc, Integer idReporteSiif) {
		EstructuraNominaDatEntity estructuraNominaDatEntity = (EstructuraNominaDatEntity) entityManager
				.createQuery(
						"SELECT n FROM EstructuraNominaDatEntity c WHERE c.rfc = :rfc AND  idReporteSiif = :idReporteSiif")
				.setParameter("rfc", rfc).setParameter("idReporteSiif", idReporteSiif).getSingleResult();
		return estructuraNominaDatEntity;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void actualizarSiifEncabezado(SIIFEncabezadoDTO siifEncabezado) {
		SIIFEncabezadoEntity encabezadoEntity = null;
		// System.out.println("siifEncabezado:: " + siifEncabezado);
		// System.out.println("siifEncabezado.getIdSIIFEncabezado():: " +
		// siifEncabezado.getIdSIIFEncabezado());
		if (siifEncabezado.getIdSIIFEncabezado() != null) {
			encabezadoEntity = entityManager.find(SIIFEncabezadoEntity.class, siifEncabezado.getIdSIIFEncabezado());
		}
		// System.out.println("1 encabezadoEntity:: " + encabezadoEntity);
		if (encabezadoEntity == null) {
			encabezadoEntity = new SIIFEncabezadoEntity();
		}

		int idBitacora = siifEncabezado.getIdSIIFBitacora();

		if (idBitacora < 1) {
			throw new ValidationException("El ID no puede ser negativo.", ValidacionCodigoError.NUMERO_NEGATIVO);
		}

		SiifBitacoraEntity bitacora = entityManager.find(SiifBitacoraEntity.class, idBitacora);
		TipoNominaEntity tipoNomina = entityManager.find(TipoNominaEntity.class, siifEncabezado.getIdTipoNomina());
		CuentasBancariasEntity cuentaBancaria = entityManager.find(CuentasBancariasEntity.class,
				siifEncabezado.getIdCuentaBancaria());

		// System.out.println("2 encabezadoEntity:: " + encabezadoEntity);

		encabezadoEntity.setIdNomina(siifEncabezado.getIdNomina());
		encabezadoEntity.setIdPoder(siifEncabezado.getIdPoder() == null ? 'E' : siifEncabezado.getIdPoder());
		encabezadoEntity.setIdNombramiento(siifEncabezado.getIdNombramiento());
		encabezadoEntity.setTipoNomina(tipoNomina);
		encabezadoEntity.setFechaFinQuincena(bitacora.getFechaEnvio());
		encabezadoEntity.setCuentaBancaria(cuentaBancaria);
		encabezadoEntity.setIdTipoEmisionNomina(siifEncabezado.getIdTipoEmisionNomina());
		encabezadoEntity.setPercepciones(
				siifEncabezado.getPercepciones() == null ? BigDecimal.ZERO : siifEncabezado.getPercepciones());
		encabezadoEntity.setDeducciones(
				siifEncabezado.getDeducciones() == null ? BigDecimal.ZERO : siifEncabezado.getDeducciones());
		encabezadoEntity.setNeto(siifEncabezado.getNeto() == null ? BigDecimal.ZERO : siifEncabezado.getNeto());
		encabezadoEntity.setIdEstadoNomina(
				siifEncabezado.getIdEstadoNomina() == null ? 'A' : siifEncabezado.getIdEstadoNomina());
		encabezadoEntity.setBitacora(bitacora);

		encabezadoRepository.actualizar(encabezadoEntity);

		// Limpiar encabezados//
		limpiarSiifEncabezados(idBitacora);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void limpiarSiifEncabezados(Integer idSiifBitacora) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_limpiar_siif_encabezados(:idSiifBitacora)")
				.setParameter("idSiifBitacora", idSiifBitacora);
		query.executeUpdate();

	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO obtenerSiiifBitacoraById(Integer idSiifBitacora) {
		System.out.println("id_siif_bitacoras::: " + idSiifBitacora);
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT id_siif_bitacoras AS idSiifBitacora, " + "id_nombramiento AS idNombramiento, "
						+ "nombramiento_descripcion AS nombramiento, " + "total_nomina AS totalNomina, " + "status, "
						+ "anio, " + "fecha_importado AS fechaImportado, " + "fecha_envio AS fechaEnvio, "
						+ "periodo AS periodo, " + "total_percepciones AS totalPercepciones, "
						+ "total_deducciones AS totalDeducciones, " + "total_neto AS totalNeto, "
						+ "id_tipo_nomina AS idTipoNomina, " + "id_cuenta_bancaria AS idCuentaBancaria, "
						+ "cuenta_bancaria AS cuentaBancaria, " + "tipo_nomina AS tipoNomina "
						+ "FROM vw_siif_bitacoras " + "WHERE id_siif_bitacoras = :id_siif_bitacoras")
				.setParameter("id_siif_bitacoras", idSiifBitacora);
		query.setResultTransformer(Transformers.aliasToBean(SiifBitacoraDTO.class));
		
		SiifBitacoraDTO result=null;
		
		if(query.list().size()>0){
			
			result= (SiifBitacoraDTO) query.list().get(0);
			if (result.getIdCuentaBancaria() != 7 || result.getIdCuentaBancaria() != 4) {
				query = session.createSQLQuery("CALL usp_encabezados_inconsistencia(:idSiifBitacora)")
						.setParameter("idSiifBitacora", idSiifBitacora);
				query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
			} else {

				query = session.createSQLQuery("CALL usp_encabezados_inconsistencia_prospera(:idSiifBitacora)")
						.setParameter("idSiifBitacora", idSiifBitacora);
				query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
			}
			@SuppressWarnings("unchecked")
			List<SIIFEncabezadoDTO> siifEncabezadoList = (List<SIIFEncabezadoDTO>) query.list();
			
			result.setSiifEncabezadoList(siifEncabezadoList);
			return result;
		}else{
			return null;
			
		}
		
	}

	//@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO importarNominaTheosToSIIF(UploadedFile dat, UploadedFile tra, SiifBitacoraDTO bitacora) {
		int i = 0;
		System.out.println("importarNominaTheosToSIIF::: " + bitacora);
		try {
			String valoresTra = new String(tra.getContents(), "UTF-8");
			StringTokenizer stTra = new StringTokenizer(valoresTra, "\n");
			while (stTra.hasMoreTokens()) {
				String dato = stTra.nextToken();

				String[] array = procesarArchivoTra(dato);
				EstructuraNominaTrailersEntity estructuraNominaTraEntity = toEstructuraNominaTrailers(array);
				estructuraNominaTraEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
				estructuraNominaTraEntity
						.setConceptoSiif(estructuraNominaTraEntity.getConcep() + estructuraNominaTraEntity.getPtaAnt());
				entityManager.persist(estructuraNominaTraEntity);
			}
			System.out.println("procesarArchivoTra::: ");

			String valoresDat = new String(dat.getContents(), "UTF-8");
			StringTokenizer stDat = new StringTokenizer(valoresDat, "\n");
			EstructuraNominaDatEntity estructuraNominaEntity = null;

			while (stDat.hasMoreTokens()) {
				String dato = stDat.nextToken();
				String[] array = procesarArchivoDat(dato);
				estructuraNominaEntity = toEstructuraNominaEntity(array);
				estructuraNominaEntity.setIdSiifBitacora(bitacora.getIdSiifBitacora());
				i++;
				entityManager.persist(estructuraNominaEntity);
			}
			System.out.println("procesarArchivoDat::: ");
			if (estructuraNominaEntity != null) {
				bitacora.setAnio(Integer.valueOf(estructuraNominaEntity.getAnioReal()));
				bitacora.setPeriodo(estructuraNominaEntity.getQnaReal());
			} else {
				System.out.println("Estructura Nomina es NULL::");
			}
			bitacora.setTotalNomina(i);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO importarNominaContSIIF(UploadedFile cont, SiifBitacoraDTO bitacora,
			PaqueteEntradaFederalDTO paqueteEntrada) {
		int i = 0;
		uploadedFile = new UploadExcelFileAnexo();
		uploadedFile.validate(cont);
		bitacora = settingDataContPlantilla(uploadedFile.getAnexoDTOs(), bitacora);
		bitacora.setTotalNomina(i);
		bitacora.setAnio(Integer.valueOf(paqueteEntrada.getPeriodoAfectacion()));
		bitacora.setPeriodo(paqueteEntrada.getPeriodoAfectacion());
		return bitacora;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private SiifBitacoraDTO settingDataCONT(List<EstructuraDTO> data, SiifBitacoraDTO bitacora) {

		Iterator<EstructuraDTO> arrayIterator = data.iterator();
		List<EstructuraContratosExcelDTO> listaEstructura = new ArrayList<EstructuraContratosExcelDTO>();
		try {
			while (arrayIterator.hasNext()) {

				EstructuraDTO genericoDTO = arrayIterator.next();
				EstructuraContratosExcelDTO estructuraDTO = new EstructuraContratosExcelDTO();

				estructuraDTO.setNum(genericoDTO.getDato(0, Integer.class));
				estructuraDTO.setPrograma(genericoDTO.getDato(1, String.class));
				estructuraDTO.setRfc(genericoDTO.getDato(2, String.class));
				estructuraDTO.setRfcVal(genericoDTO.getDato(3, String.class));
				estructuraDTO.setNombre(genericoDTO.getDato(4, String.class));
				estructuraDTO.setfI(genericoDTO.getDato(5, String.class));
				estructuraDTO.setcResponsable(genericoDTO.getDato(6, String.class));
				estructuraDTO.setFuncion(genericoDTO.getDato(7, String.class));
				estructuraDTO.setRama(genericoDTO.getDato(8, String.class));
				estructuraDTO.setfFinan(genericoDTO.getDato(9, String.class));
				estructuraDTO.setProg(genericoDTO.getDato(10, String.class));

				estructuraDTO.setTotalBruto(genericoDTO.getDato(11, BigDecimal.class));
				estructuraDTO.setPercepciones(genericoDTO.getDato(12, BigDecimal.class));
				estructuraDTO.setDeducciones(genericoDTO.getDato(13, BigDecimal.class));
				estructuraDTO.setNeto(genericoDTO.getDato(14, BigDecimal.class));

				estructuraDTO.setSueldo(genericoDTO.getDato(15, BigDecimal.class));
				estructuraDTO.setSup(genericoDTO.getDato(16, BigDecimal.class));
				estructuraDTO.setComp(genericoDTO.getDato(17, BigDecimal.class));
				estructuraDTO.setAg(genericoDTO.getDato(18, BigDecimal.class));
				estructuraDTO.setSubsidio(genericoDTO.getDato(19, BigDecimal.class));
				estructuraDTO.setVac(genericoDTO.getDato(20, Integer.class));
				estructuraDTO.setrFaltas(genericoDTO.getDato(21, Integer.class));

				estructuraDTO.setRetroa(genericoDTO.getDato(22, BigDecimal.class));
				estructuraDTO.setOtros(genericoDTO.getDato(23, BigDecimal.class));
				estructuraDTO.setFaltas(genericoDTO.getDato(24, Integer.class));

				estructuraDTO.setIstp(genericoDTO.getDato(25, BigDecimal.class));
				estructuraDTO.setRespons(genericoDTO.getDato(26, Integer.class));
				estructuraDTO.setPrestamo(genericoDTO.getDato(27, Integer.class));
				estructuraDTO.setPa(genericoDTO.getDato(28, BigDecimal.class));
				estructuraDTO.setTotal(genericoDTO.getDato(29, BigDecimal.class));

				estructuraDTO.setVerifica(genericoDTO.getDato(30, String.class));
				estructuraDTO.settCentro(genericoDTO.getDato(31, String.class));
				estructuraDTO.setcClues(genericoDTO.getDato(32, String.class));
				estructuraDTO.setNomUnidad(genericoDTO.getDato(33, String.class));
				estructuraDTO.setaAdscripcion(genericoDTO.getDato(34, String.class));
				estructuraDTO.setPuesto(genericoDTO.getDato(35, String.class));
				estructuraDTO.setcPuesto(genericoDTO.getDato(36, String.class));
				estructuraDTO.setServicio(genericoDTO.getDato(37, String.class));
				estructuraDTO.setRamas(genericoDTO.getDato(38, String.class));
				estructuraDTO.setTurno(genericoDTO.getDato(39, String.class));

				estructuraDTO.setIdSiifBitacora(bitacora.getIdSiifBitacora());

				listaEstructura.add(estructuraDTO);
			}
			registrarListaEstructuraExcel(listaEstructura);
			JSFUtils.infoMessage("Registro Contratos", "Proceso realizado correctamente");

		} catch (EstructuraException e) {
			e.printStackTrace();
			JSFUtils.errorMessage("Error", e.getMessage());
		}
		return bitacora;
	}

	// importar platilla unica de contrato
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private SiifBitacoraDTO settingDataContPlantilla(List<EstructuraDTO> data, SiifBitacoraDTO bitacora) {

		Iterator<EstructuraDTO> arrayIterator = data.iterator();
		List<EstructuraContratosPlantillaExcelDTO> listaEstructura = new ArrayList<EstructuraContratosPlantillaExcelDTO>();
		try {
			while (arrayIterator.hasNext()) {

				EstructuraDTO genericoDTO = arrayIterator.next();
				EstructuraContratosPlantillaExcelDTO estructuraDTO = new EstructuraContratosPlantillaExcelDTO();

				estructuraDTO.setTipoNomina(genericoDTO.getDato(0, Integer.class));				
				estructuraDTO.setRfc(genericoDTO.getDato(1, String.class));
				estructuraDTO.setRfcVal(genericoDTO.getDato(2, String.class));
				estructuraDTO.setNombre(genericoDTO.getDato(3, String.class));
				estructuraDTO.setCheqDap(genericoDTO.getDato(4, String.class));
				estructuraDTO.setfI(genericoDTO.getDato(5, String.class));
				estructuraDTO.setcResponsable(genericoDTO.getDato(6, String.class));
				estructuraDTO.setFuncion(genericoDTO.getDato(7, String.class));
				estructuraDTO.setRama(genericoDTO.getDato(8, String.class));
				estructuraDTO.setPrograma(genericoDTO.getDato(9, String.class));				

				estructuraDTO.setSueldo(genericoDTO.getDato(10, BigDecimal.class));
				estructuraDTO.setSup(genericoDTO.getDato(11, BigDecimal.class));
				estructuraDTO.setHonorarios(genericoDTO.getDato(12, BigDecimal.class));
				estructuraDTO.setComp(genericoDTO.getDato(13, BigDecimal.class));				
				estructuraDTO.setAg(genericoDTO.getDato(14, BigDecimal.class));
				estructuraDTO.setSubsidio(genericoDTO.getDato(15, BigDecimal.class));
				estructuraDTO.setVac(genericoDTO.getDato(16, Integer.class));
				estructuraDTO.setrFaltas(genericoDTO.getDato(17, Integer.class));
				estructuraDTO.setRetroa(genericoDTO.getDato(18, BigDecimal.class));
				estructuraDTO.setOtros(genericoDTO.getDato(19, BigDecimal.class));
				estructuraDTO.setFaltas(genericoDTO.getDato(20, Integer.class));
				estructuraDTO.setIstp(genericoDTO.getDato(21, BigDecimal.class));
				estructuraDTO.setRespons(genericoDTO.getDato(22, Integer.class));
				estructuraDTO.setPrestamo(genericoDTO.getDato(23, Integer.class));
				estructuraDTO.setPa(genericoDTO.getDato(24, BigDecimal.class));

				estructuraDTO.setTotalBruto(genericoDTO.getDato(25, BigDecimal.class));
				estructuraDTO.setPercepciones(genericoDTO.getDato(26, BigDecimal.class));
				estructuraDTO.setDeducciones(genericoDTO.getDato(27, BigDecimal.class));
				estructuraDTO.setNeto(genericoDTO.getDato(28, BigDecimal.class));

				estructuraDTO.setIdSiifBitacora(bitacora.getIdSiifBitacora());

				listaEstructura.add(estructuraDTO);
			}
			registrarListaEstructuraPlantillaExcel(listaEstructura);
			JSFUtils.infoMessage("Registro Contratos", "Proceso realizado correctamente");

		} catch (EstructuraException e) {
			e.printStackTrace();			
			JSFUtils.errorMessage("Error", e.getMessage());
			JSFUtils.infoMessage("Importacion Contratos", "La importacion no fue completada");
		}
		return bitacora;
	}
	
	// <<<<<<Se Introducen los Datos de Estructura Nomina>>>>>>
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private EstructuraNominaDatEntity toEstructuraNominaEntity(String[] array) {
		EstructuraNominaDatEntity entity = new EstructuraNominaDatEntity();
		entity.setNumEmp(((array[0]).equals(" ")) ? null : String.valueOf(array[0]));
		entity.setRfc(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		//System.out.println(entity.getRfc());
		//LOGGER.debugv("RFC:::", entity.getRfc());
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
		entity.setDiasLab(((array[57]).equals(" ")) ? null : Integer.parseInt(array[57]));//
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private EstructuraContratoExcelEntity toEstructuraContratoExcelEntity(String[] array) {
		EstructuraContratoExcelEntity entity = new EstructuraContratoExcelEntity();
		entity.setNum(((array[0]).equals(" ")) ? null : Integer.valueOf(array[0]));
		entity.setPrograma(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		entity.setRfc(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
		entity.setRfcVal(((array[3]).equals(" ")) ? null : String.valueOf(array[3]));
		entity.setNombre(((array[4]).equals(" ")) ? null : String.valueOf(array[4]));
		entity.setfI(((array[5]).equals(" ")) ? null : String.valueOf(array[5]));
		entity.setcResponsable(((array[6]).equals(" ")) ? null : String.valueOf(array[6]));
		entity.setFuncion(((array[7]).equals(" ")) ? null : String.valueOf(array[7]));
		entity.setRama(((array[8]).equals(" ")) ? null : String.valueOf(array[8]));
		entity.setfFinan(((array[9]).equals(" ")) ? null : String.valueOf(array[9]));
		entity.setProg(((array[10]).equals(" ")) ? null : String.valueOf(array[10]));
		entity.setTotalBruto(((array[11]).equals(" ")) ? null : new BigDecimal(array[11]));
		entity.setPercepciones(((array[12]).equals(" ")) ? null : new BigDecimal(array[12]));
		entity.setDeducciones(((array[13]).equals(" ")) ? null : new BigDecimal(array[13]));
		entity.setNeto(((array[14]).equals(" ")) ? null : new BigDecimal(array[14]));
		entity.setSueldo(((array[15]).equals(" ")) ? null : new BigDecimal(array[15]));
		entity.setSup(((array[16]).equals(" ")) ? null : new BigDecimal(array[16]));
		entity.setComp(((array[17]).equals(" ")) ? null : new BigDecimal(array[17]));
		entity.setAg(((array[18]).equals(" ")) ? null : new BigDecimal(array[18]));
		entity.setSubsidio(((array[19]).equals(" ")) ? null : new BigDecimal(array[19]));
		entity.setVac(((array[20]).equals(" ")) ? null : Integer.valueOf(array[20]));
		entity.setrFaltas(((array[21]).equals(" ")) ? null : Integer.valueOf(array[21]));
		entity.setRetroa(((array[22]).equals(" ")) ? null : new BigDecimal(array[22]));
		entity.setOtros(((array[23]).equals(" ")) ? null : new BigDecimal(array[23]));
		entity.setFaltas(((array[24]).equals(" ")) ? null : Integer.valueOf(array[24]));
		entity.setIstp(((array[25]).equals(" ")) ? null : new BigDecimal(array[25]));
		entity.setRespons(((array[26]).equals(" ")) ? null : Integer.valueOf(array[26]));
		entity.setPrestamo(((array[27]).equals(" ")) ? null : Integer.valueOf(array[27]));
		entity.setPa(((array[28]).equals(" ")) ? null : new BigDecimal(array[28]));
		entity.setTotal(((array[29]).equals(" ")) ? null : new BigDecimal(array[29]));
		entity.setVerifica(((array[30]).equals(" ")) ? null : String.valueOf(array[30]));
		entity.settCentro(((array[31]).equals(" ")) ? null : String.valueOf(array[31]));
		entity.setcClues(((array[32]).equals(" ")) ? null : String.valueOf(array[32]));
		entity.setNomUnidad(((array[33]).equals(" ")) ? null : String.valueOf(array[33]));
		entity.setaAdscripcion(((array[34]).equals(" ")) ? null : String.valueOf(array[34]));
		entity.setPuesto(((array[35]).equals(" ")) ? null : String.valueOf(array[35]));
		entity.setcPuesto(((array[36]).equals(" ")) ? null : String.valueOf(array[36]));
		entity.setServicio(((array[37]).equals(" ")) ? null : String.valueOf(array[37]));
		entity.setRamas(((array[38]).equals(" ")) ? null : String.valueOf(array[38]));
		entity.setTurno(((array[39]).equals(" ")) ? null : String.valueOf(array[39]));
		return entity;
	}

	// <<<<<<Se Introducen los Datos de Estructura Nomina Trailer>>>>>>
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private String[] procesarArchivoTra(String dato) {
		String newArray = "";
		for (int i = 0; i < dato.length(); i++) {
			newArray += dato.charAt(i);
			if (String.valueOf(dato.charAt(i)).equals("|")) {
				if (String.valueOf(dato.charAt(i + 1)).equals("|")) {
					newArray += "| |";
				}
				// System.out.println("newArray +i"+newArray+"/"+i);
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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private EstructuraNominaTrailersEntity toEstructuraNominaTrailers(String[] array) {
		EstructuraNominaTrailersEntity entity = new EstructuraNominaTrailersEntity();
		
		entity.setRfc(((array[0]).equals(" ")) ? null : String.valueOf(array[0]));
		//LOGGER.debugv("RFC:::", entity.getRfc());
		entity.setNumEmp(((array[1]).equals(" ")) ? null : String.valueOf(array[1]));
		entity.setNumCheq(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
		entity.setSubCheque(((array[2]).equals(" ")) ? 0 : Integer.valueOf(array[2].substring(0, 2)));
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

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO clasificarEncabezados(SiifBitacoraDTO bitacora) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_clasificar_nomina_estructura(:idSiifBitacora)")
				.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")

		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();
		LOGGER.debugv("sp_clasificar_nomina_estructura result::", result);
		Integer[] idPensiones = { 5, 10, 19, 21, 24, 27, 30, 33, 38, 45 };
		
		for (SIIFEncabezadoDTO dto : result) {
			dto.setIdCuentaBancaria(bitacora.getIdCuentaBancaria());
			dto.setIdTipoNomina(bitacora.getIdTipoNomina());
			dto.setIdSIIFBitacora(bitacora.getIdSiifBitacora());

			// Si son PENSIONES hay que unirlos
			if (Arrays.asList(idPensiones).contains(dto.getIdTipoNomina())) {
				LOGGER.debugv("Nomina Pension::", dto.getIdTipoNomina());
				LOGGER.debugv("Bitacora::", bitacora.getIdSiifBitacora());
				// Si son REGULARIZADOS hay que clasificarlos en:
				// Federales, Seguro Popular Federal y Seguro Popular
				if (dto.getIdNombramiento().intValue() == 4) {
					//System.out.println("dto.getIdNombramiento().intValue():: " + dto.getIdNombramiento().intValue());
					query = session
							.createSQLQuery("CALL sp_clasificar_reg_seguro_popular(:idSiifBitacora, :idNombramiento)")
							.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora())
							.setParameter("idNombramiento", dto.getIdNombramiento());
					query.executeUpdate();

					query = session.createSQLQuery("CALL usp_obtener_encabezados_seguro_popular(:idSiifBitacora)")
							.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
					query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
					@SuppressWarnings("unchecked")
					List<SIIFEncabezadoDTO> resultReg = (List<SIIFEncabezadoDTO>) query.list();

//					System.out.println("CALL sp_clasificar_reg_seguro_popular(:idSiifBitacora, :idNombramiento) result2::"+ resultReg);
					for (SIIFEncabezadoDTO dtoReg : resultReg) {
						dtoReg.setIdCuentaBancaria(bitacora.getIdCuentaBancaria());
						dtoReg.setIdTipoNomina(bitacora.getIdTipoNomina());
						dtoReg.setIdSIIFBitacora(bitacora.getIdSiifBitacora());
						actualizarSiifEncabezado(dtoReg);
					}
					encabezadoRepository.eliminarPorId(dto.getIdSIIFEncabezado());
					
					// Si son PERSONAL EN FORMACION hay que unirlos
				} else {
					actualizarSiifEncabezado(dto);
					System.out.println("actualizarSiifEncabezado(dto) 1 ::");

				}

			} else {
				// Si son REGULARIZADOS hay que clasificarlos en:
				// Federales, Seguro Popular Federal y Seguro Popular
				if (dto.getIdNombramiento().intValue() == 4) {
					query = session
							.createSQLQuery("CALL sp_clasificar_reg_seguro_popular(:idSiifBitacora, :idNombramiento)")
							.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora())
							.setParameter("idNombramiento", dto.getIdNombramiento());
					query.executeUpdate();

					query = session.createSQLQuery("CALL usp_obtener_encabezados_seguro_popular(:idSiifBitacora)")
							.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
					query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
					@SuppressWarnings("unchecked")
					List<SIIFEncabezadoDTO> resultReg = (List<SIIFEncabezadoDTO>) query.list();

					System.out
							.println("CALL sp_clasificar_reg_seguro_popular(:idSiifBitacora, :idNombramiento) result2::"
									+ resultReg);
					for (SIIFEncabezadoDTO dtoReg : resultReg) {
						dtoReg.setIdCuentaBancaria(bitacora.getIdCuentaBancaria());
						dtoReg.setIdTipoNomina(bitacora.getIdTipoNomina());
						dtoReg.setIdSIIFBitacora(bitacora.getIdSiifBitacora());
						actualizarSiifEncabezado(dtoReg);

					}

					encabezadoRepository.eliminarPorId(dto.getIdSIIFEncabezado());

				} else {
					// Si son PERSONAL EN FORMACION hay que unirlos
					actualizarSiifEncabezado(dto);
					System.out.println("actualizarSiifEncabezado(dto) 2 ::");

				}
			}

		}

		System.out.println("limpiarSiifEncabezados:: " + bitacora.getIdSiifBitacora());
		limpiarSiifEncabezados(bitacora.getIdSiifBitacora());

		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO clasificarEncabezadosContratos(SiifBitacoraDTO bitacora) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println("inicia clasificar contratos:: ");
		Query query = session.createSQLQuery("CALL usp_clasificar_nomina_contrato(:idSiifBitacora)")
				.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")

		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();

		System.out.println("sp_clasificar_nomina_contrato result:: " + result);
		for (SIIFEncabezadoDTO dto : result) {
			dto.setIdCuentaBancaria(bitacora.getIdCuentaBancaria());
			dto.setIdTipoNomina(bitacora.getIdTipoNomina());
			dto.setIdSIIFBitacora(bitacora.getIdSiifBitacora());

			actualizarSiifEncabezado(dto);
			System.out.println("actualizarSiifEncabezado(dto)  :: Contratos");

		}

		// System.out.println("limpiarSiifEncabezados:: " +
		// bitacora.getIdSiifBitacora());
		// limpiarSiifEncabezados(bitacora.getIdSiifBitacora());

		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO cambiarClaveConceptosTra(SiifBitacoraDTO bitacora) {
		System.out.println("CALL sp_cambiar_clave_concepto(:idSiifBitacora)");
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_cambiar_clave_concepto(:idSiifBitacora)")
				.setParameter("idSiifBitacora", bitacora.getIdSiifBitacora());
		query.executeUpdate();
		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO asignarEncabezadosTrailers(SiifBitacoraDTO bitacora) {
		System.out.println("CALL usp_asignar_encabezados_a_trailers(:id_siif_bitacoras)");
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_asignar_encabezados_a_trailers(:id_siif_bitacoras) ")
				.setParameter("id_siif_bitacoras", bitacora.getIdSiifBitacora());
		query.executeUpdate();
		bitacora.setStatus("Trailers Asignados");
		actualizarSiifBitacora(bitacora);
		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO calcularEncabezados(SiifBitacoraDTO bitacora) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_calcular_encabezados(:id_siif_bitacoras) ")
				.setParameter("id_siif_bitacoras", bitacora.getIdSiifBitacora());
		query.executeUpdate();
		bitacora.setStatus("Encabezados Calculados");
		actualizarSiifBitacora(bitacora);
		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO calcularTotalesEncabezadosContratos(SiifBitacoraDTO bitacora) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_calcular_totales_encabezados_contratos(:id_siif_bitacoras) ")
				.setParameter("id_siif_bitacoras", bitacora.getIdSiifBitacora());
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")
		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();
		for (SIIFEncabezadoDTO dto : result) {
			BigDecimal totalDeducciones = bitacora.getTotalDeducciones() == null ? BigDecimal.ZERO
					: bitacora.getTotalDeducciones();
			BigDecimal totalPercepciones = bitacora.getTotalPercepciones() == null ? BigDecimal.ZERO
					: bitacora.getTotalPercepciones();
			BigDecimal neto = bitacora.getTotalNeto() == null ? BigDecimal.ZERO : bitacora.getTotalNeto();
			bitacora.setTotalDeducciones(totalDeducciones.add(dto.getDeducciones()));
			bitacora.setTotalPercepciones(totalPercepciones.add(dto.getPercepciones()));
			bitacora.setTotalNeto(neto.add(dto.getNeto()));
		}

		bitacora.setStatus("Importado");
		actualizarSiifBitacora(bitacora);
		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO calcularTotalesEncabezadosContrato(SiifBitacoraDTO bitacora) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_calcular_totales_encabezados_contrato(:id_siif_bitacoras) ")
				.setParameter("id_siif_bitacoras", bitacora.getIdSiifBitacora());
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")
		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();
		for (SIIFEncabezadoDTO dto : result) {
			BigDecimal totalDeducciones = bitacora.getTotalDeducciones() == null ? BigDecimal.ZERO
					: bitacora.getTotalDeducciones();
			BigDecimal totalPercepciones = bitacora.getTotalPercepciones() == null ? BigDecimal.ZERO
					: bitacora.getTotalPercepciones();
			BigDecimal neto = bitacora.getTotalNeto() == null ? BigDecimal.ZERO : bitacora.getTotalNeto();
			bitacora.setTotalDeducciones(totalDeducciones.add(dto.getDeducciones()));
			bitacora.setTotalPercepciones(totalPercepciones.add(dto.getPercepciones()));
			bitacora.setTotalNeto(neto.add(dto.getNeto()));
		}

		bitacora.setStatus("Importado");
		actualizarSiifBitacora(bitacora);
		return bitacora;
	}

	//
	// public List<SubfuenteFinanciamiento> obtenerSubfuenteFinanciamientoList()
	// {
	// Session session = entityManager.unwrap(Session.class);
	// Query query = session.createSQLQuery(
	// "SELECT "
	// + " id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
	// + " descripcion "
	// + " FROM subfuentes_financiamientos "
	// + " WHERE id_tipo_nombramiento = :idTipoNombramiento ")
	// .setParameter("idTipoNombramiento", 1);
	// query.setResultTransformer(Transformers.aliasToBean(SubfuenteFinanciamiento.class));
	// @SuppressWarnings("unchecked")
	// List<SubfuenteFinanciamiento> result = (List<SubfuenteFinanciamiento>)
	// query.list();
	// return result;
	// }
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public SiifBitacoraDTO verificarDatos(SiifBitacoraDTO bitacora) {
		// Veriricar que los RFC existan en las bases de Datos Personales.
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_calcular_totales_encabezados(:id_siif_bitacoras) ")
				.setParameter("id_siif_bitacoras", bitacora.getIdSiifBitacora());
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")
		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();
		// Veriricar que los RFC existan en las bases de Datos Laborales.
		// Veriricar que se obtiene el idLaboral correctamente.

		return bitacora;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<SIIFEncabezadoDTO> generarEncabezadoSiif(int anio_reporte, String periodo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL sp_generar_encabezado_siif_final(:anio_reporte, :periodo) ");
		query.setParameter("anio_reporte", anio_reporte);
		query.setParameter("periodo", periodo);
		query.setResultTransformer(Transformers.aliasToBean(SIIFEncabezadoDTO.class));
		@SuppressWarnings("unchecked")
		List<SIIFEncabezadoDTO> result = (List<SIIFEncabezadoDTO>) query.list();

		return result;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void dispersion(Integer idSiifBitacora, Integer tipoNominaEmision, String periodo) {
		if (tipoNominaEmision == 'C') {
			System.out.println("Dispersion - size: " + deudoresPorIdPeriodo(periodo).size());
			for (SiifDeudoresDiversosDTO dto : deudoresPorIdPeriodo(periodo)) {
				dispersionToEntity(dto, idSiifBitacora);
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void dispersionToEntity(SiifDeudoresDiversosDTO d, Integer id) {
		for (EstructuraNominaTrailersDTO DTO : nominaTrailersPorDeudores(id, d.getRfc(), d.getImporte())) {
			EstructuraNominaTrailersEntity entity = nominaTrailersDAO
					.obtenerPorId(DTO.getIdEstructurasNominasTrailers());
			entity.setConcep("29");
			entity.setPtaAnt("DD");
			entity.setIdConcepto(123);
			entity.setConceptoSiif("29DD");
			nominaTrailersDAO.actualizar(entity);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deudores(Integer idSiifBitacora, Integer tipoNomina, String periodo) {
		if (tipoNomina == 1) {
			System.out.println("size" + deudoresPorIdPeriodo(periodo).size());
			for (SiifDeudoresDiversosDTO dto : deudoresPorIdPeriodo(periodo)) {
				deudoresa(dto, idSiifBitacora);
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deudoresa(SiifDeudoresDiversosDTO d, Integer id) {
		for (EstructuraNominaTrailersDTO DTO : nominaTrailersPorDeudores(id, d.getRfc(), d.getImporte())) {
			EstructuraNominaTrailersEntity entity = nominaTrailersDAO
					.obtenerPorId(DTO.getIdEstructurasNominasTrailers());
			entity.setConcep("29");
			entity.setPtaAnt("DD");
			entity.setIdConcepto(123);
			entity.setConceptoSiif("29DD");
			nominaTrailersDAO.actualizar(entity);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<SiifDeudoresDiversosDTO> deudoresPorIdPeriodo(String quincena) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("" + "SELECT id_deudores_diversos AS idDeudoresDiversos, " + "rfc AS rfc, "
						+ "quincena AS quincena, " + "importe AS importe "
						+ "FROM siif_deudores_diversos WHERE quincena =:quincena")
				.setParameter("quincena", quincena);
		query.setResultTransformer(Transformers.aliasToBean(SiifDeudoresDiversosDTO.class));
		@SuppressWarnings("unchecked")
		List<SiifDeudoresDiversosDTO> result = (List<SiifDeudoresDiversosDTO>) query.list();
		return result;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void bitacora(Integer idSiifBitacora, Integer tipoNomina, String periodo) {
		if (tipoNomina == 1) {
			System.out.println("size" + deudoresPorIdPeriodo(periodo).size());
			for (SiifDeudoresDiversosDTO dto : deudoresPorIdPeriodo(periodo)) {
				deudoresa(dto, idSiifBitacora);
			}
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void eliminaBitacoraPorId(Integer idSiifBitacora) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println("CALL usp_elimina_siif_bitacora(:idSiifBitacora):: " + idSiifBitacora);
		Query query = session
				.createSQLQuery("CALL usp_elimina_siif_bitacora(:idSiifBitacora)")
				.setParameter("idSiifBitacora", idSiifBitacora);
		query.executeUpdate();
	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void eliminaEncabezadoPorId(Integer idSiifBitacora) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println("CALL usp_elimina_siif_encabezados(:idSiifBitacora):: " + idSiifBitacora);
		Query query = session
				.createSQLQuery("CALL usp_elimina_siif_encabezados(:idSiifBitacora)")
				.setParameter("idSiifBitacora", idSiifBitacora);
		query.executeUpdate();
	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<EstructuraNominaTrailersDTO> nominaTrailersPorDeudores(Integer idSiifBitacoras, String rfc,
			BigDecimal importe) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("" + "SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc AS rfc, " + "concep AS concep, " + "importe AS importe "
						+ "FROM estructuras_nominas_trailers WHERE id_siif_bitacoras =:idSiifBitacoras AND rfc =:rfc AND importe =:importe")
				.setParameter("idSiifBitacoras", idSiifBitacoras).setParameter("rfc", rfc)
				.setParameter("importe", importe);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<EstructuraNominaTrailersDTO> nominaTrailersPorDispersion(Integer idSiifBitacoras, String rfc,
			BigDecimal importe) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("" + "SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc AS rfc, " + "concep AS concep, " + "importe AS importe "
						+ "FROM estructuras_nominas_trailers WHERE id_siif_bitacoras =:idSiifBitacoras AND rfc =:rfc AND importe =:importe")
				.setParameter("idSiifBitacoras", idSiifBitacoras).setParameter("rfc", rfc)
				.setParameter("importe", importe);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<EstructuraNominaTrailersDTO> listaDeudores(Integer idSiifBitacoras) {
		String c = "29DD";
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("" + "SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc AS rfc, " + "concep AS concep, " + "num_emp AS numEmp, " + "importe AS importe "
						+ "FROM estructuras_nominas_trailers WHERE id_siif_bitacoras =:idSiifBitacoras AND concepto_siif =:c")
				.setParameter("idSiifBitacoras", idSiifBitacoras).setParameter("c", c);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<EstructuraNominaTrailersDTO> listaDispersion(Integer idSiifBitacoras) {

		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("" + "SELECT id_estructuras_nominas_trailers AS idEstructurasNominasTrailers, "
						+ "rfc AS rfc, " + "num_cheq AS numCheq, " + "importe AS importe "
						+ "FROM estructuras_nominas_trailers WHERE id_siif_bitacoras =:idSiifBitacoras")
				.setParameter("idSiifBitacoras", idSiifBitacoras);
		query.setResultTransformer(Transformers.aliasToBean(EstructuraNominaTrailersDTO.class));
		@SuppressWarnings("unchecked")
		List<EstructuraNominaTrailersDTO> result = (List<EstructuraNominaTrailersDTO>) query.list();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
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
				estructuraEntity.setVac(cont.getVac());
				estructuraEntity.setrFaltas(cont.getrFaltas());
				estructuraEntity.setrFaltas(cont.getrFaltas());// faltaba esta
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

				estructuraEntity.setIdSiifBitacora(cont.getIdSiifBitacora());

				registroEstructuraExcel(estructuraEntity);

			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	//Plantilla Contratos
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void registrarListaEstructuraPlantillaExcel(List<EstructuraContratosPlantillaExcelDTO> listaEstructura) {
		try {

			if (listaEstructura.isEmpty()) {
				throw new ReglaNegocioException("No existen registros de datos");
			}

			for (EstructuraContratosPlantillaExcelDTO cont : listaEstructura) {
				EstructuraContratoPlantillaExcelEntity estructuraEntity = new EstructuraContratoPlantillaExcelEntity();

				estructuraEntity.setTipoNomina(cont.getTipoNomina());				
				estructuraEntity.setRfc(cont.getRfc());
				estructuraEntity.setRfcVal(cont.getRfcVal());
				estructuraEntity.setNombre(cont.getNombre());
				estructuraEntity.setCheqDap(cont.getCheqDap());
				estructuraEntity.setfI(cont.getfI());
				estructuraEntity.setcResponsable(cont.getcResponsable());
				estructuraEntity.setFuncion(cont.getFuncion());
				estructuraEntity.setRama(cont.getRama());				
				estructuraEntity.setPrograma(cont.getPrograma());				
				estructuraEntity.setSueldo(cont.getSueldo());
				estructuraEntity.setSup(cont.getSup());
				estructuraEntity.setHonorarios(cont.getHonorarios());
				estructuraEntity.setComp(cont.getComp());
				estructuraEntity.setAg(cont.getAg());
				estructuraEntity.setSubsidio(cont.getSubsidio());
				estructuraEntity.setVac(cont.getVac());
				estructuraEntity.setrFaltas(cont.getrFaltas());
				estructuraEntity.setRetroa(cont.getRetroa());
				estructuraEntity.setOtros(cont.getOtros());
				estructuraEntity.setFaltas(cont.getFaltas());
				estructuraEntity.setIstp(cont.getIstp());
				estructuraEntity.setRespons(cont.getRespons());
				estructuraEntity.setPrestamo(cont.getPrestamo());
				estructuraEntity.setPension(cont.getPa());
				estructuraEntity.setTotalBruto(cont.getTotalBruto());
				estructuraEntity.setPercepciones(cont.getPercepciones());
				estructuraEntity.setDeducciones(cont.getDeducciones());
				estructuraEntity.setNeto(cont.getNeto());
				estructuraEntity.setIdSiifBitacora(cont.getIdSiifBitacora());

				registroEstructuraPlantillaExcel(estructuraEntity);
			}
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String registroEstructuraExcel(EstructuraContratoExcelEntity estructura) {
		entityManager.persist(estructura);
		return estructura.getIdEstructura();
	}
	
	//Plantilla Contratos
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String registroEstructuraPlantillaExcel(EstructuraContratoPlantillaExcelEntity estructura) {
		entityManager.persist(estructura);
		return estructura.getIdEstructura();
	}

}