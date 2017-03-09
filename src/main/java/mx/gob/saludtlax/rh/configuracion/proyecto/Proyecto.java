/*
 * Proyecto.java
 * Creado el 23/07/2016 09:37:36 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.proyecto;

import java.util.List;
import javax.ejb.Local;

/**
 * <p>Esta clase define las operaciones que se puede realizar con los 
 * proyectos.</p>
 * 
 * <p><strong>Reglas de negocio:</strong></p>
 * <ul>
 * <li>El nombre debe ser unico por ejercicio fiscal.</li>
 * <li>No se pueden registrar proyectos de ejercicios fiscales mayores a un año
 * del ejercicio fiscal actual.<br /> 
 * <strong>Ejemplo:</strong><br /> 
 * Si el ejercicio fiscal fuera 2016 entonces solo se permitirán los ejercicios
 * fiscales 2016 y 2017.</li>
 * </ul>
 * 
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Local
public interface Proyecto {
    
    /**
     * Permite obtener un proyecto por medio de su ID.
     * 
     * @param idProyecto el ID del proyecto.
     * @return un proyecto.
     */
    ProyectoDTO obtenerPorId(int idProyecto);
    
    /**
     * Permite crear un nuevo proyecto.
     * 
     * @param proyecto el proyecto a crear.
     * @return el ID del proyecto una vez creado en el almacen de datos.
     */
    int crear(ProyectoDTO proyecto);
    
    /**
     * Permite actualizar la información de un proyecto ya existen 
     * en el almacen de datos.
     * 
     * @param proyecto el proyecto a actualizar.
     */
    void actualizar(ProyectoDTO proyecto);

    /**
     * Consulta y devuelve los proyectos que pertenecen a un 
     * ejercicio fiscal.
     * 
     * @param ejercicioFiscal el ejercicio fiscal a consultar.
     * @return una lista con los proyectos que pertenecen al ejercicio fiscal.
     */
    List<ProyectoDTO> consultarProyectosPorEjercicioFiscal(int ejercicioFiscal);
    
    /**
     * Permite eliminar un proyecto del almacen de datos por medio de su ID.
     * 
     * @param idProyecto el ID del proyecto a elimnar.
     */
    void eliminar(int idProyecto);
}
