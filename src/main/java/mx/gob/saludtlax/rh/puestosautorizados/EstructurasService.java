/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.BitacoraEmpleadoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.BitacoraModificacionService;
import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoModificacionEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.persistencia.EstructuraEntity;
import mx.gob.saludtlax.rh.persistencia.EstructuraRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 22/02/2017 00:18:04
 */
public class EstructurasService {
    @Inject
    private BitacoraModificacionService bitacoraModificacionService;
    @Inject
    private EstructuraRepository estructuraRepository;
    @Inject
    private InventarioVacanteRepository puestoAutorizadoRepository;

    protected void actualizarEstructuraContrato(
            EstructuraContratoDTO estructuraContrato) {

        InventarioVacanteEntity puesto = puestoAutorizadoRepository
                .obtenerPorId(estructuraContrato.getIdPuesto());
        if (puesto == null) {
            throw new SistemaException(
                    "No existe el puesto con identificador "
                            + estructuraContrato.getIdPuesto(),
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (puesto.getTipoContratacion()
                .getId() != EnumTipoContratacion.CONTRATO_ESTATAL) {
            throw new ReglaNegocioException(
                    "La estructura solo está permitida para contrato estatal.",
                    ReglaNegocioCodigoError.TIPO_CONTRATACION_NO_PERMITIDA);
        }

        String lccActual = puesto.lccEstructuraContrato();
        puesto.setFuncionEspecifica(estructuraContrato.getFuncion());
        puesto.setSubfuncion(estructuraContrato.getSubfuncion());
        puesto.setFinanciamiento(estructuraContrato.getFinanciamiento());
        puesto.setJornada(estructuraContrato.getJornada());
        puestoAutorizadoRepository.actualizar(puesto);
        String lccNueva = puesto.lccEstructuraContrato();

        if (!lccActual.equals(lccNueva)) {
            BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
            bitacora.setComentarios("");
            bitacora.setEmpleado(puesto.getEmpleadoActivo().getIdEmpleado());
            bitacora.setIdUsuario(estructuraContrato.getIdUsuario());
            bitacora.setLccActual(lccNueva);
            bitacora.setLccAnterior(lccActual);
            bitacora.setTipoMovimientoEmpleado(
                    EnumTipoModificacionEmpleado.ACTUALIZACION_ESTRUCTURA_NOMINA);
            bitacoraModificacionService.registrarBitacora(bitacora);
        }
    }

    protected void actualizarEstructuraNomina(EstructuraNominaDTO dto) {
        EstructuraEntity estructuraNomina = estructuraRepository
                .obtenerEstructuraPorIdPuesto(dto.getIdPuesto());
        InventarioVacanteEntity puesto = puestoAutorizadoRepository
                .obtenerPorId(dto.getIdPuesto());
        if (puesto == null) {
            throw new SistemaException(
                    "El puesto con identificador " + dto.getIdPuesto()
                            + " no existe.",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (!puesto.getEstatus().isPuestoActivo()) {
            throw new ReglaNegocioException(
                    "No se puede modificar la estructura de un puesto que no esté activo.",
                    ReglaNegocioCodigoError.ESTATUS_INCORRECTO);
        }

        BitacoraEmpleadoDTO bitacora = new BitacoraEmpleadoDTO();
        if (estructuraNomina == null) {
            EstructuraEntity nuevaEstructura = new EstructuraEntity();
            nuevaEstructura.setFinanciamientoFederal(dto.getFinanciamiento());
            nuevaEstructura.setIdPuesto(dto.getIdPuesto());
            nuevaEstructura.setIndicadorMando(dto.getIndicadorMando());
            nuevaEstructura.setJornadaTrabajo(dto.getJornadaTrabajo());
            nuevaEstructura.setPagaduria(dto.getPagaduria());
            nuevaEstructura.setSubfuncion(dto.getSubfuncion());
            nuevaEstructura.setTabuladorPuesto(dto.getTabuladorPuesto());
            nuevaEstructura.setTipoPago(dto.getTipoPago());
            nuevaEstructura.setTipoUnidad(dto.getTipoUnidad());
            estructuraRepository.crear(nuevaEstructura);

            bitacora.setComentarios(
                    "Alta configuración de la estructura de nómina.");
            bitacora.setEmpleado(puesto.getEmpleadoActivo().getIdEmpleado());
            bitacora.setIdUsuario(dto.getIdUsuario());
            bitacora.setLccActual(nuevaEstructura.lccEstructuraNomina());
            bitacora.setLccAnterior("");
            bitacora.setTipoMovimientoEmpleado(
                    EnumTipoModificacionEmpleado.ACTUALIZACION_ESTRUCTURA_NOMINA);
            bitacoraModificacionService.registrarBitacora(bitacora);
        } else {

            String lccActual = estructuraNomina.lccEstructuraNomina();
            estructuraNomina.setFinanciamientoFederal(dto.getFinanciamiento());
            estructuraNomina.setIndicadorMando(dto.getIndicadorMando());
            estructuraNomina.setJornadaTrabajo(dto.getJornadaTrabajo());
            estructuraNomina.setPagaduria(dto.getPagaduria());
            estructuraNomina.setSubfuncion(dto.getSubfuncion());
            estructuraNomina.setTabuladorPuesto(dto.getTabuladorPuesto());
            estructuraNomina.setTipoPago(dto.getTipoPago());
            estructuraNomina.setTipoUnidad(dto.getTipoUnidad());
            estructuraRepository.actualizar(estructuraNomina);

            String lccNueva = puesto.lccEstructuraContrato();

            if (!lccActual.equals(lccNueva)) {
                bitacora.setComentarios("");
                bitacora.setEmpleado(
                        puesto.getEmpleadoActivo().getIdEmpleado());
                bitacora.setIdUsuario(dto.getIdUsuario());
                bitacora.setLccActual(lccNueva);
                bitacora.setLccAnterior(lccActual);
                bitacora.setTipoMovimientoEmpleado(
                        EnumTipoModificacionEmpleado.ACTUALIZACION_ESTRUCTURA_NOMINA);
                bitacoraModificacionService.registrarBitacora(bitacora);
            }
        }

    }

    protected EstructuraNominaDTO obtenerEstructuraNominaPuesto(
            Integer idPuesto) {

        EstructuraEntity estructuraNomina = estructuraRepository
                .obtenerEstructuraPorIdPuesto(idPuesto);

        InventarioVacanteEntity puesto = puestoAutorizadoRepository
                .obtenerPorId(idPuesto);
        if (puesto == null) {
            throw new SistemaException(
                    "El puesto con identificador " + idPuesto + " no existe.",
                    SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (puesto.getTipoContratacion().getAreaResponsable() != 1) {
            throw new ReglaNegocioException(
                    "Para obtener la estructura nómina el puesto debe ser federal.",
                    ReglaNegocioCodigoError.MOVIMIENTO_NO_AUTORIZADO);
        }

        EstructuraNominaDTO estructura = new EstructuraNominaDTO();
        if (estructuraNomina != null) {
            estructura.setFinanciamiento(
                    estructuraNomina.getFinanciamientoFederal());
            estructura.setIndicadorMando(estructuraNomina.getIndicadorMando());
            estructura.setJornadaTrabajo(estructuraNomina.getJornadaTrabajo());
            estructura.setPagaduria(estructuraNomina.getPagaduria());
            estructura.setSubfuncion(estructuraNomina.getSubfuncion());
            estructura
                    .setTabuladorPuesto(estructuraNomina.getTabuladorPuesto());
            estructura.setTipoPago(estructuraNomina.getTipoPago());
            estructura.setTipoUnidad(estructuraNomina.getTipoUnidad());
        }

        return estructura;

    }

}
