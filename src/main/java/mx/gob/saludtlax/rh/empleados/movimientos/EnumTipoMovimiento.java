/**
 * 
 */
package mx.gob.saludtlax.rh.empleados.movimientos;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 03/05/2016 03/05/2016
 */
public class EnumTipoMovimiento {
	public static final Integer BAJAS_DEFINITIVAS = 1;
	public static final Integer BAJAS_TEMPORALES = 2;
	public static final Integer LICENCIAS_C_S_S = 3;
	public static final Integer LICENCIAS_C_C_S = 4;
	public static final Integer LICENCIAS_D_S_S = 5;
	public static final Integer LICENCIAS_D_C_S = 6;
	public static final Integer INCAPACIDADES_MEDICAS = 7;
	public static final Integer REAUNIDACIONES = 8;
	public static final Integer ALTAS = 9;
	public static final Integer REINGRESOS_SIN_ANT = 10;
	public static final Integer REINGRESOS = 11;
	public static final Integer PROMOCIONES = 12;
	public static final Integer DESCENSOS = 13;
	public static final Integer PROMOCIONES_Y_DES_ = 14;
	public static final Integer CAMBIO_ADSCRIPCION = 15;
	public static final Integer REGISTROS = 16;
	public static final Integer PROMOCION_PUESTO_AUMENTO_PERCEPCIONES = 128;
	
	
	
	
	
	
	
	

	public static final Integer ALTA_PERSONAL_BASE = 72;
	public static final Integer ALTA_PERSONAL_CONFIANZA = 73;
	public static final Integer ALTA_PERSONAL_EVENTUAL = 76;

	public static final Integer REINGRESO_BASE_SIN_ANTECEDENTES = 79;
	public static final Integer REINGRESO_CONFIANZA_SIN_ANTECEDENTES = 80;
	public static final Integer REINGRESO_PROVISIONAL_SIN_ANTECEDENTES = 81;
	public static final Integer REINGRESO_INTERINO_SIN_ANTECEDENTES = 82;
	public static final Integer REINGRESO_EVENTUAL_SIN_ANTECEDENTES = 83;
	public static final Integer REINGRESO_CONTRATO_HONORARIOS_SIN_ANTECEDENTES = 84;
	public static final Integer REINGRESO_PERSONAL_FORMACION_SIN_ANTECEDENTES = 85;

	public static final Integer REINGRESO_BASE = 89;
	public static final Integer REINGRESO_CONFIANZA = 90;
	public static final Integer REINGRESO_PROVISIONAL = 91;
	public static final Integer REINGRESO_INTERINO = 92;
	public static final Integer REINGRESO_EVENTUAL = 93;
	public static final Integer REINGRESO_CONTRATO_HONORARIOS = 94;
	public static final Integer REINGRESO_PERSONAL_FORMACION = 95;
	public static final Integer REINGRESO_RECONSIDERACION = 96;

