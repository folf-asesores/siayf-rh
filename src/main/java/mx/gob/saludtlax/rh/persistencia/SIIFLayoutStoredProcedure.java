/*
 * SIIFLayoutStoredProcedure.java
 * Creado el 30/06/2016 04:33:07 AM
 *
 */

package mx.gob.saludtlax.rh.persistencia;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.gob.saludtlax.rh.siif.layout.DatosLaboralesDTO;
import mx.gob.saludtlax.rh.siif.layout.DatosPersonalesDTO;
import mx.gob.saludtlax.rh.siif.layout.DetalleNominaDTO;
import mx.gob.saludtlax.rh.siif.layout.DetallePagoNominaDTO;
import mx.gob.saludtlax.rh.util.Configuracion;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFLayoutStoredProcedure {

    private static final String OBTENER_DATOS_PERSONALES = "call usp_obtener_datos_personales_siif(?)";
    private static final String OBTENER_DATOS_LABORALES = "call usp_obtener_datos_laborales_siif(?)";
    private static final String OBTENER_DATOS_LABORALES_NOM = "call usp_obtener_datos_laborales_siif_hom(?,?)";
    private static final String OBTENER_DETALLE_NOMINA = "call usp_obtener_detalle_nomina_siif(?, ?)";
    private static final String OBTENER_DETALLE_PAGO_NOMINA = "call usp_obtener_detalle_pago_nomina_siif(?, ?)";

    private static final String OBTENER_DATOS_PERSONALES_CONTRATO = "call usp_obtener_datos_personales_siif_contrato(?)";
    private static final String OBTENER_DATOS_LABORALES_CONTRATO = "call usp_obtener_datos_laborales_siif_contrato(?)";
    private static final String OBTENER_DETALLE_CONTRATO = "call usp_obtener_detalle_nomina_siif_contrato(?, ?)";
    private static final String OBTENER_DETALLE_PAGO_CONTRATO = "call usp_obtener_detalle_pago_nomina_siif_contrato(?, ?)";

    private static final String OBTENER_DATOS_PERSONALES_FINAL = "call usp_obtener_datos_personales_siif_final(?,?,?)";
    private static final String OBTENER_DATOS_LABORALES_FINAL = "call usp_obtener_datos_laborales_siif_final(?,?,?)";
    private static final String OBTENER_DETALLE_NOMINA_FINAL = "call usp_obtener_detalle_nomina_siif_final(?, ?, ?)";
    private static final String OBTENER_DETALLE_PAGO_NOMINA_FINAL = "call usp_obtener_detalle_pago_nomina_siif_final(?, ?, ?)";

    private static final String OBTENER_DETALLE_PAGO_NOMINA_610 = "call usp_obtener_detalle_pago_nomina_610_siif(?, ?)";
    private static final String OBTENER_DETALLE_NOMINA_610 = "call usp_obtener_detalle_nomina_610_siif(?, ?)";

    private static final String OBTENER_DATOS_PERSONALES_RH = "call usp_obtener_datos_personales_rh(?)";
    private static final String OBTENER_DATOS_LABORALES_RH = "call usp_obtener_datos_laborales_rh(?)";
    private static final String OBTENER_DETALLE_NOMINA_RH = "call usp_obtener_detalle_nomina_rh(?)";
    private static final String OBTENER_DETALLE_PAGO_NOMINA_RH = "call usp_obtener_detalle_pago_nomina_rh(?)";

    private static final String OBTENER_DATOS_PERSONALES_RH_CONT = "call usp_obtener_datos_personales_rh_cont(?,?)";
    private static final String OBTENER_DATOS_LABORALES_RH_CONT = "call usp_obtener_datos_laborales_rh_cont(?)";
    private static final String OBTENER_DETALLE_NOMINA_RH_CONT = "call usp_obtener_detalle_nomina_rh_cont(?)";
    private static final String OBTENER_DETALLE_PAGO_NOMINA_RH_CONT = "call usp_obtener_detalle_pago_nomina_rh_cont(?)";

    private static final String OBTENER_DATOS_PERSONALES_RH_CONTRATO = "call usp_obtener_datos_personales_rh_contrato(?,?)";

    @PersistenceContext(unitName = Configuracion.UNIDAD_PERSISTENCIA)
    private EntityManager em;

    public List<DatosPersonalesDTO> obtenerDatosPersonales(int idEncabezado) {
        //LOGGER.debug("idEncabezado:::" + +idEncabezado);
        Query query = em.createNativeQuery(OBTENER_DATOS_PERSONALES);
        query.setParameter(1, idEncabezado);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosPersonalesDTO> datosPersonalesDTOs = new ArrayList<>();
        for (Object[] obj : result) {
            DatosPersonalesDTO dato = new DatosPersonalesDTO();

            dato.setIdEmpleadoDatosPersonales((Integer) obj[0]);
            dato.setRfc((String) obj[1]);
            dato.setApellidoPaterno((String) obj[2]);
            dato.setApellidoMaterno((String) obj[3]);
            dato.setNombre((String) obj[4]);
            dato.setFechaNacimineto((Date) obj[5]);
            dato.setSexo(obj[6] != null ? ((String) obj[6]).charAt(0) : ' ');
            //            dato.setIdLocalidad(obj[7] != null ? ((BigInteger) obj[7]).intValue():0);
            //            dato.setIdColonia(obj[8] != null ? ((BigInteger) obj[8]).intValue() : 0);
            dato.setIdLocalidad((String) obj[7]);
            dato.setIdColonia((String) obj[8]);
            dato.setCalle(obj[9] != null ? ((String) obj[9]) : "NULL");
            dato.setNumeroExterior(obj[10] != null ? ((String) obj[10]) : "NULL");
            dato.setNumeroInterior(obj[11] != null ? ((String) obj[11]) : "NULL");
            dato.setCodigoPostal(obj[12] != null ? ((String) obj[12]) : "NULL");
            dato.setTelefono(obj[13] != null ? ((String) obj[13]) : "NULL");
            dato.setIdEstadoEmpleado(obj[14] != null ? ((String) obj[14]).charAt(0) : 'A');
            dato.setIdNomina((Integer) obj[15]);

            datosPersonalesDTOs.add(dato);
        }

        return datosPersonalesDTOs;
    }

    public List<DatosLaboralesDTO> generarDatosLaborales(int idEncabezado) {
        Query query = em.createNativeQuery(OBTENER_DATOS_LABORALES);
        query.setParameter(1, idEncabezado);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosLaboralesDTO> datosLaboralesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosLaboralesDTO dato = new DatosLaboralesDTO();

            Integer idEmpleadoDatosLaborales = obj[0] != null ? (obj[0] instanceof BigInteger ? ((BigInteger) obj[0]).intValue() : (Integer) obj[0]) : 0;

            dato.setIdEmpleadoDatosLaborales(idEmpleadoDatosLaborales);
            dato.setIdEmpleadoDatosPersonales((Integer) obj[1]);
            dato.setRfc((String) obj[2]);
            dato.setIdRfc(((Integer) obj[3]).intValue());
            dato.setIdPlaza((String) obj[4]);
            dato.setIdProyecto(((Integer) obj[5]).intValue());
            dato.setIdDependencia(((Integer) obj[6]).intValue());
            dato.setIdUnidadResponsable(((Integer) obj[7]).intValue());
            dato.setNombramiento(((String) obj[8]).charAt(0));
            dato.setIdPuesto((String) obj[9]);
            dato.setIdSindicato(((Integer) obj[10]).intValue());
            dato.setIdHabilitado(((Integer) obj[11]).intValue());
            dato.setFechaIngreso((Date) obj[12]);
            dato.setNoQuinquenios(((Integer) obj[13]).intValue());
            dato.setSueldoMensual((BigDecimal) obj[14]);
            dato.setPercepcionComplementaria((BigDecimal) obj[15]);
            dato.setDespensa((BigDecimal) obj[16]);
            dato.setIncentivoAhorro((BigDecimal) obj[17]);
            dato.setCompensacion((BigDecimal) obj[18]);
            dato.setQuinquenio((BigDecimal) obj[19]);
            dato.setNoCuenta((String) obj[20]);
            dato.setPolicia(((Integer) obj[21]).intValue());
            dato.setIdFuenteFinanciamiento(((Integer) obj[22]).intValue());
            dato.setIdSubfuentefinanciamiento(((Integer) obj[23]).intValue());
            dato.setIdTipoRecurso((Integer) obj[24]);
            //dato.setIdEstadoEmpleado(((String) obj[25]).charAt(0));!= null ? ((String) obj[14]).charAt(0) : 'A')
            dato.setIdEstadoEmpleado('A');
            dato.setIdNomina(((Integer) obj[26]).intValue());

            datosLaboralesDTOs.add(dato);
        }

        return datosLaboralesDTOs;
    }

    public List<DatosLaboralesDTO> generarDatosLaboralesNombramiento(int idEncabezado, String nombramiento) {
        Query query = em.createNativeQuery(OBTENER_DATOS_LABORALES_NOM);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, nombramiento);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosLaboralesDTO> datosLaboralesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosLaboralesDTO dato = new DatosLaboralesDTO();

            Integer idEmpleadoDatosLaborales = obj[0] != null ? (obj[0] instanceof BigInteger ? ((BigInteger) obj[0]).intValue() : (Integer) obj[0]) : 0;

            dato.setIdEmpleadoDatosLaborales(idEmpleadoDatosLaborales);
            dato.setIdEmpleadoDatosPersonales((Integer) obj[1]);
            dato.setRfc((String) obj[2]);
            dato.setIdRfc(((Integer) obj[3]).intValue());
            dato.setIdPlaza((String) obj[4]);
            dato.setIdProyecto(((Integer) obj[5]).intValue());
            dato.setIdDependencia(((Integer) obj[6]).intValue());
            dato.setIdUnidadResponsable(((Integer) obj[7]).intValue());
            dato.setNombramiento(((String) obj[8]).charAt(0));
            dato.setIdPuesto((String) obj[9]);
            dato.setIdSindicato(((Integer) obj[10]).intValue());
            dato.setIdHabilitado(((Integer) obj[11]).intValue());
            dato.setFechaIngreso((Date) obj[12]);
            dato.setNoQuinquenios(((Integer) obj[13]).intValue());
            dato.setSueldoMensual((BigDecimal) obj[14]);
            dato.setPercepcionComplementaria((BigDecimal) obj[15]);
            dato.setDespensa((BigDecimal) obj[16]);
            dato.setIncentivoAhorro((BigDecimal) obj[17]);
            dato.setCompensacion((BigDecimal) obj[18]);
            dato.setQuinquenio((BigDecimal) obj[19]);
            dato.setNoCuenta((String) obj[20]);
            dato.setPolicia(((Integer) obj[21]).intValue());
            dato.setIdFuenteFinanciamiento(((Integer) obj[22]).intValue());
            dato.setIdSubfuentefinanciamiento(((Integer) obj[23]).intValue());
            dato.setIdTipoRecurso((Integer) obj[24]);
            dato.setIdEstadoEmpleado(((String) obj[25]).charAt(0));
            dato.setIdNomina(((Integer) obj[26]).intValue());

            datosLaboralesDTOs.add(dato);
        }

        return datosLaboralesDTOs;
    }

    public List<DetalleNominaDTO> generarDetalleNomina(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_NOMINA);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        //        Query query = session.createSQLQuery("CALL usp_obtener_encabezados(:anyo, :periodo)")
        //				.setParameter("anyo", anyo)
        //				.setParameter("periodo", periodo);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetalleNominaDTO> datosDetalleNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetalleNominaDTO dato = new DetalleNominaDTO();

            dato.setIdDetalleNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpleadoDatosLaborales((Integer) obj[2]);
            dato.setIdConceptoNomina((String) obj[3]);
            dato.setImporte((BigDecimal) obj[4]);
            dato.setIdOrigenCalculo(((String) obj[5]).charAt(0));

            datosDetalleNomina.add(dato);
        }

        return datosDetalleNomina;
    }

    public List<DetallePagoNominaDTO> generarDetallePagoNomina(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_PAGO_NOMINA);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetallePagoNominaDTO> datosDetallePagoNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetallePagoNominaDTO dato = new DetallePagoNominaDTO();

            dato.setIdPagoNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpeladoDatosLaborales((Integer) obj[2]);
            dato.setNoChequeCuenta((String) obj[3]);
            dato.setPercepciones((BigDecimal) obj[4]);
            dato.setDeducciones((BigDecimal) obj[5]);
            dato.setNeto((BigDecimal) obj[6]);

            datosDetallePagoNomina.add(dato);
        }

        return datosDetallePagoNomina;
    }

    /**
     * Aqui inician los de Contrato
     **/

    public List<DatosPersonalesDTO> obtenerDatosPersonalesContrato(int idEncabezado) {
        Query query = em.createNativeQuery(OBTENER_DATOS_PERSONALES_CONTRATO);
        System.out.println();
        query.setParameter(1, idEncabezado);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosPersonalesDTO> datosPersonalesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosPersonalesDTO dato = new DatosPersonalesDTO();

            dato.setIdEmpleadoDatosPersonales((Integer) obj[0]);
            dato.setRfc((String) obj[1]);
            dato.setApellidoPaterno((String) obj[2]);
            dato.setApellidoMaterno((String) obj[3]);
            dato.setNombre((String) obj[4]);
            dato.setFechaNacimineto((Date) obj[5]);
            dato.setSexo(obj[6] != null ? ((String) obj[6]).charAt(0) : ' ');
            //            dato.setIdLocalidad(obj[7] != null ? ((BigInteger) obj[7]).intValue():0);
            //            dato.setIdColonia(obj[8] != null ? ((BigInteger) obj[8]).intValue() : 0);
            dato.setIdLocalidad((String) obj[7]);
            dato.setIdColonia((String) obj[8]);
            dato.setCalle((String) obj[9]);
            dato.setNumeroExterior((String) obj[10]);
            dato.setNumeroInterior((String) obj[11]);
            dato.setCodigoPostal((String) obj[12]);
            dato.setTelefono((String) obj[13]);
            dato.setIdEstadoEmpleado(obj[14] != null ? ((String) obj[14]).charAt(0) : 'A');

            datosPersonalesDTOs.add(dato);
        }

        return datosPersonalesDTOs;
    }

    public List<DatosLaboralesDTO> generarDatosLaboralesContrato(int idEncabezado) {
        Query query = em.createNativeQuery(OBTENER_DATOS_LABORALES_CONTRATO);
        query.setParameter(1, idEncabezado);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosLaboralesDTO> datosLaboralesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosLaboralesDTO dato = new DatosLaboralesDTO();

            Integer idEmpleadoDatosLaborales = obj[0] != null ? (obj[0] instanceof BigInteger ? ((BigInteger) obj[0]).intValue() : (Integer) obj[0]) : 0;

            dato.setIdEmpleadoDatosLaborales(idEmpleadoDatosLaborales);
            dato.setIdEmpleadoDatosPersonales((Integer) obj[1]);
            dato.setRfc((String) obj[2]);
            dato.setIdRfc(((Integer) obj[3]).intValue());
            dato.setIdPlaza((String) obj[4]);
            dato.setIdProyecto(((Integer) obj[5]).intValue());
            dato.setIdDependencia(((Integer) obj[6]).intValue());
            dato.setIdUnidadResponsable(((Integer) obj[7]).intValue());
            dato.setNombramiento(((String) obj[8]).charAt(0));
            dato.setIdPuesto((String) obj[9]);
            dato.setIdSindicato(((Integer) obj[10]).intValue());
            dato.setIdHabilitado(((Integer) obj[11]).intValue());
            dato.setFechaIngreso((Date) obj[12]);
            dato.setNoQuinquenios(((Integer) obj[13]).intValue());
            dato.setSueldoMensual((BigDecimal) obj[14]);
            dato.setPercepcionComplementaria((BigDecimal) obj[15]);
            dato.setDespensa((BigDecimal) obj[16]);
            dato.setIncentivoAhorro((BigDecimal) obj[17]);
            dato.setCompensacion((BigDecimal) obj[18]);
            dato.setQuinquenio((BigDecimal) obj[19]);
            dato.setNoCuenta((String) obj[20]);
            dato.setPolicia(((Integer) obj[21]).intValue());
            dato.setIdFuenteFinanciamiento(((Integer) obj[22]).intValue());
            dato.setIdSubfuentefinanciamiento(((Integer) obj[23]).intValue());
            dato.setIdTipoRecurso((Integer) obj[24]);
            dato.setIdEstadoEmpleado(((String) obj[25]).charAt(0));
            dato.setIdNomina(((Integer) obj[26]).intValue());

            datosLaboralesDTOs.add(dato);
        }

        return datosLaboralesDTOs;
    }

    public List<DetalleNominaDTO> generarDetalleContrato(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_CONTRATO);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetalleNominaDTO> datosDetalleNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetalleNominaDTO dato = new DetalleNominaDTO();

            dato.setIdDetalleNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpleadoDatosLaborales((Integer) obj[2]);
            dato.setIdConceptoNomina((String) obj[3]);
            dato.setImporte((BigDecimal) obj[4]);
            dato.setIdOrigenCalculo(((String) obj[5]).charAt(0));

            datosDetalleNomina.add(dato);
        }

        return datosDetalleNomina;
    }

    public List<DetallePagoNominaDTO> generarDetallePagoContrato(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_PAGO_CONTRATO);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetallePagoNominaDTO> datosDetallePagoNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetallePagoNominaDTO dato = new DetallePagoNominaDTO();

            dato.setIdPagoNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpeladoDatosLaborales((Integer) obj[2]);
            dato.setNoChequeCuenta((String) obj[3]);
            dato.setPercepciones((BigDecimal) obj[4]);
            dato.setDeducciones((BigDecimal) obj[5]);
            dato.setNeto((BigDecimal) obj[6]);

            datosDetallePagoNomina.add(dato);
        }

        return datosDetallePagoNomina;
    }

    /**
     * Aqui inician los finales
     **/

    public List<DatosPersonalesDTO> obtenerDatosPersonalesFinal(int idCuentaBancaria, int idTipoNomina, String idTipoEmisionNomina) {
        Query query = em.createNativeQuery(OBTENER_DATOS_PERSONALES_FINAL);
        System.out.println();
        query.setParameter(1, idCuentaBancaria);
        query.setParameter(2, idTipoNomina);
        query.setParameter(3, idTipoEmisionNomina);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosPersonalesDTO> datosPersonalesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosPersonalesDTO dato = new DatosPersonalesDTO();

            dato.setIdEmpleadoDatosPersonales((Integer) obj[0]);
            dato.setRfc((String) obj[1]);
            dato.setApellidoPaterno((String) obj[2]);
            dato.setApellidoMaterno((String) obj[3]);
            dato.setNombre((String) obj[4]);
            dato.setFechaNacimineto((Date) obj[5]);
            dato.setSexo(obj[6] != null ? ((String) obj[6]).charAt(0) : ' ');
            //            dato.setIdLocalidad(obj[7] != null ? ((BigInteger) obj[7]).intValue():0);
            //            dato.setIdColonia(obj[8] != null ? ((BigInteger) obj[8]).intValue() : 0);
            dato.setIdLocalidad((String) obj[7]);
            dato.setIdColonia((String) obj[8]);
            dato.setCalle((String) obj[9]);
            dato.setNumeroExterior((String) obj[10]);
            dato.setNumeroInterior((String) obj[11]);
            dato.setCodigoPostal((String) obj[12]);
            dato.setTelefono((String) obj[13]);
            dato.setIdEstadoEmpleado(obj[14] != null ? ((String) obj[14]).charAt(0) : 'A');

            datosPersonalesDTOs.add(dato);
        }

        return datosPersonalesDTOs;
    }

    public List<DatosLaboralesDTO> generarDatosLaboralesFinal(int idCuentaBancaria, int idTipoNomina, String idTipoEmisionNomina) {
        Query query = em.createNativeQuery(OBTENER_DATOS_LABORALES_FINAL);
        query.setParameter(1, idCuentaBancaria);
        query.setParameter(2, idTipoNomina);
        query.setParameter(3, idTipoEmisionNomina);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosLaboralesDTO> datosLaboralesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosLaboralesDTO dato = new DatosLaboralesDTO();

            Integer idEmpleadoDatosLaborales = obj[0] != null ? (obj[0] instanceof BigInteger ? ((BigInteger) obj[0]).intValue() : (Integer) obj[0]) : 0;

            dato.setIdEmpleadoDatosLaborales(idEmpleadoDatosLaborales);
            dato.setIdEmpleadoDatosPersonales((Integer) obj[1]);
            dato.setRfc((String) obj[2]);
            dato.setIdRfc(((Integer) obj[3]).intValue());
            dato.setIdPlaza((String) obj[4]);
            dato.setIdProyecto(((Integer) obj[5]).intValue());
            dato.setIdDependencia(((Integer) obj[6]).intValue());
            dato.setIdUnidadResponsable(((Integer) obj[7]).intValue());
            dato.setNombramiento(((String) obj[8]).charAt(0));
            dato.setIdPuesto((String) obj[9]);
            dato.setIdSindicato(((Integer) obj[10]).intValue());
            dato.setIdHabilitado(((Integer) obj[11]).intValue());
            dato.setFechaIngreso((Date) obj[12]);
            dato.setNoQuinquenios(((Integer) obj[13]).intValue());
            dato.setSueldoMensual((BigDecimal) obj[14]);
            dato.setPercepcionComplementaria((BigDecimal) obj[15]);
            dato.setDespensa((BigDecimal) obj[16]);
            dato.setIncentivoAhorro((BigDecimal) obj[17]);
            dato.setCompensacion((BigDecimal) obj[18]);
            dato.setQuinquenio((BigDecimal) obj[19]);
            dato.setNoCuenta((String) obj[20]);
            dato.setPolicia(((Integer) obj[21]).intValue());
            dato.setIdFuenteFinanciamiento(((Integer) obj[22]).intValue());
            dato.setIdSubfuentefinanciamiento(((Integer) obj[23]).intValue());
            dato.setIdTipoRecurso((Integer) obj[24]);
            dato.setIdEstadoEmpleado(((String) obj[25]).charAt(0));
            dato.setIdNomina(((Integer) obj[26]).intValue());

            datosLaboralesDTOs.add(dato);
        }

        return datosLaboralesDTOs;
    }

    public List<DetalleNominaDTO> generarDetalleNominaFinal(int idCuentaBancaria, int idTipoNomina, String idTipoEmisionNomina) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_NOMINA_FINAL);
        query.setParameter(1, idCuentaBancaria);
        query.setParameter(2, idTipoNomina);
        query.setParameter(3, idTipoEmisionNomina);

        //        Query query = session.createSQLQuery("CALL usp_obtener_encabezados(:anyo, :periodo)")
        //				.setParameter("anyo", anyo)
        //				.setParameter("periodo", periodo);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetalleNominaDTO> datosDetalleNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetalleNominaDTO dato = new DetalleNominaDTO();

            dato.setIdDetalleNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpleadoDatosLaborales((Integer) obj[2]);
            dato.setIdConceptoNomina((String) obj[3]);
            dato.setImporte((BigDecimal) obj[4]);
            dato.setIdOrigenCalculo(((String) obj[5]).charAt(0));

            datosDetalleNomina.add(dato);
        }

        return datosDetalleNomina;
    }

    public List<DetallePagoNominaDTO> generarDetallePagoNominaFinal(int idCuentaBancaria, int idTipoNomina, String idTipoEmisionNomina) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_PAGO_NOMINA_FINAL);
        query.setParameter(1, idCuentaBancaria);
        query.setParameter(2, idTipoNomina);
        query.setParameter(3, idTipoEmisionNomina);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetallePagoNominaDTO> datosDetallePagoNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetallePagoNominaDTO dato = new DetallePagoNominaDTO();

            dato.setIdPagoNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpeladoDatosLaborales((Integer) obj[2]);
            dato.setNoChequeCuenta((String) obj[3]);
            dato.setPercepciones((BigDecimal) obj[4]);
            dato.setDeducciones((BigDecimal) obj[5]);
            dato.setNeto((BigDecimal) obj[6]);

            datosDetallePagoNomina.add(dato);
        }

        return datosDetallePagoNomina;
    }

    public List<DetalleNominaDTO> generarDetalleNomina610(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_NOMINA_610);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        //        Query query = session.createSQLQuery("CALL usp_obtener_encabezados(:anyo, :periodo)")
        //				.setParameter("anyo", anyo)
        //				.setParameter("periodo", periodo);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetalleNominaDTO> datosDetalleNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetalleNominaDTO dato = new DetalleNominaDTO();

            dato.setIdDetalleNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpleadoDatosLaborales((Integer) obj[2]);
            dato.setIdConceptoNomina((String) obj[3]);
            dato.setImporte((BigDecimal) obj[4]);
            dato.setIdOrigenCalculo(((String) obj[5]).charAt(0));

            datosDetalleNomina.add(dato);
        }

        return datosDetalleNomina;
    }

    public List<DetallePagoNominaDTO> generarDetallePagoNomina610(int idEncabezado, int idBitacora) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_PAGO_NOMINA_610);
        query.setParameter(1, idEncabezado);
        query.setParameter(2, idBitacora);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetallePagoNominaDTO> datosDetallePagoNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetallePagoNominaDTO dato = new DetallePagoNominaDTO();

            dato.setIdPagoNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpeladoDatosLaborales((Integer) obj[2]);
            dato.setNoChequeCuenta((String) obj[3]);
            dato.setPercepciones((BigDecimal) obj[4]);
            dato.setDeducciones((BigDecimal) obj[5]);
            dato.setNeto((BigDecimal) obj[6]);

            datosDetallePagoNomina.add(dato);
        }

        return datosDetallePagoNomina;
    }

    public List<DatosPersonalesDTO> obtenerDatosPersonalesRhCont(int idProductoNomina, int idNomina) {
        //LOGGER.debug("idEncabezado:::" + +idEncabezado);
        Query query = em.createNativeQuery(OBTENER_DATOS_PERSONALES_RH_CONTRATO);
        query.setParameter(1, idProductoNomina);
        query.setParameter(2, idNomina);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosPersonalesDTO> datosPersonalesDTOs = new ArrayList<>();
        for (Object[] obj : result) {
            DatosPersonalesDTO dato = new DatosPersonalesDTO();

            dato.setIdEmpleadoDatosPersonales((Integer) obj[0]);
            dato.setRfc((String) obj[1]);
            dato.setApellidoPaterno((String) obj[2]);
            dato.setApellidoMaterno((String) obj[3]);
            dato.setNombre((String) obj[4]);
            dato.setFechaNacimineto((Date) obj[5]);
            dato.setSexo(obj[6] != null ? ((String) obj[6]).charAt(0) : ' ');
            //            dato.setIdLocalidad(obj[7] != null ? ((BigInteger) obj[7]).intValue():0);
            //            dato.setIdColonia(obj[8] != null ? ((BigInteger) obj[8]).intValue() : 0);
            dato.setIdLocalidad((String) obj[7]);
            dato.setIdColonia((String) obj[8]);
            dato.setCalle(obj[9] != null ? ((String) obj[9]) : "NULL");
            dato.setNumeroExterior(obj[10] != null ? ((String) obj[10]) : "NULL");
            dato.setNumeroInterior(obj[11] != null ? ((String) obj[11]) : "NULL");
            dato.setCodigoPostal(obj[12] != null ? ((String) obj[12]) : "NULL");
            dato.setTelefono(obj[13] != null ? ((String) obj[13]) : "NULL");
            dato.setIdEstadoEmpleado(obj[14] != null ? ((String) obj[14]).charAt(0) : 'A');
            dato.setIdNomina((Integer) obj[15]);

            datosPersonalesDTOs.add(dato);
        }

        return datosPersonalesDTOs;
    }

    public List<DatosLaboralesDTO> generarDatosLaboralesRH(int idProductoNomina) {
        Query query = em.createNativeQuery(OBTENER_DATOS_LABORALES_RH);
        query.setParameter(1, idProductoNomina);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosLaboralesDTO> datosLaboralesDTOs = new ArrayList<>();

        for (Object[] obj : result) {
            DatosLaboralesDTO dato = new DatosLaboralesDTO();

            Integer idEmpleadoDatosLaborales = obj[0] != null ? (obj[0] instanceof BigInteger ? ((BigInteger) obj[0]).intValue() : (Integer) obj[0]) : 0;

            dato.setIdEmpleadoDatosLaborales(idEmpleadoDatosLaborales);
            dato.setIdEmpleadoDatosPersonales((Integer) obj[1]);
            dato.setRfc((String) obj[2]);
            dato.setIdRfc(((Integer) obj[3]).intValue());
            dato.setIdPlaza((String) obj[4]);
            dato.setIdProyecto(((Integer) obj[5]).intValue());
            dato.setIdDependencia(((Integer) obj[6]).intValue());
            dato.setIdUnidadResponsable(((Integer) obj[7]).intValue());
            dato.setNombramiento(((String) obj[8]).charAt(0));
            dato.setIdPuesto((String) obj[9]);
            dato.setIdSindicato(((Integer) obj[10]).intValue());
            dato.setIdHabilitado(((Integer) obj[11]).intValue());
            dato.setFechaIngreso((Date) obj[12]);
            dato.setNoQuinquenios(((Integer) obj[13]).intValue());
            dato.setSueldoMensual((BigDecimal) obj[14]);
            dato.setPercepcionComplementaria((BigDecimal) obj[15]);
            dato.setDespensa((BigDecimal) obj[16]);
            dato.setIncentivoAhorro((BigDecimal) obj[17]);
            dato.setCompensacion((BigDecimal) obj[18]);
            dato.setQuinquenio((BigDecimal) obj[19]);
            dato.setNoCuenta((String) obj[20]);
            dato.setPolicia(((Integer) obj[21]).intValue());
            dato.setIdFuenteFinanciamiento(((Integer) obj[22]).intValue());
            dato.setIdSubfuentefinanciamiento(((Integer) obj[23]).intValue());
            dato.setIdTipoRecurso((Integer) obj[24]);
            dato.setIdEstadoEmpleado(((String) obj[25]).charAt(0));
            dato.setIdNomina(((Integer) obj[26]).intValue());

            datosLaboralesDTOs.add(dato);
        }

        return datosLaboralesDTOs;
    }

    public List<DetalleNominaDTO> generarDetalleNominaRH(int idProductoNomina) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_NOMINA_RH);
        query.setParameter(1, idProductoNomina);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetalleNominaDTO> datosDetalleNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetalleNominaDTO dato = new DetalleNominaDTO();

            dato.setIdDetalleNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpleadoDatosLaborales((Integer) obj[2]);
            dato.setIdConceptoNomina((String) obj[3]);
            dato.setImporte((BigDecimal) obj[4]);
            dato.setIdOrigenCalculo(((String) obj[5]).charAt(0));

            datosDetalleNomina.add(dato);
        }

        return datosDetalleNomina;
    }

    public List<DetallePagoNominaDTO> generarDetallePagoNominaRH(int idProductoNomina) {
        Query query = em.createNativeQuery(OBTENER_DETALLE_PAGO_NOMINA_RH);
        query.setParameter(1, idProductoNomina);

        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DetallePagoNominaDTO> datosDetallePagoNomina = new ArrayList<>();

        for (Object[] obj : result) {
            DetallePagoNominaDTO dato = new DetallePagoNominaDTO();

            dato.setIdPagoNomina(((BigInteger) obj[0]).intValue());
            dato.setIdNomina(obj[1] instanceof BigInteger ? ((BigInteger) obj[1]).intValue() : (Integer) obj[1]);
            dato.setIdEmpeladoDatosLaborales((Integer) obj[2]);
            dato.setNoChequeCuenta((String) obj[3]);
            dato.setPercepciones((BigDecimal) obj[4]);
            dato.setDeducciones((BigDecimal) obj[5]);
            dato.setNeto((BigDecimal) obj[6]);

            datosDetallePagoNomina.add(dato);
        }

        return datosDetallePagoNomina;
    }

    public List<DatosPersonalesDTO> obtenerDatosPersonalesRH(int idProductoNomina, int idNomina) {
        //LOGGER.debug("idEncabezado:::" + +idEncabezado);
        Query query = em.createNativeQuery(OBTENER_DATOS_PERSONALES_RH_CONT);
        query.setParameter(1, idProductoNomina);
        query.setParameter(2, idNomina);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();
        List<DatosPersonalesDTO> datosPersonalesDTOs = new ArrayList<>();
        for (Object[] obj : result) {
            DatosPersonalesDTO dato = new DatosPersonalesDTO();

            dato.setIdEmpleadoDatosPersonales((Integer) obj[0]);
            dato.setRfc((String) obj[1]);
            dato.setApellidoPaterno((String) obj[2]);
            dato.setApellidoMaterno((String) obj[3]);
            dato.setNombre((String) obj[4]);
            dato.setFechaNacimineto((Date) obj[5]);
            dato.setSexo(obj[6] != null ? ((String) obj[6]).charAt(0) : ' ');
            //            dato.setIdLocalidad(obj[7] != null ? ((BigInteger) obj[7]).intValue():0);
            //            dato.setIdColonia(obj[8] != null ? ((BigInteger) obj[8]).intValue() : 0);
            dato.setIdLocalidad((String) obj[7]);
            dato.setIdColonia((String) obj[8]);
            dato.setCalle(obj[9] != null ? ((String) obj[9]) : "NULL");
            dato.setNumeroExterior(obj[10] != null ? ((String) obj[10]) : "NULL");
            dato.setNumeroInterior(obj[11] != null ? ((String) obj[11]) : "NULL");
            dato.setCodigoPostal(obj[12] != null ? ((String) obj[12]) : "NULL");
            dato.setTelefono(obj[13] != null ? ((String) obj[13]) : "NULL");
            dato.setIdEstadoEmpleado(obj[14] != null ? ((String) obj[14]).charAt(0) : 'A');
            dato.setIdNomina((Integer) obj[15]);

            datosPersonalesDTOs.add(dato);
        }

        return datosPersonalesDTOs;
    }
}