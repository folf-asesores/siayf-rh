/*
 * PrenominaReporteService.java
 * Creado el 05/Jul/2017 12:28:37 PM
 *
 */

package mx.gob.saludtlax.rh.nomina.reportes.prenomina;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class PrenominaReporteService {

    private static final Logger LOGGER = Logger
            .getLogger(PrenominaReporteService.class.getName());

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    private static final String USP_REPORTE_PRENOMINA = "CALL usp_reporte_prenomina(:idProductoNomina)";

    public ProductoNominaDTO obtenerProductoNomina(Integer idProductoNomina) {
        List<PrenominaDTO> prenominas = consultarDatos(idProductoNomina);
        ProductoNominaDTO productoNomina = convertirDatos(idProductoNomina,
                prenominas);
        LOGGER.debug(productoNomina);
        return productoNomina;
    }

    private List<PrenominaDTO> consultarDatos(Integer idProductoNomina) {
        Query consulta = entityManager.createNativeQuery(USP_REPORTE_PRENOMINA);
        consulta.setParameter("idProductoNomina", idProductoNomina);

        List<Object[]> resultado = consulta.getResultList();
        List<PrenominaDTO> prenominas = new ArrayList<>();

        for (Object[] obj : resultado) {
            PrenominaDTO prenomina = new PrenominaDTO();

            // Elaboro
            if (obj[0] instanceof String) {
                String elaboroNombre = (String) obj[0];
                prenomina.setElaboroNombre(elaboroNombre);
            }

            if (obj[1] instanceof String) {
                String elaboroCargo = (String) obj[1];
                prenomina.setElaboroCargo(elaboroCargo);
            }

            // Reviso
            if (obj[2] instanceof String) {
                String revisoNombre = (String) obj[2];
                prenomina.setRevisoNombre(revisoNombre);
            }

            if (obj[3] instanceof String) {
                String revisoCargo = (String) obj[3];
                prenomina.setRevisoCargo(revisoCargo);
            }

            // Autorizo
            if (obj[4] instanceof String) {
                String autorizoNombre = (String) obj[4];
                prenomina.setAutorizoNombre(autorizoNombre);
            }

            if (obj[5] instanceof String) {
                String autorizoCargo = (String) obj[5];
                prenomina.setAutorizoCargo(autorizoCargo);
            }

            if (obj[6] instanceof Date) {
                Date fechaPago = (Date) obj[6];
                prenomina.setFechaPago(fechaPago);
            }

            if (obj[7] instanceof Integer) {
                Integer idPro = (Integer) obj[7];
                prenomina.setIdProductoNomina(idPro);
            }

            if (obj[8] instanceof Integer) {
                Integer idPrograma = (Integer) obj[8];
                prenomina.setIdPrograma(idPrograma);
            } else if (obj[8] instanceof BigInteger) {
                Integer idPrograma = ((BigInteger) obj[8]).intValue();
                prenomina.setIdPrograma(idPrograma);
            }

            if (obj[9] instanceof String) {
                String programa = (String) obj[9];
                prenomina.setPrograma(programa);
            }

            if (obj[10] instanceof Date) {
                Date incioPeriodo = (Date) obj[10];
                prenomina.setInicioPeriodo(incioPeriodo);
            }

            if (obj[11] instanceof Date) {
                Date finPeriodo = (Date) obj[11];
                prenomina.setFinPeriodo(finPeriodo);
            }

            if (obj[12] instanceof String) {
                String claveCentroResponsabilidad = (String) obj[12];
                prenomina.setClaveCentroResponsabilidad(
                        claveCentroResponsabilidad);
            }

            if (obj[13] instanceof String) {
                String descripcionCentroResponsabilidad = (String) obj[13];
                prenomina.setDescripcionCentroResponsabilidad(
                        descripcionCentroResponsabilidad);
            }

            if (obj[14] instanceof String) {
                String rfc = (String) obj[14];
                prenomina.setRfc(rfc);
            }

            if (obj[15] instanceof String) {
                String nombre = (String) obj[15];
                prenomina.setNombre(nombre);
            }

            if (obj[16] instanceof String) {
                String claveConcepto = (String) obj[16];
                prenomina.setClaveConcepto(claveConcepto);
            }

            if (obj[17] instanceof String) {
                String descripcionConcepto = (String) obj[17];
                prenomina.setDescripcionConcepto(descripcionConcepto);
            }

            if (obj[18] instanceof BigDecimal) {
                BigDecimal importe = (BigDecimal) obj[18];
                prenomina.setImporte(importe);
            }

            if (obj[19] instanceof BigDecimal) {
                BigDecimal total = (BigDecimal) obj[19];
                prenomina.setTotal(total);
            }

            if (obj[20] instanceof String) {
                String tipo = (String) obj[20];
                prenomina.setTipo(tipo);
            }

            prenominas.add(prenomina);
        }

        for (PrenominaDTO prenomina : prenominas) {
            LOGGER.debug(prenomina);
        }

        return prenominas;
    }

    private ProductoNominaDTO convertirDatos(Integer idProductoNomina,
            List<PrenominaDTO> prenominas) {
        if (prenominas != null && !prenominas.isEmpty()) {
            Map<Integer, ProgramaDTO> programas = new HashMap<>();

            for (PrenominaDTO prenomina : prenominas) {
                Integer idPrograma = prenomina.getIdPrograma();
                if (!programas.containsKey(idPrograma)) {

                    NominaEmpleadoDTO nominaEmpleado = new NominaEmpleadoDTO();
                    nominaEmpleado.setNombre(prenomina.getNombre());
                    nominaEmpleado.setRfc(prenomina.getRfc());

                    if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        Map<String, PercepcionDTO> percepciones = new HashMap<>();
                        PercepcionDTO percepcion = new PercepcionDTO(
                                prenomina.getClaveConcepto(),
                                prenomina.getDescripcionConcepto(),
                                prenomina.getImporte());
                        percepciones.put(prenomina.getClaveConcepto(),
                                percepcion);
                        nominaEmpleado.setPercepciones(percepciones);
                    }

                    if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        DeduccionDTO deduccion = new DeduccionDTO(
                                prenomina.getClaveConcepto(),
                                prenomina.getDescripcionConcepto(),
                                prenomina.getImporte());
                        Map<String, DeduccionDTO> deducciones = new HashMap<>();
                        deducciones.put(prenomina.getClaveConcepto(),
                                deduccion);
                        nominaEmpleado.setDeducciones(deducciones);
                    }

                    Map<String, NominaEmpleadoDTO> nominasEmpleados = new HashMap<>();
                    nominasEmpleados.put(nominaEmpleado.getRfc(),
                            nominaEmpleado);

                    UnidadResponsableDTOBuilder unidadResponsableBuilder = new UnidadResponsableDTOBuilder(
                            prenomina.getClaveCentroResponsabilidad(),
                            prenomina.getDescripcionCentroResponsabilidad())
                                    .setNominasEmpleados(nominasEmpleados);
                    UnidadResponsableDTO unidadResponsable = unidadResponsableBuilder
                            .createUnidadResponsableDTO();

                    Map<String, UnidadResponsableDTO> unidadesResponsables = new HashMap<>();
                    unidadesResponsables.put(
                            unidadResponsable.getNumeroUnidadResponsable(),
                            unidadResponsable);

                    ProgramaDTOBuilder programaBuilder = new ProgramaDTOBuilder(
                            idPrograma, prenomina.getPrograma(),
                            prenomina.getInicioPeriodo(),
                            prenomina.getFinPeriodo()).setUnidadesResponsables(
                                    unidadesResponsables);
                    ProgramaDTO programa = programaBuilder.createProgramaDTO();
                    programas.put(idPrograma, programa);
                } else {
                    ProgramaDTO programa = programas.get(idPrograma);
                    Map<String, UnidadResponsableDTO> unidadesResponsables = programa
                            .getUnidadesResponsables();

                    if (!unidadesResponsables.containsKey(
                            prenomina.getClaveCentroResponsabilidad())) {

                        NominaEmpleadoDTO nominaEmpleado = new NominaEmpleadoDTO();
                        nominaEmpleado.setNombre(prenomina.getNombre());
                        nominaEmpleado.setRfc(prenomina.getRfc());

                        if ("PERCEPCIONES"
                                .equalsIgnoreCase(prenomina.getTipo())) {
                            Map<String, PercepcionDTO> percepciones = new HashMap<>();
                            PercepcionDTO percepcion = new PercepcionDTO(
                                    prenomina.getClaveConcepto(),
                                    prenomina.getDescripcionConcepto(),
                                    prenomina.getImporte());
                            percepciones.put(prenomina.getClaveConcepto(),
                                    percepcion);
                            nominaEmpleado.setPercepciones(percepciones);
                        }

                        if ("DEDUCCIONES"
                                .equalsIgnoreCase(prenomina.getTipo())) {
                            DeduccionDTO deduccion = new DeduccionDTO(
                                    prenomina.getClaveConcepto(),
                                    prenomina.getDescripcionConcepto(),
                                    prenomina.getImporte());
                            Map<String, DeduccionDTO> deducciones = new HashMap<>();
                            deducciones.put(prenomina.getClaveConcepto(),
                                    deduccion);
                            nominaEmpleado.setDeducciones(deducciones);
                        }

                        Map<String, NominaEmpleadoDTO> nominasEmpleados = new HashMap<>();
                        nominasEmpleados.put(nominaEmpleado.getRfc(),
                                nominaEmpleado);

                        UnidadResponsableDTOBuilder unidadResponsableBuilder = new UnidadResponsableDTOBuilder(
                                prenomina.getClaveCentroResponsabilidad(),
                                prenomina.getDescripcionCentroResponsabilidad())
                                        .setNominasEmpleados(nominasEmpleados);
                        UnidadResponsableDTO unidadResponsable = unidadResponsableBuilder
                                .createUnidadResponsableDTO();
                        unidadesResponsables.put(
                                unidadResponsable.getNumeroUnidadResponsable(),
                                unidadResponsable);
                    } else {
                        UnidadResponsableDTO unidadResponsable = unidadesResponsables
                                .get(prenomina.getClaveCentroResponsabilidad());
                        Map<String, NominaEmpleadoDTO> nominasEmpleados = unidadResponsable
                                .getNominasEmpleados();

                        if (!nominasEmpleados.containsKey(prenomina.getRfc())) {
                            NominaEmpleadoDTO nominaEmpleado = new NominaEmpleadoDTO();
                            nominaEmpleado.setNombre(prenomina.getNombre());
                            nominaEmpleado.setRfc(prenomina.getRfc());

                            if ("PERCEPCIONES"
                                    .equalsIgnoreCase(prenomina.getTipo())) {
                                Map<String, PercepcionDTO> percepciones = new HashMap<>();
                                PercepcionDTO percepcion = new PercepcionDTO(
                                        prenomina.getClaveConcepto(),
                                        prenomina.getDescripcionConcepto(),
                                        prenomina.getImporte());
                                percepciones.put(prenomina.getClaveConcepto(),
                                        percepcion);
                                nominaEmpleado.setPercepciones(percepciones);
                            }

                            if ("DEDUCCIONES"
                                    .equalsIgnoreCase(prenomina.getTipo())) {
                                DeduccionDTO deduccion = new DeduccionDTO(
                                        prenomina.getClaveConcepto(),
                                        prenomina.getDescripcionConcepto(),
                                        prenomina.getImporte());
                                Map<String, DeduccionDTO> deducciones = new HashMap<>();
                                deducciones.put(prenomina.getClaveConcepto(),
                                        deduccion);
                                nominaEmpleado.setDeducciones(deducciones);
                            }

                            nominasEmpleados.put(nominaEmpleado.getRfc(),
                                    nominaEmpleado);
                        } else {
                            NominaEmpleadoDTO nominaEmpleado = nominasEmpleados
                                    .get(prenomina.getRfc());

                            if ("PERCEPCIONES"
                                    .equalsIgnoreCase(prenomina.getTipo())) {
                                if (nominaEmpleado.getPercepciones() != null) {
                                    Map<String, PercepcionDTO> percepciones = nominaEmpleado
                                            .getPercepciones();
                                    PercepcionDTO percepcion = new PercepcionDTO(
                                            prenomina.getClaveConcepto(),
                                            prenomina.getDescripcionConcepto(),
                                            prenomina.getImporte());
                                    percepciones.put(
                                            prenomina.getClaveConcepto(),
                                            percepcion);
                                } else {
                                    Map<String, PercepcionDTO> percepciones = new HashMap<>();
                                    PercepcionDTO percepcion = new PercepcionDTO(
                                            prenomina.getClaveConcepto(),
                                            prenomina.getDescripcionConcepto(),
                                            prenomina.getImporte());
                                    percepciones.put(
                                            prenomina.getClaveConcepto(),
                                            percepcion);
                                    nominaEmpleado
                                            .setPercepciones(percepciones);
                                }

                            }

                            if ("DEDUCCIONES"
                                    .equalsIgnoreCase(prenomina.getTipo())) {
                                if (nominaEmpleado.getDeducciones() != null) {
                                    Map<String, DeduccionDTO> deducciones = nominaEmpleado
                                            .getDeducciones();
                                    DeduccionDTO deduccion = new DeduccionDTO(
                                            prenomina.getClaveConcepto(),
                                            prenomina.getDescripcionConcepto(),
                                            prenomina.getImporte());
                                    deducciones.put(
                                            prenomina.getClaveConcepto(),
                                            deduccion);
                                } else {
                                    DeduccionDTO deduccion = new DeduccionDTO(
                                            prenomina.getClaveConcepto(),
                                            prenomina.getDescripcionConcepto(),
                                            prenomina.getImporte());
                                    Map<String, DeduccionDTO> deducciones = new HashMap<>();
                                    deducciones.put(
                                            prenomina.getClaveConcepto(),
                                            deduccion);
                                    nominaEmpleado.setDeducciones(deducciones);
                                }
                            }
                        }
                    }
                }
            }

            PrenominaDTO prenominaUno = prenominas.get(0);
            Date fechaPago = prenominaUno.getFechaPago();
            ProductoNominaDTO productoNomina;
            ProductoNominaDTOBuilder pnb = new ProductoNominaDTOBuilder(
                    idProductoNomina, fechaPago, programas)
                            //Firmas
                            .setNombreElaboro(prenominaUno.getElaboroNombre())
                            .setCargoElaboro(prenominaUno.getElaboroCargo())
                            .setNombreReviso(prenominaUno.getRevisoNombre())
                            .setCargoReviso(prenominaUno.getRevisoCargo())
                            .setNombreAutorizo(prenominaUno.getAutorizoNombre())
                            .setCargoAutorizo(prenominaUno.getAutorizoCargo());

            productoNomina = pnb.createProductoNominaDTO();

            return productoNomina;
        } else {
            return null;
        }
    }
}
