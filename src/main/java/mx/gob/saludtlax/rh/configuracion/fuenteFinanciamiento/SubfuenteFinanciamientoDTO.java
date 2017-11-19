
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

public class SubfuenteFinanciamientoDTO {
    private Integer idSubfuenteFinanciamiento;
    private FuenteFinanciamientoDTO fuenteFinanciamiento;
    private FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPD;
    private String idBase36;
    private String descripcion;
    private Boolean nombramiento;
    private Boolean estatales;
    private Boolean federales;

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public String getIdBase36() {
        return idBase36;
    }

    public void setIdBase36(String idBase36) {
        this.idBase36 = idBase36;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FuenteFinanciamientoDTO getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(FuenteFinanciamientoDTO fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public FuenteFinanciamientoOPDDTO getFuenteFinanciamientoOPD() {
        return fuenteFinanciamientoOPD;
    }

    public void setFuenteFinanciamientoOPD(FuenteFinanciamientoOPDDTO fuenteFinanciamientoOPD) {
        this.fuenteFinanciamientoOPD = fuenteFinanciamientoOPD;
    }

    public Boolean getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(Boolean nombramiento) {
        this.nombramiento = nombramiento;
    }

    public Boolean getEstatales() {
        return estatales;
    }

    public void setEstatales(Boolean estatales) {
        this.estatales = estatales;
    }

    public Boolean getFederales() {
        return federales;
    }

    public void setFederales(Boolean federales) {
        this.federales = federales;
    }

}
