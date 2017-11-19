
package mx.gob.saludtlax.rh.catalogos;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ConceptoPartidaNominaEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleRetencionEntity;
import mx.gob.saludtlax.rh.persistencia.WebServicesSifRepository;

@Stateless
public class CatalogosWsEJB {

    @Inject
    WebServicesSifRepository webServicesSifRepository;

    public void guardarConceptosNominaPartida(
            List<ConceptoPartidaNominaDTO> listaConceptos) {
        System.out.println("limpiando ... ");
        webServicesSifRepository.limpiarTablaConceptoPartidaNomina();

        for (ConceptoPartidaNominaDTO dto : listaConceptos) {
            ConceptoPartidaNominaEntity entity = new ConceptoPartidaNominaEntity();
            entity = dto.toEntity(entity, dto);
            webServicesSifRepository.guardarConceptoPartida(entity);
        }

        System.out.println("finalizo de guardar ..");

    }

    public void guardarDetalleRetenciones(
            List<RetencionDTO> listaElementosRetenciones) {
        System.out.println("limpiando...");
        webServicesSifRepository.limpiarTablaDetalleRetenciones();

        for (RetencionDTO dto : listaElementosRetenciones) {

            DetalleRetencionEntity entity = new DetalleRetencionEntity();

            entity = dto.toEntity(entity, dto);

            webServicesSifRepository.guardarDetalleRetencion(entity);
            ;
        }
        System.out.println("finalizo de guardar ..");
    }

}
