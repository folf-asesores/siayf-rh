package mx.gob.saludtlax.rh.siif.layout;

import java.math.BigDecimal;

public class DetalleNominaDTO {
    private Integer idDetalleNomina;
    private Integer idNomina;
    private Integer idEmpleadoDatosLaborales;
    private String idConceptoNomina;
    private BigDecimal importe;
    private Character idOrigenCalculo;

    public DetalleNominaDTO() {
    }

    public DetalleNominaDTO(Integer idDetalleNomina, Integer idNomina, Integer idEmpleadoDatosLaborales, String idConceptoNomina, BigDecimal importe, Character idOrigenCalculo) {
        this.idDetalleNomina = idDetalleNomina;
        this.idNomina = idNomina;
        this.idEmpleadoDatosLaborales = idEmpleadoDatosLaborales;
        this.idConceptoNomina = idConceptoNomina;
        this.importe = importe;
        this.idOrigenCalculo = idOrigenCalculo;
    }

    @MethodOrder(value = 1)
    public Integer getIdDetalleNomina() {
        return idDetalleNomina;
    }
    
    public void setIdDetalleNomina(Integer idDetalleNomina) {
        this.idDetalleNomina = idDetalleNomina;
    }

    @MethodOrder(value = 2)
    public Integer getIdNomina() {
        return idNomina;
    }
    
    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    @MethodOrder(value = 3)
    public Integer getIdEmpleadoDatosLaborales() {
        return idEmpleadoDatosLaborales;
    }
    
    public void setIdEmpleadoDatosLaborales(Integer idEmpleadoDatosLaborales) {
        this.idEmpleadoDatosLaborales = idEmpleadoDatosLaborales;
    }
    
    @MethodOrder(value = 4)
    public String getIdConceptoNomina() {
        return idConceptoNomina;
    }
    
    public void setIdConceptoNomina(String idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }
    
    @MethodOrder(value = 5)
    public BigDecimal getImporte() {
        return importe;
    }
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    @MethodOrder(value = 6)
    public Character getIdOrigenCalculo() {
        return idOrigenCalculo;
    }
    
    public void setIdOrigenCalculo(Character idOrigenCalculo) {
        this.idOrigenCalculo = idOrigenCalculo;
    }
}