
package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioCodigoError;
import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.excepciones.ValidacionCodigoError;
import mx.gob.saludtlax.rh.excepciones.ValidacionException;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAprobacionEntity;
import mx.gob.saludtlax.rh.persistencia.ConfiguracionAprobacionRepository;
import mx.gob.saludtlax.rh.persistencia.OperacionSistemaEntity;
import mx.gob.saludtlax.rh.persistencia.OperacionSistemaRepository;
import mx.gob.saludtlax.rh.persistencia.TipoMovimientoEmpleadoRepository;
import mx.gob.saludtlax.rh.persistencia.UsuarioEntity;
import mx.gob.saludtlax.rh.persistencia.UsuarioRepository;
import mx.gob.saludtlax.rh.seguridad.usuario.UsuarioDTO;
import mx.gob.saludtlax.rh.siif.reportarcontratos.BusinessException;
import mx.gob.saludtlax.rh.util.ValidacionUtil;

public class ConfiguracionAprobacionService {
    @Inject
    private UsuarioRepository usuarioRepository;
    @Inject
    private ConfiguracionAprobacionRepository accionesUsuariosRepository;
    @Inject
    private OperacionSistemaRepository operacionSistemaRepository;
    @Inject
    private TipoMovimientoEmpleadoRepository tipoMovimientoEmpleadoRepository;

    public void crearConfiguracionAutorizacion(RegistrarUsuarioDTO DTO) {

        Integer acc = DTO.getIdAccion();

        for (Integer i : DTO.getUsuarios()) {

            if (accionesUsuariosRepository.usuarioPorAcciones(acc, i) != null) {
                throw new ReglaNegocioException("El usuario ya tiene esa accion", ReglaNegocioCodigoError.YA_REGISTRADO);
            } else if (accionesUsuariosRepository.usuarioPorAcciones(acc, i) == null) {
                ConfiguracionAprobacionEntity entity = new ConfiguracionAprobacionEntity();
                entity.setUsuario(usuarioRepository.obtenerPorId(i));
                entity.setAccion(operacionSistemaRepository.obtenerPorId(acc));
                entity.setEstatus(EnumEstatusConfiguracion.ACTIVO);

                accionesUsuariosRepository.crear(entity);
            }
        }

    }

    protected void actualizarConfiguracionAprobacion(ActualizacionConfiguracionAprobacionDTO dto) {
        ConfiguracionAprobacionEntity entity = accionesUsuariosRepository.obtenerPorId(dto.getIdConfiguracionAprobacion());

        entity.setAccion(operacionSistemaRepository.obtenerPorId(dto.getIdAccionUsuario()));
        entity.setUsuario(usuarioRepository.obtenerPorId(dto.getIdUsuario()));

        if (dto.getEstatus() == 1) {

            entity.setEstatus(EnumEstatusConfiguracion.INACTIVO);

        } else if (dto.getEstatus() == 2) {

            entity.setEstatus(EnumEstatusConfiguracion.ACTIVO);

        }

        if (entity.getAccion() != null && entity.getAccion().getAplicaMovimiento()) {
            entity.setTipoMovimientoEmpleado(tipoMovimientoEmpleadoRepository.obtenerPorId(dto.getIdTipoMovimiento()));
        }
        accionesUsuariosRepository.actualizar(entity);
    }

    public void eliminaConfiguracionAutorizacion(List<UsuarioConfiguracionDTO> DTO) {
        try {
            for (UsuarioConfiguracionDTO acciones : DTO) {
                Integer ida = acciones.getIdAccionUsuario();
                accionesUsuariosRepository.eliminarPorId(ida);
            }
        } catch (PersistenceException ex) {
            throw new BusinessException("El usuario no se puede borrar");
        }
    }

