
package mx.gob.saludtlax.rh.nomina.movimientoscontrato;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos.ConceptoNominaContratosDTO;
import mx.gob.saludtlax.rh.configuracion.conceptosnominacontratos.ConceptoNominaContratosService;
import mx.gob.saludtlax.rh.empleados.administracion.Empleado;
import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaEmpleadoDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.NominaEmpleadoService;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductoNominaDTO;
import mx.gob.saludtlax.rh.nomina.productosnomina.ProductosNominaService;
import mx.gob.saludtlax.rh.persistencia.MovimientosNominaContratosEntity;

/**
 *
 * @author José Pablo
 *
 */
@Stateless
public class MovimientosContratosEJB {
    @Inject
    private MovimientosContratosService service;
    @Inject
    private ProductosNominaService productosNominaService;
    @Inject
    private NominaEmpleadoService nominaEmpleadoService;
    @Inject
    private ConceptoNominaContratosService conceptoNominaContratosService;
    @Inject
    private Empleado empleadoEJB;

    public EmpleadoDetalladoDTO obtenerEmpleadoDatos(Integer idEmpleadoSeleccionado) {
        return empleadoEJB.obtenerInformacionEmpleado(idEmpleadoSeleccionado);
    }

    public boolean esTipoDeContratos(Integer idEmpleado) {
        return service.esTipoDeContratos(idEmpleado);
    }

    public List<MovimientoContratosDTO> obtenerMovimientosNominaEmpleadoLista(Integer idEmpleadoSeleccionado) {
        return service.obtenerMovimientosNominaEmpleadoLista(idEmpleadoSeleccionado);
    }

    public Integer crear(MovimientoContratosDTO dto) {
        MovimientosNominaContratosEntity entitty = service.crear(dto);
        return entitty.getIdMovimiento();
    }

    public List<ConceptoNominaContratosDTO> obtenerConceptosLista(boolean aplicaMovimiento) {
        return conceptoNominaContratosService.obtenerConceptosLista(aplicaMovimiento);
    }

    public boolean esMovimientoFijo(Integer idConceptoContratos) {
        return conceptoNominaContratosService.esMovimientoFijo(idConceptoContratos);
    }

    public void guardarDetalle(DetalleMovimientoContratoDTO dto) {
        service.guardarDetalle(dto);
    }

    public List<ProductoNominaDTO> obtenerProductoNominaLista(Integer idEmpleado) {
        List<NominaEmpleadoDTO> nominaEmpleadoLista = nominaEmpleadoService.obntenerNominasActivaPorEmpleado(idEmpleado);
        return productosNominaService.obtenerProductoNominaLista(nominaEmpleadoLista);
    }

    public BigDecimal calcularDescuentoFaltas(MovimientoContratosDTO movimientoContratos) {
        BigDecimal monto = nominaEmpleadoService.calcularDescuentoFaltas(movimientoContratos);
        return monto;
    }

    public void eliminarMovimientoContrato(MovimientoContratosDTO movimientoContratos) {
        service.eliminarMovimiento(movimientoContratos);
    }

    public void actualizarMovimientoContratos(MovimientoContratosDTO movimientoContratos) {
        service.actualizarMovimientoContratos(movimientoContratos);
    }

    public MovimientoContratosDTO obtenerMovimientoContrato(MovimientoContratosDTO movimientoContratos) {
        return service.obtenerMovimientoContrato(movimientoContratos);
    }
}