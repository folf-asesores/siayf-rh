package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusNominasEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.NominaEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaRepository;
import mx.gob.saludtlax.rh.util.Configuracion;

@Stateless
public class ActualizarNominaEmpleadoService {
	@Inject private ProductoNominaRepository productoNominaRepository;
	@Inject private NominaEmpleadoRepository nominaRepository;
	@Inject private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
	@Inject private EstatusNominasEmpleadoRepository estatusNominasEmpleadoRepository;
	@Inject private InventarioVacanteRepository inventarioVacanteRepository;
    	@PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    public void actualizarNomina(ActualizarNominaEmpleadoDTO actualizarNominaEmpleado) {
		NominaEmpleadoEntity nominaEmpleadoEntity = nominaRepository
				.obtenerPorId(actualizarNominaEmpleado.getIdNominaempleado());
		nominaEmpleadoEntity = factoryNomina(actualizarNominaEmpleado, nominaEmpleadoEntity);
		nominaRepository.actualizar(nominaEmpleadoEntity);
	}

    private NominaEmpleadoEntity factoryNomina(ActualizarNominaEmpleadoDTO nominaEmpleadoDTO,
			NominaEmpleadoEntity nominaEmpleadoEntity) {
        if (nominaEmpleadoDTO.getIdConfiguracionPresupuestal() != null) {
            ConfiguracionPresupuestoEntity configuracionPresupuestal = configuracionPresupuestoRepository
                    .obtenerPorId(nominaEmpleadoDTO.getIdConfiguracionPresupuestalValue());
            nominaEmpleadoEntity.setIdConfiguracionPresupuestal(configuracionPresupuestal);
            nominaEmpleadoEntity.setFechaAltaConfiguracion(configuracionPresupuestal.getFechaAltaConfiguracion());
            nominaEmpleadoEntity.setNumeroEmpleado(configuracionPresupuestal.getNumeroEmpleado());
            nominaEmpleadoEntity.setSueldo(configuracionPresupuestal.getSueldo01());
            nominaEmpleadoEntity.setSueldo(configuracionPresupuestal.getSueldo14());
            nominaEmpleadoEntity.setSueldo(configuracionPresupuestal.getSueldo());
            nominaEmpleadoEntity.setIdTabulador(configuracionPresupuestal.getTabulador());
            nominaEmpleadoEntity.setNumeroCuenta(nominaEmpleadoEntity.getNumeroCuenta());
            nominaEmpleadoEntity.setNumeroIdPersonal(nominaEmpleadoEntity.getNumeroIdPersonal());
            nominaEmpleadoEntity.setNumeroIdLaboral(nominaEmpleadoEntity.getNumeroIdLaboral());
            nominaEmpleadoEntity.setIdFuncion(nominaEmpleadoEntity.getIdFuncion());
            nominaEmpleadoEntity.setIdProyecto(configuracionPresupuestal.getProyecto());
    		nominaEmpleadoEntity.setIdCentroResponsabilidad(configuracionPresupuestal.getCentroResponsabilidad());
    		nominaEmpleadoEntity.setIdDependencia(configuracionPresupuestal.getDependencia());
    		nominaEmpleadoEntity.setIdProyecto(configuracionPresupuestal.getProyecto());
    		nominaEmpleadoEntity.setIdUnidadResponsable(configuracionPresupuestal.getUnidadResponsable());
    		nominaEmpleadoEntity.setIdTipoContratacion(configuracionPresupuestal.getTipoContratacion());
    		nominaEmpleadoEntity.setIdTipoNombramiento(configuracionPresupuestal.getNombramiento());
    		nominaEmpleadoEntity.setIdPuestoGeneral(configuracionPresupuestal.getPuesto());
    		nominaEmpleadoEntity.setIdFuenteFinanciamiento(configuracionPresupuestal.getFuenteFinanciamiento());
    		nominaEmpleadoEntity.setIdSubfuenteFinanciamiento(configuracionPresupuestal.getSubfuenteFinanciamiento());
    		nominaEmpleadoEntity.setIdTipoRecurso(configuracionPresupuestal.getTipoRecurso());
    		nominaEmpleadoEntity.setIdEmpleado(configuracionPresupuestal.getEmpleado());
    		nominaEmpleadoEntity.setNumeroCuenta(configuracionPresupuestal.getEmpleado().getNumeroCuenta());
    		nominaEmpleadoEntity.setIdMetodoPago(configuracionPresupuestal.getEmpleado().getIdMetodoPago());
    		EstatusNominasEmpleadoEntity estatusNominaEmpleado = estatusNominasEmpleadoRepository.obtenerPorId(1);
    		nominaEmpleadoEntity.setIdEstatusNominaEmpleado(estatusNominaEmpleado);
    		InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
    				.obtenerInventarioVacantePorConfiguracionPresupuesto(configuracionPresupuestal);
    		nominaEmpleadoEntity.setIdFuncion(inventarioVacanteEntity.getFuncion());
    		nominaEmpleadoEntity.setPrograma(inventarioVacanteEntity.getPrograma());
    		nominaEmpleadoEntity.setNumeroIdPersonal(configuracionPresupuestal.getEmpleado().getNumeroEmpleado());
    		nominaEmpleadoEntity.setNumeroIdLaboral(configuracionPresupuestal.getNumeroEmpleado());
		}
		return nominaEmpleadoEntity;
	}

