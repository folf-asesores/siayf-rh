
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
        List<EstructuraNominaDatDTO> consultaNominaLista = this.consultaNominaService.listaEstructuraNomina();
        return consultaNominaLista;
    }

    public List<EstructuraNominaDatDTO> obtenerConsultaNominaListaPorCriterios(String rfc) {
        return this.consultaNominaService.listaConsultaNominaPorCriterios(rfc);
    }

    public List<EstructuraNominaDatDTO> obtenerConsultaNominaListaPorCriterios(ConsultaDatosEncabezadoDTO nominaSeleccionado) {
        return this.consultaNominaService.listaConsultaNominaPorIds(nominaSeleccionado);
    }

    public List<EstructuraContratosPlantillaExcelDTO> obtenerConsultaNominaContratoListaPorCriterios(String rfc) {
        return this.consultaNominaService.listaConsultaNominaContratoPorCriterios(rfc);
    }

    public EstructuraNominaDatDTO obtenerConsultaNominaListaPorId(EstructuraNominaDatDTO DTO) {
        EstructuraNominaDatDTO dto = this.consultaNominaService.obtenerEstructuraNominaDatPorId(DTO.getIdEstructurasNominas());
        return dto;
    }

    //	Listas para Estructura Nomina Datos

    public List<EstructuraNominaTrailersDTO> obtenerConsultaNominaTrailersLista() {
        List<EstructuraNominaTrailersDTO> consultaNominaTrailersLista = this.consultaNominaService.listaEstructuraNominaTrailers();
        return consultaNominaTrailersLista;
    }

    public List<EstructuraNominaTrailersDTO> obtenerConsultaNominaTrailersListaPorCriterios(String rfc) {
        return this.consultaNominaService.listaConsultaNominaTrailersPorCriterios(rfc);
    }

    public EstructuraNominaTrailersDTO obtenerConsultaNominaTrailersListaPorId(EstructuraNominaTrailersDTO DTO) {
        EstructuraNominaTrailersDTO dto = this.consultaNominaService.obtenerEstructuraNominaTrailersDatPorId(DTO.getIdEstructurasNominasTrailers());
        return dto;
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Datos

    public EstructuraNominaDatDTO nuevoDatos() {
        return this.consultaNominaService.nuevosDatos();
    }

    public void eliminarDatos(EstructuraNominaDatDTO estructuraNominaSelect) {
        

    }

    public EstructuraNominaDatDTO obtenerDatos(EstructuraNominaDatDTO estructuraNominaSelect) {
        EstructuraNominaDatDTO dto = this.consultaNominaService.obtenerEstructuraNominaDatPorId(estructuraNominaSelect.getIdEstructurasNominas());
        return dto;
    }

    public void crearDatos(EstructuraNominaDatDTO estructuraNomina) {
        this.consultaNominaService.crearDatos(estructuraNomina);
    }

    public void actualizarDatos(EstructuraNominaDatDTO estructuraNomina) {
        this.consultaNominaService.actualizarDatos(estructuraNomina);
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Trailers

    public EstructuraNominaTrailersDTO nuevoTrailers() {
        return this.consultaNominaService.nuevosTrailers();
    }

    public void eliminarTrailers(EstructuraNominaTrailersDTO estructuraNominaSelect) {
        

    }

    public EstructuraNominaTrailersDTO obtenerTrailers(EstructuraNominaTrailersDTO DTO) {
        EstructuraNominaTrailersDTO dto = this.consultaNominaService.obtenerEstructuraNominaTrailersDatPorId(DTO.getIdEstructurasNominasTrailers());
        return dto;
    }

    public void crearTrailers(EstructuraNominaTrailersDTO dto) {
        this.consultaNominaService.crearTrailers(dto);
    }

    public void actualizarTrailers(EstructuraNominaTrailersDTO estructuraNomina) {
        this.consultaNominaService.actualizarTrailers(estructuraNomina);
    }

    //>>>>>>>>>Modifica el RFc en TRA<<<<<<<<<
    public void modificarTrailers(String rfc, Integer id) {
        this.consultaNominaService.modificarTrailers(rfc, id);
    }
}