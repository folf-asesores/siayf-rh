/*
 * ArchivoUtil.java
 * Creado el 30/06/2016 11:35:45 AM
 *
 */
package mx.gob.saludtlax.rh.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.imgscalr.Scalr;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class ArchivoUtil {

    private static final Logger LOGGER = Logger.getLogger(ArchivoUtil.class.getName());

    private static final int PDF_PRIMERA_PAGINA = 0;
    private static final float PDF_ESCALA = 0.5f;
    private static final int IMAGEN_ANCHO = 256;
    private static final int IMAGEN_ALTO = 256;
    private static final String CARPETA_USUARIO = System.getProperty("user.home");
    public static final String SEPARADOR_DE_ARCHIVO = System.getProperty("file.separator");
    public static final String SEPARADOR_DE_ARCHIVO_UNIX = "\n";
    public static final String SEPARADOR_DE_ARCHIVO_WINDOWS = "\r\n";
    public static final Charset WINDOWS_LATIN_CHARSET = Charset.forName("windows-1252");
    public static final Charset MS_DOS_LATIN_CHARSET = Charset.forName("Cp850");
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final String PATRON_ESPACIOS_EN_BLANCO_AL_FINAL = "(\\s+)$";

    /**
     * Permite obtener el nombre del archivo sin extensión además de
     * que determina la extesión del archivo según el contenido del mismo. Su
     * función la realiza escribiendo en la carpeta temporal el archivo.
     *
     * @param nombreArchivo el nombre del archivo (incluso sin extensión).
     * @param ext la extensión que se desea probar.
     * @param bytes los datos del archivo.
     * @return
     */
    public static Map<String, Object> validarArchivo(String nombreArchivo,
            TipoArchivo ext, byte[] bytes) {
        Map<String, Object> mapa = new HashMap<>();

        try {
            String nombreSinExtension = obtenerNombreSinExtension(nombreArchivo);
            Path archivoTemporal = Files
                    .createTempFile(nombreSinExtension + '_', ".tmp");
            Files.write(archivoTemporal, bytes);
            String contentType
                    = (Files.probeContentType(archivoTemporal) == null)
                    ? ext.getMIMEType() : Files.probeContentType(archivoTemporal);
            LOGGER.debugv("contentType: {0}", contentType);
            TipoArchivo tipoReal = TipoArchivo
                    .getTipoArchivoPorMIMEType(contentType);
            Files.delete(archivoTemporal);

            mapa.put("NOMBRE_DE_ARCHIVO", nombreSinExtension);
            mapa.put("EXTENSION", tipoReal);
            return mapa;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return mapa;
        }
    }

    /**
     *
     * @param nombreArchivo
     * @param contentType
     * @param contenido
     * @param contentTypeDeseado
     * @param extensionDeseada
     * @return
     */
    public static boolean validarTipoArchivo(String nombreArchivo, String contentType, byte[] contenido, String contentTypeDeseado, String extensionDeseada){
        String contentTypeReal = obtenerMIMEType(contenido);

        LOGGER.debugv("Nombre del archivo: {0} Content Type del archivo: {1} Content Type deseado: {2} Extensi\u00f3n deseada: {3} Content Type real: {4}", new Object[]{nombreArchivo, contentType, contentTypeDeseado, extensionDeseada, contentTypeReal});

        if(!contentTypeDeseado.equals(contentTypeReal)){
            return false;
        }

        if(!(contentType != null && !contentType.equals(contentTypeDeseado))){
            return false;
        }

        return nombreArchivo.endsWith(extensionDeseada);
    }

    /**
     *
     *
     * @param nombreArchivo
     * @param contentType
     * @param contenido
     * @param tipoDeseado
     * @return
     */
    public static boolean validarTipoArchivo(String nombreArchivo, String contentType, byte[] contenido, TipoArchivo tipoDeseado) {
        String contentTypeReal = obtenerMIMEType(contenido);

        if(!tipoDeseado.getMIMEType().equals(contentTypeReal)){
            return false;
        }

        if (contentType != null && !contentType.equals(tipoDeseado.getMIMEType())){
            return false;
        }

        return nombreArchivo.endsWith(tipoDeseado.getExtension());
    }

    /**
     * Este método permite conocer el MIME Type de un archivo.
     *
     * @param contenido un arreglo de bytes con el contenido del archivo a
     * nivel de bytes.
     * @return el MIME Type.
     * @throws NullPointerException si el contenido es nulo.
     */
    private static String obtenerMIMEType(byte[] contenido) {
        try {
            if(contenido == null){
                throw new NullPointerException("No se puede obtener el MIMEType de un null");
            }

            Path archivoTemporal = Files
                    .createTempFile(GenerateUtil.generarId(), ".tmp");
            Files.write(archivoTemporal, contenido);

            String contentType = Files.probeContentType(archivoTemporal);
            Files.delete(archivoTemporal);

            return contentType;
        } catch (IOException ex) {
            LOGGER.error(null, ex);
            return null;
        }
    }

    /**
     * Permite obtener un archivo guardado en el disco.
     *
     * @param ruta la ruta en la que se leera el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @return los bytes que representan el archivo.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar el archivo.
     */
    public static byte[] leerArchivo(String ruta, String nombreArchivo) throws IOException {
        return leerArchivo(ruta, nombreArchivo, false);
    }

    /**
     * Permite obtener un archivo guardado en el disco.
     *
     * @param ruta la ruta en la que se leera el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param usarCarpetaUsuario si la ruta se tomara desde la carpeta del usuario.
     * @return los bytes que representan el archivo.
     * @throws IOException si ocurre de lectura o escritura al guardar el archivo.
     */
    public static byte[] leerArchivo(String ruta, String nombreArchivo, boolean usarCarpetaUsuario) throws IOException {
        if(ruta == null) {
            ruta = "";
        }

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);
        Path rutaArchivo = Paths.get(rutaReal.toString(), nombreArchivo);

        return Files.readAllBytes(rutaArchivo);
    }

    /**
     * Permite guardar un archivo en la carpeta principal (user home) del
     * usuario.
     *
     * @param file los bytes que representan el archivo.
     * @param fileName el nombre del archivo, incluyendo la extensión.
     * @throws IOException si ocurre alguna excepción de escritura o lectura.
     */
    public static void guardarEnCarpetaUsuario(byte[] file, String fileName) throws IOException {
        if(file == null) {
            LOGGER.error("No se puede guardar un archivo nulo");
        } else if(ValidacionUtil.esCadenaVacia(fileName)) {
            LOGGER.error("El nombre del archivo a guardar esta vacio.");
        } else {
            String filePath = CARPETA_USUARIO + SEPARADOR_DE_ARCHIVO + fileName;
            Path path = Paths.get(filePath);
            Files.write(path, file);
//            Files.write(path, file, StandardOpenOption.READ,
//                    StandardOpenOption.WRITE,
//                    StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    /**
     * Permite guardar un archivo.
     *
     * @param ruta la ruta en la que se guardará el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param archivo los bytes que representan el archivo.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del usuario.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar el archivo.
     */
    public static void guardarArchivo(String ruta, String nombreArchivo, byte[] archivo, boolean usarCarpetaUsuario) throws IOException {
        guardarArchivo(ruta, nombreArchivo, archivo, true, usarCarpetaUsuario);
    }

    /**
     * Permite guardar un archivo.
     *
     * @param ruta la ruta en la que se guardará el archivo.
     * @param nombreArchivo el nombre del archivo, que debe incluir la extesión.
     * @param archivo los bytes que representan el archivo.
     * @param sobreescribir permite sobre escribir el archivo si existe.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del usuario.
     * @throws java.io.IOException si ocurre de lectura o escritura al guardar el archivo.
     */
    public static void guardarArchivo(String ruta, String nombreArchivo,
            byte [] archivo, boolean sobreescribir, boolean usarCarpetaUsuario)
            throws IOException {

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);
        Path rutaArchivo = Paths.get(rutaReal.toString(), nombreArchivo);

        if (Files.notExists(rutaReal)) {
            Files.createDirectories(rutaReal);
        }

        if(sobreescribir) {
            Files.deleteIfExists(rutaArchivo);
            Files.createFile(rutaArchivo);
        } else if(Files.notExists(rutaArchivo)){
            Files.createFile(rutaArchivo);
        }

        Files.write(rutaArchivo, archivo);
    }

    /**
     * Permite mover un archivo.
     *
     * @param origen la ruta de origen.
     * @param destino la ruta destino.
     * @param usarCarpetaUsuario si se usará la carpeta personal del usuario.
     */
    public static void moverArchivo(String origen, String destino,
            boolean usarCarpetaUsuario) {
        try {
            Path rutaOrigen = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, origen) :  Paths.get(origen);
            Path rutaDestino = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, destino) : Paths.get(destino);

            Files.move(rutaOrigen, rutaDestino);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }
    }

    /**
     * Permite eliminar un archivo del disco.
     * @param ruta la ruta en la que se encuentra el archivo que será eliminado.
     * @param nombreArchivo el nombre del archivo que será eliminado.
     * @throws IOException si ocurre un error de lectura o escritura al eliminar
     * el archivo.
     */
    public static void eliminarArchivo(String ruta, String nombreArchivo) throws IOException {
        Path path = Paths.get(ruta, nombreArchivo);
        Files.deleteIfExists(path);
    }

    /**
     * Permite eliminar archivos ignorando la extensión de estos, basandose tan
     * sólo en nombre del archivo.
     *
     * @param ruta la ruta en la que se encuentra el archivo que será eliminado.
     * @param nombreArchivo el nombre del archivo que será eliminado, sin extensión.
     * @param usarCarpetaUsuario usar como raíz la carpeta principal del usuario.
     * @throws IOException
     */
    public static void eliminarArchivoSoloConNombre(String ruta, String nombreArchivo, boolean usarCarpetaUsuario) throws IOException {
        List<Path> archivosPorEliminar = new ArrayList<>();

        Path rutaReal = usarCarpetaUsuario ? Paths.get(CARPETA_USUARIO, ruta) : Paths.get(ruta);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rutaReal)) {
            for (Path path : directoryStream) {
                String archivoSinExtension = obtenerNombreSinExtension(path.toString().replace(rutaReal.toString() + SEPARADOR_DE_ARCHIVO, ""));

                if(nombreArchivo.equals(archivoSinExtension)){
                    archivosPorEliminar.add(path);
                }
            }
        }

        for (Path archivoPorEliminar : archivosPorEliminar) {
            Files.deleteIfExists(archivoPorEliminar);
        }
    }

    /**
     * Este permite generar una vista previa de los archivos más comunes y
     * devuelve un arreglo de bytes que representa la vistata previa en formato
     * png.
     *
     * @param extension la extensión del archivo.
     * @param archivo un arreglo de bytes que representa el archivo del cual se
     *                obtendrá la vista previa.
     * @return un areglo de bytes con la vista previa en un archivo en formato
     *         png.
     */
    public static byte [] crearVistaPrevia(TipoArchivo extension, byte[] archivo) {
        switch (extension) {
            case PDF:
                try {
                    byte[] vistaPrevia;

                    try (PDDocument document = PDDocument.load(archivo)) {
                        PDFRenderer pdfRenderer = new PDFRenderer(document);
                        BufferedImage bim = pdfRenderer
                                .renderImage(PDF_PRIMERA_PAGINA,
                                        PDF_ESCALA, ImageType.RGB);

                        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                            ImageIOUtil.writeImage(bim, "png", bos);
                            vistaPrevia = bos.toByteArray();
                        }
                    }

                    return vistaPrevia;
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex.getCause());
                }
                break;
            case JPG:
            case JPEG:
            case PNG:
                try {
/*
 * Para trabajar las vistas previas me apoye de esta página
 * http://paxcel.net/blog/java-thumbnail-generator-imagescalar-vs-imagemagic/
 * aún están pendientes los cambios para evitar el posible error cuando el
 * esquema de colores de la imagen es CMKY en vez de RGB
 */
                    InputStream is = new ByteArrayInputStream(archivo);
                    BufferedImage img = ImageIO.read(is);
                    BufferedImage vistaPrevia = Scalr
                            .resize(img, Scalr.Method.QUALITY,
                                    Scalr.Mode.AUTOMATIC, IMAGEN_ANCHO,
                                    IMAGEN_ALTO, Scalr.OP_ANTIALIAS);

                    byte[] vistaPreviaBytes;

                    try (ByteArrayOutputStream bosImagen = new ByteArrayOutputStream()) {
                        ImageIO.write(vistaPrevia, "png", bosImagen);
                        vistaPreviaBytes = bosImagen.toByteArray();
                    }

                    return vistaPreviaBytes;
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage(), ex.getCause());
                }
                break;
            case TIFF:
                try {
                    InputStream is =
                            ArchivoUtil.class.getClassLoader().getResourceAsStream("imagenes/tif-icon.png");

                    byte[] vistaPreviaBytes;

                    try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

                        int nRead;
                        byte[] data = new byte[16384];

                        while ((nRead = is.read(data, 0, data.length)) != -1) {
                            buffer.write(data, 0, nRead);
                        }

                        buffer.flush();
                        vistaPreviaBytes = buffer.toByteArray();
                    }

                    return vistaPreviaBytes;
                } catch (IOException ex) {
                    return null;
                }
        }

        LOGGER.error("No se ha logrado generar la vista previa del archivo.");
        return null;
    }

    /**
     * Permite convertir un archivo de texto plano con códificación UTF-8 y
     * caracteres de fin de línea tipo UNIX a formato de windows.
     *
     * @param archivo un arreglo de bytes que representa un archivo de texto plano.
     * @return un arreglo de bytes que representa un archivo de texto plano con
     * códificación de Windows.
     * @throws IOException en caso de que haya error de lectura o escritura al
     * crear los archivos temporales.
     */
    public static byte[] codificarComoWindows(final byte[] archivo) throws IOException {
        if(archivo == null) {
            throw new NullPointerException("El archivo no debe se nulo para poder realizar conversión.");
        }

        LOGGER.info("Iniciando la conversión a formato de Windows.");
        Path archivoTemporal = Files.createTempFile("origen", ".txt");
        Files.write(archivoTemporal, archivo);

        if (!SEPARADOR_DE_ARCHIVO_WINDOWS.equals(SEPARADOR_DE_ARCHIVO)) {
            System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO_WINDOWS);
        }

        List<String> lineas = Files.readAllLines(archivoTemporal, UTF8_CHARSET);
        List<String> lineasNuevas = new ArrayList<>();
        for(String linea : lineas) {
            String nuevaLinea = linea.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");
            lineasNuevas.add(nuevaLinea);
        }

        Path archivoTemporalWin = Files.createTempFile("destino", ".txt");
        Files.write(archivoTemporalWin, lineasNuevas, WINDOWS_LATIN_CHARSET);
        System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO);

        byte[] archivoWindows = Files.readAllBytes(archivoTemporalWin);
        LOGGER.info("La conversión en formato de Windows se ha completado correctamente.");
        Files.delete(archivoTemporal);
        Files.delete(archivoTemporalWin);
        return archivoWindows;
    }

    /**
     * Permite convertir un archivo de texto plano con códificación UTF-8 y
     * caracteres de fin de línea tipo UNIX a formato de MS-DOS.
     *
     * @param archivo un arreglo de bytes que representa un archivo de texto plano.
     * @return un arreglo de bytes que representa un archivo de texto plano con
     * códificación de Windows.
     * @throws IOException en caso de que haya error de lectura o escritura al
     * crear los archivos temporales.
     */
    public static byte[] codificarComoMsDos(final byte[] archivo) throws IOException {
        if(archivo == null) {
            throw new NullPointerException("El archivo no debe se nulo para poder realizar conversión.");
        }

        LOGGER.info("Iniciando la conversión a formato de MS-DOS.");
        Path archivoTemporal = Files.createTempFile("origen", ".txt");
        Files.write(archivoTemporal, archivo);

        if (!SEPARADOR_DE_ARCHIVO_WINDOWS.equals(SEPARADOR_DE_ARCHIVO)) {
            System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO_WINDOWS);
        }

        List<String> lineas = Files.readAllLines(archivoTemporal, UTF8_CHARSET);
        List<String> lineasNuevas = new ArrayList<>();
        int lineasEliminadas = 0;
        for(int i  = 0; i < lineas.size(); i++) {
            String lineaAnterior = lineas.get(i);
            String nuevaLineaAnterior = lineaAnterior.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");
            int j = i + 1;
            boolean agregar = true;
            int lineaHoja = i % 66;
//            LOGGER.debugv("lineaHoja: {0}",lineaHoja);
            if(j < lineas.size()) {
                String lineaSiguiente = lineas.get(j);
                String nuevaLineaSiguiente = lineaSiguiente.replaceAll(PATRON_ESPACIOS_EN_BLANCO_AL_FINAL, "");

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("05   SUPLENCIAS")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("08   D")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("14   PERCEPCI")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("17   BONO")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("24   AGUINALDO")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("26   SUBSIDIO")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("27   PRIMA VACACIONAL")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("29   BONIFICACI")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("30   RETROACTIVO")) {
                    agregar = false;
                }

                if(ValidacionUtil.esCadenaVacia(nuevaLineaAnterior) && nuevaLineaSiguiente.contains("32   OTROS")) {
                    agregar = false;
                }
            }

            if (agregar) {
                lineasNuevas.add(nuevaLineaAnterior);
            } else {
                lineasEliminadas++;
            }
