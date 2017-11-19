
package mx.gob.saludtlax.rh.siif.reportarcontratos;

import java.io.Serializable;
import java.math.BigDecimal;

public class EstructuraContratosExcelDTO implements Serializable {

    private static final long serialVersionUID = -4226990086634174773L;

    private String idEstructura;
    private Integer num;
    private String programa;
    private String rfc;
    private String rfcVal;
    private String nombre;
    private String fI;
    private String cResponsable;
    private String funcion;
    private String rama;
    private String fFinan;
    private String prog;

    private BigDecimal totalBruto;
    private BigDecimal percepciones;
    private BigDecimal deducciones;
    private BigDecimal neto;

    private BigDecimal sueldo;
    private BigDecimal sup;
    private BigDecimal comp;
    private BigDecimal ag;
    private BigDecimal subsidio;
    private Integer vac;
    private Integer rFaltas;
    private BigDecimal retroa;
    private BigDecimal otros;
    private Integer faltas;
    private BigDecimal istp;
    private Integer respons;
    private Integer prestamo;
    private BigDecimal pa;
    private BigDecimal total;

    private String verifica;
    private String tCentro;
    private String cClues;
    private String nomUnidad;
    private String aAdscripcion;
    private String puesto;
    private String cPuesto;
    private String servicio;
    private String ramas;
    private String turno;

    private Integer idNombramiento;
    private String tipoEmisionNomina;
    private Integer idSiifBitacora;

    public String getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(String idEstructura) {
        this.idEstructura = idEstructura;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfcVal() {
        return rfcVal;
    }

    public void setRfcVal(String rfcVal) {
        this.rfcVal = rfcVal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfI() {
        return fI;
    }

    public void setfI(String fI) {
        this.fI = fI;
    }

    public String getcResponsable() {
        return cResponsable;
    }

    public void setcResponsable(String cResponsable) {
        this.cResponsable = cResponsable;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getfFinan() {
        return fFinan;
    }

    public void setfFinan(String fFinan) {
        this.fFinan = fFinan;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }

    public BigDecimal getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(BigDecimal percepciones) {
        this.percepciones = percepciones;
    }

    public BigDecimal getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(BigDecimal deducciones) {
        this.deducciones = deducciones;
    }

    public BigDecimal getNeto() {
        return neto;
    }

    public void setNeto(BigDecimal neto) {
        this.neto = neto;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public BigDecimal getSup() {
        return sup;
    }

    public void setSup(BigDecimal sup) {
        this.sup = sup;
    }

    public BigDecimal getComp() {
        return comp;
    }

    public void setComp(BigDecimal comp) {
        this.comp = comp;
    }

    public BigDecimal getAg() {
        return ag;
    }

    public void setAg(BigDecimal ag) {
        this.ag = ag;
    }

    public BigDecimal getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(BigDecimal subsidio) {
        this.subsidio = subsidio;
    }

    public Integer getVac() {
        return vac;
    }

    public void setVac(Integer vac) {
        this.vac = vac;
    }

    public Integer getrFaltas() {
        return rFaltas;
    }

    public void setrFaltas(Integer rFaltas) {
        this.rFaltas = rFaltas;
    }

    public BigDecimal getRetroa() {
        return retroa;
    }

    public void setRetroa(BigDecimal retroa) {
        this.retroa = retroa;
    }

    public BigDecimal getOtros() {
        return otros;
    }

    public void setOtros(BigDecimal otros) {
        this.otros = otros;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public BigDecimal getIstp() {
        return istp;
    }

    public void setIstp(BigDecimal istp) {
        this.istp = istp;
    }

    public Integer getRespons() {
        return respons;
    }

    public void setRespons(Integer respons) {
        this.respons = respons;
    }

    public Integer getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Integer prestamo) {
        this.prestamo = prestamo;
    }

    public BigDecimal getPa() {
        return pa;
    }

    public void setPa(BigDecimal pa) {
        this.pa = pa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getVerifica() {
        return verifica;
    }

    public void setVerifica(String verifica) {
        this.verifica = verifica;
    }

    public String gettCentro() {
        return tCentro;
    }

    public void settCentro(String tCentro) {
        this.tCentro = tCentro;
    }

    public String getcClues() {
        return cClues;
    }

    public void setcClues(String cClues) {
        this.cClues = cClues;
    }

    public String getNomUnidad() {
        return nomUnidad;
    }

    public void setNomUnidad(String nomUnidad) {
        this.nomUnidad = nomUnidad;
    }

    public String getaAdscripcion() {
        return aAdscripcion;
    }

    public void setaAdscripcion(String aAdscripcion) {
        this.aAdscripcion = aAdscripcion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getcPuesto() {
        return cPuesto;
    }

    public void setcPuesto(String cPuesto) {
        this.cPuesto = cPuesto;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getRamas() {
        return ramas;
    }

    public void setRamas(String ramas) {
        this.ramas = ramas;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }

    public String getTipoEmisionNomina() {
        return tipoEmisionNomina;
    }

    public void setTipoEmisionNomina(String tipoEmisionNomina) {
        this.tipoEmisionNomina = tipoEmisionNomina;
    }

    public Integer getIdSiifBitacora() {
        return idSiifBitacora;
    }

    public void setIdSiifBitacora(Integer idSiifBitacora) {
        this.idSiifBitacora = idSiifBitacora;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}