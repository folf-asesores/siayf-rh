/**
 * 
 */

package mx.gob.saludtlax.rh.empleados.administracion;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EnumEstatusAspirante;
import mx.gob.saludtlax.rh.empleados.datolaboral.EnumEstatusConfiguracion;
import mx.gob.saludtlax.rh.empleados.movimientos.EnumTipoMovimiento;
import mx.gob.saludtlax.rh.empleados.movimientos.MovimientosEmpleados;
import mx.gob.saludtlax.rh.empleados.movimientos.RegistroMovimientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.NombramientoService;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.SistemaCodigoError;
import mx.gob.saludtlax.rh.excepciones.SistemaException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.AspiranteEntity;
import mx.gob.saludtlax.rh.persistencia.AspiranteRepository;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.CandidatoVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.ContratoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.ContratoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralEntity;
import mx.gob.saludtlax.rh.persistencia.ExperienciaLaboralRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoEntity;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.puestosautorizados.EnumEstatusPuesto;
import mx.gob.saludtlax.rh.puestosautorizados.EnumTipoContratacion;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;
import mx.gob.saludtlax.rh.vacantes.seleccion.EnumTipoCandidato;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 14:52:56
 */
public class AltaEmpleadoService {
    @Inject
    private AspiranteRepository aspiranteRepository;
    @Inject
    private CandidatoVacanteRepository canditatoVacanteRepository;

    @Inject
    private ConfiguracionPresupuestoRepository configuracionPresupuestalRepository;
    @Inject
    private ContratoEmpleadoRepository contratoEmpleadoRepository;
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private EstatusPuestosRepository estatusPuestoRepository;
    @Inject
    private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
    @Inject
    private ExperienciaLaboralRepository experienciaLaboralRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;

    @Inject
    private NombramientoService nombramientoService;
    @Inject
    private MovimientosEmpleados movimientosEmpleados;

    @Inject
    private TipoEmpleadoRepository tipoEmpleadoRepository;
    @Inject
    private TipoMovimientoEmpleadoRepository tipoMovimientoEmpleadoRepository;

