/*
 * AperturaNominaRfc.java
 * Creado el 02/Jan/2017 5:32:25 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Local
public interface AperturaNominaRfc extends Serializable {

    /**
     * Permite aperturar un producto de nómina con una lista de RFC en un
     * archivo.
     *
     * @param idProductoNomina el ID del producto de nómina que será aperturado.
     * @param archivoRfc un arreglo de bytes que representa un archivo de texto
     * plano con los RFC de quienes estarán en la nómina una vez aperturada.
     * @return El producto de nómina ya aperturado.
     */
    ProductoNominaDTO abrirProductoNomina(Integer idProductoNomina,
            byte[] archivoRfc);

    /**
     * Permite aperturar un producto de nómina con una lista de RFC.
     *
     * @param idProductoNomina el ID del producto de nómina que será aperturado.
     * @param listaRfc un arreglo de RFC de quienes estarán en la nómina una vez
     *                 aperturada.
     * @param idBitacora el ID de la bitácora.
     * @return El producto de nómina ya aperturado.
     */
    ProductoNominaDTO abrirProductoNomina(Integer idProductoNomina, List<String> listaRfc, Integer idBitacora);
    
    /**
     * Permite obtener un nuevo ID de bitacora.
     * @param idUsuario el ID del usaurio propietario de la bitácora.
     * @return el ID de la bitacora.
     */
    Integer obtenerIdBitacora(Integer idUsuario);
    
    /**
     * Permite obtener la bitácora de una apertura por medio de su ID.
     * @param idBitacora el ID de la bitácora a buscar.
     * @return la bitácora de una apertura.
     */
    AperturaNominaRfcBitacoraDTO obtenerBitacora(Integer idBitacora);
    
    /**
     * Permite registrar un evento informativo en la bitacora.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param mensaje el mensaje con el detalle a informar. 
     */
    void registrarEnBitacoraEventoInformacion(Integer idBitacora, String mensaje);
    
    /**
     * Permite registrar un evento informativo en la bitacora usando un patrón
     * de mensaje formateado.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param patron el patrón de formateo.
     * @param argumentos los argumentos del patrón a formatear.
     */
    void registrarEnBitacoraEventoInformacion(Integer idBitacora, String patron, Object... argumentos);
    
    /**
     * Permite registrar un evento de advertencia en la bitacora.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param mensaje el mensaje con el detalle a informar. 
     */
    void registrarEnBitacoraEventoAdvertencia(Integer idBitacora, String mensaje);
    
    /**
     * Permite registrar un evento de advertencia en la bitacora usando un 
     * patrón de mensaje formateado.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param patron el patrón de formateo.
     * @param argumentos los argumentos del patrón a formatear.
     */
    void registrarEnBitacoraEventoAdvertencia(Integer idBitacora, String patron, Object... argumentos);
    
    /**
     * Permite registrar un evento de error en la bitacora.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param mensaje el mensaje con el detalle a informar. 
     */
    void registrarEnBitacoraEventoError(Integer idBitacora, String mensaje);
    
    /**
     * Permite registrar un evento de error en la bitacora usando un patrón de 
     * mensaje formateado.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param patron el patrón de formateo.
     * @param argumentos los argumentos del patrón a formatear.
     */
    void registrarEnBitacoraEventoError(Integer idBitacora, String patron, Object... argumentos);
    
    /**
     * Permite registrar un evento en la bitacora.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param categoria la categoria del evento.
     * @param mensaje el mensaje con el detalle a informar. 
     */
    void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String mensaje);
    
    /**
     * Permite registrar un evento en la bitacora usando un usando un patrón de 
     * mensaje formateado.
     * 
     * @param idBitacora el ID de la bitacora en la que se registrara el evento.
     * @param categoria la categoria del evento.
     * @param patron el patrón de formateo.
     * @param argumentos los argumentos del patrón a formatear. 
     */
    void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String patron, Object... argumentos);
}
