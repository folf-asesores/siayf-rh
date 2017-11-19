
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.util.List;

import mx.gob.saludtlax.rh.configuracion.cuentabancaria.CuentaBancariaDTO;
import mx.gob.saludtlax.rh.configuracion.tiponomina.TipoNominaDTO;
import mx.gob.saludtlax.rh.siif.PaqueteEntradaContratoDTO;
import mx.gob.saludtlax.rh.siif.SiifBitacoraDTO;

public class ReportarSiifProsperaView {

    private PaqueteEntradaContratoDTO paqueteEntrada = new PaqueteEntradaContratoDTO();
    private List<CuentaBancariaDTO> cuentaBancariaList;
    private List<TipoNominaDTO> tipoNominaList;
    private SiifBitacoraDTO siifBitacoraProcesada;

    public void panelPrincipal() {
    }

    public PaqueteEntradaContratoDTO getPaqueteEntrada() {
        return paqueteEntrada;
    }

    public void setPaqueteEntrada(PaqueteEntradaContratoDTO paqueteEntrada) {
        this.paqueteEntrada = paqueteEntrada;
    }

    public List<CuentaBancariaDTO> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancariaDTO> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    public List<TipoNominaDTO> getTipoNominaList() {
        return tipoNominaList;
    }

    public void setTipoNominaList(List<TipoNominaDTO> tipoNominaList) {
        this.tipoNominaList = tipoNominaList;
    }

    public SiifBitacoraDTO getSiifBitacoraProcesada() {
        return siifBitacoraProcesada;
    }

    public void setSiifBitacoraProcesada(SiifBitacoraDTO siifBitacoraProcesada) {
        this.siifBitacoraProcesada = siifBitacoraProcesada;
    }

}