    public void actualizarConfiguracionAutorizacion(List<UsuarioConfiguracionDTO> DTO, Integer activo) {
        try {
            if (activo == 1) {
                for (UsuarioConfiguracionDTO acciones : DTO) {
                    ConfiguracionAprobacionEntity entity = accionesUsuariosRepository.obtenerPorId(acciones.getIdAccionUsuario());
                    entity.setEstatus(EnumEstatusConfiguracion.INACTIVO);
                    accionesUsuariosRepository.actualizar(entity);
                }
            } else {
                for (UsuarioConfiguracionDTO acciones : DTO) {
                    ConfiguracionAprobacionEntity entity = accionesUsuariosRepository.obtenerPorId(acciones.getIdAccionUsuario());
                    entity.setEstatus(EnumEstatusConfiguracion.ACTIVO);
                    accionesUsuariosRepository.actualizar(entity);
                }
            }
        } catch (PersistenceException ex) {
            throw new BusinessException("El usuario no se puede borrar");
        }
    }

    public void eliminaConfiguracionAutorizacion(Integer id) {
        accionesUsuariosRepository.eliminarPorId(id);
    }

    public void activar(UsuarioConfiguracionDTO dto) {
        ConfiguracionAprobacionEntity entity = accionesUsuariosRepository.obtenerPorId(dto.getIdAccionUsuario());
        // entity.setActivo(dto.getActivo());
        accionesUsuariosRepository.actualizar(entity);
    }

    public List<UsuarioDTO> obtenerListaUsuarios() {
        List<UsuarioDTO> UsuarioDTO = new ArrayList<>();
        for (UsuarioEntity usuario : usuarioRepository.consultarUsuariosActivos()) {
            UsuarioDTO DTO = new UsuarioDTO();
            DTO.setIdUsuario(usuario.getIdUsuario());
            DTO.setUserName(usuario.getUserName());
            DTO.setNombre(usuario.getNombre());
            DTO.setApellidoPaterno(usuario.getApellidoPaterno());
            DTO.setApellidoMaterno(usuario.getApellidoMaterno());
            UsuarioDTO.add(DTO);
        }
        return UsuarioDTO;
    }

    public List<UsuarioConfiguracionDTO> consultarUsuariosAprobacion(Integer idOperacion) {
        if (!ValidacionUtil.esNumeroPositivo(idOperacion)) {
            throw new ValidacionException("La aprobacion es requerida.", ValidacionCodigoError.VALOR_REQUERIDO);
        }
        List<ConfiguracionAprobacionEntity> configuraciones = accionesUsuariosRepository.consultarUsuariosConfiguraciones(idOperacion);
        List<UsuarioConfiguracionDTO> lista = new ArrayList<>();
        if (!configuraciones.isEmpty()) {
            for (ConfiguracionAprobacionEntity c : configuraciones) {
                UsuarioConfiguracionDTO dto = new UsuarioConfiguracionDTO();

                dto.setIdConfiguracionAprobacion(c.getIdConfiguracionAprobacion());
                dto.setAccion(c.getAccion().getOperacion());
                dto.setIdAccionUsuario(c.getAccion().getIdOperacion());
                dto.setEstatus(c.getEstatus());
                dto.setUsuario(c.getUsuario().nombreCompleto());
                dto.setIdUsuario(c.getUsuario().getIdUsuario());
                if (c.getTipoMovimientoEmpleado() != null) {
                    dto.setMovimiento(c.getTipoMovimientoEmpleado().getMovimiento());
                    dto.setIdTipoMovimiento(c.getTipoMovimientoEmpleado().getIdTipoMovimiento());
                } else {
                    dto.setMovimiento("SIN TIPO MOVIMIENTO");
                }
                lista.add(dto);
            }
        }

        return lista;
    }

    public String obtenerDescripcionOperacion(Integer idOperacion) {
        return operacionSistemaRepository.obtenerDescripcionOperacion(idOperacion);

    }

    public Boolean obtenerAplicaMovimientos(Integer idAccionUsuario) {
        OperacionSistemaEntity accion = operacionSistemaRepository.obtenerPorId(idAccionUsuario);
        return accion.getAplicaMovimiento() == null ? Boolean.FALSE : accion.getAplicaMovimiento();
    }

}
