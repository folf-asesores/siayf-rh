/*
 *
 * EstrategiaRepository.java
 * Creado el Jul 12, 2016 10:13:23 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class EstrategiaRepository
        extends GenericRepository<EstrategiaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 6592101091691276264L;

    private static final Logger LOGGER = Logger
            .getLogger(EstrategiaRepository.class.getName());

    private static final String CONSULTAR_ESTREATEGIAS = "from EstrategiaEntity as estrategia";
    private static final String CONSULTAR_DESCRIPCION_ESTREATEGIAS_POR_CRITERIO = "select estrategia.estrategia"
            + "  from EstrategiaEntity as estrategia"
            + "  where estrategia.estrategia like :descripcion";
    private static final String CONSULTAR_ID_ESTREATEGIAS_POR_CRITERIO = "select estrategia.idEstrategia"
            + "  from EstrategiaEntity as estrategia"
            + "  where estrategia.estrategia = :descripcion";
    private static final String OBTENER_POR_CODIGO_ESTRATEGIA = "from EstrategiaEntity as estrategia"
            + " where estrategia.codigoEstrategia = :codigoEstrategia";
    private static final String EXISTE_CODIGO_ESTRATEGIA = "select"
            + "   case count(estrategia.codigoEstrategia)"
            + "      when 0 then false" + "      else true" + "   end"
            + " from EstrategiaEntity as estrategia"
            + "    where estrategia.codigoEstrategia = :codigoEstrategia";

    /**
     * Permite obtener una estrategia por medio del código de la estrategia.
     *
     * @param codigoEstrategia
     *            el código de la estrategia.
     * @return la estrategia asociada al código.
     */
    public EstrategiaEntity obtenerPorCodigoEstrategia(int codigoEstrategia) {
        TypedQuery<EstrategiaEntity> query = em
                .createQuery(OBTENER_POR_CODIGO_ESTRATEGIA, classType);
        EstrategiaEntity estrategia = query.getSingleResult();
        return estrategia;
    }

    /**
     * Permite saber si exite una estrategia registrada con un código de
     * estrategia.
     *
     * @param codigoEstrategia
     *            el código de la estrategia.
     * @return verdad si y sólo existe un código de estrategia registado.
     */
    public boolean existeCodigoEstrategia(int codigoEstrategia) {
        TypedQuery<Boolean> query = em.createQuery(EXISTE_CODIGO_ESTRATEGIA,
                Boolean.class);
        query.setParameter("codigoEstrategia", codigoEstrategia);

        return query.getSingleResult();
    }

    /**
     * Este método permite obtener todas la estrategias que están en el almacen
     * de datos.
     *
     * @return una lista con todas las estrategias.
     */
    public List<EstrategiaEntity> consultarEstrategias() {
        TypedQuery<EstrategiaEntity> query = em
                .createQuery(CONSULTAR_ESTREATEGIAS, classType);

        return query.getResultList();
    }

    /**
     * Permite la obtener una lista con las descripciones de las estrategias
     * que coninciden con el criterio de busqueda.
     *
     * @param criterio
     *            una frase que pueden contener los descripciones de las
     *            estrategias.
     * @return una lista con las descripciones que coincidieron con el criterio
     *         de busqueda.
     */
    public List<String> consultarDescripcionEstrategiaPorCriterio(
            String criterio) {
        StringBuilder sb = new StringBuilder();
        sb.append('%');
        sb.append(criterio);
        sb.append('%');

        TypedQuery<String> query = em.createQuery(
                CONSULTAR_DESCRIPCION_ESTREATEGIAS_POR_CRITERIO, String.class);
        query.setParameter("descripcion", sb.toString());
        query.setMaxResults(10);

        return query.getResultList();
    }

    /**
     * Permite obtener el ID de una estrategia por medio de su descripción, en
     * caso de que haya más de una coincidencia se retorna la primera. En caso
     * de no encuentrar ninguna conincidencia se devuelve nulo.
     *
     * @param descripcion
     *            la descripción de la estrategia.
     * @return el ID de la estrategia si se encuentra alguna coincidencia.
     */
    public Integer consultarIdEstrategiaPorDescripcion(String descripcion) {
        TypedQuery<Integer> query = em.createQuery(
                CONSULTAR_ID_ESTREATEGIAS_POR_CRITERIO, Integer.class);
        query.setParameter("descripcion", descripcion);
        List<Integer> resultList = query.getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            if (resultList.size() > 0) {
                LOGGER.warn("Se encontro más de una estrategia que tiene la "
                        + "misma descripción, se tomo la primera como valor por "
                        + "defecto.");
            }

            return resultList.get(0);
        } else {
            return null;
        }
    }
}
