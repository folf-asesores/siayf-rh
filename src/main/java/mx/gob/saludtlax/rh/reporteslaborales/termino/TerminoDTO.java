/*
 * TerminoDTO.java
 * Creado el 25/Nov/2016 5:34:54 AM
 * 
 */
package mx.gob.saludtlax.rh.reporteslaborales.termino;

import java.io.Serializable;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
public class TerminoDTO implements Serializable {

    private static final long serialVersionUID = 549197569142886639L;

    private Integer idMovimiento;
    private String asunto;
    private String presenteNombre;
    private String puesto;
    private String presenteClaveUno;
    private String presenteClaveDos;
    private String numeroOficio;
    private String fecha;
    private String encargado;
    private String fechaComision;
    private String clavePresupuestal;
    private String asignacion;
    private String encargadoAdministracion;
    private String posicionDos;
    private String posicionTres;
    private String posicionCuatro;
    private String reincorporacionTitular;
    private String fechaTermino;
    private String funcion;
    private String secretarioSalud;
    private String fechaPlaza;
    private String nuevaClave;
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
    public String getPuesto() {
        return puesto;
    }

    /**
     *
     * @param puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
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
    public String getNumeroOficio() {
        return numeroOficio;
    }

    /**
     *
     * @param numeroOficio
     */
    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
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
    public String getEncargado() {
        return encargado;
    }

    /**
     *
     * @param encargado
     */
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    /**
     *
     * @return
     */
    public String getFechaComision() {
        return fechaComision;
    }

    /**
     *
     * @param fechaComision
     */
    public void setFechaComision(String fechaComision) {
        this.fechaComision = fechaComision;
    }

    /**
     *
     * @return
     */
    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    /**
     *
     * @param clavePresupuestal
     */
    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    /**
     *
     * @return
     */
    public String getAsignacion() {
        return asignacion;
    }

    /**
     *
     * @param asignacion
     */
    public void setAsignacion(String asignacion) {
        this.asignacion = asignacion;
    }

    /**
     *
     * @return
     */
    public String getEncargadoAdministracion() {
        return encargadoAdministracion;
    }

    /**
     *
     * @param encargadoAdministracion
     */
    public void setEncargadoAdministracion(String encargadoAdministracion) {
        this.encargadoAdministracion = encargadoAdministracion;
    }

	public String getPosicionDos() {
		return posicionDos;
	}

	public void setPosicionDos(String posicionDos) {
		this.posicionDos = posicionDos;
	}

	public String getPosicionTres() {
		return posicionTres;
	}

	public void setPosicionTres(String posicionTres) {
		this.posicionTres = posicionTres;
	}

	public String getPosicionCuatro() {
		return posicionCuatro;
	}

	public void setPosicionCuatro(String posicionCuatro) {
		this.posicionCuatro = posicionCuatro;
	}

	public String getReincorporacionTitular() {
		return reincorporacionTitular;
	}

	public void setReincorporacionTitular(String reincorporacionTitular) {
		this.reincorporacionTitular = reincorporacionTitular;
	}

	public String getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getSecretarioSalud() {
		return secretarioSalud;
	}

	public void setSecretarioSalud(String secretarioSalud) {
		this.secretarioSalud = secretarioSalud;
	}

	public String getNuevaClave() {
		return nuevaClave;
	}

	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}

	public String getFechaPlaza() {
		return fechaPlaza;
	}

	public void setFechaPlaza(String fechaPlaza) {
		this.fechaPlaza = fechaPlaza;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public Integer getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

}
