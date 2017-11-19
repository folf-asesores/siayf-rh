
package mx.gob.saludtlax.rh.seguridad.usuario;

import java.util.List;

public interface VistaUsuario {

    void crear(VistaUsuarioDTO dto);

    void editar(VistaUsuarioDTO dto);

    void eliminar(Integer id);

    List<VistaUsuarioDTO> obtenerLista();

    List<VistaUsuarioDTO> obtenerListaPorUsuario(Integer idUsuario);

}
