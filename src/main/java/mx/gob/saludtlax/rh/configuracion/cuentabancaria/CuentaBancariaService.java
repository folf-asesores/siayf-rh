package mx.gob.saludtlax.rh.configuracion.cuentabancaria;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.CuentasBancariasEntity;
import mx.gob.saludtlax.rh.persistencia.CuentasBancariasRepository;
import mx.gob.saludtlax.rh.util.Configuracion;


@Stateless
public class CuentaBancariaService {
		@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
	private EntityManager entityManager;
	
	@Inject
	private CuentasBancariasRepository cuentaBancariaDAO;

	public List<CuentaBancariaDTO> listaCuentaBancaria() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_cuenta_bancaria AS idCuentaBancaria, "
				+ "banco AS banco, "
				+ "numero_cuenta AS numeroCuenta, "
				+ "descripcion AS descripcion, "
				+ "fuente_financiamiento AS fuenteFinanciamiento, "
				+ "ejercicio_fiscal AS ejercicioFiscal, "
				+ "clave_cuenta AS claveCuenta "
				+ "FROM cuentas_bancarias");
		query.setResultTransformer(Transformers.aliasToBean(CuentaBancariaDTO.class));
		@SuppressWarnings("unchecked")
		List<CuentaBancariaDTO> result = (List<CuentaBancariaDTO>) query.list();
		return result;
	}
	
	public CuentaBancariaDTO nuevoCuentaBancaria() {
		CuentaBancariaDTO  dto = new CuentaBancariaDTO ();
		dto.setClaveCuenta(null);
		dto.setDescripcion(null);
		dto.setBanco(null);
		dto.setNumeroCuenta(null);
		dto.setFuenteFinanciamiento(null);
		dto.setEjercicioFiscal(null);
		return dto;
	}
	
	public CuentaBancariaDTO crearCuentaBancaria(CuentaBancariaDTO dto) {
		CuentasBancariasEntity entity = new CuentasBancariasEntity();
		entity.setClaveCuenta(dto.getClaveCuenta());
		entity.setDescripcion(dto.getDescripcion());
		entity.setBanco(dto.getBanco());
		entity.setNumeroCuenta(dto.getNumeroCuenta());
		entity.setFuenteFinanciamiento(dto.getFuenteFinanciamiento());
		entity.setEjercicioFiscal(dto.getEjercicioFiscal());
		cuentaBancariaDAO.crear(entity);
		return obtenerCuentaBancariaPorId(entity.getIdCuentaBancaria());
	}
	
	public CuentaBancariaDTO obtenerCuentaBancariaPorId(Integer idCuentaBancaria) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(
				"SELECT id_cuenta_bancaria AS idCuentaBancaria, "
				+ "banco AS banco, "
				+ "numero_cuenta AS numeroCuenta, "
				+ "descripcion AS descripcion, "
				+ "fuente_financiamiento AS fuenteFinanciamiento, "
				+ "ejercicio_fiscal AS ejercicioFiscal, "
				+ "clave_cuenta AS claveCuenta "
				+ "FROM cuentas_bancarias WHERE id_cuenta_bancaria = :idCuentaBancaria")
				.setParameter("idCuentaBancaria", idCuentaBancaria);
		query.setResultTransformer(Transformers.aliasToBean(CuentaBancariaDTO.class));
		CuentaBancariaDTO result = (CuentaBancariaDTO) query.list().get(0);
		return result;
	}

	public CuentaBancariaDTO actualizarCuentaBancaria(CuentaBancariaDTO dto) {
		CuentasBancariasEntity entity = cuentaBancariaDAO.obtenerPorId(dto.getIdCuentaBancaria());
		entity.setClaveCuenta(dto.getClaveCuenta());
		entity.setDescripcion(dto.getDescripcion());
		entity.setBanco(dto.getBanco());
		entity.setNumeroCuenta(dto.getNumeroCuenta());
		entity.setFuenteFinanciamiento(dto.getFuenteFinanciamiento());
		entity.setEjercicioFiscal(dto.getEjercicioFiscal());
		cuentaBancariaDAO.crear(entity);
		return obtenerCuentaBancariaPorId(entity.getIdCuentaBancaria());
	}

	public void eliminarCuentaBancaria(CuentaBancariaDTO dto) {
		CuentasBancariasEntity entity = entityManager.find
				(CuentasBancariasEntity.class, dto.getIdCuentaBancaria());
		entityManager.remove(entity);
		}
	
}