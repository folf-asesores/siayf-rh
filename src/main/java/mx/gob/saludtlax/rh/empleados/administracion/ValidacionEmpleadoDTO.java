/*
 *
 */

package mx.gob.saludtlax.rh.empleados.administracion;

/**
 * @author Leila Schiaffini Ehuan
 * @since 12/09/2016 16:04:27
 *
 */
public class ValidacionEmpleadoDTO {

    private boolean esValido;
    private String mensaje;

    public boolean isEsValido() {
        return esValido;
    }

    public void setEsValido(boolean esValido) {
        this.esValido = esValido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
