/*
 *  RegimenContratacionTrabajadorRepository.java
 *  Creado el May 25, 2016 2:08:46 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.util.List;

/**
 * Esta clase contiene las consultas necesarias para el catálogo del SAT Régimen
 * de Contratación del trabajador.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class RegimenContratacionTrabajadorRepository extends GenericRepository<RegimenContratacionTrabajadorEntity, Integer>{

    private final static String OBTENER_LISTA_REGIMEN_CONTRATACION_TRABAJADORES = 
            "from RegimenContratacionTrabajadorEntity as rct";
    
    public List<RegimenContratacionTrabajadorEntity> obtenerRegimenContratacionTrabajadores() {
        List<RegimenContratacionTrabajadorEntity> entidades = 
                em.createQuery(OBTENER_LISTA_REGIMEN_CONTRATACION_TRABAJADORES, RegimenContratacionTrabajadorEntity.class)
                .getResultList();
        return entidades;
    }
    
}