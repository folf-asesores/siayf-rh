/**
 * Copyright © 2016
 */
package mx.gob.saludtlax.rh.configuracion.banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mx.gob.saludtlax.rh.excepciones.ReglaNegocioException;
import mx.gob.saludtlax.rh.persistencia.BancoSatEntity;
import mx.gob.saludtlax.rh.persistencia.BancoSatRepository;

/**
 * @author Eduardo Mex
 * @email Lic.Eduardo_Mex@hotmail.com
 * @version 1.0
 * @since 03/06/2016 14:24:39
 */
public class BancoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -732707831982712760L;

	@Inject
	private BancoSatRepository bancoRepository;

	protected void crearBanco(BancoDTO bancoDTO) {

		String contexto = "Registro Banco: ";

		Boolean validarClave = bancoRepository.validarClaveBanco(bancoDTO.getClave());

		if (validarClave) {
			throw new ReglaNegocioException(contexto + "La clave del banco ya se encuentra registrado, ingrese otro", null);
		}

		BancoSatEntity bancoEntity = new BancoSatEntity();

		bancoEntity.setClave(bancoDTO.getClave());
		bancoEntity.setNombreCorto(bancoDTO.getNombreCorto());
		bancoEntity.setRazonSocial(bancoDTO.getRazonSocial());
		// creando Banco
		bancoRepository.crear(bancoEntity);

	}

	protected void actualizarBanco(BancoDTO bancoDTO) {

		BancoSatEntity bancoEntity = bancoRepository.obtenerPorId(bancoDTO.getIdBanco());

		bancoEntity.setClave(bancoDTO.getClave());
		bancoEntity.setNombreCorto(bancoDTO.getNombreCorto());
		bancoEntity.setRazonSocial(bancoDTO.getRazonSocial());
		// actualizando Banco
		bancoRepository.actualizar(bancoEntity);

	}

	protected void eliminarBanco(Integer idBanco) {

		BancoSatEntity bancoEntity = bancoRepository.obtenerPorId(idBanco);
		// Eliminando banco
		bancoRepository.eliminar(bancoEntity);
	}

	protected List<BancoDTO> obtenerListaBanco() {
		List<BancoDTO> listaBancoDTO = new ArrayList<BancoDTO>();
		List<BancoSatEntity> listaBanco = bancoRepository.obtenerListaBanco();

		if (!listaBanco.isEmpty()) {
			for (BancoSatEntity bancoEntity : listaBanco) {
				BancoDTO bancoDTO = new BancoDTO();

				bancoDTO.setIdBanco(bancoEntity.getIdBanco());
				bancoDTO.setClave(bancoEntity.getClave());
				bancoDTO.setNombreCorto(bancoEntity.getNombreCorto());
				bancoDTO.setRazonSocial(bancoEntity.getRazonSocial());

				listaBancoDTO.add(bancoDTO);
			}
		}
		return listaBancoDTO;
	}

}
