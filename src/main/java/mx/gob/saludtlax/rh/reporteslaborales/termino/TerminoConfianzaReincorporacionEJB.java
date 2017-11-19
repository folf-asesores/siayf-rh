
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

@Stateless
public class TerminoConfianzaReincorporacionEJB {
    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public TerminoDTO obtenerTermino(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository
                .obtenerPorId(idTipoMovimiento);
        TerminoDTO terminoDTO = new TerminoDTO();

        Integer idMovimiento = movimientoEmpleadoEntity
                .getIdMovimientoEmpleado();

        String asunto = "Se comunica término de nombramiento de confianza y reincorporación a su plaza de base.";
        String presenteNombre = movimientoEmpleadoEntity.getEmpleado()
                .nombreCompleto();
        String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado()
                .getRfc();
        String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado()
                .getCurp();
        String fechaTermino = "partir del 22 de febrero del año en curso, se da por terminado su nombramiento de confianza";
        String clavePresupuestal = "0024161103 CF41013 29004 0012";
        String fechaPlaza = "on fecha 23 del mismo mes y año, se le reincorpora a su plaza de base con función de Médico General “C”";
        String nuevaClave = "I0024161103 M01009 290040031";
        String jefe = "Dr. Efrén Samuel Orrico Torres";
        String secretarioSalud = "DR. ALEJANDRO GUARNEROS CHUMACERO";

        terminoDTO.setIdMovimiento(idMovimiento);
        terminoDTO.setAsunto(asunto);
        terminoDTO.setPresenteNombre(presenteNombre);
        terminoDTO.setPresenteClaveUno(presenteClaveUno);
        terminoDTO.setPresenteClaveDos(presenteClaveDos);
        terminoDTO.setFechaTermino(fechaTermino);
        terminoDTO.setClavePresupuestal(clavePresupuestal);
        terminoDTO.setFechaPlaza(fechaPlaza);
        terminoDTO.setNuevaClave(nuevaClave);
        terminoDTO.setJefe(jefe);
        terminoDTO.setSecretarioSalud(secretarioSalud);

        return terminoDTO;

    }

    public List<TerminoDetalleDTO> consultarPorCriterio(String criterio) {

        List<TerminoDetalleDTO> resultado = new ArrayList<>();
        List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

        try {
            movimientoEmpleadoEntityList = movimientoEmpleadoRepository
                    .consultarMovimientosPorRfc(criterio);
        } catch (Exception ex) {
        }

        if (movimientoEmpleadoEntityList != null) {

            for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
                MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList
                        .get(i);

                TerminoDetalleDTO dto = new TerminoDetalleDTO();

                String curp = m.getEmpleado().getCurp();
                String empleado = m.getEmpleado().getNombreCompleto();
                Integer idMovimiento = m.getIdMovimientoEmpleado();
                String rfc = m.getEmpleado().getRfc();
                String motivo = m.getMotivoPermiso();
                String url = "";

                dto.setCurp(curp);
                dto.setEmpleado(empleado);
                dto.setIdMovimiento(idMovimiento);
                dto.setRfc(rfc);
                dto.setMotivo(motivo);
                dto.setUrl(url);

                resultado.add(dto);
            }
        }

        return resultado;
    }
}
