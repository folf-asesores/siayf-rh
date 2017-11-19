
package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author José Pablo
 *
 */

public class ConceptosNominaEmpleadosRepository
        extends GenericRepository<ConceptosNominaEmpleadosEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 5715469185820998052L;

    public List<ConceptosNominaEmpleadosEntity> listaConceptosNomina() {
        List<ConceptosNominaEmpleadosEntity> conceptos_nomina_empleados = em
                .createQuery(
                        "select c from ConceptosNominaEmpleadosEntity as c",
                        ConceptosNominaEmpleadosEntity.class)
                .getResultList();
        return conceptos_nomina_empleados;
    }

    public List<ConceptosNominaEmpleadosEntity> listaConceptosNominaPorIdNominaEmpleado(
            int idNominaEmpleado, int tipoConcepto) {
        List<ConceptosNominaEmpleadosEntity> conceptos_nomina_empleados = em
                .createQuery(
                        "select c from ConceptosNominaEmpleadosEntity as c WHERE c.nominaEmpleado.idNominaEmpleado=:idNominaEmpleado AND c.tipo=:tipoConcepto",
                        ConceptosNominaEmpleadosEntity.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado)
                .setParameter("tipoConcepto", tipoConcepto).getResultList();
        return conceptos_nomina_empleados;
    }

    public List<BigDecimal> obtenerImportesGravadosPorIdNomina(
            Integer idNominaEmpleado) {
        return em.createQuery(
                "SELECT c.importeGravado FROM ConceptosNominaEmpleadosEntity AS c WHERE c.tipo =1 AND"
                        + " c.nominaEmpleado.idNominaEmpleado =:idNominaEmpleado",
                BigDecimal.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado)
                .getResultList();
    }

    public List<BigDecimal> obtenerImportesISRPorIdNomina(
            Integer idNominaEmpleado) {
        return em.createQuery(
                "SELECT c.importeGravado FROM ConceptosNominaEmpleadosEntity AS c WHERE c.tipo =2 AND"
                        + " c.nominaEmpleado.idNominaEmpleado =:idNominaEmpleado AND c.tipoSat =:tipoSat",
                BigDecimal.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado)
                .setParameter("tipoSat", "002").getResultList();
    }

    public List<BigDecimal> obtenerImportesSubsidioPorIdNomina(
            Integer idNominaEmpleado) {
        return em.createQuery(
                "SELECT c.importeGravado FROM ConceptosNominaEmpleadosEntity AS c WHERE c.tipo =1 AND"
                        + " c.nominaEmpleado.idNominaEmpleado =:idNominaEmpleado AND c.tipoSat =:tipoSat",
                BigDecimal.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado)
                .setParameter("tipoSat", "017").getResultList();
    }

    // public MovimientosNominaEmpleadoDTO
    // consultarMovimientos(MovimientosNominaEmpleadoDTO movimientosNomina) {
    // Session session = entityManager.unwrap(Session.class);
    // Query query = session.createSQLQuery(" SELECT "
    // + " cm.id_movimiento_fijo AS idMovimientoFijo, "
    // + " cm.id_tercero_institucional AS terceroInstitucional, "
    // + " cm.id_empleado AS idEmpleado, "
    // + " cm.rfc AS rfc, "
    // + " cm.quincena_operacion AS quincenaOperacion, "
    // + " cm.anyo_operacion AS anyoOperacion, "
    // + " cm.importe_descontado AS importeDescuento, "
    // + " cm.quincena_inicial AS quincenaInicial, "
    // + " cm.anio_inicial AS anioInicial, "
    // + " cm.quincena_final AS quincenaFinal, "
    // + " cm.anio_final AS anioFinal, "
    // + " cm.fecha_registro AS fechaRegistro, "
    // + " cm.fecha_modificacion AS fechaModificacion, "
    // + " cm.folio_documento AS folioDocumento, "
    // + " cm.fecha_documento AS fechaDocumento, "
    // + " cm.id_tipo_movimiento AS idTipoMovimiento, "
    // + " cm.dias AS dias} "
    // + " FROM movimiento_empleado_nominas AS ne "
    // + " WHERE "
    // + " cm.id_empleado = :idEmpleado ")
    // .setParameter("idEmpleado", movimientosNomina.getIdEmpleado());
    // }

    public List<ConceptosNominaEmpleadosEntity> listaConceptosNominaPorIdNominaEmpleado(
            int idNominaEmpleado) {
        List<ConceptosNominaEmpleadosEntity> conceptos_nomina_empleados = em
                .createQuery(
                        "select c from ConceptosNominaEmpleadosEntity as c WHERE c.nominaEmpleado.idNominaEmpleado=:idNominaEmpleado ",
                        ConceptosNominaEmpleadosEntity.class)
                .setParameter("idNominaEmpleado", idNominaEmpleado)
                .getResultList();
        return conceptos_nomina_empleados;
    }

}