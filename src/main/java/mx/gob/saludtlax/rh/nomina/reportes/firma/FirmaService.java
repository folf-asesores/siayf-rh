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

import mx.gob.saludtlax.rh.persistencia.FirmaReporteQuery;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class FirmaService {

    @Inject
    private FirmaReporteQuery firmaQuery;

    public FirmaDTO obtenerFirmaEmpleado(Integer idProductoNomina) {
        List<FirmaPojo> datos = firmaQuery.obtenerDatos(idProductoNomina);
        FirmaDTO firma = convertir(datos);

        return firma;
    }

    private FirmaDTO convertir(List<FirmaPojo> datosBrutos) {
        if (datosBrutos != null && !datosBrutos.isEmpty()) {
            Map<String, UnidadResponsableDTO> unidadesResponsables = new TreeMap<>();

            for (FirmaPojo firmaPojo : datosBrutos) {
                String numeroUnidadResponsable = firmaPojo.getClave();

                if (!unidadesResponsables.containsKey(numeroUnidadResponsable)) {
                    FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder().setFiliacion(firmaPojo.getFiliacion()).setNombre(firmaPojo.getNombre())
                            .setNumeroCheque(firmaPojo.getNumeroCheque()).setImporte(firmaPojo.getNeto()).construirFirmaEmpleadoDTO();
                    Map<String, FirmaEmpleadoDTO> firmasEmpleados = new TreeMap<>();
                    firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);

                    Map<Integer, ProgramaDTO> programas = new TreeMap<>();
                    Integer idPrograma = firmaPojo.getIdPrograma();
                    ProgramaDTO programa = new ProgramaDTO.Builder(idPrograma, firmaPojo.getPrograma(), firmaPojo.getInicioPeriodo(), firmaPojo.getFinPeriodo())
                            .setFirmasEmpleados(firmasEmpleados).construirProgramaDTO();
                    programas.put(idPrograma, programa);

                    UnidadResponsableDTO unidadResponsable = new UnidadResponsableDTO.Builder(firmaPojo.getClave(), firmaPojo.getDescripcion())
                            .setProgramas(programas).construirUnidadResponsableDTO();

                    unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);
                } else {
                    UnidadResponsableDTO unidadResponsable = unidadesResponsables.get(numeroUnidadResponsable);
                    Map<Integer, ProgramaDTO> programas = unidadResponsable.getProgramas();
                    Integer idPrograma = firmaPojo.getIdPrograma();

                    if (!programas.containsKey(idPrograma)) {
                        FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder().setFiliacion(firmaPojo.getFiliacion()).setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque()).setImporte(firmaPojo.getNeto()).construirFirmaEmpleadoDTO();
                        Map<String, FirmaEmpleadoDTO> firmasEmpleados = new TreeMap<>();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);

                        ProgramaDTO programa = new ProgramaDTO.Builder(idPrograma, firmaPojo.getPrograma(), firmaPojo.getInicioPeriodo(),
                                firmaPojo.getFinPeriodo()).setFirmasEmpleados(firmasEmpleados).construirProgramaDTO();
                        programas.put(idPrograma, programa);
                    } else {
                        ProgramaDTO programa = programas.get(idPrograma);
                        Map<String, FirmaEmpleadoDTO> firmasEmpleados = programa.getFirmasEmpleados();
                        FirmaEmpleadoDTO firmaEmpleado = new FirmaEmpleadoDTO.Builder().setFiliacion(firmaPojo.getFiliacion()).setNombre(firmaPojo.getNombre())
                                .setNumeroCheque(firmaPojo.getNumeroCheque()).setImporte(firmaPojo.getNeto()).construirFirmaEmpleadoDTO();
                        firmasEmpleados.put(firmaEmpleado.getFiliacion(), firmaEmpleado);
                    }
                }
            }

            FirmaPojo firmaPojo = datosBrutos.get(0);
            FirmaDTO firma = new FirmaDTO.Builder(firmaPojo.getIdProductoNomina(), firmaPojo.getFechaPago(), unidadesResponsables)
                    // Elaboro
                    .setNombreElaboro(firmaPojo.getJefe1Nombre()).setCargoElaboro(firmaPojo.getJefe1Cargo())
                    // Reviso
                    .setNombreReviso(firmaPojo.getJefe2Nombre()).setCargoReviso(firmaPojo.getJefe2Cargo())
                    // Autorizo
                    .setNombreAutorizo(firmaPojo.getJefe3Nombre()).setCargoAutorizo(firmaPojo.getJefe3Cargo()).construirFirmaDTO();

            return firma;
        } else {
            return null;
        }
    }

}
