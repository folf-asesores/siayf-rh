
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConstanciaSueldos")
public class ConstanciaSueldoEntity implements Serializable {

    private static final long serialVersionUID = -4109299710365011192L;

    @Id
    @Column(name = "IdConstanciaSueldo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdConstanciaSueldo;

    @Column(name = "MesInicial")
    private Integer MesInicial;

    @Column(name = "MesFinal")
    private Integer MesFinal;

    @Column(name = "RFC")
    private String RFC;

    @Column(name = "CURP")
    private String CURP;

    @Column(name = "ApellidoPaterno ")
    private String ApellidoPaterno;

    @Column(name = "ApellidoMaterno ")
    private String ApellidoMaterno;

    @Column(name = "Nombre ")
    private String Nombre;

    @Column(name = "AreaGeoSalarioMinimo")
    private Integer AreaGeoSalarioMinimo;

    @Column(name = "RealizoCalculoAnual")
    private Integer RealizoCalculoAnual;

    @Column(name = "TarifaEjercicioDeclara")
    private Integer TarifaEjercicioDeclara;

    @Column(name = "TarifaUtilizadaActualizada ")
    private BigDecimal TarifaUtilizadaActualizada;

    @Column(name = "SubsidioAplicado")
    private BigDecimal SubsidioAplicado;

    @Column(name = "TrabajadorSindicalizado")
    private Integer TrabajadorSindicalizado;

    @Column(name = "ClaveSalarioAsimilado ")
    private String ClaveSalarioAsimilado;

    @Column(name = "ClaveEntidadFederativa")
    private Integer ClaveEntidadFederativa;

    @Column(name = "RFCPatronPrimero")
    private String RFCPatronPrimero;

    @Column(name = "RFCPatronSegundo")
    private String RFCPatronSegundo;

    @Column(name = "RFCPatronTercero")
    private String RFCPatronTercero;

    @Column(name = "RFCPatronCuarto")
    private String RFCPatronCuarto;

    @Column(name = "RFCPatronQuinto")
    private String RFCPatronQuinto;

    @Column(name = "RFCPatronSexto")
    private String RFCPatronSexto;

    @Column(name = "RFCPatronSeptimo")
    private String RFCPatronSeptimo;

    @Column(name = " RFCPatronOctavo")
    private String RFCPatronOctavo;

    @Column(name = "RFCPatronNoveno")
    private String RFCPatronNoveno;

    @Column(name = "RFCPatronDecimo ")
    private String RFCPatronDecimo;

    @Column(name = "AportacionesVoluntarias")
    private BigDecimal AportacionesVoluntarias;

    @Column(name = "AplicoAportacionesVoluntarias")
    private Integer AplicoAportacionesVoluntarias;

    @Column(name = "AportacionesVoluntariasDeducibles")
    private Integer AportacionesVoluntariasDeducibles;

    @Column(name = " PagosSeparacion")
    private Integer PagosSeparacion;

    @Column(name = "SalariosAsimilados")
    private Integer SalariosAsimilados;

    @Column(name = "PagosEfectuadosTrabajadores")
    private Integer PagosEfectuadosTrabajadores;

    @Column(name = "SueldoGravado")
    private BigDecimal SueldoGravado;

    @Column(name = "SueldoExento")
    private BigDecimal SueldoExento;

    @Column(name = "GratificacionAnualGravado")
    private BigDecimal GratificacionAnualGravado;

    @Column(name = "GratificacionAnualExento")
    private BigDecimal GratificacionAnualExento;

    @Column(name = "ViaticosGravado")
    private BigDecimal ViaticosGravado;

    @Column(name = "ViaticosExento")
    private BigDecimal ViaticosExento;

    @Column(name = "TiempoExtraordinarioGravado")
    private BigDecimal TiempoExtraordinarioGravado;

    @Column(name = "TiempoExtraordinarioExento")
    private BigDecimal TiempoExtraordinarioExento;

    @Column(name = " PrimaVacacionalGravado")
    private BigDecimal PrimaVacacionalGravado;

    @Column(name = "PrimaVacacionalExento")
    private BigDecimal PrimaVacacionalExento;

    @Column(name = "PrimaDominicalGravado")
    private BigDecimal PrimaDominicalGravado;

    @Column(name = "PrimaDominicalExento")
    private BigDecimal PrimaDominicalExento;

    @Column(name = "ParticipacionTrabajadoresUtilidades ")
    private Integer ParticipacionTrabajadoresUtilidades;

    @Column(name = "SegundaParticipacionTrabajadoresUtilidades")
    private Integer SegundaParticipacionTrabajadoresUtilidades;

    @Column(name = "ReembolsoGastos")
    private BigDecimal ReembolsoGastos;

    @Column(name = "ReembolsoGastosMedicos")
    private String ReembolsoGastosMedicos;

    @Column(name = "FondoAhorroGravado")
    private String FondoAhorroGravado;

    @Column(name = "FondoAhorroExento")
    private BigDecimal FondoAhorroExento;

    @Column(name = "CajaAhorroGravado")
    private BigDecimal CajaAhorroGravado;

    @Column(name = "CajaAhorroExento")
    private BigDecimal CajaAhorroExento;

    @Column(name = "ValesDespensaGravado")
    private BigDecimal ValesDespensaGravado;

    @Column(name = "ValesDespensaExento")
    private BigDecimal ValesDespensaExento;

    @Column(name = "AyudaGastosFuneralGravado")
    private BigDecimal AyudaGastosFuneralGravado;

    @Column(name = "AyudaGastosFuneralExento")
    private BigDecimal AyudaGastosFuneralExento;

    @Column(name = "ContribucionTrabajadorGravado")
    private BigDecimal ContribucionTrabajadorGravado;

    @Column(name = "ContribucionTrabajadorExento")
    private BigDecimal ContribucionTrabajadorExento;

    @Column(name = "PremiosPuntualidadGravado")
    private BigDecimal PremiosPuntualidadGravado;

    @Column(name = "PremiosPuntualidadExento")
    private BigDecimal PremiosPuntualidadExento;

    @Column(name = "PrimaSeguroVidaGravado")
    private BigDecimal PrimaSeguroVidaGravado;

    @Column(name = "PrimaSeguroVidaExento")
    private BigDecimal PrimaSeguroVidaExento;

    @Column(name = "SeguroGastosMedicosMayoresGravado")
    private BigDecimal SeguroGastosMedicosMayoresGravado;

    @Column(name = "SeguroGastosMedicosMayoresExento")
    private BigDecimal SeguroGastosMedicosMayoresExento;

    @Column(name = "ValesRestauranteGravado")
    private BigDecimal ValesRestauranteGravado;

    @Column(name = "ValesRestauranteExento")
    private BigDecimal ValesRestauranteExento;

    @Column(name = "ValesGasolinaGravado")
    private BigDecimal ValesGasolinaGravado;

    @Column(name = "ValesGasolinaExento")
    private BigDecimal ValesGasolinaExento;

    @Column(name = "ValesRopaGravado")
    private BigDecimal ValesRopaGravado;

    @Column(name = "ValesRopaExento")
    private BigDecimal ValesRopaExento;

    @Column(name = "AyudaRentaGravado")
    private BigDecimal AyudaRentaGravado;

    @Column(name = "AyudaRentaExento")
    private BigDecimal AyudaRentaExento;

    @Column(name = "AyudaArticulosEscolaresGravado")
    private BigDecimal AyudaArticulosEscolaresGravado;

    @Column(name = "AyudaArticulosEscolaresExento")
    private BigDecimal AyudaArticulosEscolaresExento;

    @Column(name = "AyudaAnteojosGravado")
    private BigDecimal AyudaAnteojosGravado;

    @Column(name = "AyudaAnteojosExento")
    private BigDecimal AyudaAnteojosExento;

    @Column(name = "AyudaTransporteGravado")
    private BigDecimal AyudaTransporteGravado;

    @Column(name = "AyudaTransporteExento")
    private BigDecimal AyudaTransporteExento;

    @Column(name = "CuotasSindicalesGravado")
    private BigDecimal CuotasSindicalesGravado;

    @Column(name = "CuotasSindicalesExento")
    private BigDecimal CuotasSindicalesExento;

    @Column(name = "SubsidioIncapacidadGravado")
    private BigDecimal SubsidioIncapacidadGravado;

    @Column(name = "SubsidioIncapacidadExento")
    private BigDecimal SubsidioIncapacidadExento;

    @Column(name = "BecasGravado")
    private BigDecimal BecasGravado;

    @Column(name = "BecasExento")
    private BigDecimal BecasExento;

    @Column(name = "PagosEfectuadosGravado")
    private BigDecimal PagosEfectuadosGravado;

    @Column(name = "PagosEfectuadosExento")
    private BigDecimal PagosEfectuadosExento;

    @Column(name = "OtrosIngresosGravado")
    private BigDecimal OtrosIngresosGravado;

    @Column(name = "OtrosIngresosExento")
    private BigDecimal OtrosIngresosExento;

    @Column(name = "SumaIngresoPorSueldoGravado")
    private BigDecimal SumaIngresoPorSueldoGravado;

    @Column(name = "SumaIngresoPorSueldoExento")
    private BigDecimal SumaIngresoPorSueldoExento;

    @Column(name = "ImpuestoRetenidoEjercicioDeclara")
    private BigDecimal ImpuestoRetenidoEjercicioDeclara;

    @Column(name = "ImpuestoRetenidoPorOtros")
    private BigDecimal ImpuestoRetenidoPorOtros;

    @Column(name = "SiguienteSaldoAFavor")
    private BigDecimal SiguienteSaldoAFavor;

    @Column(name = "AnteriorSaldoAfavor")
    private BigDecimal AnteriorSaldoAfavor;

    @Column(name = "SumaConceptoCredito")
    private BigDecimal SumaConceptoCredito;

    @Column(name = "CreditoEntregado")
    private BigDecimal CreditoEntregado;

    @Column(name = "TotalConceptoPrevisionSocial")
    private BigDecimal TotalConceptoPrevisionSocial;

    @Column(name = "SumaExentosPorPrevisionSocial")
    private BigDecimal SumaExentosPorPrevisionSocial;

    @Column(name = "SumaIngresoPorSueldo")
    private BigDecimal SumaIngresoPorSueldo;

    @Column(name = "ImpuestoLocalIngresosPorSueldo")
    private BigDecimal ImpuestoLocalIngresosPorSueldo;

    @Column(name = "SubsidioParaEmpleo")
    private BigDecimal SubsidioParaEmpleo;

    @Column(name = "TotalAportacionesVoluntariasDeducibles")
    private BigDecimal TotalAportacionesVoluntariasDeducibles;

    @Column(name = "ImpuestoConformeTarifa")
    private BigDecimal ImpuestoConformeTarifa;

    @Column(name = "SubsidioAcreditable")
    private BigDecimal SubsidioAcreditable;

    @Column(name = "SubsidioNoAcreditable")
    private BigDecimal SubsidioNoAcreditable;

    @Column(name = "ImpuestoIngresosAcumulables")
    private BigDecimal ImpuestoIngresosAcumulables;

    @Column(name = "ImpuestoIngresosNoAcumulables")
    private BigDecimal ImpuestoIngresosNoAcumulables;

    @Column(name = "ImpuestoLocalPrestacionServicioSubordinado")
    private BigDecimal ImpuestoLocalPrestacionServicioSubordinado;

    @Column(name = "MontoSubsidioParaEmpleo")
    private BigDecimal MontoSubsidioParaEmpleo;

    @Column(name = "EjercicioFiscal")
    private Integer EjercicioFiscal;

    @Column(name = "FechaDeclaracion")
    private Date FechaDeclaracion;

    @Column(name = "XML")
    private String xml;

    @Column(name = "CadenaOriginal")
    private String cadenaOriginal;

    @Column(name = "Sello")
    private String sello;

    /**
     * ******** Getters and Setters *****************
     */
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

    public BigDecimal getTarifaUtilizadaActualizada() {
        return TarifaUtilizadaActualizada;
    }

    public void setTarifaUtilizadaActualizada(
            BigDecimal tarifaUtilizadaActualizada) {
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

    public String getClaveSalarioAsimilado() {
        return ClaveSalarioAsimilado;
    }

    public void setClaveSalarioAsimilado(String claveSalarioAsimilado) {
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

    public void setAplicoAportacionesVoluntarias(
            Integer aplicoAportacionesVoluntarias) {
        AplicoAportacionesVoluntarias = aplicoAportacionesVoluntarias;
    }

    public Integer getAportacionesVoluntariasDeducibles() {
        return AportacionesVoluntariasDeducibles;
    }

    public void setAportacionesVoluntariasDeducibles(
            Integer aportacionesVoluntariasDeducibles) {
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

    public void setPagosEfectuadosTrabajadores(
            Integer pagosEfectuadosTrabajadores) {
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

    public void setGratificacionAnualGravado(
            BigDecimal gratificacionAnualGravado) {
        GratificacionAnualGravado = gratificacionAnualGravado;
    }

    public BigDecimal getGratificacionAnualExento() {
        return GratificacionAnualExento;
    }

    public void setGratificacionAnualExento(
            BigDecimal gratificacionAnualExento) {
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

    public void setTiempoExtraordinarioGravado(
            BigDecimal tiempoExtraordinarioGravado) {
        TiempoExtraordinarioGravado = tiempoExtraordinarioGravado;
    }

    public BigDecimal getTiempoExtraordinarioExento() {
        return TiempoExtraordinarioExento;
    }

    public void setTiempoExtraordinarioExento(
            BigDecimal tiempoExtraordinarioExento) {
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

    public void setParticipacionTrabajadoresUtilidades(
            Integer participacionTrabajadoresUtilidades) {
        ParticipacionTrabajadoresUtilidades = participacionTrabajadoresUtilidades;
    }

    public Integer getSegundaParticipacionTrabajadoresUtilidades() {
        return SegundaParticipacionTrabajadoresUtilidades;
    }

    public void setSegundaParticipacionTrabajadoresUtilidades(
            Integer segundaParticipacionTrabajadoresUtilidades) {
        SegundaParticipacionTrabajadoresUtilidades = segundaParticipacionTrabajadoresUtilidades;
    }

    public BigDecimal getReembolsoGastos() {
        return ReembolsoGastos;
    }

    public void setReembolsoGastos(BigDecimal reembolsoGastos) {
        ReembolsoGastos = reembolsoGastos;
    }

    public String getReembolsoGastosMedicos() {
        return ReembolsoGastosMedicos;
    }

    public void setReembolsoGastosMedicos(String reembolsoGastosMedicos) {
        ReembolsoGastosMedicos = reembolsoGastosMedicos;
    }

    public String getFondoAhorroGravado() {
        return FondoAhorroGravado;
    }

    public void setFondoAhorroGravado(String fondoAhorroGravado) {
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

    public void setAyudaGastosFuneralGravado(
            BigDecimal ayudaGastosFuneralGravado) {
        AyudaGastosFuneralGravado = ayudaGastosFuneralGravado;
    }

    public BigDecimal getAyudaGastosFuneralExento() {
        return AyudaGastosFuneralExento;
    }

    public void setAyudaGastosFuneralExento(
            BigDecimal ayudaGastosFuneralExento) {
        AyudaGastosFuneralExento = ayudaGastosFuneralExento;
    }

    public BigDecimal getContribucionTrabajadorGravado() {
        return ContribucionTrabajadorGravado;
    }

    public void setContribucionTrabajadorGravado(
            BigDecimal contribucionTrabajadorGravado) {
        ContribucionTrabajadorGravado = contribucionTrabajadorGravado;
    }

    public BigDecimal getContribucionTrabajadorExento() {
        return ContribucionTrabajadorExento;
    }

    public void setContribucionTrabajadorExento(
            BigDecimal contribucionTrabajadorExento) {
        ContribucionTrabajadorExento = contribucionTrabajadorExento;
    }

    public BigDecimal getPremiosPuntualidadGravado() {
        return PremiosPuntualidadGravado;
    }

    public void setPremiosPuntualidadGravado(
            BigDecimal premiosPuntualidadGravado) {
        PremiosPuntualidadGravado = premiosPuntualidadGravado;
    }

    public BigDecimal getPremiosPuntualidadExento() {
        return PremiosPuntualidadExento;
    }

    public void setPremiosPuntualidadExento(
            BigDecimal premiosPuntualidadExento) {
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

    public void setSeguroGastosMedicosMayoresGravado(
            BigDecimal seguroGastosMedicosMayoresGravado) {
        SeguroGastosMedicosMayoresGravado = seguroGastosMedicosMayoresGravado;
    }

    public BigDecimal getSeguroGastosMedicosMayoresExento() {
        return SeguroGastosMedicosMayoresExento;
    }

    public void setSeguroGastosMedicosMayoresExento(
            BigDecimal seguroGastosMedicosMayoresExento) {
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

    public void setAyudaArticulosEscolaresGravado(
            BigDecimal ayudaArticulosEscolaresGravado) {
        AyudaArticulosEscolaresGravado = ayudaArticulosEscolaresGravado;
    }

    public BigDecimal getAyudaArticulosEscolaresExento() {
        return AyudaArticulosEscolaresExento;
    }

    public void setAyudaArticulosEscolaresExento(
            BigDecimal ayudaArticulosEscolaresExento) {
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

    public void setSubsidioIncapacidadGravado(
            BigDecimal subsidioIncapacidadGravado) {
        SubsidioIncapacidadGravado = subsidioIncapacidadGravado;
    }

    public BigDecimal getSubsidioIncapacidadExento() {
        return SubsidioIncapacidadExento;
    }

    public void setSubsidioIncapacidadExento(
            BigDecimal subsidioIncapacidadExento) {
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

    public void setSumaIngresoPorSueldoGravado(
            BigDecimal sumaIngresoPorSueldoGravado) {
        SumaIngresoPorSueldoGravado = sumaIngresoPorSueldoGravado;
    }

    public BigDecimal getSumaIngresoPorSueldoExento() {
        return SumaIngresoPorSueldoExento;
    }

    public void setSumaIngresoPorSueldoExento(
            BigDecimal sumaIngresoPorSueldoExento) {
        SumaIngresoPorSueldoExento = sumaIngresoPorSueldoExento;
    }

    public BigDecimal getImpuestoRetenidoEjercicioDeclara() {
        return ImpuestoRetenidoEjercicioDeclara;
    }

    public void setImpuestoRetenidoEjercicioDeclara(
            BigDecimal impuestoRetenidoEjercicioDeclara) {
        ImpuestoRetenidoEjercicioDeclara = impuestoRetenidoEjercicioDeclara;
    }

    public BigDecimal getImpuestoRetenidoPorOtros() {
        return ImpuestoRetenidoPorOtros;
    }

    public void setImpuestoRetenidoPorOtros(
            BigDecimal impuestoRetenidoPorOtros) {
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

    public void setTotalConceptoPrevisionSocial(
            BigDecimal totalConceptoPrevisionSocial) {
        TotalConceptoPrevisionSocial = totalConceptoPrevisionSocial;
    }

    public BigDecimal getSumaExentosPorPrevisionSocial() {
        return SumaExentosPorPrevisionSocial;
    }

    public void setSumaExentosPorPrevisionSocial(
            BigDecimal sumaExentosPorPrevisionSocial) {
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

    public void setImpuestoLocalIngresosPorSueldo(
            BigDecimal impuestoLocalIngresosPorSueldo) {
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

    public void setTotalAportacionesVoluntariasDeducibles(
            BigDecimal totalAportacionesVoluntariasDeducibles) {
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

    public void setImpuestoIngresosAcumulables(
            BigDecimal impuestoIngresosAcumulables) {
        ImpuestoIngresosAcumulables = impuestoIngresosAcumulables;
    }

    public BigDecimal getImpuestoIngresosNoAcumulables() {
        return ImpuestoIngresosNoAcumulables;
    }

    public void setImpuestoIngresosNoAcumulables(
            BigDecimal impuestoIngresosNoAcumulables) {
        ImpuestoIngresosNoAcumulables = impuestoIngresosNoAcumulables;
    }

    public BigDecimal getImpuestoLocalPrestacionServicioSubordinado() {
        return ImpuestoLocalPrestacionServicioSubordinado;
    }

    public void setImpuestoLocalPrestacionServicioSubordinado(
            BigDecimal impuestoLocalPrestacionServicioSubordinado) {
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

    public Date getFechaDeclaracion() {
        return FechaDeclaracion;
    }

    public void setEjercicioFiscal(Integer ejercicioFiscal) {
        EjercicioFiscal = ejercicioFiscal;
    }

    public void setFechaDeclaracion(Date fechaDeclaracion) {
        FechaDeclaracion = fechaDeclaracion;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

}
