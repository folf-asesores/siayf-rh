
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.util.Configuracion;

public class GenericRepository<T, K extends Serializable>
        implements Repository<T, K> {

    private static final long serialVersionUID = -8198863493714030745L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    protected EntityManager em;
    protected Class<T> classType;

    @SuppressWarnings("unchecked")
    public GenericRepository() {
        classType = (Class<T>) getParameterClass(getClass(), 0);
    }

    @Override
    public T crear(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T obtenerPorId(K key) {
        return em.find(classType, key);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Long obtenerNumeroRegistros() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> rt = cq.from(classType);
        cq.select(cb.count(rt));
        TypedQuery<T> query = em.createQuery(cq);

        return (Long) query.getSingleResult();
    }

    @Override
    public List<T> consultarTodos() {
        return consultarPaginado(true, 0, 0);
    }

    @Override
    public List<T> consultarPaginado(int cantidadResultados,
            int primerResultado) {
        return consultarPaginado(false, cantidadResultados, primerResultado);
    }

    private List<T> consultarPaginado(boolean todo, int cantidadResultados,
            int primerResultado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(classType);
        Root<T> rt = cq.from(classType);
        cq.select(rt);
        TypedQuery<T> query = em.createQuery(cq);

        if (!todo) {
            query.setMaxResults(cantidadResultados);
            query.setFirstResult(primerResultado);
        }

        return query.getResultList();
    }

    @Override
    public T actualizar(T entity) {
        return em.merge(entity);
    }

    @Override
    public void eliminarPorId(K key) {
        T entity = obtenerPorId(key);

        if (entity != null) {
            eliminar(entity);
        } else {
            throw new SistemaException(
                    "La eliminación no se ha realizado"
                            + " porque no exite un entidad con el ID indicado.",
                    SistemaCodigoError.IMPOSIBLE_ELIMINAR_OBJETO_INEXISTENTE);
        }
    }

    @Override
    public void eliminar(T entity) {
        em.remove(entity);
    }

    private static Class<?> getParameterClass(Class<?> clazz, int index) {
        return (Class<?>) (((ParameterizedType) clazz.getGenericSuperclass())
                .getActualTypeArguments()[index]);
    }
}
