
package mx.gob.saludtlax.rh.presupuesto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DistribucionPresupuestoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2346097516770608127L;

    private Integer idDistribucionPresupuestal;
    private Integer idUnidadResponsable;
    private Integer idNombramiento;
    private Integer idPartida;
    private Integer idDependencia;
    private Integer anio;
    private Integer idSubfuenteFinanciamiento;
    private Integer idQuincena;
    private String unidadResponsable;
    private String nombramiento;
    private String partida;
    private String fechaFinQuincena;

    private BigDecimal enero;
    private BigDecimal febrero;
    private BigDecimal marzo;
    private BigDecimal abril;
    private BigDecimal mayo;
    private BigDecimal junio;
    private BigDecimal julio;
    private BigDecimal agosto;
    private BigDecimal septiembre;
    private BigDecimal octubre;
    private BigDecimal noviembre;
    private BigDecimal diciembre;
    private BigDecimal total;

    public Integer getIdDistribucionPresupuestal() {
        return idDistribucionPresupuestal;
    }

    public void setIdDistribucionPresupuestal(Integer idDistribucionPresupuestal) {
        this.idDistribucionPresupuestal = idDistribucionPresupuestal;
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

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(String nombramiento) {
        this.nombramiento = nombramiento;
    }

    public String getPartida() {
        return idPartida + " " + partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
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

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public String getFechaFinQuincena() {
        return fechaFinQuincena;
    }

    public void setFechaFinQuincena(String fechaFinQuincena) {
        this.fechaFinQuincena = fechaFinQuincena;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {

        total = BigDecimal.ZERO;

        total = total.add(enero);
        total = total.add(febrero);
        total = total.add(marzo);
        total = total.add(abril);
        total = total.add(mayo);
        total = total.add(junio);
        total = total.add(julio);
        total = total.add(agosto);
        total = total.add(septiembre);
        total = total.add(octubre);
        total = total.add(noviembre);
        total = total.add(diciembre);

        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}