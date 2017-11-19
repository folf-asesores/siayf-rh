
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosDatDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.EstructuraContratosTrailersDTO;

@Stateless
public class ConsultaProsperaEJB {

    @Inject
    private ConsultaProsperaService consultaProsperaService;

    //	Listas para Estructura Nomina Datos

    public List<EstructuraContratosDatDTO> obtenerConsultaProsperaLista() {
        List<EstructuraContratosDatDTO> consultaNominaLista = consultaProsperaService
                .listaEstructuraProspera();
        return consultaNominaLista;
    }

    public EstructuraContratosDatDTO obtenerConsultaProsperaListaPorId(
            EstructuraContratosDatDTO DTO) {
        EstructuraContratosDatDTO dto = consultaProsperaService
                .obtenerEstructuraProsperaDatPorId(
                        DTO.getIdEstructurasContratos());
        return dto;
    }

    public List<EstructuraContratosDatDTO> obtenerConsultaProsperaListaPorCriterios(
            String rfcCriterio) {
        List<EstructuraContratosDatDTO> consultaProsperaLista = consultaProsperaService
                .listaEstructuraProsperaPorCriterios(rfcCriterio);
        return consultaProsperaLista;
    }

    //	Listas para Estructura Nomina Datos

    public List<EstructuraContratosTrailersDTO> obtenerConsultaNominaTrailersLista() {
        List<EstructuraContratosTrailersDTO> consultaProsperaTrailersLista = consultaProsperaService
                .listaEstructuraProsperaTrailers();
        return consultaProsperaTrailersLista;
    }

    public List<EstructuraContratosTrailersDTO> obtenerConsultaProsperaTrailersListaPorCriterios(
            String rfc) {
        return consultaProsperaService
                .listaConsultaProsperaTrailersPorCriterios(rfc);
    }

    public EstructuraContratosTrailersDTO obtenerConsultaProsperaTrailersListaPorId(
            EstructuraContratosTrailersDTO DTO) {
        EstructuraContratosTrailersDTO dto = consultaProsperaService
                .obtenerEstructuraProsperaTrailersDatPorId(
                        DTO.getIdEstructurasContratos());
        return dto;
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Datos

    public EstructuraContratosDatDTO nuevoDatos() {
        return consultaProsperaService.nuevosDatos();
    }

    public void eliminarDatos(
            EstructuraContratosDatDTO estructuraNominaSelect) {

    }

    public EstructuraContratosDatDTO obtenerDatos(
            EstructuraContratosDatDTO estructuraProsperaSelect) {
        EstructuraContratosDatDTO dto = consultaProsperaService
                .obtenerEstructuraProsperaDatPorId(
                        estructuraProsperaSelect.getIdEstructurasContratos());
        return dto;
    }

    public void crearDatos(EstructuraContratosDatDTO estructuraProspera) {
        consultaProsperaService.crearDatosProspera(estructuraProspera);
    }

    public void actualizarDatos(EstructuraContratosDatDTO estructuraProspera) {
        consultaProsperaService.actualizarDatosProspera(estructuraProspera);
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Trailers

    public EstructuraContratosTrailersDTO nuevoTrailers() {
        return consultaProsperaService.nuevosTrailersProspera();
    }

    public void eliminarTrailers(
            EstructuraContratosTrailersDTO estructuraNominaSelect) {

    }

    public EstructuraContratosTrailersDTO obtenerTrailers(
            EstructuraContratosTrailersDTO DTO) {
        EstructuraContratosTrailersDTO dto = consultaProsperaService
                .obtenerEstructuraProsperaTrailersDatPorId(
                        DTO.getIdEstructurasContratos());
        return dto;
    }

    public void crearTrailers(EstructuraContratosTrailersDTO dto) {
        consultaProsperaService.crearTrailersProspera(dto);
    }

    public void actualizarTrailers(
            EstructuraContratosTrailersDTO estructuraProspera) {
        consultaProsperaService.actualizarTrailersProspera(estructuraProspera);
    }

    //>>>>>>>>>Modifica el RFc en TRA<<<<<<<<<
    public void modificarTrailers(String rfc, Integer id) {
        consultaProsperaService.modificarTrailers(rfc, id);
    }

}