
package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.siif.ConsultaDatosEncabezadoDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

public class DatosEncabezadoRepository {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    private static final String OBTENER_DATOS_ENCABEZADO = "call sp_obtener_datos_encabezado(:idEncabezado)";
    private static final String OBTENER_DATOS_ENCABEZADO_PROSPERA = "call sp_obtener_datos_encabezado_prospera(:idEncabezado)";
    private static final String OBTENER_DATOS_ENCABEZADO_CONTRATO = "call usp_obtener_datos_encabezado_contrato(:idEncabezado)";

    public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezado(int idEncabezado) {
        System.out.println("idEncabezado Federal:: " + idEncabezado);
        Query query = entityManager.createNativeQuery(OBTENER_DATOS_ENCABEZADO);
        query.setParameter("idEncabezado", idEncabezado);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        System.out.println("result:: " + result);
        System.out.println("result:: " + result.size());
        List<ConsultaDatosEncabezadoDTO> datosDatosEncabezado = new ArrayList<>();

        for (Object[] obj : result) {
            ConsultaDatosEncabezadoDTO dato = new ConsultaDatosEncabezadoDTO();
            dato.setRfc((String) obj[0]);
            dato.setNombre((String) obj[1]);
            dato.setIdNomina(((Integer) obj[2]));
            dato.setId_empleado_datos_laborales((Integer) obj[3]);
            dato.setNoChequeCuenta((String) obj[4]);
            dato.setUr((String) obj[5]);
            dato.setFn((String) obj[6]);
            dato.setCr((String) obj[7]);
            dato.setId_puesto((String) obj[8]);
            dato.setPercepciones((BigDecimal) obj[9]);
            dato.setDeducciones((BigDecimal) obj[10]);
            dato.setNeto((BigDecimal) obj[11]);
            dato.setId_empleado_datos_personales((Integer) obj[12]);
            datosDatosEncabezado.add(dato);
        }
        return datosDatosEncabezado;
    }

    public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoProspera(Integer idEncabezado) {
        System.out.println("idEncabezado:: " + idEncabezado);
        Query query = entityManager.createNativeQuery(OBTENER_DATOS_ENCABEZADO_PROSPERA);
        query.setParameter("idEncabezado", idEncabezado);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        System.out.println("result:: " + result);
        System.out.println("result:: " + result.size());
        List<ConsultaDatosEncabezadoDTO> datosDatosEncabezado = new ArrayList<>();

        for (Object[] obj : result) {
            ConsultaDatosEncabezadoDTO dato = new ConsultaDatosEncabezadoDTO();
            dato.setRfc((String) obj[0]);
            dato.setNombre((String) obj[1]);
            dato.setIdNomina(((Integer) obj[2]));
            dato.setId_empleado_datos_laborales((Integer) obj[3]);
            dato.setNoChequeCuenta((String) obj[4]);
            dato.setUr((String) obj[5]);
            dato.setFn((String) obj[6]);
            dato.setCr((String) obj[7]);
            dato.setId_puesto((String) obj[8]);
            dato.setPercepciones((BigDecimal) obj[9]);
            dato.setDeducciones((BigDecimal) obj[10]);
            dato.setNeto((BigDecimal) obj[11]);
            dato.setId_empleado_datos_personales((Integer) obj[12]);
            datosDatosEncabezado.add(dato);
        }
        return datosDatosEncabezado;
    }

    public ConsultaDatosEncabezadoDTO obtenerDatosEncabezadoPorId(Integer idDatoPersonal) {
        Session session = entityManager.unwrap(Session.class);
        SQLQuery query = (SQLQuery) session.createSQLQuery(OBTENER_DATOS_ENCABEZADO + " where id_empleado_datos_personales=:idDatoPersonal ")
                .setParameter("idDatoPersonal", idDatoPersonal);
        query.setResultTransformer(Transformers.aliasToBean(ConsultaDatosEncabezadoDTO.class));
        ConsultaDatosEncabezadoDTO result = (ConsultaDatosEncabezadoDTO) query.list().get(0);
        return result;
    }

    public List<ConsultaDatosEncabezadoDTO> obtenerListaDatosEncabezadoContrato(int idEncabezado) {
        System.out.println("idEncabezado contrato:: " + idEncabezado);
        Query query = entityManager.createNativeQuery(OBTENER_DATOS_ENCABEZADO_CONTRATO);
        query.setParameter("idEncabezado", idEncabezado);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        System.out.println("result:: " + result);
        System.out.println("result:: " + result.size());
        List<ConsultaDatosEncabezadoDTO> datosDatosEncabezado = new ArrayList<>();

        for (Object[] obj : result) {
            ConsultaDatosEncabezadoDTO dato = new ConsultaDatosEncabezadoDTO();
            dato.setRfc((String) obj[0]);
            dato.setNombre((String) obj[1]);
            dato.setIdNomina(((Integer) obj[2]));
            dato.setId_empleado_datos_laborales((Integer) obj[3]);
            dato.setNoChequeCuenta((String) obj[4]);
            dato.setUr((String) obj[5]);
            dato.setFn((String) obj[6]);
            dato.setCr((String) obj[7]);
            dato.setId_puesto((String) obj[8]);
            dato.setPercepciones((BigDecimal) obj[9]);
            dato.setDeducciones((BigDecimal) obj[10]);
            dato.setNeto((BigDecimal) obj[11]);
            dato.setId_empleado_datos_personales((Integer) obj[12]);
            datosDatosEncabezado.add(dato);
        }
        return datosDatosEncabezado;
    }

}
