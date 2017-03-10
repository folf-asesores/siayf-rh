/**
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

/**
 * @author Eduardo Mex
 *
 */
public class EnumEstatusProductoNomina {

	public static final int ABIERTO = 1;
	public static final int PREVALIDACION = 2;
	public static final int VALIDADO = 3;
	public static final int AUTORIZADO = 4;
	public static final int PAGADO = 5;
	public static final int TIMBRADO = 7;
	public static final int RETENIDO = 8;
	public static final int CANCELADO = 9;
	public static final int IMPROCEDENTE = 10;

	public static String obtenerEstatus(int idEstatus) {

		String estatus = "";

		switch (idEstatus) {

		case ABIERTO:
			estatus = "Abierto";
			break;
		case PREVALIDACION:
			estatus = "Prevalidación";
			break;
		case VALIDADO:
			estatus = "Valido";
			break;

		case AUTORIZADO:
			estatus = "Autorizado";
			break;

		case PAGADO:
			estatus = "Pagado";
			break;
		case TIMBRADO:
			estatus = "Timbrado";
			break;
		case RETENIDO:
			estatus = "Retenido";
			break;
		case CANCELADO:
			estatus = "Cancelado";
			break;
		case IMPROCEDENTE:
			estatus = "Improcedente";
			break;

		default:
			estatus = "Estatus Invalido";
			break;

		}
		return estatus;
	}

}
