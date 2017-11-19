
package mx.gob.saludtlax.rh.persistencia;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

public class TokenRepository extends GenericRepository<TokenEntity, Integer> {

    private static final long serialVersionUID = 72786152681962527L;
    private static final Logger LOGGER = Logger
            .getLogger(TokenRepository.class.getName());
    private static final String OBTENER_POR_TOKEN = "select token"
            + " from TokenEntity as token" + " where token.token = :hashToken";
    private static final String CONSULTA_CERRAR_SESION = "update TokenEntity as token"
            + " set token.activo = false" + " where token.token = :hashToken";

    public TokenEntity obtenerPorHashToken(String hashToken) {
        TypedQuery<TokenEntity> query = em.createQuery(OBTENER_POR_TOKEN,
                classType);
        query.setParameter("hashToken", hashToken);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.infov("No se encontro un token con HashToken: {0}",
                    hashToken);
            return null;
        } catch (NonUniqueResultException ex) {
            LOGGER.infov("Se encontro más de un token con HashToken: {0}",
                    hashToken);
            return null;
        }
    }

    public void cerrarSesion(String hashToken) {
        Query query = em.createQuery(CONSULTA_CERRAR_SESION);
        query.setParameter("hashToken", hashToken);
        query.executeUpdate();
    }

}