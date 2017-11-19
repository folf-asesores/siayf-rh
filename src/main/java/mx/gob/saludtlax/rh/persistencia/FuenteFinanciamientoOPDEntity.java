/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fuentes_financiamientos_opd")
public class FuenteFinanciamientoOPDEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_fuente_financiamiento_opd")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFuenteFinanciamientoOPD;

    @Column(name = "id_fuente_financiamiento_opd_salud")
    private int idFuenteFinanciamientoOpdSalud;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity idFuenteFinanciamiento;

    @Column(name = "descripcion")
    private String descripcion;

    public Integer getIdFuenteFinanciamientoOPD() {
        return idFuenteFinanciamientoOPD;
    }

    public int getIdFuenteFinanciamientoOpdSalud() {
        return idFuenteFinanciamientoOpdSalud;
    }

    public void setIdFuenteFinanciamientoOpdSalud(int idFuenteFinanciamientoOpdSalud) {
        this.idFuenteFinanciamientoOpdSalud = idFuenteFinanciamientoOpdSalud;
    }

    public void setIdFuenteFinanciamientoOPD(Integer idFuenteFinanciamientoOPD) {
        this.idFuenteFinanciamientoOPD = idFuenteFinanciamientoOPD;
    }

    public FuenteFinanciamientoEntity getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(FuenteFinanciamientoEntity fuenteFinanciamiento) {
        idFuenteFinanciamiento = fuenteFinanciamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
