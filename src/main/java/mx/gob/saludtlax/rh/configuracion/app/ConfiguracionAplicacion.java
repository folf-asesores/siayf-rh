/*
 * 
 * ConfiguracionAplicacion.java
 * Creado el Aug 26, 2016 12:54:31 PM
 * 
 */
package mx.gob.saludtlax.rh.configuracion.app;

import java.io.Serializable;
import java.util.Map;
import javax.ejb.Local;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;

/**
 * Esta interface define las configuraciones del sistema. Basado en el concepto
 * de las <a href="https://docs.oracle.com/javase/tutorial/essential/environment/properties.html">propiedades</a> 
 * de java.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface ConfiguracionAplicacion extends Serializable {

    /**
     * Permite guardar una configuración de la aplicación.
     * 
     * <p>La clave de cumplir con este formato:</p>
     * <ol>
     *      <li>Solo están permitidas las letras minúsculas</li>
     *      <li>El primer carácter no puede ser un número</li>
     *      <li>Se permite el guion medio como separdor de palabras</li>
     *      <li>El punto es el separdor entre atributos de una propiedad</li>
     * </ol>
     * 
     * <p>Ejemplo de claves validas:</p>
     * <ul>
     *      <li>modulo.propiedad</li>
     *      <li>modulo-prueba.submodulo.propiedad</li>
     *      <li>siif2.aguinaldo</li>
     * </ul>
     * 
     * @param clave la clave de la configuración de la aplicación.
     * @param valor el valor de la configuración de la aplicación.
     */
    void setConfiguracion(String clave, String valor);

    /**
     * Permite guardar varias configuraciones de la aplicación.
     * 
     * @param configuraciones las configuraciones a guardar.
     */
    void setConfiguraciones(Map<String, String> configuraciones);

    /**
     * Este permite obtener el contenido de una configuración de la aplicación.
     * 
     * @param clave la clave de la configuración de la aplicación.
     * @return el valor de la configuración de la aplicación.
     * @throws ReglaNegocioException en caso de que la clave no exista.
     */
    String getConfiguracion(String clave);

    /**
     * Obtiene todas las configuraciones de la aplicación en caso de que no se 
     * envie nungún parametro; si se envia alguno se traen todas las 
     * configuraciones que conincidan con dichas claves.
     * 
     * @param claves las claves a buscar o en su caso nada para obtener todas 
     *               las configuraciones.
     * @return un {@link Map} con las configuraciones.
     */
    Map<String, String> getConfiguraciones(String ... claves);

}
