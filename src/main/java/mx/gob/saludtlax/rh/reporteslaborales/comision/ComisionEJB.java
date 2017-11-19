
package mx.gob.saludtlax.rh.reporteslaborales.comision;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoEntity;
import mx.gob.saludtlax.rh.persistencia.MovimientoEmpleadoRepository;

@Stateless
public class ComisionEJB {

    @Inject
    private MovimientoEmpleadoRepository movimientoEmpleadoRepository;

    public ComisionOficialDTO obtenerComisionOficial(Integer idTipoMovimiento) {

        MovimientoEmpleadoEntity movimientoEmpleadoEntity = movimientoEmpleadoRepository.obtenerPorId(idTipoMovimiento);
        ComisionOficialDTO comisionOficialDTO = new ComisionOficialDTO();

        Integer idMovimiento = movimientoEmpleadoEntity.getIdMovimientoEmpleado();
        String inventarioVacante = "";

        if (movimientoEmpleadoEntity.getInventarioVacante() != null) {
            if (movimientoEmpleadoEntity.getInventarioVacante().getAdscripcion() != null) {
                inventarioVacante = movimientoEmpleadoEntity.getInventarioVacante().getAdscripcion().getAdscripcion();
            }
        }

        String nombreCompleto = movimientoEmpleadoEntity.getEmpleado().getNombreCompleto();
        String rfc = movimientoEmpleadoEntity.getEmpleado().getRfc();
        String curp = movimientoEmpleadoEntity.getEmpleado().getCurp();
        String lugar = inventarioVacante;
        String adscripcion = movimientoEmpleadoEntity.getMotivoPermiso();
        String fecha = movimientoEmpleadoEntity.getFechaInicioPermiso() + " al " + movimientoEmpleadoEntity.getFechaFinPermiso();
        String directorAdministracion = "C.P. Luz Maria Portillo Garcia";

        comisionOficialDTO.setIdMovimiento(idMovimiento);
        comisionOficialDTO.setNombreCompleto(nombreCompleto);
        comisionOficialDTO.setRfc(rfc);
        comisionOficialDTO.setCurp(curp);
        comisionOficialDTO.setLugar(lugar);
        comisionOficialDTO.setAdscripcion(adscripcion);
        comisionOficialDTO.setFecha(fecha);
        comisionOficialDTO.setDirectorAdministracion(directorAdministracion);

        return comisionOficialDTO;
    }

    public List<ComisionDetalleDTO> consultarPorCriterio(String criterio) {

        List<ComisionDetalleDTO> resultado = new ArrayList<>();
        List<MovimientoEmpleadoEntity> movimientoEmpleadoEntityList = null;

        try {
            movimientoEmpleadoEntityList = movimientoEmpleadoRepository.consultarMovimientosPorRfc(criterio);
        } catch (Exception ex) {
        }

        if (movimientoEmpleadoEntityList != null) {

            for (int i = 0; i < movimientoEmpleadoEntityList.size(); i++) {
                MovimientoEmpleadoEntity m = movimientoEmpleadoEntityList.get(i);

                ComisionDetalleDTO dto = new ComisionDetalleDTO();

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
