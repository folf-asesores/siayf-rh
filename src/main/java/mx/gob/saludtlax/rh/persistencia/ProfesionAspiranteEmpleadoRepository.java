/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO;
import mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO;
import mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 14:20:58 05/08/2016
 */
public class ProfesionAspiranteEmpleadoRepository extends GenericRepository<ProfesionAspiranteEmpleadoEntity, Integer> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5767150379627581564L;

    /**
     * Retorna las coincidencias de aspirantes con la profesion.
     *
     * @param nombre
     */
    public List<InfoAspiranteDTO> aspirantesDisponiblesPorProfesion(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.InfoAspiranteDTO(p.aspirante.idAspirante, p.aspirante.nombreCompleto, p.aspirante.curp, p.aspirante.rfc, p.aspirante.direccionCompleta, p.aspirante.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity AS p WHERE (p.profesion.profesion LIKE :criterio) AND p.empleado.idEmpleado IS NULL";
        List<InfoAspiranteDTO> resultado = em.createQuery(consulta, InfoAspiranteDTO.class).setParameter("criterio", "%" + criterio + "%").getResultList();
        return resultado;
    }

    public List<ProfesionDTO> obtenerListaProfesionPorIdAspirante(Integer idAspirante) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO(p.idProfesionAspiranteEmpleado, p.profesion.idProfesion, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity AS p WHERE p.aspirante.idAspirante =:idAspirante AND p.empleado.idEmpleado IS NULL";

        List<ProfesionDTO> resultado = em.createQuery(consulta, ProfesionDTO.class).setParameter("idAspirante", idAspirante).getResultList();

        return resultado;

    }

    public List<InfoEmpleadoDTO> empleadoDisponiblePorProfesion(String criterio) {
        String consulta = "SELECT NEW mx.gob.saludtlax.rh.empleados.administracion.InfoEmpleadoDTO(p.empleado.idEmpleado, p.empleado.nombreCompleto, p.empleado.curp, p.empleado.rfc, p.empleado.direccionCompleta, p.empleado.numeroEmpleado, p.empleado.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity AS p WHERE (p.profesion.profesion LIKE :criterio)";
        List<InfoEmpleadoDTO> resultado = em.createQuery(consulta, InfoEmpleadoDTO.class).setParameter("criterio", "%" + criterio + "%").getResultList();

        return resultado;
    }

    public List<ProfesionDTO> obtenerListaProfesionPorIdEmpleado(Integer idEmpleado) {

        String consulta = "SELECT NEW mx.gob.saludtlax.rh.configuracion.profesion.ProfesionDTO(p.idProfesionAspiranteEmpleado, p.profesion.idProfesion, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity AS p WHERE p.empleado.idEmpleado =:idEmpleado";

        List<ProfesionDTO> resultado = em.createQuery(consulta, ProfesionDTO.class).setParameter("idEmpleado", idEmpleado).getResultList();

        return resultado;
    }

    /**
     * Obtiene los aspirantes con profesiones por id
     *
     * @param idProfesion
     * @return
     */
    public List<InfoVacantePostularDTO> obtenerListaPorIdProfesionTipoCandidatoAspirante(Integer idProfesion) {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(p.aspirante.idAspirante, p.aspirante.rfc, p.aspirante.curp, p.aspirante.nombreCompleto, p.aspirante.direccionCompleta, p.aspirante.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity As p WHERE p.profesion.idProfesion =:idProfesion AND p.empleado.idEmpleado IS NULL";

        List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).setParameter("idProfesion", idProfesion).getResultList();

        return resultado;
    }

    /**
     * Obtiene todos los aspirante con profesiones
     *
     * @return
     */
    public List<InfoVacantePostularDTO> obtenerListaProfesionTipoCandidatoAspirante() {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(p.aspirante.idAspirante, p.aspirante.rfc, p.aspirante.curp, p.aspirante.nombreCompleto, p.aspirante.direccionCompleta, p.aspirante.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity As p WHERE p.empleado.idEmpleado IS NULL";

        List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).getResultList();

        return resultado;
    }

    /**
     * Obtiene los empleados con profesiones por id
     *
     * @param idProfesion
     * @return
     */
    public List<InfoVacantePostularDTO> obtenerListaPorIdProfesionTipoCandidatoEmpleado(Integer idProfesion) {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(p.empleado.idEmpleado, p.empleado.rfc, p.empleado.curp, p.empleado.nombreCompleto, p.empleado.direccionCompleta, p.empleado.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity As p WHERE p.profesion.idProfesion =:idProfesion AND p.aspirante.idAspirante IS NULL";

        List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).setParameter("idProfesion", idProfesion).getResultList();

        return resultado;

    }

    /**
     * Obtiene los empleados con profesiones
     *
     * @param idProfesion
     * @return
     */
    public List<InfoVacantePostularDTO> obtenerListaProfesionTipoCandidatoEmpleado() {

        String query = "SELECT NEW mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO(p.empleado.idEmpleado, p.empleado.rfc, p.empleado.curp, p.empleado.nombreCompleto, p.empleado.direccionCompleta, p.empleado.idEstatus, p.profesion.profesion) FROM ProfesionAspiranteEmpleadoEntity As p WHERE p.aspirante.idAspirante IS NULL";

        List<InfoVacantePostularDTO> resultado = em.createQuery(query, InfoVacantePostularDTO.class).getResultList();

        return resultado;

    }

    public boolean existeProfesionAspirante(Integer idProfesion, Integer idAspirante) {

        try {
            em.createQuery(
                    "SELECT p.idProfesionAspiranteEmpleado FROM ProfesionAspiranteEmpleadoEntity AS p WHERE p.profesion.idProfesion =:idProfesion AND p.aspirante.idAspirante =:idAspirante",
                    ProfesionAspiranteEmpleadoEntity.class).setParameter("idProfesion", idProfesion).setParameter("idAspirante", idAspirante).getSingleResult();

            return true;
        } catch (NoResultException e) {
            return false;
        } catch (NonUniqueResultException e) {
            return true;
        }

    }

}
