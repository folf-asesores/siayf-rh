/*
 * DescuentosScriptlet.java
 * Creado el 11/Nov/2016 9:14:06 AM
 * 
 */
package mx.gob.saludtlax.rh.ca.empleado;

import mx.gob.saludtlax.rh.reportes.jasperreports.NumeroALetraScriptlet;
import mx.gob.saludtlax.rh.util.NumeroALetra;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

/**
 * @deprecated Remplazada por {@link NumeroALetraScriptlet}
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
 //TODO: Cambiar del reporte 8001-descuentos.jrxml y luego eliminar la clase.
public class DescuentosScriptlet extends JRDefaultScriptlet {

    /**
     * Ayuda en la transcripción de números a letras.
     * 
     * @param numero el número que será transcrito.
     * @return el número ya transcrito con letras.
     * @throws JRScriptletException en caso de que ocurra un error al realizar 
     * la transcripción o algún otro error en tiempo de ejecución del reporte.
     */
    public String convertirNumeroALetras(Number numero) throws JRScriptletException {
        return NumeroALetra.convertNumberToLetter(numero.toString(), false);
    }
}
