package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siif_contratos")
public class EstructuraContratoExcelEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estructura")
    private String idEstructura;
    @Column(name = "num")
    private Integer num;
    @Column(name = "programa")
    private String programa;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "rfc_val")
    private String rfcVal;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "f_i")
    private String fI;
    @Column(name = "c_responsable")
    private String cResponsable;
    @Column(name = "funcion")
    private String funcion;
    @Column(name = "rama")
    private String rama;
    @Column(name = "f_finan")
    private String fFinan;
    @Column(name = "prog")
    private String prog;

    @Column(name = "total_bruto")
    private BigDecimal totalBruto;
    @Column(name = "percepciones")
    private BigDecimal percepciones;
    @Column(name = "deducciones")
    private BigDecimal deducciones;
    @Column(name = "neto")
    private BigDecimal neto;

    @Column(name = "sueldo")
    private BigDecimal sueldo;
    @Column(name = "sup")
    private BigDecimal sup;
    @Column(name = "comp")
    private BigDecimal comp;
    @Column(name = "ag")
    private BigDecimal ag;
    @Column(name = "subsidio")
    private BigDecimal subsidio;
    @Column(name = "vac")
    private Integer vac;
    @Column(name = "r_faltas")
    private Integer rFaltas;
    @Column(name = "retroa")
    private BigDecimal retroa;
    @Column(name = "otros")
    private BigDecimal otros;
    @Column(name = "faltas")
    private Integer faltas;
    @Column(name = "istp")
    private BigDecimal istp;
    @Column(name = "respons")
    private Integer respons;
    @Column(name = "prestamo")
    private Integer prestamo;
    @Column(name = "pa")
    private BigDecimal pa;
    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "verifica")
    private String verifica;
    @Column(name = "t_centro")
    private String tCentro;
    @Column(name = "c_clues")
    private String cClues;
    @Column(name = "nom_unidad")
    private String nomUnidad;
    @Column(name = "a_adscripcion")
    private String aAdscripcion;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "c_puesto")
    private String cPuesto;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "ramas")
    private String ramas;
    @Column(name = "turno")
    private String turno;

    @Column(name = "id_nombramiento")
    private Integer idNombramiento;
    @Column(name = "tipo_emision_nomina")
    private String tipoEmisionNomina;
    @Column(name = "id_siif_bitacoras")
    private Integer idSiifBitacora;

    /**
     * **************Getters and Setters**********
     */
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
}
