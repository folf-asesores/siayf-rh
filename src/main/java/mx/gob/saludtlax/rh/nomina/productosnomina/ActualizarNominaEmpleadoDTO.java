
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class ActualizarNominaEmpleadoDTO implements Serializable {
    private static final long serialVersionUID = -1911880525744673124L;

    private Boolean idConfiguracionPresupuestal;
    private Boolean idProyecto;
    private Boolean idDependencia;
    private Boolean idUnidadResponsable;
    private Boolean idTipoContratacion;
    private Boolean idTipoNombramiento;
    private Boolean idPuestoGeneral;
    private Boolean idFuenteFinanciamiento;
    private Boolean idSubfuenteFinanciamiento;
    private Boolean idTipoRecurso;
    private Boolean numeroEmpleado;
    private Boolean fechaAltaConfiguracion;
    private Boolean sueldos;
    private Boolean idTabulador;
    private Boolean numeroCuenta;
    private Boolean idCentroResponsabilidad;
    private Boolean numeroIdPersonal;
    private Boolean numeroIdLaboral;
    private Boolean idFuncion;
    private Boolean idPrograma;

    private String rfc;
    private String nombre;

    private Integer idNominaempleado;
    private Integer idCentroResponsabilidadValue;
    private Integer idConfiguracionPresupuestalValue;
    private Integer idDependenciaValue;
    private Date fechaAltaConfiguracionValue;
    private Integer idProyectoValue;
    private Integer idUnidadResponsableValue;
    private Integer idTipoContratacionValue;
    private Integer idTipoNombramientoValue;
    private Integer idPuestoGeneralValue;
    private Integer idFuenteFinanciamientoValue;
    private Integer idSubfuenteFinanciamientoValue;
    private Integer idTipoRecursoValue;
    private Integer numeroEmpleadoValue;
    private BigDecimal sueldo01;
    private BigDecimal sueldo14;
    private BigDecimal sueldoValue;
    private Integer idTabuladorValue;
    private String numeroCuentaValue;
    private String numeroIdPersonalValue;
    private Integer numeroIdLaboralValue;
    private Integer idFuncionValue;
    private Integer idProgramaValue;
    private String tipoCambio;

    public String getCambiosStr() {
        String str = null;
        System.out.println("tipoCambio: " + tipoCambio);
        switch (tipoCambio) {
            case "1":
                str = (idConfiguracionPresupuestal ? "Clave Presupuestal, " : "") + (idProyecto ? "Proyecto, " : "") + (idDependencia ? "Dependencia, " : "")
                        + (idUnidadResponsable ? "Unidad Responsable, " : "") + (idTipoContratacion ? "Tipo Contratacion, " : "")
                        + (idTipoNombramiento ? "Tipo Nombramiento, " : "") + (idPuestoGeneral ? "Puesto General, " : "")
                        + (idFuenteFinanciamiento ? "Fuente Financiamiento, " : "") + (idSubfuenteFinanciamiento ? "Subfuente Financiamiento, " : "")
                        + (idTipoRecurso ? "Tipo Recurso, " : "") + (numeroEmpleado ? "Numero Empleado, " : "")
                        + (fechaAltaConfiguracion ? "Fecha Alta Configuracion, " : "") + (sueldos ? "Sueldos, " : "") + (idTabulador ? "Tabulador, " : "")
                        + (numeroCuenta ? "Numero Cuenta, " : "") + (idCentroResponsabilidad ? "Centro Responsabilidad, " : "")
                        + (numeroIdPersonal ? "Numero Id Personal, " : "") + (numeroIdLaboral ? "Numero Id Laboral, " : "") + (idFuncion ? "Funcion, " : "")
                        + (idPrograma ? "Programa, " : "");
                str = str.substring(0, str.length() - 2);
                break;
            case "2":
                str = "ALTA";
                break;
            case "3":
                str = "BAJA";
                break;
        }
        return str;
    }

    public Boolean getIdConfiguracionPresupuestal() {
        return idConfiguracionPresupuestal;
    }

    public void setIdConfiguracionPresupuestal(Boolean idConfiguracionPresupuestal) {
        this.idConfiguracionPresupuestal = idConfiguracionPresupuestal;
    }

    public Boolean getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Boolean idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Boolean getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Boolean idDependencia) {
        this.idDependencia = idDependencia;
    }

    public Boolean getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(Boolean idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    public Boolean getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(Boolean idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public Boolean getIdTipoNombramiento() {
        return idTipoNombramiento;
    }

    public void setIdTipoNombramiento(Boolean idTipoNombramiento) {
        this.idTipoNombramiento = idTipoNombramiento;
    }

    public Boolean getIdPuestoGeneral() {
        return idPuestoGeneral;
    }

    public void setIdPuestoGeneral(Boolean idPuestoGeneral) {
        this.idPuestoGeneral = idPuestoGeneral;
    }

    public Boolean getIdFuenteFinanciamiento() {
        return idFuenteFinanciamiento;
    }

    public void setIdFuenteFinanciamiento(Boolean idFuenteFinanciamiento) {
        this.idFuenteFinanciamiento = idFuenteFinanciamiento;
    }

    public Boolean getIdSubfuenteFinanciamiento() {
        return idSubfuenteFinanciamiento;
    }

    public void setIdSubfuenteFinanciamiento(Boolean idSubfuenteFinanciamiento) {
        this.idSubfuenteFinanciamiento = idSubfuenteFinanciamiento;
    }

    public Boolean getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(Boolean idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public Boolean getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(Boolean numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public Boolean getFechaAltaConfiguracion() {
        return fechaAltaConfiguracion;
    }

    public void setFechaAltaConfiguracion(Boolean fechaAltaConfiguracion) {
        this.fechaAltaConfiguracion = fechaAltaConfiguracion;
    }

    public Boolean getSueldos() {
        return sueldos;
    }

    public void setSueldos(Boolean sueldos) {
        this.sueldos = sueldos;
    }

    public Boolean getIdTabulador() {
        return idTabulador;
    }

    public void setIdTabulador(Boolean idTabulador) {
        this.idTabulador = idTabulador;
    }

    public Boolean getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Boolean numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Boolean getIdCentroResponsabilidad() {
        return idCentroResponsabilidad;
    }

    public void setIdCentroResponsabilidad(Boolean idCentroResponsabilidad) {
        this.idCentroResponsabilidad = idCentroResponsabilidad;
    }

    public Boolean getNumeroIdPersonal() {
        return numeroIdPersonal;
    }

    public void setNumeroIdPersonal(Boolean numeroIdPersonal) {
        this.numeroIdPersonal = numeroIdPersonal;
    }

    public Boolean getNumeroIdLaboral() {
        return numeroIdLaboral;
    }

    public void setNumeroIdLaboral(Boolean numeroIdLaboral) {
        this.numeroIdLaboral = numeroIdLaboral;
    }

    public Boolean getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Boolean idFuncion) {
        this.idFuncion = idFuncion;
    }

    public Boolean getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Boolean idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdNominaempleado() {
        return idNominaempleado;
    }

    public void setIdNominaempleado(Integer idNominaempleado) {
        this.idNominaempleado = idNominaempleado;
    }

    public Integer getIdCentroResponsabilidadValue() {
        return idCentroResponsabilidadValue;
    }

    public void setIdCentroResponsabilidadValue(Integer idCentroResponsabilidadValue) {
        this.idCentroResponsabilidadValue = idCentroResponsabilidadValue;
    }

    public Integer getIdConfiguracionPresupuestalValue() {
        return idConfiguracionPresupuestalValue;
    }

    public void setIdConfiguracionPresupuestalValue(Integer idConfiguracionPresupuestalValue) {
        this.idConfiguracionPresupuestalValue = idConfiguracionPresupuestalValue;
    }

    public Integer getIdDependenciaValue() {
        return idDependenciaValue;
    }

    public void setIdDependenciaValue(Integer idDependenciaValue) {
        this.idDependenciaValue = idDependenciaValue;
    }

    public Date getFechaAltaConfiguracionValue() {
        return fechaAltaConfiguracionValue;
    }

    public void setFechaAltaConfiguracionValue(Date fechaAltaConfiguracionValue) {
        this.fechaAltaConfiguracionValue = fechaAltaConfiguracionValue;
    }

    public Integer getIdProyectoValue() {
        return idProyectoValue;
    }

    public void setIdProyectoValue(Integer idProyectoValue) {
        this.idProyectoValue = idProyectoValue;
    }

    public Integer getIdUnidadResponsableValue() {
        return idUnidadResponsableValue;
    }

    public void setIdUnidadResponsableValue(Integer idUnidadResponsableValue) {
        this.idUnidadResponsableValue = idUnidadResponsableValue;
    }

    public Integer getIdTipoContratacionValue() {
        return idTipoContratacionValue;
    }

    public void setIdTipoContratacionValue(Integer idTipoContratacionValue) {
        this.idTipoContratacionValue = idTipoContratacionValue;
    }

    public Integer getIdTipoNombramientoValue() {
        return idTipoNombramientoValue;
    }

    public void setIdTipoNombramientoValue(Integer idTipoNombramientoValue) {
        this.idTipoNombramientoValue = idTipoNombramientoValue;
    }

    public Integer getIdPuestoGeneralValue() {
        return idPuestoGeneralValue;
    }

    public void setIdPuestoGeneralValue(Integer idPuestoGeneralValue) {
        this.idPuestoGeneralValue = idPuestoGeneralValue;
    }

    public Integer getIdFuenteFinanciamientoValue() {
        return idFuenteFinanciamientoValue;
    }

    public void setIdFuenteFinanciamientoValue(Integer idFuenteFinanciamientoValue) {
        this.idFuenteFinanciamientoValue = idFuenteFinanciamientoValue;
    }

    public Integer getIdSubfuenteFinanciamientoValue() {
        return idSubfuenteFinanciamientoValue;
    }

    public void setIdSubfuenteFinanciamientoValue(Integer idSubfuenteFinanciamientoValue) {
        this.idSubfuenteFinanciamientoValue = idSubfuenteFinanciamientoValue;
    }

    public Integer getIdTipoRecursoValue() {
        return idTipoRecursoValue;
    }

    public void setIdTipoRecursoValue(Integer idTipoRecursoValue) {
        this.idTipoRecursoValue = idTipoRecursoValue;
    }

    public Integer getNumeroEmpleadoValue() {
        return numeroEmpleadoValue;
    }

    public void setNumeroEmpleadoValue(Integer numeroEmpleadoValue) {
        this.numeroEmpleadoValue = numeroEmpleadoValue;
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

    public BigDecimal getSueldoValue() {
        return sueldoValue;
    }

    public void setSueldoValue(BigDecimal sueldoValue) {
        this.sueldoValue = sueldoValue;
    }

    public Integer getIdTabuladorValue() {
        return idTabuladorValue;
    }

    public void setIdTabuladorValue(Integer idTabuladorValue) {
        this.idTabuladorValue = idTabuladorValue;
    }

    public String getNumeroCuentaValue() {
        return numeroCuentaValue;
    }

    public void setNumeroCuentaValue(String numeroCuentaValue) {
        this.numeroCuentaValue = numeroCuentaValue;
    }

    public String getNumeroIdPersonalValue() {
        return numeroIdPersonalValue;
    }

    public void setNumeroIdPersonalValue(String numeroIdPersonalValue) {
        this.numeroIdPersonalValue = numeroIdPersonalValue;
    }

    public Integer getNumeroIdLaboralValue() {
        return numeroIdLaboralValue;
    }

    public void setNumeroIdLaboralValue(Integer numeroIdLaboralValue) {
        this.numeroIdLaboralValue = numeroIdLaboralValue;
    }

    public Integer getIdFuncionValue() {
        return idFuncionValue;
    }

    public void setIdFuncionValue(Integer idFuncionValue) {
        this.idFuncionValue = idFuncionValue;
    }

    public Integer getIdProgramaValue() {
        return idProgramaValue;
    }

    public void setIdProgramaValue(Integer idProgramaValue) {
        this.idProgramaValue = idProgramaValue;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}