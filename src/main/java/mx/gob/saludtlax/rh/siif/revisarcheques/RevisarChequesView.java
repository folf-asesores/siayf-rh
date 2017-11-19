
package mx.gob.saludtlax.rh.siif.revisarcheques;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RevisarChequesView {

    //private static final long serialVersionUID = -4983428741390069317L;

    private List<RevisarChequesDTO> obtenerListaRevisarCheques = new ArrayList<>();

    private RevisarChequesDTO revisarChequesDTO = new RevisarChequesDTO();

    private String accionRevisarCheques = "Revisar";

    private boolean mostrarResultadoConsulta;

    public boolean isMostrarResultadoConsulta() {
        return mostrarResultadoConsulta;
    }

    public void setMostrarResultadoConsulta(boolean mostrarResultadoConsulta) {
        this.mostrarResultadoConsulta = mostrarResultadoConsulta;
    }

    public List<RevisarChequesDTO> getObtenerListaRevisarChequesDTOs() {
        return obtenerListaRevisarCheques;
    }

    public void setObtenerListaRevisarChequesDTOs(
            List<RevisarChequesDTO> obtenerListaRevisarCheques) {
        this.obtenerListaRevisarCheques = obtenerListaRevisarCheques;
    }

    public RevisarChequesDTO getRevisarChequesDTO() {
        return revisarChequesDTO;
    }

    public void setRevisarChequesDTO(RevisarChequesDTO revisarCheques) {
        revisarChequesDTO = revisarCheques;
    }

    public String getAccionRevisarCheques() {
        return accionRevisarCheques;
    }

    public void setAccionRevisarCheques(String accionRevisarCheques) {
        this.accionRevisarCheques = accionRevisarCheques;
    }

}
