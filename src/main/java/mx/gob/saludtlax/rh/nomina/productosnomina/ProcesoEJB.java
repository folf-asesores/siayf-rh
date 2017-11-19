/*
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoEntity;
import mx.gob.saludtlax.rh.persistencia.ProcesoCalculoRepository;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 01/12/2016 17:07:38
 */
@Stateless
public class ProcesoEJB {
    @Inject
    private ProcesoCalculoRepository procesoCalculoRepository;

    public ProcesoDTO obtenerProceso(int idProceso) {
        ProcesoCalculoEntity procesoCalculo = procesoCalculoRepository.obtenerPorId(idProceso);
        ProcesoDTO proceso = new ProcesoDTO();
        if (procesoCalculo != null) {
            proceso.setEnProceso(procesoCalculo.isEnProceso());
            proceso.setNumeroProcesado(procesoCalculo.getNumeroProcesado());
        }
        return proceso;
    }

    public void detenerProceso(int idProceso) {
        ProcesoCalculoEntity procesoCalculo = procesoCalculoRepository.obtenerPorId(idProceso);

        procesoCalculo.setEnProceso(false);
        procesoCalculo.setNumeroProcesado(0);
        procesoCalculoRepository.actualizar(procesoCalculo);

    }

}