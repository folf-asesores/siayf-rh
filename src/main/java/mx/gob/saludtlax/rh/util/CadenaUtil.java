/**
 * 
 */
package mx.gob.saludtlax.rh.util;

import java.text.StringCharacterIterator;

/**
 * @author Eduardo Mex
 *
 */
public class CadenaUtil {
	
	public synchronized static String converterSpace(String string) {
		final StringBuffer sb = new StringBuffer(string.length() * 2);

		final StringCharacterIterator iterator = new StringCharacterIterator(string);

		char ch = iterator.current();

		while (ch != StringCharacterIterator.DONE) {
			if (Character.getNumericValue(ch) > 0) {
				sb.append(ch);
			} else {
				boolean f = false;
				
				if (Character.toString(ch).equals("")) {
					sb.append("_");
					f = true;
				}
				if (Character.toString(ch).equals(" ")) {
					sb.append("_");
					f = true;
				}
				if (!f) {
					sb.append("_");
				}
			}
			ch = iterator.next();
		}
		return sb.toString();
	}

	
	public synchronized static String utftoasci(String s) {
		final StringBuffer sb = new StringBuffer(s.length() * 2);

		final StringCharacterIterator iterator = new StringCharacterIterator(s);

		char ch = iterator.current();

		while (ch != StringCharacterIterator.DONE) {
			if (Character.getNumericValue(ch) > 0) {
				sb.append(ch);
			} else {
				boolean f = false;
				if (Character.toString(ch).equals("ÃŠ")) {
					sb.append("E");
					f = true;
				}
				if (Character.toString(ch).equals("Ãˆ")) {
					sb.append("E");
					f = true;
				}
				if (Character.toString(ch).equals("Ã«")) {
					sb.append("e");
					f = true;
				}
				if (Character.toString(ch).equals("Ã©")) {
					sb.append("e");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¨")) {
					sb.append("e");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¨")) {
					sb.append("e");
					f = true;
				}
				if (Character.toString(ch).equals("Ã‚")) {
					sb.append("A");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¤")) {
					sb.append("a");
					f = true;
				}
				if (Character.toString(ch).equals("ÃŸ")) {
					sb.append("ss");
					f = true;
				}
				if (Character.toString(ch).equals("Ã‡")) {
					sb.append("C");
					f = true;
				}
				if (Character.toString(ch).equals("Ã–")) {
					sb.append("O");
					f = true;
				}
				if (Character.toString(ch).equals("Âº")) {
					sb.append("");
					f = true;
				}
				if (Character.toString(ch).equals("Ã“")) {
					sb.append("O");
					f = true;
				}
				if (Character.toString(ch).equals("Âª")) {
					sb.append("");
					f = true;
				}
				if (Character.toString(ch).equals("Âº")) {
					sb.append("");
					f = true;
				}
				if (Character.toString(ch).equals("Ã‘")) {
					sb.append("N");
					f = true;
				}
				if (Character.toString(ch).equals("Ã‰")) {
					sb.append("E");
					f = true;
				}
				if (Character.toString(ch).equals("Ã„")) {
					sb.append("A");
					f = true;
				}
				if (Character.toString(ch).equals("Ã…")) {
					sb.append("A");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¤")) {
					sb.append("a");
					f = true;
				}
				if (Character.toString(ch).equals("Ãœ")) {
					sb.append("U");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¶")) {
					sb.append("o");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¼")) {
					sb.append("u");
					f = true;
				}
				if (Character.toString(ch).equals("Ã¡")) {
					sb.append("a");
					f = true;
				}
				if (Character.toString(ch).equals("Ã“")) {
					sb.append("O");
					f = true;
				}
				if (Character.toString(ch).equals("Ã‰")) {
					sb.append("E");
					f = true;
				}
				if (Character.toString(ch).equals("â‚¬")) {
					sb.append("");
					f = true;
				}
				if (Character.toString(ch).equals("")) {
					sb.append("");
					f = true;
				}
				if (Character.toString(ch).equals(" ")) {
					sb.append("â‚¬");
					f = true;
				}
				if (!f) {
					sb.append("?");
				}
			}
			ch = iterator.next();
		}
		return sb.toString();
	}

}
