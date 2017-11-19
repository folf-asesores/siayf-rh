
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.siif.DatosPersonalesDTO;

public class DatosPersonalesRepository
        extends GenericRepository<DatosPersonalesEntity, Integer> {

    private static final long serialVersionUID = 506352013535349173L;

    private static final String CONSULTA_DATOS_PERSONALES = "select "
            + " id_dato_personal as idDatoPersonal, "
            + " id_empleado_datos_personales as idEmpleadoDatosPersonales, "
            + " rfc as rfc, " + " apellido_paterno as apellidoPaterno, "
            + " apellido_materno as apellidoMaterno, " + " nombre as nombre, "
            + " fecha_nacimiento as fechaNacimiento, " + " sexo as sexo, "
            + " id_localidad as idLocalidad, " + " id_colonia as idColonia, "
            + " calle as calle, " + " numero_exterior as numeroExterior, "
            + " numero_interior as numeroInterior, "
            + " codigo_postal as codigoPostal, " + " telefono as telefono, "
            + " id_estado_empleado as idEstadoEmpleado "
            + " from siif_datos_personales ";

    public DatosPersonalesEntity obtenerDatoPersonalPorRfc(String rfc) {
        try {
            return em.createQuery(
                    "SELECT d FROM DatosPersonalesEntity AS d WHERE d.rfc = :rfc",
                    DatosPersonalesEntity.class).setParameter("rfc", rfc.trim())
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            return null;
        }
    }

    public DatosPersonalesDTO obtenerDatoPersonal(String rfc) {
        DatosPersonalesDTO datoPersonal = new DatosPersonalesDTO();
        Session session = em.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session
                .createSQLQuery(CONSULTA_DATOS_PERSONALES + " where rfc=:rfc ")
                .setParameter("rfc", rfc);
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        return datoPersonal = (DatosPersonalesDTO) query.list().get(0);
    }

    public List<DatosPersonalesDTO> obtenerListaDatoPersonal() {
        Session session = em.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery(CONSULTA_DATOS_PERSONALES);
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        List<DatosPersonalesDTO> datoPersonal = query.list();
        return datoPersonal;
    }

    public List<DatosPersonalesDTO> listaDatosPersonales() {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery(CONSULTA_DATOS_PERSONALES);
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        @SuppressWarnings("unchecked")
        List<DatosPersonalesDTO> result = query.list();
        return result;
    }

    public List<DatosPersonalesDTO> obtenerlistaDatosPersonalesPorCriterio(
            String rfc) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session
                .createSQLQuery(
                        CONSULTA_DATOS_PERSONALES + " where rfc LIKE :rfc ")
                .setParameter("rfc", "%" + rfc + "%");
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        @SuppressWarnings("unchecked")
        List<DatosPersonalesDTO> result = query.list();
        return result;
    }

    public DatosPersonalesDTO obtenerDatosPersonalesPorId(
            Integer idDatoPersonal) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session
                .createSQLQuery(CONSULTA_DATOS_PERSONALES
                        + " where id_dato_personal=:idDatoPersonal ")
                .setParameter("idDatoPersonal", idDatoPersonal);
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        DatosPersonalesDTO result;
        if (query.list().size() > 0) {
            result = (DatosPersonalesDTO) query.list().get(0);
            return result;
        } else {
            return null;
        }
    }

    public int verificaDatosPersonalesPorId(Integer idDatoPersonal) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session
                .createSQLQuery(CONSULTA_DATOS_PERSONALES
                        + " where id_empleado_datos_personales=:idDatoPersonal ")
                .setParameter("idDatoPersonal", idDatoPersonal);
        query.setResultTransformer(
                Transformers.aliasToBean(DatosPersonalesDTO.class));
        DatosPersonalesDTO result;
        if (query.list().size() > 0) {
            result = (DatosPersonalesDTO) query.list().get(0);
            // System.out.println("ID PERSONAL ENCONTRADO:::" +
            // result.getIdEmpleadoDatosPersonales());
            return 1;
        } else {
            // System.out.println("ID PERSONAL NO FUE ENCONTRADO:::");
            return 0;
        }
    }

    public List<DatosPersonalesEntity> consultarDatosPersonalesNuevos() {
        return em.createQuery(
                "SELECT d FROM DatosPersonalesEntity AS d WHERE d.nuevoEmpleado = true",
                DatosPersonalesEntity.class).getResultList();
    }

}
