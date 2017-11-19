/*
 * Copyright ® 2016
 */

package mx.gob.saludtlax.rh.nomina.movimientofijo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.configuracion.conceptosnomina.ConceptoNominaFederalesDTO;
import mx.gob.saludtlax.rh.empleados.datolaboral.DetalleConfiguracionPresupuestoDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.ConceptoNominaFactory;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.ConfiguracionTipoMovimientoDTO;
import mx.gob.saludtlax.rh.nomina.movimientosnomina.TipoMovimientoNominaDTO;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionTipoMovimientoNominaReporsitory;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoFijoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoFijoRepository;
import mx.gob.saludtlax.rh.persistencia.TerceroInstitucionalRepository;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaEntity;
import mx.gob.saludtlax.rh.persistencia.TiposMovimientosNominaRepository;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.FechaUtil;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 25/05/2016 13:48:31
 */
@Stateless
public class MovimientoFijoEJB implements MovimientoFijoService, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1745520928411104695L;

    @Inject
    MovimientoFijoRepository movimientoFijoRepository;

    @Inject
    EmpleadoRepository emmpleado;

    @Inject
    TerceroInstitucionalRepository terceroInstitucionalRepository;

    @Inject
    private TiposMovimientosNominaRepository tipoMovimientoRepository;

    @Inject
    private ConfiguracionTipoMovimientoNominaReporsitory configuracionMovimientoRepository;
    @Inject
    TiposMovimientosNominaRepository tipomovimientosNominaRepository;

    @Override
    public void crear(MovimientoNominaDTO dto) {
        MovimientoFijoEntity newMovimiento = new MovimientoFijoEntity();
        newMovimiento.setEmpleado(emmpleado.obtenerPorId(dto.getIdEmpleado()));
        newMovimiento.setImporteDescontado(dto.getImporteDescontado());
        newMovimiento.setQuincenaFinal(dto.getQuincenaFinal());
        newMovimiento.setAnioFinal(dto.getAnioFinal());
        newMovimiento.setQuincenaInicial(dto.getQuincenaInicial());
        newMovimiento.setQuincenaOperacion(dto.getQuincenaOperacion());
        newMovimiento.setAnyoOperacion(FechaUtil.ejercicioActual());
        newMovimiento.setAnioInicial(dto.getAnioInicial());
        newMovimiento.setRfc(newMovimiento.getEmpleado().getRfc());
        newMovimiento.setTerceroInstitucional(
                dto.getIdTerceroInstitucional() != null ? terceroInstitucionalRepository.obtenerPorId(dto.getIdTerceroInstitucional()) : null);
        newMovimiento.setFechaRegistro(FechaUtil.fechaActual());
        newMovimiento.setFolioDocumento(dto.getFolio());
        newMovimiento.setFechaDocumento(dto.getFechaDocumento());
        newMovimiento.setDias(dto.getDias());
        newMovimiento.setFechaModificacion(FechaUtil.fechaActual());
        newMovimiento.setIdTipoMovimiento(dto.getIdTipoMovimiento());
        newMovimiento.setClave(dto.getClave());
        newMovimiento.setEstatus(dto.getEstatus());
        movimientoFijoRepository.crear(newMovimiento);

    }

    @Override
    public void editar(MovimientoNominaDTO dto) {
        try {

            MovimientoFijoEntity newMovimiento = movimientoFijoRepository.obtenerPorId(dto.getIdMovimientoFijo());
            newMovimiento.setEmpleado(emmpleado.obtenerPorId(dto.getIdEmpleado()));
            newMovimiento.setImporteDescontado(dto.getImporteDescontado());
            newMovimiento.setQuincenaFinal(dto.getQuincenaFinal());
            newMovimiento.setAnioFinal(dto.getAnioFinal());
            newMovimiento.setQuincenaInicial(dto.getQuincenaInicial());
            newMovimiento.setAnioInicial(dto.getAnioInicial());
            newMovimiento.setQuincenaOperacion(dto.getQuincenaOperacion());
            newMovimiento.setFolioDocumento(dto.getFolio());
            newMovimiento.setFechaDocumento(dto.getFechaDocumento());
            newMovimiento.setAnyoOperacion(FechaUtil.ejercicioActual());
            newMovimiento.setIdTipoMovimiento(dto.getIdTipoMovimiento());
            newMovimiento.setDias(dto.getDias());
            newMovimiento.setFechaModificacion(FechaUtil.fechaActual());
            newMovimiento.setTerceroInstitucional(
                    dto.getIdTerceroInstitucional() != null ? terceroInstitucionalRepository.obtenerPorId(dto.getIdTerceroInstitucional()) : null);
            newMovimiento.setIdTipoMovimiento(dto.getIdTipoMovimiento());
            newMovimiento.setClave(dto.getClave());
            newMovimiento.setEstatus(dto.getEstatus());
            movimientoFijoRepository.actualizar(newMovimiento);
        } catch (PersistenceException e) {
            throw new BusinessException();
        }
    }

    @Override
    public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(Integer idEmpleado) {
        try {
            return movimientoFijoRepository.obtenerMovimientosFijosPorEmpleado(idEmpleado);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<MovimientoNominaDTO> obtenerMovimientos() {
        return movimientoFijoRepository.obtenerMovimientosFijos();
    }

    @Override
    public void eliminar(MovimientoNominaDTO dto) {
        movimientoFijoRepository.eliminar(movimientoFijoRepository.obtenerPorId(dto.getIdMovimientoFijo()));
    }

    @Override
    public Integer numeroQuincena(Integer tipoPeriodo, Integer ejercicioFiscal, Date fechaActual) {
        return movimientoFijoRepository.periodoActual(tipoPeriodo, ejercicioFiscal, fechaActual);
    }

    @Override
    public MovimientoNominaDTO obtenerMovimientoPorDatosArchivo(MovimientoNominaDTO archivoDto) {
        return movimientoFijoRepository.obtenerMovimientoPorDatosArchivo(archivoDto);
    }

    public List<DetalleConfiguracionPresupuestoDTO> detallesConfiguracionPresupuestalPorEmpleado(Integer idEmpleado) {
        return null;
    }

    @Override
    public List<MovimientoNominaDTO> obtenerMovimientosPorEmpleado(Integer idempleadoSeleccionado, String movimientoSeleccionado) {
        TipoMovimientoNominaDTO movDto = tipoMovimientoRepository.obtenerMovimientoPorClave(movimientoSeleccionado);

        return movimientoFijoRepository.obtenerMovimientosFijosPorEmpleadoytipoMov(idempleadoSeleccionado, movDto);
    }

    @Override
    public ConfiguracionTipoMovimientoDTO obtenerConfiguracionesPorTipoMovimiento(Integer idTipoMovimiento) {
        List<ConceptoNominaFederalesDTO> listaConcoptos = new ArrayList<>();
        List<ConfiguracionTipoMovimientoEntity> listConfMov = new ArrayList<>();
        TiposMovimientosNominaEntity tipoMovimientoEntity = tipomovimientosNominaRepository.obtenerPorId(idTipoMovimiento);
        listConfMov = configuracionMovimientoRepository.obenerConceptosPorTipoMovimiento(idTipoMovimiento);
        if (listaConcoptos != null) {
            for (ConfiguracionTipoMovimientoEntity c : listConfMov) {
                ConceptoNominaFederalesDTO concepto = new ConceptoNominaFederalesDTO();
                concepto = ConceptoNominaFactory.crearConceptoNominaFederalesDTO(c.getConceptoNomina(), concepto);
                listaConcoptos.add(concepto);
            }
        }

        ConfiguracionTipoMovimientoDTO configuracion = new ConfiguracionTipoMovimientoDTO();
        TipoMovimientoNominaDTO tipoMovDto = new TipoMovimientoNominaDTO();
        tipoMovDto.setClave(tipoMovimientoEntity.getClave());
        tipoMovDto.setDescripcion(tipoMovimientoEntity.getDescripcion());
        tipoMovDto.setIdTimpoMovimiento(tipoMovimientoEntity.getIdMovimientoNomina());

        configuracion.setIdConfiguracion(idTipoMovimiento);
        configuracion.setTipoMovimiento(tipoMovDto);
        configuracion.setListConceptoNomina(listaConcoptos);

        return configuracion;

    }
}
