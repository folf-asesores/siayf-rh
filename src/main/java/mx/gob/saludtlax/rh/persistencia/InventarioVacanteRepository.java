
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.interinatos.EnumEstatusInterinatos;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.puestosautorizados.DetallePuestoDTO;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO;
import mx.gob.saludtlax.rh.puestosautorizados.ResumenPuestoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 08/08/2016 17:38:50
 *
 */
public class InventarioVacanteRepository extends GenericRepository<InventarioVacanteEntity, Integer> {
    private static final long serialVersionUID = 272072239420795035L;
    private static final Logger LOGGER = Logger.getLogger(InventarioVacanteRepository.class.getName());

    /**
     * Obtiene el listado de puestos autorizados en la secretaría.
     */
    public List<InventarioVacanteDTO> globlalPuestosAutorizados() {
        List<InventarioVacanteDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO(COUNT(i.idVacante), i.tipoContratacion.tipoContratacion, i.tipoContratacion.id) "
                        + " FROM InventarioVacanteEntity AS i WHERE i.estatus.autorizado = true AND i.tipoContratacion.inventario = true GROUP BY i.tipoContratacion.id",
                InventarioVacanteDTO.class).getResultList();
        return inventario;

    }

    public List<ResumenPuestoDTO> consultarResumenCodigosPorContratacion(Integer tipoContratacion) {
        List<ResumenPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.puestosautorizados.ResumenPuestoDTO(COUNT(i.idVacante), i.puestoAutorizado.puesto, i.puestoAutorizado.codigo) "
                        + " FROM InventarioVacanteEntity AS i WHERE i.estatus.autorizado = true AND i.tipoContratacion.inventario = true"
                        + " AND i.tipoContratacion.id =:tipoContratacion GROUP BY i.puestoAutorizado.idPuestoGeneral ORDER BY i.puestoAutorizado.id ASC",
                ResumenPuestoDTO.class).setParameter("tipoContratacion", tipoContratacion).getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> consultarEmpleadosInventarioPorContratacion(Integer tipoContratacion) {
        List<InventarioVacanteEntity> inventario = em.createQuery(
                "SELECT i FROM InventarioVacanteEntity AS i WHERE i.estatus.autorizado = true AND i.tipoContratacion.inventario = true"
                        + " AND i.tipoContratacion.id =:tipoContratacion " + "ORDER BY i.puestoAutorizado.id, i.numeroPuestoAutorizado ASC",
                InventarioVacanteEntity.class).setParameter("tipoContratacion", tipoContratacion).getResultList();
        return inventario;

    }

    public List<InventarioVacanteDTO> globalPuestosDisponibles() {
        List<InventarioVacanteDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO(COUNT(i.idVacante),i.tipoContratacion.id) "
                        + " FROM InventarioVacanteEntity AS i WHERE i.estatus.disponible = true AND i.tipoContratacion.inventario = true GROUP BY i.tipoContratacion.id",
                InventarioVacanteDTO.class).getResultList();
        return inventario;

    }

    public List<InventarioVacanteDTO> globalPuestosPorEstatus(Integer idEstatus) {
        List<InventarioVacanteDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO(COUNT(i.idVacante),i.tipoContratacion.id) "
                        + " FROM InventarioVacanteEntity AS i WHERE i.estatus.idEstatus  =:idEstatus  AND i.tipoContratacion.inventario = true GROUP BY i.tipoContratacion.id",
                InventarioVacanteDTO.class).setParameter("idEstatus", idEstatus).getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> consultarVoluntariosPorCriterio(String criterio) {
        List<InventarioVacanteEntity> inventario = em
                .createQuery(
                        "SELECT i FROM InventarioVacanteEntity AS i WHERE i.estatus.idEstatus  =:idEstatus  AND i.tipoContratacion.id =:tipoContratacion  "
                                + "AND i.voluntario.nombreCompleto LIKE :criterio " + " ORDER BY i.voluntario.nombreCompleto ASC",
                        InventarioVacanteEntity.class)
                .setParameter("criterio", "%" + criterio + "%").setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_ACTIVO)
                .setParameter("tipoContratacion", EnumTipoContratacion.VOLUNTARIOS).getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> consultarVoluntariosActivos() {
        List<InventarioVacanteEntity> inventario = em
                .createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.estatus.idEstatus  =:idEstatus  AND i.tipoContratacion.id =:tipoContratacion "
                        + " ORDER BY i.voluntario.nombreCompleto ASC", InventarioVacanteEntity.class)
                .setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_ACTIVO).setParameter("tipoContratacion", EnumTipoContratacion.VOLUNTARIOS)
                .getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesPorTipoContratacion(Integer tipoContratacion) {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.nombramiento, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.sueldo, i.configuracion.empleado.nombreCompleto, i.configuracion.id  )"
                        + " FROM InventarioVacanteEntity AS i WHERE i.tipoContratacion.id =:tipoContratacion",
                InfoPuestoDTO.class).setParameter("tipoContratacion", tipoContratacion).getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> consultaEmpleadosPorTipoNombramiento(Integer idTipoNombramiento) {
        List<InventarioVacanteEntity> inventario = em
                .createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.nombramiento.idTipoNombramiento =:tipoNombramiento and i.estatus.idEstatus=4",
                        InventarioVacanteEntity.class)
                .setParameter("tipoNombramiento", idTipoNombramiento).getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesPorTipoContratacionEstatus(Integer tipoContratacion, String disponible) {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.sueldo, i.configuracion.empleado.nombreCompleto, i.configuracion.id  )"
                        + " FROM InventarioVacanteEntity AS i WHERE i.tipoContratacion.id =:tipoContratacion AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("tipoContratacion", tipoContratacion).setParameter("disponible", disponible).getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesDisponiblesPostuladas(Integer tipoContratacion, String disponible) {

        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.sueldo, i.configuracion.id  )"
                        + " FROM InventarioVacanteEntity AS i WHERE i.tipoContratacion.id =:tipoContratacion AND i.disponible =:disponible AND i.idCandidatoPostulado IS NOT NULL",
                InfoPuestoDTO.class).setParameter("tipoContratacion", tipoContratacion).setParameter("disponible", disponible).getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesSinAdscripcion() {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.empleado.nombreCompleto, i.configuracion.id, i.configuracion.unidadResponsable.descripcion)"
                        + " FROM InventarioVacanteEntity AS i WHERE i.adscripcion IS NULL AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("disponible", "NO").getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesSinLugarAdscripcion() {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.empleado.nombreCompleto, i.configuracion.id, i.configuracion.unidadResponsable.descripcion)"
                        + " FROM InventarioVacanteEntity AS i WHERE i.lugarAdscripcion IS NULL AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("disponible", "NO").getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesSinAreaAdscripcion() {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.empleado.nombreCompleto, i.configuracion.id, i.configuracion.unidadResponsable.descripcion)"
                        + " FROM InventarioVacanteEntity AS i WHERE i.subadscripcion IS NULL AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("disponible", "NO").getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesPorUnidadResponsable(Integer idUnidadResponsable) {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.empleado.nombreCompleto, i.configuracion.id, i.configuracion.unidadResponsable.descripcion)"
                        + " FROM InventarioVacanteEntity AS i WHERE i.configuracion.unidadResponsable.idUnidadResponsable =:idUnidadResponsable AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("disponible", "NO").setParameter("idUnidadResponsable", idUnidadResponsable).getResultList();
        return inventario;

    }

    public List<InfoPuestoDTO> consultaVacantesRfcNombre(String criterio) {
        List<InfoPuestoDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.empleado.nombreCompleto, i.configuracion.id, i.configuracion.unidadResponsable.descripcion)"
                        + " FROM InventarioVacanteEntity AS i WHERE (i.configuracion.empleado.nombreCompleto LIKE :criterio OR i.configuracion.empleado.rfc LIKE :criterio)  AND i.disponible =:disponible",
                InfoPuestoDTO.class).setParameter("disponible", "NO").setParameter("criterio", "%" + criterio + "%").getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> obtenerListaInventarioVacante() {
        return em.createQuery("SELECT i FROM InventarioVacanteEntity AS i", InventarioVacanteEntity.class).getResultList();
    }

    public List<InfoPuestoDTO> consultarVacantesDisponibles() {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO(i.idVacante, i.tipoContratacion.tipoContratacion, i.configuracion.nombramiento.descripcion, i.folioVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto, i.configuracion.sueldo) FROM InventarioVacanteEntity AS i WHERE i.disponible = 'SI' AND i.idCandidatoPostulado IS NULL";

        List<InfoPuestoDTO> inventario = em.createQuery(query, InfoPuestoDTO.class).getResultList();
        return inventario;

    }

    public List<InventarioVacanteEntity> consultarPuestosDisponibles() {

        return em.createQuery(
                "SELECT i FROM InventarioVacanteEntity AS i WHERE i.disponible = 'SI' AND i.idCandidatoPostulado IS NULL ORDER BY i.tipoContratacion.id, i.adscripcion.idAdscripcion",
                InventarioVacanteEntity.class).getResultList();

    }

    public Integer ultimoFolioVacanteContratacion(Integer tipoContratacion) {
        try {
            return em.createQuery("select max(i.numeroVacante) FROM InventarioVacanteEntity AS i WHERE i.tipoContratacion.id =:tipoContratacion", Integer.class)
                    .setParameter("tipoContratacion", tipoContratacion).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }

    }

    public List<InfoEmpleadoDTO> empleadosPorCriterio(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado, "
                + "i.empleadoActivo.nombreCompleto, i.empleadoActivo.curp, i.empleadoActivo.rfc, "
                + "i.empleadoActivo.direccionCompleta, i.empleadoActivo.numeroEmpleado," + " i.empleadoActivo.idEstatus, i.tipoContratacion.tipoContratacion, "
                + "i.folioVacante, i.idVacante) " + "FROM InventarioVacanteEntity AS i WHERE (i.empleadoActivo.nombreCompleto LIKE :criterio "
                + "OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio) AND i.disponible =:disponible and i.estatus.puestoActivo=true"
                + " ORDER BY i.tipoContratacion.id, i.empleadoActivo.nombreCompleto ASC";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%")
                .setParameter("disponible", "NO").getResultList();

        return resultado;
    }

    public List<InfoEmpleadoDTO> empleadosPorCriterioTipoContratacion(String criterio, Integer tipoContratacion) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado, "
                + "i.empleadoActivo.nombreCompleto, i.empleadoActivo.curp, i.empleadoActivo.rfc, "
                + "i.empleadoActivo.direccionCompleta, i.empleadoActivo.numeroEmpleado," + " i.empleadoActivo.idEstatus, i.tipoContratacion.tipoContratacion, "
                + "i.folioVacante, i.idVacante) "
                + "FROM InventarioVacanteEntity AS i WHERE  (i.estatus.puestoActivo=true AND i.tipoContratacion.id =:tipoContratacion) "
                + " AND (i.empleadoActivo.nombreCompleto LIKE :criterio OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio)"
                + " ORDER BY i.tipoContratacion.id, i.empleadoActivo.nombreCompleto ASC";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%")
                .setParameter("tipoContratacion", tipoContratacion).getResultList();

        return resultado;
    }

    /**
     * Consulta los empleados federales activos
     */
    public List<InfoEmpleadoDTO> empleadosFederalesPorCriterio(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado, "
                + "i.empleadoActivo.nombreCompleto, i.empleadoActivo.curp, i.empleadoActivo.rfc, "
                + "i.empleadoActivo.direccionCompleta, i.empleadoActivo.numeroEmpleado," + " i.empleadoActivo.idEstatus, i.tipoContratacion.tipoContratacion, "
                + "i.folioVacante, i.idVacante) "
                + "FROM InventarioVacanteEntity AS i WHERE  (i.estatus.puestoActivo=true AND i.tipoContratacion.areaResponsable =1) "
                + " AND (i.empleadoActivo.nombreCompleto LIKE :criterio OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio)"
                + " ORDER BY i.tipoContratacion.id, i.empleadoActivo.nombreCompleto ASC";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    /**
     * Consulta utilizada para visualizar solamente el nombre del empleado en
     * los combos.
     *
     * @param criterio
     *            rfc, curp, nombre
     */
    public List<InfoEmpleadoDTO> empleadosPorCriterioCombo(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado, "
                + "i.empleadoActivo.nombreCompleto) " + "FROM InventarioVacanteEntity AS i WHERE (i.empleadoActivo.nombreCompleto LIKE :criterio "
                + "OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio) AND i.disponible =:disponible"
                + " ORDER BY i.tipoContratacion.id, i.empleadoActivo.nombreCompleto ASC";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%")
                .setParameter("disponible", "NO").getResultList();

        return resultado;
    }

    /**
     * Consulta utilizada para visualizar solamente el nombre del empleado en
     * los combos, filtrados por adscripción
     *
     * @param criterio
     *            rfc, curp, nombre
     * @param idAdscripcion
     *            adscripción del empleado
     */
    public List<InfoEmpleadoDTO> empleadosPorCriterioAdscripcionCombo(String criterio, Integer idAdscripcion) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado, "
                + "i.empleadoActivo.nombreCompleto) " + "FROM InventarioVacanteEntity AS i WHERE (i.empleadoActivo.nombreCompleto LIKE :criterio "
                + "OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio) "
                + "AND (i.disponible =:disponible AND i.adscripcion.idAdscripcion =:idAdscripcion)"
                + " ORDER BY i.tipoContratacion.id, i.empleadoActivo.nombreCompleto ASC";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%")
                .setParameter("disponible", "NO").setParameter("idAdscripcion", idAdscripcion).getResultList();

        return resultado;
    }

    public Integer obtenerIdCandidatoPostulado(Integer idInventarioVacante) {
        try {
            return em.createQuery("SELECT v.idCandidatoPostulado FROM InventarioVacanteEntity AS v WHERE v.idVacante =:idVacante", Integer.class)
                    .setParameter("idVacante", idInventarioVacante).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * Consulta si el empleado está asignado a algún puesto.
     */
    public String obtenerTipoContratacionEmpleadoActivo(Integer idEmpleado) {

        try {
            return em.createQuery(
                    "SELECT i.tipoContratacion.tipoContratacion FROM InventarioVacanteEntity AS i WHERE i.configuracion.empleado.idEmpleado =:idEmpleado AND i.estatus.idEstatus =:idEstatus",
                    String.class).setParameter("idEmpleado", idEmpleado).setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_ACTIVO).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException("El empleado tiene mas de un puesto activo", SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }

    }

    public InventarioVacanteEntity obtenerPuestoActivoEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT i FROM InventarioVacanteEntity AS i WHERE i.configuracion.empleado.idEmpleado =:idEmpleado AND i.estatus.idEstatus =:idEstatus",
                    InventarioVacanteEntity.class).setParameter("idEmpleado", idEmpleado).setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_ACTIVO)
                    .getSingleResult();
        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException("El empleado tiene mas de un puesto activo", SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }

    }

    public boolean tienePuestoActivoEmpleado(Integer idEmpleado) {
        try {
            em.createQuery(
                    "SELECT i.idVacante FROM InventarioVacanteEntity AS i WHERE i.configuracion.empleado.idEmpleado =:idEmpleado AND i.estatus.idEstatus =:idEstatus",
                    Integer.class).setParameter("idEmpleado", idEmpleado).setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_ACTIVO).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException("El empleado tiene mas de un puesto activo", SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }

    }

    /**
     * Consulta si el empleado está asignado a algún puesto.
     */
    public Integer obtenerIdTipoContratacionPuesto(Integer idPuesto) {
        try {
            return em.createQuery("SELECT i.tipoContratacion.id FROM InventarioVacanteEntity AS i WHERE i.idVacante =:idPuesto", Integer.class)
                    .setParameter("idPuesto", idPuesto).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

    public Integer obtenerIdInventarioPorIdEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery("SELECT i.idVacante FROM InventarioVacanteEntity AS i WHERE i.configuracion.empleado.idEmpleado =:idEmpleado", Integer.class)
                    .setParameter("idEmpleado", idEmpleado).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

    public boolean tieneEmpleadoPuestoAsignado(Integer idEmpleado) {
        try {
            em.createQuery(
                    "SELECT p.idVacante FROM InventarioVacanteEntity AS p WHERE p.empleadoActivo.idEmpleado =:idEmpleado AND p.estatus.disponible = false",
                    Integer.class).setParameter("idEmpleado", idEmpleado).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    public InventarioVacanteEntity obtenerPuestoPorIdEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.configuracion.empleado.idEmpleado =:idEmpleado",
                    InventarioVacanteEntity.class).setParameter("idEmpleado", idEmpleado).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

    public List<InventarioVacanteEntity> consultarDisponiblesInterinatoPorPermiso() {
        return em.createQuery(
                "SELECT i FROM InventarioVacanteEntity AS i WHERE i.estatus.idEstatus =:idEstatus AND i.interino =false ORDER BY i.tipoContratacion.id ASC",
                InventarioVacanteEntity.class).setParameter("idEstatus", EnumEstatusPuesto.EMPLEADO_EN_PERMISO).getResultList();
    }

    public Integer obtenerIdDatoLaboralPorPuesto(Integer idPuesto) {
        try {
            return em.createQuery("SELECT i.configuracion.id FROM InventarioVacanteEntity AS i WHERE i.idVacante =:idPuesto", Integer.class)
                    .setParameter("idPuesto", idPuesto).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

    public List<InfoEmpleadoDTO> consultaEmpleadosFederales(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.empleadoActivo.idEmpleado,"
                + " i.empleadoActivo.nombreCompleto, i.empleadoActivo.curp, i.empleadoActivo.rfc,"
                + " i.empleadoActivo.direccionCompleta, i.empleadoActivo.numeroEmpleado, " + "i.empleadoActivo.idEstatus, i.tipoContratacion.tipoContratacion, "
                + "i.folioVacante, i.idVacante)" + " FROM InventarioVacanteEntity AS i WHERE (i.empleadoActivo.nombreCompleto LIKE :criterio "
                + "OR i.empleadoActivo.rfc LIKE :criterio OR i.empleadoActivo.curp LIKE :criterio)"
                + " AND i.tipoContratacion.areaResponsable = 2 AND i.empleadoActivo.idEstatus = 'ACTIVO' AND i.disponible ='NO'";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    public List<InfoEmpleadoDTO> empleadosPorCriterioConNombramiento(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(i.configuracion.empleado.idEmpleado, "
                + "i.configuracion.empleado.nombreCompleto, i.configuracion.empleado.curp, i.configuracion.empleado.rfc, "
                + "i.configuracion.empleado.direccionCompleta, i.configuracion.empleado.numeroEmpleado, "
                + "i.configuracion.empleado.idEstatus, i.tipoContratacion.tipoContratacion, i.folioVacante,"
                + " i.idVacante, i.configuracion.puesto.codigo, i.configuracion.puesto.puesto,"
                + " i.configuracion.nombramiento.descripcion, i.configuracion.sueldo, i.configuracion.empleado.claveCobro) "
                + "FROM InventarioVacanteEntity AS i WHERE (i.configuracion.empleado.nombreCompleto LIKE :criterio "
                + "OR i.configuracion.empleado.rfc LIKE :criterio OR i.configuracion.empleado LIKE :criterio) "
                + "AND i.tipoContratacion.areaResponsable = 2 AND i.configuracion.empleado.idEstatus = 'ACTIVO' AND i.disponible ='NO'";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    public List<InventarioVacanteDTO> globalPuestosInterinatos() {
        List<InventarioVacanteDTO> inventario = em.createQuery(
                "SELECT NEW mx.gob.saludtlax.rh.puestosautorizados.InventarioVacanteDTO(COUNT(i.puestoPropietario.idVacante), i.puestoPropietario.tipoContratacion.tipoContratacion, i.puestoPropietario.tipoContratacion.id) "
                        + " FROM InterinatoEntity AS i WHERE i.estatus =:estatus GROUP BY i.puestoPropietario.tipoContratacion.id",
                InventarioVacanteDTO.class).setParameter("estatus", EnumEstatusInterinatos.ACTIVO).getResultList();
        return inventario;
    }

    public InventarioVacanteEntity obtenerPuestoPorRFC(String rfc) {
        try {
            return em
                    .createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.empleadoActivo.rfc =:rfc AND i.estatus.idEstatus =:estatus",
                            InventarioVacanteEntity.class)
                    .setParameter("rfc", rfc.trim()).setParameter("estatus", EnumEstatusPuesto.EMPLEADO_ACTIVO).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            return null;
        }
    }

    public InventarioVacanteEntity obtenerPuestoPorRFCTipoContratacion(String rfc, Integer tipoContratacion) {
        try {
            return em.createQuery(
                    "SELECT i FROM InventarioVacanteEntity AS i WHERE i.empleadoActivo.rfc =:rfc " + "AND i.tipoContratacion.id =:tipoContratacion",
                    InventarioVacanteEntity.class).setParameter("rfc", rfc.trim()).setParameter("tipoContratacion", tipoContratacion).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            return null;
        }
    }

    public InventarioVacanteEntity obtenerPuestoPorIdEmpleadoTipoContratacion(Integer idEmpleado, Integer tipoContratacion) {
        try {
            return em
                    .createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.empleadoActivo.idEmpleado =:idEmpleado "
                            + "AND i.tipoContratacion.id =:tipoContratacion", InventarioVacanteEntity.class)
                    .setParameter("idEmpleado", idEmpleado).setParameter("tipoContratacion", tipoContratacion).getSingleResult();

        } catch (NoResultException exception) {
            return null;
        } catch (NonUniqueResultException exception) {
            return null;
        }
    }

    public List<InventarioVacanteEntity> consultarPuestosPorTipoContratacion(Integer tipoContratacion) {
        return em.createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.tipoContratacion.id =:tipoContratacion", InventarioVacanteEntity.class)
                .setParameter("tipoContratacion", tipoContratacion).getResultList();
    }

    public InventarioVacanteEntity obtenerInventarioVacantePorConfiguracionPresupuesto(ConfiguracionPresupuestoEntity configuracionPresupuestoEntity) {
        return em
                .createQuery("SELECT i FROM InventarioVacanteEntity AS i WHERE i.configuracion =:configuracionPresupuestoEntity", InventarioVacanteEntity.class)
                .setParameter("configuracionPresupuestoEntity", configuracionPresupuestoEntity).getSingleResult();
    }

    public List<DetallePuestoDTO> inventarioPorContratacionYEstatus(Integer idTipoContratacion, int idEstatus) {
        Session session = em.unwrap(Session.class);

        Query query = session
                .createSQLQuery(" SELECT                                                                                  "
                        + " i.id_inventario          AS idPuesto,                                         "
                        + " pg.codigo                AS codigoAutorizado,                                 "
                        + " pg.puesto                AS descripcionCodigoAutorizado,                      "
                        + " i.id_plaza               AS numeroPlaza,                                      "
                        + " e.rfc                    AS rfc,                                              "
                        + " e.nombre_completo        AS empleado,                                         "
                        + " tc.tipo_contratacion     AS tipoContratacion,                                 "
                        + " tn.descripcion           AS nombramiento,                                     "
                        + " ep.estatus               AS estatus,                                          "
                        + " i.disponible             AS disponible,                                       "
                        + " p.programa               AS programa,                                         "
                        + " ecp.nombre_completo      AS candidatoPostulado,                               "
                        + " ads.adscripcion          AS adscripcion,                                      "
                        + " sads.subadscripcion      AS areaAdscripcion,                                  "
                        + " i.provisional            AS provisional,                                      "
                        + " i.fecha_inicio           AS fechaInicio,                                      "
                        + " i.fecha_fin              AS fechaFin,                                         "
                        + " s.servicio               AS actividad,                                        "
                        + " f.funcion                AS funcion,                                          "
                        + " i.fecha_inicio_permiso   AS fechaInicioPermiso,                               "
                        + " i.fecha_fin_permiso      AS fechaFinPermiso,                                  "
                        + " i.interino               AS interino,                                         "
                        + " ea.nombre_completo       AS empleadoAnterior,                                 "
                        + " c.clues                  AS clue,                                             "
                        + " v.nombre_completo        AS voluntario,                                       "
                        + " i.seguro_popular         AS seguroPopular,                                    "
                        + " tj.tipo_jornada          AS tipoJornada,                                      "
                        + " i.subfuncion             AS subfuncion,                                       "
                        + " i.financiamiento         AS financiamiento,                                   "
                        + " i.jornada                AS jornada                                           "
                        + " FROM inventario_vacantes AS i                                                 "
                        + " LEFT JOIN puestos_generales AS pg                                             "
                        + " ON pg.id_puesto_general = i.id_puesto_autorizado                              "
                        + " LEFT JOIN empleados AS e                                                      "
                        + " ON e.id_empleado = i.id_empleado                                              "
                        + " LEFT JOIN tipos_contratacion AS tc                                            "
                        + " ON tc.id_tipo_contratacion = i.id_tipo_contratacion                           "
                        + " LEFT JOIN tipos_nombramientos AS tn                                           "
                        + " ON tn.id_tipo_nombramiento = i.id_nombramiento                                "
                        + " LEFT JOIN estatus_puestos AS ep                                               "
                        + " ON ep.id_estatus = i.id_estatus                                               "
                        + " LEFT JOIN programas AS p                                                      "
                        + " ON p.id_programa = i.id_programa                                              "
                        + " LEFT JOIN empleados AS ecp                                                    "
                        + " ON ecp.id_empleado = i.id_candidato_postulado                                 "
                        + " LEFT JOIN adscripciones AS ads                                                "
                        + " ON ads.id_adscripcion = i.id_adscripcion                                      "
                        + " LEFT JOIN subadscripciones AS sads                                            "
                        + " ON sads.id_subadscripcion = i.id_area_adscripcion                             "
                        + " LEFT JOIN servicios AS s                                                      "
                        + " ON s.id_servicio = i.id_actividad                                             "
                        + " LEFT JOIN funciones AS f                                                      "
                        + " ON f.id_funcion = i.id_funcion                                                "
                        + " LEFT JOIN empleados AS ea                                                     "
                        + " ON ea.id_empleado = i.id_empleado_anterior                                    "
                        + " LEFT JOIN clues AS c                                                          "
                        + " ON c.id_clues = i.id_clue                                                     "
                        + " LEFT JOIN voluntarios AS v                                                    "
                        + " ON v.id_voluntario = i.id_voluntario                                          "
                        + " LEFT JOIN tipos_jornadas AS tj                                                "
                        + " ON tj.id_tipo_jornada = i.id_tipo_jornada                                     "
                        + " WHERE                                                                         "
                        + " i.id_tipo_contratacion = :tipoContratacion                                    "
                        + " AND i.id_estatus = :estatusPuestos                                            ")
                .setParameter("tipoContratacion", idTipoContratacion).setParameter("estatusPuestos", idEstatus);
        query.setResultTransformer(Transformers.aliasToBean(DetallePuestoDTO.class));
        @SuppressWarnings("unchecked")
        List<DetallePuestoDTO> inventario = query.list();
        return inventario;
    }

    public DetallePuestoDTO obtenerPuesto(Integer idInventario) {
        Session session = em.unwrap(Session.class);
        LOGGER.info("idInventario:: " + idInventario);
        Query query = session.createSQLQuery(" SELECT                                                                                  "
                + " i.id_inventario          AS idPuesto,                                         "
                + " pg.codigo                AS codigoAutorizado,                                 "
                + " pg.puesto                AS descripcionCodigoAutorizado,                      "
                + " i.id_plaza               AS numeroPlaza,                                      "
                + " e.rfc                    AS rfc,                                              "
                + " e.nombre_completo        AS empleado,                                         "
                + " tc.tipo_contratacion     AS tipoContratacion,                                 "
                + " tn.descripcion           AS nombramiento,                                     "
                + " ep.estatus               AS estatus,                                          "
                + " i.disponible             AS disponible,                                       "
                + " p.programa               AS programa,                                         "
                + " ecp.nombre_completo      AS candidatoPostulado,                               "
                + " ads.adscripcion          AS adscripcion,                                      "
                + " sads.subadscripcion      AS areaAdscripcion,                                  "
                + " i.provisional            AS provisional,                                      "
                + " i.fecha_inicio           AS fechaInicio,                                      "
                + " i.fecha_fin              AS fechaFin,                                         "
                + " s.servicio               AS actividad,                                        "
                + " f.funcion                AS funcion,                                          "
                + " i.fecha_inicio_permiso   AS fechaInicioPermiso,                               "
                + " i.fecha_fin_permiso      AS fechaFinPermiso,                                  "
                + " i.interino               AS interino,                                         "
                + " ea.nombre_completo       AS empleadoAnterior,                                 "
                + " c.clues                  AS clue,                                             "
                + " v.nombre_completo        AS voluntario,                                       "
                + " i.seguro_popular         AS seguroPopular,                                    "
                + " tj.tipo_jornada          AS tipoJornada,                                      "
                + " i.subfuncion             AS subfuncion,                                       "
                + " i.financiamiento         AS financiamiento,                                   "
                + " i.jornada                AS jornada                                           "
                + " FROM inventario_vacantes AS i                                                 "
                + " LEFT JOIN puestos_generales AS pg                                             "
                + " ON pg.id_puesto_general = i.id_puesto_autorizado                              "
                + " LEFT JOIN empleados AS e                                                      "
                + " ON e.id_empleado = i.id_empleado                                              "
                + " LEFT JOIN tipos_contratacion AS tc                                            "
                + " ON tc.id_tipo_contratacion = i.id_tipo_contratacion                           "
                + " LEFT JOIN tipos_nombramientos AS tn                                           "
                + " ON tn.id_tipo_nombramiento = i.id_nombramiento                                "
                + " LEFT JOIN estatus_puestos AS ep                                               "
                + " ON ep.id_estatus = i.id_estatus                                               "
                + " LEFT JOIN programas AS p                                                      "
                + " ON p.id_programa = i.id_programa                                              "
                + " LEFT JOIN empleados AS ecp                                                    "
                + " ON ecp.id_empleado = i.id_candidato_postulado                                 "
                + " LEFT JOIN adscripciones AS ads                                                "
                + " ON ads.id_adscripcion = i.id_adscripcion                                      "
                + " LEFT JOIN subadscripciones AS sads                                            "
                + " ON sads.id_subadscripcion = i.id_area_adscripcion                             "
                + " LEFT JOIN servicios AS s                                                      "
                + " ON s.id_servicio = i.id_actividad                                             "
                + " LEFT JOIN funciones AS f                                                      "
                + " ON f.id_funcion = i.id_funcion                                                "
                + " LEFT JOIN empleados AS ea                                                     "
                + " ON ea.id_empleado = i.id_empleado_anterior                                    "
                + " LEFT JOIN clues AS c                                                          "
                + " ON c.id_clues = i.id_clue                                                     "
                + " LEFT JOIN voluntarios AS v                                                    "
                + " ON v.id_voluntario = i.id_voluntario                                          "
                + " LEFT JOIN tipos_jornadas AS tj                                                "
                + " ON tj.id_tipo_jornada = i.id_tipo_jornada                                     "
                + " WHERE                                                                         "
                + " i.id_inventario = :idInventario                                               ").setParameter("idInventario", idInventario);
        query.setResultTransformer(Transformers.aliasToBean(DetallePuestoDTO.class));
        @SuppressWarnings("unchecked")
        List<DetallePuestoDTO> inventario = query.list();
        return inventario.get(0);
    }
}