
package mx.gob.saludtlax.rh.empleados.suplencia.reportes;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 *
 * @author José Pablo
 *
 */
public interface ReporteCausalGlobal {

    public List<ReporteCausalGlobalDTO> obtenerListaCausalGlobal(Integer numeroQuincena, Integer ejercicioFiscal, Integer idCentroResponsabilidad,
            String lugar);

    public List<SelectItem> listaEjercicioFiscal();

    public List<SelectItem> listaCentroResponsabilidad();

}
