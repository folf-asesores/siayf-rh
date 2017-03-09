package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

import java.util.List;

import mx.gob.saludtlax.rh.vacantes.seleccion.InfoVacantePostularDTO;

public interface BolsaTrabajo {

	/**
	 * Crear Aspirante
	 * 
	 * @param aspiranteDTO
	 * @return IdAspirante
	 */
	Integer crearAspirante(AspiranteDTO aspiranteDTO);

	/**
	 * Actualizar Aspirante
	 * 
	 * @param aspiranteDTO
	 * @return idAspirante
	 */
	Integer actualizarAspirante(AspiranteDTO aspiranteDTO);

	/**
	 * Crear Historial Academico Aspirante
	 * 
	 * @param historialAcademico
	 */
	void crearHistorialAcademicoAspirante(HistorialAcademicoDTO historialAcademico);

	/**
	 * Actualizar Historial Academico
	 * 
	 * @param historialAcademico
	 */
	void actualizarHistorialAcademicoAspirante(HistorialAcademicoDTO historialAcademico);

	/**
	 * Crear Experiencia Laboral Aspirante
	 * 
	 * @param experienciaLaboral
	 */
	void crearExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral);

	/**
	 * Actualizar Experiencia Laboral Aspirante
	 * 
	 * @param experienciaLaboral
	 */
	void actualizarExperienciaLaboralAspirante(ExperienciaLaboralAspiranteDTO experienciaLaboral);

	/**
	 * Crear Habilidades Personales Aspirante
	 * 
	 * @param encuestaPersonalAspirante
	 */
	void crearHabilidadesPersonalesAspirante(HabilidadesPersonalesAspiranteDTO encuestaPersonalAspirante);

	/**
	 * Actualizar Habilidades Personales del Aspirante
	 * 
	 * @param encuestaPersonalAspirante
	 */
	void actualizarHabilidadesPersonalesAspirante(HabilidadesPersonalesAspiranteDTO encuestaPersonalAspirante);

	/**
	 * Obtener Habilidades Personales por Identificador Unico del Aspirante
	 * 
	 * @param idAspirante
	 * @return Habilidades Personales del Aspirante
	 */
	HabilidadesPersonalesAspiranteDTO obtenerHabilidadesPersonalesPorIdAspirante(Integer idAspirante);

	/**
	 * Eliminar Historial Academico por Identificador Unico
	 * 
	 * @param idHistorialAcademico
	 */
	void eliminarHistorialAcademico(Integer idHistorialAcademico);

	/**
	 * Eliminar Experiencia Laboral Por Indentificador Unico
	 * 
	 * @param idExperienciaLaboral
	 */
	void eliminarExperienciaLaboral(Integer idExperienciaLaboral);

	/**
	 * Obtener Lista Historial Academico Por Odentificador Unicor del Aspirante
	 * 
	 * @param idAspirante
	 * @return lista Historial Academico
	 */
	List<HistorialAcademicoDTO> obtenerListaHistorialAcademico(Integer idAspirante);

	/**
	 * Obtener Lista Experiencia Laboral Por Identificador Unicor del Aspirante
	 * 
	 * @param idAspirante
	 * @return lista Experiencia Laboral
	 */
	List<ExperienciaLaboralAspiranteDTO> obtenerListaExperienciaLaboral(Integer idAspirante);

	/*
	 * Valida si es correcto y si existe el rfc
	 */
	void validarRfcAspirante(String rfc);

	/**
	 * Valida si el la curp es correcta o existe ya registrada
	 * 
	 * @param curp
	 */
	void validarCurpAspirante(String curp);

	/*
	 * Valida si es correcto y si existe el rfc
	 */
	boolean validarRfcyIdAspirante(Integer idAspirante, String rfc);

	/**
	 * Valida si el la curp es correcta o existe ya registrada
	 * 
	 * @param curp
	 */
	boolean validarCurpyIdAspirante(Integer idAspirante, String curp);

	/**
	 * Realiza la consulta de aspirante por tipo de filtro
	 * 
	 * @param filtroDTO
	 * @return
	 */
	public List<InfoAspiranteDTO> consultarPorCriterio(FiltroDTO filtroDTO);

	/**
	 * Obtiene el aspirante por identificador unico
	 * 
	 * @param IdAspirante
	 * @return
	 */
	public AspiranteDTO obtenerAspirantePorIdentificador(Integer IdAspirante);

	/**
	 * Obtiene la lista de aspirantes a postularse
	 * 
	 * @return
	 */
	public List<InfoVacantePostularDTO> obtenerListaAspiranteCandidato();

	/**
	 * Obteiene la información de un aspirante en especifico.
	 * @param idAspirante
	 */
	public InfoAspiranteDTO obtenerDetalleAspirantePorId(Integer idAspirante);

}