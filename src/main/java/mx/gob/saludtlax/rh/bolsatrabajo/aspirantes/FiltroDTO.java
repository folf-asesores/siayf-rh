/*
 *
 */

package mx.gob.saludtlax.rh.bolsatrabajo.aspirantes;

/**
 *
 * @author Leila Schiaffini Ehuan
 * @since 01/08/2016 16:09:17
 */
public class FiltroDTO {

    private int tipoFiltro;
    private String criterio;
    private Integer id;

    public FiltroDTO() {
        this(0, "");
    }

    /**
     * Inicializa los criterios de busqueda.
     *
     * @param tipoFiltro
     *            el tipo de filtro el cual sus valores pueden ser
     *            {@link EnumTipoFiltro#NOMBRE_RFC_CURP} o
     *            {@link EnumTipoFiltro#NOMBRE_RFC_CURP_PROFESION}
     * @param criterio
     *            El criterio a buscar.
     */

    public FiltroDTO(int tipoFiltro, String criterio) {
        this.criterio = criterio;
        this.tipoFiltro = tipoFiltro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoFiltro() {
        return tipoFiltro;
    }

    /**
     *
     * @param tipoFiltro
     *            los valores pueden ser {@link EnumTipoFiltro#NOMBRE_RFC_CURP}
     *            o {@link EnumTipoFiltro#NOMBRE_RFC_CURP_PROFESION}
     */
    public void setTipoFiltro(int tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

}
