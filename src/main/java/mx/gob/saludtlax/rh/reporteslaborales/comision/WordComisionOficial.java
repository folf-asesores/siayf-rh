package mx.gob.saludtlax.rh.reporteslaborales.comision;

import static mx.gob.saludtlax.rh.util.POIUtils.remplazarCampos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordComisionOficial {

	public final String RUTA = "plantillas/comision/COMISION_OFICIAL.docx";
	
	private static final char SIGNO_APERTURA = '\u00AB';
    private static final char SIGNO_CIERRE = '\u00BB';
    
	public byte[] generar (ComisionOficialDTO comisionOficialDTO) {
		 
		 try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(RUTA);
			XWPFDocument plantilla = new XWPFDocument(inputStream);
			
			Map <String, String> camposReporte = new  HashMap<String, String> ();
			  
              camposReporte.put(SIGNO_APERTURA + "nombreCompleto" + SIGNO_CIERRE, comisionOficialDTO.getNombreCompleto());
              camposReporte.put(SIGNO_APERTURA + "rfc" + SIGNO_CIERRE,comisionOficialDTO.getRfc());
              camposReporte.put(SIGNO_APERTURA + "curp" + SIGNO_CIERRE,comisionOficialDTO.getCurp());
              camposReporte.put(SIGNO_APERTURA + "lugar" + SIGNO_CIERRE,comisionOficialDTO.getLugar());
              camposReporte.put(SIGNO_APERTURA + "adscripcion" + SIGNO_CIERRE, comisionOficialDTO.getAdscripcion());
              camposReporte.put(SIGNO_APERTURA + "fecha" + SIGNO_CIERRE,comisionOficialDTO.getFecha());
              camposReporte.put(SIGNO_APERTURA + "directorAdministracion" + SIGNO_CIERRE,comisionOficialDTO.getDirectorAdministracion());

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
