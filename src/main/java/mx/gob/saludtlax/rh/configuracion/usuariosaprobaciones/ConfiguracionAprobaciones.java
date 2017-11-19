/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.util.List;

public interface ConfiguracionAprobaciones {

    public List<UsuarioConfiguracionDTO> consultarUsuariosAprobacion(Integer idOperacion);

    public String obtenerDescripcionOperacion(Integer idOperacion);

    public void registrarUsuariosAprobacion(RegistrarUsuarioDTO dto);

    public void actualizarConfiguracionAprobacion(ActualizacionConfiguracionAprobacionDTO dto);

    public void eliminarConfiguracionAprobacion(Integer idConfiguracionAprobacion);

    public Boolean aplicaMovimientos(Integer idAccionUsuario);
}
