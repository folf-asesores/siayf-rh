
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.util.ArrayList;
import java.util.Date;
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
public class ReservacionPlazaEleccionPopularEJB {
    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public ReservacionDTO obtenerReservacionOtra(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
        ReservacionDTO reservacionDTO = new ReservacionDTO();

        Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();

        String asunto = "Asunto";
        String presenteNombre = movimientoEmpleadoEntity.getEmpleado().nombreCompleto();
        String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado().getRfc();
        String presenteClaveDos = movimientoEmpleadoEntity.getEmpleado().getCurp();
        String posicionUno = "En atención a solicitud, para que se le otorgue prorroga de licencia sin goce de sueldo en el puesto con funciones "
                + "de regularizado para ocupar cargo de elección popular, me permito comunicarle que con fundamento en los Artículos 43 Fracción "
                + "VIII de la Ley Federal de los Trabajadores al Servicio del Estado y 148 Fracción IV de las Condiciones Generales de Trabajo de "
                + "la Secretaría de Salud";
        String fecha = "del 01 de enero al 31 de diciembre de 2015";
        Date fechaIngreso = movimientoEmpleadoEntity.getFechaIngreso();
        String claveOriginal = "CLAVE ORIGINAL";
        String denominacionAlta = "DENOMINACION ALTA";
        String adscripcion = "ADSCRIPCION ";
        String fechaDesignacion = "FECHA DE DESIGNACION";
        String denominacion = "DENOMINACION";
        String posiciondos = "el Artículo 151 Párrafo Segundo de las Condiciones Generales de Trabajo, al separarse del puesto de elección "
                + "popular, deberá reincorporarse a su puesto de regularizado, en su lugar de adscripción, dentro de los seis días hábiles "
                + "siguientes, por lo que de acuerdo al Artículo 169 de las Condiciones deberá dar aviso de reincorporación a esta Dirección, "
                + "15 días antes de que se separe del puesto de elección popular";
        String directoraAdministracion = "C.P. JOEL TRINIDAD ORDOÑEZ CARRERA";

        reservacionDTO.setIdMovimiento(idMovimiento);
        reservacionDTO.setAsunto(asunto);
        reservacionDTO.setPresenteNombre(presenteNombre);
        reservacionDTO.setPresenteClaveUno(presenteClaveUno);
        reservacionDTO.setPresenteClaveDos(presenteClaveDos);
        reservacionDTO.setPosicionUno(posicionUno);
        reservacionDTO.setFecha(fecha);
        reservacionDTO.setFechaIngreso(fechaIngreso);
        reservacionDTO.setClaveOriginal(claveOriginal);
        reservacionDTO.setDenominacionAlta(denominacionAlta);
        reservacionDTO.setAdscripcion(adscripcion);
        reservacionDTO.setFechaDesignacion(fechaDesignacion);
        reservacionDTO.setDenominacion(denominacion);
        reservacionDTO.setPosiciondos(posiciondos);
        reservacionDTO.setDirectoraAdministracion(directoraAdministracion);

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