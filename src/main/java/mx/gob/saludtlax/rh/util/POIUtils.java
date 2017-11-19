
package mx.gob.saludtlax.rh.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class POIUtils {

    /**
     * Remplaza todos los campos que recibe en el Map en todas las tablas.
     *
     * @param mapa
     *            campos y valores que serÃ¡n remplazados
     */
    public static void remplazarCamposTablas(Map<String, String> mapa,
            Iterator<XWPFTable> iterador) {

        while (iterador.hasNext()) {
            XWPFTable tabla = iterador.next();

            for (XWPFTableRow row : tabla.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph parrafo : cell.getParagraphs()) {
                        remplazarCampos(parrafo, mapa);
                    }
                }
            }
        }
    }

    /**
     * @param parrafo
     * @param remplazo
     */
    public static void remplazarCampos(XWPFParagraph parrafo,
            Map<String, String> remplazo) {
        List<XWPFRun> runs = parrafo.getRuns();

        for (XWPFRun run : runs) {
            String text = remplazarCampos(run.getText(0), remplazo);
            run.setText(text, 0);
        }
    }

    /**
     *
     * @param origen
     * @param remplazo
     * @return
     */
    public static String remplazarCampos(String origen,
            Map<String, String> remplazo) {
        String salida = origen;

        for (Map.Entry<String, String> r : remplazo.entrySet()) {
            String valor = r.getValue() == null ? "" : r.getValue();
            salida = salida == null ? "" : salida;
            //El signo de pesos afecta a la expresiÃ³n regular
            if (valor.contains("$")) {
                valor = "\\$" + valor.substring(1);
            }
            salida = salida.replaceAll(r.getKey(), valor);
        }

        return salida;
    }

}
