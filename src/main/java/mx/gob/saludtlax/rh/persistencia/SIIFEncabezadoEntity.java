/*
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "siif_encabezados")
public class SIIFEncabezadoEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_siif_encabezado")
    private Integer idSIIFEncabezado;

    @Column(name = "id_nomina")
    private Integer idNomina;

    @Column(name = "id_poder")
    private Character idPoder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_nomina")
    private TipoNominaEntity tipoNomina;

    @Column(name = "fecha_fin_quincena")
    @Temporal(TemporalType.DATE)
    private Date fechaFinQuincena;

    @Column(name = "id_tipo_emision_nomina", length = 1)
    private String idTipoEmisionNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta_bancaria")
    private CuentasBancariasEntity cuentaBancaria;

    @Column(name = "percepciones")
    private BigDecimal percepciones;

    @Column(name = "deducciones")
    private BigDecimal deducciones;

    @Column(name = "neto")
    private BigDecimal neto;

    @Column(name = "id_estado_nomina")
    private Character idEstadoNomina;

    @Column(name = "id_nombramiento")
    private Integer idNombramiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_siif_bitacora")
    private SiifBitacoraEntity bitacora;

    public SIIFEncabezadoEntity() {
    }

    public Integer getIdSIIFEncabezado() {
        return idSIIFEncabezado;
    }

    public void setIdSIIFEncabezado(Integer idSIIFEncabezado) {
        this.idSIIFEncabezado = idSIIFEncabezado;
    }

    public Integer getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(Integer idNomina) {
        this.idNomina = idNomina;
    }

    public Character getIdPoder() {
        return idPoder;
    }

    public void setIdPoder(Character idPoder) {
        this.idPoder = idPoder;
    }

    public TipoNominaEntity getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNominaEntity tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Date getFechaFinQuincena() {
        return fechaFinQuincena;
    }

    public void setFechaFinQuincena(Date fechaFinQuincena) {
        this.fechaFinQuincena = fechaFinQuincena;
    }

    public String getIdTipoEmisionNomina() {
        return idTipoEmisionNomina;
    }

    public void setIdTipoEmisionNomina(String idTipoEmisionNomina) {
        this.idTipoEmisionNomina = idTipoEmisionNomina;
    }

    public CuentasBancariasEntity getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentasBancariasEntity cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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

    public Character getIdEstadoNomina() {
        return idEstadoNomina;
    }

    public void setIdEstadoNomina(Character idEstadoNomina) {
        this.idEstadoNomina = idEstadoNomina;
    }

    public SiifBitacoraEntity getBitacora() {
        return bitacora;
    }

    public void setBitacora(SiifBitacoraEntity bitacora) {
        this.bitacora = bitacora;
    }

    public Integer getIdNombramiento() {
        return idNombramiento;
    }

    public void setIdNombramiento(Integer idNombramiento) {
        this.idNombramiento = idNombramiento;
    }
}