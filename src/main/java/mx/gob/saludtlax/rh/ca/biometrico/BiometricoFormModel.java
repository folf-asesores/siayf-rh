
package mx.gob.saludtlax.rh.ca.biometrico;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BiometricoFormModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2201558796539497760L;

    @SerializedName("IdEquipo")
    private Integer idEquipo;

    @SerializedName("IdTipoEquipo")
    private Integer idTipoEquipo;

    @SerializedName("Descripcion")
    private String descripcion;

    @SerializedName("IP")
    private String iP;

    @SerializedName("Ubicacion")
    private String ubicacion;

    @SerializedName("NumeroSerie")
    private String numeroSerie;

    @SerializedName("ClaveComunicacion")
    private Integer claveComunicacion;

    @SerializedName("Puerto")
    private Integer puerto;

    @SerializedName("Activo")
    private Integer activo;

    @SerializedName("IdServicioCliente")
    private Integer idClienteBiometrico;

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(Integer idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getiP() {
        return iP;
    }

    public void setiP(String iP) {
        this.iP = iP;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Integer getClaveComunicacion() {
        return claveComunicacion;
    }

    public void setClaveComunicacion(Integer claveComunicacion) {
        this.claveComunicacion = claveComunicacion;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public Integer getIdClienteBiometrico() {
        return idClienteBiometrico;
    }

    public void setIdClienteBiometrico(Integer idClienteBiometrico) {
        this.idClienteBiometrico = idClienteBiometrico;
    }

}
