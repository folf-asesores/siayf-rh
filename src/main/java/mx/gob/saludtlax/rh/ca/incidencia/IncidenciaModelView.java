
package mx.gob.saludtlax.rh.ca.incidencia;

/**
 * DTO que represnta los datos de una incidencias.
 */
import java.io.Serializable;

public class IncidenciaModelView implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8795131585828261394L;

    private Integer idIncidencia;
    private String descripcion;
    private String marcaReporte;
    private Integer esImagen;
    private String colorTexto;
    public String tipoRegitro;
    public Integer idTipoRegistro;

    public String getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarcaReporte() {
        return marcaReporte;
    }

    public void setMarcaReporte(String marcaReporte) {
        this.marcaReporte = marcaReporte;
    }

    public Integer getEsImagen() {
        return esImagen;
    }

    public void setEsImagen(Integer esImagen) {
        this.esImagen = esImagen;
    }

    public String getTipoRegitro() {
        return tipoRegitro;
    }

    public void setTipoRegitro(String tipoRegitro) {
        this.tipoRegitro = tipoRegitro;
    }

    public Integer getIdTipoRegistro() {
        return idTipoRegistro;
    }

    public void setIdTipoRegistro(Integer idTipoRegistro) {
        this.idTipoRegistro = idTipoRegistro;
    }

}
