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
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class RegimenContratacionTrabajadorRepository extends
        GenericRepository<RegimenContratacionTrabajadorEntity, Integer> {

    /**
     *
     */
    private static final long serialVersionUID = -4899793257679677121L;
    private final static String OBTENER_LISTA_REGIMEN_CONTRATACION_TRABAJADORES = "from RegimenContratacionTrabajadorEntity as rct";

    public List<RegimenContratacionTrabajadorEntity> obtenerRegimenContratacionTrabajadores() {
        List<RegimenContratacionTrabajadorEntity> entidades = em
                .createQuery(OBTENER_LISTA_REGIMEN_CONTRATACION_TRABAJADORES,
                        RegimenContratacionTrabajadorEntity.class)
                .getResultList();
        return entidades;
    }

}