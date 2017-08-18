/**
 * CopyRight © 2016
 */
package mx.gob.saludtlax.rh.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 18/03/2016 11:43:25
 */
public class ValidacionUtil {

	/**
	 * Valida si un bigdecimal es positivo
	 */
	public static boolean esNumeroPositivo(BigDecimal numero) {
		boolean esNumeroPositivo = true;
		if (numero == null || numero.compareTo(BigDecimal.ZERO) == -1) {
			esNumeroPositivo = false;
		}
		return esNumeroPositivo;
	}

	/**
	 * Valida si un bigdecimal es mayor o igual a cero.
	 */
	public static boolean esMayorCero(BigDecimal numero) {
		boolean esMayorCero = false;
		if (numero.compareTo(BigDecimal.ZERO) == 1) {
			esMayorCero = true;
		}
		return esMayorCero;
	}

	/**
	 * Permite saber si el numero que recibe es menor que uno. En caso de que el
	 * valor sea nulo se devolvera verdad.
	 * 
	 * @param numero
	 *            un número a identificar.
	 * @return verdad sí y sólo sí el numero es menor que uno.
	 * 
	 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
	 */
	public static boolean esMenorQueUno(Number numero) {
		if (numero == null) {
			return true;
		}

		if (numero instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) numero;

			return decimal.compareTo(BigDecimal.ONE) == -1;
		}

		if (numero instanceof Double) {
			return numero.doubleValue() < 1;
		}

		return numero.intValue() < 1;
	}

	/**
	 * Valida si un bigdecimal es Nulo o igual a cero. pablinsky
	 */
	public static boolean esNulo(BigDecimal numero) {
		boolean esValido = true;
		if (numero == null) {
			esValido = false;
		} else {
			if (numero.compareTo(BigDecimal.ZERO) == 0) {
				esValido = false;
			}
		}
		return esValido;
	}

	/**
	 * Valida si un bigdecimal es Nulo o igual a cero. pablinsky
	 */
	public static boolean esNulo(Date date) {
		boolean esValido = false;
		if (date == null) {
			esValido = true;
		}
		return esValido;
	}

    /**
     * <p>Valida si la cadena está vacia.</p>
     * <p>En caso de que la cadena sea null se devolvera <code>false</code></p>
     * 
     * @param cadena la cadena que será validada
     * @return <code>true</code> si la cadena está vacia, <code>false</code> si la cadena no esta vacia.
     */
    public static boolean esCadenaVacia(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
    }

	public static boolean fechaFutura(Date fecha) {
		DateTime fechaValidacion = FechaUtil.fechaSinTiempo(fecha);
		DateTime fechaActual = FechaUtil.fechaActualSinTiempo();
		boolean esFechaFutura = false;
		if (fechaValidacion.isAfter(fechaActual)) {
			esFechaFutura = true;
		}
		return esFechaFutura;

	}

	public static boolean sonIguales(BigDecimal primerImporte,
			BigDecimal segundoImporte) {
		boolean sonIguales = false;
		if (primerImporte.compareTo(segundoImporte) == 0) {
			sonIguales = true;
		}

		return sonIguales;
	}

	/**
	 * Valida si el numero no es nulo o igual a cero
	 */
	public static boolean esNumeroPositivo(Integer numero) {
		boolean esValido = true;
		if (numero == null) {
			esValido = false;
		} else {
			if (numero == 0) {
				esValido = false;
			}
		}
		return esValido;
	}

	/**
	 * Valida si el numero no es nulo o igual a cero
	 */
	public static boolean esNumeroPositivoInt(Integer numero) {
		boolean esValido = true;
		if (numero == null) {
			esValido = false;
		} else {
			if (numero == 0) {
				esValido = false;
			}
		}
		return esValido;
	}

	/**
	 * Valida que la fecha no sea mayor a la fecha actual.
	 */
	public static boolean esFechaFutura(Date fecha) {
		boolean esValida = false;
		DateTime fechaValidada = new DateTime(fecha);
		fechaValidada.withHourOfDay(0);
		fechaValidada.withMinuteOfHour(0);
		fechaValidada.withSecondOfMinute(0);
		fechaValidada.withMillisOfSecond(0);
		fechaValidada.withMillis(0);

		DateTime fechaActual = new DateTime();
		fechaActual.withHourOfDay(0);
		fechaActual.withMinuteOfHour(0);
		fechaActual.withSecondOfMinute(0);
		fechaActual.withMillisOfSecond(0);
		fechaActual.withMillis(0);

		if (fechaValidada.isAfter(fechaActual)) {
			esValida = true;
		}

		return esValida;
	}

	/**
	 * Valida que el formato del RFC sea correcto.
         * <p>En caso de que el RFC sea nulo se devolvera <code>false</code></p>
	 * 
	 * @param rfc el RFC que será validado.
	 * @return <code>true</code> si el formato del RFC y <code>false</code> 
         * en caso de que el formato no se valido.
	 */
	public static boolean validarRfc(String rfc) {
            if(rfc == null) {
                return false;
            }
            rfc = rfc.toUpperCase().trim();
            if (rfc.length() < 12) {
                return false;
            }
            if (rfc.trim().matches("([A-Z]{4})([0-9]{6})([A-Z0-9]{3})")) {
                return true;
            }
            return rfc.trim().matches("[A-Z]{3}[0-9]{6}[A-Z0-9]{3}");                
	}

	/**
	 * Valida que el curp sea correcto
	 * 
	 * @param curp
	 * @return
	 */
	public static boolean validarCurp(String curp) {
		curp = curp.toUpperCase().trim();
		return curp.toUpperCase()
				.matches("[A-Z]{4}[0-9]{6}[A-Z]{3}[A-Z0-9]{5}");
	}

	/**
	 * Permite saber si el formato de una URL es valido. En caso de recibir una
	 * cadena vacia o nula se devuelve false.
	 * 
	 * @param url
	 *            la URL que será validada.
	 * @return true si y solo sí la URL es valida.
	 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
	 */
	public static boolean esUrlValida(String url) {
		if (esCadenaVacia(url)) {
			return false;
		}

		Pattern pattern = Pattern
				.compile("(((http|ftp|https):\\/{2})+(([0-9a-z_-]+\\.)+(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mn|mn|mo|mp|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|nom|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ra|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw|arpa)(:[0-9]+)?((\\/([~0-9a-zA-Z\\#\\+\\%@\\.\\/_-]+))?(\\?[0-9a-zA-Z\\+\\%@\\/&\\[\\];=_-]+)?)?))\\S");
		Matcher matcher = pattern.matcher(url);

		return matcher.matches();
	}

	/**
	 * Valida que la cadena solo contenga números.
	 */
	public static boolean tieneCadenaFormatoNumero(String cadena) {
		if (cadena.matches("[0-9]*")) {
			return true;
		} else {
			return false;
		}
	}

}
