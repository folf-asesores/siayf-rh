/*
 *
 */

package mx.gob.saludtlax.rh.empleados.movimientos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.movimientos.reportes.ComisionadoLicenciaExcelDTO;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 14/09/2016 15:53:54
 *
 */
public class ConsultaMovimientoService {
    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    protected List<InfoMovimientoDTO> consultarMovimientos(FiltroConsultaDTO dto) {
        List<InfoMovimientoDTO> movimientos = new ArrayList<>();
        if (dto.getTipoBusqueda() == EnumTipoConsultaMovimiento.MOVIMIENTOS) {
            movimientos = toListInfoMovimientoDTO(movimientoEmpleadoRepository.consultarMovimientosHijos(dto.getIdentificador()));

        } else if (dto.getTipoBusqueda() == EnumTipoConsultaMovimiento.MOVIMIENTO_ESPECIFICO) {
            movimientos = toListInfoMovimientoDTO(movimientoEmpleadoRepository.consultarMovimientos(dto.getIdentificador()));
        } else if (dto.getTipoBusqueda() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_FECHAS) {
            movimientos = toListInfoMovimientoDTO(movimientoEmpleadoRepository.consultarMovimientosFechas(dto.getFechaInicial(), dto.getFechaFinal()));
        } else if (dto.getTipoBusqueda() == EnumTipoConsultaMovimiento.MOVIMIENTO_POR_RFC_NOMBRE) {
            movimientos = toListInfoMovimientoDTO(movimientoEmpleadoRepository.consultarMovimientosRFC(dto.getCriterio()));
        }

        return movimientos;
    }

    public DetalleMovimientoDTO obtenerDetalleMovimiento(Integer idMovimiento) {

        if (!ValidacionUtil.esNumeroPositivo(idMovimiento)) {
            throw new ValidacionException("El identificador del movimiento es requerido.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        MovimientoEmpleadoEntity movimiento = movimientoEmpleadoRepository.obtenerPorId(idMovimiento);
        DetalleMovimientoDTO dto = toDetalleMovimiento(movimiento);
        return dto;
    }

    public DetalleMovimientoDTO toDetalleMovimiento(MovimientoEmpleadoEntity movimiento) {
        DetalleMovimientoDTO detalleMovimientoDTO = new DetalleMovimientoDTO();
        detalleMovimientoDTO.setEmpleado(movimiento.getEmpleado().getNombreCompleto());
        detalleMovimientoDTO.setFechaFin(movimiento.getFechaFinPermiso());
        detalleMovimientoDTO.setFechaInicio(movimiento.getFechaInicioPermiso());
        detalleMovimientoDTO.setFechaMovimiento(movimiento.getFechaIngreso());
        detalleMovimientoDTO.setMovimiento(movimiento.getMovimiento().getMovimiento());
        detalleMovimientoDTO.setNumeroOficio(movimiento.getNumeroOficio());
        detalleMovimientoDTO.setObservaciones(movimiento.getObservaciones());
        detalleMovimientoDTO.setUsuario(movimiento.getUsuarioEntity().nombreCompleto());
        detalleMovimientoDTO.setIdMovimiento(movimiento.getIdMovimientoEmpleado());
        detalleMovimientoDTO.setEstatus(movimiento.getEstatusMovimiento());
        return detalleMovimientoDTO;
    }

    private List<InfoMovimientoDTO> toListInfoMovimientoDTO(List<MovimientoEmpleadoEntity> movimientos) {
        List<InfoMovimientoDTO> listaMovimientos = new ArrayList<>();
        if (!movimientos.isEmpty()) {
            for (MovimientoEmpleadoEntity entity : movimientos) {
                InfoMovimientoDTO dto = new InfoMovimientoDTO();
                dto.setIdMovimiento(entity.getIdMovimientoEmpleado());
                dto.setEmpleado(entity.getEmpleado().getNombreCompleto());
                dto.setFechaFin(entity.getFechaFinPermiso());
                dto.setFechaInicio(entity.getFechaInicioPermiso());
                dto.setFechaMovimiento(entity.getFechaIngreso());
                dto.setMovimientoHijo(entity.getMovimiento().getMovimiento());
                dto.setUsuario(entity.getUsuarioEntity().nombreCompleto());
                listaMovimientos.add(dto);
            }
        }
        return listaMovimientos;

    }

    public List<ComisionadoLicenciaExcelDTO> listaConsultaComisionadoLicenciaPorRangoFecha(Date fechaInicial, Date fechaFinal) {

        return movimientoEmpleadoRepository.listaConsultaComisionadoLicenciaPorRangoFecha(fechaInicial, fechaFinal);
    }

}
