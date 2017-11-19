
package mx.gob.saludtlax.rh.acumulados;

import java.io.Serializable;
import java.math.BigDecimal;

public class AcumuladosDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3850666708904958539L;
    private Integer idAcumulado;
    private String numEmpleado;
    private String rfc;
    private String nombre;
    private String funcion;
    private String ur;
    private String gf;
    private String fn;
    private String sf;
    private String partida;
    private String puesto;
    private String p_qna_i;
    private String p_qna_f;
    private Integer qna_real;
    private Integer anio_real;
    private Integer id_concepto_nomina;
    private String clave;
    private String descripcion;
    private BigDecimal importe;

    public Integer getIdAcumulado() {
        return idAcumulado;
    }

    public void setIdAcumulado(Integer idAcumulado) {
        this.idAcumulado = idAcumulado;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getUr() {
        return ur;
    }

    public void setUr(String ur) {
        this.ur = ur;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getP_qna_i() {
        return p_qna_i;
    }

    public void setP_qna_i(String p_qna_i) {
        this.p_qna_i = p_qna_i;
    }

    public String getP_qna_f() {
        return p_qna_f;
    }

    public void setP_qna_f(String p_qna_f) {
        this.p_qna_f = p_qna_f;
    }

    public Integer getId_concepto_nomina() {
        return id_concepto_nomina;
    }

    public void setId_concepto_nomina(Integer id_concepto_nomina) {
        this.id_concepto_nomina = id_concepto_nomina;
    }

    public Integer getQna_real() {
        return qna_real;
    }

    public void setQna_real(Integer qna_real) {
        this.qna_real = qna_real;
    }

    public Integer getAnio_real() {
        return anio_real;
    }

    public void setAnio_real(Integer anio_real) {
        this.anio_real = anio_real;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

}
