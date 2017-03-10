/**
 * 
 */
package mx.gob.saludtlax.rh.autorizaciones;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.empleados.movimientos.DetalleMovimientoDTO;
import mx.gob.saludtlax.rh.empleados.suplencia.DetalleSuplenciaDTO;
import mx.gob.saludtlax.rh.puestosautorizados.programas.ConfiguracionDetalleProgramaDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 16/08/2016 19:01:52
 * 
 */
public class DetalleAutorizacionDTO {
	private String notificacion;
	private DetalleConfiguracionPresupuestoDTO configuracionPresupuesto;
	private ConfiguracionDetalleProgramaDTO configuracionDetallePrograma;
	private DetalleMovimientoDTO detalleMovimiento;
	private DetalleSuplenciaDTO detalleSuplencia;
	private BitacoraEmpleadoDTO bitacora;

	public BitacoraEmpleadoDTO getBitacora() {
		return bitacora;
	}

	public void setBitacora(BitacoraEmpleadoDTO bitacora) {
		this.bitacora = bitacora;
	}

	public DetalleSuplenciaDTO getDetalleSuplencia() {
		return detalleSuplencia;
	}

	public void setDetalleSuplencia(DetalleSuplenciaDTO detalleSuplencia) {
		this.detalleSuplencia = detalleSuplencia;
	}

	public DetalleMovimientoDTO getDetalleMovimiento() {
		return detalleMovimiento;
	}

	public void setDetalleMovimiento(DetalleMovimientoDTO detalleMovimiento) {
		this.detalleMovimiento = detalleMovimiento;
	}

	public ConfiguracionDetalleProgramaDTO getConfiguracionDetallePrograma() {
		return configuracionDetallePrograma;
	}

	public void setConfiguracionDetallePrograma(ConfiguracionDetalleProgramaDTO configuracionDetallePrograma) {
		this.configuracionDetallePrograma = configuracionDetallePrograma;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}

	public DetalleConfiguracionPresupuestoDTO getConfiguracionPresupuesto() {
		return configuracionPresupuesto;
	}

	public void setConfiguracionPresupuesto(DetalleConfiguracionPresupuestoDTO configuracionPresupuesto) {
		this.configuracionPresupuesto = configuracionPresupuesto;
	}

}
