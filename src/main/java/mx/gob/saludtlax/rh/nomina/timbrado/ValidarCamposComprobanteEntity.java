package mx.gob.saludtlax.rh.nomina.timbrado;

import java.io.Serializable;

import mx.gob.saludtlax.rh.persistencia.ComprobanteEntity;

public class ValidarCamposComprobanteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1904185811985775190L;

	public  static boolean esValido(ComprobanteEntity comprobanteNomina) {

		if (comprobanteNomina.getNombre() == null) {
			return false;
		}

		if (comprobanteNomina.getNombre().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getrFC() == null) {
			return false;
		}

		if (comprobanteNomina.getrFC().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getCodigoPostal() == null) {
			return false;
		}

		if (comprobanteNomina.getCodigoPostal().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getEstado() == null) {
			return false;
		}

		if (comprobanteNomina.getEstado().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getMunicipio() == null) {
			return false;
		}

		if (comprobanteNomina.getMunicipio().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getColonia() == null) {
			return false;
		}
		if (comprobanteNomina.getColonia().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getNoExterior() == null) {
			return false;
		}
		if (comprobanteNomina.getNoExterior().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getCalle() == null) {
			return false;
		}
		if (comprobanteNomina.getCalle().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getSalarioDiarioIntegrado() == null) {
			return false;
		}
		if (comprobanteNomina.getRiesgoPuesto() == null) {
			return false;
		}
		if (comprobanteNomina.getSalarioBaseCotizacionAport() == null) {
			return false;
		}

		if (comprobanteNomina.getPeriocidadPago() == null) {
			return false;
		}
		if (comprobanteNomina.getTipoJornada() == null) {
			return false;
		}
		if (comprobanteNomina.getTipoJornada().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getTipoContrato() == null) {
			return false;
		}
		if (comprobanteNomina.getTipoContrato().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getPuesto() == null) {
			return false;
		}
		if (comprobanteNomina.getPuesto().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getAntiguedad() == null) {
			return false;
		}

		if (comprobanteNomina.getFechaInicioRelacionLaboral() == null) {
			return false;
		}
		if (comprobanteNomina.getBanco() == null) {
			return false;
		}
		if (comprobanteNomina.getBanco().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getcLABE() == null) {
			return false;
		}
		if (comprobanteNomina.getcLABE().length() == 0) {
			return false;
		}

		if (comprobanteNomina.getDepartamento() == null) {
			return false;
		}
		if (comprobanteNomina.getDepartamento().length() == 0) {
			return false;
		}
		if (comprobanteNomina.getNumeroDiasPagados() == null) {
			return false;
		}
		if (comprobanteNomina.getFechaInicialPago() == null) {
			return false;
		}
		if (comprobanteNomina.getFechaFinalPago() == null) {
			return false;
		}
		if (comprobanteNomina.getFechaPago() == null) {
			return false;
		}
		
		if(comprobanteNomina.getNumeroSeguridadSocial() ==null){
			return false;
		}
		if(comprobanteNomina.getNumeroSeguridadSocial().length() == 0){
			return false;
		}
		if(comprobanteNomina.getcURP() == null){
			return false;
		}
		if(comprobanteNomina.getcURP().length() == 0){
			return false;
		}
		if(comprobanteNomina.getNumeroEmpleado() == null){
			return false;
		}
		if(comprobanteNomina.getNumeroEmpleado().length() == 0){
			return false;
		}
		

		return true;

	}

}
