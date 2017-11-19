
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

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
public class ReservacionPlazaResidenciaMedicaEJB {

    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public ReservacionDTO obtenerReservacion(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
        ReservacionDTO reservacionDTO = new ReservacionDTO();

        Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

        String asunto = "ASUNTO";
        String presenteNombre = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
        String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
        String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
        String fecha = "16 de febrero de 2016";
        String clavePresupuestal = "I0024161103 M02107 290040025";
        String denominacion = "Enfermera Especialista “C”";
        String adscripcion = "Hospital Regional “Lic. Emilio Sánchez Piedras”";
        String vigencia = "Del 01 de marzo de 2016 al 28 de febrero de 2017";
        String solicitud = "deberá presentar ante la Comisión Central Mixta de Capacitación y a la Oficina de Relaciones Laborales de ésta "
                + "Secretaría, en la primera quincena de diciembre de cada año, comprobantes de que subsisten las causas que dieron origen a" + "esta licencia";
        String posicionUno = "os Artículos 151 Párrafo Segundo de las Condiciones Generales de Trabajo y 55 del Reglamento mencionado, deberá "
                + "reincorporarse a su puesto de base, en su lugar de adscripción, dentro de los seis días hábiles siguientes al término de dicha "
                + "licencia, por lo que deberá dar aviso de reincorporación a ésta Dirección, 15 días antes de que concluya la residencia médica o "
                + "no prosiga con la misma";
        String posiciondos = "e acuerdo al Artículo 56 del Reglamento, una vez concluida la beca que le fue autorizada, deberá de prestar sus "
                + "servicios a la Secretaría por el tiempo equivalente al de su duración. De no hacerlo, la Secretaría, con apoyo en las "
                + "disposiciones aplicables, procederá al fincamiento de responsabilidades en su contra";
        String jefe = "LIC. VICTOR JOSE LEAL CRUZ";

        reservacionDTO.setIdMovimiento(idMovimiento);
        reservacionDTO.setAsunto(asunto);
        reservacionDTO.setPresenteNombre(presenteNombre);
        reservacionDTO.setPresenteClaveUno(presenteClaveUno);
        reservacionDTO.setPresenteClaveDos(presenteClaveDos);
        reservacionDTO.setFecha(fecha);
        reservacionDTO.setClavePresupuestal(clavePresupuestal);
        reservacionDTO.setDenominacion(denominacion);
        reservacionDTO.setAdscripcion(adscripcion);
        reservacionDTO.setVigencia(vigencia);
        reservacionDTO.setSolicitud(solicitud);
        reservacionDTO.setPosicionUno(posicionUno);
        reservacionDTO.setPosiciondos(posiciondos);
        reservacionDTO.setJefe(jefe);

        return reservacionDTO;
    }

    public List<ReservacionDetalleDTO> consultarPorCriterio(String criterio) {

        List<ReservacionDetalleDTO> resultado = new ArrayList<>();
        List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

        try {
            movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
        } catch (Exception ex) {
        }

        if (movimientoEmpleadoEntityList != null) {

            for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
                MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

                ReservacionDetalleDTO dto = new ReservacionDetalleDTO();

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
