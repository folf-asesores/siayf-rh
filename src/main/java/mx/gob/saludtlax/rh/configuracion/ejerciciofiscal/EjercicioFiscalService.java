package mx.gob.saludtlax.rh.configuracion.ejerciciofiscal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;

import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalEntity;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalRepository;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosEntity;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

@Stateless
public class EjercicioFiscalService {
	@PersistenceContext(name = "siayfrhPU")
	private EntityManager entityManager;

	@Inject
	private EjercicioFiscalRepository ejercicioFiscalDAO;
	@Inject
	private PeriodoCalendariosRepository periodoCalendariosDAO;
	@Inject
	private TipoPeriodoRepository tipoPeriodoDAO;

	public List<EjercicioFiscalListaDTO> listaEjercicioFiscal() {
		TypedQuery<EjercicioFiscalListaDTO> query = entityManager
				.createQuery("SELECT new mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalListaDTO("
						+ " ef.idEjercicioFiscal," + " ef.ejercicioFiscal," + " ef.inicio," + " ef.fin,"
						+ " ef.tipoPeriodo.tipoPeriodo)" + " FROM EjercicioFiscalEntity AS ef"
						+ " ORDER BY ef.ejercicioFiscal", EjercicioFiscalListaDTO.class);

		List<EjercicioFiscalListaDTO> result = query.getResultList();
		return result;
	}

	public List<EjercicioFiscalListaDTO> listaEjercicioFiscalPorejercicioFiscal(Integer ejercicioFiscal) {
		TypedQuery<EjercicioFiscalListaDTO> query = entityManager.createQuery(
				"SELECT new mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalListaDTO("
						+ " ef.idEjercicioFiscal," + " ef.ejercicioFiscal," + " ef.inicio," + " ef.fin,"
						+ " ef.tipoPeriodo.tipoPeriodo)" + " FROM EjercicioFiscalEntity AS ef" + " WHERE "
						+ " ef.ejercicioFiscal = :ejercicioFiscal" + " ORDER BY ef.ejercicioFiscal",
				EjercicioFiscalListaDTO.class).setParameter("ejercicioFiscal", ejercicioFiscal);
		return query.getResultList();
	}

	public EjercicioFiscalDTO nuevoEjercicioFiscal() {
		EjercicioFiscalDTO dto = new EjercicioFiscalDTO();
		Integer ejercicioActual = FechaUtil.ejercicioActual();
		dto.setEjercicioFiscal(ejercicioActual);
		dto.setInicio(FechaUtil.primerDiaEjercicioFiscal(ejercicioActual));
		dto.setFin(FechaUtil.ultimoDiaEjercicioFiscal(ejercicioActual));
		dto.setIdPeriodicidad(4);
		return dto;
	}

	public EjercicioFiscalDTO crearEjercicioFiscal(EjercicioFiscalDTO dto) {
		EjercicioFiscalEntity entity = new EjercicioFiscalEntity();
		entity.setEjercicioFiscal(dto.getEjercicioFiscal());
		entity.setInicio(dto.getInicio());
		entity.setFin(dto.getFin());
		TipoPeriodoEntity tipoPeriodoEntity = tipoPeriodoDAO.obtenerPorId(dto.getIdPeriodicidad());
		entity.setTipoPeriodo(tipoPeriodoEntity);
		ejercicioFiscalDAO.crear(entity);
		return obtenerEjercicioFiscalPorId(entity.getIdEjercicioFiscal());
	}

