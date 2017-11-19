/*
 * AperturaNominaRfcEJB.java
 * Creado el 02/Jan/2017 5:32:24 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class AperturaNominaRfcEJB implements AperturaNominaRfc {

    private static final long serialVersionUID = -4033186613542883700L;
    private static final Logger LOGGER = Logger.getLogger(AperturaNominaRfcEJB.class.getName());

    @Inject
    private AperturaNominaRfcService aperturaNominaRfcService;
    @Inject
    private ProductosNominaService productoNominaService;

    @Override
    public ProductoNominaDTO abrirProductoNomina(Integer idProductoNomina, byte[] archivoRfc) {
        if (archivoRfc == null) {
            throw new ValidacionException("El archivo no puede ser nulo.", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        return abrirProductoNomina(idProductoNomina, obtenerListaRfc(archivoRfc), 0);
    }

    @Override
    public ProductoNominaDTO abrirProductoNomina(Integer idProductoNomina, List<String> listaRfc, Integer idBitacora) {
        if (ValidacionUtil.esMenorQueUno(idProductoNomina)) {
            throw new ValidacionException("El ID del pruducto de nómina no puede ser cero o nulo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        if (listaRfc == null || listaRfc.isEmpty()) {
            throw new ValidacionException("La lista de RFC no puede ser nula o vacia.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        ProductoNominaDTO productoNomina = productoNominaService.obtenerProductoNomina(idProductoNomina);
        aperturaNominaRfcService.abrirProductoNomina(productoNomina, listaRfc, idBitacora);
        productoNomina.setIdEstatusProductoNomina(2);
        productoNominaService.aplicarConsecutivosProductoNomina(productoNomina);
        productoNominaService.actualizarProductoNomina(productoNomina);
        return productoNominaService.obtenerProductoNomina(idProductoNomina);
    }

    @Override
    public Integer obtenerIdBitacora(Integer idUsuario) {
        if (ValidacionUtil.esMenorQueUno(idUsuario)) {
            throw new ValidacionException("El ID del usuario no puede ser cero o nulo", ValidacionCodigoError.NUMERO_NEGATIVO);
        }
        return aperturaNominaRfcService.obtenerIdBitacora(idUsuario);
    }

    @Override
    public AperturaNominaRfcBitacoraDTO obtenerBitacora(Integer idBitacora) {
        return aperturaNominaRfcService.obtenerBitacora(idBitacora);
    }

    @Override
    public void registrarEnBitacoraEventoInformacion(Integer idBitacora, String mensaje) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.INFORMACION, mensaje);
    }

    @Override
    public void registrarEnBitacoraEventoInformacion(Integer idBitacora, String patron, Object... argumentos) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.INFORMACION, patron, argumentos);
    }

    @Override
    public void registrarEnBitacoraEventoAdvertencia(Integer idBitacora, String mensaje) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.ADVERTENCIA, mensaje);
    }

    @Override
    public void registrarEnBitacoraEventoAdvertencia(Integer idBitacora, String patron, Object... argumentos) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.ADVERTENCIA, patron, argumentos);
    }

    @Override
    public void registrarEnBitacoraEventoError(Integer idBitacora, String mensaje) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.ERROR, mensaje);
    }

    @Override
    public void registrarEnBitacoraEventoError(Integer idBitacora, String patron, Object... argumentos) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, AperturaNominaRfcBitacoraCategoria.ERROR, patron, argumentos);
    }

    @Override
    public void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String mensaje) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, categoria, mensaje);
    }

    @Override
    public void registrarEnBitacoraEvento(Integer idBitacora, AperturaNominaRfcBitacoraCategoria categoria, String patron, Object... argumentos) {
        aperturaNominaRfcService.registrarEnBitacoraEvento(idBitacora, categoria, patron, argumentos);
    }

    private List<String> obtenerListaRfc(byte[] archivoRfc) {
        List<String> listaRfc = new ArrayList<>();
        int linea = 1;

        try (ByteArrayInputStream bis = new ByteArrayInputStream(archivoRfc); BufferedReader br = new BufferedReader(new InputStreamReader(bis))) {
            String rfc;
            while ((rfc = br.readLine()) != null) {
                if (ValidacionUtil.validarRfc(rfc)) {
                    listaRfc.add(rfc);
                } else {
                    LOGGER.debugv("El formato del RFC \"{0}\" no es valido, en la línea {1}.", rfc, linea);
                }
                linea++;
            }
        } catch (IOException ex) {
            LOGGER.error(ex);
        }

        return listaRfc;
    }
}
