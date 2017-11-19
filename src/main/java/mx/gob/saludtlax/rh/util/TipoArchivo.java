/*
 * TipoArchivo.java
 * Creado el Mar 10, 2016, 7:14:07 PM
 *
 * Este enumerable contiene los tipos de archivo su extensión y además su MIME
 * (Multipurpose Internet Mail Extensions) que se emplean en la aplicación.
 *
 */

package mx.gob.saludtlax.rh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Este enumerable contiene los tipos de archivos MIME Type y las extensiones de
 * estos. <strong>Solo están lo más comunes dentro de la aplicación.</strong>
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum TipoArchivo {

    CSV("text/csv", "csv"),
    //DAT("text/plain", "dat"),
    JPEG("image/jpeg", "jpeg"), JPG("image/jpg", "jpg"), PDF("application/pdf", "pdf"), PNG("image/png", "png"), TIF("image/tiff", "tif"),
    TIFF("image/tiff", "tiff"),
    //TRA("text/plain", "tra"),
    TXT("text/plain", "txt"), XLS("application/xls", "xls"), XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"), XML("application/xml", "xml"), ZIP("application/zip", "zip");

    /**
     * Constructor del enumerable.
     *
     * @param MIMEType
     *            la cadena que representa el MIMEType
     * @param extension
     *            la extensión que corresponde al MIMEType
     */
    TipoArchivo(String MIMEType, String extension) {
        this.MIMEType = MIMEType;
        this.extension = extension;
    }

    /**
     * Este método devuelve la extensión asociada al tipo de archivo. Sin el
     * punto que precede a la extensión.
     *
     * @return la extensión.
     */
    public String getExtension() {
        return getExtension(false);
    }

    /**
     * Este método devuelve la extensión asociada al tipo de archivo.
     *
     * @param incluirPunto
     *            <code>true</code> para agregar el punto que precede a
     *            la extensión.
     * @return la extensión.
     */
    public String getExtension(boolean incluirPunto) {
        return incluirPunto ? '.' + extension : extension;
    }

    /**
     * Devuelve la extensión del archivo de acuerdo al MIME Type que recibe.
     *
     * @param mimeType
     *            el MIME Type para el cual se obtendrá la extensión
     * @return la extensión presedida por punto y deacuerdo al MIME Type.
     */
    public static String getExtension(String mimeType) {
        return getExtension(mimeType, true);
    }

    /**
     * Devuelve la extensión del archivo de acuerdo al MIME Type que recibe.
     *
     * @param mimeType
     *            el MIME Type para el cual se obtendrá la extensión.
     * @param incluirPunto
     *            si se incluira el punto o no, al devolver la
     *            extensión.
     * @return la extensión según el MIME Type.
     */
    public static String getExtension(String mimeType, boolean incluirPunto) {
        String extension = "";

        switch (mimeType) {
            case "text/csv":
                extension = CSV.extension;
                break;
            case "image/jpeg":
                extension = JPEG.extension;
                break;
            case "image/jpg":
                extension = JPG.extension;
                break;
            case "application/pdf":
                extension = PDF.extension;
                break;
            case "image/png":
                extension = PNG.extension;
                break;
            case "image/tiff":
                extension = TIFF.extension;
                break;
            case "text/plain":
                extension = TXT.extension;
                break;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                extension = XLSX.extension;
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                extension = DOCX.extension;
                break;
            case "application/xml":
                extension = XML.extension;
                break;
            case "application/zip":
                extension = ZIP.extension;
                break;
            default:
                throw new IllegalArgumentException("Ha indicado un MIME Type incorrecto " + mimeType);
        }

        return incluirPunto ? '.' + extension : extension;
    }

    /**
     * Este método devuelve una cadena con el MIME Type asociado con el tipo de
     * archivo.
     *
     * @return el MIME Type asociado con el tipo de archivo.
     */
    public String getMIMEType() {
        return MIMEType;
    }

    /**
     * Este método devuelve el MIME Type según la extension que recibe.
     *
     * @param extension
     * @return
     */
    public static String getMIMEType(String extension) {
        String valorMIMEType = getTipoArchivoPorExtension(extension).MIMEType;
        return valorMIMEType;
    }

    /**
     * Este método devuelve el <code>TipoArchivo</code> apartir de una extesión.
     *
     * @param extension
     *            la extensión del archivo que se requiere.
     * @return una instancia del <code>TipoArchivo</code>.
     */
    public static TipoArchivo getTipoArchivoPorExtension(final String extension) {

        System.out.println(extension);

        StringBuilder extensionSimple = new StringBuilder();

        Pattern pattern = Pattern.compile("([^\\s]+(\\.(?i)([\\w]{3,5}))$)");
        Matcher matcher = pattern.matcher(extension);

        if (matcher.matches()) {
            extensionSimple.append(matcher.group(3));
        } else {
            extensionSimple.append(extension);
        }

        if (extensionSimple.charAt(0) == '.') {
            extensionSimple.deleteCharAt(0);
        }

        switch (extensionSimple.toString().toLowerCase()) {
            case "csv":
                return CSV;
            //            case "dat":
            //                return DAT;
            case "jpeg":
                return JPEG;
            case "jpg":
                return JPG;
            case "pdf":
                return PDF;
            case "png":
                return PNG;
            case "tif":
            case "tiff":
                return TIFF;
            //            case "tra":
            //                return TRA;
            case "txt":
                return TXT;
            case "xls":
                return XLS;
            case "xlsx":
                return XLSX;
            case "docx":
                return DOCX;
            case "xml":
                return XML;
            case "zip":
                return ZIP;
            default:
                throw new IllegalArgumentException("Ha indicado una extensión incorrecto " + extension);
        }
    }

    /**
     * Este método devuelve el <code>TipoArchivo</code> apartir del MIME Type.
     *
     * @param MIMEType
     *            al MIME Type del archivo que se requiere.
     * @return una instancia del <code>TipoArchivo</code>.
     */
    public static TipoArchivo getTipoArchivoPorMIMEType(String MIMEType) {
        System.out.println("ssssM" + MIMEType);
        switch (MIMEType) {
            case "text/csv":
                return CSV;
            case "image/jpeg":
                return JPEG;
            case "image/jpg":
                return JPG;
            case "image/png":
                return PNG;
            case "application/pdf":
                return PDF;
            case "image/tiff":
                return TIFF;
            case "text/plain":
                return TXT;
            case "application/xls":
                return XLS;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return XLSX;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return DOCX;
            case "application/xml":
                return XML;
            case "application/zip":
                return ZIP;

            default:
                throw new IllegalArgumentException("Ha indicado un MIME Type incorrecto " + MIMEType);
        }
    }

    /**
     * Valida el MIME Type
     *
     * @param mimeType
     * @return
     */
    public static boolean esValido(String mimeType) {
        switch (mimeType) {
            case "text/csv":
                return true;
            case "text/plain":
                return true;
            case "image/jpeg":
                return true;
            case "image/jpg":
                return true;
            case "application/pdf":
                return true;
            case "image/png":
                return true;
            case "image/tiff":
                return true;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return true;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return true;
            case "application/xml":
                return true;
            case "application/zip":
                return true;

            default:
                throw new IllegalArgumentException("Ha indicado un MIME Type incorrecto " + mimeType);
        }
    }

    private final String extension;
    private final String MIMEType;
}
