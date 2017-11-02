/*
 * 
 * ConfiguracionAplicacionEntity.java
 * Creado el Aug 26, 2016 12:31:16 PM
 * 
 */
package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esta entidad define las configuraciones de la aplicación, por medio de 
 * clave/valor.
 * 
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Entity
@Table(name = "configuraciones_aplicacion")
public class ConfiguracionAplicacionEntity implements Serializable{
    
    private static final long serialVersionUID = -4904435898142990754L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_aplicacion")
    private Integer idConfiguracionAplicacion;
    
    @Column(name = "clave", nullable = false, unique = true)
    private String clave;
    
    @Column(name = "valor", nullable = true)
    private String valor;

    public ConfiguracionAplicacionEntity() {
    }

    public ConfiguracionAplicacionEntity(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public Integer getIdConfiguracionAplicacion() {
        return idConfiguracionAplicacion;
    }

    public void setIdConfiguracionAplicacion(Integer idConfiguracionAplicacion) {
        this.idConfiguracionAplicacion = idConfiguracionAplicacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