    public void altaEmpleado(AltaEmpleadoDTO dto) {

        InventarioVacanteEntity vacante = this.inventarioVacanteRepository.obtenerPorId(dto.getIdVacante());
        if (dto.getFechaInicioLabores() == null) {
            throw new ValidacionException("La fecha de inicio de labores es requerida", ValidacionCodigoError.VALOR_REQUERIDO);
        }

        if (vacante == null) {
            throw new ValidacionException("No se encontró registro de la vacante con identificador " + dto.getIdVacante(),
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        if (vacante.getDisponible().equals("NO")) {
            throw new ReglaNegocioException("La vacante no se encuentra disponible", ReglaNegocioCodigoError.VACANTE_NO_DISPONIBLE);
        }

        if (vacante.getIdCandidatoPostulado() == null) {
            throw new ReglaNegocioException("La vacante no tiene asignada un candidato", ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        if (this.configuracionPresupuestalRepository.numeroLaboralEnUso(dto.getNumeroLaboral())) {
            throw new ReglaNegocioException("El número laboral se encuentra en uso, ingrese uno nuevo.", ReglaNegocioCodigoError.NUMERO_EMPLEADO);
        }

        Integer tipoContratacion = vacante.getTipoContratacion().getId();
        CandidatoVacanteEntity candidato = this.canditatoVacanteRepository.obtenerPorId(vacante.getIdCandidatoPostulado());
        EmpleadoEntity empleado = null;

        if (candidato == null) {
            throw new ReglaNegocioException("No se encontro el candidato con identificador " + vacante.getIdCandidatoPostulado(),
                    ReglaNegocioCodigoError.SIN_REGISTRO);
        }

        // Registrar o reingresar al empleado.
        if (candidato.getTipoCandidato() == EnumTipoCandidato.ASPIRANTE) {
            empleado = registrarAspirante(vacante, candidato, dto);
        } else if (candidato.getTipoCandidato() == EnumTipoCandidato.EMPLEADO) {

            empleado = reingresarEmpleado(candidato, tipoContratacion, dto.getIdUsuario(), dto.getIdBanco(), dto.getNumeroCuenta(), dto.getIdMetodoPago());
        }

        if (tipoContratacion == EnumTipoContratacion.BASE || tipoContratacion == EnumTipoContratacion.CONFIANZA
                || tipoContratacion == EnumTipoContratacion.FORMALIZADOS || tipoContratacion == EnumTipoContratacion.REGULARIZADOS
                || tipoContratacion == EnumTipoContratacion.HOMOLOGADOS) {

            Integer idClasificacionNombramiento = 0;
            if (tipoContratacion == EnumTipoContratacion.BASE || tipoContratacion == EnumTipoContratacion.FORMALIZADOS
                    || tipoContratacion == EnumTipoContratacion.REGULARIZADOS) {
                idClasificacionNombramiento = EnumClasificacionNombramiento.PROVISIONAL;
                vacante.setProvisional(true);
            } else if (tipoContratacion == EnumTipoContratacion.HOMOLOGADOS) {
                idClasificacionNombramiento = EnumClasificacionNombramiento.BASE;
            } else if (tipoContratacion == EnumTipoContratacion.CONFIANZA) {
                idClasificacionNombramiento = EnumClasificacionNombramiento.CONFIANZA;
            }

            dto.getNombramiento().setEmpleado(empleado);
            dto.getNombramiento().setSueldo(vacante.getConfiguracion().getSueldo());
            dto.getNombramiento().setIdClasificacionNombramiento(idClasificacionNombramiento);

            this.nombramientoService.crearNombramiento(dto.getNombramiento(), vacante.getConfiguracion().getNombramiento().getIdTipoNombramiento(),
                    dto.getIdTipoJornada());

        } else if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL || tipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {
            ContratoEmpleadoEntity nuevoContrato = new ContratoEmpleadoEntity();
            nuevoContrato.setActivo(true);
            nuevoContrato.setDescripcionSueldo("");
            nuevoContrato.setFechaFin(dto.getFechaFin());
            nuevoContrato.setFechaInicio(dto.getFechaInicio());
            nuevoContrato.setIdEmpleado(empleado.getIdEmpleado());
            nuevoContrato.setIdVacante(vacante.getIdVacante());
            nuevoContrato.setSueldoMensual(vacante.getConfiguracion().getSueldo());

            if (tipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {
                ProgramaEntity programa = vacante.getPrograma();
                if (programa == null) {
                    throw new ValidacionException("Es requerido asignarle  a la vacante de contrato federal un programa.",
                            ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
                }
                nuevoContrato.setTipoContrato(EnumTipoContratoEmpleado.FEDERAL);
                nuevoContrato.setPrograma(programa);
            } else {
                nuevoContrato.setTipoContrato(EnumTipoContratoEmpleado.ESTATAL);
            }
            this.contratoEmpleadoRepository.crear(nuevoContrato);

        }
        EstatusPuestosEntity estatusPuesto = this.estatusPuestoRepository.obtenerPorId(EnumEstatusPuesto.EMPLEADO_ACTIVO);
        vacante.setDisponible("NO");
        vacante.setIdCandidatoPostulado(null);
        vacante.setFechaInicio(dto.getFechaInicio());
        vacante.setFechaFin(dto.getFechaFin());
        vacante.setEmpleadoActivo(empleado);
        vacante.setEstatus(estatusPuesto);

        this.inventarioVacanteRepository.actualizar(vacante);

        ConfiguracionPresupuestoEntity configuracionPresupuestal = vacante.getConfiguracion();

        EstatusConfiguracionesEntity estatus = this.estatusConfiguracionesRepository.obtenerPorId(EnumEstatusConfiguracion.ACTIVO);

        configuracionPresupuestal.setEmpleado(empleado);
        configuracionPresupuestal.setNumeroEmpleado(dto.getNumeroLaboral());
        configuracionPresupuestal.setEstatus(estatus);
        configuracionPresupuestal.setFechaInicioLabores(dto.getFechaInicioLabores());
        configuracionPresupuestal.setIdJornada(dto.getIdTipoJornada());
        configuracionPresupuestal.setTipoPago(dto.getTipoPago());
        this.configuracionPresupuestalRepository.actualizar(configuracionPresupuestal);

    }

    private EmpleadoEntity reingresarEmpleado(CandidatoVacanteEntity candidato, Integer tipoContratacion, Integer idUsuario, Integer idBanco,
            String numeroCuenta, Integer idMetodoPago) {
        EmpleadoEntity empleado = this.empleadoRepository.obtenerPorId(candidato.getIdContexto());
        if (empleado == null) {
            throw new ValidacionException("No se encontró al empleado con identificador " + candidato.getIdContexto(),
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }
        /**
         * if
         * (inventarioVacanteRepository.tieneEmpleadoPuestoAsignado(candidato.
         * getIdContexto())) { throw new ReglaNegocioException( "El empleado se
         * encuentra activo con otro puesto es requerido se de de baja, antes de
         * finalizar el reingreso.",
         * ReglaNegocioCodigoError.EMPLEADO_CON_PUESTO_ACTIVO); }
         **/
        empleado.setFechaIngreso(FechaUtil.fechaActual());
        empleado.setIdBanco(idBanco);
        empleado.setNumeroCuenta(numeroCuenta);
        empleado.setIdMetodoPago(idMetodoPago);
        empleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
        this.empleadoRepository.actualizar(empleado);

        // Registrar movimiento personal
        RegistroMovimientoDTO movimientoReingreso = new RegistroMovimientoDTO();
        Integer idTipoBaja = empleado.getTipoBaja();

        // No se tiene historico de los movimientos por lo cual para definir que
        // tipo de reingreso aplica solo podrá realizarse para los empleados que
        // tengan definido el tipo de baja.

        if (idTipoBaja != null) {
            TipoMovimientoEmpleadoEntity baja = this.tipoMovimientoEmpleadoRepository.obtenerPorId(idTipoBaja);

            if (tipoContratacion == EnumTipoContratacion.BASE || tipoContratacion == EnumTipoContratacion.FORMALIZADOS
                    || tipoContratacion == EnumTipoContratacion.REGULARIZADOS || tipoContratacion == EnumTipoContratacion.HOMOLOGADOS) {
                if (baja.isConAntecedente()) {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_BASE);
                } else {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_BASE_SIN_ANTECEDENTES);
                }
            } else if (tipoContratacion == EnumTipoContratacion.CONFIANZA) {
                if (baja.isConAntecedente()) {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_CONFIANZA);
                } else {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_CONFIANZA_SIN_ANTECEDENTES);
                }
            } else if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL || tipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL) {

                if (baja.isConAntecedente()) {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_EVENTUAL);
                } else {
                    movimientoReingreso.setIdMovimiento(EnumTipoMovimiento.REINGRESO_EVENTUAL_SIN_ANTECEDENTES);
                }
            }

        }

        // TODO quitar condición, solo está mientras definen el tipo de
        // movimiento por alta
        if (!ValidacionUtil.esNumeroPositivo(movimientoReingreso.getIdMovimiento())) {
            movimientoReingreso.setObservaciones("ALTA EMPLEADO DE " + EnumTipoContratacion.obtenerDescripcion(tipoContratacion));
            movimientoReingreso.setIdUsuario(idUsuario);
            movimientoReingreso.setIdVacante(candidato.getPostuladoVacante().getInventarioVacante().getIdVacante());
            movimientoReingreso.setObservaciones("");
            // movimientosEmpleados.crearMovimientoEmpleado(movimientoReingreso);
        }

        return empleado;

    }

    private EmpleadoEntity registrarAspirante(InventarioVacanteEntity vacante, CandidatoVacanteEntity candidato, AltaEmpleadoDTO dto) {

        AspiranteEntity aspiranteEntity = this.aspiranteRepository.obtenerPorId(candidato.getIdContexto());
        if (aspiranteEntity == null) {
            throw new SistemaException("No se encontró aspirante con identificador " + candidato.getIdContexto(), SistemaCodigoError.BUSQUEDA_SIN_RESULTADOS);
        }

        if (aspiranteEntity.getIdEstatus().equals(EnumEstatusAspirante.EMPLEADO)) {
            throw new ReglaNegocioException("El aspirante se encuentra activo como empleado", ReglaNegocioCodigoError.YA_REGISTRADO);
        }

        if (this.empleadoRepository.existeEmpleadoRfc(aspiranteEntity.getRfc())) {
            throw new ReglaNegocioException("El rfc del aspirante está asignado a un empleado.", ReglaNegocioCodigoError.RFC_REGISTRADO);
        }

        if (this.empleadoRepository.existeEmpleadoConCurp(aspiranteEntity.getCurp())) {
            throw new ReglaNegocioException("El curp del aspirante está asignado a un empleado", ReglaNegocioCodigoError.CURP_REGISTRADA);
        }

        if (this.empleadoRepository.existeEmpleadoConNumeroPersonal(dto.getNumeroEmpleado())) {
            throw new ReglaNegocioException("El número personal ya se encuentra asignado a un empleado, ingrese uno nuevo.",
                    ReglaNegocioCodigoError.NUMERO_EMPLEADO);
        }

        TipoEmpleadoEntity tipoEmpleado = this.tipoEmpleadoRepository.obtenerPorId(EnumTipoEmpleado.EMPLEADO);
        // Validar si el aspirante está aprobado

        EmpleadoEntity nuevoEmpleado = new EmpleadoEntity();
        nuevoEmpleado.setApellidoMaterno(aspiranteEntity.getApellidoMaterno());
        nuevoEmpleado.setApellidoPaterno(aspiranteEntity.getApellidoPaterno());
        nuevoEmpleado.setCorreoElectronico(aspiranteEntity.getCorreoElectronico());
        nuevoEmpleado.setCurp(aspiranteEntity.getCurp());
        nuevoEmpleado.setDireccionCompleta(aspiranteEntity.getDireccionCompleta());
        nuevoEmpleado.setEstadoCivil(aspiranteEntity.getEstadoCivil());
        nuevoEmpleado.setEstatura(aspiranteEntity.getEstatura());
        nuevoEmpleado.setFechaAlta(FechaUtil.fechaActual());
        nuevoEmpleado.setFechaIngreso(dto.getFechaIngreso());
        nuevoEmpleado.setFechaNacimiento(aspiranteEntity.getFechaNacimiento());
        nuevoEmpleado.setIdEstatus(EnumEstatusEmpleado.ACTIVO);
        // nuevoEmpleado.setIdFoto(idFoto);
        nuevoEmpleado.setIdSexo(aspiranteEntity.getIdSexo());
        nuevoEmpleado.setLugarNacimiento(aspiranteEntity.getLugarNacimiento());
        nuevoEmpleado.setNacionalidad(aspiranteEntity.getNacionalidad());
        nuevoEmpleado.setNombre(aspiranteEntity.getNombre());
        nuevoEmpleado.setNombreCompleto(aspiranteEntity.getNombreCompleto());
        nuevoEmpleado.setNumeroConyuges(aspiranteEntity.getNumeroConyuges());
        nuevoEmpleado.setNumeroCuenta(dto.getNumeroCuenta());
        nuevoEmpleado.setNumeroHijos(aspiranteEntity.getNumeroHijos());
        nuevoEmpleado.setNumeroOtros(aspiranteEntity.getNumeroOtros());
        nuevoEmpleado.setNumeroPadres(aspiranteEntity.getNumeroPadres());
        // nuevoEmpleado.setOtroParentesco(aspiranteEntity.get);
        nuevoEmpleado.setPaisNacionalidad(aspiranteEntity.getPaisNacionalidad());
        // nuevoEmpleado.setPersonasDependientes(aspiranteEntity.get);
        nuevoEmpleado.setPeso(aspiranteEntity.getPeso());
        nuevoEmpleado.setRfc(aspiranteEntity.getRfc());
        nuevoEmpleado.setTelefono(aspiranteEntity.getTelefono());
        // nuevoEmpleado.setTieneLicencia(aspiranteEntity.get);
        nuevoEmpleado.setTienePersonasDependientes(aspiranteEntity.getTienePersonasDependientes());
        nuevoEmpleado.setTipoSangre(aspiranteEntity.getTipoSangre());
        nuevoEmpleado.setViveCon(aspiranteEntity.getViveCon());
        nuevoEmpleado.setTipoEmpleado(tipoEmpleado);
        nuevoEmpleado.setIdBanco(dto.getIdBanco());
        nuevoEmpleado.setNumeroEmpleado(dto.getNumeroEmpleado());
        nuevoEmpleado.setIdMetodoPago(dto.getIdMetodoPago());

        this.empleadoRepository.crear(nuevoEmpleado);

        Integer tipoContratacion = vacante.getTipoContratacion().getId();

        aspiranteEntity.setEmpleado(nuevoEmpleado);
        aspiranteEntity.setIdEstatus(EnumEstatusAspirante.EMPLEADO);
        this.aspiranteRepository.actualizar(aspiranteEntity);

        List<ExperienciaLaboralEntity> experienciasLaborales = this.experienciaLaboralRepository
                .consultarExperienciasLaboralesAspirante(aspiranteEntity.getIdAspirante());
        if (!experienciasLaborales.isEmpty()) {
            for (ExperienciaLaboralEntity entity : experienciasLaborales) {
                entity.setIdEmpleado(nuevoEmpleado.getIdEmpleado());
                this.experienciaLaboralRepository.actualizar(entity);
            }

        }

        List<HistorialAcademicoEntity> historialesAcademicos = this.historialAcademicoRepository
                .consultarHistorialAcademicoAspirante(aspiranteEntity.getIdAspirante());
        if (historialesAcademicos.isEmpty()) {
            throw new ReglaNegocioException("El aspirante no cuenta con historial academico, es requerido actualizarlo.",
                    ReglaNegocioCodigoError.ASPIRANTE_SIN_HISTORIAL);
        }

        for (HistorialAcademicoEntity entity : historialesAcademicos) {
            entity.setIdEmpleado(nuevoEmpleado.getIdEmpleado());
            this.historialAcademicoRepository.actualizar(entity);
        }

        // Registrar movimiento personal
        RegistroMovimientoDTO movimientoAlta = new RegistroMovimientoDTO();

        if (tipoContratacion == EnumTipoContratacion.BASE) {

            movimientoAlta.setIdMovimiento(EnumTipoMovimiento.ALTA_PERSONAL_BASE);

        } else if (tipoContratacion == EnumTipoContratacion.CONFIANZA) {
            movimientoAlta.setIdMovimiento(EnumTipoMovimiento.ALTA_PERSONAL_CONFIANZA);
        } else if (tipoContratacion == EnumTipoContratacion.CONTRATO_ESTATAL || tipoContratacion == EnumTipoContratacion.CONTRATO_FEDERAL
                || tipoContratacion == EnumTipoContratacion.HONORARIOS) {

            movimientoAlta.setIdMovimiento(EnumTipoMovimiento.ALTA_PERSONAL_EVENTUAL);
        } else {
            throw new BusinessException("Error al generar tipo de movimiento de alta.");
        }

        // TODO quitar condición, solo está mientras definen el tipo de
        // movimiento por alta
        if (!ValidacionUtil.esNumeroPositivo(movimientoAlta.getIdMovimiento())) {
            movimientoAlta.setObservaciones("ALTA EMPLEADO DE " + EnumTipoContratacion.obtenerDescripcion(tipoContratacion));
            movimientoAlta.setIdUsuario(dto.getIdUsuario());
            movimientoAlta.setIdVacante(vacante.getIdVacante());
            movimientoAlta.setObservaciones("");
            this.movimientosEmpleados.crearMovimientoEmpleado(movimientoAlta);
        }

        return nuevoEmpleado;

    }

}
