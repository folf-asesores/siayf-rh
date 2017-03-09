/*
 * AperturaNominaRfcBitacoraEventoDTO.java
 * Creado el 03/Jan/2017 5:26:02 PM
 * 
 */
package mx.gob.saludtlax.rh.nomina.productosnomina;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class AperturaNominaRfcBitacoraEventoDTO implements Serializable {

    private static final long serialVersionUID = -4684129284524430638L;

    private Integer idEvento;
    private AperturaNominaRfcBitacoraCategoria categoria;
    private Date fechaHora;
    private String mensaje;

    public AperturaNominaRfcBitacoraEventoDTO() {
        this(
                0,
                AperturaNominaRfcBitacoraCategoria.INFORMACION,
                Calendar.getInstance().getTime(),
                ""
        );
    }

    public AperturaNominaRfcBitacoraEventoDTO(Integer idEvento, AperturaNominaRfcBitacoraCategoria categoria, Date fechaHora, String mensaje) {
        this.idEvento = idEvento;
        this.categoria = categoria;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
    }

    /**
     * Get the value of idEvento
     *
     * @return the value of idEvento
     */
    public Integer getIdEvento() {
        return idEvento;
    }

    /**
     * Set the value of idEvento
     *
     * @param idEvento new value of idEvento
     */
    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Get the value of categoria
     *
     * @return the value of categoria
     */
    public AperturaNominaRfcBitacoraCategoria getCategoria() {
        return categoria;
    }

    /**
     * Set the value of categoria
     *
     * @param categoria new value of categoria
     */
    public void setCategoria(AperturaNominaRfcBitacoraCategoria categoria) {
        this.categoria = categoria;
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

    /**
     * Get the value of mensaje
     *
     * @return the value of mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Set the value of mensaje
     *
     * @param mensaje new value of mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "AperturaNominaRfcBitacoraEvento{" 
                + "idEvento=" + idEvento 
                + ", categoria=" + categoria 
                + ", fechaHora=" + fechaHora 
                + ", mensaje=" + mensaje 
                + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 12;
        hash = 37 * hash + Objects.hashCode(idEvento);
        hash = 37 * hash + Objects.hashCode(categoria);
        hash = 37 * hash + Objects.hashCode(fechaHora);
        hash = 37 * hash + Objects.hashCode(mensaje);
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
        final AperturaNominaRfcBitacoraEventoDTO other = (AperturaNominaRfcBitacoraEventoDTO) obj;
        if (!Objects.equals(mensaje, other.mensaje)) {
            return false;
        }
        if (!Objects.equals(idEvento, other.idEvento)) {
            return false;
        }
        if (categoria != other.categoria) {
            return false;
        }
        return Objects.equals(fechaHora, other.fechaHora);
    }

}
