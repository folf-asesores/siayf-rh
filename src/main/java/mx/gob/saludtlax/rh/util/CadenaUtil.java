/*
 *
 */

package mx.gob.saludtlax.rh.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class CadenaUtil {

    public synchronized static String converterSpace(String string) {
        final StringBuffer sb = new StringBuffer(string.length() * 2);

        final StringCharacterIterator iterator = new StringCharacterIterator(
                string);

        char ch = iterator.current();

        while (ch != CharacterIterator.DONE) {
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

        while (ch != CharacterIterator.DONE) {
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

    public synchronized static String remplazarCaracteresLatinos(
            final String cadena) {
        final StringBuffer sb = new StringBuffer();
        final StringCharacterIterator iterator = new StringCharacterIterator(
                cadena);
        char ch;
        do {
            ch = iterator.current();

            switch (ch) {
                // Mayúsculas
                case '\u00c0': // A con acento agudo (À)
                    sb.append('A');
                    break;
                case '\u00c1': // A con acento grave (Á)
                    sb.append('A');
                    break;
                case '\u00c8': // E con acento agudo (È)
                    sb.append('E');
                    break;
                case '\u00c9': // E con acento grave (É)
                    sb.append('E');
                    break;
                case '\u00cc': // I con acento agudo (Ì)
                    sb.append('I');
                    break;
                case '\u00cd': // I con acento grave (Í)
                    sb.append('I');
                    break;
                case '\u00d2': // O con acento agudo (Ò)
                    sb.append('O');
                    break;
                case '\u00d3': // O con acento grave (Ó)
                    sb.append('O');
                    break;
                case '\u00d9': // U con acento agudo (Ù)
                    sb.append('U');
                    break;
                case '\u00da': // U con acento grave (Ú)
                    sb.append('U');
                    break;
                case '\u00dc': // U con dieresis (Ü)
                    sb.append('U');
                    break;
                case '\u00d1': // N con tilde (Ñ)
                    sb.append('N');
                    break;

                // Minúsculas
                case '\u00e0': // a con acento agudo (à)
                    sb.append('a');
                    break;
                case '\u00e1': // a con acento grave (á)
                    sb.append('a');
                    break;
                case '\u00e8': // e con acento agudo (è)
                    sb.append('e');
                    break;
                case '\u00e9': // e con acento grave (é)
                    sb.append('e');
                    break;
                case '\u00ec': // i con acento agudo (ì)
                    sb.append('i');
                    break;
                case '\u00ed': // i con acento grave (í)
                    sb.append('i');
                    break;
                case '\u00f2': // o con acento agudo (ò)
                    sb.append('o');
                    break;
                case '\u00f3': // o con acento grave (ó)
                    sb.append('o');
                    break;
                case '\u00f9': // u con acento agudo (ù)
                    sb.append('u');
                    break;
                case '\u00fa': // u con acento grave (ú)
                    sb.append('u');
                    break;
                case '\u00fc': // u con dieresis (ü)
                    sb.append('u');
                    break;
                case '\u00f1': // n con tilde (ñ)
                    sb.append('n');
                    break;

                case '.':
                    // No se debe agregar el caracter de punto
                case CharacterIterator.DONE:
                    // No se debe agregar el caracter de final
                    break;
                default:
                    sb.append(ch);
                    break;
            }
            ch = iterator.next();
        } while (ch != CharacterIterator.DONE);

        return sb.toString();
    }
}
