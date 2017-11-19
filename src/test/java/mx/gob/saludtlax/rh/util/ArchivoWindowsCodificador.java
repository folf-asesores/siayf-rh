/*
 * ArchivoWindowsCodificador.java
 * Creado el 01/Mar/2017 6:14:42 PM
 *
 */

package mx.gob.saludtlax.rh.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ArchivoWindowsCodificador {

    public ArchivoWindowsCodificador() {
    }

    @Ignore
    @Test
    public void codificarBytes() throws IOException {
        Charset utf8charset = Charset.forName("UTF-8");
        Charset iso88591charset = Charset.forName("ISO-8859-1");

        ByteBuffer inputBuffer = ByteBuffer.wrap(new byte[] { (byte) 0xC3, (byte) 0xA2 });

        // decode UTF-8
        CharBuffer data = utf8charset.decode(inputBuffer);

        // encode ISO-8559-1
        ByteBuffer outputBuffer = iso88591charset.encode(data);
        byte[] file = outputBuffer.array();
        String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "test-codificar-bytes.txt";
        Path path = Paths.get(filePath);
        Files.write(path, file);
    }

    @Test
    public void codificarLineas() throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        if ("\n".equals(lineSeparator)) {
            System.setProperty("line.separator", "\r\n");
        }

        List<String> texto = new ArrayList<>();
        texto.add(
                "Y, viéndole don Quijote de aquella manera, con muestras de tanta tristeza, le dijo: Sábete, Sancho, que no es un hombre más que otro si no hace más que otro.");
        texto.add(
                "Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas; porque no es posible que el mal ni el bien sean durables, y de aquí se sigue que, habiendo durado mucho el mal, el bien está ya cerca.");
        texto.add("Así que, no debes congojarte por las desgracias que a mí me suceden, pues a ti no te cabe");

        String filePath = System.getProperty("user.home") + System.getProperty("file.separator") + "test-codificar-lineas.txt";
        Path path = Paths.get(filePath);

        Files.write(path, texto, Charset.forName("ISO-8859-1"));
        System.setProperty("line.separator", lineSeparator);
    }
}
