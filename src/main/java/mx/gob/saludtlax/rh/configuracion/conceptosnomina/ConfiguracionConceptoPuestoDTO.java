
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConfiguracionConceptoPuestoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5172641171733087326L;

    private Integer idConfiguracionConcepto;

    private Integer idConceptoNomina;
    private String claveConcepto;
    private String decripcionConcepto;

    private Integer idTabulador;
    private Integer ejercicioFiscalTabulador;

    private BigDecimal importeConcepto;

    private Integer idPuestoGeneral;
    private String codigoPuesto;
    private String descripcionPuesto;

    private String formula;

    private Integer tipoPuesto;

    public ConfiguracionConceptoPuestoDTO() {
        importeConcepto = BigDecimal.ZERO;
    }

    public Integer getIdConfiguracionConcepto() {
        return idConfiguracionConcepto;
    }

    public void setIdConfiguracionConcepto(Integer idConfiguracionConcepto) {
        this.idConfiguracionConcepto = idConfiguracionConcepto;
    }

    public Integer getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(Integer idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public String getClaveConcepto() {
        return claveConcepto;
    }

    public void setClaveConcepto(String claveConcepto) {
        this.claveConcepto = claveConcepto;
    }

    public String getDecripcionConcepto() {
        return decripcionConcepto;
    }

    public void setDecripcionConcepto(String decripcionConcepto) {
        this.decripcionConcepto = decripcionConcepto;
    }

    public Integer getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Integer idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Integer getEjercicioFiscalTabulador() {
        return ejercicioFiscalTabulador;
    }

    public void setEjercicioFiscalTabulador(Integer ejercicioFiscalTabulador) {
        this.ejercicioFiscalTabulador = ejercicioFiscalTabulador;
    }

    public BigDecimal getImporteConcepto() {
        return importeConcepto;
    }

    public void setImporteConcepto(BigDecimal importeConcepto) {
        this.importeConcepto = importeConcepto;
    }

    public Integer getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(Integer idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Integer getTipoPuesto() {
        return tipoPuesto;
    }

    public void setTipoPuesto(Integer tipoPuesto) {
        this.tipoPuesto = tipoPuesto;
    }

}
