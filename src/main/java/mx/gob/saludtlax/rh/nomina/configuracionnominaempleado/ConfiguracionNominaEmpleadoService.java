
package mx.gob.saludtlax.rh.nomina.configuracionnominaempleado;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.empleados.administracion.EmpleadoDetalladoDTO;
import mx.gob.saludtlax.rh.empleados.administracion.EnumEstatusEmpleado;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.EmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.EmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.HistorialAcademicoRepository;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteEntity;
import mx.gob.saludtlax.rh.persistencia.InventarioVacanteRepository;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.util.FechaUtil;

public class ConfiguracionNominaEmpleadoService {
    @Inject
    private EmpleadoRepository empleadoRepository;
    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;
    @Inject
    private InventarioVacanteRepository inventarioVacanteRepository;
    @Inject
    private HistorialAcademicoRepository historialAcademicoRepository;

    public EmpleadoDetalladoDTO obtenerEmpleadoDatos(Integer idEmpleado) {

        if (idEmpleado == null) {
            throw new ValidacionException(
                    "Es requerido el identificador del empleado para obtener su información",
                    ValidacionCodigoError.VALOR_REQUERIDO);

        }

        String estatusEmpleado = empleadoRepository
                .obtenerEstatusEmpleado(idEmpleado);
        EmpleadoDetalladoDTO dto = new EmpleadoDetalladoDTO();
        if (estatusEmpleado.equals(EnumEstatusEmpleado.INACTIVO)) {
            EmpleadoEntity empleadoInactivo = empleadoRepository
                    .obtenerPorId(idEmpleado);
            dto.setCurp(empleadoInactivo.getCurp());
            dto.setDomicilio(empleadoInactivo.getDireccionCompleta());
            dto.setEdad(FechaUtil
                    .calcularEdad(empleadoInactivo.getFechaNacimiento()));
            dto.setEstadoCivil(empleadoInactivo.getEstadoCivil());

            String estudios = "";
            List<String> listaEstudios = historialAcademicoRepository
                    .consultaEstudiosProfesionistasEmpleado(idEmpleado);
            if (!listaEstudios.isEmpty()) {
                for (String estudio : listaEstudios) {
                    estudios = estudio + "/";
                }
            }
            dto.setEstudios(estudios);
            dto.setNacionalidad(empleadoInactivo.getNacionalidad());
            dto.setNombre(empleadoInactivo.getNombreCompleto());
            dto.setRfc(empleadoInactivo.getRfc());
            dto.setSexo(empleadoInactivo.getIdSexo());

        } else {

            InventarioVacanteEntity puesto = inventarioVacanteRepository
                    .obtenerPuestoPorIdEmpleado(idEmpleado);

            if (puesto == null) {
                throw new ReglaNegocioException("",
                        ReglaNegocioCodigoError.CONFIGURACION_NO_EXISTE);
            }

            if (puesto.getAdscripcion() != null) {
                dto.setAdscripcion(puesto.getAdscripcion().getAdscripcion());

            } else {
                dto.setAdscripcion("SIN ASIGNAR");
            }

            if (puesto.getSubadscripcion() != null) {
                dto.setAreaAdscripcion(
                        puesto.getSubadscripcion().getSubadscripcion());
            } else {
                dto.setAreaAdscripcion("SIN ASIGNAR");
            }

            dto.setCodigoPuesto(
                    puesto.getConfiguracion().getPuesto().getCodigo());
            dto.setCurp(puesto.getConfiguracion().getEmpleado().getCurp());
            dto.setDomicilio(puesto.getConfiguracion().getEmpleado()
                    .getDireccionCompleta());
            dto.setEdad(FechaUtil.calcularEdad(puesto.getConfiguracion()
                    .getEmpleado().getFechaNacimiento()));
            dto.setEstadoCivil(
                    puesto.getConfiguracion().getEmpleado().getEstadoCivil());
            String estudios = "";
            List<String> listaEstudios = historialAcademicoRepository
                    .consultaEstudiosProfesionistasEmpleado(idEmpleado);
            if (!listaEstudios.isEmpty()) {
                for (String estudio : listaEstudios) {
                    estudios = estudio + "/";
                }
            } else {
                estudios = "SIN REGISTROS DE HISTORIAL ACADEMICO.";
            }
            dto.setEstudios(estudios);
            dto.setIdTipoContratacion(puesto.getTipoContratacion().getId());

            dto.setNacionalidad(
                    puesto.getConfiguracion().getEmpleado().getNacionalidad());
            dto.setNombramiento(puesto.getConfiguracion().getNombramiento()
                    .getNombramiento());
            dto.setNombre(puesto.getConfiguracion().getEmpleado()
                    .getNombreCompleto());
            dto.setPuesto(puesto.getConfiguracion().getPuesto().getPuesto());
            dto.setRfc(puesto.getConfiguracion().getEmpleado().getRfc());
            dto.setSexo(puesto.getConfiguracion().getEmpleado().getIdSexo());
            dto.setTipoContratacion(
                    puesto.getTipoContratacion().getTipoContratacion());
            dto.setUnidadResponsable(puesto.getConfiguracion()
                    .getUnidadResponsable().getDescripcion());
            /*
             * MovimientoEmpleadoEntity ultimaLicencia =
             * movimientoEmpleadoRepository
             * .obtenerUltimoMovimientoPorPadre(EnumTipoMovimiento.LICENCIAS,
             * idEmpleado);
             *
             * if (ultimaLicencia != null) {
             * dto.setLicencia(ultimaLicencia.getMovimiento().getMovimiento());
             * dto.setUltimaLicencia(ultimaLicencia.getFechaIngreso()); }
             */

        }

        return dto;
    }

}