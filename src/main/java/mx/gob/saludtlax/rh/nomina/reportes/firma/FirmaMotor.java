/*
 * FirmaMotor.java
 * Creado el 07/sep/2017 6:48:15 PM
 * 
 */

package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.ArchivoUtil;

import static mx.gob.saludtlax.rh.util.FechaUtil.LUGAR_MEXICO;
import static mx.gob.saludtlax.rh.util.ArchivoUtil.SEPARADOR_DE_ARCHIVO;
import static mx.gob.saludtlax.rh.util.ArchivoUtil.SEPARADOR_DE_ARCHIVO_WINDOWS;
import static mx.gob.saludtlax.rh.util.ArchivoUtil.WINDOWS_LATIN_CHARSET;

/**
 * Esta clase es el motor para la generación del reporte en texto plano del 
 * listado de firmas.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaMotor {

    private static final Logger LOGGER = Logger.getLogger(FirmaMotor.class.getName());

    private static final String ENCABEZADO_SECRETARIA = "SALUD DE TLAXCALA";
    private static final String ENCABEZADO_DIRECCION = " DIRECCION DE ADMINISTRACION";
    private static final String ENCABEZADO_SUBDIRECCION = "SUBDIRECCION DE RECURSOS HUMANOS";
    private static final String ENCABEZADO_DEPARTAMENTO = "DEPARTAMENTO DE PERSONAL EVENTUAL";
    private static final String ENCABEZADO_TITULOS_DE_LAS_COLUMNAS
            = "                                                                                  NUMERO DE\n"
            + " FILIACION               N O M B R E                        PERIODO DE PAGO        CHEQUE       IMPORTE        F  I  R  M  A      NUM.\n";

    private static final String PATRON_ENCABEZADO_NUMERO_DE_PAGINA = "PAGINA: %1$ ,7d";
    private static final String PATRON_ENCABEZADO_DEL_PROGRAMA = "NÓMINA DE %1$S CORRESPONDIENTE A LA %2$S QUINCENA DE %3$TB DE %3$TY";
    private static final String PATRON_ENCABEZADO_UNIDAD_RESPONSABLE = "LISTADO DE FIRMAS                                       %1$20.20s                                        FECHA: %2$td-%2$tm-%2$tY";
    private static final String PATRON_DETALLE_FIRMA = " %1$-13.13S %2$-39.39S %3$td-%3$Tb-%3$tY AL %4$td-%4$Tb-%4$tY  %5$7.7S $ %6$ ,13.2f ______________________ %7$06d ";
    private static final String PATRON_TOTAL_POR = "TOTAL %1$-10.10S : %2$4d";
    private static final String PATRON_IMPORTE_TOTAL = "$ %1$ ,13.2f";
    
    private static final Integer COLUMNAS_POR_HOJA = 136;
    private static final Integer LINEAS_POR_HOJA = 66;
    
    public byte[] obtenerArchivo(FirmaDTO firma) {
        try {
            if (!SEPARADOR_DE_ARCHIVO_WINDOWS.equals(SEPARADOR_DE_ARCHIVO)) {
                System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO_WINDOWS);
            }

            Path ruta = Files.createTempFile("firma", ".txt");

            try (BufferedWriter out = Files.newBufferedWriter(ruta, WINDOWS_LATIN_CHARSET)) {
                StringBuilder sb = llenarReporte(firma);
                out.append(sb);
                out.flush();
            }

            ArchivoUtil.eliminarEspaciosAlFinalLinea(ruta, WINDOWS_LATIN_CHARSET);
            byte[] reporte = Files.readAllBytes(ruta);
            Files.deleteIfExists(ruta);

            System.setProperty("line.separator", SEPARADOR_DE_ARCHIVO);
            return reporte;
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    private StringBuilder llenarReporte(FirmaDTO firma) {
        StringBuilder sb = new StringBuilder();
        int contadorLineas = 0;
        int pagina = 1;

        try (Formatter formatter = new Formatter(sb, LUGAR_MEXICO)) {
            int contadorEmpleadosGeneral = 0;
            int contadorProgramas = 1;
            int totalProgramas = firma.getProgramas().size();
            BigDecimal totalGeneral = BigDecimal.ZERO;
            
            for(ProgramaDTO programa : firma) {
                int contadorEmpleadosPrograma = 0;
                int contadorUnidadesResponsables = 0;
                int totalUnidadesResponsables = programa.getUnidadesResponsables().size();
                BigDecimal totalPrograma = BigDecimal.ZERO;

                for(UnidadResponsableDTO unidadResponsable : programa) {
                    int contadorEmpleadosUnidadResponsable = 0;
                    BigDecimal totalUnidadResponsable = BigDecimal.ZERO;
                    contadorLineas = contadorLineas + llenarEncabezado(formatter, pagina, programa.getPrograma(), firma.getQuincena(), firma.getFechaPago(), unidadResponsable.getUnidadResponsable(), Calendar.getInstance().getTime());

                    for (FirmaEmpleadoDTO firmaEmpleado : unidadResponsable) {
                        int lineasRestantes = LINEAS_POR_HOJA - (contadorLineas % LINEAS_POR_HOJA);
                        
                        if (lineasRestantes > 5) {
                            contadorLineas = contadorLineas + llenarDetalle(formatter, firmaEmpleado, programa.getInicioPeriodo(), programa.getFinPeriodo(), contadorEmpleadosPrograma + 1);
                        } else {
                            agregarLineas(lineasRestantes, formatter);
                            contadorLineas = contadorLineas + lineasRestantes;
                            pagina = (contadorLineas / LINEAS_POR_HOJA) + 1;
                            contadorLineas = contadorLineas + llenarEncabezado(formatter, pagina, programa.getPrograma(), firma.getQuincena(), firma.getFechaPago(), unidadResponsable.getUnidadResponsable(), Calendar.getInstance().getTime());
                            contadorLineas = contadorLineas + llenarDetalle(formatter, firmaEmpleado, programa.getInicioPeriodo(), programa.getFinPeriodo(), contadorEmpleadosPrograma + 1);   
                        }

                        totalUnidadResponsable = totalUnidadResponsable.add(firmaEmpleado.getImporte());
                        totalGeneral = totalGeneral.add(firmaEmpleado.getImporte());
                        totalPrograma = totalPrograma.add(firmaEmpleado.getImporte());
                        contadorEmpleadosPrograma++;
                        contadorEmpleadosGeneral++;
                        contadorEmpleadosUnidadResponsable++;
                    }
                    
                    contadorUnidadesResponsables++;
                    contadorLineas = contadorLineas + llenarFinUnidadResponsable(formatter, contadorEmpleadosUnidadResponsable, totalUnidadResponsable);
                    
                    if(contadorUnidadesResponsables == totalUnidadesResponsables) {
                        contadorLineas = contadorLineas + llenarFinPrograma(formatter, contadorEmpleadosPrograma, totalPrograma);

                        if(contadorProgramas == totalProgramas) {
                            contadorLineas = contadorLineas + llenarFinGeneral(formatter, contadorEmpleadosGeneral, totalGeneral);
                            contadorLineas = contadorLineas + llenarFirmas(formatter, firma.getNombreElaboro(), firma.getCargoElaboro(), firma.getNombreElaboro(), firma.getCargoElaboro(), firma.getNombreAutorizo(), firma.getCargoAutorizo());
                        }
                    } 

                    int diferencia = LINEAS_POR_HOJA - (contadorLineas % LINEAS_POR_HOJA);
                    agregarLineas(diferencia, formatter);
                    contadorLineas = contadorLineas + diferencia;
                    pagina = (contadorLineas / LINEAS_POR_HOJA) + 1;
                }

                contadorProgramas++;
                LOGGER.infov("Contador: {0}", contadorProgramas);
            }

            LOGGER.infov("Contador final: {0}", contadorProgramas);
        }

        return sb;
    }

    private int llenarEncabezado(Formatter formatter, int pagina, String programa, String quincena, Date fechaPago, String unidadResponsable, Date fechaImpresion) {
        int contadorLineas = 0;
        
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        agregarEspacio(60, formatter);
        formatter.format(ENCABEZADO_SECRETARIA);
        agregarEspacio(43, formatter);
        formatter.format(PATRON_ENCABEZADO_NUMERO_DE_PAGINA, pagina);
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        formatter.format(centrarTexto(ENCABEZADO_DIRECCION, COLUMNAS_POR_HOJA));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        formatter.format(centrarTexto(ENCABEZADO_SUBDIRECCION, COLUMNAS_POR_HOJA));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        formatter.format(centrarTexto(ENCABEZADO_DEPARTAMENTO, COLUMNAS_POR_HOJA));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        formatter.format(centrarTexto(COLUMNAS_POR_HOJA, PATRON_ENCABEZADO_DEL_PROGRAMA, programa, quincena, fechaPago));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        // TODO: Freddy Centrar texto de la unidad responsable sin que se corte.
        agregarEspacio(1, formatter);
        formatter.format(PATRON_ENCABEZADO_UNIDAD_RESPONSABLE, unidadResponsable, fechaImpresion);
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        agregarLineaDivision(formatter);
        formatter.format(ENCABEZADO_TITULOS_DE_LAS_COLUMNAS);
        agregarLineaDivision(formatter);
        contadorLineas = contadorLineas + 4;
        
        return contadorLineas;
    }
    
    private int llenarDetalle(Formatter formatter, FirmaEmpleadoDTO firmaEmpleado, Date inicioPeriodo, Date finPeriodo, int contador) {
        int contadorLineas = 0;
        
        agregarLineas(1, formatter);
        formatter.format(PATRON_DETALLE_FIRMA, firmaEmpleado.getFiliacion(), firmaEmpleado.getNombre(), inicioPeriodo, finPeriodo, firmaEmpleado.getNumeroCheque(), firmaEmpleado.getImporte(), contador);
        agregarLineas(2, formatter);
        contadorLineas = contadorLineas + 3;
        
        return contadorLineas;
    }
    
    private int llenarFinUnidadResponsable(Formatter formatter, int totalEmpleadosUnidadResponsable, BigDecimal importeUnidadResponsable) {
        int contadorLineas = 0;

        agregarLineas(2, formatter);
        contadorLineas = contadorLineas + 2;
        
        agregarEspacio(9, formatter);
        formatter.format(PATRON_TOTAL_POR, "POR CENTRO", totalEmpleadosUnidadResponsable);
        agregarEspacio(59, formatter);
        formatter.format(PATRON_IMPORTE_TOTAL, importeUnidadResponsable);
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;
        
        return contadorLineas;
    }

    private int llenarFinPrograma(Formatter formatter, int totalEmpleadosPrograma, BigDecimal importePrograma) {
        int contadorLineas = 0;

        agregarEspacio(9, formatter);
        formatter.format(PATRON_TOTAL_POR, "POR NOMINA", totalEmpleadosPrograma);
        agregarEspacio(59, formatter);
        formatter.format(PATRON_IMPORTE_TOTAL, importePrograma);
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;
        
        return contadorLineas;
    }

    private int llenarFinGeneral(Formatter formatter, int totalEmpleadosPrograma, BigDecimal importePrograma) {
        int contadorLineas = 0;

        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;
        
        agregarEspacio(9, formatter);
        formatter.format(PATRON_TOTAL_POR, "GENERAL", totalEmpleadosPrograma);
        agregarEspacio(59, formatter);
        formatter.format(PATRON_IMPORTE_TOTAL, importePrograma);
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;
        
        return contadorLineas;
    }

    private int llenarFirmas(Formatter formatter, String nombreElaboro, String cargoElaboro, String nombreReviso, String cargoReviso, String nombreAutorizo, String cargoAutorizo) {
        int contadorLineas = 0;
        
        agregarLineas(4, formatter);
        contadorLineas = contadorLineas + 4;
        
        agregarEspacio(6, formatter);
        formatter.format("E  L  A  B  O  R  O");
        agregarEspacio(30, formatter);
        formatter.format("R  E  V  I  S  O");
        agregarEspacio(33, formatter);
        formatter.format("A  U  T  O  R  I  Z  O");
        agregarLineas(3, formatter);
        contadorLineas = contadorLineas + 3;
        
        try {
            contadorLineas = contadorLineas + agregarLineasFirma(formatter);
        } catch (IOException ex) {
            LOGGER.warn(ex);
        }

        formatter.format(centrarTexto(nombreElaboro, 35));
        agregarEspacio(14, formatter);
        formatter.format(centrarTexto(nombreReviso, 35));
        agregarEspacio(14, formatter);
        formatter.format(centrarTexto(nombreAutorizo, 35));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        formatter.format(centrarTexto(cargoElaboro, 35));
        agregarEspacio(14, formatter);
        formatter.format(centrarTexto(cargoReviso, 35));
        agregarEspacio(14, formatter);
        formatter.format(centrarTexto(cargoAutorizo, 35));
        agregarLineas(1, formatter);
        contadorLineas = contadorLineas + 1;

        return contadorLineas;
    }

    private void agregarEspacio(int cantidad, Formatter formatter) {
        Appendable appendable = formatter.out();

        for (int i = 0; i < cantidad; i++) {
            try {
                appendable.append(' ');
            } catch (IOException ex) {
                LOGGER.warn(ex);
            }
        }
    }

    private void agregarLineas(int cantidad, Formatter formatter) {
        Appendable appendable = formatter.out();

        for (int i = 0; i < cantidad; i++) {
            try {
                appendable.append(System.getProperty("line.separator"));
            } catch (IOException ex) {
                LOGGER.warn(ex);
            }
        }
    }

    private void agregarLineaDivision(Formatter formatter) {
        try {
            Appendable appendable = formatter.out();
            int numeroCaracteres = 135;
            appendable.append(' ');

            for (int i = 0; i < numeroCaracteres; i++) {
                appendable.append('=');
            }
            appendable.append(' ');
            appendable.append('\n');

        } catch (IOException ex) {
            LOGGER.warn(ex);
        }
    }

    private int agregarLineasFirma(Formatter formatter) throws IOException {
        Appendable appendable = formatter.out();
        int numeroCaracteres = 35;

        for (int i = 0; i < 3; i++) {
            int espacios;

            switch (i) {
                case 0:
                    espacios = 0;
                    break;
                case 1:
                    espacios = 14;
                    break;
                case 2:
                    espacios = 14;
                    break;
                default :
                    espacios = 10;
                    break;

            }

            agregarEspacio(espacios, formatter);

            for(int j = 0; j < numeroCaracteres; j++) {
                appendable.append("_");
            }
        }

        appendable.append("\n");

        return 1;
    }
    
    private static String centrarTexto(int limite, String patron, Object ... args) {
        try (Formatter formatter = new Formatter(LUGAR_MEXICO)) {
            formatter.format(patron, args);
            
            return centrarTexto(formatter.toString(), limite);
        }
    }

    private static String centrarTexto(String texto, int limite) {
        if (texto == null) {
            return null;
        }

        if (texto.length() > limite) {
            return texto.substring(0, limite);
        }

        String patron = "%" + limite + "s%s%" + limite + "s";
        String salida = String.format(patron, "", texto, "");
        float mitad = (salida.length() / 2);
        float inicio = mitad - (limite / 2);
        float fin = inicio + limite;
        return salida.substring((int) inicio, (int) fin);
    }
}
