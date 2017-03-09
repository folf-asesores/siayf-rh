package mx.gob.saludtlax.rh.siif.layout;

import java.math.BigDecimal;

public class DetallePagoNominaDTO {
    private Integer idPagoNomina;
    private Integer idNomina;
    private Integer idEmpeladoDatosLaborales;
    private String noChequeCuenta;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;

    public DetallePagoNominaDTO() {
    }

    public DetallePagoNominaDTO(Integer idPAgoNomina, Integer idNomina, Integer idEmpeladoDatosLaborales, String noChequeCuenta, BigDecimal percepciones, BigDecimal deducciones, BigDecimal neto) {
        this.idPagoNomina = idPAgoNomina;
        this.idNomina = idNomina;
        this.idEmpeladoDatosLaborales = idEmpeladoDatosLaborales;
        this.noChequeCuenta = noChequeCuenta;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.neto = neto;
    }

    @MethodOrder(value = 1)
    public Integer getIdPagoNomina() {
        return idPagoNomina;
    }

    public void setIdPagoNomina(Integer idPagoNomina) {
        this.idPagoNomina = idPagoNomina;
    }

    @MethodOrder(value = 2)
    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    @MethodOrder(value = 3)
    public Integer getIdEmpeladoDatosLaborales() {
        return idEmpeladoDatosLaborales;
    }

    public void setIdEmpeladoDatosLaborales(Integer idEmpeladoDatosLaborales) {
        this.idEmpeladoDatosLaborales = idEmpeladoDatosLaborales;
    }

    @MethodOrder(value = 4)
    public String getNoChequeCuenta() {
        return noChequeCuenta;
    }

    public void setNoChequeCuenta(String noChequeCuenta) {
        this.noChequeCuenta = noChequeCuenta;
    }

    @MethodOrder(value = 5)
    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    @MethodOrder(value = 6)
    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    @MethodOrder(value = 7)
    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }
}