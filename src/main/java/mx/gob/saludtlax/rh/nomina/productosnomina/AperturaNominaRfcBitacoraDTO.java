/*
 * AperturaNominaRfcBitacoraDTO.java
 * Creado el 03/Jan/2017 5:29:45 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * La bitácora debe ayudar a contestar las siguientes preguntas cuándo ocurrio,
 * dónde ocurrio y qué ocurrio.
 * 
 * @see AperturaNominaRfcBitacoraEventoDTO
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class AperturaNominaRfcBitacoraDTO implements Serializable {
    
    private static final long serialVersionUID = -1188685157962720355L;

    private Integer idBitacora;
    private Integer idUsuario;
    private List<AperturaNominaRfcBitacoraEventoDTO> eventos;
    private Date fechaHora;

    public AperturaNominaRfcBitacoraDTO() {
        this(
                0,
                0,
                new ArrayList<AperturaNominaRfcBitacoraEventoDTO>(),
                Calendar.getInstance().getTime()
        );
    }
    
    public AperturaNominaRfcBitacoraDTO(Integer idBitacora, Integer idUsuario, List<AperturaNominaRfcBitacoraEventoDTO> eventos, Date fechaHora) {
        this.idBitacora = idBitacora;
        this.idUsuario = idUsuario;
        this.eventos = eventos;
        this.fechaHora = fechaHora;
    }
    
    /**
     * Get the value of idBitacora
     *
     * @return the value of idBitacora
     */
    public Integer getIdBitacora() {
        return idBitacora;
    }

    /**
     * Set the value of idBitacora
     *
     * @param idBitacora new value of idBitacora
     */
    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    /**
     * Get the value of idUsuario
     *
     * @return the value of idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Set the value of idUsuario
     *
     * @param idUsuario new value of idUsuario
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Set the value of eventos
     *
     * @param eventos  new the value of eventos
     */
    public void setEventos(List<AperturaNominaRfcBitacoraEventoDTO> eventos) {
        this.eventos = eventos;
    }

    /**
     * Get the value of eventos
     *
     * @return the value of eventos
     */
    public List<AperturaNominaRfcBitacoraEventoDTO> getEventos() {
        return eventos;
    }

    /**
     * Get the value of fechaHora
     *
     * @return the value of fechaHora
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Set the value of fechaHora
     *
     * @param fechaHora new value of fechaHora
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "AperturaNominaRfcBitacoraDTO{" 
                + "idBitacora=" + idBitacora
                + ", idUsuario=" + idUsuario
                + ", eventos=" + eventos
                + ", fechaHora=" + fechaHora
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(idBitacora);
        hash = 29 * hash + Objects.hashCode(idUsuario);
        hash = 29 * hash + Objects.hashCode(eventos);
        hash = 29 * hash + Objects.hashCode(fechaHora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AperturaNominaRfcBitacoraDTO other = (AperturaNominaRfcBitacoraDTO) obj;
        if (!Objects.equals(idBitacora, other.idBitacora)) {
            return false;
        }
        if (!Objects.equals(idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(eventos, other.eventos)) {
            return false;
        }
        return Objects.equals(fechaHora, other.fechaHora);
    }

}
