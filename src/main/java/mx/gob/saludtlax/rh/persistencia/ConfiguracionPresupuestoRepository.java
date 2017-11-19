
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.puestosautorizados.InfoConfiguracionDTO;

public class ConfiguracionPresupuestoRepository extends GenericRepository<ConfiguracionPresupuestoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4315214209820375834L;

    public List<InfoConfiguracionDTO> configuracionesPorTipoContratacion(String tipoContratacion) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.confpresupuesto.InfoConfiguracionDTO(c.id, c.empleado.rfc, c.empleado.nombreCompleto, c.numeroEmpleado, c.tipoContratacion, c.nombramiento.descripcion, c.fuenteFinanciamiento.descripcion, c.subfuenteFinanciamiento.descripcion, c.programa.programa, c.fechaInicio, c.fecha_fin) FROM"
                + " ConfiguracionPresupuestoEntity AS c WHERE c.tipoContratacion =:tipoContratacion "
                + "ORDER BY  c.fuenteFinanciamiento.idFuenteFinanciamiento, c.subfuenteFinanciamiento.idSubfuenteFinanciamiento, c.programa.idPrograma  ASC";

        List<InfoConfiguracionDTO> resultado = em.createQuery(consulta, InfoConfiguracionDTO.class).setParameter("tipoContratacion", tipoContratacion)
                .getResultList();
        return resultado;
    }

    public List<InfoConfiguracionDTO> configuracionesPorIdEmpleado(Integer idEmpleado) {

        ConfiguracionPresupuestoEntity config = new ConfiguracionPresupuestoEntity();
        config = em
                .createQuery("Select c FROM ConfiguracionPresupuestoEntity as c WHERE c.empleado.idEmpleado =:idEmpleado", ConfiguracionPresupuestoEntity.class)
                .setParameter("idEmpleado", idEmpleado).getSingleResult();

        List<InfoConfiguracionDTO> resultado = new ArrayList<>();
        InfoConfiguracionDTO dto = new InfoConfiguracionDTO();
        dto.setIdConfiguracionPresupuesto(config.getId());
        resultado.add(dto);
        return resultado;
    }

    /**
     * Obtiene el tipo de contratación de la configuracion.
     */
    public String obtenerTipoContratacionConfiguracion(Integer idConfiguracion) {
        try {
            return em.createQuery("SELECT c.tipoContratacion.tipoContratacion FROM ConfiguracionPresupuestoEntity AS c WHERE c.id =:idConfiguracion",
                    String.class).setParameter("idConfiguracion", idConfiguracion).getSingleResult();
        } catch (NoResultException exception) {
            throw new ValidacionException("No se encontró dato laboral con el id" + idConfiguracion, ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
    }

    public boolean numeroLaboralEnUso(Integer numeroLaboral) {
        try {
            em.createQuery("SELECT c.id FROM ConfiguracionPresupuestoEntity AS c WHERE c.numeroEmpleado =:numeroLaboral", Integer.class)
                    .setParameter("numeroLaboral", numeroLaboral).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            return true;
        }
    }

    public String obtenerNombramientoPorIdEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery("SELECT c.nombramiento.descripcion FROM ConfiguracionPresupuestoEntity AS c WHERE c.empleado.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado).getSingleResult();
        } catch (NoResultException exception) {
            throw new ValidacionException("No se encontró dato nombramiento con el idEmpleado" + idEmpleado, ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
    }

    public Integer existeConfiguracionConfuente(Integer idFuente) {
        try {
            return em.createQuery("SELECT MAX (c.id) FROM ConfiguracionPresupuestoEntity AS c WHERE c.fuenteFinanciamiento.idFuenteFinanciamiento =:idFuente",
                    Integer.class).setParameter("idFuente", idFuente).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

}