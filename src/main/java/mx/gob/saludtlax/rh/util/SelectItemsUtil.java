/**
 * 
 */
package mx.gob.saludtlax.rh.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.DocumentoComprobatorioDTO;
import mx.gob.saludtlax.rh.bolsatrabajo.aspirantes.EscolaridadDTO;
import mx.gob.saludtlax.rh.catalogos.CatalogoDTO;
import mx.gob.saludtlax.rh.empleados.interinatos.EnumTipoBusquedaInterinato;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumEstatusSuplencia;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumTipoConsultaSuplencia;
import mx.gob.saludtlax.rh.empleados.suplencia.EnumTipoMovimientoSuplente;
import mx.gob.saludtlax.rh.voluntarios.EnumTipoConsultaVoluntario;

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 10/03/2016-12:03:17
 */
public class SelectItemsUtil {

	private SelectItemsUtil() {
	}

	/**
	 * Retorna los tipos de sangre de los empleados
	 */
	public static List<SelectItem> listaTiposSangre() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("A+", "A+");
		SelectItem item2 = new SelectItem("A-", "A-");
		SelectItem item3 = new SelectItem("B+", "B+");
		SelectItem item4 = new SelectItem("B-", "B-");
		SelectItem item5 = new SelectItem("AB+", "AB+");
		SelectItem item6 = new SelectItem("AB-", "AB-");
		SelectItem item7 = new SelectItem("O+", "O+");
		SelectItem item8 = new SelectItem("O-", "O-");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item6);
		lista.add(item7);
		lista.add(item8);
		return lista;
	}

	/**
	 * Retorna la lista de los tipos de nacionalidades del empleado.
	 */
	public static List<SelectItem> listaNacionalidad() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("NACIONAL", "NACIONAL");
		SelectItem item2 = new SelectItem("EXTRANJERA", "EXTRANJERA");
		lista.add(item1);
		lista.add(item2);
		return lista;

	}

	public static List<SelectItem> listaEstadosCivil() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("SOLTERO/A", "SOLTERO/A");
		SelectItem item2 = new SelectItem("CASADO/A", "CASADO/A");
		SelectItem item3 = new SelectItem("DIVORCIADO/A", "DIVORCIADO/A");
		SelectItem item4 = new SelectItem("VIUDO/A", "VIUDO/A");
		SelectItem item5 = new SelectItem("UNION LIBRE", "UNION LIBRE");
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);

		return lista;
	}

	public static List<SelectItem> listaTiposSexo() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("FEMENINO", "FEMENINO");
		SelectItem item2 = new SelectItem("MASCULINO", "MASCULINO");
		lista.add(item1);
		lista.add(item2);
		return lista;
	}

	public static List<SelectItem> listaNiveles() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("1", "NIVEL 1");
		SelectItem item2 = new SelectItem("2", "NIVEL 2");
		SelectItem item3 = new SelectItem("3", "NIVEL 3");
		SelectItem item4 = new SelectItem("4", "NIVEL 4");
		SelectItem item5 = new SelectItem("5", "NIVEL 5");
		SelectItem item6 = new SelectItem("6", "NIVEL 6");
		SelectItem item7 = new SelectItem("7", "NIVEL 7");
		SelectItem item8 = new SelectItem("9", "NIVEL 9");
		SelectItem item9 = new SelectItem("11", "NIVEL 11");
		SelectItem item10 = new SelectItem("16", "NIVEL 16");
		SelectItem item11 = new SelectItem("19", "NIVEL 19");
		SelectItem item12 = new SelectItem("20", "NIVEL 20");
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item6);
		lista.add(item7);
		lista.add(item8);
		lista.add(item9);
		lista.add(item10);
		lista.add(item11);
		lista.add(item12);
		return lista;
	}

	/**
	 * Transforma todos los catalogos en una lista de SelectItem.
	 */
	public static List<SelectItem> listaCatalogos(List<CatalogoDTO> catalogos) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (!catalogos.isEmpty()) {
			for (CatalogoDTO dto : catalogos) {
				SelectItem item = new SelectItem(dto.getId(), dto.getNombre());
				lista.add(item);
			}
		}

		return lista;
	}

	/**
	 * Lista de los nombramientos.
	 */
	public static List<SelectItem> nombramientos(List<CatalogoDTO> catalogos) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (!catalogos.isEmpty()) {
			for (CatalogoDTO dto : catalogos) {
				SelectItem item = new SelectItem(dto.getNombre(), dto.getNombre());
				lista.add(item);
			}
		}

		return lista;
	}

	/**
	 * Lista de las personas con la que el aspirante puede vivir.
	 */
	public static List<SelectItem> listaViveCon() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem padres = new SelectItem("PADRES", "PADRES");
		SelectItem familia = new SelectItem("FAMILIA", "FAMILIA");
		SelectItem parientes = new SelectItem("PARIENTES", "PARIENTES");
		SelectItem solo = new SelectItem("SOLO", "SOLO");

		lista.add(padres);
		lista.add(familia);
		lista.add(parientes);
		lista.add(solo);

		return lista;
	}

	/**
	 * Lista de las personas que pueden depender del aspirante.
	 */
	public static List<SelectItem> listaDependientes() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem hijos = new SelectItem("HIJOS", "HIJOS");
		SelectItem conyuge = new SelectItem("CONYUGE", "CONYUGE");
		SelectItem padres = new SelectItem("PADRES", "PADRES");
		SelectItem otros = new SelectItem("OTROS", "OTROS");

		lista.add(hijos);
		lista.add(conyuge);
		lista.add(padres);
		lista.add(otros);
		return lista;
	}

	/**
	 * Lista de los tipos de licencia de conducir.
	 */
	public static List<SelectItem> listaTiposLicencia() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem a = new SelectItem("A", "A");
		SelectItem b = new SelectItem("B", "B");
		SelectItem c = new SelectItem("C", "C");
		SelectItem d = new SelectItem("D", "D");

		lista.add(a);
		lista.add(b);
		lista.add(c);
		lista.add(d);
		return lista;
	}

	/***
	 * Lista de escolaridades
	 * 
	 * @param listaEscolaridad
	 * @return
	 */
	public static List<SelectItem> listaEscolaridad(List<EscolaridadDTO> listaEscolaridad) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (EscolaridadDTO dto : listaEscolaridad) {
			SelectItem item = new SelectItem(dto.getIdEscolaridad(),
					dto.getEscolaridad() + " - " + dto.getGrupoAcademico());

			lista.add(item);
		}

		return lista;
	}

	/**
	 * Lista de estados de escolaridades
	 * 
	 * @param listaEstatusEscolaridad
	 * @return
	 */
	public static List<SelectItem> listaEstatusEscolaridad(List<DocumentoComprobatorioDTO> listaEstatusEscolaridad) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (DocumentoComprobatorioDTO dto : listaEstatusEscolaridad) {
			SelectItem item = new SelectItem(dto.getIdDocumentoComprobatorio(), dto.getEstatus());

			lista.add(item);
		}

		return lista;
	}

	public static List<SelectItem> listaParentescos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("CONYUGE", "CONYUGE");
		SelectItem item1 = new SelectItem("PADRES", "PADRES");
		SelectItem item2 = new SelectItem("HIJOS", "HIJOS");
		SelectItem item3 = new SelectItem("OTROS", "OTROS");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		return lista;
	}

	public static List<SelectItem> listaMovimientosEmpleado(List<CatalogoDTO> movimientos) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (!movimientos.isEmpty()) {
			for (CatalogoDTO dto : movimientos) {
				SelectItem item = new SelectItem(dto.getId(), dto.getNombre());
				lista.add(item);
			}

		}
		return lista;
	}

	public static List<SelectItem> listaEstatusAutorizacionMovimientos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("AUTORIZADO", "AUTORIZADO");
		SelectItem item2 = new SelectItem("PENDIENTE", "PENDIENTE");
		SelectItem item3 = new SelectItem("CANCELADO", "CANCELADO");
		SelectItem item4 = new SelectItem("TODOS", "TODOS");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		return lista;
	}

	public static List<SelectItem> listaFiltrosFinanciamientos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "EMPLEADOS");
		SelectItem item2 = new SelectItem(2, "SALARIOS");
		lista.add(item1);
		lista.add(item2);
		return lista;
	}

	public static List<SelectItem> listaFiltrosConsultaAspirantes() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "NOMBRE, RFC O CURP");
		SelectItem item2 = new SelectItem(2, "PROFESIÓN");
		SelectItem item3 = new SelectItem(3, "ESPECIALIDAD");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);

		return lista;
	}

	public static List<SelectItem> listaTiposContratacion() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "CONTRATO ESTATAL");
		SelectItem item2 = new SelectItem(2, "CONTRATO FEDERAL");
		SelectItem item3 = new SelectItem(3, "BASE");
		SelectItem item4 = new SelectItem(4, "CONFIANZA");
		SelectItem item5 = new SelectItem(5, "HOMOLOGADOS");
		SelectItem item12 = new SelectItem(12, "HONORARIOS");
		// SelectItem item6 = new SelectItem(6, "PASANTES");
		SelectItem item7 = new SelectItem(7, "FORMALIZADOS");
		SelectItem item8 = new SelectItem(8, "REGULARIZADOS");
		// SelectItem item9 = new SelectItem(9, "INTERINATO");
		// SelectItem item11 = new SelectItem(14, "SERVICIO SOCIAL");
		// SelectItem item12 = new SelectItem(16, "UNEMES");
		// SelectItem item13 = new SelectItem(17, "PROSPERA");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item12);
		lista.add(item7);
		lista.add(item8);
		// lista.add(item9);
		// lista.add(item11);
		// lista.add(item12);
		// lista.add(item13);
		return lista;

	}

	public static List<SelectItem> listaTiposContratosEmpleados() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "CONTRATO ESTATAL");
		SelectItem item2 = new SelectItem(2, "CONTRATO FEDERAL");

		lista.add(item1);
		lista.add(item2);

		return lista;

	}

	public static List<SelectItem> listaTipoConfiguracionProgramas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "GLOBAL");
		SelectItem item2 = new SelectItem(2, "DESGLOSADA");

		lista.add(item1);
		lista.add(item2);
		return lista;
	}

	public static List<SelectItem> listaTipoCandidato() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "CANDIDATO");
		SelectItem item2 = new SelectItem(2, "EMPLEADO ACTIVO O INACTIVO");

		lista.add(item1);
		lista.add(item2);
		return lista;
	}

	public static List<SelectItem> listaTipoPerfil(String tipoCandidato) {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "PROFESIÓN");
		SelectItem item2 = new SelectItem(2, "ESPECIALIDAD");
		SelectItem item3 = new SelectItem(3, "TODOS LOS " + tipoCandidato);

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);

		return lista;
	}

	public static List<SelectItem> listaFiltrosConsultaUbicaciones() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(4, "SIN ADSCRIPCION");
		SelectItem item2 = new SelectItem(5, "SIN LUGAR ADSCRIPCION");
		SelectItem item3 = new SelectItem(6, "SIN AREA ADSCRIPCION");
		SelectItem item4 = new SelectItem(7, "UNIDAD RESPONSABLE");
		SelectItem item5 = new SelectItem(8, "RFC O NOMBRE");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);

		return lista;
	}

	public static List<SelectItem> listaFiltrosConsultaMovimientos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "MOVIMIENTOS");
		SelectItem item2 = new SelectItem(2, "MOVIMIENTO ESPECIFICO");
		SelectItem item3 = new SelectItem(3, "RANGO DE FECHAS");
		SelectItem item4 = new SelectItem(4, "RFC O NOMBRE");

		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);

		return lista;
	}

	public static List<SelectItem> listaColorPiel() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("COLOR BLANCO", "COLOR BLANCO");
		SelectItem item1 = new SelectItem("COLOR NEGRO", "COLOR NEGRO");
		SelectItem item2 = new SelectItem("COLOR MORENO CLARO", "COLOR MORENO CLARO");
		SelectItem item3 = new SelectItem("COLOR MORENO OSCURO", "COLOR MORENO OSCURO");
		SelectItem item4 = new SelectItem("COLOR AMARILLO", "COLOR AMARILLO");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		return lista;
	}

	public static List<SelectItem> listaCabello() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("CABELLO CASTAÑO CLARO", "CABELLO CASTAÑO CLARO");
		SelectItem item1 = new SelectItem("CABELLO CASTAÑO OSCURO", "CABELLO CASTAÑO OSCURO");
		SelectItem item2 = new SelectItem("CABELLO NEGRO", "CABELLO NEGRO");
		SelectItem item3 = new SelectItem("CABELLO RUBIO", "CABELLO RUBIO");
		SelectItem item4 = new SelectItem("CABELLO ROJO", "CABELLO ROJO");
		SelectItem item5 = new SelectItem("CABELLO ALBINO", "CABELLO ALBINO");
		SelectItem item6 = new SelectItem("CABELLO ENTRECANO", "CABELLO ENTRECANO");
		SelectItem item7 = new SelectItem("CABELLO CANO", "CABELLO CANO");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item6);
		lista.add(item7);
		return lista;
	}

	public static List<SelectItem> listaFrente() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("FRENTE PEQUEÑIA", "FRENTE PEQUEÑIA");
		SelectItem item1 = new SelectItem("FRENTE MEDIANA", "FRENTE MEDIANA");
		SelectItem item2 = new SelectItem("FRENTE GRANDE", "FRENTE GRANDE");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);

		return lista;
	}

	public static List<SelectItem> listaCejas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("CEJAS ABUNDANTES", "CEJAS ABUNDANTES");
		SelectItem item1 = new SelectItem("CEJAS ESCASAS", "CEJAS ESCASAS");
		SelectItem item2 = new SelectItem("CEJAS REGULARES", "CEJAS REGULARES");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);

		return lista;
	}

	public static List<SelectItem> listaOjos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("OJOS AZULES", "OJOS AZULES");
		SelectItem item1 = new SelectItem("OJOS VERDES", "OJOS VERDES");
		SelectItem item2 = new SelectItem("OJOS CASTAÑO CLARO", "OJOS CASTAÑO CLARO");
		SelectItem item3 = new SelectItem("OJOS CASTAÑO OSCURO", "OJOS CASTAÑO OSCURO");
		SelectItem item4 = new SelectItem("OJOS PARDOS", "OJOS PARDOS");
		SelectItem item5 = new SelectItem("OJOS VERDOSOS", "OJOS VERDOSOS");
		SelectItem item6 = new SelectItem("OJOS NEGROS", "OJOS NEGROS");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);
		lista.add(item3);
		lista.add(item4);
		lista.add(item5);
		lista.add(item6);

		return lista;
	}

	public static List<SelectItem> listaNariz() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("NARIZ CONVEXA", "NARIZ CONVEXA");
		SelectItem item1 = new SelectItem("NARIZ CONCAVA", "NARIZ CONCAVA");
		SelectItem item2 = new SelectItem("NARIZ RECTILINEA", "NARIZ RECTILINEA");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);

		return lista;
	}

	public static List<SelectItem> listaBoca() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("BOCA PEQUEÑA", "BOCA PEQUEÑA");
		SelectItem item1 = new SelectItem("BOCA REGULAR", "BOCA REGULAR");
		SelectItem item2 = new SelectItem("BOCA GRANDE", "BOCA GRANDE");

		lista.add(item);
		lista.add(item1);
		lista.add(item2);

		return lista;
	}

	public static List<SelectItem> listaEstatus() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("ACTIVO", "ACTIVO");
		SelectItem item1 = new SelectItem("INACTIVO", "INACTIVO");
		lista.add(item);
		lista.add(item1);
		return lista;
	}

	public static List<SelectItem> listaReporteMovimientoEmpleado() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item = new SelectItem("Comisionado o Licencia", "Comisionado o Licencia");
		SelectItem item1 = new SelectItem("Consentrado de Altas y Bajas", "Consentrado de Altas y Bajas");
		lista.add(item);
		lista.add(item1);
		return lista;
	}

	public static List<SelectItem> listaTiposSuplentes() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem("FIJO", "FIJO");
		SelectItem item2 = new SelectItem("VARIABLE", "VARIABLE");
		lista.add(item1);
		lista.add(item2);
		return lista;
	}

	public static List<SelectItem> listaTiposContratacionLaborales() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item3 = new SelectItem(3, "NUEVO FEDERAL");
		SelectItem item5 = new SelectItem(5, "NUEVO HOMOLOGADOS");
		SelectItem item7 = new SelectItem(7, "NUEVO FORMALIZADO");
		SelectItem item8 = new SelectItem(8, "NUEVO REGULARIZADOS");
		// SelectItem item9 = new SelectItem(9, "INTERINATO");
		// SelectItem item12 = new SelectItem(16, "NUEVO UNEMES");

		lista.add(item3);
		lista.add(item5);
		lista.add(item7);
		lista.add(item8);
		// lista.add(item9);
		// lista.add(item12);

		return lista;

	}

	public static List<SelectItem> listaTiposContratacionEventual() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		SelectItem item1 = new SelectItem(1, "NUEVO CONTRATO ESTATAL");
		// SelectItem item2 = new SelectItem(2, "NUEVO CONTRATO FEDERAL");
		// SelectItem item3 = new SelectItem(3, "NUEVO PASANTE");
		// SelectItem item11 = new SelectItem(14, "NUEVO SERVICIO SOCIAL");

		lista.add(item1);
		// lista.add(item2);
		// lista.add(item3);
		// lista.add(item11);

		return lista;

	}

	public static List<SelectItem> listaEstatusSuplencias() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumEstatusSuplencia.APROBADO, EnumEstatusSuplencia.APROBADO);
		SelectItem dos = new SelectItem(EnumEstatusSuplencia.PENDIENTE, EnumEstatusSuplencia.PENDIENTE);
		SelectItem tres = new SelectItem(EnumEstatusSuplencia.RECHAZADO, EnumEstatusSuplencia.RECHAZADO);

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);

		return lista;
	}

	public static List<SelectItem> listaEstatusSuplenciasCierre() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumEstatusSuplencia.APROBADO, EnumEstatusSuplencia.APROBADO);
		SelectItem dos = new SelectItem(EnumEstatusSuplencia.CERRADA, EnumEstatusSuplencia.CERRADA);

		lista.add(uno);
		lista.add(dos);

		return lista;
	}

	public static List<SelectItem> listaTiposBusquedaInterinato() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumTipoBusquedaInterinato.POR_PERMISO, "PERMISO");
		// SelectItem dos = new
		// SelectItem(EnumTipoBusquedaInterinato.POR_LIBERACION, "VACANTE");

		lista.add(uno);
		// lista.add(dos);

		return lista;
	}

	public static List<SelectItem> listaTiposBusquedaVoluntarios() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumTipoConsultaVoluntario.NOMBRE, "NOMBRE");
		SelectItem dos = new SelectItem(EnumTipoConsultaVoluntario.ACTIVOS, "ACTIVOS");

		lista.add(uno);
		lista.add(dos);

		return lista;
	}

	public static List<SelectItem> listaTiposBusquedaSuplentes() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumTipoConsultaSuplencia.NOMBRE, "NOMBRE O RFC");
		SelectItem dos = new SelectItem(EnumTipoConsultaSuplencia.ACTIVO, "ACTIVOS");

		SelectItem tres = new SelectItem(EnumTipoConsultaSuplencia.INACTIVO, "INACTIVOS");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);

		return lista;
	}

	public static List<SelectItem> listaBusquedasRevisionSuplencias() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumTipoConsultaSuplencia.QUINCENA_POR_CRITERIO, "NOMBRE O RFC");
		SelectItem dos = new SelectItem(EnumTipoConsultaSuplencia.QUINCENAS_CENTROS_ESTATUS, "CENTRO RESPONSABILIDAD");
		lista.add(uno);
		lista.add(dos);
		return lista;
	}

	public static List<SelectItem> listaTiposMovimientosSuplentes() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem(EnumTipoMovimientoSuplente.VACACIONES, EnumTipoMovimientoSuplente.VACACIONES);
		SelectItem dos = new SelectItem(EnumTipoMovimientoSuplente.INCAPACIDAD, EnumTipoMovimientoSuplente.INCAPACIDAD);
		lista.add(uno);
		lista.add(dos);
		return lista;
	}

	public static List<SelectItem> listaFuncionesEspecificas() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("1", "MÉDICO ESPECIALISTA");
		SelectItem dos = new SelectItem("2", "MÉDICO GENERAL");
		SelectItem tres = new SelectItem("3", "CIRUJANO DENTISTA");
		SelectItem cuatro = new SelectItem("4", "ODONTÓLOGOS");
		SelectItem cinco = new SelectItem("5", "ENFERMERAS Y AUXILIAR DE ENFERMERÍA");
		SelectItem seis = new SelectItem("6", "PARAMÉDICA (TÉCNICO, LABORATORISTA)");
		SelectItem siete = new SelectItem("7", "AFINES");
		SelectItem ocho = new SelectItem("8", "SOPORTES ADMINISTRATIVOS (PERSONAL CONFIANZA)");
		SelectItem nueve = new SelectItem("9", "APOYOS ADMINISTRATIVOS (PERSONAL BASE)");
		SelectItem diez = new SelectItem("10", "PERSONAL DE MANDO");
		SelectItem once = new SelectItem("11", "INVESTIGADOR");
		SelectItem doce = new SelectItem("12", "MÉDICO RESIDENTE");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);
		lista.add(cuatro);
		lista.add(cinco);
		lista.add(seis);
		lista.add(siete);
		lista.add(ocho);
		lista.add(nueve);
		lista.add(diez);
		lista.add(once);
		lista.add(doce);
		return lista;
	}

	public static List<SelectItem> listaSubfunciones() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("1", "1");
		SelectItem dos = new SelectItem("2", "2");
		SelectItem tres = new SelectItem("3", "3");
		SelectItem cuatro = new SelectItem("4", "4");
		SelectItem cinco = new SelectItem("5", "5");
		lista.add(uno);
		lista.add(dos);
		lista.add(tres);
		lista.add(cuatro);
		lista.add(cinco);
		return lista;
	}

	public static List<SelectItem> listaTabuladorPuesto() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("40", "40");
		SelectItem dos = new SelectItem("60", "60");
		SelectItem tres = new SelectItem("100", "100");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);

		return lista;
	}

	public static List<SelectItem> listaPagadurias() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("S2929", "S2929");
		SelectItem dos = new SelectItem("61019", "61019");
		SelectItem tres = new SelectItem("X0029", "X0029");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);

		return lista;
	}

	public static List<SelectItem> listaIndicadoresdeMando() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("10", "10");
		SelectItem dos = new SelectItem("20", "20");
		SelectItem tres = new SelectItem("30", "30");
		SelectItem cuatro = new SelectItem("75", "75");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);
		lista.add(cuatro);

		return lista;
	}

	public static List<SelectItem> listaTiposUnidades() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("1", "1");
		SelectItem dos = new SelectItem("2", "2");

		lista.add(uno);
		lista.add(dos);

		return lista;
	}

	public static List<SelectItem> listaTiposPago() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("1", "1");
		SelectItem dos = new SelectItem("2", "2");

		lista.add(uno);
		lista.add(dos);

		return lista;
	}

	public static List<SelectItem> listaFinanciamientosFederal() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("1", "1");
		SelectItem dos = new SelectItem("2", "2");
		lista.add(uno);
		lista.add(dos);
		return lista;
	}

	public static List<SelectItem> listaFinanciamientos() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("01", "01-FASSA");
		SelectItem dos = new SelectItem("02", "02-SEGURO POPULAR");
		SelectItem tres = new SelectItem("03", "03-RECURSOS ESTATALES");
		SelectItem cuatro = new SelectItem("04", "04-CUOTA RECUPERACIÓN");
		SelectItem cinco = new SelectItem("05", "05-OPORTUNIDADES (SUBSIDIOS)");
		SelectItem seis = new SelectItem("06", "06-RECURSO RAMO 12 (610)");
		SelectItem siete = new SelectItem("07", "07-RECURSOS RAMO 12 (X00)");
		SelectItem ocho = new SelectItem("08", "08-FASSA + SEGURO POPULAR");
		SelectItem nueve = new SelectItem("09", "09-FASSA + RECURSOS ESTATALES");
		SelectItem diez = new SelectItem("10", "10-FASSA + RECURSOS RAMO 12");
		SelectItem once = new SelectItem("11", "11-RECURSO RAMO 12 (AFASPE)");
		SelectItem doce = new SelectItem("12", "12-SEGURO POPULAR + RECURSOS ESTATALES");
		SelectItem trece = new SelectItem("13", "13-CARAVANAS DE LA SALUD");
		SelectItem catorce = new SelectItem("14", "14-FASSA + OPORTUNIDADES + CUOTAS DE RECUPERACIÓN");
		lista.add(uno);
		lista.add(dos);
		lista.add(tres);
		lista.add(cuatro);
		lista.add(cinco);
		lista.add(seis);
		lista.add(siete);
		lista.add(ocho);
		lista.add(nueve);
		lista.add(diez);
		lista.add(once);
		lista.add(doce);
		lista.add(trece);
		lista.add(catorce);

		return lista;
	}

	public static List<SelectItem> listaJornadas() {
		List<SelectItem> lista = new ArrayList<>();
		SelectItem uno = new SelectItem("01", "JORNADA DIURNA ÁREA MÉDICA (MÁXIMO 8.0 HRS)");
		SelectItem dos = new SelectItem("02", "JORNADA DIURNA GRUPO AFÍN ADMVO. (7.0 HRS)");
		SelectItem tres = new SelectItem("03", "JORNADA MIXTA ÁREA MÉDICA (7.5 HRS)");
		SelectItem cuatro = new SelectItem("04", "JORNADA ESPECIAL (12.0 HRS)");
		SelectItem cinco = new SelectItem("05", "JORNADA DIURNA (6.0 HRS)");

		lista.add(uno);
		lista.add(dos);
		lista.add(tres);
		lista.add(cuatro);
		lista.add(cinco);

		return lista;
	}

}
