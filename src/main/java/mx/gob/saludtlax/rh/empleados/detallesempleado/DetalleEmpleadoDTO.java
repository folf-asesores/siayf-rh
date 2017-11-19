/*
 *
 */

package mx.gob.saludtlax.rh.empleados.detallesempleado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public class DetalleEmpleadoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6035046641674697297L;

    private String nombreCompleto;
    private String rfc;
    private String curp;
    private Date fechaNacimiento;
    private String telefono;
    private Date fechaAlta;
    private String direccionCompleta;
    private String tipoSangre;
    private String nacionalidad;
    private String estadoCivil;
    private String sexo;
    private Float estatura;
    private Float peso;
    private String estatus;
    private String correoElectronico;
    private Date fechaIngreso;
    private String tienePersonaDependiente;
    private String idDatoPersonal;
    private String numeroConyuge;
    private Integer numeroPadre;
    private Integer numeroHijo;
    private Integer numeroOtroParentesco;
    private String numeroCuenta;
    private String banco;
    private Integer numeroIdentificadorBiometrico;
    private String tipoEmpleado;
    private String claveCobro;
    private String metodoPago;
    private Integer numeroVacante;
    private String inventarioVacanteDisponible;
    private String codigoVacante;
    private String folioVacante;
    private String programa;
    private String adscripcion;
    private String areaAdscripcion;
    private String lugarAdscripcion;
    private String actividad;
    private String funcion;
    private String clues;
    private String seguroPopular;
    private String tipoJornada;
    private String proyectoDescripcion;
    private String dependenciaDescripccion;
    private String unidadResponsableDescripcion;
    private String tipoContratacionCodigo;
    private String puestoGeneralCodigo;
    private String fuenteFinanciamientoDescripcion;
    private String subfuenteFinanciamientoDescripcion;
    private String tipoRecursoDescripcion;
    private Integer idDatoLaboral;
    private String centroResponsabilidadDescripcion;
    private String centroResponsabilidadClave;
    private Integer cuentaBancariaClaveCuenta;
    private String cuentaBancariaBanco;
    private String cuentaBancariaDescripcion;
    private String configuracionPresupuestalEstado;
    private String jornadaDescripcion;
    private String plazaClave;
    private String plazaNombre;
    private BigDecimal sueldo;
    private BigDecimal sueldo01;
    private BigDecimal sueldo14;
    private String riesgoPuestoDescripcion;
    private String tipoTabuladorDescripcion;
    private String tipoPeriodo;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTienePersonaDependiente() {
        return tienePersonaDependiente;
    }

    public void setTienePersonaDependiente(String tienePersonaDependiente) {
        this.tienePersonaDependiente = tienePersonaDependiente;
    }

    public String getIdDatoPersonal() {
        return idDatoPersonal;
    }

    public void setIdDatoPersonal(String idDatoPersonal) {
        this.idDatoPersonal = idDatoPersonal;
    }

    public String getNumeroConyuge() {
        return numeroConyuge;
    }

    public void setNumeroConyuge(String numeroConyuge) {
        this.numeroConyuge = numeroConyuge;
    }

    public Integer getNumeroPadre() {
        return numeroPadre;
    }

    public void setNumeroPadre(Integer numeroPadre) {
        this.numeroPadre = numeroPadre;
    }

    public Integer getNumeroHijo() {
        return numeroHijo;
    }

    public void setNumeroHijo(Integer numeroHijo) {
        this.numeroHijo = numeroHijo;
    }

    public Integer getNumeroOtroParentesco() {
        return numeroOtroParentesco;
    }

    public void setNumeroOtroParentesco(Integer numeroOtroParentesco) {
        this.numeroOtroParentesco = numeroOtroParentesco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Integer getNumeroIdentificadorBiometrico() {
        return numeroIdentificadorBiometrico;
    }

    public void setNumeroIdentificadorBiometrico(
            Integer numeroIdentificadorBiometrico) {
        this.numeroIdentificadorBiometrico = numeroIdentificadorBiometrico;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getClaveCobro() {
        return claveCobro;
    }

    public void setClaveCobro(String claveCobro) {
        this.claveCobro = claveCobro;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Integer getNumeroVacante() {
        return numeroVacante;
    }

    public void setNumeroVacante(Integer numeroVacante) {
        this.numeroVacante = numeroVacante;
    }

    public String getInventarioVacanteDisponible() {
        return inventarioVacanteDisponible;
    }

    public void setInventarioVacanteDisponible(
            String inventarioVacanteDisponible) {
        this.inventarioVacanteDisponible = inventarioVacanteDisponible;
    }

    public String getCodigoVacante() {
        return codigoVacante;
    }

    public void setCodigoVacante(String codigoVacante) {
        this.codigoVacante = codigoVacante;
    }

    public String getFolioVacante() {
        return folioVacante;
    }

    public void setFolioVacante(String folioVacante) {
        this.folioVacante = folioVacante;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    public String getAreaAdscripcion() {
        return areaAdscripcion;
    }

    public void setAreaAdscripcion(String areaAdscripcion) {
        this.areaAdscripcion = areaAdscripcion;
    }

    public String getLugarAdscripcion() {
        return lugarAdscripcion;
    }

    public void setLugarAdscripcion(String lugarAdscripcion) {
        this.lugarAdscripcion = lugarAdscripcion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getClues() {
        return clues;
    }

    public void setClues(String clues) {
        this.clues = clues;
    }

    public String getSeguroPopular() {
        return seguroPopular;
    }

    public void setSeguroPopular(String seguroPopular) {
        this.seguroPopular = seguroPopular;
    }

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public String getProyectoDescripcion() {
        return proyectoDescripcion;
    }

    public void setProyectoDescripcion(String proyectoDescripcion) {
        this.proyectoDescripcion = proyectoDescripcion;
    }

    public String getUnidadResponsableDescripcion() {
        return unidadResponsableDescripcion;
    }

    public void setUnidadResponsableDescripcion(
            String unidadResponsableDescripcion) {
        this.unidadResponsableDescripcion = unidadResponsableDescripcion;
    }

    public String getTipoContratacionCodigo() {
        return tipoContratacionCodigo;
    }

    public void setTipoContratacionCodigo(String tipoContratacionCodigo) {
        this.tipoContratacionCodigo = tipoContratacionCodigo;
    }

    public String getPuestoGeneralCodigo() {
        return puestoGeneralCodigo;
    }

    public void setPuestoGeneralCodigo(String puestoGeneralCodigo) {
        this.puestoGeneralCodigo = puestoGeneralCodigo;
    }

    public String getSubfuenteFinanciamientoDescripcion() {
        return subfuenteFinanciamientoDescripcion;
    }

    public void setSubfuenteFinanciamientoDescripcion(
            String subfuenteFinanciamientoDescripcion) {
        this.subfuenteFinanciamientoDescripcion = subfuenteFinanciamientoDescripcion;
    }

    public String getTipoRecursoDescripcion() {
        return tipoRecursoDescripcion;
    }

    public void setTipoRecursoDescripcion(String tipoRecursoDescripcion) {
        this.tipoRecursoDescripcion = tipoRecursoDescripcion;
    }

    public Integer getIdDatoLaboral() {
        return idDatoLaboral;
    }

    public void setIdDatoLaboral(Integer idDatoLaboral) {
        this.idDatoLaboral = idDatoLaboral;
    }

    public String getCentroResponsabilidadDescripcion() {
        return centroResponsabilidadDescripcion;
    }

    public void setCentroResponsabilidadDescripcion(
            String centroResponsabilidadDescripcion) {
        this.centroResponsabilidadDescripcion = centroResponsabilidadDescripcion;
    }

    public String getCentroResponsabilidadClave() {
        return centroResponsabilidadClave;
    }

    public void setCentroResponsabilidadClave(
            String centroResponsabilidadClave) {
        this.centroResponsabilidadClave = centroResponsabilidadClave;
    }

    public Integer getCuentaBancariaClaveCuenta() {
        return cuentaBancariaClaveCuenta;
    }

    public void setCuentaBancariaClaveCuenta(
            Integer cuentaBancariaClaveCuenta) {
        this.cuentaBancariaClaveCuenta = cuentaBancariaClaveCuenta;
    }

    public String getCuentaBancariaBanco() {
        return cuentaBancariaBanco;
    }

    public void setCuentaBancariaBanco(String cuentaBancariaBanco) {
        this.cuentaBancariaBanco = cuentaBancariaBanco;
    }

    public String getCuentaBancariaDescripcion() {
        return cuentaBancariaDescripcion;
    }

    public void setCuentaBancariaDescripcion(String cuentaBancariaDescripcion) {
        this.cuentaBancariaDescripcion = cuentaBancariaDescripcion;
    }

    public String getConfiguracionPresupuestalEstado() {
        return configuracionPresupuestalEstado;
    }

    public void setConfiguracionPresupuestalEstado(
            String configuracionPresupuestalEstado) {
        this.configuracionPresupuestalEstado = configuracionPresupuestalEstado;
    }

    public String getJornadaDescripcion() {
        return jornadaDescripcion;
    }

    public void setJornadaDescripcion(String jornadaDescripcion) {
        this.jornadaDescripcion = jornadaDescripcion;
    }

    public String getPlazaClave() {
        return plazaClave;
    }

    public void setPlazaClave(String plazaClave) {
        this.plazaClave = plazaClave;
    }

    public String getPlazaNombre() {
        return plazaNombre;
    }

    public void setPlazaNombre(String plazaNombre) {
        this.plazaNombre = plazaNombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public BigDecimal getSueldo01() {
        return sueldo01;
    }

    public void setSueldo01(BigDecimal sueldo01) {
        this.sueldo01 = sueldo01;
    }

    public BigDecimal getSueldo14() {
        return sueldo14;
    }

    public void setSueldo14(BigDecimal sueldo14) {
        this.sueldo14 = sueldo14;
    }

    public String getRiesgoPuestoDescripcion() {
        return riesgoPuestoDescripcion;
    }

    public void setRiesgoPuestoDescripcion(String riesgoPuestoDescripcion) {
        this.riesgoPuestoDescripcion = riesgoPuestoDescripcion;
    }

    public String getTipoTabuladorDescripcion() {
        return tipoTabuladorDescripcion;
    }

    public void setTipoTabuladorDescripcion(String tipoTabuladorDescripcion) {
        this.tipoTabuladorDescripcion = tipoTabuladorDescripcion;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    /**
     * @return the fuenteFinanciamientoDescripcion
     */
    public String getFuenteFinanciamientoDescripcion() {
        return fuenteFinanciamientoDescripcion;
    }

    /**
     * @param fuenteFinanciamientoDescripcion
     *            the fuenteFinanciamientoDescripcion to set
     */
    public void setFuenteFinanciamientoDescripcion(
            String fuenteFinanciamientoDescripcion) {
        this.fuenteFinanciamientoDescripcion = fuenteFinanciamientoDescripcion;
    }

    /**
     * @return the dependenciaDescripccion
     */
    public String getDependenciaDescripccion() {
        return dependenciaDescripccion;
    }

    /**
     * @param dependenciaDescripccion
     *            the dependenciaDescripccion to set
     */
    public void setDependenciaDescripccion(String dependenciaDescripccion) {
        this.dependenciaDescripccion = dependenciaDescripccion;
    }

}
