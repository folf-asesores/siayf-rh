/*
 * FirmaService.java
 * Creado el 11/sep/2017 12:51:19 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.reportes.firma;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Inject;
import mx.gob.saludtlax.rh.persistencia.espejo.FirmaReporteQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaService {

    @Inject
    private FirmaReporteQuery firmaQuery;

    public FirmaDTO obtenerFirmaEmpleado(Integer idProductoNomina) {
        List<FirmaPOJO> datos = firmaQuery.obtenerDatos(idProductoNomina);
        FirmaDTO firma = convertir(datos);
        
        return firma;
    }
    
    private FirmaDTO convertir(List<FirmaPOJO> datosBrutos) {
        if (datosBrutos != null && !datosBrutos.isEmpty()) {
            Map<Integer, ProgramaDTO> programas = new TreeMap<>();

            for (FirmaPOJO firmaPojo : datosBrutos) {
                Integer idPrograma = firmaPojo.getIdPrograma();

                if (!programas.containsKey(idPrograma)) {
                    FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder()
                            .setFiliacion(firmaPojo.getFiliacion())
                            .setNombre(firmaPojo.getNombre())
                            .setNumeroCheque(firmaPojo.getNumeroCheque())
                            .setImporte(firmaPojo.getNeto())
                            .construirFirmaEmpleadoDTO();
                    Map<String, FirmaEmpleadoDTO> firmasEmpleados = new TreeMap<>();
                    firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);

                    Map<String, UnidadResponsableDTO> unidadesResponsables = new TreeMap<>();
                    UnidadResponsableDTO unidadResponsable = new UnidadResponsableDTO.Builder(firmaPojo.getClave(), firmaPojo.getDescripcion())
                            .setFirmasEmpleados(firmasEmpleados)
                            .construirUnidadResponsableDTO();
                    unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);

                    ProgramaDTO programa = new ProgramaDTO.Builder(idPrograma, firmaPojo.getPrograma(), firmaPojo.getInicioPeriodo(), firmaPojo.getFinPeriodo())
                            .setUnidadesResponsables(unidadesResponsables)
                            .construirProgramaDTO();

                    programas.put(idPrograma, programa);
                } else {
                    ProgramaDTO programa = programas.get(idPrograma);
                    Map<String, UnidadResponsableDTO> unidadesResponsables = programa.getUnidadesResponsables();

                    if (!unidadesResponsables.containsKey(firmaPojo.getClave())) {
                        FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder()
                                .setFiliacion(firmaPojo.getFiliacion())
                                .setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque())
                                .setImporte(firmaPojo.getNeto())
                                .construirFirmaEmpleadoDTO();
                        Map<String, FirmaEmpleadoDTO> firmasEmpleados = new TreeMap<>();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);

                        UnidadResponsableDTO unidadResponsable = new UnidadResponsableDTO.Builder(firmaPojo.getClave(), firmaPojo.getDescripcion())
                                .setFirmasEmpleados(firmasEmpleados)
                                .construirUnidadResponsableDTO();
                        unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);
                    } else {
                        UnidadResponsableDTO unidadResponsable = unidadesResponsables.get(firmaPojo.getClave());
                        Map<String, FirmaEmpleadoDTO> firmasEmpleados = unidadResponsable.getFirmasEmpleados();
                        
                        FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder()
                                .setFiliacion(firmaPojo.getFiliacion())
                                .setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque())
                                .setImporte(firmaPojo.getNeto())
                                .construirFirmaEmpleadoDTO();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
                    }
                }
            }

            FirmaPOJO firmaPojo = datosBrutos.get(0);
            FirmaDTO firma = new FirmaDTO.Builder(firmaPojo.getIdProductoNomina(), firmaPojo.getFechaPago(), programas)
                    // Elaboro
                    .setNombreElaboro(firmaPojo.getJefe1Nombre())
                    .setCargoElaboro(firmaPojo.getJefe1Cargo())
                    // Reviso
                    .setNombreReviso(firmaPojo.getJefe2Nombre())
                    .setCargoReviso(firmaPojo.getJefe2Cargo())
                    // Autorizo
                    .setNombreAutorizo(firmaPojo.getJefe3Nombre())
                    .setCargoReviso(firmaPojo.getJefe3Cargo())
                    .construirFirmaDTO();

            return firma;
        } else {
            return null;
        }
    }

}
