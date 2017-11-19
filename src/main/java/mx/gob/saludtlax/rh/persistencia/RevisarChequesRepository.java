
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.siif.revisarcheques.RevisarChequesDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

public class RevisarChequesRepository {

    private static final long serialVersionUID = -7822948480531250198L;

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    private static final String CONSULTAR_CHEQHES = " select numeroCuenta, rfc, numCheq, nombramientoDescripcion from cheques ";
    private static final String CONSULTAR_CHEQHES_P_A = " select numeroCuenta, rfc, " + "numCheq, nombramientoDescripcion from cheques where periodo=:periodo "
            + "and anio=:anio ";

    public List<RevisarChequesEntity> obtenerListaRevisarCheques(String periodo) {

        return entityManager.createQuery(CONSULTAR_CHEQHES, RevisarChequesEntity.class).setParameter("periodo", periodo)
                //                .setParameter("anyo", String.valueOf(anyo))
                .getResultList();
    }

    public List<RevisarChequesDTO> obtenerListaRevisarCheques() {
        Session session = entityManager.unwrap(Session.class);
        //Query query = entityManager.createQuery(CONSULTAR_CHEQHES, RevisarChequesEntity.class);
        SQLQuery query = session.createSQLQuery(CONSULTAR_CHEQHES);
        //query.addEntity(RevisarChequesEntity.class);
        query.setResultTransformer(Transformers.aliasToBean(RevisarChequesDTO.class));

        List<RevisarChequesDTO> listaRevisarChequesDTOs = query.list();

        return listaRevisarChequesDTOs;
    }

    public List<RevisarChequesDTO> obtenerListaRevisarCheques(String periodo, int anio) {
        Session session = entityManager.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session.createSQLQuery(CONSULTAR_CHEQHES_P_A).setParameter("periodo", periodo).setParameter("anio", String.valueOf(anio));
        query.setResultTransformer(Transformers.aliasToBean(RevisarChequesDTO.class));

        List<RevisarChequesDTO> listaRevisarChequesDTOs = query.list();

        return listaRevisarChequesDTOs;
    }

    public List<RevisarChequesDTO> obtenerListaRevisarChequesAvanzada() {
        Session session = entityManager.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery(CONSULTAR_CHEQHES);
        query.setResultTransformer(Transformers.aliasToBean(RevisarChequesDTO.class));

        List<RevisarChequesDTO> listaRevisarChequesDTOs = query.list();
        return listaRevisarChequesDTOs;
    }

}
