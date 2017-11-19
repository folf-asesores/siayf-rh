/*
 * SeguroPopularReporteDTO.java
 * Creado el 09/Dec/2016 6:04:35 PM
 *
 */

package mx.gob.saludtlax.rh.siif.seguropopular;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class SeguroPopularRhReporteDTO implements Serializable {

    private static final long serialVersionUID = -326102298275737758L;

    private Integer numeroConsecutivo;
    private String mes;
    private String estado;
    private String tipoCentroSaludHospital;
    private String clues;
    private String nombreUnidad;
    private String areaAdscripcion;
    private String puesto;
    private String clavePuesto;
    private String servicio;
    private String rama;
    private String nombre;
    private String rfc;
    private String turno;
    private Date fechaIngreso;
    private BigDecimal c07SueldoBase;
    private BigDecimal c30CompensacionRiesgo;
    private BigDecimal c32PrimaDominical;
    private BigDecimal c38Despensa;
    private BigDecimal c42AsignacionBruta;
    private BigDecimal c44PrevisionSocialMultiple;
    private BigDecimal c45AyudaAnteojo;
    private BigDecimal c46AyudaServicio;
    private BigDecimal c55AyudaParaGastosActualizacion;
    private BigDecimal cqqPrimaQuinquenal;
    private BigDecimal percepcionTotal;
    private BigDecimal c01ImpuestoSobreRenta;
    private BigDecimal c02SrPensionJubilacion;
    private BigDecimal c02SiServicioMedico;
    private BigDecimal c02SsServicioSocialCultural;
    private BigDecimal c03PrestamoPersonal;
    private BigDecimal c04SsServicioMedicoMaternidad;
    private BigDecimal c04SpSeguroInvalidezVida;
    private BigDecimal c09SeguroDanyosPrestamoAvaladoIssste;
    private BigDecimal c17RetardoFalta;
    private BigDecimal c21FondoAhorroCapitalizable;
    private BigDecimal c29Responsabilidad;
    private BigDecimal c34SeguroRiesgoProfesional;
    private BigDecimal c46Inbursa;
    private BigDecimal c46SeguroArgos;
    private BigDecimal c46Etesa;
    private BigDecimal c46LaTenda;
    private BigDecimal c46PsPubliseg;
    private BigDecimal c46SeguroGnp;
    private BigDecimal c46AuditoriaOperacionCrediempleado;
    private BigDecimal c46CreditoExpres;
    private BigDecimal c51SeguroVidaInstitucional;
    private BigDecimal c56PrestamoHipotecario;
    private BigDecimal c57SeguroDeVidaAdicionalMetlife;
    private BigDecimal c58CuotaSindical;
    private BigDecimal c62PensionAlimenticia;
    private BigDecimal c64FondoVivienda;
    private BigDecimal c70FondoAhorroDefuncion;
    private BigDecimal c77SeguroRetiroMetlife;
    private BigDecimal c97DescuentoPromobien;
    private BigDecimal casAhorroSolidario;
    private BigDecimal deduccionTotal;
    private BigDecimal percepcionNeta;

    /**
     *
     */
    public SeguroPopularRhReporteDTO() {
        this(0, "", "", "", "", "", "", "", "", "", "", "", "", "",
                Calendar.getInstance().getTime(), BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /**
     *
     * @param numerConsecutivo
     * @param mes
     * @param estado
     * @param tipoDeCentroDeSaludODeHospital
     * @param clues
     * @param nombreUnidad
     * @param areaAdscripcion
     * @param puesto
     * @param claveDePuesto
     * @param servicio
     * @param rama
     * @param rombre
     * @param rfc
     * @param turno
     * @param fechaIngreso
     * @param c07SueldoBase
     * @param c30CompensacionRiesgo
     * @param c32PrimaDominical
     * @param c38Despensa
     * @param c42AsignacionBruta
     * @param c44PrevisionSocialMultiple
     * @param c45AyudaAnteojo
     * @param c46AyudaServicio
     * @param c55AyudaParaGastosActualizacion
     * @param cqqPrimaQuinquenal
     * @param percepcionTotal
     * @param c01ImpuestoSobreRenta
     * @param c02SrPensionesJubilaciones
     * @param c02SiServicioMedico
     * @param c02SsServicioSocialCultural
     * @param c03PrestamoPersonal
     * @param c04SsServiciosMedicosMaternidad
     * @param c04SpSeguroInvalidezVida
     * @param c09SeguroDanyosPrestamoAvaladoIssste
     * @param c17RetardoFalda
     * @param c21FondoAhorroCapitalizable
     * @param c29Responsabilidades
     * @param c34SeguroRiesgoProfesional
     * @param c46Inbursa
     * @param c46SeguroArgos
     * @param c46Etesa
     * @param c46LaTenda
     * @param c46PsPubliseg
     * @param c46SeguroGnp
     * @param c46AuditoriaOperacionCrediempleado
     * @param c46CreditoExpres
     * @param c51SeguroVidaInstitucional
     * @param c56PrestamoHipotecario
     * @param c57SeguroDeVidaAdicionalMetlife
     * @param c58CuotaSindical
     * @param c62PensionAlimenticia
     * @param c64FondoVivienda
     * @param c70FondoAhorroDefuncion
     * @param c77SeguroRetiroMetlife
     * @param c97DescuentosPromobien
     * @param casAhorroSolidario
     * @param deduccionTotal
     * @param percepcionNeta
     */
    public SeguroPopularRhReporteDTO(Integer numerConsecutivo, String mes,
            String estado, String tipoDeCentroDeSaludODeHospital, String clues,
            String nombreUnidad, String areaAdscripcion, String puesto,
            String claveDePuesto, String servicio, String rama, String rombre,
            String rfc, String turno, Date fechaIngreso,
            BigDecimal c07SueldoBase, BigDecimal c30CompensacionRiesgo,
            BigDecimal c32PrimaDominical, BigDecimal c38Despensa,
            BigDecimal c42AsignacionBruta,
            BigDecimal c44PrevisionSocialMultiple, BigDecimal c45AyudaAnteojo,
            BigDecimal c46AyudaServicio,
            BigDecimal c55AyudaParaGastosActualizacion,
            BigDecimal cqqPrimaQuinquenal, BigDecimal percepcionTotal,
            BigDecimal c01ImpuestoSobreRenta,
            BigDecimal c02SrPensionesJubilaciones,
            BigDecimal c02SiServicioMedico,
            BigDecimal c02SsServicioSocialCultural,
            BigDecimal c03PrestamoPersonal,
            BigDecimal c04SsServiciosMedicosMaternidad,
            BigDecimal c04SpSeguroInvalidezVida,
            BigDecimal c09SeguroDanyosPrestamoAvaladoIssste,
            BigDecimal c17RetardoFalda, BigDecimal c21FondoAhorroCapitalizable,
            BigDecimal c29Responsabilidades,
            BigDecimal c34SeguroRiesgoProfesional, BigDecimal c46Inbursa,
            BigDecimal c46SeguroArgos, BigDecimal c46Etesa,
            BigDecimal c46LaTenda, BigDecimal c46PsPubliseg,
            BigDecimal c46SeguroGnp,
            BigDecimal c46AuditoriaOperacionCrediempleado,
            BigDecimal c46CreditoExpres, BigDecimal c51SeguroVidaInstitucional,
            BigDecimal c56PrestamoHipotecario,
            BigDecimal c57SeguroDeVidaAdicionalMetlife,
            BigDecimal c58CuotaSindical, BigDecimal c62PensionAlimenticia,
            BigDecimal c64FondoVivienda, BigDecimal c70FondoAhorroDefuncion,
            BigDecimal c77SeguroRetiroMetlife,
            BigDecimal c97DescuentosPromobien, BigDecimal casAhorroSolidario,
            BigDecimal deduccionTotal, BigDecimal percepcionNeta) {
        numeroConsecutivo = numerConsecutivo;
        this.mes = mes;
        this.estado = estado;
        tipoCentroSaludHospital = tipoDeCentroDeSaludODeHospital;
        this.clues = clues;
        this.nombreUnidad = nombreUnidad;
        this.areaAdscripcion = areaAdscripcion;
        this.puesto = puesto;
        clavePuesto = claveDePuesto;
        this.servicio = servicio;
        this.rama = rama;
        nombre = rombre;
        this.rfc = rfc;
        this.turno = turno;
        this.fechaIngreso = fechaIngreso;
        this.c07SueldoBase = c07SueldoBase;
        this.c30CompensacionRiesgo = c30CompensacionRiesgo;
        this.c32PrimaDominical = c32PrimaDominical;
        this.c38Despensa = c38Despensa;
        this.c42AsignacionBruta = c42AsignacionBruta;
        this.c44PrevisionSocialMultiple = c44PrevisionSocialMultiple;
        this.c45AyudaAnteojo = c45AyudaAnteojo;
        this.c46AyudaServicio = c46AyudaServicio;
        this.c55AyudaParaGastosActualizacion = c55AyudaParaGastosActualizacion;
        this.cqqPrimaQuinquenal = cqqPrimaQuinquenal;
        this.percepcionTotal = percepcionTotal;
        this.c01ImpuestoSobreRenta = c01ImpuestoSobreRenta;
        c02SrPensionJubilacion = c02SrPensionesJubilaciones;
        this.c02SiServicioMedico = c02SiServicioMedico;
        this.c02SsServicioSocialCultural = c02SsServicioSocialCultural;
        this.c03PrestamoPersonal = c03PrestamoPersonal;
        c04SsServicioMedicoMaternidad = c04SsServiciosMedicosMaternidad;
        this.c04SpSeguroInvalidezVida = c04SpSeguroInvalidezVida;
        this.c09SeguroDanyosPrestamoAvaladoIssste = c09SeguroDanyosPrestamoAvaladoIssste;
        c17RetardoFalta = c17RetardoFalda;
        this.c21FondoAhorroCapitalizable = c21FondoAhorroCapitalizable;
        c29Responsabilidad = c29Responsabilidades;
        this.c34SeguroRiesgoProfesional = c34SeguroRiesgoProfesional;
        this.c46Inbursa = c46Inbursa;
        this.c46SeguroArgos = c46SeguroArgos;
        this.c46Etesa = c46Etesa;
        this.c46LaTenda = c46LaTenda;
        this.c46PsPubliseg = c46PsPubliseg;
        this.c46SeguroGnp = c46SeguroGnp;
        this.c46AuditoriaOperacionCrediempleado = c46AuditoriaOperacionCrediempleado;
        this.c46CreditoExpres = c46CreditoExpres;
        this.c51SeguroVidaInstitucional = c51SeguroVidaInstitucional;
        this.c56PrestamoHipotecario = c56PrestamoHipotecario;
        this.c57SeguroDeVidaAdicionalMetlife = c57SeguroDeVidaAdicionalMetlife;
        this.c58CuotaSindical = c58CuotaSindical;
        this.c62PensionAlimenticia = c62PensionAlimenticia;
        this.c64FondoVivienda = c64FondoVivienda;
        this.c70FondoAhorroDefuncion = c70FondoAhorroDefuncion;
        this.c77SeguroRetiroMetlife = c77SeguroRetiroMetlife;
        c97DescuentoPromobien = c97DescuentosPromobien;
        this.casAhorroSolidario = casAhorroSolidario;
        this.deduccionTotal = deduccionTotal;
        this.percepcionNeta = percepcionNeta;
    }

    /**
     *
     * @return
     */
    public Integer getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    /**
     *
     * @param numeroConsecutivo
     */
    public void setNumeroConsecutivo(Integer numeroConsecutivo) {
        this.numeroConsecutivo = numeroConsecutivo;
    }

    /**
     *
     * @return
     */
    public String getMes() {
        return mes;
    }

    /**
     *
     * @param mes
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     *
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     *
     * @return
     */
    public String getTipoCentroSaludHospital() {
        return tipoCentroSaludHospital;
    }

    /**
     *
     * @param tipoCentroSaludHospital
     */
    public void setTipoCentroSaludHospital(String tipoCentroSaludHospital) {
        this.tipoCentroSaludHospital = tipoCentroSaludHospital;
    }

    /**
     *
     * @return
     */
    public String getClues() {
        return clues;
    }

    /**
     *
     * @param clues
     */
    public void setClues(String clues) {
        this.clues = clues;
    }

    /**
     *
     * @return
     */
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    /**
     *
     * @param nombreUnidad
     */
    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    /**
     *
     * @return
     */
    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    /**
     *
     * @param areaAdscripcion
     */
    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    /**
     *
     * @return
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     *
     * @param puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     *
     * @return
     */
    public String getClavePuesto() {
        return clavePuesto;
    }

    /**
     *
     * @param clavePuesto
     */
    public void setClavePuesto(String clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    /**
     *
     * @return
     */
    public String getServicio() {
        return servicio;
    }

    /**
     *
     * @param servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     *
     * @return
     */
    public String getRama() {
        return rama;
    }

    /**
     *
     * @param rama
     */
    public void setRama(String rama) {
        this.rama = rama;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getRfc() {
        return rfc;
    }

    /**
     *
     * @param rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     *
     * @return
     */
    public String getTurno() {
        return turno;
    }

    /**
     *
     * @param turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     *
     * @return
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     *
     * @param fechaIngreso
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC07SueldoBase() {
        return c07SueldoBase;
    }

    /**
     *
     * @param c07SueldoBase
     */
    public void setC07SueldoBase(BigDecimal c07SueldoBase) {
        this.c07SueldoBase = c07SueldoBase;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC30CompensacionRiesgo() {
        return c30CompensacionRiesgo;
    }

    /**
     *
     * @param c30CompensacionRiesgo
     */
    public void setC30CompensacionRiesgo(BigDecimal c30CompensacionRiesgo) {
        this.c30CompensacionRiesgo = c30CompensacionRiesgo;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC32PrimaDominical() {
        return c32PrimaDominical;
    }

    /**
     *
     * @param c32PrimaDominical
     */
    public void setC32PrimaDominical(BigDecimal c32PrimaDominical) {
        this.c32PrimaDominical = c32PrimaDominical;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC38Despensa() {
        return c38Despensa;
    }

    /**
     *
     * @param c38Despensa
     */
    public void setC38Despensa(BigDecimal c38Despensa) {
        this.c38Despensa = c38Despensa;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC42AsignacionBruta() {
        return c42AsignacionBruta;
    }

    /**
     *
     * @param c42AsignacionBruta
     */
    public void setC42AsignacionBruta(BigDecimal c42AsignacionBruta) {
        this.c42AsignacionBruta = c42AsignacionBruta;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC44PrevisionSocialMultiple() {
        return c44PrevisionSocialMultiple;
    }

    /**
     *
     * @param c44PrevisionSocialMultiple
     */
    public void setC44PrevisionSocialMultiple(
            BigDecimal c44PrevisionSocialMultiple) {
        this.c44PrevisionSocialMultiple = c44PrevisionSocialMultiple;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC45AyudaAnteojo() {
        return c45AyudaAnteojo;
    }

    /**
     *
     * @param c45AyudaAnteojo
     */
    public void setC45AyudaAnteojo(BigDecimal c45AyudaAnteojo) {
        this.c45AyudaAnteojo = c45AyudaAnteojo;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46AyudaServicio() {
        return c46AyudaServicio;
    }

    /**
     *
     * @param c46AyudaServicio
     */
    public void setC46AyudaServicio(BigDecimal c46AyudaServicio) {
        this.c46AyudaServicio = c46AyudaServicio;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC55AyudaParaGastosActualizacion() {
        return c55AyudaParaGastosActualizacion;
    }

    /**
     *
     * @param c55AyudaParaGastosActualizacion
     */
    public void setC55AyudaParaGastosActualizacion(
            BigDecimal c55AyudaParaGastosActualizacion) {
        this.c55AyudaParaGastosActualizacion = c55AyudaParaGastosActualizacion;
    }

    /**
     *
     * @return
     */
    public BigDecimal getCqqPrimaQuinquenal() {
        return cqqPrimaQuinquenal;
    }

    /**
     *
     * @param cqqPrimaQuinquenal
     */
    public void setCqqPrimaQuinquenal(BigDecimal cqqPrimaQuinquenal) {
        this.cqqPrimaQuinquenal = cqqPrimaQuinquenal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getPercepcionTotal() {
        return percepcionTotal;
    }

    /**
     *
     * @param percepcionTotal
     */
    public void setPercepcionTotal(BigDecimal percepcionTotal) {
        this.percepcionTotal = percepcionTotal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC01ImpuestoSobreRenta() {
        return c01ImpuestoSobreRenta;
    }

    /**
     *
     * @param c01ImpuestoSobreRenta
     */
    public void setC01ImpuestoSobreRenta(BigDecimal c01ImpuestoSobreRenta) {
        this.c01ImpuestoSobreRenta = c01ImpuestoSobreRenta;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC02SrPensionJubilacion() {
        return c02SrPensionJubilacion;
    }

    /**
     *
     * @param c02SrPensionJubilacion
     */
    public void setC02SrPensionJubilacion(BigDecimal c02SrPensionJubilacion) {
        this.c02SrPensionJubilacion = c02SrPensionJubilacion;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC02SiServicioMedico() {
        return c02SiServicioMedico;
    }

    /**
     *
     * @param c02SiServicioMedico
     */
    public void setC02SiServicioMedico(BigDecimal c02SiServicioMedico) {
        this.c02SiServicioMedico = c02SiServicioMedico;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC02SsServicioSocialCultural() {
        return c02SsServicioSocialCultural;
    }

    /**
     *
     * @param c02SsServicioSocialCultural
     */
    public void setC02SsServicioSocialCultural(
            BigDecimal c02SsServicioSocialCultural) {
        this.c02SsServicioSocialCultural = c02SsServicioSocialCultural;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC03PrestamoPersonal() {
        return c03PrestamoPersonal;
    }

    /**
     *
     * @param c03PrestamoPersonal
     */
    public void setC03PrestamoPersonal(BigDecimal c03PrestamoPersonal) {
        this.c03PrestamoPersonal = c03PrestamoPersonal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC04SsServicioMedicoMaternidad() {
        return c04SsServicioMedicoMaternidad;
    }

    /**
     *
     * @param c04SsServicioMedicoMaternidad
     */
    public void setC04SsServicioMedicoMaternidad(
            BigDecimal c04SsServicioMedicoMaternidad) {
        this.c04SsServicioMedicoMaternidad = c04SsServicioMedicoMaternidad;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC04SpSeguroInvalidezVida() {
        return c04SpSeguroInvalidezVida;
    }

    /**
     *
     * @param c04SpSeguroInvalidezVida
     */
    public void setC04SpSeguroInvalidezVida(
            BigDecimal c04SpSeguroInvalidezVida) {
        this.c04SpSeguroInvalidezVida = c04SpSeguroInvalidezVida;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC09SeguroDanyosPrestamoAvaladoIssste() {
        return c09SeguroDanyosPrestamoAvaladoIssste;
    }

    /**
     *
     * @param c09SeguroDanyosPrestamoAvaladoIssste
     */
    public void setC09SeguroDanyosPrestamoAvaladoIssste(
            BigDecimal c09SeguroDanyosPrestamoAvaladoIssste) {
        this.c09SeguroDanyosPrestamoAvaladoIssste = c09SeguroDanyosPrestamoAvaladoIssste;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC17RetardoFalta() {
        return c17RetardoFalta;
    }

    /**
     *
     * @param c17RetardoFalta
     */
    public void setC17RetardoFalta(BigDecimal c17RetardoFalta) {
        this.c17RetardoFalta = c17RetardoFalta;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC21FondoAhorroCapitalizable() {
        return c21FondoAhorroCapitalizable;
    }

    /**
     *
     * @param c21FondoAhorroCapitalizable
     */
    public void setC21FondoAhorroCapitalizable(
            BigDecimal c21FondoAhorroCapitalizable) {
        this.c21FondoAhorroCapitalizable = c21FondoAhorroCapitalizable;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC29Responsabilidad() {
        return c29Responsabilidad;
    }

    /**
     *
     * @param c29Responsabilidad
     */
    public void setC29Responsabilidad(BigDecimal c29Responsabilidad) {
        this.c29Responsabilidad = c29Responsabilidad;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC34SeguroRiesgoProfesional() {
        return c34SeguroRiesgoProfesional;
    }

    /**
     *
     * @param c34SeguroRiesgoProfesional
     */
    public void setC34SeguroRiesgoProfesional(
            BigDecimal c34SeguroRiesgoProfesional) {
        this.c34SeguroRiesgoProfesional = c34SeguroRiesgoProfesional;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46Inbursa() {
        return c46Inbursa;
    }

    /**
     *
     * @param c46Inbursa
     */
    public void setC46Inbursa(BigDecimal c46Inbursa) {
        this.c46Inbursa = c46Inbursa;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46SeguroArgos() {
        return c46SeguroArgos;
    }

    /**
     *
     * @param c46SeguroArgos
     */
    public void setC46SeguroArgos(BigDecimal c46SeguroArgos) {
        this.c46SeguroArgos = c46SeguroArgos;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46Etesa() {
        return c46Etesa;
    }

    /**
     *
     * @param c46Etesa
     */
    public void setC46Etesa(BigDecimal c46Etesa) {
        this.c46Etesa = c46Etesa;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46LaTenda() {
        return c46LaTenda;
    }

    /**
     *
     * @param c46LaTenda
     */
    public void setC46LaTenda(BigDecimal c46LaTenda) {
        this.c46LaTenda = c46LaTenda;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46PsPubliseg() {
        return c46PsPubliseg;
    }

    /**
     *
     * @param c46PsPubliseg
     */
    public void setC46PsPubliseg(BigDecimal c46PsPubliseg) {
        this.c46PsPubliseg = c46PsPubliseg;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46SeguroGnp() {
        return c46SeguroGnp;
    }

    /**
     *
     * @param c46SeguroGnp
     */
    public void setC46SeguroGnp(BigDecimal c46SeguroGnp) {
        this.c46SeguroGnp = c46SeguroGnp;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46AuditoriaOperacionCrediempleado() {
        return c46AuditoriaOperacionCrediempleado;
    }

    /**
     *
     * @param c46AuditoriaOperacionCrediempleado
     */
    public void setC46AuditoriaOperacionCrediempleado(
            BigDecimal c46AuditoriaOperacionCrediempleado) {
        this.c46AuditoriaOperacionCrediempleado = c46AuditoriaOperacionCrediempleado;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC46CreditoExpres() {
        return c46CreditoExpres;
    }

    /**
     *
     * @param c46CreditoExpres
     */
    public void setC46CreditoExpres(BigDecimal c46CreditoExpres) {
        this.c46CreditoExpres = c46CreditoExpres;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC51SeguroVidaInstitucional() {
        return c51SeguroVidaInstitucional;
    }

    /**
     *
     * @param c51SeguroVidaInstitucional
     */
    public void setC51SeguroVidaInstitucional(
            BigDecimal c51SeguroVidaInstitucional) {
        this.c51SeguroVidaInstitucional = c51SeguroVidaInstitucional;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC56PrestamoHipotecario() {
        return c56PrestamoHipotecario;
    }

    /**
     *
     * @param c56PrestamoHipotecario
     */
    public void setC56PrestamoHipotecario(BigDecimal c56PrestamoHipotecario) {
        this.c56PrestamoHipotecario = c56PrestamoHipotecario;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC57SeguroDeVidaAdicionalMetlife() {
        return c57SeguroDeVidaAdicionalMetlife;
    }

    /**
     *
     * @param c57SeguroDeVidaAdicionalMetlife
     */
    public void setC57SeguroDeVidaAdicionalMetlife(
            BigDecimal c57SeguroDeVidaAdicionalMetlife) {
        this.c57SeguroDeVidaAdicionalMetlife = c57SeguroDeVidaAdicionalMetlife;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC58CuotaSindical() {
        return c58CuotaSindical;
    }

    /**
     *
     * @param c58CuotaSindical
     */
    public void setC58CuotaSindical(BigDecimal c58CuotaSindical) {
        this.c58CuotaSindical = c58CuotaSindical;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC62PensionAlimenticia() {
        return c62PensionAlimenticia;
    }

    /**
     *
     * @param c62PensionAlimenticia
     */
    public void setC62PensionAlimenticia(BigDecimal c62PensionAlimenticia) {
        this.c62PensionAlimenticia = c62PensionAlimenticia;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC64FondoVivienda() {
        return c64FondoVivienda;
    }

    /**
     *
     * @param c64FondoVivienda
     */
    public void setC64FondoVivienda(BigDecimal c64FondoVivienda) {
        this.c64FondoVivienda = c64FondoVivienda;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC70FondoAhorroDefuncion() {
        return c70FondoAhorroDefuncion;
    }

    /**
     *
     * @param c70FondoAhorroDefuncion
     */
    public void setC70FondoAhorroDefuncion(BigDecimal c70FondoAhorroDefuncion) {
        this.c70FondoAhorroDefuncion = c70FondoAhorroDefuncion;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC77SeguroRetiroMetlife() {
        return c77SeguroRetiroMetlife;
    }

    /**
     *
     * @param c77SeguroRetiroMetlife
     */
    public void setC77SeguroRetiroMetlife(BigDecimal c77SeguroRetiroMetlife) {
        this.c77SeguroRetiroMetlife = c77SeguroRetiroMetlife;
    }

    /**
     *
     * @return
     */
    public BigDecimal getC97DescuentoPromobien() {
        return c97DescuentoPromobien;
    }

    /**
     *
     * @param c97DescuentoPromobien
     */
    public void setC97DescuentoPromobien(BigDecimal c97DescuentoPromobien) {
        this.c97DescuentoPromobien = c97DescuentoPromobien;
    }

    /**
     *
     * @return
     */
    public BigDecimal getCasAhorroSolidario() {
        return casAhorroSolidario;
    }

    /**
     *
     * @param casAhorroSolidario
     */
    public void setCasAhorroSolidario(BigDecimal casAhorroSolidario) {
        this.casAhorroSolidario = casAhorroSolidario;
    }

    /**
     *
     * @return
     */
    public BigDecimal getDeduccionTotal() {
        return deduccionTotal;
    }

    /**
     *
     * @param deduccionTotal
     */
    public void setDeduccionTotal(BigDecimal deduccionTotal) {
        this.deduccionTotal = deduccionTotal;
    }

    /**
     *
     * @return
     */
    public BigDecimal getPercepcionNeta() {
        return percepcionNeta;
    }

    /**
     *
     * @param percepcionNeta
     */
    public void setPercepcionNeta(BigDecimal percepcionNeta) {
        this.percepcionNeta = percepcionNeta;
    }

}
