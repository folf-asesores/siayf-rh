
package mx.gob.saludtlax.rh.catalogos;

import mx.gob.saludtlax.rh.persistencia.ConceptoPartidaNominaEntity;

public class ConceptoPartidaNominaDTO {

    private Integer id_concepto_partida_nomina;
    private Integer id_concepto_nomina;
    private String descripcion_concepto_nomina;
    private Integer id_nombramiento;
    private String descripcion_nombramiento;
    private String id_partida;
    private String descripcion_partida;

    public ConceptoPartidaNominaEntity toEntity(
            ConceptoPartidaNominaEntity entity, ConceptoPartidaNominaDTO dto) {
        entity.setIdCconceptoPartidaNomina(dto.getId_concepto_partida_nomina());
        entity.setIdConceptoNomina(dto.getId_concepto_nomina());
        entity.setDescripcionConceptoNomina(
                dto.getDescripcion_concepto_nomina());
        entity.setIdNombramiento(dto.getId_nombramiento());
        entity.setDescripcionNombramiento(dto.getDescripcion_nombramiento());
        entity.setIdPartida(dto.getId_partida());
        entity.setDescripcionPartida(dto.getDescripcion_partida());

        return entity;
    }

    public Integer getId_concepto_partida_nomina() {
        return id_concepto_partida_nomina;
    }

    public void setId_concepto_partida_nomina(
            Integer id_concepto_partida_nomina) {
        this.id_concepto_partida_nomina = id_concepto_partida_nomina;
    }

    public Integer getId_concepto_nomina() {
        return id_concepto_nomina;
    }

    public void setId_concepto_nomina(Integer id_concepto_nomina) {
        this.id_concepto_nomina = id_concepto_nomina;
    }

    public String getDescripcion_concepto_nomina() {
        return descripcion_concepto_nomina;
    }

    public void setDescripcion_concepto_nomina(
            String descripcion_concepto_nomina) {
        this.descripcion_concepto_nomina = descripcion_concepto_nomina;
    }

    public Integer getId_nombramiento() {
        return id_nombramiento;
    }

    public void setId_nombramiento(Integer id_nombramiento) {
        this.id_nombramiento = id_nombramiento;
    }

    public String getDescripcion_nombramiento() {
        return descripcion_nombramiento;
    }

    public void setDescripcion_nombramiento(String descripcion_nombramiento) {
        this.descripcion_nombramiento = descripcion_nombramiento;
    }

    public String getId_partida() {
        return id_partida;
    }

    public void setId_partida(String id_partida) {
        this.id_partida = id_partida;
    }

    public String getDescripcion_partida() {
        return descripcion_partida;
    }

    public void setDescripcion_partida(String descripcion_partida) {
        this.descripcion_partida = descripcion_partida;
    }

}
