/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.especialidad;

import java.util.List;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 12:24:50 09/08/2016
 */
public interface Especialidad {

    /**
     * Obtiene las especialidades por identificador del aspirante
     *
     * @param idAspirante
     * @return
     */
    public List<EspecialidadDTO> obtenerListaEspecialidadPorIdAspirante(Integer idAspirante);

    /***
     * Obtiene las especialidades por identificador del empleado
     *
     * @param idEmpleado
     * @return
     */
    public List<EspecialidadDTO> obtenerListaEspecialidadPorIdEmpleado(Integer idEmpleado);

    public List<InfoVacantePostularDTO> obtenerListaEspecialidadPorTipoCandidato(Integer idEspecialidad, Integer tipoCandidato);

    public void crearEspecialidadAspirante(Integer idEspecialidad, Integer idAspirante);

    public void actualizarEspecialidadAspirante(EspecialidadDTO dto, Integer idAspirante);

    public void eliminarEspecialidadAspirante(Integer idEspecialidadAspirante);

}
