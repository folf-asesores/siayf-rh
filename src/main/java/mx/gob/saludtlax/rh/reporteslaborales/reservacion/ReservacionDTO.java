package mx.gob.saludtlax.rh.reporteslaborales.reservacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public class ReservacionDTO implements Serializable {

    private static final long serialVersionUID = -1860005068233909223L;

    private Integer idMovimiento;
    private String asunto;
    private String presenteNombre;
    private String presenteClaveUno;
    private String presenteClaveDos;
    private String posicionUno;
    private String fecha;
    private Date fechaIngreso;
    private String claveOriginal;
    private String denominacionAlta;
    private String adscripcionComision;
    private String fechaDesignacion;
    private String claveDesignada;
    private String denominacion;
    private String posiciondos;
    private String directoraAdministracion;
    private String comunicado = "[Comunicado]";
    private String adscripcion;
    private String adscripcionAlta;
	private String encargado;
	private String clavePresupuestal;
	private String vigencia;
	private String solicitud;
	private String jefe;
    
    

    /**
     *
     * @return
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     *
     * @param asunto
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     *
     * @return
     */
    public String getPresenteNombre() {
        return presenteNombre;
    }

    /**
     *
     * @param presenteNombre
     */
    public void setPresenteNombre(String presenteNombre) {
        this.presenteNombre = presenteNombre;
    }

    /**
     *
     * @return
     */
    public String getPresenteClaveUno() {
        return presenteClaveUno;
    }

    /**
     *
     * @param presenteClaveUno
     */
    public void setPresenteClaveUno(String presenteClaveUno) {
        this.presenteClaveUno = presenteClaveUno;
    }

    /**
     *
     * @return
     */
    public String getPresenteClaveDos() {
        return presenteClaveDos;
    }

    /**
     *
     * @param presenteClaveDos
     */
    public void setPresenteClaveDos(String presenteClaveDos) {
        this.presenteClaveDos = presenteClaveDos;
    }

    /**
     *
     * @return
     */
    public String getPosicionUno() {
        return posicionUno;
    }

    /**
     *
     * @param posicionUno
     */
    public void setPosicionUno(String posicionUno) {
        this.posicionUno = posicionUno;
    }

    /**
     *
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * @return
     */
    public String getClaveOriginal() {
        return claveOriginal;
    }

    /**
     *
     * @param claveOriginal
     */
    public void setClaveOriginal(String claveOriginal) {
        this.claveOriginal = claveOriginal;
    }

    /**
     *
     * @return
     */
    public String getDenominacionAlta() {
        return denominacionAlta;
    }

    /**
     *
     * @param denominacionAlta
     */
    public void setDenominacionAlta(String denominacionAlta) {
        this.denominacionAlta = denominacionAlta;
    }

    /**
     *
     * @return
     */
    public String getAdscripcionComision() {
        return adscripcionComision;
    }

    /**
     *
     * @param adscripcionComision
     */
    public void setAdscripcionComision(String adscripcionComision) {
        this.adscripcionComision = adscripcionComision;
    }

    /**
     *
     * @return
     */
    public String getFechaDesignacion() {
        return fechaDesignacion;
    }

    /**
     *
     * @param fechaDesignacion
     */
    public void setFechaDesignacion(String fechaDesignacion) {
        this.fechaDesignacion = fechaDesignacion;
    }

    /**
     *
     * @return
     */
    public String getClaveDesignada() {
        return claveDesignada;
    }

    /**
     *
     * @param claveDesignada
     */
    public void setClaveDesignada(String claveDesignada) {
        this.claveDesignada = claveDesignada;
    }

    /**
     *
     * @return
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     *
     * @param denominacion
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     *
     * @return
     */
    public String getPosiciondos() {
        return posiciondos;
    }

    /**
     *
     * @param posicionDos
     */
    public void setPosiciondos(String posiciondos) {
        this.posiciondos = posiciondos;
    }

    /**
     *
     * @return
     */
    public String getDirectoraAdministracion() {
        return directoraAdministracion;
    }

    /**
     *
     * @param directoraAdministracion
     */
    public void setDirectoraAdministracion(String directoraAdministracion) {
        this.directoraAdministracion = directoraAdministracion;
    }

	public String getComunicado() {
		return comunicado;
	}

	public void setComunicado(String comunicado) {
		this.comunicado = comunicado;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getAdscripcionAlta() {
		return adscripcionAlta;
	}

	public void setAdscripcionAlta(String adscripcionAlta) {
		this.adscripcionAlta = adscripcionAlta;
	}

        public String getPosicionDos() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

		public String getEncargado() {
			return encargado;
		}

		public void setEncargado(String encargado) {
			this.encargado = encargado;
		}

		public String getClavePresupuestal() {
			return clavePresupuestal;
		}

		public void setClavePresupuestal(String clavePresupuestal) {
			this.clavePresupuestal = clavePresupuestal;
		}

		public String getVigencia() {
			return vigencia;
		}

		public void setVigencia(String vigencia) {
			this.vigencia = vigencia;
		}

		public String getSolicitud() {
			return solicitud;
		}

		public void setSolicitud(String solicitud) {
			this.solicitud = solicitud;
		}

		public String getJefe() {
			return jefe;
		}

		public void setJefe(String jefe) {
			this.jefe = jefe;
		}

}
