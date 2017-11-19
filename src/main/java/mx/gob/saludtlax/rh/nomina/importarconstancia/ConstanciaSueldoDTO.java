package mx.gob.saludtlax.rh.nomina.importarconstancia;

import java.math.BigDecimal;
import java.util.Date;

public class ConstanciaSueldoDTO {

	private Integer IdConstanciaSueldo;
	private Integer MesInicial;
	private Integer MesFinal;
	private String RFC;
	private String CURP;
	private String ApellidoPaterno;
	private String ApellidoMaterno;
	private String Nombre;
	private Integer AreaGeoSalarioMinimo;
	private Integer RealizoCalculoAnual;
	private Integer TarifaEjercicioDeclara;
	private Integer TarifaUtilizadaActualizada;
	private BigDecimal SubsidioAplicado;
	private Integer TrabajadorSindicalizado;
	private Integer ClaveSalarioAsimilado;
	private Integer ClaveEntidadFederativa;
	private String RFCPatronPrimero;
	private String RFCPatronSegundo;
	private String RFCPatronTercero;
	private String RFCPatronCuarto;
	private String RFCPatronQuinto;
	private String RFCPatronSexto;
	private String RFCPatronSeptimo;
	private String RFCPatronOctavo;
	private String RFCPatronNoveno;
	private String RFCPatronDecimo;
	private BigDecimal AportacionesVoluntarias;
	private Integer AplicoAportacionesVoluntarias;
	private Integer AportacionesVoluntariasDeducibles;
	private Integer PagosSeparacion;
	private Integer SalariosAsimilados;
	private Integer PagosEfectuadosTrabajadores;
	private BigDecimal SueldoGravado;
	private BigDecimal SueldoExento;
	private BigDecimal GratificacionAnualGravado;
	private BigDecimal GratificacionAnualExento;
	private BigDecimal ViaticosGravado;
	private BigDecimal ViaticosExento;
	private BigDecimal TiempoExtraordinarioGravado;
	private BigDecimal TiempoExtraordinarioExento;
	private BigDecimal PrimaVacacionalGravado;
	private BigDecimal PrimaVacacionalExento;
	private BigDecimal PrimaDominicalGravado;
	private BigDecimal PrimaDominicalExento;
	private Integer ParticipacionTrabajadoresUtilidades;
	private Integer SegundaParticipacionTrabajadoresUtilidades;
	private BigDecimal ReembolsoGastos;
	private BigDecimal ReembolsoGastosMedicos;
	private BigDecimal FondoAhorroGravado;
	private BigDecimal FondoAhorroExento;
	private BigDecimal CajaAhorroGravado;
	private BigDecimal CajaAhorroExento;
	private BigDecimal ValesDespensaGravado;
	private BigDecimal ValesDespensaExento;
	private BigDecimal AyudaGastosFuneralGravado;
	private BigDecimal AyudaGastosFuneralExento;
	private BigDecimal ContribucionTrabajadorGravado;
	private BigDecimal ContribucionTrabajadorExento;
	private BigDecimal PremiosPuntualidadGravado;
	private BigDecimal PremiosPuntualidadExento;
	private BigDecimal PrimaSeguroVidaGravado;
	private BigDecimal PrimaSeguroVidaExento;
	private BigDecimal SeguroGastosMedicosMayoresGravado;
	private BigDecimal SeguroGastosMedicosMayoresExento;
	private BigDecimal ValesRestauranteGravado;
	private BigDecimal ValesRestauranteExento;
	private BigDecimal ValesGasolinaGravado;
	private BigDecimal ValesGasolinaExento;
	private BigDecimal ValesRopaGravado;
	private BigDecimal ValesRopaExento;
	private BigDecimal AyudaRentaGravado;
	private BigDecimal AyudaRentaExento;
	private BigDecimal AyudaArticulosEscolaresGravado;
	private BigDecimal AyudaArticulosEscolaresExento;
	private BigDecimal AyudaAnteojosGravado;
	private BigDecimal AyudaAnteojosExento;
	private BigDecimal AyudaTransporteGravado;
	private BigDecimal AyudaTransporteExento;
	private BigDecimal CuotasSindicalesGravado;
	private BigDecimal CuotasSindicalesExento;
	private BigDecimal SubsidioIncapacidadGravado;
	private BigDecimal SubsidioIncapacidadExento;
	private BigDecimal BecasGravado;
	private BigDecimal BecasExento;
	private BigDecimal PagosEfectuadosGravado;
	private BigDecimal PagosEfectuadosExento;
	private BigDecimal OtrosIngresosGravado;
	private BigDecimal OtrosIngresosExento;
	private BigDecimal SumaIngresoPorSueldoGravado;
	private BigDecimal SumaIngresoPorSueldoExento;
	private BigDecimal ImpuestoRetenidoEjercicioDeclara;
	private BigDecimal ImpuestoRetenidoPorOtros;
	private BigDecimal SiguienteSaldoAFavor;
	private BigDecimal AnteriorSaldoAfavor;
	private BigDecimal SumaConceptoCredito;
	private BigDecimal CreditoEntregado;
	private BigDecimal TotalConceptoPrevisionSocial;
	private BigDecimal SumaExentosPorPrevisionSocial;
	private BigDecimal SumaIngresoPorSueldo;
	private BigDecimal ImpuestoLocalIngresosPorSueldo;
	private BigDecimal SubsidioParaEmpleo;
	private BigDecimal TotalAportacionesVoluntariasDeducibles;
	private BigDecimal ImpuestoConformeTarifa;
	private BigDecimal SubsidioAcreditable;
	private BigDecimal SubsidioNoAcreditable;
	private BigDecimal ImpuestoIngresosAcumulables;
	private BigDecimal ImpuestoIngresosNoAcumulables;
	private BigDecimal ImpuestoLocalPrestacionServicioSubordinado;
	private BigDecimal MontoSubsidioParaEmpleo;
	private Integer EjercicioFiscal;
	private Date FechaDeclaracion;

	

