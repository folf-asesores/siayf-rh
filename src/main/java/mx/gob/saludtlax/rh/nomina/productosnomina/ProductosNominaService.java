package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.configuracion.banco.BancoDTO;
import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.persistencia.BancoSatRepository;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptosNominaEmpleadosRepository;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasRepository;
import mx.gob.saludtlax.rh.persistencia.EjercicioFiscalRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusProductoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.FuenteFinanciamientoRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.PeriodoCalendariosRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasEntity;
import mx.gob.saludtlax.rh.persistencia.QuincenasSuplenciasRepository;
import mx.gob.saludtlax.rh.persistencia.SubFuenteFinanciamientoTempRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TipoNominaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoPeriodoRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoNombramiento;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * 
 * @author José Pablo
 *
 */

@Stateless
public class ProductosNominaService {
	@Inject private ProductoNominaRepository productoNominaRepository;
	@Inject private EjercicioFiscalRepository ejercicioFiscalRepository;
	@Inject private EstatusNominasEmpleadoRepository estatusNominaRepository;
	@Inject private PeriodoCalendariosRepository periodoCalendariosRepository;
	@Inject private FuenteFinanciamientoRepository fuenteFinanciamientoDAO;
	@Inject private SubFuenteFinanciamientoTempRepository subfuenteFinanciamientoTempRepository;
	@Inject private TipoNominaRepository tipoNominaRepository;
	@Inject private TipoContratacionRepository tipoContratacionRepository;
	@Inject private EstatusProductoNominaRepository estatusProductoNominaRepository;
	@Inject private TipoPeriodoRepository tipoPeriodoRepository;
	@Inject private UsuarioRepository usuarioRepository;
	@Inject private QuincenasSuplenciasRepository quincenasSuplenciasRepository;
	@Inject private TiposNombramientosRepository tiposNombramientoRepository;
	@Inject private NominaEmpleadoRepository nominaEmpleadoRepository;
	@Inject private BancoSatRepository bancoSatRepository;
	@Inject private CuentasBancariasRepository cuentasBancariasRepository;
    @Inject private ConceptosNominaEmpleadosRepository conceptosNominaEmpleadosRepository;

    	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(ProductosNominaService.class);

	public ProductoNominaDTO crearProductoNomina(ProductoNominaDTO datos) {
		try {
		    LOGGER.debug("valor..." + datos);

			ProductoNominaEntity entity = new ProductoNominaEntity();
			entity.setNombreProducto(datos.getNombreProducto());
			entity.setTipoContratacion(tipoContratacionRepository.obtenerPorId(datos.getIdTipoContratacion()));
			entity.setEjercicioFiscal(ejercicioFiscalRepository.obtenerEjercioPorEjercicionFiscal(datos.getEjercicioFiscal(), datos.getIdTipoPeriodo()));
			entity.setAnyoEjercicioFiscal(datos.getEjercicioFiscal());
			entity.setTipoPeriodo(tipoPeriodoRepository.obtenerPorId(datos.getIdTipoPeriodo()));
			entity.setPeriodoCalendario(periodoCalendariosRepository.obtenerPorId(datos.getIdPeriodoCalendario()));
			entity.setNumeroPeriodo(datos.getNumeroPeriodo());
			entity.setInicioPeriodo(datos.getInicioPeriodo());
			entity.setFinPeriodo(datos.getFinPeriodo());
			entity.setInicioRangoFaltas(datos.getInicioPeriodo());
			entity.setFinRangoFaltas(datos.getFinPeriodo());
			entity.setBanco(bancoSatRepository.obtenerPorId(11));
			entity.setEstatusProductoNomina(estatusProductoNominaRepository.obtenerPorId(datos.getIdEstatusProductoNomina()));
			entity.setFechaPago(datos.getFechaPago());
			entity.setTipoNomina(tipoNominaRepository.obtenerPorId(datos.getIdTipoNomina()));
			entity.setFechaPago(datos.getFechaPago());
			entity.setUsuario(usuarioRepository.obtenerPorId(datos.getIdUsuario()));
			productoNominaRepository.crear(entity);
			return obtenerProductoNomina(entity.getIdProductoNomina());
		} catch (PersistenceException px) {
			px.printStackTrace();
			return null;
		}
	}

