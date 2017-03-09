package mx.gob.saludtlax.rh.reporteslaborales.termino;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class TerminoInterinatoWord {

	public final String RUTA = "plantillas/termino/TERMINO_INTERINATO.docx";

	private static final char SIGNO_APERTURA = '\u00AB';
	private static final char SIGNO_CIERRE = '\u00BB';

	public byte[] generar(TerminoDTO  terminoDTO) {
		
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RUTA);
			XWPFDocument plantilla = new XWPFDocument(inputStream);
			Map<String, String> camposReporte = new HashMap<String, String>();
			
			camposReporte.put(SIGNO_APERTURA + "asunto" + SIGNO_CIERRE,terminoDTO.getAsunto());
			camposReporte.put(SIGNO_APERTURA + "posicionDos" + SIGNO_CIERRE, terminoDTO.getPosicionDos());
			camposReporte.put(SIGNO_APERTURA + "posicionTres" + SIGNO_CIERRE, terminoDTO.getPosicionTres());
			camposReporte.put(SIGNO_APERTURA + "posicionCuatro" + SIGNO_CIERRE, terminoDTO.getPosicionCuatro());
			camposReporte.put(SIGNO_APERTURA + "reincorporacionTitular" + SIGNO_CIERRE, terminoDTO.getReincorporacionTitular());
			camposReporte.put(SIGNO_APERTURA + "fechaTermino" + SIGNO_CIERRE, terminoDTO.getFechaTermino());
			camposReporte.put(SIGNO_APERTURA + "funcion" + SIGNO_CIERRE, terminoDTO.getFuncion());
			camposReporte.put(SIGNO_APERTURA + "clavePresupuestal" + SIGNO_CIERRE, terminoDTO.getClavePresupuestal());
			camposReporte.put(SIGNO_APERTURA + "secretarioSalud" + SIGNO_CIERRE, terminoDTO.getSecretarioSalud());
			
			for (XWPFParagraph parrafo : plantilla.getParagraphs()) {
				remplazarCampos(parrafo, camposReporte);
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			plantilla.write(out);
			plantilla.close();

			return out.toByteArray();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
