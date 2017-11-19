
package mx.gob.saludtlax.rh.persistencia;

/**
 *
 * @author José Pablo
 *
 */

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otros_pagos_comprobantes")
public class OtrosPagosComprobantesEntity implements Serializable {

    private static final long serialVersionUID = 4396931589155152880L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_otros_pagos")
    private Integer idOtrosPagos;
    @Column(name = "clave_sat")
    private String claveSat;
    @Column(name = "clave")
    private String clave;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "id_comprobante")
    private Integer idComprobante;

    public Integer getIdOtrosPagos() {
        return idOtrosPagos;
    }

    public void setIdOtrosPagos(Integer idOtrosPagos) {
        this.idOtrosPagos = idOtrosPagos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getClaveSat() {
        return claveSat;
    }

    public void setClaveSat(String claveSat) {
        this.claveSat = claveSat;
    }

}
