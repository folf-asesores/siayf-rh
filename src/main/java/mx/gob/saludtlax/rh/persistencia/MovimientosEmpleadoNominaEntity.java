package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author José Pablo
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movimientos_empleado_nominas")
public class MovimientosEmpleadoNominaEntity implements Serializable {

    private static final long serialVersionUID = -8659182702465640962L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento_fijo")
    private Integer idMovimientoFijo;

    @Column(name = "id_tercero_institucional")
    private Integer terceroInstitucional;

    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "quincena_operacion")
    private Integer quincenaOperacion;

    @Column(name = "anyo_operacion")
    private Integer anyoOperacion;

    @Column(name = "importe_descontado")
    private BigDecimal importeDescuento;

    @Column(name = "quincena_inicial")
    private Integer quincenaInicial;

    @Column(name = "anio_inicial")
    private Integer anioInicial;

    @Column(name = "quincena_final")
    private Integer quincenaFinal;

    @Column(name = "anio_final")
    private Integer anioFinal;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "folio_documento")
    private String folioDocumento;

    @Column(name = "fecha_documento")
    private Date fechaDocumento;

    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "dias")
    private Integer dias;

    public Integer getIdMovimientoFijo() {
        return idMovimientoFijo;
    }

    public void setIdMovimientoFijo(Integer idMovimientoFijo) {
        this.idMovimientoFijo = idMovimientoFijo;
    }

    public Integer getTerceroInstitucional() {
        return terceroInstitucional;
    }

    public void setTerceroInstitucional(Integer terceroInstitucional) {
        this.terceroInstitucional = terceroInstitucional;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getQuincenaOperacion() {
        return quincenaOperacion;
    }

    public void setQuincenaOperacion(Integer quincenaOperacion) {
        this.quincenaOperacion = quincenaOperacion;
    }

    public Integer getAnyoOperacion() {
        return anyoOperacion;
    }

    public void setAnyoOperacion(Integer anyoOperacion) {
        this.anyoOperacion = anyoOperacion;
    }

    public BigDecimal getImporteDescuento() {
        return importeDescuento;
    }

    public void setImporteDescuento(BigDecimal importeDescuento) {
        this.importeDescuento = importeDescuento;
    }

    public Integer getQuincenaInicial() {
        return quincenaInicial;
    }

    public void setQuincenaInicial(Integer quincenaInicial) {
        this.quincenaInicial = quincenaInicial;
    }

    public Integer getAnioInicial() {
        return anioInicial;
    }

    public void setAnioInicial(Integer anioInicial) {
        this.anioInicial = anioInicial;
    }

    public Integer getQuincenaFinal() {
        return quincenaFinal;
    }

    public void setQuincenaFinal(Integer quincenaFinal) {
        this.quincenaFinal = quincenaFinal;
    }

    public Integer getAnioFinal() {
        return anioFinal;
    }

    public void setAnioFinal(Integer anioFinal) {
        this.anioFinal = anioFinal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFolioDocumento() {
        return folioDocumento;
    }

    public void setFolioDocumento(String folioDocumento) {
        this.folioDocumento = folioDocumento;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

}
