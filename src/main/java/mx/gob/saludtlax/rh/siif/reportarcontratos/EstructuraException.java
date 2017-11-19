
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EstructuraException extends Exception {

    private static final long serialVersionUID = 1886723465981408708L;

    private String codigo;

    private Integer fila;

    private char columna;

    private Integer indice;

    public static final String ERROR_INDICE_INVALIDO = "No se ha proporcionado un índice válido";

    public static final String ERROR_CLASE_INVALIDA = "El tipo de dato especificado no corresponde a los designados para procesar archivos de excel.";

    public static final String ERROR_STRING_TO_DATE = "Se encontró un texto que no se puede convertir a fecha.";

    public static final String ERROR_STRING_TO_INTEGER = "Se encontró un texto que no se puede convertir a un valor numérico entero.";

    public static final String ERROR_STRING_TO_DOUBLE = "Se encontró un texto que no se puede convertir a un valor numérico real.";

    public static final String ERROR_STRING_TO_BIGDECIMAL = "Se encontró un texto que no se puede convertir a un valor numérico decimal.";

    public static final String ERROR_STRING_TO_LONG = "Se encontró un texto que no se puede convertir a un valor numérico grande.";

    public static final String ERROR_INTEGER_TO_DATE = "Se encontró un valor númerico entero que no se puede convertir a fecha.";

    public static final String ERROR_DOUBLE_TO_DATE = "Se encontró un valor númerico real que no se puede convertir a fecha.";

    public static final String ERROR_BIGDECIMAL_TO_DATE = "Se encontró un valor númerico decimal que no se puede convertir a fecha.";

    public static final String ERROR_LONG_TO_DATE = "Se encontró un valor númerico grande que no se puede convertir a fecha.";

    public EstructuraException(String codigo, Integer fila, char columna) {
        super("Error:	" + codigo + "\nFila:	" + fila + "\nColumna:		" + columna);

        this.codigo = codigo;
        this.fila = fila;
        this.columna = columna;

    }

    public EstructuraException(String codigo, Integer indice) {
        super("Error:	" + codigo + "\nIndice:	" + indice);

        this.codigo = codigo;
        this.indice = indice;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getFila() {
        return fila;
    }

    public char getColumna() {
        return columna;
    }

    public Integer getIndice() {
        return indice;
    }

}