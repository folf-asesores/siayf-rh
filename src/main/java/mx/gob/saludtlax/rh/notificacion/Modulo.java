/*
 * Modulo.java
 * Creado el Aug 9, 2016 7:49:06 PM
 *
 */

package mx.gob.saludtlax.rh.notificacion;

/**
 * Este enumerable contiene la ruta de los modulos a los que se redireccionara
 * al enviar una notigficación.
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum Modulo {
    MI_BUZON("/contenido/notificaciones/miBuzon.xhtml"),
    AUTORIZAR_NOMINA(
            "/contenido/nomina/productos/ejecutarProductoNomina.xhtml"),
    SIN_MODULO("");

    public String getUrl() {
        return url;
    }

    Modulo(String url) {
        this.url = url;
    }

    private final String url;
}
