package mx.gob.saludtlax.rh.nomina.importarconstancia;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import mx.gob.saludtlax.rh.retenciones.RetencionesCFDIServices;
import mx.gob.saludtlax.rh.persistencia.ConstanciaSueldoRepository;
import mx.gob.saludtlax.rh.persistencia.ConstanciaSueldoEntity;

@Stateless
public class ImportarConstanciaSueldoEJB {

	private ConstanciaSueldoEntity constanciaDTO = new ConstanciaSueldoEntity();

	@Inject
	ConstanciaSueldoRepository constanciaSueldoDAO;
	@Inject
	RetencionesCFDIServices retencionesCFDIServices;

	public void guardarConstancia(String[] array,ConstanciaSueldoDTO constanciaSueldoDTO) {
		try {
			constanciaDTO = new ConstanciaSueldoEntity();
			constanciaDTO.setMesInicial(((array[0]).equals(" ")) ? null : Integer.parseInt(array[0]));
			constanciaDTO.setMesFinal(((array[1]).equals(" ")) ? null : Integer.parseInt(array[1]));
			constanciaDTO.setRFC(((array[2]).equals(" ")) ? null : String.valueOf(array[2]));
			constanciaDTO.setCURP(((array[3]).equals(" ")) ? null : String.valueOf(array[3]));
			constanciaDTO.setApellidoPaterno(((array[4]).equals(" ")) ? null : String.valueOf(array[4]));
			constanciaDTO.setApellidoMaterno(((array[5]).equals(" ")) ? null : String.valueOf(array[5]));
			constanciaDTO.setNombre(((array[6]).equals(" ")) ? null : String.valueOf(array[6]));
			constanciaDTO.setAreaGeoSalarioMinimo(((array[7]).equals(" ")) ? null : Integer.parseInt(array[7]));
			constanciaDTO.setRealizoCalculoAnual(((array[8]).equals(" ")) ? null : Integer.parseInt(array[8]));
			constanciaDTO.setTarifaEjercicioDeclara(((array[9]).equals(" ")) ? null : Integer.parseInt(array[9]));
			constanciaDTO.setTarifaUtilizadaActualizada(((array[10]).equals(" ")) ? null : new BigDecimal(array[10]));
			constanciaDTO.setSubsidioAplicado(((array[11]).equals(" ")) ? null : new BigDecimal(array[11]));
			constanciaDTO.setTrabajadorSindicalizado(((array[12]).equals(" ")) ? null : Integer.parseInt(array[12]));
			constanciaDTO.setClaveSalarioAsimilado(((array[13]).equals(" ")) ? null : String.valueOf(array[13]));
			constanciaDTO.setClaveEntidadFederativa(((array[14]).equals(" ")) ? null : Integer.parseInt(array[14]));
			constanciaDTO.setRFCPatronPrimero(((array[15]).equals(" ")) ? null : String.valueOf(array[15]));
			constanciaDTO.setRFCPatronSegundo(((array[16]).equals(" ")) ? null : String.valueOf(array[16]));
			constanciaDTO.setRFCPatronTercero(((array[17]).equals(" ")) ? null : String.valueOf(array[17]));

			constanciaDTO.setRFCPatronCuarto(((array[18]).equals(" ")) ? null : String.valueOf(array[18]));
			constanciaDTO.setRFCPatronQuinto(((array[19]).equals(" ")) ? null : String.valueOf(array[19]));
			constanciaDTO.setRFCPatronSexto(((array[20]).equals(" ")) ? null : String.valueOf(array[20]));
			constanciaDTO.setRFCPatronSeptimo(((array[21]).equals(" ")) ? null : String.valueOf(array[21]));
			constanciaDTO.setRFCPatronOctavo(((array[22]).equals(" ")) ? null : String.valueOf(array[22]));
			constanciaDTO.setRFCPatronNoveno(((array[23]).equals(" ")) ? null : String.valueOf(array[23]));
			constanciaDTO.setRFCPatronDecimo(((array[24]).equals(" ")) ? null : String.valueOf(array[24]));

			constanciaDTO.setAportacionesVoluntarias(((array[25]).equals(" ")) ? null : new BigDecimal(array[25]));
			constanciaDTO
					.setAplicoAportacionesVoluntarias((((array[26]).equals(" ")) ? null : Integer.parseInt(array[26])));
			constanciaDTO.setAportacionesVoluntariasDeducibles(
					((array[27]).equals(" ")) ? null : Integer.parseInt(array[27]));
			constanciaDTO.setPagosSeparacion((((array[28]).equals(" ")) ? null : Integer.parseInt(array[28])));
			constanciaDTO.setSalariosAsimilados((((array[29]).equals(" ")) ? null : Integer.parseInt(array[29])));
			constanciaDTO
					.setPagosEfectuadosTrabajadores((((array[30]).equals(" ")) ? null : Integer.parseInt(array[30])));
			constanciaDTO.setSueldoGravado(((array[31]).equals(" ")) ? null : new BigDecimal(array[31]));
			constanciaDTO.setSueldoExento(((array[32]).equals(" ")) ? null : new BigDecimal(array[32]));
			constanciaDTO.setGratificacionAnualGravado(((array[33]).equals(" ")) ? null : new BigDecimal(array[33]));
			constanciaDTO.setGratificacionAnualExento(((array[34]).equals(" ")) ? null : new BigDecimal(array[34]));
			constanciaDTO.setViaticosGravado(((array[35]).equals(" ")) ? null : new BigDecimal(array[35]));
			constanciaDTO.setViaticosExento(((array[36]).equals(" ")) ? null : new BigDecimal(array[36]));
			constanciaDTO.setTiempoExtraordinarioGravado(((array[37]).equals(" ")) ? null : new BigDecimal(array[37]));
			constanciaDTO.setTiempoExtraordinarioExento((((array[38]).equals(" ")) ? null : new BigDecimal(array[38])));
			constanciaDTO.setPrimaVacacionalGravado((((array[39]).equals(" ")) ? null : new BigDecimal(array[39])));
			constanciaDTO.setPrimaVacacionalExento((((array[40]).equals(" ")) ? null : new BigDecimal(array[40])));

			constanciaDTO.setPrimaDominicalGravado((((array[41]).equals(" ")) ? null : new BigDecimal(array[41])));
			constanciaDTO.setPrimaDominicalExento((((array[42]).equals(" ")) ? null : new BigDecimal(array[42])));

			constanciaDTO.setParticipacionTrabajadoresUtilidades(
					(((array[43]).equals(" ")) ? null : Integer.parseInt(array[43])));
			constanciaDTO.setSegundaParticipacionTrabajadoresUtilidades(
					(((array[44]).equals(" ")) ? null : Integer.parseInt(array[44])));

			constanciaDTO.setReembolsoGastos((((array[45]).equals(" ")) ? null : new BigDecimal(array[45])));
			constanciaDTO.setReembolsoGastosMedicos((((array[46]).equals(" ")) ? null : String.valueOf(array[46])));

			constanciaDTO.setFondoAhorroGravado(((array[47]).equals(" ")) ? null : String.valueOf(array[47]));		
			constanciaDTO.setFondoAhorroExento((((array[48]).equals(" ")) ? null : new BigDecimal(array[48])));
			constanciaDTO.setCajaAhorroGravado((((array[49]).equals(" ")) ? null : new BigDecimal(array[49])));

			constanciaDTO.setCajaAhorroExento((((array[50]).equals(" ")) ? null : new BigDecimal(array[50])));
			constanciaDTO.setValesDespensaGravado((((array[51]).equals(" ")) ? null : new BigDecimal(array[51])));
			constanciaDTO.setValesDespensaExento((((array[52]).equals(" ")) ? null : new BigDecimal(array[52])));

			constanciaDTO.setAyudaGastosFuneralGravado((((array[53]).equals(" ")) ? null : new BigDecimal(array[53])));
			constanciaDTO.setAyudaGastosFuneralExento((((array[54]).equals(" ")) ? null : new BigDecimal(array[54])));
			constanciaDTO
					.setContribucionTrabajadorGravado((((array[55]).equals(" ")) ? null : new BigDecimal(array[55])));
			constanciaDTO
					.setContribucionTrabajadorExento((((array[56]).equals(" ")) ? null : new BigDecimal(array[56])));
			constanciaDTO.setPremiosPuntualidadGravado((((array[57]).equals(" ")) ? null : new BigDecimal(array[57])));
			constanciaDTO.setPremiosPuntualidadExento((((array[58]).equals(" ")) ? null : new BigDecimal(array[58])));
			constanciaDTO.setPrimaSeguroVidaGravado((((array[59]).equals(" ")) ? null : new BigDecimal(array[59])));
			constanciaDTO.setPrimaSeguroVidaExento((((array[60]).equals(" ")) ? null : new BigDecimal(array[60])));

			constanciaDTO.setSeguroGastosMedicosMayoresGravado(
					(((array[61]).equals(" ")) ? null : new BigDecimal(array[61])));
			constanciaDTO.setSeguroGastosMedicosMayoresExento(
					(((array[62]).equals(" ")) ? null : new BigDecimal(array[62])));
			constanciaDTO.setValesRestauranteGravado((((array[63]).equals(" ")) ? null : new BigDecimal(array[63])));
			constanciaDTO.setValesRestauranteExento((((array[64]).equals(" ")) ? null : new BigDecimal(array[64])));
			constanciaDTO.setValesGasolinaGravado((((array[65]).equals(" ")) ? null : new BigDecimal(array[65])));
			constanciaDTO.setValesGasolinaExento((((array[66]).equals(" ")) ? null : new BigDecimal(array[66])));
			constanciaDTO.setValesRopaGravado((((array[67]).equals(" ")) ? null : new BigDecimal(array[67])));
			constanciaDTO.setValesRopaExento((((array[68]).equals(" ")) ? null : new BigDecimal(array[68])));
			constanciaDTO.setAyudaRentaGravado((((array[69]).equals(" ")) ? null : new BigDecimal(array[69])));
			constanciaDTO.setAyudaRentaExento((((array[70]).equals(" ")) ? null : new BigDecimal(array[70])));

			constanciaDTO
					.setAyudaArticulosEscolaresGravado((((array[71]).equals(" ")) ? null : new BigDecimal(array[71])));
			constanciaDTO
					.setAyudaArticulosEscolaresExento((((array[72]).equals(" ")) ? null : new BigDecimal(array[72])));
			constanciaDTO.setAyudaAnteojosGravado(((((array[73]).equals(" ")) ? null : new BigDecimal(array[73]))));
			constanciaDTO.setAyudaAnteojosExento((((array[74]).equals(" ")) ? null : new BigDecimal(array[74])));
			constanciaDTO.setAyudaTransporteGravado((((array[75]).equals(" ")) ? null : new BigDecimal(array[75])));
			constanciaDTO.setAyudaTransporteExento((((array[76]).equals(" ")) ? null : new BigDecimal(array[76])));
			constanciaDTO.setCuotasSindicalesGravado((((array[77]).equals(" ")) ? null : new BigDecimal(array[77])));
			constanciaDTO.setCuotasSindicalesExento((((array[78]).equals(" ")) ? null : new BigDecimal(array[78])));

			constanciaDTO.setSubsidioIncapacidadGravado((((array[79]).equals(" ")) ? null : new BigDecimal(array[79])));
			constanciaDTO.setSubsidioIncapacidadExento((((array[80]).equals(" ")) ? null : new BigDecimal(array[80])));
			constanciaDTO.setBecasGravado((((array[81]).equals(" ")) ? null : new BigDecimal(array[81])));
			constanciaDTO.setBecasExento((((array[82]).equals(" ")) ? null : new BigDecimal(array[82])));
			constanciaDTO.setPagosEfectuadosGravado((((array[83]).equals(" ")) ? null : new BigDecimal(array[83])));
			constanciaDTO.setPagosEfectuadosExento((((array[84]).equals(" ")) ? null : new BigDecimal(array[84])));

			constanciaDTO.setOtrosIngresosGravado((((array[85]).equals(" ")) ? null : new BigDecimal(array[85])));
			constanciaDTO.setOtrosIngresosExento((((array[86]).equals(" ")) ? null : new BigDecimal(array[86])));
			constanciaDTO
					.setSumaIngresoPorSueldoGravado((((array[87]).equals(" ")) ? null : new BigDecimal(array[87])));
			constanciaDTO.setSumaIngresoPorSueldoExento((((array[88]).equals(" ")) ? null : new BigDecimal(array[88])));
			constanciaDTO.setImpuestoRetenidoEjercicioDeclara(
					(((array[89]).equals(" ")) ? null : new BigDecimal(array[89])));
			constanciaDTO.setImpuestoRetenidoPorOtros((((array[90]).equals(" ")) ? null : new BigDecimal(array[90])));
			constanciaDTO.setSiguienteSaldoAFavor((((array[91]).equals(" ")) ? null : new BigDecimal(array[91])));
			constanciaDTO.setAnteriorSaldoAfavor((((array[92]).equals(" ")) ? null : new BigDecimal(array[92])));
			constanciaDTO.setSumaConceptoCredito((((array[93]).equals(" ")) ? null : new BigDecimal(array[93])));
			constanciaDTO.setCreditoEntregado((((array[94]).equals(" ")) ? null : new BigDecimal(array[94])));
			constanciaDTO
					.setTotalConceptoPrevisionSocial((((array[95]).equals(" ")) ? null : new BigDecimal(array[95])));
			constanciaDTO
					.setSumaExentosPorPrevisionSocial((((array[96]).equals(" ")) ? null : new BigDecimal(array[96])));
			constanciaDTO.setSumaIngresoPorSueldo((((array[97]).equals(" ")) ? null : new BigDecimal(array[97])));
			constanciaDTO
					.setImpuestoLocalIngresosPorSueldo((((array[98]).equals(" ")) ? null : new BigDecimal(array[98])));
			constanciaDTO.setSubsidioParaEmpleo((((array[99]).equals(" ")) ? null : new BigDecimal(array[99])));

			constanciaDTO.setTotalAportacionesVoluntariasDeducibles(
					(((array[100]).equals(" ")) ? null : new BigDecimal(array[100])));
			constanciaDTO.setImpuestoConformeTarifa((((array[101]).equals(" ")) ? null : new BigDecimal(array[101])));
			constanciaDTO.setSubsidioAcreditable((((array[102]).equals(" ")) ? null : new BigDecimal(array[102])));
			constanciaDTO.setSubsidioNoAcreditable((((array[103]).equals(" ")) ? null : new BigDecimal(array[103])));
			constanciaDTO
					.setImpuestoIngresosAcumulables((((array[104]).equals(" ")) ? null : new BigDecimal(array[104])));
			constanciaDTO
					.setImpuestoIngresosNoAcumulables((((array[105]).equals(" ")) ? null : new BigDecimal(array[105])));
			constanciaDTO.setImpuestoLocalPrestacionServicioSubordinado(
					(((array[106]).equals(" ")) ? null : new BigDecimal(array[106])));
			constanciaDTO.setMontoSubsidioParaEmpleo((((array[107]).equals(" ")) ? null : new BigDecimal(array[107])));
		} catch (IndexOutOfBoundsException ex) {
			constanciaDTO.setEjercicioFiscal(constanciaSueldoDTO.getEjercicioFiscal());
	        constanciaDTO.setFechaDeclaracion(constanciaSueldoDTO.getFechaDeclaracion());
		}
        constanciaDTO.setEjercicioFiscal(constanciaSueldoDTO.getEjercicioFiscal());
        constanciaDTO.setFechaDeclaracion(constanciaSueldoDTO.getFechaDeclaracion());
		ConstanciaSueldoEntity constanciaSueldoEntityGuardada = constanciaSueldoDAO.crear(constanciaDTO);

		try {
			constanciaSueldoEntityGuardada = retencionesCFDIServices.toComprobanteRetencionesCFDI(constanciaSueldoEntityGuardada);

			constanciaSueldoDAO.actualizar(constanciaSueldoEntityGuardada);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	public ConstanciaSueldoEntity getConstanciaDTO() {
		return constanciaDTO;
	}

	public void setConstanciaDTO(ConstanciaSueldoEntity constanciaDTO) {
		this.constanciaDTO = constanciaDTO;
	}
}