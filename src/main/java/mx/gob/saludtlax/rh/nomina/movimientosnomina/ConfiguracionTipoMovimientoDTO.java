
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;

public class ConfiguracionTipoMovimientoDTO {

    private Integer idConfiguracion;

    private TipoMovimientoNominaDTO tipoMovimiento;

    private List<ConceptoNominaFederalesDTO> listConceptoNomina = new ArrayList<>();

    public TipoMovimientoNominaDTO getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoNominaDTO tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public List<ConceptoNominaFederalesDTO> getListConceptoNomina() {
        return listConceptoNomina;
    }

    public void setListConceptoNomina(
            List<ConceptoNominaFederalesDTO> listConceptoNomina) {
        this.listConceptoNomina = listConceptoNomina;
    }

    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

}
