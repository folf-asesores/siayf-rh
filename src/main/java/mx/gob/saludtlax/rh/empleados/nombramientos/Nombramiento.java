/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos;

import java.util.List;

import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.ClasificacionReporteDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoLugarAdscripcionNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.InfoNombramientoDTO;
import mx.gob.saludtlax.rh.empleados.nombramientos.impresion.NombramientoDetalleDTO;

/**
 * @author Eduardo Mex

 * @version 1.0
 * @since 18:08:17 12/09/2016
 */
public interface Nombramiento {

    public NombramientoDetalleDTO obtenerNombramientoReportePorId(Integer idNombramiento, Integer idClasificacion);

    public NombramientoDetalleDTO obtenerNombramientoReporteFormalizaFaseIIPorId(Integer idNombramiento, Integer idClasificacion);

    public List<InfoNombramientoDTO> obtenerListaInfoNombramiento();

    public List<InfoNombramientoDTO> obtenerListaInfoNombramientoPorTipo(Integer tipoNombramiento);

    public Integer obtenerInventarioVacantePorIdNombramiento(Integer idNombramiento);

    public InfoLugarAdscripcionNombramientoDTO obtenerInfoLugarAdscripcion(Integer adscripcion, Integer areaAdscripcion, Integer lugarAdscripcion);

    public void actualizarNombramientoImpreso(Integer idNombramiento, String tipoAdscripcion, boolean impreso);

    public Integer actualizarEstructuraNombramiento(ClasificacionReporteDTO clasificacionReporteDTO);

    public NombramientoInterinatoDTO obtenerNombramientoReporteInterinato(Integer idNombramiento, Integer idClasificacion);

}
