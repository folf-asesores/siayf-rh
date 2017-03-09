package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;

/**
 * 
 * @author José Pablo
 *
 */
public class ProductoNominaFiltroDTO implements Serializable {
    private static final long serialVersionUID = 70205961392740718L;

    private Integer idFuenteFinanciamiento;
    private Integer idSubfuenteFinanciamiento;
    private Integer idTipoPeriodo;
    private Integer idTipoNomina;
    private Integer idEstatus;
    private Integer ejercicioFiscal;
    private Integer numeroPeriodo;
    private Integer idArea;

//	private Date fechaNominaInicio;
//	private Date fechaNominaFin;

    public Integer getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }
    public void setIdFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }
    public Integer getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }
    public void setIdSubfuenteFinanciamiento(Integer idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }
    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }
    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }
    public Integer getIdTipoNomina() {
        return idTipoNomina;
    }
    public void setIdTipoNomina(Integer idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }
    public Integer getIdEstatus() {
        return idEstatus;
    }
    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }
    public Integer getEjercicioFiscal() {
        return ejercicioFiscal;
    }
    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }
    public Integer getNumeroPeriodo() {
        return numeroPeriodo;
    }
    public void setNumeroPeriodo(Integer numeroPeriodo) {
        this.numeroPeriodo = numeroPeriodo;
    }
    public Integer getIdArea() {
        return idArea;
    }
    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }
}