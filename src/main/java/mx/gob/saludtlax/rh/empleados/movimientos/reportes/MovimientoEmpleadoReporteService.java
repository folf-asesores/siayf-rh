/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos.reportes;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
@Stateless
public class MovimientoEmpleadoReporteService {

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    protected EntityManager em;

    public List<ComisionadoLicenciaExcelDTO> listaConsultaComisionadoLicenciaPorRangoFecha(
            Date fechaInicial, Date fechaFinal) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery(
                    "CALL usp_comisionado_licencia(:fechaInicial, :fechaFinal)")
                    .setParameter("fechaInicial", fechaInicial)
                    .setParameter("fechaFinal", fechaFinal);
            query.setResultTransformer(Transformers
                    .aliasToBean(ComisionadoLicenciaExcelDTO.class));
            @SuppressWarnings("unchecked")
            List<ComisionadoLicenciaExcelDTO> result = query.list();

            return result;
        } catch (NoResultException exception) {
            System.err.println("Error: no se encontraron resultados: "
                    + exception.getMessage());
            exception.printStackTrace();
        }
        return null;

    }

    public List<ConsentradoAltaBajaExcelDTO> listaConsultaConsentradoAltaBajaPorRangoFecha(
            Integer idTipoContratacion, Date fechaInicial, Date fechaFinal) {
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createSQLQuery(
                    "CALL usp_concentrado_altas_bajas(:idTipoContratacion, :fechaInicial, :fechaFinal)")
                    .setParameter("idTipoContratacion", idTipoContratacion)
                    .setParameter("fechaInicial", fechaInicial)
                    .setParameter("fechaFinal", fechaFinal);
            query.setResultTransformer(Transformers
                    .aliasToBean(ConsentradoAltaBajaExcelDTO.class));
            @SuppressWarnings("unchecked")
            List<ConsentradoAltaBajaExcelDTO> result = query.list();

            return result;

        } catch (NoResultException noResultException) {
            System.err.println("Error: no se encontraron resultados: "
                    + noResultException.getMessage());
            noResultException.printStackTrace();
        }
        return null;
    }

}
