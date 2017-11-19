
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "distribucion_presupuestal")
@Entity
public class DistribucionPresupuestoEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8482450829285727060L;

    @Id
    @Column(name = "id_distribucion_presupuestal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDistribucionPresupuestal;

    @Column(name = "id_subfuente_financiamiento")
    private Integer idSubfuenteFinanciamiento;

    @Column(name = "id_unidad_responsable")
    private Integer idUnidadResponsable;

    @Column(name = "id_nombramiento")
    private Integer idNombramiento;

    @Column(name = "id_partida")
    private Integer idPartida;

    @Column(name = "partida")
    private String partida;

    @Column(name = "id_dependencia")
    private String idDependencia;

    @Column(name = "id_quincena")
    private String idQuincena;

    @Column(name = "enero")
    private BigDecimal enero;

    @Column(name = "febrero")
    private BigDecimal febrero;

    @Column(name = "marzo")
    private BigDecimal marzo;

    @Column(name = "abril")
    private BigDecimal abril;

    @Column(name = "mayo")
    private BigDecimal mayo;

    @Column(name = "junio")
    private BigDecimal junio;

    @Column(name = "julio")
    private BigDecimal julio;

    @Column(name = "agosto")
    private BigDecimal agosto;

    @Column(name = "septiembre")
    private BigDecimal septiembre;

    @Column(name = "octubre")
    private BigDecimal octubre;

    @Column(name = "noviembre")
    private BigDecimal noviembre;

    @Column(name = "diciembre")
    private BigDecimal diciembre;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "fecha_fin_quincena")
    private String fechaFinQuincena;

    public Integer getIdDistribucionPresupuestal() {
        return idDistribucionPresupuestal;
    }

    public void setIdDistribucionPresupuestal(
            Integer idDistribucionPresupuestal) {
        this.idDistribucionPresupuestal = idDistribucionPresupuestal;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(
            Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Integer idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(String idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(String idQuincena) {
        this.idQuincena = idQuincena;
    }

    public BigDecimal getEnero() {
        return enero;
    }

    public void setEnero(BigDecimal enero) {
        this.enero = enero;
    }

    public BigDecimal getFebrero() {
        return febrero;
    }

    public void setFebrero(BigDecimal febrero) {
        this.febrero = febrero;
    }

    public BigDecimal getMarzo() {
        return marzo;
    }

    public void setMarzo(BigDecimal marzo) {
        this.marzo = marzo;
    }

    public BigDecimal getAbril() {
        return abril;
    }

    public void setAbril(BigDecimal abril) {
        this.abril = abril;
    }

    public BigDecimal getMayo() {
        return mayo;
    }

    public void setMayo(BigDecimal mayo) {
        this.mayo = mayo;
    }

    public BigDecimal getJunio() {
        return junio;
    }

    public void setJunio(BigDecimal junio) {
        this.junio = junio;
    }

    public BigDecimal getJulio() {
        return julio;
    }

    public void setJulio(BigDecimal julio) {
        this.julio = julio;
    }

    public BigDecimal getAgosto() {
        return agosto;
    }

    public void setAgosto(BigDecimal agosto) {
        this.agosto = agosto;
    }

    public BigDecimal getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(BigDecimal septiembre) {
        this.septiembre = septiembre;
    }

    public BigDecimal getOctubre() {
        return octubre;
    }

    public void setOctubre(BigDecimal octubre) {
        this.octubre = octubre;
    }

    public BigDecimal getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(BigDecimal noviembre) {
        this.noviembre = noviembre;
    }

    public BigDecimal getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(BigDecimal diciembre) {
        this.diciembre = diciembre;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFechaFinQuincena() {
        return fechaFinQuincena;
    }

    public void setFechaFinQuincena(String fechaFinQuincena) {
        this.fechaFinQuincena = fechaFinQuincena;
    }

}
