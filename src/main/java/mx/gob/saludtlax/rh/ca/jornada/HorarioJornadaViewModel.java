
package mx.gob.saludtlax.rh.ca.jornada;

import java.io.Serializable;

public class HorarioJornadaViewModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5867688835361178219L;

    private Integer idHorarioJornada;

    private Integer idJornada;

    private Integer idDia;

    private String dia;

    private String horaEntrada;

    private String horaSalida;

    public Integer getIdHorarioJornada() {
        return idHorarioJornada;
    }

    public void setIdHorarioJornada(Integer idHorarioJornada) {
        this.idHorarioJornada = idHorarioJornada;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

}
