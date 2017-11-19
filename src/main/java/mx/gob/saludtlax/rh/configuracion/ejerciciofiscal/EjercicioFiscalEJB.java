
package mx.gob.saludtlax.rh.configuracion.ejerciciofiscal;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoDTO;
import mx.gob.saludtlax.rh.configuracion.tipoperiodo.TipoPeriodoService;

@Stateless
public class EjercicioFiscalEJB {

    @Inject
    private EjercicioFiscalService ejercicioFiscalService;
    @Inject
    private TipoPeriodoService tipoPeriodoService;

    public List<EjercicioFiscalListaDTO> obtenerEjercicioFiscalLista() {
        List<EjercicioFiscalListaDTO> ejercicioFiscalLista = ejercicioFiscalService
                .listaEjercicioFiscal();
        return ejercicioFiscalLista;
    }

    public List<EjercicioFiscalListaDTO> obtenerEjercicioFiscalListaPorAnio(
            Integer ejercicioFiscal) {
        // Validar el ejercicioFiscal se un número valido.
        return ejercicioFiscalService
                .listaEjercicioFiscalPorejercicioFiscal(ejercicioFiscal);
    }

    public EjercicioFiscalDTO obtenerEjercicioFiscal(
            EjercicioFiscalListaDTO ejercicioFiscal) {
        EjercicioFiscalDTO dto = ejercicioFiscalService
                .obtenerEjercicioFiscalPorId(
                        ejercicioFiscal.getIdEjercicioFiscal());
        dto.setListPeriodoCalendario(ejercicioFiscalService
                .listaPeriodoCalendarioPorIdEjercicioFiscal(
                        ejercicioFiscal.getIdEjercicioFiscal()));
        return dto;
    }

    public EjercicioFiscalDTO nuevoEjercicioFiscal() {
        return ejercicioFiscalService.nuevoEjercicioFiscal();
    }

    public EjercicioFiscalDTO crearEjercicioFiscal(
            EjercicioFiscalDTO ejercicioFiscalDTO) {
        return ejercicioFiscalService.crearEjercicioFiscal(ejercicioFiscalDTO);
    }

    public List<TipoPeriodoDTO> obtenerTipoPeriodoLista() {
        return tipoPeriodoService.listaTipoPeriodo();
    }

    public EjercicioFiscalDTO actualizarEjercicioFiscal(
            EjercicioFiscalDTO ejercicioFiscal) {
        return ejercicioFiscalService
                .actualizarEjercicioFiscal(ejercicioFiscal);
    }

    public void eliminarEjercicioFiscal(
            EjercicioFiscalListaDTO ejercicioFiscal) {
        ejercicioFiscalService.eliminarEjercicioFical(ejercicioFiscal);
    }

    // Opciones de Periodos
    //Obtener Periodos

    public PeriodoCalendarioDTO obtenerPeriodoCalendario(
            PeriodoCalendarioDTO periodoCalendarioSelect) {
        return ejercicioFiscalService.obtenerPeriodoCalendarioPorId(
                periodoCalendarioSelect.getIdPeriodoCalendario());
    }

    //	public List<PeriodoCalendarioDTO> obtenerPeriodoCalendarioLista() {
    //		List<PeriodoCalendarioDTO> periodoCalendarioLista = ejercicioFiscalService.listaPeriodoCalendario();
    //		return periodoCalendarioLista;
    //	}

    //Nuevo Crear Actualizar Eliminar Periodos

    public PeriodoCalendarioDTO nuevoPeriodoCalendario(PeriodoCalendarioDTO dto,
            Integer ef) {
        return ejercicioFiscalService.nuevoPeriodoCalendario(dto, ef);
    }

    public void actualizaPeriodoCalendario(
            PeriodoCalendarioDTO periodoCalendario) {
        ejercicioFiscalService.actualizaPeriodoCalendario(periodoCalendario);
    }

    public void eliminarPeriodoCalendario(
            PeriodoCalendarioDTO periodoCalendarioSelect) {
        ejercicioFiscalService
                .eliminarPeriodoCalendario(periodoCalendarioSelect);
    }

}