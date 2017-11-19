/*
 *
 */

package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 * 
 * @version 1.0
 * @since 03/06/2016 14:23:36
 */
@Stateless
public class BancoEJB implements Banco, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6340143902837762923L;

    @Inject
    private BancoService bancoService;

    @Override
    public void crearBanco(BancoDTO bancoDTO) {
        bancoService.crearBanco(bancoDTO);
    }

    @Override
    public void actualizarBanco(BancoDTO bancoDTO) {
        bancoService.actualizarBanco(bancoDTO);
    }

    @Override
    public void eliminarBanco(Integer idBanco) {
        bancoService.eliminarBanco(idBanco);
    }

    @Override
    public List<BancoDTO> obtenerListaBanco() {
        return bancoService.obtenerListaBanco();
    }

}
