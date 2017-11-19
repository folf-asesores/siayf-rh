
package mx.gob.saludtlax.rh.configuracion.banco;

import java.util.List;

/**
 *
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:23:18
 */
public interface Banco {

    void crearBanco(BancoDTO bancoDTO);

    void actualizarBanco(BancoDTO bancoDTO);

    void eliminarBanco(Integer idBanco);

    List<BancoDTO> obtenerListaBanco();

}
