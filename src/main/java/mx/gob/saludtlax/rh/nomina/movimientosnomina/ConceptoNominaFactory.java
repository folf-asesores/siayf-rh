
package mx.gob.saludtlax.rh.nomina.movimientosnomina;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaEntity;
import mx.gob.saludtlax.rh.persistencia.ConceptoNominaFederalesEntity;

public class ConceptoNominaFactory {

    public static ConceptoNominaDTO crearConceptoNominaDTO(ConceptoNominaEntity entity, ConceptoNominaDTO dto) {
        dto.setAguinaldo(entity.getAguinaldo());
        dto.setAlta(entity.getAlta());
        dto.setBase(entity.getBase());
        dto.setCategoriaSAT(entity.getCategoriaSAT() == null ? null : entity.getCategoriaSAT().getCategoriaSAT());
        dto.setClave(entity.getClave());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEstatusConceptoNomina(entity.getEstatusConceptoNomina() == null ? null : entity.getEstatusConceptoNomina().getEstatus());
        dto.setFormula(entity.getFormula());
        dto.setIdCategoriaSAT(entity.getCategoriaSAT() == null ? null : entity.getCategoriaSAT().getIdCategoriaSAT());
        dto.setIdConceptoNomina(entity.getIdConceptoNomina());
        dto.setIdEstatusConceptoNomina(entity.getEstatusConceptoNomina() == null ? null : entity.getEstatusConceptoNomina().getIdEstatusConceptoNomina());
        dto.setObservacion(entity.getObservacion());
        dto.setRetroactivo(entity.getRetroactivo());
        dto.setTipo(entity.getTipo());
        dto.setTratamiento(entity.getTratamiento());
        return dto;
    }

    public static ConceptoNominaFederalesDTO crearConceptoNominaFederalesDTO(ConceptoNominaFederalesEntity entity, ConceptoNominaFederalesDTO dto) {
        dto.setAguinaldo(entity.getAguinaldo());
        dto.setAlta(entity.getAlta());
        dto.setBase(entity.getBase());
        dto.setCategoriaSAT(entity.getCategoriaSAT() == null ? null : entity.getCategoriaSAT().getCategoriaSAT());
        dto.setClave(entity.getClave());
        dto.setDescripcion(entity.getDescripcion());
        dto.setEstatusConceptoNomina(entity.getEstatusConceptoNomina() == null ? null : entity.getEstatusConceptoNomina().getEstatus());
        dto.setFormula(entity.getFormula());
        dto.setIdCategoriaSAT(entity.getCategoriaSAT() == null ? null : entity.getCategoriaSAT().getIdCategoriaSAT());
        dto.setIdConceptoNomina(entity.getIdConceptoNomina());
        dto.setIdEstatusConceptoNomina(entity.getEstatusConceptoNomina() == null ? null : entity.getEstatusConceptoNomina().getIdEstatusConceptoNomina());
        dto.setObservacion(entity.getObservacion());
        dto.setRetroactivo(entity.getRetroactivo());
        dto.setTipo(entity.getTipo());
        dto.setTratamiento(entity.getTratamiento());
        return dto;
    }

}
