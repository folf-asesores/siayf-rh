
package mx.gob.saludtlax.rh.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Juan Carlos Ivan Ganzo Dominguez
 *
 */
public class CadenaOriginalServices implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7353430624484414642L;

    /**
     * Genera la cadena original del CFDI
     *
     * @param retencionesCFDI
     * @return
     * @throws IOException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public String generar(ByteArrayOutputStream retencionesXMLStream) throws IOException, TransformerConfigurationException, TransformerException {

        File xstlFile = new File(this.getClass().getResource("/xstl/cadenaoriginal_3_2.xslt").getPath());

        StreamSource sourceXSL = new StreamSource(xstlFile);

        InputStream inDatosXML = new ByteArrayInputStream(retencionesXMLStream.toByteArray());

        TransformerFactory factory = TransformerFactory.newInstance();
        //factory.setAttribute("enable-inlining", Boolean.TRUE);
        Transformer transformer = factory.newTransformer(sourceXSL);
        StreamSource in = new StreamSource(inDatosXML);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        transformer.transform(in, new StreamResult(outStream));

        //System.out.println(outStream.toString());

        return outStream.toString();
    }

    /**
     * Genera la cadena original del CFDI
     *
     * @param retencionesCFDI
     * @return
     * @throws IOException
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public String generarNomina12(ByteArrayOutputStream retencionesXMLStream) throws IOException, TransformerConfigurationException, TransformerException {

        File xstlFile = new File(this.getClass().getResource("/xstl/cadenaoriginal_3_2.xslt").getPath());

        StreamSource sourceXSL = new StreamSource(xstlFile);

        InputStream inDatosXML = new ByteArrayInputStream(retencionesXMLStream.toByteArray());

        TransformerFactory factory = TransformerFactory.newInstance();
        //factory.setAttribute("enable-inlining", Boolean.TRUE);
        Transformer transformer = factory.newTransformer(sourceXSL);
        StreamSource in = new StreamSource(inDatosXML);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        transformer.transform(in, new StreamResult(outStream));

        System.out.println(outStream.toString());

        return outStream.toString();
    }

    public String generar(String cfdi) throws TransformerException {

        // cargar el archivo XSLT

        File xstlFile = new File(this.getClass().getResource("/xstl/cadenaoriginal_3_2.xslt").getPath());

        StreamSource sourceXSL = new StreamSource(xstlFile);

        // cargar el CFDI
        StreamSource sourceXML = new StreamSource(new StringReader(cfdi));

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // crear el procesador XSLT que nos ayudarÃ¡ a generar la cadena
        // original
        // con base en las reglas del archivo XSLT
        TransformerFactory tFactory = TransformerFactory.newInstance();
        //tFactory.setAttribute("enable-inlining", Boolean.TRUE);
        Transformer transformer = tFactory.newTransformer(sourceXSL);

        // aplicar las reglas del XSLT con los datos del CFDI y escribir el
        // resultado en outStream
        transformer.transform(sourceXML, new StreamResult(outStream));

        return new String(outStream.toByteArray());

    }

}
