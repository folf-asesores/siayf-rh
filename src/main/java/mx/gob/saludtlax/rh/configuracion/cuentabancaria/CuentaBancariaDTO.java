
package mx.gob.saludtlax.rh.configuracion.cuentabancaria;

public class CuentaBancariaDTO {
    private Integer idCuentaBancaria;
    private String banco;
    private String numeroCuenta;
    private String descripcion;
    private String fuenteFinanciamiento;
    private Integer ejercicioFiscal;
    private Integer claveCuenta;

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    public Integer getClaveCuenta() {
        return claveCuenta;
    }

    public void setClaveCuenta(Integer claveCuenta) {
        this.claveCuenta = claveCuenta;
    }

}
