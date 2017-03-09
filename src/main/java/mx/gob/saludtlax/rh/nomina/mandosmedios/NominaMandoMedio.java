/*
 * NominaMandoMedio.java
 * Creado el 29/Nov/2016 1:33:04 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.mandosmedios;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 * Esta interfaz define las operaciones de negocios que se realizarán con las 
 * nominas de mandos medios.
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Local
public interface NominaMandoMedio extends Serializable {
    
    /**
     * Permite crear una nueva nomina de mando medio.
     * 
     * @param nominaMandoMedio la informacion de la nomina del mando medio.
     */
    void crear(NominaMandoMedioDTO nominaMandoMedio);

    /**
     * Permite obtener la información de una nomina de mando medio por medio de 
     * su <code>ID</code>.
     * 
     * @param id el <code>ID</code> de la nomina de mando medio a buscar.
     * @return la información del mando medio.
     */
    NominaMandoMedioDTO obtenerPorId(Integer id);

    /**
     * Consulta todos las nominas de mandos medios disponibles.
     * 
     * @return una lista con todos los mandos medios que están disponibles.
     */
    List<NominaMandoMedioDTO> consultarTodos();

    /**
     * Permite actualizar la información de una nomina de mando medio.
     * 
     * @param nominaMandoMedio la información del mando medio que se 
     * actualizará.
     */
    void actualizar(NominaMandoMedioDTO nominaMandoMedio);

    /**
     * Permite eliminar la nomina de un mando medio.
     * @param id el <code>ID</code> de la nomina de mando medio a eliminar.
     */
    void eliminar(Integer id);

    
}
