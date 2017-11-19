
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public class PadronEventualEntity24Repository
        extends GenericRepository<PadronEventualEntity24, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 4747942611917193355L;

    public PadronEventualEntity24 obtenerSuplentePorRfc(String rfc) {
        try {
            return em.createQuery(
                    "SELECT p FROM PadronEventualEntity24 as p WHERE p.rfc =:rfc AND p.tipoContratacion =11",
                    PadronEventualEntity24.class).setParameter("rfc", rfc)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            System.out.println("duplicado" + rfc);
            return null;
        }
    }

    public List<PadronEventualEntity24> consultarPadronSuplentesNuevos() {
        return em.createQuery(
                "SELECT p FROM PadronEventualEntity24 AS p WHERE p.tipoContratacion = 11 AND p.activo =false",
                PadronEventualEntity24.class).getResultList();
    }

    public List<PadronEventualEntity24> consultarPadronContratosEstatales() {
        return em.createQuery(
                "SELECT p FROM PadronEventualEntity24 AS p WHERE p.tipoContratacion = 0 AND p.activo =false",
                PadronEventualEntity24.class).getResultList();
    }

    public List<PadronEventualEntity24> consultarBajas() {
        return em.createQuery(
                "SELECT p FROM PadronEventualEntity24 AS p WHERE p.tipoContratacion = 0 AND p.baja = true",
                PadronEventualEntity24.class).getResultList();
    }

    public List<PadronEventualEntity24> consultarAltas() {
        return em.createQuery(
                "SELECT p FROM PadronEventualEntity24 AS p WHERE p.tipoContratacion = 0 AND p.alta = true",
                PadronEventualEntity24.class).getResultList();

    }
}
