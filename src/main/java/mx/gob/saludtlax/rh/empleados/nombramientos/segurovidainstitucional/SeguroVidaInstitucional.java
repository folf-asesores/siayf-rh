/*
 *
 */

package mx.gob.saludtlax.rh.empleados.nombramientos.segurovidainstitucional;

/**
 * @author L.I. Eduardo B. C. Mex (lic.eduardo_mex@hotmail.com)
 *
 */
public interface SeguroVidaInstitucional {

    public Integer crearSeguroVida(
            SeguroVidaInstitucionalDTO seguroVidaInstitucionalDTO);

    public boolean existeNumeroExpediente(String numeroExpediente);

    public Integer existeEmpleado(Integer idEmpleado);

}
