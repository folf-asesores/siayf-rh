package mx.gob.saludtlax.rh.configuracion.usuariosaprobaciones;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ConfiguracionAprobacionesBean implements ConfiguracionAprobaciones {

	@Inject
	private ConfiguracionAprobacionService configuracionAutorizacionService;

	@Override
	public List<UsuarioConfiguracionDTO> consultarUsuariosAprobacion(Integer idOperacion) {
		return configuracionAutorizacionService.consultarUsuariosAprobacion(idOperacion);
	}

	@Override
	public String obtenerDescripcionOperacion(Integer idOperacion) {
		return configuracionAutorizacionService.obtenerDescripcionOperacion(idOperacion);

	}

	@Override
	public void registrarUsuariosAprobacion(RegistrarUsuarioDTO dto) {

		configuracionAutorizacionService.crearConfiguracionAutorizacion(dto);

	}

	@Override
	public void actualizarConfiguracionAprobacion(ActualizacionConfiguracionAprobacionDTO dto) {
		configuracionAutorizacionService.actualizarConfiguracionAprobacion(dto);
	}

	@Override
	public void eliminarConfiguracionAprobacion(Integer idConfiguracionAprobacion) {

		configuracionAutorizacionService.eliminaConfiguracionAutorizacion(idConfiguracionAprobacion);
	}

	@Override
	public Boolean aplicaMovimientos(Integer idAccionUsuario) {
		return configuracionAutorizacionService.obtenerAplicaMovimientos(idAccionUsuario);
	}

}
