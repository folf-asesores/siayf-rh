/*
 * PlainTextReport.java
 * Creado el 27/Mar/2017 10:45:10 AM
 *
 */
package mx.gob.saludtlax.rh.nomina.reportes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import org.junit.Test;

/**
 *
 * @author Freddy Barrera (freddy.barrera@gmail.com)
 */
public class PlainTextReport {

    private static final Locale LOCALIZACION_MEXICO = new Locale("es", "MX");
    private static final String PATRON_ENCABEZADO_SALUD = "\n                                                                                                         SALUD DE TLAXCALA                                                                                             PAGINA: %1$ ,7d\n";
    private static final String ENCABEZADO_SUBDIRECCION = "                                                                                                  SUBDIRECCION DE RECURSOS HUMANOS\n";
    private static final String PATRON_ENCABEZADO_SISTEMA = "                                                                                               SISTEMA DE ADMINISTRACION DE PERSONAL\n";
    private static final String PATRON_ENCABEZADO_DEL_PROGRAMA = "                                                                            NOMINA DE %1$s CORRESPONDIENTE A LA %2$s QUINCENA DE %3$tB DE %3$tY\n";
    private static final String ENCABEZADO_LINEA_DE_DIVISION = " ======================================================================================================================================================================================================================================\n";
    private static final String ENCABEZADO_TITULOS_DE_LAS_COLUMNAS = "                                                                                                                                                                                                                            NETO\n No.     R.F.C.               N   O   M   B   R   E                           PERIODO DE PAGO                      CL   DESCRIPCIÓN                   PERCEPCIÓN     CL   DESCRIPCIÓN                   DEDUCCIÓN         A PAGAR\n";
    private static final String PATRON_UNIDAD_RESPONSABLE = "\n     %1s ( %2d )\n";
    private static final String PATRON_DETALLE_PRIMERA_PARTE = " %1$ ,4d  %2$.13s   %3$-48s  %4$td-%4$tb-%4$tY AL %5$td-%5$tb-%5$tY";
    private static final String PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA = "%1$ 18d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA = "%1$ 7d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA = "\n%1$ 117d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA = "%1$ 7d   %2$-26s   %3$ ,11.2f";
    private static final String PATRON_DETALLE_TOTALES = "\n%1$ ,160.2f%2$ ,50.2f%3$ ,15.2f";

    public PlainTextReport() {
    }