	public EjercicioFiscalDTO obtenerEjercicioFiscalPorId(Integer idEjercicioFiscal) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(
						"SELECT id_ejercicio_fiscal AS idEjercicioFiscal, " + "ejercicio_fiscal AS ejercicioFiscal, "
								+ "inicio As inicio, " + "fin AS fin, " + "id_tipo_periodo as idPeriodicidad "
								+ "FROM ejercicios_fiscales WHERE id_ejercicio_fiscal = :idEjercicioFiscal")
				.setParameter("idEjercicioFiscal", idEjercicioFiscal);
		query.setResultTransformer(Transformers.aliasToBean(EjercicioFiscalDTO.class));
		EjercicioFiscalDTO result = (EjercicioFiscalDTO) query.list().get(0);
		return result;
	}

	public Integer obtenerAnioPorId(Integer idEjercicioFiscal) {
		Integer an = entityManager
				.createQuery(
						"SELECT ejercicio_fiscal AS ejercicioFiscal "
								+ "FROM ejercicios_fiscales WHERE id_ejercicio_fiscal = :idEjercicioFiscal",
						Integer.class)
				.setParameter("idEjercicioFiscal", idEjercicioFiscal).getSingleResult();
		return an;
	}

	public EjercicioFiscalDTO actualizarEjercicioFiscal(EjercicioFiscalDTO dto) {
		EjercicioFiscalEntity ejercicioFiscalEntity = ejercicioFiscalDAO.obtenerPorId(dto.getIdEjercicioFiscal());
		ejercicioFiscalEntity.setEjercicioFiscal(dto.getEjercicioFiscal());
		ejercicioFiscalEntity.setInicio(dto.getInicio());
		ejercicioFiscalEntity.setFin(dto.getFin());
		TipoPeriodoEntity tipoPeriodoEntity = tipoPeriodoDAO.obtenerPorId(dto.getIdPeriodicidad());
		ejercicioFiscalEntity.setTipoPeriodo(tipoPeriodoEntity);
		ejercicioFiscalDAO.actualizar(ejercicioFiscalEntity);

		for (PeriodoCalendarioDTO periodo : dto.getListPeriodoCalendario()) {
			PeriodoCalendariosEntity entity = new PeriodoCalendariosEntity();
			entity.setIdPeriodoCalendario(periodo.getIdPeriodoCalendario());
			entity.setInicioPeriodo(periodo.getInicioPeriodo());
			entity.setFinPeriodo(periodo.getFinPeriodo());

			TipoPeriodoEntity tipoPeriodoEntityTem = tipoPeriodoDAO.obtenerPorId(periodo.getIdTipoPeriodo());
			entity.setTipoPeriodo(tipoPeriodoEntityTem);
			entity.setNumeroPeriodo(periodo.getNumeroPeriodo());
			entity.setIdEjercicioFiscal(ejercicioFiscalEntity.getIdEjercicioFiscal());
			periodoCalendariosDAO.actualizar(entity);
		}
		return obtenerEjercicioFiscalPorId(ejercicioFiscalEntity.getIdEjercicioFiscal());
	}

	public void eliminarEjercicioFical(EjercicioFiscalListaDTO dto) {
		EjercicioFiscalEntity entity = entityManager.find(EjercicioFiscalEntity.class, dto.getIdEjercicioFiscal());
		entityManager.remove(entity);
	}

	// Opciones para Periodos
	// Obtener

	public PeriodoCalendarioDTO obtenerPeriodoCalendarioPorId(Integer idPeriodoCalendario) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT " + " id_periodo_calendario AS idPeriodoCalendario, "
						+ " inicio_periodo AS inicioPeriodo, " + " fin_periodo AS finPeriodo, "
						+ " tipo_periodo As idTipoPeriodo, " + " numero_periodo AS numeroPeriodo, "
						+ " id_ejercicio_fiscal AS idEjercicioFiscal"
						+ " FROM periodos_calendarios WHERE id_periodo_calendario = :idPeriodoCalendario")
				.setParameter("idPeriodoCalendario", idPeriodoCalendario);
		query.setResultTransformer(Transformers.aliasToBean(PeriodoCalendarioDTO.class));
		PeriodoCalendarioDTO result = (PeriodoCalendarioDTO) query.list().get(0);
		return result;
	}

	// public List<PeriodoCalendarioDTO> listaPeriodoCalendario() {
	// Session session = entityManager.unwrap(Session.class);
	// Query query = session.createSQLQuery("SELECT * FROM periodos ORDER BY
	// inicioPeriodo");
	// query.setResultTransformer(Transformers.aliasToBean(PeriodoCalendarioDTO.class));
	// @SuppressWarnings("unchecked")
	// List<PeriodoCalendarioDTO> result = (List<PeriodoCalendarioDTO>)
	// query.list();
	// return result;
	// }

	public List<PeriodoCalendarioDTO> listaPeriodoCalendarioPorIdEjercicioFiscal(Integer idEjercicioFiscal) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT                                                                 "
						+ "	pc.id_periodo_calendario 		AS idPeriodoCalendario,                     "
						+ "	pc.inicio_periodo 				AS inicioPeriodo,                      		"
						+ "	pc.fin_periodo 					AS finPeriodo,                      		"
						+ "	pc.numero_periodo 				AS numeroPeriodo,                      		"
						+ "	pc.id_ejercicio_fiscal 			AS idEjercicioFiscal,                       "
						+ "	tp.id_tipo_periodo 				AS idTipoPeriodo                            "
						+ "	FROM periodos_calendarios AS pc                                             "
						+ "	INNER JOIN tipos_periodos AS tp                                             "
						+ "	ON tp.id_tipo_periodo = pc.tipo_periodo                                     "
						+ "	WHERE                                                                  		"
						+ "	pc.id_ejercicio_fiscal = :idEjercicioFiscal                                 "
						+ "	ORDER BY pc.inicio_periodo                                            		")
				.setParameter("idEjercicioFiscal", idEjercicioFiscal);
		query.setResultTransformer(Transformers.aliasToBean(PeriodoCalendarioDTO.class));
		@SuppressWarnings("unchecked")
		List<PeriodoCalendarioDTO> result = (List<PeriodoCalendarioDTO>) query.list();
		return result;
	}

	public Integer siguienteNumero(Integer idEjercicioFiscal) {
		Integer p = entityManager
				.createQuery("SELECT MAX(a.numeroPeriodo) " + "FROM PeriodoCalendariosEntity a "
						+ "WHERE a.idEjercicioFiscal = :idEjercicioFiscal", Integer.class)
				.setParameter("idEjercicioFiscal", idEjercicioFiscal).getSingleResult();
		return (p == null) ? 1 : p + 1;
	}

	public Date siguienteFecha(Integer idEjercicioFiscal) {
		Date dt = entityManager
				.createQuery("SELECT MAX(a.finPeriodo) " + "FROM PeriodoCalendariosEntity a "
						+ "WHERE a.idEjercicioFiscal = :idEjercicioFiscal", Date.class)
				.setParameter("idEjercicioFiscal", idEjercicioFiscal).getSingleResult();
		Integer anio = ejercicioEnviado(dt);
		if (dt == null)
			return dt = FechaUtil.primerDiaEjercicioFiscal(anio);
		else
			return siguienteDia(dt);

	}

	public Date siguienteDia(Date d) {
		DateTime fecha = new DateTime(d);
		fecha = fecha.plusDays(1);
		return fecha.toDate();
	}

	public Date siguienteQuincena(Integer idEjercicioFiscal) {
		Date dt = siguienteFecha(idEjercicioFiscal);
		Integer anio = ejercicioEnviado(dt);
		Integer mes = numMes(dt);
		Integer dia = diaMes(dt);
		if (dia < 15)
			return primeraQuincena(anio, mes);
		else
			return dt = FechaUtil.ultimoDiaMes(mes, anio);

	}

	public static Integer ejercicioEnviado(Date dt) {
		DateTime fecha = new DateTime(dt);
		Integer anio = fecha.getYear();
		return anio;
	}

	public static Integer numMes(Date dt) {
		DateTime fecha = new DateTime(dt);
		Integer mes = fecha.getMonthOfYear();
		return mes;
	}

	public static Integer diaMes(Date dt) {
		DateTime fecha = new DateTime(dt);
		Integer dia = fecha.getDayOfMonth();
		return dia;
	}

	public Date inicioQuincena(Integer ef) {
		DateTime fecha = new DateTime(ef, 1, 15, 0, 0);
		return fecha.toDate();
	}

	public Date primeraQuincena(Integer ef, Integer m) {
		DateTime fecha = new DateTime(ef, m, 15, 0, 0);
		return fecha.toDate();
	}

	private Integer r;

	public Integer getR() {
		return r;
	}

	public void setR(Integer r) {
		this.r = r;
	}

	private Date d;

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	private Date q;

	public Date getQ() {
		return q;
	}

	public void setQ(Date q) {
		this.q = q;
	}

	// Nuevo Crear Actualizar Eliminar Periodos

	public PeriodoCalendarioDTO nuevoPeriodoCalendario(PeriodoCalendarioDTO dto, Integer ef) {
		PeriodoCalendariosEntity entity = new PeriodoCalendariosEntity();
		r = siguienteNumero(ef);
		d = siguienteFecha(ef);
		q = siguienteQuincena(ef);
		entity.setIdEjercicioFiscal(ef);
		entity.setNumeroPeriodo(r);
		TipoPeriodoEntity tipoPeriodoEntity = tipoPeriodoDAO.obtenerPorId(4);
		entity.setTipoPeriodo(tipoPeriodoEntity);
		entity.setInicioPeriodo(d);
		entity.setFinPeriodo(q);
		periodoCalendariosDAO.crear(entity);
		return obtenerPeriodoCalendarioPorId(entity.getIdPeriodoCalendario());
	}

	public void actualizaPeriodoCalendario(PeriodoCalendarioDTO dto) {
		PeriodoCalendariosEntity entity = periodoCalendariosDAO.obtenerPorId(dto.getIdPeriodoCalendario());
		entity.setInicioPeriodo(dto.getInicioPeriodo());
		entity.setFinPeriodo(dto.getFinPeriodo());
		TipoPeriodoEntity tipoPeriodoEntity = tipoPeriodoDAO.obtenerPorId(dto.getIdTipoPeriodo());
		entity.setTipoPeriodo(tipoPeriodoEntity);
		entity.setNumeroPeriodo(dto.getNumeroPeriodo());
		periodoCalendariosDAO.actualizar(entity);
	}

	public void eliminarPeriodoCalendario(PeriodoCalendarioDTO dto) {
		PeriodoCalendariosEntity entity = entityManager.find(PeriodoCalendariosEntity.class,
				dto.getIdPeriodoCalendario());
		entityManager.remove(entity);
	}

	public Integer obtenerIdEjercicioFiscal(Integer ejercicioFiscal, Integer idTipoPeriodo) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(" SELECT id_ejercicio_fiscal " + " FROM ejercicios_fiscales " + " WHERE "
						+ " ejercicio_fiscal = :ejercicioFiscal " + " AND " + " id_tipo_periodo = :idTipoPeriodo ")
				.setParameter("ejercicioFiscal", ejercicioFiscal).setParameter("idTipoPeriodo", idTipoPeriodo);
		return (Integer) query.uniqueResult();
	}

	public List<PeriodoCalendarioDTO> listaPeriodoCalendario(Integer idEjercicioFiscal) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery(
						"SELECT * FROM periodos p WHERE p.idEjercicioFiscal = :idEjercicioFiscal ORDER BY inicioPeriodo")
				.setParameter("idEjercicioFiscal", idEjercicioFiscal);
		query.setResultTransformer(Transformers.aliasToBean(PeriodoCalendarioDTO.class));
		@SuppressWarnings("unchecked")
		List<PeriodoCalendarioDTO> result = (List<PeriodoCalendarioDTO>) query.list();
		return result;
	}

	public PeriodoCalendarioDTO obtenerPeriodoCalendario(Integer idEjercicioFiscal, Integer numeroPeriodo) {
		System.out.println("obtenerPeriodoCalendario:: idEjercicioFiscal:: " + idEjercicioFiscal);
		System.out.println("obtenerPeriodoCalendario:: numeroPeriodo:: " + numeroPeriodo);
		Session session = entityManager.unwrap(Session.class);
		Query query = session
				.createSQLQuery("SELECT " + " id_periodo_calendario AS idPeriodoCalendario, "
						+ " inicio_periodo AS inicioPeriodo, " + " fin_periodo AS finPeriodo, "
						+ " tipo_periodo As idTipoPeriodo, " + " numero_periodo AS numeroPeriodo, "
						+ " id_ejercicio_fiscal AS idEjercicioFiscal" + " FROM periodos_calendarios " + " WHERE "
						+ " id_ejercicio_fiscal = :idEjercicioFiscal " + " AND " + " numero_periodo = :numeroPeriodo ")
				.setParameter("idEjercicioFiscal", idEjercicioFiscal).setParameter("numeroPeriodo", numeroPeriodo);
		query.setResultTransformer(Transformers.aliasToBean(PeriodoCalendarioDTO.class));
		@SuppressWarnings("unchecked")
		List<PeriodoCalendarioDTO> result = query.list();
		PeriodoCalendarioDTO periodoCalendarioDTO = null;
		if (!result.isEmpty()) {
			periodoCalendarioDTO = result.get(0);
		} else {

		}
		return periodoCalendarioDTO;
	}

	public List<SelectItem> listaEjercicioFiscalItems() {
		List<SelectItem> selectItems = new ArrayList<>();
		List<EjercicioFiscalEntity> listaEjercicioFiscal = ejercicioFiscalDAO.obtenerListaEjercicioFiscal();
		for (EjercicioFiscalEntity ejercicioFiscalEntity : listaEjercicioFiscal) {
			SelectItem item = new SelectItem(ejercicioFiscalEntity.getEjercicioFiscal(),
					ejercicioFiscalEntity.getEjercicioFiscal().toString());

			selectItems.add(item);

		}

		return selectItems;

	}

}