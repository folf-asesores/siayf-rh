/*
 *  CorreoNotificacionRepository.java
 *  Creado el Jun 17, 2016 2:48:30 PM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class CorreoNotificacionRepository extends GenericRepository<CorreoNotificacionEntity, Integer> {

    private static final long serialVersionUID = 6305350527361247182L;
    private static final String EXISTE_CORREO = "SELECT" + " CASE count(correo.idCorreoNotificacion)" + " WHEN 0 THEN false" + " ELSE true" + " END"
            + " FROM CorreoNotificacionEntity AS correo" + " WHERE correo.correoElectronico = :correoElectronico";
    private static final String CONSULTAR_CORREOS = "SELECT correo.correoElectronico" + " FROM CorreoNotificacionEntity AS correo";

    public boolean existeCorreo(String correoElectronico) {
        return em.createQuery(EXISTE_CORREO, Boolean.class).setParameter("correoElectronico", correoElectronico).getSingleResult();
    }

    public List<String> consutarCorreos() {
        List<String> consulta = em.createQuery(CONSULTAR_CORREOS, String.class).getResultList();
        return consulta;
    }

}