    @Test
    public void generarReporte() {
        try(PrintWriter out = new PrintWriter("/home/neo_cs/report.txt" )) {
            out.print(getEncabezado(1, "ATENCIÓN A LA SALUD", "2A.", Calendar.getInstance().getTime(), "SUBDIRECCIÓN DE PRIMER NIVEL", 1300));
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, 5);
            List<Percepcion> percepciones1 = new ArrayList<>();
            percepciones1.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
            List<Percepcion> percepciones2 = new ArrayList<>();
            percepciones2.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
            percepciones2.add(new Percepcion(26, "SUBSIDIO", new BigDecimal("55.5")));
            List<Percepcion> percepciones3 = new ArrayList<>();
            percepciones3.add(new Percepcion(5, "SUPLENCIAS", new BigDecimal("2180")));
            percepciones3.add(new Percepcion(26, "SUBSIDIO", new BigDecimal("55.5")));
            percepciones3.add(new Percepcion(32, "OTROS", new BigDecimal("3300.00")));
            List<Deduccion> deducciones1 = new ArrayList<>();
            deducciones1.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
            List<Deduccion> deducciones2 = new ArrayList<>();
            deducciones2.add(new Deduccion(51, "FALTAS Y RETARDOS", new BigDecimal("34.415")));
            deducciones2.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
            List<Deduccion> deducciones3 = new ArrayList<>();
            deducciones3.add(new Deduccion(51, "FALTAS Y RETARDOS", new BigDecimal("34.415")));
            deducciones3.add(new Deduccion(52, "I.S.R.", new BigDecimal("342.5")));
            deducciones3.add(new Deduccion(62, "PENSION ALIMENTICIA", new BigDecimal("3516")));

            out.print(getDetalle(1, "CAME750818R3A", "CAPORAL MENDIETA MA ELENA", cal.getTime(), Calendar.getInstance().getTime(), percepciones1, deducciones1));
            out.print(getDetalle(2, "COGO770228MU5", "CORDERO GONZALEZ OSVALDO", cal.getTime(), Calendar.getInstance().getTime(), percepciones2, deducciones2));
            out.print(getDetalle(3, "COHS910628CD4", "CONTRERAS HERNANDEZ SANDRA NELY", cal.getTime(), Calendar.getInstance().getTime(), percepciones3, deducciones3));
            out.print(getDetalle(4, "CUMM8702236M7", "CUECUECHA MENDIETA MARTHA PAOLA", cal.getTime(), Calendar.getInstance().getTime(), null, deducciones1));
            out.print(getDetalle(5, "CUMY910419HI3", "CUECUECHA MENDOZA YANET", cal.getTime(), Calendar.getInstance().getTime(), percepciones1, deducciones2));
            out.print(getDetalle(6, "CUSE890829LS7", "CUAMATZI SANCHEZ EDUARDO", cal.getTime(), Calendar.getInstance().getTime(), percepciones2, deducciones3));
            out.print(getDetalle(7, "CUSJ870703AG6", "CUAPIO SANCHEZ JAVIER", cal.getTime(), Calendar.getInstance().getTime(), percepciones1, deducciones3));
            out.print(getDetalle(8, "CUTJ8809095A0", "CUAHUTECATL TETLACUILO JEMMY", cal.getTime(), Calendar.getInstance().getTime(), null, null));
            out.print(getDetalle(9, "DESR741226269", "DELGADO SANTIAGO RUT", cal.getTime(), Calendar.getInstance().getTime(), null, null));
            out.print(getDetalle(10, "DOHE860510UT7", "DOMINGUEZ HERNANDEZ ELMAR ARMANDO", cal.getTime(), Calendar.getInstance().getTime(), null, null));
        } catch (FileNotFoundException ex) {
            // No importa el error
        }
    }

    private String getEncabezado(int numeroPagina, String programa, String quincena, Date fecha, String unidadResponsable, int numeroUnidadResponsable) {
        StringBuilder sb = new StringBuilder();
        sb.append((new Formatter()).format(PATRON_ENCABEZADO_SALUD, numeroPagina).toString());
        sb.append(ENCABEZADO_SUBDIRECCION);
        sb.append(PATRON_ENCABEZADO_SISTEMA);
        sb.append((new Formatter(LOCALIZACION_MEXICO)).format(PATRON_ENCABEZADO_DEL_PROGRAMA, programa, quincena, fecha).toString().toUpperCase());
        sb.append(ENCABEZADO_LINEA_DE_DIVISION);
        sb.append(ENCABEZADO_TITULOS_DE_LAS_COLUMNAS);
        sb.append(ENCABEZADO_LINEA_DE_DIVISION);
        sb.append('\n');
        sb.append((new Formatter()).format(PATRON_UNIDAD_RESPONSABLE, unidadResponsable, numeroUnidadResponsable).toString());
        sb.append('\n');
        sb.append('\n');

        return sb.toString();
    }

    private String getDetalle(int ordinal, String rfc, String nombre, Date inicioPeriodoPago, Date finPeriodoPago, List<Percepcion> percepciones, List<Deduccion> deducciones) {
        StringBuilder sb = new StringBuilder();
        sb.append((new Formatter(LOCALIZACION_MEXICO)).format(PATRON_DETALLE_PRIMERA_PARTE, ordinal, rfc, nombre, inicioPeriodoPago, finPeriodoPago).toString().toUpperCase());
        if (percepciones != null && deducciones != null) {
            int count = 0;
            BigDecimal totalPercepciones = BigDecimal.ZERO;
            BigDecimal totalDeducciones = BigDecimal.ZERO;
            int limiteContador = percepciones.size() > deducciones.size() ?
                percepciones.size() : deducciones.size();

            for (int i = 0; i < limiteContador; i++) {
                Percepcion percepcion = i < percepciones.size() ? percepciones.get(i) : null;

                if(percepcion != null && count == 0) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_PERCEPCIONES_MISMA_LINEA, percepcion.getClave(), percepcion.getNombre(), percepcion.getMonto()));
                    totalPercepciones = totalPercepciones.add(percepcion.getMonto());
                } else if (percepcion != null) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_PERCEPCIONES_NUEVA_LINEA, percepcion.getClave(), percepcion.getNombre(), percepcion.getMonto()));
                    totalPercepciones = totalPercepciones.add(percepcion.getMonto());
                }

                Deduccion deduccion = i < deducciones.size() ? deducciones.get(i) : null;

                if (deduccion != null && count == 0) {
                    sb.append((new Formatter()).format(PATRON_DETALLE_DEDUCCIONES_MISMA_LINEA, deduccion.getClave(), deduccion.getNombre(), deduccion.getMonto()));
                    totalDeducciones = totalDeducciones.add(deduccion.getMonto());
                } else if (deduccion != null) {
                    if (percepciones.size() < deducciones.size()) {
                        sb.append("\n                                                                                                                                                                ");
                    }
                    sb.append((new Formatter()).format(PATRON_DETALLE_DEDUCCIONES_NUEVA_LINEA, deduccion.getClave(), deduccion.getNombre(), deduccion.getMonto()));
                    totalDeducciones = totalDeducciones.add(deduccion.getMonto());
                }
                count++;
            }
            sb.append((new Formatter()).format(PATRON_DETALLE_TOTALES, totalPercepciones, totalDeducciones, totalPercepciones.subtract(totalDeducciones)));
            sb.append('\n');
        } else {
            sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
    }

    class Percepcion {
        private int clave;
        private String nombre;
        private BigDecimal monto;

        public Percepcion() {
        }

        public Percepcion(int clave, String nombre, BigDecimal monto) {
            this.clave = clave;
            this.nombre = nombre;
            this.monto = monto;
        }

        public int getClave() {
            return clave;
        }

        public void setClave(int clave) {
            this.clave = clave;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public BigDecimal getMonto() {
            return monto;
        }

        public void setMonto(BigDecimal monto) {
            this.monto = monto;
        }
    }

    class Deduccion {
        private int clave;
        private String nombre;
        private BigDecimal monto;

        public Deduccion() {
        }

        public Deduccion(int clave, String nombre, BigDecimal monto) {
            this.clave = clave;
            this.nombre = nombre;
            this.monto = monto;
        }

        public int getClave() {
            return clave;
        }

        public void setClave(int clave) {
            this.clave = clave;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public BigDecimal getMonto() {
            return monto;
        }

        public void setMonto(BigDecimal monto) {
            this.monto = monto;
        }
    }
}
