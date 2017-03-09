/*
 * NumeroALetraScriptlet.java
 * Creado el 28/Nov/2016 9:33:55 AM
 * 
 */
package mx.gob.saludtlax.rh.reportes.jasperreports;

import java.math.BigDecimal;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class NumeroALetraScriptlet extends JRDefaultScriptlet {

    private static final Logger LOGGER = Logger.getLogger(NumeroALetraScriptlet.class.getName());

    /**
     * Ayuda en la transcripción de números a letras como una cantidad en
     * moneda.
     *
     * @param numero el número que será transcrito.
     * @return el número ya transcrito con letras.
     * @throws JRScriptletException en caso de que ocurra un error al realizar
     * la transcripción o algún otro error en tiempo de ejecución del reporte.
     */
    public String convertirNumeroALetras(Number numero) throws JRScriptletException {
        return convertirNumeroALetras(numero, true);
    }
    
    /**
     * Ayuda en la transcripción de números a letras donde el patrón moneda se
     * decide por medio del parámetro <code>moneda</code>.
     *
     * @param numero el número que será transcrito.
     * @param moneda si es <code>true</code> la cadena que se retornara incluira
     * información de la modena en caso contrario solo se devuelve una cadena
     * con la cantidad escrita en con letras.
     * @return el número ya transcrito con letras.
     * @throws JRScriptletException en caso de que ocurra un error al realizar
     * la transcripción o algún otro error en tiempo de ejecución del reporte.
     */
    public String convertirNumeroALetras(Number numero, boolean moneda) throws JRScriptletException {
        String asLetters = "";

        try {
            if (numero instanceof BigDecimal) {
                LOGGER.debugv("Evaluandolo como BigDecimal {0}", numero);
                asLetters = NumeroALetra.convertNumberToLetter((BigDecimal) numero, moneda);
            } else {
                LOGGER.debugv("Evaluandolo como String {0}", numero);
                asLetters = NumeroALetra.convertNumberToLetter(numero.toString(), moneda);
            }
        } catch (NumberFormatException ex) {
            LOGGER.errorv("Error de conversión: {0}", ex);
        } catch (Exception ex) {
            LOGGER.errorv("Error insperado al convertir: {0}",ex);
        }

        LOGGER.debugv("Resultado de la conversion {0}", asLetters);
        return asLetters;
    }

}
