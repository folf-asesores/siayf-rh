
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

/**
 * @author Daniela
 *
 */

@Stateless
public class TerminoInterinatoEJB {

    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public TerminoDTO obtenerTermino(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
        TerminoDTO terminoDTO = new TerminoDTO();

        Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

        String asunto = "Se comunica término de nombramiento de confianza.";
        String posicionDos = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
        String posicionTres = movimientoEmpleadoEntity.getEmpleado().getRfc();
        String posicionCuatro = movimientoEmpleadoEntity.getEmpleado().getCurp();
        String reincorporacionTitular = "a partir del 01 de junio del 2016, se da por terminado su nombramiento de confianza";
        String fechaTermino = "Departamento Estatal (Jefe de la Oficina de Educación Médica)";
        String funcion = "I0024161103 CF34263290040022";
        String clavePresupuestal = "DR. ALEJANDRO GUARNEROS CHUMACERO";
        String secretarioSalud = "";

        terminoDTO.setIdMovimiento(idMovimiento);
        terminoDTO.setAsunto(asunto);
        terminoDTO.setPosicionDos(posicionDos);
        terminoDTO.setPosicionTres(posicionTres);
        terminoDTO.setPosicionCuatro(posicionCuatro);
        terminoDTO.setReincorporacionTitular(reincorporacionTitular);
        terminoDTO.setFechaTermino(fechaTermino);
        terminoDTO.setFuncion(funcion);
        terminoDTO.setClavePresupuestal(clavePresupuestal);
        terminoDTO.setSecretarioSalud(secretarioSalud);

        return terminoDTO;

    }

    public List<TerminoDetalleDTO> consultarPorCriterio(String criterio) {

        List<TerminoDetalleDTO> resultado = new ArrayList<>();
        List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

        try {
            movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
        } catch (Exception ex) {
        }

        if (movimientoEmpleadoEntityList != null) {

            for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
                MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

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
