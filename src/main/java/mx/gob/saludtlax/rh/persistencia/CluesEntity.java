package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clues")
public class CluesEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clues")
    private Integer idClues;

    @Column(name = "clues")
    private String clues;

    @Column(name = "clave_institucion_salud")
    private String claveInstitucionSalud;

    @Column(name = "clave_corta_institucion_salud")
    private String claveCortaInstitucionSalud;

    @Column(name = "nombre_institucion_salud")
    private String nombreInstitucionSalud;

    @Column(name = "clave_entidad")
    private String claveEntidad;

    @Column(name = "nombre_entidad")
    private String nombreEntidad;

    @Column(name = "clave_jurisdiccion")
    private String claveJurisdiccion;

    @Column(name = "nombre_jurisdiccion")
    private String nombreJurisdiccion;

    @Column(name = "clave_municipio")
    private String claveMunicipio;

    @Column(name = "nombre_municipio")
    private String nombreMunicipio;

    @Column(name = "clave_localidad")
    private String claveLocalidad;

    @Column(name = "nombre_localidad")
    private String nombreLocalidad;

    @Column(name = "cvercve")
    private String cveRcve;

    @Column(name = "cinscve")
    private String cinsCve;

    @Column(name = "ctuncve")
    private String ctunCve;

    @Column(name = "ccomcve")
    private String ccomCve;

    @Column(name = "clave_tipo_unidad")
    private String claveTipoUnidad;

    @Column(name = "nombre_tipo_unidad")
    private String nombreTipoUnidad;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "clave_tipologia")
    private String claveTipologia;

    @Column(name = "nombre_tipologia")
    private String nombreTipologia;

    @Column(name = "nombre_unidad")
    private String nombreUnidad;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "numero_interior")
    private String numeroInterior;

    @Column(name = "numero_exterior")
    private String numeroExterior;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "corta_estancia")
    private String cortaEstancia;

    @Column(name = "clave_estatus_salud")
    private String claveEstatusSalud;

    @Column(name = "estatus_salud")
    private String estatusSalud;

    public Integer getIdClues() {
        return idClues;
    }

    public void setIdClues(Integer idClues) {
        this.idClues = idClues;
    }

    public String getClues() {
        return clues;
    }

    public void setClues(String clues) {
        this.clues = clues;
    }

    public String getClaveInstitucionSalud() {
        return claveInstitucionSalud;
    }

    public void setClaveInstitucionSalud(String claveInstitucionSalud) {
        this.claveInstitucionSalud = claveInstitucionSalud;
    }

    public String getClaveCortaInstitucionSalud() {
        return claveCortaInstitucionSalud;
    }

    public void setClaveCortaInstitucionSalud(String claveCortaInstitucionSalud) {
        this.claveCortaInstitucionSalud = claveCortaInstitucionSalud;
    }

    public String getNombreInstitucionSalud() {
        return nombreInstitucionSalud;
    }

    public void setNombreInstitucionSalud(String nombreInstitucionSalud) {
        this.nombreInstitucionSalud = nombreInstitucionSalud;
    }

    public String getClaveEntidad() {
        return claveEntidad;
    }

    public void setClaveEntidad(String claveEntidad) {
        this.claveEntidad = claveEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getClaveJurisdiccion() {
        return claveJurisdiccion;
    }

    public void setClaveJurisdiccion(String claveJurisdiccion) {
        this.claveJurisdiccion = claveJurisdiccion;
    }

    public String getNombreJurisdiccion() {
        return nombreJurisdiccion;
    }

    public void setNombreJurisdiccion(String nombreJurisdiccion) {
        this.nombreJurisdiccion = nombreJurisdiccion;
    }

    public String getClaveMunicipio() {
        return claveMunicipio;
    }

    public void setClaveMunicipio(String claveMunicipio) {
        this.claveMunicipio = claveMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getClaveLocalidad() {
        return claveLocalidad;
    }

    public void setClaveLocalidad(String claveLocalidad) {
        this.claveLocalidad = claveLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getCveRcve() {
        return cveRcve;
    }

    public void setCveRcve(String cveRcve) {
        this.cveRcve = cveRcve;
    }

    public String getCinsCve() {
        return cinsCve;
    }

    public void setCinsCve(String cinsCve) {
        this.cinsCve = cinsCve;
    }

    public String getCtunCve() {
        return ctunCve;
    }

    public void setCtunCve(String ctunCve) {
        this.ctunCve = ctunCve;
    }

    public String getCcomCve() {
        return ccomCve;
    }

    public void setCcomCve(String ccomCve) {
        this.ccomCve = ccomCve;
    }

    public String getClaveTipoUnidad() {
        return claveTipoUnidad;
    }

    public void setClaveTipoUnidad(String claveTipoUnidad) {
        this.claveTipoUnidad = claveTipoUnidad;
    }

    public String getNombreTipoUnidad() {
        return nombreTipoUnidad;
    }

    public void setNombreTipoUnidad(String nombreTipoUnidad) {
        this.nombreTipoUnidad = nombreTipoUnidad;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getClaveTipologia() {
        return claveTipologia;
    }

    public void setClaveTipologia(String claveTipologia) {
        this.claveTipologia = claveTipologia;
    }

    public String getNombreTipologia() {
        return nombreTipologia;
    }

    public void setNombreTipologia(String nombreTipologia) {
        this.nombreTipologia = nombreTipologia;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCortaEstancia() {
        return cortaEstancia;
    }

    public void setCortaEstancia(String cortaEstancia) {
        this.cortaEstancia = cortaEstancia;
    }

    public String getClaveEstatusSalud() {
        return claveEstatusSalud;
    }

    public void setClaveEstatusSalud(String claveEstatusSalud) {
        this.claveEstatusSalud = claveEstatusSalud;
    }

    public String getEstatusSalud() {
        return estatusSalud;
    }

    public void setEstatusSalud(String estatusSalud) {
        this.estatusSalud = estatusSalud;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
