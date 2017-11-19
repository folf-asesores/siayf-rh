
package mx.gob.saludtlax.rh.configuracion.profesion;

import java.util.List;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

public interface Profesion {

    void crearProfesion(ProfesionDTO profesion);

    void actualizarProfesion(ProfesionDTO profesion);

    void eliminarProfesion(Integer idProfesion);

    List<ProfesionDTO> listaProfesion();

    List<ProfesionDTO> obtenerListaProfesionPorIdAspirante(Integer idAspirante);

    List<ProfesionDTO> obtenerListaProfesionPorIdEmpleado(Integer idEmpleado);

    public List<InfoVacantePostularDTO> obtenerListaProfesionPorTipoCandidato(Integer idProfesion, Integer tipoCandidato);

    public void crearProfesionAspirante(Integer idProfesion, Integer idAspirante);

    public void actualizarProfesionAspirante(ProfesionDTO profesionDTO, Integer idAspirante);

    public void eliminarProfesionAspirante(Integer idProfesionAspirante);

}