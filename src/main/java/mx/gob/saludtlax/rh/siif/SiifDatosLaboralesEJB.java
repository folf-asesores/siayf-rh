
package mx.gob.saludtlax.rh.siif;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.dependencia.DependenciaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.nombramiento.TipoNombramientoDTO;
import mx.gob.saludtlax.rh.configuracion.unidadresponsable.UnidadResponsableDTO;

@Stateless
public class SiifDatosLaboralesEJB {
    @Inject
    private SiifDatosLaboralesService service;

    public List<SiifDatosLaboralesDTO> obtenerDatosLaboralesLista() {
        List<SiifDatosLaboralesDTO> consultaDatosLaboralesLista = service
                .listaSiifDatosLaborales();
        return consultaDatosLaboralesLista;
    }

    public List<SiifDatosLaboralesDTO> obtenerDatosLaboralesListaPorCriterios(
            String rfc) {
        return service.listaSiifDatosLaboralesPorCriterio(rfc);
    }

    public SiifDatosLaboralesDTO obtenerDatosLaboralesListaPorId(
            SiifDatosLaboralesDTO DTO) {
        SiifDatosLaboralesDTO dto = service
                .obtenerSiifDatosLaboralesPorId(DTO.getIdDatoLaboral());
        return dto;
    }

    public SiifDatosLaboralesDTO nuevoDatosLaborales(String rfc) {
        return service.nuevosDatosLaborales(rfc);
    }

    public void eliminarDatosLaborales(
            SiifDatosLaboralesDTO siifDatosLaboralesSelect) {
        service.eliminarDatosLaborales(siifDatosLaboralesSelect);

    }

    public SiifDatosLaboralesDTO obtenerDatosLaborales(
            SiifDatosLaboralesDTO siifDatosLaboralesSelect) {
        SiifDatosLaboralesDTO dto = service.obtenerSiifDatosLaboralesPorId(
                siifDatosLaboralesSelect.getIdDatoLaboral());
        return dto;
    }

    public void crearDatosLaborales(SiifDatosLaboralesDTO siifDatosLaborales,
            String rfc) {
        service.crearDatosLaborales(siifDatosLaborales, rfc);

    }

    public void actualizarDatosLaborales(
            SiifDatosLaboralesDTO siifDatosLaborales) {
        service.actualizarDatosLaborales(siifDatosLaborales);

    }

    public Boolean verificaIdDatoLaboral(int idDatoLaboral) {
        int existe = 0;
        existe = service.verificaIdDatoLaboralPorId(idDatoLaboral);
        if (existe == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<DependenciaDTO> obtenerDependenciaLista() {
        return service.obtenerDependencia();
    }

    public List<TipoNombramientoDTO> obtenerNombramientoLista() {
        return service.obtenerNombramiento();
    }

    public List<UnidadResponsableDTO> obtenerUnidadLista() {
        return service.obtenerUnidad();
    }

    //	public List<PlazaDTO> obtenerPlaza() {
    //		return service.obtenerPlaza;
    //	}

    public List<PuestosGeneralesDTO> obtenerPuesto() {
        return service.obtenerPuesto();
    }

    public List<FuenteFinanciamientoDTO> obtenerFuenteFinanciamiento() {
        return service.obtenerFuenteFinanciamiento();
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamiento() {
        return service.obtenerSubfuenteFinanciamiento();
    }

    public List<ProyectosTempDTO> obtenerProyecto() {
        return service.obtenerProyecto();
    }

    public void modificarLaborales(String rfc, Integer idDatoPersonal) {
        service.modificarLaborales(rfc, idDatoPersonal);
    }

    //	Listas para Estructura Nomina Datos

    public List<SiifLaboralesSubfuentesDTO> obtenerSiifLaboralesSubfuentes() {
        List<SiifLaboralesSubfuentesDTO> SiifLaboralesSubfuentesLista = service
                .listaSiifLaboralesSubfuentes();
        return SiifLaboralesSubfuentesLista;
    }

    public List<SiifLaboralesSubfuentesDTO> obtenerSiifLaboralesSubfuentesPorIDLaborles(
            Integer idDatosLaborales) {
        return service
                .obtenerSiifLaboralesSubfuentesPorIdDatos(idDatosLaborales);
    }

    public SiifLaboralesSubfuentesDTO obtenerSiifLaboralesSubfuentesListaPorId(
            SiifLaboralesSubfuentesDTO DTO) {
        SiifLaboralesSubfuentesDTO dto = service
                .obtenerSiifLaboralesSubfuentesPorId(
                        DTO.getIdSiifDatosLaborales());
        return dto;
    }

    //	Opciones CLAE (Crear-Leer-Actualizar-Eliminar) para Estructura Nomina Datos

    public SiifLaboralesSubfuentesDTO nuevoDatos() {
        return service.nuevasSubfuentes();
    }

    public List<SiifLaboralesSubfuentesDTO> eliminarDatos(
            SiifLaboralesSubfuentesDTO dto) {
        service.eliminarSiifLAborlaesSubfuente(dto);
        return service.obtenerSiifLaboralesSubfuentesPorIdDatos(
                dto.getIdSiifDatosLaborales());
    }

    public SiifLaboralesSubfuentesDTO obtenerDatos(
            SiifLaboralesSubfuentesDTO dtoSelect) {
        SiifLaboralesSubfuentesDTO dto = service
                .obtenerSiifLaboralesSubfuentesPorId(
                        dtoSelect.getIdSiifLaboralesSubfuentes());
        return dto;
    }

    public List<SiifLaboralesSubfuentesDTO> crearDatos(
            Integer idDatosLaborales) {
        service.crearSubfuentes(idDatosLaborales);
        return service
                .obtenerSiifLaboralesSubfuentesPorIdDatos(idDatosLaborales);
    }

    public void actualizarDatos(List<SiifLaboralesSubfuentesDTO> list,
            Integer idDL) {
        service.actualizarDatos(list, idDL);
    }

    //	Otras Listas

    public List<FuenteFinanciamientoDTO> obtenerFuentesF() {
        return service.listaFuenteFinanciamiento();
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuentesF() {
        return service.listaSubfuenteFinanciamiento();
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuentesFPorId(
            Integer dto) {
        return service.listaSubfuenteFinanciamientoPorIdFF(dto);
    }

    public void change() {
        service.change();
    }

}