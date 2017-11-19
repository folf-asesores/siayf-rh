
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.util.Date;

public class PagoNominaDTO {
    private Integer idPagoNomina;
    private Integer idProductoNomina;
    private Integer idFuenteFinanciamiento;
    private String fuenteFinanciamiento;
    private Integer idSubfuenteFinanciamiento;
    private String subfuenteFinanciamiento;
    private Integer idBanco;
    private String banco;
    private Integer idCuentaBancaria;
    private String cuentaBancaria;
    private Date fechaPago;
    private String listaRfc;
    private Boolean aplicarPadron;

    public Integer getIdPagoNomina() {
        return idPagoNomina;
    }

    public void setIdPagoNomina(Integer idPagoNomina) {
        this.idPagoNomina = idPagoNomina;
    }

    public Integer getIdProductoNomina() {
        return idProductoNomina;
    }

    public void setIdProductoNomina(Integer idProductoNomina) {
        this.idProductoNomina = idProductoNomina;
    }

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public String getFuenteFinanciamiento() {
        return fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(String fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(
            Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public String getSubfuenteFinanciamiento() {
        return subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(String subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Integer idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getListaRfc() {
        return listaRfc;
    }

    public void setListaRfc(String listaRfc) {
        this.listaRfc = listaRfc;
    }

    public Boolean getAplicarPadron() {
        return aplicarPadron;
    }

    public void setAplicarPadron(Boolean aplicarPadron) {
        this.aplicarPadron = aplicarPadron;
    }
}