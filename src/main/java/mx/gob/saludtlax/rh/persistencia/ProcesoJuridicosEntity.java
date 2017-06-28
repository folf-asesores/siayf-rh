/**
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

@Entity
@Table(name = "procesos_juridicos")
public class ProcesoJuridicosEntity implements Serializable {

    private static final long serialVersionUID = -3491218769714297031L;

    @Id
    @Column(name = "id_proceso_juridico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProcesoJuridico;

    @Column(name = "proceso")
    private String proceso;
//		<<<<<Getters & Setters>>>>>

    public Integer getIdProcesoJuridico() {
        return idProcesoJuridico;
    }

    public void setIdProcesoJuridico(Integer idProcesoJuridico) {
        this.idProcesoJuridico = idProcesoJuridico;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

}
