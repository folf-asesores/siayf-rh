/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-14:56:14
 */
public class EmpleadoRepository
        extends GenericRepository<EmpleadoEntity, Integer> {

    private static final long serialVersionUID = -2508778264142668669L;

    /**
     * Valida si existe un empleado registrado con la curp.
     *
     * @param curp
     */
    public boolean existeAspiranteCurp(String curp) {
        try {
            em.createQuery(
                    "SELECT a.idEmpleado FROM EmpleadoEntity AS a WHERE a.curp =:curp",
                    Integer.class).setParameter("curp", curp).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    /**
     * Consulta si el rfc ya esta dado de alta.
     *
     * @param rfc
     * @return boolean si ya existe un empleado o no con ese rfc.
     */
    public boolean existeEmpleadoRfc(String rfc) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.rfc =:rfc",
                    Integer.class).setParameter("rfc", rfc.trim())
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    /**
     * Consulta si el numero de empleado ya esta dado de alta.
     *
     * @param numeroEmpleado
     * @return boolean si ya existe un empleado o no con ese numero empleado.
     */
    public boolean existeEmpleadoNumeroEmpleado(String numeroEmpleado) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.curp =:curp",
                    Integer.class).setParameter("curp", numeroEmpleado.trim())
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    /**
     * Consulta de empleados activos e inactivos
     *
     * @param criterio
     *            rfc, curp, nombre
     *
     */
    public List<InfoEmpleadoDTO> consultarEmpleadoPorCriterio(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(e.idEmpleado, e.nombreCompleto,"
                + " e.curp, e.rfc, e.direccionCompleta, e.numeroEmpleado, e.idEstatus, e.tipoEmpleado.tipoEmpleado) "
                + "FROM EmpleadoEntity AS e WHERE e.nombreCompleto LIKE :criterio OR e.rfc LIKE :criterio OR e.curp LIKE :criterio";
        List<InfoEmpleadoDTO> resultado = em
                .createQuery(consulta, InfoEmpleadoDTO.class)
                .setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    public List<InfoEmpleadoDTO> consultarEmpleadosActivosPorCriterio(
            String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(e.idEmpleado, e.nombreCompleto,"
                + " e.curp, e.rfc, e.direccionCompleta, e.numeroEmpleado, e.idEstatus, e.tipoEmpleado.tipoEmpleado) "
                + "FROM EmpleadoEntity AS e WHERE e.idEstatus =:estatus AND ( e.nombreCompleto LIKE :criterio OR e.rfc LIKE :criterio OR e.curp LIKE :criterio)";
        List<InfoEmpleadoDTO> resultado = em
                .createQuery(consulta, InfoEmpleadoDTO.class)
                .setParameter("criterio", "%" + criterio + "%")
                .setParameter("estatus", EnumEstatusEmpleado.ACTIVO)
                .getResultList();

        return resultado;
    }

    /**
     * Consulta de empleados activos e inactivos
     *
     * @param criterio
     *            rfc, curp, nombre, perfil
     */
    public List<InfoEmpleadoDTO> empleadosPorCriteriosPerfil(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(e.idEmpleado, e.nombreCompleto, e.curp,"
                + " e.rfc, e.direccionCompleta, e.numeroEmpleado, e.idEstatus,e.tipoEmpleado.tipoEmpleado) "
                + "FROM EmpleadoEntity AS e WHERE e.nombreCompleto LIKE :criterio OR e.rfc "
                + "LIKE :criterio OR e.curp LIKE :criterio OR e.perfilAcademico LIKE :criterio";
        List<InfoEmpleadoDTO> resultado = em
                .createQuery(consulta, InfoEmpleadoDTO.class)
                .setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    /**
     * Consulta si existe el empleado por el identificador.
     *
     * @param idEmpleado
     */
    public boolean existeIdEmpleado(Integer idEmpleado) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    Integer.class).setParameter("idEmpleado", idEmpleado)
                    .getResultList();
            return true;
        } catch (NoResultException exception) {
            return false;
        }

    }

    public boolean existeEmpleadoConCurp(String curp) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.curp =:curp",
                    Integer.class).setParameter("curp", curp).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            throw new ValidacionException("La curp " + curp + " existe",
                    ValidacionCodigoError.VALOR_DUPLICADO);
        }
    }

    public boolean existeEmpleadoConRfc(String rfc) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.rfc =:rfc",
                    Integer.class).setParameter("rfc", rfc).getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    public boolean existeEmpleadoConNumeroPersonal(Integer numeroEmpleado) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.numeroEmpleado =:numeroEmpleado AND e.idEstatus =:estatus",
                    Integer.class)
                    .setParameter("numeroEmpleado", numeroEmpleado)
                    .setParameter("estatus", EnumEstatusEmpleado.ACTIVO)
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        }
    }

    public String obtenerCurpEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.curp FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();

        } catch (NoResultException exception) {
            throw new SistemaException(
                    "obtenerCurpEmpleado: No existe empleado con el identificador indicado.",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

    }

    public String obtenerNombreEmpleadoId(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.nombreCompleto FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();

        } catch (NoResultException exception) {
            throw new SistemaException(
                    "obtenerCurpEmpleado: No existe empleado con el identificador indicado.",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

    }

    public String obtenerRfcEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.rfc FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();

        } catch (NoResultException exception) {
            throw new SistemaException(
                    "obtenerRfcEmpleado: No existe empleado con el identificador indicado.",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

    }

    public EmpleadoEntity obtenerEmpleadoRfc(String rfc) {
        try {
            return em.createQuery(
                    "SELECT e FROM EmpleadoEntity AS e WHERE e.rfc =:rfc",
                    EmpleadoEntity.class).setParameter("rfc", rfc.trim())
                    .getSingleResult();

        } catch (NoResultException exception) {
            return null;
        }
    }

    public List<InfoVacantePostularDTO> obtenerListaEmpleadoCandidato() {
        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(e.idEmpleado, e.rfc, e.curp, e.nombreCompleto, e.direccionCompleta, e.idEstatus) FROM EmpleadoEntity AS e WHERE e.idEstatus = 'ACTIVO'";

        List<InfoVacantePostularDTO> resultado = em
                .createQuery(query, InfoVacantePostularDTO.class)
                .getResultList();

        return resultado;

    }

    public String obtenerNombreEmpleadoPorId(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.nombreCompleto FROM EmpleadoEntity As e WHERE e.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();
        } catch (NoResultException e) {
            return "";
        }
    }

    public DatoGeneralCandidatoDTO obtenerCadidatoEmpleado(Integer idEmpleado) {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.consulta.DatoGeneralCandidatoDTO(e.nombreCompleto, e.rfc, e.curp, e.direccionCompleta, e.idSexo, e.estadoCivil, e.fechaNacimiento, e.lugarNacimiento, e.telefono, e.correoElectronico, e.tipoSangre, e.tienePersonasDependientes, e.numeroHijos, e.numeroPadres, e.numeroConyuges, e.numeroOtros)FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado";

        DatoGeneralCandidatoDTO dto = em
                .createQuery(query, DatoGeneralCandidatoDTO.class)
                .setParameter("idEmpleado", idEmpleado).getSingleResult();

        return dto;

    }

    public String obtenerEstatusEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.idEstatus FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    String.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();
        } catch (NoResultException exception) {
            throw new SistemaException(
                    "No se encontró empleado con el identificador "
                            + idEmpleado,
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }
    }

    public Integer obtenerTipoEmpleado(Integer idEmpleado) {
        try {
            return em.createQuery(
                    "SELECT e.tipoEmpleado.idTipoEmpleado FROM EmpleadoEntity AS e WHERE e.idEmpleado =:idEmpleado",
                    Integer.class).setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();
        } catch (NoResultException exception) {
            throw new SistemaException(
                    "No se encontró empleado con el identificador "
                            + idEmpleado,
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }
    }

    public List<EmpleadoEntity> consultarEmpleadosPorEstatus(String estatus) {
        return em.createQuery(
                "SELECT e FROM EmpleadoEntity AS e WHERE e.idEstatus =:estatus",
                EmpleadoEntity.class).setParameter("estatus", estatus)
                .getResultList();
    }

    public boolean existeCuentaAsignada(String cuenta) {
        try {
            em.createQuery(
                    "SELECT e.idEmpleado FROM EmpleadoEntity AS e WHERE e.numeroCuenta =:cuenta",
                    Integer.class).setParameter("cuenta", cuenta.trim())
                    .getSingleResult();
            return true;
        } catch (NoResultException exception) {
            return false;
        } catch (NonUniqueResultException exception) {
            throw new SistemaException("La cuenta " + cuenta
                    + " está asignada a mas de un empleado, verificar con sistemas.",
                    SistemaCodigoError.ERROR_MULTIPLES_RESULTADOS);
        }
    }

}
