/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import java.util.ArrayList;
import java.util.List;

import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.vacantes.seleccion.InfoPuestoDTO;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 17/08/2016 21:28:23
 */
public class InventarioPuestosView {
    private List<InventarioVacanteDTO> inventarios = new ArrayList<>();
    private List<InfoPuestoDTO> detallesInventarios = new ArrayList<>();
    private List<ResumenPuestoDTO> resumenPuestos = new ArrayList<>();
    private List<DetallePuestoDTO> detallesEmpleados = new ArrayList<>();
    private DetalleConfiguracionPresupuestoDTO detalleConfiguracion = new DetalleConfiguracionPresupuestoDTO();
    private DetallePuestoDTO detallePuesto;

    private boolean mostrarResumenCodigos;
    private boolean mostrarDetalleEmpleados;
    private boolean mostrarInventario;
    private boolean mostrarDetallePuesto;

    private Integer idTipoContratacion;

    public boolean isMostrarResumenCodigos() {
        return mostrarResumenCodigos;
    }

    public void setMostrarResumenCodigos(boolean mostrarResumenCodigos) {
        this.mostrarResumenCodigos = mostrarResumenCodigos;
    }

    public boolean isMostrarDetalleEmpleados() {
        return mostrarDetalleEmpleados;
    }

    public void setMostrarDetalleEmpleados(boolean mostrarDetalleEmpleados) {
        this.mostrarDetalleEmpleados = mostrarDetalleEmpleados;
    }

    public List<DetallePuestoDTO> getDetallesEmpleados() {
        return detallesEmpleados;
    }

    public void setDetallesEmpleados(List<DetallePuestoDTO> detallesEmpleados) {
        this.detallesEmpleados = detallesEmpleados;
    }

    public List<ResumenPuestoDTO> getResumenPuestos() {
        return resumenPuestos;
    }

    public void setResumenPuestos(List<ResumenPuestoDTO> resumenPuestos) {
        this.resumenPuestos = resumenPuestos;
    }

    public boolean isMostrarInventario() {
        return mostrarInventario;
    }

    public void setMostrarInventario(boolean mostrarInventario) {
        this.mostrarInventario = mostrarInventario;
    }

    public DetalleConfiguracionPresupuestoDTO getDetalleConfiguracion() {
        return detalleConfiguracion;
    }

    public void setDetalleConfiguracion(
            DetalleConfiguracionPresupuestoDTO detalleConfiguracion) {
        this.detalleConfiguracion = detalleConfiguracion;
    }

    public List<InfoPuestoDTO> getDetallesInventarios() {
        return detallesInventarios;
    }

    public void setDetallesInventarios(
            List<InfoPuestoDTO> detallesInventarios) {
        this.detallesInventarios = detallesInventarios;
    }

    public List<InventarioVacanteDTO> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<InventarioVacanteDTO> inventarios) {
        this.inventarios = inventarios;
    }

    public Integer getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Integer idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public DetallePuestoDTO getDetallePuesto() {
        return detallePuesto;
    }

    public void setDetallePuesto(DetallePuestoDTO detallePuesto) {
        this.detallePuesto = detallePuesto;
    }

    public boolean isMostrarDetallePuesto() {
        return mostrarDetallePuesto;
    }

    public void setMostrarDetallePuesto(boolean mostrarDetallePuesto) {
        this.mostrarDetallePuesto = mostrarDetallePuesto;
    }
}