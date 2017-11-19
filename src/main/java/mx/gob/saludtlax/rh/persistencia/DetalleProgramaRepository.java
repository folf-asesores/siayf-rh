/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 25/08/2016 20:44:02
 */
public class DetalleProgramaRepository
        extends GenericRepository<DetalleProgramaEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -1194517085732608984L;

    public List<DetalleProgramaEntity> consultarDetallesPrograma(
            Integer idPrograma) {
        List<DetalleProgramaEntity> programas = em.createQuery(
                "SELECT d FROM DetalleProgramaEntity AS d WHERE d.idPrograma =:idPrograma",
                DetalleProgramaEntity.class)
                .setParameter("idPrograma", idPrograma).getResultList();
        return programas;
    }

    public String obtenerDescripcionDetalle(Integer idDetalle) {
        try {
            return em.createQuery(
                    "SELECT d.descripcion FROM DetalleProgramaEntity AS d WHERE d.id =:idDetalle",
                    String.class).setParameter("idDetalle", idDetalle)
                    .getSingleResult();
        } catch (NoResultException exception) {
            throw new ValidacionException(
                    "No se encontró detalle con identificador " + idDetalle,
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
    }

}
