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

/**
 * @author Leila Schiaffini Ehuan
 *
 * @since 04/09/2016 21:02:52
 */
@Entity
@Table(name = "adscripciones")
public class AdscripcionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5399979720967258644L;
        
	@Id
	@Column(name = "id_adscripcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdscripcion;

	@Column(name = "adscripcion")
	private String adscripcion;
	
	@Column(name = "domicilio_servicio")
	private String domicilioServicio;
        
        @Column(name = "codigo_incidencias")
        private String codigoIncidencias;
        
	/**
	 * @return the domicilioServicio
	 */
	public String getDomicilioServicio() {
		return domicilioServicio;
	}

	/**
	 * @param domicilioServicio the domicilioServicio to set
	 */
	public void setDomicilioServicio(String domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public Integer getIdAdscripcion() {
		return idAdscripcion;
	}

        /**
         * Get the value of codigoIncidencias
         *
         * @return the value of codigoIncidencias
         */
        public String getCodigoIncidencias() {
            return codigoIncidencias;
        }

        /**
         * Set the value of codigoIncidencias
         *
         * @param codigoIncidencias new value of codigoIncidencias
         */
        public void setCodigoIncidencias(String codigoIncidencias) {
            this.codigoIncidencias = codigoIncidencias;
        }

}
