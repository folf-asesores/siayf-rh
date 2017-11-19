/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

/**
 * @author Eduardo Mex
 *
 */
public interface SeguroVidaInstitucional {

    public Integer crearSeguroVida(SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTO);

    public boolean existeNumeroExpediente(String numeroExpediente);

    public Integer existeEmpleado(Integer idEmpleado);

}
