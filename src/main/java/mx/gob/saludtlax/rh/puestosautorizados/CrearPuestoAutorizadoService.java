/*
 *
 */

package mx.gob.saludtlax.rh.puestosautorizados;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.datolaboral.EnumEstatusConfiguracion;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionPresupuestoRepository;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.DetalleProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusConfiguracionesRepository;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosEntity;
import mx.gob.saludtlax.rh.persistencia.EstatusPuestosRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.ProgramaEntity;
import mx.gob.saludtlax.rh.persistencia.ProgramaRepository;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralEntity;
import mx.gob.saludtlax.rh.persistencia.PuestoGeneralRepository;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionEntity;
import mx.gob.saludtlax.rh.persistencia.TipoContratacionRepository;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosEntity;
import mx.gob.saludtlax.rh.persistencia.TiposNombramientosRepository;
import mx.gob.saludtlax.rh.puestosautorizados.programas.EnumEstatusDetallePrograma;
import mx.gob.saludtlax.rh.puestosautorizados.programas.EnumTipoDetallePrograma;
import mx.gob.saludtlax.rh.util.FechaUtil;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

/**
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 11:16:14
 *
 */
public class CrearPuestoAutorizadoService {
    @Inject
    private ConfiguracionPresupuestoRepository datosLaboralesRepository;
    @Inject
    private DetalleProgramaRepository detalleProgramaRepository;
    @Inject
    private EstatusConfiguracionesRepository estatusConfiguracionesRepository;
    @Inject
    private EstatusPuestosRepository estatusPuestoRepository;

    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private TipoContratacionRepository tipoContratacionRepository;
    @Inject
    private TiposNombramientosRepository tiposNombramientoRepository;
    @Inject
    private ProgramaRepository programaRepository;
    @Inject
    private PuestoGeneralRepository puestoGeneralRepository;

    public void crearPuestoAutorizado(AperturaVacanteDTO dto) {
        EstatusPuestosEntity estatusPuesto = estatusPuestoRepository
                .obtenerPorId(EnumEstatusPuesto.APERTURA_DESIGNACION);
        if (dto.getTipoApertura() == EnumTipoApertura.APERTURA_INDIVIDUAL) {

            ConfiguracionPresupuestoEntity datosLaborales = datosLaboralesRepository
                    .obtenerPorId(dto.getIdConfiguracionPresupuesto());
            EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
                    .obtenerPorId(
                            EnumEstatusConfiguracion.EN_ESPERA_ACTIVACION);

            datosLaborales.setEstatus(estatus);
            datosLaboralesRepository.actualizar(datosLaborales);

            Integer ultimoFolio = inventarioVacanteRepository
                    .ultimoFolioVacanteContratacion(
                            datosLaborales.getTipoContratacion().getId());
            Integer siguienteNumeroVacante = 1;
            if (ultimoFolio != null) {
                siguienteNumeroVacante = ultimoFolio + 1;
            }
            String folioVacante = generarFolioVacante(siguienteNumeroVacante,
                    datosLaborales.getTipoContratacion().getCodigo());

            InventarioVacanteEntity nuevaVacante = new InventarioVacanteEntity();
            nuevaVacante.setCodigoVacante(
                    datosLaborales.getTipoContratacion().getCodigo());
            nuevaVacante.setConfiguracion(datosLaborales);
            nuevaVacante.setDisponible("SI");
            nuevaVacante.setFolioVacante(folioVacante);
            nuevaVacante.setEstatus(estatusPuesto);
            nuevaVacante.setNumeroVacante(siguienteNumeroVacante);
            // nuevaVacante.setPrograma(programa);
            nuevaVacante
                    .setTipoContratacion(datosLaborales.getTipoContratacion());
            nuevaVacante.setPuestoAutorizado(datosLaborales.getPuesto());
            nuevaVacante.setProvisional(false);

            inventarioVacanteRepository.crear(nuevaVacante);

        }

    }