	public List<ProductoNominaListaDTO> filtrarProductoNomina(ProductoNominaFiltroDTO filtro) {
		Session session = entityManager.unwrap(Session.class);
	    Integer idTipoNomina = (filtro.getIdTipoNomina() != null ? filtro.getIdTipoNomina() : 0);
	    Integer idEstatus = (filtro.getIdEstatus() != null ? filtro.getIdEstatus() : 0);
	    Integer idSubfuenteFinanciamiento = (filtro.getIdSubfuenteFinanciamiento() != null ? filtro.getIdSubfuenteFinanciamiento() : 0);
	    Integer ejercicioFiscal = (filtro.getEjercicioFiscal() != null && filtro.getEjercicioFiscal() > 0 ? filtro.getEjercicioFiscal() : 0);

		Query query = session
				.createSQLQuery("CALL usp_filtrar_productos_nomina(:ejercicioFiscal, :idSubfuenteFinanciamiento,"
				        + " :idTipoNomina, :idArea, :idEstatus) ")
				.setParameter("ejercicioFiscal",ejercicioFiscal)
				.setParameter("idSubfuenteFinanciamiento", idSubfuenteFinanciamiento)
                .setParameter("idTipoNomina", idTipoNomina)
                .setParameter("idArea", filtro.getIdArea())
				.setParameter("idEstatus", idEstatus);
		query.setResultTransformer(Transformers.aliasToBean(ProductoNominaListaDTO.class));
		@SuppressWarnings("unchecked")
		List<ProductoNominaListaDTO> result = (List<ProductoNominaListaDTO>) query.list();
		return result;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ProductoNominaDTO obtenerProductoNomina(Integer idProductoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT " 
				+ " pdn.id_producto_nomina 				AS idProductoNomina, "
				+ " pdn.nombre_producto 				AS nombreProducto, " 
				+ " pdn.id_ejercicio_fiscal 			AS idEjercicioFiscal, "
				+ " pdn.id_tipo_periodo 				AS idTipoPeriodo, " 
				+ " pdn.id_periodo_calendario 			AS idPeriodoCalendario, "
				+ " pdn.id_fuente_financiamiento 		AS idFuenteFinanciamiento, "
				+ " pdn.id_subfuente_financiamiento 	AS idSubfuenteFinanciamiento, "
				+ " pdn.id_tipo_nomina 					AS idTipoNomina, "
				+ " pdn.id_estatus_producto_nomina 		AS idEstatusProductoNomina, " 
				+ " pdn.id_banco                		AS idBanco, "
				+ " pdn.id_cuenta_bancaria          	AS idCuentaBancaria, "
				+ " pdn.numero_inicio_cheques           AS numeroInicioCheques, "
				+ " pdn.id_usuario 						AS idUsuario, "
				+ " pdn.ejercicio_fiscal 				AS ejercicioFiscal, " 
				+ " pdn.numero_periodo 					AS numeroPeriodo, "
				+ " pdn.inicio_periodo 					AS inicioPeriodo, " 
				+ " pdn.fin_periodo 					AS finPeriodo, "
				+ " pdn.inicio_rango_faltas 			AS inicioRangoFaltas, " 
				+ " pdn.fin_rango_faltas 				AS finRangoFaltas, "
				+ " pdn.fecha_pago 						AS fechaPago, " 
				+ " pdn.id_tipo_contratacion 			AS idTipoContratacion, "
				+ " tn.descripcion 						AS tipoNomina, "
				+ " spn.estatus 						AS estatusProductoNomina, "
				+ " tc.tipo_contratacion 				AS tipoContratacion, "
				+ " tc.area_responsable 				AS idArea, "
				+ " tp.tipo_periodo 					AS tipoPeriodo,"
				+ " pdn.dias_prima_vacasional           AS diasPrimaVacasional, "
				+ " pdn.dias_aguinaldo                  AS diasAguinaldo, "
				+ " pdn.dias_exento_prima_vacasional    AS diasExentoPrimaVacasional, "
				+ " pdn.dias_exento_aguinaldo           AS diasExentoAguinaldo "
				+ " FROM productos_nomina 				AS pdn "
				+ " INNER JOIN tipos_nominas 			AS tn "
				+ "  ON tn.id_tipo_nomina = pdn.id_tipo_nomina "
				+ " INNER JOIN estatus_productos_nomina AS spn "
				+ "  ON spn.id_estatus_producto_nomina = pdn.id_estatus_producto_nomina "
				+ " INNER JOIN tipos_contratacion 		AS tc "
				+ "  ON tc.id_tipo_contratacion = pdn.id_tipo_contratacion "
				+ " INNER JOIN tipos_periodos 			AS tp "
				+ "  ON tp.id_tipo_periodo = pdn.id_tipo_periodo "
				+ " WHERE "
				+ " pdn.id_producto_nomina = :idProductoNomina ")
				.setParameter("idProductoNomina", idProductoNomina);
		query.setResultTransformer(Transformers.aliasToBean(ProductoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<ProductoNominaDTO> result = (List<ProductoNominaDTO>) query.list();
		return result.isEmpty() ? null : result.get(0);
	}

	public void actualizarProductoNomina(ProductoNominaDTO dto) {
		try {
			ProductoNominaEntity entity = productoNominaRepository.obtenerPorId(dto.getIdProductoNomina());

			entity.setNombreProducto(dto.getNombreProducto());
			entity.setTipoContratacion(tipoContratacionRepository.obtenerPorId(dto.getIdTipoContratacion()));
			if (dto.getIdSubfuenteFinanciamiento() != null && dto.getIdSubfuenteFinanciamiento() > 0) {
				entity.setCambiarSubfuenteFinanciamiento(dto.getCambiarFuenteFinanciamiento());
				entity.setSubfuenteFinanciamiento(
						subfuenteFinanciamientoTempRepository.obtenerPorId(dto.getIdSubfuenteFinanciamiento()));
			}
			if (dto.getIdFuenteFinanciamiento() != null && dto.getIdFuenteFinanciamiento() > 0) {
				entity.setFuenteFinanciamiento(fuenteFinanciamientoDAO.obtenerPorId(dto.getIdFuenteFinanciamiento()));
			}
			entity.setNumeroInicioCheques(dto.getNumeroInicioCheques());
			if (dto.getIdBanco() != null && dto.getIdBanco() > 0) {
				entity.setBanco(bancoSatRepository.obtenerPorId(dto.getIdBanco()));
			}
			if (dto.getIdCuentaBancaria() != null && dto.getIdCuentaBancaria() > 0) {
				entity.setCuentaBancaria(cuentasBancariasRepository.obtenerPorId(dto.getIdCuentaBancaria()));
			}

			entity.setEjercicioFiscal(
					ejercicioFiscalRepository.obtenerEjercioPorEjercicionFiscal(dto.getEjercicioFiscal(), dto.getIdTipoPeriodo()));
			entity.setAnyoEjercicioFiscal(dto.getEjercicioFiscal());
			entity.setTipoPeriodo(tipoPeriodoRepository.obtenerPorId(dto.getIdTipoPeriodo()));
			entity.setPeriodoCalendario(periodoCalendariosRepository.obtenerPorId(dto.getIdPeriodoCalendario()));
			entity.setNumeroPeriodo(dto.getNumeroPeriodo());
			entity.setInicioPeriodo(dto.getInicioPeriodo());
			entity.setFinPeriodo(dto.getFinPeriodo());
			entity.setInicioRangoFaltas(dto.getInicioPeriodo());
			entity.setFinRangoFaltas(dto.getFinPeriodo());

			entity.setEstatusProductoNomina(
					estatusProductoNominaRepository.obtenerPorId(dto.getIdEstatusProductoNomina()));
			entity.setTipoNomina(tipoNominaRepository.obtenerPorId(dto.getIdTipoNomina()));
			entity.setFechaPago(dto.getFechaPago());
			entity.setUsuario(usuarioRepository.obtenerPorId(dto.getIdUsuario()));
			
			entity.setDiasAguinaldo(dto.getDiasAguinaldo());
			entity.setDiasExentoAguinaldo(dto.getDiasExentoAguinaldo());
			entity.setDiasExentoPrimaVacasional(dto.getDiasExentoPrimaVacasional());
			entity.setDiasPrimaVacasional(dto.getDiasPrimaVacasional());
			productoNominaRepository.actualizar(entity);
		} catch (PersistenceException px) {
			px.printStackTrace();
		}
	}

	public void abrirProductoNomina(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("CALL usp_generar_estructura_nomina(:idProductoNomina) ")
				.setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
		query.setResultTransformer(Transformers.aliasToBean(ProductoNominaListaDTO.class));
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void abrirProductoNominaSuplencia(Integer idProductoNomina, QuincenasSuplenciasEntity q) {

		EstatusNominasEmpleadoEntity estatusNomina = estatusNominaRepository.obtenerPorId(1);
		ProductoNominaEntity productoNomina = productoNominaRepository.obtenerPorId(idProductoNomina);
		TipoContratacionEntity tipoContratacion = tipoContratacionRepository.obtenerPorId(EnumTipoContratacion.SUPLENCIA);
		TiposNombramientosEntity tipoNombramiento = tiposNombramientoRepository.nombramientoPorId(EnumTipoNombramiento.EVENTUALES);

		NominaEmpleadoEntity nomina = new NominaEmpleadoEntity();
		nomina.setFinPeriodo(productoNomina.getFinPeriodo());
		nomina.setIdCentroResponsabilidad(q.getSuplente().getCentroResponsabilidad());
		nomina.setIdDependencia(q.getDependencia());
		nomina.setIdEmpleado(q.getSuplente().getEmpleado());
		nomina.setIdEstatusNominaEmpleado(estatusNomina);
		nomina.setIdFuncion(q.getSuplente().getFuncion());
		nomina.setIdMetodoPago(q.getSuplente().getMetodoPago());
		nomina.setIdProductoNomina(productoNomina);
		nomina.setIdProyecto(q.getProyecto());
		nomina.setIdPuestoGeneral(q.getSuplente().getPuesto());
		nomina.setIdTipoContratacion(tipoContratacion);
		nomina.setIdTipoNombramiento(tipoNombramiento);
		nomina.setIdUnidadResponsable(q.getUnidadResponsable());
		nomina.setInicioPeriodo(productoNomina.getInicioPeriodo());
		if (q.getSuplente().getMetodoPago() == 1) {
			nomina.setNumeroCuenta(q.getSuplente().getNumero());
		} else {
			nomina.setNumeroCuenta(q.getSuplente().getNumero());
		}
		nomina.setNumeroDiasPagados(q.getTotalDias());
		nomina.setNumeroIdLaboral(q.getSuplente().getNumeroLaboral());
		nomina.setNumeroIdPersonal(q.getSuplente().getEmpleado().getNumeroEmpleado());
		nomina.setSueldo(q.getTotal());
		nomina.setMotivo(" ");
		nomina.setCalculado(false);
		nominaEmpleadoRepository.crear(nomina);

		q.setIdNomina(nomina.getIdNominaEmpleado());
		quincenasSuplenciasRepository.actualizar(q);
//		procesoRepository.actualizar(proceso);

	}

	public List<EstatusProductoNominaDTO> obtenerEstatusProductoNominaLista() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(" SELECT " + " id_estatus_producto_nomina AS idEstatusConceptoNomina, "
				+ " estatus " + " FROM estatus_productos_nomina ");
		query.setResultTransformer(Transformers.aliasToBean(EstatusProductoNominaDTO.class));
		@SuppressWarnings("unchecked")
		List<EstatusProductoNominaDTO> result = (List<EstatusProductoNominaDTO>) query.list();
		return result;
	}

	public List<TipoContratacionDTO> listaTipoContratacionListPorArea(Integer tipoArea) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				" SELECT " + " id_tipo_contratacion AS idTipoContratacion, " + " tipo_contratacion AS tipoContratacion "
						+ " FROM tipos_contratacion " + " WHERE " + " area_responsable = :tipoArea ")
				.setParameter("tipoArea", tipoArea);
		query.setResultTransformer(Transformers.aliasToBean(TipoContratacionDTO.class));
		@SuppressWarnings("unchecked")
		List<TipoContratacionDTO> result = (List<TipoContratacionDTO>) query.list();
		return result;
	}

    public void aplicarConsecutivosProductoNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_actualizar_consecutivo_nomina_empleado(:idProductoNomina) ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
        query.executeUpdate();
    }

    public List<ProductoNominaDTO> obtenerProductoNominaLista(List<NominaEmpleadoDTO> nominaEmpleadoLista) {
        Session session = entityManager.unwrap(Session.class);
        List<ProductoNominaDTO> result = new ArrayList<>();
        for (NominaEmpleadoDTO nominaEmpleado: nominaEmpleadoLista) {
            Query query = session.createSQLQuery(" SELECT " 
                    + " pdn.id_producto_nomina AS idProductoNomina, "
                    + " pdn.nombre_producto AS nombreProducto, " 
                    + " pdn.id_ejercicio_fiscal AS idEjercicioFiscal, "
                    + " pdn.id_tipo_periodo AS idTipoPeriodo, " 
                    + " pdn.id_periodo_calendario AS idPeriodoCalendario, "
                    + " pdn.id_fuente_financiamiento AS idFuenteFinanciamiento, "
                    + " pdn.id_subfuente_financiamiento AS idSubfuenteFinanciamiento, "
                    + " pdn.id_tipo_nomina AS idTipoNomina, "
                    + " pdn.id_estatus_producto_nomina AS idEstatusProductoNomina, " 
                    + " pdn.id_usuario AS idUsuario, "
                    + " pdn.ejercicio_fiscal AS ejercicioFiscal, " 
                    + " pdn.numero_periodo AS numeroPeriodo, "
                    + " pdn.inicio_periodo AS inicioPeriodo, " 
                    + " pdn.fin_periodo AS finPeriodo, "
                    + " pdn.inicio_rango_faltas AS inicioRangoFaltas, " 
                    + " pdn.fin_rango_faltas AS finRangoFaltas, "
                    + " pdn.fecha_pago AS fechaPago, " 
                    + " pdn.id_tipo_contratacion AS idTipoContratacion "
                    + " FROM productos_nomina AS pdn " 
                    + " WHERE " 
                    + " pdn.id_producto_nomina = :idEmpleado ")
                    .setParameter("idEmpleado", nominaEmpleado.getIdProductoNomina());
            query.setResultTransformer(Transformers.aliasToBean(ProductoNominaDTO.class));
            @SuppressWarnings("unchecked")
            List<ProductoNominaDTO> resultTem = (List<ProductoNominaDTO>) query.list();
            ProductoNominaDTO productoNominaDTO = resultTem.get(0);
            productoNominaDTO.setIdNominaEmpleado(nominaEmpleado.getIdNominaEmpleado());
            result.add(productoNominaDTO);
        }
        return result;
    }

	public void validarProductoNomina(ProductoNominaDTO productoNomina) {
		ProductoNominaEntity entity = productoNominaRepository.obtenerPorId(productoNomina.getIdProductoNomina());
		EstatusProductoNominaEntity estatusProductoNominaEntity = estatusProductoNominaRepository.obtenerPorId(5);
		entity.setEstatusProductoNomina(estatusProductoNominaEntity);
		productoNominaRepository.actualizar(entity);
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void aplicarChequesProductoNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_actualizar_numero_cheque_nomina_empleado(:idProductoNomina,:numeroInicioCheques) ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina())
                .setParameter("numeroInicioCheques", productoNomina.getNumeroInicioCheques());
        query.executeUpdate();
    }

	public List<CuentaBancariaDTO> obtenerCuentaBancariaList(ProductoNominaDTO productoNomina) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(""
				+ " SELECT " 
				+ " id_cuenta_bancaria AS idCuentaBancaria, " 
				+ " banco AS banco, "
				+ " numero_cuenta AS numeroCuenta, "
				+ " descripcion AS descripcion, "
				+ " fuente_financiamiento AS fuenteFinanciamiento, "
				+ " ejercicio_fiscal AS ejercicioFiscal, "
				+ " clave_cuenta AS claveCuenta "
				+ " FROM cuentas_bancarias " );
		query.setResultTransformer(Transformers.aliasToBean(CuentaBancariaDTO.class));
		@SuppressWarnings("unchecked")
		List<CuentaBancariaDTO> result = (List<CuentaBancariaDTO>) query.list();
		return result;
	}

	public List<BancoDTO> obtenerBancoList() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(""
				+ " SELECT " 
				+ " id_banco_sat AS idBanco, " 
				+ " clave AS clave, "
				+ " nombre_corto AS nombreCorto, "
				+ " razon_social AS razonSocial "
				+ " FROM bancos_sat ");
		query.setResultTransformer(Transformers.aliasToBean(BancoDTO.class));
		@SuppressWarnings("unchecked")
		List<BancoDTO> result = (List<BancoDTO>) query.list();
		return result;
	}

	public void eliminarProductoNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery(" SELECT " 
                		+ " ne.id_nomina_empleado AS idNominaEmpleado "
                		+ " FROM nomina_empleado AS ne "
                        + " WHERE "
                        + " ne.id_producto_nomina = :idProductoNomina ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
        query.setResultTransformer(Transformers.aliasToBean(NominaEmpleadoDTO.class));

        @SuppressWarnings("unchecked")
        List<NominaEmpleadoDTO> nominaEmpleadoList = (List<NominaEmpleadoDTO>) query.list();

		for (NominaEmpleadoDTO nominaEmpleado : nominaEmpleadoList) {
			eliminarNominaEmpleado(nominaEmpleado.getIdNominaEmpleado());
		}
    	// producto_nomina
		productoNominaRepository.eliminarPorId(productoNomina.getIdProductoNomina());
	}

	public void eliminarNominaEmpleado(Integer idNominaEmpleado) {
        NominaEmpleadoEntity nominaEmpleadoEntity = nominaEmpleadoRepository.obtenerPorId(idNominaEmpleado);

    	// conceptos_nomina_empleado
		List<ConceptosNominaEmpleadosEntity> conceptosNominaEmpleadosEntities = conceptosNominaEmpleadosRepository
				.listaConceptosNominaPorIdNominaEmpleado(nominaEmpleadoEntity.getIdNominaEmpleado().intValue());
    	for (ConceptosNominaEmpleadosEntity conceptosNominaEmpleadosEntity : conceptosNominaEmpleadosEntities) {
    		conceptosNominaEmpleadosRepository.eliminar(conceptosNominaEmpleadosEntity);
    	}

    	// nomina_faltas_contadas
//    	fataContadaRepository.
    	// Movimientos

    	// Nominas pension

    	// bitacoras_aperturas

    	// bitacoras_eventos

    	// nomina_empleado
    	nominaEmpleadoRepository.eliminar(nominaEmpleadoEntity);
	}
}