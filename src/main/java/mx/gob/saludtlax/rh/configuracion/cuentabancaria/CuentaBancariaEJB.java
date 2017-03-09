package mx.gob.saludtlax.rh.configuracion.cuentabancaria;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CuentaBancariaEJB {

	@Inject
	private CuentaBancariaService cuentaBancariaService;

	public List<CuentaBancariaDTO> obtenerCuentaBancariaLista() {
		List<CuentaBancariaDTO> cuentaBancariaLista = cuentaBancariaService.listaCuentaBancaria();
		return cuentaBancariaLista;
	}

	public CuentaBancariaDTO obtenerCuentaBancaria(CuentaBancariaDTO cuentaBancaria) {
		CuentaBancariaDTO dto = cuentaBancariaService.obtenerCuentaBancariaPorId(cuentaBancaria.getIdCuentaBancaria());
		return dto;
	}
	public CuentaBancariaDTO nuevoCuentaBancaria() {
		return cuentaBancariaService.nuevoCuentaBancaria();
	}

	public CuentaBancariaDTO crearCuentaBancaria(CuentaBancariaDTO dto) {
		return cuentaBancariaService.crearCuentaBancaria(dto);
	}

	public CuentaBancariaDTO actualizarCuentaBancaria(CuentaBancariaDTO dto) {
		return cuentaBancariaService.actualizarCuentaBancaria(dto);
	}

	public void  eliminarCuentaBancaria(CuentaBancariaDTO dto) {
		cuentaBancariaService.eliminarCuentaBancaria(dto);
	}
}