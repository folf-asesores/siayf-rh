
package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.math.BigDecimal;

public class ArchivoDTO {

    private String rfc;
    private String capitulo;
    private BigDecimal monto;
    private Integer anio;
    private Integer numero_quincena;
    private String clave_partida;
    private String tipo_movimiento;

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getNumero_quincena() {
        return numero_quincena;
    }

    public void setNumero_quincena(Integer numero_quincena) {
        this.numero_quincena = numero_quincena;
    }

    public String getClave_partida() {
        return clave_partida;
    }

    public void setClave_partida(String clave_partida) {
        this.clave_partida = clave_partida;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

}
