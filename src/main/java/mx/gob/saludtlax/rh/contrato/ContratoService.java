/*
 *
 */

package mx.gob.saludtlax.rh.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.EnumTipoContratoEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ContratoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ContratoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class ContratoService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7341161588822311510L;

    @Inject
    private ContratoEmpleadoRepository contratoEmpleadoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;

    protected ContratoDTO obtenerContratoEmpleadoPorIdContrato(
            Integer idContrato) {

        String contexto = "obtenerContratoEmpleadoPorIdContrato:";

        if (idContrato == null) {
            throw new ValidacionException(
                    contexto + "El identificador del contrato es requerido",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        ContratoEmpleadoEntity contratoEmpleadoEntity = contratoEmpleadoRepository
                .obtenerPorId(idContrato);

        if (contratoEmpleadoEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "El contrato del empleado no se encuentra registrado",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        EmpleadoEntity empleadoEntity = empleadoRepository
                .obtenerPorId(contratoEmpleadoEntity.getIdEmpleado());

        if (empleadoEntity == null) {
            throw new ReglaNegocioException(
                    contexto + "El empleado no se encuentra registrado",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
                .obtenerPorId(contratoEmpleadoEntity.getIdVacante());

        if (inventarioVacanteEntity == null) {
            throw new ReglaNegocioException(contexto
                    + "El inventario vacante no se encuentra registrado",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        ContratoDTO dto = new ContratoDTO();

        dto.setIdContrato(contratoEmpleadoEntity.getIdContrato());
        dto.setTipoContrato(contratoEmpleadoEntity.getTipoContrato());
        dto.setFechaInicio(contratoEmpleadoEntity.getFechaInicio());
        dto.setFechaFin(contratoEmpleadoEntity.getFechaFin());
        dto.setNumeroContrato(contratoEmpleadoEntity.getNumeroContrato());
        dto.setIdEmpleado(empleadoEntity.getIdEmpleado() == null ? 0
                : empleadoEntity.getIdEmpleado());
        dto.setNombreCompletoEmpleado(empleadoEntity.getNombreCompleto() == null
                ? "" : empleadoEntity.getNombreCompleto());
        dto.setIdVacante(inventarioVacanteEntity.getIdVacante() == null ? 0
                : inventarioVacanteEntity.getIdVacante());
        dto.setNombrePuestoGeneral(
                inventarioVacanteEntity.getConfiguracion().getPuesto() == null
                        ? "" : inventarioVacanteEntity.getConfiguracion()
                                .getPuesto().getPuesto());
        dto.setActivo(contratoEmpleadoEntity.isActivo());
        dto.setSueldoMensual(contratoEmpleadoEntity.getSueldoMensual());
        dto.setDescripcionSueldo(contratoEmpleadoEntity.getDescripcionSueldo());
        dto.setDomicilioServicio(
                inventarioVacanteEntity.getAdscripcion() == null ? ""
                        : inventarioVacanteEntity.getAdscripcion()
                                .getDomicilioServicio());

        return dto;
    }

    protected List<ContratoDTO> listaContrato() {

        String contexto = "listaContrato:";

        List<ContratoDTO> listaContratoDTO = new ArrayList<>();

        List<ContratoEmpleadoEntity> listaContratoEmpleadoEntities = contratoEmpleadoRepository
                .obtenerlistaContrato();

        if (!listaContratoEmpleadoEntities.isEmpty()) {
            for (ContratoEmpleadoEntity contratoEmpleadoEntity : listaContratoEmpleadoEntities) {
                ContratoDTO dto = new ContratoDTO();

                dto.setIdContrato(contratoEmpleadoEntity.getIdContrato());

                EmpleadoEntity empleadoEntity = empleadoRepository
                        .obtenerPorId(contratoEmpleadoEntity.getIdEmpleado());

                if (empleadoEntity == null) {
                    throw new ReglaNegocioException(
                            contexto + "El empleado no se encuentra registrado",
                            ReglaNegocioCodigoError.SIN_REGISTRO);
                }

                InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
                        .obtenerPorId(contratoEmpleadoEntity.getIdVacante());

                if (inventarioVacanteEntity == null) {
                    throw new ReglaNegocioException(contexto
                            + "El inventario vacante no se encuentra registrado",
                            ReglaNegocioCodigoError.SIN_REGISTRO);
                }

                dto.setIdContrato(contratoEmpleadoEntity.getIdContrato());
                dto.setTipoContrato(contratoEmpleadoEntity.getTipoContrato());

                if (contratoEmpleadoEntity
                        .getTipoContrato() == EnumTipoContratoEmpleado.ESTATAL) {
                    dto.setNombreTipoContrato("CONTRATO ESTATAL");
                }

                if (contratoEmpleadoEntity
                        .getTipoContrato() == EnumTipoContratoEmpleado.FEDERAL) {
                    dto.setNombreTipoContrato("CONTRATO FEDERAL");
                }

                dto.setFechaInicio(contratoEmpleadoEntity.getFechaInicio());
                dto.setFechaFin(contratoEmpleadoEntity.getFechaFin());

                dto.setNombreCompletoEmpleado(
                        empleadoEntity.getNombreCompleto() == null ? ""
                                : empleadoEntity.getNombreCompleto());
                dto.setNumeroContrato(
                        contratoEmpleadoEntity.getNumeroContrato());
                dto.setImpreso(contratoEmpleadoEntity.isImpreso());

                listaContratoDTO.add(dto);
            }
        }

        return listaContratoDTO;
    }

    protected List<ContratoDTO> obtenerListaContratoPorTipo(
            Integer tipoContrato) {

        String contexto = "listaContrato:";

        if (tipoContrato == null | tipoContrato == 0) {
            throw new ValidacionException(
                    contexto + "El tipo contrato es requerido",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        List<ContratoDTO> listaContratoDTO = new ArrayList<>();

        List<ContratoEmpleadoEntity> listaContratoEmpleadoEntities = contratoEmpleadoRepository
                .obtenerlistaContratoPorTipo(tipoContrato);

        if (!listaContratoEmpleadoEntities.isEmpty()) {
            for (ContratoEmpleadoEntity contratoEmpleadoEntity : listaContratoEmpleadoEntities) {
                ContratoDTO dto = new ContratoDTO();

                dto.setIdContrato(contratoEmpleadoEntity.getIdContrato());

                EmpleadoEntity empleadoEntity = empleadoRepository
                        .obtenerPorId(contratoEmpleadoEntity.getIdEmpleado());

                if (empleadoEntity == null) {
                    throw new ReglaNegocioException(
                            contexto + "El empleado no se encuentra registrado",
                            ReglaNegocioCodigoError.SIN_REGISTRO);
                }

                InventarioVacanteEntity inventarioVacanteEntity = inventarioVacanteRepository
                        .obtenerPorId(contratoEmpleadoEntity.getIdVacante());

                if (inventarioVacanteEntity == null) {
                    throw new ReglaNegocioException(contexto
                            + "El inventario vacante no se encuentra registrado",
                            ReglaNegocioCodigoError.SIN_REGISTRO);
                }

                dto.setIdContrato(contratoEmpleadoEntity.getIdContrato());
                dto.setTipoContrato(contratoEmpleadoEntity.getTipoContrato());

                if (contratoEmpleadoEntity
                        .getTipoContrato() == EnumTipoContratoEmpleado.ESTATAL) {
                    dto.setNombreTipoContrato("CONTRATO ESTATAL");
                }

                if (contratoEmpleadoEntity
                        .getTipoContrato() == EnumTipoContratoEmpleado.FEDERAL) {
                    dto.setNombreTipoContrato("CONTRATO FEDERAL");
                }

                dto.setFechaInicio(contratoEmpleadoEntity.getFechaInicio());
                dto.setFechaFin(contratoEmpleadoEntity.getFechaFin());

                dto.setNombreCompletoEmpleado(
                        empleadoEntity.getNombreCompleto() == null ? ""
                                : empleadoEntity.getNombreCompleto());

                listaContratoDTO.add(dto);
            }
        }

        return listaContratoDTO;
    }

    protected void actualizarContratoPorImpresion(Integer idContrato,
            String numeroContrato) {

        String contexto = "actualizarContratoPorImpresion:";

        if (idContrato == null) {
            throw new ValidacionException(
                    contexto + "El identificador del contrato es requerido",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (ValidacionUtil.esCadenaVacia(numeroContrato)) {
            throw new ValidacionException(
                    contexto + "El numero del contrato es requerido",
                    ValidacionCodigoError.VALOR_REQUERIDO);
        }

        ContratoEmpleadoEntity contratoEmpleadoEntity = contratoEmpleadoRepository
                .obtenerPorId(idContrato);

        if (contratoEmpleadoEntity == null) {
            throw new ReglaNegocioException(
                    contexto + "El contrato no se encuentra registrado",
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        contratoEmpleadoEntity.setNumeroContrato(numeroContrato);
        contratoEmpleadoEntity.setFechaImpreso(FechaUtil.fechaActual());
        boolean yaImpreso = true;
        contratoEmpleadoEntity.setImpreso(yaImpreso);

        contratoEmpleadoRepository.actualizar(contratoEmpleadoEntity);

    }

}
