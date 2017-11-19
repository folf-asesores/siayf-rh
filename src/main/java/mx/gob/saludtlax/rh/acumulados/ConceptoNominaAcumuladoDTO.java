
package mx.gob.saludtlax.rh.acumulados;

import java.math.BigDecimal;

public class ConceptoNominaAcumuladoDTO {

    private Integer idCoceptoNomina;
    private String clave;
    private Integer tipo;
    private String descripcion;
    private BigDecimal importe;

    public ConceptoNominaAcumuladoDTO() {
    }

    public ConceptoNominaAcumuladoDTO(Integer idCoceptoNomina, String clave, Integer tipo, String descripcion, BigDecimal importe) {
        super();
        this.idCoceptoNomina = idCoceptoNomina;
        this.clave = clave;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public Integer getIdCoceptoNomina() {
        return idCoceptoNomina;
    }

    public void setIdCoceptoNomina(Integer idCoceptoNomina) {
        this.idCoceptoNomina = idCoceptoNomina;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

}
