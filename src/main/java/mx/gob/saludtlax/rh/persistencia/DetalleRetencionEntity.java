
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kisin-hp1 Eduardo N Castillo Caballero <eduardo.castillo.caballero@hotmail.com>
 * @version 1.0 10/01/2017
 */

@Entity
@Table(name = "consulta_detalle_retenciones")
public class DetalleRetencionEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1840685612994823533L;

    @Id
    @Column(name = "id_detalle_retencion_cuenta_bancaria")
    //	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleRetencionCuentaBancaria;

    @Column(name = "id_concepto_nomina")
    private Integer idConceptoNomina;

    @Column(name = "descripcion_concepto_nomina")
    private String descripcionConceptoNomina;

    @Column(name = "cuenta_contable_retencion")
    private String cuentaContableRetencion;

    @Column(name = "descripcion_cuenta_contable")
    private String descripcionCuentaContable;

    @Column(name = "cuenta_retencion")
    private String cuentaRetencion;

    @Column(name = "cuenta_contable_banco")
    private String cuentaContableBanco;

    @Column(name = "descripcion_cuenta_banco")
    private String descripcionCuentaBanco;

    public Integer getIdDetalleRetencionCuentaBancaria() {
        return idDetalleRetencionCuentaBancaria;
    }

    public void setIdDetalleRetencionCuentaBancaria(Integer idDetalleRetencionCuentaBancaria) {
        this.idDetalleRetencionCuentaBancaria = idDetalleRetencionCuentaBancaria;
    }

    public Integer getIdConceptoNomina() {
        return idConceptoNomina;
    }

    public void setIdConceptoNomina(Integer idConceptoNomina) {
        this.idConceptoNomina = idConceptoNomina;
    }

    public String getDescripcionConceptoNomina() {
        return descripcionConceptoNomina;
    }

    public void setDescripcionConceptoNomina(String descripcionConceptoNomina) {
        this.descripcionConceptoNomina = descripcionConceptoNomina;
    }

    public String getCuentaContableRetencion() {
        return cuentaContableRetencion;
    }

    public void setCuentaContableRetencion(String cuentaContableRetencion) {
        this.cuentaContableRetencion = cuentaContableRetencion;
    }

    public String getDescripcionCuentaContable() {
        return descripcionCuentaContable;
    }

    public void setDescripcionCuentaContable(String descripcionCuentaContable) {
        this.descripcionCuentaContable = descripcionCuentaContable;
    }

    public String getCuentaRetencion() {
        return cuentaRetencion;
    }

    public void setCuentaRetencion(String cuentaRetencion) {
        this.cuentaRetencion = cuentaRetencion;
    }

    public String getCuentaContableBanco() {
        return cuentaContableBanco;
    }

    public void setCuentaContableBanco(String cuentaContableBanco) {
        this.cuentaContableBanco = cuentaContableBanco;
    }

    public String getDescripcionCuentaBanco() {
        return descripcionCuentaBanco;
    }

    public void setDescripcionCuentaBanco(String descripcionCuentaBanco) {
        this.descripcionCuentaBanco = descripcionCuentaBanco;
    }

}
