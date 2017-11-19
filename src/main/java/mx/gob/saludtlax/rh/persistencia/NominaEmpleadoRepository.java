
package mx.gob.saludtlax.rh.persistencia;

import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.nomina.productosnomina.NominaAcumuladoDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaErroneaDTO;

/**
 *
 * @author José Pablo
 *
 */

public class NominaEmpleadoRepository
        extends GenericRepository<NominaEmpleadoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -2542728805799476316L;

    public Integer obtenerEstatusPorIdProductoNomina(Integer idProductoNomina) {

        NominaEmpleadoEntity nominaEmpleadoEntity = em
                .find(NominaEmpleadoEntity.class, idProductoNomina);

        return nominaEmpleadoEntity.getIdEstatusNominaEmpleado()
                .getIdEstatusNominaEmpleado();
    }

    public List<NominaEmpleadoEntity> listaNominaEmpleado() {
        List<NominaEmpleadoEntity> nomina_empleado = em
                .createQuery("select c from NominaEmpleadoEntity as c",
                        NominaEmpleadoEntity.class)
                .getResultList();
        return nomina_empleado;
    }

    public Integer obtenerNominaPorEmpleado(Integer empleado) {
        List<NominaEmpleadoEntity> list = new ArrayList<>();
        list = em
                .createQuery(
                        "Select c from NominaEmpleadoEntity as c "
                                + "where c.idEmpleado.idEmpleado=:idempleado "
                                + "and c.idEstatusNominaEmpleado=1",
                        NominaEmpleadoEntity.class)
                .setParameter("idempleado", empleado).getResultList();
        if (!list.isEmpty()) {
            return list.get(0).getIdNominaEmpleado();
        }
        return null;
    }

    /**
     * Consulta las nóminas con estatus, autorizado, pagado, timbrado, retenido,
     * cancelado, que no sean retroactivas
     */
    public List<NominaAcumuladoDTO> obtenerNominaAutorizadaEmpleado(
            Integer empleado, Integer numeroQuincena, Integer ejercicioFiscal) {

        return em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.nomina.productosnomina.NominaAcumuladoDTO(c.idNominaEmpleado, c.idProductoNomina.tipoNomina.idTipoNomina) "
                        + "from NominaEmpleadoEntity as c where c.idEmpleado.idEmpleado=:idempleado and c.idEstatusNominaEmpleado.idEstatusNominaEmpleado >=3 AND "
                        + "c.idProductoNomina.numeroPeriodo =:numeroPeriodo AND c.idProductoNomina.anyoEjercicioFiscal =:anyoEjercicioFiscal",
                NominaAcumuladoDTO.class).setParameter("idempleado", empleado)
                .setParameter("numeroPeriodo", numeroQuincena)
                .setParameter("anyoEjercicioFiscal", ejercicioFiscal)
                .getResultList();

    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoPorIdProductoNomina(
            Integer idProductoNomina) {
        return em.createQuery(
                "SELECT n FROM NominaEmpleadoEntity n WHERE n.idProductoNomina.idProductoNomina=:idProductoNomina and n.idComprobante IS NULL",
                NominaEmpleadoEntity.class)
                .setParameter("idProductoNomina", idProductoNomina)
                .getResultList();
    }

    public List<NominaErroneaDTO> consultarNominaConErrorEmpleado(
            Integer idProductoNomina) {
        return em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.nomina.productosnomina.NominaErroneaDTO(n.idNominaEmpleado, n.idEmpleado.rfc, n.idEmpleado.nombreCompleto, n.motivo) "
                        + "from NominaEmpleadoEntity as n where n.idProductoNomina.idProductoNomina =:idProductoNomina AND n.calculado = false",
                NominaErroneaDTO.class)
                .setParameter("idProductoNomina", idProductoNomina)
                .getResultList();

    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoPorSubfuente(
            ProductoNominaEntity productoNomina,
            SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento) {
        return em.createQuery(
                " FROM NominaEmpleadoEntity n                                      "
                        + " WHERE                                                  "
                        + " n.idSubfuenteFinanciamiento = :subfuenteFinanciamiento "
                        + " AND                                                    "
                        + " n.idProductoNomina = :productoNomina                   ",
                NominaEmpleadoEntity.class)
                .setParameter("subfuenteFinanciamiento",
                        subfuenteFinanciamiento)
                .setParameter("productoNomina", productoNomina).getResultList();
    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoSinSubfuente(
            ProductoNominaEntity productoNomina) {
        return em.createQuery(
                " FROM NominaEmpleadoEntity n                                      "
                        + " WHERE                                                  "
                        + " n.idSubfuenteFinanciamiento IS NULL "
                        + " AND                                                    "
                        + " n.idProductoNomina = :productoNomina                   ",
                NominaEmpleadoEntity.class)
                .setParameter("productoNomina", productoNomina).getResultList();
    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoPorPagoNominaEntity(
            PagoNominaEntity pagoNominaEntity) {
        return em.createQuery(
                " FROM NominaEmpleadoEntity n                                      "
                        + " WHERE                                                  "
                        + " n.pagoNomina = :pagoNominaEntity                       ",
                NominaEmpleadoEntity.class)
                .setParameter("pagoNominaEntity", pagoNominaEntity)
                .getResultList();
    }

    public List<NominaEmpleadoEntity> obtenerNominaEmpleadoSinPago(
            ProductoNominaEntity productoNomina) {
        return em.createQuery(
                " FROM NominaEmpleadoEntity n                                      "
                        + " WHERE                                                  "
                        + " n.pagoNomina IS NULL                                   "
                        + " AND                                                    "
                        + " n.idProductoNomina = :productoNomina                   ",
                NominaEmpleadoEntity.class)
                .setParameter("productoNomina", productoNomina).getResultList();
    }
}