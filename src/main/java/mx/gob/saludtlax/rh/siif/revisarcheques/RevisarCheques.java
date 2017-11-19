
package mx.gob.saludtlax.rh.siif.revisarcheques;

import java.util.List;

public interface RevisarCheques {

    void crearRevisarCheques(RevisarChequesDTO dto);

    List<RevisarChequesDTO> obtenerListaRevisarCheques();

    List<RevisarChequesDTO> obtenerListaRevisarCheques(String periodo,
            Integer anio);

    List<RevisarChequesDTO> obtenerListaRevisarChequesAvanzada();

}