    public void crearPuestosProgramaFederal(Integer idDetallePrograma) {
        if (!ValidacionUtil.esNumeroPositivo(idDetallePrograma)) {
            throw new ValidacionException(
                    "El detalle del programa " + idDetallePrograma
                            + " no existe.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        DetalleProgramaEntity detallePrograma = detalleProgramaRepository
                .obtenerPorId(idDetallePrograma);

        if (detallePrograma == null) {
            throw new ValidacionException("El detalle del programa no existe.",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        if (detallePrograma.getEstatus()
                .equals(EnumEstatusDetallePrograma.ACTIVO)) {
            throw new ReglaNegocioException(
                    "Ya se han aperturado las vacantes para  "
                            + detallePrograma.getDescripcion(),
                    ReglaNegocioCodigoError.YA_AUTORIZADO);
        }

        ProgramaEntity programa = programaRepository
                .obtenerPorId(detallePrograma.getIdPrograma());

        if (programa == null) {
            throw new ValidacionException("El programa no existe",
                    ValidacionCodigoError.REGISTRO_NO_ENCONTRADO);
        }

        int numeroPuestos = detallePrograma.getNumeroPersonas();

        EstatusConfiguracionesEntity estatus = estatusConfiguracionesRepository
                .obtenerPorId(EnumEstatusConfiguracion.EN_ESPERA_ACTIVACION);
        EstatusPuestosEntity estatusPuesto = estatusPuestoRepository
                .obtenerPorId(EnumEstatusPuesto.APERTURA_DESIGNACION);

        // Sin puesto especificado.
        PuestoGeneralEntity sinPuesto = puestoGeneralRepository
                .obtenerPorId(239);

        if (detallePrograma.getTipoDetallePrograma()
                .equals(EnumTipoDetallePrograma.PARTIDA)) {

            TiposNombramientosEntity nombramiento = tiposNombramientoRepository
                    .nombramientoPorId(EnumTipoNombramiento.EVENTUALES);
            TipoContratacionEntity tipoContratacion = tipoContratacionRepository
                    .obtenerPorId(EnumTipoContratacion.CONTRATO_FEDERAL);
            TipoContratacionEntity honorarios = tipoContratacionRepository
                    .obtenerPorId(EnumTipoContratacion.HONORARIOS);

            for (int i = 1; i <= numeroPuestos; i++) {

                // Crear datos laborales
                ConfiguracionPresupuestoEntity datosLaborales = new ConfiguracionPresupuestoEntity();
                datosLaborales.setCuenta(programa.getCuenta());
                datosLaborales.setDependencia(programa.getDependencia());
                datosLaborales.setEstatus(estatus);
                datosLaborales
                        .setFechaAltaConfiguracion(FechaUtil.fechaActual());
                datosLaborales.setFuenteFinanciamiento(
                        programa.getFuenteFinanciamiento());
                datosLaborales.setNombramiento(nombramiento);
                // datosLaborales.setNumeroEmpleado(numeroEmpleado);
                datosLaborales.setProyecto(programa.getProyecto());
                datosLaborales.setPuesto(sinPuesto);
                datosLaborales.setSubfuenteFinanciamiento(
                        programa.getSubfuenteFinanciamiento());
                datosLaborales.setSueldo(detallePrograma.getCostoUnitario());
                // datosLaborales.setTabulador(tabulador);
                if (detallePrograma.getEsHonorario()) {
                    datosLaborales.setTipoContratacion(honorarios);
                } else {
                    datosLaborales.setTipoContratacion(tipoContratacion);
                }

                datosLaborales.setTipoRecurso(programa.getTipoRecurso());
                datosLaborales
                        .setUnidadResponsable(programa.getUnidadResponsable());
                datosLaboralesRepository.crear(datosLaborales);
                // Crear puesto en el inventario
                Integer ultimoFolio = inventarioVacanteRepository
                        .ultimoFolioVacanteContratacion(
                                datosLaborales.getTipoContratacion().getId());
                Integer siguienteNumeroVacante = 1;
                if (ultimoFolio != null) {
                    siguienteNumeroVacante = ultimoFolio + 1;
                }
                String folioVacante = generarFolioVacante(
                        siguienteNumeroVacante,
                        datosLaborales.getTipoContratacion().getCodigo());

                InventarioVacanteEntity nuevaVacante = new InventarioVacanteEntity();
                nuevaVacante.setCodigoVacante(
                        datosLaborales.getTipoContratacion().getCodigo());
                nuevaVacante.setConfiguracion(datosLaborales);
                nuevaVacante.setDisponible("SI");
                nuevaVacante.setFolioVacante(folioVacante);
                nuevaVacante.setEstatus(estatusPuesto);
                nuevaVacante.setNumeroVacante(siguienteNumeroVacante);
                nuevaVacante.setPrograma(programa);
                nuevaVacante.setDetallePrograma(detallePrograma);
                nuevaVacante.setTipoContratacion(
                        datosLaborales.getTipoContratacion());

                nuevaVacante.setProvisional(false);

                inventarioVacanteRepository.crear(nuevaVacante);
            }

        } else if (detallePrograma.getTipoDetallePrograma()
                .equals(EnumTipoDetallePrograma.VOLUNTARIO)) {

            TiposNombramientosEntity nombramiento = tiposNombramientoRepository
                    .nombramientoPorId(EnumTipoNombramiento.VOLUNTARIO);
            TipoContratacionEntity tipoContratacion = tipoContratacionRepository
                    .obtenerPorId(EnumTipoContratacion.VOLUNTARIOS);

            // Los voluntarios no cuentan con datos labores, por lo cual serán
            // omitidos en la creación de la configuración presupuestal
            ConfiguracionPresupuestoEntity datosLaborales = new ConfiguracionPresupuestoEntity();
            datosLaborales.setEstatus(estatus);
            datosLaborales.setFechaAltaConfiguracion(FechaUtil.fechaActual());
            datosLaborales.setNombramiento(nombramiento);
            // datosLaborales.setNumeroEmpleado(numeroEmpleado);
            datosLaborales.setProyecto(programa.getProyecto());
            datosLaborales.setPuesto(sinPuesto);
            datosLaborales.setSubfuenteFinanciamiento(
                    programa.getSubfuenteFinanciamiento());
            datosLaborales.setSueldo(detallePrograma.getCostoUnitario());
            // datosLaborales.setTabulador(tabulador);
            datosLaborales.setTipoContratacion(tipoContratacion);
            datosLaborales.setTipoRecurso(programa.getTipoRecurso());
            datosLaborales
                    .setUnidadResponsable(programa.getUnidadResponsable());
            datosLaboralesRepository.crear(datosLaborales);
            // Crear puesto en el inventario
            Integer ultimoFolio = inventarioVacanteRepository
                    .ultimoFolioVacanteContratacion(
                            datosLaborales.getTipoContratacion().getId());
            Integer siguienteNumeroVacante = 1;
            if (ultimoFolio != null) {
                siguienteNumeroVacante = ultimoFolio + 1;
            }
            String folioVacante = generarFolioVacante(siguienteNumeroVacante,
                    datosLaborales.getTipoContratacion().getCodigo());

            InventarioVacanteEntity nuevaVacante = new InventarioVacanteEntity();
            nuevaVacante.setCodigoVacante(
                    datosLaborales.getTipoContratacion().getCodigo());
            nuevaVacante.setConfiguracion(datosLaborales);
            nuevaVacante.setDisponible("NO");
            nuevaVacante.setFolioVacante(folioVacante);
            nuevaVacante.setEstatus(estatusPuesto);
            nuevaVacante.setNumeroVacante(siguienteNumeroVacante);
            nuevaVacante.setPrograma(programa);
            nuevaVacante.setDetallePrograma(detallePrograma);
            nuevaVacante
                    .setTipoContratacion(datosLaborales.getTipoContratacion());

            nuevaVacante.setProvisional(false);

            inventarioVacanteRepository.crear(nuevaVacante);
        }

        detallePrograma.setEstatus(EnumEstatusDetallePrograma.ACTIVO);
        detalleProgramaRepository.actualizar(detallePrograma);

    }

    private String generarFolioVacante(Integer siguienteNumeroVacante,
            String codigoContratacion) {
        String folioVacante = "";

        if (siguienteNumeroVacante < 10) {
            folioVacante = codigoContratacion + "-00" + siguienteNumeroVacante;
        } else if (siguienteNumeroVacante < 100) {
            folioVacante = codigoContratacion + "-0" + siguienteNumeroVacante;
        } else {
            folioVacante = codigoContratacion + "-" + siguienteNumeroVacante;
        }

        return folioVacante;
    }
}
