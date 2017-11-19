
package mx.gob.saludtlax.rh.siif.revisarcheques;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RevisarChequesEJB implements RevisarCheques, Serializable {

    private static final long serialVersionUID = 648837261243804947L;

    @Inject
    private RevisarChequesServices revisarChequesService;

    @Override
    public List<RevisarChequesDTO> obtenerListaRevisarCheques() {
        return revisarChequesService.obtenerListaRevisarCheques();
    }

    @Override
    public void crearRevisarCheques(RevisarChequesDTO dto) {

    }

    @Override
    public List<RevisarChequesDTO> obtenerListaRevisarChequesAvanzada() {
        return revisarChequesService.obtenerListaRevisarChequesAvanzada();
    }

    @Override
    public List<RevisarChequesDTO> obtenerListaRevisarCheques(String periodo, Integer anio) {
        return revisarChequesService.obtenerListaRevisarCheques(periodo, anio);
    }

}