	public static final Integer BAJA_POR_RENUNCIA_PARA_PERSONAL_DE_BASE = 17;
	public static final Integer BAJA_POR_JUBILACION_O_PENSION_A_PERSONAL_DE_BASE = 18;
	public static final Integer BAJA_POR_DEFUNCIÓN_A_PERSONAL_DE_BASE = 19;
	public static final Integer BAJA_POR_CONCLUSIÓN_DE_NOMBRAMIENTO_O_CONTRATO_A_PERSONAL_PROVISIONAL = 20;
	public static final Integer BAJA_POR_INSUBSISTENCIA_DE_NOMBRAMIENTO_O_BECA_PERSONAL_PROVISIONAL = 21;
	public static final Integer BAJA_POR_ABANDONO_DE_EMPLEO_A_PERSONAL_DE_BASE = 22;
	public static final Integer BAJA_POR_ABANDONO_DE_LABORES_TÉCNICAS_A_PERSONAL_DE_BASE = 23;
	public static final Integer BAJA_POR_REPETIDA_INASISTENCIA_A_LABORES_TÉCNICAS_A_PERSONAL_DE_BASE = 24;
	public static final Integer BAJA_POR_RESOLUCIÓN_DEL_TRIBUNAL_FEDERAL_DE_CONCILIACIÓN_Y_ARBITRAJE_A_PERSONAL_DE_BASE = 25;
	public static final Integer BAJA_POR_RESPONSABILIDAD_ADMINISTRATIVA_DESTITUCIÓN_A_PERSONAL_DE_BASE = 26;
	public static final Integer BAJA_POR_INCAPACIDAD_TOTAL_PERMANENTE_A_PERSONAL_DE_BASE = 27;
	public static final Integer BAJA_POR_SUPRESION_DE_PUESTO_A_PERSONAL_DE_CONFIANZA = 28;
	public static final Integer BAJA_POR_DICTAMEN_JURIDICO = 29;
	public static final Integer BAJA_POR_INCUMPLIMIENTO_DE_CARTA_COMPROMISO_A_PERSONAL_DE_BASE = 30;
	public static final Integer BAJA_POR_INCUMPLIMIENTO_A_LA_NORMA_TÉCNICA_DE_ENSEÑANZA = 31;
	public static final Integer BAJA_POR_PÉRDIDA_DE_PLAZA_DE_BASE_AL_NO_PRORROGAR_A_PERSONAL_DE_BASE = 32;
	public static final Integer BAJA_POR_RETIRO_VOLUNTARIO_A_PERSONAL_DE_BASE = 33;
	public static final Integer BAJA_POR_ENFERMEDAD_NO_PROFESIONAL_CONTAGIOSA = 34;
	public static final Integer BAJA_POR_PRISION_PREVENTIVA = 35;
	public static final Integer BAJA_POR_INVESTIGACION_O_AUDITORIA = 36;
	public static final Integer BAJA_POR_SUSPENSION_CONCERTADA = 37;
	public static final Integer BAJA_POR_RESPONSABILIDAD_ADMINISTRATIVA = 38;
	public static final Integer BAJA_EN_SISTEMA_POR_PROMOCIÓN_O_DESCENSO = 39;
	public static final Integer BAJA_EN_SISTEMA_POR_PROMOCIÓN_O_DESCENSO_ESCALAFONARIO = 40;
	public static final Integer BAJA_EN_SISTEMA_POR_CAMBIO_DE_ADSCRIPCIÓN = 41;

	public static final Integer SANCIONES_ADMINISTRATIVAS = 11;
	public static final Integer PAGOS_DIVERSOS = 12;
	public static final Integer DESCUENTOS_DIVERSOS = 13;
	public static final Integer MODIFICACION_DATOS_PERSONALES = 14;
	public static final Integer PAGO_PERSONAL_EVENTUAL_SUSTITUTO = 15;
	public static final Integer COMPENSACION = 16;

	public static final Integer LICENCIA_POR_INCAPACIDAD_MEDICA = 57;

	public static String toString(Integer movimiento) {
		String descripcion = "";
		switch (movimiento) {
		case 1:
			descripcion = "plantilla";
			break;
		case 2:
			descripcion = "baja";
			break;
		case 3:
			descripcion = "licencia";
			break;
		case 4:
			descripcion = "promocion";
			break;
		case 5:
			descripcion = "descenso";
			break;
		case 6:
			descripcion = "reanudacion";
			break;
		case 7:
			descripcion = "nuevo ingreso";
			break;
		case 8:
			descripcion = "reingreso";
			break;
		case 9:
			descripcion = "cambio adscripcion";
			break;
		case 10:
			descripcion = "prima quincenal";
			break;
		case 11:
			descripcion = "sancion administrativa";
			break;
		case 12:
			descripcion = "pago diverso";
			break;
		case 13:
			descripcion = "descuento diverso";
			break;
		case 14:
			descripcion = "modificacion datos personales";
			break;
		case 15:
			descripcion = "pago personal eventual sustituto";
			break;
		case 16:
			descripcion = "compensacion";
			break;
		}

		return descripcion;
	}

}
