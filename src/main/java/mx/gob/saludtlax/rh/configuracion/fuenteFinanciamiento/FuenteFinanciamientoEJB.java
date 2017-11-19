
package mx.gob.saludtlax.rh.configuracion.fuenteFinanciamiento;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FuenteFinanciamientoEJB {

    @Inject
    private FuenteFinanciamientoService fuenteFinanciamientoService;

    // Inicia para operaciones Fuente de Financiamiento

    public List<FuenteFinanciamientoDTO> obtenerFuenteFinanciamientoLista() {
        List<FuenteFinanciamientoDTO> fuenteFinanciamientoLista = fuenteFinanciamientoService
                .listaFuenteFinanciamiento();
        return fuenteFinanciamientoLista;
    }

    public FuenteFinanciamientoDTO obtenerFuenteFinanciamiento(
            FuenteFinanciamientoDTO fuenteFinanciamiento) {
        FuenteFinanciamientoDTO dto = fuenteFinanciamientoService
                .obtenerFuenteFinanciamientoPorId(
                        fuenteFinanciamiento.getIdFuenteFinanciamiento());
        return dto;
    }

    public void crearFuenteFinanciamiento(FuenteFinanciamientoDTO dto) {
        fuenteFinanciamientoService.crearFuenteFinanciamiento(dto);
    }

    public void eliminarFuenteFinanciamiento(Integer idFuenteFinanciamiento) {
        fuenteFinanciamientoService
                .eliminarFuenteFinanciamiento(idFuenteFinanciamiento);
    }

    // Inicia para operaciones Fuente de Financiamiento OPD

    public List<FuenteFinanciamientoOPDDTO> obtenerFuenteFinanciamientoOPDLista() {
        List<FuenteFinanciamientoOPDDTO> fuenteFinanciamientoOPDLista = fuenteFinanciamientoService
                .listaFuenteFinanciamientoOPD();
        return fuenteFinanciamientoOPDLista;
    }

    public FuenteFinanciamientoOPDDTO obtenerFuenteFinanciamientoOPD(
            FuenteFinanciamientoOPDDTO fuenteFinanciamiento) {
        FuenteFinanciamientoOPDDTO dto = fuenteFinanciamientoService
                .obtenerFuenteFinanciamientoOPDPorId(
                        fuenteFinanciamiento.getIdFuenteFinanciamientoOPD());
        return dto;
    }

    public FuenteFinanciamientoOPDDTO nuevoFuenteFinanciamientoOPD() {
        return fuenteFinanciamientoService.nuevoFuenteFinanciamientoOPD();
    }

    public FuenteFinanciamientoOPDDTO crearFuenteFinanciamientoOPD(
            FuenteFinanciamientoOPDDTO dto) {
        return fuenteFinanciamientoService.crearFuenteFinanciamientoOPD(dto);
    }

    public FuenteFinanciamientoOPDDTO actualizarFuenteFinanciamientoOPD(
            FuenteFinanciamientoOPDDTO dto) {
        return fuenteFinanciamientoService
                .actualizarFuenteFinanciamientoOPD(dto);
    }

    public void eliminarFuenteFinanciamientoOPD(
            FuenteFinanciamientoOPDDTO dto) {
        fuenteFinanciamientoService.eliminarFuenteFinanciamientoOPD(dto);
    }

    // Inicia para operaciones Subfuente de Financiamiento

    public SubfuenteFinanciamientoDTO obtenerSubfuenteFinanciamiento(
            SubfuenteFinanciamientoDTO fuenteFinanciamiento) {
        SubfuenteFinanciamientoDTO dto = fuenteFinanciamientoService
                .obtenerSubfuenteFinanciamientoPorId(
                        fuenteFinanciamiento.getIdSubfuenteFinanciamiento());
        return dto;
    }

    public SubfuenteFinanciamientoDTO nuevoSubfuenteFinanciamiento() {
        return fuenteFinanciamientoService.nuevoSubfuenteFinanciamiento();
    }

    public SubfuenteFinanciamientoDTO crearSubfuenteFinanciamiento(
            SubfuenteFinanciamientoDTO dto) {
        return fuenteFinanciamientoService.crearSubfuenteFinanciamiento(dto);
    }

    public SubfuenteFinanciamientoDTO actualizarSubfuenteFinanciamiento(
            SubfuenteFinanciamientoDTO dto) {
        return fuenteFinanciamientoService
                .actualizarSubfuenteFinanciamiento(dto);
    }

    public void eliminarSubfuenteFinanciamiento(
            SubfuenteFinanciamientoDTO dto) {
        fuenteFinanciamientoService.eliminarSubfuenteFinanciamiento(dto);
    }

    public List<SubfuenteFinanciamientoDTO> obtenerSubfuenteFinanciamientoLista() {
        return fuenteFinanciamientoService.listaSubfuenteFinanciamientoNomina();
    }
}