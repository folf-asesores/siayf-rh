/*
 *  ValidacionCodigoError.java
 *  Creado el Jun 17, 2016 4:42:48 PM
 *
 */

package mx.gob.saludtlax.rh.excepciones;

/**
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
public enum ValidacionCodigoError implements CodigoError {
    VALOR_REQUERIDO(3000), FORMATO_INVALIDO(3001), VALOR_MUY_CORTO(3002),
    VALOR_MUY_LARGO(3003), NUMERO_NEGATIVO(3004), VALOR_DUPLICADO(3005),
    REGISTRO_NO_ENCONTRADO(3006), VALOR_NO_PERMITIDO(3007),
    FECHA_INCORRECTA(3008), ESTATUS_INCORRECTO(3009);

    ValidacionCodigoError(int numero) {
        this.numero = numero;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    private final int numero;
}
