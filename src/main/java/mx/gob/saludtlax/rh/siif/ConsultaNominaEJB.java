
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosPlantillaExcelDTO;

@Stateless
public class ConsultaNominaEJB {
    @Inject
    private ConsultaNominaService consultaNominaService;

    //	Listas para Estructura Nomina Datos

    public List<EstructuraNominaDatDTO> obtenerConsultaNominaLista() {
        List<EstructuraNominaDatDTO> consultaNominaLista = consultaNominaService.listaEstructuraNomina();
        return consultaNominaLista;
    }

    public List<EstructuraNominaDatDTO> obtenerConsultaNominaListaPorCriterios(String rfc) {
        return consultaNominaService.listaConsultaNominaPorCriterios(rfc);
    }

    public List<EstructuraNominaDatDTO> obtenerConsultaNominaListaPorCriterios(ConsultaDatosEncabezadoDTO nominaSeleccionado) {
        return consultaNominaService.listaConsultaNominaPorIds(nominaSeleccionado);
    }

    public List<EstructuraContratosPlantillaExcelDTO> obtenerConsultaNominaContratoListaPorCriterios(String rfc) {
        return consultaNominaService.listaConsultaNominaContratoPorCriterios(rfc);
    }

    public EstructuraNominaDatDTO obtenerConsultaNominaListaPorId(EstructuraNominaDatDTO DTO) {
        EstructuraNominaDatDTO dto = consultaNominaService.obtenerEstructuraNominaDatPorId(DTO.getIdEstructurasNominas());
        return dto;
    }

    //	Listas para Estructura Nomina Datos

    public List<EstructuraNominaTrailersDTO> obtenerConsultaNominaTrailersLista() {
        List<EstructuraNominaTrailersDTO> consultaNominaTrailersLista = consultaNominaService.listaEstructuraNominaTrailers();
        return consultaNominaTrailersLista;
    }

    public List<EstructuraNominaTrailersDTO> obtenerConsultaNominaTrailersListaPorCriterios(String rfc) {
        return consultaNominaService.listaConsultaNominaTrailersPorCriterios(rfc);
    }

    public EstructuraNominaTrailersDTO obtenerConsultaNominaTrailersListaPorId(EstructuraNominaTrailersDTO DTO) {
        EstructuraNominaTrailersDTO dto = consultaNominaService.obtenerEstructuraNominaTrailersDatPorId(DTO.getIdEstructurasNominasTrailers());
        return dto;
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Datos

    public EstructuraNominaDatDTO nuevoDatos() {
        return consultaNominaService.nuevosDatos();
    }

    public void eliminarDatos(EstructuraNominaDatDTO estructuraNominaSelect) {

    }

    public EstructuraNominaDatDTO obtenerDatos(EstructuraNominaDatDTO estructuraNominaSelect) {
        EstructuraNominaDatDTO dto = consultaNominaService.obtenerEstructuraNominaDatPorId(estructuraNominaSelect.getIdEstructurasNominas());
        return dto;
    }

    public void crearDatos(EstructuraNominaDatDTO estructuraNomina) {
        consultaNominaService.crearDatos(estructuraNomina);
    }

    public void actualizarDatos(EstructuraNominaDatDTO estructuraNomina) {
        consultaNominaService.actualizarDatos(estructuraNomina);
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Trailers

    public EstructuraNominaTrailersDTO nuevoTrailers() {
        return consultaNominaService.nuevosTrailers();
    }

    public void eliminarTrailers(EstructuraNominaTrailersDTO estructuraNominaSelect) {

    }

    public EstructuraNominaTrailersDTO obtenerTrailers(EstructuraNominaTrailersDTO DTO) {
        EstructuraNominaTrailersDTO dto = consultaNominaService.obtenerEstructuraNominaTrailersDatPorId(DTO.getIdEstructurasNominasTrailers());
        return dto;
    }

    public void crearTrailers(EstructuraNominaTrailersDTO dto) {
        consultaNominaService.crearTrailers(dto);
    }

    public void actualizarTrailers(EstructuraNominaTrailersDTO estructuraNomina) {
        consultaNominaService.actualizarTrailers(estructuraNomina);
    }

    //>>>>>>>>>Modifica el RFc en TRA<<<<<<<<<
    public void modificarTrailers(String rfc, Integer id) {
        consultaNominaService.modificarTrailers(rfc, id);
    }
}