/*
 * SIIFLayoutService.java
 * Creado el 30/06/2016 05:53:30 AM
 *
 */

package mx.gob.saludtlax.rh.siif.layout;

import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.SIIFLayoutStoredProcedure;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SIIFLayoutService {
    @Inject
    private SIIFLayoutStoredProcedure sp;

    protected List<DatosPersonalesDTO> generarDatosPersonales(
            int idEncabezado) {
        return sp.obtenerDatosPersonales(idEncabezado);
    }

    protected List<DatosLaboralesDTO> generarDatosLaborales(int idEncabezado) {
        return sp.generarDatosLaborales(idEncabezado);
    }

    protected List<DatosLaboralesDTO> generarDatosLaboralesNombramiento(
            int idEncabezado, String nombramiento) {
        return sp.generarDatosLaboralesNombramiento(idEncabezado, nombramiento);
    }

    protected List<DetalleNominaDTO> generarDetalleNomina(int idEncabezado,
            int idBitacora) {
        return sp.generarDetalleNomina(idEncabezado, idBitacora);
    }

    protected List<DetallePagoNominaDTO> generarDetallePagoNomina(
            int idEncabezado, int idBitacora) {
        return sp.generarDetallePagoNomina(idEncabezado, idBitacora);
    }
    // Generar Contrato

    protected List<DatosPersonalesDTO> generarDatosPersonalesContrato(
            int idEncabezado) {
        return sp.obtenerDatosPersonalesContrato(idEncabezado);
    }

    protected List<DatosLaboralesDTO> generarDatosLaboralesContrato(
            int idEncabezado) {
        return sp.generarDatosLaboralesContrato(idEncabezado);
    }

    protected List<DetalleNominaDTO> generarDetalleContrato(int idEncabezado,
            int idBitacora) {
        return sp.generarDetalleContrato(idEncabezado, idBitacora);
    }

    protected List<DetallePagoNominaDTO> generarDetallePagoContrato(
            int idEncabezado, int idBitacora) {
        return sp.generarDetallePagoContrato(idEncabezado, idBitacora);
    }

    // Generar Finales

    protected List<DatosPersonalesDTO> generarDatosPersonalesFinal(
            int idCuentaBancaria, int idTipoNomina,
            String idTipoEmisionNomina) {
        return sp.obtenerDatosPersonalesFinal(idCuentaBancaria, idTipoNomina,
                idTipoEmisionNomina);
    }

    protected List<DatosLaboralesDTO> generarDatosLaboralesFinal(
            int idCuentaBancaria, int idTipoNomina,
            String idTipoEmisionNomina) {
        return sp.generarDatosLaboralesFinal(idCuentaBancaria, idTipoNomina,
                idTipoEmisionNomina);
    }

    protected List<DetalleNominaDTO> generarDetalleNominaFinal(
            int idCuentaBancaria, int idTipoNomina,
            String idTipoEmisionNomina) {
        return sp.generarDetalleNominaFinal(idCuentaBancaria, idTipoNomina,
                idTipoEmisionNomina);
    }

    protected List<DetallePagoNominaDTO> generarDetallePagoNominaFinal(
            int idCuentaBancaria, int idTipoNomina,
            String idTipoEmisionNomina) {
        return sp.generarDetallePagoNominaFinal(idCuentaBancaria, idTipoNomina,
                idTipoEmisionNomina);
    }

    //Nomina 610
    protected List<DetalleNominaDTO> generarDetalleNomina610(int idEncabezado,
            int idBitacora) {
        return sp.generarDetalleNomina610(idEncabezado, idBitacora);
    }

    protected List<DetallePagoNominaDTO> generarDetallePagoNomina610(
            int idEncabezado, int idBitacora) {
        return sp.generarDetallePagoNomina610(idEncabezado, idBitacora);
    }

    //RH
    protected List<DatosPersonalesDTO> generarDatosPersonalesRH(
            int idProductoNomina, int idNomina) {
        return sp.obtenerDatosPersonalesRH(idProductoNomina, idNomina);
    }

    protected List<DatosLaboralesDTO> generarDatosLaboralesRH(
            int idProductoNomina) {
        return sp.generarDatosLaboralesRH(idProductoNomina);
    }

    protected List<DetalleNominaDTO> generarDetalleNominaRH(
            int idProductoNomina) {
        return sp.generarDetalleNominaRH(idProductoNomina);
    }

    protected List<DetallePagoNominaDTO> generarDetallePagoNominaRH(
            int idProductoNomina) {
        return sp.generarDetallePagoNominaRH(idProductoNomina);
    }

    protected List<DatosPersonalesDTO> generarDatosPersonalesRhCont(
            int idProductoNomina, int idNomina) {
        return sp.obtenerDatosPersonalesRhCont(idProductoNomina, idNomina);
    }
}