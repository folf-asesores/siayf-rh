
package mx.gob.saludtlax.rh.empleados.suplencia;

import java.util.ArrayList;
import java.util.List;

public class ConsultaSuplenciaView {
    private FiltroSuplenciaDTO filtro = new FiltroSuplenciaDTO();
    private List<SuplenteDTO> suplentesAutorizados = new ArrayList<>();
    private List<InfoSuplenciaDTO> suplencias = new ArrayList<>();

    public FiltroSuplenciaDTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroSuplenciaDTO filtro) {
        this.filtro = filtro;
    }

    public List<InfoSuplenciaDTO> getSuplencias() {
        return suplencias;
    }

    public void setSuplencias(List<InfoSuplenciaDTO> suplencias) {
        this.suplencias = suplencias;
    }

    public List<SuplenteDTO> getSuplentesAutorizados() {
        return suplentesAutorizados;
    }

    public void setSuplentesAutorizados(
            List<SuplenteDTO> suplentesAutorizados) {
        this.suplentesAutorizados = suplentesAutorizados;
    }

}
