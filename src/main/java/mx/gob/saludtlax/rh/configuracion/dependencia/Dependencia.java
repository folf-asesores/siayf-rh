/*
 * 
 * Dependencia.java
 * Creado el Jul 31, 2016 8:38:54 AM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.dependencia;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface Dependencia {

    DependenciaDTO actualizarDependencia(DependenciaDTO dto);

    List<String> consultarDescripcionDependenciasPorCriterio(String consulta);

    Integer consultarIdDependenciaPorDescripcion(String descripcion);

    DependenciaDTO crearDependencia(DependenciaDTO dto);

    void eliminarDependencia(DependenciaDTO dto);

    DependenciaDTO nuevoDependencia();

    DependenciaDTO obtenerDependencia(DependenciaDTO dependencia);

    List<DependenciaDTO> obtenerDependenciaLista();
    
}
