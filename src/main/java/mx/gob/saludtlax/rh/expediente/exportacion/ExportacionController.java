/*
 * ExportacionController.java
 * Creado el Sep 10, 2016 8:33:11 AM
 * 
 */
package mx.gob.saludtlax.rh.expediente.exportacion;

import java.io.Serializable;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Freddy Barrera (freddy.barrera@folfasesores.com.mx)
 */
@Named
@SessionScoped
public class ExportacionController implements Serializable {

    private static final long serialVersionUID = -7783053924034816824L;

    
    private Integer posicion;
	private Integer tamanyo;
    
    public long iniciarTarea() throws Exception {
        String jobName =  "expedientesExportacion";

        Properties properties = new Properties();
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        properties.setProperty("posicion", posicion.toString());
		properties.setProperty("tamanyo", tamanyo.toString());
        long executionID = jobOperator.start(jobName, properties);

        //try { Thread.sleep(3000); } catch (Exception ex) {}

        return executionID;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }
	
	public Integer getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(Integer tamanyo) {
        this.tamanyo = tamanyo;
    }
}
