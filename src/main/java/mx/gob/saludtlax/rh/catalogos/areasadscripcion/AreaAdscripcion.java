
package mx.gob.saludtlax.rh.catalogos.areasadscripcion;

import java.util.List;

public interface AreaAdscripcion {

    public void crearAreaAdscripcion(AreaAdscripcionDTO areaAdscripcionDTO);

    /**
     * Permite obtener un área de adcripción por medio de su ID.
     *
     * @param idAreaAdscripcion
     *            el ID del área de adscripción a buscar.
     * @return un área de adcripción si existe, en caso contrario retorna
     *         null.
     */
    AreaAdscripcionDTO obtenerPorId(int idAreaAdscripcion);

    public List<InfoAreaAdscripcionDTO> consultarAreasAdscripcion(
            TipoFiltro filtro, Integer idFiltro);

    /**
     * Permite consultar las descripciones de las áreas de adscripción que
     * coninciden con el criterio de busqueda.
     *
     * @param criterio
     *            una frase que pudiera encontrarse en el adescripcion
     *            de alguna área de adscripción.
     * @return una lista con la descripción de las áreas de adcripción que
     *         coninciden con el criterio.
     */
    List<String> consultarAreaAdscripcionPorCriterio(String criterio);

    /**
     * Permite obtener el ID del área de adscripción que coincide con la
     * descripción.
     *
     * @param descripcion
     *            la descripción a consultar entre las áreas de
     *            adscripción.
     * @return el ID del área de adscripción.
     */
    Integer consultarIdAreaAdscripcionPorDescripcion(String descripcion);

}