	public List<ActualizarNominaEmpleadoDTO> obtenerActualizarNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_cambios_producto_nomina(:idProductoNomina) ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
        query.setResultTransformer(Transformers.aliasToBean(ActualizarNominaEmpleadoDTO.class));
        @SuppressWarnings("unchecked")
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = (List<ActualizarNominaEmpleadoDTO>) query.list();
        return actualizarNominaEmpleadoList;
    }

	public List<ActualizarNominaEmpleadoDTO> obtenerAltasBajasNomina(ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery("CALL usp_altas_bajas_producto_nomina(:idProductoNomina) ")
                .setParameter("idProductoNomina", productoNomina.getIdProductoNomina());
        query.setResultTransformer(Transformers.aliasToBean(AltaBajaNominaEmpleadoDTO.class));
        @SuppressWarnings("unchecked")
        List<AltaBajaNominaEmpleadoDTO> altaBajaNominaEmpleadoList = (List<AltaBajaNominaEmpleadoDTO>) query.list();
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = new ArrayList<>();
        for (AltaBajaNominaEmpleadoDTO altaBajaNominaEmpleadoDTO : altaBajaNominaEmpleadoList) {
        	ActualizarNominaEmpleadoDTO actualizarNominaEmpleadoDTO = new ActualizarNominaEmpleadoDTO();
        	actualizarNominaEmpleadoDTO.setIdNominaempleado(altaBajaNominaEmpleadoDTO.getIdNominaempleado());
        	actualizarNominaEmpleadoDTO.setIdConfiguracionPresupuestalValue(altaBajaNominaEmpleadoDTO.getIdConfiguracionPresupuestalValue());
        	actualizarNominaEmpleadoDTO.setRfc(altaBajaNominaEmpleadoDTO.getRfc());
        	actualizarNominaEmpleadoDTO.setNombre(altaBajaNominaEmpleadoDTO.getNombre());
        	actualizarNominaEmpleadoDTO.setTipoCambio(altaBajaNominaEmpleadoDTO.getTipoCambio());
        	actualizarNominaEmpleadoList.add(actualizarNominaEmpleadoDTO);
        }
        return actualizarNominaEmpleadoList;
	}

	public void agregarNominaEmpleado(ActualizarNominaEmpleadoDTO actualizarNominaEmpleado, ProductoNominaDTO productoNomina) {
		NominaEmpleadoEntity nominaEmpleadoEntity = new NominaEmpleadoEntity();
		nominaEmpleadoEntity = factoryNomina(actualizarNominaEmpleado, nominaEmpleadoEntity);
		ProductoNominaEntity entity = productoNominaRepository.obtenerPorId(productoNomina.getIdProductoNomina());
		nominaEmpleadoEntity.setIdProductoNomina(entity);
		nominaEmpleadoEntity.setInicioPeriodo(entity.getInicioPeriodo());
		nominaEmpleadoEntity.setFinPeriodo(entity.getFinPeriodo());
		nominaEmpleadoEntity.setFechaPago(entity.getFechaPago());
		nominaRepository.crear(nominaEmpleadoEntity);
	}
}