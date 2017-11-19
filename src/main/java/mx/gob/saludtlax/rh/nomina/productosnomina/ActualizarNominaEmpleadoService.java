
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jboss.ejb3.annotation.TransactionTimeout;

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
    @Inject
    private ProductoNominaRepository productoNominaRepository;
    @Inject
    private NominaEmpleadoRepository nominaRepository;
    @Inject
    private ConfiguracionPresupuestoRepository configuracionPresupuestoRepository;
    @Inject
    private EstatusNominasEmpleadoRepository estatusNominasEmpleadoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;
    private static final Logger LOGGER = Logger
            .getLogger(ActualizarNominaEmpleadoService.class);

    public void actualizarNomina(
            ActualizarNominaEmpleadoDTO actualizarNominaEmpleado) {
        NominaEmpleadoEntity nominaEmpleadoEntity = nominaRepository
                .obtenerPorId(actualizarNominaEmpleado.getIdNominaempleado());
        nominaEmpleadoEntity = factoryNomina(actualizarNominaEmpleado,
                nominaEmpleadoEntity);
        nominaRepository.actualizar(nominaEmpleadoEntity);
    }

    @TransactionTimeout(value = 10, unit = TimeUnit.MINUTES)
    public void actualizarNomina(
            List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoListTem) {
        List<NominaEmpleadoToActualizarDTO> nominaEmpleadoList = new ArrayList<>();
        for (ActualizarNominaEmpleadoDTO actualizarNominaEmpleado : actualizarNominaEmpleadoListTem) {
            NominaEmpleadoEntity nominaEmpleadoEntity = nominaRepository
                    .obtenerPorId(
                            actualizarNominaEmpleado.getIdNominaempleado());
            NominaEmpleadoToActualizarDTO empleadoToActualizarDTO = new NominaEmpleadoToActualizarDTO(
                    nominaEmpleadoEntity, actualizarNominaEmpleado);
            nominaEmpleadoList.add(empleadoToActualizarDTO);

        }
        for (NominaEmpleadoToActualizarDTO empleadoToActualizar : nominaEmpleadoList) {
            NominaEmpleadoEntity nominaEmpleadoEntity = factoryNomina(
                    empleadoToActualizar.getActualizarNominaEmpleado(),
                    empleadoToActualizar.getNominaEmpleadoEntity());
            nominaRepository.actualizar(nominaEmpleadoEntity);
        }
    }

    private NominaEmpleadoEntity factoryNomina(
            ActualizarNominaEmpleadoDTO nominaEmpleadoDTO,
            NominaEmpleadoEntity nominaEmpleadoEntity) {
        if (nominaEmpleadoDTO.getIdConfiguracionPresupuestalValue() != null) {
            LOGGER.info("1. getIdConfiguracionPresupuestal():: "
                    + nominaEmpleadoDTO.getIdConfiguracionPresupuestalValue());
            ConfiguracionPresupuestoEntity configuracionPresupuestal = configuracionPresupuestoRepository
                    .obtenerPorId(nominaEmpleadoDTO
                            .getIdConfiguracionPresupuestalValue());
            nominaEmpleadoEntity
                    .setIdConfiguracionPresupuestal(configuracionPresupuestal);
            nominaEmpleadoEntity.setFechaAltaConfiguracion(
                    configuracionPresupuestal.getFechaAltaConfiguracion());
            nominaEmpleadoEntity.setNumeroEmpleado(
                    configuracionPresupuestal.getNumeroEmpleado());
            nominaEmpleadoEntity
                    .setSueldo(configuracionPresupuestal.getSueldo01());
            nominaEmpleadoEntity
                    .setSueldo(configuracionPresupuestal.getSueldo14());
            nominaEmpleadoEntity
                    .setSueldo(configuracionPresupuestal.getSueldo());
            nominaEmpleadoEntity
                    .setIdTabulador(configuracionPresupuestal.getTabulador());
            //            nominaEmpleadoEntity.setNumeroCuenta(nominaEmpleadoEntity.getNumeroCuenta());
            //            nominaEmpleadoEntity.setNumeroIdPersonal(nominaEmpleadoEntity.getNumeroIdPersonal());
            //            nominaEmpleadoEntity.setNumeroIdLaboral(nominaEmpleadoEntity.getNumeroIdLaboral());
            //            nominaEmpleadoEntity.setIdFuncion(nominaEmpleadoEntity.getIdFuncion());
            nominaEmpleadoEntity
                    .setIdProyecto(configuracionPresupuestal.getProyecto());
            nominaEmpleadoEntity.setIdCentroResponsabilidad(
                    configuracionPresupuestal.getCentroResponsabilidad());
            nominaEmpleadoEntity.setIdDependencia(
                    configuracionPresupuestal.getDependencia());
            nominaEmpleadoEntity
                    .setIdProyecto(configuracionPresupuestal.getProyecto());
            nominaEmpleadoEntity.setIdUnidadResponsable(
                    configuracionPresupuestal.getUnidadResponsable());
            nominaEmpleadoEntity.setIdTipoContratacion(
                    configuracionPresupuestal.getTipoContratacion());
            nominaEmpleadoEntity.setIdTipoNombramiento(
                    configuracionPresupuestal.getNombramiento());
            nominaEmpleadoEntity
                    .setIdPuestoGeneral(configuracionPresupuestal.getPuesto());
            nominaEmpleadoEntity.setIdFuenteFinanciamiento(
                    configuracionPresupuestal.getFuenteFinanciamiento());
            nominaEmpleadoEntity.setIdSubfuenteFinanciamiento(
                    configuracionPresupuestal.getSubfuenteFinanciamiento());
            nominaEmpleadoEntity.setIdTipoRecurso(
                    configuracionPresupuestal.getTipoRecurso());
            LOGGER.info("2. getEmpleado():: "
                    + configuracionPresupuestal.getEmpleado());

            nominaEmpleadoEntity
                    .setIdEmpleado(configuracionPresupuestal.getEmpleado());
            nominaEmpleadoEntity.setNumeroCuenta(
                    configuracionPresupuestal.getEmpleado().getNumeroCuenta());
            nominaEmpleadoEntity.setIdMetodoPago(
                    configuracionPresupuestal.getEmpleado().getIdMetodoPago());
            nominaEmpleadoEntity.setNumeroIdPersonal(configuracionPresupuestal
                    .getEmpleado().getNumeroEmpleado());
            EstatusNominasEmpleadoEntity estatusNominaEmpleado = estatusNominasEmpleadoRepository
                    .obtenerPorId(1);
            LOGGER.info("3. estatusNominaEmpleado:: " + estatusNominaEmpleado);
            nominaEmpleadoEntity
                    .setIdEstatusNominaEmpleado(estatusNominaEmpleado);
            InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
                    .obtenerInventarioVacantePorConfiguracionPresupuesto(
                            configuracionPresupuestal);
            LOGGER.info(
                    "4. inventarioVacanteEntity:: " + inventarioVacanteEntity);
            nominaEmpleadoEntity
                    .setIdFuncion(inventarioVacanteEntity.getFuncion());
            nominaEmpleadoEntity
                    .setPrograma(inventarioVacanteEntity.getPrograma());
            nominaEmpleadoEntity.setNumeroIdLaboral(
                    configuracionPresupuestal.getNumeroEmpleado());
        }
        return nominaEmpleadoEntity;
    }

    public List<ActualizarNominaEmpleadoDTO> obtenerActualizarNomina(
            ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session
                .createSQLQuery(
                        "CALL usp_cambios_producto_nomina(:idProductoNomina) ")
                .setParameter("idProductoNomina",
                        productoNomina.getIdProductoNomina());
        query.setResultTransformer(
                Transformers.aliasToBean(ActualizarNominaEmpleadoDTO.class));
        @SuppressWarnings("unchecked")
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = query
                .list();
        return actualizarNominaEmpleadoList;
    }

    public List<ActualizarNominaEmpleadoDTO> obtenerAltasBajasNomina(
            ProductoNominaDTO productoNomina) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(
                "CALL usp_altas_bajas_producto_nomina(:idProductoNomina) ")
                .setParameter("idProductoNomina",
                        productoNomina.getIdProductoNomina());
        query.setResultTransformer(
                Transformers.aliasToBean(AltaBajaNominaEmpleadoDTO.class));
        @SuppressWarnings("unchecked")
        List<AltaBajaNominaEmpleadoDTO> altaBajaNominaEmpleadoList = query
                .list();
        List<ActualizarNominaEmpleadoDTO> actualizarNominaEmpleadoList = new ArrayList<>();
        for (AltaBajaNominaEmpleadoDTO altaBajaNominaEmpleadoDTO : altaBajaNominaEmpleadoList) {
            ActualizarNominaEmpleadoDTO actualizarNominaEmpleadoDTO = new ActualizarNominaEmpleadoDTO();
            actualizarNominaEmpleadoDTO.setIdNominaempleado(
                    altaBajaNominaEmpleadoDTO.getIdNominaempleado());
            actualizarNominaEmpleadoDTO.setIdConfiguracionPresupuestalValue(
                    altaBajaNominaEmpleadoDTO
                            .getIdConfiguracionPresupuestalValue());
            actualizarNominaEmpleadoDTO
                    .setRfc(altaBajaNominaEmpleadoDTO.getRfc());
            actualizarNominaEmpleadoDTO
                    .setNombre(altaBajaNominaEmpleadoDTO.getNombre());
            actualizarNominaEmpleadoDTO
                    .setTipoCambio(altaBajaNominaEmpleadoDTO.getTipoCambio());
            actualizarNominaEmpleadoList.add(actualizarNominaEmpleadoDTO);
        }
        return actualizarNominaEmpleadoList;
    }

    public void agregarNominaEmpleado(
            ActualizarNominaEmpleadoDTO actualizarNominaEmpleado,
            ProductoNominaDTO productoNomina) {
        NominaEmpleadoEntity nominaEmpleadoEntity = new NominaEmpleadoEntity();
        nominaEmpleadoEntity = factoryNomina(actualizarNominaEmpleado,
                nominaEmpleadoEntity);
        ProductoNominaEntity entity = productoNominaRepository
                .obtenerPorId(productoNomina.getIdProductoNomina());
        nominaEmpleadoEntity.setIdProductoNomina(entity);
        nominaEmpleadoEntity.setInicioPeriodo(entity.getInicioPeriodo());
        nominaEmpleadoEntity.setFinPeriodo(entity.getFinPeriodo());
        nominaEmpleadoEntity.setFechaPago(entity.getFechaPago());
        nominaRepository.crear(nominaEmpleadoEntity);
    }

}