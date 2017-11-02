/*
 * NumeroUtil.java
 * Creado el Jun 28, 2016 2:15:26 PM
 * 
 */
package mx.gob.saludtlax.rh.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class NumeroUtil {

	private NumeroUtil() {
	}

	/**
	 * <p>
	 * Este método permite convertir en formato decimal, reduciendo la
         * matiza a dos digitos en caso de ser un número decimal; en caso de ser
         * un número entero le añade dos digitos en la matiza.
	 * </p>
	 * 
	 * <p>
	 * Por ejemplo:<br>
	 * <ul>
	 * <li>1882 <strong>devuelve</strong> 1882.00</li>
	 * <li>18.534534582 <strong>devuelve</strong> 18.53</li>
	 * </ul>
	 * </p>
	 * 
	 * @param numero
	 *            un número, incluso numero no decimales.
	 * @return una cadena que con la representación del número en formato
	 *         decimal.
	 */
	public static String formatoDecimal(Number numero) {
		String patron = "#0.00";

		DecimalFormat formato = new DecimalFormat(patron);
		formato.setRoundingMode(RoundingMode.HALF_UP);
		return formato.format(numero);
	}

	/**
	 * <p>
	 * Este método permite convertir en formato moneda, reduciendo la matiza
         * a dos digitos en caso de ser un número decimal; en caso de ser un 
         * número entero le añade dos digitos en la matiza.
	 * </p>
	 * 
	 * <p>
	 * Por ejemplo:<br>
	 * <ul>
	 * <li>1882 <strong>devuelve</strong> $ 1882.00</li>
	 * <li>18.534534582 <strong>devuelve</strong>$ 18.53</li>
	 * </ul>
	 * </p>
	 * 
	 * @param numero
	 *            un número, incluso número no decimales.
	 * @return una cadena que con la representación del número en formato
	 *         decimal.
	 */
	public static String formatoMoneda(Number numero) {
            return formatoMoneda(numero, false);
        }

	/**
	 * <p>
	 * Este método permite convertir en formato moneda, reduciendo la matiza
         * a dos digitos en caso de ser un número decimal; en caso de ser un 
         * número entero le añade dos digitos en la matiza.
	 * </p>
	 * 
	 * <p>
	 * Por ejemplo:<br>
	 * <ul>
	 * <li>1882 <strong>devuelve</strong> $ 1882.00</li>
	 * <li>18.534534582 <strong>devuelve</strong>$ 18.53</li>
	 * </ul>
	 * </p>
	 * 
	 * @param numero
	 *            un número, incluso número no decimales.
         * @param separador true para incluir el separador de miles.
	 * @return una cadena que con la representación del número en formato
	 *         decimal.
	 */
	public static String formatoMoneda(Number numero, boolean separador) {
		String patron = separador ? "¤ #,##0.00" : "¤ #0.00";
		if ( numero == null) {
			numero = BigDecimal.ZERO;
		}
		DecimalFormat formato = new DecimalFormat(patron);
		formato.setRoundingMode(RoundingMode.HALF_UP);
		return formato.format(numero);
	}

	/**
	 * Devuelve el bigcimal en string formateado
	 * 
	 * @author eduardo Mex
	 * @param bigDecimal
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal bigDecimal) {

		DecimalFormat formateador = new DecimalFormat("###,##0.00");
		formateador.setRoundingMode(RoundingMode.HALF_UP);

		return formateador.format(bigDecimal);
	}

        /**
         * 
	 * @author eduardo Mex
         * @param importe
         * @return 
         */
	public static BigDecimal redondear(BigDecimal importe) {
		return importe.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}

        /**
         * 
	 * @author eduardo Mex
         * @param importe
         * @return 
         */
	public static BigDecimal redondear(double importe) {
		return redondear(new BigDecimal(importe + ""));
	}
}
