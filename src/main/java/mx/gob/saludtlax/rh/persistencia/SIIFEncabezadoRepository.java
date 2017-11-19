/*
 * SIIFEncabezadoRepository.java
 * Creado el Jul 5, 2016 5:52:05 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFEncabezadoRepository
        extends GenericRepository<SIIFEncabezadoEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = 6673075234083646187L;

    private static final String CONSULTAR_ENCABEZADO = "select encabezado"
            + " from SIIFEncabezadoEntity as encabezado"
            + "	 inner join encabezado.bitacora as b"
            + " where b.periodo = :periodo and b.anio= :anyo";

    private static final String OBTENER_ULTIMO_ID_NOMINA = "select max(e.idNomina)"
            + " from SIIFEncabezadoEntity e";

    public Integer obtenerUltimoIdNomina() {
        TypedQuery<Integer> query = em.createQuery(OBTENER_ULTIMO_ID_NOMINA,
                Integer.class);
        Integer idNomina = query.getSingleResult();
        idNomina = idNomina == null ? 139 : idNomina + 1;

        return idNomina;
    }

    @Override
    public SIIFEncabezadoEntity crear(SIIFEncabezadoEntity entity) {
        entity.setIdNomina(obtenerUltimoIdNomina());
        em.persist(entity);
        return entity;
    }

    @Override
    public SIIFEncabezadoEntity actualizar(SIIFEncabezadoEntity entity) {
        if ((entity.getIdNomina() == null || entity.getIdNomina() == 0)
                && entity.getPercepciones().intValue() > 0
                && entity.getNeto().intValue() > 0) {
            entity.setIdNomina(obtenerUltimoIdNomina());
        }
        em.merge(entity);
        return entity;
    }

    public List<SIIFEncabezadoEntity> consultarEncabezado(String periodo,
            Integer anyo) {
        //String anyoComoCadena = Integer.toString(anyo);
        TypedQuery<SIIFEncabezadoEntity> query = em
                .createQuery(CONSULTAR_ENCABEZADO, SIIFEncabezadoEntity.class);

        query.setParameter("periodo", periodo);
        query.setParameter("anyo", anyo);

        return query.getResultList();
    }

    public Integer obtenerCuentaBancaria(Integer idSIIFEncabezado) {
        try {
            Integer cb = em
                    .createQuery("SELECT cuentaBancaria.idCuentaBancaria "
                            + "FROM SIIFEncabezadoEntity "
                            + "WHERE id_siif_encabezado =:idSIIFEncabezado",
                            Integer.class)
                    .setParameter("idSIIFEncabezado", idSIIFEncabezado)
                    .getSingleResult();
            return cb;
        } catch (NoResultException exception) {
            return null;
        }
    }

}