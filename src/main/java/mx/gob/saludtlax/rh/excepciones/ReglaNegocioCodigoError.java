/**
 * 
 */
package mx.gob.saludtlax.rh.excepciones;

/**
 * @author Leila Schiaffini Ehuan
 * @since 25/06/2016 10:54:56
 * 
 */
public enum ReglaNegocioCodigoError implements CodigoError {
        CURP_DUPLICADA(2000),
        RFC_DUPLICADA(2001),
        DEPENDIENTE_MAXIMO(2002),
        CODIGO_ESTRATEGIA_DUPLICADO(2003),
        CLAVE_AREA_DUPLICADA(2004),
        PROYECTO_DUPLICADO(2005),
        SIN_REGISTRO(2006),
        YA_AUTORIZADO(2007),
        NOTIFICACION_NO_ENCONTRADA(2008),
        CANDIDATO_REPETIDO(2009),
        EMPLEADO_REPETIDO(2010), 
        CONFIGURACION_NO_EXISTE(2011),
        YA_REGISTRADO(2012),
        VACANTE_NO_DISPONIBLE(2013),
        VACANTE_OCUPADA(2014),
        /** Cuando se intenta guardar un adjunto que es unico y ya se ha adjuntado otro. */
        ADJUNTO_UNICO(2015),
        EMPLEADO_CON_PUESTO_ACTIVO(2016),
        ASPIRANTE_SIN_HISTORIAL(2017),
        RFC_REGISTRADO(2018),
        CURP_REGISTRADA(2019),
        SUELDO_INCORRECTO(2020),
        VACANTE_DISPONIBLE(2021),
        UBICACION_NO_AUTORIZADA(2022),
        UBICACION_INCORRECTA(2023),
        MOVIMIENTO_NO_AUTORIZADO(2024),
        USUARIO_NO_ENCONTRADO(2025),
        /** Cuando el empleado o aspirante tiene más de un expediente. */
        EXISTE_MAS_DE_UN_EXPEDIENTE(2026),
        /** Cuando se trata de registrar un dependiente económico a un empleado que no tiene dependientes. */
        EMPLEADO_NO_TIENE_DEPENDIENTES(2027),
        /** Cuando un dependiente económico tiene el mismo nombre que otro ya registrado. */
        DEPENDIENTE_REGISTRADO(2028), 
        /** Cuando el empleado no se ha encontrado. */
        EMPLEADO_NO_ENCONTRADO(2029),
        /** Cuando el empleado no está activo en el sistema.  */
        EMPLEADO_INACTIVO(2030),
        /** Cuando el aspirante está contratado o ha sido contratado. */
        ASPIRANTE_AUTORIZADO (2031),
        /** Cuando el aspirante cuenta con un estatus de rechazo. */
        ASPIRANTE_RECHAZADO(2032), 
	/** Cuando tiene una postulación activa. */
	POSTULACION_ACTIVA(2033),
        /** Cuando no se ha indicado el adjunto. */
        ADJUNTO_REQUERIDO(2034), 
        /** Cuando de intenta guardar un adjunto y no se ha aperturado el expediente. */
        EXPEDIENTE_SIN_APERTURAR(2035),
        EMPLEADO_HABILITADO(2036),
        QUINCENA_CERRADA (2037),
        FECHA_INCORRECTA(2038),
        ESTATUS_INCORRECTO(2039),
        TABULADOR_NO_CONFIGURADO(2040),
        NUMERO_EMPLEADO(2041),
        /** Cuando de se trata de registrar un correo para la notificacion de errores que ya existe. */
        CORREO_NOTIFICACION_DUPLICADO(2042),
        ERROR_QUINCENA_ACTIVA(2043);

	ReglaNegocioCodigoError(int numero) {
		this.numero = numero;
	}

	@Override
	public int getNumero() {

		return numero;
	}

	private final int numero;
}
