
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pago_nomina")
public class PagoNominaEntity implements Serializable {
    private static final long serialVersionUID = 3462579403234111661L;

    @Id
    @Column(name = "id_pago_nomina")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_nomina")
    private ProductoNominaEntity productoNomina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_financiamiento")
    private FuenteFinanciamientoEntity fuenteFinanciamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subfuente_financiamiento")
    private SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento;

    @Column(name = "fecha_pago")
    private Date fechaPago;

    @JoinColumn(name = "id_banco")
    @ManyToOne(fetch = FetchType.LAZY)
    private BancoSatEntity banco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta_bancaria")
    private CuentasBancariasEntity cuentaBancaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuente_finan_opd")
    private FuenteFinanciamientoOPDEntity fuenteFinanciamientoOPD;

    public Integer getIdPagoNomina() {
        return this.idPagoNomina;
    }

    public void setIdPagoNomina(Integer idPagoNomina) {
        this.idPagoNomina = idPagoNomina;
    }

    public ProductoNominaEntity getProductoNomina() {
        return this.productoNomina;
    }

    public void setProductoNomina(ProductoNominaEntity productoNomina) {
        this.productoNomina = productoNomina;
    }

    public FuenteFinanciamientoEntity getFuenteFinanciamiento() {
        return this.fuenteFinanciamiento;
    }

    public void setFuenteFinanciamiento(FuenteFinanciamientoEntity fuenteFinanciamiento) {
        this.fuenteFinanciamiento = fuenteFinanciamiento;
    }

    public SubFuenteFinanciamientoTempEntity getSubfuenteFinanciamiento() {
        return this.subfuenteFinanciamiento;
    }

    public void setSubfuenteFinanciamiento(SubFuenteFinanciamientoTempEntity subfuenteFinanciamiento) {
        this.subfuenteFinanciamiento = subfuenteFinanciamiento;
    }

    public Date getFechaPago() {
        return this.fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BancoSatEntity getBanco() {
        return this.banco;
    }

    public void setBanco(BancoSatEntity banco) {
        this.banco = banco;
    }

    public CuentasBancariasEntity getCuentaBancaria() {
        return this.cuentaBancaria;
    }

    public void setCuentaBancaria(CuentasBancariasEntity cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public FuenteFinanciamientoOPDEntity getFuenteFinanciamientoOPD() {
        return this.fuenteFinanciamientoOPD;
    }

    public void setFuenteFinanciamientoOPD(FuenteFinanciamientoOPDEntity fuenteFinanciamientoOPD) {
        this.fuenteFinanciamientoOPD = fuenteFinanciamientoOPD;
    }

}