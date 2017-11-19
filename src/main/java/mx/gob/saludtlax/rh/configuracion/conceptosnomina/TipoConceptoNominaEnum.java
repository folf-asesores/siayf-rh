
package mx.gob.saludtlax.rh.configuracion.conceptosnomina;

public enum TipoConceptoNominaEnum {
    TODOS(0, "Todos"), PERCEPCION(1, "Percepción"), DEDUCCION(2, "Deducción"), DEDUCCION_PRESAMO(3, "Deducción Prestamo");

    private Integer idTipoConceptoNomina;
    private String tipoConceptoNomina;

    private TipoConceptoNominaEnum(Integer idTipoConceptoNomina, String tipoConceptoNomina) {
        this.idTipoConceptoNomina = idTipoConceptoNomina;
        this.tipoConceptoNomina = tipoConceptoNomina;
    }

    public Integer getIdTipoConceptoNomina() {
        return idTipoConceptoNomina;
    }

    public String getTipoConceptoNomina() {
        return tipoConceptoNomina;
    }

    public static TipoConceptoNominaEnum get(Integer tipo) {
        TipoConceptoNominaEnum val;
        if (tipo.intValue() == 1) {
            val = PERCEPCION;
        } else if (tipo.intValue() == 2) {
            val = DEDUCCION;
        } else if (tipo.intValue() == 3) {
            val = DEDUCCION_PRESAMO;
        } else {
            val = TODOS;
        }
        return val;
    }
}