	public Integer getIdConstanciaSueldo() {
		return IdConstanciaSueldo;
	}

	public void setIdConstanciaSueldo(Integer idConstanciaSueldo) {
		IdConstanciaSueldo = idConstanciaSueldo;
	}

	public Integer getMesInicial() {
		return MesInicial;
	}

	public void setMesInicial(Integer mesInicial) {
		MesInicial = mesInicial;
	}

	public Integer getMesFinal() {
		return MesFinal;
	}

	public void setMesFinal(Integer mesFinal) {
		MesFinal = mesFinal;
	}

	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	public String getCURP() {
		return CURP;
	}

	public void setCURP(String cURP) {
		CURP = cURP;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Integer getAreaGeoSalarioMinimo() {
		return AreaGeoSalarioMinimo;
	}

	public void setAreaGeoSalarioMinimo(Integer areaGeoSalarioMinimo) {
		AreaGeoSalarioMinimo = areaGeoSalarioMinimo;
	}

	public Integer getRealizoCalculoAnual() {
		return RealizoCalculoAnual;
	}

	public void setRealizoCalculoAnual(Integer realizoCalculoAnual) {
		RealizoCalculoAnual = realizoCalculoAnual;
	}

	public Integer getTarifaEjercicioDeclara() {
		return TarifaEjercicioDeclara;
	}

	public void setTarifaEjercicioDeclara(Integer tarifaEjercicioDeclara) {
		TarifaEjercicioDeclara = tarifaEjercicioDeclara;
	}

	public Integer getTarifaUtilizadaActualizada() {
		return TarifaUtilizadaActualizada;
	}

	public void setTarifaUtilizadaActualizada(Integer tarifaUtilizadaActualizada) {
		TarifaUtilizadaActualizada = tarifaUtilizadaActualizada;
	}

	public BigDecimal getSubsidioAplicado() {
		return SubsidioAplicado;
	}

	public void setSubsidioAplicado(BigDecimal subsidioAplicado) {
		SubsidioAplicado = subsidioAplicado;
	}

	public Integer getTrabajadorSindicalizado() {
		return TrabajadorSindicalizado;
	}

	public void setTrabajadorSindicalizado(Integer trabajadorSindicalizado) {
		TrabajadorSindicalizado = trabajadorSindicalizado;
	}

	public Integer getClaveSalarioAsimilado() {
		return ClaveSalarioAsimilado;
	}

	public void setClaveSalarioAsimilado(Integer claveSalarioAsimilado) {
		ClaveSalarioAsimilado = claveSalarioAsimilado;
	}

	public Integer getClaveEntidadFederativa() {
		return ClaveEntidadFederativa;
	}

	public void setClaveEntidadFederativa(Integer claveEntidadFederativa) {
		ClaveEntidadFederativa = claveEntidadFederativa;
	}

	public String getRFCPatronPrimero() {
		return RFCPatronPrimero;
	}

	public void setRFCPatronPrimero(String rFCPatronPrimero) {
		RFCPatronPrimero = rFCPatronPrimero;
	}

	public String getRFCPatronSegundo() {
		return RFCPatronSegundo;
	}

	public void setRFCPatronSegundo(String rFCPatronSegundo) {
		RFCPatronSegundo = rFCPatronSegundo;
	}

	public String getRFCPatronTercero() {
		return RFCPatronTercero;
	}

	public void setRFCPatronTercero(String rFCPatronTercero) {
		RFCPatronTercero = rFCPatronTercero;
	}

	public String getRFCPatronCuarto() {
		return RFCPatronCuarto;
	}

	public void setRFCPatronCuarto(String rFCPatronCuarto) {
		RFCPatronCuarto = rFCPatronCuarto;
	}

	public String getRFCPatronQuinto() {
		return RFCPatronQuinto;
	}

	public void setRFCPatronQuinto(String rFCPatronQuinto) {
		RFCPatronQuinto = rFCPatronQuinto;
	}

	public String getRFCPatronSexto() {
		return RFCPatronSexto;
	}

	public void setRFCPatronSexto(String rFCPatronSexto) {
		RFCPatronSexto = rFCPatronSexto;
	}

	public String getRFCPatronSeptimo() {
		return RFCPatronSeptimo;
	}

	public void setRFCPatronSeptimo(String rFCPatronSeptimo) {
		RFCPatronSeptimo = rFCPatronSeptimo;
	}

	public String getRFCPatronOctavo() {
		return RFCPatronOctavo;
	}

	public void setRFCPatronOctavo(String rFCPatronOctavo) {
		RFCPatronOctavo = rFCPatronOctavo;
	}

	public String getRFCPatronNoveno() {
		return RFCPatronNoveno;
	}

	public void setRFCPatronNoveno(String rFCPatronNoveno) {
		RFCPatronNoveno = rFCPatronNoveno;
	}

	public String getRFCPatronDecimo() {
		return RFCPatronDecimo;
	}

	public void setRFCPatronDecimo(String rFCPatronDecimo) {
		RFCPatronDecimo = rFCPatronDecimo;
	}

	public BigDecimal getAportacionesVoluntarias() {
		return AportacionesVoluntarias;
	}

	public void setAportacionesVoluntarias(BigDecimal aportacionesVoluntarias) {
		AportacionesVoluntarias = aportacionesVoluntarias;
	}

	public Integer getAplicoAportacionesVoluntarias() {
		return AplicoAportacionesVoluntarias;
	}

	public void setAplicoAportacionesVoluntarias(Integer aplicoAportacionesVoluntarias) {
		AplicoAportacionesVoluntarias = aplicoAportacionesVoluntarias;
	}

	public Integer getAportacionesVoluntariasDeducibles() {
		return AportacionesVoluntariasDeducibles;
	}

	public void setAportacionesVoluntariasDeducibles(Integer aportacionesVoluntariasDeducibles) {
		AportacionesVoluntariasDeducibles = aportacionesVoluntariasDeducibles;
	}

	public Integer getPagosSeparacion() {
		return PagosSeparacion;
	}

	public void setPagosSeparacion(Integer pagosSeparacion) {
		PagosSeparacion = pagosSeparacion;
	}

	public Integer getSalariosAsimilados() {
		return SalariosAsimilados;
	}

	public void setSalariosAsimilados(Integer salariosAsimilados) {
		SalariosAsimilados = salariosAsimilados;
	}

	public Integer getPagosEfectuadosTrabajadores() {
		return PagosEfectuadosTrabajadores;
	}

	public void setPagosEfectuadosTrabajadores(Integer pagosEfectuadosTrabajadores) {
		PagosEfectuadosTrabajadores = pagosEfectuadosTrabajadores;
	}

	public BigDecimal getSueldoGravado() {
		return SueldoGravado;
	}

	public void setSueldoGravado(BigDecimal sueldoGravado) {
		SueldoGravado = sueldoGravado;
	}

	public BigDecimal getSueldoExento() {
		return SueldoExento;
	}

	public void setSueldoExento(BigDecimal sueldoExento) {
		SueldoExento = sueldoExento;
	}

	public BigDecimal getGratificacionAnualGravado() {
		return GratificacionAnualGravado;
	}

	public void setGratificacionAnualGravado(BigDecimal gratificacionAnualGravado) {
		GratificacionAnualGravado = gratificacionAnualGravado;
	}

	public BigDecimal getGratificacionAnualExento() {
		return GratificacionAnualExento;
	}

	public void setGratificacionAnualExento(BigDecimal gratificacionAnualExento) {
		GratificacionAnualExento = gratificacionAnualExento;
	}

	public BigDecimal getViaticosGravado() {
		return ViaticosGravado;
	}

	public void setViaticosGravado(BigDecimal viaticosGravado) {
		ViaticosGravado = viaticosGravado;
	}

	public BigDecimal getViaticosExento() {
		return ViaticosExento;
	}

	public void setViaticosExento(BigDecimal viaticosExento) {
		ViaticosExento = viaticosExento;
	}

	public BigDecimal getTiempoExtraordinarioGravado() {
		return TiempoExtraordinarioGravado;
	}

	public void setTiempoExtraordinarioGravado(BigDecimal tiempoExtraordinarioGravado) {
		TiempoExtraordinarioGravado = tiempoExtraordinarioGravado;
	}

	public BigDecimal getTiempoExtraordinarioExento() {
		return TiempoExtraordinarioExento;
	}

	public void setTiempoExtraordinarioExento(BigDecimal tiempoExtraordinarioExento) {
		TiempoExtraordinarioExento = tiempoExtraordinarioExento;
	}

	public BigDecimal getPrimaVacacionalGravado() {
		return PrimaVacacionalGravado;
	}

	public void setPrimaVacacionalGravado(BigDecimal primaVacacionalGravado) {
		PrimaVacacionalGravado = primaVacacionalGravado;
	}

	public BigDecimal getPrimaVacacionalExento() {
		return PrimaVacacionalExento;
	}

	public void setPrimaVacacionalExento(BigDecimal primaVacacionalExento) {
		PrimaVacacionalExento = primaVacacionalExento;
	}

	public BigDecimal getPrimaDominicalGravado() {
		return PrimaDominicalGravado;
	}

	public void setPrimaDominicalGravado(BigDecimal primaDominicalGravado) {
		PrimaDominicalGravado = primaDominicalGravado;
	}

	public BigDecimal getPrimaDominicalExento() {
		return PrimaDominicalExento;
	}

	public void setPrimaDominicalExento(BigDecimal primaDominicalExento) {
		PrimaDominicalExento = primaDominicalExento;
	}

	public Integer getParticipacionTrabajadoresUtilidades() {
		return ParticipacionTrabajadoresUtilidades;
	}

	public void setParticipacionTrabajadoresUtilidades(Integer participacionTrabajadoresUtilidades) {
		ParticipacionTrabajadoresUtilidades = participacionTrabajadoresUtilidades;
	}

	public Integer getSegundaParticipacionTrabajadoresUtilidades() {
		return SegundaParticipacionTrabajadoresUtilidades;
	}

	public void setSegundaParticipacionTrabajadoresUtilidades(Integer segundaParticipacionTrabajadoresUtilidades) {
		SegundaParticipacionTrabajadoresUtilidades = segundaParticipacionTrabajadoresUtilidades;
	}

	public BigDecimal getReembolsoGastos() {
		return ReembolsoGastos;
	}

	public void setReembolsoGastos(BigDecimal reembolsoGastos) {
		ReembolsoGastos = reembolsoGastos;
	}

	public BigDecimal getReembolsoGastosMedicos() {
		return ReembolsoGastosMedicos;
	}

	public void setReembolsoGastosMedicos(BigDecimal reembolsoGastosMedicos) {
		ReembolsoGastosMedicos = reembolsoGastosMedicos;
	}

	public BigDecimal getFondoAhorroGravado() {
		return FondoAhorroGravado;
	}

	public void setFondoAhorroGravado(BigDecimal fondoAhorroGravado) {
		FondoAhorroGravado = fondoAhorroGravado;
	}

	public BigDecimal getFondoAhorroExento() {
		return FondoAhorroExento;
	}

	public void setFondoAhorroExento(BigDecimal fondoAhorroExento) {
		FondoAhorroExento = fondoAhorroExento;
	}

	public BigDecimal getCajaAhorroGravado() {
		return CajaAhorroGravado;
	}

	public void setCajaAhorroGravado(BigDecimal cajaAhorroGravado) {
		CajaAhorroGravado = cajaAhorroGravado;
	}

	public BigDecimal getCajaAhorroExento() {
		return CajaAhorroExento;
	}

	public void setCajaAhorroExento(BigDecimal cajaAhorroExento) {
		CajaAhorroExento = cajaAhorroExento;
	}

	public BigDecimal getValesDespensaGravado() {
		return ValesDespensaGravado;
	}

	public void setValesDespensaGravado(BigDecimal valesDespensaGravado) {
		ValesDespensaGravado = valesDespensaGravado;
	}

	public BigDecimal getValesDespensaExento() {
		return ValesDespensaExento;
	}

	public void setValesDespensaExento(BigDecimal valesDespensaExento) {
		ValesDespensaExento = valesDespensaExento;
	}

	public BigDecimal getAyudaGastosFuneralGravado() {
		return AyudaGastosFuneralGravado;
	}

	public void setAyudaGastosFuneralGravado(BigDecimal ayudaGastosFuneralGravado) {
		AyudaGastosFuneralGravado = ayudaGastosFuneralGravado;
	}

	public BigDecimal getAyudaGastosFuneralExento() {
		return AyudaGastosFuneralExento;
	}

	public void setAyudaGastosFuneralExento(BigDecimal ayudaGastosFuneralExento) {
		AyudaGastosFuneralExento = ayudaGastosFuneralExento;
	}

	public BigDecimal getContribucionTrabajadorGravado() {
		return ContribucionTrabajadorGravado;
	}

	public void setContribucionTrabajadorGravado(BigDecimal contribucionTrabajadorGravado) {
		ContribucionTrabajadorGravado = contribucionTrabajadorGravado;
	}

	public BigDecimal getContribucionTrabajadorExento() {
		return ContribucionTrabajadorExento;
	}

	public void setContribucionTrabajadorExento(BigDecimal contribucionTrabajadorExento) {
		ContribucionTrabajadorExento = contribucionTrabajadorExento;
	}

	public BigDecimal getPremiosPuntualidadGravado() {
		return PremiosPuntualidadGravado;
	}

	public void setPremiosPuntualidadGravado(BigDecimal premiosPuntualidadGravado) {
		PremiosPuntualidadGravado = premiosPuntualidadGravado;
	}

	public BigDecimal getPremiosPuntualidadExento() {
		return PremiosPuntualidadExento;
	}

	public void setPremiosPuntualidadExento(BigDecimal premiosPuntualidadExento) {
		PremiosPuntualidadExento = premiosPuntualidadExento;
	}

	public BigDecimal getPrimaSeguroVidaGravado() {
		return PrimaSeguroVidaGravado;
	}

	public void setPrimaSeguroVidaGravado(BigDecimal primaSeguroVidaGravado) {
		PrimaSeguroVidaGravado = primaSeguroVidaGravado;
	}

	public BigDecimal getPrimaSeguroVidaExento() {
		return PrimaSeguroVidaExento;
	}

	public void setPrimaSeguroVidaExento(BigDecimal primaSeguroVidaExento) {
		PrimaSeguroVidaExento = primaSeguroVidaExento;
	}

	public BigDecimal getSeguroGastosMedicosMayoresGravado() {
		return SeguroGastosMedicosMayoresGravado;
	}

	public void setSeguroGastosMedicosMayoresGravado(BigDecimal seguroGastosMedicosMayoresGravado) {
		SeguroGastosMedicosMayoresGravado = seguroGastosMedicosMayoresGravado;
	}

	public BigDecimal getSeguroGastosMedicosMayoresExento() {
		return SeguroGastosMedicosMayoresExento;
	}

	public void setSeguroGastosMedicosMayoresExento(BigDecimal seguroGastosMedicosMayoresExento) {
		SeguroGastosMedicosMayoresExento = seguroGastosMedicosMayoresExento;
	}

	public BigDecimal getValesRestauranteGravado() {
		return ValesRestauranteGravado;
	}

	public void setValesRestauranteGravado(BigDecimal valesRestauranteGravado) {
		ValesRestauranteGravado = valesRestauranteGravado;
	}

	public BigDecimal getValesRestauranteExento() {
		return ValesRestauranteExento;
	}

	public void setValesRestauranteExento(BigDecimal valesRestauranteExento) {
		ValesRestauranteExento = valesRestauranteExento;
	}

	public BigDecimal getValesGasolinaGravado() {
		return ValesGasolinaGravado;
	}

	public void setValesGasolinaGravado(BigDecimal valesGasolinaGravado) {
		ValesGasolinaGravado = valesGasolinaGravado;
	}

	public BigDecimal getValesGasolinaExento() {
		return ValesGasolinaExento;
	}

	public void setValesGasolinaExento(BigDecimal valesGasolinaExento) {
		ValesGasolinaExento = valesGasolinaExento;
	}

	public BigDecimal getValesRopaGravado() {
		return ValesRopaGravado;
	}

	public void setValesRopaGravado(BigDecimal valesRopaGravado) {
		ValesRopaGravado = valesRopaGravado;
	}

	public BigDecimal getValesRopaExento() {
		return ValesRopaExento;
	}

	public void setValesRopaExento(BigDecimal valesRopaExento) {
		ValesRopaExento = valesRopaExento;
	}

	public BigDecimal getAyudaRentaGravado() {
		return AyudaRentaGravado;
	}

	public void setAyudaRentaGravado(BigDecimal ayudaRentaGravado) {
		AyudaRentaGravado = ayudaRentaGravado;
	}

	public BigDecimal getAyudaRentaExento() {
		return AyudaRentaExento;
	}

	public void setAyudaRentaExento(BigDecimal ayudaRentaExento) {
		AyudaRentaExento = ayudaRentaExento;
	}

	public BigDecimal getAyudaArticulosEscolaresGravado() {
		return AyudaArticulosEscolaresGravado;
	}

	public void setAyudaArticulosEscolaresGravado(BigDecimal ayudaArticulosEscolaresGravado) {
		AyudaArticulosEscolaresGravado = ayudaArticulosEscolaresGravado;
	}

	public BigDecimal getAyudaArticulosEscolaresExento() {
		return AyudaArticulosEscolaresExento;
	}

	public void setAyudaArticulosEscolaresExento(BigDecimal ayudaArticulosEscolaresExento) {
		AyudaArticulosEscolaresExento = ayudaArticulosEscolaresExento;
	}

	public BigDecimal getAyudaAnteojosGravado() {
		return AyudaAnteojosGravado;
	}

	public void setAyudaAnteojosGravado(BigDecimal ayudaAnteojosGravado) {
		AyudaAnteojosGravado = ayudaAnteojosGravado;
	}

	public BigDecimal getAyudaAnteojosExento() {
		return AyudaAnteojosExento;
	}

	public void setAyudaAnteojosExento(BigDecimal ayudaAnteojosExento) {
		AyudaAnteojosExento = ayudaAnteojosExento;
	}

	public BigDecimal getAyudaTransporteGravado() {
		return AyudaTransporteGravado;
	}

	public void setAyudaTransporteGravado(BigDecimal ayudaTransporteGravado) {
		AyudaTransporteGravado = ayudaTransporteGravado;
	}

	public BigDecimal getAyudaTransporteExento() {
		return AyudaTransporteExento;
	}

	public void setAyudaTransporteExento(BigDecimal ayudaTransporteExento) {
		AyudaTransporteExento = ayudaTransporteExento;
	}

	public BigDecimal getCuotasSindicalesGravado() {
		return CuotasSindicalesGravado;
	}

	public void setCuotasSindicalesGravado(BigDecimal cuotasSindicalesGravado) {
		CuotasSindicalesGravado = cuotasSindicalesGravado;
	}

	public BigDecimal getCuotasSindicalesExento() {
		return CuotasSindicalesExento;
	}

	public void setCuotasSindicalesExento(BigDecimal cuotasSindicalesExento) {
		CuotasSindicalesExento = cuotasSindicalesExento;
	}

	public BigDecimal getSubsidioIncapacidadGravado() {
		return SubsidioIncapacidadGravado;
	}

	public void setSubsidioIncapacidadGravado(BigDecimal subsidioIncapacidadGravado) {
		SubsidioIncapacidadGravado = subsidioIncapacidadGravado;
	}

	public BigDecimal getSubsidioIncapacidadExento() {
		return SubsidioIncapacidadExento;
	}

	public void setSubsidioIncapacidadExento(BigDecimal subsidioIncapacidadExento) {
		SubsidioIncapacidadExento = subsidioIncapacidadExento;
	}

	public BigDecimal getBecasGravado() {
		return BecasGravado;
	}

	public void setBecasGravado(BigDecimal becasGravado) {
		BecasGravado = becasGravado;
	}

	public BigDecimal getBecasExento() {
		return BecasExento;
	}

	public void setBecasExento(BigDecimal becasExento) {
		BecasExento = becasExento;
	}

	public BigDecimal getPagosEfectuadosGravado() {
		return PagosEfectuadosGravado;
	}

	public void setPagosEfectuadosGravado(BigDecimal pagosEfectuadosGravado) {
		PagosEfectuadosGravado = pagosEfectuadosGravado;
	}

	public BigDecimal getPagosEfectuadosExento() {
		return PagosEfectuadosExento;
	}

	public void setPagosEfectuadosExento(BigDecimal pagosEfectuadosExento) {
		PagosEfectuadosExento = pagosEfectuadosExento;
	}

	public BigDecimal getOtrosIngresosGravado() {
		return OtrosIngresosGravado;
	}

	public void setOtrosIngresosGravado(BigDecimal otrosIngresosGravado) {
		OtrosIngresosGravado = otrosIngresosGravado;
	}

	public BigDecimal getOtrosIngresosExento() {
		return OtrosIngresosExento;
	}

	public void setOtrosIngresosExento(BigDecimal otrosIngresosExento) {
		OtrosIngresosExento = otrosIngresosExento;
	}

	public BigDecimal getSumaIngresoPorSueldoGravado() {
		return SumaIngresoPorSueldoGravado;
	}

	public void setSumaIngresoPorSueldoGravado(BigDecimal sumaIngresoPorSueldoGravado) {
		SumaIngresoPorSueldoGravado = sumaIngresoPorSueldoGravado;
	}

	public BigDecimal getSumaIngresoPorSueldoExento() {
		return SumaIngresoPorSueldoExento;
	}

	public void setSumaIngresoPorSueldoExento(BigDecimal sumaIngresoPorSueldoExento) {
		SumaIngresoPorSueldoExento = sumaIngresoPorSueldoExento;
	}

	public BigDecimal getImpuestoRetenidoEjercicioDeclara() {
		return ImpuestoRetenidoEjercicioDeclara;
	}

	public void setImpuestoRetenidoEjercicioDeclara(BigDecimal impuestoRetenidoEjercicioDeclara) {
		ImpuestoRetenidoEjercicioDeclara = impuestoRetenidoEjercicioDeclara;
	}

	public BigDecimal getImpuestoRetenidoPorOtros() {
		return ImpuestoRetenidoPorOtros;
	}

	public void setImpuestoRetenidoPorOtros(BigDecimal impuestoRetenidoPorOtros) {
		ImpuestoRetenidoPorOtros = impuestoRetenidoPorOtros;
	}

	public BigDecimal getSiguienteSaldoAFavor() {
		return SiguienteSaldoAFavor;
	}

	public void setSiguienteSaldoAFavor(BigDecimal siguienteSaldoAFavor) {
		SiguienteSaldoAFavor = siguienteSaldoAFavor;
	}

	public BigDecimal getAnteriorSaldoAfavor() {
		return AnteriorSaldoAfavor;
	}

	public void setAnteriorSaldoAfavor(BigDecimal anteriorSaldoAfavor) {
		AnteriorSaldoAfavor = anteriorSaldoAfavor;
	}

	public BigDecimal getSumaConceptoCredito() {
		return SumaConceptoCredito;
	}

	public void setSumaConceptoCredito(BigDecimal sumaConceptoCredito) {
		SumaConceptoCredito = sumaConceptoCredito;
	}

	public BigDecimal getCreditoEntregado() {
		return CreditoEntregado;
	}

	public void setCreditoEntregado(BigDecimal creditoEntregado) {
		CreditoEntregado = creditoEntregado;
	}

	public BigDecimal getTotalConceptoPrevisionSocial() {
		return TotalConceptoPrevisionSocial;
	}

	public void setTotalConceptoPrevisionSocial(BigDecimal totalConceptoPrevisionSocial) {
		TotalConceptoPrevisionSocial = totalConceptoPrevisionSocial;
	}

	public BigDecimal getSumaExentosPorPrevisionSocial() {
		return SumaExentosPorPrevisionSocial;
	}

	public void setSumaExentosPorPrevisionSocial(BigDecimal sumaExentosPorPrevisionSocial) {
		SumaExentosPorPrevisionSocial = sumaExentosPorPrevisionSocial;
	}

	public BigDecimal getSumaIngresoPorSueldo() {
		return SumaIngresoPorSueldo;
	}

	public void setSumaIngresoPorSueldo(BigDecimal sumaIngresoPorSueldo) {
		SumaIngresoPorSueldo = sumaIngresoPorSueldo;
	}

	public BigDecimal getImpuestoLocalIngresosPorSueldo() {
		return ImpuestoLocalIngresosPorSueldo;
	}

	public void setImpuestoLocalIngresosPorSueldo(BigDecimal impuestoLocalIngresosPorSueldo) {
		ImpuestoLocalIngresosPorSueldo = impuestoLocalIngresosPorSueldo;
	}

	public BigDecimal getSubsidioParaEmpleo() {
		return SubsidioParaEmpleo;
	}

	public void setSubsidioParaEmpleo(BigDecimal subsidioParaEmpleo) {
		SubsidioParaEmpleo = subsidioParaEmpleo;
	}

	public BigDecimal getTotalAportacionesVoluntariasDeducibles() {
		return TotalAportacionesVoluntariasDeducibles;
	}

	public void setTotalAportacionesVoluntariasDeducibles(BigDecimal totalAportacionesVoluntariasDeducibles) {
		TotalAportacionesVoluntariasDeducibles = totalAportacionesVoluntariasDeducibles;
	}

	public BigDecimal getImpuestoConformeTarifa() {
		return ImpuestoConformeTarifa;
	}

	public void setImpuestoConformeTarifa(BigDecimal impuestoConformeTarifa) {
		ImpuestoConformeTarifa = impuestoConformeTarifa;
	}

	public BigDecimal getSubsidioAcreditable() {
		return SubsidioAcreditable;
	}

	public void setSubsidioAcreditable(BigDecimal subsidioAcreditable) {
		SubsidioAcreditable = subsidioAcreditable;
	}

	public BigDecimal getSubsidioNoAcreditable() {
		return SubsidioNoAcreditable;
	}

	public void setSubsidioNoAcreditable(BigDecimal subsidioNoAcreditable) {
		SubsidioNoAcreditable = subsidioNoAcreditable;
	}

	public BigDecimal getImpuestoIngresosAcumulables() {
		return ImpuestoIngresosAcumulables;
	}

	public void setImpuestoIngresosAcumulables(BigDecimal impuestoIngresosAcumulables) {
		ImpuestoIngresosAcumulables = impuestoIngresosAcumulables;
	}

	public BigDecimal getImpuestoIngresosNoAcumulables() {
		return ImpuestoIngresosNoAcumulables;
	}

	public void setImpuestoIngresosNoAcumulables(BigDecimal impuestoIngresosNoAcumulables) {
		ImpuestoIngresosNoAcumulables = impuestoIngresosNoAcumulables;
	}

	public BigDecimal getImpuestoLocalPrestacionServicioSubordinado() {
		return ImpuestoLocalPrestacionServicioSubordinado;
	}

	public void setImpuestoLocalPrestacionServicioSubordinado(BigDecimal impuestoLocalPrestacionServicioSubordinado) {
		ImpuestoLocalPrestacionServicioSubordinado = impuestoLocalPrestacionServicioSubordinado;
	}

	public BigDecimal getMontoSubsidioParaEmpleo() {
		return MontoSubsidioParaEmpleo;
	}

	public void setMontoSubsidioParaEmpleo(BigDecimal montoSubsidioParaEmpleo) {
		MontoSubsidioParaEmpleo = montoSubsidioParaEmpleo;
	}
	
	public Integer getEjercicioFiscal() {
		return EjercicioFiscal;
	}
	
	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		EjercicioFiscal = ejercicioFiscal;
	}
	
	public Date getFechaDeclaracion() {
		return FechaDeclaracion;
	}
	
	public void setFechaDeclaracion(Date fechaDeclaracion) {
		FechaDeclaracion = fechaDeclaracion;
	}

}