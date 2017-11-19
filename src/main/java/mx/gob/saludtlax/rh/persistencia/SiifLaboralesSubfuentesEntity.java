
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_laborales_subfuentes")

public class SiifLaboralesSubfuentesEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siif_laborales_subfuentes")
    private Integer idSiifLaboralesSubfuentes;
    @Column(name = "id_fuente_financiamiento")
    private Integer idFuenteFinanciamiento;
    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;
    @Column(name = "id_siif_datos_laborales")
    private Integer idSiifDatosLaborales;

    public Integer getIdSiifLaboralesSubfuentes() {
        return idSiifLaboralesSubfuentes;
    }

    public void setIdSiifLaboralesSubfuentes(
            Integer idSiifLaboralesSubfuentes) {
        this.idSiifLaboralesSubfuentes = idSiifLaboralesSubfuentes;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(
            Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdSiifDatosLaborales() {
        return idSiifDatosLaborales;
    }

    public void setIdSiifDatosLaborales(Integer idSiifDatosLaborales) {
        this.idSiifDatosLaborales = idSiifDatosLaborales;
    }

}
