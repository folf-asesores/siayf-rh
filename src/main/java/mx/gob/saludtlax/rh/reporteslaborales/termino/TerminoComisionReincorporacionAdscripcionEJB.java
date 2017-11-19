
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
public class TerminoComisionReincorporacionAdscripcionEJB {
    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public TerminoDTO obtenerTermino(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
        TerminoDTO terminoDTO = new TerminoDTO();

        Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

        String asunto = "Se comunica término de comisión oficial y reincorporación a lugar de adscripción. ";
        String presenteNombre = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
        String puesto = "Trabajador Regularizado comisionado a Oficina Central";
        String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
        String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
        String numeroOficio = "HGT/RH/00075";
        String fecha = "16 de marzo de 2016";
        String encargado = "Dr. José Gómez González";
        String fechaComision = "15 de julio del presente año, se da por terminada su comisión oficial en el Área de Mantenimiento del Departamento de Infraestructura de Oficina Central, por lo anterior, con fecha 16 de julio del año en curso, deberá de reincorporarse a su lugar de adscripción en el Hospital General de Tlaxcala";
        String clavePresupuestal = "U004REG1103 M03025000220423";
        String asignacion = "el Dr. José Gómez González";
        String encargadoAdministracion = "C.P. LUZ MARIA PORTILLO GARCIA";

        terminoDTO.setIdMovimiento(idMovimiento);
        terminoDTO.setAsunto(asunto);
        terminoDTO.setPresenteNombre(presenteNombre);
        terminoDTO.setPuesto(puesto);
        terminoDTO.setPresenteClaveUno(presenteClaveUno);
        terminoDTO.setPresenteClaveDos(presenteClaveDos);
        terminoDTO.setNumeroOficio(numeroOficio);
        terminoDTO.setFecha(fecha);
        terminoDTO.setEncargado(encargado);
        terminoDTO.setFechaComision(fechaComision);
        terminoDTO.setClavePresupuestal(clavePresupuestal);
        terminoDTO.setAsignacion(asignacion);
        terminoDTO.setEncargadoAdministracion(encargadoAdministracion);

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
