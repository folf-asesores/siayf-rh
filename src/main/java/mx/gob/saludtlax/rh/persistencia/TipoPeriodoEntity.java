package mx.gob.saludtlax.rh.persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipos_periodos")

public class TipoPeriodoEntity implements Serializable {

    private static final long serialVersionUID = 3111008823681449423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_periodo")
    private Integer idTipoPeriodo;
    @Column(name = "tipo_periodo")
    private String tipoPeriodo;

//	<Getters & Setters>
    public Integer getIdTipoPeriodo() {
        return idTipoPeriodo;
    }

    public void setIdTipoPeriodo(Integer idTipoPeriodo) {
        this.idTipoPeriodo = idTipoPeriodo;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

}
