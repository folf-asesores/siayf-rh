
package mx.gob.saludtlax.rh.nomina.aguinaldo;

import java.io.Serializable;
import java.math.BigDecimal;

public class AguinaldoResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2647314745308427018L;

    private BigDecimal excento;
    private BigDecimal gravado;
    private BigDecimal total;
    private Integer diasPagados;
    private BigDecimal importeAguinaldoCompleto;
    private BigDecimal importeAguinaldoPorDia;

    /**
     * Importe excento del aguinaldo
     *
     * @return
     */
    public BigDecimal getExcento() {
        return excento;
    }

    public void setExcento(BigDecimal excento) {
        this.excento = excento;
    }

    /**
     * Parte gravada del aguinaldo
     *
     * @return
     */
    public BigDecimal getGravado() {
        return gravado;
    }

    public void setGravado(BigDecimal gravado) {
        this.gravado = gravado;
    }

    /**
     * Total de aguinaldo que se le debera pagar al empleado de acuerdo a los
     * dias trabajados
     *
     * @return
     */
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Total de dias de aguinaldo que se le estan pagando al empleado
     *
     * @return
     */
    public Integer getDiasPagados() {
        return diasPagados;
    }

    public void setDiasPagados(Integer diasPagados) {
        this.diasPagados = diasPagados;
    }

    /**
     * Es el importe de referencia del aguinaldo que le corresponderia al
     * empleado en caso de no tener ninguna falta o motivo de descuento
     *
     * @return
     */
    public BigDecimal getImporteAguinaldoCompleto() {
        return importeAguinaldoCompleto;
    }

    public void setImporteAguinaldoCompleto(
            BigDecimal importeAguinaldoCompleto) {
        this.importeAguinaldoCompleto = importeAguinaldoCompleto;
    }

    /**
     * Importe de referencia que se le esta pagando al empleado por cada dia
     * trabajado por concepto de aguinaldo es el resultado de dividir el
     * aguinaldo entre los 365 dias del año
     *
     * @return
     */
    public BigDecimal getImporteAguinaldoPorDia() {
        return importeAguinaldoPorDia;
    }

    public void setImporteAguinaldoPorDia(BigDecimal importeAguinaldoPorDia) {
        this.importeAguinaldoPorDia = importeAguinaldoPorDia;
    }

    @Override
    public String toString() {

        return "Dias Pagados: " + diasPagados.toString() + " \n Total: "
                + total.toString() + " \n Excento: " + excento.toString()
                + " \n Gravado: " + gravado.toString()
                + " \n Importe Aguinaldo Completo: "
                + importeAguinaldoCompleto.toString()
                + " \n Importe Aguinaldo Por Dia: "
                + importeAguinaldoPorDia.toString();
    }

}
