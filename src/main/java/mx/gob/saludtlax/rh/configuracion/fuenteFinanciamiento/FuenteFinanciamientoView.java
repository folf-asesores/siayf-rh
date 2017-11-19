
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.List;

public class FuenteFinanciamientoView {
    private List<FuenteFinanciamientoDTO> listFuenteFinanciamiento;
    private FuenteFinanciamientoDTO nuevaFuenteFinanciamiento = new FuenteFinanciamientoDTO();

    private boolean mostrarRegistroFuenteFinanciamiento;

    public FuenteFinanciamientoDTO getNuevaFuenteFinanciamiento() {
        return nuevaFuenteFinanciamiento;
    }

    public void setNuevaFuenteFinanciamiento(FuenteFinanciamientoDTO nuevaFuenteFinanciamiento) {
        this.nuevaFuenteFinanciamiento = nuevaFuenteFinanciamiento;
    }

    public List<FuenteFinanciamientoDTO> getListFuenteFinanciamiento() {
        return listFuenteFinanciamiento;
    }

    public void setListFuenteFinanciamiento(List<FuenteFinanciamientoDTO> listFuenteFinanciamiento) {
        this.listFuenteFinanciamiento = listFuenteFinanciamiento;
    }

    public boolean isMostrarRegistroFuenteFinanciamiento() {
        return mostrarRegistroFuenteFinanciamiento;
    }

    public void setMostrarRegistroFuenteFinanciamiento(boolean mostrarRegistroFuenteFinanciamiento) {
        this.mostrarRegistroFuenteFinanciamiento = mostrarRegistroFuenteFinanciamiento;
    }

}