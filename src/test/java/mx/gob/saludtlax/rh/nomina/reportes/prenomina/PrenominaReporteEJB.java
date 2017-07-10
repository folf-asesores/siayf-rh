/*
 * PrenominaReporteEJB.java
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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.gob.saludtlax.rh.util.Configuracion;
import org.jboss.logging.Logger;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Stateless
public class PrenominaReporteEJB {

    private static final Logger LOGGER = Logger.getLogger(PrenominaReporteEJB.class.getName());

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager entityManager;

    private static final String USP_REPORTE_PRENOMINA
            = "CALL usp_reporte_prenomina(:idProductoNomina)";

    public ProductoNomina obtenerProductoNomina(Integer idProductoNomina) {
        List<PrenominaDTO> prenominas = consultarDatos(idProductoNomina);
        ProductoNomina productoNomina = convertirDatos(idProductoNomina, prenominas);
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
                prenomina.setClaveCentroResponsabilidad(claveCentroResponsabilidad);
            }

            if (obj[13] instanceof String) {
                String descripcionCentroResponsabilidad = (String) obj[13];
                prenomina.setDescripcionCentroResponsabilidad(descripcionCentroResponsabilidad);
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

    private ProductoNomina convertirDatos(Integer idProductoNomina, List<PrenominaDTO> prenominas) {
        if (prenominas != null && !prenominas.isEmpty()) {
            PrenominaDTO prenominaUno = prenominas.get(0);
            Date fechaPago = prenominaUno.getFechaPago();
            Map<Integer, Programa> programas = new HashMap<>();

            for (PrenominaDTO prenomina : prenominas) {
                Integer idPrograma = prenomina.getIdPrograma();
                if (!programas.containsKey(idPrograma)) {
                    Programa programa = new Programa();

                    programa.setIdPrograma(idPrograma);
                    programa.setPrograma(prenomina.getPrograma());
                    programa.setInicioPeriodo(prenomina.getInicioPeriodo());
                    programa.setFinPeriodo(prenomina.getFinPeriodo());

                    UnidadResponsable unidadResponsable = new UnidadResponsable();
                    unidadResponsable.setNumeroUnidadResponsable(prenomina.getClaveCentroResponsabilidad());
                    unidadResponsable.setUnidadResponsable(prenomina.getDescripcionCentroResponsabilidad());

                    Map<String, UnidadResponsable> unidadesResponsables = new HashMap<>();
                    unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);

                    NominaEmpleado nominaEmpleado = new NominaEmpleado();
                    nominaEmpleado.setNombre(prenomina.getNombre());
                    nominaEmpleado.setRfc(prenomina.getRfc());

                    if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        Map<String, Percepcion> percepciones = new HashMap<>();
                        Percepcion percepcion = new Percepcion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                        percepciones.put(prenomina.getClaveConcepto(), percepcion);
                        nominaEmpleado.setPercepciones(percepciones);
                    }

                    if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                        Deduccion deduccion = new Deduccion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                        Map<String, Deduccion> deducciones = new HashMap<>();
                        deducciones.put(prenomina.getClaveConcepto(), deduccion);
                        nominaEmpleado.setDeducciones(deducciones);
                    }

                    Map<String, NominaEmpleado> nominasEmpleados = new HashMap<>();
                    nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);
                    unidadResponsable.setNominasEmpleados(nominasEmpleados);
                    programa.setUnidadesResponsables(unidadesResponsables);
                    programas.put(idPrograma, programa);
                } else {
                    Programa programa = programas.get(idPrograma);
                    Map<String, UnidadResponsable> unidadesResponsables = programa.getUnidadesResponsables();

                    if (!unidadesResponsables.containsKey(prenomina.getClaveCentroResponsabilidad())) {
                        UnidadResponsable unidadResponsable = new UnidadResponsable();
                        unidadResponsable.setNumeroUnidadResponsable(prenomina.getClaveCentroResponsabilidad());
                        unidadResponsable.setUnidadResponsable(prenomina.getDescripcionCentroResponsabilidad());

                        unidadesResponsables.put(unidadResponsable.getNumeroUnidadResponsable(), unidadResponsable);

                        NominaEmpleado nominaEmpleado = new NominaEmpleado();
                        nominaEmpleado.setNombre(prenomina.getNombre());
                        nominaEmpleado.setRfc(prenomina.getRfc());

                        if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                            Map<String, Percepcion> percepciones = new HashMap<>();
                            Percepcion percepcion = new Percepcion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                            percepciones.put(prenomina.getClaveConcepto(), percepcion);
                            nominaEmpleado.setPercepciones(percepciones);
                        }

                        if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                            Deduccion deduccion = new Deduccion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                            Map<String, Deduccion> deducciones = new HashMap<>();
                            deducciones.put(prenomina.getClaveConcepto(), deduccion);
                            nominaEmpleado.setDeducciones(deducciones);
                        }

                        Map<String, NominaEmpleado> nominasEmpleados = new HashMap<>();
                        nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);
                        unidadResponsable.setNominasEmpleados(nominasEmpleados);
                    } else {
                        UnidadResponsable unidadResponsable = unidadesResponsables.get(prenomina.getClaveCentroResponsabilidad());
                        Map<String, NominaEmpleado> nominasEmpleados = unidadResponsable.getNominasEmpleados();

                        if (!nominasEmpleados.containsKey(prenomina.getRfc())) {
                            NominaEmpleado nominaEmpleado = new NominaEmpleado();
                            nominaEmpleado.setNombre(prenomina.getNombre());
                            nominaEmpleado.setRfc(prenomina.getRfc());

                            if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                Map<String, Percepcion> percepciones = new HashMap<>();
                                Percepcion percepcion = new Percepcion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                nominaEmpleado.setPercepciones(percepciones);
                            }

                            if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                Deduccion deduccion = new Deduccion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                Map<String, Deduccion> deducciones = new HashMap<>();
                                deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                nominaEmpleado.setDeducciones(deducciones);
                            }

                            nominasEmpleados.put(nominaEmpleado.getRfc(), nominaEmpleado);
                        } else {
                            NominaEmpleado nominaEmpleado = nominasEmpleados.get(prenomina.getRfc());

                            if ("PERCEPCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                if (nominaEmpleado.getPercepciones() != null) {
                                    Map<String, Percepcion> percepciones = nominaEmpleado.getPercepciones();
                                    Percepcion percepcion = new Percepcion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                } else {
                                    Map<String, Percepcion> percepciones = new HashMap<>();
                                    Percepcion percepcion = new Percepcion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    percepciones.put(prenomina.getClaveConcepto(), percepcion);
                                    nominaEmpleado.setPercepciones(percepciones);
                                }

                            }

                            if ("DEDUCCIONES".equalsIgnoreCase(prenomina.getTipo())) {
                                if(nominaEmpleado.getDeducciones() != null) {
                                    Map<String, Deduccion> deducciones = nominaEmpleado.getDeducciones();
                                    Deduccion deduccion = new Deduccion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                } else {
                                    Deduccion deduccion = new Deduccion(prenomina.getClaveConcepto(), prenomina.getDescripcionConcepto(), prenomina.getImporte());
                                    Map<String, Deduccion> deducciones = new HashMap<>();
                                    deducciones.put(prenomina.getClaveConcepto(), deduccion);
                                    nominaEmpleado.setDeducciones(deducciones);
                                }
                            }
                        }
                    }
                }
            }

            ProductoNomina productoNomina;
            ProductoNominaBuilder pnb = new ProductoNominaBuilder(idProductoNomina, fechaPago, programas);

            //Firmas
            pnb.setNombreElaboro(prenominaUno.getElaboroNombre());
            pnb.setCargoElaboro(prenominaUno.getElaboroCargo());
            pnb.setNombreReviso(prenominaUno.getRevisoNombre());
            pnb.setCargoReviso(prenominaUno.getRevisoCargo());
            pnb.setNombreAutorizo(prenominaUno.getAutorizoNombre());
            pnb.setCargoAutorizo(prenominaUno.getAutorizoCargo());

            productoNomina = pnb.createProductoNomina();

            return productoNomina;
        } else {
            return null;
        }
    }
}
