/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 20:59:39
 */
public class NombramientoRepository extends GenericRepository<NombramientoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2514200313262236117L;

    public List<InfoNombramientoDTO> obtenerListaInfoNombramiento() {
        String query = "SELECT new mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO(n.idNombramiento, n.clasificacionNombramiento.clasificacionNombramiento, n.empleado.nombreCompleto, n.clavePresupuestal, n.funcion, n.sueldo, n.domicilio, n.clasificacionNombramiento.idClasificacionNombramiento) FROM NombramientoEntity AS n";
        return em.createQuery(query, InfoNombramientoDTO.class).getResultList();
    }

    public List<InfoNombramientoDTO> obtenerListaInfoNombramientoPorTipo(Integer tipoNombramiento) {
        String query = "SELECT new mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO(n.idNombramiento, n.clasificacionNombramiento.clasificacionNombramiento, n.empleado.nombreCompleto, n.clavePresupuestal, n.funcion, n.sueldo, n.domicilio, n.clasificacionNombramiento.idClasificacionNombramiento) FROM NombramientoEntity AS n WHERE n.clasificacionNombramiento.idClasificacionNombramiento =:tipoNombramiento";
        return em.createQuery(query, InfoNombramientoDTO.class).setParameter("tipoNombramiento", tipoNombramiento).getResultList();
    }

    public Integer obtenerIdEmpleadoPorIdNombramiento(Integer idNombramiento) {
        try {
            return em.createQuery("SELECT n.empleado.idEmpleado FROM NombramientoEntity AS n WHERE n.idNombramiento =:idNombramiento", Integer.class)
                    .setParameter("idNombramiento", idNombramiento).getSingleResult();
        } catch (NullPointerException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

}
