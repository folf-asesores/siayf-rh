/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/03/2016-13:05:27
 */

@Stateless
public class BolsaTrabajoEJB implements BolsaTrabajo {

    @Inject
    private AspiranteService registrarAspiranteService;
    @Inject
    private HistorialAcademicoService historialAcademicoService;
    @Inject
    private ExperienciaLaboralService experienciaLaboralService;
    @Inject
    private HabilidadesPersonalesService habilidadesPersonalesService;
    @Inject
    private ConsultaAspiranteService consultaAspirante;

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#crearAspirante(mx.gob.
     * saludtlax.rh.api.aspirantes.AspiranteDTO)
     */
    @Override
    @Interceptors({ RegistroDatoPersonalValidator.class })
    public Integer crearAspirante(AspiranteDTO aspiranteDTO) {
        return registrarAspiranteService.crearAspirante(aspiranteDTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#actualizarAspirante(mx.
     * gob.saludtlax.rh.api.aspirantes.AspiranteDTO)
     */
    // @Override
    @Override
    @Interceptors({ RegistroDatoPersonalValidator.class })
    public Integer actualizarAspirante(AspiranteDTO aspiranteDTO) {
        return registrarAspiranteService.actualizarAspirante(aspiranteDTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * registrarHistorialAcademicoAspirante(mx.gob.saludtlax.rh.api.aspirantes.
     * HistorialAcademicoDTO)
     */
    @Override
    @Interceptors({ RegistroHistorialAcademicoValidator.class })
    public void crearHistorialAcademicoAspirante(HistorialAcademicoDTO historialAcademico) {
        historialAcademicoService.crearHistorialAcademicoAspirante(historialAcademico);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * actualizarHistorialAcademicoAspirante(mx.gob.saludtlax.rh.api.aspirantes.
     * HistorialAcademicoDTO)
     */
    @Override
    @Interceptors({ RegistroHistorialAcademicoValidator.class })
    public void actualizarHistorialAcademicoAspirante(HistorialAcademicoDTO historialAcademico) {
        historialAcademicoService.actualizarHistorialAcademicoAspirante(historialAcademico);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * registrarExperienciaLaboralAspirante(mx.gob.saludtlax.rh.api.aspirantes.
     * ExperienciaLaboralAspiranteDTO)
     */
    @Override
    @Interceptors({ RegistroExperienciaLaboralValidator.class })
    public void crearExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral) {
        experienciaLaboralService.crearExperienciaLaboralAspirante(experienciaLaboral);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * actualizarExperienciaLaboralAspirante(mx.gob.saludtlax.rh.api.aspirantes.
     * ExperienciaLaboralAspiranteDTO)
     */
    @Override
    @Interceptors({ RegistroExperienciaLaboralValidator.class })
    public void actualizarExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral) {
        experienciaLaboralService.actualizarExperienciaLaboralAspirante(experienciaLaboral);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * registrarHabilidadesPersonalesAspirante(mx.gob.saludtlax.rh.api.
     * aspirantes.EncuestaPersonalAspiranteDTO)
     */
    @Override
    @Interceptors({ RegistroHabilidadPersonalValidator.class })
    public void crearHabilidadesPersonalesAspirante(HabilidadesPersonalesAspiranteDTO encuestaPersonalAspirante) {
        habilidadesPersonalesService.crearHabilidadesPersonalesAspirante(encuestaPersonalAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * actualizarHabilidadesPersonalesAspirante(mx.gob.saludtlax.rh.api.
     * aspirantes.EncuestaPersonalAspiranteDTO)
     */
    @Override
    @Interceptors({ RegistroHabilidadPersonalValidator.class })
    public void actualizarHabilidadesPersonalesAspirante(HabilidadesPersonalesAspiranteDTO encuestaPersonalAspirante) {
        habilidadesPersonalesService.actualizarHabilidadesPersonalesAspirante(encuestaPersonalAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * encuestaPersonalPorIdAspirante(java.lang.Integer)
     */
    @Override
    public HabilidadesPersonalesAspiranteDTO obtenerHabilidadesPersonalesPorIdAspirante(Integer idAspirante) {
        return habilidadesPersonalesService.obtenerHabilidadesPersonalesPorIdAspirante(idAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * eliminarHistorialAcademico(java.lang.Integer)
     */
    @Override
    public void eliminarHistorialAcademico(Integer idHistorialAcademico) {
        historialAcademicoService.eliminarHistorialAcademico(idHistorialAcademico);
    }

    /*
     * (non-Javadoc)
     *
     * @see mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#
     * eliminarExperienciaLaboral(java.lang.Integer)
     */
    @Override
    public void eliminarExperienciaLaboral(Integer idExperienciaLaboral) {
        experienciaLaboralService.eliminarExperienciaLaboral(idExperienciaLaboral);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#listaHistorialAcademico(
     * java.lang.Integer)
     */
    @Override
    public List<HistorialAcademicoDTO> obtenerListaHistorialAcademico(Integer idAspirante) {
        return historialAcademicoService.obtenerListaHistorialAcademicoPorIdAspirante(idAspirante);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * mx.gob.saludtlax.rh.api.aspirantes.BolsaTrabajo#listaExperienciaLaboral(
     * java.lang.Integer)
     */
    @Override
    public List<ExperienciaLaboralAspiranteDTO> obtenerListaExperienciaLaboral(Integer idAspirante) {
        return experienciaLaboralService.obtenerListaExperienciaLaboralPorIdAspirante(idAspirante);
    }

    @Override
    public void validarRfcAspirante(String rfc) {
        registrarAspiranteService.validarAspiranteRfc(rfc);
    }

    @Override
    public void validarCurpAspirante(String curp) {
        registrarAspiranteService.validarAspiranteCurp(curp);
    }

    @Override
    public List<InfoAspiranteDTO> consultarPorCriterio(FiltroDTO filtroDTO) {

        return consultaAspirante.consultarAspirantePorCriterio(filtroDTO);
    }

    @Override
    public AspiranteDTO obtenerAspirantePorIdentificador(Integer IdAspirante) {
        return consultaAspirante.obtenerAspirantePorIdentificador(IdAspirante);
    }

    @Override
    public List<InfoVacantePostularDTO> obtenerListaAspiranteCandidato() {

        return consultaAspirante.obtenerListaAspiranteCandidato();
    }

    @Override
    public boolean validarRfcyIdAspirante(Integer idAspirante, String rfc) {

        return registrarAspiranteService.validarRfcyIdAspirante(idAspirante, rfc);
    }

    @Override
    public boolean validarCurpyIdAspirante(Integer idAspirante, String curp) {

        return registrarAspiranteService.validarCurpyIdAspirante(idAspirante, curp);
    }

    @Override
    public InfoAspiranteDTO obtenerDetalleAspirantePorId(Integer idAspirante) {

        return consultaAspirante.obtenerDetalleAspirantePorId(idAspirante);
    }

}
