
package mx.gob.saludtlax.rh.configuracion.tiponomina;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.clasificacionnomina.ClasificacionNominaDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.FuenteFinanciamientoDTO;
import mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento.SubfuenteFinanciamientoDTO;

@Stateless
public class TipoNominaEJB {

    @Inject
    private TipoNominaService tipoNominaService;

    public List<TipoNominaDTO> obtenerTipoNominaLista() {
        List<TipoNominaDTO> tipoNominaLista = tipoNominaService
                .listaTipoNomina();
        return tipoNominaLista;
    }

    public List<TipoNominaListaDTO> obtenerListaTipoNomina() {
        List<TipoNominaListaDTO> tipoNominaLista = tipoNominaService
                .tipoNominaLista();
        return tipoNominaLista;
    }

    public TipoNominaDTO obtenerTipoNomina(TipoNominaDTO tipoNomina) {
        TipoNominaDTO dto = tipoNominaService
                .obtenerTipoNominaPorId(tipoNomina.getIdTipoNomina());
        return dto;
    }

    public TipoNominaDTO nuevoTipoNomina() {
        return tipoNominaService.nuevoTipoNomina();
    }

    public TipoNominaDTO crearTipoNomina(TipoNominaDTO dto) {
        return tipoNominaService.crearTipoNomina(dto);
    }

    public TipoNominaDTO actualizarTipoNomina(TipoNominaDTO dto) {
        return tipoNominaService.actualizarTipoNomina(dto);
    }

    public void eliminarTipoNomina(TipoNominaDTO dto) {
        tipoNominaService.eliminarTipoNomina(dto);
    }

    // <<<<<<Listas>>>>>>

    public List<ClasificacionNominaDTO> obtenerClasificacionLista() {
        return tipoNominaService.obtenerClasificacion();
    }

    public List<FuenteFinanciamientoDTO> obtenerFuenteLista() {
        return tipoNominaService.obtenerFuente();
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteLista() {
        return tipoNominaService.obtenerSubfuente();
    }

    public List<TipoRecursoDTO> obtenerListaTipoRecurso() {
        return tipoNominaService.obtenerListaTipoRecurso();
    }
}