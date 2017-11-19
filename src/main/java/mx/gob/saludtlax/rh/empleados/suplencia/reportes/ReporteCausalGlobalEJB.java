
package mx.gob.saludtlax.rh.empleados.suplencia.reportes;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.centroresponsabilidad.CentroResponsabilidadService;
import mx.gob.saludtlax.rh.configuracion.ejerciciofiscal.EjercicioFiscalService;

/**
 *
 * @author José Pablo
 *
 */
@Stateless
public class ReporteCausalGlobalEJB
        implements ReporteCausalGlobal, Serializable {
    private static final long serialVersionUID = 4161800114423833582L;

    @Inject
    private EjercicioFiscalService ejercicioFiscalService;
    @Inject
    private CentroResponsabilidadService centroResponsabilidadService;
    @Inject
    private ReporteCausalGlobalService reporteCausalGlobalService;

    @Override
    public List<ReporteCausalGlobalDTO> obtenerListaCausalGlobal(
            Integer numeroQuincena, Integer ejercicioFiscal,
            Integer idCentroResponsabilidad, String lugar) {

        return reporteCausalGlobalService.obtenerListaCausalGlobal(
                numeroQuincena, ejercicioFiscal, idCentroResponsabilidad,
                lugar);
    }

    @Override
    public List<SelectItem> listaEjercicioFiscal() {
        return ejercicioFiscalService.listaEjercicioFiscalItems();
    }

    @Override
    public List<SelectItem> listaCentroResponsabilidad() {
        return centroResponsabilidadService.obtenerCentroResponsabilidadItems();
    }
}
