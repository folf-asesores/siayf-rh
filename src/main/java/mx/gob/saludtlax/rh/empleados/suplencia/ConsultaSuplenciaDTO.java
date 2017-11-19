/*
 *
 */

package mx.gob.saludtlax.rh.empleados.suplencia;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 07/11/2016 14:27:19
 */
public class ConsultaSuplenciaDTO {
    private Integer idSuplenteAutorizado;
    private int quincena;
    private int ejercicio;
    private boolean conDetalleMovimieto;
    private String estatus;
    private int tipoConsulta;
    private Integer idQuincena;
    private String criterio;
    private Integer idCentroResponsabilidad;

    public Integer getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Integer idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public Integer getIdSuplenteAutorizado() {
        return idSuplenteAutorizado;
    }

    public void setIdSuplenteAutorizado(Integer idSuplenteAutorizado) {
        this.idSuplenteAutorizado = idSuplenteAutorizado;
    }

    public int getQuincena() {
        return quincena;
    }

    public void setQuincena(int quincena) {
        this.quincena = quincena;
    }

    public boolean isConDetalleMovimieto() {
        return conDetalleMovimieto;
    }

    public void setConDetalleMovimieto(boolean conDetalleMovimieto) {
        this.conDetalleMovimieto = conDetalleMovimieto;
    }

}
