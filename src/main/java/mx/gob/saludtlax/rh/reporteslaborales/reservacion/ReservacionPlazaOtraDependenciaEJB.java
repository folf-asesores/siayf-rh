
package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

/**
 * @author Daniela Hernández
 *
 */

@Stateless
public class ReservacionPlazaOtraDependenciaEJB {

    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public ReservacionDTO obtenerReservacionOtra(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository
                .obtenerPorId(idTipoMovimiento);
        ReservacionDTO reservacionDTO = new ReservacionDTO();

        Integer idMovimiento = movimientoEmpleadoEntity
                .getIdMovimientoEmpleado();

        String asunto = "ASUNTO";
        String presenteNombre = movimientoEmpleadoEntity.getEmpleado()
                .nombreCompleto();
        String presenteClaveUno = movimientoEmpleadoEntity.getEmpleado()
                .getRfc();
        String presenteClavedos = movimientoEmpleadoEntity.getEmpleado()
                .getCurp();
        String comunicado = "externa sin goce de sueldo en el puesto con funciones de base para ocupar puesto de confianza en otra Dependencia, "
                + "me permito comunicarle que con fundamento en los Artículos 43 Fracción VIII de la Ley Federal de los Trabajadores al Servicio "
                + "del Estado y 148 Fracción II de las Condiciones Generales de Trabajo de la Secretaría de Salud";
        String fecha = "16 de julio al 31 de diciembre de 2015";
        Date fechaIngreso = movimientoEmpleadoEntity.getFechaIngreso();
        String claveOriginal = "CLAVE ORIGINAL";
        String denominacionAlta = "DENOMINACION ALTA";
        String adscripcion = "ADSCRIPCION";
        String fechaDesignacion = "FECHA DE DESIGNACION";
        String denominacion = "DENOMINACION";
        String posicionUno = "por el Artículo 151 Párrafo Segundo de las Condiciones Generales de Trabajo, al separarse del puesto de confianza, "
                + "deberá reincorporarse a su puesto de base, en su lugar de adscripción, dentro de los seis días hábiles siguientes, por lo que "
                + "deberá dar aviso de reincorporación a esta Dirección";
        String directoraAdministracion = "C.P. LUZ MARIA PORTILLO GARCIA";

        reservacionDTO.setIdMovimiento(idMovimiento);
        reservacionDTO.setAsunto(asunto);
        reservacionDTO.setPresenteNombre(presenteNombre);
        reservacionDTO.setPresenteClaveUno(presenteClaveUno);
        reservacionDTO.setPresenteClaveDos(presenteClavedos);
        reservacionDTO.setComunicado(comunicado);
        reservacionDTO.setFecha(fecha);
        reservacionDTO.setFechaIngreso(fechaIngreso);
        reservacionDTO.setClaveOriginal(claveOriginal);
        reservacionDTO.setDenominacionAlta(denominacionAlta);
        reservacionDTO.setAdscripcion(adscripcion);
        reservacionDTO.setFechaDesignacion(fechaDesignacion);
        reservacionDTO.setDenominacion(denominacion);
        reservacionDTO.setPosicionUno(posicionUno);
        reservacionDTO.setDirectoraAdministracion(directoraAdministracion);

        return reservacionDTO;
    }

    public List<ReservacionDetalleDTO> consultarPorCriterio(String criterio) {

        List<ReservacionDetalleDTO> resultado = new ArrayList<>();
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
