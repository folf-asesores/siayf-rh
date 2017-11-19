
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.math.BigDecimal;

/**
 *
 * @author José Pablo
 *
 */

public class ConceptosNominaEmpleadosDTO {

    private Integer idConceptosNominaEmpleado;
    private Integer idNominaEmpleado;
    private Integer tipo;
    private String clave;
    private String tipoSat;
    private String concepto;
    private BigDecimal importeGravado;
    private BigDecimal importeExcento;
    private Integer idConceptoNominaEventuales;
    private Integer idConceptoNominaFederales;

    public Integer getIdConceptosNominaEmpleado() {
        return idConceptosNominaEmpleado;
    }

    public void setIdConceptosNominaEmpleado(Integer idConceptosNominaEmpleado) {
        this.idConceptosNominaEmpleado = idConceptosNominaEmpleado;
    }

    public Integer getIdNominaEmpleado() {
        return idNominaEmpleado;
    }

    public void setIdNominaEmpleado(Integer idNominaEmpleado) {
        this.idNominaEmpleado = idNominaEmpleado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoSat() {
        return tipoSat;
    }

    public void setTipoSat(String tipoSat) {
        this.tipoSat = tipoSat;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporteGravado() {
        return importeGravado;
    }

    public void setImporteGravado(BigDecimal importeGravado) {
        this.importeGravado = importeGravado;
    }

    public BigDecimal getImporteExcento() {
        return importeExcento;
    }

    public void setImporteExcento(BigDecimal importeExcento) {
        this.importeExcento = importeExcento;
    }

    public Integer getIdConceptoNominaEventuales() {
        return idConceptoNominaEventuales;
    }

    public void setIdConceptoNominaEventuales(Integer idConceptoNominaEventuales) {
        this.idConceptoNominaEventuales = idConceptoNominaEventuales;
    }

    public Integer getIdConceptoNominaFederales() {
        return idConceptoNominaFederales;
    }

    public void setIdConceptoNominaFederales(Integer idConceptoNominaFederales) {
        this.idConceptoNominaFederales = idConceptoNominaFederales;
    }
}