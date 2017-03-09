/*
 * DividirNominaService.java
 * Creado el 25/Dec/2016 9:15:47 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mx.gob.saludtlax.rh.persistencia.ProductoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import org.jboss.logging.Logger;

/**
 * Esta clase ayuda es un ayudate para la implementación del EJB 
 * {@link DividirNominaEJB}.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class DividirNominaService implements Serializable {

    private static final long serialVersionUID = -1668809482049301023L;
    private static final Logger LOGGER = Logger.getLogger(DividirNominaService.class.getName());
    private static final String OBTENER_PRODUCTO_NOMINA =
            "SELECT NEW mx.gob.saludtlax.rh.nomina.productosnomina.DividirNominaDTO("
            + " nominaEmpleado.idNominaEmpleado,"
            + " productoNomina.idProductoNomina,"
            + " productoNomina.nombreProducto,"
            + " nominaEmpleado.consecutivo,"
            + " empleado.idEmpleado,"
            + " empleado.rfc,"
            + " empleado.nombreCompleto,"
            + " unidadResponsable.idUnidadResponsable,"
            + " unidadResponsable.descripcion,"
            + " rama.idRamaPuesto,"
            + " rama.nombreRamaPuesto,"
            + " nominaEmpleado.percepciones,"
            + " nominaEmpleado.deducciones,"
            + " nominaEmpleado.neto)"
            + " FROM NominaEmpleadoEntity AS nominaEmpleado"
            + " JOIN nominaEmpleado.idProductoNomina AS productoNomina"
            + " JOIN nominaEmpleado.idEmpleado AS empleado"
            + " JOIN nominaEmpleado.idUnidadResponsable AS unidadResponsable"
            + " JOIN nominaEmpleado.idPuestoGeneral AS puesto"
            + " JOIN puesto.idRama AS rama"
            + " WHERE productoNomina.idProductoNomina = :idProductoNomina";
    private static final String OBTENER_ID_NOMINA_EMPLEADOS = 
            "SELECT nominaEmpleado.idNominaEmpleado"
            + " FROM NominaEmpleadoEntity AS nominaEmpleado"
            + " JOIN nominaEmpleado.idProductoNomina AS productoNomina"
            + " JOIN nominaEmpleado.idEmpleado AS empleado"
            + " JOIN nominaEmpleado.idUnidadResponsable AS unidadResponsable"
            + " JOIN nominaEmpleado.idPuestoGeneral AS puesto"
            + " JOIN puesto.idRama AS rama"
            + " WHERE productoNomina.idProductoNomina = :idProductoNomina"
            + " AND empleado.rfc IN :rfc"
            + " AND unidadResponsable.descripcion LIKE :unidadResponsable"
            + " AND rama.nombreRamaPuesto LIKE :rama";
    private static final String ACTUALIZAR_PRODUCTO_NOMINA = 
            "UPDATE nomina_empleado"
            + " INNER JOIN empleados "
            + " ON empleados.id_empleado = nomina_empleado.id_empleado"
            + " SET nomina_empleado.id_producto_nomina = :idProductoNominaNuevo"
            + " WHERE nomina_empleado.id_nomina_empleado > 0"
            + " AND nomina_empleado.id_producto_nomina = :idProductoNominaViejo"
            + " AND empleados.rfc IN :rfc";

    @PersistenceContext(name = "siayfrhPU")
    private EntityManager em;

    /**
     * Permite obtener las información páginada de un producto de nómina.
     * 
     * @param todo si se traeran todos los registros.
     * @param primerResultado la posición inicial de la consulta.
     * @param cantidadResultados la cantidad máxima de resultados que debe
     * devolver la consulta.
     * @param idProductoNomina el ID del producto de nómina a obtener.
     * @return una lista de con la información del producto de nómina.
     */
    protected List<DividirNominaDTO> obtenerProductoNomina(boolean todo, Integer primerResultado, Integer cantidadResultados, Integer idProductoNomina) {
        TypedQuery<DividirNominaDTO> query = em.createQuery(OBTENER_PRODUCTO_NOMINA, DividirNominaDTO.class);
        query.setParameter("idProductoNomina", idProductoNomina);
        if (!todo) {
            query.setFirstResult(primerResultado);
            query.setMaxResults(cantidadResultados);
        }
        return query.getResultList();
    }
    
    protected Long obtenerTotalProductosNomina(Integer idProductoNomina) {
        TypedQuery<Long> query = em.createQuery("SELECT count(nominaEmpleado.idProductoNomina.idProductoNomina) FROM NominaEmpleadoEntity AS nominaEmpleado WHERE nominaEmpleado.idProductoNomina.idProductoNomina = :idProductoNomina", Long.class);
        query.setParameter("idProductoNomina", idProductoNomina);
        return query.getSingleResult();
    }

    protected List<DividirNominaDTO> dividirProductoNomina(DividirNominaFiltro filtro, Integer idUsuario) {
        UsuarioEntity usuario = em.find(UsuarioEntity.class, idUsuario);
        ProductoNominaEntity productoNominaOrigen = em.find(ProductoNominaEntity.class, filtro.getIdProductoNomina());
        ProductoNominaEntity productoNominaDestino = new ProductoNominaEntity();

        productoNominaDestino.setIdProductoNomina(null);
        productoNominaDestino.setTipoPeriodo(productoNominaOrigen.getTipoPeriodo());
        productoNominaDestino.setPeriodoCalendario(productoNominaOrigen.getPeriodoCalendario());
        productoNominaDestino.setUsuario(usuario);
        productoNominaDestino.setEjercicioFiscal(productoNominaOrigen.getEjercicioFiscal());
        productoNominaDestino.setEstatusProductoNomina(productoNominaOrigen.getEstatusProductoNomina());
        productoNominaDestino.setBanco(productoNominaOrigen.getBanco());
        productoNominaDestino.setCuentaBancaria(productoNominaOrigen.getCuentaBancaria());
        productoNominaDestino.setTipoContratacion(productoNominaOrigen.getTipoContratacion());
        productoNominaDestino.setFuenteFinanciamiento(productoNominaOrigen.getFuenteFinanciamiento());
        productoNominaDestino.setSubfuenteFinanciamiento(productoNominaOrigen.getSubfuenteFinanciamiento());
        productoNominaDestino.setTipoNomina(productoNominaOrigen.getTipoNomina());
        productoNominaDestino.setTipoRecursoSat(productoNominaOrigen.getTipoRecursoSat());
        productoNominaDestino.setCambiarSubfuenteFinanciamiento(productoNominaOrigen.getCambiarSubfuenteFinanciamiento());
        productoNominaDestino.setAnyoEjercicioFiscal(productoNominaOrigen.getAnyoEjercicioFiscal());
        productoNominaDestino.setInicioPeriodo(productoNominaOrigen.getInicioPeriodo());
        productoNominaDestino.setFinPeriodo(productoNominaOrigen.getFinPeriodo());
        productoNominaDestino.setNumeroPeriodo(productoNominaOrigen.getNumeroPeriodo());
        productoNominaDestino.setFechaPago(productoNominaOrigen.getFechaPago());
        productoNominaDestino.setNombreProducto(productoNominaOrigen.getNombreProducto());
        productoNominaDestino.setNumeroInicioCheques(productoNominaOrigen.getNumeroInicioCheques());
        productoNominaDestino.setTotalPercepciones(productoNominaOrigen.getTotalPercepciones());
        productoNominaDestino.setTotalDeducciones(productoNominaOrigen.getTotalDeducciones());
        productoNominaDestino.setTotalNeto(productoNominaOrigen.getTotalNeto());
        productoNominaDestino.setTotalSubsidio(productoNominaOrigen.getTotalSubsidio());
        productoNominaDestino.setTotalIsr(productoNominaOrigen.getTotalIsr());
        productoNominaDestino.setInicioRangoFaltas(productoNominaOrigen.getInicioRangoFaltas());
        productoNominaDestino.setDiasPrimaVacasional(productoNominaOrigen.getDiasPrimaVacasional());
        productoNominaDestino.setDiasAguinaldo(productoNominaOrigen.getDiasAguinaldo());
        productoNominaDestino.setDiasExentoPrimaVacasional(productoNominaOrigen.getDiasExentoPrimaVacasional());
        productoNominaDestino.setDiasExentoAguinaldo(productoNominaOrigen.getDiasExentoAguinaldo());
        
        em.persist(productoNominaDestino);
        Integer nuevoIdProductoNomina = productoNominaDestino.getIdProductoNomina();
        Query query = em.createNativeQuery(ACTUALIZAR_PRODUCTO_NOMINA);
        query.setParameter("idProductoNominaNuevo", nuevoIdProductoNomina);
        query.setParameter("idProductoNominaViejo", filtro.getIdProductoNomina());
        query.setParameter("rfc", filtro.getRfc());
        query.executeUpdate();
        
        TypedQuery<DividirNominaDTO> productoNominaQuery = em.createQuery(OBTENER_PRODUCTO_NOMINA, DividirNominaDTO.class);
        productoNominaQuery.setParameter("idProductoNomina", nuevoIdProductoNomina);

        return productoNominaQuery.getResultList();
    }

}
