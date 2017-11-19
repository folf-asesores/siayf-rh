/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.util.List;

import mx.gob.saludtlax.rh.puestosautorizados.FiltroConsultaFinanciamientosDTO;
import mx.gob.saludtlax.rh.puestosautorizados.InfoConfiguracionDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/08/2016 13:46:42
 *
 */
public interface ConfiguracionPresupuestal {

    public DetalleConfiguracionPresupuestoDTO obtenerDetalleConfiguracionId(
            Integer idConfiguracion);

    /**
     * Crea la configuración de los datos laborales.
     */
    public Integer crearDatosLaborales(
            SolicitudNuevoPuestoDTO solicitudPuestoDTO);

    public List<InfoConfiguracionDTO> consultarConfiguracionesPorCriterio(
            FiltroConsultaFinanciamientosDTO filtro);

    public DatoLaboralDTO obtenerConfiguracionPorId(Integer idConfiguracion);

    public DatoLaboralDTO obtenerDatosLaboralesPuesto(Integer idPuesto);

    public InfoDatosLaboralesDTO obtenerDatosLaboralesId(Integer idPuesto);

    public InfoConfiguracionDTO obtenerPorIdEmpleado(Integer idEmpleado);

    /**
     * Permite la edición de los datos laborales del empleado.
     *
     * @param datoLaboral
     *            información de datos laborales
     * @param idPuesto
     *            puesto al que corresponde la configuración laboral a
     *            modificar.
     * @param idUsuario
     *            usuario que realiza la modificación
     */
    public void modificarDatoLaboral(DatoLaboralDTO datoLaboral,
            Integer idPuesto, Integer idUsuario);

}