//            if((lineaHoja + 1) == 66) {
//                for (int k = 0; k < lineasEliminadas; k++) {
//                    lineasNuevas.add("");
//                }
//                lineasEliminadas = 0;
//            }
        }

        Path archivoTemporalWin = Files.createTempFile("destino", ".txt");
        Files.write(archivoTemporalWin, lineasNuevas, MS_DOS_LATIN_CHARSET);
        System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO);

        byte[] archivoWindows = Files.readAllBytes(archivoTemporalWin);
        LOGGER.info("La conversión en formato de MS-DOS se ha completado correctamente.");
//        Files.delete(archivoTemporal);
        Files.delete(archivoTemporalWin);
        return archivoWindows;
    }

    /**
     * Permite obtener el nombre del archivo sin extensión y estandariza el
     * nombre del archivo eliminando carácteres especiales además de que
     * transforma las letras en minúsculas.
     *
     * @param nombreArchivo el nombre del archivo con extensión o sin ella.
     * @return el nombre del archivo sin extensión.
     */
    public static String obtenerNombreSinExtension(String nombreArchivo) {
        String nombreSinExtension = FilenameUtils.removeExtension(nombreArchivo);

        nombreSinExtension = nombreSinExtension.toLowerCase();
        nombreSinExtension = nombreSinExtension.replace('\u00e0', 'a'); // a con acento agudo
        nombreSinExtension = nombreSinExtension.replace('\u00e1', 'a'); // a con acento grave
        nombreSinExtension = nombreSinExtension.replace('\u00e8', 'e'); // e con acento agudo
        nombreSinExtension = nombreSinExtension.replace('\u00e9', 'e'); // e con acento grave
        nombreSinExtension = nombreSinExtension.replace('\u00ec', 'i'); // i con acento agudo
        nombreSinExtension = nombreSinExtension.replace('\u00ed', 'i'); // i con acento grave
        nombreSinExtension = nombreSinExtension.replace('\u00f2', 'o'); // o con acento agudo
        nombreSinExtension = nombreSinExtension.replace('\u00f3', 'o'); // o con acento grave
        nombreSinExtension = nombreSinExtension.replace('\u00f9', 'u'); // u con acento agudo
        nombreSinExtension = nombreSinExtension.replace('\u00fa', 'u'); // u con acento grave
        nombreSinExtension = nombreSinExtension.replace('\u00fc', 'u'); // u con dieresis
        nombreSinExtension = nombreSinExtension.replace('\u00f1', 'n'); // n con tilde
        nombreSinExtension = nombreSinExtension.replace('.', '_');

        return nombreSinExtension;
    }

    /**
     * Por ser una utilería no se permite la creación de instancias de esta
     * clase.
     */
    private ArchivoUtil() {

    }
}
