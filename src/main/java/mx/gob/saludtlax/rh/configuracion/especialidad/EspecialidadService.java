/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.especialidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.EspecialidadAspiranteEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EspecialidadAspiranteEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EspecialidadEntity;
import mx.gob.saludtlax.rh.persistencia.EspecialidadRepository;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 12:25:53 09/08/2016
 */
public class EspecialidadService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1985421410511032620L;

    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private EspecialidadRepository especialidadRepository;
    @Inject
    private EspecialidadAspiranteEmpleadoRepository especialidadAspiranteEmpleadoRepository;

    protected List<EspecialidadDTO> obtenerListaEspecialidadPorIdAspirante(
            Integer idAspirante) {

        return especialidadAspiranteEmpleadoRepository
                .obtenerListaEspecialidadPorIdAspirante(idAspirante);
    }

    protected List<EspecialidadDTO> obtenerListaEspecialidadPorIdEmpleado(
            Integer idEmpleado) {

        return especialidadAspiranteEmpleadoRepository
                .obtenerListaEspecialidadPorIdEmpleado(idEmpleado);
    }

    protected List<InfoVacantePostularDTO> obtenerListaEspecialidadPorTipoCandidato(
            Integer idEspecialidad, Integer tipoCandidato) {

        List<InfoVacantePostularDTO> listaEspecialidad = new ArrayList<>();

        if (tipoCandidato == EnumTipoCandidato.ASPIRANTE) {
            listaEspecialidad = especialidadAspiranteEmpleadoRepository
                    .obtenerListaPorIdEspecialidadTipoCandidatoAspirante(
                            idEspecialidad);

            // Si no hay ninguna candidato con esa especialidad, entonces
            // verifica
            // si existe la especialiad en la EspecialidadEntity, si no existe
            // entonces trae todos los aspirantes con sus especialidades ya que
            // el
            // idEspecialidad se agrego como un id que no existe en la bd y
            // bandera
            // para traer todo las aspirantes aspirante
            if (listaEspecialidad.isEmpty()) {
                if (!especialidadRepository
                        .existeIdEspecialidad(idEspecialidad)) {

                    listaEspecialidad = especialidadAspiranteEmpleadoRepository
                            .obtenerListaEspecialidadTipoCandidatoAspirante();
                }
            }

        }

        if (tipoCandidato == EnumTipoCandidato.EMPLEADO) {
            listaEspecialidad = especialidadAspiranteEmpleadoRepository
                    .obtenerListaPorIdEspecialidadTipoCandidatoEmpleado(
                            idEspecialidad);

            // Si no hay ninguna candidato con esa especialidad, entonces
            // verifica
            // si existe la especialiad en la EspecialidadEntity, si no existe
            // entonces trae todos los empleados con sus especialidades ya que
            // el
            // idEspecialidad se agrego como un id que no existe en la bd y
            // bandera
            // para traer todo las empleados aspirante
            if (listaEspecialidad.isEmpty()) {
                if (!especialidadRepository
                        .existeIdEspecialidad(idEspecialidad)) {

                    listaEspecialidad = especialidadAspiranteEmpleadoRepository
                            .obtenerListaEspecialidadTipoCandidatoEmpleado();
                }
            }
        }
        return listaEspecialidad;
    }

    protected void crearEspecialidadAspirante(Integer idEspecialidad,
            Integer idAspirante) {

        String contexto = "Registro Especialidad: ";

        if (idAspirante == null) {
            throw new ReglaNegocioException(
                    contexto + "El necesario registrar los datos generales...",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        if (especialidadAspiranteEmpleadoRepository
                .existeEspecialidadAspirante(idEspecialidad, idAspirante)) {
            throw new ReglaNegocioException(
                    contexto + "La especialidad ya se encuentra registrado...",
                    ReglaNegocioCodigoError.YA_REGISTRADO);
        }

        EspecialidadAspiranteEmpleadoEntity especialidadAspiranteEmpleadoEntity = new EspecialidadAspiranteEmpleadoEntity();

        EspecialidadEntity especialidadEntity = especialidadRepository
                .obtenerPorId(idEspecialidad);

        especialidadAspiranteEmpleadoEntity.setEspecialidad(especialidadEntity);

        AspiranteEntity aspiranteEntity = aspiranteRepository
                .obtenerPorId(idAspirante);

        especialidadAspiranteEmpleadoEntity.setAspirante(aspiranteEntity);

        especialidadAspiranteEmpleadoRepository
                .crear(especialidadAspiranteEmpleadoEntity);

    }

    protected void actualizarEspecialidadAspirante(EspecialidadDTO dto,
            Integer idAspirante) {
        String contexto = "Actualización Especialidad: ";

        if (idAspirante == null) {
            throw new ReglaNegocioException(
                    contexto + "El necesario registrar los datos generales...",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        if (especialidadAspiranteEmpleadoRepository.existeEspecialidadAspirante(
                dto.getIdEspecialidad(), idAspirante)) {
            throw new ReglaNegocioException(
                    contexto + "La especialidad ya se encuentra registrado...",
                    ReglaNegocioCodigoError.YA_REGISTRADO);
        }

        EspecialidadAspiranteEmpleadoEntity especialidadAspiranteEmpleadoEntity = especialidadAspiranteEmpleadoRepository
                .obtenerPorId(dto.getIdEspecialidadAspiranteEmpleado());

        EspecialidadEntity especialidadEntity = especialidadRepository
                .obtenerPorId(dto.getIdEspecialidad());

        especialidadAspiranteEmpleadoEntity.setEspecialidad(especialidadEntity);

        AspiranteEntity aspiranteEntity = aspiranteRepository
                .obtenerPorId(idAspirante);

        especialidadAspiranteEmpleadoEntity.setAspirante(aspiranteEntity);

        especialidadAspiranteEmpleadoRepository
                .actualizar(especialidadAspiranteEmpleadoEntity);
    }

    protected void eliminarEspecialidadAspirante(
            Integer idEspecialidadAspirante) {

        especialidadAspiranteEmpleadoRepository
                .eliminarPorId(idEspecialidadAspirante);
    }

}
