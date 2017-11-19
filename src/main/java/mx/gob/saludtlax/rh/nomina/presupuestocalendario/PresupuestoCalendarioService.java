/*
 *
 */

package mx.gob.saludtlax.rh.nomina.presupuestocalendario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.PresupuestoCalendarioEntity;
import mx.gob.saludtlax.rh.persistencia.PresupuestoCalendarioRepository;

/**
 * @author Eduardo Mex
 *
 */
public class PresupuestoCalendarioService implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1103326538235599887L;

    @Inject
    private PresupuestoCalendarioRepository presupuestoCalendarioRepository;

    protected void crearPresupuestoCalendario(PresupuestoCalendarioDTO dto) {

        PresupuestoCalendarioEntity entity = new PresupuestoCalendarioEntity();

        entity.setUr(dto.getUr());
        entity.setFin(dto.getFin());
        entity.setFn(dto.getFn());
        entity.setSf(dto.getSf());
        entity.setRg(dto.getRg());
        entity.setAi(dto.getAi());
        entity.setMpp(dto.getMpp());
        entity.setPp(dto.getPp());
        entity.setPtda(dto.getPtda());
        entity.setTg(dto.getTg());
        entity.setFf(dto.getFf());
        entity.setEf(dto.getEf());
        entity.setPpii(dto.getPpii());
        entity.setAnio(dto.getAnio());
        entity.setProyectoAnual(dto.getProyectoAnual());
        entity.setEnero(dto.getEnero());
        entity.setFebrero(dto.getFebrero());
        entity.setMarzo(dto.getMarzo());
        entity.setAbril(dto.getAbril());
        entity.setMayo(dto.getMayo());
        entity.setJunio(dto.getJunio());
        entity.setJulio(dto.getJulio());
        entity.setAgosto(dto.getAgosto());
        entity.setSeptimbre(dto.getSeptimbre());
        entity.setOctubre(dto.getOctubre());
        entity.setNoviembre(dto.getNoviembre());
        entity.setDiciembre(dto.getDiciembre());

        presupuestoCalendarioRepository.crear(entity);

    }

    protected void actualizarPresupuestoCalendario(PresupuestoCalendarioDTO dto) {
        PresupuestoCalendarioEntity entity = presupuestoCalendarioRepository.obtenerPorId(dto.getIdPresupuestoCalendario());

        entity.setUr(dto.getUr());
        entity.setFin(dto.getFin());
        entity.setFn(dto.getFn());
        entity.setSf(dto.getSf());
        entity.setRg(dto.getRg());
        entity.setAi(dto.getAi());
        entity.setMpp(dto.getMpp());
        entity.setPp(dto.getPp());
        entity.setPtda(dto.getPtda());
        entity.setTg(dto.getTg());
        entity.setFf(dto.getFf());
        entity.setEf(dto.getEf());
        entity.setPpii(dto.getPpii());
        entity.setAnio(dto.getAnio());
        entity.setProyectoAnual(dto.getProyectoAnual());
        entity.setEnero(dto.getEnero());
        entity.setFebrero(dto.getFebrero());
        entity.setMarzo(dto.getMarzo());
        entity.setAbril(dto.getAbril());
        entity.setMayo(dto.getMayo());
        entity.setJunio(dto.getJunio());
        entity.setJulio(dto.getJulio());
        entity.setAgosto(dto.getAgosto());
        entity.setSeptimbre(dto.getSeptimbre());
        entity.setOctubre(dto.getOctubre());
        entity.setNoviembre(dto.getNoviembre());
        entity.setDiciembre(dto.getDiciembre());

        presupuestoCalendarioRepository.actualizar(entity);

    }

    protected void eliminarPresupuestoCalendario(Integer idPresupuestoCalendario) {

        presupuestoCalendarioRepository.eliminarPorId(idPresupuestoCalendario);
    }

    protected List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendario() {

        List<PresupuestoCalendarioDTO> listaDtos = new ArrayList<>();

        List<PresupuestoCalendarioEntity> listaEntities = presupuestoCalendarioRepository.obtenerListaPresupuestoCalendario();

        if (!listaEntities.isEmpty()) {
            for (PresupuestoCalendarioEntity entity : listaEntities) {
                PresupuestoCalendarioDTO dto = new PresupuestoCalendarioDTO(entity.getIdPresupuestoCalendario(), entity.getUr(), entity.getFin(),
                        entity.getFn(), entity.getSf(), entity.getRg(), entity.getAi(), entity.getMpp(), entity.getPp(), entity.getPtda(), entity.getTg(),
                        entity.getFf(), entity.getEf(), entity.getPpii(), entity.getAnio(), entity.getProyectoAnual(), entity.getEnero(), entity.getFebrero(),
                        entity.getMarzo(), entity.getAbril(), entity.getMayo(), entity.getJunio(), entity.getJulio(), entity.getAgosto(), entity.getSeptimbre(),
                        entity.getOctubre(), entity.getNoviembre(), entity.getDiciembre());

                listaDtos.add(dto);
            }
        }

        return listaDtos;
    }

    protected List<PresupuestoCalendarioDTO> obtenerListaPresupuestoCalendarioPorAnio(Integer anio) {

        List<PresupuestoCalendarioDTO> listaDtos = new ArrayList<>();

        List<PresupuestoCalendarioEntity> listaEntities = presupuestoCalendarioRepository.obtenerListaPresupuestoCalendarioPorAnio(anio);

        if (!listaEntities.isEmpty()) {
            for (PresupuestoCalendarioEntity entity : listaEntities) {
                PresupuestoCalendarioDTO dto = new PresupuestoCalendarioDTO(entity.getIdPresupuestoCalendario(), entity.getUr(), entity.getFin(),
                        entity.getFn(), entity.getSf(), entity.getRg(), entity.getAi(), entity.getMpp(), entity.getPp(), entity.getPtda(), entity.getTg(),
                        entity.getFf(), entity.getEf(), entity.getPpii(), entity.getAnio(), entity.getProyectoAnual(), entity.getEnero(), entity.getFebrero(),
                        entity.getMarzo(), entity.getAbril(), entity.getMayo(), entity.getJunio(), entity.getJulio(), entity.getAgosto(), entity.getSeptimbre(),
                        entity.getOctubre(), entity.getNoviembre(), entity.getDiciembre());

                listaDtos.add(dto);
            }
        }

        return listaDtos;
    }

}
