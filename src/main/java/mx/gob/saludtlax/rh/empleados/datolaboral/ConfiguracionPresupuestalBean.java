/*
 *
 */

package mx.gob.saludtlax.rh.empleados.datolaboral;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import mx.gob.saludtlax.rh.puestosautorizados.FiltroConsultaFinanciamientosDTO;
import mx.gob.saludtlax.rh.puestosautorizados.InfoConfiguracionDTO;

/**
 * @author Leila Schiaffini Ehuan
 * @since 15/08/2016 13:47:00
 *
 */
@Stateless
public class ConfiguracionPresupuestalBean implements ConfiguracionPresupuestal {

    @Inject
    private ConfiguracionPresupuestalService configuracionPresupuestalService;

    @Override
    public DetalleConfiguracionPresupuestoDTO obtenerDetalleConfiguracionId(Integer idConfiguracion) {

        return configuracionPresupuestalService.obtenerDetallePorIdConfiguracion(idConfiguracion);
    }

    @Override
    public Integer crearDatosLaborales(SolicitudNuevoPuestoDTO solicitudPuestoDTO) {

        return configuracionPresupuestalService.crearDatoLaboral(solicitudPuestoDTO);
    }

    @Override
    public List<InfoConfiguracionDTO> consultarConfiguracionesPorCriterio(FiltroConsultaFinanciamientosDTO filtro) {
        return configuracionPresupuestalService.consultarConfiguracionesPorCriterio(filtro);
    }

    @Override
    public DatoLaboralDTO obtenerConfiguracionPorId(Integer idConfiguracion) {

        return configuracionPresupuestalService.obtenerConfiguracionPorId(idConfiguracion);
    }

    @Override
    public DatoLaboralDTO obtenerDatosLaboralesPuesto(Integer idPuesto) {

        return configuracionPresupuestalService.obtenerDatosLaboralesPuesto(idPuesto);
    }

    @Override
    public InfoDatosLaboralesDTO obtenerDatosLaboralesId(Integer idPuesto) {

        return configuracionPresupuestalService.obtenerDatosLaboralesIdPuesto(idPuesto);
    }

    @Interceptors({ ModificacionLaboralValidator.class })
    @Override
    public void modificarDatoLaboral(DatoLaboralDTO datoLaboral, Integer idPuesto, Integer idUsuario) {
        configuracionPresupuestalService.modificarDatoLaboral(datoLaboral, idPuesto, idUsuario);

    }

    @Override
    public InfoConfiguracionDTO obtenerPorIdEmpleado(Integer idEmpleado) {

        List<InfoConfiguracionDTO> listaConfiguraciones = new ArrayList<>();
        listaConfiguraciones = configuracionPresupuestalService.consultarConfiguracionesPorEmpleado(idEmpleado);
        if (listaConfiguraciones != null && !listaConfiguraciones.isEmpty()) {
            return listaConfiguraciones.get(0);
        }
        return null;
    }
}
